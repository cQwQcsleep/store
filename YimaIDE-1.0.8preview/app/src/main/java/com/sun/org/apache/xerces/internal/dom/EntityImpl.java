package com.sun.org.apache.xerces.internal.dom;

import defpackage.zi0;
import org.w3c.dom.DOMException;
import org.w3c.dom.Entity;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EntityImpl extends ParentNode implements Entity {
    static final long serialVersionUID = -3575760943444303423L;
    protected String baseURI;
    protected String encoding;
    protected String inputEncoding;
    protected String name;
    protected String notationName;
    protected String publicId;
    protected String systemId;
    protected String version;

    public EntityImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl);
        this.name = str;
        isReadOnly(true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.ChildNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        EntityImpl entityImpl = (EntityImpl) super.cloneNode(z);
        entityImpl.setReadOnly(true, z);
        return entityImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getBaseURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        String str = this.baseURI;
        return str != null ? str : ((CoreDocumentImpl) getOwnerDocument()).getBaseURI();
    }

    @Override // org.w3c.dom.Entity
    public String getInputEncoding() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.inputEncoding;
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
        return (short) 6;
    }

    @Override // org.w3c.dom.Entity
    public String getNotationName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.notationName;
    }

    @Override // org.w3c.dom.Entity
    public String getPublicId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.publicId;
    }

    @Override // org.w3c.dom.Entity
    public String getSystemId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.systemId;
    }

    @Override // org.w3c.dom.Entity
    public String getXmlEncoding() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.encoding;
    }

    @Override // org.w3c.dom.Entity
    public String getXmlVersion() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.version;
    }

    public void setBaseURI(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.baseURI = str;
    }

    public void setInputEncoding(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.inputEncoding = str;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void setNodeValue(String str) throws DOMException {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
    }

    public void setNotationName(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.notationName = str;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void setPrefix(String str) throws DOMException {
        if (this.ownerDocument.errorChecking && isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
    }

    public void setPublicId(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.publicId = str;
    }

    public void setSystemId(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.systemId = str;
    }

    public void setXmlEncoding(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.encoding = str;
    }

    public void setXmlVersion(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.version = str;
    }
}
