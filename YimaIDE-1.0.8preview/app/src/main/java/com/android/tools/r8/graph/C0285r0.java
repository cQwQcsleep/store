package com.android.tools.r8.graph;

import com.android.tools.r8.graph.C0285r0;
import com.android.tools.r8.internal.C1338dh;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.EnumC3077y2;
import defpackage.m87;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.graph.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0285r0 extends AbstractC0259n1 implements com.android.tools.r8.utils.structural.x<C0285r0> {
    public static final C0285r0[] d = new C0285r0[0];
    public static final /* synthetic */ boolean e = true;
    public final int b;
    public final C0196e1 c;

    public C0285r0(int i, C0196e1 c0196e1) {
        this.b = i;
        this.c = c0196e1;
    }

    public static C0279q0 a(C0306u0 c0306u0, B1 b1, com.android.tools.r8.synthesis.J j, com.android.tools.r8.androidapi.a aVar) {
        com.android.tools.r8.androidapi.f fVar;
        com.android.tools.r8.synthesis.S.b bVar;
        if (c0306u0.size() != 1) {
            return null;
        }
        C0285r0 c0285r0 = c0306u0.d[0];
        C0196e1 c0196e1 = c0285r0.c;
        if (c0196e1.b != b1.u5 || c0196e1.c.length != 3) {
            return null;
        }
        boolean z = e;
        if (!z && !b1.j1.e(b1.k1)) {
            x1f.a();
            return null;
        }
        C0299t0[] c0299t0Arr = c0285r0.c.c;
        C0299t0 c0299t0 = c0299t0Arr[0];
        C0299t0 c0299t1 = c0299t0Arr[1];
        C0299t0 c0299t2 = c0299t0Arr[2];
        if (c0299t1.b != b1.j1 || !c0299t1.c.R0() || c0299t2.b != b1.k1 || !c0299t2.c.Y0() || c0299t0.b != b1.l1 || !c0299t0.c.R0() || !j.b.a().equals(((H2) c0299t2.c.E0().d1()).toString())) {
            return null;
        }
        int iD1 = c0299t0.c.y0().d1();
        if (iD1 == -2) {
            int i = com.android.tools.r8.androidapi.f.a;
            fVar = com.android.tools.r8.androidapi.g.b;
        } else if (iD1 == -1) {
            int i2 = com.android.tools.r8.androidapi.f.a;
            fVar = com.android.tools.r8.androidapi.h.b;
        } else {
            EnumC3077y2 enumC3077y2B = EnumC3077y2.b(iD1);
            aVar.getClass();
            if (enumC3077y2B == EnumC3077y2.K) {
                int i3 = com.android.tools.r8.androidapi.f.a;
                fVar = com.android.tools.r8.androidapi.f.a.c;
            } else {
                fVar = aVar.a[enumC3077y2B.d()];
            }
        }
        com.android.tools.r8.synthesis.S s = j.b;
        int iD2 = c0299t1.c.y0().d1();
        if (iD2 > 0) {
            if (iD2 <= s.R.size()) {
                bVar = (com.android.tools.r8.synthesis.S.b) s.R.get(iD2 - 1);
            }
            if (!z || bVar != j.b.O || fVar.v()) {
                return new C0279q0(bVar, fVar);
            }
            x1f.a();
            return null;
        }
        s.getClass();
        bVar = null;
        if (!z) {
        }
        return new C0279q0(bVar, fVar);
    }

    public static ArrayList b(I2 i2, C0285r0 c0285r0) {
        O2 o2A = a(i2, c0285r0);
        if (o2A == null) {
            return null;
        }
        O2.a aVarQ0 = o2A.q0();
        ArrayList arrayList = new ArrayList(aVarQ0.d1().length);
        for (O2 o2 : aVarQ0.d1()) {
            arrayList.add((I2) o2.F0().c);
        }
        return arrayList;
    }

    public static ArrayList c(C0285r0 c0285r0, B1 b1) {
        O2 o2A = a(b1.g5, c0285r0);
        if (o2A == null) {
            return null;
        }
        O2.a aVarQ0 = o2A.q0();
        ArrayList arrayList = new ArrayList(aVarQ0.d1().length);
        for (O2 o2 : aVarQ0.d1()) {
            arrayList.add((I2) o2.F0().c);
        }
        return arrayList;
    }

    public static boolean d(C0285r0 c0285r0, B1 b1) {
        return c0285r0.c.b == b1.i5;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0285r0) {
            C0285r0 c0285r0 = (C0285r0) obj;
            if (this.b == c0285r0.b && this.c.equals(c0285r0.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.c.hashCode() * 3) + this.b;
    }

    public J2 n0() {
        return null;
    }

    public com.android.tools.r8.utils.structural.y o() {
        return new m87();
    }

    public I2 o0() {
        return this.c.b;
    }

    public int p0() {
        return this.b;
    }

    public boolean q0() {
        return false;
    }

    public final String toString() {
        return this.b + " " + this.c;
    }

    public static C0322w2 b(C0285r0 c0285r0, B1 b1) {
        O2 o2A = a(b1.e5, c0285r0);
        if (o2A == null) {
            return null;
        }
        return (C0322w2) o2A.A0().c;
    }

    public void a(C0333y c0333y, com.android.tools.r8.dex.M m) {
        this.c.a(c0333y, m);
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public void a(com.android.tools.r8.dex.X x) {
        x.a(this);
    }

    public static boolean a(C2752uB c2752uB, I2 i2) {
        if (!c2752uB.A0 && i2 != c2752uB.a.u5) {
            H2 h2Z0 = i2.z0();
            H2 h2 = c2752uB.a.m1;
            h2Z0.getClass();
            if (!h2Z0.b(h2.f)) {
                if (!c2752uB.K0) {
                    return false;
                }
                B1 b1 = c2752uB.a;
                boolean z = C1338dh.d;
                return i2 == b1.v5 || i2 == b1.w5;
            }
        }
        return true;
    }

    public static I2 a(C0285r0 c0285r0, B1 b1) {
        O2 o2A = a(b1.d5, c0285r0);
        if (o2A == null) {
            return null;
        }
        return (I2) o2A.F0().c;
    }

    public static C0285r0 a(O2[] o2Arr, O2[] o2Arr2, B1 b1) {
        if (e || o2Arr.length == o2Arr2.length) {
            return new C0285r0(2, new C0196e1(b1.h5, new C0299t0[]{new C0299t0(b1.c("names"), new O2.a(o2Arr)), new C0299t0(b1.c("accessFlags"), new O2.a(o2Arr2))}));
        }
        x1f.a();
        return null;
    }

    public static C0285r0 a(String str, B1 b1) {
        I2 i2 = b1.i5;
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < length) {
            int i3 = i + 1;
            if (str.charAt(i) == 'L') {
                while (i3 < length) {
                    char cCharAt = str.charAt(i3);
                    if (cCharAt == ';' || cCharAt == '<') {
                        i3++;
                        break;
                    }
                    i3++;
                }
            } else {
                while (i3 < length && str.charAt(i3) != 'L') {
                    i3++;
                }
            }
            arrayList.add(new O2.j(b1.c(str.substring(i, i3))));
            i = i3;
        }
        return a(i2, b1, new O2.a((O2[]) arrayList.toArray(O2.b)));
    }

    public static String a(C0285r0 c0285r0) {
        return a(c0285r0.c);
    }

    public static String a(C0196e1 c0196e1) {
        O2.a aVarQ0 = c0196e1.c[0].c.q0();
        StringBuilder sb = new StringBuilder();
        for (O2 o2 : aVarQ0.d1()) {
            sb.append(((H2) o2.E0().c).toString());
        }
        return sb.toString();
    }

    public static C0285r0 a(I2 i2, B1 b1, O2 o2) {
        return new C0285r0(2, new C0196e1(i2, new C0299t0[]{new C0299t0(b1.c("value"), o2)}));
    }

    public static O2 a(I2 i2, C0285r0 c0285r0) {
        boolean z = e;
        if (!z && c0285r0.b != 2) {
            x1f.a();
            return null;
        }
        if (!z && c0285r0.c.b != i2) {
            x1f.a();
            return null;
        }
        C0299t0[] c0299t0Arr = c0285r0.c.c;
        if (c0299t0Arr.length == 0) {
            return null;
        }
        return c0299t0Arr[0].c;
    }

    public static O2 a(I2 i2, C0285r0 c0285r0, H2 h2) {
        boolean z = e;
        if (!z && c0285r0.b != 2) {
            x1f.a();
            return null;
        }
        if (!z && c0285r0.o0() != i2) {
            x1f.a();
            return null;
        }
        for (C0299t0 c0299t0 : c0285r0.c.c) {
            if (c0299t0.b == h2) {
                return c0299t0.c;
            }
        }
        return null;
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.e(new Function() { // from class: r5i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0285r0) obj).c;
            }
        }).a(new ToIntFunction() { // from class: w5i
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((C0285r0) obj).b;
            }
        });
    }

    public final C0285r0 a(Function function) {
        C0196e1 c0196e1 = (C0196e1) function.apply(this.c);
        if (c0196e1 == this.c) {
            return this;
        }
        if (c0196e1 == null) {
            return null;
        }
        return new C0285r0(this.b, c0196e1);
    }
}
