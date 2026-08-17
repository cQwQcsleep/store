package com.sun.org.apache.xerces.internal.impl.xs.models;

import com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMStateSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSCMUniOp extends CMNode {
    private CMNode fChild;

    public XSCMUniOp(int i, CMNode cMNode) {
        super(i);
        if (type() == 5 || type() == 4 || type() == 6) {
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
        if (type() == 6) {
            return this.fChild.isNullable();
        }
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode
    public void setUserData(Object obj) {
        super.setUserData(obj);
        this.fChild.setUserData(obj);
    }
}
