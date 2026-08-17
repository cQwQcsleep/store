package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2257oT extends AbstractC2684tT implements BU {
    public C2513rT h;
    public final /* synthetic */ C2855vT i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2257oT(C2855vT c2855vT) {
        super(c2855vT);
        this.i = c2855vT;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C2513rT c2513rT = new C2513rT(this.i, a());
        this.h = c2513rT;
        return c2513rT;
    }

    @Override // com.android.tools.r8.internal.AbstractC2684tT, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
