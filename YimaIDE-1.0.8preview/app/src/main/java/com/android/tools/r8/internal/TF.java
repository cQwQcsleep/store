package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2585sF;
import defpackage.f5c;
import defpackage.g5c;
import defpackage.w36;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TF extends XO {
    public final InterfaceC1221cG c;
    public final String d;
    public final String e;
    public final String f;
    public final C2944wW g;

    public TF(C2601sW c2601sW, InterfaceC1221cG interfaceC1221cG, String str, String str2, String str3) {
        super(589824, null);
        this.c = interfaceC1221cG;
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = new C2944wW(c2601sW, str2, str3);
    }

    public static K2 a(String str, boolean z, boolean z2, Consumer consumer, C2516rW c2516rW, String str2, String str3, String str4, Consumer consumer2) {
        if (z || !z2) {
            return null;
        }
        if (str.equals("Lcom/android/tools/r8/keepanno/annotations/KeepEdge;")) {
            Objects.requireNonNull(consumer);
            return new UF(c2516rW, new g5c(consumer), consumer2);
        }
        if (str.equals("Lcom/android/tools/r8/keepanno/annotations/UsesReflection;")) {
            Objects.requireNonNull(consumer);
            return new C1561gG(c2516rW, new g5c(consumer), consumer2, a(str2, str3, str4));
        }
        if (str.equals("Lcom/android/tools/r8/keepanno/annotations/KeepForApi;")) {
            Objects.requireNonNull(consumer);
            return new LF(c2516rW, new g5c(consumer), consumer2, a(str2, str3, str4));
        }
        if (str.equals("Lcom/android/tools/r8/keepanno/annotations/UsedByReflection;") || str.equals("Lcom/android/tools/r8/keepanno/annotations/UsedByNative;")) {
            Objects.requireNonNull(consumer);
            return new C1389eG(c2516rW, new g5c(consumer), consumer2, a(str2, str3, str4));
        }
        if (str.equals("Lcom/android/tools/r8/keepanno/annotations/CheckRemoved;")) {
            Objects.requireNonNull(consumer);
            return new CF(c2516rW, new f5c(consumer), consumer2, a(str2, str3, str4), 1);
        }
        if (!str.equals("Lcom/android/tools/r8/keepanno/annotations/CheckOptimizedOut;")) {
            return null;
        }
        Objects.requireNonNull(consumer);
        return new CF(c2516rW, new f5c(consumer), consumer2, a(str2, str3, str4), 2);
    }

    @Override // com.android.tools.r8.internal.XO
    public final J2 a(String str, boolean z) {
        InterfaceC1221cG interfaceC1221cG = this.c;
        Objects.requireNonNull(interfaceC1221cG);
        w36 w36Var = new w36(interfaceC1221cG);
        C2944wW c2944wW = this.g;
        c2944wW.getClass();
        return a(str, z, true, w36Var, new C2516rW(c2944wW, str), this.d, this.e, this.f, new Consumer() { // from class: v0e
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C2585sF) obj);
            }
        });
    }

    public static AG a(String str, String str2, String str3) {
        QG og;
        KG kg;
        String strB = C3050xi0.a(C3050xi0.f(str3), str3.length(), str3).b();
        C3050xi0[] c3050xi0ArrB = C3050xi0.b(str3);
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        for (C3050xi0 c3050xi0 : c3050xi0ArrB) {
            c0473EuG.a(AbstractC2587sH.a(c3050xi0.b()));
        }
        if ("V".equals(strB)) {
            og = PG.a;
        } else {
            AbstractC2587sH abstractC2587sHA = AbstractC2587sH.a(strB);
            if (abstractC2587sHA instanceof C2245oH) {
                og = OG.b;
            } else {
                og = new OG(abstractC2587sHA);
            }
        }
        ME.g();
        EG eg = EG.d;
        C1476fH c1476fHA = C1476fH.a(str);
        C1476fH.a();
        C2416qG c2416qG = C2416qG.c;
        C2345pV c2345pV = C2345pV.b;
        OE oe = new OE(new ME(c1476fHA, c2416qG, c2345pV));
        HG hg = HG.k;
        IG ig = IG.b;
        OG og2 = OG.b;
        IG igA = IG.a(C1988lH.a(str2));
        AbstractC0551Hu abstractC0551HuA = c0473EuG.a();
        if (abstractC0551HuA.isEmpty()) {
            kg = KG.b;
        } else {
            kg = new KG(abstractC0551HuA);
        }
        igA.getClass();
        if (IG.c == igA || IG.d == igA) {
            if (!og.b() && !(og instanceof PG)) {
                defpackage.l0.a("Method constructor pattern must match 'void' type.");
                return null;
            }
            og = PG.a;
        }
        return new AG(oe, new NG(c2345pV, hg, igA, og, kg));
    }

    public final void a(C2585sF c2585sF) {
        String strC = AbstractC1732iG.c(this.d);
        String str = this.e;
        String str2 = this.f;
        c2585sF.getClass();
        c2585sF.a = new C3013xF(strC, str, str2);
    }
}
