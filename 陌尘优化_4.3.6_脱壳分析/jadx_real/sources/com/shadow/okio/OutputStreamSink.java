package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: /workspace/unpacked/classes2.dex */
final class OutputStreamSink implements Sink {
    private final OutputStream out;
    private final Timeout timeout;

    public OutputStreamSink(OutputStream outputStream, Timeout timeout) {
        CloseableKt.checkNotNullParameter(outputStream, "out");
        CloseableKt.checkNotNullParameter(timeout, "timeout");
        this.out = outputStream;
        this.timeout = timeout;
    }

    @Override // com.shadow.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }

    @Override // com.shadow.okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // com.shadow.okio.Sink
    public Timeout timeout() {
        return this.timeout;
    }

    public String toString() {
        return "sink(" + this.out + ')';
    }

    @Override // com.shadow.okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        SegmentedByteString.checkOffsetAndCount(buffer.size(), 0L, j);
        while (j > 0) {
            this.timeout.throwIfReached();
            Segment segment = buffer.head;
            CloseableKt.checkNotNull(segment);
            int iMin = (int) Math.min(j, segment.limit - segment.pos);
            this.out.write(segment.data, segment.pos, iMin);
            segment.pos += iMin;
            long j2 = iMin;
            j -= j2;
            buffer.setSize$okio(buffer.size() - j2);
            if (segment.pos == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }
}
