package com.sun.org.apache.xerces.internal.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredProcessingInstructionImpl extends ProcessingInstructionImpl implements DeferredNode {
    static final long serialVersionUID = -4643577954293565388L;
    protected transient int fNodeIndex;

    public DeferredProcessingInstructionImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
        super(deferredDocumentImpl, null, null);
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
        DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl) ownerDocument();
        this.target = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        this.data = deferredDocumentImpl.getNodeValueString(this.fNodeIndex);
    }
}
