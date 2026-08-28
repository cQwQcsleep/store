package com.shadow.kotlin.coroutines.jvm.internal;

import com.shadow.kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.Continuation;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class CompletedContinuation implements Continuation<Object> {
    public static final CompletedContinuation INSTANCE = new CompletedContinuation();

    public final CoroutineContext getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}
