package com.sun.org.apache.xml.internal.serialize;

import com.sun.jna.platform.win32.Ddeml;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.dom.DOMErrorImpl;
import com.sun.org.apache.xerces.internal.dom.DOMLocatorImpl;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import defpackage.x73;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSSerializerFilter;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public abstract class BaseMarkupSerializer implements ContentHandler, DocumentHandler, LexicalHandler, DTDHandler, DeclHandler, DOMSerializer, Serializer {
    protected String _docTypePublicId;
    protected String _docTypeSystemId;
    private int _elementStateCount;
    protected EncodingInfo _encodingInfo;
    protected OutputFormat _format;
    protected boolean _indenting;
    private OutputStream _output;
    private List<String> _preRoot;
    protected Map<String, String> _prefixes;
    private boolean _prepared;
    protected Printer _printer;
    protected boolean _started;
    private Writer _writer;
    protected DOMErrorHandler fDOMErrorHandler;
    protected LSSerializerFilter fDOMFilter;
    protected final DOMErrorImpl fDOMError = new DOMErrorImpl();
    protected final StringBuffer fStrBuffer = new StringBuffer(40);
    protected short features = -1;
    protected Node fCurrentNode = null;
    private ElementState[] _elementStates = new ElementState[10];

    public BaseMarkupSerializer(OutputFormat outputFormat) {
        int i = 0;
        while (true) {
            ElementState[] elementStateArr = this._elementStates;
            if (i >= elementStateArr.length) {
                this._format = outputFormat;
                return;
            } else {
                elementStateArr[i] = new ElementState();
                i++;
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Serializer
    public ContentHandler asContentHandler() throws IOException {
        prepare();
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Serializer
    public DOMSerializer asDOMSerializer() throws IOException {
        prepare();
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Serializer
    public DocumentHandler asDocumentHandler() throws IOException {
        prepare();
        return this;
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ATTLIST ");
            this._printer.printText(str);
            this._printer.printText(' ');
            this._printer.printText(str2);
            this._printer.printText(' ');
            this._printer.printText(str3);
            if (str4 != null) {
                this._printer.printText(' ');
                this._printer.printText(str4);
            }
            if (str5 != null) {
                this._printer.printText(" \"");
                printEscaped(str5);
                this._printer.printText('\"');
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        int i3;
        try {
            ElementState elementStateContent = content();
            boolean z = elementStateContent.inCData;
            if (!z && !elementStateContent.doCData) {
                if (!elementStateContent.preserveSpace) {
                    printText(cArr, i, i2, false, elementStateContent.unescaped);
                    return;
                }
                int nextIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                printText(cArr, i, i2, true, elementStateContent.unescaped);
                this._printer.setNextIndent(nextIndent);
                return;
            }
            int i4 = i;
            if (!z) {
                this._printer.printText("<![CDATA[");
                elementStateContent.inCData = true;
            }
            int nextIndent2 = this._printer.getNextIndent();
            this._printer.setNextIndent(0);
            int i5 = i4 + i2;
            while (i4 < i5) {
                char c = cArr[i4];
                if (c == ']' && (i3 = i4 + 2) < i5 && cArr[i4 + 1] == ']' && cArr[i3] == '>') {
                    this._printer.printText(SerializerConstants.CDATA_CONTINUE);
                    i4 = i3;
                } else if (!XMLChar.isValid(c)) {
                    i4++;
                    if (i4 < i5) {
                        surrogates(c, cArr[i4], true);
                    } else {
                        fatalError("The character '" + c + "' is an invalid XML character");
                    }
                } else if ((c >= ' ' && this._encodingInfo.isPrintable(c) && c != 127) || c == '\n' || c == '\r' || c == '\t') {
                    this._printer.printText(c);
                } else {
                    this._printer.printText("]]>&#x");
                    this._printer.printText(Integer.toHexString(c));
                    this._printer.printText(";<![CDATA[");
                }
                i4++;
            }
            this._printer.setNextIndent(nextIndent2);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void checkUnboundNamespacePrefixedNode(Node node) throws IOException {
    }

    public void cleanup() {
        this.fCurrentNode = null;
    }

    public final void clearDocumentState() {
        this._elementStateCount = 0;
    }

    public void comment(String str) throws IOException {
        if (this._format.getOmitComments()) {
            return;
        }
        ElementState elementStateContent = content();
        int iIndexOf = str.indexOf("-->");
        StringBuffer stringBuffer = this.fStrBuffer;
        if (iIndexOf >= 0) {
            stringBuffer.append("<!--");
            stringBuffer.append(str.substring(0, iIndexOf));
            stringBuffer.append("-->");
        } else {
            stringBuffer.append("<!--");
            stringBuffer.append(str);
            stringBuffer.append("-->");
        }
        if (isDocumentState()) {
            if (this._preRoot == null) {
                this._preRoot = new ArrayList();
            }
            this._preRoot.add(this.fStrBuffer.toString());
        } else {
            if (this._indenting && !elementStateContent.preserveSpace) {
                this._printer.breakLine();
            }
            this._printer.indent();
            printText(this.fStrBuffer.toString(), true, true);
            this._printer.unindent();
            if (this._indenting) {
                elementStateContent.afterElement = true;
            }
        }
        this.fStrBuffer.setLength(0);
        elementStateContent.afterComment = true;
        elementStateContent.afterElement = false;
    }

    public ElementState content() throws IOException {
        ElementState elementState = getElementState();
        if (!isDocumentState()) {
            if (elementState.inCData && !elementState.doCData) {
                this._printer.printText("]]>");
                elementState.inCData = false;
            }
            if (elementState.empty) {
                this._printer.printText('>');
                elementState.empty = false;
            }
            elementState.afterElement = false;
            elementState.afterComment = false;
        }
        return elementState;
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void elementDecl(String str, String str2) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ELEMENT ");
            this._printer.printText(str);
            this._printer.printText(' ');
            this._printer.printText(str2);
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() {
        getElementState().doCData = false;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() {
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        try {
            serializePreRoot();
            this._printer.flush();
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) {
    }

    public void endNonEscaping() {
        getElementState().unescaped = false;
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
    }

    public void endPreserving() {
        getElementState().preserveSpace = false;
    }

    public ElementState enterElementState(String str, String str2, String str3, boolean z) {
        ElementState[] elementStateArr;
        int i = this._elementStateCount + 1;
        ElementState[] elementStateArr2 = this._elementStates;
        if (i == elementStateArr2.length) {
            int length = elementStateArr2.length + 10;
            ElementState[] elementStateArr3 = new ElementState[length];
            int i2 = 0;
            while (true) {
                elementStateArr = this._elementStates;
                if (i2 >= elementStateArr.length) {
                    break;
                }
                elementStateArr3[i2] = elementStateArr[i2];
                i2++;
            }
            for (int length2 = elementStateArr.length; length2 < length; length2++) {
                elementStateArr3[length2] = new ElementState();
            }
            this._elementStates = elementStateArr3;
        }
        int i3 = this._elementStateCount + 1;
        this._elementStateCount = i3;
        ElementState elementState = this._elementStates[i3];
        elementState.namespaceURI = str;
        elementState.localName = str2;
        elementState.rawName = str3;
        elementState.preserveSpace = z;
        elementState.empty = true;
        elementState.afterElement = false;
        elementState.afterComment = false;
        elementState.inCData = false;
        elementState.doCData = false;
        elementState.unescaped = false;
        elementState.prefixes = this._prefixes;
        this._prefixes = null;
        return elementState;
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
        try {
            this._printer.enterDTD();
            unparsedEntityDecl(str, str2, str3, null);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void fatalError(String str) throws IOException {
        if (this.fDOMErrorHandler == null) {
            a16.a(str);
        } else {
            modifyDOMError(str, (short) 3, null, this.fCurrentNode);
            this.fDOMErrorHandler.handleError(this.fDOMError);
        }
    }

    public ElementState getElementState() {
        return this._elementStates[this._elementStateCount];
    }

    public abstract String getEntityRef(int i);

    public String getPrefix(String str) {
        String str2;
        String str3;
        Map<String, String> map = this._prefixes;
        if (map != null && (str3 = map.get(str)) != null) {
            return str3;
        }
        int i = this._elementStateCount;
        if (i == 0) {
            return null;
        }
        while (i > 0) {
            Map<String, String> map2 = this._elementStates[i].prefixes;
            if (map2 != null && (str2 = map2.get(str)) != null) {
                return str2;
            }
            i--;
        }
        return null;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        try {
            content();
            if (!this._indenting) {
                return;
            }
            this._printer.setThisIndent(0);
            while (true) {
                int i3 = i2 - 1;
                if (i2 <= 0) {
                    return;
                }
                this._printer.printText(cArr[i]);
                i++;
                i2 = i3;
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void internalEntityDecl(String str, String str2) throws SAXException {
        try {
            this._printer.enterDTD();
            this._printer.printText("<!ENTITY ");
            this._printer.printText(str);
            this._printer.printText(" \"");
            printEscaped(str2);
            this._printer.printText("\">");
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public boolean isDocumentState() {
        return this._elementStateCount == 0;
    }

    public ElementState leaveElementState() {
        int i = this._elementStateCount;
        if (i <= 0) {
            k2d.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "Internal", null));
            return null;
        }
        this._prefixes = null;
        int i2 = i - 1;
        this._elementStateCount = i2;
        return this._elementStates[i2];
    }

    public DOMError modifyDOMError(String str, short s, String str2, Node node) {
        this.fDOMError.reset();
        DOMErrorImpl dOMErrorImpl = this.fDOMError;
        dOMErrorImpl.fMessage = str;
        dOMErrorImpl.fType = str2;
        dOMErrorImpl.fSeverity = s;
        dOMErrorImpl.fLocator = new DOMLocatorImpl(-1, -1, -1, node, null);
        return this.fDOMError;
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String str, String str2, String str3) throws SAXException {
        try {
            this._printer.enterDTD();
            Printer printer = this._printer;
            if (str2 != null) {
                printer.printText("<!NOTATION ");
                this._printer.printText(str);
                this._printer.printText(" PUBLIC ");
                printDoctypeURL(str2);
                if (str3 != null) {
                    this._printer.printText(' ');
                    printDoctypeURL(str3);
                }
            } else {
                printer.printText("<!NOTATION ");
                this._printer.printText(str);
                this._printer.printText(" SYSTEM ");
                printDoctypeURL(str3);
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void prepare() throws IOException {
        if (this._prepared) {
            return;
        }
        if (this._writer == null && this._output == null) {
            a16.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            return;
        }
        EncodingInfo encodingInfo = this._format.getEncodingInfo();
        this._encodingInfo = encodingInfo;
        OutputStream outputStream = this._output;
        if (outputStream != null) {
            this._writer = encodingInfo.getWriter(outputStream);
        }
        if (this._format.getIndenting()) {
            this._indenting = true;
            this._printer = new IndentPrinter(this._writer, this._format);
        } else {
            this._indenting = false;
            this._printer = new Printer(this._writer, this._format);
        }
        this._elementStateCount = 0;
        ElementState elementState = this._elementStates[0];
        elementState.namespaceURI = null;
        elementState.localName = null;
        elementState.rawName = null;
        elementState.preserveSpace = this._format.getPreserveSpace();
        elementState.empty = true;
        elementState.afterElement = false;
        elementState.afterComment = false;
        elementState.inCData = false;
        elementState.doCData = false;
        elementState.prefixes = null;
        this._docTypePublicId = this._format.getDoctypePublic();
        this._docTypeSystemId = this._format.getDoctypeSystem();
        this._started = false;
        this._prepared = true;
    }

    public void printCDATAText(String str) throws IOException {
        int i;
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == ']' && (i = i2 + 2) < length && str.charAt(i2 + 1) == ']' && str.charAt(i) == '>') {
                if (this.fDOMErrorHandler != null) {
                    if ((this.features & 16) == 0) {
                        String message = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "EndingCDATA", null);
                        int i3 = this.features & 2;
                        Node node = this.fCurrentNode;
                        if (i3 != 0) {
                            modifyDOMError(message, (short) 3, MsgKey.ER_WF_INVALID_CHARACTER, node);
                            this.fDOMErrorHandler.handleError(this.fDOMError);
                            throw new LSException((short) 82, message);
                        }
                        modifyDOMError(message, (short) 2, "cdata-section-not-splitted", node);
                        if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                            throw new LSException((short) 82, message);
                        }
                    } else {
                        modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SplittingCDATA", null), (short) 1, null, this.fCurrentNode);
                        this.fDOMErrorHandler.handleError(this.fDOMError);
                    }
                }
                this._printer.printText(SerializerConstants.CDATA_CONTINUE);
                i2 = i;
            } else if (!XMLChar.isValid(cCharAt)) {
                i2++;
                if (i2 < length) {
                    surrogates(cCharAt, str.charAt(i2), true);
                } else {
                    fatalError("The character '" + cCharAt + "' is an invalid XML character");
                }
            } else if ((cCharAt >= ' ' && this._encodingInfo.isPrintable(cCharAt) && cCharAt != 127) || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t') {
                this._printer.printText(cCharAt);
            } else {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(cCharAt));
                this._printer.printText(";<![CDATA[");
            }
            i2++;
        }
    }

    public void printDoctypeURL(String str) throws IOException {
        this._printer.printText('\"');
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\"' || str.charAt(i) < ' ' || str.charAt(i) > 127) {
                this._printer.printText('%');
                this._printer.printText(Integer.toHexString(str.charAt(i)));
            } else {
                this._printer.printText(str.charAt(i));
            }
        }
        this._printer.printText('\"');
    }

    public void printEscaped(int i) throws IOException {
        String entityRef = getEntityRef(i);
        if (entityRef != null) {
            this._printer.printText('&');
            this._printer.printText(entityRef);
            this._printer.printText(';');
            return;
        }
        if ((i < 32 || !this._encodingInfo.isPrintable((char) i) || i == 127) && i != 10 && i != 13 && i != 9) {
            printHex(i);
            return;
        }
        Printer printer = this._printer;
        if (i < 65536) {
            printer.printText((char) i);
            return;
        }
        int i2 = i - 65536;
        printer.printText((char) ((i2 >> 10) + 55296));
        this._printer.printText((char) ((i2 & 1023) + 56320));
    }

    public final void printHex(int i) throws IOException {
        this._printer.printText("&#x");
        this._printer.printText(Integer.toHexString(i));
        this._printer.printText(';');
    }

    public void printText(String str, boolean z, boolean z2) throws IOException {
        int i = 0;
        if (z) {
            while (i < str.length()) {
                char cCharAt = str.charAt(i);
                if (cCharAt == '\n' || cCharAt == '\r' || z2) {
                    this._printer.printText(cCharAt);
                } else {
                    printEscaped(cCharAt);
                }
                i++;
            }
            return;
        }
        while (i < str.length()) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 == ' ' || cCharAt2 == '\f' || cCharAt2 == '\t' || cCharAt2 == '\n' || cCharAt2 == '\r') {
                this._printer.printSpace();
            } else if (z2) {
                this._printer.printText(cCharAt2);
            } else {
                printEscaped(cCharAt2);
            }
            i++;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public final void processingInstruction(String str, String str2) throws SAXException {
        try {
            processingInstructionIO(str, str2);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void processingInstructionIO(String str, String str2) throws IOException {
        ElementState elementStateContent = content();
        int iIndexOf = str.indexOf("?>");
        StringBuffer stringBuffer = this.fStrBuffer;
        if (iIndexOf >= 0) {
            stringBuffer.append("<?");
            stringBuffer.append(str.substring(0, iIndexOf));
        } else {
            stringBuffer.append("<?");
            stringBuffer.append(str);
        }
        if (str2 != null) {
            this.fStrBuffer.append(' ');
            int iIndexOf2 = str2.indexOf("?>");
            StringBuffer stringBuffer2 = this.fStrBuffer;
            if (iIndexOf2 >= 0) {
                stringBuffer2.append(str2.substring(0, iIndexOf2));
            } else {
                stringBuffer2.append(str2);
            }
        }
        this.fStrBuffer.append("?>");
        if (isDocumentState()) {
            if (this._preRoot == null) {
                this._preRoot = new ArrayList();
            }
            this._preRoot.add(this.fStrBuffer.toString());
        } else {
            this._printer.indent();
            printText(this.fStrBuffer.toString(), true, true);
            this._printer.unindent();
            if (this._indenting) {
                elementStateContent.afterElement = true;
            }
        }
        this.fStrBuffer.setLength(0);
    }

    public boolean reset() {
        if (this._elementStateCount > 1) {
            k2d.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ResetInMiddle", null));
            return false;
        }
        this._prepared = false;
        this.fCurrentNode = null;
        this.fStrBuffer.setLength(0);
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.DOMSerializer
    public void serialize(Document document) throws IOException {
        reset();
        prepare();
        serializeNode(document);
        serializePreRoot();
        cleanup();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }

    public void serializeDTD(String str) throws IOException {
        String strLeaveDTD = this._printer.leaveDTD();
        if (this._format.getOmitDocumentType()) {
            return;
        }
        if (this._docTypeSystemId == null) {
            if (strLeaveDTD == null || strLeaveDTD.length() <= 0) {
                return;
            }
            this._printer.printText("<!DOCTYPE ");
            this._printer.printText(str);
            this._printer.printText(" [");
            printText(strLeaveDTD, true, true);
            this._printer.printText("]>");
            this._printer.breakLine();
            return;
        }
        this._printer.printText("<!DOCTYPE ");
        this._printer.printText(str);
        String str2 = this._docTypePublicId;
        Printer printer = this._printer;
        if (str2 != null) {
            printer.printText(" PUBLIC ");
            printDoctypeURL(this._docTypePublicId);
            boolean z = this._indenting;
            Printer printer2 = this._printer;
            if (z) {
                printer2.breakLine();
                for (int i = 0; i < str.length() + 18; i++) {
                    this._printer.printText(" ");
                }
            } else {
                printer2.printText(" ");
            }
            printDoctypeURL(this._docTypeSystemId);
        } else {
            printer.printText(" SYSTEM ");
            printDoctypeURL(this._docTypeSystemId);
        }
        if (strLeaveDTD != null && strLeaveDTD.length() > 0) {
            this._printer.printText(" [");
            printText(strLeaveDTD, true, true);
            this._printer.printText(']');
        }
        this._printer.printText(">");
        this._printer.breakLine();
    }

    public void serializeDocument() throws IOException {
        this._printer.leaveDTD();
        if (!this._started && !this._format.getOmitXMLDeclaration()) {
            StringBuffer stringBuffer = new StringBuffer("<?xml version=\"");
            if (this._format.getVersion() != null) {
                stringBuffer.append(this._format.getVersion());
            } else {
                stringBuffer.append("1.0");
            }
            stringBuffer.append('\"');
            String encoding = this._format.getEncoding();
            if (encoding != null) {
                stringBuffer.append(" encoding=\"");
                stringBuffer.append(encoding);
                stringBuffer.append('\"');
            }
            if (this._format.getStandalone() && this._docTypeSystemId == null && this._docTypePublicId == null) {
                stringBuffer.append(" standalone=\"yes\"");
            }
            stringBuffer.append("?>");
            this._printer.printText(stringBuffer);
            this._printer.breakLine();
        }
        serializePreRoot();
    }

    public abstract void serializeElement(Element element) throws IOException;

    public void serializeNode(Node node) throws IOException {
        short sAcceptNode;
        short sAcceptNode2;
        String nodeValue;
        short sAcceptNode3;
        this.fCurrentNode = node;
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            LSSerializerFilter lSSerializerFilter = this.fDOMFilter;
            if (lSSerializerFilter != null && (lSSerializerFilter.getWhatToShow() & 1) != 0) {
                short sAcceptNode4 = this.fDOMFilter.acceptNode(node);
                if (sAcceptNode4 == 2) {
                    return;
                }
                if (sAcceptNode4 == 3) {
                    for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                        serializeNode(firstChild);
                    }
                    return;
                }
            }
            serializeElement((Element) node);
            return;
        }
        if (nodeType != 11) {
            if (nodeType == 3) {
                String nodeValue2 = node.getNodeValue();
                if (nodeValue2 != null) {
                    LSSerializerFilter lSSerializerFilter2 = this.fDOMFilter;
                    if (lSSerializerFilter2 == null || (lSSerializerFilter2.getWhatToShow() & 4) == 0) {
                        if (this._indenting && !getElementState().preserveSpace && nodeValue2.replace('\n', ' ').trim().length() == 0) {
                            return;
                        }
                        characters(nodeValue2);
                        return;
                    }
                    short sAcceptNode5 = this.fDOMFilter.acceptNode(node);
                    if (sAcceptNode5 == 2 || sAcceptNode5 == 3) {
                        return;
                    }
                    characters(nodeValue2);
                    return;
                }
                return;
            }
            if (nodeType == 4) {
                String nodeValue3 = node.getNodeValue();
                if ((this.features & 8) == 0) {
                    characters(nodeValue3);
                    return;
                }
                if (nodeValue3 != null) {
                    LSSerializerFilter lSSerializerFilter3 = this.fDOMFilter;
                    if (lSSerializerFilter3 == null || (lSSerializerFilter3.getWhatToShow() & 8) == 0 || !((sAcceptNode = this.fDOMFilter.acceptNode(node)) == 2 || sAcceptNode == 3)) {
                        startCDATA();
                        characters(nodeValue3);
                        endCDATA();
                        return;
                    }
                    return;
                }
                return;
            }
            if (nodeType == 5) {
                endCDATA();
                content();
                if ((this.features & 4) == 0 && node.getFirstChild() != null) {
                    for (Node firstChild2 = node.getFirstChild(); firstChild2 != null; firstChild2 = firstChild2.getNextSibling()) {
                        serializeNode(firstChild2);
                    }
                    return;
                }
                LSSerializerFilter lSSerializerFilter4 = this.fDOMFilter;
                if (lSSerializerFilter4 != null && (lSSerializerFilter4.getWhatToShow() & 16) != 0) {
                    short sAcceptNode6 = this.fDOMFilter.acceptNode(node);
                    if (sAcceptNode6 == 2) {
                        return;
                    }
                    if (sAcceptNode6 == 3) {
                        for (Node firstChild3 = node.getFirstChild(); firstChild3 != null; firstChild3 = firstChild3.getNextSibling()) {
                            serializeNode(firstChild3);
                        }
                        return;
                    }
                }
                checkUnboundNamespacePrefixedNode(node);
                this._printer.printText("&");
                this._printer.printText(node.getNodeName());
                this._printer.printText(";");
                return;
            }
            if (nodeType == 7) {
                LSSerializerFilter lSSerializerFilter5 = this.fDOMFilter;
                if (lSSerializerFilter5 == null || (lSSerializerFilter5.getWhatToShow() & 64) == 0 || !((sAcceptNode2 = this.fDOMFilter.acceptNode(node)) == 2 || sAcceptNode2 == 3)) {
                    processingInstructionIO(node.getNodeName(), node.getNodeValue());
                    return;
                }
                return;
            }
            if (nodeType == 8) {
                if (this._format.getOmitComments() || (nodeValue = node.getNodeValue()) == null) {
                    return;
                }
                LSSerializerFilter lSSerializerFilter6 = this.fDOMFilter;
                if (lSSerializerFilter6 == null || (lSSerializerFilter6.getWhatToShow() & 128) == 0 || !((sAcceptNode3 = this.fDOMFilter.acceptNode(node)) == 2 || sAcceptNode3 == 3)) {
                    comment(nodeValue);
                    return;
                }
                return;
            }
            if (nodeType != 9) {
                return;
            }
            serializeDocument();
            DocumentType doctype = ((Document) node).getDoctype();
            if (doctype != null) {
                try {
                    this._printer.enterDTD();
                    this._docTypePublicId = doctype.getPublicId();
                    this._docTypeSystemId = doctype.getSystemId();
                    String internalSubset = doctype.getInternalSubset();
                    if (internalSubset != null && internalSubset.length() > 0) {
                        this._printer.printText(internalSubset);
                    }
                    endDTD();
                } catch (Exception unused) {
                    this._printer.enterDTD();
                    this._docTypePublicId = null;
                    this._docTypeSystemId = null;
                    endDTD();
                }
                serializeDTD(doctype.getName());
            }
            this._started = true;
        }
        for (Node firstChild4 = node.getFirstChild(); firstChild4 != null; firstChild4 = firstChild4.getNextSibling()) {
            serializeNode(firstChild4);
        }
    }

    public void serializePreRoot() throws IOException {
        if (this._preRoot == null) {
            return;
        }
        int i = 0;
        while (true) {
            int size = this._preRoot.size();
            List<String> list = this._preRoot;
            if (i >= size) {
                list.clear();
                return;
            }
            printText(list.get(i), true, true);
            if (this._indenting) {
                this._printer.breakLine();
            }
            i++;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Serializer
    public void setOutputByteStream(OutputStream outputStream) {
        if (outputStream == null) {
            x0e.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ArgumentIsNull", new Object[]{Constants.ELEMNAME_OUTPUT_STRING}));
            return;
        }
        this._output = outputStream;
        this._writer = null;
        reset();
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Serializer
    public void setOutputCharStream(Writer writer) {
        if (writer == null) {
            x0e.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ArgumentIsNull", new Object[]{"writer"}));
            return;
        }
        this._writer = writer;
        this._output = null;
        reset();
    }

    @Override // com.sun.org.apache.xml.internal.serialize.Serializer
    public void setOutputFormat(OutputFormat outputFormat) {
        if (outputFormat == null) {
            x0e.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ArgumentIsNull", new Object[]{Constants.ATTRNAME_FORMAT}));
        } else {
            this._format = outputFormat;
            reset();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
        try {
            endCDATA();
            content();
            this._printer.printText('&');
            this._printer.printText(str);
            this._printer.printText(';');
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() {
        getElementState().doCData = true;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public final void startDTD(String str, String str2, String str3) throws SAXException {
        try {
            this._printer.enterDTD();
            this._docTypePublicId = str2;
            this._docTypeSystemId = str3;
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        try {
            prepare();
        } catch (IOException e) {
            throw new SAXException(e.toString());
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) {
    }

    public void startNonEscaping() {
        getElementState().unescaped = true;
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        if (this._prefixes == null) {
            this._prefixes = new HashMap();
        }
        Map<String, String> map = this._prefixes;
        if (str == null) {
            str = "";
        }
        map.put(str2, str);
    }

    public void startPreserving() {
        getElementState().preserveSpace = true;
    }

    public void surrogates(int i, int i2, boolean z) throws IOException {
        if (!XMLChar.isHighSurrogate(i)) {
            fatalError("The character '" + ((char) i) + "' is an invalid XML character");
            return;
        }
        if (!XMLChar.isLowSurrogate(i2)) {
            fatalError("The character '" + ((char) i2) + "' is an invalid XML character");
            return;
        }
        int iSupplemental = XMLChar.supplemental((char) i, (char) i2);
        if (!XMLChar.isValid(iSupplemental)) {
            fatalError("The character '" + ((char) iSupplemental) + "' is an invalid XML character");
            return;
        }
        if (!z || !content().inCData) {
            printHex(iSupplemental);
            return;
        }
        this._printer.printText("]]>&#x");
        this._printer.printText(Integer.toHexString(iSupplemental));
        this._printer.printText(";<![CDATA[");
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException {
        try {
            this._printer.enterDTD();
            Printer printer = this._printer;
            if (str2 == null) {
                printer.printText("<!ENTITY ");
                this._printer.printText(str);
                this._printer.printText(" SYSTEM ");
                printDoctypeURL(str3);
            } else {
                printer.printText("<!ENTITY ");
                this._printer.printText(str);
                this._printer.printText(" PUBLIC ");
                printDoctypeURL(str2);
                this._printer.printText(' ');
                printDoctypeURL(str3);
            }
            if (str4 != null) {
                this._printer.printText(" NDATA ");
                this._printer.printText(str4);
            }
            this._printer.printText('>');
            if (this._indenting) {
                this._printer.breakLine();
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void serialize(Node node) throws IOException {
        reset();
        prepare();
        serializeNode(node);
        serializePreRoot();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.DOMSerializer
    public void serialize(DocumentFragment documentFragment) throws IOException {
        reset();
        prepare();
        serializeNode(documentFragment);
        cleanup();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.DOMSerializer
    public void serialize(Element element) throws IOException {
        reset();
        prepare();
        serializeNode(element);
        cleanup();
        this._printer.flush();
        if (this._printer.getException() != null) {
            throw this._printer.getException();
        }
    }

    public void printText(char[] cArr, int i, int i2, boolean z, boolean z2) throws IOException {
        if (z) {
            while (true) {
                int i3 = i2 - 1;
                if (i2 <= 0) {
                    return;
                }
                char c = cArr[i];
                i++;
                if (c != '\n' && c != '\r' && !z2) {
                    printEscaped(c);
                } else {
                    this._printer.printText(c);
                }
                i2 = i3;
            }
        } else {
            while (true) {
                int i4 = i2 - 1;
                if (i2 <= 0) {
                    return;
                }
                char c2 = cArr[i];
                i++;
                if (c2 == ' ' || c2 == '\f' || c2 == '\t' || c2 == '\n' || c2 == '\r') {
                    this._printer.printSpace();
                } else if (z2) {
                    this._printer.printText(c2);
                } else {
                    printEscaped(c2);
                }
                i2 = i4;
            }
        }
    }

    public void printEscaped(String str) throws IOException {
        int i;
        int i2 = 0;
        while (i2 < str.length()) {
            int iCharAt = str.charAt(i2);
            if ((iCharAt & Ddeml.XCLASS_MASK) == 55296 && (i = i2 + 1) < str.length()) {
                char cCharAt = str.charAt(i);
                if ((64512 & cCharAt) == 56320) {
                    iCharAt = ((((iCharAt - 55296) << 10) + 65536) + cCharAt) - 56320;
                    i2 = i;
                }
            }
            printEscaped(iCharAt);
            i2++;
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        try {
            comment(new String(cArr, i, i2));
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void characters(String str) throws IOException {
        ElementState elementStateContent = content();
        boolean z = elementStateContent.inCData;
        if (!z && !elementStateContent.doCData) {
            if (elementStateContent.preserveSpace) {
                int nextIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                printText(str, true, elementStateContent.unescaped);
                this._printer.setNextIndent(nextIndent);
                return;
            }
            printText(str, false, elementStateContent.unescaped);
            return;
        }
        if (!z) {
            this._printer.printText("<![CDATA[");
            elementStateContent.inCData = true;
        }
        int nextIndent2 = this._printer.getNextIndent();
        this._printer.setNextIndent(0);
        printCDATAText(str);
        this._printer.setNextIndent(nextIndent2);
    }
}
