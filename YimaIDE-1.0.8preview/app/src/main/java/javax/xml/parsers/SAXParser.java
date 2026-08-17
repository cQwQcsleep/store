package javax.xml.parsers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.validation.Schema;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SAXParser {
    public abstract Parser getParser() throws SAXException;

    public abstract Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException;

    public Schema getSchema() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public abstract XMLReader getXMLReader() throws SAXException;

    public abstract boolean isNamespaceAware();

    public abstract boolean isValidating();

    public boolean isXIncludeAware() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public void parse(InputSource inputSource, HandlerBase handlerBase) throws SAXException, IOException {
        if (inputSource == null) {
            w01.a("InputSource cannot be null");
            return;
        }
        Parser parser = getParser();
        if (handlerBase != null) {
            parser.setDocumentHandler(handlerBase);
            parser.setEntityResolver(handlerBase);
            parser.setErrorHandler(handlerBase);
            parser.setDTDHandler(handlerBase);
        }
        parser.parse(inputSource);
    }

    public void reset() {
        throw new UnsupportedOperationException("This SAXParser, \"" + getClass().getName() + "\", does not support the reset functionality.  Specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public abstract void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException;

    public void parse(InputStream inputStream, HandlerBase handlerBase, String str) throws SAXException, IOException {
        if (inputStream != null) {
            InputSource inputSource = new InputSource(inputStream);
            inputSource.setSystemId(str);
            parse(inputSource, handlerBase);
            return;
        }
        w01.a("InputStream cannot be null");
    }

    public void parse(InputStream inputStream, DefaultHandler defaultHandler) throws SAXException, IOException {
        if (inputStream != null) {
            parse(new InputSource(inputStream), defaultHandler);
        } else {
            w01.a("InputStream cannot be null");
        }
    }

    public void parse(InputStream inputStream, DefaultHandler defaultHandler, String str) throws SAXException, IOException {
        if (inputStream != null) {
            InputSource inputSource = new InputSource(inputStream);
            inputSource.setSystemId(str);
            parse(inputSource, defaultHandler);
            return;
        }
        w01.a("InputStream cannot be null");
    }

    public void parse(String str, HandlerBase handlerBase) throws SAXException, IOException {
        if (str != null) {
            parse(new InputSource(str), handlerBase);
        } else {
            w01.a("uri cannot be null");
        }
    }

    public void parse(String str, DefaultHandler defaultHandler) throws SAXException, IOException {
        if (str != null) {
            parse(new InputSource(str), defaultHandler);
        } else {
            w01.a("uri cannot be null");
        }
    }

    public void parse(File file, HandlerBase handlerBase) throws SAXException, IOException {
        if (file != null) {
            parse(new InputSource(file.toURI().toASCIIString()), handlerBase);
        } else {
            w01.a("File cannot be null");
        }
    }

    public void parse(File file, DefaultHandler defaultHandler) throws SAXException, IOException {
        if (file != null) {
            parse(new InputSource(file.toURI().toASCIIString()), defaultHandler);
        } else {
            w01.a("File cannot be null");
        }
    }

    public void parse(InputStream inputStream, HandlerBase handlerBase) throws SAXException, IOException {
        if (inputStream != null) {
            parse(new InputSource(inputStream), handlerBase);
        } else {
            w01.a("InputStream cannot be null");
        }
    }

    public void parse(InputSource inputSource, DefaultHandler defaultHandler) throws SAXException, IOException {
        if (inputSource != null) {
            XMLReader xMLReader = getXMLReader();
            if (defaultHandler != null) {
                xMLReader.setContentHandler(defaultHandler);
                xMLReader.setEntityResolver(defaultHandler);
                xMLReader.setErrorHandler(defaultHandler);
                xMLReader.setDTDHandler(defaultHandler);
            }
            xMLReader.parse(inputSource);
            return;
        }
        w01.a("InputSource cannot be null");
    }
}
