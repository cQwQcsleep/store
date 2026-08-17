package org.jetbrains.kotlin.backend.jvm.ir;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/ir/IrArrayElement;", "", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "isSpread", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Z)V", "getExpression", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "()Z", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class IrArrayElement {
    private final IrExpression expression;
    private final boolean isSpread;

    public IrArrayElement(IrExpression irExpression, boolean z) {
        irExpression.getClass();
        this.expression = irExpression;
        this.isSpread = z;
    }

    public final IrExpression getExpression() {
        return this.expression;
    }

    /* JADX INFO: renamed from: isSpread, reason: from getter */
    public final boolean getIsSpread() {
        return this.isSpread;
    }
}
