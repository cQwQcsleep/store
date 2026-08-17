package com.sun.org.apache.xalan.internal.lib;

import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import com.sun.org.apache.xpath.internal.NodeSet;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.StringTokenizer;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Extensions {
    private Extensions() {
    }

    public static NodeList difference(NodeList nodeList, NodeList nodeList2) {
        return ExsltSets.difference(nodeList, nodeList2);
    }

    public static NodeList distinct(NodeList nodeList) {
        return ExsltSets.distinct(nodeList);
    }

    public static XObject evaluate(ExpressionContext expressionContext, String str) throws SAXNotSupportedException {
        return ExsltDynamic.evaluate(expressionContext, str);
    }

    public static boolean hasSameNodes(NodeList nodeList, NodeList nodeList2) {
        NodeSet nodeSet = new NodeSet(nodeList);
        NodeSet nodeSet2 = new NodeSet(nodeList2);
        if (nodeSet.getLength() != nodeSet2.getLength()) {
            return false;
        }
        for (int i = 0; i < nodeSet.getLength(); i++) {
            if (!nodeSet2.contains(nodeSet.elementAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static NodeList intersection(NodeList nodeList, NodeList nodeList2) {
        return ExsltSets.intersection(nodeList, nodeList2);
    }

    public static NodeSet nodeset(ExpressionContext expressionContext, Object obj) {
        String str;
        if (obj instanceof NodeIterator) {
            return new NodeSet((NodeIterator) obj);
        }
        if (obj instanceof String) {
            str = (String) obj;
        } else if (obj instanceof Boolean) {
            str = new XBoolean(((Boolean) obj).booleanValue()).str();
        } else {
            str = obj instanceof Double ? new XNumber(((Double) obj).doubleValue()).str() : obj.toString();
        }
        Document dOMDocument = JdkXmlUtils.getDOMDocument();
        Text textCreateTextNode = dOMDocument.createTextNode(str);
        DocumentFragment documentFragmentCreateDocumentFragment = dOMDocument.createDocumentFragment();
        documentFragmentCreateDocumentFragment.appendChild(textCreateTextNode);
        return new NodeSet(documentFragmentCreateDocumentFragment);
    }

    public static NodeList tokenize(String str, String str2) {
        Document dOMDocument = JdkXmlUtils.getDOMDocument();
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        NodeSet nodeSet = new NodeSet();
        synchronized (dOMDocument) {
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    nodeSet.addNode(dOMDocument.createTextNode(stringTokenizer.nextToken()));
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return nodeSet;
    }

    public static NodeList tokenize(String str) {
        return tokenize(str, " \t\n\r");
    }
}
