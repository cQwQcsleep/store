package com.sun.org.apache.xerces.internal.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredCDATASectionImpl extends CDATASectionImpl implements DeferredNode {
    static final long serialVersionUID = 1983580632355645726L;
    protected transient int fNodeIndex;

    public DeferredCDATASectionImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = i;
        needsSyncData(true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DeferredNode
    public int getNodeIndex() {
        return this.fNodeIndex;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void synchronizeData() {
        needsSyncData(false);
        this.data = ((DeferredDocumentImpl) ownerDocument()).getNodeValueString(this.fNodeIndex);
    }
}
