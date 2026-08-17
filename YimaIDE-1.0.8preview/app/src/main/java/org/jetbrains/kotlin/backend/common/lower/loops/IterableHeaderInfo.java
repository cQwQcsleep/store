package org.jetbrains.kotlin.backend.common.lower.loops;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/loops/IterableHeaderInfo;", "Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;", "iteratorVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrVariable;)V", "getIteratorVariable", "()Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "asReversed", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IterableHeaderInfo extends HeaderInfo {
    private final IrVariable iteratorVariable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IterableHeaderInfo(IrVariable irVariable) {
        super(null);
        irVariable.getClass();
        this.iteratorVariable = irVariable;
    }

    @Override // org.jetbrains.kotlin.backend.common.lower.loops.HeaderInfo
    public HeaderInfo asReversed() {
        return null;
    }

    public final IrVariable getIteratorVariable() {
        return this.iteratorVariable;
    }
}
