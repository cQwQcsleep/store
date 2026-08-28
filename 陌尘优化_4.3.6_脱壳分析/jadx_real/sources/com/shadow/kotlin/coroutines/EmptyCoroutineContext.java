package com.shadow.kotlin.coroutines;

import com.shadow.kotlin.io.CloseableKt;
import java.io.Serializable;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class EmptyCoroutineContext implements CoroutineContext, Serializable {
    public static final EmptyCoroutineContext INSTANCE = new EmptyCoroutineContext();
    private static final long serialVersionUID = 0;

    private final Object readResolve() {
        return INSTANCE;
    }

    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        CloseableKt.checkNotNullParameter(function2, "operation");
        return r;
    }

    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        CloseableKt.checkNotNullParameter(key, "key");
        return null;
    }

    public int hashCode() {
        return 0;
    }

    public kotlin.coroutines.CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        CloseableKt.checkNotNullParameter(key, "key");
        return this;
    }

    public CoroutineContext plus(CoroutineContext coroutineContext) {
        CloseableKt.checkNotNullParameter(coroutineContext, "context");
        return coroutineContext;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
