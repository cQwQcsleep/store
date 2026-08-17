package com.sun.org.apache.xerces.internal.impl.xs.opti;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.XML11DTDScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XML11NSDocumentScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLDTDScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLEntityHandler;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLVersionDetector;
import com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.parsers.BasicParserConfiguration;
import com.sun.org.apache.xerces.internal.util.FeatureState;
import com.sun.org.apache.xerces.internal.util.PropertyState;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLPullParserConfiguration;
import defpackage.knd;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SchemaParsingConfig extends BasicParserConfiguration implements XMLPullParserConfiguration {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String LOCALE = "http://apache.org/xml/properties/locale";
    protected static final String NAMESPACE_BINDER = "http://apache.org/xml/properties/internal/namespace-binder";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    private static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String WARN_ON_UNDECLARED_ELEMDEF = "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef";
    protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "com.sun.org.apache.xerces.internal.impl.dv.dtd.XML11DTDDVFactoryImpl";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private boolean f11Initialized;
    protected boolean fConfigUpdated;
    protected XMLDTDScanner fCurrentDTDScanner;
    protected DTDDVFactory fCurrentDVFactory;
    protected XMLDocumentScanner fCurrentScanner;
    protected final XMLDTDScannerImpl fDTDScanner;
    protected final DTDDVFactory fDatatypeValidatorFactory;
    protected final XMLEntityManager fEntityManager;
    protected final XMLErrorReporter fErrorReporter;
    protected XMLGrammarPool fGrammarPool;
    protected XMLInputSource fInputSource;
    protected XMLLocator fLocator;
    protected final XMLNSDocumentScannerImpl fNamespaceScanner;
    protected boolean fParseInProgress;
    protected final ValidationManager fValidationManager;
    protected final XMLVersionDetector fVersionDetector;
    protected XML11DTDScannerImpl fXML11DTDScanner;
    protected DTDDVFactory fXML11DatatypeFactory;
    protected XML11NSDocumentScannerImpl fXML11NSDocScanner;

    public SchemaParsingConfig(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool, XMLComponentManager xMLComponentManager) {
        super(symbolTable, xMLComponentManager);
        this.fXML11DatatypeFactory = null;
        this.fXML11NSDocScanner = null;
        this.fXML11DTDScanner = null;
        this.fParseInProgress = false;
        this.fConfigUpdated = false;
        this.f11Initialized = false;
        addRecognizedFeatures(new String[]{"http://apache.org/xml/features/internal/parser-settings", WARN_ON_DUPLICATE_ATTDEF, WARN_ON_UNDECLARED_ELEMDEF, ALLOW_JAVA_ENCODINGS, CONTINUE_AFTER_FATAL_ERROR, LOAD_EXTERNAL_DTD, NOTIFY_BUILTIN_REFS, NOTIFY_CHAR_REFS, "http://apache.org/xml/features/generate-synthetic-annotations", "http://javax.xml.XMLConstants/feature/useCatalog", JdkConstants.OVERRIDE_PARSER});
        Map<String, Boolean> map = this.fFeatures;
        Boolean bool = Boolean.TRUE;
        map.put("http://apache.org/xml/features/internal/parser-settings", bool);
        Map<String, Boolean> map2 = this.fFeatures;
        Boolean bool2 = Boolean.FALSE;
        map2.put(WARN_ON_DUPLICATE_ATTDEF, bool2);
        this.fFeatures.put(WARN_ON_UNDECLARED_ELEMDEF, bool2);
        this.fFeatures.put(ALLOW_JAVA_ENCODINGS, bool2);
        this.fFeatures.put(CONTINUE_AFTER_FATAL_ERROR, bool2);
        this.fFeatures.put(LOAD_EXTERNAL_DTD, bool);
        this.fFeatures.put(NOTIFY_BUILTIN_REFS, bool2);
        this.fFeatures.put(NOTIFY_CHAR_REFS, bool2);
        this.fFeatures.put("http://apache.org/xml/features/generate-synthetic-annotations", bool2);
        this.fFeatures.put("http://javax.xml.XMLConstants/feature/useCatalog", Boolean.valueOf(JdkXmlUtils.USE_CATALOG_DEFAULT));
        this.fFeatures.put(JdkConstants.OVERRIDE_PARSER, Boolean.valueOf(JdkConstants.OVERRIDE_PARSER_DEFAULT));
        addRecognizedProperties(new String[]{"http://apache.org/xml/properties/internal/error-reporter", ENTITY_MANAGER, DOCUMENT_SCANNER, DTD_SCANNER, DTD_VALIDATOR, NAMESPACE_BINDER, "http://apache.org/xml/properties/internal/grammar-pool", DATATYPE_VALIDATOR_FACTORY, VALIDATION_MANAGER, "http://apache.org/xml/features/generate-synthetic-annotations", "http://apache.org/xml/properties/locale", JdkXmlUtils.CATALOG_DEFER, JdkXmlUtils.CATALOG_FILES, JdkXmlUtils.CATALOG_PREFER, JdkXmlUtils.CATALOG_RESOLVE, JdkConstants.CDATA_CHUNK_SIZE});
        this.fGrammarPool = xMLGrammarPool;
        if (xMLGrammarPool != null) {
            setProperty("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPool);
        }
        XMLEntityManager xMLEntityManager = new XMLEntityManager();
        this.fEntityManager = xMLEntityManager;
        this.fProperties.put(ENTITY_MANAGER, xMLEntityManager);
        addComponent(xMLEntityManager);
        XMLErrorReporter xMLErrorReporter = new XMLErrorReporter();
        this.fErrorReporter = xMLErrorReporter;
        xMLErrorReporter.setDocumentLocator(xMLEntityManager.getEntityScanner());
        this.fProperties.put("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter);
        addComponent(xMLErrorReporter);
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = new XMLNSDocumentScannerImpl();
        this.fNamespaceScanner = xMLNSDocumentScannerImpl;
        this.fProperties.put(DOCUMENT_SCANNER, xMLNSDocumentScannerImpl);
        addRecognizedParamsAndSetDefaults(xMLNSDocumentScannerImpl);
        XMLDTDScannerImpl xMLDTDScannerImpl = new XMLDTDScannerImpl();
        this.fDTDScanner = xMLDTDScannerImpl;
        this.fProperties.put(DTD_SCANNER, xMLDTDScannerImpl);
        addRecognizedParamsAndSetDefaults(xMLDTDScannerImpl);
        DTDDVFactory dTDDVFactory = DTDDVFactory.getInstance();
        this.fDatatypeValidatorFactory = dTDDVFactory;
        this.fProperties.put(DATATYPE_VALIDATOR_FACTORY, dTDDVFactory);
        ValidationManager validationManager = new ValidationManager();
        this.fValidationManager = validationManager;
        this.fProperties.put(VALIDATION_MANAGER, validationManager);
        this.fProperties.put(JdkConstants.CDATA_CHUNK_SIZE, Integer.valueOf(JdkConstants.CDATA_CHUNK_SIZE_DEFAULT));
        this.fVersionDetector = new XMLVersionDetector();
        if (xMLErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            XMLMessageFormatter xMLMessageFormatter = new XMLMessageFormatter();
            xMLErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xMLMessageFormatter);
            xMLErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xMLMessageFormatter);
        }
        if (xMLErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
            xMLErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
        }
        try {
            setLocale(Locale.getDefault());
        } catch (XNIException unused) {
        }
    }

    private void addRecognizedParamsAndSetDefaults(XMLComponent xMLComponent) {
        String[] recognizedFeatures = xMLComponent.getRecognizedFeatures();
        addRecognizedFeatures(recognizedFeatures);
        String[] recognizedProperties = xMLComponent.getRecognizedProperties();
        addRecognizedProperties(recognizedProperties);
        if (recognizedFeatures != null) {
            for (String str : recognizedFeatures) {
                Boolean featureDefault = xMLComponent.getFeatureDefault(str);
                if (featureDefault != null && !this.fFeatures.containsKey(str)) {
                    this.fFeatures.put(str, featureDefault);
                    this.fConfigUpdated = true;
                }
            }
        }
        if (recognizedProperties != null) {
            for (String str2 : recognizedProperties) {
                Object propertyDefault = xMLComponent.getPropertyDefault(str2);
                if (propertyDefault != null && !this.fProperties.containsKey(str2)) {
                    this.fProperties.put(str2, propertyDefault);
                    this.fConfigUpdated = true;
                }
            }
        }
    }

    private void initXML11Components() {
        if (this.f11Initialized) {
            return;
        }
        this.fXML11DatatypeFactory = DTDDVFactory.getInstance(XML11_DATATYPE_VALIDATOR_FACTORY);
        XML11DTDScannerImpl xML11DTDScannerImpl = new XML11DTDScannerImpl();
        this.fXML11DTDScanner = xML11DTDScannerImpl;
        addRecognizedParamsAndSetDefaults(xML11DTDScannerImpl);
        XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = new XML11NSDocumentScannerImpl();
        this.fXML11NSDocScanner = xML11NSDocumentScannerImpl;
        addRecognizedParamsAndSetDefaults(xML11NSDocumentScannerImpl);
        this.f11Initialized = true;
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.BasicParserConfiguration, com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings
    public FeatureState checkFeature(String str) throws XMLConfigurationException {
        if (str.startsWith(Constants.XERCES_FEATURE_PREFIX)) {
            int length = str.length() - 31;
            if (length == 18 && str.endsWith(Constants.DYNAMIC_VALIDATION_FEATURE)) {
                return FeatureState.RECOGNIZED;
            }
            if (length == 35 && str.endsWith(Constants.DEFAULT_ATTRIBUTE_VALUES_FEATURE)) {
                return FeatureState.NOT_SUPPORTED;
            }
            if (length == 34 && str.endsWith(Constants.VALIDATE_CONTENT_MODELS_FEATURE)) {
                return FeatureState.NOT_SUPPORTED;
            }
            if (length == 30 && str.endsWith(Constants.LOAD_DTD_GRAMMAR_FEATURE)) {
                return FeatureState.RECOGNIZED;
            }
            if (length == 31 && str.endsWith(Constants.LOAD_EXTERNAL_DTD_FEATURE)) {
                return FeatureState.RECOGNIZED;
            }
            if (length == 29 && str.endsWith(Constants.VALIDATE_DATATYPES_FEATURE)) {
                return FeatureState.NOT_SUPPORTED;
            }
        }
        return super.checkFeature(str);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.BasicParserConfiguration, com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings
    public PropertyState checkProperty(String str) throws XMLConfigurationException {
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX) && str.length() - 33 == 20 && str.endsWith(Constants.DTD_SCANNER_PROPERTY)) {
            return PropertyState.RECOGNIZED;
        }
        return (str.startsWith(Constants.JAXP_PROPERTY_PREFIX) && str.length() + (-40) == 12 && str.endsWith(Constants.SCHEMA_SOURCE)) ? PropertyState.RECOGNIZED : super.checkProperty(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLPullParserConfiguration
    public void cleanup() {
        this.fEntityManager.closeReaders();
    }

    public void configurePipeline() {
        DTDDVFactory dTDDVFactory = this.fCurrentDVFactory;
        DTDDVFactory dTDDVFactory2 = this.fDatatypeValidatorFactory;
        if (dTDDVFactory != dTDDVFactory2) {
            this.fCurrentDVFactory = dTDDVFactory2;
            setProperty(DATATYPE_VALIDATOR_FACTORY, dTDDVFactory2);
        }
        XMLDocumentScanner xMLDocumentScanner = this.fCurrentScanner;
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = this.fNamespaceScanner;
        if (xMLDocumentScanner != xMLNSDocumentScannerImpl) {
            this.fCurrentScanner = xMLNSDocumentScannerImpl;
            setProperty(DOCUMENT_SCANNER, xMLNSDocumentScannerImpl);
        }
        this.fNamespaceScanner.setDocumentHandler(this.fDocumentHandler);
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.setDocumentSource(this.fNamespaceScanner);
        }
        this.fLastComponent = this.fNamespaceScanner;
        XMLDTDScanner xMLDTDScanner = this.fCurrentDTDScanner;
        XMLDTDScannerImpl xMLDTDScannerImpl = this.fDTDScanner;
        if (xMLDTDScanner != xMLDTDScannerImpl) {
            this.fCurrentDTDScanner = xMLDTDScannerImpl;
            setProperty(DTD_SCANNER, xMLDTDScannerImpl);
        }
        this.fDTDScanner.setDTDHandler(this.fDTDHandler);
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.setDTDSource(this.fDTDScanner);
        }
        this.fDTDScanner.setDTDContentModelHandler(this.fDTDContentModelHandler);
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.setDTDContentModelSource(this.fDTDScanner);
        }
    }

    public void configureXML11Pipeline() {
        DTDDVFactory dTDDVFactory = this.fCurrentDVFactory;
        DTDDVFactory dTDDVFactory2 = this.fXML11DatatypeFactory;
        if (dTDDVFactory != dTDDVFactory2) {
            this.fCurrentDVFactory = dTDDVFactory2;
            setProperty(DATATYPE_VALIDATOR_FACTORY, dTDDVFactory2);
        }
        XMLDocumentScanner xMLDocumentScanner = this.fCurrentScanner;
        XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = this.fXML11NSDocScanner;
        if (xMLDocumentScanner != xML11NSDocumentScannerImpl) {
            this.fCurrentScanner = xML11NSDocumentScannerImpl;
            setProperty(DOCUMENT_SCANNER, xML11NSDocumentScannerImpl);
        }
        this.fXML11NSDocScanner.setDocumentHandler(this.fDocumentHandler);
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.setDocumentSource(this.fXML11NSDocScanner);
        }
        this.fLastComponent = this.fXML11NSDocScanner;
        XMLDTDScanner xMLDTDScanner = this.fCurrentDTDScanner;
        XML11DTDScannerImpl xML11DTDScannerImpl = this.fXML11DTDScanner;
        if (xMLDTDScanner != xML11DTDScannerImpl) {
            this.fCurrentDTDScanner = xML11DTDScannerImpl;
            setProperty(DTD_SCANNER, xML11DTDScannerImpl);
        }
        this.fXML11DTDScanner.setDTDHandler(this.fDTDHandler);
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.setDTDSource(this.fXML11DTDScanner);
        }
        this.fXML11DTDScanner.setDTDContentModelHandler(this.fDTDContentModelHandler);
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.setDTDContentModelSource(this.fXML11DTDScanner);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public FeatureState getFeatureState(String str) throws XMLConfigurationException {
        return str.equals("http://apache.org/xml/features/internal/parser-settings") ? FeatureState.is(this.fConfigUpdated) : super.getFeatureState(str);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public PropertyState getPropertyState(String str) throws XMLConfigurationException {
        return "http://apache.org/xml/properties/locale".equals(str) ? PropertyState.is(getLocale()) : super.getPropertyState(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLPullParserConfiguration
    public boolean parse(boolean z) throws IOException, XNIException {
        if (this.fInputSource != null) {
            try {
                this.fValidationManager.reset();
                this.fVersionDetector.reset(this);
                reset();
                short sDetermineDocVersion = this.fVersionDetector.determineDocVersion(this.fInputSource);
                if (sDetermineDocVersion == 1) {
                    configurePipeline();
                    resetXML10();
                } else {
                    if (sDetermineDocVersion != 2) {
                        return false;
                    }
                    initXML11Components();
                    configureXML11Pipeline();
                    resetXML11();
                }
                this.fConfigUpdated = false;
                this.fVersionDetector.startDocumentParsing((XMLEntityHandler) this.fCurrentScanner, sDetermineDocVersion);
                this.fInputSource = null;
            } catch (XNIException e) {
                throw e;
            } catch (IOException e2) {
                throw e2;
            } catch (RuntimeException e3) {
                throw e3;
            } catch (Exception e4) {
                knd.a(e4);
                return false;
            }
        }
        try {
            return this.fCurrentScanner.scanDocument(z);
        } catch (XNIException e5) {
            throw e5;
        } catch (IOException e6) {
            throw e6;
        } catch (RuntimeException e7) {
            throw e7;
        } catch (Exception e8) {
            knd.a(e8);
            return false;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.BasicParserConfiguration
    public void reset() throws XNIException {
        super.reset();
    }

    public void resetNodePool() {
    }

    public final void resetXML10() throws XNIException {
        this.fNamespaceScanner.reset(this);
        this.fDTDScanner.reset(this);
    }

    public final void resetXML11() throws XNIException {
        this.fXML11NSDocScanner.reset(this);
        this.fXML11DTDScanner.reset(this);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.BasicParserConfiguration, com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        this.fConfigUpdated = true;
        this.fNamespaceScanner.setFeature(str, z);
        this.fDTDScanner.setFeature(str, z);
        if (this.f11Initialized) {
            try {
                this.fXML11DTDScanner.setFeature(str, z);
            } catch (Exception unused) {
            }
            try {
                this.fXML11NSDocScanner.setFeature(str, z);
            } catch (Exception unused2) {
            }
        }
        super.setFeature(str, z);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLPullParserConfiguration
    public void setInputSource(XMLInputSource xMLInputSource) throws IOException, XMLConfigurationException {
        this.fInputSource = xMLInputSource;
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.BasicParserConfiguration, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setLocale(Locale locale) throws XNIException {
        super.setLocale(locale);
        this.fErrorReporter.setLocale(locale);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.BasicParserConfiguration, com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        this.fConfigUpdated = true;
        if ("http://apache.org/xml/properties/locale".equals(str)) {
            setLocale((Locale) obj);
        }
        this.fNamespaceScanner.setProperty(str, obj);
        this.fDTDScanner.setProperty(str, obj);
        if (this.f11Initialized) {
            try {
                this.fXML11DTDScanner.setProperty(str, obj);
            } catch (Exception unused) {
            }
            try {
                this.fXML11NSDocScanner.setProperty(str, obj);
            } catch (Exception unused2) {
            }
        }
        super.setProperty(str, obj);
    }

    @Override // com.sun.org.apache.xerces.internal.parsers.BasicParserConfiguration, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void parse(XMLInputSource xMLInputSource) throws IOException, XNIException {
        if (!this.fParseInProgress) {
            this.fParseInProgress = true;
            try {
                try {
                    try {
                        setInputSource(xMLInputSource);
                        parse(true);
                        this.fParseInProgress = false;
                        cleanup();
                        return;
                    } catch (XNIException e) {
                        throw e;
                    } catch (IOException e2) {
                        throw e2;
                    }
                } catch (RuntimeException e3) {
                    throw e3;
                } catch (Exception e4) {
                    throw new XNIException(e4);
                }
            } catch (Throwable th) {
                this.fParseInProgress = false;
                cleanup();
                throw th;
            }
        }
        throw new XNIException("FWK005 parse may not be called while parsing.");
    }

    public SchemaParsingConfig(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public SchemaParsingConfig(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this(symbolTable, xMLGrammarPool, null);
    }

    public SchemaParsingConfig() {
        this(null, null, null);
    }
}
