package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u00028\u00008VX\u0096\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmSymbol;", "T", "", "Lorg/jetbrains/kotlin/wasm/ir/WasmSymbolReadOnly;", "owner", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/Object;)V", "_owner", "getOwner$annotations", "()V", "getOwner", "()Ljava/lang/Object;", "bind", "", "value", "isBound", "", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmSymbol<T> implements WasmSymbolReadOnly<T> {
    private Object _owner;

    public /* synthetic */ WasmSymbol(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj);
    }

    public static /* synthetic */ void getOwner$annotations() {
    }

    public final void bind(Object value) {
        value.getClass();
        this._owner = value;
    }

    public boolean equals(Object other) {
        return (other instanceof WasmSymbol) && Intrinsics.areEqual(this._owner, ((WasmSymbol) other)._owner);
    }

    public T getOwner() {
        T t = (T) this._owner;
        if (t == null) {
            t = null;
        }
        if (t != null) {
            return t;
        }
        w04.a("Unbound wasm symbol ", this);
        return null;
    }

    public int hashCode() {
        Object obj = this._owner;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public final boolean isBound() {
        return this._owner != null;
    }

    public String toString() {
        String string;
        Object obj = this._owner;
        return (obj == null || (string = obj.toString()) == null) ? "UNBOUND-WASM-SYMBOL" : string;
    }

    public WasmSymbol(T t) {
        this._owner = t;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public WasmSymbol() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        this(defaultConstructorMarker, 1, defaultConstructorMarker);
    }
}
