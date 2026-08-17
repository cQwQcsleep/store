package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import defpackage.x73;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StAXStream2SAX implements XMLReader, Locator {
    private final XMLStreamReader staxStreamReader;
    private ContentHandler _sax = null;
    private LexicalHandler _lex = null;
    private SAXImpl _saxImpl = null;

    public StAXStream2SAX(XMLStreamReader xMLStreamReader) {
        this.staxStreamReader = xMLStreamReader;
    }

    private Attributes getAttributes() {
        AttributesImpl attributesImpl = new AttributesImpl();
        int eventType = this.staxStreamReader.getEventType();
        if (eventType != 10 && eventType != 1) {
            ck6.a("getAttributes() attempting to process: ", eventType);
            return null;
        }
        for (int i = 0; i < this.staxStreamReader.getAttributeCount(); i++) {
            String attributeNamespace = this.staxStreamReader.getAttributeNamespace(i);
            if (attributeNamespace == null) {
                attributeNamespace = "";
            }
            String attributeLocalName = this.staxStreamReader.getAttributeLocalName(i);
            String attributePrefix = this.staxStreamReader.getAttributePrefix(i);
            attributesImpl.addAttribute(attributeNamespace, attributeLocalName, (attributePrefix == null || attributePrefix.length() == 0) ? attributeLocalName : attributePrefix + ':' + attributeLocalName, this.staxStreamReader.getAttributeType(i), this.staxStreamReader.getAttributeValue(i));
        }
        return attributesImpl;
    }

    private void handleAttribute() {
    }

    private void handleCDATA() {
    }

    private void handleCharacters() throws XMLStreamException {
        int textLength = this.staxStreamReader.getTextLength();
        char[] cArr = new char[textLength];
        this.staxStreamReader.getTextCharacters(0, cArr, 0, textLength);
        try {
            this._sax.characters(cArr, 0, textLength);
        } catch (SAXException e) {
            az3.a(e);
        }
    }

    private void handleComment() {
    }

    private void handleDTD() {
    }

    private void handleEndDocument() throws SAXException {
        this._sax.endDocument();
    }

    private void handleEndElement() throws XMLStreamException {
        QName name = this.staxStreamReader.getName();
        try {
            this._sax.endElement(name.getNamespaceURI(), name.getLocalPart(), ((name.getPrefix() == null || name.getPrefix().trim().length() == 0) ? "" : name.getPrefix() + ":") + name.getLocalPart());
            for (int namespaceCount = this.staxStreamReader.getNamespaceCount() + (-1); namespaceCount >= 0; namespaceCount--) {
                String namespacePrefix = this.staxStreamReader.getNamespacePrefix(namespaceCount);
                if (namespacePrefix == null) {
                    namespacePrefix = "";
                }
                this._sax.endPrefixMapping(namespacePrefix);
            }
        } catch (SAXException e) {
            az3.a(e);
        }
    }

    private void handleEntityDecl() {
    }

    private void handleEntityReference() {
    }

    private void handleNamespace() {
    }

    private void handleNotationDecl() {
    }

    private void handlePI() throws XMLStreamException {
        try {
            this._sax.processingInstruction(this.staxStreamReader.getPITarget(), this.staxStreamReader.getPIData());
        } catch (SAXException e) {
            az3.a(e);
        }
    }

    private void handleSpace() {
    }

    private void handleStartDocument() throws SAXException {
        this._sax.setDocumentLocator(new Locator2() { // from class: com.sun.org.apache.xalan.internal.xsltc.trax.StAXStream2SAX.1
            @Override // org.xml.sax.Locator
            public int getColumnNumber() {
                return StAXStream2SAX.this.staxStreamReader.getLocation().getColumnNumber();
            }

            @Override // org.xml.sax.ext.Locator2
            public String getEncoding() {
                return StAXStream2SAX.this.staxStreamReader.getEncoding();
            }

            @Override // org.xml.sax.Locator
            public int getLineNumber() {
                return StAXStream2SAX.this.staxStreamReader.getLocation().getLineNumber();
            }

            @Override // org.xml.sax.Locator
            public String getPublicId() {
                return StAXStream2SAX.this.staxStreamReader.getLocation().getPublicId();
            }

            @Override // org.xml.sax.Locator
            public String getSystemId() {
                return StAXStream2SAX.this.staxStreamReader.getLocation().getSystemId();
            }

            @Override // org.xml.sax.ext.Locator2
            public String getXMLVersion() {
                return StAXStream2SAX.this.staxStreamReader.getVersion();
            }
        });
        this._sax.startDocument();
    }

    private void handleStartElement() throws XMLStreamException {
        XMLStreamReader xMLStreamReader;
        String localPart;
        try {
            int namespaceCount = this.staxStreamReader.getNamespaceCount();
            int i = 0;
            while (true) {
                xMLStreamReader = this.staxStreamReader;
                if (i >= namespaceCount) {
                    break;
                }
                String namespacePrefix = xMLStreamReader.getNamespacePrefix(i);
                String str = "";
                if (namespacePrefix == null) {
                    namespacePrefix = "";
                }
                String namespaceURI = this.staxStreamReader.getNamespaceURI(i);
                if (namespaceURI != null || !namespacePrefix.isEmpty()) {
                    str = namespaceURI;
                }
                this._sax.startPrefixMapping(namespacePrefix, str);
                i++;
            }
            QName name = xMLStreamReader.getName();
            String prefix = name.getPrefix();
            if (prefix == null || prefix.length() == 0) {
                localPart = name.getLocalPart();
            } else {
                localPart = prefix + ':' + name.getLocalPart();
            }
            this._sax.startElement(name.getNamespaceURI(), name.getLocalPart(), localPart, getAttributes());
        } catch (SAXException e) {
            az3.a(e);
        }
    }

    public void bridge() throws XMLStreamException {
        boolean z;
        try {
            int eventType = this.staxStreamReader.getEventType();
            int i = 0;
            if (eventType == 7) {
                eventType = this.staxStreamReader.next();
                z = true;
            } else {
                z = false;
            }
            handleStartDocument();
            while (eventType != 1) {
                if (eventType == 3) {
                    handlePI();
                } else if (eventType == 4) {
                    handleCharacters();
                } else if (eventType == 5) {
                    handleComment();
                } else if (eventType == 6) {
                    handleSpace();
                } else {
                    if (eventType != 11) {
                        throw new InternalError("processing prolog event: " + eventType);
                    }
                    handleDTD();
                }
                eventType = this.staxStreamReader.next();
            }
            do {
                switch (eventType) {
                    case 1:
                        i++;
                        handleStartElement();
                        break;
                    case 2:
                        handleEndElement();
                        i--;
                        break;
                    case 3:
                        handlePI();
                        break;
                    case 4:
                        handleCharacters();
                        break;
                    case 5:
                        handleComment();
                        break;
                    case 6:
                        handleSpace();
                        break;
                    case 7:
                    case 8:
                    default:
                        throw new InternalError("processing event: " + eventType);
                    case 9:
                        handleEntityReference();
                        break;
                    case 10:
                        handleAttribute();
                        break;
                    case 11:
                        handleDTD();
                        break;
                    case 12:
                        handleCDATA();
                        break;
                    case 13:
                        handleNamespace();
                        break;
                    case 14:
                        handleNotationDecl();
                        break;
                    case 15:
                        handleEntityDecl();
                        break;
                }
                eventType = this.staxStreamReader.next();
            } while (i != 0);
            if (z) {
                while (eventType != 8) {
                    if (eventType == 3) {
                        handlePI();
                    } else if (eventType == 4) {
                        handleCharacters();
                    } else if (eventType == 5) {
                        handleComment();
                    } else {
                        if (eventType != 6) {
                            throw new InternalError("processing misc event after document element: " + eventType);
                        }
                        handleSpace();
                    }
                    eventType = this.staxStreamReader.next();
                }
            }
            handleEndDocument();
        } catch (SAXException e) {
            az3.a(e);
        }
    }

    @Override // org.xml.sax.Locator
    public int getColumnNumber() {
        return 0;
    }

    @Override // org.xml.sax.XMLReader
    public ContentHandler getContentHandler() {
        return this._sax;
    }

    @Override // org.xml.sax.XMLReader
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public ErrorHandler getErrorHandler() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        return false;
    }

    @Override // org.xml.sax.Locator
    public int getLineNumber() {
        return 0;
    }

    @Override // org.xml.sax.XMLReader
    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        return null;
    }

    @Override // org.xml.sax.Locator
    public String getPublicId() {
        return null;
    }

    @Override // org.xml.sax.Locator
    public String getSystemId() {
        return null;
    }

    @Override // org.xml.sax.XMLReader
    public void parse(InputSource inputSource) throws SAXException, IOException {
        try {
            bridge();
        } catch (XMLStreamException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.XMLReader
    public void setContentHandler(ContentHandler contentHandler) throws NullPointerException {
        this._sax = contentHandler;
        if (contentHandler instanceof LexicalHandler) {
            this._lex = (LexicalHandler) contentHandler;
        }
        if (contentHandler instanceof SAXImpl) {
            this._saxImpl = (SAXImpl) contentHandler;
        }
    }

    @Override // org.xml.sax.XMLReader
    public void setDTDHandler(DTDHandler dTDHandler) throws NullPointerException {
    }

    @Override // org.xml.sax.XMLReader
    public void setEntityResolver(EntityResolver entityResolver) throws NullPointerException {
    }

    @Override // org.xml.sax.XMLReader
    public void setErrorHandler(ErrorHandler errorHandler) throws NullPointerException {
    }

    @Override // org.xml.sax.XMLReader
    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
    }

    @Override // org.xml.sax.XMLReader
    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
    }

    public void parse() throws SAXException, XMLStreamException, IOException {
        bridge();
    }

    @Override // org.xml.sax.XMLReader
    public void parse(String str) throws SAXException, IOException {
        throw new IOException("This method is not yet implemented.");
    }
}
