package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0900Vg {
    public static final /* synthetic */ boolean d = true;
    public final String a;
    public final String b;
    public final String c;

    public C0900Vg(String str) {
        this.a = str;
        this.b = null;
        this.c = null;
    }

    public final C2585sF a(C2585sF c2585sF) {
        String str;
        String str2;
        String str3;
        String str4 = this.c;
        if (str4 == null) {
            String str5 = this.a;
            c2585sF.getClass();
            c2585sF.a = new C2670tF(str5);
            return c2585sF;
        }
        if (str4.charAt(0) == '(') {
            String str6 = this.a;
            String str7 = this.b;
            if (!d && ((str3 = this.c) == null || str3.charAt(0) != '(')) {
                x1f.a();
                return null;
            }
            String str8 = this.c;
            c2585sF.getClass();
            c2585sF.a = new C3013xF(str6, str7, str8);
            return c2585sF;
        }
        boolean z = d;
        if (!z && ((str2 = this.c) == null || str2.charAt(0) != ':')) {
            x1f.a();
            return null;
        }
        String str9 = this.a;
        String str10 = this.b;
        if (!z && ((str = this.c) == null || str.charAt(0) != ':')) {
            x1f.a();
            return null;
        }
        String strSubstring = this.c.substring(1);
        c2585sF.getClass();
        c2585sF.a = new C2927wF(str9, str10, strSubstring);
        return c2585sF;
    }

    public C0900Vg(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public static C0900Vg a(String str, C3030xW c3030xW) {
        int iIndexOf = str.indexOf(59) + 1;
        if (iIndexOf > 0) {
            String strSubstring = str.substring(0, iIndexOf);
            if (iIndexOf == str.length()) {
                return new C0900Vg(strSubstring);
            }
            int iIndexOf2 = str.indexOf(40, iIndexOf);
            if (iIndexOf2 < 0) {
                iIndexOf2 = str.indexOf(58, iIndexOf);
            }
            if (iIndexOf2 >= 0) {
                return new C0900Vg(strSubstring, str.substring(iIndexOf, iIndexOf2), str.substring(iIndexOf2));
            }
            c3030xW.a("Invalid descriptor: ".concat(str));
            throw null;
        }
        c3030xW.a("Invalid descriptor: ".concat(str));
        throw null;
    }
}
