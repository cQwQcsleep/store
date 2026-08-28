package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class ForwardingSink implements Sink {
    private final Sink delegate;

    public ForwardingSink(Sink sink) {
        CloseableKt.checkNotNullParameter(sink, "delegate");
        this.delegate = sink;
    }

    /* renamed from: -deprecated_delegate, reason: not valid java name */
    public final Sink m171deprecated_delegate() {
        return this.delegate;
    }

    @Override // com.shadow.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    public final Sink delegate() {
        return this.delegate;
    }

    @Override // com.shadow.okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // com.shadow.okio.Sink
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.delegate + ')';
    }

    @Override // com.shadow.okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        this.delegate.write(buffer, j);
    }
}
