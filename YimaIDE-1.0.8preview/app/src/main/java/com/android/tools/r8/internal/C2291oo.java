package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.Iterator;
import java.util.function.BiFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.oo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2291oo extends AbstractC1808j9 {
    public static final /* synthetic */ boolean d = true;
    public final String c;

    public C2291oo(String str) {
        if (d || str != null) {
            this.c = str;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static String a(int i, InterfaceC1101ar interfaceC1101ar) {
        if (!interfaceC1101ar.a()) {
            if (!interfaceC1101ar.m()) {
                if (!d && !interfaceC1101ar.l() && !interfaceC1101ar.f()) {
                    x1f.a();
                    return null;
                }
                if (i == 1) {
                    return "top";
                }
                return interfaceC1101ar.l() ? "a single width value" : "a double width value";
            }
            if (!interfaceC1101ar.t()) {
                return "uninitialized-this";
            }
            com.android.tools.r8.graph.I2 i2W = interfaceC1101ar.w();
            if (i2W == null) {
                return "uninitialized-new";
            }
            return "uninitialized " + i2W.H0();
        }
        if (!interfaceC1101ar.E()) {
            if (!d && !interfaceC1101ar.isPrimitive()) {
                x1f.a();
                return null;
            }
            return "primitive " + interfaceC1101ar.asPrimitive().getTypeName();
        }
        if (interfaceC1101ar.g()) {
            return "null";
        }
        if (interfaceC1101ar.z()) {
            AbstractC1120b40 abstractC1120b40K = interfaceC1101ar.c().K();
            if (abstractC1120b40K.r()) {
                return a(abstractC1120b40K);
            }
            if (!d && !abstractC1120b40K.w()) {
                x1f.a();
                return null;
            }
            return "initialized " + a(abstractC1120b40K);
        }
        boolean z = d;
        if (!z && !interfaceC1101ar.F()) {
            x1f.a();
            return null;
        }
        com.android.tools.r8.graph.I2 i2K = interfaceC1101ar.e().K();
        if (i2K.I0()) {
            return i2K.H0();
        }
        if (!z && !i2K.M0()) {
            x1f.a();
            return null;
        }
        return "initialized " + i2K.H0();
    }

    public static String b(InterfaceC1101ar interfaceC1101ar) {
        return a(1, interfaceC1101ar);
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 c(InterfaceC2576s8 interfaceC2576s8, C1724i9 c1724i9) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final C2291oo e() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC3159z1
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2291oo.class != obj.getClass()) {
            return false;
        }
        return this.c.equals(((C2291oo) obj).c);
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 f() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 g() {
        return this;
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 b(InterfaceC2576s8 interfaceC2576s8, C1724i9 c1724i9) {
        return this;
    }

    public static String b(El0 el0) {
        return a(el0);
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(C0322w2 c0322w2, InterfaceC2576s8 interfaceC2576s8) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(C0333y c0333y, com.android.tools.r8.graph.I2 i2, InterfaceC2576s8 interfaceC2576s8, BiFunction biFunction) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(C0333y c0333y, InterfaceC2576s8 interfaceC2576s8, int i, El0 el0, BiFunction biFunction) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(C0333y c0333y, InterfaceC2576s8 interfaceC2576s8, com.android.tools.r8.graph.I2... i2Arr) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(C1240ca c1240ca, com.android.tools.r8.graph.I2 i2) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(AbstractC1430ek0 abstractC1430ek0, com.android.tools.r8.graph.I2 i2) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, com.android.tools.r8.graph.I2 i2) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, BX bx) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, C1724i9 c1724i9) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(InterfaceC2576s8 interfaceC2576s8, AbstractC2624sj0 abstractC2624sj0) {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(BiFunction biFunction) {
        return this;
    }

    public static String a(com.android.tools.r8.graph.I2 i2) {
        if (!i2.I0() && !i2.M0()) {
            if (i2.R0()) {
                return "null";
            }
            if (!d && !i2.T0()) {
                x1f.a();
                return null;
            }
            return "primitive " + i2.H0();
        }
        return i2.H0();
    }

    @Override // com.android.tools.r8.internal.AbstractC1808j9
    public final AbstractC1808j9 a(int i, InterfaceC1101ar interfaceC1101ar, InterfaceC2576s8 interfaceC2576s8) {
        return this;
    }

    public static String a(AbstractC2624sj0 abstractC2624sj0) {
        if (abstractC2624sj0.r()) {
            Q3 q3A = abstractC2624sj0.a();
            AbstractC2624sj0 abstractC2624sj0Q = q3A.Q();
            if (!d && !abstractC2624sj0Q.w() && !abstractC2624sj0Q.H()) {
                x1f.a();
                return null;
            }
            boolean z = abstractC2624sj0Q.w() && !abstractC2624sj0Q.b().R().a.isEmpty();
            StringBuilder sb = new StringBuilder();
            if (z) {
                sb.append("(");
            }
            sb.append(a(abstractC2624sj0Q));
            if (z) {
                sb.append(")");
            }
            for (int i = 0; i < q3A.T(); i++) {
                sb.append("[]");
            }
            return sb.toString();
        }
        if (abstractC2624sj0.w()) {
            C2441qd c2441qdB = abstractC2624sj0.b();
            StringBuilder sb2 = new StringBuilder(c2441qdB.Q().H0());
            if (!c2441qdB.R().a.isEmpty()) {
                Iterator it = c2441qdB.R().b().iterator();
                sb2.append(" implements ");
                sb2.append(((com.android.tools.r8.graph.I2) ((C1405eW) it.next()).a()).H0());
                while (it.hasNext()) {
                    sb2.append(", ");
                    sb2.append(((com.android.tools.r8.graph.I2) ((C1405eW) it.next()).a()).H0());
                }
            }
            return sb2.toString();
        }
        if (abstractC2624sj0 instanceof C1034a40) {
            return "null";
        }
        if (d || abstractC2624sj0.H()) {
            return abstractC2624sj0.c().Q();
        }
        x1f.a();
        return null;
    }

    public static String a(El0 el0) {
        if (el0.a()) {
            return "object";
        }
        return "primitive " + el0.d().Q();
    }
}
