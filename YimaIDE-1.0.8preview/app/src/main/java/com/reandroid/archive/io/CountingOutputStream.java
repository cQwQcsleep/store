package com.reandroid.archive.io;

import com.reandroid.utils.Crc32;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class CountingOutputStream<T extends OutputStream> extends OutputStream {
    private Crc32 crc32;
    private boolean mClosed;
    private final T outputStream;
    private long size;

    public CountingOutputStream(T t, boolean z) {
        this.outputStream = t;
        this.crc32 = z ? null : new Crc32();
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.outputStream.close();
        this.mClosed = true;
    }

    public void disableCrc(boolean z) {
        if (z) {
            this.crc32 = null;
        } else if (this.crc32 == null) {
            this.crc32 = new Crc32();
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.outputStream.flush();
    }

    public long getCrc32() {
        Crc32 crc32 = this.crc32;
        if (crc32 != null) {
            return crc32.getValue();
        }
        return 0L;
    }

    public T getOutputStream() {
        return this.outputStream;
    }

    public long getSize() {
        return this.size;
    }

    public boolean isOpen() {
        return !this.mClosed;
    }

    public void reset() {
        Crc32 crc32 = this.crc32;
        if (crc32 != null) {
            crc32.reset();
        }
        this.size = 0L;
    }

    public String toString() {
        return "[" + this.size + "]: " + this.outputStream.getClass().getSimpleName();
    }

    public void write(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[2048];
        while (true) {
            int i = inputStream.read(bArr, 0, bArr.length);
            if (i < 0) {
                inputStream.close();
                return;
            } else {
                write(bArr, 0, i);
                if (bArr.length < 81920) {
                    bArr = new byte[bArr.length + 500];
                }
            }
        }
    }

    public CountingOutputStream(T t) {
        this(t, false);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return;
        }
        this.outputStream.write(bArr, i, i2);
        this.size += (long) i2;
        Crc32 crc32 = this.crc32;
        if (crc32 != null) {
            crc32.update(bArr, i, i2);
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }
}
