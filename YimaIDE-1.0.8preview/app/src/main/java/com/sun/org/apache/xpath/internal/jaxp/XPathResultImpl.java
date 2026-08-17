package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.Objects;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathEvaluationResult;
import javax.xml.xpath.XPathNodes;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XPathResultImpl<T> implements XPathEvaluationResult<T> {
    int currentIndex;
    Node currentNode;
    XPathEvaluationResult.XPathResultType mapToType;
    double numValue;
    XObject resultObject;
    int resultType;
    String strValue;
    Class<T> type;
    NodeList nodeList = null;
    boolean boolValue = false;
    Node node = null;

    public XPathResultImpl(XObject xObject, Class<T> cls) throws TransformerException {
        this.resultObject = xObject;
        this.resultType = xObject.getType();
        this.type = cls;
        getResult(xObject);
    }

    public static <T> int classToInternalType(Class<T> cls) {
        if (cls.isAssignableFrom(Boolean.class)) {
            return 1;
        }
        if (Number.class.isAssignableFrom(cls)) {
            return 2;
        }
        if (cls.isAssignableFrom(String.class)) {
            return 3;
        }
        if (cls.isAssignableFrom(XPathNodes.class)) {
            return 4;
        }
        return cls.isAssignableFrom(Node.class) ? 5 : -1;
    }

    private void getResult(XObject xObject) throws TransformerException {
        int i = this.resultType;
        if (i == 1) {
            this.boolValue = xObject.bool();
            this.mapToType = XPathEvaluationResult.XPathResultType.BOOLEAN;
            return;
        }
        if (i == 2) {
            this.numValue = xObject.num();
            this.mapToType = XPathEvaluationResult.XPathResultType.NUMBER;
            return;
        }
        if (i == 3) {
            this.strValue = xObject.str();
            this.mapToType = XPathEvaluationResult.XPathResultType.STRING;
        } else if (i == 4) {
            this.mapToType = XPathEvaluationResult.XPathResultType.NODESET;
            this.nodeList = xObject.nodelist();
        } else {
            if (i != 5) {
                return;
            }
            this.mapToType = XPathEvaluationResult.XPathResultType.NODE;
            this.node = xObject.nodeset().nextNode();
        }
    }

    public static <T> T getValue(XObject xObject, Class<T> cls) throws TransformerException {
        Objects.requireNonNull(cls);
        if (cls.isAssignableFrom(XPathEvaluationResult.class)) {
            return cls.cast(new XPathResultImpl(xObject, cls));
        }
        int iClassToInternalType = classToInternalType(cls);
        if (iClassToInternalType == 1) {
            return cls.cast(Boolean.valueOf(xObject.bool()));
        }
        if (iClassToInternalType == 2) {
            if (Double.class.isAssignableFrom(cls)) {
                return cls.cast(Double.valueOf(xObject.num()));
            }
            if (Integer.class.isAssignableFrom(cls)) {
                return cls.cast(Integer.valueOf((int) xObject.num()));
            }
            if (Long.class.isAssignableFrom(cls)) {
                return cls.cast(Long.valueOf((long) xObject.num()));
            }
            return null;
        }
        if (iClassToInternalType == 3) {
            return cls.cast(xObject.str());
        }
        if (iClassToInternalType == 4) {
            return cls.cast(new XPathNodesImpl(xObject.nodelist(), Node.class));
        }
        if (iClassToInternalType != 5) {
            return null;
        }
        try {
            return cls.cast(xObject.nodeset().nextNode());
        } catch (RuntimeException e) {
            throw new TransformerException(e.getMessage(), e.getCause());
        }
    }

    @Override // javax.xml.xpath.XPathEvaluationResult
    public XPathEvaluationResult.XPathResultType type() {
        return this.mapToType;
    }

    @Override // javax.xml.xpath.XPathEvaluationResult
    public T value() {
        Objects.requireNonNull(this.type);
        try {
            return (T) getValue(this.resultObject, this.type);
        } catch (TransformerException e) {
            rc6.a(e);
            return null;
        }
    }
}
