package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2415qF;
import com.android.tools.r8.internal.C2585sF;
import com.android.tools.r8.internal.FF;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FF extends K2 {
    public final InterfaceC1221cG c;
    public C0900Vg d;
    public String e;
    public UF f;
    public boolean g;
    public boolean h;

    public FF(C2516rW c2516rW, InterfaceC1221cG interfaceC1221cG) {
        super(c2516rW);
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = false;
        this.c = interfaceC1221cG;
    }

    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        FG c2158nG;
        String str;
        String str2;
        QG og;
        String str3;
        if (this.e == null) {
            AbstractC3114yW abstractC3114yW = this.b;
            abstractC3114yW.getClass();
            throw new C3096yE(abstractC3114yW, "Invalid extracted annotation, expected a version property.");
        }
        C0900Vg c0900Vg = this.d;
        if (c0900Vg == null) {
            AbstractC3114yW abstractC3114yW2 = this.b;
            abstractC3114yW2.getClass();
            throw new C3096yE(abstractC3114yW2, "Invalid extracted annotation, expected a context property.");
        }
        UF uf = this.f;
        boolean z = this.g;
        if (uf != null) {
            if (z || this.h) {
                AbstractC3114yW abstractC3114yW3 = this.b;
                abstractC3114yW3.getClass();
                throw new C3096yE(abstractC3114yW3, "Invalid extracted annotation, cannot be both an edge and check.");
            }
            InterfaceC1221cG interfaceC1221cG = this.c;
            C2329pF c2329pF = uf.e;
            c2329pF.a = c0900Vg.a(uf.f).a();
            interfaceC1221cG.accept(c2329pF.a());
            return;
        }
        if (z && this.h) {
            AbstractC3114yW abstractC3114yW4 = this.b;
            abstractC3114yW4.getClass();
            throw new C3096yE(abstractC3114yW4, "Invalid extracted annotation, cannot be both a removed and optimized-out check.");
        }
        if (!z && !this.h) {
            AbstractC3114yW abstractC3114yW5 = this.b;
            abstractC3114yW5.getClass();
            throw new C3096yE(abstractC3114yW5, "Invalid extracted annotation, must specify either an edge, a removed check, or an optimized-out check.");
        }
        int i = z ? 1 : 2;
        InterfaceC1221cG interfaceC1221cG2 = this.c;
        C3097yF c3097yF = C3097yF.c;
        C3097yF c3097yFA = c0900Vg.a(new C2585sF()).a();
        C0900Vg c0900Vg2 = this.d;
        C1476fH c1476fHB = C1476fH.b(c0900Vg2.a);
        C1476fH.a();
        C2416qG c2416qG = C2416qG.c;
        C2345pV c2345pV = C2345pV.b;
        ME me = new ME(c1476fHB, c2416qG, c2345pV);
        String str4 = c0900Vg2.c;
        AbstractC2671tG ag = me;
        if (str4 != null) {
            if (str4.charAt(0) != '(') {
                boolean z2 = C0900Vg.d;
                if (!z2 && ((str2 = c0900Vg2.c) == null || str2.charAt(0) != ':')) {
                    x1f.a();
                    return;
                }
                C1901kG c1901kG = C1901kG.h;
                C1987lG c1987lG = C1987lG.b;
                C2244oG c2244oG = C2244oG.b;
                C1988lH c1988lHA = C1988lH.a(c0900Vg2.b);
                C1987lG c1987lG2 = C1988lH.d == c1988lHA ? C1987lG.b : new C1987lG(c1988lHA);
                if (!z2 && ((str = c0900Vg2.c) == null || str.charAt(0) != ':')) {
                    x1f.a();
                    return;
                }
                c2158nG = new C2158nG(c2345pV, c1901kG, c1987lG2, AbstractC2330pG.a(AbstractC2587sH.a(c0900Vg2.c.substring(1))));
            } else {
                if (!C0900Vg.d && ((str3 = c0900Vg2.c) == null || str3.charAt(0) != '(')) {
                    x1f.a();
                    return;
                }
                C3050xi0 c3050xi0D = C3050xi0.d(c0900Vg2.c);
                String strB = c3050xi0D.b();
                String strB2 = C3050xi0.a(C3050xi0.f(strB), strB.length(), strB).b();
                if (strB2.equals("V")) {
                    og = PG.a;
                } else {
                    AbstractC2587sH abstractC2587sHA = AbstractC2587sH.a(strB2);
                    og = abstractC2587sHA instanceof C2245oH ? OG.b : new OG(abstractC2587sHA);
                }
                C0473Eu c0473EuG = AbstractC0551Hu.g();
                for (C3050xi0 c3050xi0 : C3050xi0.b(c3050xi0D.b())) {
                    c0473EuG.a(AbstractC2587sH.a(c3050xi0.b()));
                }
                C2345pV c2345pV2 = C2345pV.b;
                HG hg = HG.k;
                IG ig = IG.b;
                OG og2 = OG.b;
                IG igA = IG.a(C1988lH.a(c0900Vg2.b));
                AbstractC0551Hu abstractC0551HuA = c0473EuG.a();
                KG kg = abstractC0551HuA.isEmpty() ? KG.b : new KG(abstractC0551HuA);
                igA.getClass();
                if (IG.c == igA || IG.d == igA) {
                    if (!og.b() && !(og instanceof PG)) {
                        defpackage.l0.a("Method constructor pattern must match 'void' type.");
                        return;
                    }
                    og = PG.a;
                }
                c2158nG = new NG(c2345pV2, hg, igA, og, kg);
            }
            ME.g();
            EG eg = EG.d;
            ag = new AG(new OE(me), c2158nG);
        }
        interfaceC1221cG2.accept(new JE(c3097yFA, i, ag));
        super.a();
    }

    public static /* synthetic */ void a(C2585sF c2585sF) {
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(Object obj, String str) {
        if (str.equals("version") && (obj instanceof String)) {
            this.e = (String) obj;
            return;
        }
        C3030xW c3030xWB = this.b.b(str);
        if (this.e != null) {
            if (str.equals("context") && (obj instanceof String)) {
                this.d = C0900Vg.a((String) obj, c3030xWB);
                return;
            }
            if (str.equals("checkRemoved") && (obj instanceof Boolean)) {
                this.g = true;
                return;
            } else if (str.equals("checkOptimizedOut") && (obj instanceof Boolean)) {
                this.h = true;
                return;
            } else {
                super.a(obj, str);
                throw null;
            }
        }
        throw new C3096yE(c3030xWB, "Property 'version' must be defined before any other property");
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        if (str.equals("edge") && str2.equals("Lcom/android/tools/r8/keepanno/annotations/KeepEdge;")) {
            AbstractC3114yW abstractC3114yW = this.b;
            abstractC3114yW.getClass();
            UF uf = new UF(new C2516rW(abstractC3114yW, str2), new InterfaceC1221cG() { // from class: fl4
                @Override // com.android.tools.r8.internal.InterfaceC1221cG
                public final void accept(Object obj) {
                    FF.a((C2415qF) obj);
                }
            }, new Consumer() { // from class: gl4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    FF.a((C2585sF) obj);
                }
            });
            this.f = uf;
            return uf;
        }
        super.a(str, str2);
        throw null;
    }

    public static /* synthetic */ void a(C2415qF c2415qF) {
    }
}
