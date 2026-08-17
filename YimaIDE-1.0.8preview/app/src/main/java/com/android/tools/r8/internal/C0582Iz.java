package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Iz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0582Iz extends AbstractC0685Mz {
    public final /* synthetic */ C0659Lz g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0582Iz(C0659Lz c0659Lz) {
        super(c0659Lz.j);
        this.g = c0659Lz;
        this.c = c0659Lz.i();
    }

    @Override // com.android.tools.r8.internal.AbstractC0685Mz
    public final void d() {
        C0374Az c0374AzC = this.c.c();
        this.c = c0374AzC;
        C0659Lz c0659Lz = this.g;
        if (c0659Lz.f || c0374AzC == null || c0659Lz.j.c(c0374AzC.b, c0659Lz.d) < 0) {
            return;
        }
        this.c = null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0685Mz
    public final void e() {
        C0374Az c0374AzE = this.b.e();
        this.b = c0374AzE;
        C0659Lz c0659Lz = this.g;
        if (c0659Lz.e || c0374AzE == null || c0659Lz.j.c(c0374AzE.b, c0659Lz.c) >= 0) {
            return;
        }
        this.b = null;
    }
}
