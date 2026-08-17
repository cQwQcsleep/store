package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.DOMCache;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMWSFilter;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import com.sun.org.apache.xalan.internal.xsltc.dom.XSLTCDTMManager;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.runtime.output.TransletOutputHandlerFactory;
import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import com.sun.org.apache.xml.internal.utils.XMLReaderManager;
import defpackage.bke;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.xml.catalog.CatalogException;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.catalog.CatalogManager;
import javax.xml.catalog.CatalogResolver;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.SecuritySupport;
import jdk.xml.internal.TransformErrorListener;
import jdk.xml.internal.XMLSecurityManager;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class TransformerImpl extends Transformer implements DOMCache {
    private static final String LEXICAL_HANDLER_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
    private static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    private String _accessExternalDTD;
    CatalogFeatures _catalogFeatures;
    CatalogResolver _catalogUriResolver;
    int _cdataChunkSize;
    private final ErrorListener _defaultListener;
    private DOM _dom;
    private XSLTCDTMManager _dtmManager;
    private String _encoding;
    private ErrorListener _errorListener;
    private int _indentNumber;
    private boolean _isIdentity;
    private boolean _isSecureProcessing;
    private String _method;
    private OutputStream _ostream;
    private boolean _overrideDefaultParser;
    private Map<String, Object> _parameters;
    private Properties _properties;
    private Properties _propertiesClone;
    private XMLReaderManager _readerManager;
    private XMLSecurityManager _securityManager;
    private String _sourceSystemId;
    private TransformerFactoryImpl _tfactory;
    private TransletOutputHandlerFactory _tohFactory;
    private AbstractTranslet _translet;
    private URIResolver _uriResolver;
    boolean _useCatalog;
    JdkProperty<String> _xsltcIsStandalone;

    public static class MessageHandler extends com.sun.org.apache.xalan.internal.xsltc.runtime.MessageHandler {
        private ErrorListener _errorListener;

        public MessageHandler(ErrorListener errorListener) {
            this._errorListener = errorListener;
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.runtime.MessageHandler
        public void displayMessage(String str) {
            ErrorListener errorListener = this._errorListener;
            if (errorListener == null) {
                System.err.println(str);
            } else {
                try {
                    errorListener.warning(new TransformerException(str));
                } catch (TransformerException unused) {
                }
            }
        }

        @Override // com.sun.org.apache.xalan.internal.xsltc.runtime.MessageHandler
        public ErrorListener getErrorListener() {
            return this._errorListener;
        }
    }

    public TransformerImpl(Translet translet, Properties properties, int i, TransformerFactoryImpl transformerFactoryImpl) {
        this._translet = null;
        this._method = null;
        this._encoding = null;
        this._sourceSystemId = null;
        TransformErrorListener transformErrorListener = new TransformErrorListener();
        this._defaultListener = transformErrorListener;
        this._errorListener = transformErrorListener;
        this._uriResolver = null;
        this._tohFactory = null;
        this._dom = null;
        this._indentNumber = -1;
        this._tfactory = null;
        this._ostream = null;
        this._dtmManager = null;
        this._isIdentity = false;
        this._isSecureProcessing = false;
        this._accessExternalDTD = "all";
        this._parameters = null;
        this._useCatalog = true;
        int i2 = JdkConstants.CDATA_CHUNK_SIZE_DEFAULT;
        this._cdataChunkSize = i2;
        AbstractTranslet abstractTranslet = (AbstractTranslet) translet;
        this._translet = abstractTranslet;
        if (abstractTranslet != null) {
            abstractTranslet.setMessageHandler(new MessageHandler(transformErrorListener));
        }
        this._properties = createOutputProperties(properties);
        this._xsltcIsStandalone = new JdkProperty<>(JdkProperty.ImplPropMap.XSLTCISSTANDALONE, String.class, "no", JdkProperty.State.DEFAULT);
        this._propertiesClone = (Properties) this._properties.clone();
        this._indentNumber = i;
        this._tfactory = transformerFactoryImpl;
        this._overrideDefaultParser = transformerFactoryImpl.overrideDefaultParser();
        this._accessExternalDTD = (String) this._tfactory.getAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD");
        this._securityManager = (XMLSecurityManager) this._tfactory.getAttribute("http://apache.org/xml/properties/security-manager");
        XMLReaderManager xMLReaderManager = XMLReaderManager.getInstance(this._overrideDefaultParser);
        this._readerManager = xMLReaderManager;
        xMLReaderManager.setProperty("http://javax.xml.XMLConstants/property/accessExternalDTD", this._accessExternalDTD);
        this._readerManager.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", this._isSecureProcessing);
        this._readerManager.setProperty("http://apache.org/xml/properties/security-manager", this._securityManager);
        int value = JdkXmlUtils.getValue(this._tfactory.getAttribute(JdkConstants.CDATA_CHUNK_SIZE), i2);
        this._cdataChunkSize = value;
        this._readerManager.setProperty(JdkConstants.CDATA_CHUNK_SIZE, Integer.valueOf(value));
        boolean feature = this._tfactory.getFeature("http://javax.xml.XMLConstants/feature/useCatalog");
        this._useCatalog = feature;
        if (feature) {
            CatalogFeatures catalogFeatures = (CatalogFeatures) this._tfactory.getAttribute(JdkXmlFeatures.CATALOG_FEATURES);
            this._catalogFeatures = catalogFeatures;
            if (catalogFeatures.get(CatalogFeatures.Feature.DEFER) != null) {
                this._readerManager.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", this._useCatalog);
                this._readerManager.setProperty(JdkXmlFeatures.CATALOG_FEATURES, this._catalogFeatures);
            }
        }
    }

    private Properties createOutputProperties(Properties properties) {
        Properties properties2 = new Properties();
        setDefaults(properties2, "xml");
        Properties properties3 = new Properties(properties2);
        if (properties != null) {
            Enumeration<?> enumerationPropertyNames = properties.propertyNames();
            while (enumerationPropertyNames.hasMoreElements()) {
                String str = (String) enumerationPropertyNames.nextElement();
                properties3.setProperty(str, properties.getProperty(str));
            }
        } else {
            properties3.setProperty("encoding", this._translet._encoding);
            String str2 = this._translet._method;
            if (str2 != null) {
                properties3.setProperty(Constants.ATTRNAME_OUTPUT_METHOD, str2);
            }
        }
        String property = properties3.getProperty(Constants.ATTRNAME_OUTPUT_METHOD);
        if (property != null) {
            if (property.equals("html")) {
                setDefaults(properties2, "html");
                return properties3;
            }
            if (property.equals("text")) {
                setDefaults(properties2, "text");
            }
        }
        return properties3;
    }

    private DOM getDOM(Source source) throws TransformerException {
        DOM dom;
        DOMWSFilter dOMWSFilter = null;
        try {
            if (source != null) {
                AbstractTranslet abstractTranslet = this._translet;
                if (abstractTranslet != null && (abstractTranslet instanceof StripFilter)) {
                    dOMWSFilter = new DOMWSFilter(abstractTranslet);
                }
                DOMWSFilter dOMWSFilter2 = dOMWSFilter;
                AbstractTranslet abstractTranslet2 = this._translet;
                boolean zHasIdCall = abstractTranslet2 != null ? abstractTranslet2.hasIdCall() : false;
                if (this._dtmManager == null) {
                    XSLTCDTMManager xSLTCDTMManagerCreateNewDTMManagerInstance = this._tfactory.createNewDTMManagerInstance();
                    this._dtmManager = xSLTCDTMManagerCreateNewDTMManagerInstance;
                    xSLTCDTMManagerCreateNewDTMManagerInstance.setOverrideDefaultParser(this._overrideDefaultParser);
                }
                dom = (DOM) this._dtmManager.getDTM(source, false, dOMWSFilter2, true, false, false, 0, zHasIdCall);
            } else {
                dom = this._dom;
                if (dom == null) {
                    return null;
                }
                this._dom = null;
            }
            if (!this._isIdentity) {
                this._translet.prepassDocument(dom);
            }
            return dom;
        } catch (Exception e) {
            if (this._errorListener != null) {
                postErrorToListener(e.getMessage());
            }
            throw new TransformerException(e);
        }
    }

    private boolean isDefaultProperty(String str, Properties properties) {
        return properties.get(str) == null;
    }

    private void postErrorToListener(String str) {
        try {
            this._errorListener.error(new TransformerException(str));
        } catch (TransformerException unused) {
        }
    }

    private void postWarningToListener(String str) {
        try {
            this._errorListener.warning(new TransformerException(str));
        } catch (TransformerException unused) {
        }
    }

    private void setDefaults(Properties properties, String str) {
        Properties defaultMethodProperties = OutputPropertiesFactory.getDefaultMethodProperties(str);
        Enumeration<?> enumerationPropertyNames = defaultMethodProperties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str2 = (String) enumerationPropertyNames.nextElement();
            properties.setProperty(str2, defaultMethodProperties.getProperty(str2));
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x004d A[Catch: all -> 0x001e, Exception -> 0x0021, RuntimeException -> 0x0024, TransletException -> 0x0027, TRY_LEAVE, TryCatch #5 {Exception -> 0x0021, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000b, B:9:0x0014, B:30:0x004d, B:31:0x0053, B:32:0x005a, B:34:0x005e, B:36:0x006a, B:38:0x0072, B:39:0x0082, B:41:0x0095, B:43:0x0099, B:45:0x009d, B:46:0x00a1, B:20:0x002a, B:22:0x002e, B:24:0x0037, B:26:0x0040, B:28:0x0044), top: B:75:0x0001, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x0072 A[Catch: all -> 0x001e, Exception -> 0x0021, RuntimeException -> 0x0024, TransletException -> 0x0027, LOOP:0: B:37:0x0070->B:38:0x0072, LOOP_END, TryCatch #5 {Exception -> 0x0021, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000b, B:9:0x0014, B:30:0x004d, B:31:0x0053, B:32:0x005a, B:34:0x005e, B:36:0x006a, B:38:0x0072, B:39:0x0082, B:41:0x0095, B:43:0x0099, B:45:0x009d, B:46:0x00a1, B:20:0x002a, B:22:0x002e, B:24:0x0037, B:26:0x0040, B:28:0x0044), top: B:75:0x0001, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x0095 A[Catch: all -> 0x001e, Exception -> 0x0021, RuntimeException -> 0x0024, TransletException -> 0x0027, TryCatch #5 {Exception -> 0x0021, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000b, B:9:0x0014, B:30:0x004d, B:31:0x0053, B:32:0x005a, B:34:0x005e, B:36:0x006a, B:38:0x0072, B:39:0x0082, B:41:0x0095, B:43:0x0099, B:45:0x009d, B:46:0x00a1, B:20:0x002a, B:22:0x002e, B:24:0x0037, B:26:0x0040, B:28:0x0044), top: B:75:0x0001, outer: #4 }] */
    private void transform(Source source, SerializationHandler serializationHandler, String str) throws TransformerException {
        DocumentBuilderFactory dOMFactory;
        String systemId;
        DOMSource dOMSource;
        CatalogFeatures catalogFeatures;
        int i;
        try {
            try {
                try {
                    if ((source instanceof StreamSource) && source.getSystemId() == null && ((StreamSource) source).getInputStream() == null && ((StreamSource) source).getReader() == null) {
                        dOMFactory = JdkXmlUtils.getDOMFactory(this._overrideDefaultParser);
                        dOMFactory.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", this._useCatalog);
                        if (this._useCatalog) {
                            for (CatalogFeatures.Feature feature : CatalogFeatures.Feature.values()) {
                                dOMFactory.setAttribute(feature.getPropertyName(), catalogFeatures.get(feature));
                            }
                        }
                        DocumentBuilder documentBuilderNewDocumentBuilder = dOMFactory.newDocumentBuilder();
                        systemId = source.getSystemId();
                        dOMSource = new DOMSource(documentBuilderNewDocumentBuilder.newDocument());
                        if (systemId != null) {
                            dOMSource.setSystemId(systemId);
                        }
                        source = dOMSource;
                    } else if (((source instanceof SAXSource) && ((SAXSource) source).getInputSource() == null && ((SAXSource) source).getXMLReader() == null) || ((source instanceof DOMSource) && ((DOMSource) source).getNode() == null)) {
                        dOMFactory = JdkXmlUtils.getDOMFactory(this._overrideDefaultParser);
                        try {
                            dOMFactory.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", this._useCatalog);
                            if (this._useCatalog && (catalogFeatures = (CatalogFeatures) this._tfactory.getAttribute(JdkXmlFeatures.CATALOG_FEATURES)) != null) {
                                while (i < r3) {
                                    dOMFactory.setAttribute(feature.getPropertyName(), catalogFeatures.get(feature));
                                }
                            }
                        } catch (ParserConfigurationException unused) {
                        }
                        DocumentBuilder documentBuilderNewDocumentBuilder2 = dOMFactory.newDocumentBuilder();
                        systemId = source.getSystemId();
                        dOMSource = new DOMSource(documentBuilderNewDocumentBuilder2.newDocument());
                        if (systemId != null) {
                            dOMSource.setSystemId(systemId);
                        }
                        source = dOMSource;
                    }
                    if (this._isIdentity) {
                        transformIdentity(source, serializationHandler);
                    } else {
                        this._translet.transform(getDOM(source), serializationHandler);
                    }
                    this._dtmManager = null;
                    OutputStream outputStream = this._ostream;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException unused2) {
                        }
                        this._ostream = null;
                    }
                } catch (Exception e) {
                    if (this._errorListener != null) {
                        postErrorToListener(e.getMessage());
                    }
                    throw new TransformerException(e);
                }
            } catch (TransletException e2) {
                if (this._errorListener != null) {
                    postErrorToListener(e2.getMessage());
                }
                throw new TransformerException(e2);
            } catch (RuntimeException e3) {
                if (this._errorListener != null) {
                    postErrorToListener(e3.getMessage());
                }
                throw new TransformerException(e3);
            }
        } catch (Throwable th) {
            this._dtmManager = null;
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00a8  */
    private void transformIdentity(Source source, SerializationHandler serializationHandler) throws Exception {
        boolean z;
        InputSource inputSource;
        InputSource inputSource2;
        if (source != null) {
            this._sourceSystemId = source.getSystemId();
        }
        boolean z2 = true;
        if (source instanceof StreamSource) {
            StreamSource streamSource = (StreamSource) source;
            InputStream inputStream = streamSource.getInputStream();
            Reader reader = streamSource.getReader();
            XMLReader xMLReader = this._readerManager.getXMLReader();
            try {
                try {
                    xMLReader.setProperty(LEXICAL_HANDLER_PROPERTY, serializationHandler);
                    xMLReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                } catch (Throwable th) {
                    this._readerManager.releaseXMLReader(xMLReader);
                    throw th;
                }
            } catch (SAXException unused) {
            }
            xMLReader.setContentHandler(serializationHandler);
            if (inputStream != null) {
                inputSource2 = new InputSource(inputStream);
                inputSource2.setSystemId(this._sourceSystemId);
            } else {
                if (reader != null) {
                    inputSource = new InputSource(reader);
                    inputSource.setSystemId(this._sourceSystemId);
                } else {
                    String str = this._sourceSystemId;
                    if (str == null) {
                        throw new TransformerException(new ErrorMsg(ErrorMsg.JAXP_NO_SOURCE_ERR).toString());
                    }
                    inputSource = new InputSource(str);
                }
                inputSource2 = inputSource;
            }
            xMLReader.parse(inputSource2);
            this._readerManager.releaseXMLReader(xMLReader);
            return;
        }
        if (!(source instanceof SAXSource)) {
            if (!(source instanceof StAXSource)) {
                if (source instanceof DOMSource) {
                    new DOM2TO(((DOMSource) source).getNode(), serializationHandler).parse();
                    return;
                } else {
                    if (!(source instanceof XSLTCSource)) {
                        throw new TransformerException(new ErrorMsg(ErrorMsg.JAXP_NO_SOURCE_ERR).toString());
                    }
                    ((SAXImpl) ((XSLTCSource) source).getDOM(null, this._translet)).copy(serializationHandler);
                    return;
                }
            }
            StAXSource stAXSource = (StAXSource) source;
            if (stAXSource.getXMLEventReader() != null) {
                StAXEvent2SAX stAXEvent2SAX = new StAXEvent2SAX(stAXSource.getXMLEventReader());
                stAXEvent2SAX.setContentHandler(serializationHandler);
                stAXEvent2SAX.parse();
                serializationHandler.flushPending();
                return;
            }
            if (stAXSource.getXMLStreamReader() != null) {
                StAXStream2SAX stAXStream2SAX = new StAXStream2SAX(stAXSource.getXMLStreamReader());
                stAXStream2SAX.setContentHandler(serializationHandler);
                stAXStream2SAX.parse();
                serializationHandler.flushPending();
                return;
            }
            return;
        }
        SAXSource sAXSource = (SAXSource) source;
        XMLReader xMLReader2 = sAXSource.getXMLReader();
        InputSource inputSource3 = sAXSource.getInputSource();
        if (xMLReader2 == null) {
            try {
                xMLReader2 = this._readerManager.getXMLReader();
                z = false;
            } catch (Throwable th2) {
                th = th2;
                if (!z2) {
                    this._readerManager.releaseXMLReader(xMLReader2);
                }
                throw th;
            }
        } else {
            z = true;
        }
        try {
            try {
                xMLReader2.setProperty(LEXICAL_HANDLER_PROPERTY, serializationHandler);
                xMLReader2.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
            } catch (Throwable th3) {
                th = th3;
                z2 = z;
                if (!z2) {
                    this._readerManager.releaseXMLReader(xMLReader2);
                }
                throw th;
            }
        } catch (SAXException unused2) {
        }
        xMLReader2.setContentHandler(serializationHandler);
        xMLReader2.parse(inputSource3);
        if (z) {
            return;
        }
        this._readerManager.releaseXMLReader(xMLReader2);
    }

    private boolean validOutputProperty(String str) {
        return str.equals("encoding") || str.equals(Constants.ATTRNAME_OUTPUT_METHOD) || str.equals("indent") || str.equals(Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC) || str.equals(Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM) || str.equals(Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS) || str.equals(Constants.ATTRNAME_OUTPUT_MEDIATYPE) || str.equals("omit-xml-declaration") || str.equals(Constants.ATTRNAME_OUTPUT_STANDALONE) || str.equals("version") || JdkProperty.ImplPropMap.XSLTCISSTANDALONE.is(str) || str.charAt(0) == '{';
    }

    @Override // javax.xml.transform.Transformer
    public void clearParameters() {
        Map<String, Object> map;
        if (!this._isIdentity || (map = this._parameters) == null) {
            this._translet.clearParameters();
        } else {
            map.clear();
        }
    }

    @Override // javax.xml.transform.Transformer
    public ErrorListener getErrorListener() {
        return this._errorListener;
    }

    public SerializationHandler getOutputHandler(Result result) throws TransformerException {
        this._method = (String) this._properties.get(Constants.ATTRNAME_OUTPUT_METHOD);
        this._encoding = this._properties.getProperty("encoding");
        TransletOutputHandlerFactory transletOutputHandlerFactoryNewInstance = TransletOutputHandlerFactory.newInstance(this._overrideDefaultParser, this._errorListener);
        this._tohFactory = transletOutputHandlerFactoryNewInstance;
        transletOutputHandlerFactoryNewInstance.setEncoding(this._encoding);
        String str = this._method;
        if (str != null) {
            this._tohFactory.setOutputMethod(str);
        }
        int i = this._indentNumber;
        if (i >= 0) {
            this._tohFactory.setIndentNumber(i);
        }
        try {
            if (result instanceof SAXResult) {
                SAXResult sAXResult = (SAXResult) result;
                this._tohFactory.setHandler(sAXResult.getHandler());
                LexicalHandler lexicalHandler = sAXResult.getLexicalHandler();
                if (lexicalHandler != null) {
                    this._tohFactory.setLexicalHandler(lexicalHandler);
                }
                this._tohFactory.setOutputType(1);
                return this._tohFactory.getSerializationHandler();
            }
            if (result instanceof StAXResult) {
                if (((StAXResult) result).getXMLEventWriter() != null) {
                    this._tohFactory.setXMLEventWriter(((StAXResult) result).getXMLEventWriter());
                } else if (((StAXResult) result).getXMLStreamWriter() != null) {
                    this._tohFactory.setXMLStreamWriter(((StAXResult) result).getXMLStreamWriter());
                }
                this._tohFactory.setOutputType(3);
                return this._tohFactory.getSerializationHandler();
            }
            if (result instanceof DOMResult) {
                this._tohFactory.setNode(((DOMResult) result).getNode());
                this._tohFactory.setNextSibling(((DOMResult) result).getNextSibling());
                this._tohFactory.setOutputType(2);
                return this._tohFactory.getSerializationHandler();
            }
            if (!(result instanceof StreamResult)) {
                return null;
            }
            StreamResult streamResult = (StreamResult) result;
            this._tohFactory.setOutputType(0);
            Writer writer = streamResult.getWriter();
            if (writer != null) {
                this._tohFactory.setWriter(writer);
                return this._tohFactory.getSerializationHandler();
            }
            OutputStream outputStream = streamResult.getOutputStream();
            if (outputStream != null) {
                this._tohFactory.setOutputStream(outputStream);
                return this._tohFactory.getSerializationHandler();
            }
            String systemId = result.getSystemId();
            if (systemId == null) {
                throw new TransformerException(new ErrorMsg(ErrorMsg.JAXP_NO_RESULT_ERR).toString());
            }
            if (systemId.startsWith("file:")) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(new URI(systemId)).toFile());
                    this._ostream = fileOutputStream;
                    this._tohFactory.setOutputStream(fileOutputStream);
                    return this._tohFactory.getSerializationHandler();
                } catch (Exception e) {
                    throw new TransformerException(e);
                }
            }
            if (!systemId.startsWith("http:")) {
                TransletOutputHandlerFactory transletOutputHandlerFactory = this._tohFactory;
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(systemId));
                this._ostream = fileOutputStream2;
                transletOutputHandlerFactory.setOutputStream(fileOutputStream2);
                return this._tohFactory.getSerializationHandler();
            }
            URLConnection uRLConnectionOpenConnection = new URL(systemId).openConnection();
            TransletOutputHandlerFactory transletOutputHandlerFactory2 = this._tohFactory;
            OutputStream outputStream2 = uRLConnectionOpenConnection.getOutputStream();
            this._ostream = outputStream2;
            transletOutputHandlerFactory2.setOutputStream(outputStream2);
            return this._tohFactory.getSerializationHandler();
        } catch (UnknownServiceException e2) {
            throw new TransformerException(e2);
        } catch (IOException e3) {
            throw new TransformerException(e3);
        } catch (ParserConfigurationException e4) {
            throw new TransformerException(e4);
        }
    }

    @Override // javax.xml.transform.Transformer
    public Properties getOutputProperties() {
        return (Properties) this._properties.clone();
    }

    @Override // javax.xml.transform.Transformer
    public String getOutputProperty(String str) throws IllegalArgumentException {
        if (JdkProperty.ImplPropMap.XSLTCISSTANDALONE.is(str)) {
            return this._xsltcIsStandalone.getValue();
        }
        if (validOutputProperty(str)) {
            return this._properties.getProperty(str);
        }
        bke.a(new ErrorMsg(ErrorMsg.JAXP_UNKNOWN_PROP_ERR, str));
        return null;
    }

    @Override // javax.xml.transform.Transformer
    public final Object getParameter(String str) {
        if (!this._isIdentity) {
            return this._translet.getParameter(str);
        }
        Map<String, Object> map = this._parameters;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public TransformerFactoryImpl getTransformerFactory() {
        return this._tfactory;
    }

    public AbstractTranslet getTranslet() {
        return this._translet;
    }

    public TransletOutputHandlerFactory getTransletOutputHandlerFactory() {
        return this._tohFactory;
    }

    @Override // javax.xml.transform.Transformer
    public URIResolver getURIResolver() {
        return this._uriResolver;
    }

    public boolean isIdentity() {
        return this._isIdentity;
    }

    public boolean isSecureProcessing() {
        return this._isSecureProcessing;
    }

    public boolean overrideDefaultParser() {
        return this._overrideDefaultParser;
    }

    @Override // javax.xml.transform.Transformer
    public void reset() {
        this._method = null;
        this._encoding = null;
        this._sourceSystemId = null;
        this._errorListener = this._defaultListener;
        this._uriResolver = null;
        this._dom = null;
        this._parameters = null;
        this._indentNumber = -1;
        setOutputProperties(null);
        this._tohFactory = null;
        this._ostream = null;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOMCache
    public DOM retrieveDocument(String str, String str2, Translet translet) {
        String message;
        try {
            if (str2.length() == 0) {
                str2 = str;
            }
            URIResolver uRIResolver = this._uriResolver;
            Source sourceResolve = uRIResolver != null ? uRIResolver.resolve(str2, str) : null;
            if (sourceResolve == null && this._useCatalog && this._catalogFeatures.get(CatalogFeatures.Feature.FILES) != null) {
                if (this._catalogUriResolver == null) {
                    this._catalogUriResolver = CatalogManager.catalogResolver(this._catalogFeatures, new URI[0]);
                }
                sourceResolve = this._catalogUriResolver.resolve(str2, str);
            }
            if (sourceResolve != null) {
                return getDOM(sourceResolve);
            }
            AbstractTranslet abstractTranslet = (AbstractTranslet) translet;
            String absoluteURI = SystemIDResolver.getAbsoluteURI(str2, str);
            try {
                String strCheckAccess = SecuritySupport.checkAccess(absoluteURI, abstractTranslet.getAllowedProtocols(), "all");
                message = strCheckAccess != null ? new ErrorMsg(ErrorMsg.ACCESSING_XSLT_TARGET_ERR, SecuritySupport.sanitizePath(str2), strCheckAccess).toString() : null;
            } catch (IOException e) {
                message = e.getMessage();
            }
            if (message == null) {
                return getDOM(new StreamSource(absoluteURI));
            }
            abstractTranslet.setAccessError(message);
            return null;
        } catch (TransformerException | CatalogException e2) {
            if (this._errorListener != null) {
                postErrorToListener("File not found: " + e2.getMessage());
            }
            return null;
        }
    }

    public void setDOM(DOM dom) {
        this._dom = dom;
    }

    @Override // javax.xml.transform.Transformer
    public void setErrorListener(ErrorListener errorListener) throws IllegalArgumentException {
        if (errorListener == null) {
            bke.a(new ErrorMsg(ErrorMsg.ERROR_LISTENER_NULL_ERR, "Transformer"));
            return;
        }
        this._errorListener = errorListener;
        AbstractTranslet abstractTranslet = this._translet;
        if (abstractTranslet != null) {
            abstractTranslet.setMessageHandler(new MessageHandler(errorListener));
        }
    }

    @Override // javax.xml.transform.Transformer
    public void setOutputProperties(Properties properties) throws IllegalArgumentException {
        if (properties == null) {
            this._properties = (Properties) this._propertiesClone.clone();
            return;
        }
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str = (String) enumerationPropertyNames.nextElement();
            if (!isDefaultProperty(str, properties)) {
                if (!validOutputProperty(str)) {
                    bke.a(new ErrorMsg(ErrorMsg.JAXP_UNKNOWN_PROP_ERR, str));
                    return;
                }
                this._properties.setProperty(str, properties.getProperty(str));
            }
        }
    }

    @Override // javax.xml.transform.Transformer
    public void setOutputProperty(String str, String str2) throws IllegalArgumentException {
        if (!validOutputProperty(str)) {
            bke.a(new ErrorMsg(ErrorMsg.JAXP_UNKNOWN_PROP_ERR, str));
        } else if (JdkProperty.ImplPropMap.XSLTCISSTANDALONE.is(str)) {
            this._xsltcIsStandalone.setValue(str, str2, JdkProperty.State.APIPROPERTY);
        } else {
            this._properties.setProperty(str, str2);
        }
    }

    public void setOverrideDefaultParser(boolean z) {
        this._overrideDefaultParser = z;
    }

    @Override // javax.xml.transform.Transformer
    public void setParameter(String str, Object obj) {
        if (obj == null) {
            bke.a(new ErrorMsg(ErrorMsg.JAXP_INVALID_SET_PARAM_VALUE, str));
        } else {
            if (!this._isIdentity) {
                this._translet.addParameter(str, obj);
                return;
            }
            if (this._parameters == null) {
                this._parameters = new HashMap();
            }
            this._parameters.put(str, obj);
        }
    }

    public void setSecureProcessing(boolean z) {
        this._isSecureProcessing = z;
        this._readerManager.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", z);
    }

    @Override // javax.xml.transform.Transformer
    public void setURIResolver(URIResolver uRIResolver) {
        this._uriResolver = uRIResolver;
    }

    public void transferOutputProperties(SerializationHandler serializationHandler) {
        String strSubstring;
        Properties properties = this._properties;
        if (properties == null) {
            return;
        }
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        String str = null;
        String str2 = null;
        while (enumerationPropertyNames.hasMoreElements()) {
            String str3 = (String) enumerationPropertyNames.nextElement();
            String str4 = (String) this._properties.get(str3);
            if (str4 != null) {
                if (str3.equals(Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC)) {
                    str = str4;
                } else if (str3.equals(Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM)) {
                    str2 = str4;
                } else if (str3.equals(Constants.ATTRNAME_OUTPUT_MEDIATYPE)) {
                    serializationHandler.setMediaType(str4);
                } else if (str3.equals(Constants.ATTRNAME_OUTPUT_STANDALONE)) {
                    serializationHandler.setStandalone(str4);
                } else if (str3.equals("version")) {
                    serializationHandler.setVersion(str4);
                } else if (str3.equals("omit-xml-declaration")) {
                    serializationHandler.setOmitXMLDeclaration(str4.toLowerCase().equals(JdkConstants.JDK_YES));
                } else if (str3.equals("indent")) {
                    serializationHandler.setIndent(str4.toLowerCase().equals(JdkConstants.JDK_YES));
                } else if (str3.equals("{http://xml.apache.org/xslt}indent-amount")) {
                    serializationHandler.setIndentAmount(Integer.parseInt(str4));
                } else if (str3.equals(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT)) {
                    serializationHandler.setIndentAmount(Integer.parseInt(str4));
                } else if (str3.equals(Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS)) {
                    StringTokenizer stringTokenizer = new StringTokenizer(str4);
                    ArrayList arrayList = null;
                    while (stringTokenizer.hasMoreTokens()) {
                        String strNextToken = stringTokenizer.nextToken();
                        int iLastIndexOf = strNextToken.lastIndexOf(58);
                        if (iLastIndexOf > 0) {
                            strSubstring = strNextToken.substring(0, iLastIndexOf);
                            strNextToken = strNextToken.substring(iLastIndexOf + 1);
                        } else {
                            strSubstring = null;
                        }
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(strSubstring);
                        arrayList.add(strNextToken);
                    }
                    serializationHandler.setCdataSectionElements(arrayList);
                }
            }
        }
        if (str != null || str2 != null) {
            serializationHandler.setDoctype(str2, str);
        }
        if (this._xsltcIsStandalone.getValue().equals(JdkConstants.JDK_YES)) {
            serializationHandler.setIsStandalone(true);
        }
    }

    public TransformerImpl(Properties properties, int i, TransformerFactoryImpl transformerFactoryImpl) {
        this(null, properties, i, transformerFactoryImpl);
        this._isIdentity = true;
    }

    @Override // javax.xml.transform.Transformer
    public void transform(Source source, Result result) throws TransformerException {
        if (!this._isIdentity) {
            AbstractTranslet abstractTranslet = this._translet;
            if (abstractTranslet != null) {
                transferOutputProperties(abstractTranslet);
            } else {
                throw new TransformerException(new ErrorMsg(ErrorMsg.JAXP_NO_TRANSLET_ERR).toString());
            }
        }
        SerializationHandler outputHandler = getOutputHandler(result);
        if (outputHandler != null) {
            if (!this._isIdentity && (this._uriResolver != null || (this._tfactory.getFeature("http://javax.xml.XMLConstants/feature/useCatalog") && this._tfactory.getAttribute(JdkXmlUtils.CATALOG_FILES) != null))) {
                this._translet.setDOMCache(this);
            }
            if (this._isIdentity) {
                transferOutputProperties(outputHandler);
            }
            transform(source, outputHandler, this._encoding);
            try {
                if (result instanceof DOMResult) {
                    ((DOMResult) result).setNode(this._tohFactory.getNode());
                    return;
                }
                if (result instanceof StAXResult) {
                    if (((StAXResult) result).getXMLEventWriter() != null) {
                        this._tohFactory.getXMLEventWriter().flush();
                        return;
                    } else {
                        if (((StAXResult) result).getXMLStreamWriter() != null) {
                            this._tohFactory.getXMLStreamWriter().flush();
                            return;
                        }
                        return;
                    }
                }
                return;
            } catch (Exception unused) {
                System.out.println("Result writing error");
                return;
            }
        }
        throw new TransformerException(new ErrorMsg(ErrorMsg.JAXP_NO_HANDLER_ERR).toString());
    }

    private void transferOutputProperties(AbstractTranslet abstractTranslet) {
        Properties properties = this._properties;
        if (properties == null) {
            return;
        }
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String str = (String) enumerationPropertyNames.nextElement();
            String str2 = (String) this._properties.get(str);
            if (str2 != null) {
                if (str.equals("encoding")) {
                    abstractTranslet._encoding = str2;
                } else if (str.equals(Constants.ATTRNAME_OUTPUT_METHOD)) {
                    abstractTranslet._method = str2;
                } else if (str.equals(Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC)) {
                    abstractTranslet._doctypePublic = str2;
                } else if (str.equals(Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM)) {
                    abstractTranslet._doctypeSystem = str2;
                } else if (str.equals(Constants.ATTRNAME_OUTPUT_MEDIATYPE)) {
                    abstractTranslet._mediaType = str2;
                } else if (str.equals(Constants.ATTRNAME_OUTPUT_STANDALONE)) {
                    abstractTranslet._standalone = str2;
                } else if (str.equals("version")) {
                    abstractTranslet._version = str2;
                } else if (str.equals("omit-xml-declaration")) {
                    abstractTranslet._omitHeader = str2.toLowerCase().equals(JdkConstants.JDK_YES);
                } else if (str.equals("indent")) {
                    abstractTranslet._indent = str2.toLowerCase().equals(JdkConstants.JDK_YES);
                } else if (str.equals("{http://xml.apache.org/xslt}indent-amount")) {
                    abstractTranslet._indentamount = Integer.parseInt(str2);
                } else if (str.equals(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT)) {
                    abstractTranslet._indentamount = Integer.parseInt(str2);
                } else if (str.equals(Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS)) {
                    abstractTranslet._cdata = null;
                    StringTokenizer stringTokenizer = new StringTokenizer(str2);
                    while (stringTokenizer.hasMoreTokens()) {
                        abstractTranslet.addCdataElement(stringTokenizer.nextToken());
                    }
                }
            }
        }
        if (this._xsltcIsStandalone.getValue().equals(JdkConstants.JDK_YES)) {
            abstractTranslet._isStandalone = true;
        }
    }
}
