package com.shadow.kotlin.coroutines.jvm.internal;

import com.shadow.kotlin.coroutines.CoroutineContext;
import com.shadow.kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.Continuation;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class RestrictedContinuationImpl extends BaseContinuationImpl {
    public RestrictedContinuationImpl(Continuation<Object> continuation) {
        super(continuation);
        if (continuation != null && continuation.getContext() != EmptyCoroutineContext.INSTANCE) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // com.shadow.kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
