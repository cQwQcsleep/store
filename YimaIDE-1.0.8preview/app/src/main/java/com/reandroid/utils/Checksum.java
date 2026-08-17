package com.reandroid.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Checksum {
    private byte[] oneByte;

    public abstract long getValue();

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

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }
}
