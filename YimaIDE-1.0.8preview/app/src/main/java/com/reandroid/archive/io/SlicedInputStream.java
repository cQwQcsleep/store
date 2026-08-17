package com.reandroid.archive.io;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SlicedInputStream extends InputStream {
    private final InputStream inputStream;
    private long mCount;
    private boolean mFinished;
    private final long mLength;
    private final long mOffset;
    private boolean mStarted;

    public SlicedInputStream(InputStream inputStream, long j, long j2) {
        this.inputStream = inputStream;
        this.mOffset = j;
        this.mLength = j2;
    }

    private void checkStarted() throws IOException {
        if (this.mStarted) {
            return;
        }
        this.mStarted = true;
        this.inputStream.skip(this.mOffset);
        this.mCount = 0L;
    }

    private void onFinished() throws IOException {
        this.mFinished = true;
        this.inputStream.close();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        onFinished();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        boolean z;
        if (this.mFinished) {
            return -1;
        }
        checkStarted();
        long j = this.mLength - this.mCount;
        if (j <= 0) {
            onFinished();
            return -1;
        }
        if (i2 > j) {
            i2 = (int) j;
            z = true;
        } else {
            z = false;
        }
        int i3 = this.inputStream.read(bArr, i, i2);
        this.mCount += (long) i3;
        if (z) {
            onFinished();
        }
        return i3;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        checkStarted();
        long jSkip = this.inputStream.skip(j);
        if (jSkip > 0) {
            this.mCount += jSkip;
        }
        return jSkip;
    }

    public String toString() {
        return "[" + this.mOffset + "," + this.mLength + "] " + this.mCount;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.mFinished) {
            return -1;
        }
        checkStarted();
        long j = this.mLength - this.mCount;
        if (j <= 0) {
            onFinished();
            return -1;
        }
        int i = this.inputStream.read();
        this.mCount++;
        if (j == 1) {
            onFinished();
        }
        return i;
    }
}
