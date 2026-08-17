package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.q30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2397q30 extends AbstractC2311p30 implements InterfaceC1640hA {
    public final /* synthetic */ C2481r30 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2397q30(C2481r30 c2481r30) {
        super(c2481r30);
        this.h = c2481r30;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return Integer.valueOf(this.h.d[a()]);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        return this.h.d[a()];
    }
}
