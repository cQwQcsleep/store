package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.util.URI;
import defpackage.zi0;
import org.w3c.dom.Notation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NotationImpl extends NodeImpl implements Notation {
    static final long serialVersionUID = -764632195890658402L;
    protected String baseURI;
    protected String name;
    protected String publicId;
    protected String systemId;

    public NotationImpl(CoreDocumentImpl coreDocumentImpl, String str) {
        super(coreDocumentImpl);
        this.name = str;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getBaseURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        String str = this.baseURI;
        if (str == null || str.length() == 0) {
            return this.baseURI;
        }
        try {
            return new URI(this.baseURI).toString();
        } catch (URI.MalformedURIException unused) {
            return null;
        }
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
        return (short) 12;
    }

    @Override // org.w3c.dom.Notation
    public String getPublicId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.publicId;
    }

    @Override // org.w3c.dom.Notation
    public String getSystemId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.systemId;
    }

    public void setBaseURI(String str) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.baseURI = str;
    }

    public void setPublicId(String str) {
        if (isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        this.publicId = str;
    }

    public void setSystemId(String str) {
        if (isReadOnly()) {
            zi0.a(7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
            return;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        this.systemId = str;
    }
}
