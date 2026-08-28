package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class CipherSink implements Sink {
    private final int blockSize;
    private final Cipher cipher;
    private boolean closed;
    private final BufferedSink sink;

    public CipherSink(BufferedSink bufferedSink, Cipher cipher) {
        CloseableKt.checkNotNullParameter(bufferedSink, "sink");
        CloseableKt.checkNotNullParameter(cipher, "cipher");
        this.sink = bufferedSink;
        this.cipher = cipher;
        int blockSize = cipher.getBlockSize();
        this.blockSize = blockSize;
        if (blockSize > 0) {
            return;
        }
        throw new IllegalArgumentException(("Block cipher required " + cipher).toString());
    }

    private final Throwable doFinal() {
        int outputSize = this.cipher.getOutputSize(0);
        Throwable th = null;
        if (outputSize == 0) {
            return null;
        }
        if (outputSize > 8192) {
            try {
                BufferedSink bufferedSink = this.sink;
                byte[] bArrDoFinal = this.cipher.doFinal();
                CloseableKt.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
                bufferedSink.write(bArrDoFinal);
                return null;
            } catch (Throwable th2) {
                return th2;
            }
        }
        Buffer buffer = this.sink.getBuffer();
        Segment segmentWritableSegment$okio = buffer.writableSegment$okio(outputSize);
        try {
            int iDoFinal = this.cipher.doFinal(segmentWritableSegment$okio.data, segmentWritableSegment$okio.limit);
            segmentWritableSegment$okio.limit += iDoFinal;
            buffer.setSize$okio(buffer.size() + iDoFinal);
        } catch (Throwable th3) {
            th = th3;
        }
        if (segmentWritableSegment$okio.pos == segmentWritableSegment$okio.limit) {
            buffer.head = segmentWritableSegment$okio.pop();
            SegmentPool.recycle(segmentWritableSegment$okio);
        }
        return th;
    }

    private final int update(Buffer buffer, long j) throws IOException, ShortBufferException {
        Segment segment = buffer.head;
        CloseableKt.checkNotNull(segment);
        int iMin = (int) Math.min(j, segment.limit - segment.pos);
        Buffer buffer2 = this.sink.getBuffer();
        int outputSize = this.cipher.getOutputSize(iMin);
        while (outputSize > 8192) {
            int i = this.blockSize;
            if (iMin <= i) {
                BufferedSink bufferedSink = this.sink;
                byte[] bArrUpdate = this.cipher.update(buffer.readByteArray(j));
                CloseableKt.checkNotNullExpressionValue(bArrUpdate, "update(...)");
                bufferedSink.write(bArrUpdate);
                return (int) j;
            }
            iMin -= i;
            outputSize = this.cipher.getOutputSize(iMin);
        }
        Segment segmentWritableSegment$okio = buffer2.writableSegment$okio(outputSize);
        int iUpdate = this.cipher.update(segment.data, segment.pos, iMin, segmentWritableSegment$okio.data, segmentWritableSegment$okio.limit);
        segmentWritableSegment$okio.limit += iUpdate;
        buffer2.setSize$okio(buffer2.size() + iUpdate);
        if (segmentWritableSegment$okio.pos == segmentWritableSegment$okio.limit) {
            buffer2.head = segmentWritableSegment$okio.pop();
            SegmentPool.recycle(segmentWritableSegment$okio);
        }
        this.sink.emitCompleteSegments();
        buffer.setSize$okio(buffer.size() - iMin);
        int i2 = segment.pos + iMin;
        segment.pos = i2;
        if (i2 == segment.limit) {
            buffer.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return iMin;
    }

    @Override // com.shadow.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        this.closed = true;
        Throwable thDoFinal = doFinal();
        try {
            this.sink.close();
        } catch (Throwable th) {
            if (thDoFinal == null) {
                thDoFinal = th;
            }
        }
        if (thDoFinal != null) {
            throw thDoFinal;
        }
    }

    @Override // com.shadow.okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.sink.flush();
    }

    public final Cipher getCipher() {
        return this.cipher;
    }

    @Override // com.shadow.okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @Override // com.shadow.okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0L, j);
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            j -= update(buffer, j);
        }
    }
}
