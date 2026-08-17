package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Properties;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ToHTMLSAXHandler extends ToSAXHandler {
    private boolean m_dtdHandled;
    protected boolean m_escapeSetting;

    public ToHTMLSAXHandler(ContentHandler contentHandler, String str) {
        super(contentHandler, str);
        this.m_dtdHandled = false;
        this.m_escapeSetting = true;
    }

    private void resetToHTMLSAXHandler() {
        this.m_escapeSetting = true;
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(String str) throws SAXException {
        int length = str.length();
        if (length > this.m_charsBuff.length) {
            this.m_charsBuff = new char[(length * 2) + 1];
        }
        str.getChars(0, length, this.m_charsBuff, 0);
        characters(this.m_charsBuff, 0, length);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void close() {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler
    public void closeStartTag() throws SAXException {
        ElemContext elemContext = this.m_elemContext;
        elemContext.m_startTagOpen = false;
        ContentHandler contentHandler = this.m_saxHandler;
        String str = elemContext.m_elementName;
        contentHandler.startElement("", str, str, this.m_attributes);
        this.m_attributes.clear();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        flushPending();
        LexicalHandler lexicalHandler = this.m_lexHandler;
        if (lexicalHandler != null) {
            lexicalHandler.comment(cArr, i, i2);
        }
        if (this.m_tracer != null) {
            super.fireCommentEvent(cArr, i, i2);
        }
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void elementDecl(String str, String str2) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        flushPending();
        this.m_saxHandler.endDocument();
        if (this.m_tracer != null) {
            super.fireEndDoc();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void endElement(String str) throws SAXException {
        flushPending();
        this.m_saxHandler.endElement("", str, str);
        if (this.m_tracer != null) {
            super.fireEndElem(str);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void flushPending() throws SAXException {
        if (this.m_needToCallStartDocument) {
            startDocumentInternal();
            this.m_needToCallStartDocument = false;
        }
        if (this.m_elemContext.m_startTagOpen) {
            closeStartTag();
            this.m_elemContext.m_startTagOpen = false;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Properties getOutputFormat() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public OutputStream getOutputStream() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Writer getWriter() {
        return null;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
    }

    public void indent(int i) throws SAXException {
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void internalEntityDecl(String str, String str2) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void namespaceAfterStartElement(String str, String str2) throws SAXException {
        ElemContext elemContext = this.m_elemContext;
        if (elemContext.m_elementURI == null && SerializerBase.getPrefixPart(elemContext.m_elementName) == null && "".equals(str)) {
            this.m_elemContext.m_elementURI = str2;
        }
        startPrefixMapping(str, str2, false);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        flushPending();
        this.m_saxHandler.processingInstruction(str, str2);
        if (this.m_tracer != null) {
            super.fireEscapingEvent(str, str2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public boolean reset() {
        if (!super.reset()) {
            return false;
        }
        resetToHTMLSAXHandler();
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.DOMSerializer
    public void serialize(Node node) throws IOException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public boolean setEscaping(boolean z) throws SAXException {
        boolean z2 = this.m_escapeSetting;
        this.m_escapeSetting = z;
        if (z) {
            processingInstruction("javax.xml.transform.enable-output-escaping", "");
            return z2;
        }
        processingInstruction("javax.xml.transform.disable-output-escaping", "");
        return z2;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setIndent(boolean z) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setOutputFormat(Properties properties) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setOutputStream(OutputStream outputStream) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setWriter(Writer writer) {
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str, String str2, String str3) throws SAXException {
        LexicalHandler lexicalHandler;
        super.startElement(str, str2, str3);
        flushPending();
        if (!this.m_dtdHandled) {
            String doctypeSystem = getDoctypeSystem();
            String doctypePublic = getDoctypePublic();
            if ((doctypeSystem != null || doctypePublic != null) && (lexicalHandler = this.m_lexHandler) != null) {
                lexicalHandler.startDTD(str3, doctypePublic, doctypeSystem);
            }
            this.m_dtdHandled = true;
        }
        this.m_elemContext = this.m_elemContext.push(str, str2, str3);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public boolean startPrefixMapping(String str, String str2, boolean z) throws SAXException {
        if (z) {
            flushPending();
        }
        this.m_saxHandler.startPrefixMapping(str, str2);
        return false;
    }

    public ToHTMLSAXHandler(ContentHandler contentHandler, LexicalHandler lexicalHandler, String str) {
        super(contentHandler, lexicalHandler, str);
        this.m_dtdHandled = false;
        this.m_escapeSetting = true;
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        startPrefixMapping(str, str2, true);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        flushPending();
        this.m_saxHandler.endElement(str, str2, str3);
        if (this.m_tracer != null) {
            super.fireEndElem(str3);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        flushPending();
        this.m_saxHandler.characters(cArr, i, i2);
        if (this.m_tracer != null) {
            super.fireCharEvent(cArr, i, i2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        flushPending();
        super.startElement(str, str2, str3, attributes);
        this.m_saxHandler.startElement(str, str2, str3, attributes);
        this.m_elemContext.m_startTagOpen = false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str) throws SAXException {
        startElement(null, null, str);
    }
}
