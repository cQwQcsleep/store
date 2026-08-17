package org.jetbrains.kotlin.utils.concurrent.block;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u000e\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0003\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/utils/concurrent/block/LockedClearableLazyValue;", "T", "", "lock", "init", "Lkotlin/Function0;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)V", "getLock", "()Ljava/lang/Object;", "getInit", "()Lkotlin/jvm/functions/Function0;", "value", "Ljava/lang/Object;", "get", "drop", "", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LockedClearableLazyValue<T> {
    private final Function0<T> init;
    private final Object lock;
    private volatile T value;

    /* JADX WARN: Multi-variable type inference failed */
    public LockedClearableLazyValue(Object obj, Function0<? extends T> function0) {
        obj.getClass();
        function0.getClass();
        this.lock = obj;
        this.init = function0;
    }

    public final void drop() {
        synchronized (this.lock) {
            this.value = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final T get() {
        T t;
        T t2 = this.value;
        if (t2 != null) {
            return t2;
        }
        synchronized (this.lock) {
            t = this.value;
            if (t == null) {
                t = (T) this.init.invoke();
                this.value = t;
            }
        }
        return t;
    }

    public final Function0<T> getInit() {
        return this.init;
    }

    public final Object getLock() {
        return this.lock;
    }
}
