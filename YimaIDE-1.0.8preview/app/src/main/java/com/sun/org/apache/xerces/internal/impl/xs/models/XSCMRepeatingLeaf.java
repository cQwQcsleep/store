package com.sun.org.apache.xerces.internal.impl.xs.models;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XSCMRepeatingLeaf extends XSCMLeaf {
    private final int fMaxOccurs;
    private final int fMinOccurs;

    public XSCMRepeatingLeaf(int i, Object obj, int i2, int i3, int i4, int i5) {
        super(i, obj, i4, i5);
        this.fMinOccurs = i2;
        this.fMaxOccurs = i3;
    }

    public final int getMaxOccurs() {
        return this.fMaxOccurs;
    }

    public final int getMinOccurs() {
        return this.fMinOccurs;
    }
}
