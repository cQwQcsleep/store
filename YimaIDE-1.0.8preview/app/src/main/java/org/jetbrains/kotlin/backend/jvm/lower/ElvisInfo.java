package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/ElvisInfo;", "", "block", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "tmpVal", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "elvisLhs", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "elvisRhs", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrBlock;Lorg/jetbrains/kotlin/ir/declarations/IrVariable;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)V", "getBlock", "()Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "getTmpVal", "()Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "getElvisLhs", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "getElvisRhs", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ElvisInfo {
    private final IrBlock block;
    private final IrExpression elvisLhs;
    private final IrExpression elvisRhs;
    private final IrVariable tmpVal;

    public ElvisInfo(IrBlock irBlock, IrVariable irVariable, IrExpression irExpression, IrExpression irExpression2) {
        irBlock.getClass();
        irVariable.getClass();
        irExpression.getClass();
        irExpression2.getClass();
        this.block = irBlock;
        this.tmpVal = irVariable;
        this.elvisLhs = irExpression;
        this.elvisRhs = irExpression2;
    }

    public final IrBlock getBlock() {
        return this.block;
    }

    public final IrExpression getElvisLhs() {
        return this.elvisLhs;
    }

    public final IrExpression getElvisRhs() {
        return this.elvisRhs;
    }

    public final IrVariable getTmpVal() {
        return this.tmpVal;
    }
}
