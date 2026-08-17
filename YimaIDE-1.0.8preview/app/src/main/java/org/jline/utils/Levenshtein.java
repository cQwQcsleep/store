package org.jline.utils;

import java.lang.reflect.Array;
import java.util.HashMap;
import org.joni.CodeRangeBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class Levenshtein {
    public static int distance(CharSequence charSequence, CharSequence charSequence2, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i2 + i;
        if (i4 * 2 < i7) {
            w01.a("Unsupported cost assignment");
            return 0;
        }
        if (charSequence.length() == 0) {
            return charSequence2.length() * i2;
        }
        if (charSequence2.length() == 0) {
            return charSequence.length() * i;
        }
        int i8 = 1;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, charSequence.length(), charSequence2.length());
        HashMap map = new HashMap();
        if (charSequence.charAt(0) != charSequence2.charAt(0)) {
            iArr[0][0] = Math.min(i3, i7);
        }
        map.put(Character.valueOf(charSequence.charAt(0)), 0);
        int i9 = 1;
        while (i9 < charSequence.length()) {
            int i10 = i9 + 1;
            iArr[i9][0] = Math.min(Math.min(iArr[i9 - 1][0] + i, (i10 * i) + i2), (i9 * i) + (charSequence.charAt(i9) == charSequence2.charAt(0) ? 0 : i3));
            i9 = i10;
        }
        int i11 = 1;
        while (i11 < charSequence2.length()) {
            int i12 = i11 + 1;
            iArr[0][i11] = Math.min(Math.min((i12 * i2) + i, iArr[0][i11 - 1] + i2), (i11 * i2) + (charSequence.charAt(0) == charSequence2.charAt(i11) ? 0 : i3));
            i11 = i12;
        }
        int i13 = 1;
        while (i13 < charSequence.length()) {
            int i14 = -1;
            int i15 = charSequence.charAt(i13) == charSequence2.charAt(0) ? 0 : -1;
            int i16 = i8;
            while (i16 < charSequence2.length()) {
                Integer num = (Integer) map.get(Character.valueOf(charSequence2.charAt(i16)));
                int[] iArr2 = iArr[i13 - 1];
                int i17 = iArr2[i16] + i;
                int i18 = i16 - 1;
                int i19 = iArr[i13][i18] + i2;
                int i20 = iArr2[i18];
                int i21 = i8;
                if (charSequence.charAt(i13) != charSequence2.charAt(i16)) {
                    i20 += i3;
                    i5 = i15;
                } else {
                    i5 = i16;
                }
                if (num == null || i15 == i14) {
                    i6 = CodeRangeBuffer.LAST_CODE_POINT;
                } else {
                    int iIntValue = num.intValue();
                    i6 = ((iIntValue == 0 && i15 == 0) ? 0 : iArr[Math.max(0, iIntValue - 1)][Math.max(0, i15 - 1)]) + (((i13 - iIntValue) - 1) * i) + (((i16 - i15) - 1) * i2) + i4;
                }
                iArr[i13][i16] = Math.min(Math.min(Math.min(i17, i19), i20), i6);
                i16++;
                i15 = i5;
                i8 = i21;
                i14 = -1;
            }
            map.put(Character.valueOf(charSequence.charAt(i13)), Integer.valueOf(i13));
            i13++;
            i8 = i8;
        }
        return iArr[charSequence.length() - 1][charSequence2.length() - 1];
    }

    public static int distance(CharSequence charSequence, CharSequence charSequence2) {
        return distance(charSequence, charSequence2, 1, 1, 1, 1);
    }
}
