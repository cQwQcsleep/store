package javax.xml.validation;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ValidatorHandler implements ContentHandler {
    public abstract ContentHandler getContentHandler();

    public abstract ErrorHandler getErrorHandler();

    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        str.getClass();
        throw new SAXNotRecognizedException(str);
    }

    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        str.getClass();
        throw new SAXNotRecognizedException(str);
    }

    public abstract LSResourceResolver getResourceResolver();

    public abstract TypeInfoProvider getTypeInfoProvider();

    public abstract void setContentHandler(ContentHandler contentHandler);

    public abstract void setErrorHandler(ErrorHandler errorHandler);

    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        str.getClass();
        throw new SAXNotRecognizedException(str);
    }

    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        str.getClass();
        throw new SAXNotRecognizedException(str);
    }

    public abstract void setResourceResolver(LSResourceResolver lSResourceResolver);
}
