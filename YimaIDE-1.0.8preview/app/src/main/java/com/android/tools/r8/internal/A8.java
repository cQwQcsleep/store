package com.android.tools.r8.internal;

import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.pah;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class A8 extends AbstractC3175z9 {
    public static final /* synthetic */ boolean d = true;
    public final KN c;

    public A8(KN kn) {
        if (d || kn.a()) {
            this.c = kn;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final KN T() {
        return this.c;
    }

    public final com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.B1 b1) {
        switch (this.c.ordinal()) {
            case 0:
                return b1.d2;
            case 1:
                return b1.K1;
            case 2:
                return b1.H1;
            case XmlPullParser.END_TAG /* 3 */:
                return b1.M1;
            case 4:
                return b1.K1;
            case XmlPullParser.CDSECT /* 5 */:
                return b1.J1;
            case XmlPullParser.ENTITY_REF /* 6 */:
                return b1.L1;
            case 7:
                return b1.I1;
            default:
                pah.a("Unexpected type: ", this.c);
                return null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int w() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final boolean x() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final void a(com.android.tools.r8.utils.structural.o oVar) {
    }

    @Override // com.android.tools.r8.internal.AbstractC3175z9
    public final int a(AbstractC3175z9 abstractC3175z9, AbstractC3519a abstractC3519a, com.android.tools.r8.graph.O o) {
        com.android.tools.r8.graph.O.a(this, abstractC3175z9);
        return 0;
    }
}
