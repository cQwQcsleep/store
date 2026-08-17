package com.android.tools.r8.graph;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.dex.code.AbstractC0138z1;
import com.android.tools.r8.graph.O0;
import com.android.tools.r8.graph.W0;
import com.android.tools.r8.internal.C0901Vh;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.ief;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class W0 extends E implements com.android.tools.r8.utils.structural.x<W0> {
    public static final /* synthetic */ boolean d = true;

    public static a a(J0 j0, B1 b1) {
        if (j0.H0() == null) {
            return null;
        }
        if (j0.H0().t0()) {
            return j0.H0().o0();
        }
        boolean z = d;
        if (!z && !j0.H0().u0()) {
            x1f.a();
            return null;
        }
        X0 x0P0 = j0.H0().p0();
        if (!z) {
            C0901Vh.a(j0, x0P0.f);
        }
        return a(1, x0P0.e, j0.j, b1);
    }

    public abstract int a(W0 w0, AbstractC3519a abstractC3519a);

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        return com.android.tools.r8.utils.structural.k.a(this, obj);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        throw new Kk0();
    }

    public a o0() {
        return null;
    }

    public X0 p0() {
        return null;
    }

    public abstract int q0();

    public abstract int r0();

    public abstract int s0();

    public boolean t0() {
        return q0() == 1;
    }

    public boolean u0() {
        return q0() == 2;
    }

    @Override // com.android.tools.r8.utils.structural.x
    /* JADX INFO: renamed from: v0, reason: merged with bridge method [inline-methods] */
    public abstract W0 R();

    public static class a extends W0 {
        public static final /* synthetic */ boolean h = true;
        public final int e;
        public final H2[] f;
        public O0[] g;

        public a(int i, H2[] h2Arr, O0[] o0Arr) {
            if (!h && i < 0) {
                x1f.a();
                throw null;
            }
            this.e = i;
            this.f = h2Arr;
            this.g = o0Arr;
        }

        @Override // com.android.tools.r8.graph.W0, com.android.tools.r8.utils.structural.x
        public final com.android.tools.r8.utils.structural.x R() {
            return this;
        }

        @Override // com.android.tools.r8.graph.W0
        public final int a(W0 w0, AbstractC3519a abstractC3519a) {
            if (h || w0.t0()) {
                return abstractC3519a.a(this, w0.o0(), new ief());
            }
            x1f.a();
            return 0;
        }

        @Override // com.android.tools.r8.graph.E
        public final int n0() {
            return (Arrays.hashCode(this.g) * 13) + (Arrays.hashCode(this.f) * 7) + this.e;
        }

        @Override // com.android.tools.r8.graph.W0
        public final a o0() {
            return this;
        }

        @Override // com.android.tools.r8.graph.W0
        public final int q0() {
            return 1;
        }

        @Override // com.android.tools.r8.graph.W0
        public final int r0() {
            return this.f.length;
        }

        @Override // com.android.tools.r8.graph.W0
        public final int s0() {
            return this.e;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("DebugInfo (line " + this.e + ") events: [\n");
            for (O0 o0 : this.g) {
                sb.append("  ");
                sb.append(o0);
                sb.append("\n");
            }
            sb.append("  END_SEQUENCE\n]\n");
            return sb.toString();
        }

        @Override // com.android.tools.r8.graph.W0
        /* JADX INFO: renamed from: v0 */
        public final W0 R() {
            return this;
        }

        public static void a(com.android.tools.r8.utils.structural.A a) {
            a.a(new ToIntFunction() { // from class: fef
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((W0.a) obj).e;
                }
            }).g(new Function() { // from class: gef
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((W0.a) obj).f;
                }
            }).f(new Function() { // from class: hef
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((W0.a) obj).g;
                }
            });
        }

        @Override // com.android.tools.r8.utils.structural.x
        public final void a(com.android.tools.r8.utils.structural.o oVar) {
            ief iefVar = new ief();
            com.android.tools.r8.utils.structural.q qVar = (com.android.tools.r8.utils.structural.q) oVar;
            qVar.getClass();
            iefVar.a(new com.android.tools.r8.utils.structural.p(this, qVar));
        }

        @Override // com.android.tools.r8.graph.W0, com.android.tools.r8.utils.structural.x
        public final int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
            W0 w0 = (W0) xVar;
            int iA = abstractC3519a.a(AbstractC0007c.b(1), AbstractC0007c.b(w0.q0()));
            return iA != 0 ? iA : a(w0, abstractC3519a);
        }

        @Override // com.android.tools.r8.graph.AbstractC0259n1
        public void a(com.android.tools.r8.dex.X x) {
            throw new Kk0();
        }
    }

    @Override // com.android.tools.r8.utils.structural.x
    public int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        W0 w0 = (W0) xVar;
        int iA = abstractC3519a.a(AbstractC0007c.b(q0()), AbstractC0007c.b(w0.q0()));
        return iA != 0 ? iA : a(w0, abstractC3519a);
    }

    public static a a(int i, int i2, AbstractC0138z1[] abstractC0138z1Arr, B1 b1) {
        ArrayList arrayList = new ArrayList(abstractC0138z1Arr.length);
        int iT = 0;
        for (AbstractC0138z1 abstractC0138z1 : abstractC0138z1Arr) {
            if (abstractC0138z1.i()) {
                U0.a(iT, iT, arrayList, b1);
                iT = 0;
            }
            iT += abstractC0138z1.t();
        }
        return new a(i, new H2[i2], (O0[]) arrayList.toArray(O0.b));
    }

    public static Z0 a(W0 w0) {
        if (w0 == null) {
            return null;
        }
        if (w0.u0()) {
            return w0.p0();
        }
        a aVarO0 = w0.o0();
        return new Y0(aVarO0.e, aVarO0.f, (O0[]) com.android.tools.r8.internal.R3.a(aVarO0.g, new Predicate() { // from class: ydf
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((O0) obj).r0();
            }
        }, O0.b));
    }
}
