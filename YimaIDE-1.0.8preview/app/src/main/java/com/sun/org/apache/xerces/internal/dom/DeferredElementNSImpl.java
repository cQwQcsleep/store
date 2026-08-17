package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import org.w3c.dom.NamedNodeMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredElementNSImpl extends ElementNSImpl implements DeferredNode {
    static final long serialVersionUID = -5001885145370927385L;
    protected transient int fNodeIndex;

    public DeferredElementNSImpl(DeferredDocumentImpl deferredDocumentImpl, int i) {
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
        boolean z = false;
        needsSyncData(false);
        DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl) this.ownerDocument;
        boolean z2 = deferredDocumentImpl.mutationEvents;
        deferredDocumentImpl.mutationEvents = false;
        String nodeName = deferredDocumentImpl.getNodeName(this.fNodeIndex);
        this.name = nodeName;
        int iIndexOf = nodeName.indexOf(58);
        String str = this.name;
        if (iIndexOf < 0) {
            this.localName = str;
        } else {
            this.localName = str.substring(iIndexOf + 1);
        }
        this.namespaceURI = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
        this.type = (XSTypeDefinition) deferredDocumentImpl.getTypeInfo(this.fNodeIndex);
        setupDefaultAttributes();
        int nodeExtra = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
        if (nodeExtra != -1) {
            NamedNodeMap attributes = getAttributes();
            do {
                AttrImpl attrImpl = (AttrImpl) deferredDocumentImpl.getNodeObject(nodeExtra);
                if (attrImpl.getSpecified() || (!z && (attrImpl.getNamespaceURI() == null || attrImpl.getNamespaceURI() == NamespaceContext.XMLNS_URI || attrImpl.getName().indexOf(58) >= 0))) {
                    attributes.setNamedItem(attrImpl);
                } else {
                    attributes.setNamedItemNS(attrImpl);
                    z = true;
                }
                nodeExtra = deferredDocumentImpl.getPrevSibling(nodeExtra);
            } while (nodeExtra != -1);
        }
        deferredDocumentImpl.mutationEvents = z2;
    }
}
