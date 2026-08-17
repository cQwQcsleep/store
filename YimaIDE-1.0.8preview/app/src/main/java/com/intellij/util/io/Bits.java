package com.intellij.util.io;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class Bits {
    public static int getInt(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) + ((bArr[i + 2] & 255) << 8) + ((bArr[i + 1] & 255) << 16) + ((bArr[i] & 255) << 24);
    }

    public static long getLong(byte[] bArr, int i) {
        return (((long) bArr[i + 7]) & 255) + ((((long) bArr[i + 6]) & 255) << 8) + ((((long) bArr[i + 5]) & 255) << 16) + ((((long) bArr[i + 4]) & 255) << 24) + ((((long) bArr[i + 3]) & 255) << 32) + ((((long) bArr[i + 2]) & 255) << 40) + ((((long) bArr[i + 1]) & 255) << 48) + ((((long) bArr[i]) & 255) << 56);
    }

    public static void putInt(byte[] bArr, int i, int i2) {
        bArr[i + 3] = (byte) i2;
        bArr[i + 2] = (byte) (i2 >>> 8);
        bArr[i + 1] = (byte) (i2 >>> 16);
        bArr[i] = (byte) (i2 >>> 24);
    }

    public static void putLong(byte[] bArr, int i, long j) {
        bArr[i + 7] = (byte) j;
        bArr[i + 6] = (byte) (j >>> 8);
        bArr[i + 5] = (byte) (j >>> 16);
        bArr[i + 4] = (byte) (j >>> 24);
        bArr[i + 3] = (byte) (j >>> 32);
        bArr[i + 2] = (byte) (j >>> 40);
        bArr[i + 1] = (byte) (j >>> 48);
        bArr[i] = (byte) (j >>> 56);
    }
}
