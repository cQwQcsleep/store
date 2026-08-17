package com.android.tools.r8.shaking;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class A3 {
    public static final C3481x3 a = new C3481x3();
    public static final /* synthetic */ boolean b = true;

    public static boolean a(String str, int i, String str2, int i2, List list, int i3) {
        int length;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '*') {
                S3 s3 = (S3) list.get(i3);
                if (!b && !s3.e()) {
                    x1f.a();
                    return false;
                }
                S3.b bVarB = s3.b();
                int i4 = i2;
                while (i4 <= str2.length()) {
                    bVarB.a(str2.substring(i2, i4));
                    String str3 = str;
                    String str4 = str2;
                    List list2 = list;
                    if (a(str3, i + 1, str4, i4, list2, i3 + 1)) {
                        return true;
                    }
                    i4++;
                    str = str3;
                    str2 = str4;
                    list = list2;
                }
                return false;
            }
            if (cCharAt != '<') {
                if (cCharAt != '?') {
                    if (i2 != str2.length()) {
                        int i5 = i2 + 1;
                        if (cCharAt == str2.charAt(i2)) {
                            i2 = i5;
                        }
                    }
                    return false;
                }
                S3 s4 = (S3) list.get(i3);
                if (!b && !s4.e()) {
                    x1f.a();
                    return false;
                }
                if (i2 == str2.length()) {
                    return false;
                }
                S3.b bVarB2 = s4.b();
                length = i2 + 1;
                String strSubstring = str2.substring(i2, length);
                synchronized (bVarB2) {
                    bVarB2.b = strSubstring;
                }
                i3++;
                i++;
            } else {
                S3 s5 = (S3) list.get(i3);
                if (!b && !s5.d()) {
                    x1f.a();
                    return false;
                }
                String strG = s5.a().g();
                if (strG == null || str2.length() < strG.length() + i2 || !strG.equals(str2.substring(i2, strG.length() + i2))) {
                    return false;
                }
                length = strG.length() + i2;
                i3++;
                i = str.indexOf(">", i);
            }
            i2 = length;
            i++;
        }
        return i2 == str2.length();
    }

    public abstract boolean a(String str);

    public A3 b() {
        return this;
    }

    public Iterable a() {
        return new defpackage.s();
    }

    public static Iterable a(A3 a3) {
        return a3 == null ? new defpackage.s() : a3.a();
    }

    public static A3 a(T2.a aVar) {
        if (aVar.a.equals("*")) {
            return a;
        }
        if (aVar.b.isEmpty()) {
            return new C3491z3(aVar.a);
        }
        return new C3486y3(aVar);
    }
}
