package com.sun.org.apache.xerces.internal.dom;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeepNodeListImpl implements NodeList {
    protected int changes;
    protected boolean enableNS;
    protected List<Node> nodes;
    protected String nsName;
    protected NodeImpl rootNode;
    protected String tagName;

    public DeepNodeListImpl(NodeImpl nodeImpl, String str) {
        this.changes = 0;
        this.enableNS = false;
        this.rootNode = nodeImpl;
        this.tagName = str;
        this.nodes = new ArrayList();
    }

    @Override // org.w3c.dom.NodeList
    public int getLength() {
        item(Integer.MAX_VALUE);
        return this.nodes.size();
    }

    @Override // org.w3c.dom.NodeList
    public Node item(int i) {
        if (this.rootNode.changes() != this.changes) {
            this.nodes = new ArrayList();
            this.changes = this.rootNode.changes();
        }
        int size = this.nodes.size();
        if (i < size) {
            return this.nodes.get(i);
        }
        Node nodeNextMatchingElementAfter = size == 0 ? this.rootNode : (NodeImpl) this.nodes.get(size - 1);
        while (nodeNextMatchingElementAfter != null && i >= this.nodes.size()) {
            nodeNextMatchingElementAfter = nextMatchingElementAfter(nodeNextMatchingElementAfter);
            if (nodeNextMatchingElementAfter != null) {
                this.nodes.add(nodeNextMatchingElementAfter);
            }
        }
        return nodeNextMatchingElementAfter;
    }

    public Node nextMatchingElementAfter(Node node) {
        Node nextSibling;
        String str;
        String str2;
        String str3;
        while (true) {
            Node nextSibling2 = null;
            if (node == null) {
                return null;
            }
            if (node.hasChildNodes()) {
                node = node.getFirstChild();
            } else if (node == this.rootNode || (nextSibling = node.getNextSibling()) == null) {
                while (node != this.rootNode && (nextSibling2 = node.getNextSibling()) == null) {
                    node = node.getParentNode();
                }
                node = nextSibling2;
            } else {
                node = nextSibling;
            }
            if (node != this.rootNode && node != null && node.getNodeType() == 1) {
                boolean z = this.enableNS;
                String str4 = this.tagName;
                if (z) {
                    if (str4.equals("*")) {
                        String str5 = this.nsName;
                        if (str5 == null || !str5.equals("*")) {
                            ElementImpl elementImpl = (ElementImpl) node;
                            if ((this.nsName != null || elementImpl.getNamespaceURI() != null) && ((str = this.nsName) == null || !str.equals(elementImpl.getNamespaceURI()))) {
                            }
                        }
                        return node;
                    }
                    ElementImpl elementImpl2 = (ElementImpl) node;
                    if (elementImpl2.getLocalName() != null && elementImpl2.getLocalName().equals(this.tagName) && (((str2 = this.nsName) != null && str2.equals("*")) || ((this.nsName == null && elementImpl2.getNamespaceURI() == null) || ((str3 = this.nsName) != null && str3.equals(elementImpl2.getNamespaceURI()))))) {
                        return node;
                    }
                } else if (str4.equals("*") || ((ElementImpl) node).getTagName().equals(this.tagName)) {
                    return node;
                }
            }
        }
    }

    public DeepNodeListImpl(NodeImpl nodeImpl, String str, String str2) {
        this(nodeImpl, str2);
        this.nsName = (str == null || str.length() == 0) ? null : str;
        this.enableNS = true;
    }
}
