package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.builders.Scope;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ScopeWithIr;", "", "scope", "Lorg/jetbrains/kotlin/ir/builders/Scope;", "irElement", "Lorg/jetbrains/kotlin/ir/IrElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/builders/Scope;Lorg/jetbrains/kotlin/ir/IrElement;)V", "getScope", "()Lorg/jetbrains/kotlin/ir/builders/Scope;", "getIrElement", "()Lorg/jetbrains/kotlin/ir/IrElement;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class ScopeWithIr {
    private final IrElement irElement;
    private final Scope scope;

    public ScopeWithIr(Scope scope, IrElement irElement) {
        scope.getClass();
        irElement.getClass();
        this.scope = scope;
        this.irElement = irElement;
    }

    public final IrElement getIrElement() {
        return this.irElement;
    }

    public final Scope getScope() {
        return this.scope;
    }
}
