package com.android.apksig.internal.util;

import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.ReadableDataSink;
import defpackage.n31;
import defpackage.o31;
import defpackage.p31;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ByteArrayDataSink implements ReadableDataSink {
    private static final int MAX_READ_CHUNK_SIZE = 65536;
    private byte[] mArray;
    private int mSize;

    public class SliceDataSource implements DataSource {
        private final int mSliceOffset;
        private final int mSliceSize;

        private SliceDataSource(int i, int i2) {
            this.mSliceOffset = i;
            this.mSliceSize = i2;
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
            int i = this.mSliceSize;
            if (j > i) {
                p31.a(j, this.mSliceSize);
                return;
            }
            long j3 = j + j2;
            if (j3 < j) {
                o31.a(j, ") + size (", j2, ") overflow");
            } else {
                if (j3 <= i) {
                    return;
                }
                n31.a(j, j2, this.mSliceSize);
            }
        }

        @Override // com.android.apksig.util.DataSource
        public void copyTo(long j, int i, ByteBuffer byteBuffer) throws IOException {
            checkChunkValid(j, i);
            byteBuffer.put(ByteArrayDataSink.this.mArray, (int) (((long) this.mSliceOffset) + j), i);
        }

        @Override // com.android.apksig.util.DataSource
        public void feed(long j, long j2, DataSink dataSink) throws IOException {
            checkChunkValid(j, j2);
            dataSink.consume(ByteArrayDataSink.this.mArray, (int) (((long) this.mSliceOffset) + j), (int) j2);
        }

        @Override // com.android.apksig.util.DataSource
        public ByteBuffer getByteBuffer(long j, int i) throws IOException {
            checkChunkValid(j, i);
            return ByteBuffer.wrap(ByteArrayDataSink.this.mArray, (int) (((long) this.mSliceOffset) + j), i).slice();
        }

        @Override // com.android.apksig.util.DataSource
        public long size() {
            return this.mSliceSize;
        }

        @Override // com.android.apksig.util.DataSource
        public DataSource slice(long j, long j2) {
            checkChunkValid(j, j2);
            return ByteArrayDataSink.this.new SliceDataSource((int) (((long) this.mSliceOffset) + j), (int) j2);
        }
    }

    public ByteArrayDataSink(int i) {
        if (i >= 0) {
            this.mArray = new byte[i];
        } else {
            qf1.a("initial capacity: ", i);
            throw null;
        }
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

    private void ensureAvailable(int i) throws IOException {
        if (i <= 0) {
            return;
        }
        long j = ((long) this.mSize) + ((long) i);
        byte[] bArr = this.mArray;
        if (j <= bArr.length) {
            return;
        }
        if (j <= 2147483647L) {
            this.mArray = Arrays.copyOf(this.mArray, (int) Math.max(j, (int) Math.min(((long) bArr.length) * 2, 2147483647L)));
        } else {
            throw new IOException("Required capacity too large: " + j + ", max: 2147483647");
        }
    }

    @Override // com.android.apksig.util.DataSink
    public void consume(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.hasRemaining()) {
            if (byteBuffer.hasArray()) {
                consume(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
                byteBuffer.position(byteBuffer.limit());
                return;
            }
            ensureAvailable(byteBuffer.remaining());
            int iMin = Math.min(byteBuffer.remaining(), 65536);
            byte[] bArr = new byte[iMin];
            while (byteBuffer.hasRemaining()) {
                int iMin2 = Math.min(byteBuffer.remaining(), iMin);
                byteBuffer.get(bArr, 0, iMin2);
                System.arraycopy(bArr, 0, this.mArray, this.mSize, iMin2);
                this.mSize += iMin2;
            }
        }
    }

    @Override // com.android.apksig.util.DataSource
    public void copyTo(long j, int i, ByteBuffer byteBuffer) throws IOException {
        checkChunkValid(j, i);
        byteBuffer.put(this.mArray, (int) j, i);
    }

    @Override // com.android.apksig.util.DataSource
    public void feed(long j, long j2, DataSink dataSink) throws IOException {
        checkChunkValid(j, j2);
        dataSink.consume(this.mArray, (int) j, (int) j2);
    }

    @Override // com.android.apksig.util.DataSource
    public ByteBuffer getByteBuffer(long j, int i) {
        checkChunkValid(j, i);
        return ByteBuffer.wrap(this.mArray, (int) j, i).slice();
    }

    @Override // com.android.apksig.util.DataSource
    public long size() {
        return this.mSize;
    }

    @Override // com.android.apksig.util.DataSource
    public DataSource slice(long j, long j2) {
        checkChunkValid(j, j2);
        return new SliceDataSource((int) j, (int) j2);
    }

    public ByteArrayDataSink() {
        this(65536);
    }

    @Override // com.android.apksig.util.DataSink
    public void consume(byte[] bArr, int i, int i2) throws IOException {
        if (i >= 0) {
            if (i > bArr.length) {
                kac.a("offset: ", i, ", buf.length: ", bArr.length);
                return;
            } else {
                if (i2 == 0) {
                    return;
                }
                ensureAvailable(i2);
                System.arraycopy(bArr, i, this.mArray, this.mSize, i2);
                this.mSize += i2;
                return;
            }
        }
        b1e.a("offset: ", i);
    }
}
