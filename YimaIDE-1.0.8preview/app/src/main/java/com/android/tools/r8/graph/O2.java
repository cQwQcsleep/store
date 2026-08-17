package com.android.tools.r8.graph;

import com.android.tools.r8.dex.C0161y;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.Ak0;
import com.android.tools.r8.internal.C0497Fs;
import com.android.tools.r8.internal.C2033ln;
import com.android.tools.r8.internal.C3050xi0;
import com.android.tools.r8.internal.CJ;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.gk0;
import defpackage.sla;
import defpackage.x0g;
import java.util.Arrays;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class O2 extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<O2> {
    public static final O2[] b = new O2[0];

    public static O2 a(Object obj, C0178b4 c0178b4, I2 i2) {
        if (obj instanceof Integer) {
            return g.j(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return h.a(((Long) obj).longValue());
        }
        if (obj instanceof Float) {
            return f.a(((Float) obj).floatValue());
        }
        if (obj instanceof Double) {
            return e.a(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return new j(c0178b4.d((String) obj));
        }
        if (!(obj instanceof C3050xi0)) {
            if (obj instanceof C0497Fs) {
                return new T2(C0336y2.a((C0497Fs) obj, c0178b4, i2));
            }
            x0g.a("Unsupported bootstrap static argument of type ".concat(obj.getClass().getSimpleName()));
            return null;
        }
        C3050xi0 c3050xi0 = (C3050xi0) obj;
        int iC = c3050xi0.c();
        if (iC == 10) {
            return new k(c0178b4.e(c3050xi0.b()));
        }
        if (iC == 11) {
            return new U2(c0178b4.c(c3050xi0.b()));
        }
        sla.a("Type sort is not supported: ", c3050xi0.c());
        return null;
    }

    public S2 A0() {
        return null;
    }

    public T2 B0() {
        return null;
    }

    public U2 C0() {
        return null;
    }

    public i D0() {
        return null;
    }

    public j E0() {
        return null;
    }

    public k F0() {
        return null;
    }

    public abstract Object G0();

    public abstract R2 H0();

    public boolean I0() {
        return this instanceof M2;
    }

    public boolean J0() {
        return this instanceof a;
    }

    public boolean K0() {
        return false;
    }

    public boolean L0() {
        return false;
    }

    public boolean M0() {
        return false;
    }

    public boolean N0() {
        return false;
    }

    public boolean O0() {
        return this instanceof P2;
    }

    public boolean P0() {
        return this instanceof Q2;
    }

    public boolean Q0() {
        return false;
    }

    public com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public boolean R0() {
        return false;
    }

    public boolean S0() {
        return false;
    }

    public boolean T0() {
        return this instanceof S2;
    }

    public boolean U0() {
        return this instanceof T2;
    }

    public boolean V0() {
        return this instanceof V2;
    }

    public boolean W0() {
        return false;
    }

    public boolean X0() {
        return false;
    }

    public boolean Y0() {
        return false;
    }

    public boolean Z0() {
        return false;
    }

    public abstract I2 a(B1 b1);

    public abstract com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1);

    public abstract void a(C0161y c0161y, C0284q5 c0284q5);

    public boolean a1() {
        return false;
    }

    public abstract int b(O2 o2, AbstractC3519a abstractC3519a);

    public boolean b1() {
        return !(this instanceof j);
    }

    public abstract void c(com.android.tools.r8.utils.structural.o oVar);

    public abstract void c1();

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract Object n0();

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    public M2 o0() {
        return null;
    }

    public N2 p0() {
        return null;
    }

    public a q0() {
        return null;
    }

    public b r0() {
        return null;
    }

    public c s0() {
        return null;
    }

    public d t0() {
        return null;
    }

    public abstract String toString();

    public e u0() {
        return null;
    }

    public P2 v0() {
        return null;
    }

    public Q2 w0() {
        return null;
    }

    public f x0() {
        return null;
    }

    public g y0() {
        return null;
    }

    public h z0() {
        return null;
    }

    public static class k extends l<I2> {
        public k(I2 i2) {
            super(i2);
        }

        @Override // com.android.tools.r8.graph.O2
        public final k F0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.m;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean Z0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
            ((I2) this.c).a(c0333y, m);
        }

        @Override // com.android.tools.r8.graph.O2.l, com.android.tools.r8.graph.O2
        public final /* bridge */ /* synthetic */ boolean a1() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            I2 i2 = (I2) this.c;
            I2 i3 = (I2) o2.F0().c;
            i2.getClass();
            return abstractC3519a.a(i2, i3);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            I2 i2 = (I2) this.c;
            i2.getClass();
            oVar.a(i2);
        }

        @Override // com.android.tools.r8.graph.O2.l, com.android.tools.r8.graph.O2
        public final /* bridge */ /* synthetic */ void c1() {
        }

        public X3 d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2.l, com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            throw new Kk0();
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return Ak0.a;
        }
    }

    public static class j extends l<H2> {
        public j(H2 h2) {
            super(h2);
        }

        @Override // com.android.tools.r8.graph.O2
        public final j E0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.l;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean Y0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
            H2 h2 = (H2) this.c;
            h2.getClass();
            m.a(h2);
        }

        @Override // com.android.tools.r8.graph.O2.l, com.android.tools.r8.graph.O2
        public final /* bridge */ /* synthetic */ boolean a1() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            int iA = M2.a(this, o2, abstractC3519a);
            if (iA != 0) {
                return iA;
            }
            H2 h2 = (H2) this.c;
            H2 h3 = (H2) o2.E0().c;
            h2.getClass();
            return abstractC3519a.a(h2, h3);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((H2) this.c).a(oVar);
        }

        @Override // com.android.tools.r8.graph.O2.l, com.android.tools.r8.graph.O2
        public final /* bridge */ /* synthetic */ void c1() {
        }

        public X3 d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2.l, com.android.tools.r8.graph.O2
        public final Object n0() {
            return ((H2) this.c).toString();
        }

        @Override // com.android.tools.r8.graph.O2.l, com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.Y1;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a((H2) this.c);
        }
    }

    public static class c extends W2 {
        public static final c d = new c((byte) 0);
        public final byte c;

        public c(byte b) {
            this.c = b;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            return Byte.valueOf(d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean L0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            O2.a(R2.c, 0, c0161y);
            c0161y.a(1, this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a((int) this.c, (int) o2.s0().c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a((int) this.c);
        }

        public byte d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof c) && this.c == ((c) obj).c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return this.c * 3;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            return Integer.valueOf(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final c s0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return "Byte " + ((int) this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.x1;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a(this.c, AbstractC2624sj0.g());
        }

        public static c a(byte b) {
            return b == 0 ? d : new c(b);
        }
    }

    public static class b extends W2 {
        public static final b d = new b(true);
        public static final b e = new b(false);
        public static final b f = new b(false);
        public final boolean c;

        public b(boolean z) {
            this.c = z;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            return Boolean.valueOf(d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.t;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean K0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a(this.c ? 1L : 0L, AbstractC2624sj0.e());
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a(this.c, o2.r0().c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.c);
        }

        public boolean d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof b) && ((b) obj).c == this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return this.c ? 1234 : 4321;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            return Integer.valueOf(this.c ? 1 : 0);
        }

        @Override // com.android.tools.r8.graph.O2
        public final b r0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return this.c ? "True" : "False";
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.w1;
        }

        public static b a(boolean z) {
            return z ? d : e;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            O2.a(R2.t, this.c ? 1 : 0, c0161y);
        }
    }

    public static class a extends O2 {
        public final O2[] c;

        public a(O2[] o2Arr) {
            this.c = o2Arr;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            throw new Kk0("No boxed value for DexValueArray");
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.q;
        }

        @Override // com.android.tools.r8.graph.O2, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            O2.a(R2.q, 0, c0161y);
            CJ.b(c0161y, this.c.length);
            for (O2 o2 : this.c) {
                o2.a(c0161y, c0284q5);
            }
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            O2[] o2Arr = this.c;
            O2[] o2Arr2 = o2.q0().c;
            abstractC3519a.getClass();
            return abstractC3519a.a(Arrays.asList(o2Arr), Arrays.asList(o2Arr2));
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            O2[] o2Arr = this.c;
            oVar.getClass();
            oVar.a(Arrays.asList(o2Arr));
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c1() {
            for (O2 o2 : this.c) {
                o2.c1();
            }
        }

        public final void d(Consumer consumer) {
            for (O2 o2 : this.c) {
                consumer.accept(o2);
            }
        }

        public O2[] d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof a) {
                return Arrays.equals(((a) obj).c, this.c);
            }
            return false;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return Arrays.hashCode(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            throw new Kk0("No ASM conversion for DexValueArray");
        }

        @Override // com.android.tools.r8.graph.O2
        public final a q0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return "Array " + Arrays.toString(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
            for (O2 o2 : this.c) {
                o2.a(c0333y, m);
            }
        }

        @Override // com.android.tools.r8.graph.O2, com.android.tools.r8.utils.structural.x
        public final /* bridge */ /* synthetic */ int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
            return a((O2) xVar, abstractC3519a);
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            throw new Kk0();
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return Ak0.a;
        }
    }

    public static class e extends W2 {
        public static final e d = new e(0.0d);
        public final double c;

        public e(double d2) {
            this.c = d2;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            return Double.valueOf(d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.i;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean N0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            c0161y.d(1);
            int iA = C2033ln.a(c0161y, this.c);
            c0161y.d(-(iA + 1));
            O2.a(R2.i, iA - 1, c0161y);
            c0161y.d(iA);
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a(this.c, o2.u0().c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.c);
        }

        public double d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof e) && Double.compare(this.c, ((e) obj).c) == 0;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return (int) (this.c * 29.0d);
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            return Double.valueOf(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return "Double " + this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final e u0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.z1;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a(Double.doubleToRawLongBits(this.c), AbstractC2624sj0.i());
        }

        public static e a(double d2) {
            return Double.compare(d2, 0.0d) == 0 ? d : new e(d2);
        }
    }

    public static class f extends W2 {
        public static final f d = new f(0.0f);
        public final float c;

        public f(float f) {
            this.c = f;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            return Float.valueOf(d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.h;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean Q0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            c0161y.d(1);
            int iA = C2033ln.a(c0161y, this.c);
            c0161y.d(-(iA + 1));
            O2.a(R2.h, iA - 1, c0161y);
            c0161y.d(iA);
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a(this.c, o2.x0().c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.c);
        }

        public float d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof f) && Float.compare(this.c, ((f) obj).c) == 0;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return (int) (this.c * 19.0f);
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            return Float.valueOf(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return "Float " + this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final f x0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.A1;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a(Float.floatToIntBits(this.c), AbstractC2624sj0.j());
        }

        public static f a(float f) {
            return Float.compare(f, 0.0f) == 0 ? d : new f(f);
        }
    }

    public static class d extends W2 {
        public static final d d = new d(0);
        public final char c;

        public d(char c) {
            this.c = c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            return Character.valueOf(d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.e;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean M0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            c0161y.d(1);
            int iB = c0161y.b(2, this.c);
            c0161y.d(-(iB + 1));
            O2.a(R2.e, iB - 1, c0161y);
            c0161y.d(iB);
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a((int) this.c, (int) o2.t0().c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a((int) this.c);
        }

        public char d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof d) && this.c == ((d) obj).c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return this.c * 5;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            return Integer.valueOf(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final d t0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return "Char " + this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.y1;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a(this.c, AbstractC2624sj0.h());
        }

        public static d a(char c) {
            return c == 0 ? d : new d(c);
        }
    }

    public static class g extends W2 {
        public static final g d = new g(0);
        public final int c;

        public g(int i) {
            this.c = i;
        }

        public static g j(int i) {
            g gVar = d;
            return i == gVar.c ? gVar : new g(i);
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            return Integer.valueOf(d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.f;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean R0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            R2 r2 = R2.f;
            long j = this.c;
            c0161y.d(1);
            int iA = c0161y.a(4, j);
            c0161y.d(-(iA + 1));
            O2.a(r2, iA - 1, c0161y);
            c0161y.d(iA);
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a(this.c, o2.y0().c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.c);
        }

        public int d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof g) && this.c == ((g) obj).c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return this.c * 11;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            return Integer.valueOf(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return "Int " + this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final g y0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a(this.c, AbstractC2624sj0.k());
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.B1;
        }
    }

    public static class h extends W2 {
        public static final h d = new h(0);
        public final long c;

        public h(long j) {
            this.c = j;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            return Long.valueOf(d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.g;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean S0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            R2 r2 = R2.g;
            long j = this.c;
            c0161y.d(1);
            int iA = c0161y.a(8, j);
            c0161y.d(-(iA + 1));
            O2.a(r2, iA - 1, c0161y);
            c0161y.d(iA);
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a(this.c, o2.z0().c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a(this.c);
        }

        public long d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof h) && this.c == ((h) obj).c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return ((int) this.c) * 13;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            return Long.valueOf(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return "Long " + this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final h z0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.C1;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a(this.c, AbstractC2624sj0.l());
        }

        public static h a(long j) {
            return j == 0 ? d : new h(j);
        }
    }

    public static class i extends W2 {
        public static final i d = new i(0);
        public final short c;

        public i(short s) {
            this.c = s;
        }

        @Override // com.android.tools.r8.graph.O2
        public final i D0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object G0() {
            return Short.valueOf(d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final R2 H0() {
            return R2.d;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean X0() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public final void a(C0161y c0161y, C0284q5 c0284q5) {
            R2 r2 = R2.d;
            long j = this.c;
            c0161y.d(1);
            int iA = c0161y.a(2, j);
            c0161y.d(-(iA + 1));
            O2.a(r2, iA - 1, c0161y);
            c0161y.d(iA);
        }

        @Override // com.android.tools.r8.graph.O2
        public final int b(O2 o2, AbstractC3519a abstractC3519a) {
            return abstractC3519a.a((int) this.c, (int) o2.D0().d1());
        }

        @Override // com.android.tools.r8.graph.O2
        public final void c(com.android.tools.r8.utils.structural.o oVar) {
            ((com.android.tools.r8.utils.structural.q) oVar).a.a((int) this.c);
        }

        public short d1() {
            return this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof i) && this.c == ((i) obj).c;
        }

        @Override // com.android.tools.r8.graph.O2
        public final int hashCode() {
            return this.c * 7;
        }

        @Override // com.android.tools.r8.graph.O2
        public final Object n0() {
            return Integer.valueOf(this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final String toString() {
            return "Short " + ((int) this.c);
        }

        @Override // com.android.tools.r8.graph.O2
        public final I2 a(B1 b1) {
            return b1.D1;
        }

        @Override // com.android.tools.r8.graph.O2
        public final com.android.tools.r8.internal.B1 a(com.android.tools.r8.internal.C1 c1) {
            return c1.a(this.c, AbstractC2624sj0.n());
        }

        public static i a(short s) {
            return s == 0 ? d : new i(s);
        }
    }

    public static abstract class l<T extends X3> extends O2 {
        public final T c;

        /* JADX WARN: Multi-variable type inference failed */
        public l(X3 x3) {
            this.c = x3;
        }

        @Override // com.android.tools.r8.graph.O2
        public Object G0() {
            throw new Kk0("No boxed value for DexValue ".concat(getClass().getSimpleName()));
        }

        @Override // com.android.tools.r8.graph.O2, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.graph.O2
        public void a(C0161y c0161y, C0284q5 c0284q5) {
            int iA = this.c.a(c0284q5);
            c0161y.d(1);
            int iB = c0161y.b(4, iA);
            c0161y.d(-(iB + 1));
            O2.a(H0(), iB - 1, c0161y);
            c0161y.d(iB);
        }

        @Override // com.android.tools.r8.graph.O2
        public boolean a1() {
            return true;
        }

        @Override // com.android.tools.r8.graph.O2
        public void c1() {
        }

        @Override // com.android.tools.r8.graph.O2
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof l) {
                l lVar = (l) obj;
                if (lVar.H0() == H0() && lVar.c.equals(this.c)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.android.tools.r8.graph.O2
        public int hashCode() {
            return (this.c.hashCode() * 7) + H0().b;
        }

        @Override // com.android.tools.r8.graph.O2
        public Object n0() {
            throw new Kk0("No ASM conversion for DexValue ".concat(getClass().getSimpleName()));
        }

        @Override // com.android.tools.r8.graph.O2
        public String toString() {
            return "Item " + H0() + " " + this.c;
        }

        @Override // com.android.tools.r8.graph.O2
        public I2 a(B1 b1) {
            throw new Kk0();
        }

        @Override // com.android.tools.r8.graph.O2, com.android.tools.r8.utils.structural.x
        public final /* bridge */ /* synthetic */ int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
            return a((O2) xVar, abstractC3519a);
        }
    }

    public void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
    }

    public static void a(R2 r2, int i2, C0161y c0161y) {
        c0161y.a((byte) (r2.b | (i2 << 5)));
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(O2 o2, AbstractC3519a abstractC3519a) {
        if (H0() != o2.H0()) {
            return abstractC3519a.a((int) H0().b, (int) o2.H0().b);
        }
        return b(o2, abstractC3519a);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        ((com.android.tools.r8.utils.structural.q) oVar).a.a((int) H0().b);
        c(oVar);
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        throw new Kk0();
    }

    public static W2 a(I2 i2) {
        char cA1 = i2.a1();
        if (cA1 == 'F') {
            return f.d;
        }
        if (cA1 == 'L') {
            return V2.c;
        }
        if (cA1 == 'S') {
            return i.d;
        }
        if (cA1 == 'Z') {
            return b.f;
        }
        if (cA1 == 'I') {
            return g.d;
        }
        if (cA1 != 'J') {
            switch (cA1) {
                case 'B':
                    return c.d;
                case 'C':
                    return d.d;
                case 'D':
                    return e.d;
                default:
                    gk0.a("No default value for unexpected type ", i2);
                    return null;
            }
        }
        return h.d;
    }
}
