package org.jetbrains.kotlin.backend.common.lower.loops;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/loops/ComparableRangeInfo;", "Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;", "start", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "endInclusive", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/expressions/IrExpression;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)V", "getStart", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "getEndInclusive", "asReversed", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComparableRangeInfo extends HeaderInfo {
    private final IrExpression endInclusive;
    private final IrExpression start;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComparableRangeInfo(IrExpression irExpression, IrExpression irExpression2) {
        super(null);
        irExpression.getClass();
        irExpression2.getClass();
        this.start = irExpression;
        this.endInclusive = irExpression2;
    }

    @Override // org.jetbrains.kotlin.backend.common.lower.loops.HeaderInfo
    public HeaderInfo asReversed() {
        return null;
    }

    public final IrExpression getEndInclusive() {
        return this.endInclusive;
    }

    public final IrExpression getStart() {
        return this.start;
    }
}
