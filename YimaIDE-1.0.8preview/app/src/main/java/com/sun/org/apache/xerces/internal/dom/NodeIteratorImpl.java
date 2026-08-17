package com.sun.org.apache.xerces.internal.dom;

import defpackage.zi0;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NodeIteratorImpl implements NodeIterator {
    private DocumentImpl fDocument;
    private boolean fEntityReferenceExpansion;
    private NodeFilter fNodeFilter;
    private Node fRoot;
    private int fWhatToShow;
    private boolean fDetach = false;
    private boolean fForward = true;
    private Node fCurrentNode = null;

    public NodeIteratorImpl(DocumentImpl documentImpl, Node node, int i, NodeFilter nodeFilter, boolean z) {
        this.fDocument = documentImpl;
        this.fRoot = node;
        this.fWhatToShow = i;
        this.fNodeFilter = nodeFilter;
        this.fEntityReferenceExpansion = z;
    }

    public boolean acceptNode(Node node) {
        NodeFilter nodeFilter = this.fNodeFilter;
        int i = this.fWhatToShow;
        if (nodeFilter == null) {
            return ((1 << (node.getNodeType() - 1)) & i) != 0;
        }
        return ((1 << (node.getNodeType() - 1)) & i) != 0 && this.fNodeFilter.acceptNode(node) == 1;
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public void detach() {
        this.fDetach = true;
        this.fDocument.removeNodeIterator(this);
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public boolean getExpandEntityReferences() {
        return this.fEntityReferenceExpansion;
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public NodeFilter getFilter() {
        return this.fNodeFilter;
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public Node getRoot() {
        return this.fRoot;
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public int getWhatToShow() {
        return this.fWhatToShow;
    }

    public Node matchNodeOrParent(Node node) {
        Node parentNode = this.fCurrentNode;
        if (parentNode == null) {
            return null;
        }
        while (parentNode != this.fRoot) {
            if (node == parentNode) {
                return parentNode;
            }
            parentNode = parentNode.getParentNode();
        }
        return null;
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public Node nextNode() {
        if (this.fDetach) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            return null;
        }
        if (this.fRoot == null) {
            return null;
        }
        Node nodeNextNode = this.fCurrentNode;
        boolean zAcceptNode = false;
        while (!zAcceptNode) {
            if (this.fForward || nodeNextNode == null) {
                nodeNextNode = (this.fEntityReferenceExpansion || nodeNextNode == null || nodeNextNode.getNodeType() != 5) ? nextNode(nodeNextNode, true) : nextNode(nodeNextNode, false);
            } else {
                nodeNextNode = this.fCurrentNode;
            }
            this.fForward = true;
            if (nodeNextNode == null) {
                return null;
            }
            zAcceptNode = acceptNode(nodeNextNode);
            if (zAcceptNode) {
                this.fCurrentNode = nodeNextNode;
                return nodeNextNode;
            }
        }
        return null;
    }

    @Override // org.w3c.dom.traversal.NodeIterator
    public Node previousNode() {
        if (this.fDetach) {
            zi0.a(11, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_STATE_ERR", null));
            return null;
        }
        if (this.fRoot != null && (nodePreviousNode = this.fCurrentNode) != null) {
            boolean zAcceptNode = false;
            while (!zAcceptNode) {
                Node nodePreviousNode = (!this.fForward || nodePreviousNode == null) ? previousNode(nodePreviousNode) : this.fCurrentNode;
                this.fForward = false;
                if (nodePreviousNode == null) {
                    return null;
                }
                zAcceptNode = acceptNode(nodePreviousNode);
                if (zAcceptNode) {
                    this.fCurrentNode = nodePreviousNode;
                    return nodePreviousNode;
                }
            }
        }
        return null;
    }

    public void removeNode(Node node) {
        Node nodeMatchNodeOrParent;
        if (node == null || (nodeMatchNodeOrParent = matchNodeOrParent(node)) == null) {
            return;
        }
        if (this.fForward) {
            this.fCurrentNode = previousNode(nodeMatchNodeOrParent);
            return;
        }
        Node nodeNextNode = nextNode(nodeMatchNodeOrParent, false);
        if (nodeNextNode != null) {
            this.fCurrentNode = nodeNextNode;
        } else {
            this.fCurrentNode = previousNode(nodeMatchNodeOrParent);
            this.fForward = true;
        }
    }

    public Node previousNode(Node node) {
        if (node == this.fRoot) {
            return null;
        }
        Node previousSibling = node.getPreviousSibling();
        if (previousSibling == null) {
            return node.getParentNode();
        }
        if (previousSibling.hasChildNodes() && (this.fEntityReferenceExpansion || previousSibling.getNodeType() != 5)) {
            while (previousSibling.hasChildNodes()) {
                previousSibling = previousSibling.getLastChild();
            }
        }
        return previousSibling;
    }

    public Node nextNode(Node node, boolean z) {
        if (node == null) {
            return this.fRoot;
        }
        if (z && node.hasChildNodes()) {
            return node.getFirstChild();
        }
        if (node == this.fRoot) {
            return null;
        }
        Node nextSibling = node.getNextSibling();
        if (nextSibling != null) {
            return nextSibling;
        }
        for (Node parentNode = node.getParentNode(); parentNode != null && parentNode != this.fRoot; parentNode = parentNode.getParentNode()) {
            Node nextSibling2 = parentNode.getNextSibling();
            if (nextSibling2 != null) {
                return nextSibling2;
            }
        }
        return null;
    }
}
