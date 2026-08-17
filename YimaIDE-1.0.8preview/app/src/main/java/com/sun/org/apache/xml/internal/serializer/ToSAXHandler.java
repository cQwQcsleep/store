package com.sun.org.apache.xml.internal.serializer;

import java.util.List;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ToSAXHandler extends SerializerBase {
    protected LexicalHandler m_lexHandler;
    protected ContentHandler m_saxHandler;
    private boolean m_shouldGenerateNSAttribute = true;
    protected TransformStateSetter m_state = null;

    public ToSAXHandler(ContentHandler contentHandler, LexicalHandler lexicalHandler, String str) {
        setContentHandler(contentHandler);
        setLexHandler(lexicalHandler);
        setEncoding(str);
    }

    private void resetToSAXHandler() {
        this.m_lexHandler = null;
        this.m_saxHandler = null;
        this.m_state = null;
        this.m_shouldGenerateNSAttribute = false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addUniqueAttribute(String str, String str2, int i) throws SAXException {
        addAttribute(str, str2);
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

    public void closeCDATA() throws SAXException {
    }

    public void closeStartTag() throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedLexicalHandler
    public void comment(String str) throws SAXException {
        flushPending();
        if (this.m_lexHandler != null) {
            int length = str.length();
            if (length > this.m_charsBuff.length) {
                this.m_charsBuff = new char[(length * 2) + 1];
            }
            str.getChars(0, length, this.m_charsBuff, 0);
            this.m_lexHandler.comment(this.m_charsBuff, 0, length);
            if (this.m_tracer != null) {
                super.fireCommentEvent(this.m_charsBuff, 0, length);
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        super.error(sAXParseException);
        ContentHandler contentHandler = this.m_saxHandler;
        if (contentHandler instanceof ErrorHandler) {
            ((ErrorHandler) contentHandler).error(sAXParseException);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        super.fatalError(sAXParseException);
        this.m_needToCallStartDocument = false;
        ContentHandler contentHandler = this.m_saxHandler;
        if (contentHandler instanceof ErrorHandler) {
            ((ErrorHandler) contentHandler).fatalError(sAXParseException);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void flushPending() throws SAXException {
        if (this.m_needToCallStartDocument) {
            startDocumentInternal();
            this.m_needToCallStartDocument = false;
        }
        if (this.m_elemContext.m_startTagOpen) {
            closeStartTag();
            this.m_elemContext.m_startTagOpen = false;
        }
        if (this.m_cdataTagOpen) {
            closeCDATA();
            this.m_cdataTagOpen = false;
        }
    }

    public boolean getShouldOutputNSAttr() {
        return this.m_shouldGenerateNSAttribute;
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public boolean reset() {
        if (!super.reset()) {
            return false;
        }
        resetToSAXHandler();
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setCdataSectionElements(List<String> list) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setContentHandler(ContentHandler contentHandler) {
        this.m_saxHandler = contentHandler;
        if (this.m_lexHandler == null && (contentHandler instanceof LexicalHandler)) {
            this.m_lexHandler = (LexicalHandler) contentHandler;
        }
    }

    public void setLexHandler(LexicalHandler lexicalHandler) {
        this.m_lexHandler = lexicalHandler;
    }

    public void setShouldOutputNSAttr(boolean z) {
        this.m_shouldGenerateNSAttribute = z;
    }

    public void setTransformState(TransformStateSetter transformStateSetter) {
        this.m_state = transformStateSetter;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase
    public void startDocumentInternal() throws SAXException {
        if (this.m_needToCallStartDocument) {
            super.startDocumentInternal();
            this.m_saxHandler.startDocument();
            this.m_needToCallStartDocument = false;
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        TransformStateSetter transformStateSetter = this.m_state;
        if (transformStateSetter != null) {
            transformStateSetter.resetState(getTransformer());
        }
        if (this.m_tracer != null) {
            super.fireStartElem(str3);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
        super.warning(sAXParseException);
        ContentHandler contentHandler = this.m_saxHandler;
        if (contentHandler instanceof ErrorHandler) {
            ((ErrorHandler) contentHandler).warning(sAXParseException);
        }
    }

    public ToSAXHandler() {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str, String str2, String str3) throws SAXException {
        TransformStateSetter transformStateSetter = this.m_state;
        if (transformStateSetter != null) {
            transformStateSetter.resetState(getTransformer());
        }
        if (this.m_tracer != null) {
            super.fireStartElem(str3);
        }
    }

    public ToSAXHandler(ContentHandler contentHandler, String str) {
        setContentHandler(contentHandler);
        setEncoding(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str) throws SAXException {
        TransformStateSetter transformStateSetter = this.m_state;
        if (transformStateSetter != null) {
            transformStateSetter.resetState(getTransformer());
        }
        if (this.m_tracer != null) {
            super.fireStartElem(str);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(Node node) throws SAXException {
        TransformStateSetter transformStateSetter = this.m_state;
        if (transformStateSetter != null) {
            transformStateSetter.setCurrentNode(node);
        }
        String nodeValue = node.getNodeValue();
        if (nodeValue != null) {
            characters(nodeValue);
        }
    }
}
