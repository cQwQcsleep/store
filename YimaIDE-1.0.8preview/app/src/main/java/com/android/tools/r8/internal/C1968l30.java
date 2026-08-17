package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.l30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1968l30 extends AbstractC2311p30 implements BU {
    public final /* synthetic */ C2481r30 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1968l30(C2481r30 c2481r30) {
        super(c2481r30);
        this.h = c2481r30;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.c[a()];
    }
}
