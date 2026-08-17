package com.sun.tools.javac.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringUtils {
    public static int indexOfIgnoreCase(String str, String str2, int i) {
        Matcher matcher = Pattern.compile(Pattern.quote(str2), 2).matcher(str);
        if (matcher.find(i)) {
            return matcher.start();
        }
        return -1;
    }

    public static String toLowerCase(String str) {
        return str.toLowerCase(Locale.US);
    }

    public static String toUpperCase(String str) {
        return str.toUpperCase(Locale.US);
    }

    public static final class DamerauLevenshteinDistance {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int Wc = 1;
        private static final int Wd = 1;
        private static final int Wi = 1;
        private static final int Ws = 1;
        private final int INF;
        private final String a;
        private final String b;
        private Map<Character, Integer> bigDA;
        private final int[][] h;
        private int[] smallDA;

        private DamerauLevenshteinDistance(String str, String str2) {
            this.a = str;
            this.b = str2;
            this.h = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, str.length() + 2, str2.length() + 2);
            int length = str.length() + str2.length() + 1;
            this.INF = length;
            if (length >= 0) {
                return;
            }
            pv9.a("Overflow");
            throw null;
        }

        private int calculate() {
            for (int i = 0; i <= this.a.length(); i++) {
                h(i, 0, i);
                h(i, -1, this.INF);
            }
            for (int i2 = 0; i2 <= this.b.length(); i2++) {
                h(0, i2, i2);
                h(-1, i2, this.INF);
            }
            for (int i3 = 1; i3 <= this.a.length(); i3++) {
                int i4 = 0;
                int i5 = 1;
                while (i5 <= this.b.length()) {
                    int iDa = da(characterAt(this.b, i5));
                    int i6 = characterAt(this.a, i3) == characterAt(this.b, i5) ? 1 : 0;
                    int i7 = i6 ^ 1;
                    int i8 = i6 != 0 ? i5 : i4;
                    int i9 = i3 - 1;
                    int i10 = i5 - 1;
                    h(i3, i5, min(h(i9, i10) + i7, h(i3, i10) + 1, h(i9, i5) + 1, h(iDa - 1, i4 - 1) + ((i3 - iDa) - 1) + 1 + ((i5 - i4) - 1)));
                    i5++;
                    i4 = i8;
                }
                da(characterAt(this.a, i3), i3);
            }
            return h(this.a.length(), this.b.length());
        }

        private int characterAt(String str, int i) {
            return str.charAt(i - 1);
        }

        private void da(int i, int i2) {
            Map<Character, Integer> map = this.bigDA;
            if (map == null && i < 128) {
                if (this.smallDA == null) {
                    this.smallDA = new int[127];
                }
                this.smallDA[i] = i2;
                return;
            }
            if (map == null) {
                this.bigDA = new HashMap();
                if (this.smallDA != null) {
                    int i3 = 0;
                    while (true) {
                        int[] iArr = this.smallDA;
                        if (i3 >= iArr.length) {
                            break;
                        }
                        int i4 = iArr[i3];
                        if (i4 != 0) {
                            this.bigDA.put(Character.valueOf((char) i3), Integer.valueOf(i4));
                        }
                        i3++;
                    }
                    this.smallDA = null;
                }
            }
            this.bigDA.put(Character.valueOf((char) i), Integer.valueOf(i2));
        }

        private void h(int i, int i2, int i3) {
            this.h[i + 1][i2 + 1] = i3;
        }

        private static int min(int i, int i2, int i3, int i4) {
            return Math.min(i, Math.min(i2, Math.min(i3, i4)));
        }

        public static int of(String str, String str2) {
            return new DamerauLevenshteinDistance(str, str2).calculate();
        }

        private int h(int i, int i2) {
            return this.h[i + 1][i2 + 1];
        }

        private int da(int i) {
            Integer num;
            int[] iArr = this.smallDA;
            if (iArr != null && i < 128) {
                return iArr[i];
            }
            Map<Character, Integer> map = this.bigDA;
            if (map == null || (num = map.get(Character.valueOf((char) i))) == null) {
                return 0;
            }
            return num.intValue();
        }
    }

    public static int indexOfIgnoreCase(String str, String str2) {
        return indexOfIgnoreCase(str, str2, 0);
    }
}
