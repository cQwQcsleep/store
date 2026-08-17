package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.util.DOMEntityResolverWrapper;
import com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper;
import com.sun.org.apache.xerces.internal.util.FeatureState;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings;
import com.sun.org.apache.xerces.internal.util.PropertyState;
import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.xml.catalog.CatalogFeatures;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class XMLSchemaValidatorComponentManager extends ParserConfigurationSettings implements XMLComponentManager {
    private static final String DISALLOW_DOCTYPE_DECL_FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
    private static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    private static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String IDENTITY_CONSTRAINT_CHECKING = "http://apache.org/xml/features/validation/identity-constraint-checking";
    protected static final String ID_IDREF_CHECKING = "http://apache.org/xml/features/validation/id-idref-checking";
    protected static final String IGNORE_XSI_TYPE = "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl";
    private static final String LOCALE = "http://apache.org/xml/properties/locale";
    private static final String NAMESPACE_CONTEXT = "http://apache.org/xml/properties/internal/namespace-context";
    private static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    private static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    private static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    private static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String UNPARSED_ENTITY_CHECKING = "http://apache.org/xml/features/validation/unparsed-entity-checking";
    private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private boolean _isSecureMode;
    private final HashMap<String, Object> fComponents;
    private boolean fConfigUpdated = true;
    private XMLEntityManager fEntityManager;
    private ErrorHandler fErrorHandler;
    private XMLErrorReporter fErrorReporter;
    private final HashMap<String, Boolean> fInitFeatures;
    private final HashMap<String, Object> fInitProperties;
    private XMLSecurityManager fInitSecurityManager;
    private Locale fLocale;
    private NamespaceContext fNamespaceContext;
    private LSResourceResolver fResourceResolver;
    private XMLSchemaValidator fSchemaValidator;
    private final XMLSecurityPropertyManager fSecurityPropertyMgr;
    private boolean fUseGrammarPoolOnly;
    private ValidationManager fValidationManager;

    public XMLSchemaValidatorComponentManager(XSGrammarPoolContainer xSGrammarPoolContainer) {
        this._isSecureMode = false;
        boolean z = true;
        HashMap<String, Object> map = new HashMap<>();
        this.fComponents = map;
        this.fInitFeatures = new HashMap<>();
        this.fInitProperties = new HashMap<>();
        this.fErrorHandler = null;
        this.fResourceResolver = null;
        this.fLocale = null;
        XMLEntityManager xMLEntityManager = new XMLEntityManager();
        this.fEntityManager = xMLEntityManager;
        map.put(ENTITY_MANAGER, xMLEntityManager);
        XMLErrorReporter xMLErrorReporter = new XMLErrorReporter();
        this.fErrorReporter = xMLErrorReporter;
        map.put("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter);
        NamespaceSupport namespaceSupport = new NamespaceSupport();
        this.fNamespaceContext = namespaceSupport;
        map.put(NAMESPACE_CONTEXT, namespaceSupport);
        XMLSchemaValidator xMLSchemaValidator = new XMLSchemaValidator();
        this.fSchemaValidator = xMLSchemaValidator;
        map.put(SCHEMA_VALIDATOR, xMLSchemaValidator);
        ValidationManager validationManager = new ValidationManager();
        this.fValidationManager = validationManager;
        map.put(VALIDATION_MANAGER, validationManager);
        map.put("http://apache.org/xml/properties/internal/entity-resolver", null);
        map.put("http://apache.org/xml/properties/internal/error-handler", null);
        map.put("http://apache.org/xml/properties/internal/symbol-table", new SymbolTable());
        map.put("http://apache.org/xml/properties/internal/grammar-pool", xSGrammarPoolContainer.getGrammarPool());
        this.fUseGrammarPoolOnly = xSGrammarPoolContainer.isFullyComposed();
        this.fErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
        addRecognizedFeatures(new String[]{DISALLOW_DOCTYPE_DECL_FEATURE, NORMALIZE_DATA, SCHEMA_ELEMENT_DEFAULT, SCHEMA_AUGMENT_PSVI, "http://javax.xml.XMLConstants/feature/useCatalog", JdkConstants.OVERRIDE_PARSER});
        Map<String, Boolean> map2 = this.fFeatures;
        Boolean bool = Boolean.FALSE;
        map2.put(DISALLOW_DOCTYPE_DECL_FEATURE, bool);
        this.fFeatures.put(NORMALIZE_DATA, bool);
        this.fFeatures.put(SCHEMA_ELEMENT_DEFAULT, bool);
        Map<String, Boolean> map3 = this.fFeatures;
        Boolean bool2 = Boolean.TRUE;
        map3.put(SCHEMA_AUGMENT_PSVI, bool2);
        this.fFeatures.put("http://javax.xml.XMLConstants/feature/useCatalog", xSGrammarPoolContainer.getFeature("http://javax.xml.XMLConstants/feature/useCatalog"));
        this.fFeatures.put(JdkConstants.OVERRIDE_PARSER, xSGrammarPoolContainer.getFeature(JdkConstants.OVERRIDE_PARSER));
        addRecognizedParamsAndSetDefaults(this.fEntityManager, xSGrammarPoolContainer);
        addRecognizedParamsAndSetDefaults(this.fErrorReporter, xSGrammarPoolContainer);
        addRecognizedParamsAndSetDefaults(this.fSchemaValidator, xSGrammarPoolContainer);
        this.fFeatures.put(IGNORE_XSI_TYPE, bool);
        this.fFeatures.put(ID_IDREF_CHECKING, bool2);
        this.fFeatures.put(IDENTITY_CONSTRAINT_CHECKING, bool2);
        this.fFeatures.put(UNPARSED_ENTITY_CHECKING, bool2);
        boolean zBooleanValue = xSGrammarPoolContainer.getFeature("http://javax.xml.XMLConstants/feature/secure-processing").booleanValue();
        if (System.getSecurityManager() != null) {
            this._isSecureMode = true;
        } else {
            z = zBooleanValue;
        }
        XMLSecurityManager xMLSecurityManager = (XMLSecurityManager) xSGrammarPoolContainer.getProperty("http://apache.org/xml/properties/security-manager");
        this.fInitSecurityManager = xMLSecurityManager;
        if (xMLSecurityManager != null) {
            xMLSecurityManager.setSecureProcessing(z);
        } else {
            this.fInitSecurityManager = new XMLSecurityManager(z);
        }
        setProperty("http://apache.org/xml/properties/security-manager", this.fInitSecurityManager);
        XMLSecurityPropertyManager xMLSecurityPropertyManager = (XMLSecurityPropertyManager) xSGrammarPoolContainer.getProperty("jdk.xml.xmlSecurityPropertyManager");
        this.fSecurityPropertyMgr = xMLSecurityPropertyManager;
        setProperty("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager);
        for (CatalogFeatures.Feature feature : CatalogFeatures.Feature.values()) {
            setProperty(feature.getPropertyName(), xSGrammarPoolContainer.getProperty(feature.getPropertyName()));
        }
        setProperty(JdkConstants.CDATA_CHUNK_SIZE, xSGrammarPoolContainer.getProperty(JdkConstants.CDATA_CHUNK_SIZE));
    }

    private void setFeatureDefaults(XMLComponent xMLComponent, String[] strArr, XSGrammarPoolContainer xSGrammarPoolContainer) {
        if (strArr != null) {
            for (String str : strArr) {
                Boolean feature = xSGrammarPoolContainer.getFeature(str);
                if (feature == null) {
                    feature = xMLComponent.getFeatureDefault(str);
                }
                if (feature != null && !this.fFeatures.containsKey(str)) {
                    this.fFeatures.put(str, feature);
                    this.fConfigUpdated = true;
                }
            }
        }
    }

    private void setPropertyDefaults(XMLComponent xMLComponent, String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                Object propertyDefault = xMLComponent.getPropertyDefault(str);
                if (propertyDefault != null && !this.fProperties.containsKey(str)) {
                    this.fProperties.put(str, propertyDefault);
                    this.fConfigUpdated = true;
                }
            }
        }
    }

    public void addRecognizedParamsAndSetDefaults(XMLComponent xMLComponent, XSGrammarPoolContainer xSGrammarPoolContainer) {
        String[] recognizedFeatures = xMLComponent.getRecognizedFeatures();
        addRecognizedFeatures(recognizedFeatures);
        String[] recognizedProperties = xMLComponent.getRecognizedProperties();
        addRecognizedProperties(recognizedProperties);
        setFeatureDefaults(xMLComponent, recognizedFeatures, xSGrammarPoolContainer);
        setPropertyDefaults(xMLComponent, recognizedProperties);
    }

    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public FeatureState getFeatureState(String str) throws XMLConfigurationException {
        if ("http://apache.org/xml/features/internal/parser-settings".equals(str)) {
            return FeatureState.is(this.fConfigUpdated);
        }
        if (VALIDATION.equals(str) || SCHEMA_VALIDATION.equals(str)) {
            return FeatureState.is(true);
        }
        if (USE_GRAMMAR_POOL_ONLY.equals(str)) {
            return FeatureState.is(this.fUseGrammarPoolOnly);
        }
        if ("http://javax.xml.XMLConstants/feature/secure-processing".equals(str)) {
            return FeatureState.is(this.fInitSecurityManager.isSecureProcessing());
        }
        return SCHEMA_ELEMENT_DEFAULT.equals(str) ? FeatureState.is(true) : super.getFeatureState(str);
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public PropertyState getPropertyState(String str) throws XMLConfigurationException {
        if ("http://apache.org/xml/properties/locale".equals(str)) {
            return PropertyState.is(getLocale());
        }
        Object obj = this.fComponents.get(str);
        if (obj != null) {
            return PropertyState.is(obj);
        }
        return this.fComponents.containsKey(str) ? PropertyState.is(null) : super.getPropertyState(str);
    }

    public LSResourceResolver getResourceResolver() {
        return this.fResourceResolver;
    }

    public void reset() throws XNIException {
        this.fNamespaceContext.reset();
        this.fValidationManager.reset();
        this.fEntityManager.reset(this);
        this.fErrorReporter.reset(this);
        this.fSchemaValidator.reset(this);
        this.fConfigUpdated = false;
    }

    public void restoreInitialState() {
        this.fConfigUpdated = true;
        this.fComponents.put("http://apache.org/xml/properties/internal/entity-resolver", null);
        this.fComponents.put("http://apache.org/xml/properties/internal/error-handler", null);
        setLocale(null);
        this.fComponents.put("http://apache.org/xml/properties/locale", null);
        this.fInitSecurityManager.setSecureProcessing(true);
        this.fComponents.put("http://apache.org/xml/properties/security-manager", this.fInitSecurityManager);
        setLocale(null);
        this.fComponents.put("http://apache.org/xml/properties/locale", null);
        if (!this.fInitFeatures.isEmpty()) {
            for (Map.Entry<String, Boolean> entry : this.fInitFeatures.entrySet()) {
                super.setFeature(entry.getKey(), entry.getValue().booleanValue());
            }
            this.fInitFeatures.clear();
        }
        if (this.fInitProperties.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry2 : this.fInitProperties.entrySet()) {
            super.setProperty(entry2.getKey(), entry2.getValue());
        }
        this.fInitProperties.clear();
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.fErrorHandler = errorHandler;
        setProperty("http://apache.org/xml/properties/internal/error-handler", errorHandler != null ? new ErrorHandlerWrapper(errorHandler) : new ErrorHandlerWrapper(DraconianErrorHandler.getInstance()));
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        if ("http://apache.org/xml/features/internal/parser-settings".equals(str)) {
            throw new XMLConfigurationException(Status.NOT_SUPPORTED, str);
        }
        if (!z && (VALIDATION.equals(str) || SCHEMA_VALIDATION.equals(str))) {
            throw new XMLConfigurationException(Status.NOT_SUPPORTED, str);
        }
        if (USE_GRAMMAR_POOL_ONLY.equals(str) && z != this.fUseGrammarPoolOnly) {
            throw new XMLConfigurationException(Status.NOT_SUPPORTED, str);
        }
        if (!"http://javax.xml.XMLConstants/feature/secure-processing".equals(str)) {
            this.fConfigUpdated = true;
            this.fEntityManager.setFeature(str, z);
            this.fErrorReporter.setFeature(str, z);
            this.fSchemaValidator.setFeature(str, z);
            if (!this.fInitFeatures.containsKey(str)) {
                this.fInitFeatures.put(str, super.getFeature(str) ? Boolean.TRUE : Boolean.FALSE);
            }
            super.setFeature(str, z);
            return;
        }
        if (this._isSecureMode && !z) {
            throw new XMLConfigurationException(Status.NOT_ALLOWED, "http://javax.xml.XMLConstants/feature/secure-processing");
        }
        this.fInitSecurityManager.setSecureProcessing(z);
        setProperty("http://apache.org/xml/properties/security-manager", this.fInitSecurityManager);
        if (z) {
            XMLSecurityPropertyManager xMLSecurityPropertyManager = this.fSecurityPropertyMgr;
            XMLSecurityPropertyManager.Property property = XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD;
            XMLSecurityPropertyManager.State state = XMLSecurityPropertyManager.State.FSP;
            xMLSecurityPropertyManager.setValue(property, state, "");
            this.fSecurityPropertyMgr.setValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_SCHEMA, state, "");
            setProperty("jdk.xml.xmlSecurityPropertyManager", this.fSecurityPropertyMgr);
        }
    }

    public void setLocale(Locale locale) {
        this.fLocale = locale;
        this.fErrorReporter.setLocale(locale);
    }

    @Override // com.sun.org.apache.xerces.internal.util.ParserConfigurationSettings, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        if (ENTITY_MANAGER.equals(str) || "http://apache.org/xml/properties/internal/error-reporter".equals(str) || NAMESPACE_CONTEXT.equals(str) || SCHEMA_VALIDATOR.equals(str) || "http://apache.org/xml/properties/internal/symbol-table".equals(str) || VALIDATION_MANAGER.equals(str) || "http://apache.org/xml/properties/internal/grammar-pool".equals(str)) {
            throw new XMLConfigurationException(Status.NOT_SUPPORTED, str);
        }
        this.fConfigUpdated = true;
        this.fEntityManager.setProperty(str, obj);
        this.fErrorReporter.setProperty(str, obj);
        this.fSchemaValidator.setProperty(str, obj);
        if ("http://apache.org/xml/properties/internal/entity-resolver".equals(str) || "http://apache.org/xml/properties/internal/error-handler".equals(str) || "http://apache.org/xml/properties/security-manager".equals(str)) {
            this.fComponents.put(str, obj);
            return;
        }
        if ("http://apache.org/xml/properties/locale".equals(str)) {
            setLocale((Locale) obj);
            this.fComponents.put(str, obj);
            return;
        }
        XMLSecurityManager xMLSecurityManager = this.fInitSecurityManager;
        if (xMLSecurityManager == null || !xMLSecurityManager.setLimit(str, JdkProperty.State.APIPROPERTY, obj)) {
            XMLSecurityPropertyManager xMLSecurityPropertyManager = this.fSecurityPropertyMgr;
            if (xMLSecurityPropertyManager == null || !xMLSecurityPropertyManager.setValue(str, XMLSecurityPropertyManager.State.APIPROPERTY, obj)) {
                if (!this.fInitProperties.containsKey(str)) {
                    this.fInitProperties.put(str, super.getProperty(str));
                }
                super.setProperty(str, obj);
            }
        }
    }

    public void setResourceResolver(LSResourceResolver lSResourceResolver) {
        this.fResourceResolver = lSResourceResolver;
        setProperty("http://apache.org/xml/properties/internal/entity-resolver", new DOMEntityResolverWrapper(lSResourceResolver));
    }
}
