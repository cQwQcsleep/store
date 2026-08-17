package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.impl.xs.util.SimpleLocator;
import com.sun.org.apache.xerces.internal.jaxp.validation.WrappedSAXException;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAX2XNI implements ContentHandler, XMLDocumentSource {
    private XMLDocumentHandler fCore;
    private Locator locator;
    private final NamespaceSupport nsContext = new NamespaceSupport();
    private final SymbolTable symbolTable = new SymbolTable();
    private final XMLAttributes xa = new XMLAttributesImpl();

    public SAX2XNI(XMLDocumentHandler xMLDocumentHandler) {
        this.fCore = xMLDocumentHandler;
    }

    private XMLAttributes createAttributes(Attributes attributes) {
        this.xa.removeAllAttributes();
        int length = attributes.getLength();
        int i = 0;
        while (true) {
            XMLAttributes xMLAttributes = this.xa;
            if (i >= length) {
                return xMLAttributes;
            }
            xMLAttributes.addAttribute(createQName(attributes.getURI(i), attributes.getLocalName(i), attributes.getQName(i)), attributes.getType(i), attributes.getValue(i));
            i++;
        }
    }

    private QName createQName(String str, String str2, String str3) {
        int iIndexOf = str3.indexOf(58);
        if (str2.length() == 0) {
            str = "";
            str2 = iIndexOf < 0 ? str3 : str3.substring(iIndexOf + 1);
        }
        String strSubstring = iIndexOf < 0 ? null : str3.substring(0, iIndexOf);
        if (str != null && str.length() == 0) {
            str = null;
        }
        return new QName(symbolize(strSubstring), symbolize(str2), symbolize(str3), symbolize(str));
    }

    private XMLString createXMLString(String str) {
        return new XMLString(str.toCharArray(), 0, str.length());
    }

    private String symbolize(String str) {
        if (str == null) {
            return null;
        }
        return this.symbolTable.addSymbol(str);
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        try {
            this.fCore.characters(new XMLString(cArr, i, i2), null);
        } catch (WrappedSAXException e) {
            throw e.exception;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        try {
            this.fCore.endDocument(null);
        } catch (WrappedSAXException e) {
            throw e.exception;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        try {
            this.fCore.endElement(createQName(str, str2, str3), null);
        } catch (WrappedSAXException e) {
            throw e.exception;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) {
        this.nsContext.popContext();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public XMLDocumentHandler getDocumentHandler() {
        return this.fCore;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        try {
            this.fCore.ignorableWhitespace(new XMLString(cArr, i, i2), null);
        } catch (WrappedSAXException e) {
            throw e.exception;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        try {
            this.fCore.processingInstruction(symbolize(str), createXMLString(str2), null);
        } catch (WrappedSAXException e) {
            throw e.exception;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fCore = xMLDocumentHandler;
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) {
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        try {
            this.nsContext.reset();
            Locator locator = this.locator;
            this.fCore.startDocument(locator == null ? new SimpleLocator(null, null, -1, -1) : new LocatorWrapper(locator), null, this.nsContext, null);
        } catch (WrappedSAXException e) {
            throw e.exception;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        try {
            this.fCore.startElement(createQName(str, str2, str3), createAttributes(attributes), null);
        } catch (WrappedSAXException e) {
            throw e.exception;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) {
        this.nsContext.pushContext();
        this.nsContext.declarePrefix(str, str2);
    }
}
