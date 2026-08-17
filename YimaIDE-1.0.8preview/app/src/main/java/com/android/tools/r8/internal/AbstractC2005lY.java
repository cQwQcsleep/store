package com.android.tools.r8.internal;

import defpackage.le6;
import defpackage.q68;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lY, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2005lY extends AbstractC2624sj0 {
    public static final /* synthetic */ boolean b = true;

    /* JADX WARN: Code duplicated, block: B:30:0x0042  */
    /* JADX WARN: Code duplicated, block: B:33:0x0049  */
    public static AbstractC2005lY a(char c, boolean z) {
        if (c == 'F') {
            return AbstractC2624sj0.j();
        }
        if (c == 'S') {
            if (z) {
                return AbstractC2624sj0.n();
            }
            if (z) {
                return AbstractC2624sj0.h();
            }
        } else {
            if (c == 'V') {
                throw new C1727iB("No value type for void type.");
            }
            if (c != 'Z') {
                if (c != 'I') {
                    if (c == 'J') {
                        return AbstractC2624sj0.l();
                    }
                    switch (c) {
                        case 'B':
                            break;
                        case 'C':
                            if (z) {
                                return AbstractC2624sj0.h();
                            }
                            break;
                        case 'D':
                            return AbstractC2624sj0.i();
                        default:
                            q68.a(c);
                            return null;
                    }
                }
            } else if (z) {
                return AbstractC2624sj0.e();
            }
            if (z) {
                return AbstractC2624sj0.g();
            }
            if (z) {
                return AbstractC2624sj0.n();
            }
            if (z) {
                return AbstractC2624sj0.h();
            }
        }
        return AbstractC2624sj0.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final boolean H() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final C2427qS N() {
        return C2427qS.b();
    }

    public abstract String P();

    public abstract String Q();

    public final com.android.tools.r8.graph.I2 b(com.android.tools.r8.graph.B1 b1) {
        if (s()) {
            return b1.w1;
        }
        if (u()) {
            return b1.x1;
        }
        if (J()) {
            return b1.D1;
        }
        if (v()) {
            return b1.y1;
        }
        if (C()) {
            return b1.B1;
        }
        if (B()) {
            return b1.A1;
        }
        if (D()) {
            return b1.C1;
        }
        if (z()) {
            return b1.z1;
        }
        throw new Kk0("Imprecise primitive type '" + toString() + "'");
    }

    @Override // com.android.tools.r8.internal.AbstractC2624sj0
    public final AbstractC2005lY c() {
        return this;
    }

    public static AbstractC2005lY a(US us) {
        switch (AbstractC1919kY.a[us.ordinal()]) {
            case 1:
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
            case 4:
                return AbstractC2624sj0.k();
            case XmlPullParser.CDSECT /* 5 */:
                return AbstractC2624sj0.j();
            case XmlPullParser.ENTITY_REF /* 6 */:
                return AbstractC2624sj0.l();
            case 7:
                return AbstractC2624sj0.i();
            default:
                le6.a("Invalid numeric type '", us, "'");
                return null;
        }
    }
}
