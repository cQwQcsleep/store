package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.util.URI;
import defpackage.zi0;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.ElementTraversal;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.TypeInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ElementImpl extends ParentNode implements Element, ElementTraversal, TypeInfo {
    static final long serialVersionUID = 3717253516652722278L;
    protected AttributeMap attributes;
    protected String name;

    public ElementImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl);
        this.name = str;
        needsSyncData(true);
    }

    private Element getFirstElementChild(Node node) {
        Node firstChild = node;
        loop0: while (firstChild != null) {
            if (firstChild.getNodeType() != 1) {
                Node parentNode = firstChild;
                firstChild = firstChild.getFirstChild();
                while (firstChild == null && node != parentNode) {
                    firstChild = parentNode.getNextSibling();
                    if (firstChild == null && ((parentNode = parentNode.getParentNode()) == null || node == parentNode)) {
                        break loop0;
                    }
                }
            } else {
                return (Element) firstChild;
            }
        }
        return null;
    }

    private Element getLastElementChild(Node node) {
        Node lastChild = node;
        loop0: while (lastChild != null) {
            if (lastChild.getNodeType() != 1) {
                Node parentNode = lastChild;
                lastChild = lastChild.getLastChild();
                while (lastChild == null && node != parentNode) {
                    lastChild = parentNode.getPreviousSibling();
                    if (lastChild == null && ((parentNode = parentNode.getParentNode()) == null || node == parentNode)) {
                        break loop0;
                    }
                }
            } else {
                return (Element) lastChild;
            }
        }
        return null;
    }

    private Node getNextLogicalSibling(Node node) {
        Node nextSibling = node.getNextSibling();
        if (nextSibling == null) {
            for (Node parentNode = node.getParentNode(); parentNode != null && parentNode.getNodeType() == 5; parentNode = parentNode.getParentNode()) {
                nextSibling = parentNode.getNextSibling();
                if (nextSibling != null) {
                    return nextSibling;
                }
            }
        }
        return nextSibling;
    }

    private Node getPreviousLogicalSibling(Node node) {
        Node previousSibling = node.getPreviousSibling();
        if (previousSibling == null) {
            for (Node parentNode = node.getParentNode(); parentNode != null && parentNode.getNodeType() == 5; parentNode = parentNode.getParentNode()) {
                previousSibling = parentNode.getPreviousSibling();
                if (previousSibling != null) {
                    return previousSibling;
                }
            }
        }
        return previousSibling;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.ChildNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        ElementImpl elementImpl = (ElementImpl) super.cloneNode(z);
        AttributeMap attributeMap = this.attributes;
        if (attributeMap != null) {
            elementImpl.attributes = (AttributeMap) attributeMap.cloneMap(elementImpl);
        }
        return elementImpl;
    }

    @Override // org.w3c.dom.Element
    public String getAttribute(String str) {
        Attr attr;
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        return (attributeMap == null || (attr = (Attr) attributeMap.getNamedItem(str)) == null) ? "" : attr.getValue();
    }

    @Override // org.w3c.dom.Element
    public String getAttributeNS(String str, String str2) {
        Attr attr;
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        return (attributeMap == null || (attr = (Attr) attributeMap.getNamedItemNS(str, str2)) == null) ? "" : attr.getValue();
    }

    @Override // org.w3c.dom.Element
    public Attr getAttributeNode(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        if (attributeMap == null) {
            return null;
        }
        return (Attr) attributeMap.getNamedItem(str);
    }

    @Override // org.w3c.dom.Element
    public Attr getAttributeNodeNS(String str, String str2) {
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        if (attributeMap == null) {
            return null;
        }
        return (Attr) attributeMap.getNamedItemNS(str, str2);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return this.attributes;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getBaseURI() {
        Attr xMLBaseAttribute;
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes != null && (xMLBaseAttribute = getXMLBaseAttribute()) != null) {
            String nodeValue = xMLBaseAttribute.getNodeValue();
            if (nodeValue.length() != 0) {
                try {
                    URI uri = new URI(nodeValue, true);
                    if (uri.isAbsoluteURI()) {
                        return uri.toString();
                    }
                    NodeImpl nodeImpl = this.ownerNode;
                    String baseURI = nodeImpl != null ? nodeImpl.getBaseURI() : null;
                    if (baseURI != null) {
                        uri.absolutize(new URI(baseURI));
                        return uri.toString();
                    }
                    return null;
                } catch (URI.MalformedURIException unused) {
                }
            }
        }
        NodeImpl nodeImpl2 = this.ownerNode;
        if (nodeImpl2 != null) {
            return nodeImpl2.getBaseURI();
        }
        return null;
    }

    @Override // org.w3c.dom.ElementTraversal
    public final int getChildElementCount() {
        int i = 0;
        for (Element firstElementChild = getFirstElementChild(); firstElementChild != null; firstElementChild = ((ElementImpl) firstElementChild).getNextElementSibling()) {
            i++;
        }
        return i;
    }

    public NamedNodeMapImpl getDefaultAttributes() {
        ElementDefinitionImpl elementDefinitionImpl;
        DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl) this.ownerDocument.getDoctype();
        if (documentTypeImpl == null || (elementDefinitionImpl = (ElementDefinitionImpl) documentTypeImpl.getElements().getNamedItem(getNodeName())) == null) {
            return null;
        }
        return (NamedNodeMapImpl) elementDefinitionImpl.getAttributes();
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagName(String str) {
        return new DeepNodeListImpl(this, str);
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagNameNS(String str, String str2) {
        return new DeepNodeListImpl(this, str, str2);
    }

    @Override // org.w3c.dom.ElementTraversal
    public final Element getNextElementSibling() {
        Element firstElementChild;
        Node nextLogicalSibling = getNextLogicalSibling(this);
        while (nextLogicalSibling != null) {
            short nodeType = nextLogicalSibling.getNodeType();
            if (nodeType == 1) {
                return (Element) nextLogicalSibling;
            }
            if (nodeType == 5 && (firstElementChild = getFirstElementChild(nextLogicalSibling)) != null) {
                return firstElementChild;
            }
            nextLogicalSibling = getNextLogicalSibling(nextLogicalSibling);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 1;
    }

    @Override // org.w3c.dom.ElementTraversal
    public final Element getPreviousElementSibling() {
        Element lastElementChild;
        Node previousLogicalSibling = getPreviousLogicalSibling(this);
        while (previousLogicalSibling != null) {
            short nodeType = previousLogicalSibling.getNodeType();
            if (nodeType == 1) {
                return (Element) previousLogicalSibling;
            }
            if (nodeType == 5 && (lastElementChild = getLastElementChild(previousLogicalSibling)) != null) {
                return lastElementChild;
            }
            previousLogicalSibling = getPreviousLogicalSibling(previousLogicalSibling);
        }
        return null;
    }

    @Override // org.w3c.dom.Element
    public TypeInfo getSchemaTypeInfo() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this;
    }

    @Override // org.w3c.dom.Element
    public String getTagName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    @Override // org.w3c.dom.TypeInfo
    public String getTypeName() {
        return null;
    }

    @Override // org.w3c.dom.TypeInfo
    public String getTypeNamespace() {
        return null;
    }

    public Attr getXMLBaseAttribute() {
        return (Attr) this.attributes.getNamedItem("xml:base");
    }

    public int getXercesAttribute(String str, String str2) {
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        if (attributeMap == null) {
            return -1;
        }
        return attributeMap.getNamedItemIndex(str, str2);
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttribute(String str) {
        return getAttributeNode(str) != null;
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttributeNS(String str, String str2) {
        return getAttributeNodeNS(str, str2) != null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public boolean hasAttributes() {
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        return (attributeMap == null || attributeMap.getLength() == 0) ? false : true;
    }

    @Override // org.w3c.dom.TypeInfo
    public boolean isDerivedFrom(String str, String str2, int i) {
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public boolean isEqualNode(Node node) {
        if (!super.isEqualNode(node)) {
            return false;
        }
        boolean zHasAttributes = hasAttributes();
        Element element = (Element) node;
        if (zHasAttributes != element.hasAttributes()) {
            return false;
        }
        if (!zHasAttributes) {
            return true;
        }
        NamedNodeMap attributes = getAttributes();
        NamedNodeMap attributes2 = element.getAttributes();
        int length = attributes.getLength();
        if (length != attributes2.getLength()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            Node nodeItem = attributes.item(i);
            if (nodeItem.getLocalName() == null) {
                Node namedItem = attributes2.getNamedItem(nodeItem.getNodeName());
                if (namedItem == null || !((NodeImpl) nodeItem).isEqualNode(namedItem)) {
                    return false;
                }
            } else {
                Node namedItemNS = attributes2.getNamedItemNS(nodeItem.getNamespaceURI(), nodeItem.getLocalName());
                if (namedItemNS == null || !((NodeImpl) nodeItem).isEqualNode(namedItemNS)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void moveSpecifiedAttributes(ElementImpl elementImpl) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (elementImpl.hasAttributes()) {
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            this.attributes.moveSpecifiedAttributes(elementImpl.attributes);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void normalize() {
        if (isNormalized()) {
            return;
        }
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        ChildNode childNode = this.firstChild;
        while (childNode != null) {
            ChildNode childNode2 = childNode.nextSibling;
            if (childNode.getNodeType() == 3) {
                if (childNode2 != null && childNode2.getNodeType() == 3) {
                    ((Text) childNode).appendData(childNode2.getNodeValue());
                    removeChild(childNode2);
                } else if (childNode.getNodeValue() == null || childNode.getNodeValue().length() == 0) {
                    removeChild(childNode);
                }
            } else if (childNode.getNodeType() == 1) {
                childNode.normalize();
            }
            childNode = childNode2;
        }
        if (this.attributes != null) {
            for (int i = 0; i < this.attributes.getLength(); i++) {
                this.attributes.item(i).normalize();
            }
        }
        isNormalized(true);
    }

    public void reconcileDefaultAttributes() {
        if (this.attributes != null) {
            this.attributes.reconcileDefaults(getDefaultAttributes());
        }
    }

    @Override // org.w3c.dom.Element
    public void removeAttribute(String str) {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        if (attributeMap == null) {
            return;
        }
        attributeMap.safeRemoveNamedItem(str);
    }

    @Override // org.w3c.dom.Element
    public void removeAttributeNS(String str, String str2) {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        if (attributeMap == null) {
            return;
        }
        attributeMap.safeRemoveNamedItemNS(str, str2);
    }

    @Override // org.w3c.dom.Element
    public Attr removeAttributeNode(Attr attr) throws DOMException {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return null;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        AttributeMap attributeMap = this.attributes;
        if (attributeMap != null) {
            return (Attr) attributeMap.removeItem(attr, true);
        }
        zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
        return null;
    }

    public void rename(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (str.indexOf(58) != -1) {
                zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
                return;
            } else if (!CoreDocumentImpl.isXMLName(str, this.ownerDocument.isXML11Version())) {
                zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                return;
            }
        }
        this.name = str;
        reconcileDefaultAttributes();
    }

    @Override // org.w3c.dom.Element
    public void setAttribute(String str, String str2) {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        Attr attributeNode = getAttributeNode(str);
        if (attributeNode != null) {
            attributeNode.setNodeValue(str2);
            return;
        }
        Attr attrCreateAttribute = getOwnerDocument().createAttribute(str);
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        attrCreateAttribute.setNodeValue(str2);
        this.attributes.setNamedItem(attrCreateAttribute);
    }

    @Override // org.w3c.dom.Element
    public void setAttributeNS(String str, String str2, String str3) {
        String strSubstring;
        String strSubstring2;
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        int iIndexOf = str2.indexOf(58);
        if (iIndexOf < 0) {
            strSubstring2 = str2;
            strSubstring = null;
        } else {
            strSubstring = str2.substring(0, iIndexOf);
            strSubstring2 = str2.substring(iIndexOf + 1);
        }
        Attr attributeNodeNS = getAttributeNodeNS(str, strSubstring2);
        if (attributeNodeNS == null) {
            Attr attrCreateAttributeNS = getOwnerDocument().createAttributeNS(str, str2);
            if (this.attributes == null) {
                this.attributes = new AttributeMap(this, null);
            }
            attrCreateAttributeNS.setNodeValue(str3);
            this.attributes.setNamedItemNS(attrCreateAttributeNS);
            return;
        }
        if (attributeNodeNS instanceof AttrNSImpl) {
            AttrNSImpl attrNSImpl = (AttrNSImpl) attributeNodeNS;
            String str4 = attrNSImpl.name;
            if (strSubstring != null) {
                strSubstring2 = strSubstring + ":" + strSubstring2;
            }
            attrNSImpl.name = strSubstring2;
            if (!strSubstring2.equals(str4)) {
                attributeNodeNS = (Attr) this.attributes.removeItem(attributeNodeNS, false);
                this.attributes.addItem(attributeNodeNS);
            }
        } else {
            attributeNodeNS = ((CoreDocumentImpl) getOwnerDocument()).createAttributeNS(str, str2, strSubstring2);
            this.attributes.setNamedItemNS(attributeNodeNS);
        }
        attributeNodeNS.setNodeValue(str3);
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNode(Attr attr) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return null;
            }
            if (attr.getOwnerDocument() != this.ownerDocument) {
                zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                return null;
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr) this.attributes.setNamedItem(attr);
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNodeNS(Attr attr) throws DOMException {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return null;
            }
            if (attr.getOwnerDocument() != this.ownerDocument) {
                zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
                return null;
            }
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return (Attr) this.attributes.setNamedItemNS(attr);
    }

    @Override // org.w3c.dom.Element
    public void setIdAttribute(String str, boolean z) {
        if (needsSyncData()) {
            synchronizeData();
        }
        Attr attributeNode = getAttributeNode(str);
        if (attributeNode == null) {
            zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
            return;
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return;
            } else if (attributeNode.getOwnerElement() != this) {
                zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
                return;
            }
        }
        ((AttrImpl) attributeNode).isIdAttribute(z);
        CoreDocumentImpl coreDocumentImpl = this.ownerDocument;
        if (z) {
            coreDocumentImpl.putIdentifier(attributeNode.getValue(), this);
        } else {
            coreDocumentImpl.removeIdentifier(attributeNode.getValue());
        }
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNS(String str, String str2, boolean z) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (str != null && str.length() == 0) {
            str = null;
        }
        Attr attributeNodeNS = getAttributeNodeNS(str, str2);
        if (attributeNodeNS == null) {
            zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
            return;
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return;
            } else if (attributeNodeNS.getOwnerElement() != this) {
                zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
                return;
            }
        }
        ((AttrImpl) attributeNodeNS).isIdAttribute(z);
        CoreDocumentImpl coreDocumentImpl = this.ownerDocument;
        if (z) {
            coreDocumentImpl.putIdentifier(attributeNodeNS.getValue(), this);
        } else {
            coreDocumentImpl.removeIdentifier(attributeNodeNS.getValue());
        }
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNode(Attr attr, boolean z) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.ownerDocument.errorChecking) {
            if (isReadOnly()) {
                zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
                return;
            } else if (attr.getOwnerElement() != this) {
                zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_FOUND_ERR", null));
                return;
            }
        }
        ((AttrImpl) attr).isIdAttribute(z);
        CoreDocumentImpl coreDocumentImpl = this.ownerDocument;
        if (z) {
            coreDocumentImpl.putIdentifier(attr.getValue(), this);
        } else {
            coreDocumentImpl.removeIdentifier(attr.getValue());
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void setOwnerDocument(CoreDocumentImpl coreDocumentImpl) {
        super.setOwnerDocument(coreDocumentImpl);
        AttributeMap attributeMap = this.attributes;
        if (attributeMap != null) {
            attributeMap.setOwnerDocument(coreDocumentImpl);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void setReadOnly(boolean z, boolean z2) {
        super.setReadOnly(z, z2);
        AttributeMap attributeMap = this.attributes;
        if (attributeMap != null) {
            attributeMap.setReadOnly(z, true);
        }
    }

    public int setXercesAttributeNode(Attr attr) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.attributes == null) {
            this.attributes = new AttributeMap(this, null);
        }
        return this.attributes.addItem(attr);
    }

    public void setupDefaultAttributes() {
        NamedNodeMapImpl defaultAttributes = getDefaultAttributes();
        if (defaultAttributes != null) {
            this.attributes = new AttributeMap(this, defaultAttributes);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void synchronizeData() {
        needsSyncData(false);
        boolean mutationEvents = this.ownerDocument.getMutationEvents();
        this.ownerDocument.setMutationEvents(false);
        setupDefaultAttributes();
        this.ownerDocument.setMutationEvents(mutationEvents);
    }

    public ElementImpl() {
    }

    @Override // org.w3c.dom.ElementTraversal
    public final Element getFirstElementChild() {
        Element firstElementChild;
        for (Node firstChild = getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            short nodeType = firstChild.getNodeType();
            if (nodeType == 1) {
                return (Element) firstChild;
            }
            if (nodeType == 5 && (firstElementChild = getFirstElementChild(firstChild)) != null) {
                return firstElementChild;
            }
        }
        return null;
    }

    @Override // org.w3c.dom.ElementTraversal
    public final Element getLastElementChild() {
        Element lastElementChild;
        for (Node lastChild = getLastChild(); lastChild != null; lastChild = lastChild.getPreviousSibling()) {
            short nodeType = lastChild.getNodeType();
            if (nodeType == 1) {
                return (Element) lastChild;
            }
            if (nodeType == 5 && (lastElementChild = getLastElementChild(lastChild)) != null) {
                return lastElementChild;
            }
        }
        return null;
    }
}
