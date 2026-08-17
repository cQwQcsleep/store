package javax.xml.transform;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class TransformerFactory {
    public static TransformerFactory newDefaultInstance() {
        return new TransformerFactoryImpl();
    }

    public static TransformerFactory newInstance() throws TransformerFactoryConfigurationError {
        return (TransformerFactory) FactoryFinder.find(TransformerFactory.class, "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
    }

    public abstract Source getAssociatedStylesheet(Source source, String str, String str2, String str3) throws TransformerConfigurationException;

    public abstract Object getAttribute(String str);

    public abstract ErrorListener getErrorListener();

    public abstract boolean getFeature(String str);

    public abstract URIResolver getURIResolver();

    public abstract Templates newTemplates(Source source) throws TransformerConfigurationException;

    public abstract Transformer newTransformer() throws TransformerConfigurationException;

    public abstract Transformer newTransformer(Source source) throws TransformerConfigurationException;

    public abstract void setAttribute(String str, Object obj);

    public abstract void setErrorListener(ErrorListener errorListener);

    public abstract void setFeature(String str, boolean z) throws TransformerConfigurationException;

    public abstract void setURIResolver(URIResolver uRIResolver);

    public static TransformerFactory newInstance(String str, ClassLoader classLoader) throws TransformerFactoryConfigurationError {
        return (TransformerFactory) FactoryFinder.newInstance(TransformerFactory.class, str, classLoader, false);
    }
}
