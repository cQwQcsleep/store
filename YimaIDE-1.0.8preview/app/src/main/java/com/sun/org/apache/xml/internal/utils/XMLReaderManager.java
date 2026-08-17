package com.sun.org.apache.xml.internal.utils;

import java.util.HashMap;
import javax.xml.catalog.CatalogFeatures;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.SecuritySupport;
import jdk.xml.internal.XMLSecurityManager;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLReaderManager {
    private static final String LEXICAL_HANDLER_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
    private static final XMLReaderManager m_singletonManager = new XMLReaderManager();
    private static final String property = "org.xml.sax.driver";
    private String _accessExternalDTD = "all";
    private CatalogFeatures _catalogFeatures;
    private int _cdataChunkSize;
    private boolean _secureProcessing;
    private boolean _useCatalog;
    private XMLSecurityManager _xmlSecurityManager;
    private HashMap<XMLReader, Boolean> m_inUse;
    private boolean m_overrideDefaultParser;
    private ThreadLocal<ReaderWrapper> m_readers;

    public class ReaderWrapper {
        boolean overrideDefaultParser;
        XMLReader reader;

        public ReaderWrapper(XMLReader xMLReader, boolean z) {
            this.reader = xMLReader;
            this.overrideDefaultParser = z;
        }
    }

    private XMLReaderManager() {
    }

    public static XMLReaderManager getInstance(boolean z) {
        XMLReaderManager xMLReaderManager = m_singletonManager;
        xMLReaderManager.setOverrideDefaultParser(z);
        return xMLReaderManager;
    }

    public Object getProperty(String str) {
        if (str.equals("http://javax.xml.XMLConstants/property/accessExternalDTD")) {
            return this._accessExternalDTD;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            return this._xmlSecurityManager;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x005e A[Catch: all -> 0x000d, TryCatch #2 {all -> 0x000d, blocks: (B:3:0x0001, B:5:0x0005, B:8:0x0010, B:10:0x0014, B:11:0x001b, B:16:0x002c, B:18:0x0030, B:20:0x0038, B:22:0x0042, B:25:0x004a, B:27:0x0058, B:31:0x007b, B:32:0x008f, B:34:0x0093, B:36:0x009b, B:38:0x00a5, B:41:0x00b5, B:42:0x00b8, B:44:0x00c0, B:47:0x00d4, B:48:0x00db, B:50:0x00df, B:52:0x00e3, B:54:0x00ea, B:46:0x00c9, B:28:0x005e, B:30:0x0068), top: B:65:0x0001, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0068 A[Catch: all -> 0x000d, TryCatch #2 {all -> 0x000d, blocks: (B:3:0x0001, B:5:0x0005, B:8:0x0010, B:10:0x0014, B:11:0x001b, B:16:0x002c, B:18:0x0030, B:20:0x0038, B:22:0x0042, B:25:0x004a, B:27:0x0058, B:31:0x007b, B:32:0x008f, B:34:0x0093, B:36:0x009b, B:38:0x00a5, B:41:0x00b5, B:42:0x00b8, B:44:0x00c0, B:47:0x00d4, B:48:0x00db, B:50:0x00df, B:52:0x00e3, B:54:0x00ea, B:46:0x00c9, B:28:0x005e, B:30:0x0068), top: B:65:0x0001, inners: #1 }] */
    public synchronized XMLReader getXMLReader() throws SAXException {
        XMLReader xMLReader;
        try {
            if (this.m_readers == null) {
                this.m_readers = new ThreadLocal<>();
            }
            if (this.m_inUse == null) {
                this.m_inUse = new HashMap<>();
            }
            ReaderWrapper readerWrapper = this.m_readers.get();
            boolean z = readerWrapper != null;
            xMLReader = z ? readerWrapper.reader : null;
            String systemProperty = SecuritySupport.getSystemProperty(property);
            if (z) {
                Boolean bool = this.m_inUse.get(xMLReader);
                Boolean bool2 = Boolean.TRUE;
                if (bool != bool2 && readerWrapper.overrideDefaultParser == this.m_overrideDefaultParser && (systemProperty == null || xMLReader.getClass().getName().equals(systemProperty))) {
                    this.m_inUse.put(xMLReader, bool2);
                } else {
                    xMLReader = JdkXmlUtils.getXMLReader(this.m_overrideDefaultParser, this._secureProcessing);
                    if (!z) {
                        this.m_readers.set(new ReaderWrapper(xMLReader, this.m_overrideDefaultParser));
                        this.m_inUse.put(xMLReader, Boolean.TRUE);
                    }
                }
            } else {
                xMLReader = JdkXmlUtils.getXMLReader(this.m_overrideDefaultParser, this._secureProcessing);
                if (!z) {
                    this.m_readers.set(new ReaderWrapper(xMLReader, this.m_overrideDefaultParser));
                    this.m_inUse.put(xMLReader, Boolean.TRUE);
                }
            }
            JdkXmlUtils.setXMLReaderPropertyIfSupport(xMLReader, "http://javax.xml.XMLConstants/property/accessExternalDTD", this._accessExternalDTD, true);
            JdkXmlUtils.setXMLReaderPropertyIfSupport(xMLReader, JdkConstants.CDATA_CHUNK_SIZE, Integer.valueOf(this._cdataChunkSize), false);
            String str = "";
            try {
                if (this._xmlSecurityManager != null) {
                    for (XMLSecurityManager.Limit limit : XMLSecurityManager.Limit.values()) {
                        if (limit.isSupported(XMLSecurityManager.Processor.PARSER)) {
                            xMLReader.setProperty(limit.apiProperty(), this._xmlSecurityManager.getLimitValueAsString(limit));
                        }
                    }
                    if (this._xmlSecurityManager.printEntityCountInfo()) {
                        str = JdkConstants.JDK_DEBUG_LIMIT;
                        xMLReader.setProperty(JdkConstants.JDK_DEBUG_LIMIT, JdkConstants.JDK_YES);
                    }
                }
            } catch (SAXException e) {
                XMLSecurityManager.printWarning(xMLReader.getClass().getName(), str, e);
            }
            try {
                xMLReader.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", this._useCatalog);
                if (this._useCatalog && this._catalogFeatures != null) {
                    for (CatalogFeatures.Feature feature : CatalogFeatures.Feature.values()) {
                        xMLReader.setProperty(feature.getPropertyName(), this._catalogFeatures.get(feature));
                    }
                }
            } catch (SAXNotRecognizedException | SAXNotSupportedException unused) {
            }
        } catch (Throwable th) {
            throw th;
        }
        return xMLReader;
    }

    public boolean overrideDefaultParser() {
        return this.m_overrideDefaultParser;
    }

    public synchronized void releaseXMLReader(XMLReader xMLReader) {
        ReaderWrapper readerWrapper = this.m_readers.get();
        if (readerWrapper != null && readerWrapper.reader == xMLReader && xMLReader != null) {
            xMLReader.setContentHandler(null);
            xMLReader.setDTDHandler(null);
            xMLReader.setEntityResolver(null);
            try {
                xMLReader.setProperty(LEXICAL_HANDLER_PROPERTY, null);
            } catch (SAXNotRecognizedException | SAXNotSupportedException unused) {
            }
            this.m_inUse.remove(xMLReader);
        }
    }

    public void setFeature(String str, boolean z) {
        if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            this._secureProcessing = z;
        } else if ("http://javax.xml.XMLConstants/feature/useCatalog".equals(str)) {
            this._useCatalog = z;
        }
    }

    public void setOverrideDefaultParser(boolean z) {
        this.m_overrideDefaultParser = z;
    }

    public void setProperty(String str, Object obj) {
        if (str.equals("http://javax.xml.XMLConstants/property/accessExternalDTD")) {
            this._accessExternalDTD = (String) obj;
            return;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            this._xmlSecurityManager = (XMLSecurityManager) obj;
        } else if (JdkXmlFeatures.CATALOG_FEATURES.equals(str)) {
            this._catalogFeatures = (CatalogFeatures) obj;
        } else if (JdkConstants.CDATA_CHUNK_SIZE.equals(str)) {
            this._cdataChunkSize = JdkXmlUtils.getValue(obj, this._cdataChunkSize);
        }
    }
}
