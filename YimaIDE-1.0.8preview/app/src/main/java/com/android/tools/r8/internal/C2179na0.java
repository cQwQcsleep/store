package com.android.tools.r8.internal;

import defpackage.f44;

/* JADX INFO: renamed from: com.android.tools.r8.internal.na0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2179na0 {
    public static final /* synthetic */ boolean b = true;
    public final int a;

    public C2179na0(int i) {
        this.a = i;
    }

    public static C2179na0 a(AbstractC1643hD abstractC1643hD) {
        String strG = abstractC1643hD.g();
        int iIndexOf = strG.indexOf("(");
        int iIndexOf2 = strG.indexOf(")");
        if (iIndexOf2 <= iIndexOf) {
            throw new C1345dk0("Unexpected action: ".concat(strG));
        }
        String strSubstring = strG.substring(0, iIndexOf);
        String strSubstring2 = strG.substring(iIndexOf + 1, iIndexOf2);
        if ("removeInnerFrames".equals(strSubstring)) {
            return a(strSubstring2);
        }
        if (b) {
            throw new C1345dk0("Unexpected action: ".concat(strSubstring));
        }
        x01.a("Unknown function ".concat(strSubstring));
        return null;
    }

    public final C2155nD a() {
        return new C2155nD("removeInnerFrames(" + this.a + ")");
    }

    public static C2179na0 a(String str) {
        try {
            return new C2179na0(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            f44.a("Unexpected number for removeInnerFrames: ", str);
            return null;
        }
    }
}
