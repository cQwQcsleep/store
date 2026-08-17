package com.android.apksig.internal.util;

import com.android.apksig.util.DataSink;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class RandomAccessFileDataSink implements DataSink {
    private final RandomAccessFile mFile;
    private final FileChannel mFileChannel;
    private long mPosition;

    public RandomAccessFileDataSink(RandomAccessFile randomAccessFile, long j) {
        if (randomAccessFile == null) {
            x0e.a("file == null");
            throw null;
        }
        if (j < 0) {
            t01.a("startPosition: ", j);
            throw null;
        }
        this.mFile = randomAccessFile;
        this.mFileChannel = randomAccessFile.getChannel();
        this.mPosition = j;
    }

    @Override // com.android.apksig.util.DataSink
    public void consume(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            b1e.a("offset: ", i);
            return;
        }
        if (i > bArr.length) {
            kac.a("offset: ", i, ", buf.length: ", bArr.length);
            return;
        }
        if (i2 == 0) {
            return;
        }
        synchronized (this.mFile) {
            this.mFile.seek(this.mPosition);
            this.mFile.write(bArr, i, i2);
            this.mPosition += (long) i2;
        }
    }

    public RandomAccessFile getFile() {
        return this.mFile;
    }

    public RandomAccessFileDataSink(RandomAccessFile randomAccessFile) {
        this(randomAccessFile, 0L);
    }

    @Override // com.android.apksig.util.DataSink
    public void consume(ByteBuffer byteBuffer) throws IOException {
        int iRemaining = byteBuffer.remaining();
        if (iRemaining == 0) {
            return;
        }
        synchronized (this.mFile) {
            try {
                this.mFile.seek(this.mPosition);
                while (byteBuffer.hasRemaining()) {
                    this.mFileChannel.write(byteBuffer);
                }
                this.mPosition += (long) iRemaining;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
