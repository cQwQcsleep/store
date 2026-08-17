package com.fasterxml.aalto.stax;

import com.fasterxml.aalto.UncheckedStreamException;
import com.fasterxml.aalto.WFCException;
import com.fasterxml.aalto.impl.ErrorConsts;
import com.fasterxml.aalto.in.InputBootstrapper;
import com.fasterxml.aalto.in.PName;
import com.fasterxml.aalto.in.ReaderConfig;
import com.fasterxml.aalto.in.XmlScanner;
import com.fasterxml.aalto.util.TextAccumulator;
import java.util.Collections;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.LocationInfo;
import org.codehaus.stax2.XMLStreamLocation2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.ri.typed.CharArrayBase64Decoder;
import org.codehaus.stax2.ri.typed.ValueDecoderFactory;
import org.codehaus.stax2.typed.TypedValueDecoder;
import org.codehaus.stax2.typed.TypedXMLStreamException;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StreamReaderImpl implements LocationInfo, XMLStreamReader2 {
    protected int _attrCount;
    protected final boolean _cfgCoalesceText;
    protected final boolean _cfgReportTextAsChars;
    protected PName _currName;
    protected ValueDecoderFactory _decoderFactory;
    protected PName _dtdRootName;
    protected int _parseState;
    protected final XmlScanner _scanner;
    protected CharArrayBase64Decoder _base64Decoder = null;
    protected int _currToken = 7;

    public StreamReaderImpl(XmlScanner xmlScanner) {
        this._scanner = xmlScanner;
        ReaderConfig config = xmlScanner.getConfig();
        this._cfgCoalesceText = config.willCoalesceText();
        this._cfgReportTextAsChars = !config.willReportCData();
    }

    private TypedXMLStreamException _constructTypeException(IllegalArgumentException illegalArgumentException, String str) {
        return new TypedXMLStreamException(str, illegalArgumentException.getMessage(), getStartLocation(), illegalArgumentException);
    }

    public static StreamReaderImpl construct(InputBootstrapper inputBootstrapper) throws XMLStreamException {
        return new StreamReaderImpl(inputBootstrapper.bootstrap());
    }

    private void throwNotTextXxx(int i) {
        throw new IllegalStateException("getTextXxx() methods can not be called on " + ErrorConsts.tokenTypeDesc(this._currToken));
    }

    private void throwNotTextual(int i) {
        throw new IllegalStateException("Not a textual event (" + ErrorConsts.tokenTypeDesc(this._currToken) + ")");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void _closeScanner(boolean z) throws XMLStreamException {
        if (this._parseState != 3) {
            this._parseState = 3;
            if (this._currToken != 8) {
                this._currToken = 8;
            }
        }
        this._scanner.close(z);
    }

    public final ValueDecoderFactory _decoderFactory() {
        if (this._decoderFactory == null) {
            this._decoderFactory = new ValueDecoderFactory();
        }
        return this._decoderFactory;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void _reportNonTextEvent(int i) throws XMLStreamException {
        throwWfe("Expected a text token, got " + ErrorConsts.tokenTypeDesc(i) + ".");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void close() throws XMLStreamException {
        _closeScanner(false);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void closeCompletely() throws XMLStreamException {
        _closeScanner(true);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.codehaus.stax2.typed.TypedXMLStreamException */
    public final void getAttributeAs(int i, TypedValueDecoder typedValueDecoder) throws TypedXMLStreamException, XMLStreamException {
        if (this._currToken != 1) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
            return;
        }
        try {
            this._scanner.decodeAttrValue(i, typedValueDecoder);
        } catch (IllegalArgumentException e) {
            throw _constructTypeException(e, getAttributeValue(i));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.codehaus.stax2.typed.TypedXMLStreamException */
    public final boolean getAttributeAsBoolean(int i) throws TypedXMLStreamException, XMLStreamException {
        ValueDecoderFactory.BooleanDecoder booleanDecoder = _decoderFactory().getBooleanDecoder();
        getAttributeAs(i, booleanDecoder);
        return booleanDecoder.getValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.codehaus.stax2.typed.TypedXMLStreamException */
    public final int getAttributeAsInt(int i) throws TypedXMLStreamException, XMLStreamException {
        ValueDecoderFactory.IntDecoder intDecoder = _decoderFactory().getIntDecoder();
        getAttributeAs(i, intDecoder);
        return intDecoder.getValue();
    }

    public final int getAttributeCount() {
        if (this._currToken == 1) {
            return this._attrCount;
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
        return 0;
    }

    public final String getAttributeLocalName(int i) {
        if (this._currToken != 1) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
            return null;
        }
        if (i >= this._attrCount || i < 0) {
            reportInvalidAttrIndex(i);
        }
        return this._scanner.getAttrLocalName(i);
    }

    public final QName getAttributeName(int i) {
        if (this._currToken != 1) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
            return null;
        }
        if (i >= this._attrCount || i < 0) {
            reportInvalidAttrIndex(i);
        }
        return this._scanner.getAttrQName(i);
    }

    public final String getAttributeNamespace(int i) {
        if (this._currToken != 1) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
            return null;
        }
        if (i >= this._attrCount || i < 0) {
            reportInvalidAttrIndex(i);
        }
        String attrNsURI = this._scanner.getAttrNsURI(i);
        return attrNsURI == null ? XmlPullParser.NO_NAMESPACE : attrNsURI;
    }

    public final String getAttributePrefix(int i) {
        if (this._currToken != 1) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
            return null;
        }
        if (i >= this._attrCount || i < 0) {
            reportInvalidAttrIndex(i);
        }
        String attrPrefix = this._scanner.getAttrPrefix(i);
        return attrPrefix == null ? XmlPullParser.NO_NAMESPACE : attrPrefix;
    }

    public final String getAttributeType(int i) {
        if (this._currToken != 1) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
            return null;
        }
        if (i >= this._attrCount || i < 0) {
            reportInvalidAttrIndex(i);
        }
        return this._scanner.getAttrType(i);
    }

    public final String getAttributeValue(int i) {
        if (this._currToken != 1) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
            return null;
        }
        if (i >= this._attrCount || i < 0) {
            reportInvalidAttrIndex(i);
        }
        return this._scanner.getAttrValue(i);
    }

    public final String getCharacterEncodingScheme() {
        return this._scanner.getConfig().getXmlDeclEncoding();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final String getElementText() throws XMLStreamException {
        if (this._currToken != 1) {
            throwWfe(ErrorConsts.ERR_STATE_NOT_STELEM);
        }
        while (true) {
            int next = next();
            if (next == 2) {
                return XmlPullParser.NO_NAMESPACE;
            }
            if (next != 5 && next != 3) {
                if (((1 << next) & 4688) == 0) {
                    _reportNonTextEvent(next);
                }
                String text = this._scanner.getText();
                TextAccumulator textAccumulator = null;
                while (true) {
                    int next2 = next();
                    if (next2 == 2) {
                        break;
                    }
                    if (((1 << next2) & 4688) != 0) {
                        if (textAccumulator == null) {
                            textAccumulator = new TextAccumulator();
                            textAccumulator.addText(text);
                        }
                        textAccumulator.addText(getText());
                    } else if (next2 != 5 && next2 != 3) {
                        _reportNonTextEvent(next2);
                    }
                }
                return textAccumulator == null ? text : textAccumulator.getAndClear();
            }
        }
    }

    public final String getEncoding() {
        return this._scanner.getConfig().getActualEncoding();
    }

    public final int getEventType() {
        int i = this._currToken;
        if (i == 12 && (this._cfgCoalesceText || this._cfgReportTextAsChars)) {
            return 4;
        }
        return i;
    }

    public Location getLastCharLocation() {
        return this._scanner.getCurrentLocation();
    }

    public final String getLocalName() {
        int i = this._currToken;
        if (i == 1 || i == 2 || i == 9) {
            return this._currName.getLocalName();
        }
        k2d.a("Current state not START_ELEMENT, END_ELEMENT or ENTITY_REFERENCE");
        return null;
    }

    public final Location getLocation() {
        return getStartLocation();
    }

    public final LocationInfo getLocationInfo() {
        return this;
    }

    public final QName getName() {
        int i = this._currToken;
        if (i == 1 || i == 2) {
            return this._scanner.getQName();
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_ELEM);
        return null;
    }

    public final NamespaceContext getNamespaceContext() {
        return this._scanner;
    }

    public final int getNamespaceCount() {
        int i = this._currToken;
        if (i == 1 || i == 2) {
            return this._scanner.getNsCount();
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_ELEM);
        return 0;
    }

    public final String getNamespacePrefix(int i) {
        int i2 = this._currToken;
        if (i2 == 1 || i2 == 2) {
            String namespacePrefix = this._scanner.getNamespacePrefix(i);
            return namespacePrefix == null ? XmlPullParser.NO_NAMESPACE : namespacePrefix;
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_ELEM);
        return null;
    }

    public final String getNamespaceURI() {
        int i = this._currToken;
        if (i == 1 || i == 2) {
            String namespaceURI = this._scanner.getNamespaceURI();
            return namespaceURI == null ? XmlPullParser.NO_NAMESPACE : namespaceURI;
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_ELEM);
        return null;
    }

    public final String getPIData() {
        if (this._currToken != 3) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_PI);
            return null;
        }
        try {
            return this._scanner.getText();
        } catch (XMLStreamException e) {
            throw UncheckedStreamException.createFrom(e);
        }
    }

    public final String getPITarget() {
        if (this._currToken == 3) {
            return this._currName.getLocalName();
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_PI);
        return null;
    }

    public final String getPrefix() {
        int i = this._currToken;
        if (i == 1 || i == 2) {
            String prefix = this._currName.getPrefix();
            return prefix == null ? XmlPullParser.NO_NAMESPACE : prefix;
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_ELEM);
        return null;
    }

    public Object getProperty(String str) {
        if (!str.equals("javax.xml.stream.entities") && !str.equals("javax.xml.stream.notations")) {
            return this._scanner.getConfig().getProperty(str, false);
        }
        return Collections.EMPTY_LIST;
    }

    public final XMLStreamLocation2 getStartLocation() {
        return this._scanner.getStartLocation();
    }

    public final String getText() {
        int i = this._currToken;
        if (((1 << i) & 6768) == 0) {
            throwNotTextual(i);
        }
        try {
            return this._scanner.getText();
        } catch (XMLStreamException e) {
            throw UncheckedStreamException.createFrom(e);
        }
    }

    public final char[] getTextCharacters() {
        int i = this._currToken;
        if (((1 << i) & 4208) == 0) {
            throwNotTextXxx(i);
        }
        try {
            return this._scanner.getTextCharacters();
        } catch (XMLStreamException e) {
            throw UncheckedStreamException.createFrom(e);
        }
    }

    public final int getTextLength() {
        int i = this._currToken;
        if (((1 << i) & 4208) == 0) {
            throwNotTextXxx(i);
        }
        try {
            return this._scanner.getTextLength();
        } catch (XMLStreamException e) {
            throw UncheckedStreamException.createFrom(e);
        }
    }

    public final int getTextStart() {
        int i = this._currToken;
        if (((1 << i) & 4208) != 0) {
            return 0;
        }
        throwNotTextXxx(i);
        return 0;
    }

    public String getVersion() {
        return this._scanner.getConfig().getXmlDeclVersion();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public int handlePrologEoi(boolean z) throws XMLStreamException {
        close();
        if (!z) {
            return 8;
        }
        throwUnexpectedEOI(ErrorConsts.SUFFIX_IN_PROLOG);
        return 8;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void handleTreeEoi() throws XMLStreamException {
        this._currToken = 8;
        throwUnexpectedEOI(ErrorConsts.SUFFIX_IN_TREE);
    }

    public final boolean hasName() {
        int i = this._currToken;
        return i == 1 || i == 2;
    }

    public final boolean hasNext() {
        return this._currToken != 8;
    }

    public final boolean hasText() {
        return ((1 << this._currToken) & 6768) != 0;
    }

    public final boolean isAttributeSpecified(int i) {
        if (this._currToken == 1) {
            return this._scanner.isAttrSpecified(i);
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
        return false;
    }

    public final boolean isCharacters() {
        return getEventType() == 4;
    }

    public final boolean isEmptyElement() throws XMLStreamException {
        if (this._currToken == 1) {
            return this._scanner.isEmptyTag();
        }
        return false;
    }

    public final boolean isEndElement() {
        return this._currToken == 2;
    }

    public final boolean isStandalone() {
        return this._scanner.getConfig().getXmlDeclStandalone() == 1;
    }

    public final boolean isStartElement() {
        return this._currToken == 1;
    }

    public final boolean isWhiteSpace() {
        int i = this._currToken;
        if (i != 4 && i != 12) {
            return i == 6;
        }
        try {
            return this._scanner.isTextWhitespace();
        } catch (XMLStreamException e) {
            throw UncheckedStreamException.createFrom(e);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final int next() throws XMLStreamException {
        int iNextFromProlog;
        int i = this._parseState;
        if (i == 1) {
            int iNextFromTree = this._scanner.nextFromTree();
            if (iNextFromTree == -1) {
                handleTreeEoi();
            }
            this._currToken = iNextFromTree;
            if (iNextFromTree != 12) {
                this._currName = this._scanner.getName();
                if (iNextFromTree == 2) {
                    if (this._scanner.hasEmptyStack()) {
                        this._parseState = 2;
                        return iNextFromTree;
                    }
                } else if (iNextFromTree == 1) {
                    this._attrCount = this._scanner.getAttrCount();
                }
            } else if (this._cfgCoalesceText || this._cfgReportTextAsChars) {
                return 4;
            }
            return iNextFromTree;
        }
        if (i == 0) {
            iNextFromProlog = this._scanner.nextFromProlog(true);
            if (iNextFromProlog == 1) {
                this._parseState = 1;
                this._attrCount = this._scanner.getAttrCount();
            } else if (iNextFromProlog == 11) {
                if (this._dtdRootName != null) {
                    throwWfe("Duplicate DOCTYPE declaration");
                }
                this._dtdRootName = this._scanner.getName();
            }
        } else {
            if (i != 2) {
                z0e.a();
                return 0;
            }
            iNextFromProlog = this._scanner.nextFromProlog(false);
        }
        if (iNextFromProlog < 0) {
            return handlePrologEoi(this._parseState == 0);
        }
        this._currName = this._scanner.getName();
        this._currToken = iNextFromProlog;
        return iNextFromProlog;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0008. Please report as an issue. */
    public final int nextTag() throws XMLStreamException {
        while (true) {
            int next = next();
            if (next != 12) {
                switch (next) {
                    case 1:
                    case 2:
                        break;
                    case XmlPullParser.END_TAG /* 3 */:
                    case XmlPullParser.CDSECT /* 5 */:
                    case XmlPullParser.ENTITY_REF /* 6 */:
                        break;
                    case 4:
                        break;
                    default:
                        throwWfe("Received event " + ErrorConsts.tokenTypeDesc(next) + ", instead of START_ELEMENT or END_ELEMENT.");
                        break;
                }
                return next;
            }
            if (!isWhiteSpace()) {
                throwWfe("Received non-all-whitespace CHARACTERS or CDATA event in nextTag().");
                throwWfe("Received event " + ErrorConsts.tokenTypeDesc(next) + ", instead of START_ELEMENT or END_ELEMENT.");
            }
        }
    }

    public void reportInvalidAttrIndex(int i) {
        throw new IllegalArgumentException("Illegal attribute index, " + i + ", current START_ELEMENT has " + this._attrCount + " attributes");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void require(int i, String str, String str2) throws XMLStreamException {
        int i2 = this._currToken;
        if (i2 != i && i2 == 12 && (this._cfgCoalesceText || this._cfgReportTextAsChars)) {
            i2 = 4;
        }
        if (i != i2) {
            throwWfe("Expected type " + ErrorConsts.tokenTypeDesc(i) + ", current type " + ErrorConsts.tokenTypeDesc(i2));
        }
        if (str2 != null) {
            if (i2 != 1 && i2 != 2 && i2 != 9) {
                throwWfe("Expected non-null local name, but current token not a START_ELEMENT, END_ELEMENT or ENTITY_REFERENCE (was " + ErrorConsts.tokenTypeDesc(this._currToken) + ")");
            }
            String localName = getLocalName();
            if (localName != str2 && !localName.equals(str2)) {
                throwWfe("Expected local name '" + str2 + "'; current local name '" + localName + "'.");
            }
        }
        if (str != null) {
            if (i2 != 1 && i2 != 2) {
                throwWfe("Expected non-null NS URI, but current token not a START_ELEMENT or END_ELEMENT (was " + ErrorConsts.tokenTypeDesc(i2) + ")");
            }
            String namespaceURI = getNamespaceURI();
            if (str.length() == 0) {
                if (namespaceURI == null || namespaceURI.length() <= 0) {
                    return;
                }
                throwWfe("Expected empty namespace, instead have '" + namespaceURI + "'.");
                return;
            }
            if (str == namespaceURI || str.equals(namespaceURI)) {
                return;
            }
            throwWfe("Expected namespace '" + str + "'; have '" + namespaceURI + "'.");
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void skipElement() throws XMLStreamException {
        if (this._currToken != 1) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
            return;
        }
        int i = 1;
        while (true) {
            int next = next();
            if (next == 1) {
                i++;
            } else if (next == 2 && (i = i - 1) == 0) {
                return;
            }
        }
    }

    public final boolean standaloneSet() {
        return this._scanner.getConfig().getXmlDeclStandalone() != 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void throwUnexpectedEOI(String str) throws XMLStreamException {
        throwWfe("Unexpected End-of-input" + str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void throwWfe(String str) throws XMLStreamException {
        throw new WFCException(str, getLastCharLocation());
    }

    public final String toString() {
        return "[Aalto stream reader, scanner: " + this._scanner + "]";
    }

    public final int getTextCharacters(int i, char[] cArr, int i2, int i3) {
        int i4 = this._currToken;
        if (((1 << i4) & 4208) == 0) {
            throwNotTextXxx(i4);
        }
        try {
            return this._scanner.getTextCharacters(i, cArr, i2, i3);
        } catch (XMLStreamException e) {
            throw UncheckedStreamException.createFrom(e);
        }
    }

    public final String getNamespaceURI(int i) {
        int i2 = this._currToken;
        if (i2 != 1 && i2 != 2) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_ELEM);
            return null;
        }
        String namespaceURI = this._scanner.getNamespaceURI(i);
        return namespaceURI == null ? XmlPullParser.NO_NAMESPACE : namespaceURI;
    }

    public final String getAttributeValue(String str, String str2) {
        if (this._currToken == 1) {
            return this._scanner.getAttrValue(str, str2);
        }
        k2d.a(ErrorConsts.ERR_STATE_NOT_STELEM);
        return null;
    }

    public final String getNamespaceURI(String str) {
        int i = this._currToken;
        if (i != 1 && i != 2) {
            k2d.a(ErrorConsts.ERR_STATE_NOT_ELEM);
            return null;
        }
        return this._scanner.getNamespaceURI(str);
    }
}
