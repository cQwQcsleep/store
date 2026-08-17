package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2530rf extends WR {
    public static final /* synthetic */ boolean c = true;
    public Set b;

    public AbstractC2530rf(Set set) {
        this.b = set;
    }

    public final WR a(C0333y c0333y, Cl0 cl0, com.android.tools.r8.graph.I2 i2, Y1 y1) {
        if (cl0.g()) {
            return this;
        }
        if (cl0 instanceof Bk0) {
            return Bk0.b;
        }
        AbstractC2530rf abstractC2530rfC = cl0.c();
        if (j()) {
            if (c || abstractC2530rfC.j()) {
                return f().a(c0333y, abstractC2530rfC.f(), i2, y1);
            }
            x1f.a();
            return null;
        }
        C2188nf c2188nfE = e();
        C2188nf c2188nfE2 = abstractC2530rfC.e();
        c2188nfE.getClass();
        if (!C2188nf.e && !i2.T0()) {
            x1f.a();
            return null;
        }
        B1 b1 = c2188nfE2.d;
        B1 b2 = c2188nfE.d;
        G1 g1 = c0333y.w;
        g1.getClass();
        F1 f1 = F1.b;
        AbstractC2624sj0 abstractC2624sj0B = i2.b(g1.a);
        B1 b1A = g1.a(b2, b1, abstractC2624sj0B);
        if (!G1.c && !b1A.equals(g1.a(b1, b2, abstractC2624sj0B))) {
            x1f.a();
            return null;
        }
        c2188nfE.d = b1A;
        boolean zEquals = b1A.equals(b2);
        if (c2188nfE.d.isUnknown()) {
            return Bk0.b;
        }
        boolean zA = c2188nfE.a(c2188nfE2);
        if (c2188nfE.b(c0333y)) {
            return Bk0.b;
        }
        if (zEquals && !zA) {
            return c2188nfE;
        }
        y1.b();
        return c2188nfE;
    }

    public final boolean b(C0333y c0333y) {
        Set set = this.b;
        if (set == null) {
            return false;
        }
        int size = set.size();
        c0333y.M().c().getClass();
        return size > 10;
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final AbstractC2530rf c() {
        return this;
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final boolean h() {
        return true;
    }

    public final Set n() {
        if (!this.b.isEmpty()) {
            return new HashSet(this.b);
        }
        if (c || this.b == Collections.EMPTY_SET) {
            return this.b;
        }
        x1f.a();
        return null;
    }

    public abstract AbstractC1889k7 o();

    public final Set p() {
        if (c || this.b.isEmpty() || (this.b instanceof HashSet)) {
            return this.b;
        }
        x1f.a();
        return null;
    }

    public final boolean q() {
        return !this.b.isEmpty();
    }

    public abstract boolean r();

    public abstract boolean s();

    @Override // com.android.tools.r8.internal.Cl0
    public final /* bridge */ /* synthetic */ Cl0 a(C0333y c0333y, Cl0 cl0, com.android.tools.r8.graph.I2 i2, AbstractC1589ge0 abstractC1589ge0, Y1 y1) {
        return a(c0333y, cl0, i2, y1);
    }

    public final boolean a(AbstractC2530rf abstractC2530rf) {
        Set setP = abstractC2530rf.p();
        if (setP.isEmpty()) {
            return false;
        }
        if (this.b.isEmpty()) {
            if (!c && this.b != Collections.EMPTY_SET) {
                x1f.a();
                return false;
            }
            this.b = new HashSet();
        }
        return this.b.addAll(setP);
    }
}
