package org.jetbrains.kotlin.backend.common.lower.loops;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/loops/InitializerCallReplacer;", "Lorg/jetbrains/kotlin/ir/visitors/IrElementTransformerVoid;", "replacement", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)V", "initializerCall", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "getInitializerCall", "()Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "setInitializerCall", "(Lorg/jetbrains/kotlin/ir/expressions/IrCall;)V", "visitCall", "expression", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InitializerCallReplacer extends IrElementTransformerVoid {
    private IrCall initializerCall;
    private final IrExpression replacement;

    public InitializerCallReplacer(IrExpression irExpression) {
        irExpression.getClass();
        this.replacement = irExpression;
    }

    public final IrCall getInitializerCall() {
        return this.initializerCall;
    }

    public final void setInitializerCall(IrCall irCall) {
        this.initializerCall = irCall;
    }

    public IrExpression visitCall(IrCall expression) {
        expression.getClass();
        if (this.initializerCall == null) {
            this.initializerCall = expression;
            return this.replacement;
        }
        IrCall irCall = this.initializerCall;
        irCall.getClass();
        co4.a("Multiple initializer calls found. First: ", RenderIrElementKt.render$default(irCall, (DumpIrTreeOptions) null, 1, (Object) null), "\nSecond: ", RenderIrElementKt.render$default(expression, (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }
}
