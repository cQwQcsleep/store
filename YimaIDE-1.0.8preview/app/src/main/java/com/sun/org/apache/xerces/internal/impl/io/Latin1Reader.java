package com.sun.org.apache.xerces.internal.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Latin1Reader extends Reader {
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected final byte[] fBuffer;
    protected final InputStream fInputStream;

    public Latin1Reader(InputStream inputStream, byte[] bArr) {
        this.fInputStream = inputStream;
        this.fBuffer = bArr;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.fInputStream.close();
    }

    @Override // java.io.Reader
    public void mark(int i) throws IOException {
        this.fInputStream.mark(i);
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return this.fInputStream.markSupported();
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        byte[] bArr = this.fBuffer;
        if (i2 > bArr.length) {
            i2 = bArr.length;
        }
        int i3 = this.fInputStream.read(bArr, 0, i2);
        for (int i4 = 0; i4 < i3; i4++) {
            cArr[i + i4] = (char) (this.fBuffer[i4] & 255);
        }
        return i3;
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        return false;
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        this.fInputStream.reset();
    }

    @Override // java.io.Reader
    public long skip(long j) throws IOException {
        return this.fInputStream.skip(j);
    }

    public Latin1Reader(InputStream inputStream, int i) {
        this(inputStream, new byte[i]);
    }

    public Latin1Reader(InputStream inputStream) {
        this(inputStream, 2048);
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        return this.fInputStream.read();
    }
}
