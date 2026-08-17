package javax.xml.parsers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.validation.Schema;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DocumentBuilder {
    public abstract DOMImplementation getDOMImplementation();

    public Schema getSchema() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public abstract boolean isNamespaceAware();

    public abstract boolean isValidating();

    public boolean isXIncludeAware() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public abstract Document newDocument();

    public Document parse(File file) throws SAXException, IOException {
        if (file != null) {
            return parse(new InputSource(file.toURI().toASCIIString()));
        }
        w01.a("File cannot be null");
        return null;
    }

    public abstract Document parse(InputSource inputSource) throws SAXException, IOException;

    public void reset() {
        throw new UnsupportedOperationException("This DocumentBuilder, \"" + getClass().getName() + "\", does not support the reset functionality.  Specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public abstract void setEntityResolver(EntityResolver entityResolver);

    public abstract void setErrorHandler(ErrorHandler errorHandler);

    public Document parse(InputStream inputStream) throws SAXException, IOException {
        if (inputStream != null) {
            return parse(new InputSource(inputStream));
        }
        w01.a("InputStream cannot be null");
        return null;
    }

    public Document parse(InputStream inputStream, String str) throws SAXException, IOException {
        if (inputStream != null) {
            InputSource inputSource = new InputSource(inputStream);
            inputSource.setSystemId(str);
            return parse(inputSource);
        }
        w01.a("InputStream cannot be null");
        return null;
    }

    public Document parse(String str) throws SAXException, IOException {
        if (str != null) {
            return parse(new InputSource(str));
        }
        w01.a("URI cannot be null");
        return null;
    }
}
