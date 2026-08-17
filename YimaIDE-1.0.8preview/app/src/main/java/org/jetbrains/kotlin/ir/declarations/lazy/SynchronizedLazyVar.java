package org.jetbrains.kotlin.ir.declarations.lazy;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.ir.IrLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J%\u0010\u0010\u001a\u00028\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0017J-\u0010\u0018\u001a\u00020\u00192\b\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/lazy/SynchronizedLazyVar;", "T", "Lkotlin/properties/ReadWriteProperty;", "", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "initializer", "Lkotlin/Function0;", "<init>", "(Lorg/jetbrains/kotlin/ir/IrLock;Lkotlin/jvm/functions/Function0;)V", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "isInitialized", "", "_value", "value", "getValue", "()Ljava/lang/Object;", "toString", "", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class SynchronizedLazyVar<T> implements ReadWriteProperty<Object, T> {
    private volatile Object _value;
    private Function0<? extends T> initializer;
    private volatile boolean isInitialized;
    private final IrLock lock;

    public SynchronizedLazyVar(IrLock irLock, Function0<? extends T> function0) {
        irLock.getClass();
        function0.getClass();
        this.lock = irLock;
        this.initializer = function0;
    }

    private final T getValue() {
        T t;
        if (this.isInitialized) {
            return (T) this._value;
        }
        synchronized (this.lock) {
            try {
                if (!this.isInitialized) {
                    Function0<? extends T> function0 = this.initializer;
                    function0.getClass();
                    this._value = function0.invoke();
                    this.isInitialized = true;
                    this.initializer = null;
                }
                t = (T) this._value;
            } catch (Throwable th) {
                throw th;
            }
        }
        return t;
    }

    public final IrLock getLock() {
        return this.lock;
    }

    public void setValue(Object thisRef, KProperty<?> property, T value) {
        property.getClass();
        synchronized (this.lock) {
            this._value = value;
            this.isInitialized = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    public String toString() {
        return this.isInitialized ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public T getValue(Object thisRef, KProperty<?> property) {
        property.getClass();
        return getValue();
    }
}
