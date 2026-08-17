package com.android.apksig.internal.util;

import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import defpackage.n31;
import defpackage.o31;
import defpackage.p31;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ByteBufferDataSource implements DataSource {
    private final ByteBuffer mBuffer;
    private final int mSize;

    private ByteBufferDataSource(ByteBuffer byteBuffer, boolean z) {
        this.mBuffer = z ? byteBuffer.slice() : byteBuffer;
        this.mSize = byteBuffer.remaining();
    }

    private void checkChunkValid(long j, long j2) {
        if (j < 0) {
            gz9.a("offset: ", j);
            return;
        }
        if (j2 < 0) {
            gz9.a("size: ", j2);
            return;
        }
        int i = this.mSize;
        if (j > i) {
            p31.a(j, this.mSize);
            return;
        }
        long j3 = j + j2;
        if (j3 < j) {
            o31.a(j, ") + size (", j2, ") overflow");
        } else {
            if (j3 <= i) {
                return;
            }
            n31.a(j, j2, this.mSize);
        }
    }

    @Override // com.android.apksig.util.DataSource
    public void copyTo(long j, int i, ByteBuffer byteBuffer) {
        byteBuffer.put(getByteBuffer(j, i));
    }

    @Override // com.android.apksig.util.DataSource
    public void feed(long j, long j2, DataSink dataSink) throws IOException {
        if (j2 >= 0 && j2 <= this.mSize) {
            dataSink.consume(getByteBuffer(j, (int) j2));
            return;
        }
        throw new IndexOutOfBoundsException("size: " + j2 + ", source size: " + this.mSize);
    }

    @Override // com.android.apksig.util.DataSource
    public ByteBuffer getByteBuffer(long j, int i) {
        ByteBuffer byteBufferSlice;
        checkChunkValid(j, i);
        int i2 = (int) j;
        int i3 = i + i2;
        synchronized (this.mBuffer) {
            this.mBuffer.position(0);
            this.mBuffer.limit(i3);
            this.mBuffer.position(i2);
            byteBufferSlice = this.mBuffer.slice();
        }
        return byteBufferSlice;
    }

    @Override // com.android.apksig.util.DataSource
    public long size() {
        return this.mSize;
    }

    @Override // com.android.apksig.util.DataSource
    public ByteBufferDataSource slice(long j, long j2) {
        if (j == 0 && j2 == this.mSize) {
            return this;
        }
        if (j2 >= 0 && j2 <= this.mSize) {
            return new ByteBufferDataSource(getByteBuffer(j, (int) j2), false);
        }
        throw new IndexOutOfBoundsException("size: " + j2 + ", source size: " + this.mSize);
    }

    public ByteBufferDataSource(ByteBuffer byteBuffer) {
        this(byteBuffer, true);
    }
}
