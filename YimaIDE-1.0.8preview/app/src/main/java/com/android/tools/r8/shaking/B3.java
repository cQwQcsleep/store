package com.android.tools.r8.shaking;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class B3 {
    public final String a;

    public B3(String str) {
        this.a = str;
    }

    public static boolean a(int i, int i2, String str, String str2) {
        while (true) {
            if (i >= str.length()) {
                return i2 == str2.length();
            }
            char cCharAt = str.charAt(i);
            if (cCharAt == '*') {
                int i3 = i + 1;
                boolean z = str.length() > i3 && str.charAt(i3) == '*';
                if (z) {
                    i3 = i + 2;
                }
                if (i3 == str.length()) {
                    if (z) {
                        return true;
                    }
                    return !(str2.indexOf(46, i2) != -1);
                }
                while (i2 < str2.length()) {
                    if (!z && str2.charAt(i2) == '.') {
                        return a(i3, i2, str, str2);
                    }
                    if (a(i3, i2, str, str2)) {
                        return true;
                    }
                    i2++;
                }
                return a(i3, str2.length(), str, str2);
            }
            if (cCharAt != '?') {
                if (i2 != str2.length()) {
                    int i4 = i2 + 1;
                    if (cCharAt == str2.charAt(i2)) {
                        i2 = i4;
                    }
                }
                return false;
            }
            if (i2 == str2.length() || str2.charAt(i2) == '.') {
                return false;
            }
            i2++;
            i++;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof B3) {
            return this.a.equals(((B3) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
