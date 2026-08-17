package com.sun.org.apache.xerces.internal.jaxp;

import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer;
import com.sun.org.apache.xerces.internal.util.FeatureState;
import com.sun.org.apache.xerces.internal.util.PropertyState;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class SchemaValidatorConfiguration implements XMLComponentManager {
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String VALIDATION = "http://xml.org/sax/features/validation";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private final XMLGrammarPool fGrammarPool;
    private final XMLComponentManager fParentComponentManager;
    private final boolean fUseGrammarPoolOnly;
    private final ValidationManager fValidationManager;

    public SchemaValidatorConfiguration(XMLComponentManager xMLComponentManager, XSGrammarPoolContainer xSGrammarPoolContainer, ValidationManager validationManager) {
        this.fParentComponentManager = xMLComponentManager;
        this.fGrammarPool = xSGrammarPoolContainer.getGrammarPool();
        this.fUseGrammarPoolOnly = xSGrammarPoolContainer.isFullyComposed();
        this.fValidationManager = validationManager;
        try {
            XMLErrorReporter xMLErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
            if (xMLErrorReporter != null) {
                xMLErrorReporter.putMessageFormatter(XSMessageFormatter.SCHEMA_DOMAIN, new XSMessageFormatter());
            }
        } catch (XMLConfigurationException unused) {
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration
    public boolean getFeature(String str) throws XMLConfigurationException {
        FeatureState featureState = getFeatureState(str);
        if (featureState.isExceptional()) {
            throw new XMLConfigurationException(featureState.status, str);
        }
        return featureState.state;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public FeatureState getFeatureState(String str) {
        if (PARSER_SETTINGS.equals(str)) {
            return this.fParentComponentManager.getFeatureState(str);
        }
        if (VALIDATION.equals(str) || SCHEMA_VALIDATION.equals(str)) {
            return FeatureState.is(true);
        }
        return USE_GRAMMAR_POOL_ONLY.equals(str) ? FeatureState.is(this.fUseGrammarPoolOnly) : this.fParentComponentManager.getFeatureState(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public Object getProperty(String str) throws XMLConfigurationException {
        PropertyState propertyState = getPropertyState(str);
        if (propertyState.isExceptional()) {
            throw new XMLConfigurationException(propertyState.status, str);
        }
        return propertyState.state;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public PropertyState getPropertyState(String str) {
        if ("http://apache.org/xml/properties/internal/grammar-pool".equals(str)) {
            return PropertyState.is(this.fGrammarPool);
        }
        return VALIDATION_MANAGER.equals(str) ? PropertyState.is(this.fValidationManager) : this.fParentComponentManager.getPropertyState(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public boolean getFeature(String str, boolean z) {
        FeatureState featureState = getFeatureState(str);
        return featureState.isExceptional() ? z : featureState.state;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager
    public Object getProperty(String str, Object obj) {
        PropertyState propertyState = getPropertyState(str);
        return propertyState.isExceptional() ? obj : propertyState.state;
    }
}
