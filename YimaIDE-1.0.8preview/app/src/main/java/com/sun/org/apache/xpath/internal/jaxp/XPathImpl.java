package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathEvaluationResult;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFunctionException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.XMLSecurityManager;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPathImpl extends XPathImplUtil implements XPath {
    private NamespaceContext namespaceContext;
    private XPathFunctionResolver origFunctionResolver;
    private XPathVariableResolver origVariableResolver;

    public XPathImpl(XPathVariableResolver xPathVariableResolver, XPathFunctionResolver xPathFunctionResolver, boolean z, JdkXmlFeatures jdkXmlFeatures, XMLSecurityManager xMLSecurityManager) {
        this.namespaceContext = null;
        this.variableResolver = xPathVariableResolver;
        this.origVariableResolver = xPathVariableResolver;
        this.functionResolver = xPathFunctionResolver;
        this.origFunctionResolver = xPathFunctionResolver;
        this.featureSecureProcessing = z;
        this.featureManager = jdkXmlFeatures;
        this.overrideDefaultParser = jdkXmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.JDK_OVERRIDE_PARSER);
        this.xmlSecMgr = xMLSecurityManager;
    }

    private XObject eval(String str, Object obj) throws TransformerException {
        requireNonNull(str, "XPath expression");
        return eval(obj, new com.sun.org.apache.xpath.internal.XPath(str, null, this.prefixResolver, 0, null, null, this.xmlSecMgr));
    }

    @Override // javax.xml.xpath.XPath
    public XPathExpression compile(String str) throws XPathExpressionException {
        requireNonNull(str, "XPath expression");
        try {
            return new XPathExpressionImpl(new com.sun.org.apache.xpath.internal.XPath(str, null, this.prefixResolver, 0, null, null, this.xmlSecMgr), this.prefixResolver, this.functionResolver, this.variableResolver, this.featureSecureProcessing, this.featureManager);
        } catch (TransformerException e) {
            throw new XPathExpressionException(e);
        }
    }

    @Override // javax.xml.xpath.XPath
    public Object evaluate(String str, Object obj, QName qName) throws XPathExpressionException {
        requireNonNull(str, "XPath expression");
        isSupported(qName);
        try {
            return getResultAsType(eval(str, obj), qName);
        } catch (NullPointerException e) {
            throw new XPathExpressionException(e);
        } catch (TransformerException e2) {
            Throwable exception = e2.getException();
            if (exception instanceof XPathFunctionException) {
                throw ((XPathFunctionException) exception);
            }
            throw new XPathExpressionException(e2);
        }
    }

    @Override // javax.xml.xpath.XPath
    public <T> T evaluateExpression(String str, Object obj, Class<T> cls) throws XPathExpressionException {
        isSupportedClassType(cls);
        try {
            XObject xObjectEval = eval(str, obj);
            return cls.isAssignableFrom(XPathEvaluationResult.class) ? (T) getXPathResult(xObjectEval, cls) : (T) XPathResultImpl.getValue(xObjectEval, cls);
        } catch (TransformerException e) {
            throw new XPathExpressionException(e);
        }
    }

    @Override // javax.xml.xpath.XPath
    public NamespaceContext getNamespaceContext() {
        return this.namespaceContext;
    }

    @Override // javax.xml.xpath.XPath
    public XPathFunctionResolver getXPathFunctionResolver() {
        return this.functionResolver;
    }

    @Override // javax.xml.xpath.XPath
    public XPathVariableResolver getXPathVariableResolver() {
        return this.variableResolver;
    }

    @Override // javax.xml.xpath.XPath
    public void reset() {
        this.variableResolver = this.origVariableResolver;
        this.functionResolver = this.origFunctionResolver;
        this.namespaceContext = null;
    }

    @Override // javax.xml.xpath.XPath
    public void setNamespaceContext(NamespaceContext namespaceContext) {
        requireNonNull(namespaceContext, "NamespaceContext");
        this.namespaceContext = namespaceContext;
        this.prefixResolver = new JAXPPrefixResolver(namespaceContext);
    }

    @Override // javax.xml.xpath.XPath
    public void setXPathFunctionResolver(XPathFunctionResolver xPathFunctionResolver) {
        requireNonNull(xPathFunctionResolver, "XPathFunctionResolver");
        this.functionResolver = xPathFunctionResolver;
    }

    @Override // javax.xml.xpath.XPath
    public void setXPathVariableResolver(XPathVariableResolver xPathVariableResolver) {
        requireNonNull(xPathVariableResolver, "XPathVariableResolver");
        this.variableResolver = xPathVariableResolver;
    }

    public XPathImpl(XPathVariableResolver xPathVariableResolver, XPathFunctionResolver xPathFunctionResolver) {
        this(xPathVariableResolver, xPathFunctionResolver, false, new JdkXmlFeatures(false), new XMLSecurityManager(true));
    }

    @Override // javax.xml.xpath.XPath
    public XPathEvaluationResult<?> evaluateExpression(String str, Object obj) throws XPathExpressionException {
        return (XPathEvaluationResult) evaluateExpression(str, obj, XPathEvaluationResult.class);
    }

    @Override // javax.xml.xpath.XPath
    public <T> T evaluateExpression(String str, InputSource inputSource, Class<T> cls) throws XPathExpressionException {
        return (T) evaluateExpression(str, getDocument(inputSource), cls);
    }

    @Override // javax.xml.xpath.XPath
    public XPathEvaluationResult<?> evaluateExpression(String str, InputSource inputSource) throws XPathExpressionException {
        return (XPathEvaluationResult) evaluateExpression(str, inputSource, XPathEvaluationResult.class);
    }

    @Override // javax.xml.xpath.XPath
    public String evaluate(String str, Object obj) throws XPathExpressionException {
        return (String) evaluate(str, obj, XPathConstants.STRING);
    }

    @Override // javax.xml.xpath.XPath
    public Object evaluate(String str, InputSource inputSource, QName qName) throws XPathExpressionException {
        isSupported(qName);
        try {
            return getResultAsType(eval(str, getDocument(inputSource)), qName);
        } catch (TransformerException e) {
            Throwable exception = e.getException();
            if (exception instanceof XPathFunctionException) {
                throw ((XPathFunctionException) exception);
            }
            throw new XPathExpressionException(e);
        }
    }

    @Override // javax.xml.xpath.XPath
    public String evaluate(String str, InputSource inputSource) throws XPathExpressionException {
        return (String) evaluate(str, inputSource, XPathConstants.STRING);
    }
}
