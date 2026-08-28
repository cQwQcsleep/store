package com.shadow.kotlin.sequences;

import com.shadow.kotlin.coroutines.intrinsics.CoroutineSingletons;
import com.shadow.kotlin.coroutines.jvm.internal.BaseContinuationImpl;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class SequenceScope<T> {
    public abstract CoroutineSingletons yield(Comparable comparable, BaseContinuationImpl baseContinuationImpl);
}
