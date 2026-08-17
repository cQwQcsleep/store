package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ExtractedWhenBranch;", "", "conditions", "", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ExtractedWhenCondition;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/List;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)V", "getConditions", "()Ljava/util/List;", "getExpression", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ExtractedWhenBranch {
    private final List<ExtractedWhenCondition> conditions;
    private final IrExpression expression;

    public ExtractedWhenBranch(List<ExtractedWhenCondition> list, IrExpression irExpression) {
        list.getClass();
        irExpression.getClass();
        this.conditions = list;
        this.expression = irExpression;
    }

    public final List<ExtractedWhenCondition> getConditions() {
        return this.conditions;
    }

    public final IrExpression getExpression() {
        return this.expression;
    }
}
