package com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import defpackage.x73;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSSerializerFilter;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class XMLSerializer extends BaseMarkupSerializer {
    protected static final boolean DEBUG = false;
    protected static final String PREFIX = "NS";
    protected NamespaceSupport fLocalNSBinder;
    protected NamespaceSupport fNSBinder;
    protected boolean fNamespacePrefixes;
    protected boolean fNamespaces;
    private boolean fPreserveSpace;
    protected SymbolTable fSymbolTable;

    public XMLSerializer(Writer writer, OutputFormat outputFormat) {
        super(outputFormat == null ? new OutputFormat("xml", null, false) : outputFormat);
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        this._format.setMethod("xml");
        setOutputCharStream(writer);
    }

    private Attributes extractNamespaces(Attributes attributes) throws SAXException {
        if (attributes == null) {
            return null;
        }
        int length = attributes.getLength();
        AttributesImpl attributesImpl = new AttributesImpl(attributes);
        for (int i = length - 1; i >= 0; i--) {
            String qName = attributesImpl.getQName(i);
            if (qName.startsWith("xmlns")) {
                if (qName.length() == 5) {
                    startPrefixMapping("", attributes.getValue(i));
                    attributesImpl.removeAttribute(i);
                } else if (qName.charAt(5) == ':') {
                    startPrefixMapping(qName.substring(6), attributes.getValue(i));
                    attributesImpl.removeAttribute(i);
                }
            }
        }
        return attributesImpl;
    }

    private void printAttribute(String str, String str2, boolean z, Attr attr) throws IOException {
        short sAcceptNode;
        if (z || (this.features & 64) == 0) {
            LSSerializerFilter lSSerializerFilter = this.fDOMFilter;
            if (lSSerializerFilter != null && (lSSerializerFilter.getWhatToShow() & 2) != 0 && ((sAcceptNode = this.fDOMFilter.acceptNode(attr)) == 2 || sAcceptNode == 3)) {
                return;
            }
            this._printer.printSpace();
            this._printer.printText(str);
            this._printer.printText("=\"");
            printEscaped(str2);
            this._printer.printText('\"');
        }
        if (str.equals(Constants.ATTRNAME_XMLSPACE)) {
            if (str2.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                this.fPreserveSpace = true;
            } else {
                this.fPreserveSpace = this._format.getPreserveSpace();
            }
        }
    }

    private void printNamespaceAttr(String str, String str2) throws IOException {
        this._printer.printSpace();
        String str3 = XMLSymbols.EMPTY_STRING;
        Printer printer = this._printer;
        if (str == str3) {
            printer.printText(XMLSymbols.PREFIX_XMLNS);
        } else {
            printer.printText("xmlns:" + str);
        }
        this._printer.printText("=\"");
        printEscaped(str2);
        this._printer.printText('\"');
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void checkUnboundNamespacePrefixedNode(Node node) throws IOException {
        if (this.fNamespaces) {
            Node firstChild = node.getFirstChild();
            while (firstChild != null) {
                Node nextSibling = firstChild.getNextSibling();
                String prefix = firstChild.getPrefix();
                String strAddSymbol = (prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix);
                if (this.fNSBinder.getURI(strAddSymbol) == null && strAddSymbol != null) {
                    fatalError("The replacement text of the entity node '" + node.getNodeName() + "' contains an element node '" + firstChild.getNodeName() + "' with an undeclared prefix '" + strAddSymbol + "'.");
                }
                if (firstChild.getNodeType() == 1) {
                    NamedNodeMap attributes = firstChild.getAttributes();
                    for (int i = 0; i < attributes.getLength(); i++) {
                        String prefix2 = attributes.item(i).getPrefix();
                        String strAddSymbol2 = (prefix2 == null || prefix2.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix2);
                        if (this.fNSBinder.getURI(strAddSymbol2) == null && strAddSymbol2 != null) {
                            fatalError("The replacement text of the entity node '" + node.getNodeName() + "' contains an element node '" + firstChild.getNodeName() + "' with an attribute '" + attributes.item(i).getNodeName() + "' an undeclared prefix '" + strAddSymbol2 + "'.");
                        }
                    }
                }
                if (firstChild.hasChildNodes()) {
                    checkUnboundNamespacePrefixedNode(firstChild);
                }
                firstChild = nextSibling;
            }
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        try {
            endElementIO(str, str2, str3);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void endElementIO(String str, String str2, String str3) throws IOException {
        this._printer.unindent();
        ElementState elementState = getElementState();
        if (elementState.empty) {
            this._printer.printText("/>");
        } else {
            if (elementState.inCData) {
                this._printer.printText("]]>");
            }
            if (this._indenting && !elementState.preserveSpace && (elementState.afterElement || elementState.afterComment)) {
                this._printer.breakLine();
            }
            this._printer.printText("</");
            this._printer.printText(elementState.rawName);
            this._printer.printText('>');
        }
        ElementState elementStateLeaveElementState = leaveElementState();
        elementStateLeaveElementState.afterElement = true;
        elementStateLeaveElementState.afterComment = false;
        elementStateLeaveElementState.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public String getEntityRef(int i) {
        if (i == 34) {
            return "quot";
        }
        if (i == 60) {
            return "lt";
        }
        if (i == 62) {
            return "gt";
        }
        if (i == 38) {
            return "amp";
        }
        if (i != 39) {
            return null;
        }
        return "apos";
    }

    /* JADX WARN: Code duplicated, block: B:31:0x007a  */
    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void printEscaped(String str) throws IOException {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (!XMLChar.isValid(cCharAt)) {
                i++;
                if (i < length) {
                    surrogates(cCharAt, str.charAt(i), false);
                } else {
                    StringBuilder sb = new StringBuilder("The character '");
                    sb.append(cCharAt);
                    sb.append("' is an invalid XML character");
                    fatalError(sb.toString());
                }
            } else if (cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t') {
                printHex(cCharAt);
            } else if (cCharAt == '<') {
                this._printer.printText(SerializerConstants.ENTITY_LT);
            } else if (cCharAt == '&') {
                this._printer.printText(SerializerConstants.ENTITY_AMP);
            } else if (cCharAt == '\"') {
                this._printer.printText(SerializerConstants.ENTITY_QUOT);
            } else if (cCharAt >= ' ') {
                char c = cCharAt;
                if (this._encodingInfo.isPrintable(c)) {
                    this._printer.printText(c);
                } else {
                    printHex(cCharAt);
                }
            } else {
                printHex(cCharAt);
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void printText(char[] cArr, int i, int i2, boolean z, boolean z2) throws IOException {
        if (z) {
            while (true) {
                int i3 = i2 - 1;
                if (i2 <= 0) {
                    return;
                }
                int i4 = i + 1;
                char c = cArr[i];
                if (XMLChar.isValid(c)) {
                    if (z2) {
                        this._printer.printText(c);
                    } else {
                        printXMLChar(c);
                    }
                    i2 = i3;
                } else {
                    i2 -= 2;
                    if (i3 > 0) {
                        i += 2;
                        surrogates(c, cArr[i4], true);
                    } else {
                        fatalError("The character '" + c + "' is an invalid XML character");
                    }
                }
                i = i4;
            }
        } else {
            while (true) {
                int i5 = i2 - 1;
                if (i2 <= 0) {
                    return;
                }
                int i6 = i + 1;
                char c2 = cArr[i];
                if (XMLChar.isValid(c2)) {
                    if (z2) {
                        this._printer.printText(c2);
                    } else {
                        printXMLChar(c2);
                    }
                    i2 = i5;
                } else {
                    i2 -= 2;
                    if (i5 > 0) {
                        i += 2;
                        surrogates(c2, cArr[i6], true);
                    } else {
                        fatalError("The character '" + c2 + "' is an invalid XML character");
                    }
                }
                i = i6;
            }
        }
    }

    public void printXMLChar(int i) throws IOException {
        if (i == 13) {
            printHex(i);
            return;
        }
        if (i == 60) {
            this._printer.printText(SerializerConstants.ENTITY_LT);
            return;
        }
        if (i == 38) {
            this._printer.printText(SerializerConstants.ENTITY_AMP);
            return;
        }
        if (i == 62) {
            this._printer.printText(SerializerConstants.ENTITY_GT);
            return;
        }
        if (i == 10 || i == 9 || (i >= 32 && this._encodingInfo.isPrintable((char) i))) {
            this._printer.printText((char) i);
        } else {
            printHex(i);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public boolean reset() {
        super.reset();
        NamespaceSupport namespaceSupport = this.fNSBinder;
        if (namespaceSupport == null) {
            return true;
        }
        namespaceSupport.reset();
        NamespaceSupport namespaceSupport2 = this.fNSBinder;
        String str = XMLSymbols.EMPTY_STRING;
        namespaceSupport2.declarePrefix(str, str);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:117:0x0202  */
    /* JADX WARN: Code duplicated, block: B:122:0x0222  */
    /* JADX WARN: Code duplicated, block: B:124:0x0226  */
    /* JADX WARN: Code duplicated, block: B:126:0x022a  */
    /* JADX WARN: Code duplicated, block: B:128:0x0230  */
    /* JADX WARN: Code duplicated, block: B:129:0x0233  */
    /* JADX WARN: Code duplicated, block: B:132:0x024d  */
    /* JADX WARN: Code duplicated, block: B:138:0x0261  */
    /* JADX WARN: Code duplicated, block: B:141:0x0271  */
    /* JADX WARN: Code duplicated, block: B:146:0x0289  */
    /* JADX WARN: Code duplicated, block: B:150:0x0298  */
    /* JADX WARN: Code duplicated, block: B:155:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:160:0x02ce  */
    /* JADX WARN: Code duplicated, block: B:162:0x02de A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:164:0x02f3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:165:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:168:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:171:0x0310 A[LOOP:4: B:169:0x0308->B:171:0x0310, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:175:0x033f  */
    /* JADX WARN: Code duplicated, block: B:178:0x0354  */
    /* JADX WARN: Code duplicated, block: B:180:0x035f  */
    /* JADX WARN: Code duplicated, block: B:182:0x036b  */
    /* JADX WARN: Code duplicated, block: B:184:0x036f  */
    /* JADX WARN: Code duplicated, block: B:189:0x0395  */
    /* JADX WARN: Code duplicated, block: B:191:0x039e  */
    /* JADX WARN: Code duplicated, block: B:223:0x038d A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:171:0x0310, please report this as an issue */
    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void serializeElement(Element element) throws IOException {
        int length;
        NamedNodeMap attributes;
        Element element2;
        int i;
        Attr attr;
        String value;
        String nodeName;
        String namespaceURI;
        String str;
        NamedNodeMap namedNodeMap;
        int i2;
        char c;
        String prefix;
        String strAddSymbol;
        String strAddSymbol2;
        String strAddSymbol3;
        String str2;
        String prefix2;
        int i3;
        String prefix3;
        String strAddSymbol4;
        String strAddSymbol5;
        String str3;
        String uri;
        String strAddSymbol6;
        String strAddSymbol7;
        if (this.fNamespaces) {
            this.fLocalNSBinder.reset();
            this.fNSBinder.pushContext();
        }
        String tagName = element.getTagName();
        ElementState elementState = getElementState();
        if (!isDocumentState()) {
            if (elementState.empty) {
                this._printer.printText('>');
            }
            if (elementState.inCData) {
                this._printer.printText("]]>");
                elementState.inCData = false;
            }
            if (this._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement || elementState.afterComment)) {
                this._printer.breakLine();
            }
        } else if (!this._started) {
            startDocument(tagName);
        }
        this.fPreserveSpace = elementState.preserveSpace;
        String str4 = null;
        if (element.hasAttributes()) {
            attributes = element.getAttributes();
            length = attributes.getLength();
        } else {
            length = 0;
            attributes = null;
        }
        if (this.fNamespaces) {
            for (int i4 = 0; i4 < length; i4++) {
                Attr attr2 = (Attr) attributes.item(i4);
                String namespaceURI2 = attr2.getNamespaceURI();
                if (namespaceURI2 != null) {
                    String str5 = NamespaceContext.XMLNS_URI;
                    if (namespaceURI2.equals(str5)) {
                        String nodeValue = attr2.getNodeValue();
                        if (nodeValue == null) {
                            nodeValue = XMLSymbols.EMPTY_STRING;
                        }
                        if (!nodeValue.equals(str5)) {
                            String prefix4 = attr2.getPrefix();
                            String strAddSymbol8 = (prefix4 == null || prefix4.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix4);
                            String strAddSymbol9 = this.fSymbolTable.addSymbol(attr2.getLocalName());
                            String str6 = XMLSymbols.PREFIX_XMLNS;
                            SymbolTable symbolTable = this.fSymbolTable;
                            if (strAddSymbol8 == str6) {
                                String strAddSymbol10 = symbolTable.addSymbol(nodeValue);
                                if (strAddSymbol10.length() != 0) {
                                    this.fNSBinder.declarePrefix(strAddSymbol9, strAddSymbol10);
                                }
                            } else {
                                this.fNSBinder.declarePrefix(XMLSymbols.EMPTY_STRING, symbolTable.addSymbol(nodeValue));
                            }
                        } else if (this.fDOMErrorHandler != null) {
                            modifyDOMError(DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "CantBindXMLNS", null), (short) 2, null, attr2);
                            if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                                f63.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SerializationStopped", null));
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
            String namespaceURI3 = element.getNamespaceURI();
            String prefix5 = element.getPrefix();
            if (namespaceURI3 == null || prefix5 == null || namespaceURI3.length() != 0 || prefix5.length() == 0) {
                this._printer.printText('<');
                this._printer.printText(tagName);
                this._printer.indent();
            } else {
                this._printer.printText('<');
                this._printer.printText(element.getLocalName());
                this._printer.indent();
                prefix5 = null;
            }
            if (namespaceURI3 != null) {
                String strAddSymbol11 = this.fSymbolTable.addSymbol(namespaceURI3);
                String strAddSymbol12 = (prefix5 == null || prefix5.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix5);
                if (this.fNSBinder.getURI(strAddSymbol12) != strAddSymbol11) {
                    if (this.fNamespacePrefixes) {
                        printNamespaceAttr(strAddSymbol12, strAddSymbol11);
                    }
                    this.fLocalNSBinder.declarePrefix(strAddSymbol12, strAddSymbol11);
                    this.fNSBinder.declarePrefix(strAddSymbol12, strAddSymbol11);
                }
            } else {
                if (element.getLocalName() != null) {
                    element2 = element;
                    NamespaceSupport namespaceSupport = this.fNSBinder;
                    String str7 = XMLSymbols.EMPTY_STRING;
                    String uri2 = namespaceSupport.getURI(str7);
                    if (uri2 != null && uri2.length() > 0) {
                        if (this.fNamespacePrefixes) {
                            printNamespaceAttr(str7, str7);
                        }
                        this.fLocalNSBinder.declarePrefix(str7, str7);
                        this.fNSBinder.declarePrefix(str7, str7);
                    }
                } else if (this.fDOMErrorHandler != null) {
                    element2 = element;
                    modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalElementName", new Object[]{element.getNodeName()}), (short) 2, null, element2);
                    if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                        f63.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SerializationStopped", null));
                        return;
                    }
                }
                i = 0;
                while (i < length) {
                    attr = (Attr) attributes.item(i);
                    value = attr.getValue();
                    nodeName = attr.getNodeName();
                    namespaceURI = attr.getNamespaceURI();
                    if (namespaceURI == null && namespaceURI.length() == 0) {
                        nodeName = attr.getLocalName();
                        str = str4;
                    } else {
                        str = namespaceURI;
                    }
                    if (value == null) {
                        value = XMLSymbols.EMPTY_STRING;
                    }
                    if (str != null) {
                        prefix = attr.getPrefix();
                        if (prefix == null) {
                            strAddSymbol = XMLSymbols.EMPTY_STRING;
                        } else {
                            strAddSymbol = this.fSymbolTable.addSymbol(prefix);
                        }
                        namedNodeMap = attributes;
                        strAddSymbol2 = this.fSymbolTable.addSymbol(attr.getLocalName());
                        if (str.equals(NamespaceContext.XMLNS_URI)) {
                            prefix3 = attr.getPrefix();
                            if (prefix3 != null || prefix3.length() == 0) {
                                strAddSymbol4 = XMLSymbols.EMPTY_STRING;
                            } else {
                                strAddSymbol4 = this.fSymbolTable.addSymbol(prefix3);
                            }
                            strAddSymbol5 = this.fSymbolTable.addSymbol(attr.getLocalName());
                            if (strAddSymbol4 == XMLSymbols.PREFIX_XMLNS) {
                                String uri3 = this.fLocalNSBinder.getURI(strAddSymbol5);
                                strAddSymbol7 = this.fSymbolTable.addSymbol(value);
                                if (strAddSymbol7.length() != 0 && uri3 == null) {
                                    if (this.fNamespacePrefixes) {
                                        printNamespaceAttr(strAddSymbol5, strAddSymbol7);
                                    }
                                    this.fLocalNSBinder.declarePrefix(strAddSymbol5, strAddSymbol7);
                                }
                            } else {
                                NamespaceSupport namespaceSupport2 = this.fNSBinder;
                                str3 = XMLSymbols.EMPTY_STRING;
                                namespaceSupport2.getURI(str3);
                                uri = this.fLocalNSBinder.getURI(str3);
                                strAddSymbol6 = this.fSymbolTable.addSymbol(value);
                                if (uri == null && this.fNamespacePrefixes) {
                                    printNamespaceAttr(str3, strAddSymbol6);
                                }
                            }
                            i2 = i;
                        } else {
                            strAddSymbol3 = this.fSymbolTable.addSymbol(str);
                            String uri4 = this.fNSBinder.getURI(strAddSymbol);
                            str2 = XMLSymbols.EMPTY_STRING;
                            if (strAddSymbol == str2 && uri4 == strAddSymbol3) {
                                i2 = i;
                            } else {
                                String nodeName2 = attr.getNodeName();
                                prefix2 = this.fNSBinder.getPrefix(strAddSymbol3);
                                i2 = i;
                                if (prefix2 != null || prefix2 == str2) {
                                    if (strAddSymbol == str2 && this.fLocalNSBinder.getURI(strAddSymbol) == null) {
                                        nodeName = nodeName2;
                                    } else {
                                        strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                        i3 = 2;
                                        while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                            strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                            i3++;
                                        }
                                        nodeName = strAddSymbol + ":" + strAddSymbol2;
                                    }
                                    if (this.fNamespacePrefixes) {
                                        printNamespaceAttr(strAddSymbol, strAddSymbol3);
                                    }
                                    value = this.fSymbolTable.addSymbol(value);
                                    this.fLocalNSBinder.declarePrefix(strAddSymbol, value);
                                    this.fNSBinder.declarePrefix(strAddSymbol, strAddSymbol3);
                                } else {
                                    nodeName = prefix2 + ":" + strAddSymbol2;
                                }
                            }
                            if (value == null) {
                                value = XMLSymbols.EMPTY_STRING;
                            }
                            printAttribute(nodeName, value, attr.getSpecified(), attr);
                        }
                        c = 2;
                    } else {
                        namedNodeMap = attributes;
                        length = length;
                        i2 = i;
                        if (attr.getLocalName() == null) {
                            if (this.fDOMErrorHandler != null) {
                                c = 2;
                                modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalAttrName", new Object[]{attr.getNodeName()}), (short) 2, null, attr);
                                if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                                    f63.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SerializationStopped", null));
                                    return;
                                }
                            } else {
                                c = 2;
                            }
                            printAttribute(nodeName, value, attr.getSpecified(), attr);
                        } else {
                            c = 2;
                            printAttribute(nodeName, value, attr.getSpecified(), attr);
                        }
                    }
                    i = i2 + 1;
                    attributes = namedNodeMap;
                    length = length;
                    str4 = null;
                }
            }
            element2 = element;
            i = 0;
            while (i < length) {
                attr = (Attr) attributes.item(i);
                value = attr.getValue();
                nodeName = attr.getNodeName();
                namespaceURI = attr.getNamespaceURI();
                if (namespaceURI == null) {
                    str = namespaceURI;
                } else {
                    str = namespaceURI;
                }
                if (value == null) {
                    value = XMLSymbols.EMPTY_STRING;
                }
                if (str != null) {
                    prefix = attr.getPrefix();
                    if (prefix == null) {
                        strAddSymbol = XMLSymbols.EMPTY_STRING;
                    } else {
                        strAddSymbol = this.fSymbolTable.addSymbol(prefix);
                    }
                    namedNodeMap = attributes;
                    strAddSymbol2 = this.fSymbolTable.addSymbol(attr.getLocalName());
                    if (str.equals(NamespaceContext.XMLNS_URI)) {
                        prefix3 = attr.getPrefix();
                        if (prefix3 != null) {
                            strAddSymbol4 = XMLSymbols.EMPTY_STRING;
                        } else {
                            strAddSymbol4 = XMLSymbols.EMPTY_STRING;
                        }
                        strAddSymbol5 = this.fSymbolTable.addSymbol(attr.getLocalName());
                        if (strAddSymbol4 == XMLSymbols.PREFIX_XMLNS) {
                            String uri5 = this.fLocalNSBinder.getURI(strAddSymbol5);
                            strAddSymbol7 = this.fSymbolTable.addSymbol(value);
                            if (strAddSymbol7.length() != 0) {
                                if (this.fNamespacePrefixes) {
                                    printNamespaceAttr(strAddSymbol5, strAddSymbol7);
                                }
                                this.fLocalNSBinder.declarePrefix(strAddSymbol5, strAddSymbol7);
                            }
                        } else {
                            NamespaceSupport namespaceSupport3 = this.fNSBinder;
                            str3 = XMLSymbols.EMPTY_STRING;
                            namespaceSupport3.getURI(str3);
                            uri = this.fLocalNSBinder.getURI(str3);
                            strAddSymbol6 = this.fSymbolTable.addSymbol(value);
                            if (uri == null) {
                                printNamespaceAttr(str3, strAddSymbol6);
                            }
                        }
                        i2 = i;
                    } else {
                        strAddSymbol3 = this.fSymbolTable.addSymbol(str);
                        String uri6 = this.fNSBinder.getURI(strAddSymbol);
                        str2 = XMLSymbols.EMPTY_STRING;
                        if (strAddSymbol == str2) {
                            String nodeName3 = attr.getNodeName();
                            prefix2 = this.fNSBinder.getPrefix(strAddSymbol3);
                            i2 = i;
                            if (prefix2 != null) {
                                if (strAddSymbol == str2) {
                                    strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                    i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                        strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                    nodeName = strAddSymbol + ":" + strAddSymbol2;
                                } else {
                                    strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                    i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                        strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                    nodeName = strAddSymbol + ":" + strAddSymbol2;
                                }
                                if (this.fNamespacePrefixes) {
                                    printNamespaceAttr(strAddSymbol, strAddSymbol3);
                                }
                                value = this.fSymbolTable.addSymbol(value);
                                this.fLocalNSBinder.declarePrefix(strAddSymbol, value);
                                this.fNSBinder.declarePrefix(strAddSymbol, strAddSymbol3);
                            } else {
                                if (strAddSymbol == str2) {
                                    strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                    i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                        strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                    nodeName = strAddSymbol + ":" + strAddSymbol2;
                                } else {
                                    strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                    i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                        strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                    nodeName = strAddSymbol + ":" + strAddSymbol2;
                                }
                                if (this.fNamespacePrefixes) {
                                    printNamespaceAttr(strAddSymbol, strAddSymbol3);
                                }
                                value = this.fSymbolTable.addSymbol(value);
                                this.fLocalNSBinder.declarePrefix(strAddSymbol, value);
                                this.fNSBinder.declarePrefix(strAddSymbol, strAddSymbol3);
                            }
                        } else {
                            String nodeName4 = attr.getNodeName();
                            prefix2 = this.fNSBinder.getPrefix(strAddSymbol3);
                            i2 = i;
                            if (prefix2 != null) {
                                if (strAddSymbol == str2) {
                                    strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                    i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                        strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                    nodeName = strAddSymbol + ":" + strAddSymbol2;
                                } else {
                                    strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                    i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                        strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                    nodeName = strAddSymbol + ":" + strAddSymbol2;
                                }
                                if (this.fNamespacePrefixes) {
                                    printNamespaceAttr(strAddSymbol, strAddSymbol3);
                                }
                                value = this.fSymbolTable.addSymbol(value);
                                this.fLocalNSBinder.declarePrefix(strAddSymbol, value);
                                this.fNSBinder.declarePrefix(strAddSymbol, strAddSymbol3);
                            } else {
                                if (strAddSymbol == str2) {
                                    strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                    i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                        strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                    nodeName = strAddSymbol + ":" + strAddSymbol2;
                                } else {
                                    strAddSymbol = this.fSymbolTable.addSymbol("NS1");
                                    i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol) != null) {
                                        strAddSymbol = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                    nodeName = strAddSymbol + ":" + strAddSymbol2;
                                }
                                if (this.fNamespacePrefixes) {
                                    printNamespaceAttr(strAddSymbol, strAddSymbol3);
                                }
                                value = this.fSymbolTable.addSymbol(value);
                                this.fLocalNSBinder.declarePrefix(strAddSymbol, value);
                                this.fNSBinder.declarePrefix(strAddSymbol, strAddSymbol3);
                            }
                        }
                        if (value == null) {
                            value = XMLSymbols.EMPTY_STRING;
                        }
                        printAttribute(nodeName, value, attr.getSpecified(), attr);
                    }
                    c = 2;
                } else {
                    namedNodeMap = attributes;
                    length = length;
                    i2 = i;
                    if (attr.getLocalName() == null) {
                        if (this.fDOMErrorHandler != null) {
                            c = 2;
                            modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalAttrName", new Object[]{attr.getNodeName()}), (short) 2, null, attr);
                            if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                                f63.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SerializationStopped", null));
                                return;
                            }
                        } else {
                            c = 2;
                        }
                        printAttribute(nodeName, value, attr.getSpecified(), attr);
                    } else {
                        c = 2;
                        printAttribute(nodeName, value, attr.getSpecified(), attr);
                    }
                }
                i = i2 + 1;
                attributes = namedNodeMap;
                length = length;
                str4 = null;
            }
        } else {
            this._printer.printText('<');
            this._printer.printText(tagName);
            this._printer.indent();
            for (int i5 = 0; i5 < length; i5++) {
                Attr attr3 = (Attr) attributes.item(i5);
                String name = attr3.getName();
                String value2 = attr3.getValue();
                if (value2 == null) {
                    value2 = "";
                }
                printAttribute(name, value2, attr3.getSpecified(), attr3);
            }
            element2 = element;
        }
        if (element2.hasChildNodes()) {
            ElementState elementStateEnterElementState = enterElementState(null, null, tagName, this.fPreserveSpace);
            elementStateEnterElementState.doCData = this._format.isCDataElement(tagName);
            elementStateEnterElementState.unescaped = this._format.isNonEscapingElement(tagName);
            for (Node firstChild = element2.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                serializeNode(firstChild);
            }
            if (this.fNamespaces) {
                this.fNSBinder.popContext();
            }
            endElementIO(null, null, tagName);
            return;
        }
        if (this.fNamespaces) {
            this.fNSBinder.popContext();
        }
        this._printer.unindent();
        this._printer.printText("/>");
        elementState.afterElement = true;
        elementState.afterComment = false;
        elementState.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    public void setNamespaces(boolean z) {
        this.fNamespaces = z;
        if (this.fNSBinder == null) {
            this.fNSBinder = new NamespaceSupport();
            this.fLocalNSBinder = new NamespaceSupport();
            this.fSymbolTable = new SymbolTable();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer, com.sun.org.apache.xml.internal.serialize.Serializer
    public void setOutputFormat(OutputFormat outputFormat) {
        if (outputFormat == null) {
            outputFormat = new OutputFormat("xml", null, false);
        }
        super.setOutputFormat(outputFormat);
    }

    public void startDocument(String str) throws IOException {
        String strLeaveDTD = this._printer.leaveDTD();
        if (!this._started) {
            if (!this._format.getOmitXMLDeclaration()) {
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
            if (!this._format.getOmitDocumentType()) {
                if (this._docTypeSystemId != null) {
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
                } else if (strLeaveDTD != null && strLeaveDTD.length() > 0) {
                    this._printer.printText("<!DOCTYPE ");
                    this._printer.printText(str);
                    this._printer.printText(" [");
                    printText(strLeaveDTD, true, true);
                    this._printer.printText("]>");
                    this._printer.breakLine();
                }
            }
        }
        this._started = true;
        serializePreRoot();
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        String prefix;
        String prefix2;
        try {
            if (this._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            }
            ElementState elementState = getElementState();
            if (!isDocumentState()) {
                if (elementState.empty) {
                    this._printer.printText('>');
                }
                if (elementState.inCData) {
                    this._printer.printText("]]>");
                    elementState.inCData = false;
                }
                if (this._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement || elementState.afterComment)) {
                    this._printer.breakLine();
                }
            } else if (!this._started) {
                startDocument((str2 == null || str2.length() == 0) ? str3 : str2);
            }
            boolean preserveSpace = elementState.preserveSpace;
            Attributes attributesExtractNamespaces = extractNamespaces(attributes);
            if (str3 == null || str3.length() == 0) {
                if (str2 == null) {
                    throw new SAXException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoName", null));
                }
                str3 = (str == null || str.equals("") || (prefix = getPrefix(str)) == null || prefix.length() <= 0) ? str2 : prefix + ":" + str2;
            }
            this._printer.printText('<');
            this._printer.printText(str3);
            this._printer.indent();
            if (attributesExtractNamespaces != null) {
                for (int i = 0; i < attributesExtractNamespaces.getLength(); i++) {
                    this._printer.printSpace();
                    String qName = attributesExtractNamespaces.getQName(i);
                    if (qName != null && qName.length() == 0) {
                        qName = attributesExtractNamespaces.getLocalName(i);
                        String uri = attributesExtractNamespaces.getURI(i);
                        if (uri != null && uri.length() != 0 && ((str == null || str.length() == 0 || !uri.equals(str)) && (prefix2 = getPrefix(uri)) != null && prefix2.length() > 0)) {
                            qName = prefix2 + ":" + qName;
                        }
                    }
                    String value = attributesExtractNamespaces.getValue(i);
                    if (value == null) {
                        value = "";
                    }
                    this._printer.printText(qName);
                    this._printer.printText("=\"");
                    printEscaped(value);
                    this._printer.printText('\"');
                    if (qName.equals(Constants.ATTRNAME_XMLSPACE)) {
                        preserveSpace = value.equals(SchemaSymbols.ATTVAL_PRESERVE) ? true : this._format.getPreserveSpace();
                    }
                }
            }
            Map<String, String> map = this._prefixes;
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this._printer.printSpace();
                    String key = entry.getKey();
                    String value2 = entry.getValue();
                    int length = value2.length();
                    Printer printer = this._printer;
                    if (length == 0) {
                        printer.printText("xmlns=\"");
                        printEscaped(key);
                        this._printer.printText('\"');
                    } else {
                        printer.printText("xmlns:");
                        this._printer.printText(value2);
                        this._printer.printText("=\"");
                        printEscaped(key);
                        this._printer.printText('\"');
                    }
                }
            }
            ElementState elementStateEnterElementState = enterElementState(str, str2, str3, preserveSpace);
            if (str2 != null && str2.length() != 0) {
                str3 = str + "^" + str2;
            }
            elementStateEnterElementState.doCData = this._format.isCDataElement(str3);
            elementStateEnterElementState.unescaped = this._format.isNonEscapingElement(str3);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void endElement(String str) throws SAXException {
        endElement(null, null, str);
    }

    public XMLSerializer(OutputFormat outputFormat) {
        super(outputFormat == null ? new OutputFormat("xml", null, false) : outputFormat);
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        this._format.setMethod("xml");
    }

    public XMLSerializer() {
        super(new OutputFormat("xml", null, false));
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
    }

    public XMLSerializer(OutputStream outputStream, OutputFormat outputFormat) {
        super(outputFormat == null ? new OutputFormat("xml", null, false) : outputFormat);
        this.fNamespaces = false;
        this.fNamespacePrefixes = true;
        this._format.setMethod("xml");
        setOutputByteStream(outputStream);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void printText(String str, boolean z, boolean z2) throws IOException {
        int length = str.length();
        int i = 0;
        if (z) {
            while (i < length) {
                char cCharAt = str.charAt(i);
                if (!XMLChar.isValid(cCharAt)) {
                    i++;
                    if (i < length) {
                        surrogates(cCharAt, str.charAt(i), true);
                    } else {
                        fatalError("The character '" + cCharAt + "' is an invalid XML character");
                    }
                } else if (z2) {
                    this._printer.printText(cCharAt);
                } else {
                    printXMLChar(cCharAt);
                }
                i++;
            }
            return;
        }
        while (i < length) {
            char cCharAt2 = str.charAt(i);
            if (!XMLChar.isValid(cCharAt2)) {
                i++;
                if (i < length) {
                    surrogates(cCharAt2, str.charAt(i), true);
                } else {
                    fatalError("The character '" + cCharAt2 + "' is an invalid XML character");
                }
            } else if (z2) {
                this._printer.printText(cCharAt2);
            } else {
                printXMLChar(cCharAt2);
            }
            i++;
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void startElement(String str, AttributeList attributeList) throws SAXException {
        try {
            if (this._printer != null) {
                ElementState elementState = getElementState();
                if (isDocumentState()) {
                    if (!this._started) {
                        startDocument(str);
                    }
                } else {
                    if (elementState.empty) {
                        this._printer.printText('>');
                    }
                    if (elementState.inCData) {
                        this._printer.printText("]]>");
                        elementState.inCData = false;
                    }
                    if (this._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement || elementState.afterComment)) {
                        this._printer.breakLine();
                    }
                }
                boolean preserveSpace = elementState.preserveSpace;
                this._printer.printText('<');
                this._printer.printText(str);
                this._printer.indent();
                if (attributeList != null) {
                    for (int i = 0; i < attributeList.getLength(); i++) {
                        this._printer.printSpace();
                        String name = attributeList.getName(i);
                        String value = attributeList.getValue(i);
                        if (value != null) {
                            this._printer.printText(name);
                            this._printer.printText("=\"");
                            printEscaped(value);
                            this._printer.printText('\"');
                        }
                        if (name.equals(Constants.ATTRNAME_XMLSPACE)) {
                            preserveSpace = value.equals(SchemaSymbols.ATTVAL_PRESERVE) ? true : this._format.getPreserveSpace();
                        }
                    }
                }
                ElementState elementStateEnterElementState = enterElementState(null, null, str, preserveSpace);
                elementStateEnterElementState.doCData = this._format.isCDataElement(str);
                elementStateEnterElementState.unescaped = this._format.isNonEscapingElement(str);
                return;
            }
            throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
        } catch (IOException e) {
            x73.a(e);
        }
    }
}
