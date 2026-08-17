package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.axes.LocPathIterator;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathEvaluationResult;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathNodes;
import javax.xml.xpath.XPathVariableResolver;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.XMLSecurityManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XPathImplUtil {
    JdkXmlFeatures featureManager;
    boolean featureSecureProcessing = false;
    XPathFunctionResolver functionResolver;
    boolean overrideDefaultParser;
    JAXPPrefixResolver prefixResolver;
    XPathVariableResolver variableResolver;
    XMLSecurityManager xmlSecMgr;

    public XObject eval(Object obj, XPath xPath) throws TransformerException {
        if (obj == null && (xPath.getExpression() instanceof LocPathIterator)) {
            throw new TransformerException(XPATHMessages.createXPATHMessage("ER_CONTEXT_CAN_NOT_BE_NULL", new Object[0]));
        }
        XPathFunctionResolver xPathFunctionResolver = this.functionResolver;
        XPathContext xPathContext = xPathFunctionResolver != null ? new XPathContext(new JAXPExtensionsProvider(xPathFunctionResolver, this.featureSecureProcessing, this.featureManager)) : new XPathContext();
        xPathContext.setVarStack(new JAXPVariableStack(this.variableResolver));
        Node node = (Node) obj;
        JAXPPrefixResolver jAXPPrefixResolver = this.prefixResolver;
        return node == null ? xPath.execute(xPathContext, -1, jAXPPrefixResolver) : xPath.execute(xPathContext, node, jAXPPrefixResolver);
    }

    public Document getDocument(InputSource inputSource) throws XPathExpressionException {
        requireNonNull(inputSource, "Source");
        try {
            return JdkXmlUtils.getDOMFactory(this.overrideDefaultParser).newDocumentBuilder().parse(inputSource);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new XPathExpressionException(e);
        }
    }

    public Object getResultAsType(XObject xObject, QName qName) throws TransformerException {
        if (qName.equals(XPathConstants.STRING)) {
            return xObject.str();
        }
        if (qName.equals(XPathConstants.NUMBER)) {
            return Double.valueOf(xObject.num());
        }
        if (qName.equals(XPathConstants.BOOLEAN)) {
            return Boolean.valueOf(xObject.bool());
        }
        if (qName.equals(XPathConstants.NODESET)) {
            return xObject.nodelist();
        }
        if (qName.equals(XPathConstants.NODE)) {
            return xObject.nodeset().nextNode();
        }
        w01.a(XPATHMessages.createXPATHMessage("ER_UNSUPPORTED_RETURN_TYPE", new Object[]{qName.toString()}));
        return null;
    }

    public <T> T getXPathResult(XObject xObject, Class<T> cls) throws TransformerException {
        int type = xObject.getType();
        if (type == 1) {
            return cls.cast(new XPathResultImpl(xObject, Boolean.class));
        }
        if (type == 2) {
            return cls.cast(new XPathResultImpl(xObject, Double.class));
        }
        if (type == 3) {
            return cls.cast(new XPathResultImpl(xObject, String.class));
        }
        if (type == 4) {
            return cls.cast(new XPathResultImpl(xObject, XPathNodes.class));
        }
        if (type != 5) {
            return null;
        }
        return cls.cast(new XPathResultImpl(xObject, Node.class));
    }

    public void isSupported(QName qName) {
        requireNonNull(qName, "returnType");
        if (qName.equals(XPathConstants.STRING) || qName.equals(XPathConstants.NUMBER) || qName.equals(XPathConstants.BOOLEAN) || qName.equals(XPathConstants.NODE) || qName.equals(XPathConstants.NODESET)) {
            return;
        }
        w01.a(XPATHMessages.createXPATHMessage("ER_UNSUPPORTED_RETURN_TYPE", new Object[]{qName.toString()}));
    }

    public <T> void isSupportedClassType(Class<T> cls) {
        requireNonNull(cls, "The class type");
        if (cls.isAssignableFrom(Boolean.class) || cls.isAssignableFrom(Double.class) || cls.isAssignableFrom(Integer.class) || cls.isAssignableFrom(Long.class) || cls.isAssignableFrom(String.class) || cls.isAssignableFrom(XPathNodes.class) || cls.isAssignableFrom(Node.class) || cls.isAssignableFrom(XPathEvaluationResult.class)) {
            return;
        }
        w01.a(XPATHMessages.createXPATHMessage("ER_UNSUPPORTED_RETURN_TYPE", new Object[]{cls.toString()}));
    }

    public <T> void requireNonNull(T t, String str) {
        if (t != null) {
            return;
        }
        x0e.a(XPATHMessages.createXPATHMessage("ER_ARG_CANNOT_BE_NULL", new Object[]{str}));
    }
}
