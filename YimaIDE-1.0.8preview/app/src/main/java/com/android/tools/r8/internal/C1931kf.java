package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1931kf extends AbstractC2389q implements QI {
    public static final /* synthetic */ boolean b = true;
    public final Set a;

    public C1931kf(C0210g1 c0210g1) {
        Set setC = AbstractC2780ub0.c();
        setC.add(c0210g1);
        this.a = setC;
    }

    @Override // com.android.tools.r8.internal.AbstractC2389q
    public final AbstractC2389q a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.graph.I5 i5) {
        if (!b && this.a.isEmpty()) {
            x1f.a();
            return null;
        }
        C1931kf c1931kf = new C1931kf();
        for (C0210g1 c0210g1 : this.a) {
            if (!i5.a(c0210g1.getReference())) {
                C0245l1 c0245l1E = abstractC3148ys.e((AbstractC3148ys) null, c0210g1.getReference());
                c0333y.getClass();
                C0210g1 c0210g1B = c0245l1E.b(c0333y.d(c0245l1E.f));
                if (c0210g1B != null) {
                    c1931kf.a.add(c0210g1B);
                } else if (!b) {
                    x1f.a();
                    return null;
                }
            }
        }
        return c1931kf;
    }

    @Override // com.android.tools.r8.internal.AbstractC2389q
    public final QI b() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2389q
    public final boolean c() {
        return this.a.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == C1931kf.class) {
            return this.a.equals(((C1931kf) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.internal.QI
    public final int size() {
        return this.a.size();
    }

    public C1931kf() {
        this.a = AbstractC2780ub0.c();
    }

    @Override // com.android.tools.r8.internal.QI
    public final boolean a(C0210g1 c0210g1) {
        return this.a.contains(c0210g1);
    }

    @Override // com.android.tools.r8.internal.AbstractC2389q
    public final boolean a(com.android.tools.r8.graph.F0 f0) {
        return this.a.contains(f0.e());
    }

    @Override // com.android.tools.r8.internal.AbstractC2389q
    public final AbstractC2389q a(C0333y c0333y, com.android.tools.r8.graph.proto.c cVar) {
        if (!b && this.a.isEmpty()) {
            x1f.a();
            return null;
        }
        if (!cVar.c()) {
            AbstractC2389q abstractC2389qA = C0828Sm.a.a(c0333y, cVar);
            if (!abstractC2389qA.c()) {
                C1931kf c1931kfA = abstractC2389qA.a();
                c1931kfA.a.addAll(this.a);
                return c1931kfA;
            }
        }
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2389q, com.android.tools.r8.internal.QI
    public final C1931kf a() {
        return this;
    }
}
