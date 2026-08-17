package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C2427qS;
import com.android.tools.r8.internal.UK;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.ejh;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.l1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0245l1 extends AbstractC0287r2<C0210g1, C0245l1> {
    public static final C0245l1[] j = new C0245l1[0];
    public final I2 i;

    public C0245l1(I2 i2, I2 i3, H2 h2, boolean z) {
        super(h2, i2);
        this.i = i3;
        if (z || h2.t0()) {
            return;
        }
        throw new C0613Ke("Field name '" + h2.toString() + "' cannot be represented in dex format.");
    }

    public final String A0() {
        return this.f + "." + this.g;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.F2
    public final void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        if (m.a(this)) {
            this.f.a(c0333y, m);
            this.i.a(c0333y, m);
            H2 h2A = c0333y.w().a(this);
            h2A.getClass();
            m.a(h2A);
        }
    }

    @Override // com.android.tools.r8.graph.F2
    public final int b(F2 f2) {
        if (f2.s0()) {
            return compareTo(f2.o0());
        }
        if (f2.u0()) {
            int iA = w0().compareTo(f2.z());
            if (iA != 0) {
                return iA;
            }
            return -1;
        }
        int iA2 = w0().compareTo(f2.r0());
        if (iA2 != 0) {
            return iA2;
        }
        return 1;
    }

    @Override // com.android.tools.r8.graph.E
    public final boolean c(Object obj) {
        if (obj instanceof C0245l1) {
            C0245l1 c0245l1 = (C0245l1) obj;
            if (this.f.equals(c0245l1.f) && this.i.equals(c0245l1.i) && this.g.equals(c0245l1.g)) {
                return true;
            }
        }
        return false;
    }

    public I2 getType() {
        return this.i;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final String l0() {
        return this.f.l0() + "->" + this.g + ":" + this.i.l0();
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public String m0() {
        return this.i.m0() + " " + this.f.m0() + "." + this.g.m0();
    }

    @Override // com.android.tools.r8.graph.E
    public final int n0() {
        return (this.g.hashCode() * 31) + (this.i.hashCode() * 7) + this.f.hashCode();
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: fjh
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0245l1.a(a);
            }
        };
    }

    @Override // com.android.tools.r8.graph.F2
    public final C0245l1 o0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.F2
    public final boolean s0() {
        return true;
    }

    public final String toString() {
        return "Field " + this.i + " " + this.f + "." + this.g;
    }

    @Override // com.android.tools.r8.internal.UK
    public final int y() {
        return 3;
    }

    @Override // com.android.tools.r8.graph.AbstractC0287r2
    public final Iterable y0() {
        return Collections.singleton(this.i);
    }

    public FieldReference z0() {
        return Reference.field(Reference.classFromDescriptor(this.f.Z0()), this.g.toString(), Reference.typeFromDescriptor(this.i.Z0()));
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final int a(com.android.tools.r8.utils.structural.x xVar, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (C0245l1) xVar);
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: cjh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0245l1) obj).w0();
            }
        }).e(new Function() { // from class: djh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0245l1) obj).x0();
            }
        }).e(new ejh());
    }

    @Override // com.android.tools.r8.internal.UK
    public final int a(UK uk, AbstractC3519a abstractC3519a) {
        return abstractC3519a.a(this, (C0245l1) uk);
    }

    public final AbstractC2624sj0 a(C0333y c0333y) {
        return AbstractC2624sj0.a(getType(), C2427qS.h(), (C0333y<?>) c0333y);
    }

    public final C0346z5 a(D2 d2) {
        if (d2 != null) {
            return d2.b(this);
        }
        return null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0287r2
    public final Object a(Function function, Function function2) {
        return function.apply(this);
    }

    @Override // com.android.tools.r8.graph.F2
    public final Object a(Function function, Function function2, Function function3) {
        return function2.apply(this);
    }

    @Override // com.android.tools.r8.graph.F2
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        consumer2.accept(this);
    }

    public final boolean a(C0245l1 c0245l1) {
        return this == c0245l1;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
        oVar.getClass();
        o().a(new com.android.tools.r8.utils.structural.p(this, (com.android.tools.r8.utils.structural.q) oVar));
    }

    @Override // com.android.tools.r8.graph.X3
    public final int a(C0284q5 c0284q5) {
        return C0284q5.a(this, c0284q5.i);
    }

    @Override // com.android.tools.r8.graph.AbstractC0287r2
    public final G0 a(E0 e0) {
        C0210g1 c0210g1A;
        if (e0 == null || (c0210g1A = e0.a(this)) == null) {
            return null;
        }
        return F0.a(e0, c0210g1A);
    }

    public final C0210g1 b(E0 e0) {
        if (e0 != null) {
            return e0.a(this);
        }
        return null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0287r2
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public C0245l1 a(F2 f2, B1 b1) {
        return b1.a(f2.z(), this.i, this.g);
    }

    public final C0245l1 b(B1 b1, I2 i2) {
        return b1.a(this.f, i2, this.g);
    }
}
