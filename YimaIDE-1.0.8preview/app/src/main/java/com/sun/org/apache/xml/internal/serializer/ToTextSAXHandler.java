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
public final class ToTextSAXHandler extends ToSAXHandler {
    public ToTextSAXHandler(ContentHandler contentHandler, LexicalHandler lexicalHandler, String str) {
        super(contentHandler, lexicalHandler, str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5, boolean z) {
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
        this.m_saxHandler.characters(this.m_charsBuff, 0, length);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedLexicalHandler
    public void comment(String str) throws SAXException {
        int length = str.length();
        if (length > this.m_charsBuff.length) {
            this.m_charsBuff = new char[(length * 2) + 1];
        }
        str.getChars(0, length, this.m_charsBuff, 0);
        comment(this.m_charsBuff, 0, length);
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
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        if (this.m_tracer != null) {
            super.fireEscapingEvent(str, str2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public boolean reset() {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.DOMSerializer
    public void serialize(Node node) throws IOException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public boolean setEscaping(boolean z) {
        return false;
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

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        flushPending();
        super.startElement(str, str2, str3, attributes);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public boolean startPrefixMapping(String str, String str2, boolean z) throws SAXException {
        return false;
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
    }

    public ToTextSAXHandler(ContentHandler contentHandler, String str) {
        super(contentHandler, str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str, String str2, String str3) throws SAXException {
        super.startElement(str, str2, str3);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        if (this.m_tracer != null) {
            super.fireEndElem(str3);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToSAXHandler, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str) throws SAXException {
        super.startElement(str);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_tracer != null) {
            super.fireCommentEvent(cArr, i, i2);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        this.m_saxHandler.characters(cArr, i, i2);
        if (this.m_tracer != null) {
            super.fireCharEvent(cArr, i, i2);
        }
    }
}
