package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2556rx extends AbstractC2984wx implements BU {
    public C2812ux h;
    public final /* synthetic */ C3153yx i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2556rx(C3153yx c3153yx) {
        super(c3153yx);
        this.i = c3153yx;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C2812ux c2812ux = new C2812ux(this.i, a());
        this.h = c2812ux;
        return c2812ux;
    }

    @Override // com.android.tools.r8.internal.AbstractC2984wx, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
