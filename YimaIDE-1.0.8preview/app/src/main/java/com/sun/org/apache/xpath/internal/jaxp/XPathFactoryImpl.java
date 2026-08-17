package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.XMLSecurityManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPathFactoryImpl extends XPathFactory {
    private static final String CLASS_NAME = "XPathFactoryImpl";
    private final JdkXmlFeatures _featureManager;
    private boolean _isNotSecureProcessing;
    private boolean _isSecureMode;
    private XMLSecurityManager _xmlSecMgr;
    private XPathFunctionResolver xPathFunctionResolver = null;
    private XPathVariableResolver xPathVariableResolver = null;

    public XPathFactoryImpl() {
        this._isNotSecureProcessing = true;
        this._isSecureMode = false;
        if (System.getSecurityManager() != null) {
            this._isSecureMode = true;
            this._isNotSecureProcessing = false;
        }
        this._featureManager = new JdkXmlFeatures(!this._isNotSecureProcessing);
        this._xmlSecMgr = new XMLSecurityManager(true);
    }

    @Override // javax.xml.xpath.XPathFactory
    public boolean getFeature(String str) throws XPathFactoryConfigurationException {
        if (str == null) {
            x0e.a(XPATHMessages.createXPATHMessage("ER_GETTING_NULL_FEATURE", new Object[]{CLASS_NAME}));
            return false;
        }
        if (str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            return !this._isNotSecureProcessing;
        }
        int index = this._featureManager.getIndex(str);
        if (index > -1) {
            return this._featureManager.getFeature(index);
        }
        throw new XPathFactoryConfigurationException(XPATHMessages.createXPATHMessage("ER_GETTING_UNKNOWN_FEATURE", new Object[]{str, CLASS_NAME}));
    }

    @Override // javax.xml.xpath.XPathFactory
    public boolean isObjectModelSupported(String str) {
        if (str == null) {
            x0e.a(XPATHMessages.createXPATHMessage("ER_OBJECT_MODEL_NULL", new Object[]{getClass().getName()}));
            return false;
        }
        if (str.length() != 0) {
            return str.equals("http://java.sun.com/jaxp/xpath/dom");
        }
        w01.a(XPATHMessages.createXPATHMessage("ER_OBJECT_MODEL_EMPTY", new Object[]{getClass().getName()}));
        return false;
    }

    @Override // javax.xml.xpath.XPathFactory
    public XPath newXPath() {
        return new XPathImpl(this.xPathVariableResolver, this.xPathFunctionResolver, !this._isNotSecureProcessing, this._featureManager, this._xmlSecMgr);
    }

    @Override // javax.xml.xpath.XPathFactory
    public void setFeature(String str, boolean z) throws XPathFactoryConfigurationException {
        JdkXmlFeatures jdkXmlFeatures;
        if (str == null) {
            x0e.a(XPATHMessages.createXPATHMessage("ER_FEATURE_NAME_NULL", new Object[]{CLASS_NAME, Boolean.valueOf(z)}));
            return;
        }
        if (!str.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            if (str.equals(JdkConstants.ORACLE_FEATURE_SERVICE_MECHANISM) && this._isSecureMode) {
                return;
            }
            JdkXmlFeatures jdkXmlFeatures2 = this._featureManager;
            if (jdkXmlFeatures2 == null || !jdkXmlFeatures2.setFeature(str, JdkProperty.State.APIPROPERTY, Boolean.valueOf(z))) {
                throw new XPathFactoryConfigurationException(XPATHMessages.createXPATHMessage("ER_FEATURE_UNKNOWN", new Object[]{str, CLASS_NAME, Boolean.valueOf(z)}));
            }
            return;
        }
        if (this._isSecureMode && !z) {
            throw new XPathFactoryConfigurationException(XPATHMessages.createXPATHMessage("ER_SECUREPROCESSING_FEATURE", new Object[]{str, CLASS_NAME, Boolean.valueOf(z)}));
        }
        this._isNotSecureProcessing = !z;
        if (!z || (jdkXmlFeatures = this._featureManager) == null) {
            return;
        }
        jdkXmlFeatures.setFeature(JdkXmlFeatures.XmlFeature.ENABLE_EXTENSION_FUNCTION, JdkProperty.State.FSP, false);
    }

    @Override // javax.xml.xpath.XPathFactory
    public void setXPathFunctionResolver(XPathFunctionResolver xPathFunctionResolver) {
        if (xPathFunctionResolver != null) {
            this.xPathFunctionResolver = xPathFunctionResolver;
        } else {
            x0e.a(XPATHMessages.createXPATHMessage("ER_NULL_XPATH_FUNCTION_RESOLVER", new Object[]{CLASS_NAME}));
        }
    }

    @Override // javax.xml.xpath.XPathFactory
    public void setXPathVariableResolver(XPathVariableResolver xPathVariableResolver) {
        if (xPathVariableResolver != null) {
            this.xPathVariableResolver = xPathVariableResolver;
        } else {
            x0e.a(XPATHMessages.createXPATHMessage("ER_NULL_XPATH_VARIABLE_RESOLVER", new Object[]{CLASS_NAME}));
        }
    }
}
