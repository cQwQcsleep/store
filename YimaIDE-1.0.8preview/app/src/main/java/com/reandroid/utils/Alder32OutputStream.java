package com.reandroid.utils;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Alder32OutputStream extends OutputStream {
    private final ALDER32 alder32 = new ALDER32();

    public long getValue() {
        return this.alder32.getValue();
    }

    public void reset() {
        this.alder32.reset();
    }

    public String toString() {
        return this.alder32.toString();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.alder32.update((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.alder32.update(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.alder32.update(bArr, i, i2);
    }
}
