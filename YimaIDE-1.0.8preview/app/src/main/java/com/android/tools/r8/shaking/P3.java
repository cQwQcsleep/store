package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C2986wz;
import com.android.tools.r8.internal.Wf0;
import defpackage.lua;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P3 extends K3 {
    public static final /* synthetic */ boolean d = true;
    public final String a;
    public final List b;
    public final K3.a c;

    public P3(T2.a aVar, K3.a aVar2) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar2;
    }

    public static boolean a(String str, int i, String str2, int i2, List list, int i3, K3.a aVar) {
        int length;
        int length2;
        int i4;
        String str3 = str;
        String str4 = str2;
        List list2 = list;
        K3.a aVar2 = aVar;
        int iIndexOf = i;
        int i5 = i2;
        int i6 = i3;
        while (iIndexOf < str3.length()) {
            char cCharAt = str3.charAt(iIndexOf);
            if (cCharAt == '*') {
                S3 s3 = (S3) list2.get(i6);
                if (!d && !s3.e()) {
                    x1f.a();
                    return false;
                }
                S3.b bVarB = s3.b();
                int i7 = iIndexOf + 1;
                boolean z = str3.length() > i7 && str3.charAt(i7) == '*';
                boolean z2 = z && str3.length() > (i4 = iIndexOf + 2) && str3.charAt(i4) == '*';
                if (z2) {
                    i7 = iIndexOf + 3;
                } else if (z) {
                    i7 = iIndexOf + 2;
                }
                int i8 = i7;
                if (i8 == str3.length()) {
                    bVarB.a(str4.substring(i5));
                    if (z2) {
                        return true;
                    }
                    if (z) {
                        return aVar2 == K3.a.b || (length2 = str4.length()) < 2 || str4.charAt(length2 + (-1)) != ']' || str4.charAt(length2 - 2) != '[';
                    }
                    return str4.indexOf(46, i5) == -1 && (aVar2 == K3.a.b || (length = str4.length()) < 2 || str4.charAt(length + (-1)) != ']' || str4.charAt(length - 2) != '[');
                }
                int i9 = i5;
                while (i9 < str4.length()) {
                    bVarB.a(str4.substring(i5, i9));
                    if (!z && str4.charAt(i9) == '.') {
                        return a(str3, i8, str4, i9, list2, i6 + 1, aVar2);
                    }
                    if (aVar2 == K3.a.c && str4.charAt(i9) == '[') {
                        return a(str, i8, str4, i9, list, i6 + 1, aVar2);
                    }
                    str4 = str2;
                    if (a(str, i8, str4, i9, list, i6 + 1, aVar)) {
                        return true;
                    }
                    i9++;
                    str3 = str;
                    list2 = list;
                    aVar2 = aVar;
                }
                bVarB.a(str4.substring(i5));
                return a(str, i8, str4, str4.length(), list, i6 + 1, aVar);
            }
            if (cCharAt == '<') {
                S3 s4 = (S3) list2.get(i6);
                if (!d && !s4.d()) {
                    x1f.a();
                    return false;
                }
                String strG = s4.a().g();
                if (strG == null || str4.length() < strG.length() + i5 || !strG.equals(str4.substring(i5, strG.length() + i5))) {
                    return false;
                }
                int length3 = strG.length() + i5;
                i6++;
                iIndexOf = str3.indexOf(">", iIndexOf);
                i5 = length3;
            } else {
                if (cCharAt != '?') {
                    if (i5 != str4.length()) {
                        int i10 = i5 + 1;
                        if (cCharAt == str4.charAt(i5)) {
                            i5 = i10;
                        }
                    }
                    return false;
                }
                S3 s5 = (S3) list2.get(i6);
                if (!d && !s5.e()) {
                    x1f.a();
                    return false;
                }
                if (i5 == str4.length() || str4.charAt(i5) == '.') {
                    return false;
                }
                S3.b bVarB2 = s5.b();
                int i11 = i5 + 1;
                String strSubstring = str4.substring(i5, i11);
                synchronized (bVarB2) {
                    bVarB2.b = strSubstring;
                }
                i6++;
                i5 = i11;
            }
            iIndexOf++;
        }
        return i5 == str2.length();
    }

    @Override // com.android.tools.r8.shaking.K3
    public final boolean b(com.android.tools.r8.graph.I2 i2) {
        boolean zA = a(this.a, 0, i2.m0(), 0, this.b, 0, this.c);
        if (!zA) {
            this.b.forEach(new lua());
        }
        return zA;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final Iterable c() {
        return this.b;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final boolean equals(Object obj) {
        if (obj instanceof P3) {
            P3 p3 = (P3) obj;
            if (this.c.equals(p3.c) && this.a.equals(p3.a)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final int hashCode() {
        return this.c.hashCode() + (this.a.hashCode() * 7);
    }

    @Override // com.android.tools.r8.shaking.K3
    public final String toString() {
        return this.a;
    }

    @Override // com.android.tools.r8.shaking.K3
    public final K3 a(com.android.tools.r8.graph.B1 b1) {
        C2986wz c2986wz = new C2986wz(16);
        ArrayList arrayList = new ArrayList();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            S3 s3F = ((S3) it.next()).f();
            if (s3F.d()) {
                S3.a aVarA = s3F.a();
                c2986wz.a(aVarA.b, aVarA.g());
            } else {
                arrayList.add(s3F);
            }
        }
        boolean zIsEmpty = c2986wz.isEmpty();
        String str = this.a;
        if (!zIsEmpty) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int i2 = 0;
            while (i < str.length()) {
                if (str.charAt(i) == '<') {
                    int i3 = i + 1;
                    int i4 = i3;
                    while (i4 < str.length() && str.charAt(i4) != '>') {
                        i4++;
                    }
                    if (i4 == str.length()) {
                        break;
                    }
                    String strSubstring = str.substring(i3, i4);
                    if (!strSubstring.isEmpty()) {
                        char[] cArr = Wf0.a;
                        int i5 = 0;
                        while (true) {
                            if (i5 < strSubstring.length()) {
                                if (!Character.isDigit(strSubstring.charAt(i5))) {
                                    break;
                                }
                                i5++;
                            } else {
                                String str2 = (String) c2986wz.get(Integer.valueOf(strSubstring).intValue());
                                if (str2 != null) {
                                    sb.append(str.substring(i2, i));
                                    sb.append(str2);
                                    i2 = i4 + 1;
                                    i = i4;
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
                i++;
            }
            if (!d && i != str.length()) {
                x1f.a();
                return null;
            }
            if (i2 < i) {
                sb.append(str.substring(i2));
            }
            String string = sb.toString();
            if (!string.contains("*")) {
                return new K3.b(b1.e(C0929Wj.I(string)));
            }
            return new P3(new T2.a(string, arrayList), this.c);
        }
        return new P3(new T2.a(str, arrayList), this.c);
    }
}
