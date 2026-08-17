package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.shaking.C3403i;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0491Fm extends AbstractC0439Dm {
    public static final C0491Fm c = new C0491Fm(AbstractC2624sj0.f());
    public static final C0491Fm d = new C0491Fm(AbstractC2624sj0.m());
    public static final C0491Fm e = new C0491Fm(AbstractC2624sj0.p());
    public static final /* synthetic */ boolean f = true;
    public final AbstractC2624sj0 b;

    public C0491Fm(AbstractC2624sj0 abstractC2624sj0) {
        if (f || abstractC2624sj0 != null) {
            this.b = abstractC2624sj0;
        } else {
            x1f.a();
            throw null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x008c  */
    public final C0491Fm a(C0333y c0333y, C0491Fm c0491Fm) {
        C2441qd c2441qdD;
        com.android.tools.r8.graph.E0 e0D;
        AbstractC2624sj0 abstractC2624sj0A = n().a(c0333y, c0491Fm.n());
        if (abstractC2624sj0A.w() && (e0D = c0333y.d(abstractC2624sj0A.b().Q())) != null && e0D.c(c0333y)) {
            c2441qdD = abstractC2624sj0A.b();
        } else if (k()) {
            if (c0491Fm.e()) {
                c2441qdD = c0491Fm.b().b(C2427qS.c());
            } else {
                c2441qdD = null;
            }
        } else if (c0491Fm.k()) {
            if (e()) {
                c2441qdD = b().b(C2427qS.c());
            } else {
                c2441qdD = null;
            }
        } else if (e() && c0491Fm.e()) {
            C2441qd c2441qdB = b();
            C2441qd c2441qdB2 = c0491Fm.b();
            if (c2441qdB.b(c2441qdB2, c0333y)) {
                c2441qdD = c2441qdB.b(c2441qdB2.b);
            } else if (c2441qdB2.b(c2441qdB, c0333y)) {
                c2441qdD = c2441qdB2.b(c2441qdB.b);
            } else {
                c2441qdD = null;
            }
        } else {
            c2441qdD = null;
        }
        return (abstractC2624sj0A.equals(n()) && Objects.equals(c2441qdD, b())) ? this : a((C0333y<C3403i>) c0333y, abstractC2624sj0A, c2441qdD);
    }

    public final boolean b(C0333y c0333y, C0491Fm c0491Fm) {
        if (equals(c0491Fm)) {
            return false;
        }
        if (n().equals(c0491Fm.n())) {
            if (c0491Fm.e()) {
                return e() && c0491Fm.b().c(b(), c0333y);
            }
            return e();
        }
        if (!n().c(c0491Fm.n(), c0333y)) {
            return false;
        }
        if (c0491Fm.e()) {
            return e() && c0491Fm.b().b(n(), c0333y);
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public C2441qd c() {
        if (e() && b().a(n())) {
            return b();
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public final C2427qS d() {
        return this.b.N();
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public boolean e() {
        return this instanceof C0465Em;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b.equals(((C0491Fm) obj).b);
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public final boolean f() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public final boolean g() {
        AbstractC2624sj0 abstractC2624sj0 = this.b;
        abstractC2624sj0.getClass();
        return abstractC2624sj0 instanceof C1720i7;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public final boolean h() {
        return true;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public boolean i() {
        return c() != null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public final boolean k() {
        AbstractC2624sj0 abstractC2624sj0 = this.b;
        abstractC2624sj0.getClass();
        return abstractC2624sj0 instanceof C1034a40;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public final boolean l() {
        AbstractC2624sj0 abstractC2624sj0 = this.b;
        abstractC2624sj0.getClass();
        return abstractC2624sj0 instanceof Lh0;
    }

    public AbstractC2624sj0 n() {
        return this.b;
    }

    public String toString() {
        return "DynamicTypeWithUpperBound(upperBound=" + n() + ")";
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public C2441qd b() {
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public C0491Fm a(C2427qS c2427qS) {
        if (!f && e()) {
            x1f.a();
            return null;
        }
        if (n().I()) {
            AbstractC1120b40 abstractC1120b40D = n().d();
            if (abstractC1120b40D.b != c2427qS) {
                return new C0491Fm(abstractC1120b40D.a(c2427qS));
            }
        }
        return this;
    }

    public static C0491Fm a(C0333y<C3403i> c0333y, AbstractC2624sj0 abstractC2624sj0, C2441qd c2441qd) {
        abstractC2624sj0.getClass();
        if (abstractC2624sj0 instanceof C1720i7) {
            return c;
        }
        if (abstractC2624sj0 instanceof C1034a40) {
            return d;
        }
        if (abstractC2624sj0 instanceof Lh0) {
            return AbstractC0439Dm.m();
        }
        if (c2441qd != null) {
            boolean z = f;
            if (!z && !abstractC2624sj0.w()) {
                x1f.a();
                return null;
            }
            if (!z && abstractC2624sj0.N() != c2441qd.b) {
                x1f.a();
                return null;
            }
            if (abstractC2624sj0.equals(c2441qd)) {
                return new C2547ro(c2441qd);
            }
            return C0465Em.a((C0333y) c0333y, abstractC2624sj0.b(), c2441qd);
        }
        boolean z2 = f;
        if (!z2 && abstractC2624sj0.w()) {
            com.android.tools.r8.graph.E0 e0D = c0333y.d(abstractC2624sj0.b().Q());
            if (!z2 && e0D != null && e0D.c(c0333y)) {
                x1f.a();
                return null;
            }
        }
        return new C0491Fm(abstractC2624sj0);
    }

    public static C0491Fm a(C0333y<C3403i> c0333y, AbstractC2624sj0 abstractC2624sj0) {
        com.android.tools.r8.graph.E0 e0D;
        return a(c0333y, abstractC2624sj0, (abstractC2624sj0.w() && (e0D = c0333y.d(abstractC2624sj0.b().Q())) != null && e0D.c(c0333y)) ? abstractC2624sj0.b() : null);
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public final AbstractC2624sj0 a(AbstractC2624sj0 abstractC2624sj0) {
        return n();
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public final C0491Fm a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC0439Dm
    public AbstractC0439Dm a(C0333y c0333y, AbstractC3148ys abstractC3148ys, Set set) {
        if (g() || k() || l()) {
            return this;
        }
        C2441qd c2441qdB = null;
        AbstractC2624sj0 abstractC2624sj0A = this.b.a(c0333y, abstractC3148ys, null, set);
        if (e()) {
            AbstractC2624sj0 abstractC2624sj0A2 = b().a(c0333y, abstractC3148ys, (AbstractC3148ys) null, set);
            if (abstractC2624sj0A2.w()) {
                c2441qdB = abstractC2624sj0A2.b();
            }
        }
        if (c2441qdB != null) {
            return a((C0333y<C3403i>) c0333y, abstractC2624sj0A, c2441qdB);
        }
        return a((C0333y<C3403i>) c0333y, abstractC2624sj0A);
    }
}
