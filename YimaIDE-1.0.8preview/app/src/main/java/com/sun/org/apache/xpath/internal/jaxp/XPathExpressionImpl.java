package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathEvaluationResult;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFunctionException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import jdk.xml.internal.JdkXmlFeatures;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPathExpressionImpl extends XPathImplUtil implements XPathExpression {
    private XPath xpath;

    public XPathExpressionImpl(XPath xPath, JAXPPrefixResolver jAXPPrefixResolver, XPathFunctionResolver xPathFunctionResolver, XPathVariableResolver xPathVariableResolver, boolean z, JdkXmlFeatures jdkXmlFeatures) {
        this.xpath = xPath;
        this.prefixResolver = jAXPPrefixResolver;
        this.functionResolver = xPathFunctionResolver;
        this.variableResolver = xPathVariableResolver;
        this.featureSecureProcessing = z;
        this.overrideDefaultParser = jdkXmlFeatures.getFeature(JdkXmlFeatures.XmlFeature.JDK_OVERRIDE_PARSER);
        this.featureManager = jdkXmlFeatures;
    }

    public Object eval(Object obj, QName qName) throws TransformerException {
        return getResultAsType(eval(obj, this.xpath), qName);
    }

    @Override // javax.xml.xpath.XPathExpression
    public Object evaluate(Object obj, QName qName) throws XPathExpressionException {
        isSupported(qName);
        try {
            return eval(obj, qName);
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

    @Override // javax.xml.xpath.XPathExpression
    public <T> T evaluateExpression(Object obj, Class<T> cls) throws XPathExpressionException {
        isSupportedClassType(cls);
        try {
            XObject xObjectEval = eval(obj, this.xpath);
            return cls.isAssignableFrom(XPathEvaluationResult.class) ? (T) getXPathResult(xObjectEval, cls) : (T) XPathResultImpl.getValue(xObjectEval, cls);
        } catch (TransformerException e) {
            throw new XPathExpressionException(e);
        }
    }

    public void setXPath(XPath xPath) {
        this.xpath = xPath;
    }

    public XPathExpressionImpl(XPath xPath, JAXPPrefixResolver jAXPPrefixResolver, XPathFunctionResolver xPathFunctionResolver, XPathVariableResolver xPathVariableResolver) {
        this(xPath, jAXPPrefixResolver, xPathFunctionResolver, xPathVariableResolver, false, new JdkXmlFeatures(false));
    }

    public XPathExpressionImpl() {
        this(null, null, null, null, false, new JdkXmlFeatures(false));
    }

    @Override // javax.xml.xpath.XPathExpression
    public String evaluate(Object obj) throws XPathExpressionException {
        return (String) evaluate(obj, XPathConstants.STRING);
    }

    @Override // javax.xml.xpath.XPathExpression
    public Object evaluate(InputSource inputSource, QName qName) throws XPathExpressionException {
        isSupported(qName);
        try {
            return eval(getDocument(inputSource), qName);
        } catch (TransformerException e) {
            throw new XPathExpressionException(e);
        }
    }

    @Override // javax.xml.xpath.XPathExpression
    public XPathEvaluationResult<?> evaluateExpression(Object obj) throws XPathExpressionException {
        return (XPathEvaluationResult) evaluateExpression(obj, XPathEvaluationResult.class);
    }

    @Override // javax.xml.xpath.XPathExpression
    public <T> T evaluateExpression(InputSource inputSource, Class<T> cls) throws XPathExpressionException {
        return (T) evaluateExpression(getDocument(inputSource), cls);
    }

    @Override // javax.xml.xpath.XPathExpression
    public XPathEvaluationResult<?> evaluateExpression(InputSource inputSource) throws XPathExpressionException {
        return (XPathEvaluationResult) evaluateExpression(inputSource, XPathEvaluationResult.class);
    }

    @Override // javax.xml.xpath.XPathExpression
    public String evaluate(InputSource inputSource) throws XPathExpressionException {
        return (String) evaluate(inputSource, XPathConstants.STRING);
    }
}
