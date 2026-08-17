package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0788Qy extends AbstractC0892Uy {
    public final /* synthetic */ C0866Ty g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0788Qy(C0866Ty c0866Ty) {
        super(c0866Ty.j);
        this.g = c0866Ty;
        this.c = c0866Ty.i();
    }

    @Override // com.android.tools.r8.internal.AbstractC0892Uy
    public final void d() {
        C0581Iy c0581IyB = this.c.b();
        this.c = c0581IyB;
        C0866Ty c0866Ty = this.g;
        if (c0866Ty.f || c0581IyB == null || c0866Ty.j.c(c0581IyB.b, c0866Ty.d) < 0) {
            return;
        }
        this.c = null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0892Uy
    public final void e() {
        C0581Iy c0581IyD = this.b.d();
        this.b = c0581IyD;
        C0866Ty c0866Ty = this.g;
        if (c0866Ty.e || c0581IyD == null || c0866Ty.j.c(c0581IyD.b, c0866Ty.c) >= 0) {
            return;
        }
        this.b = null;
    }
}
