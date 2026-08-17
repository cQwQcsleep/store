package com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import defpackage.x73;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class HTMLSerializer extends BaseMarkupSerializer {
    public static final String XHTMLNamespace = "http://www.w3.org/1999/xhtml";
    private boolean _xhtml;
    private String fUserXHTMLNamespace;

    public HTMLSerializer(Writer writer, OutputFormat outputFormat) {
        this(false, outputFormat == null ? new OutputFormat("html", "ISO-8859-1", false) : outputFormat);
        setOutputCharStream(writer);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        try {
            content().doCData = false;
            super.characters(cArr, i, i2);
        } catch (IOException e) {
            x73.a(e);
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
        String str4;
        String str5;
        this._printer.unindent();
        ElementState elementState = getElementState();
        String str6 = elementState.namespaceURI;
        if (str6 == null || str6.length() == 0) {
            str4 = elementState.rawName;
        } else {
            str4 = (elementState.namespaceURI.equals("http://www.w3.org/1999/xhtml") || ((str5 = this.fUserXHTMLNamespace) != null && str5.equals(elementState.namespaceURI))) ? elementState.localName : null;
        }
        boolean z = this._xhtml;
        boolean z2 = elementState.empty;
        if (!z) {
            if (z2) {
                this._printer.printText('>');
            }
            if (str4 == null || !HTMLdtd.isOnlyOpening(str4)) {
                if (this._indenting && !elementState.preserveSpace && elementState.afterElement) {
                    this._printer.breakLine();
                }
                if (elementState.inCData) {
                    this._printer.printText("]]>");
                }
                this._printer.printText("</");
                this._printer.printText(elementState.rawName);
                this._printer.printText('>');
            }
        } else if (z2) {
            this._printer.printText(" />");
        } else {
            if (elementState.inCData) {
                this._printer.printText("]]>");
            }
            this._printer.printText("</");
            this._printer.printText(elementState.rawName.toLowerCase(Locale.ENGLISH));
            this._printer.printText('>');
        }
        ElementState elementStateLeaveElementState = leaveElementState();
        if (str4 == null || (!str4.equalsIgnoreCase("A") && !str4.equalsIgnoreCase("TD"))) {
            elementStateLeaveElementState.afterElement = true;
        }
        elementStateLeaveElementState.empty = false;
        if (isDocumentState()) {
            this._printer.flush();
        }
    }

    public String escapeURI(String str) {
        int iIndexOf = str.indexOf("\"");
        return iIndexOf >= 0 ? str.substring(0, iIndexOf) : str;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public String getEntityRef(int i) {
        return HTMLdtd.fromChar(i);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void serializeElement(Element element) throws IOException {
        String tagName = element.getTagName();
        ElementState elementState = getElementState();
        if (!isDocumentState()) {
            if (elementState.empty) {
                this._printer.printText('>');
            }
            if (this._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
                this._printer.breakLine();
            }
        } else if (!this._started) {
            startDocument(tagName);
        }
        boolean z = elementState.preserveSpace;
        this._printer.printText('<');
        boolean z2 = this._xhtml;
        Printer printer = this._printer;
        if (z2) {
            printer.printText(tagName.toLowerCase(Locale.ENGLISH));
        } else {
            printer.printText(tagName);
        }
        this._printer.indent();
        NamedNodeMap attributes = element.getAttributes();
        if (attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                Attr attr = (Attr) attributes.item(i);
                String lowerCase = attr.getName().toLowerCase(Locale.ENGLISH);
                String value = attr.getValue();
                if (attr.getSpecified()) {
                    this._printer.printSpace();
                    if (this._xhtml) {
                        Printer printer2 = this._printer;
                        if (value == null) {
                            printer2.printText(lowerCase);
                            this._printer.printText("=\"\"");
                        } else {
                            printer2.printText(lowerCase);
                            this._printer.printText("=\"");
                            printEscaped(value);
                            this._printer.printText('\"');
                        }
                    } else {
                        if (value == null) {
                            value = "";
                        }
                        if (!this._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            this._printer.printText(lowerCase);
                        } else if (HTMLdtd.isURI(tagName, lowerCase)) {
                            this._printer.printText(lowerCase);
                            this._printer.printText("=\"");
                            this._printer.printText(escapeURI(value));
                            this._printer.printText('\"');
                        } else {
                            boolean zIsBoolean = HTMLdtd.isBoolean(tagName, lowerCase);
                            Printer printer3 = this._printer;
                            if (zIsBoolean) {
                                printer3.printText(lowerCase);
                            } else {
                                printer3.printText(lowerCase);
                                this._printer.printText("=\"");
                                printEscaped(value);
                                this._printer.printText('\"');
                            }
                        }
                    }
                }
            }
        }
        if (HTMLdtd.isPreserveSpace(tagName)) {
            z = true;
        }
        if (!element.hasChildNodes() && HTMLdtd.isEmptyTag(tagName)) {
            this._printer.unindent();
            boolean z3 = this._xhtml;
            Printer printer4 = this._printer;
            if (z3) {
                printer4.printText(" />");
            } else {
                printer4.printText('>');
            }
            elementState.afterElement = true;
            elementState.empty = false;
            if (isDocumentState()) {
                this._printer.flush();
                return;
            }
            return;
        }
        ElementState elementStateEnterElementState = enterElementState(null, null, tagName, z);
        if (tagName.equalsIgnoreCase("A") || tagName.equalsIgnoreCase("TD")) {
            elementStateEnterElementState.empty = false;
            this._printer.printText('>');
        }
        if (tagName.equalsIgnoreCase("SCRIPT") || tagName.equalsIgnoreCase("STYLE")) {
            if (this._xhtml) {
                elementStateEnterElementState.doCData = true;
            } else {
                elementStateEnterElementState.unescaped = true;
            }
        }
        for (Node firstChild = element.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            serializeNode(firstChild);
        }
        endElementIO(null, null, tagName);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer, com.sun.org.apache.xml.internal.serialize.Serializer
    public void setOutputFormat(OutputFormat outputFormat) {
        if (outputFormat == null) {
            outputFormat = new OutputFormat("html", "ISO-8859-1", false);
        }
        super.setOutputFormat(outputFormat);
    }

    public void setXHTMLNamespace(String str) {
        this.fUserXHTMLNamespace = str;
    }

    public void startDocument(String str) throws IOException {
        boolean z;
        this._printer.leaveDTD();
        if (!this._started) {
            if (this._docTypePublicId == null && this._docTypeSystemId == null) {
                if (this._xhtml) {
                    this._docTypePublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
                    this._docTypeSystemId = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
                } else {
                    this._docTypePublicId = "-//W3C//DTD HTML 4.01//EN";
                    this._docTypeSystemId = "http://www.w3.org/TR/html4/strict.dtd";
                }
            }
            if (!this._format.getOmitDocumentType()) {
                if (this._docTypePublicId != null && (!(z = this._xhtml) || this._docTypeSystemId != null)) {
                    Printer printer = this._printer;
                    if (z) {
                        printer.printText("<!DOCTYPE html PUBLIC ");
                    } else {
                        printer.printText("<!DOCTYPE HTML PUBLIC ");
                    }
                    printDoctypeURL(this._docTypePublicId);
                    if (this._docTypeSystemId != null) {
                        boolean z2 = this._indenting;
                        Printer printer2 = this._printer;
                        if (z2) {
                            printer2.breakLine();
                            this._printer.printText("                      ");
                        } else {
                            printer2.printText(' ');
                        }
                        printDoctypeURL(this._docTypeSystemId);
                    }
                    this._printer.printText('>');
                    this._printer.breakLine();
                } else if (this._docTypeSystemId != null) {
                    boolean z3 = this._xhtml;
                    Printer printer3 = this._printer;
                    if (z3) {
                        printer3.printText("<!DOCTYPE html SYSTEM ");
                    } else {
                        printer3.printText("<!DOCTYPE HTML SYSTEM ");
                    }
                    printDoctypeURL(this._docTypeSystemId);
                    this._printer.printText('>');
                    this._printer.breakLine();
                }
            }
        }
        this._started = true;
        serializePreRoot();
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        String str4;
        boolean z;
        String prefix;
        String str5;
        try {
            String str6 = null;
            if (this._printer == null) {
                throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
            }
            ElementState elementState = getElementState();
            if (!isDocumentState()) {
                if (elementState.empty) {
                    this._printer.printText('>');
                }
                if (this._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
                    this._printer.breakLine();
                }
            } else if (!this._started) {
                startDocument((str2 == null || str2.length() == 0) ? str3 : str2);
            }
            boolean z2 = elementState.preserveSpace;
            boolean z3 = (str == null || str.length() == 0) ? false : true;
            if (str3 == null || str3.length() == 0) {
                str4 = (!z3 || (prefix = getPrefix(str)) == null || prefix.length() == 0) ? str2 : prefix + ":" + str2;
                z = true;
            } else {
                str4 = str3;
                z = false;
            }
            if (!z3) {
                str6 = str4;
            } else if (str.equals("http://www.w3.org/1999/xhtml") || ((str5 = this.fUserXHTMLNamespace) != null && str5.equals(str))) {
                str6 = str2;
            }
            this._printer.printText('<');
            boolean z4 = this._xhtml;
            Printer printer = this._printer;
            if (z4) {
                printer.printText(str4.toLowerCase(Locale.ENGLISH));
            } else {
                printer.printText(str4);
            }
            this._printer.indent();
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    this._printer.printSpace();
                    String lowerCase = attributes.getQName(i).toLowerCase(Locale.ENGLISH);
                    String value = attributes.getValue(i);
                    if (this._xhtml || z3) {
                        Printer printer2 = this._printer;
                        if (value == null) {
                            printer2.printText(lowerCase);
                            this._printer.printText("=\"\"");
                        } else {
                            printer2.printText(lowerCase);
                            this._printer.printText("=\"");
                            printEscaped(value);
                            this._printer.printText('\"');
                        }
                    } else {
                        if (value == null) {
                            value = "";
                        }
                        if (!this._format.getPreserveEmptyAttributes() && value.length() == 0) {
                            this._printer.printText(lowerCase);
                        } else if (HTMLdtd.isURI(str4, lowerCase)) {
                            this._printer.printText(lowerCase);
                            this._printer.printText("=\"");
                            this._printer.printText(escapeURI(value));
                            this._printer.printText('\"');
                        } else {
                            boolean zIsBoolean = HTMLdtd.isBoolean(str4, lowerCase);
                            Printer printer3 = this._printer;
                            if (zIsBoolean) {
                                printer3.printText(lowerCase);
                            } else {
                                printer3.printText(lowerCase);
                                this._printer.printText("=\"");
                                printEscaped(value);
                                this._printer.printText('\"');
                            }
                        }
                    }
                }
            }
            if (str6 != null && HTMLdtd.isPreserveSpace(str6)) {
                z2 = true;
            }
            if (z) {
                for (Map.Entry<String, String> entry : this._prefixes.entrySet()) {
                    this._printer.printSpace();
                    String key = entry.getKey();
                    String value2 = entry.getValue();
                    int length = value2.length();
                    Printer printer4 = this._printer;
                    if (length == 0) {
                        printer4.printText("xmlns=\"");
                        printEscaped(key);
                        this._printer.printText('\"');
                    } else {
                        printer4.printText("xmlns:");
                        this._printer.printText(value2);
                        this._printer.printText("=\"");
                        printEscaped(key);
                        this._printer.printText('\"');
                    }
                }
            }
            ElementState elementStateEnterElementState = enterElementState(str, str2, str4, z2);
            if (str6 != null && (str6.equalsIgnoreCase("A") || str6.equalsIgnoreCase("TD"))) {
                elementStateEnterElementState.empty = false;
                this._printer.printText('>');
            }
            if (str6 != null) {
                if (str4.equalsIgnoreCase("SCRIPT") || str4.equalsIgnoreCase("STYLE")) {
                    if (this._xhtml) {
                        elementStateEnterElementState.doCData = true;
                    } else {
                        elementStateEnterElementState.unescaped = true;
                    }
                }
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void endElement(String str) throws SAXException {
        endElement(null, null, str);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void characters(String str) throws IOException {
        content();
        super.characters(str);
    }

    public HTMLSerializer() {
        this(false, new OutputFormat("html", "ISO-8859-1", false));
    }

    public HTMLSerializer(OutputFormat outputFormat) {
        this(false, outputFormat == null ? new OutputFormat("html", "ISO-8859-1", false) : outputFormat);
    }

    public HTMLSerializer(boolean z, OutputFormat outputFormat) {
        super(outputFormat);
        this.fUserXHTMLNamespace = null;
        this._xhtml = z;
    }

    public HTMLSerializer(OutputStream outputStream, OutputFormat outputFormat) {
        this(false, outputFormat == null ? new OutputFormat("html", "ISO-8859-1", false) : outputFormat);
        setOutputByteStream(outputStream);
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
                    if (this._indenting && !elementState.preserveSpace && (elementState.empty || elementState.afterElement)) {
                        this._printer.breakLine();
                    }
                }
                boolean z = elementState.preserveSpace;
                this._printer.printText('<');
                boolean z2 = this._xhtml;
                Printer printer = this._printer;
                if (z2) {
                    printer.printText(str.toLowerCase(Locale.ENGLISH));
                } else {
                    printer.printText(str);
                }
                this._printer.indent();
                if (attributeList != null) {
                    for (int i = 0; i < attributeList.getLength(); i++) {
                        this._printer.printSpace();
                        String lowerCase = attributeList.getName(i).toLowerCase(Locale.ENGLISH);
                        String value = attributeList.getValue(i);
                        if (this._xhtml) {
                            Printer printer2 = this._printer;
                            if (value == null) {
                                printer2.printText(lowerCase);
                                this._printer.printText("=\"\"");
                            } else {
                                printer2.printText(lowerCase);
                                this._printer.printText("=\"");
                                printEscaped(value);
                                this._printer.printText('\"');
                            }
                        } else {
                            if (value == null) {
                                value = "";
                            }
                            if (!this._format.getPreserveEmptyAttributes() && value.length() == 0) {
                                this._printer.printText(lowerCase);
                            } else if (HTMLdtd.isURI(str, lowerCase)) {
                                this._printer.printText(lowerCase);
                                this._printer.printText("=\"");
                                this._printer.printText(escapeURI(value));
                                this._printer.printText('\"');
                            } else {
                                boolean zIsBoolean = HTMLdtd.isBoolean(str, lowerCase);
                                Printer printer3 = this._printer;
                                if (zIsBoolean) {
                                    printer3.printText(lowerCase);
                                } else {
                                    printer3.printText(lowerCase);
                                    this._printer.printText("=\"");
                                    printEscaped(value);
                                    this._printer.printText('\"');
                                }
                            }
                        }
                    }
                }
                if (HTMLdtd.isPreserveSpace(str)) {
                    z = true;
                }
                ElementState elementStateEnterElementState = enterElementState(null, null, str, z);
                if (str.equalsIgnoreCase("A") || str.equalsIgnoreCase("TD")) {
                    elementStateEnterElementState.empty = false;
                    this._printer.printText('>');
                }
                if (!str.equalsIgnoreCase("SCRIPT") && !str.equalsIgnoreCase("STYLE")) {
                    return;
                }
                if (this._xhtml) {
                    elementStateEnterElementState.doCData = true;
                    return;
                } else {
                    elementStateEnterElementState.unescaped = true;
                    return;
                }
            }
            throw new IllegalStateException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "NoWriterSupplied", null));
        } catch (IOException e) {
            x73.a(e);
        }
    }
}
