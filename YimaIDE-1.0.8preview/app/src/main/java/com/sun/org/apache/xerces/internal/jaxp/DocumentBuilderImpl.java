package com.sun.org.apache.xerces.internal.jaxp;

import com.sun.org.apache.xerces.internal.dom.DOMImplementationImpl;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import defpackage.x73;
import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.validation.Schema;
import jdk.xml.internal.JdkProperty;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocumentBuilderImpl extends DocumentBuilder implements JAXPConstants {
    public static final String ACCESS_EXTERNAL_DTD = "http://javax.xml.XMLConstants/property/accessExternalDTD";
    public static final String ACCESS_EXTERNAL_SCHEMA = "http://javax.xml.XMLConstants/property/accessExternalSchema";
    private static final String CREATE_CDATA_NODES_FEATURE = "http://apache.org/xml/features/create-cdata-nodes";
    private static final String CREATE_ENTITY_REF_NODES_FEATURE = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
    private static final String INCLUDE_COMMENTS_FEATURE = "http://apache.org/xml/features/include-comments";
    private static final String INCLUDE_IGNORABLE_WHITESPACE = "http://apache.org/xml/features/dom/include-ignorable-whitespace";
    private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    private static final String XMLSCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private final DOMParser domParser;
    private final EntityResolver fInitEntityResolver;
    private final ErrorHandler fInitErrorHandler;
    private final ValidationManager fSchemaValidationManager;
    private final XMLComponent fSchemaValidator;
    private final XMLComponentManager fSchemaValidatorComponentManager;
    private XMLSecurityManager fSecurityManager;
    private XMLSecurityPropertyManager fSecurityPropertyMgr;
    private final UnparsedEntityHandler fUnparsedEntityHandler;
    private final Schema grammar;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.parsers.DOMParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler] */
    /* JADX WARN: Type inference failed for: r1v15, types: [com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration] */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.sun.org.apache.xerces.internal.jaxp.UnparsedEntityHandler, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler, com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource] */
    /* JADX WARN: Type inference failed for: r5v1, types: [javax.xml.validation.Schema] */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent, com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource] */
    public DocumentBuilderImpl(DocumentBuilderFactoryImpl documentBuilderFactoryImpl, Map<String, Object> map, Map<String, Boolean> map2, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        ?? r8;
        Boolean bool;
        ?? dOMParser = new DOMParser();
        this.domParser = dOMParser;
        if (documentBuilderFactoryImpl.isValidating()) {
            DefaultValidationErrorHandler defaultValidationErrorHandler = new DefaultValidationErrorHandler(dOMParser.getXMLParserConfiguration().getLocale());
            this.fInitErrorHandler = defaultValidationErrorHandler;
            setErrorHandler(defaultValidationErrorHandler);
        } else {
            this.fInitErrorHandler = dOMParser.getErrorHandler();
        }
        dOMParser.setFeature(VALIDATION_FEATURE, documentBuilderFactoryImpl.isValidating());
        dOMParser.setFeature("http://xml.org/sax/features/namespaces", documentBuilderFactoryImpl.isNamespaceAware());
        dOMParser.setFeature(INCLUDE_IGNORABLE_WHITESPACE, !documentBuilderFactoryImpl.isIgnoringElementContentWhitespace());
        dOMParser.setFeature(CREATE_ENTITY_REF_NODES_FEATURE, !documentBuilderFactoryImpl.isExpandEntityReferences());
        dOMParser.setFeature(INCLUDE_COMMENTS_FEATURE, !documentBuilderFactoryImpl.isIgnoringComments());
        dOMParser.setFeature(CREATE_CDATA_NODES_FEATURE, !documentBuilderFactoryImpl.isCoalescing());
        if (documentBuilderFactoryImpl.isXIncludeAware()) {
            dOMParser.setFeature(XINCLUDE_FEATURE, true);
        }
        XMLSecurityPropertyManager xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
        this.fSecurityPropertyMgr = xMLSecurityPropertyManager;
        dOMParser.setProperty("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager);
        XMLSecurityManager xMLSecurityManager = new XMLSecurityManager(z);
        this.fSecurityManager = xMLSecurityManager;
        dOMParser.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
        if (z && map2 != null && (bool = map2.get("http://javax.xml.XMLConstants/feature/secure-processing")) != null && bool.booleanValue()) {
            XMLSecurityPropertyManager xMLSecurityPropertyManager2 = this.fSecurityPropertyMgr;
            XMLSecurityPropertyManager.Property property = XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD;
            XMLSecurityPropertyManager.State state = XMLSecurityPropertyManager.State.FSP;
            xMLSecurityPropertyManager2.setValue(property, state, "");
            this.fSecurityPropertyMgr.setValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_SCHEMA, state, "");
        }
        ?? schema = documentBuilderFactoryImpl.getSchema();
        this.grammar = schema;
        if (schema != 0) {
            ?? xMLParserConfiguration = dOMParser.getXMLParserConfiguration();
            if (schema instanceof XSGrammarPoolContainer) {
                XMLSchemaValidator xMLSchemaValidator = new XMLSchemaValidator();
                ValidationManager validationManager = new ValidationManager();
                this.fSchemaValidationManager = validationManager;
                ?? unparsedEntityHandler = new UnparsedEntityHandler(validationManager);
                this.fUnparsedEntityHandler = unparsedEntityHandler;
                xMLParserConfiguration.setDTDHandler(unparsedEntityHandler);
                unparsedEntityHandler.setDTDHandler(dOMParser);
                dOMParser.setDTDSource(unparsedEntityHandler);
                this.fSchemaValidatorComponentManager = new SchemaValidatorConfiguration(xMLParserConfiguration, (XSGrammarPoolContainer) schema, validationManager);
                r8 = xMLSchemaValidator;
            } else {
                JAXPValidatorComponent jAXPValidatorComponent = new JAXPValidatorComponent(schema.newValidatorHandler());
                this.fSchemaValidationManager = null;
                this.fUnparsedEntityHandler = null;
                this.fSchemaValidatorComponentManager = xMLParserConfiguration;
                r8 = jAXPValidatorComponent;
            }
            xMLParserConfiguration.addRecognizedFeatures(r8.getRecognizedFeatures());
            xMLParserConfiguration.addRecognizedProperties(r8.getRecognizedProperties());
            setFeatures(map2);
            xMLParserConfiguration.setDocumentHandler(r8);
            r8.setDocumentHandler(dOMParser);
            dOMParser.setDocumentSource(r8);
            this.fSchemaValidator = r8;
        } else {
            this.fSchemaValidationManager = null;
            this.fUnparsedEntityHandler = null;
            this.fSchemaValidatorComponentManager = null;
            this.fSchemaValidator = null;
            setFeatures(map2);
        }
        setDocumentBuilderFactoryAttributes(map);
        this.fInitEntityResolver = dOMParser.getEntityResolver();
    }

    private void resetSchemaValidator() throws SAXException {
        try {
            this.fSchemaValidator.reset(this.fSchemaValidatorComponentManager);
        } catch (XMLConfigurationException e) {
            x73.a(e);
        }
    }

    private void setDocumentBuilderFactoryAttributes(Map<String, Object> map) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Boolean) {
                this.domParser.setFeature(key, ((Boolean) value).booleanValue());
            } else if (JAXPConstants.JAXP_SCHEMA_LANGUAGE.equals(key)) {
                if ("http://www.w3.org/2001/XMLSchema".equals(value) && isValidating()) {
                    this.domParser.setFeature(XMLSCHEMA_VALIDATION_FEATURE, true);
                    this.domParser.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
                }
            } else if (!JAXPConstants.JAXP_SCHEMA_SOURCE.equals(key)) {
                XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
                if (xMLSecurityManager == null || !xMLSecurityManager.setLimit(key, JdkProperty.State.APIPROPERTY, value)) {
                    XMLSecurityPropertyManager xMLSecurityPropertyManager = this.fSecurityPropertyMgr;
                    if (xMLSecurityPropertyManager == null || !xMLSecurityPropertyManager.setValue(key, XMLSecurityPropertyManager.State.APIPROPERTY, value)) {
                        this.domParser.setProperty(key, value);
                    }
                }
            } else if (isValidating()) {
                String str = (String) map.get(JAXPConstants.JAXP_SCHEMA_LANGUAGE);
                if (str == null || !"http://www.w3.org/2001/XMLSchema".equals(str)) {
                    w01.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "jaxp-order-not-supported", new Object[]{JAXPConstants.JAXP_SCHEMA_LANGUAGE, JAXPConstants.JAXP_SCHEMA_SOURCE}));
                    return;
                }
                this.domParser.setProperty(key, value);
            } else {
                continue;
            }
        }
    }

    private void setFeatures(Map<String, Boolean> map) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (map != null) {
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                this.domParser.setFeature(entry.getKey(), entry.getValue().booleanValue());
            }
        }
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public DOMImplementation getDOMImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }

    public DOMParser getDOMParser() {
        return this.domParser;
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public Schema getSchema() {
        return this.grammar;
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public boolean isNamespaceAware() {
        try {
            return this.domParser.getFeature("http://xml.org/sax/features/namespaces");
        } catch (SAXException e) {
            k2d.a(e.getMessage());
            return false;
        }
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public boolean isValidating() {
        try {
            return this.domParser.getFeature(VALIDATION_FEATURE);
        } catch (SAXException e) {
            k2d.a(e.getMessage());
            return false;
        }
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public boolean isXIncludeAware() {
        try {
            return this.domParser.getFeature(XINCLUDE_FEATURE);
        } catch (SAXException unused) {
            return false;
        }
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public Document newDocument() {
        return new DocumentImpl();
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public Document parse(InputSource inputSource) throws SAXException, IOException {
        if (inputSource == null) {
            w01.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "jaxp-null-input-source", null));
            return null;
        }
        if (this.fSchemaValidator != null) {
            ValidationManager validationManager = this.fSchemaValidationManager;
            if (validationManager != null) {
                validationManager.reset();
                this.fUnparsedEntityHandler.reset();
            }
            resetSchemaValidator();
        }
        this.domParser.parse(inputSource);
        Document document = this.domParser.getDocument();
        this.domParser.dropDocumentReferences();
        return document;
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public void reset() {
        ErrorHandler errorHandler = this.domParser.getErrorHandler();
        ErrorHandler errorHandler2 = this.fInitErrorHandler;
        if (errorHandler != errorHandler2) {
            this.domParser.setErrorHandler(errorHandler2);
        }
        EntityResolver entityResolver = this.domParser.getEntityResolver();
        EntityResolver entityResolver2 = this.fInitEntityResolver;
        if (entityResolver != entityResolver2) {
            this.domParser.setEntityResolver(entityResolver2);
        }
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public void setEntityResolver(EntityResolver entityResolver) {
        this.domParser.setEntityResolver(entityResolver);
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.domParser.setErrorHandler(errorHandler);
    }

    public DocumentBuilderImpl(DocumentBuilderFactoryImpl documentBuilderFactoryImpl, Map<String, Object> map, Map<String, Boolean> map2) throws SAXNotRecognizedException, SAXNotSupportedException {
        this(documentBuilderFactoryImpl, map, map2, false);
    }
}
