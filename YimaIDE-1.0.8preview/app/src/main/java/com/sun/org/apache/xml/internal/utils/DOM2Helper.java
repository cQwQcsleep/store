package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeProxy;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class DOM2Helper {
    private DOM2Helper() {
    }

    public static String getLocalNameOfNode(Node node) {
        String localName = node.getLocalName();
        return localName == null ? getLocalNameOfNodeFallback(node) : localName;
    }

    private static String getLocalNameOfNodeFallback(Node node) {
        String nodeName = node.getNodeName();
        int iIndexOf = nodeName.indexOf(58);
        return iIndexOf < 0 ? nodeName : nodeName.substring(iIndexOf + 1);
    }

    public static String getNamespaceOfNode(Node node) {
        return node.getNamespaceURI();
    }

    public static Node getParentOfNode(Node node) {
        Node parentNode = node.getParentNode();
        return (parentNode == null && 2 == node.getNodeType()) ? ((Attr) node).getOwnerElement() : parentNode;
    }

    public static boolean isNodeAfter(Node node, Node node2) {
        if (node != node2 && !isNodeTheSame(node, node2)) {
            Node parentOfNode = getParentOfNode(node);
            Node parentOfNode2 = getParentOfNode(node2);
            if (parentOfNode != parentOfNode2 && !isNodeTheSame(parentOfNode, parentOfNode2)) {
                int i = 2;
                int i2 = 2;
                while (parentOfNode != null) {
                    i2++;
                    parentOfNode = getParentOfNode(parentOfNode);
                }
                while (parentOfNode2 != null) {
                    i++;
                    parentOfNode2 = getParentOfNode(parentOfNode2);
                }
                if (i2 < i) {
                    int i3 = i - i2;
                    for (int i4 = 0; i4 < i3; i4++) {
                        node2 = getParentOfNode(node2);
                    }
                } else if (i2 > i) {
                    int i5 = i2 - i;
                    for (int i6 = 0; i6 < i5; i6++) {
                        node = getParentOfNode(node);
                    }
                }
                Node node3 = null;
                Node node4 = null;
                while (node != null) {
                    if (node == node2 || isNodeTheSame(node, node2)) {
                        if (node3 == null) {
                            return i2 < i;
                        }
                        return isNodeAfterSibling(node, node3, node4);
                    }
                    node3 = node;
                    node = getParentOfNode(node);
                    node4 = node2;
                    node2 = getParentOfNode(node2);
                }
            } else if (parentOfNode != null) {
                return isNodeAfterSibling(parentOfNode, node, node2);
            }
        }
        return true;
    }

    private static boolean isNodeAfterSibling(Node node, Node node2, Node node3) {
        short nodeType = node2.getNodeType();
        short nodeType2 = node3.getNodeType();
        if (2 != nodeType && 2 == nodeType2) {
            return false;
        }
        if (2 == nodeType && 2 != nodeType2) {
            return true;
        }
        if (2 == nodeType) {
            NamedNodeMap attributes = node.getAttributes();
            int length = attributes.getLength();
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < length; i++) {
                Node nodeItem = attributes.item(i);
                if (node2 == nodeItem || isNodeTheSame(node2, nodeItem)) {
                    if (z) {
                        return false;
                    }
                    z2 = true;
                } else if (node3 == nodeItem || isNodeTheSame(node3, nodeItem)) {
                    if (z2) {
                        return true;
                    }
                    z = true;
                }
            }
        } else {
            boolean z3 = false;
            boolean z4 = false;
            for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                if (node2 == firstChild || isNodeTheSame(node2, firstChild)) {
                    if (z3) {
                        return false;
                    }
                    z4 = true;
                } else if (node3 == firstChild || isNodeTheSame(node3, firstChild)) {
                    if (z4) {
                        return true;
                    }
                    z3 = true;
                }
            }
        }
        return false;
    }

    public static boolean isNodeTheSame(Node node, Node node2) {
        if ((node instanceof DTMNodeProxy) && (node2 instanceof DTMNodeProxy)) {
            return ((DTMNodeProxy) node).equals(node2);
        }
        return node == node2;
    }
}
