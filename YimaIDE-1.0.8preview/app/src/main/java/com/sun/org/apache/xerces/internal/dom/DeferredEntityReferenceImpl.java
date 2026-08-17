package com.sun.org.apache.xerces.internal.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredEntityReferenceImpl extends EntityReferenceImpl implements DeferredNode {
    static final long serialVersionUID = 390319091370032223L;
    protected transient int fNodeIndex;

    public DeferredEntityReferenceImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = i;
        needsSyncData(true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DeferredNode
    public int getNodeIndex() {
        return this.fNodeIndex;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.EntityReferenceImpl, com.sun.org.apache.xerces.internal.dom.ParentNode
    public void synchronizeChildren() {
        needsSyncChildren(false);
        isReadOnly(false);
        ((DeferredDocumentImpl) ownerDocument()).synchronizeChildren(this, this.fNodeIndex);
        setReadOnly(true, true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void synchronizeData() {
        needsSyncData(false);
        DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl) this.ownerDocument;
        this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        this.baseURI = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
    }
}
