package com.shadow.kotlin;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import java.io.Serializable;
import kotlin.jvm.functions.Function0;

/* loaded from: /workspace/unpacked/classes2.dex */
final class SynchronizedLazyImpl<T> implements kotlin.Lazy<T>, Serializable {
    private volatile Object _value;
    private Function0<? extends T> initializer;
    private final Object lock;

    public SynchronizedLazyImpl(Function0<? extends T> function0, Object obj) {
        CloseableKt.checkNotNullParameter(function0, "initializer");
        this.initializer = function0;
        this._value = Unit.INSTANCE$1;
        this.lock = obj == null ? this : obj;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    public T getValue() {
        T t;
        T t2 = (T) this._value;
        Unit unit = Unit.INSTANCE$1;
        if (t2 != unit) {
            return t2;
        }
        synchronized (this.lock) {
            t = (T) this._value;
            if (t == unit) {
                Function0<? extends T> function0 = this.initializer;
                CloseableKt.checkNotNull(function0);
                t = (T) function0.invoke();
                this._value = t;
                this.initializer = null;
            }
        }
        return t;
    }

    public boolean isInitialized() {
        return this._value != Unit.INSTANCE$1;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ SynchronizedLazyImpl(com.shadow.kotlin.jvm.functions.Function0 function0, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, (i & 2) != 0 ? null : obj);
    }
}
