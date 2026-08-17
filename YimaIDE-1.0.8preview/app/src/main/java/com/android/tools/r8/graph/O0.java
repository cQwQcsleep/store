package com.android.tools.r8.graph;

import com.android.tools.r8.dex.C0157u;
import com.android.tools.r8.graph.O0;
import com.android.tools.r8.internal.AbstractC3148ys;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.hla;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class O0 extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<O0> {
    public static final O0[] b = new O0[0];
    public static final /* synthetic */ boolean c = true;

    public com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(O0 o0, AbstractC3519a abstractC3519a) {
        int iA = abstractC3519a.a(q0(), o0.q0());
        return iA != 0 ? iA : b(o0, abstractC3519a);
    }

    public abstract void a(V0 v0);

    public abstract int b(O0 o0, AbstractC3519a abstractC3519a);

    public abstract void c(com.android.tools.r8.utils.structural.o oVar);

    public final boolean equals(Object obj) {
        return (obj instanceof O0) && compareTo((O0) obj) == 0;
    }

    public N0 n0() {
        return null;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    public b o0() {
        return null;
    }

    public S0 p0() {
        return null;
    }

    public abstract int q0();

    public boolean r0() {
        return this instanceof N0;
    }

    public static class b extends O0 {
        public static final /* synthetic */ boolean e = true;
        public final int d;

        public b(int i) {
            if (e || (i >= 10 && i <= 255)) {
                this.d = i;
            } else {
                x1f.a();
                throw null;
            }
        }

        @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
        public final /* bridge */ /* synthetic */ int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
            return a((O0) xVar, abstractC3519a);
        }

        @Override // com.android.tools.r8.graph.O0
        public final int b(O0 o0, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a(this.d, ((b) o0).d);
        }

        @Override // com.android.tools.r8.graph.O0
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.d);
        }

        public final int hashCode() {
            return (this.d * 7) + 10;
        }

        @Override // com.android.tools.r8.graph.O0
        public final b o0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O0
        public final int q0() {
            return 10;
        }

        @Override // com.android.tools.r8.graph.O0
        public final boolean r0() {
            return true;
        }

        public int s0() {
            return ((this.d - 10) % 15) - 4;
        }

        public int t0() {
            return (this.d - 10) / 15;
        }

        public final String toString() {
            return String.format("DEFAULT %d (dpc %d, dline %d)", Integer.valueOf(this.d), Integer.valueOf(t0()), Integer.valueOf(s0()));
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(C0157u c0157u, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
            c0157u.b(this.d);
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(V0 v0) {
            v0.a(this);
        }
    }

    public static class c extends O0 {
        public final H2 d;

        public c(H2 h2) {
            this.d = h2;
        }

        @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m) {
            H2 h2 = this.d;
            h2.getClass();
            m.a(h2);
        }

        @Override // com.android.tools.r8.graph.O0
        public final int b(O0 o0, AbstractC3519a abstractC3519a) {
            H2 h2 = this.d;
            H2 h3 = ((c) o0).d;
            h2.getClass();
            return abstractC3519a.a(h2, h3);
        }

        @Override // com.android.tools.r8.graph.O0
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            this.d.a(oVar);
        }

        public final int hashCode() {
            return (this.d.hashCode() * 7) + 9;
        }

        @Override // com.android.tools.r8.graph.O0
        public final int q0() {
            return 9;
        }

        @Override // com.android.tools.r8.graph.O0
        public final boolean r0() {
            return false;
        }

        public final String toString() {
            return "SET_FILE " + this.d.toString();
        }

        @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
        public final /* bridge */ /* synthetic */ int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
            return a((O0) xVar, abstractC3519a);
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(V0 v0) {
            v0.a(this);
        }
    }

    public static class d extends O0 {
        public final int d;
        public final H2 e;
        public final I2 f;
        public final H2 g;

        public d(int i, H2 h2, I2 i2, H2 h3) {
            this.d = i;
            this.e = h2;
            this.f = i2;
            this.g = h3;
        }

        @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(C0157u c0157u, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
            c0157u.b(this.g == null ? 3 : 4);
            c0157u.c(this.d);
            H2 h2 = this.e;
            if (h2 == null) {
                c0157u.c(0);
            } else {
                c0157u.c(C0284q5.a(h2, c0157u.a.j) + 1);
            }
            I2 i2C = abstractC3148ys.c(abstractC3148ys2, this.f);
            if (i2C == null) {
                c0157u.c(0);
            } else {
                c0157u.c(C0284q5.a(i2C, c0157u.a.g) + 1);
            }
            H2 h3 = this.g;
            if (h3 != null) {
                c0157u.c(C0284q5.a(h3, c0157u.a.j) + 1);
            }
        }

        @Override // com.android.tools.r8.graph.O0
        public final int b(O0 o0, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a(this, (d) o0, new hla());
        }

        @Override // com.android.tools.r8.graph.O0
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            hla hlaVar = new hla();
            com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
            qVar.getClass();
            hlaVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
        }

        public final int hashCode() {
            return (Objects.hashCode(this.g) * 19) + (Objects.hashCode(this.f) * 17) + (Objects.hashCode(this.e) * 13) + (this.d * 7) + 3;
        }

        @Override // com.android.tools.r8.graph.O0
        public final int q0() {
            return 3;
        }

        @Override // com.android.tools.r8.graph.O0
        public final boolean r0() {
            return true;
        }

        public final String toString() {
            return "START_LOCAL " + this.d;
        }

        public static void a(com.android.tools.r8.utils.structural.A a) {
            a.a(new ToIntFunction() { // from class: ila
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((O0.d) obj).d;
                }
            }).e(new Function() { // from class: jla
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((O0.d) obj).e;
                }
            }).e(new Function() { // from class: kla
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((O0.d) obj).f;
                }
            }).j(new Function() { // from class: lla
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((O0.d) obj).g;
                }
            });
        }

        @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
        public final /* bridge */ /* synthetic */ int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
            return a((O0) xVar, abstractC3519a);
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m) {
            H2 h2 = this.e;
            if (h2 != null) {
                m.a(h2);
            }
            if (this.f != null) {
                c0333y.A().c(abstractC3148ys, this.f).a(c0333y, m);
            }
            H2 h3 = this.g;
            if (h3 != null) {
                m.a(h3);
            }
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(V0 v0) {
            v0.a(this);
        }
    }

    public static class a extends O0 {
        public static final /* synthetic */ boolean e = true;
        public final int d;

        public a(int i) {
            this.d = i;
        }

        @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(V0 v0) {
            if (e || this.d >= 0) {
                v0.a(this);
            } else {
                x1f.a();
            }
        }

        @Override // com.android.tools.r8.graph.O0
        public final int b(O0 o0, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a(this.d, ((a) o0).d);
        }

        @Override // com.android.tools.r8.graph.O0
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.d);
        }

        public final int hashCode() {
            return (this.d * 7) + 1;
        }

        @Override // com.android.tools.r8.graph.O0
        public final int q0() {
            return 1;
        }

        @Override // com.android.tools.r8.graph.O0
        public final boolean r0() {
            return true;
        }

        public final String toString() {
            return "ADVANCE_PC " + this.d;
        }

        @Override // com.android.tools.r8.graph.O0
        public final void a(C0157u c0157u, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
            c0157u.b(1);
            c0157u.c(this.d);
        }

        @Override // com.android.tools.r8.graph.O0, com.android.tools.r8.utils.structural.x
        public final /* bridge */ /* synthetic */ int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
            return a((O0) xVar, abstractC3519a);
        }
    }

    public void a(C0333y c0333y, AbstractC3148ys abstractC3148ys, com.android.tools.r8.dex.M m) {
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a(q0());
        c(oVar);
    }

    public void a(C0157u c0157u, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        throw new Kk0();
    }
}
