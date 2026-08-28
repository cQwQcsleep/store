package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class ForwardingSource implements Source {
    private final Source delegate;

    public ForwardingSource(Source source) {
        CloseableKt.checkNotNullParameter(source, "delegate");
        this.delegate = source;
    }

    /* renamed from: -deprecated_delegate, reason: not valid java name */
    public final Source m172deprecated_delegate() {
        return this.delegate;
    }

    @Override // com.shadow.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    public final Source delegate() {
        return this.delegate;
    }

    @Override // com.shadow.okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        CloseableKt.checkNotNullParameter(buffer, "sink");
        return this.delegate.read(buffer, j);
    }

    @Override // com.shadow.okio.Source
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + this.delegate + ')';
    }
}
