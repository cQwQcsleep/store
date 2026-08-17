package com.intellij.util.concurrency;

import com.intellij.util.ObjectUtils;
import com.intellij.util.concurrency.SynchronizedClearableLazy;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u001e*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u001eB\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\n\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\u0004\u0018\u00018\u00002\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00102\b\u0010\r\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u0010\u0011J\r\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\r\u0010\u001b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u000bJ\u0013\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00028\u0000¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00018\u00008\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0012\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000bR$\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u00008F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\u0018¨\u0006\u001f"}, d2 = {"Lcom/intellij/util/concurrency/SynchronizedClearableLazy;", "T", "Ljava/util/function/Supplier;", "initializer", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "computedValue", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "notYetInitialized", "()Ljava/lang/Object;", "nullize", "t", "(Ljava/lang/Object;)Ljava/lang/Object;", "isInitialized", "", "(Ljava/lang/Object;)Z", "valueIfInitialized", "getValueIfInitialized", "get", "value", "getValue", "setValue", "(Ljava/lang/Object;)V", "toString", "", "drop", "compareAndDrop", "expectedValue", "Companion", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SynchronizedClearableLazy<T> implements Supplier<T> {
    private static final Object NOT_YET_INITIALIZED;
    private final AtomicReference<T> computedValue;
    private final Function0<T> initializer;

    static {
        Object objSentinel = ObjectUtils.sentinel("Not yet initialized");
        objSentinel.getClass();
        NOT_YET_INITIALIZED = objSentinel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SynchronizedClearableLazy(Function0<? extends T> function0) {
        function0.getClass();
        this.initializer = function0;
        this.computedValue = new AtomicReference<>(notYetInitialized());
    }

    public static Object a(SynchronizedClearableLazy synchronizedClearableLazy, Object obj) {
        return synchronizedClearableLazy.isInitialized(obj) ? obj : synchronizedClearableLazy.initializer.invoke();
    }

    private final boolean isInitialized(T t) {
        return t != NOT_YET_INITIALIZED;
    }

    private final T notYetInitialized() {
        return (T) NOT_YET_INITIALIZED;
    }

    private final T nullize(T t) {
        if (isInitialized(t)) {
            return t;
        }
        return null;
    }

    public final T drop() {
        return nullize(this.computedValue.getAndSet(notYetInitialized()));
    }

    @Override // java.util.function.Supplier
    public T get() {
        return getValue();
    }

    public final T getValue() {
        T tUpdateAndGet;
        T t = this.computedValue.get();
        if (isInitialized(t)) {
            return t;
        }
        synchronized (this) {
            tUpdateAndGet = this.computedValue.updateAndGet(new UnaryOperator() { // from class: yyd
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SynchronizedClearableLazy.a(this.b, obj);
                }
            });
        }
        return tUpdateAndGet;
    }

    public String toString() {
        String string = this.computedValue.toString();
        string.getClass();
        return string;
    }
}
