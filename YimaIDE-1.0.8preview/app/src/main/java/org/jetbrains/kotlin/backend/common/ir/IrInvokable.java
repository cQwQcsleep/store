package org.jetbrains.kotlin.backend.common.ir;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ir/IrInvokable;", "Lorg/jetbrains/kotlin/backend/common/ir/IrInlinable;", "invokable", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;)V", "getInvokable", "()Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrInvokable extends IrInlinable {
    private final IrValueDeclaration invokable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrInvokable(IrValueDeclaration irValueDeclaration) {
        super(null);
        irValueDeclaration.getClass();
        this.invokable = irValueDeclaration;
    }

    public final IrValueDeclaration getInvokable() {
        return this.invokable;
    }
}
