package com.sun.org.apache.xerces.internal.jaxp;

import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import com.sun.org.apache.xerces.internal.jaxp.validation.XSGrammarPoolContainer;
import com.sun.org.apache.xerces.internal.util.SAXMessageFormatter;
import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import com.sun.org.apache.xerces.internal.xs.PSVIProvider;
import defpackage.x73;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.validation.Schema;
import jdk.xml.internal.JdkProperty;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAXParserImpl extends SAXParser implements JAXPConstants, PSVIProvider {
    private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    private static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    private static final String XINCLUDE_FEATURE = "http://apache.org/xml/features/xinclude";
    private static final String XMLSCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private final EntityResolver fInitEntityResolver;
    private final ErrorHandler fInitErrorHandler;
    private final ValidationManager fSchemaValidationManager;
    private final XMLComponent fSchemaValidator;
    private final XMLComponentManager fSchemaValidatorComponentManager;
    private final XMLSecurityManager fSecurityManager;
    private final XMLSecurityPropertyManager fSecurityPropertyMgr;
    private final UnparsedEntityHandler fUnparsedEntityHandler;
    private final Schema grammar;
    private String schemaLanguage;
    private final JAXPSAXParser xmlReader;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v4, types: [com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent, com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.sun.org.apache.xerces.internal.jaxp.UnparsedEntityHandler, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler, com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.sun.org.apache.xerces.internal.jaxp.SAXParserImpl$JAXPSAXParser, com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser, com.sun.org.apache.xerces.internal.parsers.AbstractXMLDocumentParser, com.sun.org.apache.xerces.internal.xni.XMLDTDHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler] */
    /* JADX WARN: Type inference failed for: r8v1, types: [javax.xml.validation.Schema] */
    /* JADX WARN: Type inference failed for: r9v4, types: [com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager, com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration] */
    public SAXParserImpl(SAXParserFactoryImpl sAXParserFactoryImpl, Map<String, Boolean> map, boolean z) throws SAXException {
        ?? r10;
        Boolean bool;
        this.schemaLanguage = null;
        XMLSecurityManager xMLSecurityManager = new XMLSecurityManager(z);
        this.fSecurityManager = xMLSecurityManager;
        XMLSecurityPropertyManager xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
        this.fSecurityPropertyMgr = xMLSecurityPropertyManager;
        ?? jAXPSAXParser = new JAXPSAXParser(this, xMLSecurityPropertyManager, xMLSecurityManager);
        this.xmlReader = jAXPSAXParser;
        jAXPSAXParser.setFeature0("http://xml.org/sax/features/namespaces", sAXParserFactoryImpl.isNamespaceAware());
        jAXPSAXParser.setFeature0("http://xml.org/sax/features/namespace-prefixes", !sAXParserFactoryImpl.isNamespaceAware());
        if (sAXParserFactoryImpl.isXIncludeAware()) {
            jAXPSAXParser.setFeature0(XINCLUDE_FEATURE, true);
        }
        jAXPSAXParser.setProperty0("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager);
        jAXPSAXParser.setProperty0("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
        if (z && map != null && (bool = map.get("http://javax.xml.XMLConstants/feature/secure-processing")) != null && bool.booleanValue()) {
            XMLSecurityPropertyManager.Property property = XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD;
            XMLSecurityPropertyManager.State state = XMLSecurityPropertyManager.State.FSP;
            xMLSecurityPropertyManager.setValue(property, state, "");
            xMLSecurityPropertyManager.setValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_SCHEMA, state, "");
        }
        setFeatures(map);
        if (sAXParserFactoryImpl.isValidating()) {
            DefaultValidationErrorHandler defaultValidationErrorHandler = new DefaultValidationErrorHandler(jAXPSAXParser.getLocale());
            this.fInitErrorHandler = defaultValidationErrorHandler;
            jAXPSAXParser.setErrorHandler(defaultValidationErrorHandler);
        } else {
            this.fInitErrorHandler = jAXPSAXParser.getErrorHandler();
        }
        jAXPSAXParser.setFeature0(VALIDATION_FEATURE, sAXParserFactoryImpl.isValidating());
        ?? schema = sAXParserFactoryImpl.getSchema();
        this.grammar = schema;
        if (schema != 0) {
            ?? xMLParserConfiguration = jAXPSAXParser.getXMLParserConfiguration();
            if (schema instanceof XSGrammarPoolContainer) {
                XMLSchemaValidator xMLSchemaValidator = new XMLSchemaValidator();
                ValidationManager validationManager = new ValidationManager();
                this.fSchemaValidationManager = validationManager;
                ?? unparsedEntityHandler = new UnparsedEntityHandler(validationManager);
                this.fUnparsedEntityHandler = unparsedEntityHandler;
                xMLParserConfiguration.setDTDHandler(unparsedEntityHandler);
                unparsedEntityHandler.setDTDHandler(jAXPSAXParser);
                jAXPSAXParser.setDTDSource(unparsedEntityHandler);
                this.fSchemaValidatorComponentManager = new SchemaValidatorConfiguration(xMLParserConfiguration, (XSGrammarPoolContainer) schema, validationManager);
                r10 = xMLSchemaValidator;
            } else {
                JAXPValidatorComponent jAXPValidatorComponent = new JAXPValidatorComponent(schema.newValidatorHandler());
                this.fSchemaValidationManager = null;
                this.fUnparsedEntityHandler = null;
                this.fSchemaValidatorComponentManager = xMLParserConfiguration;
                r10 = jAXPValidatorComponent;
            }
            xMLParserConfiguration.addRecognizedFeatures(r10.getRecognizedFeatures());
            xMLParserConfiguration.addRecognizedProperties(r10.getRecognizedProperties());
            xMLParserConfiguration.setDocumentHandler(r10);
            r10.setDocumentHandler(jAXPSAXParser);
            jAXPSAXParser.setDocumentSource(r10);
            this.fSchemaValidator = r10;
        } else {
            this.fSchemaValidationManager = null;
            this.fUnparsedEntityHandler = null;
            this.fSchemaValidatorComponentManager = null;
            this.fSchemaValidator = null;
        }
        this.fInitEntityResolver = jAXPSAXParser.getEntityResolver();
    }

    private void setFeatures(Map<String, Boolean> map) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (map != null) {
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                this.xmlReader.setFeature0(entry.getKey(), entry.getValue().booleanValue());
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xs.PSVIProvider
    public AttributePSVI getAttributePSVI(int i) {
        return this.xmlReader.getAttributePSVI(i);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.PSVIProvider
    public AttributePSVI getAttributePSVIByName(String str, String str2) {
        return this.xmlReader.getAttributePSVIByName(str, str2);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.PSVIProvider
    public ElementPSVI getElementPSVI() {
        return this.xmlReader.getElementPSVI();
    }

    @Override // javax.xml.parsers.SAXParser
    public Parser getParser() throws SAXException {
        return this.xmlReader;
    }

    @Override // javax.xml.parsers.SAXParser
    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.xmlReader.getProperty(str);
    }

    @Override // javax.xml.parsers.SAXParser
    public Schema getSchema() {
        return this.grammar;
    }

    @Override // javax.xml.parsers.SAXParser
    public XMLReader getXMLReader() {
        return this.xmlReader;
    }

    @Override // javax.xml.parsers.SAXParser
    public boolean isNamespaceAware() {
        try {
            return this.xmlReader.getFeature("http://xml.org/sax/features/namespaces");
        } catch (SAXException e) {
            k2d.a(e.getMessage());
            return false;
        }
    }

    @Override // javax.xml.parsers.SAXParser
    public boolean isValidating() {
        try {
            return this.xmlReader.getFeature(VALIDATION_FEATURE);
        } catch (SAXException e) {
            k2d.a(e.getMessage());
            return false;
        }
    }

    @Override // javax.xml.parsers.SAXParser
    public boolean isXIncludeAware() {
        try {
            return this.xmlReader.getFeature(XINCLUDE_FEATURE);
        } catch (SAXException unused) {
            return false;
        }
    }

    @Override // javax.xml.parsers.SAXParser
    public void parse(InputSource inputSource, DefaultHandler defaultHandler) throws SAXException, IOException {
        if (inputSource == null) {
            j2d.a();
            return;
        }
        if (defaultHandler != null) {
            this.xmlReader.setContentHandler(defaultHandler);
            this.xmlReader.setEntityResolver(defaultHandler);
            this.xmlReader.setErrorHandler(defaultHandler);
            this.xmlReader.setDTDHandler(defaultHandler);
            this.xmlReader.setDocumentHandler(null);
        }
        this.xmlReader.parse(inputSource);
    }

    @Override // javax.xml.parsers.SAXParser
    public void reset() {
        try {
            this.xmlReader.restoreInitState();
        } catch (SAXException unused) {
        }
        this.xmlReader.setContentHandler(null);
        this.xmlReader.setDTDHandler(null);
        ErrorHandler errorHandler = this.xmlReader.getErrorHandler();
        ErrorHandler errorHandler2 = this.fInitErrorHandler;
        if (errorHandler != errorHandler2) {
            this.xmlReader.setErrorHandler(errorHandler2);
        }
        EntityResolver entityResolver = this.xmlReader.getEntityResolver();
        EntityResolver entityResolver2 = this.fInitEntityResolver;
        if (entityResolver != entityResolver2) {
            this.xmlReader.setEntityResolver(entityResolver2);
        }
    }

    @Override // javax.xml.parsers.SAXParser
    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.xmlReader.setProperty(str, obj);
    }

    @Override // javax.xml.parsers.SAXParser
    public void parse(InputSource inputSource, HandlerBase handlerBase) throws SAXException, IOException {
        if (inputSource != null) {
            if (handlerBase != null) {
                this.xmlReader.setDocumentHandler(handlerBase);
                this.xmlReader.setEntityResolver(handlerBase);
                this.xmlReader.setErrorHandler(handlerBase);
                this.xmlReader.setDTDHandler(handlerBase);
                this.xmlReader.setContentHandler(null);
            }
            this.xmlReader.parse(inputSource);
            return;
        }
        j2d.a();
    }

    public static class JAXPSAXParser extends com.sun.org.apache.xerces.internal.parsers.SAXParser {
        private final Map<String, Boolean> fInitFeatures;
        private final Map<String, Object> fInitProperties;
        private final SAXParserImpl fSAXParser;
        private XMLSecurityManager fSecurityManager;
        private XMLSecurityPropertyManager fSecurityPropertyMgr;

        public JAXPSAXParser(SAXParserImpl sAXParserImpl, XMLSecurityPropertyManager xMLSecurityPropertyManager, XMLSecurityManager xMLSecurityManager) {
            this.fInitFeatures = new HashMap();
            this.fInitProperties = new HashMap();
            this.fSAXParser = sAXParserImpl;
            this.fSecurityManager = xMLSecurityManager;
            this.fSecurityPropertyMgr = xMLSecurityPropertyManager;
            if (xMLSecurityManager == null) {
                XMLSecurityManager xMLSecurityManager2 = new XMLSecurityManager(true);
                this.fSecurityManager = xMLSecurityManager2;
                try {
                    super.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager2);
                } catch (SAXException e) {
                    throw new UnsupportedOperationException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[]{"http://apache.org/xml/properties/security-manager"}), e);
                }
            }
            if (this.fSecurityPropertyMgr == null) {
                XMLSecurityPropertyManager xMLSecurityPropertyManager2 = new XMLSecurityPropertyManager();
                this.fSecurityPropertyMgr = xMLSecurityPropertyManager2;
                try {
                    super.setProperty("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager2);
                } catch (SAXException e2) {
                    throw new UnsupportedOperationException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[]{"http://apache.org/xml/properties/security-manager"}), e2);
                }
            }
        }

        private void resetSchemaValidator() throws SAXException {
            try {
                this.fSAXParser.fSchemaValidator.reset(this.fSAXParser.fSchemaValidatorComponentManager);
            } catch (XMLConfigurationException e) {
                x73.a(e);
            }
        }

        private void setSchemaValidatorFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
            try {
                this.fSAXParser.fSchemaValidator.setFeature(str, z);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() != Status.NOT_RECOGNIZED) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-supported", new Object[]{identifier}));
                }
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "feature-not-recognized", new Object[]{identifier}));
            }
        }

        private void setSchemaValidatorProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
            try {
                this.fSAXParser.fSchemaValidator.setProperty(str, obj);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() != Status.NOT_RECOGNIZED) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-supported", new Object[]{identifier}));
                }
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "property-not-recognized", new Object[]{identifier}));
            }
        }

        @Override // com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser, com.sun.org.apache.xerces.internal.parsers.XMLParser, org.xml.sax.XMLReader
        public synchronized boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
            try {
                if (str == null) {
                    throw new NullPointerException();
                }
                if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
                    return this.fSecurityManager.isSecureProcessing();
                }
                return super.getFeature(str);
            } catch (Throwable th) {
                throw th;
            }
        }

        public boolean getFeature0(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
            return super.getFeature(str);
        }

        public Locale getLocale() {
            return this.fConfiguration.getLocale();
        }

        @Override // com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser, org.xml.sax.XMLReader
        public synchronized Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
            try {
                if (str == null) {
                    throw new NullPointerException();
                }
                if (this.fSAXParser != null && JAXPConstants.JAXP_SCHEMA_LANGUAGE.equals(str)) {
                    return this.fSAXParser.schemaLanguage;
                }
                XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
                String limitAsString = xMLSecurityManager != null ? xMLSecurityManager.getLimitAsString(str) : null;
                if (limitAsString != null) {
                    return limitAsString;
                }
                XMLSecurityPropertyManager xMLSecurityPropertyManager = this.fSecurityPropertyMgr;
                String value = xMLSecurityPropertyManager != null ? xMLSecurityPropertyManager.getValue(str) : null;
                if (value != null) {
                    return value;
                }
                return super.getProperty(str);
            } catch (Throwable th) {
                throw th;
            }
        }

        public Object getProperty0(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
            return super.getProperty(str);
        }

        public XMLParserConfiguration getXMLParserConfiguration() {
            return this.fConfiguration;
        }

        @Override // com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser, org.xml.sax.Parser, org.xml.sax.XMLReader
        public void parse(InputSource inputSource) throws SAXException, IOException {
            SAXParserImpl sAXParserImpl = this.fSAXParser;
            if (sAXParserImpl != null && sAXParserImpl.fSchemaValidator != null) {
                if (this.fSAXParser.fSchemaValidationManager != null) {
                    this.fSAXParser.fSchemaValidationManager.reset();
                    this.fSAXParser.fUnparsedEntityHandler.reset();
                }
                resetSchemaValidator();
            }
            super.parse(inputSource);
        }

        public synchronized void restoreInitState() throws SAXNotRecognizedException, SAXNotSupportedException {
            try {
                if (!this.fInitFeatures.isEmpty()) {
                    for (Map.Entry<String, Boolean> entry : this.fInitFeatures.entrySet()) {
                        super.setFeature(entry.getKey(), entry.getValue().booleanValue());
                    }
                    this.fInitFeatures.clear();
                }
                if (!this.fInitProperties.isEmpty()) {
                    for (Map.Entry<String, Object> entry2 : this.fInitProperties.entrySet()) {
                        super.setProperty(entry2.getKey(), entry2.getValue());
                    }
                    this.fInitProperties.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        @Override // com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser, org.xml.sax.XMLReader
        public synchronized void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
            if (str == null) {
                throw new NullPointerException();
            }
            if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
                try {
                    this.fSecurityManager.setSecureProcessing(z);
                    setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
                } catch (SAXNotRecognizedException e) {
                    if (z) {
                        throw e;
                    }
                } catch (SAXNotSupportedException e2) {
                    if (z) {
                        throw e2;
                    }
                }
                return;
            }
            if (!this.fInitFeatures.containsKey(str)) {
                this.fInitFeatures.put(str, super.getFeature(str) ? Boolean.TRUE : Boolean.FALSE);
            }
            SAXParserImpl sAXParserImpl = this.fSAXParser;
            if (sAXParserImpl != null && sAXParserImpl.fSchemaValidator != null) {
                setSchemaValidatorFeature(str, z);
            }
            super.setFeature(str, z);
        }

        public void setFeature0(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
            super.setFeature(str, z);
        }

        @Override // com.sun.org.apache.xerces.internal.parsers.SAXParser, com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser, org.xml.sax.XMLReader
        public synchronized void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
            XMLSecurityPropertyManager xMLSecurityPropertyManager;
            try {
                if (str == null) {
                    throw new NullPointerException();
                }
                if (this.fSAXParser != null) {
                    if (JAXPConstants.JAXP_SCHEMA_LANGUAGE.equals(str)) {
                        if (this.fSAXParser.grammar != null) {
                            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "schema-already-specified", new Object[]{str}));
                        }
                        if ("http://www.w3.org/2001/XMLSchema".equals(obj)) {
                            if (this.fSAXParser.isValidating()) {
                                this.fSAXParser.schemaLanguage = "http://www.w3.org/2001/XMLSchema";
                                setFeature(SAXParserImpl.XMLSCHEMA_VALIDATION_FEATURE, true);
                                if (!this.fInitProperties.containsKey(JAXPConstants.JAXP_SCHEMA_LANGUAGE)) {
                                    this.fInitProperties.put(JAXPConstants.JAXP_SCHEMA_LANGUAGE, super.getProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE));
                                }
                                super.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, "http://www.w3.org/2001/XMLSchema");
                            }
                        } else {
                            if (obj != null) {
                                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "schema-not-supported", null));
                            }
                            this.fSAXParser.schemaLanguage = null;
                            setFeature(SAXParserImpl.XMLSCHEMA_VALIDATION_FEATURE, false);
                        }
                        return;
                    }
                    if (JAXPConstants.JAXP_SCHEMA_SOURCE.equals(str)) {
                        if (this.fSAXParser.grammar != null) {
                            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "schema-already-specified", new Object[]{str}));
                        }
                        String str2 = (String) getProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE);
                        if (str2 == null || !"http://www.w3.org/2001/XMLSchema".equals(str2)) {
                            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fConfiguration.getLocale(), "jaxp-order-not-supported", new Object[]{JAXPConstants.JAXP_SCHEMA_LANGUAGE, JAXPConstants.JAXP_SCHEMA_SOURCE}));
                        }
                        if (!this.fInitProperties.containsKey(JAXPConstants.JAXP_SCHEMA_SOURCE)) {
                            this.fInitProperties.put(JAXPConstants.JAXP_SCHEMA_SOURCE, super.getProperty(JAXPConstants.JAXP_SCHEMA_SOURCE));
                        }
                        super.setProperty(str, obj);
                        return;
                    }
                }
                SAXParserImpl sAXParserImpl = this.fSAXParser;
                if (sAXParserImpl != null && sAXParserImpl.fSchemaValidator != null) {
                    setSchemaValidatorProperty(str, obj);
                }
                XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
                if ((xMLSecurityManager == null || !xMLSecurityManager.setLimit(str, JdkProperty.State.APIPROPERTY, obj)) && ((xMLSecurityPropertyManager = this.fSecurityPropertyMgr) == null || !xMLSecurityPropertyManager.setValue(str, XMLSecurityPropertyManager.State.APIPROPERTY, obj))) {
                    if (!this.fInitProperties.containsKey(str)) {
                        this.fInitProperties.put(str, super.getProperty(str));
                    }
                    super.setProperty(str, obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        public void setProperty0(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
            super.setProperty(str, obj);
        }

        @Override // com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser, org.xml.sax.Parser, org.xml.sax.XMLReader
        public void parse(String str) throws SAXException, IOException {
            SAXParserImpl sAXParserImpl = this.fSAXParser;
            if (sAXParserImpl != null && sAXParserImpl.fSchemaValidator != null) {
                if (this.fSAXParser.fSchemaValidationManager != null) {
                    this.fSAXParser.fSchemaValidationManager.reset();
                    this.fSAXParser.fUnparsedEntityHandler.reset();
                }
                resetSchemaValidator();
            }
            super.parse(str);
        }

        public JAXPSAXParser() {
            this(null, null, null);
        }
    }

    public SAXParserImpl(SAXParserFactoryImpl sAXParserFactoryImpl, Map<String, Boolean> map) throws SAXException {
        this(sAXParserFactoryImpl, map, false);
    }
}
