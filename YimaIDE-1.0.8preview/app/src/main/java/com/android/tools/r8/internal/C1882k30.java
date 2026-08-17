package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.k30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1882k30 extends AbstractC2311p30 implements BU {
    public C2139n30 h;
    public final /* synthetic */ C2481r30 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1882k30(C2481r30 c2481r30) {
        super(c2481r30);
        this.i = c2481r30;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C2139n30 c2139n30 = new C2139n30(this.i, a());
        this.h = c2139n30;
        return c2139n30;
    }

    @Override // com.android.tools.r8.internal.AbstractC2311p30, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
