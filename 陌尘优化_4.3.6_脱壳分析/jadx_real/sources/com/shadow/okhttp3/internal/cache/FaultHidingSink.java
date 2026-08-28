package com.shadow.okhttp3.internal.cache;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.Buffer;
import com.shadow.okio.ForwardingSink;
import com.shadow.okio.Sink;
import java.io.EOFException;
import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: /workspace/unpacked/classes2.dex */
public class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;
    private final Function1<IOException, Unit> onException;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FaultHidingSink(Sink sink, Function1<? super IOException, Unit> function1) {
        super(sink);
        CloseableKt.checkNotNullParameter(sink, "delegate");
        CloseableKt.checkNotNullParameter(function1, "onException");
        this.onException = function1;
    }

    @Override // com.shadow.okio.ForwardingSink, com.shadow.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.hasErrors = true;
            this.onException.invoke(e);
        }
    }

    @Override // com.shadow.okio.ForwardingSink, com.shadow.okio.Sink, java.io.Flushable
    public void flush() {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.hasErrors = true;
            this.onException.invoke(e);
        }
    }

    public final Function1<IOException, Unit> getOnException() {
        return this.onException;
    }

    @Override // com.shadow.okio.ForwardingSink, com.shadow.okio.Sink
    public void write(Buffer buffer, long j) throws EOFException {
        CloseableKt.checkNotNullParameter(buffer, "source");
        if (this.hasErrors) {
            buffer.skip(j);
            return;
        }
        try {
            super.write(buffer, j);
        } catch (IOException e) {
            this.hasErrors = true;
            this.onException.invoke(e);
        }
    }
}
