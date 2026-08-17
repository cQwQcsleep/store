package com.sun.org.apache.xerces.internal.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredCommentImpl extends CommentImpl implements DeferredNode {
    static final long serialVersionUID = 6498796371083589338L;
    protected transient int fNodeIndex;

    public DeferredCommentImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
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
