package com.intellij.util.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class LimitedInputStream extends FilterInputStream {
    private int myBytesRead;
    private final int myReadLimit;

    public LimitedInputStream(InputStream inputStream, int i) {
        super(inputStream);
        this.myReadLimit = i;
        this.myBytesRead = 0;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return Math.min(super.available(), remainingLimit());
    }

    public int getBytesRead() {
        return this.myBytesRead;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int iMin;
        if (i2 == 0) {
            return 0;
        }
        if (remainingLimit() <= 0 || (iMin = Math.min(i2, remainingLimit())) <= 0) {
            return -1;
        }
        int i3 = super.read(bArr, i, iMin);
        if (i3 >= 0) {
            this.myBytesRead += i3;
        }
        return i3;
    }

    public int remainingLimit() {
        return this.myReadLimit - this.myBytesRead;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long jMin = Math.min(j, remainingLimit());
        if (jMin <= 0) {
            return 0L;
        }
        long jSkip = super.skip(jMin);
        this.myBytesRead = (int) (((long) this.myBytesRead) + jSkip);
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (remainingLimit() <= 0) {
            return -1;
        }
        int i = super.read();
        if (i >= 0) {
            this.myBytesRead++;
        }
        return i;
    }
}
