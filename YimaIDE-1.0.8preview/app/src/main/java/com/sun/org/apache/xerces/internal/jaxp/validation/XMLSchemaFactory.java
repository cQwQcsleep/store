package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader;
import com.sun.org.apache.xerces.internal.util.DOMEntityResolverWrapper;
import com.sun.org.apache.xerces.internal.util.DOMInputSource;
import com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper;
import com.sun.org.apache.xerces.internal.util.SAXInputSource;
import com.sun.org.apache.xerces.internal.util.SAXMessageFormatter;
import com.sun.org.apache.xerces.internal.util.StAXInputSource;
import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.stream.XMLEventReader;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XMLSchemaFactory extends SchemaFactory {
    private static final String JAXP_SOURCE_FEATURE_PREFIX = "http://javax.xml.transform";
    private static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private final DOMEntityResolverWrapper fDOMEntityResolverWrapper;
    private ErrorHandler fErrorHandler;
    private final ErrorHandlerWrapper fErrorHandlerWrapper;
    private LSResourceResolver fLSResourceResolver;
    private final boolean fOverrideDefaultParser;
    private XMLSecurityManager fSecurityManager;
    private XMLSecurityPropertyManager fSecurityPropertyMgr;
    private boolean fUseGrammarPoolOnly;
    private final XMLGrammarPoolWrapper fXMLGrammarPoolWrapper;
    private final XMLSchemaLoader fXMLSchemaLoader;
    private final JdkXmlFeatures fXmlFeatures;

    public static class XMLGrammarPoolWrapper implements XMLGrammarPool {
        private XMLGrammarPool fGrammarPool;

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void cacheGrammars(String str, Grammar[] grammarArr) {
            this.fGrammarPool.cacheGrammars(str, grammarArr);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void clear() {
            this.fGrammarPool.clear();
        }

        public XMLGrammarPool getGrammarPool() {
            return this.fGrammarPool;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void lockPool() {
            this.fGrammarPool.lockPool();
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar retrieveGrammar(XMLGrammarDescription xMLGrammarDescription) {
            return this.fGrammarPool.retrieveGrammar(xMLGrammarDescription);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar[] retrieveInitialGrammarSet(String str) {
            return this.fGrammarPool.retrieveInitialGrammarSet(str);
        }

        public void setGrammarPool(XMLGrammarPool xMLGrammarPool) {
            this.fGrammarPool = xMLGrammarPool;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void unlockPool() {
            this.fGrammarPool.unlockPool();
        }
    }

    public XMLSchemaFactory() {
        XMLSchemaLoader xMLSchemaLoader = new XMLSchemaLoader();
        this.fXMLSchemaLoader = xMLSchemaLoader;
        ErrorHandlerWrapper errorHandlerWrapper = new ErrorHandlerWrapper(DraconianErrorHandler.getInstance());
        this.fErrorHandlerWrapper = errorHandlerWrapper;
        DOMEntityResolverWrapper dOMEntityResolverWrapper = new DOMEntityResolverWrapper();
        this.fDOMEntityResolverWrapper = dOMEntityResolverWrapper;
        XMLGrammarPoolWrapper xMLGrammarPoolWrapper = new XMLGrammarPoolWrapper();
        this.fXMLGrammarPoolWrapper = xMLGrammarPoolWrapper;
        xMLSchemaLoader.setFeature(SCHEMA_FULL_CHECKING, true);
        xMLSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xMLGrammarPoolWrapper);
        xMLSchemaLoader.setEntityResolver(dOMEntityResolverWrapper);
        xMLSchemaLoader.setErrorHandler(errorHandlerWrapper);
        this.fUseGrammarPoolOnly = true;
        XMLSecurityManager xMLSecurityManager = new XMLSecurityManager(true);
        this.fSecurityManager = xMLSecurityManager;
        xMLSchemaLoader.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
        XMLSecurityPropertyManager xMLSecurityPropertyManager = new XMLSecurityPropertyManager();
        this.fSecurityPropertyMgr = xMLSecurityPropertyManager;
        xMLSchemaLoader.setProperty("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager);
        xMLSchemaLoader.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", JdkXmlUtils.USE_CATALOG_DEFAULT);
        for (CatalogFeatures.Feature feature : CatalogFeatures.Feature.values()) {
            this.fXMLSchemaLoader.setProperty(feature.getPropertyName(), null);
        }
        this.fXMLSchemaLoader.setProperty(JdkConstants.CDATA_CHUNK_SIZE, Integer.valueOf(JdkConstants.CDATA_CHUNK_SIZE_DEFAULT));
        JdkXmlFeatures jdkXmlFeatures = new JdkXmlFeatures(this.fSecurityManager.isSecureProcessing());
        this.fXmlFeatures = jdkXmlFeatures;
        boolean feature2 = jdkXmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.JDK_OVERRIDE_PARSER);
        this.fOverrideDefaultParser = feature2;
        this.fXMLSchemaLoader.setFeature(JdkConstants.OVERRIDE_PARSER, feature2);
    }

    private void propagateFeatures(AbstractXMLSchema abstractXMLSchema) {
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        abstractXMLSchema.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", xMLSecurityManager != null && xMLSecurityManager.isSecureProcessing());
        abstractXMLSchema.setFeature(JdkConstants.OVERRIDE_PARSER, this.fOverrideDefaultParser);
        String[] recognizedFeatures = this.fXMLSchemaLoader.getRecognizedFeatures();
        for (int i = 0; i < recognizedFeatures.length; i++) {
            abstractXMLSchema.setFeature(recognizedFeatures[i], this.fXMLSchemaLoader.getFeature(recognizedFeatures[i]));
        }
    }

    private void propagateProperties(AbstractXMLSchema abstractXMLSchema) {
        String[] recognizedProperties = this.fXMLSchemaLoader.getRecognizedProperties();
        for (int i = 0; i < recognizedProperties.length; i++) {
            abstractXMLSchema.setProperty(recognizedProperties[i], this.fXMLSchemaLoader.getProperty(recognizedProperties[i]));
        }
    }

    @Override // javax.xml.validation.SchemaFactory
    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    @Override // javax.xml.validation.SchemaFactory
    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str == null) {
            x0e.a(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "FeatureNameNull", null));
            return false;
        }
        if (str.startsWith(JAXP_SOURCE_FEATURE_PREFIX) && (str.equals(StreamSource.FEATURE) || str.equals(SAXSource.FEATURE) || str.equals("http://javax.xml.transform.dom.DOMSource/feature") || str.equals(StAXSource.FEATURE))) {
            return true;
        }
        if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
            return xMLSecurityManager != null && xMLSecurityManager.isSecureProcessing();
        }
        if (str.equals(USE_GRAMMAR_POOL_ONLY)) {
            return this.fUseGrammarPoolOnly;
        }
        int index = this.fXmlFeatures.getIndex(str);
        if (index > -1) {
            return this.fXmlFeatures.getFeature(index);
        }
        try {
            return this.fXMLSchemaLoader.getFeature(str);
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() == Status.NOT_RECOGNIZED) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-recognized", new Object[]{identifier}));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-supported", new Object[]{identifier}));
        }
    }

    @Override // javax.xml.validation.SchemaFactory
    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str == null) {
            x0e.a(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "ProperyNameNull", null));
            return null;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            return this.fSecurityManager;
        }
        if (str.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{str}));
        }
        try {
            XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
            String limitAsString = xMLSecurityManager != null ? xMLSecurityManager.getLimitAsString(str) : null;
            return limitAsString != null ? limitAsString : this.fXMLSchemaLoader.getProperty(str);
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() == Status.NOT_RECOGNIZED) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-recognized", new Object[]{identifier}));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{identifier}));
        }
    }

    @Override // javax.xml.validation.SchemaFactory
    public LSResourceResolver getResourceResolver() {
        return this.fLSResourceResolver;
    }

    @Override // javax.xml.validation.SchemaFactory
    public boolean isSchemaLanguageSupported(String str) {
        if (str == null) {
            x0e.a(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SchemaLanguageNull", null));
            return false;
        }
        if (str.length() != 0) {
            return str.equals("http://www.w3.org/2001/XMLSchema") || str.equals(Constants.W3C_XML_SCHEMA10_NS_URI);
        }
        w01.a(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SchemaLanguageLengthZero", null));
        return false;
    }

    @Override // javax.xml.validation.SchemaFactory
    public Schema newSchema(Source[] sourceArr) throws SAXException {
        AbstractXMLSchema xMLSchema;
        XMLGrammarPoolImplExtension xMLGrammarPoolImplExtension = new XMLGrammarPoolImplExtension();
        this.fXMLGrammarPoolWrapper.setGrammarPool(xMLGrammarPoolImplExtension);
        XMLInputSource[] xMLInputSourceArr = new XMLInputSource[sourceArr.length];
        for (int i = 0; i < sourceArr.length; i++) {
            Source source = sourceArr[i];
            if (source instanceof StreamSource) {
                StreamSource streamSource = (StreamSource) source;
                String publicId = streamSource.getPublicId();
                String systemId = streamSource.getSystemId();
                InputStream inputStream = streamSource.getInputStream();
                Reader reader = streamSource.getReader();
                XMLInputSource xMLInputSource = new XMLInputSource(publicId, systemId, null, false);
                xMLInputSource.setByteStream(inputStream);
                xMLInputSource.setCharacterStream(reader);
                xMLInputSourceArr[i] = xMLInputSource;
            } else if (source instanceof SAXSource) {
                SAXSource sAXSource = (SAXSource) source;
                InputSource inputSource = sAXSource.getInputSource();
                if (inputSource == null) {
                    throw new SAXException(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "SAXSourceNullInputSource", null));
                }
                xMLInputSourceArr[i] = new SAXInputSource(sAXSource.getXMLReader(), inputSource);
            } else if (source instanceof DOMSource) {
                DOMSource dOMSource = (DOMSource) source;
                xMLInputSourceArr[i] = new DOMInputSource(dOMSource.getNode(), dOMSource.getSystemId());
            } else {
                if (!(source instanceof StAXSource)) {
                    XMLSchemaLoader xMLSchemaLoader = this.fXMLSchemaLoader;
                    if (source == null) {
                        x0e.a(JAXPValidationMessageFormatter.formatMessage(xMLSchemaLoader.getLocale(), "SchemaSourceArrayMemberNull", null));
                        return null;
                    }
                    w01.a(JAXPValidationMessageFormatter.formatMessage(xMLSchemaLoader.getLocale(), "SchemaFactorySourceUnrecognized", new Object[]{source.getClass().getName()}));
                    return null;
                }
                StAXSource stAXSource = (StAXSource) source;
                XMLEventReader xMLEventReader = stAXSource.getXMLEventReader();
                if (xMLEventReader != null) {
                    xMLInputSourceArr[i] = new StAXInputSource(xMLEventReader);
                } else {
                    xMLInputSourceArr[i] = new StAXInputSource(stAXSource.getXMLStreamReader());
                }
            }
        }
        try {
            this.fXMLSchemaLoader.loadGrammar(xMLInputSourceArr);
            this.fXMLGrammarPoolWrapper.setGrammarPool(null);
            int grammarCount = xMLGrammarPoolImplExtension.getGrammarCount();
            if (!this.fUseGrammarPoolOnly) {
                xMLSchema = new XMLSchema(new ReadOnlyGrammarPool(xMLGrammarPoolImplExtension), false);
            } else if (grammarCount > 1) {
                xMLSchema = new XMLSchema(new ReadOnlyGrammarPool(xMLGrammarPoolImplExtension));
            } else {
                xMLSchema = grammarCount == 1 ? new SimpleXMLSchema(xMLGrammarPoolImplExtension.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema")[0]) : new EmptyXMLSchema();
            }
            propagateFeatures(xMLSchema);
            propagateProperties(xMLSchema);
            return xMLSchema;
        } catch (XNIException e) {
            throw Util.toSAXException(e);
        } catch (IOException e2) {
            SAXParseException sAXParseException = new SAXParseException(e2.getMessage(), null, e2);
            ErrorHandler errorHandler = this.fErrorHandler;
            if (errorHandler == null) {
                throw sAXParseException;
            }
            errorHandler.error(sAXParseException);
            throw sAXParseException;
        }
    }

    @Override // javax.xml.validation.SchemaFactory
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.fErrorHandler = errorHandler;
        ErrorHandlerWrapper errorHandlerWrapper = this.fErrorHandlerWrapper;
        if (errorHandler == null) {
            errorHandler = DraconianErrorHandler.getInstance();
        }
        errorHandlerWrapper.setErrorHandler(errorHandler);
        this.fXMLSchemaLoader.setErrorHandler(this.fErrorHandlerWrapper);
    }

    @Override // javax.xml.validation.SchemaFactory
    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str == null) {
            x0e.a(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "FeatureNameNull", null));
            return;
        }
        if (str.startsWith(JAXP_SOURCE_FEATURE_PREFIX) && (str.equals(StreamSource.FEATURE) || str.equals(SAXSource.FEATURE) || str.equals("http://javax.xml.transform.dom.DOMSource/feature") || str.equals(StAXSource.FEATURE))) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-read-only", new Object[]{str}));
        }
        if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            if (System.getSecurityManager() != null && !z) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(null, "jaxp-secureprocessing-feature", null));
            }
            this.fSecurityManager.setSecureProcessing(z);
            if (z) {
                XMLSecurityPropertyManager xMLSecurityPropertyManager = this.fSecurityPropertyMgr;
                XMLSecurityPropertyManager.Property property = XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD;
                XMLSecurityPropertyManager.State state = XMLSecurityPropertyManager.State.FSP;
                xMLSecurityPropertyManager.setValue(property, state, "");
                this.fSecurityPropertyMgr.setValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_SCHEMA, state, "");
            }
            this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
            return;
        }
        if (str.equals(USE_GRAMMAR_POOL_ONLY)) {
            this.fUseGrammarPoolOnly = z;
            return;
        }
        if (!str.equals(JdkConstants.ORACLE_FEATURE_SERVICE_MECHANISM) || System.getSecurityManager() == null) {
            JdkXmlFeatures jdkXmlFeatures = this.fXmlFeatures;
            if (jdkXmlFeatures != null && jdkXmlFeatures.setFeature(str, JdkProperty.State.APIPROPERTY, Boolean.valueOf(z))) {
                if (JdkProperty.ImplPropMap.OVERRIDEPARSER.is(str) || str.equals("http://javax.xml.XMLConstants/feature/useCatalog")) {
                    this.fXMLSchemaLoader.setFeature(str, z);
                    return;
                }
                return;
            }
            try {
                this.fXMLSchemaLoader.setFeature(str, z);
            } catch (XMLConfigurationException e) {
                String identifier = e.getIdentifier();
                if (e.getType() != Status.NOT_RECOGNIZED) {
                    throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-supported", new Object[]{identifier}));
                }
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "feature-not-recognized", new Object[]{identifier}));
            }
        }
    }

    @Override // javax.xml.validation.SchemaFactory
    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str == null) {
            x0e.a(JAXPValidationMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "ProperyNameNull", null));
            return;
        }
        if (str.equals("http://apache.org/xml/properties/security-manager")) {
            XMLSecurityManager xMLSecurityManagerConvert = XMLSecurityManager.convert(obj, this.fSecurityManager);
            this.fSecurityManager = xMLSecurityManagerConvert;
            this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManagerConvert);
            return;
        }
        if (str.equals("jdk.xml.xmlSecurityPropertyManager")) {
            if (obj == null) {
                this.fSecurityPropertyMgr = new XMLSecurityPropertyManager();
            } else {
                this.fSecurityPropertyMgr = (XMLSecurityPropertyManager) obj;
            }
            this.fXMLSchemaLoader.setProperty("jdk.xml.xmlSecurityPropertyManager", this.fSecurityPropertyMgr);
            return;
        }
        if (str.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{str}));
        }
        try {
            XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
            if (xMLSecurityManager != null && xMLSecurityManager.setLimit(str, JdkProperty.State.APIPROPERTY, obj)) {
                return;
            }
            XMLSecurityPropertyManager xMLSecurityPropertyManager = this.fSecurityPropertyMgr;
            if (xMLSecurityPropertyManager != null && xMLSecurityPropertyManager.setValue(str, XMLSecurityPropertyManager.State.APIPROPERTY, obj)) {
                return;
            }
            this.fXMLSchemaLoader.setProperty(str, obj);
        } catch (XMLConfigurationException e) {
            String identifier = e.getIdentifier();
            if (e.getType() != Status.NOT_RECOGNIZED) {
                throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-supported", new Object[]{identifier}));
            }
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(this.fXMLSchemaLoader.getLocale(), "property-not-recognized", new Object[]{identifier}));
        }
    }

    @Override // javax.xml.validation.SchemaFactory
    public void setResourceResolver(LSResourceResolver lSResourceResolver) {
        this.fLSResourceResolver = lSResourceResolver;
        this.fDOMEntityResolverWrapper.setEntityResolver(lSResourceResolver);
        this.fXMLSchemaLoader.setEntityResolver(this.fDOMEntityResolverWrapper);
    }

    public static class XMLGrammarPoolImplExtension extends XMLGrammarPoolImpl {
        public XMLGrammarPoolImplExtension() {
        }

        public int getGrammarCount() {
            return this.fGrammarCount;
        }

        public XMLGrammarPoolImplExtension(int i) {
            super(i);
        }
    }

    @Override // javax.xml.validation.SchemaFactory
    public Schema newSchema() throws SAXException {
        WeakReferenceXMLSchema weakReferenceXMLSchema = new WeakReferenceXMLSchema();
        propagateFeatures(weakReferenceXMLSchema);
        propagateProperties(weakReferenceXMLSchema);
        return weakReferenceXMLSchema;
    }

    public Schema newSchema(XMLGrammarPool xMLGrammarPool) throws SAXException {
        XMLSchema xMLSchema;
        if (this.fUseGrammarPoolOnly) {
            xMLSchema = new XMLSchema(new ReadOnlyGrammarPool(xMLGrammarPool));
        } else {
            xMLSchema = new XMLSchema(xMLGrammarPool, false);
        }
        propagateFeatures(xMLSchema);
        return xMLSchema;
    }
}
