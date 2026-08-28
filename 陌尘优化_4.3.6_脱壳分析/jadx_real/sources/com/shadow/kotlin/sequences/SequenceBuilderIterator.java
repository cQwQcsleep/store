package com.shadow.kotlin.sequences;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.Result;
import com.shadow.kotlin.coroutines.CoroutineContext;
import com.shadow.kotlin.coroutines.EmptyCoroutineContext;
import com.shadow.kotlin.coroutines.intrinsics.CoroutineSingletons;
import com.shadow.kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import com.shadow.kotlin.io.CloseableKt;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: /workspace/unpacked/classes2.dex */
final class SequenceBuilderIterator<T> extends kotlin.sequences.SequenceScope<T> implements Iterator<T>, Continuation<Unit>, KMappedMarker {
    private Continuation<? super Unit> nextStep;
    private Comparable nextValue;
    private int state;

    private final RuntimeException exceptionalState() {
        int i = this.state;
        if (i == 4) {
            return new NoSuchElementException();
        }
        if (i == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.state);
    }

    public final CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i;
        while (true) {
            i = this.state;
            if (i != 0) {
                break;
            }
            this.state = 5;
            Continuation<? super Unit> continuation = this.nextStep;
            CloseableKt.checkNotNull(continuation);
            this.nextStep = null;
            continuation.resumeWith(Result.m1constructorimpl(com.shadow.kotlin.Unit.INSTANCE));
        }
        if (i == 1) {
            CloseableKt.checkNotNull(null);
            throw null;
        }
        if (i == 2 || i == 3) {
            return true;
        }
        if (i == 4) {
            return false;
        }
        throw exceptionalState();
    }

    @Override // java.util.Iterator
    public final T next() {
        int i = this.state;
        if (i == 0 || i == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i == 2) {
            this.state = 1;
            CloseableKt.checkNotNull(null);
            throw null;
        }
        if (i != 3) {
            throw exceptionalState();
        }
        this.state = 0;
        T t = (T) this.nextValue;
        this.nextValue = null;
        return t;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void resumeWith(Object obj) throws Throwable {
        LazyKt.throwOnFailure(obj);
        this.state = 4;
    }

    public final void setNextStep(Continuation<? super Unit> continuation) {
        this.nextStep = continuation;
    }

    public final CoroutineSingletons yield(Comparable comparable, BaseContinuationImpl baseContinuationImpl) {
        this.nextValue = comparable;
        this.state = 3;
        this.nextStep = baseContinuationImpl;
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }
}
