package com.sun.org.apache.xerces.internal.dom;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class NodeImpl implements Node, NodeList, EventTarget, Cloneable, Serializable {
    public static final short ELEMENT_DEFINITION_NODE = 21;
    protected static final short FIRSTCHILD = 16;
    protected static final short HASSTRING = 128;
    protected static final short ID = 512;
    protected static final short IGNORABLEWS = 64;
    protected static final short NORMALIZED = 256;
    protected static final short OWNED = 8;
    protected static final short READONLY = 1;
    protected static final short SPECIFIED = 32;
    protected static final short SYNCCHILDREN = 4;
    protected static final short SYNCDATA = 2;
    public static final short TREE_POSITION_ANCESTOR = 4;
    public static final short TREE_POSITION_DESCENDANT = 8;
    public static final short TREE_POSITION_DISCONNECTED = 0;
    public static final short TREE_POSITION_EQUIVALENT = 16;
    public static final short TREE_POSITION_FOLLOWING = 2;
    public static final short TREE_POSITION_PRECEDING = 1;
    public static final short TREE_POSITION_SAME_NODE = 32;
    static final long serialVersionUID = -6316591992167219696L;
    protected short flags;
    protected NodeImpl ownerNode;

    public NodeImpl(CoreDocumentImpl coreDocumentImpl) {
        this.ownerNode = coreDocumentImpl;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        if (needsSyncData()) {
            synchronizeData();
        }
        objectOutputStream.defaultWriteObject();
    }

    @Override // org.w3c.dom.events.EventTarget
    public void addEventListener(String str, EventListener eventListener, boolean z) {
        ownerDocument().addEventListener(this, str, eventListener, z);
    }

    @Override // org.w3c.dom.Node
    public Node appendChild(Node node) throws DOMException {
        return insertBefore(node, null);
    }

    public void changed() {
        ownerDocument().changed();
    }

    public int changes() {
        return ownerDocument().changes();
    }

    @Override // org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        if (needsSyncData()) {
            synchronizeData();
        }
        try {
            NodeImpl nodeImpl = (NodeImpl) clone();
            nodeImpl.ownerNode = ownerDocument();
            nodeImpl.isOwned(false);
            nodeImpl.isReadOnly(false);
            ownerDocument().callUserDataHandlers(this, nodeImpl, (short) 1);
            return nodeImpl;
        } catch (CloneNotSupportedException e) {
            y04.a("**Internal Error**", e);
            return null;
        }
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 4341. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    @Override // org.w3c.dom.Node
    public short compareDocumentPosition(org.w3c.dom.Node r24) throws org.w3c.dom.DOMException {
        /*
            Method dump skipped, instruction units count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xerces.internal.dom.NodeImpl.compareDocumentPosition(org.w3c.dom.Node):short");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v2, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r10v9, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [org.w3c.dom.Node] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r9v5 */
    @Deprecated
    public short compareTreePosition(Node node) {
        Node parentNode;
        Node node2;
        Element ownerElement;
        if (this == node) {
            return (short) 48;
        }
        short nodeType = getNodeType();
        short nodeType2 = node.getNodeType();
        if (nodeType != 6 && nodeType != 12 && nodeType2 != 6 && nodeType2 != 12) {
            Node node3 = this;
            Node node4 = node3;
            int i = 0;
            while (parentNode != null) {
                i++;
                if (parentNode == node) {
                    parentNode = node3;
                    return (short) 5;
                }
                parentNode = node3;
                node4 = parentNode;
                parentNode = parentNode.getParentNode();
            }
            parentNode = node3;
            Node parentNode2 = node;
            Node node5 = parentNode2;
            int i2 = 0;
            while (parentNode2 != null) {
                i2++;
                if (parentNode2 == this) {
                    return (short) 10;
                }
                node5 = parentNode2;
                parentNode2 = parentNode2.getParentNode();
            }
            short nodeType3 = node4.getNodeType();
            short nodeType4 = node5.getNodeType();
            ?? parentNode3 = this;
            if (nodeType3 == 2) {
                ownerElement = ((AttrImpl) node4).getOwnerElement();
            }
            if (nodeType4 == 2) {
                parentNode3 = ownerElement;
                node = ((AttrImpl) node5).getOwnerElement();
            }
            if (nodeType3 == 2 && nodeType4 == 2 && parentNode3 == node) {
                return (short) 16;
            }
            ?? r2 = node4;
            if (nodeType3 == 2) {
                ?? r0 = node4;
                i = 0;
                ?? parentNode4 = parentNode3;
                while (parentNode4 != 0) {
                    i++;
                    if (parentNode4 == node) {
                        return (short) 1;
                    }
                    ?? r9 = parentNode4;
                    parentNode4 = parentNode4.getParentNode();
                    r0 = r9;
                }
                r2 = r0;
            }
            if (nodeType4 == 2) {
                i2 = 0;
                Node node6 = node5;
                Node parentNode5 = node;
                while (parentNode5 != null) {
                    i2++;
                    if (parentNode5 == parentNode3) {
                        return (short) 2;
                    }
                    Node node7 = parentNode5;
                    parentNode5 = parentNode5.getParentNode();
                    node6 = node7;
                }
                node5 = node6;
            }
            if (r2 != node5) {
                return (short) 0;
            }
            if (i > i2) {
                int i3 = 0;
                parentNode3 = parentNode3;
                while (i3 < i - i2) {
                    i3++;
                    parentNode3 = parentNode3.getParentNode();
                }
                if (parentNode3 == node) {
                    return (short) 1;
                }
            } else {
                for (int i4 = 0; i4 < i2 - i; i4++) {
                    node = node.getParentNode();
                }
                if (node == parentNode3) {
                    return (short) 2;
                }
            }
            Node parentNode6 = parentNode3.getParentNode();
            Node parentNode7 = node.getParentNode();
            Node node8 = node;
            ?? r11 = parentNode3;
            Node parentNode8 = parentNode6;
            while (true) {
                node2 = node8;
                if (parentNode8 == parentNode7) {
                    break;
                }
                r11 = parentNode8;
                parentNode8 = parentNode8.getParentNode();
                node8 = parentNode7;
                parentNode7 = parentNode7.getParentNode();
            }
            for (Node firstChild = parentNode8.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                if (firstChild == node2) {
                    return (short) 1;
                }
                if (firstChild == r11) {
                    return (short) 2;
                }
            }
        }
        return (short) 0;
    }

    @Override // org.w3c.dom.events.EventTarget
    public boolean dispatchEvent(Event event) {
        return ownerDocument().dispatchEvent(this, event);
    }

    @Override // org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getBaseURI() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public NodeList getChildNodes() {
        return this;
    }

    public Node getContainer() {
        return null;
    }

    public Node getElementAncestor(Node node) {
        for (Node parentNode = node.getParentNode(); parentNode != null; parentNode = parentNode.getParentNode()) {
            if (parentNode.getNodeType() == 1) {
                return parentNode;
            }
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public Object getFeature(String str, String str2) {
        if (isSupported(str, str2)) {
            return this;
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getFirstChild() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getLastChild() {
        return null;
    }

    public int getLength() {
        return 0;
    }

    @Override // org.w3c.dom.Node
    public String getLocalName() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNamespaceURI() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getNextSibling() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public abstract String getNodeName();

    public int getNodeNumber() {
        return ((CoreDocumentImpl) getOwnerDocument()).getNodeNumber(this);
    }

    @Override // org.w3c.dom.Node
    public abstract short getNodeType();

    @Override // org.w3c.dom.Node
    public String getNodeValue() throws DOMException {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.w3c.dom.Node
    public Document getOwnerDocument() {
        boolean zIsOwned = isOwned();
        NodeImpl nodeImpl = this.ownerNode;
        return zIsOwned ? nodeImpl.ownerDocument() : (Document) nodeImpl;
    }

    @Override // org.w3c.dom.Node
    public Node getParentNode() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getPrefix() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getPreviousSibling() {
        return null;
    }

    public boolean getReadOnly() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return isReadOnly();
    }

    public void getTextContent(StringBuilder sb) throws DOMException {
        String nodeValue = getNodeValue();
        if (nodeValue != null) {
            sb.append(nodeValue);
        }
    }

    @Override // org.w3c.dom.Node
    public Object getUserData(String str) {
        return ownerDocument().getUserData(this, str);
    }

    public Map<String, ParentNode.UserDataRecord> getUserDataRecord() {
        return ownerDocument().getUserDataRecord(this);
    }

    @Override // org.w3c.dom.Node
    public boolean hasAttributes() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean hasChildNodes() {
        return false;
    }

    public final void hasStringValue(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 128 : s & (-129));
    }

    @Override // org.w3c.dom.Node
    public Node insertBefore(Node node, Node node2) throws DOMException {
        throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
    }

    public final boolean internalIsIgnorableWhitespace() {
        return (this.flags & 64) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.w3c.dom.Node
    public boolean isDefaultNamespace(String str) {
        NodeImpl nodeImpl;
        short nodeType = getNodeType();
        if (nodeType != 1) {
            if (nodeType == 2) {
                if (this.ownerNode.getNodeType() == 1) {
                    return this.ownerNode.isDefaultNamespace(str);
                }
                return false;
            }
            if (nodeType != 6) {
                switch (nodeType) {
                    case 9:
                        Element documentElement = ((Document) this).getDocumentElement();
                        if (documentElement != null) {
                            return documentElement.isDefaultNamespace(str);
                        }
                    case 10:
                    case 11:
                    case 12:
                        return false;
                    default:
                        NodeImpl nodeImpl2 = (NodeImpl) getElementAncestor(this);
                        if (nodeImpl2 != null) {
                            return nodeImpl2.isDefaultNamespace(str);
                        }
                        return false;
                }
            }
            return false;
        }
        String namespaceURI = getNamespaceURI();
        String prefix = getPrefix();
        if (prefix == null || prefix.length() == 0) {
            if (str == null) {
                return namespaceURI == str;
            }
            return str.equals(namespaceURI);
        }
        if (!hasAttributes() || (nodeImpl = (NodeImpl) ((ElementImpl) this).getAttributeNodeNS("http://www.w3.org/2000/xmlns/", "xmlns")) == null) {
            NodeImpl nodeImpl3 = (NodeImpl) getElementAncestor(this);
            if (nodeImpl3 != null) {
                return nodeImpl3.isDefaultNamespace(str);
            }
            return false;
        }
        String nodeValue = nodeImpl.getNodeValue();
        if (str == null) {
            return namespaceURI == nodeValue;
        }
        return str.equals(nodeValue);
    }

    @Override // org.w3c.dom.Node
    public boolean isEqualNode(Node node) {
        if (node == this) {
            return true;
        }
        if (node.getNodeType() != getNodeType()) {
            return false;
        }
        if (getNodeName() == null) {
            if (node.getNodeName() != null) {
                return false;
            }
        } else if (!getNodeName().equals(node.getNodeName())) {
            return false;
        }
        if (getLocalName() == null) {
            if (node.getLocalName() != null) {
                return false;
            }
        } else if (!getLocalName().equals(node.getLocalName())) {
            return false;
        }
        if (getNamespaceURI() == null) {
            if (node.getNamespaceURI() != null) {
                return false;
            }
        } else if (!getNamespaceURI().equals(node.getNamespaceURI())) {
            return false;
        }
        if (getPrefix() == null) {
            if (node.getPrefix() != null) {
                return false;
            }
        } else if (!getPrefix().equals(node.getPrefix())) {
            return false;
        }
        if (getNodeValue() == null) {
            if (node.getNodeValue() != null) {
                return false;
            }
        } else if (!getNodeValue().equals(node.getNodeValue())) {
            return false;
        }
        return true;
    }

    public final void isFirstChild(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 16 : s & (-17));
    }

    public final void isIdAttribute(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 512 : s & (-513));
    }

    public final void isIgnorableWhitespace(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 64 : s & (-65));
    }

    public final void isNormalized(boolean z) {
        NodeImpl nodeImpl;
        if (!z && isNormalized() && (nodeImpl = this.ownerNode) != null) {
            nodeImpl.isNormalized(false);
        }
        short s = this.flags;
        this.flags = (short) (z ? s | 256 : s & (-257));
    }

    public final void isOwned(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 8 : s & (-9));
    }

    public final void isReadOnly(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 1 : s & (-2));
    }

    @Override // org.w3c.dom.Node
    public boolean isSameNode(Node node) {
        return this == node;
    }

    public final void isSpecified(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 32 : s & (-33));
    }

    @Override // org.w3c.dom.Node
    public boolean isSupported(String str, String str2) {
        return ownerDocument().getImplementation().hasFeature(str, str2);
    }

    public Node item(int i) {
        return null;
    }

    public String lookupNamespacePrefix(String str, ElementImpl elementImpl) {
        String localName;
        String strLookupNamespaceURI;
        String strLookupNamespaceURI2;
        String namespaceURI = getNamespaceURI();
        String prefix = getPrefix();
        if (namespaceURI != null && namespaceURI.equals(str) && prefix != null && (strLookupNamespaceURI2 = elementImpl.lookupNamespaceURI(prefix)) != null && strLookupNamespaceURI2.equals(str)) {
            return prefix;
        }
        if (hasAttributes()) {
            NamedNodeMap attributes = getAttributes();
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = attributes.item(i);
                String namespaceURI2 = nodeItem.getNamespaceURI();
                if (namespaceURI2 != null && namespaceURI2.equals("http://www.w3.org/2000/xmlns/")) {
                    String prefix2 = nodeItem.getPrefix();
                    String nodeValue = nodeItem.getNodeValue();
                    if ((nodeItem.getNodeName().equals("xmlns") || (prefix2 != null && prefix2.equals("xmlns") && nodeValue.equals(str))) && (strLookupNamespaceURI = elementImpl.lookupNamespaceURI((localName = nodeItem.getLocalName()))) != null && strLookupNamespaceURI.equals(str)) {
                        return localName;
                    }
                }
            }
        }
        NodeImpl nodeImpl = (NodeImpl) getElementAncestor(this);
        if (nodeImpl != null) {
            return nodeImpl.lookupNamespacePrefix(str, elementImpl);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.w3c.dom.Node
    public String lookupNamespaceURI(String str) {
        short nodeType = getNodeType();
        if (nodeType != 1) {
            if (nodeType == 2) {
                if (this.ownerNode.getNodeType() == 1) {
                    return this.ownerNode.lookupNamespaceURI(str);
                }
                return null;
            }
            if (nodeType != 6) {
                switch (nodeType) {
                    case 9:
                        Element documentElement = ((Document) this).getDocumentElement();
                        if (documentElement != null) {
                            return documentElement.lookupNamespaceURI(str);
                        }
                    case 10:
                    case 11:
                    case 12:
                        return null;
                    default:
                        NodeImpl nodeImpl = (NodeImpl) getElementAncestor(this);
                        if (nodeImpl != null) {
                            return nodeImpl.lookupNamespaceURI(str);
                        }
                        return null;
                }
            }
            return null;
        }
        String namespaceURI = getNamespaceURI();
        String prefix = getPrefix();
        if (namespaceURI != null && ((str == null && prefix == str) || (prefix != null && prefix.equals(str)))) {
            return namespaceURI;
        }
        if (hasAttributes()) {
            NamedNodeMap attributes = getAttributes();
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                Node nodeItem = attributes.item(i);
                String namespaceURI2 = nodeItem.getNamespaceURI();
                if (namespaceURI2 != null && namespaceURI2.equals("http://www.w3.org/2000/xmlns/")) {
                    String prefix2 = nodeItem.getPrefix();
                    String nodeValue = nodeItem.getNodeValue();
                    if (str == null && nodeItem.getNodeName().equals("xmlns")) {
                        if (nodeValue.length() > 0) {
                            return nodeValue;
                        }
                        return null;
                    }
                    if (prefix2 != null && prefix2.equals("xmlns") && nodeItem.getLocalName().equals(str)) {
                        if (nodeValue.length() > 0) {
                            return nodeValue;
                        }
                        return null;
                    }
                }
            }
        }
        NodeImpl nodeImpl2 = (NodeImpl) getElementAncestor(this);
        if (nodeImpl2 != null) {
            return nodeImpl2.lookupNamespaceURI(str);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.w3c.dom.Node
    public String lookupPrefix(String str) {
        if (str == null) {
            return null;
        }
        short nodeType = getNodeType();
        if (nodeType == 1) {
            getNamespaceURI();
            return lookupNamespacePrefix(str, (ElementImpl) this);
        }
        if (nodeType == 2) {
            if (this.ownerNode.getNodeType() == 1) {
                return this.ownerNode.lookupPrefix(str);
            }
            return null;
        }
        if (nodeType != 6) {
            switch (nodeType) {
                case 9:
                    Element documentElement = ((Document) this).getDocumentElement();
                    if (documentElement != null) {
                        return documentElement.lookupPrefix(str);
                    }
                case 10:
                case 11:
                case 12:
                    return null;
                default:
                    NodeImpl nodeImpl = (NodeImpl) getElementAncestor(this);
                    if (nodeImpl != null) {
                        return nodeImpl.lookupPrefix(str);
                    }
                    return null;
            }
        }
        return null;
    }

    public final void needsSyncChildren(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 4 : s & (-5));
    }

    public final void needsSyncData(boolean z) {
        short s = this.flags;
        this.flags = (short) (z ? s | 2 : s & (-3));
    }

    @Override // org.w3c.dom.Node
    public void normalize() {
    }

    public CoreDocumentImpl ownerDocument() {
        boolean zIsOwned = isOwned();
        NodeImpl nodeImpl = this.ownerNode;
        return zIsOwned ? nodeImpl.ownerDocument() : (CoreDocumentImpl) nodeImpl;
    }

    public NodeImpl parentNode() {
        return null;
    }

    public ChildNode previousSibling() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node removeChild(Node node) throws DOMException {
        throw new DOMException((short) 8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
    }

    @Override // org.w3c.dom.events.EventTarget
    public void removeEventListener(String str, EventListener eventListener, boolean z) {
        ownerDocument().removeEventListener(this, str, eventListener, z);
    }

    @Override // org.w3c.dom.Node
    public Node replaceChild(Node node, Node node2) throws DOMException {
        throw new DOMException((short) 3, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
    }

    @Override // org.w3c.dom.Node
    public void setNodeValue(String str) throws DOMException {
    }

    public void setOwnerDocument(CoreDocumentImpl coreDocumentImpl) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (isOwned()) {
            return;
        }
        this.ownerNode = coreDocumentImpl;
    }

    @Override // org.w3c.dom.Node
    public void setPrefix(String str) throws DOMException {
        throw new DOMException((short) 14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
    }

    public void setReadOnly(boolean z, boolean z2) {
        if (needsSyncData()) {
            synchronizeData();
        }
        isReadOnly(z);
    }

    @Override // org.w3c.dom.Node
    public void setTextContent(String str) throws DOMException {
        setNodeValue(str);
    }

    @Override // org.w3c.dom.Node
    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        return ownerDocument().setUserData(this, str, obj, userDataHandler);
    }

    public void synchronizeData() {
        needsSyncData(false);
    }

    public String toString() {
        return "[" + getNodeName() + ": " + getNodeValue() + "]";
    }

    public NodeImpl() {
    }

    public Object getUserData() {
        return ownerDocument().getUserData(this);
    }

    public void setUserData(Object obj) {
        ownerDocument().setUserData(this, obj);
    }

    @Override // org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        return getNodeValue();
    }

    public final boolean hasStringValue() {
        return (this.flags & 128) != 0;
    }

    public final boolean isFirstChild() {
        return (this.flags & 16) != 0;
    }

    public final boolean isIdAttribute() {
        return (this.flags & 512) != 0;
    }

    public final boolean isOwned() {
        return (this.flags & 8) != 0;
    }

    public final boolean isReadOnly() {
        return (this.flags & 1) != 0;
    }

    public final boolean isSpecified() {
        return (this.flags & 32) != 0;
    }

    public final boolean needsSyncChildren() {
        return (this.flags & 4) != 0;
    }

    public final boolean needsSyncData() {
        return (this.flags & 2) != 0;
    }

    public final boolean isNormalized() {
        return (this.flags & 256) != 0;
    }
}
