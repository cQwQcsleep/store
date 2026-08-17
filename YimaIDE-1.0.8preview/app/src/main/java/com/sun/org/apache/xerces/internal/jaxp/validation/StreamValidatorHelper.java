package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import com.sun.org.apache.xerces.internal.parsers.XML11Configuration;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import java.io.IOException;
import java.lang.ref.SoftReference;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class StreamValidatorHelper implements ValidatorHelper {
    private static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    private static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final String SCHEMA_VALIDATOR = "http://apache.org/xml/properties/internal/validator/schema";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    private XMLSchemaValidatorComponentManager fComponentManager;
    private XMLSchemaValidator fSchemaValidator;
    private SoftReference<XMLParserConfiguration> fConfiguration = new SoftReference<>(null);
    private ValidatorHandlerImpl handler = null;

    public StreamValidatorHelper(XMLSchemaValidatorComponentManager xMLSchemaValidatorComponentManager) {
        this.fComponentManager = xMLSchemaValidatorComponentManager;
        this.fSchemaValidator = (XMLSchemaValidator) xMLSchemaValidatorComponentManager.getProperty(SCHEMA_VALIDATOR);
    }

    private XMLParserConfiguration initialize() {
        XML11Configuration xML11Configuration = new XML11Configuration();
        if (this.fComponentManager.getFeature("http://javax.xml.XMLConstants/feature/secure-processing")) {
            xML11Configuration.setProperty("http://apache.org/xml/properties/security-manager", new XMLSecurityManager());
        }
        xML11Configuration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver"));
        xML11Configuration.setProperty("http://apache.org/xml/properties/internal/error-handler", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-handler"));
        XMLErrorReporter xMLErrorReporter = (XMLErrorReporter) this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        xML11Configuration.setProperty("http://apache.org/xml/properties/internal/error-reporter", xMLErrorReporter);
        if (xMLErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210") == null) {
            XMLMessageFormatter xMLMessageFormatter = new XMLMessageFormatter();
            xMLErrorReporter.putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", xMLMessageFormatter);
            xMLErrorReporter.putMessageFormatter("http://www.w3.org/TR/1999/REC-xml-names-19990114", xMLMessageFormatter);
        }
        xML11Configuration.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table"));
        xML11Configuration.setProperty(VALIDATION_MANAGER, this.fComponentManager.getProperty(VALIDATION_MANAGER));
        xML11Configuration.setDocumentHandler(this.fSchemaValidator);
        xML11Configuration.setDTDHandler(null);
        xML11Configuration.setDTDContentModelHandler(null);
        xML11Configuration.setProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER, this.fComponentManager.getProperty(JdkConstants.XML_SECURITY_PROPERTY_MANAGER));
        xML11Configuration.setProperty("http://apache.org/xml/properties/security-manager", this.fComponentManager.getProperty("http://apache.org/xml/properties/security-manager"));
        JdkXmlUtils.catalogFeaturesConfig2Config(this.fComponentManager, xML11Configuration);
        xML11Configuration.setProperty(JdkConstants.CDATA_CHUNK_SIZE, this.fComponentManager.getProperty(JdkConstants.CDATA_CHUNK_SIZE));
        this.fConfiguration = new SoftReference<>(xML11Configuration);
        return xML11Configuration;
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.ValidatorHelper
    public void validate(Source source, Result result) throws SAXException, IOException {
        if (result != null && !(result instanceof StreamResult)) {
            w01.a(JAXPValidationMessageFormatter.formatMessage(this.fComponentManager.getLocale(), "SourceResultMismatch", new Object[]{source.getClass().getName(), result.getClass().getName()}));
            return;
        }
        StreamSource streamSource = (StreamSource) source;
        if (result != null) {
            try {
                TransformerHandler transformerHandlerNewTransformerHandler = JdkXmlUtils.getSAXTransformFactory(this.fComponentManager.getFeature(JdkConstants.OVERRIDE_PARSER)).newTransformerHandler();
                ValidatorHandlerImpl validatorHandlerImpl = new ValidatorHandlerImpl(this.fComponentManager);
                this.handler = validatorHandlerImpl;
                validatorHandlerImpl.setContentHandler(transformerHandlerNewTransformerHandler);
                transformerHandlerNewTransformerHandler.setResult(result);
            } catch (TransformerConfigurationException e) {
                throw new TransformerFactoryConfigurationError(e);
            }
        }
        XMLInputSource xMLInputSource = new XMLInputSource(streamSource.getPublicId(), streamSource.getSystemId(), null, false);
        xMLInputSource.setByteStream(streamSource.getInputStream());
        xMLInputSource.setCharacterStream(streamSource.getReader());
        XMLParserConfiguration xMLParserConfigurationInitialize = this.fConfiguration.get();
        if (xMLParserConfigurationInitialize == null) {
            xMLParserConfigurationInitialize = initialize();
        } else if (this.fComponentManager.getFeature(PARSER_SETTINGS)) {
            xMLParserConfigurationInitialize.setProperty("http://apache.org/xml/properties/internal/entity-resolver", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver"));
            xMLParserConfigurationInitialize.setProperty("http://apache.org/xml/properties/internal/error-handler", this.fComponentManager.getProperty("http://apache.org/xml/properties/internal/error-handler"));
        }
        this.fComponentManager.reset();
        this.fSchemaValidator.setDocumentHandler(this.handler);
        try {
            xMLParserConfigurationInitialize.parse(xMLInputSource);
        } catch (XMLParseException e2) {
            throw Util.toSAXParseException(e2);
        } catch (XNIException e3) {
            throw Util.toSAXException(e3);
        }
    }
}
