package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.NamedNodeMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredElementImpl extends ElementImpl implements DeferredNode {
    static final long serialVersionUID = -7670981133940934842L;
    protected transient int fNodeIndex;

    public DeferredElementImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = i;
        needsSyncChildren(true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DeferredNode
    public final int getNodeIndex() {
        return this.fNodeIndex;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode
    public final void synchronizeChildren() {
        ((DeferredDocumentImpl) ownerDocument()).synchronizeChildren(this, this.fNodeIndex);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ElementImpl, com.sun.org.apache.xerces.internal.dom.NodeImpl
    public final void synchronizeData() {
        needsSyncData(false);
        DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl) this.ownerDocument;
        boolean z = deferredDocumentImpl.mutationEvents;
        deferredDocumentImpl.mutationEvents = false;
        this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        setupDefaultAttributes();
        int nodeExtra = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
        if (nodeExtra != -1) {
            NamedNodeMap attributes = getAttributes();
            do {
                attributes.setNamedItem((NodeImpl) deferredDocumentImpl.getNodeObject(nodeExtra));
                nodeExtra = deferredDocumentImpl.getPrevSibling(nodeExtra);
            } while (nodeExtra != -1);
        }
        deferredDocumentImpl.mutationEvents = z;
    }
}
