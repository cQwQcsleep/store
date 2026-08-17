package javax.xml.xpath;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XPath {
    XPathExpression compile(String str) throws XPathExpressionException;

    Object evaluate(String str, Object obj, QName qName) throws XPathExpressionException;

    Object evaluate(String str, InputSource inputSource, QName qName) throws XPathExpressionException;

    String evaluate(String str, Object obj) throws XPathExpressionException;

    String evaluate(String str, InputSource inputSource) throws XPathExpressionException;

    default <T> T evaluateExpression(String str, Object obj, Class<T> cls) throws XPathExpressionException {
        return cls.cast(evaluate(str, obj, XPathEvaluationResult.XPathResultType.getQNameType(cls)));
    }

    NamespaceContext getNamespaceContext();

    XPathFunctionResolver getXPathFunctionResolver();

    XPathVariableResolver getXPathVariableResolver();

    void reset();

    void setNamespaceContext(NamespaceContext namespaceContext);

    void setXPathFunctionResolver(XPathFunctionResolver xPathFunctionResolver);

    void setXPathVariableResolver(XPathVariableResolver xPathVariableResolver);

    default XPathEvaluationResult<?> evaluateExpression(String str, Object obj) throws XPathExpressionException {
        return (XPathEvaluationResult) evaluateExpression(str, obj, XPathEvaluationResult.class);
    }

    default <T> T evaluateExpression(String str, InputSource inputSource, Class<T> cls) throws XPathExpressionException {
        return cls.cast(evaluate(str, inputSource, XPathEvaluationResult.XPathResultType.getQNameType(cls)));
    }

    default XPathEvaluationResult<?> evaluateExpression(String str, InputSource inputSource) throws XPathExpressionException {
        return (XPathEvaluationResult) evaluateExpression(str, inputSource, XPathEvaluationResult.class);
    }
}
