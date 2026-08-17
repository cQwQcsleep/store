package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1679hg0 extends AbstractC1422eg0 {
    public static final int a(int i, CharSequence charSequence, String str, boolean z) {
        String str2;
        boolean z2;
        boolean zRegionMatches;
        KB.c(charSequence, "<this>");
        KB.c(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i);
        }
        int length = charSequence.length();
        if (i < 0) {
            i = 0;
        }
        int length2 = charSequence.length();
        if (length > length2) {
            length = length2;
        }
        C3092yA c3092yA = new C3092yA(i, length);
        boolean z3 = charSequence instanceof String;
        int i2 = c3092yA.c;
        if (!z3) {
            int i3 = c3092yA.d;
            if ((i3 <= 0 || i > i2) && (i3 >= 0 || i2 > i)) {
                return -1;
            }
            while (!a(str, charSequence, i, str.length(), z)) {
                if (i == i2) {
                    return -1;
                }
                i += i3;
            }
            return i;
        }
        int i4 = c3092yA.d;
        if ((i4 <= 0 || i > i2) && (i4 >= 0 || i2 > i)) {
            return -1;
        }
        int i5 = i;
        while (true) {
            String str3 = (String) charSequence;
            int length3 = str.length();
            if (z) {
                str2 = str;
                z2 = z;
                zRegionMatches = str2.regionMatches(z2, 0, str3, i5, length3);
            } else {
                zRegionMatches = str.regionMatches(0, str3, i5, length3);
                str2 = str;
                z2 = z;
            }
            if (zRegionMatches) {
                return i5;
            }
            if (i5 == i2) {
                return -1;
            }
            i5 += i4;
            str = str2;
            z = z2;
        }
    }

    public static final List b(int i, CharSequence charSequence, String str, boolean z) {
        if (i < 0) {
            b6c.a(CX.a(i, "Limit must be non-negative, but was "));
            return null;
        }
        int length = 0;
        int iA = a(0, charSequence, str, z);
        if (iA == -1 || i == 1) {
            List listSingletonList = Collections.singletonList(charSequence.toString());
            KB.b(listSingletonList, "singletonList(...)");
            return listSingletonList;
        }
        boolean z2 = i > 0;
        int i2 = 10;
        if (z2 && i <= 10) {
            i2 = i;
        }
        ArrayList arrayList = new ArrayList(i2);
        do {
            arrayList.add(charSequence.subSequence(length, iA).toString());
            length = str.length() + iA;
            if (z2 && arrayList.size() == i - 1) {
                break;
            }
            iA = a(length, charSequence, str, z);
        } while (iA != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static String b(String str, char c) {
        KB.c(str, "<this>");
        KB.c(str, "missingDelimiterValue");
        int iA = a((CharSequence) str, c, false, 6);
        if (iA == -1) {
            return str;
        }
        String strSubstring = str.substring(0, iA);
        KB.b(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String b(String str, String str2) {
        KB.c(str, "<this>");
        KB.c(str, "missingDelimiterValue");
        int iA = a((CharSequence) str, str2, 0, false, 6);
        if (iA == -1) {
            return str;
        }
        String strSubstring = str.substring(str2.length() + iA, str.length());
        KB.b(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static int a(CharSequence charSequence, char c, boolean z, int i) {
        if ((i & 4) != 0) {
            z = false;
        }
        KB.c(charSequence, "<this>");
        return (z || !(charSequence instanceof String)) ? a(charSequence, new char[]{c}, 0, z) : ((String) charSequence).indexOf(c, 0);
    }

    public static String a(String str, char c, char c2) {
        KB.c(str, "<this>");
        String strReplace = str.replace(c, c2);
        KB.b(strReplace, "replace(...)");
        return strReplace;
    }

    public static List a(CharSequence charSequence, char[] cArr, int i) {
        KB.c(charSequence, "<this>");
        if (cArr.length == 1) {
            return b(i, charSequence, String.valueOf(cArr[0]), false);
        }
        if (i >= 0) {
            C1937ki<C3092yA> c1937ki = new C1937ki(charSequence, 0, i, new C1508fg0(cArr, false));
            ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) new C1243cb0(c1937ki)));
            for (C3092yA c3092yA : c1937ki) {
                KB.c(c3092yA, "range");
                arrayList.add(charSequence.subSequence(c3092yA.b, c3092yA.c + 1).toString());
            }
            return arrayList;
        }
        b6c.a(CX.a(i, "Limit must be non-negative, but was "));
        return null;
    }

    public static boolean a(String str, String str2) {
        KB.c(str, "<this>");
        return str.startsWith(str2);
    }

    public static String a(String str, char c) {
        KB.c(str, "<this>");
        KB.c(str, "missingDelimiterValue");
        int iA = a((CharSequence) str, c);
        if (iA == -1) {
            return str;
        }
        String strSubstring = str.substring(iA + 1, str.length());
        KB.b(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final int a(CharSequence charSequence) {
        KB.c(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static String a(String str, char c, String str2) {
        KB.c(str, "<this>");
        KB.c(str2, "missingDelimiterValue");
        int iA = a((CharSequence) str, c);
        if (iA == -1) {
            return str2;
        }
        String strSubstring = str.substring(0, iA);
        KB.b(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final boolean a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z) {
        KB.c(charSequence, "<this>");
        KB.c(charSequence2, "other");
        if (i < 0 || charSequence.length() - i2 < 0 || i > charSequence2.length() - i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (!AbstractC3036xb.a(charSequence.charAt(i3), charSequence2.charAt(i + i3), z)) {
                return false;
            }
        }
        return true;
    }

    public static final int a(CharSequence charSequence, char[] cArr, int i, boolean z) {
        int i2;
        KB.c(charSequence, "<this>");
        boolean z2 = true;
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            int length = cArr.length;
            if (length == 0) {
                hb9.a("Array is empty.");
                return 0;
            }
            if (length == 1) {
                return ((String) charSequence).indexOf(cArr[0], i);
            }
            w01.a("Array has more than one element.");
            return 0;
        }
        if (i < 0) {
            i = 0;
        }
        C3092yA c3092yA = new C3092yA(i, a(charSequence));
        int i3 = c3092yA.c;
        int i4 = c3092yA.d;
        if (i4 <= 0 ? i < i3 : i > i3) {
            z2 = false;
        }
        if (!z2) {
            i = i3;
        }
        while (z2) {
            if (i != i3) {
                i2 = i + i4;
            } else {
                if (!z2) {
                    z0e.a();
                    return 0;
                }
                i2 = i;
                z2 = false;
            }
            char cCharAt = charSequence.charAt(i);
            for (char c : cArr) {
                if (AbstractC3036xb.a(c, cCharAt, z)) {
                    return i;
                }
            }
            i = i2;
        }
        return -1;
    }

    public static /* synthetic */ int a(CharSequence charSequence, String str, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return a(i, charSequence, str, z);
    }

    public static boolean a(CharSequence charSequence, String str) {
        KB.c(charSequence, "<this>");
        return a(charSequence, str, 0, false, 2) >= 0;
    }

    public static int a(CharSequence charSequence, char c) {
        int iA = a(charSequence);
        KB.c(charSequence, "<this>");
        boolean z = charSequence instanceof String;
        if (!z) {
            char[] cArr = {c};
            if (z) {
                return ((String) charSequence).lastIndexOf(cArr[0], iA);
            }
            int iA2 = a(charSequence);
            if (iA > iA2) {
                iA = iA2;
            }
            while (-1 < iA) {
                if (AbstractC3036xb.a(cArr[0], charSequence.charAt(iA), false)) {
                    return iA;
                }
                iA--;
            }
            return -1;
        }
        return ((String) charSequence).lastIndexOf(c, iA);
    }

    public static List a(String str, String[] strArr, int i, int i2) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        if (strArr.length == 1) {
            String str2 = strArr[0];
            if (str2.length() != 0) {
                return b(i, str, str2, false);
            }
        }
        if (i >= 0) {
            C1937ki<C3092yA> c1937ki = new C1937ki(str, 0, i, new C1593gg0(T3.a(strArr), false));
            ArrayList arrayList = new ArrayList(AbstractC2015le.a((Iterable) new C1243cb0(c1937ki)));
            for (C3092yA c3092yA : c1937ki) {
                KB.c(c3092yA, "range");
                arrayList.add(str.subSequence(c3092yA.b, c3092yA.c + 1).toString());
            }
            return arrayList;
        }
        b6c.a(CX.a(i, "Limit must be non-negative, but was "));
        return null;
    }

    public static CharSequence a(String str) {
        KB.c(str, "<this>");
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            char cCharAt = str.charAt(!z ? i : length);
            boolean z2 = Character.isWhitespace(cCharAt) || Character.isSpaceChar(cCharAt);
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return str.subSequence(i, length + 1);
    }
}
