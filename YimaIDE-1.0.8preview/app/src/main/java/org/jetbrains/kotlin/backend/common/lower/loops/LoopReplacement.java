package org.jetbrains.kotlin.backend.common.lower.loops;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrLoop;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/loops/LoopReplacement;", "", "newLoop", "Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "replacementExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrLoop;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)V", "getNewLoop", "()Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "getReplacementExpression", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class LoopReplacement {
    private final IrLoop newLoop;
    private final IrExpression replacementExpression;

    public LoopReplacement(IrLoop irLoop, IrExpression irExpression) {
        irLoop.getClass();
        irExpression.getClass();
        this.newLoop = irLoop;
        this.replacementExpression = irExpression;
    }

    public static /* synthetic */ LoopReplacement copy$default(LoopReplacement loopReplacement, IrLoop irLoop, IrExpression irExpression, int i, Object obj) {
        if ((i & 1) != 0) {
            irLoop = loopReplacement.newLoop;
        }
        if ((i & 2) != 0) {
            irExpression = loopReplacement.replacementExpression;
        }
        return loopReplacement.copy(irLoop, irExpression);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrLoop getNewLoop() {
        return this.newLoop;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final IrExpression getReplacementExpression() {
        return this.replacementExpression;
    }

    public final LoopReplacement copy(IrLoop newLoop, IrExpression replacementExpression) {
        newLoop.getClass();
        replacementExpression.getClass();
        return new LoopReplacement(newLoop, replacementExpression);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoopReplacement)) {
            return false;
        }
        LoopReplacement loopReplacement = (LoopReplacement) other;
        return Intrinsics.areEqual(this.newLoop, loopReplacement.newLoop) && Intrinsics.areEqual(this.replacementExpression, loopReplacement.replacementExpression);
    }

    public final IrLoop getNewLoop() {
        return this.newLoop;
    }

    public final IrExpression getReplacementExpression() {
        return this.replacementExpression;
    }

    public int hashCode() {
        return (this.newLoop.hashCode() * 31) + this.replacementExpression.hashCode();
    }

    public String toString() {
        return "LoopReplacement(newLoop=" + this.newLoop + ", replacementExpression=" + this.replacementExpression + Util.C_PARAM_END;
    }
}
