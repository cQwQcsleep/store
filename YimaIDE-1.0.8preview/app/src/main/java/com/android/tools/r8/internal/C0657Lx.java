package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0657Lx extends AbstractC0761Px {
    public final /* synthetic */ C0735Ox g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0657Lx(C0735Ox c0735Ox) {
        super(c0735Ox.j);
        this.g = c0735Ox;
        this.c = c0735Ox.i();
    }

    @Override // com.android.tools.r8.internal.AbstractC0761Px
    public final void d() {
        C0450Dx c0450DxB = this.c.b();
        this.c = c0450DxB;
        C0735Ox c0735Ox = this.g;
        if (c0735Ox.f || c0450DxB == null || c0735Ox.j.c(c0450DxB.b, c0735Ox.d) < 0) {
            return;
        }
        this.c = null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0761Px
    public final void e() {
        C0450Dx c0450DxD = this.b.d();
        this.b = c0450DxD;
        C0735Ox c0735Ox = this.g;
        if (c0735Ox.e || c0450DxD == null || c0735Ox.j.c(c0450DxD.b, c0735Ox.c) >= 0) {
            return;
        }
        this.b = null;
    }
}
