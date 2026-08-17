package com.sun.org.apache.xerces.internal.parsers;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.XML11DTDScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XML11DocumentScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XML11NSDocumentScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLDTDScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLEntityHandler;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.XMLNSDocumentScannerImpl;
import com.sun.org.apache.xerces.internal.impl.XMLVersionDetector;
import com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.util.FeatureState;
import com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings;
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
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLPullParserConfiguration;
import defpackage.knd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11NonValidatingConfiguration extends ParserConfigurationSettings implements XMLPullParserConfiguration, XML11Configurable {
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_BINDER = "http://apache.org/xml/properties/internal/namespace-binder";
    protected static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "com.sun.org.apache.xerces.internal.impl.dv.dtd.XML11DTDDVFactoryImpl";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    private boolean f11Initialized;
    protected List<XMLComponent> fCommonComponents;
    protected List<XMLComponent> fComponents;
    protected boolean fConfigUpdated;
    protected XMLDTDScanner fCurrentDTDScanner;
    protected DTDDVFactory fCurrentDVFactory;
    protected XMLDocumentScanner fCurrentScanner;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDScanner fDTDScanner;
    protected DTDDVFactory fDatatypeValidatorFactory;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    protected XMLGrammarPool fGrammarPool;
    protected XMLInputSource fInputSource;
    protected XMLDocumentSource fLastComponent;
    protected Locale fLocale;
    protected XMLLocator fLocator;
    protected XMLNSDocumentScannerImpl fNamespaceScanner;
    protected XMLDocumentScannerImpl fNonNSScanner;
    protected boolean fParseInProgress;
    protected SymbolTable fSymbolTable;
    protected ValidationManager fValidationManager;
    protected XMLVersionDetector fVersionDetector;
    protected List<XMLComponent> fXML11Components;
    protected XML11DTDScannerImpl fXML11DTDScanner;
    protected DTDDVFactory fXML11DatatypeFactory;
    protected XML11DocumentScannerImpl fXML11DocScanner;
    protected XML11NSDocumentScannerImpl fXML11NSDocScanner;

    public XML11NonValidatingConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool, XMLComponentManager xMLComponentManager) {
        super(xMLComponentManager);
        this.fXML11Components = null;
        this.fCommonComponents = null;
        this.fParseInProgress = false;
        this.fConfigUpdated = false;
        this.fXML11DatatypeFactory = null;
        this.fXML11NSDocScanner = null;
        this.fXML11DocScanner = null;
        this.fXML11DTDScanner = null;
        this.f11Initialized = false;
        this.fComponents = new ArrayList();
        this.fXML11Components = new ArrayList();
        this.fCommonComponents = new ArrayList();
        this.fFeatures = new HashMap();
        this.fProperties = new HashMap();
        addRecognizedFeatures(new String[]{CONTINUE_AFTER_FATAL_ERROR, VALIDATION, "http://xml.org/sax/features/namespaces", EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES, "http://apache.org/xml/features/internal/parser-settings"});
        Map<String, Boolean> map = this.fFeatures;
        Boolean bool = Boolean.FALSE;
        map.put(VALIDATION, bool);
        Map<String, Boolean> map2 = this.fFeatures;
        Boolean bool2 = Boolean.TRUE;
        map2.put("http://xml.org/sax/features/namespaces", bool2);
        this.fFeatures.put(EXTERNAL_GENERAL_ENTITIES, bool2);
        this.fFeatures.put(EXTERNAL_PARAMETER_ENTITIES, bool2);
        this.fFeatures.put(CONTINUE_AFTER_FATAL_ERROR, bool);
        this.fFeatures.put("http://apache.org/xml/features/internal/parser-settings", bool2);
        addRecognizedProperties(new String[]{XML_STRING, "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", ENTITY_MANAGER, DOCUMENT_SCANNER, DTD_SCANNER, DTD_VALIDATOR, DATATYPE_VALIDATOR_FACTORY, VALIDATION_MANAGER, XML_STRING, "http://apache.org/xml/properties/internal/grammar-pool"});
        SymbolTable symbolTable2 = symbolTable == null ? new SymbolTable() : symbolTable;
        this.fSymbolTable = symbolTable2;
        this.fProperties.put("http://apache.org/xml/properties/internal/symbol-table", symbolTable2);
        this.fGrammarPool = xMLGrammarPool;
        if (xMLGrammarPool != null) {
            this.fProperties.put("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPool);
        }
        XMLEntityManager xMLEntityManager = new XMLEntityManager();
        this.fEntityManager = xMLEntityManager;
        this.fProperties.put(ENTITY_MANAGER, xMLEntityManager);
        addCommonComponent(this.fEntityManager);
        XMLErrorReporter xMLErrorReporter = new XMLErrorReporter();
        this.fErrorReporter = xMLErrorReporter;
        xMLErrorReporter.setDocumentLocator(this.fEntityManager.getEntityScanner());
        this.fProperties.put("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        addCommonComponent(this.fErrorReporter);
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = new XMLNSDocumentScannerImpl();
        this.fNamespaceScanner = xMLNSDocumentScannerImpl;
        this.fProperties.put(DOCUMENT_SCANNER, xMLNSDocumentScannerImpl);
        addComponent(this.fNamespaceScanner);
        XMLDTDScannerImpl xMLDTDScannerImpl = new XMLDTDScannerImpl();
        this.fDTDScanner = xMLDTDScannerImpl;
        this.fProperties.put(DTD_SCANNER, xMLDTDScannerImpl);
        addComponent((XMLComponent) this.fDTDScanner);
        DTDDVFactory dTDDVFactory = DTDDVFactory.getInstance();
        this.fDatatypeValidatorFactory = dTDDVFactory;
        this.fProperties.put(DATATYPE_VALIDATOR_FACTORY, dTDDVFactory);
        ValidationManager validationManager = new ValidationManager();
        this.fValidationManager = validationManager;
        this.fProperties.put(VALIDATION_MANAGER, validationManager);
        this.fVersionDetector = new XMLVersionDetector();
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            XMLMessageFormatter xMLMessageFormatter = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xMLMessageFormatter);
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xMLMessageFormatter);
        }
        try {
            setLocale(Locale.getDefault());
        } catch (XNIException unused) {
        }
        this.fConfigUpdated = false;
    }

    private void initXML11Components() {
        if (this.f11Initialized) {
            return;
        }
        this.fXML11DatatypeFactory = DTDDVFactory.getInstance(XML11_DATATYPE_VALIDATOR_FACTORY);
        XML11DTDScannerImpl xML11DTDScannerImpl = new XML11DTDScannerImpl();
        this.fXML11DTDScanner = xML11DTDScannerImpl;
        addXML11Component(xML11DTDScannerImpl);
        XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = new XML11NSDocumentScannerImpl();
        this.fXML11NSDocScanner = xML11NSDocumentScannerImpl;
        addXML11Component(xML11NSDocumentScannerImpl);
        this.f11Initialized = true;
    }

    public void addCommonComponent(XMLComponent xMLComponent) {
        if (this.fCommonComponents.contains(xMLComponent)) {
            return;
        }
        this.fCommonComponents.add(xMLComponent);
        addRecognizedParamsAndSetDefaults(xMLComponent);
    }

    public void addComponent(XMLComponent xMLComponent) {
        if (this.fComponents.contains(xMLComponent)) {
            return;
        }
        this.fComponents.add(xMLComponent);
        addRecognizedParamsAndSetDefaults(xMLComponent);
    }

    public void addRecognizedParamsAndSetDefaults(XMLComponent xMLComponent) {
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

    public void addXML11Component(XMLComponent xMLComponent) {
        if (this.fXML11Components.contains(xMLComponent)) {
            return;
        }
        this.fXML11Components.add(xMLComponent);
        addRecognizedParamsAndSetDefaults(xMLComponent);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings
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
            if (length == 24 && str.endsWith(Constants.PARSER_SETTINGS)) {
                return FeatureState.NOT_SUPPORTED;
            }
        }
        return super.checkFeature(str);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings
    public PropertyState checkProperty(String str) throws XMLConfigurationException {
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX) && str.length() - 33 == 20 && str.endsWith(Constants.DTD_SCANNER_PROPERTY)) {
            return PropertyState.RECOGNIZED;
        }
        if (str.startsWith(Constants.JAXP_PROPERTY_PREFIX) && str.length() - 40 == 12 && str.endsWith(Constants.SCHEMA_SOURCE)) {
            return PropertyState.RECOGNIZED;
        }
        return (str.startsWith(Constants.SAX_PROPERTY_PREFIX) && str.length() + (-30) == 10 && str.endsWith(Constants.XML_STRING_PROPERTY)) ? PropertyState.NOT_SUPPORTED : super.checkProperty(str);
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
        XMLDTDScanner xMLDTDScanner = this.fCurrentDTDScanner;
        XMLDTDScanner xMLDTDScanner2 = this.fDTDScanner;
        if (xMLDTDScanner != xMLDTDScanner2) {
            this.fCurrentDTDScanner = xMLDTDScanner2;
            setProperty(DTD_SCANNER, xMLDTDScanner2);
        }
        this.fDTDScanner.setDTDHandler(this.fDTDHandler);
        this.fDTDScanner.setDTDContentModelHandler(this.fDTDContentModelHandler);
        if (this.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
            XMLDocumentScanner xMLDocumentScanner = this.fCurrentScanner;
            XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = this.fNamespaceScanner;
            if (xMLDocumentScanner != xMLNSDocumentScannerImpl) {
                this.fCurrentScanner = xMLNSDocumentScannerImpl;
                setProperty(DOCUMENT_SCANNER, xMLNSDocumentScannerImpl);
            }
            this.fNamespaceScanner.setDTDValidator(null);
            this.fNamespaceScanner.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.setDocumentSource(this.fNamespaceScanner);
            }
            this.fLastComponent = this.fNamespaceScanner;
            return;
        }
        if (this.fNonNSScanner == null) {
            XMLDocumentScannerImpl xMLDocumentScannerImpl = new XMLDocumentScannerImpl();
            this.fNonNSScanner = xMLDocumentScannerImpl;
            addComponent(xMLDocumentScannerImpl);
        }
        XMLDocumentScanner xMLDocumentScanner2 = this.fCurrentScanner;
        XMLDocumentScannerImpl xMLDocumentScannerImpl2 = this.fNonNSScanner;
        if (xMLDocumentScanner2 != xMLDocumentScannerImpl2) {
            this.fCurrentScanner = xMLDocumentScannerImpl2;
            setProperty(DOCUMENT_SCANNER, xMLDocumentScannerImpl2);
        }
        this.fNonNSScanner.setDocumentHandler(this.fDocumentHandler);
        XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
        if (xMLDocumentHandler2 != null) {
            xMLDocumentHandler2.setDocumentSource(this.fNonNSScanner);
        }
        this.fLastComponent = this.fNonNSScanner;
    }

    public void configureXML11Pipeline() {
        DTDDVFactory dTDDVFactory = this.fCurrentDVFactory;
        DTDDVFactory dTDDVFactory2 = this.fXML11DatatypeFactory;
        if (dTDDVFactory != dTDDVFactory2) {
            this.fCurrentDVFactory = dTDDVFactory2;
            setProperty(DATATYPE_VALIDATOR_FACTORY, dTDDVFactory2);
        }
        XMLDTDScanner xMLDTDScanner = this.fCurrentDTDScanner;
        XML11DTDScannerImpl xML11DTDScannerImpl = this.fXML11DTDScanner;
        if (xMLDTDScanner != xML11DTDScannerImpl) {
            this.fCurrentDTDScanner = xML11DTDScannerImpl;
            setProperty(DTD_SCANNER, xML11DTDScannerImpl);
        }
        this.fXML11DTDScanner.setDTDHandler(this.fDTDHandler);
        this.fXML11DTDScanner.setDTDContentModelHandler(this.fDTDContentModelHandler);
        if (this.fFeatures.get("http://xml.org/sax/features/namespaces") == Boolean.TRUE) {
            XMLDocumentScanner xMLDocumentScanner = this.fCurrentScanner;
            XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = this.fXML11NSDocScanner;
            if (xMLDocumentScanner != xML11NSDocumentScannerImpl) {
                this.fCurrentScanner = xML11NSDocumentScannerImpl;
                setProperty(DOCUMENT_SCANNER, xML11NSDocumentScannerImpl);
            }
            this.fXML11NSDocScanner.setDTDValidator(null);
            this.fXML11NSDocScanner.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.setDocumentSource(this.fXML11NSDocScanner);
            }
            this.fLastComponent = this.fXML11NSDocScanner;
            return;
        }
        if (this.fXML11DocScanner == null) {
            XML11DocumentScannerImpl xML11DocumentScannerImpl = new XML11DocumentScannerImpl();
            this.fXML11DocScanner = xML11DocumentScannerImpl;
            addXML11Component(xML11DocumentScannerImpl);
        }
        XMLDocumentScanner xMLDocumentScanner2 = this.fCurrentScanner;
        XML11DocumentScannerImpl xML11DocumentScannerImpl2 = this.fXML11DocScanner;
        if (xMLDocumentScanner2 != xML11DocumentScannerImpl2) {
            this.fCurrentScanner = xML11DocumentScannerImpl2;
            setProperty(DOCUMENT_SCANNER, xML11DocumentScannerImpl2);
        }
        this.fXML11DocScanner.setDocumentHandler(this.fDocumentHandler);
        XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
        if (xMLDocumentHandler2 != null) {
            xMLDocumentHandler2.setDocumentSource(this.fXML11DocScanner);
        }
        this.fLastComponent = this.fXML11DocScanner;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLEntityResolver getEntityResolver() {
        return (XMLEntityResolver) this.fProperties.get("http://apache.org/xml/properties/internal/entity-resolver");
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLErrorHandler getErrorHandler() {
        return (XMLErrorHandler) this.fProperties.get("http://apache.org/xml/properties/internal/error-handler");
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public FeatureState getFeatureState(String str) throws XMLConfigurationException {
        return str.equals("http://apache.org/xml/features/internal/parser-settings") ? FeatureState.is(this.fConfigUpdated) : super.getFeatureState(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public Locale getLocale() {
        return this.fLocale;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLPullParserConfiguration
    public boolean parse(boolean z) throws IOException, XNIException {
        if (this.fInputSource != null) {
            try {
                this.fValidationManager.reset();
                this.fVersionDetector.reset(this);
                resetCommon();
                short sDetermineDocVersion = this.fVersionDetector.determineDocVersion(this.fInputSource);
                if (sDetermineDocVersion == 2) {
                    initXML11Components();
                    configureXML11Pipeline();
                    resetXML11();
                } else {
                    configurePipeline();
                    reset();
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

    public void reset() throws XNIException {
        Iterator<XMLComponent> it = this.fComponents.iterator();
        while (it.hasNext()) {
            it.next().reset(this);
        }
    }

    public void resetCommon() throws XNIException {
        Iterator<XMLComponent> it = this.fCommonComponents.iterator();
        while (it.hasNext()) {
            it.next().reset(this);
        }
    }

    public void resetXML11() throws XNIException {
        Iterator<XMLComponent> it = this.fXML11Components.iterator();
        while (it.hasNext()) {
            it.next().reset(this);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler) {
        this.fDTDContentModelHandler = xMLDTDContentModelHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDTDHandler(XMLDTDHandler xMLDTDHandler) {
        this.fDTDHandler = xMLDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fDocumentHandler = xMLDocumentHandler;
        XMLDocumentSource xMLDocumentSource = this.fLastComponent;
        if (xMLDocumentSource != null) {
            xMLDocumentSource.setDocumentHandler(xMLDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
            if (xMLDocumentHandler2 != null) {
                xMLDocumentHandler2.setDocumentSource(this.fLastComponent);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setEntityResolver(XMLEntityResolver xMLEntityResolver) {
        this.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setErrorHandler(XMLErrorHandler xMLErrorHandler) {
        this.fProperties.put("http://apache.org/xml/properties/internal/error-handler", xMLErrorHandler);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        this.fConfigUpdated = true;
        Iterator<XMLComponent> it = this.fComponents.iterator();
        while (it.hasNext()) {
            it.next().setFeature(str, z);
        }
        Iterator<XMLComponent> it2 = this.fCommonComponents.iterator();
        while (it2.hasNext()) {
            it2.next().setFeature(str, z);
        }
        Iterator<XMLComponent> it3 = this.fXML11Components.iterator();
        while (it3.hasNext()) {
            try {
                it3.next().setFeature(str, z);
            } catch (Exception unused) {
            }
        }
        super.setFeature(str, z);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLPullParserConfiguration
    public void setInputSource(XMLInputSource xMLInputSource) throws IOException, XMLConfigurationException {
        this.fInputSource = xMLInputSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setLocale(Locale locale) throws XNIException {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        this.fConfigUpdated = true;
        Iterator<XMLComponent> it = this.fComponents.iterator();
        while (it.hasNext()) {
            it.next().setProperty(str, obj);
        }
        Iterator<XMLComponent> it2 = this.fCommonComponents.iterator();
        while (it2.hasNext()) {
            it2.next().setProperty(str, obj);
        }
        Iterator<XMLComponent> it3 = this.fXML11Components.iterator();
        while (it3.hasNext()) {
            try {
                it3.next().setProperty(str, obj);
            } catch (Exception unused) {
            }
        }
        super.setProperty(str, obj);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
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

    public XML11NonValidatingConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public XML11NonValidatingConfiguration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this(symbolTable, xMLGrammarPool, null);
    }

    public XML11NonValidatingConfiguration() {
        this(null, null, null);
    }
}
