package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stax.StAXResult;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class StAXValidatorHelper implements ValidatorHelper {
    private XMLSchemaValidatorComponentManager fComponentManager;
    private Transformer identityTransformer1 = null;
    private TransformerHandler identityTransformer2 = null;
    private ValidatorHandlerImpl handler = null;

    public StAXValidatorHelper(XMLSchemaValidatorComponentManager xMLSchemaValidatorComponentManager) {
        this.fComponentManager = xMLSchemaValidatorComponentManager;
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.ValidatorHelper
    public void validate(Source source, Result result) throws SAXException, IOException {
        if (result != null && !(result instanceof StAXResult)) {
            w01.a(JAXPValidationMessageFormatter.formatMessage(this.fComponentManager.getLocale(), "SourceResultMismatch", new Object[]{source.getClass().getName(), result.getClass().getName()}));
            return;
        }
        if (this.identityTransformer1 == null) {
            try {
                SAXTransformerFactory sAXTransformFactory = JdkXmlUtils.getSAXTransformFactory(this.fComponentManager.getFeature(JdkConstants.OVERRIDE_PARSER));
                XMLSecurityManager xMLSecurityManager = (XMLSecurityManager) this.fComponentManager.getProperty("http://apache.org/xml/properties/security-manager");
                if (xMLSecurityManager != null) {
                    for (XMLSecurityManager.Limit limit : XMLSecurityManager.Limit.values()) {
                        if (xMLSecurityManager.isSet(limit.ordinal())) {
                            sAXTransformFactory.setAttribute(limit.apiProperty(), xMLSecurityManager.getLimitValueAsString(limit));
                        }
                    }
                    if (xMLSecurityManager.printEntityCountInfo()) {
                        sAXTransformFactory.setAttribute(JdkConstants.JDK_DEBUG_LIMIT, JdkConstants.JDK_YES);
                    }
                }
                this.identityTransformer1 = sAXTransformFactory.newTransformer();
                this.identityTransformer2 = sAXTransformFactory.newTransformerHandler();
            } catch (TransformerConfigurationException e) {
                throw new TransformerFactoryConfigurationError(e);
            }
        }
        ValidatorHandlerImpl validatorHandlerImpl = new ValidatorHandlerImpl(this.fComponentManager);
        this.handler = validatorHandlerImpl;
        if (result != null) {
            validatorHandlerImpl.setContentHandler(this.identityTransformer2);
            this.identityTransformer2.setResult(result);
        }
        try {
            try {
                this.identityTransformer1.transform(source, new SAXResult(this.handler));
                this.handler.setContentHandler(null);
            } catch (Throwable th) {
                this.handler.setContentHandler(null);
                throw th;
            }
        } catch (TransformerException e2) {
            if (!(e2.getException() instanceof SAXException)) {
                throw new SAXException(e2);
            }
            throw ((SAXException) e2.getException());
        }
    }
}
