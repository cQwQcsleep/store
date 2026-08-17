package com.android.tools.r8.shaking;

import defpackage.le6;

/* JADX WARN: Enum visitor error
java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.nodes.MethodNode.getBasicBlocks()" is null
	at jadx.core.dex.visitors.EnumVisitor.searchEnumSuperCtrInsn(EnumVisitor.java:495)
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:473)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:422)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:351)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class O2 {
    public static final O2 b;
    public static final O2 c;
    public static final L2 d;
    public static final M2 e;
    public static final /* synthetic */ O2[] f;

    static {
        O2 o2 = new O2() { // from class: com.android.tools.r8.shaking.J2
            @Override // com.android.tools.r8.shaking.O2
            public final boolean a(com.android.tools.r8.graph.E0 e0) {
                return e0.f.L() && e0.f.J();
            }
        };
        b = o2;
        O2 o3 = new O2() { // from class: com.android.tools.r8.shaking.K2
            @Override // com.android.tools.r8.shaking.O2
            public final boolean a(com.android.tools.r8.graph.E0 e0) {
                return true;
            }
        };
        c = o3;
        L2 l2 = new L2();
        d = l2;
        M2 m2 = new M2();
        e = m2;
        f = new O2[]{o2, o3, l2, m2};
    }

    public O2(int i, String str) {
        super(str, i);
    }

    public abstract boolean a(com.android.tools.r8.graph.E0 e0);

    @Override // java.lang.Enum
    public final String toString() {
        int i = N2.a[ordinal()];
        if (i == 1) {
            return "@interface";
        }
        if (i == 2) {
            return "class";
        }
        if (i == 3) {
            return "enum";
        }
        if (i == 4) {
            return "interface";
        }
        le6.a("Invalid proguard class type '", this, "'");
        return null;
    }
}
