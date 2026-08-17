package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0196e1;
import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.graph.C0299t0;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.O2;
import com.android.tools.r8.utils.StringDiagnostic;
import java.util.IdentityHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class r {
    public static final /* synthetic */ boolean a = true;

    public static boolean a(C0333y c0333y, E0 e0, Supplier supplier) {
        C0285r0 c0285r0A;
        B1 b1A = c0333y.a();
        C3289g c3289g = b1A.J4;
        if (p0.a(e0, c3289g) == 3 || (c0285r0A = e0.n0().a(b1A.w3)) == null) {
            return false;
        }
        C0196e1 c0196e1 = c0285r0A.c;
        IdentityHashMap identityHashMap = new IdentityHashMap();
        for (C0299t0 c0299t0 : c0196e1.c) {
            identityHashMap.put(c0299t0.b, c0299t0);
        }
        try {
            C0299t0 c0299t1 = (C0299t0) identityHashMap.get(c3289g.d.a);
            if (c0299t1 == null) {
                throw new C3299q("element 'k' is missing.");
            }
            if (((Integer) c0299t1.c.G0()).intValue() == 3) {
                com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r rVarA = a(c3289g, identityHashMap);
                if (rVarA instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.t) {
                    return ((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.t) rVarA).c();
                }
            }
            if (!a) {
                boolean z = a(c3289g, identityHashMap) instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.t;
                C0299t0 c0299t2 = (C0299t0) identityHashMap.get(c3289g.d.a);
                if (c0299t2 == null) {
                    throw new C3299q("element 'k' is missing.");
                }
                if (z != (((Integer) c0299t2.c.G0()).intValue() == 3)) {
                    throw new AssertionError("Synthetic class kinds should agree");
                }
            }
            return false;
        } catch (V e) {
            if (((Boolean) supplier.get()).booleanValue()) {
                c0333y.O().warning(S.a());
            }
            c0333y.O().info(new StringDiagnostic("Class " + e0.e.m0() + " has malformed kotlin.Metadata: " + e.getMessage()));
            return false;
        }
    }

    public static String[] b(O2 o2, String str) {
        if (!o2.J0()) {
            throw new C3299q("invalid '" + str + "' value: " + o2.m0());
        }
        O2[] o2ArrD1 = o2.q0().d1();
        String[] strArr = new String[o2ArrD1.length];
        for (int i = 0; i < o2ArrD1.length; i++) {
            strArr[i] = a(o2ArrD1[i], str + "[" + i + "]");
        }
        return strArr;
    }

    public static InterfaceC3298p a(C0333y c0333y, E0 e0, Consumer consumer, C0285r0 c0285r0) throws V {
        C3289g c3289g = c0333y.a().J4;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r rVarA = a(c3289g, c0285r0.c);
        if (rVarA != null) {
            return a(c3289g, e0, rVarA, c0333y, consumer);
        }
        throw new V(new Exception("Could not parse metadata for " + e0.m0()));
    }

    public static InterfaceC3298p a(C0333y c0333y, E0 e0, C0285r0 c0285r0, Consumer consumer, Supplier supplier) {
        try {
            return a(c0333y, e0, consumer, c0285r0);
        } catch (V e) {
            if (((Boolean) supplier.get()).booleanValue()) {
                c0333y.O().warning(S.a());
            }
            c0333y.O().info(new StringDiagnostic("Class " + e0.e.m0() + " has malformed kotlin.Metadata: " + e.getMessage()));
            return d0.b;
        } catch (Throwable th) {
            if (((Boolean) supplier.get()).booleanValue()) {
                c0333y.O().warning(S.a());
            }
            c0333y.O().info(new StringDiagnostic("Unexpected error while reading " + e0.e.m0() + "'s kotlin.Metadata: " + th.getMessage()));
            return d0.a;
        }
    }

    public static boolean a(E0 e0, T t) {
        return e0.n0().a(t.d.a.a().w3) != null;
    }

    public static com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r a(C3289g c3289g, C0196e1 c0196e1) throws V {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        for (C0299t0 c0299t0 : c0196e1.c) {
            identityHashMap.put(c0299t0.b, c0299t0);
        }
        return a(c3289g, identityHashMap);
    }

    public static com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r a(C3289g c3289g, IdentityHashMap identityHashMap) throws V {
        C0299t0 c0299t0 = (C0299t0) identityHashMap.get(c3289g.d.a);
        if (c0299t0 != null) {
            Integer num = (Integer) c0299t0.c.G0();
            num.getClass();
            C0299t0 c0299t1 = (C0299t0) identityHashMap.get(c3289g.d.b);
            int[] iArrA = c0299t1 == null ? null : a(c0299t1.c);
            C0299t0 c0299t2 = (C0299t0) identityHashMap.get(c3289g.d.c);
            String[] strArrB = c0299t2 == null ? null : b(c0299t2.c, "d1");
            C0299t0 c0299t3 = (C0299t0) identityHashMap.get(c3289g.d.d);
            String[] strArrB2 = c0299t3 == null ? null : b(c0299t3.c, "d2");
            C0299t0 c0299t4 = (C0299t0) identityHashMap.get(c3289g.d.e);
            String strA = c0299t4 == null ? null : a(c0299t4.c, "xs");
            C0299t0 c0299t5 = (C0299t0) identityHashMap.get(c3289g.d.f);
            String strA2 = c0299t5 == null ? null : a(c0299t5.c, "pn");
            C0299t0 c0299t6 = (C0299t0) identityHashMap.get(c3289g.d.g);
            try {
                return com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.a(new Q(num, iArrA, strArrB, strArrB2, strA, strA2, c0299t6 != null ? (Integer) c0299t6.c.G0() : null));
            } catch (C3299q | ClassCastException | IllegalArgumentException e) {
                throw new V(e);
            }
        }
        throw new C3299q("element 'k' is missing.");
    }

    public static InterfaceC3298p a(C3289g c3289g, E0 e0, com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r rVar, C0333y c0333y, Consumer consumer) {
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.h hVarB;
        com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.j jVarA = rVar.a();
        if (jVarA.b == 1 && jVarA.c < 4) {
            rVar.a(L.a);
            hVarB = rVar.b();
            rVar.a(jVarA);
        } else {
            hVarB = rVar.b();
        }
        String strPn = hVarB.pn();
        int[] iArrA = L.a(rVar.a());
        if (rVar instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.a) {
            return C3297o.a((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.a) rVar, strPn, iArrA, e0, c0333y, consumer);
        }
        if (rVar instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.b) {
            return H.a((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.b) rVar, strPn, iArrA, e0, c0333y, consumer);
        }
        if (rVar instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.c) {
            return g0.a((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.c) rVar, strPn, iArrA, c0333y.a());
        }
        if (rVar instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.d) {
            return h0.a((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.r.d) rVar, strPn, iArrA, e0, c0333y, consumer);
        }
        if (rVar instanceof com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.t) {
            return p0.a((com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.t) rVar, strPn, iArrA, e0, c3289g, c0333y);
        }
        throw new C3299q("unsupported 'k' value: " + hVarB.k());
    }

    public static int[] a(O2 o2) {
        if (o2.J0()) {
            O2[] o2ArrD1 = o2.q0().d1();
            int[] iArr = new int[o2ArrD1.length];
            for (int i = 0; i < o2ArrD1.length; i++) {
                iArr[i] = ((Integer) o2ArrD1[i].G0()).intValue();
            }
            return iArr;
        }
        throw new C3299q("invalid 'mv' value: " + o2.m0());
    }

    public static String a(O2 o2, String str) {
        if (o2.Y0()) {
            return ((H2) o2.E0().d1()).toString();
        }
        throw new C3299q("invalid '" + str + "' value: " + o2.m0());
    }
}
