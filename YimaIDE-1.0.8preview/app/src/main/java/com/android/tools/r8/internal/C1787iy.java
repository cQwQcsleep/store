package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1787iy extends AbstractC1703hy implements BU {
    public final /* synthetic */ C1873jy h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1787iy(C1873jy c1873jy) {
        super(c1873jy);
        this.h = c1873jy;
    }

    @Override // java.util.Iterator
    public final Object next() {
        return this.h.d[a()];
    }
}
