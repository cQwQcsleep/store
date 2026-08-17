package com.sun.org.apache.xerces.internal.dom;

import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocumentFragmentImpl extends ParentNode implements DocumentFragment {
    static final long serialVersionUID = -7596449967279236746L;

    public DocumentFragmentImpl(CoreDocumentImpl coreDocumentImpl) {
        super(coreDocumentImpl);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return "#document-fragment";
    }

    @Override // com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return (short) 11;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public void normalize() {
        if (isNormalized()) {
            return;
        }
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        ChildNode childNode = this.firstChild;
        while (childNode != null) {
            ChildNode childNode2 = childNode.nextSibling;
            if (childNode.getNodeType() == 3) {
                if (childNode2 != null && childNode2.getNodeType() == 3) {
                    ((Text) childNode).appendData(childNode2.getNodeValue());
                    removeChild(childNode2);
                    childNode2 = childNode;
                } else if (childNode.getNodeValue() == null || childNode.getNodeValue().length() == 0) {
                    removeChild(childNode);
                }
            }
            childNode.normalize();
            childNode = childNode2;
        }
        isNormalized(true);
    }

    public DocumentFragmentImpl() {
    }
}
