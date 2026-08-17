package org.jetbrains.kotlin.backend.jvm.lower.indy;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrRichFunctionReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/indy/SamDelegatingLambdaBlock;", "", "rootExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "ref", "Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;)V", "getRootExpression", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "getRef", "()Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SamDelegatingLambdaBlock {
    private final IrRichFunctionReference ref;
    private final IrExpression rootExpression;

    public SamDelegatingLambdaBlock(IrExpression irExpression, IrRichFunctionReference irRichFunctionReference) {
        irExpression.getClass();
        irRichFunctionReference.getClass();
        this.rootExpression = irExpression;
        this.ref = irRichFunctionReference;
    }

    public final IrRichFunctionReference getRef() {
        return this.ref;
    }

    public final IrExpression getRootExpression() {
        return this.rootExpression;
    }
}
