package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2157nF extends K2 {
    public final InterfaceC1221cG c;
    public final C1815jF d;

    public C2157nF(C3030xW c3030xW, InterfaceC1221cG interfaceC1221cG) {
        super(c3030xW);
        this.d = new C1815jF();
        this.c = interfaceC1221cG;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final void a(String str, String str2, String str3) {
        if (!str2.equals("Lcom/android/tools/r8/keepanno/annotations/KeepConstraint;")) {
            super.a(str, str2, str3);
            throw null;
        }
        str3.getClass();
        switch (str3) {
            case "LOOKUP":
                C1815jF c1815jF = this.d;
                c1815jF.b.add(ZE.a);
                return;
            case "CLASS_OPEN_HIERARCHY":
                C1815jF c1815jF2 = this.d;
                c1815jF2.b.add(VE.a);
                return;
            case "METHOD_INVOKE":
                C1815jF c1815jF3 = this.d;
                c1815jF3.b.add(C1050aF.a);
                return;
            case "NEVER_INLINE":
                C1815jF c1815jF4 = this.d;
                c1815jF4.b.add(C1304dF.a);
                return;
            case "FIELD_GET":
                C1815jF c1815jF5 = this.d;
                c1815jF5.b.add(WE.a);
                return;
            case "FIELD_SET":
                C1815jF c1815jF6 = this.d;
                c1815jF6.b.add(YE.a);
                return;
            case "NAME":
                C1815jF c1815jF7 = this.d;
                c1815jF7.b.add(C1220cF.a);
                return;
            case "VISIBILITY_RELAX":
                C1815jF c1815jF8 = this.d;
                c1815jF8.b.add(C1388eF.a);
                return;
            case "VISIBILITY_RESTRICT":
                C1815jF c1815jF9 = this.d;
                c1815jF9.b.add(C1474fF.a);
                return;
            case "VISIBILITY_INVARIANT":
                C1815jF c1815jF10 = this.d;
                c1815jF10.b.add(C1388eF.a);
                C1815jF c1815jF11 = this.d;
                c1815jF11.b.add(C1474fF.a);
                return;
            case "CLASS_INSTANTIATE":
                C1815jF c1815jF12 = this.d;
                c1815jF12.b.add(UE.a);
                return;
            case "METHOD_REPLACE":
                C1815jF c1815jF13 = this.d;
                c1815jF13.b.add(C1136bF.a);
                return;
            case "FIELD_REPLACE":
                C1815jF c1815jF14 = this.d;
                c1815jF14.b.add(XE.a);
                return;
            default:
                super.a(str, str2, str3);
                throw null;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.android.tools.r8.internal.J2
    public final void a() {
        InterfaceC1221cG interfaceC1221cG = this.c;
        C1815jF c1815jF = this.d;
        C1900kF c1900kF = new C1900kF(c1815jF.b);
        Object c1731iF = c1900kF;
        if (c1815jF.a) {
            c1731iF = new C1731iF(c1900kF);
        }
        interfaceC1221cG.accept(c1731iF);
        super.a();
    }
}
