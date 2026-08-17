package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3069xx extends AbstractC2984wx implements InterfaceC1640hA {
    public final /* synthetic */ C3153yx h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3069xx(C3153yx c3153yx) {
        super(c3153yx);
        this.h = c3153yx;
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
