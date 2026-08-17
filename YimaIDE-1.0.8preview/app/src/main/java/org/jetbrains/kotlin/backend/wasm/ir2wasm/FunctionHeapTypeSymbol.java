package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.wasm.ir.WasmHeapType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/FunctionHeapTypeSymbol;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type$FunctionType;", "type", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/util/IdSignature;)V", "getType", "()Lorg/jetbrains/kotlin/ir/util/IdSignature;", "hashCode", "", "equals", "", "other", "", "toString", "", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FunctionHeapTypeSymbol extends WasmHeapType.Type.FunctionType {
    private final IdSignature type;

    public FunctionHeapTypeSymbol(IdSignature idSignature) {
        idSignature.getClass();
        this.type = idSignature;
    }

    public boolean equals(Object other) {
        return (other instanceof FunctionHeapTypeSymbol) && Intrinsics.areEqual(this.type, ((FunctionHeapTypeSymbol) other).type);
    }

    public final IdSignature getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "FunctionHeapTypeSymbol:" + this.type;
    }
}
