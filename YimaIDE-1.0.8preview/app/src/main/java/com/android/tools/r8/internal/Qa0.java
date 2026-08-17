package com.android.tools.r8.internal;

import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Qa0 {
    public static final AbstractC0551Hu a = AbstractC0551Hu.a(".png", ".9.png", ".gif", ".jpeg", ".jpg", ".bmp", ".webp", ".avif");

    public static String a(String str) {
        StringBuilder sb = new StringBuilder(str.length() * 2);
        sb.append('^');
        int length = str.length();
        int i = 0;
        int iA = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '*') {
                int iA2 = a(sb, str, iA, i);
                int i2 = iA2 + 1;
                if (i < length - 1) {
                    int i3 = i + 1;
                    if (str.charAt(i3) == '*') {
                        i2 = iA2 + 2;
                        i = i3;
                    }
                }
                sb.append(".*?");
                iA = i2;
            } else if (cCharAt == '?') {
                iA = a(sb, str, iA, i) + 1;
                sb.append(".?");
            }
            i++;
        }
        a(sb, str, iA, str.length());
        sb.append('$');
        return sb.toString();
    }

    public static int a(StringBuilder sb, String str, int i, int i2) {
        if (i2 > i) {
            for (int i3 = i; i3 < i2; i3++) {
                char cCharAt = str.charAt(i3);
                if (!Character.isLetterOrDigit(cCharAt) && cCharAt != '/' && cCharAt != ' ') {
                    sb.append(Pattern.quote(str.substring(i, i2)));
                    return i2;
                }
            }
            while (i < i2) {
                sb.append(str.charAt(i));
                i++;
            }
        }
        return i2;
    }
}
