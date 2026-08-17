package com.sun.org.apache.xalan.internal.lib;

import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import com.sun.org.apache.xalan.internal.res.XSLMessages;
import com.sun.org.apache.xpath.internal.NodeSet;
import com.sun.org.apache.xpath.internal.NodeSetDTM;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XNodeSet;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ExsltDynamic extends ExsltBase {
    public static final String EXSL_URI = "http://exslt.org/common";

    public static NodeList closure(ExpressionContext expressionContext, NodeList nodeList, String str) throws SAXNotSupportedException {
        if (!(expressionContext instanceof XPathContext.XPathExpressionContext)) {
            throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[]{expressionContext}));
        }
        XPathContext xPathContext = ((XPathContext.XPathExpressionContext) expressionContext).getXPathContext();
        if (str == null || str.length() == 0) {
            return new NodeSet();
        }
        NodeSet nodeSet = new NodeSet();
        nodeSet.setShouldCacheNodes(true);
        while (true) {
            NodeSet nodeSet2 = new NodeSet();
            NodeSetDTM nodeSetDTM = new NodeSetDTM(nodeList, xPathContext);
            xPathContext.pushContextNodeList(nodeSetDTM);
            for (int i = 0; i < nodeList.getLength(); i++) {
                int iItem = nodeSetDTM.item(i);
                xPathContext.pushCurrentNode(iItem);
                try {
                    XObject xObjectExecute = new XPath(str, xPathContext.getSAXLocator(), xPathContext.getNamespaceContext(), 0).execute(xPathContext, iItem, xPathContext.getNamespaceContext());
                    if (!(xObjectExecute instanceof XNodeSet)) {
                        xPathContext.popCurrentNode();
                        xPathContext.popContextNodeList();
                        return new NodeSet();
                    }
                    NodeList nodelist = ((XNodeSet) xObjectExecute).nodelist();
                    for (int i2 = 0; i2 < nodelist.getLength(); i2++) {
                        Node nodeItem = nodelist.item(i2);
                        if (!nodeSet2.contains(nodeItem)) {
                            nodeSet2.addNode(nodeItem);
                        }
                    }
                    xPathContext.popCurrentNode();
                } catch (TransformerException unused) {
                    xPathContext.popCurrentNode();
                    xPathContext.popContextNodeList();
                    return new NodeSet();
                }
            }
            xPathContext.popContextNodeList();
            for (int i3 = 0; i3 < nodeSet2.getLength(); i3++) {
                Node nodeItem2 = nodeSet2.item(i3);
                if (!nodeSet.contains(nodeItem2)) {
                    nodeSet.addNode(nodeItem2);
                }
            }
            if (nodeSet2.getLength() <= 0) {
                return nodeSet;
            }
            nodeList = nodeSet2;
        }
    }

    public static XObject evaluate(ExpressionContext expressionContext, String str) throws SAXNotSupportedException {
        if (!(expressionContext instanceof XPathContext.XPathExpressionContext)) {
            throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[]{expressionContext}));
        }
        XPathContext xPathContext = null;
        try {
            xPathContext = ((XPathContext.XPathExpressionContext) expressionContext).getXPathContext();
            return new XPath(str, xPathContext.getSAXLocator(), xPathContext.getNamespaceContext(), 0).execute(xPathContext, expressionContext.getContextNode(), xPathContext.getNamespaceContext());
        } catch (TransformerException unused) {
            return new XNodeSet(xPathContext.getDTMManager());
        }
    }

    public static NodeList map(ExpressionContext expressionContext, NodeList nodeList, String str) throws SAXNotSupportedException {
        if (!(expressionContext instanceof XPathContext.XPathExpressionContext)) {
            throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[]{expressionContext}));
        }
        XPathContext xPathContext = ((XPathContext.XPathExpressionContext) expressionContext).getXPathContext();
        if (str == null || str.length() == 0) {
            return new NodeSet();
        }
        NodeSetDTM nodeSetDTM = new NodeSetDTM(nodeList, xPathContext);
        xPathContext.pushContextNodeList(nodeSetDTM);
        NodeSet nodeSet = new NodeSet();
        nodeSet.setShouldCacheNodes(true);
        Document dOMDocument = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            int iItem = nodeSetDTM.item(i);
            xPathContext.pushCurrentNode(iItem);
            try {
                XObject xObjectExecute = new XPath(str, xPathContext.getSAXLocator(), xPathContext.getNamespaceContext(), 0).execute(xPathContext, iItem, xPathContext.getNamespaceContext());
                if (xObjectExecute instanceof XNodeSet) {
                    NodeList nodelist = ((XNodeSet) xObjectExecute).nodelist();
                    for (int i2 = 0; i2 < nodelist.getLength(); i2++) {
                        Node nodeItem = nodelist.item(i2);
                        if (!nodeSet.contains(nodeItem)) {
                            nodeSet.addNode(nodeItem);
                        }
                    }
                } else {
                    if (dOMDocument == null) {
                        dOMDocument = JdkXmlUtils.getDOMDocument();
                    }
                    Element elementCreateElementNS = xObjectExecute instanceof XNumber ? dOMDocument.createElementNS("http://exslt.org/common", "exsl:number") : xObjectExecute instanceof XBoolean ? dOMDocument.createElementNS("http://exslt.org/common", "exsl:boolean") : dOMDocument.createElementNS("http://exslt.org/common", "exsl:string");
                    elementCreateElementNS.appendChild(dOMDocument.createTextNode(xObjectExecute.str()));
                    nodeSet.addNode(elementCreateElementNS);
                }
                xPathContext.popCurrentNode();
            } catch (Exception unused) {
                xPathContext.popCurrentNode();
                xPathContext.popContextNodeList();
                return new NodeSet();
            }
        }
        xPathContext.popContextNodeList();
        return nodeSet;
    }

    public static double max(ExpressionContext expressionContext, NodeList nodeList, String str) throws SAXNotSupportedException {
        if (!(expressionContext instanceof XPathContext.XPathExpressionContext)) {
            throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[]{expressionContext}));
        }
        XPathContext xPathContext = ((XPathContext.XPathExpressionContext) expressionContext).getXPathContext();
        if (str == null || str.length() == 0) {
            return Double.NaN;
        }
        NodeSetDTM nodeSetDTM = new NodeSetDTM(nodeList, xPathContext);
        xPathContext.pushContextNodeList(nodeSetDTM);
        double d = -1.7976931348623157E308d;
        for (int i = 0; i < nodeSetDTM.getLength(); i++) {
            int iItem = nodeSetDTM.item(i);
            xPathContext.pushCurrentNode(iItem);
            try {
                double dNum = new XPath(str, xPathContext.getSAXLocator(), xPathContext.getNamespaceContext(), 0).execute(xPathContext, iItem, xPathContext.getNamespaceContext()).num();
                xPathContext.popCurrentNode();
                if (dNum > d) {
                    d = dNum;
                }
            } catch (TransformerException unused) {
                xPathContext.popCurrentNode();
                xPathContext.popContextNodeList();
                return Double.NaN;
            }
        }
        xPathContext.popContextNodeList();
        return d;
    }

    public static double min(ExpressionContext expressionContext, NodeList nodeList, String str) throws SAXNotSupportedException {
        if (!(expressionContext instanceof XPathContext.XPathExpressionContext)) {
            throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[]{expressionContext}));
        }
        XPathContext xPathContext = ((XPathContext.XPathExpressionContext) expressionContext).getXPathContext();
        if (str == null || str.length() == 0) {
            return Double.NaN;
        }
        NodeSetDTM nodeSetDTM = new NodeSetDTM(nodeList, xPathContext);
        xPathContext.pushContextNodeList(nodeSetDTM);
        double d = Double.MAX_VALUE;
        for (int i = 0; i < nodeList.getLength(); i++) {
            int iItem = nodeSetDTM.item(i);
            xPathContext.pushCurrentNode(iItem);
            try {
                double dNum = new XPath(str, xPathContext.getSAXLocator(), xPathContext.getNamespaceContext(), 0).execute(xPathContext, iItem, xPathContext.getNamespaceContext()).num();
                xPathContext.popCurrentNode();
                if (dNum < d) {
                    d = dNum;
                }
            } catch (TransformerException unused) {
                xPathContext.popCurrentNode();
                xPathContext.popContextNodeList();
                return Double.NaN;
            }
        }
        xPathContext.popContextNodeList();
        return d;
    }

    public static double sum(ExpressionContext expressionContext, NodeList nodeList, String str) throws SAXNotSupportedException {
        if (!(expressionContext instanceof XPathContext.XPathExpressionContext)) {
            throw new SAXNotSupportedException(XSLMessages.createMessage("ER_INVALID_CONTEXT_PASSED", new Object[]{expressionContext}));
        }
        XPathContext xPathContext = ((XPathContext.XPathExpressionContext) expressionContext).getXPathContext();
        if (str == null || str.length() == 0) {
            return Double.NaN;
        }
        NodeSetDTM nodeSetDTM = new NodeSetDTM(nodeList, xPathContext);
        xPathContext.pushContextNodeList(nodeSetDTM);
        double d = XPath.MATCH_SCORE_QNAME;
        for (int i = 0; i < nodeList.getLength(); i++) {
            int iItem = nodeSetDTM.item(i);
            xPathContext.pushCurrentNode(iItem);
            try {
                double dNum = new XPath(str, xPathContext.getSAXLocator(), xPathContext.getNamespaceContext(), 0).execute(xPathContext, iItem, xPathContext.getNamespaceContext()).num();
                xPathContext.popCurrentNode();
                d += dNum;
            } catch (TransformerException unused) {
                xPathContext.popCurrentNode();
                xPathContext.popContextNodeList();
                return Double.NaN;
            }
        }
        xPathContext.popContextNodeList();
        return d;
    }
}
