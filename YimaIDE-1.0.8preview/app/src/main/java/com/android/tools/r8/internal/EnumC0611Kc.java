package com.android.tools.r8.internal;

import defpackage.x0g;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.Kc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC0611Kc {
    public static final EnumC0611Kc b = new EnumC0611Kc(0, "NONE");
    public static final EnumC0611Kc c = new EnumC0611Kc(1, "NAME");
    public static final EnumC0611Kc d = new EnumC0611Kc(2, "TYPE_NAME");
    public static final EnumC0611Kc e = new EnumC0611Kc(3, "CANONICAL_NAME");
    public static final EnumC0611Kc f = new EnumC0611Kc(4, "SIMPLE_NAME");
    public static final /* synthetic */ boolean g = true;

    public EnumC0611Kc(int i, String str) {
        super(str, i);
    }

    public final com.android.tools.r8.graph.H2 a(String str, com.android.tools.r8.graph.E0 e0, com.android.tools.r8.graph.B1 b1, int i) {
        String strK;
        int iOrdinal = ordinal();
        boolean z = true;
        if (iOrdinal == 1) {
            strK = C0929Wj.k(str);
            if (i > 0) {
                strK = "[".repeat(i) + "L" + strK + ";";
            }
        } else {
            if (iOrdinal == 2) {
                x0g.a("Type#getTypeName not supported yet");
                return null;
            }
            if (iOrdinal == 3) {
                strK = C0929Wj.h(str);
                if (i > 0) {
                    strK = strK + "[]".repeat(i);
                }
            } else {
                if (iOrdinal != 4) {
                    defpackage.gk0.a("Unexpected ClassNameMapping: ", this);
                    return null;
                }
                if (!g && e0 == null) {
                    x1f.a();
                    return null;
                }
                boolean zEquals = str.equals(e0.e.Z0());
                if (!e0.v1() && !e0.u1()) {
                    z = false;
                }
                strK = (zEquals && z) ? e0.S0().c().toString() : C0929Wj.w(str);
                if (i > 0) {
                    strK = strK + "[]".repeat(i);
                }
            }
        }
        return b1.c(strK);
    }
}
