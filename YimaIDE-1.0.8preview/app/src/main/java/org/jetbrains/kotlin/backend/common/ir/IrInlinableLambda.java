package org.jetbrains.kotlin.backend.common.ir;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ir/IrInlinableLambda;", "Lorg/jetbrains/kotlin/backend/common/ir/IrInlinable;", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "boundArguments", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Ljava/util/List;)V", "getFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getBoundArguments", "()Ljava/util/List;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrInlinableLambda extends IrInlinable {
    private final List<IrValueDeclaration> boundArguments;
    private final IrSimpleFunction function;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrInlinableLambda(IrSimpleFunction irSimpleFunction, List<? extends IrValueDeclaration> list) {
        super(null);
        irSimpleFunction.getClass();
        list.getClass();
        this.function = irSimpleFunction;
        this.boundArguments = list;
    }

    public final List<IrValueDeclaration> getBoundArguments() {
        return this.boundArguments;
    }

    public final IrSimpleFunction getFunction() {
        return this.function;
    }
}
