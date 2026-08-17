package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ZF extends AbstractC0927Wh {
    public final C2773uW a;
    public C2842vG b = null;
    public final C0688Nc c;
    public final C1137bG d;
    public final JF e;
    public final AbstractC0551Hu f;

    public ZF(AbstractC3114yW abstractC3114yW) {
        abstractC3114yW.getClass();
        this.a = new C2773uW(abstractC3114yW, "member");
        C0688Nc c0688Nc = new C0688Nc(new C2773uW(abstractC3114yW, "member-annotated-by"));
        this.c = c0688Nc;
        c0688Nc.a("memberAnnotatedByClassName", EnumC0662Mc.c);
        c0688Nc.a("memberAnnotatedByClassConstant", EnumC0662Mc.d);
        c0688Nc.a("memberAnnotatedByClassNamePattern", EnumC0662Mc.b);
        C1137bG c1137bG = new C1137bG(abstractC3114yW);
        this.d = c1137bG;
        JF jf = new JF(abstractC3114yW);
        this.e = jf;
        this.f = AbstractC0551Hu.a(c0688Nc, c1137bG, jf);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final J2 a(String str) {
        if (!str.equals("memberAccess")) {
            return super.a(str);
        }
        C3014xG c3014xG = C3014xG.e;
        C2842vG c2842vG = new C2842vG();
        this.b = c2842vG;
        return new YF(this.a, c2842vG);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final AbstractC0551Hu b() {
        return this.f;
    }

    /* JADX WARN: Code duplicated, block: B:108:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:110:0x01bb  */
    public final FG c() {
        C1137bG c1137bG = this.d;
        if (c1137bG.b != null) {
            if (c1137bG.c == null) {
                c1137bG.c = new MG();
            }
            c1137bG.c.b = c1137bG.b.c();
        }
        if (c1137bG.d.a()) {
            if (c1137bG.c == null) {
                c1137bG.c = new MG();
            }
            MG mg = c1137bG.c;
            C1476fH c1476fH = (C1476fH) c1137bG.d.getValue();
            if (!AbstractC2515rV.a && c1476fH == null) {
                x1f.a();
                return null;
            }
            mg.a = new C2430qV(c1476fH);
        }
        if (c1137bG.e.a()) {
            C1988lH c1988lH = (C1988lH) c1137bG.e.getValue();
            if (c1137bG.c == null) {
                c1137bG.c = new MG();
            }
            c1137bG.c.c = IG.a(c1988lH);
        }
        if (c1137bG.f.a()) {
            if (c1137bG.c == null) {
                c1137bG.c = new MG();
            }
            c1137bG.c.d = c1137bG.f.b();
        }
        if (c1137bG.g.a.a()) {
            if (c1137bG.c == null) {
                c1137bG.c = new MG();
            }
            c1137bG.c.e = (LG) c1137bG.g.getValue();
        }
        MG mg2 = c1137bG.c;
        NG ngA = mg2 != null ? mg2.a() : null;
        JF jf = this.e;
        if (jf.e != null) {
            if (jf.f == null) {
                jf.f = new C2073mG();
            }
            jf.f.b = jf.e.c();
        }
        if (jf.b.a()) {
            if (jf.f == null) {
                jf.f = new C2073mG();
            }
            C2073mG c2073mG = jf.f;
            C1476fH c1476fH2 = (C1476fH) jf.b.getValue();
            if (!AbstractC2515rV.a && c1476fH2 == null) {
                x1f.a();
                return null;
            }
            c2073mG.a = new C2430qV(c1476fH2);
        }
        if (jf.c.a()) {
            if (jf.f == null) {
                jf.f = new C2073mG();
            }
            C2073mG c2073mG2 = jf.f;
            C1988lH c1988lH2 = (C1988lH) jf.c.getValue();
            C1987lG c1987lG = C1987lG.b;
            c1988lH2.getClass();
            c2073mG2.c = C1988lH.d == c1988lH2 ? C1987lG.b : new C1987lG(c1988lH2);
        }
        if (jf.d.a.a()) {
            if (jf.f == null) {
                jf.f = new C2073mG();
            }
            jf.f.d = (AbstractC2330pG) jf.d.getValue();
        }
        C2073mG c2073mG3 = jf.f;
        C2158nG c2158nGA = c2073mG3 != null ? c2073mG3.a() : null;
        if (this.b == null && !this.c.a()) {
            if (ngA != null && c2158nGA != null) {
                C2773uW c2773uW = this.a;
                c2773uW.getClass();
                throw new C3096yE(c2773uW, "Cannot define both a field and a method pattern");
            }
            if (ngA != null) {
                return ngA;
            }
            if (c2158nGA != null) {
                return c2158nGA;
            }
            return null;
        }
        if (ngA != null || c2158nGA != null) {
            C2773uW c2773uW2 = this.a;
            c2773uW2.getClass();
            throw new C3096yE(c2773uW2, "Cannot define common member access as well as field or method pattern");
        }
        C3014xG c3014xG = C3014xG.e;
        C2842vG c2842vG = this.b;
        if (c2842vG != null) {
            Set setA = c2842vG.a();
            AbstractC1484fP abstractC1484fP = c2842vG.c;
            AbstractC1484fP abstractC1484fP2 = c2842vG.d;
            AbstractC1484fP abstractC1484fP3 = c2842vG.e;
            if (X1.a(setA)) {
                abstractC1484fP.getClass();
                if (abstractC1484fP instanceof C1230cP) {
                    abstractC1484fP2.getClass();
                    if (abstractC1484fP2 instanceof C1230cP) {
                        abstractC1484fP3.getClass();
                        if (!(abstractC1484fP3 instanceof C1230cP)) {
                            c3014xG = new C3014xG(setA, abstractC1484fP, abstractC1484fP2, abstractC1484fP3);
                            if (!C2842vG.f && c3014xG.a()) {
                                x1f.a();
                                return null;
                            }
                        }
                    } else {
                        c3014xG = new C3014xG(setA, abstractC1484fP, abstractC1484fP2, abstractC1484fP3);
                        if (!C2842vG.f) {
                            x1f.a();
                            return null;
                        }
                    }
                } else {
                    c3014xG = new C3014xG(setA, abstractC1484fP, abstractC1484fP2, abstractC1484fP3);
                    if (!C2842vG.f) {
                        x1f.a();
                        return null;
                    }
                }
            } else {
                c3014xG = new C3014xG(setA, abstractC1484fP, abstractC1484fP2, abstractC1484fP3);
                if (!C2842vG.f) {
                    x1f.a();
                    return null;
                }
            }
        }
        C1476fH c1476fH3 = (C1476fH) this.c.getValue();
        AbstractC2515rV c2430qV = c1476fH3 != null ? new C2430qV(c1476fH3) : C2345pV.b;
        return (c2430qV.b() && c3014xG.a()) ? EG.d : new EG(c2430qV, c3014xG);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh, com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a() {
        return this.b != null || super.a();
    }
}
