package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaDOMParser;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SAXLocatorWrapper;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLAttributesImpl;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class SchemaContentHandler implements ContentHandler {
    private boolean fNeedPushNSContext;
    private SchemaDOMParser fSchemaDOMParser;
    private SymbolTable fSymbolTable;
    private final SAXLocatorWrapper fSAXLocatorWrapper = new SAXLocatorWrapper();
    private NamespaceSupport fNamespaceContext = new NamespaceSupport();
    private boolean fNamespacePrefixes = false;
    private boolean fStringsInternalized = false;
    private final QName fElementQName = new QName();
    private final QName fAttributeQName = new QName();
    private final XMLAttributesImpl fAttributes = new XMLAttributesImpl();
    private final XMLString fTempString = new XMLString();
    private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();

    private void addNamespaceDeclarations(int i) {
        String str;
        String strAddSymbol;
        for (int i2 = 0; i2 < i; i2++) {
            String declaredPrefixAt = this.fNamespaceContext.getDeclaredPrefixAt(i2);
            String uri = this.fNamespaceContext.getURI(declaredPrefixAt);
            if (declaredPrefixAt.length() > 0) {
                str = XMLSymbols.PREFIX_XMLNS;
                this.fStringBuffer.clear();
                this.fStringBuffer.append(str);
                this.fStringBuffer.append(':');
                this.fStringBuffer.append(declaredPrefixAt);
                SymbolTable symbolTable = this.fSymbolTable;
                XMLStringBuffer xMLStringBuffer = this.fStringBuffer;
                strAddSymbol = symbolTable.addSymbol(xMLStringBuffer.ch, xMLStringBuffer.offset, xMLStringBuffer.length);
            } else {
                str = XMLSymbols.EMPTY_STRING;
                declaredPrefixAt = XMLSymbols.PREFIX_XMLNS;
                strAddSymbol = declaredPrefixAt;
            }
            this.fAttributeQName.setValues(str, declaredPrefixAt, strAddSymbol, NamespaceContext.XMLNS_URI);
            XMLAttributesImpl xMLAttributesImpl = this.fAttributes;
            QName qName = this.fAttributeQName;
            String str2 = XMLSymbols.fCDATASymbol;
            if (uri == null) {
                uri = XMLSymbols.EMPTY_STRING;
            }
            xMLAttributesImpl.addAttribute(qName, str2, uri);
        }
    }

    public static void convertToSAXException(XNIException xNIException) throws SAXException {
        Exception exception = xNIException.getException();
        if (exception == null) {
            throw new SAXException(xNIException.getMessage());
        }
        if (!(exception instanceof SAXException)) {
            throw new SAXException(exception);
        }
        throw ((SAXException) exception);
    }

    public static void convertToSAXParseException(XMLParseException xMLParseException) throws SAXException {
        Exception exception = xMLParseException.getException();
        if (exception != null) {
            if (!(exception instanceof SAXException)) {
                throw new SAXException(exception);
            }
            throw ((SAXException) exception);
        }
        LocatorImpl locatorImpl = new LocatorImpl();
        locatorImpl.setPublicId(xMLParseException.getPublicId());
        locatorImpl.setSystemId(xMLParseException.getExpandedSystemId());
        locatorImpl.setLineNumber(xMLParseException.getLineNumber());
        locatorImpl.setColumnNumber(xMLParseException.getColumnNumber());
        throw new SAXParseException(xMLParseException.getMessage(), locatorImpl);
    }

    private void fillQName(QName qName, String str, String str2, String str3) {
        String strAddSymbol;
        String strAddSymbol2 = null;
        if (this.fStringsInternalized) {
            if (str != null && str.length() == 0) {
                str = null;
            }
            if (str2 == null) {
                str2 = XMLSymbols.EMPTY_STRING;
            }
            if (str3 == null) {
                str3 = XMLSymbols.EMPTY_STRING;
            }
            strAddSymbol2 = str;
            strAddSymbol = str2;
        } else {
            if (str != null && str.length() > 0) {
                strAddSymbol2 = this.fSymbolTable.addSymbol(str);
            }
            strAddSymbol = str2 != null ? this.fSymbolTable.addSymbol(str2) : XMLSymbols.EMPTY_STRING;
            str3 = str3 != null ? this.fSymbolTable.addSymbol(str3) : XMLSymbols.EMPTY_STRING;
        }
        String str4 = XMLSymbols.EMPTY_STRING;
        int iIndexOf = str3.indexOf(58);
        if (iIndexOf != -1) {
            String strAddSymbol3 = this.fSymbolTable.addSymbol(str3.substring(0, iIndexOf));
            if (strAddSymbol == str4) {
                strAddSymbol = this.fSymbolTable.addSymbol(str3.substring(iIndexOf + 1));
            }
            str4 = strAddSymbol3;
        } else if (strAddSymbol == str4) {
            strAddSymbol = str3;
        }
        qName.setValues(str4, strAddSymbol, str3, strAddSymbol2);
    }

    private void fillXMLAttributes(Attributes attributes) {
        this.fAttributes.removeAllAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            fillQName(this.fAttributeQName, attributes.getURI(i), attributes.getLocalName(i), attributes.getQName(i));
            String type = attributes.getType(i);
            XMLAttributesImpl xMLAttributesImpl = this.fAttributes;
            QName qName = this.fAttributeQName;
            if (type == null) {
                type = XMLSymbols.fCDATASymbol;
            }
            xMLAttributesImpl.addAttributeNS(qName, type, attributes.getValue(i));
            this.fAttributes.setSpecified(i, true);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        try {
            this.fTempString.setValues(cArr, i, i2);
            this.fSchemaDOMParser.characters(this.fTempString, null);
        } catch (XMLParseException e) {
            convertToSAXParseException(e);
        } catch (XNIException e2) {
            convertToSAXException(e2);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        this.fSAXLocatorWrapper.setLocator(null);
        try {
            this.fSchemaDOMParser.endDocument(null);
        } catch (XMLParseException e) {
            convertToSAXParseException(e);
        } catch (XNIException e2) {
            convertToSAXException(e2);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        fillQName(this.fElementQName, str, str2, str3);
        try {
            this.fSchemaDOMParser.endElement(this.fElementQName, null);
        } catch (XNIException e) {
            convertToSAXException(e);
        } catch (XMLParseException e2) {
            convertToSAXParseException(e2);
        } finally {
            this.fNamespaceContext.popContext();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
    }

    public Document getDocument() {
        return this.fSchemaDOMParser.getDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        try {
            this.fTempString.setValues(cArr, i, i2);
            this.fSchemaDOMParser.ignorableWhitespace(this.fTempString, null);
        } catch (XMLParseException e) {
            convertToSAXParseException(e);
        } catch (XNIException e2) {
            convertToSAXException(e2);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        try {
            this.fTempString.setValues(str2.toCharArray(), 0, str2.length());
            this.fSchemaDOMParser.processingInstruction(str, this.fTempString, null);
        } catch (XMLParseException e) {
            convertToSAXParseException(e);
        } catch (XNIException e2) {
            convertToSAXException(e2);
        }
    }

    public void reset(SchemaDOMParser schemaDOMParser, SymbolTable symbolTable, boolean z, boolean z2) {
        this.fSchemaDOMParser = schemaDOMParser;
        this.fSymbolTable = symbolTable;
        this.fNamespacePrefixes = z;
        this.fStringsInternalized = z2;
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this.fSAXLocatorWrapper.setLocator(locator);
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        this.fNeedPushNSContext = true;
        this.fNamespaceContext.reset();
        try {
            this.fSchemaDOMParser.startDocument(this.fSAXLocatorWrapper, null, this.fNamespaceContext, null);
        } catch (XMLParseException e) {
            convertToSAXParseException(e);
        } catch (XNIException e2) {
            convertToSAXException(e2);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        int declaredPrefixCount;
        if (this.fNeedPushNSContext) {
            this.fNamespaceContext.pushContext();
        }
        this.fNeedPushNSContext = true;
        fillQName(this.fElementQName, str, str2, str3);
        fillXMLAttributes(attributes);
        if (!this.fNamespacePrefixes && (declaredPrefixCount = this.fNamespaceContext.getDeclaredPrefixCount()) > 0) {
            addNamespaceDeclarations(declaredPrefixCount);
        }
        try {
            this.fSchemaDOMParser.startElement(this.fElementQName, this.fAttributes, null);
        } catch (XMLParseException e) {
            convertToSAXParseException(e);
        } catch (XNIException e2) {
            convertToSAXException(e2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0037 A[PHI: r3
      0x0037: PHI (r3v3 java.lang.String) = (r3v1 java.lang.String), (r3v6 java.lang.String), (r3v6 java.lang.String) binds: [B:18:0x0035, B:10:0x001c, B:12:0x0022] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        if (this.fNeedPushNSContext) {
            this.fNeedPushNSContext = false;
            this.fNamespaceContext.pushContext();
        }
        if (this.fStringsInternalized) {
            if (str == null) {
                str = XMLSymbols.EMPTY_STRING;
            }
            if (str2 != null && str2.length() == 0) {
                str2 = null;
            }
        } else {
            str = str != null ? this.fSymbolTable.addSymbol(str) : XMLSymbols.EMPTY_STRING;
            if (str2 == null || str2.length() <= 0) {
                str2 = null;
            } else {
                str2 = this.fSymbolTable.addSymbol(str2);
            }
        }
        this.fNamespaceContext.declarePrefix(str, str2);
    }
}
