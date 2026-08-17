package com.android.tools.r8.graph;

import com.android.tools.r8.DesugarGraphConsumer;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.E6;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.synthesis.C3492a;
import defpackage.hzg;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/* JADX INFO: renamed from: com.android.tools.r8.graph.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0215h implements InterfaceC0189d1 {
    public static final /* synthetic */ boolean f = true;
    public final AbstractC0327x0 a;
    public final B1 b;
    public final com.android.tools.r8.shaking.R1 c;
    public final com.android.tools.r8.synthesis.J d;
    public final E6 e;

    public C0215h(C3492a c3492a, com.android.tools.r8.shaking.R1 r1) {
        this(c3492a.a, new com.android.tools.r8.synthesis.J(c3492a), r1, new E6());
    }

    public final void a(E0 e0, E0 e1) {
        boolean z = f;
        if (!z && e0.b0()) {
            x1f.a();
            return;
        }
        if (!z && e1.b0()) {
            x1f.a();
            return;
        }
        DesugarGraphConsumer desugarGraphConsumer = j().P1;
        if (desugarGraphConsumer == null) {
            return;
        }
        Origin origin = e1.d;
        List<Origin> listD = g().d(e0.getType());
        if (listD.isEmpty()) {
            Origin origin2 = e0.d;
            if (origin == com.android.tools.r8.origin.c.a() || origin2 == com.android.tools.r8.origin.c.a() || origin2 == origin) {
                return;
            }
            desugarGraphConsumer.accept(origin2, origin);
            return;
        }
        for (Origin origin3 : listD) {
            if (origin != com.android.tools.r8.origin.c.a() && origin3 != com.android.tools.r8.origin.c.a() && origin3 != origin) {
                desugarGraphConsumer.accept(origin3, origin);
            }
        }
    }

    public AbstractC0327x0 b() {
        if (!f) {
            c();
        }
        return this.a;
    }

    public final E0 c(I2 i2) {
        if (!f) {
            c();
        }
        com.android.tools.r8.synthesis.J j = this.d;
        AbstractC0327x0 abstractC0327x0 = this.a;
        Objects.requireNonNull(abstractC0327x0);
        return j.a(new hzg(abstractC0327x0), i2).P();
    }

    public Collection<D2> d() {
        if (!f) {
            c();
        }
        return this.a.d();
    }

    public final Collection e() {
        if (!f) {
            c();
        }
        return this.a.e();
    }

    public final com.android.tools.r8.shaking.R1 f() {
        if (!f) {
            c();
        }
        return this.c;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public InterfaceC0174b0 g(I2 i2) {
        if (!f) {
            c();
        }
        com.android.tools.r8.synthesis.J j = this.d;
        AbstractC0327x0 abstractC0327x0 = this.a;
        Objects.requireNonNull(abstractC0327x0);
        return j.a(new hzg(abstractC0327x0), i2);
    }

    public boolean h() {
        if (f) {
            return false;
        }
        c();
        return false;
    }

    public boolean i() {
        if (f) {
            return false;
        }
        c();
        return false;
    }

    public C2752uB j() {
        return this.a.d;
    }

    public final void k() {
        this.e.g();
    }

    public C0229j l() {
        if (f) {
            return null;
        }
        c();
        return null;
    }

    public C3403i m() {
        if (f) {
            return null;
        }
        c();
        return null;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public E0 d(I2 i2) {
        return c(i2);
    }

    public final boolean e(I2 i2) {
        return c(i2) != null;
    }

    public C0215h(AbstractC0327x0 abstractC0327x0, com.android.tools.r8.synthesis.J j, com.android.tools.r8.shaking.R1 r1, E6 e6) {
        this.a = abstractC0327x0;
        this.b = abstractC0327x0.e;
        this.c = r1;
        this.d = j;
        this.e = e6;
    }

    public final com.android.tools.r8.synthesis.J g() {
        if (!f) {
            c();
        }
        return this.d;
    }

    public final void c() {
        if (f || !this.e.a()) {
            return;
        }
        x1f.a();
    }

    public C0215h a(I5 i5, ExecutorService executorService, Ch0 ch0) {
        boolean z = f;
        if (!z && getClass() != C0215h.class) {
            x1f.a();
            return null;
        }
        if (!z) {
            c();
        }
        if (!z && i5.a != b()) {
            x1f.a();
            return null;
        }
        if (i5.d()) {
            return this;
        }
        ch0.a("Pruning AppInfo");
        com.android.tools.r8.synthesis.J jG = g();
        C0215h c0215h = new C0215h(com.android.tools.r8.synthesis.J.a(i5, jG.d, jG.e, jG.c, jG.a, jG.f), f().a(i5));
        ch0.b();
        return c0215h;
    }

    public C0215h a(com.android.tools.r8.shaking.R1 r1) {
        if (!f) {
            c();
        }
        return new C0215h(this.a, this.d, r1, new E6());
    }

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public B1 a() {
        if (!f) {
            c();
        }
        return this.b;
    }

    public static C0215h a(AbstractC0327x0 abstractC0327x0, com.android.tools.r8.synthesis.E e) {
        return new C0215h(com.android.tools.r8.synthesis.J.a(abstractC0327x0, e), com.android.tools.r8.shaking.R1.b());
    }

    public AbstractC0330x3 a(I2 i2, C0245l1 c0245l1, B5 b5) {
        if (i2 != b5.s()) {
            boolean z = AbstractC0330x3.a;
            return C0282q3.b;
        }
        D2 d2A = b5.a();
        C0210g1 c0210g1A = d2A.a(c0245l1);
        if (c0210g1A != null) {
            return AbstractC0330x3.a(d2A, d2A, c0210g1A);
        }
        boolean z2 = AbstractC0330x3.a;
        return A3.b;
    }
}
