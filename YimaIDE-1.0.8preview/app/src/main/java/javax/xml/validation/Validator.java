package javax.xml.validation;

import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Validator {
    public abstract ErrorHandler getErrorHandler();

    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str == null) {
            throw new NullPointerException("the name parameter is null");
        }
        throw new SAXNotRecognizedException(str);
    }

    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str == null) {
            throw new NullPointerException("the name parameter is null");
        }
        throw new SAXNotRecognizedException(str);
    }

    public abstract LSResourceResolver getResourceResolver();

    public abstract void reset();

    public abstract void setErrorHandler(ErrorHandler errorHandler);

    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str != null) {
            throw new SAXNotRecognizedException(str);
        }
        throw new NullPointerException("the name parameter is null");
    }

    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str != null) {
            throw new SAXNotRecognizedException(str);
        }
        throw new NullPointerException("the name parameter is null");
    }

    public abstract void setResourceResolver(LSResourceResolver lSResourceResolver);

    public void validate(Source source) throws SAXException, IOException {
        validate(source, null);
    }

    public abstract void validate(Source source, Result result) throws SAXException, IOException;
}
