package com.reandroid.common;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class BytesInputStream extends InputStream {
    private final byte[] array;
    private final int length;
    private int mark;
    private final int offset;
    private int position;

    public BytesInputStream(byte[] bArr, int i, int i2) {
        i = i >= bArr.length ? bArr.length - 1 : i;
        i = i < 0 ? 0 : i;
        int length = bArr.length - i;
        i2 = i2 > length ? length : i2;
        this.array = bArr;
        this.offset = i;
        this.length = i2;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.length - this.position;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.position = this.length;
    }

    public byte[] getArray() {
        return this.array;
    }

    public int getLength() {
        return this.length;
    }

    public int getOffset() {
        return this.offset;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.mark = i;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int iAvailable = available();
        if (iAvailable <= 0) {
            return -1;
        }
        if (i2 > iAvailable) {
            i2 = iAvailable;
        }
        System.arraycopy(this.array, this.offset + this.position, bArr, i, i2);
        this.position += i2;
        return i2;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        this.position = this.mark;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return j;
        }
        int i = (int) j;
        int iAvailable = available();
        if (i > iAvailable) {
            i = iAvailable;
        }
        this.position += i;
        return i;
    }

    public byte[] toByteArray() {
        int i = this.offset;
        if (i == 0 && this.position == 0) {
            int i2 = this.length;
            byte[] bArr = this.array;
            if (i2 == bArr.length) {
                return bArr;
            }
        }
        int i3 = this.length;
        int i4 = this.position;
        int i5 = i3 - i4;
        byte[] bArr2 = new byte[i5];
        System.arraycopy(this.array, i + i4, bArr2, 0, i5);
        return bArr2;
    }

    public BytesInputStream(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (available() <= 0) {
            return -1;
        }
        byte[] bArr = this.array;
        int i = this.offset;
        int i2 = this.position;
        byte b = bArr[i + i2];
        this.position = i2 + 1;
        return b & 255;
    }
}
