package com.sun.org.apache.xerces.internal.impl.dtd.models;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CMUniOp extends CMNode {
    private CMNode fChild;

    public CMUniOp(int i, CMNode cMNode) {
        super(i);
        if (type() == 1 || type() == 2 || type() == 3) {
            this.fChild = cMNode;
        } else {
            f63.a("ImplementationMessages.VAL_UST");
            throw null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode
    public void calcFirstPos(CMStateSet cMStateSet) {
        cMStateSet.setTo(this.fChild.firstPos());
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode
    public void calcLastPos(CMStateSet cMStateSet) {
        cMStateSet.setTo(this.fChild.lastPos());
    }

    public final CMNode getChild() {
        return this.fChild;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode
    public boolean isNullable() {
        if (type() == 3) {
            return this.fChild.isNullable();
        }
        return true;
    }
}
