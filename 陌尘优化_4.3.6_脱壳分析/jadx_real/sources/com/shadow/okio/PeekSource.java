package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import core.pro.android.notify.h;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    public PeekSource(BufferedSource bufferedSource) {
        CloseableKt.checkNotNullParameter(bufferedSource, "upstream");
        this.upstream = bufferedSource;
        Buffer buffer = bufferedSource.getBuffer();
        this.buffer = buffer;
        Segment segment = buffer.head;
        this.expectedSegment = segment;
        this.expectedPos = segment != null ? segment.pos : -1;
    }

    @Override // com.shadow.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
    
        if (r3 == r4.pos) goto L15;
     */
    @Override // com.shadow.okio.Source
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long read(Buffer buffer, long j) {
        Segment segment;
        CloseableKt.checkNotNullParameter(buffer, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(h.c("byteCount < 0: ", j).toString());
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Segment segment2 = this.expectedSegment;
        if (segment2 != null) {
            Segment segment3 = this.buffer.head;
            if (segment2 == segment3) {
                int i = this.expectedPos;
                CloseableKt.checkNotNull(segment3);
            }
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        if (j == 0) {
            return 0L;
        }
        if (!this.upstream.request(this.pos + 1)) {
            return -1L;
        }
        if (this.expectedSegment == null && (segment = this.buffer.head) != null) {
            this.expectedSegment = segment;
            CloseableKt.checkNotNull(segment);
            this.expectedPos = segment.pos;
        }
        long jMin = Math.min(j, this.buffer.size() - this.pos);
        this.buffer.copyTo(buffer, this.pos, jMin);
        this.pos += jMin;
        return jMin;
    }

    @Override // com.shadow.okio.Source
    public Timeout timeout() {
        return this.upstream.timeout();
    }
}
