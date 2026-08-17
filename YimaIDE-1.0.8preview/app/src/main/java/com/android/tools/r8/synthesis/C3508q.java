package com.android.tools.r8.synthesis;

import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.I0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.XR;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.synthesis.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3508q extends AbstractC3505n {
    public static final /* synthetic */ boolean e = true;

    public C3508q(S.b bVar, C3502k c3502k, I2 i2) {
        super(bVar, c3502k, i2);
    }

    @Override // com.android.tools.r8.synthesis.a0
    public final AbstractC3509s a(Function function) {
        E0 e0 = (E0) function.apply(this.d);
        if (e0 == null) {
            return null;
        }
        if (e || (e0 instanceof I0)) {
            return new C3507p(this.a, this.b, e0.m());
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.synthesis.a0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C3508q a(C3502k c3502k, XR xr) {
        boolean z = e;
        if (!z) {
            I2 i2 = this.d;
            if (i2 != xr.c(i2)) {
                pe1.a("Unexpected classpath rewrite of type ", this.d.m0());
                return null;
            }
        }
        if (z || b() == c3502k) {
            return this;
        }
        pe1.a("Unexpected classpath rewrite of context type ", b());
        return null;
    }
}
