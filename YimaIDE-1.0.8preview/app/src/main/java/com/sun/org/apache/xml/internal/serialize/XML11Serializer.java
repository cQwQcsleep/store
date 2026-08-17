package com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import defpackage.x73;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class XML11Serializer extends XMLSerializer {
    protected static final boolean DEBUG = false;
    protected static final String PREFIX = "NS";
    protected boolean fDOML1;
    protected NamespaceSupport fLocalNSBinder;
    protected NamespaceSupport fNSBinder;
    protected int fNamespaceCounter;
    protected boolean fNamespaces;
    protected SymbolTable fSymbolTable;

    public XML11Serializer(OutputStream outputStream, OutputFormat outputFormat) {
        super(outputStream, outputFormat == null ? new OutputFormat("xml", null, false) : outputFormat);
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        this._format.setVersion(SerializerConstants.XMLVERSION11);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer, org.xml.sax.ContentHandler
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
                } else if (!XML11Char.isXML11Valid(c)) {
                    i4++;
                    if (i4 < i5) {
                        surrogates(c, cArr[i4], true);
                    } else {
                        fatalError("The character '" + c + "' is an invalid XML character");
                    }
                } else if (this._encodingInfo.isPrintable(c) && XML11Char.isXML11ValidLiteral(c)) {
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

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public final void printCDATAText(String str) throws IOException {
        int i;
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == ']' && (i = i2 + 2) < length && str.charAt(i2 + 1) == ']' && str.charAt(i) == '>') {
                if (this.fDOMErrorHandler != null) {
                    short s = this.features;
                    if ((s & 16) == 0 && (s & 2) == 0) {
                        modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "EndingCDATA", null), (short) 3, null, this.fCurrentNode);
                        if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                            throw new IOException();
                        }
                    } else {
                        modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SplittingCDATA", null), (short) 1, null, this.fCurrentNode);
                        this.fDOMErrorHandler.handleError(this.fDOMError);
                    }
                }
                this._printer.printText(SerializerConstants.CDATA_CONTINUE);
                i2 = i;
            } else if (!XML11Char.isXML11Valid(cCharAt)) {
                i2++;
                if (i2 < length) {
                    surrogates(cCharAt, str.charAt(i2), true);
                } else {
                    fatalError("The character '" + cCharAt + "' is an invalid XML character");
                }
            } else if (this._encodingInfo.isPrintable(cCharAt) && XML11Char.isXML11ValidLiteral(cCharAt)) {
                this._printer.printText(cCharAt);
            } else {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(cCharAt));
                this._printer.printText(";<![CDATA[");
            }
            i2++;
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0083  */
    @Override // com.sun.org.apache.xml.internal.serialize.XMLSerializer, com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void printEscaped(String str) throws IOException {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (!XML11Char.isXML11Valid(cCharAt)) {
                i++;
                if (i < length) {
                    surrogates(cCharAt, str.charAt(i), false);
                } else {
                    StringBuilder sb = new StringBuilder("The character '");
                    sb.append(cCharAt);
                    sb.append("' is an invalid XML character");
                    fatalError(sb.toString());
                }
            } else if (cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t' || cCharAt == 133 || cCharAt == 8232) {
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

    @Override // com.sun.org.apache.xml.internal.serialize.XMLSerializer, com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void printText(char[] cArr, int i, int i2, boolean z, boolean z2) throws IOException {
        if (z) {
            while (true) {
                int i3 = i2 - 1;
                if (i2 <= 0) {
                    return;
                }
                int i4 = i + 1;
                char c = cArr[i];
                if (XML11Char.isXML11Valid(c)) {
                    if (z2 && XML11Char.isXML11ValidLiteral(c)) {
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
                if (XML11Char.isXML11Valid(c2)) {
                    if (z2 && XML11Char.isXML11ValidLiteral(c2)) {
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

    @Override // com.sun.org.apache.xml.internal.serialize.XMLSerializer
    public final void printXMLChar(int i) throws IOException {
        if (i == 13 || i == 133 || i == 8232) {
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
        char c = (char) i;
        if (this._encodingInfo.isPrintable(c) && XML11Char.isXML11ValidLiteral(i)) {
            this._printer.printText(c);
        } else {
            printHex(i);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serialize.XMLSerializer, com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public boolean reset() {
        super.reset();
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public final void surrogates(int i, int i2, boolean z) throws IOException {
        if (!XMLChar.isHighSurrogate(i)) {
            fatalError("The character '" + ((char) i) + "' is an invalid XML character");
            return;
        }
        if (!XMLChar.isLowSurrogate(i2)) {
            fatalError("The character '" + ((char) i2) + "' is an invalid XML character");
            return;
        }
        int iSupplemental = XMLChar.supplemental((char) i, (char) i2);
        if (!XML11Char.isXML11Valid(iSupplemental)) {
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

    public XML11Serializer(OutputFormat outputFormat) {
        super(outputFormat);
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        this._format.setVersion(SerializerConstants.XMLVERSION11);
    }

    public XML11Serializer(Writer writer, OutputFormat outputFormat) {
        super(writer, outputFormat);
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        this._format.setVersion(SerializerConstants.XMLVERSION11);
    }

    public XML11Serializer() {
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        this._format.setVersion(SerializerConstants.XMLVERSION11);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.XMLSerializer, com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer
    public void printText(String str, boolean z, boolean z2) throws IOException {
        int length = str.length();
        int i = 0;
        if (z) {
            while (i < length) {
                char cCharAt = str.charAt(i);
                if (!XML11Char.isXML11Valid(cCharAt)) {
                    i++;
                    if (i < length) {
                        surrogates(cCharAt, str.charAt(i), true);
                    } else {
                        fatalError("The character '" + cCharAt + "' is an invalid XML character");
                    }
                } else if (z2 && XML11Char.isXML11ValidLiteral(cCharAt)) {
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
            if (!XML11Char.isXML11Valid(cCharAt2)) {
                i++;
                if (i < length) {
                    surrogates(cCharAt2, str.charAt(i), true);
                } else {
                    fatalError("The character '" + cCharAt2 + "' is an invalid XML character");
                }
            } else if (z2 && XML11Char.isXML11ValidLiteral(cCharAt2)) {
                this._printer.printText(cCharAt2);
            } else {
                printXMLChar(cCharAt2);
            }
            i++;
        }
    }
}
