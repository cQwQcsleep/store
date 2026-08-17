package com.sun.org.apache.xerces.internal.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class DeferredAttrNSImpl extends AttrNSImpl implements DeferredNode {
    static final long serialVersionUID = 6074924934945957154L;
    protected transient int fNodeIndex;

    public DeferredAttrNSImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = i;
        needsSyncData(true);
        needsSyncChildren(true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DeferredNode
    public int getNodeIndex() {
        return this.fNodeIndex;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.AttrImpl
    public void synchronizeChildren() {
        ((DeferredDocumentImpl) ownerDocument()).synchronizeChildren(this, this.fNodeIndex);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void synchronizeData() {
        needsSyncData(false);
        DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl) ownerDocument();
        String nodeName = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        this.name = nodeName;
        int iIndexOf = nodeName.indexOf(58);
        String str = this.name;
        if (iIndexOf < 0) {
            this.localName = str;
        } else {
            this.localName = str.substring(iIndexOf + 1);
        }
        int nodeExtra = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
        isSpecified((nodeExtra & 32) != 0);
        isIdAttribute((nodeExtra & 512) != 0);
        this.namespaceURI = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
        this.type = deferredDocumentImpl.getTypeInfo(deferredDocumentImpl.getLastChild(this.fNodeIndex));
    }
}
