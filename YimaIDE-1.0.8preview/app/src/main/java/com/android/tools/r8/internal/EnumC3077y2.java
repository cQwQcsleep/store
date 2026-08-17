package com.android.tools.r8.internal;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.sun.jna.platform.linux.Fcntl;
import java.util.Arrays;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum EnumC3077y2 implements com.android.tools.r8.utils.structural.s<EnumC3077y2> {
    c("B"),
    d("B_1_1"),
    e("C"),
    f("D"),
    g("E"),
    h("E_0_1"),
    i("E_MR1"),
    j("F"),
    k("G"),
    l("G_MR1"),
    m("H"),
    n("H_MR1"),
    o("H_MR2"),
    p("I"),
    q("I_MR1"),
    r("J"),
    s("J_MR1"),
    t("J_MR2"),
    u("K"),
    v("K_WATCH"),
    w("L"),
    x("L_MR1"),
    y("M"),
    z("N"),
    A("N_MR1"),
    B("O"),
    C("O_MR1"),
    D("P"),
    E("Q"),
    F("R"),
    G("S"),
    H("Sv2"),
    I("T"),
    J("U"),
    K("MAIN");

    public static final EnumC3077y2 L;
    public static final EnumC3077y2 M;
    public static final EnumC3077y2 N;
    public static final /* synthetic */ boolean P = true;
    public final int b;

    static {
        EnumC3077y2 enumC3077y2 = J;
        EnumC3077y2 enumC3077y3 = K;
        L = enumC3077y2;
        M = enumC3077y2;
        N = enumC3077y3;
    }

    EnumC3077y2(String str) {
        this.b = i;
    }

    public static List<EnumC3077y2> a() {
        return Arrays.asList(values());
    }

    public static EnumC3077y2 b(int i2) {
        boolean z2 = P;
        if (!z2 && i2 <= 0) {
            x1f.a();
            return null;
        }
        if (!z2 && J != L) {
            x1f.a();
            return null;
        }
        if (!z2 && !N.d(L)) {
            x1f.a();
            return null;
        }
        switch (i2) {
            case 1:
                return c;
            case 2:
                return d;
            case XmlPullParser.END_TAG /* 3 */:
                return e;
            case 4:
                return f;
            case XmlPullParser.CDSECT /* 5 */:
                return g;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return h;
            case 7:
                return i;
            case 8:
                return j;
            case 9:
                return k;
            case XmlPullParser.DOCDECL /* 10 */:
                return l;
            case AndroidSdkVersion.HONEYCOMB /* 11 */:
                return m;
            case 12:
                return n;
            case 13:
                return o;
            case 14:
                return p;
            case 15:
                return q;
            case Fcntl.S_IWGRP /* 16 */:
                return r;
            case 17:
                return s;
            case AndroidSdkVersion.JELLY_BEAN_MR2 /* 18 */:
                return t;
            case AndroidSdkVersion.KITKAT /* 19 */:
                return u;
            case 20:
                return v;
            case AndroidSdkVersion.LOLLIPOP /* 21 */:
                return w;
            case 22:
                return x;
            case AndroidSdkVersion.M /* 23 */:
                return y;
            case AndroidSdkVersion.N /* 24 */:
                return z;
            case 25:
                return A;
            case AndroidSdkVersion.O /* 26 */:
                return B;
            case 27:
                return C;
            case AndroidSdkVersion.P /* 28 */:
                return D;
            case AndroidSdkVersion.Q /* 29 */:
                return E;
            case AndroidSdkVersion.R /* 30 */:
                return F;
            case AndroidSdkVersion.S /* 31 */:
                return G;
            case 32:
                return H;
            case AndroidSdkVersion.T /* 33 */:
                return I;
            case AndroidSdkVersion.U /* 34 */:
                return J;
            default:
                return K;
        }
    }

    public EnumC3077y2 c(EnumC3077y2 enumC3077y2) {
        return (EnumC3077y2) com.android.tools.r8.utils.structural.s.a(this, enumC3077y2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.s
    public final /* bridge */ /* synthetic */ int compareTo(com.android.tools.r8.utils.structural.s sVar) {
        return compareTo((Enum) sVar);
    }

    public int d() {
        return this.b;
    }

    public final String e() {
        return "Android " + name();
    }

    public EnumC3077y2 f() {
        return b(d() + 1);
    }

    public EnumC1095am c() {
        return EnumC1095am.c(this);
    }

    public static EnumC3077y2 b() {
        return c;
    }
}
