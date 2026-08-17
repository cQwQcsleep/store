package com.reandroid.utils;

import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class Crc32OutputStream extends OutputStream {
    private final Crc32 crc32 = new Crc32();

    public long getLength() {
        return this.crc32.getLength();
    }

    public long getValue() {
        return this.crc32.getValue();
    }

    public String toString() {
        return HexUtil.toHex((String) null, getValue(), 8);
    }

    public void update(byte[] bArr, int i, int i2) {
        write(bArr, i, i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.crc32.update(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.crc32.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.crc32.update(bArr, i, i2);
    }
}
