package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.util.DOMEntityResolverWrapper;
import com.sun.org.apache.xerces.internal.util.DOMErrorHandlerWrapper;
import com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings;
import com.sun.org.apache.xerces.internal.util.PropertyState;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.xml.catalog.CatalogFeatures;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.ls.LSResourceResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMConfigurationImpl extends ParserConfigurationSettings implements XMLParserConfiguration, DOMConfiguration {
    protected static final String BALANCE_SYNTAX_TREES = "http://apache.org/xml/features/validation/balance-syntax-trees";
    protected static final short CDATA = 8;
    protected static final short COMMENTS = 32;
    protected static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    protected static final String DTD_VALIDATOR_FACTORY_PROPERTY = "http://apache.org/xml/properties/internal/datatype-validator-factory";
    protected static final String DTD_VALIDATOR_PROPERTY = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final short DTNORMALIZATION = 2;
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final short ENTITIES = 4;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final short INFOSET_FALSE_PARAMS = 14;
    protected static final short INFOSET_MASK = 815;
    protected static final short INFOSET_TRUE_PARAMS = 801;
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final short NAMESPACES = 1;
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final short NSDECL = 512;
    protected static final short PSVI = 128;
    protected static final String SCHEMA = "http://apache.org/xml/features/validation/schema";
    protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String SEND_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final short SPLITCDATA = 16;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    protected static final short VALIDATE = 64;
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final short WELLFORMED = 256;
    protected static final String XERCES_NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String XERCES_VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String XML11_DATATYPE_VALIDATOR_FACTORY = "com.sun.org.apache.xerces.internal.impl.dv.dtd.XML11DTDDVFactoryImpl";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    protected static final String XML_STRING = "http://xml.org/sax/properties/xml-string";
    protected List<XMLComponent> fComponents;
    protected DTDDVFactory fCurrentDVFactory;
    protected DTDDVFactory fDatatypeValidatorFactory;
    XMLDocumentHandler fDocumentHandler;
    protected final DOMErrorHandlerWrapper fErrorHandlerWrapper;
    protected XMLErrorReporter fErrorReporter;
    protected Locale fLocale;
    private DOMStringList fRecognizedParameters;
    private String fSchemaLocation;
    protected SymbolTable fSymbolTable;
    protected ValidationManager fValidationManager;
    protected DTDDVFactory fXML11DatatypeFactory;
    protected short features;

    public DOMConfigurationImpl(SymbolTable symbolTable, XMLComponentManager xMLComponentManager) {
        XSMessageFormatter xSMessageFormatter;
        super(xMLComponentManager);
        this.features = (short) 0;
        this.fErrorHandlerWrapper = new DOMErrorHandlerWrapper();
        this.fSchemaLocation = null;
        this.fFeatures = new HashMap();
        this.fProperties = new HashMap();
        addRecognizedFeatures(new String[]{XERCES_VALIDATION, "http://xml.org/sax/features/namespaces", SCHEMA, SCHEMA_FULL_CHECKING, DYNAMIC_VALIDATION, NORMALIZE_DATA, SCHEMA_ELEMENT_DEFAULT, SEND_PSVI, "http://apache.org/xml/features/generate-synthetic-annotations", VALIDATE_ANNOTATIONS, HONOUR_ALL_SCHEMALOCATIONS, USE_GRAMMAR_POOL_ONLY, DISALLOW_DOCTYPE_DECL_FEATURE, BALANCE_SYNTAX_TREES, WARN_ON_DUPLICATE_ATTDEF, "http://apache.org/xml/features/internal/parser-settings", NAMESPACE_GROWTH, TOLERATE_DUPLICATES, "http://javax.xml.XMLConstants/feature/useCatalog", JdkConstants.OVERRIDE_PARSER});
        setFeature(XERCES_VALIDATION, false);
        setFeature(SCHEMA, false);
        setFeature(SCHEMA_FULL_CHECKING, false);
        setFeature(DYNAMIC_VALIDATION, false);
        setFeature(NORMALIZE_DATA, false);
        setFeature(SCHEMA_ELEMENT_DEFAULT, false);
        setFeature("http://xml.org/sax/features/namespaces", true);
        setFeature(SEND_PSVI, true);
        setFeature("http://apache.org/xml/features/generate-synthetic-annotations", false);
        setFeature(VALIDATE_ANNOTATIONS, false);
        setFeature(HONOUR_ALL_SCHEMALOCATIONS, false);
        setFeature(USE_GRAMMAR_POOL_ONLY, false);
        setFeature(DISALLOW_DOCTYPE_DECL_FEATURE, false);
        setFeature(BALANCE_SYNTAX_TREES, false);
        setFeature(WARN_ON_DUPLICATE_ATTDEF, false);
        setFeature("http://apache.org/xml/features/internal/parser-settings", true);
        setFeature(NAMESPACE_GROWTH, false);
        setFeature(TOLERATE_DUPLICATES, false);
        setFeature("http://javax.xml.XMLConstants/feature/useCatalog", JdkXmlUtils.USE_CATALOG_DEFAULT);
        setFeature(JdkConstants.OVERRIDE_PARSER, JdkConstants.OVERRIDE_PARSER_DEFAULT);
        addRecognizedProperties(new String[]{XML_STRING, "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-handler", "http://apache.org/xml/properties/internal/entity-resolver", "http://apache.org/xml/properties/internal/error-reporter", ENTITY_MANAGER, VALIDATION_MANAGER, "http://apache.org/xml/properties/internal/grammar-pool", "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage", SCHEMA_LOCATION, SCHEMA_NONS_LOCATION, DTD_VALIDATOR_PROPERTY, DTD_VALIDATOR_FACTORY_PROPERTY, SCHEMA_DV_FACTORY, "http://apache.org/xml/properties/security-manager", "jdk.xml.xmlSecurityPropertyManager", JdkXmlUtils.CATALOG_DEFER, JdkXmlUtils.CATALOG_FILES, JdkXmlUtils.CATALOG_PREFER, JdkXmlUtils.CATALOG_RESOLVE, JdkConstants.CDATA_CHUNK_SIZE});
        this.features = (short) (((short) (((short) (((short) (((short) (((short) (((short) (this.features | 1)) | 4)) | 32)) | 8)) | 16)) | 256)) | 512);
        this.fSymbolTable = symbolTable == null ? new SymbolTable() : symbolTable;
        this.fComponents = new ArrayList();
        setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
        XMLErrorReporter xMLErrorReporter = new XMLErrorReporter();
        this.fErrorReporter = xMLErrorReporter;
        setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter);
        addComponent(this.fErrorReporter);
        this.fDatatypeValidatorFactory = DTDDVFactory.getInstance();
        this.fXML11DatatypeFactory = DTDDVFactory.getInstance(XML11_DATATYPE_VALIDATOR_FACTORY);
        DTDDVFactory dTDDVFactory = this.fDatatypeValidatorFactory;
        this.fCurrentDVFactory = dTDDVFactory;
        setProperty(DTD_VALIDATOR_FACTORY_PROPERTY, dTDDVFactory);
        XMLComponent xMLEntityManager = new XMLEntityManager();
        setProperty(ENTITY_MANAGER, xMLEntityManager);
        addComponent(xMLEntityManager);
        ValidationManager validationManagerCreateValidationManager = createValidationManager();
        this.fValidationManager = validationManagerCreateValidationManager;
        setProperty(VALIDATION_MANAGER, validationManagerCreateValidationManager);
        setProperty("http://apache.org/xml/properties/security-manager", new XMLSecurityManager(true));
        setProperty("jdk.xml.xmlSecurityPropertyManager", new XMLSecurityPropertyManager());
        if (this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            XMLMessageFormatter xMLMessageFormatter = new XMLMessageFormatter();
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xMLMessageFormatter);
            this.fErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xMLMessageFormatter);
        }
        if (this.fErrorReporter.getMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN) == null) {
            try {
                xSMessageFormatter = new XSMessageFormatter();
            } catch (Exception unused) {
                xSMessageFormatter = null;
            }
            if (xSMessageFormatter != null) {
                this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, xSMessageFormatter);
            }
        }
        try {
            setLocale(Locale.getDefault());
        } catch (XNIException unused2) {
        }
        for (CatalogFeatures.Feature feature : CatalogFeatures.Feature.values()) {
            setProperty(feature.getPropertyName(), null);
        }
        setProperty(JdkConstants.CDATA_CHUNK_SIZE, Integer.valueOf(JdkConstants.CDATA_CHUNK_SIZE_DEFAULT));
    }

    private static DOMException newFeatureNotFoundError(String str) {
        return new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{str}));
    }

    private static DOMException newFeatureNotSupportedError(String str) {
        return new DOMException((short) 9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
    }

    private static DOMException newTypeMismatchError(String str) {
        return new DOMException((short) 17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_TYPE_MISMATCH_ERR, new Object[]{str}));
    }

    public void addComponent(XMLComponent xMLComponent) {
        if (this.fComponents.contains(xMLComponent)) {
            return;
        }
        this.fComponents.add(xMLComponent);
        addRecognizedFeatures(xMLComponent.getRecognizedFeatures());
        addRecognizedProperties(xMLComponent.getRecognizedProperties());
    }

    @Override // org.w3c.dom.DOMConfiguration
    public boolean canSetParameter(String str, Object obj) {
        if (obj == null) {
            return true;
        }
        if (!(obj instanceof Boolean)) {
            if (str.equalsIgnoreCase("error-handler")) {
                return obj instanceof DOMErrorHandler;
            }
            if (str.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
                return obj instanceof LSResourceResolver;
            }
            if (str.equalsIgnoreCase("schema-location")) {
                return obj instanceof String;
            }
            if (str.equalsIgnoreCase("schema-type")) {
                return (obj instanceof String) && (obj.equals(Constants.NS_XMLSCHEMA) || obj.equals(Constants.NS_DTD));
            }
            if (str.equalsIgnoreCase("http://apache.org/xml/properties/internal/entity-resolver")) {
                return obj instanceof XMLEntityResolver;
            }
            if (str.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
                return obj instanceof SymbolTable;
            }
            return str.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool") && (obj instanceof XMLGrammarPool);
        }
        if (str.equalsIgnoreCase("comments") || str.equalsIgnoreCase("datatype-normalization") || str.equalsIgnoreCase("cdata-sections") || str.equalsIgnoreCase("entities") || str.equalsIgnoreCase("split-cdata-sections") || str.equalsIgnoreCase("namespaces") || str.equalsIgnoreCase("validate") || str.equalsIgnoreCase("well-formed") || str.equalsIgnoreCase("infoset") || str.equalsIgnoreCase("namespace-declarations")) {
            return true;
        }
        if (str.equalsIgnoreCase("normalize-characters") || str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("check-character-normalization")) {
            return true ^ obj.equals(Boolean.TRUE);
        }
        if (str.equalsIgnoreCase("element-content-whitespace") || str.equalsIgnoreCase(SEND_PSVI)) {
            return obj.equals(Boolean.TRUE);
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings
    public PropertyState checkProperty(String str) throws XMLConfigurationException {
        return (str.startsWith(Constants.SAX_PROPERTY_PREFIX) && str.length() + (-30) == 10 && str.endsWith(Constants.XML_STRING_PROPERTY)) ? PropertyState.NOT_SUPPORTED : super.checkProperty(str);
    }

    public ValidationManager createValidationManager() {
        return new ValidationManager();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public XMLDTDHandler getDTDHandler() {
        return null;
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

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public boolean getFeature(String str) throws XMLConfigurationException {
        if (str.equals("http://apache.org/xml/features/internal/parser-settings")) {
            return true;
        }
        return super.getFeature(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public Locale getLocale() {
        return this.fLocale;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public Object getParameter(String str) throws DOMException {
        if (str.equalsIgnoreCase("comments")) {
            return (this.features & 32) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("namespaces")) {
            return (this.features & 1) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("datatype-normalization")) {
            return (this.features & 2) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("cdata-sections")) {
            return (this.features & 8) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("entities")) {
            return (this.features & 4) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("split-cdata-sections")) {
            return (this.features & 16) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("validate")) {
            return (this.features & 64) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("well-formed")) {
            return (this.features & 256) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("namespace-declarations")) {
            return (this.features & 512) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("infoset")) {
            return (this.features & INFOSET_MASK) == 801 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("normalize-characters") || str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("check-character-normalization")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase(SEND_PSVI)) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase(Constants.DOM_PSVI)) {
            return (this.features & 128) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("element-content-whitespace")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("error-handler")) {
            return this.fErrorHandlerWrapper.getErrorHandler();
        }
        if (str.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
            XMLEntityResolver entityResolver = getEntityResolver();
            if (entityResolver == null || !(entityResolver instanceof DOMEntityResolverWrapper)) {
                return null;
            }
            return ((DOMEntityResolverWrapper) entityResolver).getEntityResolver();
        }
        if (str.equalsIgnoreCase("schema-type")) {
            return getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage");
        }
        if (str.equalsIgnoreCase("schema-location")) {
            return this.fSchemaLocation;
        }
        if (str.equalsIgnoreCase("http://apache.org/xml/properties/internal/entity-resolver")) {
            return getEntityResolver();
        }
        if (str.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
            return getProperty("http://apache.org/xml/properties/internal/symbol-table");
        }
        if (str.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool")) {
            return getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        }
        if (str.equalsIgnoreCase("http://apache.org/xml/properties/security-manager")) {
            return getProperty("http://apache.org/xml/properties/security-manager");
        }
        throw newFeatureNotFoundError(str);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("comments");
            arrayList.add("datatype-normalization");
            arrayList.add("cdata-sections");
            arrayList.add("entities");
            arrayList.add("split-cdata-sections");
            arrayList.add("namespaces");
            arrayList.add("validate");
            arrayList.add("infoset");
            arrayList.add("normalize-characters");
            arrayList.add("canonical-form");
            arrayList.add("validate-if-schema");
            arrayList.add("check-character-normalization");
            arrayList.add("well-formed");
            arrayList.add("namespace-declarations");
            arrayList.add("element-content-whitespace");
            arrayList.add("error-handler");
            arrayList.add("schema-type");
            arrayList.add("schema-location");
            arrayList.add(Constants.DOM_RESOURCE_RESOLVER);
            arrayList.add("http://apache.org/xml/properties/internal/entity-resolver");
            arrayList.add("http://apache.org/xml/properties/internal/grammar-pool");
            arrayList.add("http://apache.org/xml/properties/security-manager");
            arrayList.add("http://apache.org/xml/properties/internal/symbol-table");
            arrayList.add(SEND_PSVI);
            this.fRecognizedParameters = new DOMStringListImpl(arrayList);
        }
        return this.fRecognizedParameters;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void parse(XMLInputSource xMLInputSource) throws IOException, XNIException {
    }

    public void reset() throws XNIException {
        ValidationManager validationManager = this.fValidationManager;
        if (validationManager != null) {
            validationManager.reset();
        }
        int size = this.fComponents.size();
        for (int i = 0; i < size; i++) {
            this.fComponents.get(i).reset(this);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler) {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDTDHandler(XMLDTDHandler xMLDTDHandler) {
    }

    public final void setDTDValidatorFactory(String str) {
        boolean zEquals = SerializerConstants.XMLVERSION11.equals(str);
        DTDDVFactory dTDDVFactory = this.fCurrentDVFactory;
        if (zEquals) {
            DTDDVFactory dTDDVFactory2 = this.fXML11DatatypeFactory;
            if (dTDDVFactory != dTDDVFactory2) {
                this.fCurrentDVFactory = dTDDVFactory2;
                setProperty(DTD_VALIDATOR_FACTORY_PROPERTY, dTDDVFactory2);
                return;
            }
            return;
        }
        DTDDVFactory dTDDVFactory3 = this.fDatatypeValidatorFactory;
        if (dTDDVFactory != dTDDVFactory3) {
            this.fCurrentDVFactory = dTDDVFactory3;
            setProperty(DTD_VALIDATOR_FACTORY_PROPERTY, dTDDVFactory3);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fDocumentHandler = xMLDocumentHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setEntityResolver(XMLEntityResolver xMLEntityResolver) {
        this.fProperties.put("http://apache.org/xml/properties/internal/entity-resolver", xMLEntityResolver);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setErrorHandler(XMLErrorHandler xMLErrorHandler) {
        if (xMLErrorHandler != null) {
            this.fProperties.put("http://apache.org/xml/properties/internal/error-handler", xMLErrorHandler);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        super.setFeature(str, z);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setLocale(Locale locale) throws XNIException {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    /* JADX WARN: Code duplicated, block: B:198:0x0282 A[RETURN] */
    @Override // org.w3c.dom.DOMConfiguration
    public void setParameter(String str, Object obj) throws DOMException {
        boolean z = obj instanceof Boolean;
        if (z) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            if (str.equalsIgnoreCase("comments")) {
                short s = this.features;
                this.features = (short) (zBooleanValue ? s | 32 : s & (-33));
            } else if (str.equalsIgnoreCase("datatype-normalization")) {
                setFeature(NORMALIZE_DATA, zBooleanValue);
                short s2 = this.features;
                short s3 = (short) (zBooleanValue ? s2 | 2 : s2 & (-3));
                this.features = s3;
                if (zBooleanValue) {
                    this.features = (short) (s3 | 64);
                }
            } else if (str.equalsIgnoreCase("namespaces")) {
                short s4 = this.features;
                this.features = (short) (zBooleanValue ? s4 | 1 : s4 & (-2));
            } else if (str.equalsIgnoreCase("cdata-sections")) {
                short s5 = this.features;
                this.features = (short) (zBooleanValue ? s5 | 8 : s5 & (-9));
            } else if (str.equalsIgnoreCase("entities")) {
                short s6 = this.features;
                this.features = (short) (zBooleanValue ? s6 | 4 : s6 & (-5));
            } else if (str.equalsIgnoreCase("split-cdata-sections")) {
                short s7 = this.features;
                this.features = (short) (zBooleanValue ? s7 | 16 : s7 & (-17));
            } else if (str.equalsIgnoreCase("validate")) {
                short s8 = this.features;
                this.features = (short) (zBooleanValue ? s8 | 64 : s8 & (-65));
            } else if (str.equalsIgnoreCase("well-formed")) {
                short s9 = this.features;
                this.features = (short) (zBooleanValue ? s9 | 256 : s9 & (-257));
            } else if (str.equalsIgnoreCase("namespace-declarations")) {
                short s10 = this.features;
                this.features = (short) (zBooleanValue ? s10 | 512 : s10 & (-513));
            } else if (str.equalsIgnoreCase("infoset")) {
                if (zBooleanValue) {
                    this.features = (short) (((short) (this.features | INFOSET_TRUE_PARAMS)) & (-15));
                    setFeature(NORMALIZE_DATA, false);
                }
            } else if (str.equalsIgnoreCase("normalize-characters") || str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("check-character-normalization")) {
                if (zBooleanValue) {
                    throw newFeatureNotSupportedError(str);
                }
            } else if (str.equalsIgnoreCase("element-content-whitespace")) {
                if (!zBooleanValue) {
                    throw newFeatureNotSupportedError(str);
                }
            } else if (str.equalsIgnoreCase(SEND_PSVI)) {
                if (!zBooleanValue) {
                    throw newFeatureNotSupportedError(str);
                }
            } else if (str.equalsIgnoreCase(Constants.DOM_PSVI)) {
                short s11 = this.features;
                this.features = (short) (zBooleanValue ? s11 | 128 : s11 & (-129));
            }
            if (z) {
                return;
            }
        } else if (z) {
            return;
        }
        if (str.equalsIgnoreCase("error-handler")) {
            if (!(obj instanceof DOMErrorHandler) && obj != null) {
                throw newTypeMismatchError(str);
            }
            this.fErrorHandlerWrapper.setErrorHandler((DOMErrorHandler) obj);
            setErrorHandler(this.fErrorHandlerWrapper);
            return;
        }
        try {
            if (str.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER)) {
                if (!(obj instanceof LSResourceResolver) && obj != null) {
                    throw newTypeMismatchError(str);
                }
                setEntityResolver(new DOMEntityResolverWrapper((LSResourceResolver) obj));
                return;
            }
            if (str.equalsIgnoreCase("schema-location")) {
                if (!(obj instanceof String) && obj != null) {
                    throw newTypeMismatchError(str);
                }
                if (obj == null) {
                    this.fSchemaLocation = null;
                    setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", null);
                    return;
                }
                this.fSchemaLocation = (String) obj;
                StringTokenizer stringTokenizer = new StringTokenizer(this.fSchemaLocation, " \n\t\r");
                if (!stringTokenizer.hasMoreTokens()) {
                    setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", new String[]{(String) obj});
                    return;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(stringTokenizer.nextToken());
                while (stringTokenizer.hasMoreTokens()) {
                    arrayList.add(stringTokenizer.nextToken());
                }
                setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", arrayList.toArray(new String[arrayList.size()]));
                return;
            }
            if (str.equalsIgnoreCase("schema-type")) {
                if (!(obj instanceof String) && obj != null) {
                    throw newTypeMismatchError(str);
                }
                if (obj == null) {
                    setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", null);
                    return;
                }
                String str2 = Constants.NS_XMLSCHEMA;
                if (obj.equals(str2)) {
                    setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", str2);
                    return;
                }
                String str3 = Constants.NS_DTD;
                if (obj.equals(str3)) {
                    setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", str3);
                    return;
                }
                return;
            }
            if (str.equalsIgnoreCase("http://apache.org/xml/properties/internal/entity-resolver")) {
                if (!(obj instanceof XMLEntityResolver) && obj != null) {
                    throw newTypeMismatchError(str);
                }
                setEntityResolver((XMLEntityResolver) obj);
                return;
            }
            if (str.equalsIgnoreCase("http://apache.org/xml/properties/internal/symbol-table")) {
                if (!(obj instanceof SymbolTable)) {
                    throw newTypeMismatchError(str);
                }
                setProperty("http://apache.org/xml/properties/internal/symbol-table", obj);
            } else {
                if (!str.equalsIgnoreCase("http://apache.org/xml/properties/internal/grammar-pool")) {
                    throw newFeatureNotFoundError(str);
                }
                if (!(obj instanceof XMLGrammarPool) && obj != null) {
                    throw newTypeMismatchError(str);
                }
                setProperty("http://apache.org/xml/properties/internal/grammar-pool", obj);
            }
        } catch (XMLConfigurationException unused) {
        }
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        super.setProperty(str, obj);
    }

    public DOMConfigurationImpl(SymbolTable symbolTable) {
        this(symbolTable, null);
    }

    public DOMConfigurationImpl() {
        this(null, null);
    }
}
