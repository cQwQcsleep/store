package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1137bG extends AbstractC0927Wh {
    public final AbstractC3114yW a;
    public GG b = null;
    public MG c = null;
    public final C0688Nc d;
    public final Hf0 e;
    public final OO f;
    public final HO g;
    public final AbstractC0551Hu h;

    public C1137bG(AbstractC3114yW abstractC3114yW) {
        this.a = abstractC3114yW;
        abstractC3114yW.getClass();
        C0688Nc c0688Nc = new C0688Nc(new C2773uW(abstractC3114yW, "method-annotated-by"));
        this.d = c0688Nc;
        c0688Nc.a("methodAnnotatedByClassName", EnumC0662Mc.c);
        c0688Nc.a("methodAnnotatedByClassConstant", EnumC0662Mc.d);
        c0688Nc.a("methodAnnotatedByClassNamePattern", EnumC0662Mc.b);
        Hf0 hf0 = new Hf0(new C2773uW(abstractC3114yW, "method-name"));
        this.e = hf0;
        hf0.a("methodName", Gf0.b);
        hf0.a("methodNamePattern", Gf0.c);
        OO oo = new OO(new C2773uW(abstractC3114yW, "return-type"));
        this.f = oo;
        EnumC2881vj0 enumC2881vj0 = EnumC2881vj0.c;
        oo.a("methodReturnType", enumC2881vj0);
        oo.a("methodReturnTypeConstant", EnumC2881vj0.d);
        EnumC2881vj0 enumC2881vj1 = EnumC2881vj0.b;
        oo.a("methodReturnTypePattern", enumC2881vj1);
        HO ho = new HO(new C2773uW(abstractC3114yW, "parameters"));
        this.g = ho;
        ho.a.a("methodParameters", enumC2881vj0);
        ho.a.a("methodParameterTypePatterns", enumC2881vj1);
        this.h = AbstractC0551Hu.a(c0688Nc, hf0, oo, ho);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final J2 a(String str) {
        if (!str.equals("methodAccess")) {
            return super.a(str);
        }
        HG hg = HG.k;
        GG gg = new GG();
        this.b = gg;
        return new C1051aG(this.a, gg);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final AbstractC0551Hu b() {
        return this.h;
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh, com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a() {
        return (this.b == null && this.c == null && !super.a()) ? false : true;
    }
}
