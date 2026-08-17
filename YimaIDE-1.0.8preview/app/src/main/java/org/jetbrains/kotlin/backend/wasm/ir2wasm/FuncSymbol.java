package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.wasm.ir.WasmImmediate;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/FuncSymbol;", "Lorg/jetbrains/kotlin/wasm/ir/WasmImmediate$FuncIdx;", "value", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/util/IdSignature;)V", "getValue", "()Lorg/jetbrains/kotlin/ir/util/IdSignature;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FuncSymbol extends WasmImmediate.FuncIdx {
    private final IdSignature value;

    public FuncSymbol(IdSignature idSignature) {
        idSignature.getClass();
        this.value = idSignature;
    }

    public final IdSignature getValue() {
        return this.value;
    }
}
