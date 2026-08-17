package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2857vV implements r {
    public static final /* synthetic */ boolean c = true;
    public final B5 a;
    public final C2525rc0 b;

    public C2857vV(C0650Lp c0650Lp, C2525rc0 c2525rc0) {
        this.a = c0650Lp;
        this.b = c2525rc0;
    }

    @Override // com.android.tools.r8.internal.r
    public final Cl0 a(C0333y c0333y, InterfaceC0599Jq interfaceC0599Jq, AbstractC2530rf abstractC2530rf) {
        C2188nf c2188nfE = abstractC2530rf.e();
        return C2188nf.a(AbstractC2046m.b(c0333y, c2188nfE.d, this.b), c2188nfE.n());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C2857vV.class == obj.getClass()) {
            C2857vV c2857vV = (C2857vV) obj;
            if (this.a.equals(c2857vV.a) && this.b.equals(c2857vV.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(C2857vV.class, this.a, this.b);
    }

    @Override // com.android.tools.r8.internal.r
    public final Iterable k() {
        this.a.getClass();
        return C2753uC.b(this.a);
    }

    @Override // com.android.tools.r8.internal.r
    public final boolean a(B5 b5) {
        this.a.getClass();
        if (!c) {
            this.a.getClass();
        }
        return this.a.equals(b5);
    }
}
