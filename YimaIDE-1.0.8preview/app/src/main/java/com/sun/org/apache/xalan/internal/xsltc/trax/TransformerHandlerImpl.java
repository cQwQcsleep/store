package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMBuilder;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMWSFilter;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import com.sun.org.apache.xalan.internal.xsltc.dom.XSLTCDTMManager;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import defpackage.bke;
import defpackage.x73;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.TransformerHandler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TransformerHandlerImpl implements TransformerHandler, DeclHandler {
    private ContentHandler _handler;
    private boolean _isIdentity;
    private String _systemId;
    private TransformerImpl _transformer;
    private AbstractTranslet _translet;
    private SAXImpl _dom = null;
    private LexicalHandler _lexHandler = null;
    private DTDHandler _dtdHandler = null;
    private DeclHandler _declHandler = null;
    private Result _result = null;
    private Locator _locator = null;
    private boolean _done = false;

    public TransformerHandlerImpl(TransformerImpl transformerImpl) {
        this._translet = null;
        this._handler = null;
        this._isIdentity = false;
        this._transformer = transformerImpl;
        if (!transformerImpl.isIdentity()) {
            this._translet = this._transformer.getTranslet();
        } else {
            this._handler = new DefaultHandler();
            this._isIdentity = true;
        }
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
        DeclHandler declHandler = this._declHandler;
        if (declHandler != null) {
            declHandler.attributeDecl(str, str2, str3, str4, str5);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        this._handler.characters(cArr, i, i2);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        LexicalHandler lexicalHandler = this._lexHandler;
        if (lexicalHandler != null) {
            lexicalHandler.comment(cArr, i, i2);
        }
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void elementDecl(String str, String str2) throws SAXException {
        DeclHandler declHandler = this._declHandler;
        if (declHandler != null) {
            declHandler.elementDecl(str, str2);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        LexicalHandler lexicalHandler = this._lexHandler;
        if (lexicalHandler != null) {
            lexicalHandler.endCDATA();
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
        LexicalHandler lexicalHandler = this._lexHandler;
        if (lexicalHandler != null) {
            lexicalHandler.endDTD();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        this._handler.endDocument();
        if (!this._isIdentity) {
            if (this._result != null) {
                try {
                    this._transformer.setDOM(this._dom);
                    this._transformer.transform(null, this._result);
                } catch (TransformerException e) {
                    x73.a(e);
                    return;
                }
            }
            this._done = true;
            this._transformer.setDOM(this._dom);
        }
        if (this._isIdentity) {
            Result result = this._result;
            if (result instanceof DOMResult) {
                ((DOMResult) result).setNode(this._transformer.getTransletOutputHandlerFactory().getNode());
            }
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        this._handler.endElement(str, str2, str3);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
        LexicalHandler lexicalHandler = this._lexHandler;
        if (lexicalHandler != null) {
            lexicalHandler.endEntity(str);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
        this._handler.endPrefixMapping(str);
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
        DeclHandler declHandler = this._declHandler;
        if (declHandler != null) {
            declHandler.externalEntityDecl(str, str2, str3);
        }
    }

    @Override // javax.xml.transform.sax.TransformerHandler
    public String getSystemId() {
        return this._systemId;
    }

    @Override // javax.xml.transform.sax.TransformerHandler
    public Transformer getTransformer() {
        return this._transformer;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        this._handler.ignorableWhitespace(cArr, i, i2);
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void internalEntityDecl(String str, String str2) throws SAXException {
        DeclHandler declHandler = this._declHandler;
        if (declHandler != null) {
            declHandler.internalEntityDecl(str, str2);
        }
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String str, String str2, String str3) throws SAXException {
        DTDHandler dTDHandler = this._dtdHandler;
        if (dTDHandler != null) {
            dTDHandler.notationDecl(str, str2, str3);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        this._handler.processingInstruction(str, str2);
    }

    public void reset() {
        this._systemId = null;
        this._dom = null;
        this._handler = null;
        this._lexHandler = null;
        this._dtdHandler = null;
        this._declHandler = null;
        this._result = null;
        this._locator = null;
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this._locator = locator;
        ContentHandler contentHandler = this._handler;
        if (contentHandler != null) {
            contentHandler.setDocumentLocator(locator);
        }
    }

    @Override // javax.xml.transform.sax.TransformerHandler
    public void setResult(Result result) throws IllegalArgumentException {
        this._result = result;
        if (result == null) {
            bke.a(new ErrorMsg("ER_RESULT_NULL"));
            return;
        }
        if (this._isIdentity) {
            try {
                SerializationHandler outputHandler = this._transformer.getOutputHandler(result);
                this._transformer.transferOutputProperties(outputHandler);
                this._handler = outputHandler;
                this._lexHandler = outputHandler;
                return;
            } catch (TransformerException unused) {
                this._result = null;
                return;
            }
        }
        if (this._done) {
            try {
                this._transformer.setDOM(this._dom);
                this._transformer.transform(null, this._result);
            } catch (TransformerException e) {
                w01.a(e.getMessage());
            }
        }
    }

    @Override // javax.xml.transform.sax.TransformerHandler
    public void setSystemId(String str) {
        this._systemId = str;
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
        this._handler.skippedEntity(str);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        LexicalHandler lexicalHandler = this._lexHandler;
        if (lexicalHandler != null) {
            lexicalHandler.startCDATA();
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
        LexicalHandler lexicalHandler = this._lexHandler;
        if (lexicalHandler != null) {
            lexicalHandler.startDTD(str, str2, str3);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        if (this._result == null) {
            throw new SAXException(new ErrorMsg(ErrorMsg.JAXP_SET_RESULT_ERR).toString());
        }
        if (!this._isIdentity) {
            AbstractTranslet abstractTranslet = this._translet;
            boolean zHasIdCall = abstractTranslet != null ? abstractTranslet.hasIdCall() : false;
            try {
                XSLTCDTMManager xSLTCDTMManagerCreateNewDTMManagerInstance = this._transformer.getTransformerFactory().createNewDTMManagerInstance();
                AbstractTranslet abstractTranslet2 = this._translet;
                SAXImpl sAXImpl = (SAXImpl) xSLTCDTMManagerCreateNewDTMManagerInstance.getDTM(null, false, (abstractTranslet2 == null || !(abstractTranslet2 instanceof StripFilter)) ? null : new DOMWSFilter(abstractTranslet2), true, false, zHasIdCall);
                this._dom = sAXImpl;
                DOMBuilder builder = sAXImpl.getBuilder();
                this._handler = builder;
                this._lexHandler = builder;
                this._dtdHandler = builder;
                this._declHandler = builder;
                this._dom.setDocumentURI(this._systemId);
                Locator locator = this._locator;
                if (locator != null) {
                    this._handler.setDocumentLocator(locator);
                }
            } catch (Exception e) {
                x73.a(e);
                return;
            }
        }
        this._handler.startDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        this._handler.startElement(str, str2, str3, attributes);
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
        LexicalHandler lexicalHandler = this._lexHandler;
        if (lexicalHandler != null) {
            lexicalHandler.startEntity(str);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        this._handler.startPrefixMapping(str, str2);
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException {
        DTDHandler dTDHandler = this._dtdHandler;
        if (dTDHandler != null) {
            dTDHandler.unparsedEntityDecl(str, str2, str3, str4);
        }
    }
}
