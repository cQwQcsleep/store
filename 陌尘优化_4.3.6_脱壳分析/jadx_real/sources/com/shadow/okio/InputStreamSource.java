package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import core.pro.android.notify.h;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: /workspace/unpacked/classes2.dex */
class InputStreamSource implements Source {
    private final InputStream input;
    private final Timeout timeout;

    public InputStreamSource(InputStream inputStream, Timeout timeout) {
        CloseableKt.checkNotNullParameter(inputStream, "input");
        CloseableKt.checkNotNullParameter(timeout, "timeout");
        this.input = inputStream;
        this.timeout = timeout;
    }

    @Override // com.shadow.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.input.close();
    }

    @Override // com.shadow.okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "sink");
        if (j == 0) {
            return 0L;
        }
        if (j < 0) {
            throw new IllegalArgumentException(h.c("byteCount < 0: ", j).toString());
        }
        try {
            this.timeout.throwIfReached();
            Segment segmentWritableSegment$okio = buffer.writableSegment$okio(1);
            int i = this.input.read(segmentWritableSegment$okio.data, segmentWritableSegment$okio.limit, (int) Math.min(j, 8192 - segmentWritableSegment$okio.limit));
            if (i != -1) {
                segmentWritableSegment$okio.limit += i;
                long j2 = i;
                buffer.setSize$okio(buffer.size() + j2);
                return j2;
            }
            if (segmentWritableSegment$okio.pos != segmentWritableSegment$okio.limit) {
                return -1L;
            }
            buffer.head = segmentWritableSegment$okio.pop();
            SegmentPool.recycle(segmentWritableSegment$okio);
            return -1L;
        } catch (AssertionError e) {
            if (Okio.isAndroidGetsocknameError(e)) {
                throw new IOException(e);
            }
            throw e;
        }
    }

    @Override // com.shadow.okio.Source
    public Timeout timeout() {
        return this.timeout;
    }

    public String toString() {
        return "source(" + this.input + ')';
    }
}
