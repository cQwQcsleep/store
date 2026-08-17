package javax.xml.parsers;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import javax.xml.validation.Schema;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DocumentBuilderFactory {
    private static final String DEFAULT_IMPL = "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl";
    private boolean validating = false;
    private boolean namespaceAware = false;
    private boolean whitespace = false;
    private boolean expandEntityRef = true;
    private boolean ignoreComments = false;
    private boolean coalescing = false;

    private static DocumentBuilderFactory makeNSAware(DocumentBuilderFactory documentBuilderFactory) {
        documentBuilderFactory.setNamespaceAware(true);
        return documentBuilderFactory;
    }

    public static DocumentBuilderFactory newDefaultInstance() {
        return new DocumentBuilderFactoryImpl();
    }

    public static DocumentBuilderFactory newDefaultNSInstance() {
        return makeNSAware(new DocumentBuilderFactoryImpl());
    }

    public static DocumentBuilderFactory newInstance() {
        return (DocumentBuilderFactory) FactoryFinder.find(DocumentBuilderFactory.class, DEFAULT_IMPL);
    }

    public static DocumentBuilderFactory newNSInstance() {
        return makeNSAware((DocumentBuilderFactory) FactoryFinder.find(DocumentBuilderFactory.class, DEFAULT_IMPL));
    }

    public abstract Object getAttribute(String str) throws IllegalArgumentException;

    public abstract boolean getFeature(String str) throws ParserConfigurationException;

    public Schema getSchema() {
        throw new UnsupportedOperationException("This parser does not support specification \"" + getClass().getPackage().getSpecificationTitle() + "\" version \"" + getClass().getPackage().getSpecificationVersion() + "\"");
    }

    public boolean isCoalescing() {
        return this.coalescing;
    }

    public boolean isExpandEntityReferences() {
        return this.expandEntityRef;
    }

    public boolean isIgnoringComments() {
        return this.ignoreComments;
    }

    public boolean isIgnoringElementContentWhitespace() {
        return this.whitespace;
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

    public abstract DocumentBuilder newDocumentBuilder() throws ParserConfigurationException;

    public abstract void setAttribute(String str, Object obj) throws IllegalArgumentException;

    public void setCoalescing(boolean z) {
        this.coalescing = z;
    }

    public void setExpandEntityReferences(boolean z) {
        this.expandEntityRef = z;
    }

    public abstract void setFeature(String str, boolean z) throws ParserConfigurationException;

    public void setIgnoringComments(boolean z) {
        this.ignoreComments = z;
    }

    public void setIgnoringElementContentWhitespace(boolean z) {
        this.whitespace = z;
    }

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

    public static DocumentBuilderFactory newInstance(String str, ClassLoader classLoader) {
        return (DocumentBuilderFactory) FactoryFinder.newInstance(DocumentBuilderFactory.class, str, classLoader, false);
    }

    public static DocumentBuilderFactory newNSInstance(String str, ClassLoader classLoader) {
        return makeNSAware((DocumentBuilderFactory) FactoryFinder.newInstance(DocumentBuilderFactory.class, str, classLoader, false));
    }
}
