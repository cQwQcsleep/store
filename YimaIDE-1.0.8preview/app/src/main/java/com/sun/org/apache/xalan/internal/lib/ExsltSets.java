package com.sun.org.apache.xalan.internal.lib;

import com.sun.org.apache.xml.internal.utils.DOM2Helper;
import com.sun.org.apache.xpath.internal.NodeSet;
import java.util.HashMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ExsltSets extends ExsltBase {
    public static NodeList difference(NodeList nodeList, NodeList nodeList2) {
        NodeSet nodeSet = new NodeSet(nodeList);
        NodeSet nodeSet2 = new NodeSet(nodeList2);
        NodeSet nodeSet3 = new NodeSet();
        nodeSet3.setShouldCacheNodes(true);
        for (int i = 0; i < nodeSet.getLength(); i++) {
            Node nodeElementAt = nodeSet.elementAt(i);
            if (!nodeSet2.contains(nodeElementAt)) {
                nodeSet3.addElement(nodeElementAt);
            }
        }
        return nodeSet3;
    }

    public static NodeList distinct(NodeList nodeList) {
        NodeSet nodeSet = new NodeSet();
        nodeSet.setShouldCacheNodes(true);
        HashMap map = new HashMap();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeItem = nodeList.item(i);
            String string = ExsltBase.toString(nodeItem);
            if (string == null) {
                nodeSet.addElement(nodeItem);
            } else if (!map.containsKey(string)) {
                map.put(string, nodeItem);
                nodeSet.addElement(nodeItem);
            }
        }
        return nodeSet;
    }

    public static boolean hasSameNode(NodeList nodeList, NodeList nodeList2) {
        NodeSet nodeSet = new NodeSet(nodeList);
        NodeSet nodeSet2 = new NodeSet(nodeList2);
        for (int i = 0; i < nodeSet.getLength(); i++) {
            if (nodeSet2.contains(nodeSet.elementAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static NodeList intersection(NodeList nodeList, NodeList nodeList2) {
        NodeSet nodeSet = new NodeSet(nodeList);
        NodeSet nodeSet2 = new NodeSet(nodeList2);
        NodeSet nodeSet3 = new NodeSet();
        nodeSet3.setShouldCacheNodes(true);
        for (int i = 0; i < nodeSet.getLength(); i++) {
            Node nodeElementAt = nodeSet.elementAt(i);
            if (nodeSet2.contains(nodeElementAt)) {
                nodeSet3.addElement(nodeElementAt);
            }
        }
        return nodeSet3;
    }

    public static NodeList leading(NodeList nodeList, NodeList nodeList2) {
        if (nodeList2.getLength() == 0) {
            return nodeList;
        }
        NodeSet nodeSet = new NodeSet(nodeList);
        NodeSet nodeSet2 = new NodeSet();
        Node nodeItem = nodeList2.item(0);
        if (nodeSet.contains(nodeItem)) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodeItem2 = nodeList.item(i);
                if (DOM2Helper.isNodeAfter(nodeItem2, nodeItem) && !DOM2Helper.isNodeTheSame(nodeItem2, nodeItem)) {
                    nodeSet2.addElement(nodeItem2);
                }
            }
        }
        return nodeSet2;
    }

    public static NodeList trailing(NodeList nodeList, NodeList nodeList2) {
        if (nodeList2.getLength() == 0) {
            return nodeList;
        }
        NodeSet nodeSet = new NodeSet(nodeList);
        NodeSet nodeSet2 = new NodeSet();
        Node nodeItem = nodeList2.item(0);
        if (nodeSet.contains(nodeItem)) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodeItem2 = nodeList.item(i);
                if (DOM2Helper.isNodeAfter(nodeItem, nodeItem2) && !DOM2Helper.isNodeTheSame(nodeItem, nodeItem2)) {
                    nodeSet2.addElement(nodeItem2);
                }
            }
        }
        return nodeSet2;
    }
}
