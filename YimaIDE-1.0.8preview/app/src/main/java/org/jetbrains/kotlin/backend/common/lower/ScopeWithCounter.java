package org.jetbrains.kotlin.backend.common.lower;

import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/ScopeWithCounter;", "", "irElement", "Lorg/jetbrains/kotlin/ir/IrElement;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "getIrElement", "()Lorg/jetbrains/kotlin/ir/IrElement;", "counter", "", "getCounter", "()I", "setCounter", "(I)V", "usedLocalFunctionNames", "", "Lorg/jetbrains/kotlin/name/Name;", "getUsedLocalFunctionNames", "()Ljava/util/Set;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ScopeWithCounter {
    private int counter;
    private final IrElement irElement;
    private final Set<Name> usedLocalFunctionNames;

    public ScopeWithCounter(IrElement irElement) {
        irElement.getClass();
        this.irElement = irElement;
        this.usedLocalFunctionNames = new HashSet();
    }

    public final int getCounter() {
        return this.counter;
    }

    public final IrElement getIrElement() {
        return this.irElement;
    }

    public final Set<Name> getUsedLocalFunctionNames() {
        return this.usedLocalFunctionNames;
    }

    public final void setCounter(int i) {
        this.counter = i;
    }
}
