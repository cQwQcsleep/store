package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Dm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0439Dm {
    public static final /* synthetic */ boolean a = true;

    public static C0491Fm a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0, C2441qd c2441qd) {
        abstractC2624sj0.getClass();
        if (abstractC2624sj0 instanceof C1720i7) {
            return C0491Fm.c;
        }
        if (abstractC2624sj0 instanceof C1034a40) {
            return C0491Fm.d;
        }
        if (abstractC2624sj0 instanceof Lh0) {
            return m();
        }
        if (c2441qd != null) {
            boolean z = a;
            if (!z && !abstractC2624sj0.w()) {
                x1f.a();
                return null;
            }
            if (z || abstractC2624sj0.N() == c2441qd.b) {
                return abstractC2624sj0.equals(c2441qd) ? new C2547ro(c2441qd) : C0465Em.a(c0333y, abstractC2624sj0.b(), c2441qd);
            }
            x1f.a();
            return null;
        }
        boolean z2 = a;
        if (!z2 && abstractC2624sj0.w()) {
            com.android.tools.r8.graph.E0 e0D = c0333y.d(abstractC2624sj0.b().Q());
            if (!z2 && e0D != null && e0D.c(c0333y)) {
                x1f.a();
                return null;
            }
        }
        return new C0491Fm(abstractC2624sj0);
    }

    public static C0491Fm m() {
        return C0491Fm.e;
    }

    public abstract AbstractC0439Dm a(C0333y c0333y, AbstractC3148ys abstractC3148ys, Set set);

    public abstract AbstractC0439Dm a(C2427qS c2427qS);

    public abstract AbstractC2624sj0 a(AbstractC2624sj0 abstractC2624sj0);

    public C2441qd b() {
        return null;
    }

    public abstract C2441qd c();

    public abstract C2427qS d();

    public boolean e() {
        return false;
    }

    public abstract boolean equals(Object obj);

    public boolean f() {
        return false;
    }

    public boolean g() {
        return false;
    }

    public boolean h() {
        return false;
    }

    public boolean i() {
        return c() != null;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return false;
    }

    public boolean l() {
        return false;
    }

    public C0491Fm a() {
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001b  */
    public static C0491Fm a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0) {
        C2441qd c2441qdB;
        if (abstractC2624sj0.w()) {
            c2441qdB = abstractC2624sj0.b();
            com.android.tools.r8.graph.E0 e0D = c0333y.d(c2441qdB.Q());
            if (e0D == null || !e0D.c(c0333y)) {
                c2441qdB = null;
            }
        } else {
            c2441qdB = null;
        }
        return a(c0333y, abstractC2624sj0, c2441qdB);
    }

    public static C2547ro a(C2441qd c2441qd) {
        return new C2547ro(c2441qd);
    }

    public final AbstractC0439Dm a(C0333y c0333y, AbstractC0439Dm abstractC0439Dm) {
        if (g()) {
            return abstractC0439Dm;
        }
        if (abstractC0439Dm.g() || equals(abstractC0439Dm)) {
            return this;
        }
        if (!l() && !abstractC0439Dm.l()) {
            if (!j() && !abstractC0439Dm.j()) {
                boolean z = a;
                if (!z && !h()) {
                    x1f.a();
                    return null;
                }
                if (z || abstractC0439Dm.h()) {
                    return a().a(c0333y, abstractC0439Dm.a());
                }
                x1f.a();
                return null;
            }
            if (!d().g() && !abstractC0439Dm.d().g()) {
                return C1744iS.b;
            }
            return m();
        }
        return m();
    }
}
