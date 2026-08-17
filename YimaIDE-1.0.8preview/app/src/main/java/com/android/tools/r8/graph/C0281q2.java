package com.android.tools.r8.graph;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0281q2;
import com.android.tools.r8.internal.De0;
import com.android.tools.r8.origin.Origin;
import defpackage.md6;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.graph.q2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0281q2 extends E0 implements InterfaceC0289r4, Supplier<C0281q2> {
    public static final /* synthetic */ boolean u = true;

    public C0281q2(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, H4.a aVar, boolean z) {
        super(h2, k2, q, i3, i2, c0210g1Arr, c0210g1Arr2, aVar, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, origin, z);
        boolean z2 = u;
        if (!z2 && !De0.a(C1()).allMatch(new Predicate() { // from class: k2i
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0281q2.e((C0231j1) obj);
            }
        })) {
            x1f.a();
            throw null;
        }
        if (!z2 && !Arrays.stream(c0210g1Arr).allMatch(new Predicate() { // from class: l2i
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0281q2.d((C0210g1) obj);
            }
        })) {
            x1f.a();
            throw null;
        }
        if (!z2 && !Arrays.stream(c0210g1Arr2).allMatch(new Predicate() { // from class: l2i
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C0281q2.d((C0210g1) obj);
            }
        })) {
            x1f.a();
            throw null;
        }
        for (C0210g1 c0210g1 : c0210g1Arr) {
            c0210g1.L0();
        }
        if (u || kind == ProgramResource.Kind.CF) {
            return;
        }
        md6.a("Invalid kind ", kind, " for library-path class ", i2);
        throw null;
    }

    public static boolean d(C0210g1 c0210g1) {
        boolean z = u;
        if (!z && c0210g1.J0()) {
            x1f.a();
            return false;
        }
        if (z || !c0210g1.z0() || !c0210g1.S0()) {
            return true;
        }
        x1f.a();
        return false;
    }

    public static boolean e(C0231j1 c0231j1) {
        boolean z = u;
        if (!z && c0231j1.m1()) {
            x1f.a();
            return false;
        }
        if (!z) {
            c0231j1.O0();
            if (c0231j1.g.i()) {
                x1f.a();
                return false;
            }
        }
        if (z || !c0231j1.i1()) {
            return true;
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.graph.InterfaceC0265o0
    public final InterfaceC0202f0 K() {
        return this;
    }

    @Override // com.android.tools.r8.graph.E0
    public final V V0() {
        return V.e;
    }

    @Override // com.android.tools.r8.graph.E0, com.android.tools.r8.graph.S
    public final C0281q2 Z() {
        return this;
    }

    @Override // com.android.tools.r8.graph.E0
    public final boolean a(C0333y c0333y, E0 e0, Predicate predicate, Set set) {
        if (set.add(getType()) && !predicate.test(getType())) {
            if (isInterface()) {
                return c0333y.M().w;
            }
            if (!c0333y.a().c6.contains(this.e)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.graph.E0, com.android.tools.r8.graph.S
    public final boolean b0() {
        return true;
    }

    @Override // java.util.function.Supplier
    public final C0281q2 get() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final String m0() {
        return this.e.m0() + "(library class)";
    }

    public final String toString() {
        return this.e.toString() + "(library class)";
    }

    @Override // com.android.tools.r8.graph.E0
    public final boolean y1() {
        return true;
    }

    @Override // com.android.tools.r8.graph.E0
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        consumer3.accept(this);
    }
}
