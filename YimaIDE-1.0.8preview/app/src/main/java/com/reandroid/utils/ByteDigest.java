package com.reandroid.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ByteDigest {
    private byte[] oneByte;

    public static void fillZero(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = 0;
        }
    }

    public static int getBigEndianInteger(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) | (bArr[i + 3] & 255) | ((bArr[i + 2] & 255) << 8) | ((bArr[i + 1] & 255) << 16);
    }

    public static void putBigEndianInteger(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >>> 24);
        bArr[i + 1] = (byte) (i2 >>> 16);
        bArr[i + 2] = (byte) (i2 >>> 8);
        bArr[i + 3] = (byte) (i2 & 255);
    }

    public abstract void digest(byte[] bArr, int i);

    public byte[] digest() {
        byte[] bArr = new byte[getDigestLength()];
        digest(bArr, 0);
        return bArr;
    }

    public abstract int getDigestLength();

    public abstract void reset();

    public void update(byte b) {
        byte[] bArr = this.oneByte;
        if (bArr == null) {
            bArr = new byte[1];
            this.oneByte = bArr;
        }
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    public abstract void update(byte[] bArr, int i, int i2);

    public static void fillZero(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = 0;
        }
    }

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }
}
