package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import defpackage.x73;
import java.io.IOException;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
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
public class StAXEvent2SAX implements XMLReader, Locator {
    private final XMLEventReader staxEventReader;
    private ContentHandler _sax = null;
    private LexicalHandler _lex = null;
    private SAXImpl _saxImpl = null;
    private String version = null;
    private String encoding = null;

    public StAXEvent2SAX(XMLEventReader xMLEventReader) {
        this.staxEventReader = xMLEventReader;
    }

    private void bridge() throws XMLStreamException {
        boolean z;
        try {
            StartDocument startDocumentPeek = this.staxEventReader.peek();
            if (!startDocumentPeek.isStartDocument() && !startDocumentPeek.isStartElement()) {
                throw new IllegalStateException();
            }
            int i = 0;
            if (startDocumentPeek.getEventType() == 7) {
                this.version = startDocumentPeek.getVersion();
                if (startDocumentPeek.encodingSet()) {
                    this.encoding = startDocumentPeek.getCharacterEncodingScheme();
                }
                this.staxEventReader.nextEvent();
                startDocumentPeek = this.staxEventReader.nextEvent();
                z = true;
            } else {
                z = false;
            }
            handleStartDocument(startDocumentPeek);
            while (startDocumentPeek.getEventType() != 1) {
                int eventType = startDocumentPeek.getEventType();
                if (eventType == 3) {
                    handlePI((ProcessingInstruction) startDocumentPeek);
                } else if (eventType == 4) {
                    handleCharacters(startDocumentPeek.asCharacters());
                } else if (eventType == 5) {
                    handleComment();
                } else if (eventType == 6) {
                    handleSpace();
                } else {
                    if (eventType != 11) {
                        throw new InternalError("processing prolog event: " + startDocumentPeek);
                    }
                    handleDTD();
                }
                startDocumentPeek = this.staxEventReader.nextEvent();
            }
            do {
                switch (startDocumentPeek.getEventType()) {
                    case 1:
                        i++;
                        handleStartElement(startDocumentPeek.asStartElement());
                        break;
                    case 2:
                        handleEndElement(startDocumentPeek.asEndElement());
                        i--;
                        break;
                    case 3:
                        handlePI((ProcessingInstruction) startDocumentPeek);
                        break;
                    case 4:
                        handleCharacters(startDocumentPeek.asCharacters());
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
                        throw new InternalError("processing event: " + startDocumentPeek);
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
                startDocumentPeek = this.staxEventReader.nextEvent();
            } while (i != 0);
            if (z) {
                while (startDocumentPeek.getEventType() != 8) {
                    int eventType2 = startDocumentPeek.getEventType();
                    if (eventType2 == 3) {
                        handlePI((ProcessingInstruction) startDocumentPeek);
                    } else if (eventType2 == 4) {
                        handleCharacters(startDocumentPeek.asCharacters());
                    } else if (eventType2 == 5) {
                        handleComment();
                    } else {
                        if (eventType2 != 6) {
                            throw new InternalError("processing misc event after document element: " + startDocumentPeek);
                        }
                        handleSpace();
                    }
                    startDocumentPeek = this.staxEventReader.nextEvent();
                }
            }
            handleEndDocument();
        } catch (SAXException e) {
            az3.a(e);
        }
    }

    private Attributes getAttributes(StartElement startElement) {
        String str;
        AttributesImpl attributesImpl = new AttributesImpl();
        if (!startElement.isStartElement()) {
            throw new InternalError("getAttributes() attempting to process: " + startElement);
        }
        Iterator attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = (Attribute) attributes.next();
            String namespaceURI = attribute.getName().getNamespaceURI();
            if (namespaceURI == null) {
                namespaceURI = "";
            }
            String localPart = attribute.getName().getLocalPart();
            String prefix = attribute.getName().getPrefix();
            if (prefix == null || prefix.length() == 0) {
                str = localPart;
            } else {
                str = prefix + ':' + localPart;
            }
            attributesImpl.addAttribute(namespaceURI, localPart, str, attribute.getDTDType(), attribute.getValue());
        }
        return attributesImpl;
    }

    private void handleAttribute() {
    }

    private void handleCDATA() {
    }

    private void handleCharacters(Characters characters) throws XMLStreamException {
        try {
            this._sax.characters(characters.getData().toCharArray(), 0, characters.getData().length());
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

    private void handleEndElement(EndElement endElement) throws XMLStreamException {
        String str;
        QName name = endElement.getName();
        if (name.getPrefix() == null || name.getPrefix().trim().length() == 0) {
            str = "";
        } else {
            str = name.getPrefix() + ":";
        }
        try {
            this._sax.endElement(name.getNamespaceURI(), name.getLocalPart(), str + name.getLocalPart());
            Iterator<Namespace> namespaces = endElement.getNamespaces();
            while (namespaces.hasNext()) {
                String prefix = namespaces.next().getPrefix();
                if (prefix == null) {
                    prefix = "";
                }
                this._sax.endPrefixMapping(prefix);
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

    private void handlePI(ProcessingInstruction processingInstruction) throws XMLStreamException {
        try {
            this._sax.processingInstruction(processingInstruction.getTarget(), processingInstruction.getData());
        } catch (SAXException e) {
            az3.a(e);
        }
    }

    private void handleSpace() {
    }

    private void handleStartDocument(final XMLEvent xMLEvent) throws SAXException {
        this._sax.setDocumentLocator(new Locator2() { // from class: com.sun.org.apache.xalan.internal.xsltc.trax.StAXEvent2SAX.1
            @Override // org.xml.sax.Locator
            public int getColumnNumber() {
                return xMLEvent.getLocation().getColumnNumber();
            }

            @Override // org.xml.sax.ext.Locator2
            public String getEncoding() {
                return StAXEvent2SAX.this.encoding;
            }

            @Override // org.xml.sax.Locator
            public int getLineNumber() {
                return xMLEvent.getLocation().getLineNumber();
            }

            @Override // org.xml.sax.Locator
            public String getPublicId() {
                return xMLEvent.getLocation().getPublicId();
            }

            @Override // org.xml.sax.Locator
            public String getSystemId() {
                return xMLEvent.getLocation().getSystemId();
            }

            @Override // org.xml.sax.ext.Locator2
            public String getXMLVersion() {
                return StAXEvent2SAX.this.version;
            }
        });
        this._sax.startDocument();
    }

    private void handleStartElement(StartElement startElement) throws XMLStreamException {
        String localPart;
        try {
            Iterator namespaces = startElement.getNamespaces();
            while (namespaces.hasNext()) {
                String prefix = ((Namespace) namespaces.next()).getPrefix();
                if (prefix == null) {
                    prefix = "";
                }
                this._sax.startPrefixMapping(prefix, startElement.getNamespaceURI(prefix));
            }
            QName name = startElement.getName();
            String prefix2 = name.getPrefix();
            if (prefix2 == null || prefix2.length() == 0) {
                localPart = name.getLocalPart();
            } else {
                localPart = prefix2 + ':' + name.getLocalPart();
            }
            this._sax.startElement(name.getNamespaceURI(), name.getLocalPart(), localPart, getAttributes(startElement));
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
