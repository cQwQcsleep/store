package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.UK;
import com.android.tools.r8.internal.Y6;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.nni;
import defpackage.pni;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.w2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0322w2 extends AbstractC0287r2<C0231j1, C0322w2> {
    public final E2 i;

    public C0322w2(I2 i2, E2 e2, H2 h2, boolean z) {
        super(h2, i2);
        this.i = e2;
        if (z || h2.u0()) {
            return;
        }
        nni.a("Method name '", h2, "' in class '", i2.m0(), "' cannot be represented in dex format.");
        throw null;
    }

    public int A0() {
        return this.i.f.size();
    }

    public K2 B0() {
        return this.i.f;
    }

    public E2 C0() {
        return this.i;
    }

    public I2 D0() {
        return this.i.e;
    }

    public String E0() {
        return this.f + "." + this.g;
    }

    public String F0() {
        return a(false, true);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final String a(boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder();
        if (z2) {
            sb.append(D0().m0());
            sb.append(" ");
        }
        if (z) {
            sb.append(this.f.m0());
            sb.append(".");
        }
        sb.append(this.g);
        sb.append("(");
        for (int i = 0; i < A0(); i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(this.i.f.b[i].m0());
        }
        sb.append(")");
        return sb.toString();
    }

    public final boolean b(C0333y c0333y, com.android.tools.r8.dex.M m) {
        if (!m.a(this)) {
            return false;
        }
        this.f.a(c0333y, m);
        E2 e2 = this.i;
        e2.getClass();
        if (!m.a(e2)) {
            return true;
        }
        e2.e.a(c0333y, m);
        e2.f.a(c0333y, m);
        return true;
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        if (obj instanceof C0322w2) {
            C0322w2 c0322w2 = (C0322w2) obj;
            if (this.f.equals(c0322w2.f) && this.g.equals(c0322w2.g) && this.i.equals(c0322w2.i)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final C0231j1 c(E0 e0) {
        if (e0 != null) {
            return (C0231j1) (s0() ? e0.a((C0245l1) null) : e0.c(q0()));
        }
        return null;
    }

    public I2 k(int i) {
        return this.i.f.b[i];
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public String l0() {
        return this.f.l0() + "->" + this.g + this.i.l0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public String m0() {
        return a(true, true);
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        return (this.g.hashCode() * 31) + (this.i.hashCode() * 29) + (this.f.hashCode() * 7);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: rni
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0322w2.a(a);
            }
        };
    }

    @Override // com.android.tools.r8.graph.F2
    public final C0322w2 q0() {
        return this;
    }

    public String toString() {
        return m0();
    }

    @Override // com.android.tools.r8.graph.F2
    public final boolean u0() {
        return true;
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 4;
    }

    @Override // com.android.tools.r8.graph.AbstractC0287r2
    public Iterable<I2> y0() {
        E2 e2 = this.i;
        return AbstractC0728Oq.a(Collections.singleton(e2.e), e2.f);
    }

    public MethodReference z0() {
        ArrayList arrayList = new ArrayList();
        for (I2 i2 : this.i.f.b) {
            arrayList.add(Reference.typeFromDescriptor(i2.Z0()));
        }
        String strZ0 = this.i.e.Z0();
        return Reference.method(Reference.classFromDescriptor(this.f.Z0()), this.g.toString(), arrayList, strZ0.equals("V") ? null : Reference.typeFromDescriptor(strZ0));
    }

    public final boolean d(C0322w2 c0322w2) {
        return a(c0322w2) || a(c0322w2.C0(), c0322w2.x0());
    }

    @Override // com.android.tools.r8.graph.F2
    public final int b(F2 f2) {
        if (f2.u0()) {
            return compareTo(f2.q0());
        }
        int iA = w0().compareTo(f2.z());
        if (iA != 0) {
            return iA;
        }
        return 1;
    }

    @Override // com.android.tools.r8.graph.AbstractC0287r2
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final H0 a(E0 e0) {
        if (e0 != null) {
            return e0.a(this);
        }
        return null;
    }

    public final boolean b(C0322w2 c0322w2) {
        return !a(c0322w2);
    }

    public final boolean b(B1 b1) {
        b1.getClass();
        return this.g == b1.c1;
    }

    @Override // com.android.tools.r8.graph.AbstractC0287r2
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public C0322w2 a(F2 f2, B1 b1) {
        return b1.a(f2.z(), this.i, this.g);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (C0322w2) xVar);
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: oni
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0322w2) obj).w0();
            }
        }).e(new pni()).e(new Function() { // from class: qni
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0322w2) obj).i;
            }
        });
    }

    public final int a(C0322w2 c0322w2, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, c0322w2);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        oVar.getClass();
        o().a(new com.android.tools.r8.utils.structural.p(this, (com.android.tools.r8.utils.structural.q) oVar));
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return a((C0322w2) uk, abstractC3519a);
    }

    public final I2 a(int i, boolean z) {
        if (z) {
            return k(i);
        }
        if (i == 0) {
            return w0();
        }
        return k(i - 1);
    }

    public final int a(boolean z) {
        return Y6.a(!z) + A0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0287r2
    public final Object a(Function function, Function function2) {
        return function2.apply(this);
    }

    @Override // com.android.tools.r8.graph.F2
    public final Object a(Function function, Function function2, Function function3) {
        return function3.apply(this);
    }

    @Override // com.android.tools.r8.graph.F2
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        consumer3.accept(this);
    }

    public final B5 a(D2 d2) {
        if (d2 != null) {
            return d2.f(this);
        }
        return null;
    }

    @Override // com.android.tools.r8.graph.F2
    public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        if (b(c0333y, m)) {
            H2 h2A = c0333y.w().a(this);
            h2A.getClass();
            m.a(h2A);
        }
    }

    public final boolean a(E2 e2, H2 h2) {
        E2 e3 = this.i;
        e3.getClass();
        return E2.a(e3, e2) && this.g.b(h2);
    }

    public boolean a(C0231j1 c0231j1) {
        return d(c0231j1.getReference());
    }

    public final boolean a(C0322w2 c0322w2) {
        return this == c0322w2;
    }

    public final C0322w2 a(AbstractC0175b1 abstractC0175b1, B1 b1) {
        return a(abstractC0175b1.z(), b1);
    }

    public final C0322w2 a(B1 b1, H2 h2) {
        return b1.a(this.f, this.i, h2);
    }

    @Override // com.android.tools.r8.graph.X3
    public final int a(C0284q5 c0284q5) {
        return C0284q5.a(this, c0284q5.h);
    }

    public final C0322w2 a(E2 e2, B1 b1) {
        return b1.a(this.f, e2, this.g);
    }
}
