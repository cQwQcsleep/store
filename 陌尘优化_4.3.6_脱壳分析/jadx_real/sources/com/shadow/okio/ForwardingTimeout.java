package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/* loaded from: /workspace/unpacked/classes2.dex */
public class ForwardingTimeout extends Timeout {
    private Timeout delegate;

    public ForwardingTimeout(Timeout timeout) {
        CloseableKt.checkNotNullParameter(timeout, "delegate");
        this.delegate = timeout;
    }

    @Override // com.shadow.okio.Timeout
    public void awaitSignal(Condition condition) throws InterruptedException, InterruptedIOException {
        CloseableKt.checkNotNullParameter(condition, "condition");
        this.delegate.awaitSignal(condition);
    }

    @Override // com.shadow.okio.Timeout
    public void cancel() {
        this.delegate.cancel();
    }

    @Override // com.shadow.okio.Timeout
    public Timeout clearDeadline() {
        return this.delegate.clearDeadline();
    }

    @Override // com.shadow.okio.Timeout
    public Timeout clearTimeout() {
        return this.delegate.clearTimeout();
    }

    @Override // com.shadow.okio.Timeout
    public long deadlineNanoTime() {
        return this.delegate.deadlineNanoTime();
    }

    public final Timeout delegate() {
        return this.delegate;
    }

    @Override // com.shadow.okio.Timeout
    public boolean hasDeadline() {
        return this.delegate.hasDeadline();
    }

    /* renamed from: setDelegate, reason: collision with other method in class */
    public final /* synthetic */ void m173setDelegate(Timeout timeout) {
        CloseableKt.checkNotNullParameter(timeout, "<set-?>");
        this.delegate = timeout;
    }

    @Override // com.shadow.okio.Timeout
    public void throwIfReached() throws IOException {
        this.delegate.throwIfReached();
    }

    @Override // com.shadow.okio.Timeout
    public Timeout timeout(long j, TimeUnit timeUnit) {
        CloseableKt.checkNotNullParameter(timeUnit, "unit");
        return this.delegate.timeout(j, timeUnit);
    }

    @Override // com.shadow.okio.Timeout
    public long timeoutNanos() {
        return this.delegate.timeoutNanos();
    }

    @Override // com.shadow.okio.Timeout
    public void waitUntilNotified(Object obj) throws InterruptedException, InterruptedIOException {
        CloseableKt.checkNotNullParameter(obj, "monitor");
        this.delegate.waitUntilNotified(obj);
    }

    @Override // com.shadow.okio.Timeout
    public Timeout deadlineNanoTime(long j) {
        return this.delegate.deadlineNanoTime(j);
    }

    public final ForwardingTimeout setDelegate(Timeout timeout) {
        CloseableKt.checkNotNullParameter(timeout, "delegate");
        this.delegate = timeout;
        return this;
    }
}
