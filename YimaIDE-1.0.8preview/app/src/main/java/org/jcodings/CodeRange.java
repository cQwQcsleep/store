package org.jcodings;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class CodeRange {
    public static boolean isInCodeRange(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        int i4 = i3;
        int i5 = 0;
        while (i5 < i4) {
            int i6 = (i5 + i4) >> 1;
            if (i2 > iArr[(i6 << 1) + 2 + i]) {
                i5 = i6 + 1;
            } else {
                i4 = i6;
            }
        }
        return i5 < i3 && i2 >= iArr[((i5 << 1) + 1) + i];
    }

    public static boolean isInCodeRange(int[] iArr, int i) {
        return isInCodeRange(iArr, 0, i);
    }
}
