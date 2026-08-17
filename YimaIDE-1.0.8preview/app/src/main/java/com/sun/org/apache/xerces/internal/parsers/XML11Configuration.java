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
import com.sun.org.apache.xerces.internal.impl.dtd.XML11DTDProcessor;
import com.sun.org.apache.xerces.internal.impl.dtd.XML11DTDValidator;
import com.sun.org.apache.xerces.internal.impl.dtd.XML11NSDTDValidator;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDProcessor;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidator;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLNSDTDValidator;
import com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.catalog.CatalogFeatures;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XML11Configuration extends ParserConfigurationSettings implements XMLPullParserConfiguration, XML11Configurable {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String DATATYPE_VALIDATOR_FACTORY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String DOCUMENT_SCANNER = "http://apache.org/xml/properties/internal/document-scanner";
    protected static final String DTD_PROCESSOR = "http://apache.org/xml/properties/internal/dtd-processor";
    protected static final String DTD_SCANNER = "http://apache.org/xml/properties/internal/dtd-scanner";
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String IDENTITY_CONSTRAINT_CHECKING = "http://apache.org/xml/features/validation/identity-constraint-checking";
    protected static final String ID_IDREF_CHECKING = "http://apache.org/xml/features/validation/id-idref-checking";
    protected static final String IGNORE_XSI_TYPE = "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl";
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    protected static final String LOCALE = "http://apache.org/xml/properties/locale";
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_BINDER = "http://apache.org/xml/properties/internal/namespace-binder";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    protected static final boolean PRINT_EXCEPTION_STACK_TRACE = false;
    protected static final String ROOT_ELEMENT_DECL = "http://apache.org/xml/properties/validation/schema/root-element-declaration";
    protected static final String ROOT_TYPE_DEF = "http://apache.org/xml/properties/validation/schema/root-type-definition";
    protected static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    protected static final String UNPARSED_ENTITY_CHECKING = "http://apache.org/xml/features/validation/unparsed-entity-checking";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    protected static final String WARN_ON_UNDECLARED_ELEMDEF = "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef";
    protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "com.sun.org.apache.xerces.internal.impl.dv.dtd.XML11DTDDVFactoryImpl";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String XMLSCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
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
    protected XMLDTDProcessor fDTDProcessor;
    protected XMLDTDScanner fDTDScanner;
    protected XMLDTDValidator fDTDValidator;
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
    protected XMLDTDValidator fNonNSDTDValidator;
    protected XMLDocumentScannerImpl fNonNSScanner;
    protected boolean fParseInProgress;
    protected XMLSchemaValidator fSchemaValidator;
    protected SymbolTable fSymbolTable;
    private boolean fSymbolTableJustInitialized;
    private boolean fSymbolTableProvided;
    protected ValidationManager fValidationManager;
    protected XMLVersionDetector fVersionDetector;
    protected List<XMLComponent> fXML11Components;
    protected XML11DTDProcessor fXML11DTDProcessor;
    protected XML11DTDScannerImpl fXML11DTDScanner;
    protected XML11DTDValidator fXML11DTDValidator;
    protected DTDDVFactory fXML11DatatypeFactory;
    protected XML11DocumentScannerImpl fXML11DocScanner;
    protected XML11NSDTDValidator fXML11NSDTDValidator;
    protected XML11NSDocumentScannerImpl fXML11NSDocScanner;

    public XML11Configuration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool, XMLComponentManager xMLComponentManager) {
        super(xMLComponentManager);
        this.fXML11Components = null;
        this.fCommonComponents = null;
        this.fParseInProgress = false;
        this.fConfigUpdated = false;
        this.fXML11DatatypeFactory = null;
        this.fXML11NSDocScanner = null;
        this.fXML11DocScanner = null;
        this.fXML11NSDTDValidator = null;
        this.fXML11DTDValidator = null;
        this.fXML11DTDScanner = null;
        this.fXML11DTDProcessor = null;
        this.f11Initialized = false;
        this.fSymbolTableProvided = false;
        this.fSymbolTableJustInitialized = true;
        this.fComponents = new ArrayList();
        this.fXML11Components = new ArrayList();
        this.fCommonComponents = new ArrayList();
        addRecognizedFeatures(new String[]{CONTINUE_AFTER_FATAL_ERROR, LOAD_EXTERNAL_DTD, VALIDATION, "http://xml.org/sax/features/namespaces", NORMALIZE_DATA, SCHEMA_ELEMENT_DEFAULT, SCHEMA_AUGMENT_PSVI, "http://apache.org/xml/features/generate-synthetic-annotations", VALIDATE_ANNOTATIONS, HONOUR_ALL_SCHEMALOCATIONS, IGNORE_XSI_TYPE, ID_IDREF_CHECKING, IDENTITY_CONSTRAINT_CHECKING, UNPARSED_ENTITY_CHECKING, NAMESPACE_GROWTH, TOLERATE_DUPLICATES, USE_GRAMMAR_POOL_ONLY, XMLSCHEMA_VALIDATION, XMLSCHEMA_FULL_CHECKING, EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES, "http://apache.org/xml/features/internal/parser-settings", "http://javax.xml.XMLConstants/feature/secure-processing", "http://javax.xml.XMLConstants/feature/useCatalog", JdkConstants.RESET_SYMBOL_TABLE, JdkConstants.OVERRIDE_PARSER});
        Map<String, Boolean> map = this.fFeatures;
        Boolean bool = Boolean.FALSE;
        map.put(VALIDATION, bool);
        Map<String, Boolean> map2 = this.fFeatures;
        Boolean bool2 = Boolean.TRUE;
        map2.put("http://xml.org/sax/features/namespaces", bool2);
        this.fFeatures.put(EXTERNAL_GENERAL_ENTITIES, bool2);
        this.fFeatures.put(EXTERNAL_PARAMETER_ENTITIES, bool2);
        this.fFeatures.put(CONTINUE_AFTER_FATAL_ERROR, bool);
        this.fFeatures.put(LOAD_EXTERNAL_DTD, bool2);
        this.fFeatures.put(SCHEMA_ELEMENT_DEFAULT, bool2);
        this.fFeatures.put(NORMALIZE_DATA, bool2);
        this.fFeatures.put(SCHEMA_AUGMENT_PSVI, bool2);
        this.fFeatures.put("http://apache.org/xml/features/generate-synthetic-annotations", bool);
        this.fFeatures.put(VALIDATE_ANNOTATIONS, bool);
        this.fFeatures.put(HONOUR_ALL_SCHEMALOCATIONS, bool);
        this.fFeatures.put(IGNORE_XSI_TYPE, bool);
        this.fFeatures.put(ID_IDREF_CHECKING, bool2);
        this.fFeatures.put(IDENTITY_CONSTRAINT_CHECKING, bool2);
        this.fFeatures.put(UNPARSED_ENTITY_CHECKING, bool2);
        this.fFeatures.put(NAMESPACE_GROWTH, bool);
        this.fFeatures.put(TOLERATE_DUPLICATES, bool);
        this.fFeatures.put(USE_GRAMMAR_POOL_ONLY, bool);
        this.fFeatures.put("http://apache.org/xml/features/internal/parser-settings", bool2);
        this.fFeatures.put("http://javax.xml.XMLConstants/feature/secure-processing", bool2);
        this.fFeatures.put("http://javax.xml.XMLConstants/feature/useCatalog", Boolean.valueOf(JdkXmlUtils.USE_CATALOG_DEFAULT));
        this.fFeatures.put(JdkConstants.RESET_SYMBOL_TABLE, Boolean.valueOf(JdkConstants.RESET_SYMBOL_TABLE_DEFAULT));
        this.fFeatures.put(JdkConstants.OVERRIDE_PARSER, Boolean.valueOf(JdkConstants.OVERRIDE_PARSER_DEFAULT));
        addRecognizedProperties(new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", ENTITY_MANAGER, DOCUMENT_SCANNER, DTD_SCANNER, DTD_PROCESSOR, DTD_VALIDATOR, DATATYPE_VALIDATOR_FACTORY, VALIDATION_MANAGER, SCHEMA_VALIDATOR, XML_STRING, "http://apache.org/xml/properties/internal/grammar-pool", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage", SCHEMA_LOCATION, SCHEMA_NONS_LOCATION, ROOT_TYPE_DEF, ROOT_ELEMENT_DECL, "http://apache.org/xml/properties/locale", SCHEMA_DV_FACTORY, "http://apache.org/xml/properties/security-manager", "jdk.xml.xmlSecurityPropertyManager", JdkXmlUtils.CATALOG_DEFER, JdkXmlUtils.CATALOG_FILES, JdkXmlUtils.CATALOG_PREFER, JdkXmlUtils.CATALOG_RESOLVE, JdkConstants.CDATA_CHUNK_SIZE});
        boolean z = symbolTable != null;
        this.fSymbolTableProvided = z;
        if (z) {
            this.fSymbolTable = symbolTable;
        } else {
            this.fSymbolTable = new SymbolTable();
        }
        this.fProperties.put("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
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
        XMLDTDProcessor xMLDTDProcessor = new XMLDTDProcessor();
        this.fDTDProcessor = xMLDTDProcessor;
        this.fProperties.put(DTD_PROCESSOR, xMLDTDProcessor);
        addComponent(this.fDTDProcessor);
        XMLNSDTDValidator xMLNSDTDValidator = new XMLNSDTDValidator();
        this.fDTDValidator = xMLNSDTDValidator;
        this.fProperties.put(DTD_VALIDATOR, xMLNSDTDValidator);
        addComponent(this.fDTDValidator);
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
        for (CatalogFeatures.Feature feature : CatalogFeatures.Feature.values()) {
            this.fProperties.put(feature.getPropertyName(), null);
        }
        setProperty(JdkConstants.CDATA_CHUNK_SIZE, Integer.valueOf(JdkConstants.CDATA_CHUNK_SIZE_DEFAULT));
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
        XML11DTDProcessor xML11DTDProcessor = new XML11DTDProcessor();
        this.fXML11DTDProcessor = xML11DTDProcessor;
        addXML11Component(xML11DTDProcessor);
        XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = new XML11NSDocumentScannerImpl();
        this.fXML11NSDocScanner = xML11NSDocumentScannerImpl;
        addXML11Component(xML11NSDocumentScannerImpl);
        XML11NSDTDValidator xML11NSDTDValidator = new XML11NSDTDValidator();
        this.fXML11NSDTDValidator = xML11NSDTDValidator;
        addXML11Component(xML11NSDTDValidator);
        this.f11Initialized = true;
    }

    private void resetSymbolTable() {
        if (!this.fFeatures.get(JdkConstants.RESET_SYMBOL_TABLE).booleanValue() || this.fSymbolTableProvided) {
            return;
        }
        if (this.fSymbolTableJustInitialized) {
            this.fSymbolTableJustInitialized = false;
            return;
        }
        SymbolTable symbolTable = new SymbolTable();
        this.fSymbolTable = symbolTable;
        this.fProperties.put("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
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
            if (length == 17 && str.endsWith(Constants.SCHEMA_VALIDATION_FEATURE)) {
                return FeatureState.RECOGNIZED;
            }
            if (length == 31 && str.endsWith(Constants.SCHEMA_FULL_CHECKING)) {
                return FeatureState.RECOGNIZED;
            }
            if (length == 34 && str.endsWith(Constants.SCHEMA_NORMALIZED_VALUE)) {
                return FeatureState.RECOGNIZED;
            }
            if (length == 33 && str.endsWith(Constants.SCHEMA_ELEMENT_DEFAULT)) {
                return FeatureState.RECOGNIZED;
            }
            if (length == 24 && str.endsWith(Constants.PARSER_SETTINGS)) {
                return FeatureState.NOT_SUPPORTED;
            }
        }
        return super.checkFeature(str);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings
    public PropertyState checkProperty(String str) throws XMLConfigurationException {
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int length = str.length() - 33;
            if (length == 20 && str.endsWith(Constants.DTD_SCANNER_PROPERTY)) {
                return PropertyState.RECOGNIZED;
            }
            if (length == 30 && str.endsWith(Constants.SCHEMA_LOCATION)) {
                return PropertyState.RECOGNIZED;
            }
            if (length == 41 && str.endsWith(Constants.SCHEMA_NONS_LOCATION)) {
                return PropertyState.RECOGNIZED;
            }
        }
        if (str.startsWith(Constants.JAXP_PROPERTY_PREFIX) && str.length() - 40 == 12 && str.endsWith(Constants.SCHEMA_SOURCE)) {
            return PropertyState.RECOGNIZED;
        }
        return (str.startsWith(Constants.SAX_PROPERTY_PREFIX) && str.length() - 30 == 10 && str.endsWith(Constants.XML_STRING_PROPERTY)) ? PropertyState.NOT_SUPPORTED : super.checkProperty(str);
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
            setProperty(DTD_PROCESSOR, this.fDTDProcessor);
        }
        this.fDTDScanner.setDTDHandler(this.fDTDProcessor);
        this.fDTDProcessor.setDTDSource(this.fDTDScanner);
        this.fDTDProcessor.setDTDHandler(this.fDTDHandler);
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.setDTDSource(this.fDTDProcessor);
        }
        this.fDTDScanner.setDTDContentModelHandler(this.fDTDProcessor);
        this.fDTDProcessor.setDTDContentModelSource(this.fDTDScanner);
        this.fDTDProcessor.setDTDContentModelHandler(this.fDTDContentModelHandler);
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.setDTDContentModelSource(this.fDTDProcessor);
        }
        Boolean bool = this.fFeatures.get("http://xml.org/sax/features/namespaces");
        Boolean bool2 = Boolean.TRUE;
        if (bool == bool2) {
            XMLDocumentScanner xMLDocumentScanner = this.fCurrentScanner;
            XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = this.fNamespaceScanner;
            if (xMLDocumentScanner != xMLNSDocumentScannerImpl) {
                this.fCurrentScanner = xMLNSDocumentScannerImpl;
                setProperty(DOCUMENT_SCANNER, xMLNSDocumentScannerImpl);
                setProperty(DTD_VALIDATOR, this.fDTDValidator);
            }
            this.fNamespaceScanner.setDTDValidator(this.fDTDValidator);
            this.fNamespaceScanner.setDocumentHandler(this.fDTDValidator);
            this.fDTDValidator.setDocumentSource(this.fNamespaceScanner);
            this.fDTDValidator.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.setDocumentSource(this.fDTDValidator);
            }
            this.fLastComponent = this.fDTDValidator;
        } else {
            if (this.fNonNSScanner == null) {
                this.fNonNSScanner = new XMLDocumentScannerImpl();
                this.fNonNSDTDValidator = new XMLDTDValidator();
                addComponent(this.fNonNSScanner);
                addComponent(this.fNonNSDTDValidator);
            }
            XMLDocumentScanner xMLDocumentScanner2 = this.fCurrentScanner;
            XMLDocumentScannerImpl xMLDocumentScannerImpl = this.fNonNSScanner;
            if (xMLDocumentScanner2 != xMLDocumentScannerImpl) {
                this.fCurrentScanner = xMLDocumentScannerImpl;
                setProperty(DOCUMENT_SCANNER, xMLDocumentScannerImpl);
                setProperty(DTD_VALIDATOR, this.fNonNSDTDValidator);
            }
            this.fNonNSScanner.setDocumentHandler(this.fNonNSDTDValidator);
            this.fNonNSDTDValidator.setDocumentSource(this.fNonNSScanner);
            this.fNonNSDTDValidator.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
            if (xMLDocumentHandler2 != null) {
                xMLDocumentHandler2.setDocumentSource(this.fNonNSDTDValidator);
            }
            this.fLastComponent = this.fNonNSDTDValidator;
        }
        if (this.fFeatures.get(XMLSCHEMA_VALIDATION) == bool2) {
            if (this.fSchemaValidator == null) {
                XMLSchemaValidator xMLSchemaValidator = new XMLSchemaValidator();
                this.fSchemaValidator = xMLSchemaValidator;
                setProperty(SCHEMA_VALIDATOR, xMLSchemaValidator);
                addCommonComponent(this.fSchemaValidator);
                this.fSchemaValidator.reset(this);
                if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
                    this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
                }
            }
            this.fLastComponent.setDocumentHandler(this.fSchemaValidator);
            this.fSchemaValidator.setDocumentSource(this.fLastComponent);
            this.fSchemaValidator.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler3 = this.fDocumentHandler;
            if (xMLDocumentHandler3 != null) {
                xMLDocumentHandler3.setDocumentSource(this.fSchemaValidator);
            }
            this.fLastComponent = this.fSchemaValidator;
        }
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
            setProperty(DTD_PROCESSOR, this.fXML11DTDProcessor);
        }
        this.fXML11DTDScanner.setDTDHandler(this.fXML11DTDProcessor);
        this.fXML11DTDProcessor.setDTDSource(this.fXML11DTDScanner);
        this.fXML11DTDProcessor.setDTDHandler(this.fDTDHandler);
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.setDTDSource(this.fXML11DTDProcessor);
        }
        this.fXML11DTDScanner.setDTDContentModelHandler(this.fXML11DTDProcessor);
        this.fXML11DTDProcessor.setDTDContentModelSource(this.fXML11DTDScanner);
        this.fXML11DTDProcessor.setDTDContentModelHandler(this.fDTDContentModelHandler);
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.setDTDContentModelSource(this.fXML11DTDProcessor);
        }
        Boolean bool = this.fFeatures.get("http://xml.org/sax/features/namespaces");
        Boolean bool2 = Boolean.TRUE;
        if (bool == bool2) {
            XMLDocumentScanner xMLDocumentScanner = this.fCurrentScanner;
            XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = this.fXML11NSDocScanner;
            if (xMLDocumentScanner != xML11NSDocumentScannerImpl) {
                this.fCurrentScanner = xML11NSDocumentScannerImpl;
                setProperty(DOCUMENT_SCANNER, xML11NSDocumentScannerImpl);
                setProperty(DTD_VALIDATOR, this.fXML11NSDTDValidator);
            }
            this.fXML11NSDocScanner.setDTDValidator(this.fXML11NSDTDValidator);
            this.fXML11NSDocScanner.setDocumentHandler(this.fXML11NSDTDValidator);
            this.fXML11NSDTDValidator.setDocumentSource(this.fXML11NSDocScanner);
            this.fXML11NSDTDValidator.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.setDocumentSource(this.fXML11NSDTDValidator);
            }
            this.fLastComponent = this.fXML11NSDTDValidator;
        } else {
            if (this.fXML11DocScanner == null) {
                XML11DocumentScannerImpl xML11DocumentScannerImpl = new XML11DocumentScannerImpl();
                this.fXML11DocScanner = xML11DocumentScannerImpl;
                addXML11Component(xML11DocumentScannerImpl);
                XML11DTDValidator xML11DTDValidator = new XML11DTDValidator();
                this.fXML11DTDValidator = xML11DTDValidator;
                addXML11Component(xML11DTDValidator);
            }
            XMLDocumentScanner xMLDocumentScanner2 = this.fCurrentScanner;
            XML11DocumentScannerImpl xML11DocumentScannerImpl2 = this.fXML11DocScanner;
            if (xMLDocumentScanner2 != xML11DocumentScannerImpl2) {
                this.fCurrentScanner = xML11DocumentScannerImpl2;
                setProperty(DOCUMENT_SCANNER, xML11DocumentScannerImpl2);
                setProperty(DTD_VALIDATOR, this.fXML11DTDValidator);
            }
            this.fXML11DocScanner.setDocumentHandler(this.fXML11DTDValidator);
            this.fXML11DTDValidator.setDocumentSource(this.fXML11DocScanner);
            this.fXML11DTDValidator.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
            if (xMLDocumentHandler2 != null) {
                xMLDocumentHandler2.setDocumentSource(this.fXML11DTDValidator);
            }
            this.fLastComponent = this.fXML11DTDValidator;
        }
        if (this.fFeatures.get(XMLSCHEMA_VALIDATION) == bool2) {
            if (this.fSchemaValidator == null) {
                XMLSchemaValidator xMLSchemaValidator = new XMLSchemaValidator();
                this.fSchemaValidator = xMLSchemaValidator;
                setProperty(SCHEMA_VALIDATOR, xMLSchemaValidator);
                addCommonComponent(this.fSchemaValidator);
                this.fSchemaValidator.reset(this);
                if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
                    this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
                }
            }
            this.fLastComponent.setDocumentHandler(this.fSchemaValidator);
            this.fSchemaValidator.setDocumentSource(this.fLastComponent);
            this.fSchemaValidator.setDocumentHandler(this.fDocumentHandler);
            XMLDocumentHandler xMLDocumentHandler3 = this.fDocumentHandler;
            if (xMLDocumentHandler3 != null) {
                xMLDocumentHandler3.setDocumentSource(this.fSchemaValidator);
            }
            this.fLastComponent = this.fSchemaValidator;
        }
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

    public FeatureState getFeatureState0(String str) throws XMLConfigurationException {
        return super.getFeatureState(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public Locale getLocale() {
        return this.fLocale;
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
                this.fConfigUpdated = true;
                resetSymbolTable();
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
            } catch (IOException | RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                knd.a(e2);
                return false;
            }
        }
        try {
            return this.fCurrentScanner.scanDocument(z);
        } catch (IOException | RuntimeException e3) {
            throw e3;
        } catch (Exception e4) {
            knd.a(e4);
            return false;
        }
    }

    public void reset() throws XNIException {
        int size = this.fComponents.size();
        for (int i = 0; i < size; i++) {
            this.fComponents.get(i).reset(this);
        }
    }

    public void resetCommon() throws XNIException {
        int size = this.fCommonComponents.size();
        for (int i = 0; i < size; i++) {
            this.fCommonComponents.get(i).reset(this);
        }
    }

    public void resetXML11() throws XNIException {
        int size = this.fXML11Components.size();
        for (int i = 0; i < size; i++) {
            this.fXML11Components.get(i).reset(this);
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
        if ("http://apache.org/xml/properties/locale".equals(str)) {
            setLocale((Locale) obj);
        }
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

    public XML11Configuration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public XML11Configuration(SymbolTable symbolTable, XMLGrammarPool xMLGrammarPool) {
        this(symbolTable, xMLGrammarPool, null);
    }

    public XML11Configuration() {
        this(null, null, null);
    }
}
