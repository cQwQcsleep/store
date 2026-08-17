package com.sun.org.apache.xerces.internal.dom;

import defpackage.zi0;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeWalkerImpl implements TreeWalker {
    Node fCurrentNode;
    private boolean fEntityReferenceExpansion;
    NodeFilter fNodeFilter;
    Node fRoot;
    int fWhatToShow;

    public TreeWalkerImpl(Node node, int i, NodeFilter nodeFilter, boolean z) {
        this.fCurrentNode = node;
        this.fRoot = node;
        this.fWhatToShow = i;
        this.fNodeFilter = nodeFilter;
        this.fEntityReferenceExpansion = z;
    }

    public short acceptNode(Node node) {
        NodeFilter nodeFilter = this.fNodeFilter;
        int i = this.fWhatToShow;
        if (nodeFilter == null) {
            return ((1 << (node.getNodeType() - 1)) & i) != 0 ? (short) 1 : (short) 3;
        }
        if (((1 << (node.getNodeType() - 1)) & i) != 0) {
            return this.fNodeFilter.acceptNode(node);
        }
        return (short) 3;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node firstChild() {
        Node node = this.fCurrentNode;
        if (node == null) {
            return null;
        }
        Node firstChild = getFirstChild(node);
        if (firstChild != null) {
            this.fCurrentNode = firstChild;
        }
        return firstChild;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node getCurrentNode() {
        return this.fCurrentNode;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public boolean getExpandEntityReferences() {
        return this.fEntityReferenceExpansion;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public NodeFilter getFilter() {
        return this.fNodeFilter;
    }

    public Node getFirstChild(Node node) {
        Node firstChild;
        if (node == null) {
            return null;
        }
        if ((!this.fEntityReferenceExpansion && node.getNodeType() == 5) || (firstChild = node.getFirstChild()) == null) {
            return null;
        }
        short sAcceptNode = acceptNode(firstChild);
        if (sAcceptNode == 1) {
            return firstChild;
        }
        if (sAcceptNode != 3 || !firstChild.hasChildNodes()) {
            return getNextSibling(firstChild, node);
        }
        Node firstChild2 = getFirstChild(firstChild);
        return firstChild2 == null ? getNextSibling(firstChild, node) : firstChild2;
    }

    public Node getLastChild(Node node) {
        Node lastChild;
        if (node == null) {
            return null;
        }
        if ((!this.fEntityReferenceExpansion && node.getNodeType() == 5) || (lastChild = node.getLastChild()) == null) {
            return null;
        }
        short sAcceptNode = acceptNode(lastChild);
        if (sAcceptNode == 1) {
            return lastChild;
        }
        if (sAcceptNode != 3 || !lastChild.hasChildNodes()) {
            return getPreviousSibling(lastChild, node);
        }
        Node lastChild2 = getLastChild(lastChild);
        return lastChild2 == null ? getPreviousSibling(lastChild, node) : lastChild2;
    }

    public Node getNextSibling(Node node, Node node2) {
        Node firstChild;
        if (node == null || node == node2) {
            return null;
        }
        Node nextSibling = node.getNextSibling();
        if (nextSibling != null) {
            short sAcceptNode = acceptNode(nextSibling);
            if (sAcceptNode == 1) {
                return nextSibling;
            }
            return (sAcceptNode != 3 || (firstChild = getFirstChild(nextSibling)) == null) ? getNextSibling(nextSibling, node2) : firstChild;
        }
        Node parentNode = node.getParentNode();
        if (parentNode == null || parentNode == node2 || acceptNode(parentNode) != 3) {
            return null;
        }
        return getNextSibling(parentNode, node2);
    }

    public Node getParentNode(Node node) {
        Node parentNode;
        if (node == null || node == this.fRoot || (parentNode = node.getParentNode()) == null) {
            return null;
        }
        return acceptNode(parentNode) == 1 ? parentNode : getParentNode(parentNode);
    }

    public Node getPreviousSibling(Node node, Node node2) {
        Node lastChild;
        if (node == null || node == node2) {
            return null;
        }
        Node previousSibling = node.getPreviousSibling();
        if (previousSibling != null) {
            short sAcceptNode = acceptNode(previousSibling);
            if (sAcceptNode == 1) {
                return previousSibling;
            }
            return (sAcceptNode != 3 || (lastChild = getLastChild(previousSibling)) == null) ? getPreviousSibling(previousSibling, node2) : lastChild;
        }
        Node parentNode = node.getParentNode();
        if (parentNode == null || parentNode == node2 || acceptNode(parentNode) != 3) {
            return null;
        }
        return getPreviousSibling(parentNode, node2);
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node getRoot() {
        return this.fRoot;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public int getWhatToShow() {
        return this.fWhatToShow;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node lastChild() {
        Node node = this.fCurrentNode;
        if (node == null) {
            return null;
        }
        Node lastChild = getLastChild(node);
        if (lastChild != null) {
            this.fCurrentNode = lastChild;
        }
        return lastChild;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node nextNode() {
        Node node = this.fCurrentNode;
        if (node == null) {
            return null;
        }
        Node firstChild = getFirstChild(node);
        if (firstChild != null) {
            this.fCurrentNode = firstChild;
            return firstChild;
        }
        Node nextSibling = getNextSibling(this.fCurrentNode);
        if (nextSibling != null) {
            this.fCurrentNode = nextSibling;
            return nextSibling;
        }
        Node parentNode = getParentNode(this.fCurrentNode);
        while (parentNode != null) {
            Node nextSibling2 = getNextSibling(parentNode);
            if (nextSibling2 != null) {
                this.fCurrentNode = nextSibling2;
                return nextSibling2;
            }
            parentNode = getParentNode(parentNode);
        }
        return null;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node nextSibling() {
        Node node = this.fCurrentNode;
        if (node == null) {
            return null;
        }
        Node nextSibling = getNextSibling(node);
        if (nextSibling != null) {
            this.fCurrentNode = nextSibling;
        }
        return nextSibling;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node parentNode() {
        Node node = this.fCurrentNode;
        if (node == null) {
            return null;
        }
        Node parentNode = getParentNode(node);
        if (parentNode != null) {
            this.fCurrentNode = parentNode;
        }
        return parentNode;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node previousNode() {
        Node node = this.fCurrentNode;
        if (node == null) {
            return null;
        }
        Node previousSibling = getPreviousSibling(node);
        if (previousSibling == null) {
            Node parentNode = getParentNode(this.fCurrentNode);
            if (parentNode == null) {
                return null;
            }
            this.fCurrentNode = parentNode;
            return parentNode;
        }
        Node lastChild = getLastChild(previousSibling);
        Node node2 = lastChild;
        while (lastChild != null) {
            node2 = lastChild;
            lastChild = getLastChild(lastChild);
        }
        if (node2 != null) {
            this.fCurrentNode = node2;
            return node2;
        }
        this.fCurrentNode = previousSibling;
        return previousSibling;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public Node previousSibling() {
        Node node = this.fCurrentNode;
        if (node == null) {
            return null;
        }
        Node previousSibling = getPreviousSibling(node);
        if (previousSibling != null) {
            this.fCurrentNode = previousSibling;
        }
        return previousSibling;
    }

    @Override // org.w3c.dom.traversal.TreeWalker
    public void setCurrentNode(Node node) {
        if (node != null) {
            this.fCurrentNode = node;
        } else {
            zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        }
    }

    public void setWhatShow(int i) {
        this.fWhatToShow = i;
    }

    public Node getNextSibling(Node node) {
        return getNextSibling(node, this.fRoot);
    }

    public Node getPreviousSibling(Node node) {
        return getPreviousSibling(node, this.fRoot);
    }
}
