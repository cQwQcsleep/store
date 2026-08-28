package com.shadow.kotlin.coroutines;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface Continuation<T> {
    CoroutineContext getContext();

    void resumeWith(Object obj);
}
