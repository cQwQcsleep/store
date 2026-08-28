package com.shadow.kotlin.coroutines.jvm.internal;

import com.shadow.kotlin.coroutines.ContinuationInterceptor;
import com.shadow.kotlin.coroutines.CoroutineContext;
import com.shadow.kotlin.coroutines.EmptyCoroutineContext;
import com.shadow.kotlin.io.CloseableKt;
import kotlin.coroutines.Continuation;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class ContinuationImpl extends BaseContinuationImpl {
    private final CoroutineContext _context;
    private transient Continuation<Object> intercepted;

    public ContinuationImpl(Continuation<Object> continuation, kotlin.coroutines.CoroutineContext coroutineContext) {
        super(continuation);
        this._context = coroutineContext;
    }

    @Override // com.shadow.kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext = this._context;
        CloseableKt.checkNotNull(coroutineContext);
        return coroutineContext;
    }

    public final Continuation<Object> intercepted() {
        Continuation<Object> continuation = this.intercepted;
        if (continuation != null) {
            return continuation;
        }
        ((EmptyCoroutineContext) getContext()).get(ContinuationInterceptor.Key);
        this.intercepted = this;
        return this;
    }

    @Override // com.shadow.kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        Continuation<Object> continuation = this.intercepted;
        if (continuation == null || continuation == this) {
            this.intercepted = CompletedContinuation.INSTANCE;
            return;
        }
        ((EmptyCoroutineContext) getContext()).get(ContinuationInterceptor.Key);
        CloseableKt.checkNotNull(null);
        throw null;
    }

    public ContinuationImpl(Continuation<Object> continuation) {
        this(continuation, continuation != null ? continuation.getContext() : null);
    }
}
