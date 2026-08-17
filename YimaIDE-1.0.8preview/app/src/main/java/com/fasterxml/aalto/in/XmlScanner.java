package com.fasterxml.aalto.in;

import com.fasterxml.aalto.WFCException;
import com.fasterxml.aalto.impl.ErrorConsts;
import com.fasterxml.aalto.impl.IoStreamException;
import com.fasterxml.aalto.impl.LocationImpl;
import com.fasterxml.aalto.util.DataUtil;
import com.fasterxml.aalto.util.EmptyIterator;
import com.fasterxml.aalto.util.IllegalCharHandler;
import com.fasterxml.aalto.util.SingletonIterator;
import com.fasterxml.aalto.util.TextBuilder;
import com.fasterxml.aalto.util.XmlChars;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.XMLStreamLocation2;
import org.codehaus.stax2.typed.TypedValueDecoder;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class XmlScanner implements NamespaceContext, XMLStreamConstants {
    protected final AttributeCollector _attrCollector;
    protected final boolean _cfgCoalescing;
    protected boolean _cfgLazyParsing;
    protected final ReaderConfig _config;
    protected ElementScope _currElem;
    protected char[] _nameBuffer;
    protected NsBinding[] _nsBindings;
    protected long _pastBytesOrChars;
    protected String _publicId;
    protected int _rowStartOffset;
    protected long _startRawOffset;
    protected String _systemId;
    protected final TextBuilder _textBuilder;
    protected final boolean _xml11;
    protected final String CDATA_STR = "CDATA[";
    protected int _currToken = 7;
    protected boolean _tokenIncomplete = false;
    protected int _depth = 0;
    protected boolean _entityPending = false;
    protected PName _tokenName = null;
    protected boolean _isEmptyTag = false;
    protected NsDeclaration _lastNsDecl = null;
    protected int _currNsCount = 0;
    protected NsBinding _defaultNs = NsBinding.createDefaultNs();
    protected int _nsBindingCount = 0;
    protected PName[] _nsBindingCache = null;
    protected int _nsBindMisses = 0;
    protected FixedNsContext _lastNsContext = FixedNsContext.EMPTY_CONTEXT;
    protected int _attrCount = 0;
    protected long _startRow = -1;
    protected long _startColumn = -1;
    protected int _currRow = 0;

    public XmlScanner(ReaderConfig readerConfig) {
        this._nameBuffer = null;
        this._config = readerConfig;
        this._cfgCoalescing = readerConfig.willCoalesceText();
        this._cfgLazyParsing = readerConfig.willParseLazily();
        this._xml11 = readerConfig.isXml11();
        this._textBuilder = TextBuilder.createRecyclableBuffer(readerConfig);
        this._attrCollector = new AttributeCollector(readerConfig);
        this._nameBuffer = readerConfig.allocSmallCBuffer(60);
    }

    private NsDeclaration findCurrNsDecl(int i) {
        int i2;
        int i3 = this._depth;
        if (this._currToken == 1) {
            i2 = (this._currNsCount - 1) - i;
            i3--;
        } else {
            i2 = i;
        }
        for (NsDeclaration prev = this._lastNsDecl; prev != null && prev.getLevel() == i3; prev = prev.getPrev()) {
            if (i2 == 0) {
                return prev;
            }
            i2--;
        }
        reportInvalidNsIndex(i);
        return null;
    }

    public abstract void _closeSource() throws IOException;

    public void _releaseBuffers() {
        this._textBuilder.recycle(true);
        char[] cArr = this._nameBuffer;
        if (cArr != null) {
            this._nameBuffer = null;
            this._config.freeSmallCBuffer(cArr);
        }
    }

    public final PName bindName(PName pName, String str) {
        PName pName2;
        PName[] pNameArr = this._nsBindingCache;
        if (pNameArr != null && (pName2 = pNameArr[pName.unboundHashCode() & 63]) != null && pName2.unboundEquals(pName)) {
            return pName2;
        }
        int i = this._nsBindingCount;
        for (int i2 = 0; i2 < i; i2++) {
            NsBinding[] nsBindingArr = this._nsBindings;
            NsBinding nsBinding = nsBindingArr[i2];
            if (nsBinding.mPrefix == str) {
                if (i2 > 0) {
                    int i3 = i2 - 1;
                    nsBindingArr[i2] = nsBindingArr[i3];
                    nsBindingArr[i3] = nsBinding;
                }
                PName pNameCreateBoundName = pName.createBoundName(nsBinding);
                if (this._nsBindingCache == null) {
                    int i4 = this._nsBindMisses + 1;
                    this._nsBindMisses = i4;
                    if (i4 < 10) {
                        return pNameCreateBoundName;
                    }
                    this._nsBindingCache = new PName[64];
                }
                this._nsBindingCache[pNameCreateBoundName.unboundHashCode() & 63] = pNameCreateBoundName;
                return pNameCreateBoundName;
            }
        }
        if (str == "xml") {
            return pName.createBoundName(NsBinding.XML_BINDING);
        }
        this._nsBindMisses++;
        NsBinding nsBinding2 = new NsBinding(str);
        int i5 = this._nsBindingCount;
        if (i5 == 0) {
            this._nsBindings = new NsBinding[16];
        } else {
            NsBinding[] nsBindingArr2 = this._nsBindings;
            if (i5 >= nsBindingArr2.length) {
                this._nsBindings = (NsBinding[]) DataUtil.growAnyArrayBy(nsBindingArr2, nsBindingArr2.length);
            }
        }
        NsBinding[] nsBindingArr3 = this._nsBindings;
        int i6 = this._nsBindingCount;
        nsBindingArr3[i6] = nsBinding2;
        this._nsBindingCount = i6 + 1;
        return pName.createBoundName(nsBinding2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void bindNs(PName pName, String str) throws XMLStreamException {
        NsBinding nsBindingFindOrCreateBinding;
        String prefix = pName.getPrefix();
        if (prefix == null) {
            nsBindingFindOrCreateBinding = this._defaultNs;
        } else {
            prefix = pName.getLocalName();
            nsBindingFindOrCreateBinding = findOrCreateBinding(prefix);
            if (nsBindingFindOrCreateBinding.isImmutable()) {
                checkImmutableBinding(prefix, str);
            }
        }
        if (!nsBindingFindOrCreateBinding.isImmutable()) {
            if (str == "http://www.w3.org/XML/1998/namespace") {
                reportIllegalNsDecl("xml", "http://www.w3.org/XML/1998/namespace");
            } else if (str == "http://www.w3.org/2000/xmlns/") {
                reportIllegalNsDecl("xmlns", "http://www.w3.org/2000/xmlns/");
            }
        }
        NsDeclaration nsDeclaration = this._lastNsDecl;
        if (nsDeclaration != null && nsDeclaration.alreadyDeclared(prefix, this._depth)) {
            reportDuplicateNsDecl(prefix);
        }
        this._lastNsDecl = new NsDeclaration(nsBindingFindOrCreateBinding, str, this._lastNsDecl, this._depth);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void checkImmutableBinding(String str, String str2) throws XMLStreamException {
        if (str == "xml" && str2.equals("http://www.w3.org/XML/1998/namespace")) {
            return;
        }
        reportIllegalNsDecl(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void close(boolean z) throws XMLStreamException {
        _releaseBuffers();
        if (z || this._config.willAutoCloseInput()) {
            try {
                _closeSource();
            } catch (IOException e) {
                throw new IoStreamException(e);
            }
        }
    }

    public final void decodeAttrValue(int i, TypedValueDecoder typedValueDecoder) throws XMLStreamException {
        this._attrCollector.decodeValue(i, typedValueDecoder);
    }

    public final NsBinding findOrCreateBinding(String str) throws XMLStreamException {
        int i = this._nsBindingCount;
        for (int i2 = 0; i2 < i; i2++) {
            NsBinding[] nsBindingArr = this._nsBindings;
            NsBinding nsBinding = nsBindingArr[i2];
            if (nsBinding.mPrefix == str) {
                if (i2 > 0) {
                    int i3 = i2 - 1;
                    nsBindingArr[i2] = nsBindingArr[i3];
                    nsBindingArr[i3] = nsBinding;
                }
                return nsBinding;
            }
        }
        if (str == "xml") {
            return NsBinding.XML_BINDING;
        }
        if (str == "xmlns") {
            return NsBinding.XMLNS_BINDING;
        }
        NsBinding nsBinding2 = new NsBinding(str);
        int i4 = this._nsBindingCount;
        if (i4 == 0) {
            this._nsBindings = new NsBinding[16];
        } else {
            NsBinding[] nsBindingArr2 = this._nsBindings;
            if (i4 >= nsBindingArr2.length) {
                this._nsBindings = (NsBinding[]) DataUtil.growAnyArrayBy(nsBindingArr2, nsBindingArr2.length);
            }
        }
        NsBinding[] nsBindingArr3 = this._nsBindings;
        int i5 = this._nsBindingCount;
        nsBindingArr3[i5] = nsBinding2;
        this._nsBindingCount = i5 + 1;
        return nsBinding2;
    }

    public abstract void finishCData() throws XMLStreamException;

    public abstract void finishCharacters() throws XMLStreamException;

    public abstract void finishComment() throws XMLStreamException;

    public abstract void finishDTD(boolean z) throws XMLStreamException;

    public abstract void finishPI() throws XMLStreamException;

    public abstract void finishSpace() throws XMLStreamException;

    public abstract void finishToken() throws XMLStreamException;

    public final int getAttrCount() {
        return this._attrCount;
    }

    public final String getAttrLocalName(int i) {
        return this._attrCollector.getName(i).getLocalName();
    }

    public final String getAttrNsURI(int i) {
        return this._attrCollector.getName(i).getNsUri();
    }

    public final String getAttrPrefix(int i) {
        return this._attrCollector.getName(i).getPrefix();
    }

    public final QName getAttrQName(int i) {
        return this._attrCollector.getQName(i);
    }

    public final String getAttrType(int i) {
        return "CDATA";
    }

    public final String getAttrValue(String str, String str2) {
        if (this._attrCount < 1) {
            return null;
        }
        return this._attrCollector.getValue(str, str2);
    }

    public ReaderConfig getConfig() {
        return this._config;
    }

    public abstract XMLStreamLocation2 getCurrentLocation();

    public final PName getName() {
        return this._tokenName;
    }

    public final String getNamespacePrefix(int i) {
        return findCurrNsDecl(i).getBinding().mPrefix;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getNamespaceURI(String str) {
        if (str == null) {
            w01.a(ErrorConsts.ERR_NULL_ARG);
            return null;
        }
        if (str.length() == 0) {
            String str2 = this._defaultNs.mURI;
            return str2 == null ? XmlPullParser.NO_NAMESPACE : str2;
        }
        if (str.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if (str.equals("xmlns")) {
            return "http://www.w3.org/2000/xmlns/";
        }
        for (NsDeclaration prev = this._lastNsDecl; prev != null; prev = prev.getPrev()) {
            if (prev.hasPrefix(str)) {
                return prev.getCurrNsURI();
            }
        }
        return null;
    }

    public final int getNsCount() {
        if (this._currToken == 1) {
            return this._currNsCount;
        }
        NsDeclaration nsDeclaration = this._lastNsDecl;
        if (nsDeclaration == null) {
            return 0;
        }
        return nsDeclaration.countDeclsOnLevel(this._depth);
    }

    @Override // javax.xml.namespace.NamespaceContext
    public String getPrefix(String str) {
        String prefix;
        if (str == null) {
            w01.a(ErrorConsts.ERR_NULL_ARG);
            return null;
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            return "xml";
        }
        if (str.equals("http://www.w3.org/2000/xmlns/")) {
            return "xmlns";
        }
        if (str.equals(this._defaultNs.mURI)) {
            return XmlPullParser.NO_NAMESPACE;
        }
        for (NsDeclaration prev = this._lastNsDecl; prev != null; prev = prev.getPrev()) {
            if (prev.hasNsURI(str) && (prefix = prev.getPrefix()) != null) {
                for (NsDeclaration prev2 = this._lastNsDecl; prev2 != prev; prev2 = prev2.getPrev()) {
                    if (!prev2.hasPrefix(prefix)) {
                    }
                }
                return prefix;
            }
        }
        return null;
    }

    @Override // javax.xml.namespace.NamespaceContext
    public Iterator<String> getPrefixes(String str) {
        String prefix;
        ArrayList arrayList = null;
        if (str == null) {
            w01.a(ErrorConsts.ERR_NULL_ARG);
            return null;
        }
        if (str.equals("http://www.w3.org/XML/1998/namespace")) {
            return new SingletonIterator("xml");
        }
        if (str.equals("http://www.w3.org/2000/xmlns/")) {
            return new SingletonIterator("xmlns");
        }
        if (str.equals(this._defaultNs.mURI)) {
            arrayList = new ArrayList();
            arrayList.add(XmlPullParser.NO_NAMESPACE);
        }
        for (NsDeclaration prev = this._lastNsDecl; prev != null; prev = prev.getPrev()) {
            if (prev.hasNsURI(str) && (prefix = prev.getPrefix()) != null) {
                NsDeclaration prev2 = this._lastNsDecl;
                while (true) {
                    if (prev2 == prev) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(prefix);
                        break;
                    }
                    if (prev2.hasPrefix(prefix)) {
                        break;
                    }
                    prev2 = prev2.getPrev();
                }
            }
        }
        if (arrayList == null) {
            return EmptyIterator.getInstance();
        }
        return arrayList.size() == 1 ? new SingletonIterator((String) arrayList.get(0)) : arrayList.iterator();
    }

    public final QName getQName() {
        return this._tokenName.constructQName(this._defaultNs);
    }

    public final XMLStreamLocation2 getStartLocation() {
        return LocationImpl.fromZeroBased(this._config.getPublicId(), this._config.getSystemId(), this._startRawOffset, (int) this._startRow, (int) this._startColumn);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final String getText() throws XMLStreamException {
        if (this._tokenIncomplete) {
            finishToken();
        }
        return this._textBuilder.contentsAsString();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final char[] getTextCharacters() throws XMLStreamException {
        if (this._tokenIncomplete) {
            finishToken();
        }
        return this._textBuilder.getTextBuffer();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final int getTextLength() throws XMLStreamException {
        if (this._tokenIncomplete) {
            finishToken();
        }
        return this._textBuilder.size();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public char handleInvalidXmlChar(int i) throws XMLStreamException {
        IllegalCharHandler illegalCharHandler = this._config.getIllegalCharHandler();
        if (illegalCharHandler != null) {
            return illegalCharHandler.convertIllegalChar(i);
        }
        char c = (char) i;
        if (c == 0) {
            throwNullChar();
        }
        String strConcat = "Illegal XML character (" + XmlChars.getCharDesc(c) + ")";
        if (this._xml11 && i < 32) {
            strConcat = strConcat.concat(" [note: in XML 1.1, it could be included via entity expansion]");
        }
        reportInputProblem(strConcat);
        return c;
    }

    public final boolean hasEmptyStack() {
        return this._depth == 0;
    }

    public final boolean isAttrSpecified(int i) {
        return true;
    }

    public final boolean isEmptyTag() {
        return this._isEmptyTag;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final boolean isTextWhitespace() throws XMLStreamException {
        if (this._tokenIncomplete) {
            finishToken();
        }
        return this._textBuilder.isAllWhitespace();
    }

    public abstract boolean loadMore() throws XMLStreamException;

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void loadMoreGuaranteed() throws XMLStreamException {
        if (loadMore()) {
            return;
        }
        reportInputProblem("Unexpected end-of-input when trying to parse " + ErrorConsts.tokenTypeDesc(this._currToken));
    }

    public abstract int nextFromProlog(boolean z) throws XMLStreamException;

    public abstract int nextFromTree() throws XMLStreamException;

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportDoubleHyphenInComments() throws XMLStreamException {
        reportInputProblem("String '--' not allowed in comment (missing '>'?)");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportDuplicateNsDecl(String str) throws XMLStreamException {
        if (str == null) {
            reportInputProblem("Duplicate namespace declaration for the default namespace");
            return;
        }
        reportInputProblem("Duplicate namespace declaration for prefix '" + str + "'");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportEntityOverflow() throws XMLStreamException {
        reportInputProblem("Illegal character entity: value higher than max allowed (0x" + Integer.toHexString(1114111) + ")");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportEofInName(char[] cArr, int i) throws XMLStreamException {
        reportInputProblem("Unexpected end-of-input in name (parsing " + ErrorConsts.tokenTypeDesc(this._currToken) + ")");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportIllegalCDataEnd() throws XMLStreamException {
        reportInputProblem("String ']]>' not allowed in textual content, except as the end marker of CDATA section");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportIllegalNsDecl(String str, String str2) throws XMLStreamException {
        reportInputProblem("Illegal namespace declaration: can not bind URI '" + str2 + "' to prefix other than '" + str + "'");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportInputProblem(String str) throws XMLStreamException {
        throw new WFCException(str, getCurrentLocation());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportInvalidNameChar(int i, int i2) throws XMLStreamException {
        if (i == 58) {
            reportInputProblem("Invalid colon in name: at most one colon allowed in element/attribute names, and none in PI target or entity names");
        }
        if (i2 == 0) {
            reportInputProblem("Invalid name start character (0x" + Integer.toHexString(i) + ")");
        }
        reportInputProblem("Invalid name character (0x" + Integer.toHexString(i) + ")");
    }

    public void reportInvalidNsIndex(int i) {
        throw new IndexOutOfBoundsException("Illegal namespace declaration index, " + i + ", current START_ELEMENT/END_ELEMENT has " + getNsCount() + " declarations");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportInvalidXmlChar(int i) throws XMLStreamException {
        if (i == 0) {
            reportInputProblem("Invalid null character");
        }
        if (i < 32) {
            reportInputProblem("Invalid white space character (0x" + Integer.toHexString(i) + ")");
        }
        reportInputProblem("Invalid xml content character (0x" + Integer.toHexString(i) + ")");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportMissingPISpace(int i) throws XMLStreamException {
        throwUnexpectedChar(i, ": expected either white space, or closing '?>'");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportMultipleColonsInName() throws XMLStreamException {
        reportInputProblem("Multiple colons not allowed in names");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportPrologUnexpChar(boolean z, int i, String str) throws XMLStreamException {
        String str2 = z ? ErrorConsts.SUFFIX_IN_PROLOG : ErrorConsts.SUFFIX_IN_EPILOG;
        if (str != null) {
            str2 = str2 + str;
        } else if (i == 38) {
            throwUnexpectedChar(i, str2 + "; no entities allowed");
        }
        throwUnexpectedChar(i, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportPrologUnexpElement(boolean z, int i) throws XMLStreamException {
        if (i < 0) {
            i &= 524287;
        }
        if (i == 47) {
            if (z) {
                reportInputProblem("Unexpected end element in prolog: malformed XML document, expected root element");
            }
            reportInputProblem("Unexpected end element in epilog: malformed XML document (unbalanced start/end tags?)");
        }
        if (i < 32) {
            throwUnexpectedChar(i, "Unrecognized directive " + (z ? ErrorConsts.SUFFIX_IN_PROLOG : ErrorConsts.SUFFIX_IN_EPILOG));
        }
        reportInputProblem("Second root element in content: malformed XML document, only one allowed");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportTreeUnexpChar(int i, String str) throws XMLStreamException {
        String str2 = ErrorConsts.SUFFIX_IN_TREE;
        if (str != null) {
            str2 = str2 + str;
        }
        throwUnexpectedChar(i, str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportUnboundPrefix(PName pName, boolean z) throws XMLStreamException {
        StringBuilder sb = new StringBuilder("Unbound namespace prefix '");
        sb.append(pName.getPrefix());
        sb.append("' (for ");
        sb.append(z ? "attribute" : "element");
        sb.append(" name '");
        sb.append(pName.getPrefixedName());
        sb.append("')");
        reportInputProblem(sb.toString());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportUnexpandedEntityInAttr(PName pName, boolean z) throws XMLStreamException {
        StringBuilder sb = new StringBuilder("Unexpanded ENTITY_REFERENCE (");
        sb.append(this._tokenName);
        sb.append(") in ");
        sb.append(z ? "namespace declaration" : "attribute value");
        reportInputProblem(sb.toString());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportUnexpectedEndTag(String str) throws XMLStreamException {
        reportInputProblem("Unexpected end tag: expected </" + str + ">");
    }

    public abstract void skipCData() throws XMLStreamException;

    public abstract boolean skipCharacters() throws XMLStreamException;

    public abstract boolean skipCoalescedText() throws XMLStreamException;

    public abstract void skipComment() throws XMLStreamException;

    public abstract void skipPI() throws XMLStreamException;

    public abstract void skipSpace() throws XMLStreamException;

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final boolean skipToken() throws XMLStreamException {
        this._tokenIncomplete = false;
        int i = this._currToken;
        if (i == 3) {
            skipPI();
        } else if (i != 4) {
            if (i == 5) {
                skipComment();
            } else if (i == 6) {
                skipSpace();
            } else if (i == 11) {
                finishDTD(false);
            } else {
                if (i != 12) {
                    throw new Error("Internal error, unexpected incomplete token type " + ErrorConsts.tokenTypeDesc(this._currToken));
                }
                skipCData();
                if (this._cfgCoalescing) {
                    skipCoalescedText();
                    if (this._entityPending) {
                        this._currToken = 9;
                        return true;
                    }
                }
            }
        } else {
            if (skipCharacters()) {
                this._currToken = 9;
                return true;
            }
            if (this._cfgCoalescing && skipCoalescedText()) {
                this._currToken = 9;
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void throwInvalidSpace(int i) throws XMLStreamException {
        char c = (char) i;
        if (c == 0) {
            throwNullChar();
        }
        String strConcat = "Illegal character (" + XmlChars.getCharDesc(c) + ")";
        if (this._xml11 && i < 32) {
            strConcat = strConcat.concat(" [note: in XML 1.1, it could be included via entity expansion]");
        }
        reportInputProblem(strConcat);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void throwNullChar() throws XMLStreamException {
        reportInputProblem("Illegal character (NULL, unicode 0) encountered: not valid in any content");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void throwUnexpectedChar(int i, String str) throws XMLStreamException {
        if (i < 32 && i != 13 && i != 10 && i != 9) {
            throwInvalidSpace(i);
        }
        reportInputProblem("Unexpected character " + XmlChars.getCharDesc((char) i) + str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void verifyXmlChar(int i) throws XMLStreamException {
        if (i >= 55296) {
            if (i < 57344) {
                reportInvalidXmlChar(i);
            }
            if (i == 65534 || i == 65535) {
                reportInvalidXmlChar(i);
                return;
            }
            return;
        }
        if (i >= 32 || i == 10 || i == 13 || i == 9) {
            return;
        }
        if (!this._xml11 || i == 0) {
            reportInvalidXmlChar(i);
        }
    }

    public final String getAttrValue(int i) {
        return this._attrCollector.getValue(i);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        if (this._tokenIncomplete) {
            finishToken();
        }
        return this._textBuilder.contentsToArray(i, cArr, i2, i3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void loadMoreGuaranteed(int i) throws XMLStreamException {
        if (loadMore()) {
            return;
        }
        reportInputProblem("Unexpected end-of-input when trying to parse " + ErrorConsts.tokenTypeDesc(i));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportIllegalNsDecl(String str) throws XMLStreamException {
        reportInputProblem("Illegal namespace declaration: can not re-bind prefix '" + str + "'");
    }

    public final String getNamespaceURI() {
        String nsUri = this._tokenName.getNsUri();
        return nsUri == null ? this._defaultNs.mURI : nsUri;
    }

    public final String getNamespaceURI(int i) {
        return findCurrNsDecl(i).getBinding().mURI;
    }
}
