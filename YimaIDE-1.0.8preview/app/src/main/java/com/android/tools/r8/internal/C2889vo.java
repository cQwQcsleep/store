package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2889vo extends AbstractC3220zi0 {
    public AbstractC3220zi0 a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ C0471Es c;
    public final /* synthetic */ Fj0 d;
    public final /* synthetic */ C2975wo e;

    public C2889vo(C2975wo c2975wo, boolean z, boolean z2, C0471Es c0471Es, Fj0 fj0) {
        this.e = c2975wo;
        this.b = z2;
        this.c = c0471Es;
        this.d = fj0;
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        if (this.b) {
            c2754uD.i();
            return;
        }
        AbstractC3220zi0 abstractC3220zi0 = this.a;
        if (abstractC3220zi0 == null) {
            C0471Es c0471Es = this.c;
            Ai0 ai0 = this.e;
            Fj0 fj0 = this.d;
            if (!c0471Es.e.contains(ai0)) {
                ai0 = c0471Es.d;
            }
            boolean z = false;
            for (Ai0 ai1 : c0471Es.e) {
                if (z) {
                    AbstractC3220zi0 abstractC3220zi0A = ai1.a(c0471Es, fj0);
                    if (abstractC3220zi0A != null) {
                        this.a = abstractC3220zi0A;
                        abstractC3220zi0 = abstractC3220zi0A;
                    }
                } else if (ai1 == ai0) {
                    z = true;
                }
            }
            aca.a("GSON cannot serialize ", fj0);
            return;
        }
        abstractC3220zi0.a(c2754uD, obj);
    }
}
