package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2738u30 extends AbstractC3164z30 implements BU {
    public C2995x30 h;
    public final /* synthetic */ B30 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2738u30(B30 b30) {
        super(b30);
        this.i = b30;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C2995x30 c2995x30 = new C2995x30(this.i, a());
        this.h = c2995x30;
        return c2995x30;
    }

    @Override // com.android.tools.r8.internal.AbstractC3164z30, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
