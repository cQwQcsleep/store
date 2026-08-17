package com.reandroid.utils;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Sha1OutputStream extends OutputStream {
    private final SHA1 sha1 = new SHA1();

    public byte[] digest() {
        return this.sha1.digest();
    }

    public void reset() {
        this.sha1.reset();
    }

    public String toString() {
        return this.sha1.toString();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.sha1.update(bArr, 0, bArr.length);
    }

    public void digest(byte[] bArr, int i) {
        this.sha1.digest(bArr, i);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.sha1.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.sha1.update(bArr, i, i2);
    }
}
