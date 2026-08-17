package javax.xml.xpath;

import com.sun.org.apache.xpath.internal.jaxp.XPathFactoryImpl;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class XPathFactory {
    public static final String DEFAULT_OBJECT_MODEL_URI = "http://java.sun.com/jaxp/xpath/dom";
    public static final String DEFAULT_PROPERTY_NAME = "javax.xml.xpath.XPathFactory";

    public static XPathFactory newDefaultInstance() {
        return new XPathFactoryImpl();
    }

    public static XPathFactory newInstance(String str, String str2, ClassLoader classLoader) throws XPathFactoryConfigurationException {
        if (str == null) {
            x0e.a("XPathFactory#newInstance(String uri) cannot be called with uri == null");
            return null;
        }
        if (str.length() == 0) {
            w01.a("XPathFactory#newInstance(String uri) cannot be called with uri == \"\"");
            return null;
        }
        if (classLoader == null) {
            classLoader = SecuritySupport.getContextClassLoader();
        }
        XPathFactory xPathFactoryCreateInstance = new XPathFactoryFinder(classLoader).createInstance(str2);
        if (xPathFactoryCreateInstance == null) {
            throw new XPathFactoryConfigurationException("No XPathFactory implementation found for the object model: ".concat(str));
        }
        if (xPathFactoryCreateInstance.isObjectModelSupported(str)) {
            return xPathFactoryCreateInstance;
        }
        throw new XPathFactoryConfigurationException("Factory " + str2 + " doesn't support given " + str + " object model");
    }

    public abstract boolean getFeature(String str) throws XPathFactoryConfigurationException;

    public abstract boolean isObjectModelSupported(String str);

    public abstract XPath newXPath();

    public abstract void setFeature(String str, boolean z) throws XPathFactoryConfigurationException;

    public abstract void setXPathFunctionResolver(XPathFunctionResolver xPathFunctionResolver);

    public abstract void setXPathVariableResolver(XPathVariableResolver xPathVariableResolver);

    public static XPathFactory newInstance(String str) throws XPathFactoryConfigurationException {
        if (str != null) {
            if (str.length() != 0) {
                ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
                if (contextClassLoader == null) {
                    contextClassLoader = XPathFactory.class.getClassLoader();
                }
                XPathFactory xPathFactoryNewFactory = new XPathFactoryFinder(contextClassLoader).newFactory(str);
                if (xPathFactoryNewFactory != null) {
                    return xPathFactoryNewFactory;
                }
                throw new XPathFactoryConfigurationException("No XPathFactory implementation found for the object model: ".concat(str));
            }
            w01.a("XPathFactory#newInstance(String uri) cannot be called with uri == \"\"");
            return null;
        }
        x0e.a("XPathFactory#newInstance(String uri) cannot be called with uri == null");
        return null;
    }

    public static XPathFactory newInstance() {
        try {
            return newInstance("http://java.sun.com/jaxp/xpath/dom");
        } catch (XPathFactoryConfigurationException e) {
            eyf.a("XPathFactory#newInstance() failed to create an XPathFactory for the default object model: http://java.sun.com/jaxp/xpath/dom with the XPathFactoryConfigurationException: ", e.getMessage(), e);
            return null;
        }
    }
}
