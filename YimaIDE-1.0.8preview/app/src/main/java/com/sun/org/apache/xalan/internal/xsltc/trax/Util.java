package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.compiler.XSLTC;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.io.InputStream;
import java.io.Reader;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.XMLSecurityManager;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Util {
    private static final String property = "org.xml.sax.driver";

    public static String baseName(String str) {
        return com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.baseName(str);
    }

    public static InputSource getInputSource(XSLTC xsltc, Source source) throws TransformerConfigurationException {
        InputSource inputSource;
        InputSource inputSource2;
        String systemId = source.getSystemId();
        try {
            if (source instanceof SAXSource) {
                SAXSource sAXSource = (SAXSource) source;
                inputSource2 = sAXSource.getInputSource();
                try {
                    try {
                        XMLReader xMLReader = sAXSource.getXMLReader();
                        boolean z = true;
                        if (xMLReader == null) {
                            xMLReader = JdkXmlUtils.getXMLReader(xsltc.getFeature(JdkXmlFeatures.XmlFeature.JDK_OVERRIDE_PARSER), xsltc.isSecureProcessing());
                        } else {
                            xMLReader.setFeature("http://xml.org/sax/features/namespaces", true);
                            xMLReader.setFeature(JdkXmlUtils.NAMESPACE_PREFIXES_FEATURE, false);
                        }
                        JdkXmlUtils.setXMLReaderPropertyIfSupport(xMLReader, "http://javax.xml.XMLConstants/property/accessExternalDTD", xsltc.getProperty("http://javax.xml.XMLConstants/property/accessExternalDTD"), true);
                        JdkXmlUtils.setXMLReaderPropertyIfSupport(xMLReader, JdkConstants.CDATA_CHUNK_SIZE, xsltc.getProperty(JdkConstants.CDATA_CHUNK_SIZE), false);
                        String str = "";
                        try {
                            XMLSecurityManager xMLSecurityManager = (XMLSecurityManager) xsltc.getProperty("http://apache.org/xml/properties/security-manager");
                            if (xMLSecurityManager != null) {
                                for (XMLSecurityManager.Limit limit : XMLSecurityManager.Limit.values()) {
                                    if (limit.isSupported(XMLSecurityManager.Processor.PARSER)) {
                                        xMLReader.setProperty(limit.apiProperty(), xMLSecurityManager.getLimitValueAsString(limit));
                                    }
                                }
                                if (xMLSecurityManager.printEntityCountInfo()) {
                                    str = JdkConstants.JDK_DEBUG_LIMIT;
                                    xMLReader.setProperty(JdkConstants.JDK_DEBUG_LIMIT, JdkConstants.JDK_YES);
                                }
                            }
                        } catch (SAXException e) {
                            XMLSecurityManager.printWarning(xMLReader.getClass().getName(), str, e);
                        }
                        boolean feature = xsltc.getFeature(JdkXmlFeatures.XmlFeature.USE_CATALOG);
                        try {
                            xMLReader.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", feature);
                        } catch (SAXNotRecognizedException | SAXNotSupportedException unused) {
                            z = false;
                        }
                        if (feature & z) {
                            try {
                                CatalogFeatures catalogFeatures = (CatalogFeatures) xsltc.getProperty(JdkXmlFeatures.CATALOG_FEATURES);
                                if (catalogFeatures != null) {
                                    for (CatalogFeatures.Feature feature2 : CatalogFeatures.Feature.values()) {
                                        xMLReader.setProperty(feature2.getPropertyName(), catalogFeatures.get(feature2));
                                    }
                                }
                            } catch (SAXNotRecognizedException unused2) {
                            }
                        }
                        xsltc.setXMLReader(xMLReader);
                    } catch (SAXNotSupportedException e2) {
                        throw new TransformerConfigurationException("SAXNotSupportedException ", e2);
                    }
                } catch (SAXNotRecognizedException e3) {
                    throw new TransformerConfigurationException("SAXNotRecognizedException ", e3);
                }
            } else {
                if (source instanceof DOMSource) {
                    DOMSource dOMSource = (DOMSource) source;
                    xsltc.setXMLReader(new DOM2SAX((Document) dOMSource.getNode()));
                    inputSource = SAXSource.sourceToInputSource(source);
                    if (inputSource == null) {
                        inputSource = new InputSource(dOMSource.getSystemId());
                    }
                } else if (source instanceof StAXSource) {
                    StAXSource stAXSource = (StAXSource) source;
                    if (stAXSource.getXMLEventReader() != null) {
                        xsltc.setXMLReader(new StAXEvent2SAX(stAXSource.getXMLEventReader()));
                    } else if (stAXSource.getXMLStreamReader() != null) {
                        xsltc.setXMLReader(new StAXStream2SAX(stAXSource.getXMLStreamReader()));
                    }
                    inputSource = SAXSource.sourceToInputSource(source);
                    if (inputSource == null) {
                        inputSource = new InputSource(stAXSource.getSystemId());
                    }
                } else {
                    if (!(source instanceof StreamSource)) {
                        throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.JAXP_UNKNOWN_SOURCE_ERR).toString());
                    }
                    StreamSource streamSource = (StreamSource) source;
                    InputStream inputStream = streamSource.getInputStream();
                    Reader reader = streamSource.getReader();
                    xsltc.setXMLReader(null);
                    inputSource = inputStream != null ? new InputSource(inputStream) : reader != null ? new InputSource(reader) : new InputSource(systemId);
                }
                inputSource2 = inputSource;
            }
            inputSource2.setSystemId(systemId);
            return inputSource2;
        } catch (NullPointerException unused3) {
            throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.JAXP_NO_SOURCE_ERR, "TransformerFactory.newTemplates()").toString());
        } catch (SecurityException unused4) {
            throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.FILE_ACCESS_ERR, systemId).toString());
        }
    }

    public static String noExtName(String str) {
        return com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.noExtName(str);
    }

    public static String toJavaName(String str) {
        return com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.toJavaName(str);
    }
}
