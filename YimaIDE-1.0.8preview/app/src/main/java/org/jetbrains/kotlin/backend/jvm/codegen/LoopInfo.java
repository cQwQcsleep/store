package org.jetbrains.kotlin.backend.jvm.codegen;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrLoop;
import org.jetbrains.org.objectweb.asm.Label;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/LoopInfo;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/ExpressionInfo;", "loop", "Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "continueLabel", "Lorg/jetbrains/org/objectweb/asm/Label;", "breakLabel", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrLoop;Lorg/jetbrains/org/objectweb/asm/Label;Lorg/jetbrains/org/objectweb/asm/Label;)V", "getLoop", "()Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "getContinueLabel", "()Lorg/jetbrains/org/objectweb/asm/Label;", "getBreakLabel", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LoopInfo extends ExpressionInfo {
    private final Label breakLabel;
    private final Label continueLabel;
    private final IrLoop loop;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoopInfo(IrLoop irLoop, Label label, Label label2) {
        super(null);
        irLoop.getClass();
        label.getClass();
        label2.getClass();
        this.loop = irLoop;
        this.continueLabel = label;
        this.breakLabel = label2;
    }

    public final Label getBreakLabel() {
        return this.breakLabel;
    }

    public final Label getContinueLabel() {
        return this.continueLabel;
    }

    public final IrLoop getLoop() {
        return this.loop;
    }
}
