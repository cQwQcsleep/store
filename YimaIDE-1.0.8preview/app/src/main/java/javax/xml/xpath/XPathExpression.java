package javax.xml.xpath;

import javax.xml.namespace.QName;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XPathExpression {
    Object evaluate(Object obj, QName qName) throws XPathExpressionException;

    Object evaluate(InputSource inputSource, QName qName) throws XPathExpressionException;

    String evaluate(Object obj) throws XPathExpressionException;

    String evaluate(InputSource inputSource) throws XPathExpressionException;

    default <T> T evaluateExpression(Object obj, Class<T> cls) throws XPathExpressionException {
        return cls.cast(evaluate(obj, XPathEvaluationResult.XPathResultType.getQNameType(cls)));
    }

    default XPathEvaluationResult<?> evaluateExpression(Object obj) throws XPathExpressionException {
        return (XPathEvaluationResult) evaluateExpression(obj, XPathEvaluationResult.class);
    }

    default <T> T evaluateExpression(InputSource inputSource, Class<T> cls) throws XPathExpressionException {
        return cls.cast(evaluate(inputSource, XPathEvaluationResult.XPathResultType.getQNameType(cls)));
    }

    default XPathEvaluationResult<?> evaluateExpression(InputSource inputSource) throws XPathExpressionException {
        return (XPathEvaluationResult) evaluateExpression(inputSource, XPathEvaluationResult.class);
    }
}
