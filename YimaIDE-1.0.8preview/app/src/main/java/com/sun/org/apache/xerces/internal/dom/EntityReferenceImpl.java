package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.util.URI;
import org.w3c.dom.DocumentType;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EntityReferenceImpl extends ParentNode implements EntityReference {
    static final long serialVersionUID = -7381452955687102062L;
    protected String baseURI;
    protected String name;

    public EntityReferenceImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl);
        this.name = str;
        isReadOnly(true);
        needsSyncChildren(true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.ChildNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        EntityReferenceImpl entityReferenceImpl = (EntityReferenceImpl) super.cloneNode(z);
        entityReferenceImpl.setReadOnly(true, z);
        return entityReferenceImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getBaseURI() {
        NamedNodeMap entities;
        EntityImpl entityImpl;
        if (needsSyncData()) {
            synchronizeData();
        }
        String str = this.baseURI;
        if (str == null) {
            DocumentType doctype = getOwnerDocument().getDoctype();
            if (doctype != null && (entities = doctype.getEntities()) != null && (entityImpl = (EntityImpl) entities.getNamedItem(getNodeName())) != null) {
                return entityImpl.getBaseURI();
            }
        } else if (str != null && str.length() != 0) {
            try {
                return new URI(this.baseURI).toString();
            } catch (URI.MalformedURIException unused) {
                return null;
            }
        }
        return this.baseURI;
    }

    public String getEntityRefValue() {
        String nodeValue;
        String nodeValue2;
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        ChildNode childNode = this.firstChild;
        if (childNode == null) {
            return "";
        }
        short nodeType = childNode.getNodeType();
        ChildNode childNode2 = this.firstChild;
        if (nodeType == 5) {
            nodeValue = ((EntityReferenceImpl) childNode2).getEntityRefValue();
        } else {
            if (childNode2.getNodeType() != 3) {
                return null;
            }
            nodeValue = this.firstChild.getNodeValue();
        }
        if (this.firstChild.nextSibling == null) {
            return nodeValue;
        }
        StringBuffer stringBuffer = new StringBuffer(nodeValue);
        for (ChildNode childNode3 = this.firstChild.nextSibling; childNode3 != null; childNode3 = childNode3.nextSibling) {
            if (childNode3.getNodeType() == 5) {
                nodeValue2 = ((EntityReferenceImpl) childNode3).getEntityRefValue();
            } else {
                if (childNode3.getNodeType() != 3) {
                    return null;
                }
                nodeValue2 = childNode3.getNodeValue();
            }
            stringBuffer.append(nodeValue2);
        }
        return stringBuffer.toString();
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
        return (short) 5;
    }

    public void setBaseURI(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.baseURI = str;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void setReadOnly(boolean z, boolean z2) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (z2) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            for (ChildNode childNode = this.firstChild; childNode != null; childNode = childNode.nextSibling) {
                childNode.setReadOnly(z, true);
            }
        }
        isReadOnly(z);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode
    public void synchronizeChildren() {
        NamedNodeMap entities;
        EntityImpl entityImpl;
        needsSyncChildren(false);
        DocumentType doctype = getOwnerDocument().getDoctype();
        if (doctype == null || (entities = doctype.getEntities()) == null || (entityImpl = (EntityImpl) entities.getNamedItem(getNodeName())) == null) {
            return;
        }
        isReadOnly(false);
        for (Node firstChild = entityImpl.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            insertBefore(firstChild.cloneNode(true), null);
        }
        setReadOnly(true, true);
    }
}
