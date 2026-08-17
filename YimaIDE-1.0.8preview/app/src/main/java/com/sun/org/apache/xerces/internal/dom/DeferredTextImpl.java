package com.sun.org.apache.xerces.internal.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredTextImpl extends TextImpl implements DeferredNode {
    static final long serialVersionUID = 2310613872100393425L;
    protected transient int fNodeIndex;

    public DeferredTextImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
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
        DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl) ownerDocument();
        this.data = deferredDocumentImpl.getNodeValueString(this.fNodeIndex);
        isIgnorableWhitespace(deferredDocumentImpl.getNodeExtra(this.fNodeIndex) == 1);
    }
}
