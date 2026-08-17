package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SL extends XL implements BU {
    public VL h;
    public final /* synthetic */ ZL i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SL(ZL zl) {
        super(zl);
        this.i = zl;
    }

    @Override // java.util.Iterator
    public final Object next() {
        VL vl = new VL(this.i, a());
        this.h = vl;
        return vl;
    }

    @Override // com.android.tools.r8.internal.XL, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
