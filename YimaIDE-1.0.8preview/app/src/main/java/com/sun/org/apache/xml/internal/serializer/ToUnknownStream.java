package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.Transformer;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ToUnknownStream extends SerializerBase {
    private static final String EMPTYSTRING = "";
    private String m_firstElementLocalName;
    private String m_firstElementName;
    private String m_firstElementPrefix;
    private String m_firstElementURI;
    private boolean m_firstTagNotEmitted;
    private SerializationHandler m_handler;
    private List<String> m_namespacePrefix;
    private List<String> m_namespaceURI;
    private boolean m_needToCallStartDocument;
    private boolean m_wrapped_handler_not_initialized;

    public ToUnknownStream(ErrorListener errorListener) {
        this.m_wrapped_handler_not_initialized = false;
        this.m_firstElementLocalName = null;
        this.m_firstTagNotEmitted = true;
        this.m_namespaceURI = null;
        this.m_namespacePrefix = null;
        this.m_needToCallStartDocument = false;
        this.m_handler = new ToXMLStream(errorListener);
    }

    private void emitFirstTag() throws SAXException {
        if (this.m_firstElementName != null) {
            if (this.m_wrapped_handler_not_initialized) {
                initStreamOutput();
                this.m_wrapped_handler_not_initialized = false;
            }
            this.m_handler.startElement(this.m_firstElementURI, null, this.m_firstElementName, this.m_attributes);
            this.m_attributes = null;
            List<String> list = this.m_namespacePrefix;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    this.m_handler.startPrefixMapping(this.m_namespacePrefix.get(i), this.m_namespaceURI.get(i), false);
                }
                this.m_namespacePrefix = null;
                this.m_namespaceURI = null;
            }
            this.m_firstTagNotEmitted = false;
        }
    }

    private void flush() {
        try {
            if (this.m_firstTagNotEmitted) {
                emitFirstTag();
            }
            if (this.m_needToCallStartDocument) {
                this.m_handler.startDocument();
                this.m_needToCallStartDocument = false;
            }
        } catch (SAXException e) {
            throw new RuntimeException(e.toString());
        }
    }

    private String getLocalNameUnknown(String str) {
        int iLastIndexOf = str.lastIndexOf(58);
        if (iLastIndexOf >= 0) {
            str = str.substring(iLastIndexOf + 1);
        }
        int iLastIndexOf2 = str.lastIndexOf(64);
        return iLastIndexOf2 >= 0 ? str.substring(iLastIndexOf2 + 1) : str;
    }

    private String getPrefixPartUnknown(String str) {
        int iIndexOf = str.indexOf(58);
        return iIndexOf > 0 ? str.substring(0, iIndexOf) : "";
    }

    private void initStreamOutput() throws SAXException {
        if (isFirstElemHTML()) {
            SerializationHandler serializationHandler = this.m_handler;
            this.m_handler = (SerializationHandler) SerializerFactory.getSerializer(OutputPropertiesFactory.getDefaultMethodProperties("html"));
            Writer writer = serializationHandler.getWriter();
            if (writer != null) {
                this.m_handler.setWriter(writer);
            } else {
                OutputStream outputStream = serializationHandler.getOutputStream();
                if (outputStream != null) {
                    this.m_handler.setOutputStream(outputStream);
                }
            }
            this.m_handler.setVersion(serializationHandler.getVersion());
            this.m_handler.setDoctypeSystem(serializationHandler.getDoctypeSystem());
            this.m_handler.setDoctypePublic(serializationHandler.getDoctypePublic());
            this.m_handler.setMediaType(serializationHandler.getMediaType());
            this.m_handler.setTransformer(serializationHandler.getTransformer());
        }
        if (this.m_needToCallStartDocument) {
            this.m_handler.startDocument();
            this.m_needToCallStartDocument = false;
        }
        this.m_wrapped_handler_not_initialized = false;
    }

    private boolean isFirstElemHTML() {
        List<String> list;
        String str;
        boolean zEqualsIgnoreCase = getLocalNameUnknown(this.m_firstElementName).equalsIgnoreCase("html");
        if (zEqualsIgnoreCase && (str = this.m_firstElementURI) != null && !"".equals(str)) {
            zEqualsIgnoreCase = false;
        }
        if (zEqualsIgnoreCase && (list = this.m_namespacePrefix) != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String str2 = this.m_namespacePrefix.get(i);
                String str3 = this.m_namespaceURI.get(i);
                String str4 = this.m_firstElementPrefix;
                if (str4 != null && str4.equals(str2) && !"".equals(str3)) {
                    return false;
                }
            }
        }
        return zEqualsIgnoreCase;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5, boolean z) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.addAttribute(str, str2, str3, str4, str5, z);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttributes(Attributes attributes) throws SAXException {
        this.m_handler.addAttributes(attributes);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addUniqueAttribute(String str, String str2, int i) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.addUniqueAttribute(str, str2, i);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public ContentHandler asContentHandler() throws IOException {
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public Object asDOM3Serializer() throws IOException {
        return this.m_handler.asDOM3Serializer();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public DOMSerializer asDOMSerializer() throws IOException {
        return this.m_handler.asDOMSerializer();
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
        this.m_handler.attributeDecl(str, str2, str3, str4, str5);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(String str) throws SAXException {
        int length = str == null ? 0 : str.length();
        if (length > this.m_charsBuff.length) {
            this.m_charsBuff = new char[(length * 2) + 1];
        }
        if (length > 0) {
            str.getChars(0, length, this.m_charsBuff, 0);
        }
        characters(this.m_charsBuff, 0, length);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void close() {
        this.m_handler.close();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedLexicalHandler
    public void comment(String str) throws SAXException {
        if (this.m_firstTagNotEmitted && this.m_firstElementName != null) {
            emitFirstTag();
        } else if (this.m_needToCallStartDocument) {
            this.m_handler.startDocument();
            this.m_needToCallStartDocument = false;
        }
        this.m_handler.comment(str);
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void elementDecl(String str, String str2) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            emitFirstTag();
        }
        this.m_handler.elementDecl(str, str2);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        this.m_handler.endCDATA();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
        this.m_handler.endDTD();
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.endDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        String str4;
        String str5;
        if (this.m_firstTagNotEmitted) {
            flush();
            if (str == null && (str5 = this.m_firstElementURI) != null) {
                str = str5;
            }
            if (str2 == null && (str4 = this.m_firstElementLocalName) != null) {
                str2 = str4;
            }
        }
        this.m_handler.endElement(str, str2, str3);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            emitFirstTag();
        }
        this.m_handler.endEntity(str);
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
        this.m_handler.endPrefixMapping(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void entityReference(String str) throws SAXException {
        this.m_handler.entityReference(str);
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.externalEntityDecl(str, str2, str3);
    }

    public void firePseudoElement(String str) {
        if (this.m_tracer != null) {
            StringBuffer stringBuffer = new StringBuffer("<");
            stringBuffer.append(str);
            char[] charArray = stringBuffer.toString().toCharArray();
            this.m_tracer.fireGenerateEvent(11, charArray, 0, charArray.length);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void flushPending() throws SAXException {
        flush();
        this.m_handler.flushPending();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getDoctypePublic() {
        return this.m_handler.getDoctypePublic();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getDoctypeSystem() {
        return this.m_handler.getDoctypeSystem();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getEncoding() {
        return this.m_handler.getEncoding();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public boolean getIndent() {
        return this.m_handler.getIndent();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public int getIndentAmount() {
        return this.m_handler.getIndentAmount();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getMediaType() {
        return this.m_handler.getMediaType();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public NamespaceMappings getNamespaceMappings() {
        SerializationHandler serializationHandler = this.m_handler;
        if (serializationHandler != null) {
            return serializationHandler.getNamespaceMappings();
        }
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getNamespaceURI(String str, boolean z) {
        return this.m_handler.getNamespaceURI(str, z);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getNamespaceURIFromPrefix(String str) {
        return this.m_handler.getNamespaceURIFromPrefix(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public boolean getOmitXMLDeclaration() {
        return this.m_handler.getOmitXMLDeclaration();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Properties getOutputFormat() {
        return this.m_handler.getOutputFormat();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public OutputStream getOutputStream() {
        return this.m_handler.getOutputStream();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getPrefix(String str) {
        return this.m_handler.getPrefix(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getStandalone() {
        return this.m_handler.getStandalone();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public Transformer getTransformer() {
        return this.m_handler.getTransformer();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getVersion() {
        return this.m_handler.getVersion();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Writer getWriter() {
        return this.m_handler.getWriter();
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.ignorableWhitespace(cArr, i, i2);
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void internalEntityDecl(String str, String str2) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.internalEntityDecl(str, str2);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void namespaceAfterStartElement(String str, String str2) throws SAXException {
        String str3;
        if (this.m_firstTagNotEmitted && this.m_firstElementURI == null && (str3 = this.m_firstElementName) != null && SerializerBase.getPrefixPart(str3) == null && "".equals(str)) {
            this.m_firstElementURI = str2;
        }
        startPrefixMapping(str, str2, false);
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.processingInstruction(str, str2);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public boolean reset() {
        return this.m_handler.reset();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.DOMSerializer
    public void serialize(Node node) throws IOException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.serialize(node);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setCdataSectionElements(List<String> list) {
        this.m_handler.setCdataSectionElements(list);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setContentHandler(ContentHandler contentHandler) {
        this.m_handler.setContentHandler(contentHandler);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctype(String str, String str2) {
        this.m_handler.setDoctypePublic(str2);
        this.m_handler.setDoctypeSystem(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctypePublic(String str) {
        this.m_handler.setDoctypePublic(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctypeSystem(String str) {
        this.m_handler.setDoctypeSystem(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
        this.m_handler.setDocumentLocator(locator);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setEncoding(String str) {
        this.m_handler.setEncoding(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public boolean setEscaping(boolean z) throws SAXException {
        return this.m_handler.setEscaping(z);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setIndent(boolean z) {
        this.m_handler.setIndent(z);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setIndentAmount(int i) {
        this.m_handler.setIndentAmount(i);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setIsStandalone(boolean z) {
        super.setIsStandalone(z);
        this.m_handler.setIsStandalone(z);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setMediaType(String str) {
        this.m_handler.setMediaType(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setOmitXMLDeclaration(boolean z) {
        this.m_handler.setOmitXMLDeclaration(z);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setOutputFormat(Properties properties) {
        this.m_handler.setOutputFormat(properties);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setOutputStream(OutputStream outputStream) {
        this.m_handler.setOutputStream(outputStream);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void setSourceLocator(SourceLocator sourceLocator) {
        this.m_handler.setSourceLocator(sourceLocator);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setStandalone(String str) {
        this.m_handler.setStandalone(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setTransformer(Transformer transformer) {
        this.m_handler.setTransformer(transformer);
        if (transformer instanceof SerializerTrace) {
            SerializerTrace serializerTrace = (SerializerTrace) transformer;
            if (serializerTrace.hasTraceListeners()) {
                this.m_tracer = serializerTrace;
                return;
            }
        }
        this.m_tracer = null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setVersion(String str) {
        this.m_handler.setVersion(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setWriter(Writer writer) {
        this.m_handler.setWriter(writer);
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
        this.m_handler.skippedEntity(str);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        this.m_handler.startCDATA();
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
        this.m_handler.startDTD(str, str2, str3);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        this.m_needToCallStartDocument = true;
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (this.m_needToCallSetDocumentInfo) {
            super.setDocumentInfo();
            this.m_needToCallSetDocumentInfo = false;
        }
        if (!this.m_firstTagNotEmitted) {
            this.m_handler.startElement(str, str2, str3, attributes);
            return;
        }
        if (this.m_firstElementName != null) {
            flush();
            this.m_handler.startElement(str, str2, str3, attributes);
            return;
        }
        this.m_wrapped_handler_not_initialized = true;
        this.m_firstElementName = str3;
        this.m_firstElementPrefix = getPrefixPartUnknown(str3);
        this.m_firstElementURI = str;
        this.m_firstElementLocalName = str2;
        if (this.m_tracer != null) {
            firePseudoElement(str3);
        }
        if (attributes != null) {
            super.addAttributes(attributes);
        }
        if (attributes != null) {
            flush();
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
        this.m_handler.startEntity(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public boolean startPrefixMapping(String str, String str2, boolean z) throws SAXException {
        if (!this.m_firstTagNotEmitted) {
            return this.m_handler.startPrefixMapping(str, str2, z);
        }
        if (this.m_firstElementName != null && z) {
            flush();
            return this.m_handler.startPrefixMapping(str, str2, z);
        }
        if (this.m_namespacePrefix == null) {
            this.m_namespacePrefix = new ArrayList();
            this.m_namespaceURI = new ArrayList();
        }
        this.m_namespacePrefix.add(str);
        this.m_namespaceURI.add(str2);
        if (this.m_firstElementURI == null && str.equals(this.m_firstElementPrefix)) {
            this.m_firstElementURI = str2;
        }
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5) throws SAXException {
        addAttribute(str, str2, str3, str4, str5, false);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2) {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.addAttribute(str, str2);
    }

    public ToUnknownStream() {
        this(null);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void endElement(String str) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.endElement(str);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.comment(cArr, i, i2);
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_firstTagNotEmitted) {
            flush();
        }
        this.m_handler.characters(cArr, i, i2);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str, String str2, String str3) throws SAXException {
        startElement(str, str2, str3, null);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str) throws SAXException {
        startElement(null, null, str, null);
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        startPrefixMapping(str, str2, true);
    }
}
