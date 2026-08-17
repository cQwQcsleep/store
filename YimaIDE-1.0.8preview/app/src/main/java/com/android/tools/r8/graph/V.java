package com.android.tools.r8.graph;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.graph.B3;
import com.android.tools.r8.graph.C0191d3;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0228i5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0306u0;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.K2;
import com.android.tools.r8.graph.Q;
import com.android.tools.r8.graph.V;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.synthesis.L;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class V<C extends E0> {
    public static V<D2> c = new V<>(new U() { // from class: a3f
        @Override // com.android.tools.r8.graph.U
        public final E0 a(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, C0231j1[] c0231j1Arr, C0231j1[] c0231j1Arr2, boolean z, D2.a aVar, L l) {
            return V.a(i2, kind, origin, q, i3, k2, h2, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, c0210g1Arr, c0210g1Arr2, c0231j1Arr, c0231j1Arr2, z, aVar, l);
        }
    }, new Predicate() { // from class: e3f
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return ((E0) obj).a0();
        }
    });
    public static final V d = new V(new U() { // from class: h3f
        @Override // com.android.tools.r8.graph.U
        public final E0 a(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, C0231j1[] c0231j1Arr, C0231j1[] c0231j1Arr2, boolean z, D2.a aVar, L l) {
            return V.b(i2, kind, origin, q, i3, k2, h2, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, c0210g1Arr, c0210g1Arr2, c0231j1Arr, c0231j1Arr2, z, aVar, l);
        }
    }, new Predicate() { // from class: k3f
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return ((E0) obj).q1();
        }
    });
    public static final V e = new V(new U() { // from class: n3f
        @Override // com.android.tools.r8.graph.U
        public final E0 a(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, C0231j1[] c0231j1Arr, C0231j1[] c0231j1Arr2, boolean z, D2.a aVar, L l) {
            return V.c(i2, kind, origin, q, i3, k2, h2, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, c0210g1Arr, c0210g1Arr2, c0231j1Arr, c0231j1Arr2, z, aVar, l);
        }
    }, new Predicate() { // from class: p3f
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return ((E0) obj).b0();
        }
    });
    public final U a;
    public final Predicate b;

    public V(U u, Predicate predicate) {
        this.a = u;
        this.b = predicate;
    }

    public static /* synthetic */ I0 b(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, C0231j1[] c0231j1Arr, C0231j1[] c0231j1Arr2, boolean z, D2.a aVar, com.android.tools.r8.synthesis.L l) {
        return new I0(i2, kind, origin, q, i3, k2, h2, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, c0210g1Arr, c0210g1Arr2, H4.a.a(c0231j1Arr, c0231j1Arr2), z);
    }

    public static /* synthetic */ C0281q2 c(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, C0231j1[] c0231j1Arr, C0231j1[] c0231j1Arr2, boolean z, D2.a aVar, com.android.tools.r8.synthesis.L l) {
        return new C0281q2(i2, kind, origin, q, i3, k2, h2, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, c0210g1Arr, c0210g1Arr2, H4.a.a(c0231j1Arr, c0231j1Arr2), z);
    }

    public final E0 a(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, C0231j1[] c0231j1Arr, C0231j1[] c0231j1Arr2, boolean z, D2.a aVar) {
        return this.a.a(i2, kind, origin, q, i3, k2, h2, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, c0210g1Arr, c0210g1Arr2, c0231j1Arr, c0231j1Arr2, z, aVar, null);
    }

    public static /* synthetic */ D2 a(I2 i2, ProgramResource.Kind kind, Origin origin, Q q, I2 i3, K2 k2, H2 h2, C0228i5 c0228i5, List list, List list2, List list3, C0191d3 c0191d3, List list4, B3.b bVar, C0306u0 c0306u0, C0210g1[] c0210g1Arr, C0210g1[] c0210g1Arr2, C0231j1[] c0231j1Arr, C0231j1[] c0231j1Arr2, boolean z, D2.a aVar, com.android.tools.r8.synthesis.L l) {
        return new D2(i2, kind, origin, q, i3, k2, h2, c0228i5, list, list2, list3, c0191d3, list4, bVar, c0306u0, c0210g1Arr, c0210g1Arr2, H4.a.a(c0231j1Arr, c0231j1Arr2), z, aVar, l);
    }

    public final boolean a(E0 e0) {
        return this.b.test(e0);
    }
}
