package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2899vy extends AbstractC3240zy {
    public final /* synthetic */ C3154yy g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2899vy(C3154yy c3154yy) {
        super(c3154yy.j);
        this.g = c3154yy;
        this.c = c3154yy.i();
    }

    @Override // com.android.tools.r8.internal.AbstractC3240zy
    public final void d() {
        C2215ny c2215nyC = this.c.c();
        this.c = c2215nyC;
        C3154yy c3154yy = this.g;
        if (c3154yy.f || c2215nyC == null || c3154yy.j.c(c2215nyC.b, c3154yy.d) < 0) {
            return;
        }
        this.c = null;
    }

    @Override // com.android.tools.r8.internal.AbstractC3240zy
    public final void e() {
        C2215ny c2215nyE = this.b.e();
        this.b = c2215nyE;
        C3154yy c3154yy = this.g;
        if (c3154yy.e || c2215nyE == null || c3154yy.j.c(c2215nyE.b, c3154yy.c) >= 0) {
            return;
        }
        this.b = null;
    }
}
