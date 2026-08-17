package com.android.tools.r8.internal;

import com.android.tools.r8.naming.AbstractC3345r0;
import defpackage.oof;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ar, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC1101ar {
    public static final /* synthetic */ int a = 0;

    static {
        boolean z = AbstractC1014Zq.a;
    }

    static InterfaceC0993Yv a(KN kn, com.android.tools.r8.graph.B1 b1) {
        if (!AbstractC1014Zq.a && !kn.a()) {
            x1f.a();
            return null;
        }
        switch (kn.ordinal()) {
            case 0:
                return b(b1.a2);
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
                return C1469fA.c;
            case XmlPullParser.CDSECT /* 5 */:
                return C3062xq.c;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return C1227cM.c;
            case 7:
                return C2202nm.c;
            default:
                defpackage.gk0.a("Unexpected MemberType: ", kn);
                return null;
        }
    }

    static C1192bw b(com.android.tools.r8.graph.I2 i2) {
        boolean z = AbstractC1014Zq.a;
        if (!z && !i2.U0()) {
            x1f.a();
            return null;
        }
        if (z || !i2.R0()) {
            return new C1192bw(i2);
        }
        x1f.a();
        return null;
    }

    static void d() {
        C1857jk0 c1857jk0 = C1857jk0.c;
    }

    AbstractC2610sc0 A();

    boolean B();

    boolean C();

    AbstractC1430ek0 D();

    boolean E();

    boolean F();

    C2170nS G();

    boolean H();

    K9 I();

    AbstractC1019Zv J();

    com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.B1 b1);

    com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.B1 b1, com.android.tools.r8.graph.I2 i2);

    Object a(AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2, AbstractC3345r0 abstractC3345r0);

    boolean a();

    InterfaceC1750iY asPrimitive();

    boolean b();

    C1106aw c();

    C1192bw e();

    boolean f();

    boolean g();

    boolean h();

    boolean i();

    boolean isPrimitive();

    C1857jk0 j();

    Am0 k();

    boolean l();

    boolean m();

    InterfaceC3228zm0 n();

    int o();

    boolean p();

    InterfaceC2355pc0 q();

    boolean r();

    InterfaceC1274cw s();

    boolean t();

    C1687hk0 u();

    boolean v();

    com.android.tools.r8.graph.I2 w();

    boolean x();

    BX y();

    boolean z();

    static InterfaceC0993Yv a(com.android.tools.r8.graph.I2 i2) {
        if (i2.T0()) {
            if (AbstractC1014Zq.a || i2.T0()) {
                return a((char) i2.z0().f[0]);
            }
            x1f.a();
            return null;
        }
        if (AbstractC1014Zq.a || i2.U0()) {
            return i2.R0() ? C2170nS.c : b(i2);
        }
        x1f.a();
        return null;
    }

    static InterfaceC1750iY a(char c) {
        if (c == 'F') {
            return C3062xq.c;
        }
        if (c == 'S') {
            return C3121yb0.c;
        }
        if (c == 'Z') {
            return J6.c;
        }
        if (c == 'I') {
            return C1469fA.c;
        }
        if (c != 'J') {
            switch (c) {
                case 'B':
                    return G7.c;
                case 'C':
                    return C1582gb.c;
                case 'D':
                    return C2202nm.c;
                default:
                    oof.a("Unexpected primitive type: ", c);
                    return null;
            }
        }
        return C1227cM.c;
    }

    static C1687hk0 a(com.android.tools.r8.graph.I2 i2, K9 k9) {
        return new C1687hk0(i2, k9);
    }

    default InterfaceC1101ar a(Function function) {
        if (AbstractC1014Zq.a || !C()) {
            return this;
        }
        x1f.a();
        return null;
    }
}
