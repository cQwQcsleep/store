package com.sun.org.apache.xerces.internal.dom;

import defpackage.zi0;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttributeMap extends NamedNodeMapImpl {
    static final long serialVersionUID = 8872606282138665383L;

    public AttributeMap(ElementImpl elementImpl, NamedNodeMapImpl namedNodeMapImpl) {
        super(elementImpl);
        if (namedNodeMapImpl != null) {
            cloneContent(namedNodeMapImpl);
            if (this.nodes != null) {
                hasDefaults(true);
            }
        }
    }

    private final Node remove(AttrImpl attrImpl, int i, boolean z) {
        NamedNodeMapImpl defaultAttributes;
        Node namedItem;
        CoreDocumentImpl coreDocumentImplOwnerDocument = this.ownerNode.ownerDocument();
        String nodeName = attrImpl.getNodeName();
        if (attrImpl.isIdAttribute()) {
            coreDocumentImplOwnerDocument.removeIdentifier(attrImpl.getValue());
        }
        if (!hasDefaults() || !z || (defaultAttributes = ((ElementImpl) this.ownerNode).getDefaultAttributes()) == null || (namedItem = defaultAttributes.getNamedItem(nodeName)) == null || findNamePoint(nodeName, i + 1) >= 0) {
            this.nodes.remove(i);
        } else {
            NodeImpl nodeImpl = (NodeImpl) namedItem.cloneNode(true);
            if (namedItem.getLocalName() != null) {
                ((AttrNSImpl) nodeImpl).namespaceURI = attrImpl.getNamespaceURI();
            }
            nodeImpl.ownerNode = this.ownerNode;
            nodeImpl.isOwned(true);
            nodeImpl.isSpecified(false);
            this.nodes.set(i, nodeImpl);
            if (attrImpl.isIdAttribute()) {
                coreDocumentImplOwnerDocument.putIdentifier(nodeImpl.getNodeValue(), (ElementImpl) this.ownerNode);
            }
        }
        attrImpl.ownerNode = coreDocumentImplOwnerDocument;
        attrImpl.isOwned(false);
        attrImpl.isSpecified(true);
        attrImpl.isIdAttribute(false);
        coreDocumentImplOwnerDocument.removedAttrNode(attrImpl, this.ownerNode, nodeName);
        return attrImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl
    public final int addItem(Node node) {
        AttrImpl attrImpl = (AttrImpl) node;
        attrImpl.ownerNode = this.ownerNode;
        attrImpl.isOwned(true);
        int iFindNamePoint = findNamePoint(attrImpl.getNamespaceURI(), attrImpl.getLocalName());
        if (iFindNamePoint >= 0) {
            this.nodes.set(iFindNamePoint, node);
        } else {
            iFindNamePoint = findNamePoint(attrImpl.getNodeName(), 0);
            List<Node> list = this.nodes;
            if (iFindNamePoint >= 0) {
                list.add(iFindNamePoint, node);
            } else {
                iFindNamePoint = (-1) - iFindNamePoint;
                if (list == null) {
                    this.nodes = new ArrayList();
                }
                this.nodes.add(iFindNamePoint, node);
            }
        }
        this.ownerNode.ownerDocument().setAttrNode(attrImpl, null);
        return iFindNamePoint;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl
    public void cloneContent(NamedNodeMapImpl namedNodeMapImpl) {
        int size;
        List<Node> list = namedNodeMapImpl.nodes;
        if (list == null || (size = list.size()) == 0) {
            return;
        }
        List<Node> list2 = this.nodes;
        if (list2 == null) {
            this.nodes = new ArrayList(size);
        } else {
            list2.clear();
        }
        for (int i = 0; i < size; i++) {
            NodeImpl nodeImpl = (NodeImpl) list.get(i);
            NodeImpl nodeImpl2 = (NodeImpl) nodeImpl.cloneNode(true);
            nodeImpl2.isSpecified(nodeImpl.isSpecified());
            this.nodes.add(nodeImpl2);
            nodeImpl2.ownerNode = this.ownerNode;
            nodeImpl2.isOwned(true);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl
    public NamedNodeMapImpl cloneMap(NodeImpl nodeImpl) {
        AttributeMap attributeMap = new AttributeMap((ElementImpl) nodeImpl, null);
        attributeMap.hasDefaults(hasDefaults());
        attributeMap.cloneContent(this);
        return attributeMap;
    }

    public final Node internalRemoveNamedItem(String str, boolean z) {
        if (isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return null;
        }
        int iFindNamePoint = findNamePoint(str, 0);
        if (iFindNamePoint >= 0) {
            return remove((AttrImpl) this.nodes.get(iFindNamePoint), iFindNamePoint, true);
        }
        if (!z) {
            return null;
        }
        zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
        return null;
    }

    public final Node internalRemoveNamedItemNS(String str, String str2, boolean z) {
        NamedNodeMapImpl defaultAttributes;
        Node namedItem;
        int iFindNamePoint;
        CoreDocumentImpl coreDocumentImplOwnerDocument = this.ownerNode.ownerDocument();
        if (coreDocumentImplOwnerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return null;
        }
        int iFindNamePoint2 = findNamePoint(str, str2);
        if (iFindNamePoint2 < 0) {
            if (!z) {
                return null;
            }
            zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
            return null;
        }
        AttrImpl attrImpl = (AttrImpl) this.nodes.get(iFindNamePoint2);
        if (attrImpl.isIdAttribute()) {
            coreDocumentImplOwnerDocument.removeIdentifier(attrImpl.getValue());
        }
        String nodeName = attrImpl.getNodeName();
        if (!hasDefaults() || (defaultAttributes = ((ElementImpl) this.ownerNode).getDefaultAttributes()) == null || (namedItem = defaultAttributes.getNamedItem(nodeName)) == null || (iFindNamePoint = findNamePoint(nodeName, 0)) < 0 || findNamePoint(nodeName, iFindNamePoint + 1) >= 0) {
            this.nodes.remove(iFindNamePoint2);
        } else {
            NodeImpl nodeImpl = (NodeImpl) namedItem.cloneNode(true);
            nodeImpl.ownerNode = this.ownerNode;
            if (namedItem.getLocalName() != null) {
                ((AttrNSImpl) nodeImpl).namespaceURI = str;
            }
            nodeImpl.isOwned(true);
            nodeImpl.isSpecified(false);
            this.nodes.set(iFindNamePoint2, nodeImpl);
            if (nodeImpl.isIdAttribute()) {
                coreDocumentImplOwnerDocument.putIdentifier(nodeImpl.getNodeValue(), (ElementImpl) this.ownerNode);
            }
        }
        attrImpl.ownerNode = coreDocumentImplOwnerDocument;
        attrImpl.isOwned(false);
        attrImpl.isSpecified(true);
        attrImpl.isIdAttribute(false);
        coreDocumentImplOwnerDocument.removedAttrNode(attrImpl, this.ownerNode, str2);
        return attrImpl;
    }

    public void moveSpecifiedAttributes(AttributeMap attributeMap) {
        List<Node> list = attributeMap.nodes;
        for (int size = (list != null ? list.size() : 0) - 1; size >= 0; size--) {
            AttrImpl attrImpl = (AttrImpl) attributeMap.nodes.get(size);
            if (attrImpl.isSpecified()) {
                attributeMap.remove(attrImpl, size, false);
                if (attrImpl.getLocalName() != null) {
                    setNamedItem(attrImpl);
                } else {
                    setNamedItemNS(attrImpl);
                }
            }
        }
    }

    public void reconcileDefaults(NamedNodeMapImpl namedNodeMapImpl) {
        List<Node> list = this.nodes;
        for (int size = (list != null ? list.size() : 0) - 1; size >= 0; size--) {
            AttrImpl attrImpl = (AttrImpl) this.nodes.get(size);
            if (!attrImpl.isSpecified()) {
                remove(attrImpl, size, false);
            }
        }
        if (namedNodeMapImpl == null) {
            return;
        }
        List<Node> list2 = this.nodes;
        if (list2 == null || list2.size() == 0) {
            cloneContent(namedNodeMapImpl);
            return;
        }
        int size2 = namedNodeMapImpl.nodes.size();
        for (int i = 0; i < size2; i++) {
            AttrImpl attrImpl2 = (AttrImpl) namedNodeMapImpl.nodes.get(i);
            int iFindNamePoint = findNamePoint(attrImpl2.getNodeName(), 0);
            if (iFindNamePoint < 0) {
                NodeImpl nodeImpl = (NodeImpl) attrImpl2.cloneNode(true);
                nodeImpl.ownerNode = this.ownerNode;
                nodeImpl.isOwned(true);
                nodeImpl.isSpecified(false);
                this.nodes.add((-1) - iFindNamePoint, nodeImpl);
            }
        }
    }

    public Node removeItem(Node node, boolean z) throws DOMException {
        int i;
        List<Node> list = this.nodes;
        if (list == null) {
            i = -1;
            break;
        }
        int size = list.size();
        i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            }
            if (this.nodes.get(i) == node) {
                break;
            }
            i++;
        }
        if (i >= 0) {
            return remove((AttrImpl) node, i, z);
        }
        zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl, org.w3c.dom.NamedNodeMap
    public Node removeNamedItem(String str) throws DOMException {
        return internalRemoveNamedItem(str, true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl, org.w3c.dom.NamedNodeMap
    public Node removeNamedItemNS(String str, String str2) throws DOMException {
        return internalRemoveNamedItemNS(str, str2, true);
    }

    public Node safeRemoveNamedItem(String str) {
        return internalRemoveNamedItem(str, false);
    }

    public Node safeRemoveNamedItemNS(String str, String str2) {
        return internalRemoveNamedItemNS(str, str2, false);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl, org.w3c.dom.NamedNodeMap
    public Node setNamedItem(Node node) throws DOMException {
        boolean z = this.ownerNode.ownerDocument().errorChecking;
        AttrImpl attrImpl = null;
        if (z) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return null;
            }
            if (node.getOwnerDocument() != this.ownerNode.ownerDocument()) {
                zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                return null;
            }
            if (node.getNodeType() != 2) {
                zi0.a(3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
                return null;
            }
        }
        AttrImpl attrImpl2 = (AttrImpl) node;
        if (attrImpl2.isOwned()) {
            if (!z || attrImpl2.getOwnerElement() == this.ownerNode) {
                return node;
            }
            zi0.a(10, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INUSE_ATTRIBUTE_ERR", null));
            return null;
        }
        attrImpl2.ownerNode = this.ownerNode;
        attrImpl2.isOwned(true);
        int iFindNamePoint = findNamePoint(attrImpl2.getNodeName(), 0);
        List<Node> list = this.nodes;
        if (iFindNamePoint >= 0) {
            attrImpl = (AttrImpl) list.get(iFindNamePoint);
            this.nodes.set(iFindNamePoint, node);
            attrImpl.ownerNode = this.ownerNode.ownerDocument();
            attrImpl.isOwned(false);
            attrImpl.isSpecified(true);
        } else {
            int i = (-1) - iFindNamePoint;
            if (list == null) {
                this.nodes = new ArrayList();
            }
            this.nodes.add(i, node);
        }
        this.ownerNode.ownerDocument().setAttrNode(attrImpl2, attrImpl);
        if (!attrImpl2.isNormalized()) {
            this.ownerNode.isNormalized(false);
        }
        return attrImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NamedNodeMapImpl, org.w3c.dom.NamedNodeMap
    public Node setNamedItemNS(Node node) throws DOMException {
        boolean z = this.ownerNode.ownerDocument().errorChecking;
        AttrImpl attrImpl = null;
        if (z) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return null;
            }
            if (node.getOwnerDocument() != this.ownerNode.ownerDocument()) {
                zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                return null;
            }
            if (node.getNodeType() != 2) {
                zi0.a(3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
                return null;
            }
        }
        AttrImpl attrImpl2 = (AttrImpl) node;
        if (attrImpl2.isOwned()) {
            if (!z || attrImpl2.getOwnerElement() == this.ownerNode) {
                return node;
            }
            zi0.a(10, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INUSE_ATTRIBUTE_ERR", null));
            return null;
        }
        attrImpl2.ownerNode = this.ownerNode;
        attrImpl2.isOwned(true);
        int iFindNamePoint = findNamePoint(attrImpl2.getNamespaceURI(), attrImpl2.getLocalName());
        if (iFindNamePoint >= 0) {
            attrImpl = (AttrImpl) this.nodes.get(iFindNamePoint);
            this.nodes.set(iFindNamePoint, node);
            attrImpl.ownerNode = this.ownerNode.ownerDocument();
            attrImpl.isOwned(false);
            attrImpl.isSpecified(true);
        } else {
            int iFindNamePoint2 = findNamePoint(node.getNodeName(), 0);
            List<Node> list = this.nodes;
            if (iFindNamePoint2 >= 0) {
                attrImpl = (AttrImpl) list.get(iFindNamePoint2);
                this.nodes.add(iFindNamePoint2, node);
            } else {
                int i = (-1) - iFindNamePoint2;
                if (list == null) {
                    this.nodes = new ArrayList();
                }
                this.nodes.add(i, node);
            }
        }
        this.ownerNode.ownerDocument().setAttrNode(attrImpl2, attrImpl);
        if (!attrImpl2.isNormalized()) {
            this.ownerNode.isNormalized(false);
        }
        return attrImpl;
    }
}
