package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xml.internal.utils.XMLReaderManager;
import java.io.IOException;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXResult;
import jdk.xml.internal.JdkXmlUtils;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TrAXFilter extends XMLFilterImpl {
    private boolean _overrideDefaultParser;
    private Templates _templates;
    private TransformerImpl _transformer;
    private TransformerHandlerImpl _transformerHandler;

    public TrAXFilter(Templates templates) throws TransformerConfigurationException {
        this._templates = templates;
        TransformerImpl transformerImpl = (TransformerImpl) templates.newTransformer();
        this._transformer = transformerImpl;
        this._transformerHandler = new TransformerHandlerImpl(transformerImpl);
        this._overrideDefaultParser = this._transformer.overrideDefaultParser();
    }

    private void createParent() throws SAXException {
        setParent(JdkXmlUtils.getXMLReader(this._overrideDefaultParser, this._transformer.isSecureProcessing()));
    }

    public Transformer getTransformer() {
        return this._transformer;
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
    public void parse(InputSource inputSource) throws SAXException, IOException {
        XMLReader xMLReader = null;
        try {
            if (getParent() == null) {
                try {
                    xMLReader = XMLReaderManager.getInstance(this._overrideDefaultParser).getXMLReader();
                    setParent(xMLReader);
                } catch (SAXException e) {
                    throw new SAXException(e.toString());
                }
            }
            getParent().parse(inputSource);
            if (xMLReader != null) {
                XMLReaderManager.getInstance(this._overrideDefaultParser).releaseXMLReader(xMLReader);
            }
        } catch (Throwable th) {
            if (xMLReader != null) {
                XMLReaderManager.getInstance(this._overrideDefaultParser).releaseXMLReader(xMLReader);
            }
            throw th;
        }
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
    public void setContentHandler(ContentHandler contentHandler) {
        this._transformerHandler.setResult(new SAXResult(contentHandler));
        if (getParent() == null) {
            try {
                createParent();
            } catch (SAXException unused) {
                return;
            }
        }
        getParent().setContentHandler(this._transformerHandler);
    }

    public void setErrorListener(ErrorListener errorListener) {
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.XMLReader
    public void parse(String str) throws SAXException, IOException {
        parse(new InputSource(str));
    }
}
