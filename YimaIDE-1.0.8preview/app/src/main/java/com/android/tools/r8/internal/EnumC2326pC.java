package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.EnumC0329x2;
import defpackage.exe;
import defpackage.hkh;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.pC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC2326pC {
    public static final EnumC2326pC d = new EnumC2326pC(0, 112, 118, "DIRECT");
    public static final EnumC2326pC e = new EnumC2326pC(1, 114, 120, "INTERFACE");
    public static final EnumC2326pC f = new EnumC2326pC(2, 113, 119, "STATIC");
    public static final EnumC2326pC g = new EnumC2326pC(3, 111, 117, "SUPER");
    public static final EnumC2326pC h = new EnumC2326pC(4, 110, 116, "VIRTUAL");
    public static final EnumC2326pC i = new EnumC2326pC(5, 35, -1, "NEW_ARRAY");
    public static final EnumC2326pC j = new EnumC2326pC(6, -1, -1, "MULTI_NEW_ARRAY");
    public static final EnumC2326pC k = new EnumC2326pC(8, 250, 251, "POLYMORPHIC");
    public static final /* synthetic */ boolean l = true;
    public final int b;
    public final int c;

    public EnumC2326pC(int i2, int i3, int i4, String str) {
        super(str, i2);
        this.b = i3;
        this.c = i4;
    }

    /* JADX WARN: Code duplicated, block: B:61:0x0102  */
    /* JADX WARN: Code duplicated, block: B:63:0x0108  */
    public static EnumC2326pC a(C0322w2 c0322w2, com.android.tools.r8.graph.H0 h0, C0333y c0333y, AbstractC3148ys abstractC3148ys) {
        com.android.tools.r8.graph.E0 e0D;
        if (c0322w2.b(c0333y.a())) {
            return d;
        }
        AbstractC3148ys abstractC3148ysA = c0333y.A();
        C0322w2 c0322w2A = abstractC3148ysA.a(abstractC3148ys, h0.getReference());
        if (c0322w2.w0() != c0322w2A.w0()) {
            if (!c0333y.M().Z() || !c0333y.M().l() || !h0.a().t1() || (e0D = c0333y.d(c0322w2.w0())) == null || !e0D.t1() || !e0D.a(h0.a())) {
                return g;
            }
            C0322w2 reference = h0.getReference();
            EnumC2326pC enumC2326pC = d;
            C0231j1 c0231j1C = e0D.c((C0322w2) abstractC3148ysA.a(c0322w2, reference, enumC2326pC, abstractC3148ys).a);
            return (c0231j1C == null || !c0231j1C.J0()) ? g : enumC2326pC;
        }
        C0322w2 reference2 = h0.getReference();
        EnumC2326pC enumC2326pC2 = d;
        C2850vO c2850vOA = abstractC3148ysA.a(c0322w2, reference2, enumC2326pC2, abstractC3148ys);
        if (!c2850vOA.c.d() && !c2850vOA.c.f()) {
            C0231j1 c0231j1C2 = h0.a().c((C0322w2) c2850vOA.a);
            if (c0231j1C2 != null && abstractC3148ysA.a(abstractC3148ys, c0231j1C2.getReference()).w0() == c0322w2A.w0()) {
                if (!h0.a().isInterface()) {
                    C1605gm0 c1605gm0 = c0333y.I;
                    if (c1605gm0 != null) {
                        com.android.tools.r8.graph.I2 i2W0 = c0322w2A.w0();
                        if (c1605gm0.b.containsKey(i2W0) || c1605gm0.c.containsKey(i2W0)) {
                            if (c0231j1C2.M0()) {
                                return g;
                            }
                        }
                    }
                    if (!l && !c0231j1C2.J0() && !c2850vOA.c.f()) {
                        x1f.a();
                        return null;
                    }
                } else if (c0231j1C2.M0()) {
                    return g;
                }
            }
            return g;
        }
        return enumC2326pC2;
    }

    public final boolean b() {
        return this == d;
    }

    public final boolean c() {
        return this == e;
    }

    public final boolean d() {
        return this == f;
    }

    public final boolean e() {
        return this == g;
    }

    public final boolean f() {
        return this == h;
    }

    public static EnumC2326pC a(int i2, C0322w2 c0322w2, com.android.tools.r8.graph.H0 h0, C0333y c0333y, AbstractC3148ys abstractC3148ys) {
        switch (i2) {
            case 182:
                if (c0333y.a().K4.b(c0322w2) && !c0333y.M().p0()) {
                    return k;
                }
                return h;
            case 183:
                return a(c0322w2, h0, c0333y, abstractC3148ys);
            case 184:
                return f;
            case 185:
                return e;
            default:
                exe.a("unknown CfInvoke opcode ", i2);
                return null;
        }
    }

    public final int a() {
        int iOrdinal = ordinal();
        if (iOrdinal == 8) {
            return 182;
        }
        if (iOrdinal == 0) {
            return 183;
        }
        if (iOrdinal == 1) {
            return 185;
        }
        if (iOrdinal == 2) {
            return 184;
        }
        if (iOrdinal == 3) {
            return 183;
        }
        if (iOrdinal == 4) {
            return 182;
        }
        hkh.a();
        return 0;
    }

    public final int a(boolean z) {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            return z ? 206 : 205;
        }
        if (iOrdinal == 1) {
            return 185;
        }
        if (iOrdinal == 2) {
            return z ? 204 : 184;
        }
        if (iOrdinal == 3) {
            return z ? 208 : 207;
        }
        if (iOrdinal == 4) {
            return 182;
        }
        hkh.a();
        return 0;
    }

    public final EnumC0329x2 a(C0322w2 c0322w2) {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            if (c0322w2.g.toString().equals("<init>")) {
                return EnumC0329x2.i;
            }
            return EnumC0329x2.j;
        }
        if (iOrdinal == 1) {
            return EnumC0329x2.k;
        }
        if (iOrdinal == 2) {
            return EnumC0329x2.g;
        }
        if (iOrdinal == 3) {
            return EnumC0329x2.l;
        }
        if (iOrdinal == 4) {
            return EnumC0329x2.h;
        }
        defpackage.gk0.a("Conversion to method handle with unexpected invoke type: ", this);
        return null;
    }
}
