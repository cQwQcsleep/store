package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UU extends AbstractC3220zi0 {
    public static final TU b = new TU();
    public final C0471Es a;

    public UU(C0471Es c0471Es) {
        this.a = c0471Es;
    }

    @Override // com.android.tools.r8.internal.AbstractC3220zi0
    public final void a(C2754uD c2754uD, Object obj) throws IOException {
        if (obj == null) {
            c2754uD.i();
            return;
        }
        C0471Es c0471Es = this.a;
        Class<?> cls = obj.getClass();
        c0471Es.getClass();
        AbstractC3220zi0 abstractC3220zi0A = c0471Es.a(new Fj0(cls));
        if (!(abstractC3220zi0A instanceof UU)) {
            abstractC3220zi0A.a(c2754uD, obj);
        } else {
            c2754uD.e();
            c2754uD.g();
        }
    }
}
