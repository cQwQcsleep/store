package com.sun.org.apache.xerces.internal.impl.dtd.models;

import com.sun.org.apache.xerces.internal.xni.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CMLeaf extends CMNode {
    private QName fElement;
    private int fPosition;

    public CMLeaf(QName qName, int i) {
        super(0);
        QName qName2 = new QName();
        this.fElement = qName2;
        this.fPosition = -1;
        qName2.setValues(qName);
        this.fPosition = i;
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

    public final QName getElement() {
        return this.fElement;
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
        StringBuilder sb = new StringBuilder(this.fElement.toString());
        sb.append(" (");
        sb.append(this.fElement.uri);
        sb.append(',');
        sb.append(this.fElement.localpart);
        sb.append(')');
        if (this.fPosition >= 0) {
            sb.append(" (Pos:" + this.fPosition + ")");
        }
        return sb.toString();
    }

    public CMLeaf(QName qName) {
        super(0);
        QName qName2 = new QName();
        this.fElement = qName2;
        this.fPosition = -1;
        qName2.setValues(qName);
    }
}
