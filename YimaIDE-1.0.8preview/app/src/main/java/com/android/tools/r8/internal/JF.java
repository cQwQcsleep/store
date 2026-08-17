package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class JF extends AbstractC0927Wh {
    public final AbstractC3114yW a;
    public final C0688Nc b;
    public final Hf0 c;
    public final C0624Kp d;
    public C1816jG e = null;
    public C2073mG f = null;
    public final AbstractC0551Hu g;

    public JF(AbstractC3114yW abstractC3114yW) {
        this.a = abstractC3114yW;
        abstractC3114yW.getClass();
        C0688Nc c0688Nc = new C0688Nc(new C2773uW(abstractC3114yW, "field-annotated-by"));
        this.b = c0688Nc;
        c0688Nc.a("fieldAnnotatedByClassName", EnumC0662Mc.c);
        c0688Nc.a("fieldAnnotatedByClassConstant", EnumC0662Mc.d);
        c0688Nc.a("fieldAnnotatedByClassNamePattern", EnumC0662Mc.b);
        Hf0 hf0 = new Hf0(new C2773uW(abstractC3114yW, "field-name"));
        this.c = hf0;
        hf0.a("fieldName", Gf0.b);
        hf0.a("fieldNamePattern", Gf0.c);
        C0624Kp c0624Kp = new C0624Kp(new C2773uW(abstractC3114yW, "field-type"));
        this.d = c0624Kp;
        c0624Kp.a.a("fieldTypePattern", EnumC2881vj0.b);
        c0624Kp.a.a("fieldType", EnumC2881vj0.c);
        c0624Kp.a.a("fieldTypeConstant", EnumC2881vj0.d);
        this.g = AbstractC0551Hu.a(c0688Nc, hf0, c0624Kp);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final J2 a(String str) {
        if (!str.equals("fieldAccess")) {
            return super.a(str);
        }
        C1901kG c1901kG = C1901kG.h;
        C1816jG c1816jG = new C1816jG();
        this.e = c1816jG;
        return new IF(this.a, c1816jG);
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh
    public final AbstractC0551Hu b() {
        return this.g;
    }

    @Override // com.android.tools.r8.internal.AbstractC0927Wh, com.android.tools.r8.internal.InterfaceC2260oW
    public final boolean a() {
        return (this.e == null && this.f == null && !super.a()) ? false : true;
    }
}
