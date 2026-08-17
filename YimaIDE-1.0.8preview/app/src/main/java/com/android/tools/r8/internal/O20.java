package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O20 extends T20 implements BU {
    public R20 h;
    public final /* synthetic */ V20 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O20(V20 v20) {
        super(v20);
        this.i = v20;
    }

    @Override // java.util.Iterator
    public final Object next() {
        R20 r20 = new R20(this.i, a());
        this.h = r20;
        return r20;
    }

    @Override // com.android.tools.r8.internal.T20, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
