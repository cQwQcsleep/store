package com.reandroid.arsc.value;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocaleUtil {
    public static boolean contains(long[] jArr, long j) {
        for (long j2 : jArr) {
            if (j2 == j) {
                return true;
            }
        }
        return false;
    }

    public static int count(long[] jArr, long j) {
        int i = 0;
        for (long j2 : jArr) {
            if (j2 == j) {
                i++;
            }
        }
        return i;
    }

    public static int[] find(int[][] iArr, int i) {
        for (int[] iArr2 : iArr) {
            if (iArr2[0] == i) {
                return iArr2;
            }
        }
        return null;
    }

    public static void memcpy(char[] cArr, byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = (char) bArr[i2];
        }
    }

    public static void memset(char[] cArr, char c, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = c;
        }
    }
}
