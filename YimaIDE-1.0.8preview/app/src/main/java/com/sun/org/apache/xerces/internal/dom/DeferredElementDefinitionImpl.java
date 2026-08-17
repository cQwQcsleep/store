package com.sun.org.apache.xerces.internal.dom;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredElementDefinitionImpl extends ElementDefinitionImpl implements DeferredNode {
    static final long serialVersionUID = 6703238199538041591L;
    protected transient int fNodeIndex;

    public DeferredElementDefinitionImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
        super(deferredDocumentImpl, null);
        this.fNodeIndex = i;
        needsSyncData(true);
        needsSyncChildren(true);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.DeferredNode
    public int getNodeIndex() {
        return this.fNodeIndex;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode
    public void synchronizeChildren() {
        boolean mutationEvents = this.ownerDocument.getMutationEvents();
        this.ownerDocument.setMutationEvents(false);
        needsSyncChildren(false);
        DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl) this.ownerDocument;
        this.attributes = new NamedNodeMapImpl(deferredDocumentImpl);
        for (int lastChild = deferredDocumentImpl.getLastChild(this.fNodeIndex); lastChild != -1; lastChild = deferredDocumentImpl.getPrevSibling(lastChild)) {
            this.attributes.setNamedItem(deferredDocumentImpl.getNodeObject(lastChild));
        }
        deferredDocumentImpl.setMutationEvents(mutationEvents);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl
    public void synchronizeData() {
        needsSyncData(false);
        this.name = ((DeferredDocumentImpl) this.ownerDocument).getNodeName(this.fNodeIndex);
    }
}
