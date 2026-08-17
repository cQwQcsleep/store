package com.android.apksig.internal.util;

import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import defpackage.o31;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class FileChannelDataSource implements DataSource {
    private static final int MAX_READ_CHUNK_SIZE = 1048576;
    private final FileChannel mChannel;
    private final long mOffset;
    private final long mSize;

    public FileChannelDataSource(FileChannel fileChannel, long j, long j2) {
        if (j < 0) {
            gz9.a("offset: ", j2);
            throw null;
        }
        if (j2 < 0) {
            gz9.a("size: ", j2);
            throw null;
        }
        this.mChannel = fileChannel;
        this.mOffset = j;
        this.mSize = j2;
    }

    private static void checkChunkValid(long j, long j2, long j3) {
        if (j < 0) {
            gz9.a("offset: ", j);
            return;
        }
        if (j2 < 0) {
            gz9.a("size: ", j2);
            return;
        }
        if (j > j3) {
            o31.a(j, ") > source size (", j3, ")");
            return;
        }
        long j4 = j + j2;
        if (j4 < j) {
            o31.a(j, ") + size (", j2, ") overflow");
            return;
        }
        if (j4 <= j3) {
            return;
        }
        throw new IndexOutOfBoundsException("offset (" + j + ") + size (" + j2 + ") > source size (" + j3 + ")");
    }

    @Override // com.android.apksig.util.DataSource
    public void copyTo(long j, int i, ByteBuffer byteBuffer) throws IOException {
        int i2;
        checkChunkValid(j, i, size());
        if (i == 0) {
            return;
        }
        if (i > byteBuffer.remaining()) {
            throw new BufferOverflowException();
        }
        long j2 = this.mOffset + j;
        int iLimit = byteBuffer.limit();
        try {
            byteBuffer.limit(byteBuffer.position() + i);
            while (i > 0) {
                synchronized (this.mChannel) {
                    this.mChannel.position(j2);
                    i2 = this.mChannel.read(byteBuffer);
                }
                j2 += (long) i2;
                i -= i2;
            }
            byteBuffer.limit(iLimit);
        } catch (Throwable th) {
            byteBuffer.limit(iLimit);
            throw th;
        }
    }

    @Override // com.android.apksig.util.DataSource
    public void feed(long j, long j2, DataSink dataSink) throws IOException {
        checkChunkValid(j, j2, size());
        if (j2 == 0) {
            return;
        }
        long j3 = this.mOffset + j;
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect((int) Math.min(j2, 1048576L));
        long j4 = j3;
        long j5 = j2;
        while (j5 > 0) {
            int iMin = (int) Math.min(j5, byteBufferAllocateDirect.capacity());
            byteBufferAllocateDirect.limit(iMin);
            synchronized (this.mChannel) {
                try {
                    this.mChannel.position(j4);
                    int i = iMin;
                    while (i > 0) {
                        int i2 = this.mChannel.read(byteBufferAllocateDirect);
                        if (i2 < 0) {
                            throw new IOException("Unexpected EOF encountered");
                        }
                        i -= i2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            byteBufferAllocateDirect.flip();
            dataSink.consume(byteBufferAllocateDirect);
            byteBufferAllocateDirect.clear();
            long j6 = iMin;
            j4 += j6;
            j5 -= j6;
        }
    }

    @Override // com.android.apksig.util.DataSource
    public ByteBuffer getByteBuffer(long j, int i) throws IOException {
        if (i < 0) {
            b1e.a("size: ", i);
            return null;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        copyTo(j, i, byteBufferAllocate);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // com.android.apksig.util.DataSource
    public long size() {
        long j = this.mSize;
        if (j != -1) {
            return j;
        }
        try {
            return this.mChannel.size();
        } catch (IOException unused) {
            return 0L;
        }
    }

    @Override // com.android.apksig.util.DataSource
    public FileChannelDataSource slice(long j, long j2) {
        long size = size();
        checkChunkValid(j, j2, size);
        return (j == 0 && j2 == size) ? this : new FileChannelDataSource(this.mChannel, this.mOffset + j, j2);
    }

    public FileChannelDataSource(FileChannel fileChannel) {
        this.mChannel = fileChannel;
        this.mOffset = 0L;
        this.mSize = -1L;
    }
}
