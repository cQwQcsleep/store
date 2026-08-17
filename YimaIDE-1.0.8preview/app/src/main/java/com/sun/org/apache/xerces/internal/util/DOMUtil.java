package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.dom.AttrImpl;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xerces.internal.impl.xs.opti.ElementImpl;
import com.sun.org.apache.xerces.internal.impl.xs.opti.NodeImpl;
import java.util.Map;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMUtil {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r13v1, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r4v11, types: [org.w3c.dom.ProcessingInstruction] */
    /* JADX WARN: Type inference failed for: r4v13, types: [org.w3c.dom.Comment] */
    /* JADX WARN: Type inference failed for: r4v2, types: [org.w3c.dom.Element] */
    /* JADX WARN: Type inference failed for: r4v3, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r4v5, types: [org.w3c.dom.Text] */
    /* JADX WARN: Type inference failed for: r4v7, types: [org.w3c.dom.CDATASection] */
    /* JADX WARN: Type inference failed for: r4v9, types: [org.w3c.dom.EntityReference] */
    public static void copyInto(Node node, Node node2) throws DOMException {
        ?? CreateElement;
        Document ownerDocument = node2.getOwnerDocument();
        boolean z = ownerDocument instanceof DocumentImpl;
        Node firstChild = node;
        Node parentNode = firstChild;
        while (firstChild != null) {
            short nodeType = firstChild.getNodeType();
            if (nodeType == 1) {
                CreateElement = ownerDocument.createElement(firstChild.getNodeName());
                NamedNodeMap attributes = firstChild.getAttributes();
                int length = attributes.getLength();
                for (int i = 0; i < length; i++) {
                    Attr attr = (Attr) attributes.item(i);
                    String nodeName = attr.getNodeName();
                    CreateElement.setAttribute(nodeName, attr.getNodeValue());
                    if (z && !attr.getSpecified()) {
                        ((AttrImpl) CreateElement.getAttributeNode(nodeName)).setSpecified(false);
                    }
                }
            } else if (nodeType == 3) {
                CreateElement = ownerDocument.createTextNode(firstChild.getNodeValue());
            } else if (nodeType == 4) {
                CreateElement = ownerDocument.createCDATASection(firstChild.getNodeValue());
            } else if (nodeType == 5) {
                CreateElement = ownerDocument.createEntityReference(firstChild.getNodeName());
            } else if (nodeType == 7) {
                CreateElement = ownerDocument.createProcessingInstruction(firstChild.getNodeName(), firstChild.getNodeValue());
            } else {
                if (nodeType != 8) {
                    throw new IllegalArgumentException("can't copy node type, " + ((int) nodeType) + " (" + firstChild.getNodeName() + ')');
                }
                CreateElement = ownerDocument.createComment(firstChild.getNodeValue());
            }
            node2.appendChild(CreateElement);
            if (firstChild.hasChildNodes()) {
                parentNode = firstChild;
                firstChild = firstChild.getFirstChild();
                node2 = CreateElement;
            } else {
                firstChild = firstChild.getNextSibling();
                node2 = node2;
                while (firstChild == null && parentNode != node) {
                    firstChild = parentNode.getNextSibling();
                    parentNode = parentNode.getParentNode();
                    node2 = node2.getParentNode();
                }
            }
        }
    }

    public static DOMException createDOMException(short s, Throwable th) {
        DOMException dOMException = new DOMException(s, th != null ? th.getMessage() : null);
        if (th != null) {
            dOMException.initCause(th);
        }
        return dOMException;
    }

    public static LSException createLSException(short s, Throwable th) {
        LSException lSException = new LSException(s, th != null ? th.getMessage() : null);
        if (th != null) {
            lSException.initCause(th);
        }
        return lSException;
    }

    public static String getAnnotation(Node node) {
        if (node instanceof ElementImpl) {
            return ((ElementImpl) node).getAnnotation();
        }
        return null;
    }

    public static Attr getAttr(Element element, String str) {
        return element.getAttributeNode(str);
    }

    public static Attr getAttrNS(Element element, String str, String str2) {
        return element.getAttributeNodeNS(str, str2);
    }

    public static String getAttrValue(Element element, String str) {
        return element.getAttribute(str);
    }

    public static String getAttrValueNS(Element element, String str, String str2) {
        return element.getAttributeNS(str, str2);
    }

    public static Attr[] getAttrs(Element element) {
        NamedNodeMap attributes = element.getAttributes();
        Attr[] attrArr = new Attr[attributes.getLength()];
        for (int i = 0; i < attributes.getLength(); i++) {
            attrArr[i] = (Attr) attributes.item(i);
        }
        return attrArr;
    }

    public static String getChildText(Node node) {
        if (node == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            short nodeType = firstChild.getNodeType();
            if (nodeType == 3) {
                stringBuffer.append(firstChild.getNodeValue());
            } else if (nodeType == 4) {
                stringBuffer.append(getChildText(firstChild));
            }
        }
        return stringBuffer.toString();
    }

    public static Document getDocument(Node node) {
        return node.getOwnerDocument();
    }

    public static Element getFirstChildElement(Node node, String str, String str2, String str3) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1) {
                Element element = (Element) firstChild;
                if (element.getNodeName().equals(str) && element.getAttribute(str2).equals(str3)) {
                    return element;
                }
            }
        }
        return null;
    }

    public static Element getFirstChildElementNS(Node node, String[][] strArr) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1) {
                for (int i = 0; i < strArr.length; i++) {
                    String namespaceURI = firstChild.getNamespaceURI();
                    if (namespaceURI != null && namespaceURI.equals(strArr[i][0]) && firstChild.getLocalName().equals(strArr[i][1])) {
                        return (Element) firstChild;
                    }
                }
            }
        }
        return null;
    }

    public static Element getFirstVisibleChildElement(Node node) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1 && !isHidden(firstChild)) {
                return (Element) firstChild;
            }
        }
        return null;
    }

    public static Element getLastChildElement(Node node, String str, String str2, String str3) {
        for (Node lastChild = node.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            if (lastChild.getNodeType() == 1) {
                Element element = (Element) lastChild;
                if (element.getNodeName().equals(str) && element.getAttribute(str2).equals(str3)) {
                    return element;
                }
            }
        }
        return null;
    }

    public static Element getLastChildElementNS(Node node, String[][] strArr) {
        for (Node lastChild = node.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            if (lastChild.getNodeType() == 1) {
                for (int i = 0; i < strArr.length; i++) {
                    String namespaceURI = lastChild.getNamespaceURI();
                    if (namespaceURI != null && namespaceURI.equals(strArr[i][0]) && lastChild.getLocalName().equals(strArr[i][1])) {
                        return (Element) lastChild;
                    }
                }
            }
        }
        return null;
    }

    public static Element getLastVisibleChildElement(Node node) {
        for (Node lastChild = node.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            if (lastChild.getNodeType() == 1 && !isHidden(lastChild)) {
                return (Element) lastChild;
            }
        }
        return null;
    }

    public static String getLocalName(Node node) {
        String localName = node.getLocalName();
        return localName != null ? localName : node.getNodeName();
    }

    public static String getName(Node node) {
        return node.getNodeName();
    }

    public static String getNamespaceURI(Node node) {
        return node.getNamespaceURI();
    }

    public static Element getNextSiblingElement(Node node, String str, String str2, String str3) {
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getNodeType() == 1) {
                Element element = (Element) nextSibling;
                if (element.getNodeName().equals(str) && element.getAttribute(str2).equals(str3)) {
                    return element;
                }
            }
        }
        return null;
    }

    public static Element getNextSiblingElementNS(Node node, String[][] strArr) {
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getNodeType() == 1) {
                for (int i = 0; i < strArr.length; i++) {
                    String namespaceURI = nextSibling.getNamespaceURI();
                    if (namespaceURI != null && namespaceURI.equals(strArr[i][0]) && nextSibling.getLocalName().equals(strArr[i][1])) {
                        return (Element) nextSibling;
                    }
                }
            }
        }
        return null;
    }

    public static Element getNextVisibleSiblingElement(Node node) {
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getNodeType() == 1 && !isHidden(nextSibling)) {
                return (Element) nextSibling;
            }
        }
        return null;
    }

    public static Element getParent(Element element) {
        Node parentNode = element.getParentNode();
        if (parentNode instanceof Element) {
            return (Element) parentNode;
        }
        return null;
    }

    public static String getPrefix(Node node) {
        return node.getPrefix();
    }

    public static Element getRoot(Document document) {
        return document.getDocumentElement();
    }

    public static String getSyntheticAnnotation(Node node) {
        if (node instanceof ElementImpl) {
            return ((ElementImpl) node).getSyntheticAnnotation();
        }
        return null;
    }

    public static String getValue(Attr attr) {
        return attr.getValue();
    }

    public static boolean isHidden(Node node) {
        if (node instanceof NodeImpl) {
            return ((NodeImpl) node).getReadOnly();
        }
        if (node instanceof com.sun.org.apache.xerces.internal.dom.NodeImpl) {
            return ((com.sun.org.apache.xerces.internal.dom.NodeImpl) node).getReadOnly();
        }
        return false;
    }

    public static void setHidden(Node node) {
        if (node instanceof NodeImpl) {
            ((NodeImpl) node).setReadOnly(true, false);
        } else if (node instanceof com.sun.org.apache.xerces.internal.dom.NodeImpl) {
            ((com.sun.org.apache.xerces.internal.dom.NodeImpl) node).setReadOnly(true, false);
        }
    }

    public static void setVisible(Node node) {
        if (node instanceof NodeImpl) {
            ((NodeImpl) node).setReadOnly(false, false);
        } else if (node instanceof com.sun.org.apache.xerces.internal.dom.NodeImpl) {
            ((com.sun.org.apache.xerces.internal.dom.NodeImpl) node).setReadOnly(false, false);
        }
    }

    public static void setVisible(Node node, Map<Node, String> map) {
        if (node instanceof NodeImpl) {
            ((NodeImpl) node).setReadOnly(false, false);
        } else {
            map.remove(node);
        }
    }

    public static void setHidden(Node node, Map<Node, String> map) {
        if (node instanceof NodeImpl) {
            ((NodeImpl) node).setReadOnly(true, false);
        } else {
            map.put(node, "");
        }
    }

    public static boolean isHidden(Node node, Map<Node, String> map) {
        if (node instanceof NodeImpl) {
            return ((NodeImpl) node).getReadOnly();
        }
        return map.containsKey(node);
    }

    public static Element getFirstVisibleChildElement(Node node, Map<Node, String> map) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1 && !isHidden(firstChild, map)) {
                return (Element) firstChild;
            }
        }
        return null;
    }

    public static Element getLastVisibleChildElement(Node node, Map<Node, String> map) {
        for (Node lastChild = node.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            if (lastChild.getNodeType() == 1 && !isHidden(lastChild, map)) {
                return (Element) lastChild;
            }
        }
        return null;
    }

    public static Element getNextVisibleSiblingElement(Node node, Map<Node, String> map) {
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getNodeType() == 1 && !isHidden(nextSibling, map)) {
                return (Element) nextSibling;
            }
        }
        return null;
    }

    public static Element getFirstChildElement(Node node, String str) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1 && firstChild.getNodeName().equals(str)) {
                return (Element) firstChild;
            }
        }
        return null;
    }

    public static Element getLastChildElement(Node node, String str) {
        for (Node lastChild = node.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            if (lastChild.getNodeType() == 1 && lastChild.getNodeName().equals(str)) {
                return (Element) lastChild;
            }
        }
        return null;
    }

    public static Element getNextSiblingElement(Node node, String str) {
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getNodeType() == 1 && nextSibling.getNodeName().equals(str)) {
                return (Element) nextSibling;
            }
        }
        return null;
    }

    public static Element getFirstChildElement(Node node, String[] strArr) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1) {
                for (String str : strArr) {
                    if (firstChild.getNodeName().equals(str)) {
                        return (Element) firstChild;
                    }
                }
            }
        }
        return null;
    }

    public static Element getLastChildElement(Node node, String[] strArr) {
        for (Node lastChild = node.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            if (lastChild.getNodeType() == 1) {
                for (String str : strArr) {
                    if (lastChild.getNodeName().equals(str)) {
                        return (Element) lastChild;
                    }
                }
            }
        }
        return null;
    }

    public static Element getNextSiblingElement(Node node, String[] strArr) {
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getNodeType() == 1) {
                for (String str : strArr) {
                    if (nextSibling.getNodeName().equals(str)) {
                        return (Element) nextSibling;
                    }
                }
            }
        }
        return null;
    }

    public static Element getFirstChildElement(Node node) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1) {
                return (Element) firstChild;
            }
        }
        return null;
    }

    public static Element getLastChildElement(Node node) {
        for (Node lastChild = node.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            if (lastChild.getNodeType() == 1) {
                return (Element) lastChild;
            }
        }
        return null;
    }

    public static Element getNextSiblingElement(Node node) {
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getNodeType() == 1) {
                return (Element) nextSibling;
            }
        }
        return null;
    }

    public static Element getFirstChildElementNS(Node node, String str, String str2) {
        String namespaceURI;
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if (firstChild.getNodeType() == 1 && (namespaceURI = firstChild.getNamespaceURI()) != null && namespaceURI.equals(str) && firstChild.getLocalName().equals(str2)) {
                return (Element) firstChild;
            }
        }
        return null;
    }

    public static Element getLastChildElementNS(Node node, String str, String str2) {
        String namespaceURI;
        for (Node lastChild = node.getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            if (lastChild.getNodeType() == 1 && (namespaceURI = lastChild.getNamespaceURI()) != null && namespaceURI.equals(str) && lastChild.getLocalName().equals(str2)) {
                return (Element) lastChild;
            }
        }
        return null;
    }

    public static Element getNextSiblingElementNS(Node node, String str, String str2) {
        String namespaceURI;
        for (Node nextSibling = node.getNextSibling(); nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.getNodeType() == 1 && (namespaceURI = nextSibling.getNamespaceURI()) != null && namespaceURI.equals(str) && nextSibling.getLocalName().equals(str2)) {
                return (Element) nextSibling;
            }
        }
        return null;
    }
}
