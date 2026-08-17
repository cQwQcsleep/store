package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.ir.optimize.info.AbstractC3264e;
import com.android.tools.r8.ir.optimize.info.C;
import com.android.tools.r8.ir.optimize.info.C3262c;
import com.android.tools.r8.utils.structural.A;
import defpackage.hih;
import defpackage.jwg;
import defpackage.k26;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.g1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0210g1 extends AbstractC0217h1<C0210g1, C0245l1> implements com.android.tools.r8.utils.structural.x<C0210g1> {
    public static final C0210g1[] o = new C0210g1[0];
    public static final /* synthetic */ boolean p = true;
    public final C0205f3 g;
    public O2 h;
    public C0297s5 i;
    public final boolean j;
    public B3.e k;
    public AbstractC3264e l;
    public com.android.tools.r8.kotlin.G m;
    public boolean n;

    public C0210g1(C0245l1 c0245l1, C0205f3 c0205f3, B3.e eVar, C0306u0 c0306u0, O2 o2, com.android.tools.r8.androidapi.f fVar, boolean z, boolean z2) {
        super(c0245l1, c0306u0, z2, fVar);
        this.i = null;
        this.l = C3262c.a;
        this.m = com.android.tools.r8.kotlin.d0.a;
        this.n = false;
        this.g = c0205f3;
        this.h = o2;
        this.j = z;
        this.k = eVar;
        boolean z3 = p;
        if (!z3 && eVar == null) {
            x1f.a();
            throw null;
        }
        if (z3 || T3.a(eVar, c0306u0)) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final void B() {
        this.k = B3.e.p();
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final void B0() {
        this.m = com.android.tools.r8.kotlin.d0.a;
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final com.android.tools.r8.androidapi.f C0() {
        return this.e;
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final com.android.tools.r8.ir.optimize.info.g G0() {
        return this.l;
    }

    public final void L0() {
        if (p || this.g.n()) {
            this.h = null;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1, com.android.tools.r8.graph.InterfaceC0265o0
    /* JADX INFO: renamed from: M0, reason: merged with bridge method [inline-methods] */
    public C0205f3 getAccessFlags() {
        return this.g;
    }

    public B3.e N0() {
        return this.k;
    }

    public final com.android.tools.r8.kotlin.G O0() {
        return this.m;
    }

    public final AbstractC3264e P0() {
        return this.l;
    }

    public final C0297s5 Q0() {
        return this.i;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public O2 R0() {
        if (p || this.g.n()) {
            O2 o2 = this.h;
            return o2 == null ? O2.a(getReference().i) : o2;
        }
        x1f.a();
        return null;
    }

    public boolean S0() {
        if (p || this.g.n()) {
            return this.h != null;
        }
        x1f.a();
        return false;
    }

    public final boolean T0() {
        C0297s5 c0297s5 = this.i;
        if (c0297s5 != null) {
            return !c0297s5.b.a(getReference());
        }
        return false;
    }

    public final boolean U0() {
        return this.j;
    }

    public final boolean V0() {
        return this.g.H();
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final com.android.tools.r8.kotlin.P W() {
        return this.m;
    }

    public final boolean W0() {
        return !z0();
    }

    public final boolean X0() {
        return this.g.J();
    }

    public I2 Y0() {
        return getReference().i;
    }

    public final boolean a(B1 b1) {
        if (this.n) {
            return true;
        }
        if (!z0() || !this.g.f() || !S0()) {
            return false;
        }
        if (getType().T0()) {
            return true;
        }
        if (getType() != b1.Y1 || !R0().Y0()) {
            return false;
        }
        this.n = true;
        return true;
    }

    public final boolean b(B1 b1) {
        if (this.g.n() && this.h != null) {
            if (getReference().i.T0() && !p && this.h.a(b1) != getReference().i) {
                k26.a("Static ", getReference(), " has invalid static value ", this.h, ".");
                return false;
            }
            if (this.h.V0() && !p && !getReference().i.U0()) {
                hih.a("Static ", getReference(), " has invalid null static value.");
                return false;
            }
        }
        return true;
    }

    public I2 getType() {
        return getReference().getType();
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final String l0() {
        return getReference().l0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public String m0() {
        return getReference().m0();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: qwg
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a2) {
                C0210g1.a(a2);
            }
        };
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final C0210g1 p0() {
        return this;
    }

    public final String toString() {
        return "Encoded field " + getReference();
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public boolean z0() {
        return this.g.n();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.graph.g1$a */
    public static class a {
        public static final /* synthetic */ boolean n = true;
        public C0245l1 a;
        public C0306u0 b;
        public C0205f3 c;
        public B3.e d;
        public final com.android.tools.r8.kotlin.G e;
        public O2 f;
        public final C0297s5 g;
        public com.android.tools.r8.androidapi.f h;
        public final AbstractC3264e i;
        public boolean j;
        public final boolean k;
        public Consumer l;
        public boolean m;

        public a(boolean z, C0210g1 c0210g1) {
            this.b = C0306u0.o0();
            this.d = B3.e.p();
            this.e = com.android.tools.r8.kotlin.d0.a;
            this.f = null;
            this.g = null;
            int i = com.android.tools.r8.androidapi.f.a;
            this.h = com.android.tools.r8.androidapi.g.b;
            this.i = C3262c.a;
            this.l = C0822Sg.b();
            this.m = true;
            this.a = c0210g1.getReference();
            C0205f3 c0205f3 = c0210g1.g;
            this.c = new C0205f3(c0205f3.b, c0205f3.c);
            this.d = c0210g1.N0();
            this.e = c0210g1.m;
            this.b = c0210g1.n0();
            this.f = c0210g1.h;
            this.g = c0210g1.i;
            this.h = c0210g1.e;
            AbstractC3264e abstractC3264e = c0210g1.l;
            abstractC3264e.getClass();
            boolean z2 = abstractC3264e instanceof com.android.tools.r8.ir.optimize.info.v;
            AbstractC3264e abstractC3264e2 = c0210g1.l;
            AbstractC3264e abstractC3264e3 = abstractC3264e2;
            if (z2) {
                com.android.tools.r8.ir.optimize.info.v vVarC = abstractC3264e2.c();
                vVarC.getClass();
                com.android.tools.r8.ir.optimize.info.v vVar = new com.android.tools.r8.ir.optimize.info.v();
                vVar.a = vVarC.a;
                vVar.b = vVarC.b;
                vVar.c = vVarC.c;
                vVar.d = vVarC.d;
                abstractC3264e3 = vVar;
            }
            this.i = abstractC3264e3;
            this.j = c0210g1.j;
            this.k = z;
        }

        public C0210g1 a() {
            boolean z = n;
            if (!z && this.a == null) {
                x1f.a();
                return null;
            }
            if (!z && this.c == null) {
                x1f.a();
                return null;
            }
            if (!z && this.d == null) {
                x1f.a();
                return null;
            }
            if (!z && this.b == null) {
                x1f.a();
                return null;
            }
            if (!z && this.m && this.h.G()) {
                x1f.a();
                return null;
            }
            C0210g1 c0210g1 = new C0210g1(this.a, this.c, this.d, this.b, this.f, this.h, this.j, this.k);
            com.android.tools.r8.kotlin.G g = this.e;
            if (!C0210g1.p && c0210g1.m != com.android.tools.r8.kotlin.d0.a) {
                x1f.a();
                return null;
            }
            c0210g1.m = g;
            c0210g1.l = this.i;
            c0210g1.i = this.g;
            this.l.accept(c0210g1);
            return c0210g1;
        }

        public final a b() {
            this.l = this.l.andThen(new Consumer() { // from class: rwg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    C.b.a((C0210g1) obj, AbstractC0439Dm.m());
                }
            });
            return this;
        }

        public a c() {
            this.m = false;
            return this;
        }

        public final void b(Consumer consumer) {
            consumer.accept(this.c);
        }

        public a a(C0245l1 c0245l1) {
            this.a = c0245l1;
            return this;
        }

        public final a a(Consumer consumer) {
            consumer.accept(this);
            return this;
        }

        public a(boolean z) {
            this.b = C0306u0.o0();
            this.d = B3.e.p();
            this.e = com.android.tools.r8.kotlin.d0.a;
            this.f = null;
            this.g = null;
            int i = com.android.tools.r8.androidapi.f.a;
            this.h = com.android.tools.r8.androidapi.g.b;
            this.i = C3262c.a;
            this.l = C0822Sg.b();
            this.m = true;
            this.k = z;
        }
    }

    public final void a(com.android.tools.r8.ir.optimize.info.v vVar) {
        this.l = vVar;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        C0306u0 c0306u0N0 = n0();
        c0306u0N0.getClass();
        x.a(c0306u0N0);
        AbstractC0259n1.a(x, c0306u0N0.d);
    }

    @Override // com.android.tools.r8.graph.AbstractC0217h1
    public final Object a(Function function, Function function2) {
        return function.apply(this);
    }

    public final C0346z5 a(InterfaceC0189d1 interfaceC0189d1) {
        if (!p && !E0().M0()) {
            x1f.a();
            return null;
        }
        D2 d2B = D2.b(interfaceC0189d1.a((AbstractC0287r2) getReference()));
        if (d2B != null) {
            return new C0346z5(d2B, this);
        }
        return null;
    }

    public final void a(O2 o2) {
        boolean z = p;
        if (!z && !this.g.n()) {
            x1f.a();
        } else if (!z && o2 == null) {
            x1f.a();
        } else {
            this.h = o2;
        }
    }

    public final C0210g1 a(C0333y c0333y, C0245l1 c0245l1, Consumer consumer) {
        if (getReference() == c0245l1) {
            return this;
        }
        a aVarA = a(this).a(c0245l1);
        if (c0333y.M().a().b && c0333y.o()) {
            aVarA.getClass();
        } else {
            aVarA.m = false;
        }
        return aVarA.a(consumer).a();
    }

    public static a a(C0210g1 c0210g1) {
        return new a(c0210g1.I0(), c0210g1);
    }

    public static void a(com.android.tools.r8.utils.structural.A a2) {
        a2.e(new jwg()).e(new Function() { // from class: lwg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0210g1) obj).getAccessFlags();
            }
        }).j(new Function() { // from class: nwg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0210g1) obj).h;
            }
        }).b(new Predicate() { // from class: owg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0210g1) obj).U0();
            }
        }).a(new Predicate() { // from class: pwg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((C0210g1) obj).k.a();
            }
        });
    }

    public final A5 b(InterfaceC0189d1 interfaceC0189d1) {
        return a(interfaceC0189d1);
    }
}
