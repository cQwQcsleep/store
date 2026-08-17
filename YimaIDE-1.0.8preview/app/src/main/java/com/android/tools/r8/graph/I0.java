package com.android.tools.r8.graph;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.I0;
import com.android.tools.r8.internal.MX;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.structural.A;
import defpackage.md6;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I0 extends E0 implements InterfaceC0181c0, InterfaceC0202f0, C5, Supplier, com.android.tools.r8.utils.structural.x {
    public static final /* synthetic */ boolean u = true;

    public I0(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, H4.a aVar, boolean z) {
        super(h2, k2, q, i3, i2, c0210g1Arr, c0210g1Arr2, aVar, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, origin, z);
        if (u || kind == ProgramResource.Kind.CF) {
            return;
        }
        md6.a("Invalid kind ", kind, " for class-path class ", i2);
        throw null;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: td6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).getType();
            }
        }).e(new Function() { // from class: vd6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).d1();
            }
        }).e(new Function() { // from class: wd6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).U0();
            }
        }).e(new Function() { // from class: xd6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).getAccessFlags();
            }
        }).j(new Function() { // from class: yd6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).c1();
            }
        }).j(new Function() { // from class: zd6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).X0();
            }
        }).h(new Function() { // from class: nd6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).Y0();
            }
        }).e(new Function() { // from class: od6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).n0();
            }
        }).a(new Predicate() { // from class: pd6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return I0.a((I0) obj);
            }
        }).h(new Function() { // from class: qd6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).B0();
            }
        }).h(new Function() { // from class: ud6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I0) obj).D0();
            }
        });
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final InterfaceC0202f0 K() {
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.E0
    public final V V0() {
        return V.d;
    }

    public final /* synthetic */ void b(Consumer consumer, C0231j1 c0231j1) {
        consumer.accept(new C0195e0(this, c0231j1));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this;
    }

    public final void j(final Consumer consumer) {
        this.l.b(new Consumer() { // from class: rd6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b(consumer, (C0231j1) obj);
            }
        }, MX.b);
    }

    @Override // com.android.tools.r8.graph.E0, com.android.tools.r8.graph.S
    public final I0 m() {
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: sd6
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                I0.a(a);
            }
        };
    }

    public final String toString() {
        return this.e.toString() + "(classpath class)";
    }

    @Override // com.android.tools.r8.graph.E0
    public final boolean y1() {
        return true;
    }

    @Override // com.android.tools.r8.graph.E0
    public final boolean a(C0333y c0333y, E0 e0, Predicate predicate, Set set) {
        if (!set.add(getType()) || predicate.test(getType())) {
            return false;
        }
        if (!isInterface()) {
            return true;
        }
        c0333y.M().getClass();
        return false;
    }

    @Override // com.android.tools.r8.graph.E0
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        consumer2.accept(this);
    }

    public static /* synthetic */ boolean a(I0 i0) {
        return i0.s == B3.b.f();
    }
}
