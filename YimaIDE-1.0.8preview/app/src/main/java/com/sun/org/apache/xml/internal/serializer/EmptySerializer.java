package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.Properties;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.Transformer;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EmptySerializer implements SerializationHandler {
    protected static final String ERR = "EmptySerializer method not over-ridden";

    public void aMethodIsCalled() {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5, boolean z) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttributes(Attributes attributes) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addUniqueAttribute(String str, String str2, int i) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addXSLAttribute(String str, String str2, String str3) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public ContentHandler asContentHandler() throws IOException {
        couldThrowIOException();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Object asDOM3Serializer() throws IOException {
        couldThrowIOException();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public DOMSerializer asDOMSerializer() throws IOException {
        couldThrowIOException();
        return null;
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(String str) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void close() {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedLexicalHandler
    public void comment(String str) throws SAXException {
        couldThrowSAXException();
    }

    public void couldThrowIOException() throws IOException {
    }

    public void couldThrowSAXException() throws SAXException {
    }

    public void couldThrowSAXException(String str) throws SAXException {
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void elementDecl(String str, String str2) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void endElement(String str) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void entityReference(String str) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void flushPending() throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getDoctypePublic() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getDoctypeSystem() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getEncoding() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public boolean getIndent() {
        aMethodIsCalled();
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public int getIndentAmount() {
        aMethodIsCalled();
        return 0;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getMediaType() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public NamespaceMappings getNamespaceMappings() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getNamespaceURI(String str, boolean z) {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getNamespaceURIFromPrefix(String str) {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public boolean getOmitXMLDeclaration() {
        aMethodIsCalled();
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Properties getOutputFormat() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getOutputProperty(String str) {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getOutputPropertyDefault(String str) {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public OutputStream getOutputStream() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getPrefix(String str) {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getStandalone() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public Transformer getTransformer() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getVersion() {
        aMethodIsCalled();
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Writer getWriter() {
        aMethodIsCalled();
        return null;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void internalEntityDecl(String str, String str2) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void namespaceAfterStartElement(String str, String str2) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String str, String str2, String str3) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public boolean reset() {
        aMethodIsCalled();
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.DOMSerializer
    public void serialize(Node node) throws IOException {
        couldThrowIOException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setCdataSectionElements(List<String> list) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setContentHandler(ContentHandler contentHandler) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setDTDEntityExpansion(boolean z) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctype(String str, String str2) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctypePublic(String str) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctypeSystem(String str) {
        aMethodIsCalled();
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setEncoding(String str) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public boolean setEscaping(boolean z) throws SAXException {
        couldThrowSAXException();
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setIndent(boolean z) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setIndentAmount(int i) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setIsStandalone(boolean z) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setMediaType(String str) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setNamespaceMappings(NamespaceMappings namespaceMappings) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setOmitXMLDeclaration(boolean z) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setOutputFormat(Properties properties) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setOutputProperty(String str, String str2) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setOutputPropertyDefault(String str, String str2) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setOutputStream(OutputStream outputStream) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void setSourceLocator(SourceLocator sourceLocator) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setStandalone(String str) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setTransformer(Transformer transformer) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setVersion(String str) {
        aMethodIsCalled();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setWriter(Writer writer) {
        aMethodIsCalled();
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str, String str2, String str3) throws SAXException {
        couldThrowSAXException(str3);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public boolean startPrefixMapping(String str, String str2, boolean z) throws SAXException {
        couldThrowSAXException();
        return false;
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
        couldThrowSAXException();
    }

    public void couldThrowSAXException(char[] cArr, int i, int i2) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2) {
        aMethodIsCalled();
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        couldThrowSAXException(cArr, i, i2);
    }

    public void comment(char[] cArr, int i, int i2) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str) throws SAXException {
        couldThrowSAXException(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5) throws SAXException {
        couldThrowSAXException();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(Node node) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        couldThrowSAXException();
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        couldThrowSAXException();
    }
}
