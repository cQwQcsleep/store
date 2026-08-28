package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.EOFException;

/* loaded from: /workspace/unpacked/classes2.dex */
final class BlackholeSink implements Sink {
    @Override // com.shadow.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // com.shadow.okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // com.shadow.okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override // com.shadow.okio.Sink
    public void write(Buffer buffer, long j) throws EOFException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        buffer.skip(j);
    }
}
