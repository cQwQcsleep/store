package org.jetbrains.kotlin.utils;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B#\u0012\u0006\u0010\u0004\u001a\u00028\u0001\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00028\u00012\u0006\u0010\f\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\rR\u0010\u0010\u0004\u001a\u00028\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\"\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/utils/CallOnceFunction;", "F", "T", "Lkotlin/Function1;", "defaultValue", "delegate", "<init>", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "Ljava/lang/Object;", "functionRef", "Ljava/util/concurrent/atomic/AtomicReference;", "invoke", "p1", "(Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallOnceFunction<F, T> implements Function1<F, T> {
    private final T defaultValue;
    private final AtomicReference<Function1<F, T>> functionRef;

    public CallOnceFunction(T t, Function1<? super F, ? extends T> function1) {
        function1.getClass();
        this.defaultValue = t;
        this.functionRef = new AtomicReference<>(function1);
    }

    public T invoke(F p1) {
        T t;
        Function1<F, T> andSet = this.functionRef.getAndSet(null);
        return (andSet == null || (t = (T) andSet.invoke(p1)) == null) ? this.defaultValue : t;
    }
}
