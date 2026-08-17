package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.Ak0;
import com.android.tools.r8.internal.B1;
import com.android.tools.r8.internal.C2256oS;
import com.android.tools.r8.internal.C2440qc0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class v extends AbstractC3264e {
    public static final /* synthetic */ boolean e = true;
    public int b;
    public B1 a = Ak0.a;
    public int c = 0;
    public AbstractC0439Dm d = AbstractC0439Dm.m();

    public final v a(B1 b1) {
        if (!e && !this.a.isUnknown() && !b1.F()) {
            B1 b2 = this.a;
            b2.getClass();
            if (!(b2 instanceof C2256oS) || !this.a.m().a.K()) {
                x1f.a();
                return null;
            }
        }
        this.a = b1;
        return this;
    }

    @Override // com.android.tools.r8.ir.optimize.info.g
    public final g b() {
        return this;
    }

    @Override // com.android.tools.r8.ir.optimize.info.g
    public final v c() {
        return this;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3264e
    public final boolean e() {
        return (this.b & 1) != 0;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3264e
    public final B1 f() {
        return this.a;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3264e
    public final AbstractC0439Dm g() {
        return this.d;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3264e
    public final int h() {
        return this.c;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3264e
    public final boolean i() {
        return (this.b & 2) != 0;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3264e
    public final boolean j() {
        return (this.b & 4) != 0;
    }

    public final v a(B1 b1, C0210g1 c0210g1) {
        if (!e) {
            b1.getClass();
            if ((b1 instanceof C2440qc0) && !c0210g1.getType().U0()) {
                x1f.a();
                return null;
            }
        }
        return a(b1);
    }
}
