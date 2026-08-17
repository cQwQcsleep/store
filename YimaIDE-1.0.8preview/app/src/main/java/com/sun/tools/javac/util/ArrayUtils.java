package com.sun.tools.javac.util;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayUtils {
    private static int calculateNewLength(int i, int i2) {
        int i3;
        if (i2 == Integer.MAX_VALUE) {
            i2--;
        }
        do {
            i3 = i2 + 1;
            if (i >= i3) {
                return i;
            }
            i *= 2;
        } while (i > 0);
        return i3;
    }

    public static <T> T[] ensureCapacity(T[] tArr, int i) {
        if (i < 0) {
            qf1.a("maxIndex=", i);
            return null;
        }
        if (i < tArr.length) {
            return tArr;
        }
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), calculateNewLength(tArr.length, i)));
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        return tArr2;
    }

    public static byte[] ensureCapacity(byte[] bArr, int i) {
        if (i >= 0) {
            if (i < bArr.length) {
                return bArr;
            }
            byte[] bArr2 = new byte[calculateNewLength(bArr.length, i)];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }
        qf1.a("maxIndex=", i);
        return null;
    }

    public static char[] ensureCapacity(char[] cArr, int i) {
        if (i >= 0) {
            if (i < cArr.length) {
                return cArr;
            }
            char[] cArr2 = new char[calculateNewLength(cArr.length, i)];
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
            return cArr2;
        }
        qf1.a("maxIndex=", i);
        return null;
    }

    public static int[] ensureCapacity(int[] iArr, int i) {
        if (i >= 0) {
            if (i < iArr.length) {
                return iArr;
            }
            int[] iArr2 = new int[calculateNewLength(iArr.length, i)];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            return iArr2;
        }
        qf1.a("maxIndex=", i);
        return null;
    }
}
