package javax.xml.parsers;

import com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl;
import javax.xml.validation.Schema;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SAXParserFactory {
    private static final String DEFAULT_IMPL = "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl";
    private boolean validating = false;
    private boolean namespaceAware = false;

    private static SAXParserFactory makeNSAware(SAXParserFactory sAXParserFactory) {
        sAXParserFactory.setNamespaceAware(true);
        return sAXParserFactory;
    }

    public static SAXParserFactory newDefaultInstance() {
        return new SAXParserFactoryImpl();
    }

    public static SAXParserFactory newDefaultNSInstance() {
        return makeNSAware(new SAXParserFactoryImpl());
    }

    public static SAXParserFactory newInstance() {
        return (SAXParserFactory) FactoryFinder.find(SAXParserFactory.class, DEFAULT_IMPL);
    }

    public static SAXParserFactory newNSInstance() {
        return makeNSAware((SAXParserFactory) FactoryFinder.find(SAXParserFactory.class, DEFAULT_IMPL));
    }

    public abstract boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException, ParserConfigurationException;

    public Schema getSchema() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }

    public boolean isValidating() {
        return this.validating;
    }

    public boolean isXIncludeAware() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public abstract SAXParser newSAXParser() throws ParserConfigurationException, SAXException;

    public abstract void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException, ParserConfigurationException;

    public void setNamespaceAware(boolean z) {
        this.namespaceAware = z;
    }

    public void setSchema(Schema schema) {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public void setValidating(boolean z) {
        this.validating = z;
    }

    public void setXIncludeAware(boolean z) {
        if (z) {
            o1c.a(" setXIncludeAware is not supported on this JAXP implementation or earlier: ", getClass());
        }
    }

    public static SAXParserFactory newInstance(String str, ClassLoader classLoader) {
        return (SAXParserFactory) FactoryFinder.newInstance(SAXParserFactory.class, str, classLoader, false);
    }

    public static SAXParserFactory newNSInstance(String str, ClassLoader classLoader) {
        return makeNSAware((SAXParserFactory) FactoryFinder.newInstance(SAXParserFactory.class, str, classLoader, false));
    }
}
