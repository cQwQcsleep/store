package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Db0 {
    public static int a(String str, int i, Ph0 ph0) {
        int i2;
        int iA = i + 1;
        char cCharAt = str.charAt(i);
        if (cCharAt != 'F') {
            if (cCharAt == 'L') {
                int i3 = iA;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    i2 = iA + 1;
                    char cCharAt2 = str.charAt(iA);
                    if (cCharAt2 == '.' || cCharAt2 == ';') {
                        if (!z) {
                            String strSubstring = str.substring(i3, iA);
                            if (z2) {
                                ph0.b(strSubstring);
                            } else {
                                ph0.a(strSubstring);
                            }
                        }
                        if (cCharAt2 == ';') {
                            break;
                        }
                        z = false;
                        z2 = true;
                        i3 = i2;
                        iA = i3;
                    } else if (cCharAt2 == '<') {
                        String strSubstring2 = str.substring(i3, iA);
                        if (z2) {
                            ph0.b(strSubstring2);
                        } else {
                            ph0.a(strSubstring2);
                        }
                        iA = i2;
                        while (true) {
                            char cCharAt3 = str.charAt(iA);
                            if (cCharAt3 == '>') {
                                break;
                            }
                            if (cCharAt3 != '*') {
                                iA = (cCharAt3 == '+' || cCharAt3 == '-') ? a(str, iA + 1, ph0.a(cCharAt3)) : a(str, iA, ph0.a('='));
                            } else {
                                iA++;
                                int i4 = ph0.i;
                                if (i4 % 2 == 0) {
                                    ph0.i = i4 + 1;
                                    ph0.b.append('<');
                                } else {
                                    ph0.b.append(", ");
                                }
                                ph0.b.append('?');
                            }
                        }
                        z = true;
                    } else {
                        iA = i2;
                    }
                }
                if (ph0.i % 2 != 0) {
                    ph0.b.append('>');
                }
                ph0.i /= 2;
                ph0.a();
                return i2;
            }
            if (cCharAt != 'V' && cCharAt != 'I' && cCharAt != 'J' && cCharAt != 'S') {
                if (cCharAt == 'T') {
                    int iIndexOf = str.indexOf(59, iA);
                    String strSubstring3 = str.substring(iA, iIndexOf);
                    StringBuilder sb = ph0.b;
                    sb.append(ph0.k);
                    sb.append(strSubstring3);
                    ph0.k = XmlPullParser.NO_NAMESPACE;
                    ph0.a();
                    return iIndexOf + 1;
                }
                if (cCharAt != 'Z') {
                    if (cCharAt == '[') {
                        ph0.j = (ph0.j * 2) | 1;
                        return a(str, iA, ph0);
                    }
                    switch (cCharAt) {
                        case 'B':
                        case 'C':
                        case 'D':
                            break;
                        default:
                            j2d.a();
                            return 0;
                    }
                }
            }
        }
        String str2 = (String) Ph0.l.get(Character.valueOf(cCharAt));
        if (str2 == null) {
            j2d.a();
            return 0;
        }
        ph0.b.append(str2);
        ph0.a();
        return iA;
    }
}
