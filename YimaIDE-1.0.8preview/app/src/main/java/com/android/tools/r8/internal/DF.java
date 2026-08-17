package com.android.tools.r8.internal;

import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DF extends AbstractC0927Wh {
    public static final /* synthetic */ boolean h = true;
    public final C2773uW a;
    public final Supplier b;
    public PE c = null;
    public final C0688Nc d;
    public final C0688Nc e;
    public final C0656Lw f;
    public final AbstractC0551Hu g;

    public DF(AbstractC3114yW abstractC3114yW, Supplier supplier) {
        abstractC3114yW.getClass();
        this.a = new C2773uW(abstractC3114yW, "class");
        this.b = supplier;
        C0688Nc c0688Nc = new C0688Nc(new C2773uW(abstractC3114yW, "class-name"));
        this.d = c0688Nc;
        EnumC0662Mc enumC0662Mc = EnumC0662Mc.c;
        c0688Nc.a("className", enumC0662Mc);
        EnumC0662Mc enumC0662Mc2 = EnumC0662Mc.d;
        c0688Nc.a("classConstant", enumC0662Mc2);
        EnumC0662Mc enumC0662Mc3 = EnumC0662Mc.b;
        c0688Nc.a("classNamePattern", enumC0662Mc3);
        C0688Nc c0688Nc2 = new C0688Nc(new C2773uW(abstractC3114yW, "class-annotated-by"));
        this.e = c0688Nc2;
        c0688Nc2.a("classAnnotatedByClassName", enumC0662Mc);
        c0688Nc2.a("classAnnotatedByClassConstant", enumC0662Mc2);
        c0688Nc2.a("classAnnotatedByClassNamePattern", enumC0662Mc3);
        C0656Lw c0656Lw = new C0656Lw(abstractC3114yW);
        this.f = c0656Lw;
        c0656Lw.a("instanceOfPattern", EnumC0631Kw.f);
        c0656Lw.a("instanceOfClassName", EnumC0631Kw.b);
        c0656Lw.a("instanceOfClassConstant", EnumC0631Kw.d);
        c0656Lw.a("instanceOfClassNameExclusive", EnumC0631Kw.c);
        c0656Lw.a("instanceOfClassConstantExclusive", EnumC0631Kw.e);
        this.g = AbstractC0551Hu.a(c0688Nc, c0688Nc2, c0656Lw);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final boolean a(Object obj, String str) {
        if (!str.equals("classFromBinding") || !(obj instanceof String)) {
            return super.a(obj, str);
        }
        NE ne = new NE(new KE(((C1475fG) this.b.get()).a((String) obj)));
        if (this.c == null) {
            this.c = ne;
            return true;
        }
        C2773uW c2773uW = this.a;
        c2773uW.getClass();
        throw new C3096yE(c2773uW, "Cannot reference multiple class bindings for a single class item");
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final AbstractC0551Hu b() {
        return this.g;
    }

    public final PE c() {
        if (this.c != null && (this.d.a() || this.e.a() || this.f.a())) {
            C2773uW c2773uW = this.a;
            c2773uW.getClass();
            throw new C3096yE(c2773uW, "Cannot reference a class binding and class patterns for a single class item");
        }
        PE pe = this.c;
        if (pe != null) {
            return pe;
        }
        if (this.d.a() || this.e.a() || this.f.a()) {
            C1476fH.a();
            C2416qG c2416qG = C2416qG.c;
            C1476fH c1476fH = (C1476fH) this.d.a((Object) C1476fH.a());
            C1476fH c1476fH2 = (C1476fH) this.e.getValue();
            return new OE(new ME(c1476fH, (AbstractC2500rG) this.f.a(C2416qG.c), c1476fH2 != null ? new C2430qV(c1476fH2) : C2345pV.b));
        }
        if (h || !a()) {
            return new OE(ME.g());
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh, com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a() {
        return this.c != null || super.a();
    }
}
