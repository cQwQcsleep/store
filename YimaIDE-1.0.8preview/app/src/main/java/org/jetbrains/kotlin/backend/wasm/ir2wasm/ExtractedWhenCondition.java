package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrConst;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ExtractedWhenCondition;", "", "condition", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "const", "Lorg/jetbrains/kotlin/ir/expressions/IrConst;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;Lorg/jetbrains/kotlin/ir/expressions/IrConst;)V", "getCondition", "()Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "getConst", "()Lorg/jetbrains/kotlin/ir/expressions/IrConst;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ExtractedWhenCondition {
    private final IrCall condition;
    private final IrConst const;

    public ExtractedWhenCondition(IrCall irCall, IrConst irConst) {
        irCall.getClass();
        irConst.getClass();
        this.condition = irCall;
        this.const = irConst;
    }

    public final IrCall getCondition() {
        return this.condition;
    }

    public final IrConst getConst() {
        return this.const;
    }
}
