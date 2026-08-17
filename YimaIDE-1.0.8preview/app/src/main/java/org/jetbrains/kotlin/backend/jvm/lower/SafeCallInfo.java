package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBranch;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/SafeCallInfo;", "", "block", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "tmpVal", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "ifNullBranch", "Lorg/jetbrains/kotlin/ir/expressions/IrBranch;", "ifNotNullBranch", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrBlock;Lorg/jetbrains/kotlin/ir/declarations/IrVariable;Lorg/jetbrains/kotlin/ir/expressions/IrBranch;Lorg/jetbrains/kotlin/ir/expressions/IrBranch;)V", "getBlock", "()Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "getTmpVal", "()Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "getIfNullBranch", "()Lorg/jetbrains/kotlin/ir/expressions/IrBranch;", "getIfNotNullBranch", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SafeCallInfo {
    private final IrBlock block;
    private final IrBranch ifNotNullBranch;
    private final IrBranch ifNullBranch;
    private final IrVariable tmpVal;

    public SafeCallInfo(IrBlock irBlock, IrVariable irVariable, IrBranch irBranch, IrBranch irBranch2) {
        irBlock.getClass();
        irVariable.getClass();
        irBranch.getClass();
        irBranch2.getClass();
        this.block = irBlock;
        this.tmpVal = irVariable;
        this.ifNullBranch = irBranch;
        this.ifNotNullBranch = irBranch2;
    }

    public final IrBlock getBlock() {
        return this.block;
    }

    public final IrBranch getIfNotNullBranch() {
        return this.ifNotNullBranch;
    }

    public final IrBranch getIfNullBranch() {
        return this.ifNullBranch;
    }

    public final IrVariable getTmpVal() {
        return this.tmpVal;
    }
}
