package com.sun.org.apache.xerces.internal.impl.xs.models;

import com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMStateSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSCMLeaf extends CMNode {
    private Object fLeaf;
    private int fParticleId;
    private int fPosition;

    public XSCMLeaf(int i, Object obj, int i2, int i3) {
        super(i);
        this.fLeaf = obj;
        this.fParticleId = i2;
        this.fPosition = i3;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode
    public void calcFirstPos(CMStateSet cMStateSet) {
        int i = this.fPosition;
        if (i == -1) {
            cMStateSet.zeroBits();
        } else {
            cMStateSet.setBit(i);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode
    public void calcLastPos(CMStateSet cMStateSet) {
        int i = this.fPosition;
        if (i == -1) {
            cMStateSet.zeroBits();
        } else {
            cMStateSet.setBit(i);
        }
    }

    public final Object getLeaf() {
        return this.fLeaf;
    }

    public final int getParticleId() {
        return this.fParticleId;
    }

    public final int getPosition() {
        return this.fPosition;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode
    public boolean isNullable() {
        return this.fPosition == -1;
    }

    public final void setPosition(int i) {
        this.fPosition = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(this.fLeaf.toString());
        if (this.fPosition >= 0) {
            stringBuffer.append(" (Pos:" + Integer.toString(this.fPosition) + ")");
        }
        return stringBuffer.toString();
    }
}
