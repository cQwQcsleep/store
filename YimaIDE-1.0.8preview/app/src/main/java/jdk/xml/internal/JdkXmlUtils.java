package jdk.xml.internal;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl;
import com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JdkXmlUtils {
    private static final String DOM_FACTORY_ID = "javax.xml.parsers.DocumentBuilderFactory";
    public static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    public static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    private static final String SAX_DRIVER = "org.xml.sax.driver";
    private static final String SAX_FACTORY_ID = "javax.xml.parsers.SAXParserFactory";
    public static final String USE_CATALOG = "http://javax.xml.XMLConstants/feature/useCatalog";
    public static final String CATALOG_FILES = CatalogFeatures.Feature.FILES.getPropertyName();
    public static final String CATALOG_DEFER = CatalogFeatures.Feature.DEFER.getPropertyName();
    public static final String CATALOG_PREFER = CatalogFeatures.Feature.PREFER.getPropertyName();
    public static final String CATALOG_RESOLVE = CatalogFeatures.Feature.RESOLVE.getPropertyName();
    public static final String SP_USE_CATALOG = "javax.xml.useCatalog";
    public static final boolean USE_CATALOG_DEFAULT = ((Boolean) SecuritySupport.getJAXPSystemProperty(Boolean.class, SP_USE_CATALOG, "true")).booleanValue();
    private static final SAXParserFactory defaultSAXFactory = getSAXFactory(false);

    public static void catalogFeaturesConfig2Config(XMLComponentManager xMLComponentManager, ParserConfigurationSettings parserConfigurationSettings) {
        boolean feature = xMLComponentManager.getFeature("http://javax.xml.XMLConstants/feature/useCatalog");
        try {
            parserConfigurationSettings.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", feature);
            if (feature) {
                for (CatalogFeatures.Feature feature2 : CatalogFeatures.Feature.values()) {
                    parserConfigurationSettings.setProperty(feature2.getPropertyName(), xMLComponentManager.getProperty(feature2.getPropertyName()));
                }
            }
        } catch (XMLConfigurationException unused) {
        }
    }

    public static void catalogFeaturesConfig2Reader(XMLComponentManager xMLComponentManager, XMLReader xMLReader) {
        boolean feature = xMLComponentManager.getFeature("http://javax.xml.XMLConstants/feature/useCatalog");
        try {
            xMLReader.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", feature);
            if (feature) {
                for (CatalogFeatures.Feature feature2 : CatalogFeatures.Feature.values()) {
                    xMLReader.setProperty(feature2.getPropertyName(), xMLComponentManager.getProperty(feature2.getPropertyName()));
                }
            }
        } catch (SAXNotRecognizedException | SAXNotSupportedException unused) {
        }
    }

    public static String getCatalogFeature(CatalogFeatures catalogFeatures, String str) {
        for (CatalogFeatures.Feature feature : CatalogFeatures.Feature.values()) {
            if (feature.getPropertyName().equals(str)) {
                return catalogFeatures.get(feature);
            }
        }
        return null;
    }

    public static CatalogFeatures getCatalogFeatures(String str, String str2, String str3, String str4) {
        CatalogFeatures.Builder builder = CatalogFeatures.builder();
        if (str2 != null) {
            builder = builder.with(CatalogFeatures.Feature.FILES, str2);
        }
        if (str3 != null) {
            builder = builder.with(CatalogFeatures.Feature.PREFER, str3);
        }
        if (str != null) {
            builder = builder.with(CatalogFeatures.Feature.DEFER, str);
        }
        if (str4 != null) {
            builder = builder.with(CatalogFeatures.Feature.RESOLVE, str4);
        }
        return builder.build();
    }

    public static Document getDOMDocument() {
        try {
            return getDOMFactory(false).newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException unused) {
            return null;
        }
    }

    public static DocumentBuilderFactory getDOMFactory(boolean z) {
        if (SecuritySupport.getJAXPSystemProperty(DOM_FACTORY_ID) != null && System.getSecurityManager() == null) {
            z = true;
        }
        DocumentBuilderFactory documentBuilderFactoryImpl = !z ? new DocumentBuilderFactoryImpl() : DocumentBuilderFactory.newInstance();
        documentBuilderFactoryImpl.setNamespaceAware(true);
        documentBuilderFactoryImpl.setValidating(false);
        return documentBuilderFactoryImpl;
    }

    public static String getDTDExternalDecl(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(" PUBLIC ");
            sb.append(quoteString(str));
        }
        if (str2 != null) {
            if (str == null) {
                sb.append(" SYSTEM ");
            } else {
                sb.append(" ");
            }
            sb.append(quoteString(str2));
        }
        return sb.toString();
    }

    public static SAXParserFactory getSAXFactory(boolean z) {
        if (SecuritySupport.getJAXPSystemProperty(SAX_FACTORY_ID) != null && System.getSecurityManager() == null) {
            z = true;
        }
        SAXParserFactory sAXParserFactoryImpl = !z ? new SAXParserFactoryImpl() : SAXParserFactory.newInstance();
        sAXParserFactoryImpl.setNamespaceAware(true);
        return sAXParserFactoryImpl;
    }

    public static SAXTransformerFactory getSAXTransformFactory(boolean z) {
        SAXTransformerFactory transformerFactoryImpl = z ? (SAXTransformerFactory) TransformerFactory.newInstance() : new TransformerFactoryImpl();
        try {
            transformerFactoryImpl.setFeature(JdkConstants.OVERRIDE_PARSER, z);
        } catch (TransformerConfigurationException unused) {
        }
        return transformerFactoryImpl;
    }

    public static int getValue(Object obj, int i) {
        if (obj == null) {
            return i;
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            return Integer.parseInt(String.valueOf(obj));
        }
        z01.a("Unexpected class: ", obj.getClass());
        return 0;
    }

    public static XMLReader getXMLReader(boolean z, boolean z2) {
        XMLReader xMLReaderWSAXFactory;
        if (SecuritySupport.getSystemProperty(SAX_DRIVER) != null) {
            xMLReaderWSAXFactory = getXMLReaderWXMLReaderFactory();
        } else {
            xMLReaderWSAXFactory = z ? getXMLReaderWSAXFactory(z) : null;
        }
        if (xMLReaderWSAXFactory == null) {
            try {
                return defaultSAXFactory.newSAXParser().getXMLReader();
            } catch (ParserConfigurationException | SAXException unused) {
                return xMLReaderWSAXFactory;
            }
        }
        if (z2) {
            try {
                xMLReaderWSAXFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", z2);
            } catch (SAXException e) {
                XMLSecurityManager.printWarning(xMLReaderWSAXFactory.getClass().getName(), "http://javax.xml.XMLConstants/feature/secure-processing", e);
            }
        }
        try {
            xMLReaderWSAXFactory.setFeature("http://xml.org/sax/features/namespaces", true);
            xMLReaderWSAXFactory.setFeature(NAMESPACE_PREFIXES_FEATURE, false);
        } catch (SAXException unused2) {
        }
        return xMLReaderWSAXFactory;
    }

    private static XMLReader getXMLReaderWSAXFactory(boolean z) {
        try {
            return getSAXFactory(z).newSAXParser().getXMLReader();
        } catch (ParserConfigurationException | SAXException unused) {
            return getXMLReaderWXMLReaderFactory();
        }
    }

    private static XMLReader getXMLReaderWXMLReaderFactory() {
        try {
            return XMLReaderFactory.createXMLReader();
        } catch (SAXException unused) {
            return null;
        }
    }

    private static String quoteString(String str) {
        char c = str.indexOf(34) > -1 ? '\'' : '\"';
        return c + str + c;
    }

    public static void setXMLReaderPropertyIfSupport(XMLReader xMLReader, String str, Object obj, boolean z) {
        try {
            xMLReader.setProperty(str, obj);
        } catch (SAXNotRecognizedException | SAXNotSupportedException e) {
            if (z) {
                XMLSecurityManager.printWarning(xMLReader.getClass().getName(), str, e);
            }
        }
    }
}
