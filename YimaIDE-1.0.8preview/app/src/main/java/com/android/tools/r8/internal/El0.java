package com.android.tools.r8.internal;

import com.android.tools.r8.internal.El0;
import com.android.tools.r8.utils.structural.A;
import defpackage.hkh;
import defpackage.le6;
import defpackage.q68;
import java.util.function.ToIntFunction;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum El0 implements com.android.tools.r8.utils.structural.x<El0> {
    b,
    c,
    d,
    e,
    f;

    El0() {
    }

    public static El0 a(char c2) {
        if (c2 == 'F') {
            return d;
        }
        if (c2 != 'L') {
            if (c2 != 'S') {
                if (c2 == 'V') {
                    throw new C1727iB("No value type for void type.");
                }
                if (c2 != 'I') {
                    if (c2 == 'J') {
                        return e;
                    }
                    if (c2 != 'Z') {
                        if (c2 != '[') {
                            switch (c2) {
                                case 'B':
                                case 'C':
                                    break;
                                case 'D':
                                    return f;
                                default:
                                    q68.a(c2);
                                    return null;
                            }
                        }
                    }
                }
            }
            return c;
        }
        return b;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final boolean b() {
        return this == e || this == f;
    }

    public final int c() {
        return b() ? 2 : 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.x, com.android.tools.r8.utils.structural.s
    public final /* bridge */ /* synthetic */ int compareTo(com.android.tools.r8.utils.structural.s sVar) {
        return compareTo((Enum) sVar);
    }

    public final AbstractC2005lY d() {
        int i = Dl0.c[ordinal()];
        if (i == 2) {
            return AbstractC2624sj0.k();
        }
        if (i == 3) {
            return AbstractC2624sj0.j();
        }
        if (i == 4) {
            return AbstractC2624sj0.l();
        }
        if (i == 5) {
            return AbstractC2624sj0.i();
        }
        defpackage.gk0.a("Unexpected type in conversion to primitive: ", this);
        return null;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: ea4
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                El0.a(a);
            }
        };
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.a(new ToIntFunction() { // from class: fa4
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((El0) obj).ordinal();
            }
        });
    }

    public final boolean a() {
        return this == b;
    }

    public static El0 a(KN kn) {
        switch (kn.ordinal()) {
            case 0:
                return b;
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
                return c;
            case XmlPullParser.CDSECT /* 5 */:
                return d;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return e;
            case 7:
                return f;
            case 8:
            case 9:
                defpackage.gk0.a("Unexpected imprecise type: ", kn);
                return null;
            default:
                defpackage.gk0.a("Unexpected member type: ", kn);
                return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.x
    /* JADX INFO: renamed from: a */
    public final /* bridge */ /* synthetic */ int compareTo(com.android.tools.r8.utils.structural.x xVar) {
        return compareTo((Enum) xVar);
    }

    public static El0 a(com.android.tools.r8.graph.I2 i2) {
        return a((char) i2.f.f[0]);
    }

    public static El0 a(US us) {
        switch (Dl0.b[us.ordinal()]) {
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
                return c;
            case XmlPullParser.CDSECT /* 5 */:
                return d;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return e;
            case 7:
                return f;
            default:
                le6.a("Invalid numeric type '", us, "'");
                return null;
        }
    }

    public static El0 a(AbstractC2624sj0 abstractC2624sj0) {
        if (abstractC2624sj0.I()) {
            return b;
        }
        if (abstractC2624sj0.C()) {
            return c;
        }
        if (abstractC2624sj0.B()) {
            return d;
        }
        if (abstractC2624sj0.D()) {
            return e;
        }
        if (abstractC2624sj0.z()) {
            return f;
        }
        defpackage.gk0.a("Unexpected conversion of imprecise type: ", abstractC2624sj0);
        return null;
    }

    public final com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.B1 b1) {
        int i = Dl0.c[ordinal()];
        if (i == 1) {
            return b1.a2;
        }
        if (i == 2) {
            return b1.B1;
        }
        if (i == 3) {
            return b1.A1;
        }
        if (i == 4) {
            return b1.C1;
        }
        if (i == 5) {
            return b1.z1;
        }
        hkh.a();
        return null;
    }
}
