package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1276cy extends AbstractC1703hy implements BU {
    public C1532fy h;
    public final /* synthetic */ C1873jy i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1276cy(C1873jy c1873jy) {
        super(c1873jy);
        this.i = c1873jy;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C1532fy c1532fy = new C1532fy(this.i, a());
        this.h = c1532fy;
        return c1532fy;
    }

    @Override // com.android.tools.r8.internal.AbstractC1703hy, java.util.Iterator
    public final void remove() {
        super.remove();
        this.h.b = -1;
    }
}
