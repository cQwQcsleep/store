package org.jetbrains.kotlin.ir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0005"}, d2 = {"isAssignmentOperatorWithResult", "", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "isAssignmentOperator", "isComparisonOperator", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IrStatementOriginKt {
    public static final boolean isAssignmentOperator(IrStatementOrigin irStatementOrigin) {
        irStatementOrigin.getClass();
        IrStatementOrigin.Companion companion = IrStatementOrigin.INSTANCE;
        if (Intrinsics.areEqual(irStatementOrigin, companion.getEQ()) || Intrinsics.areEqual(irStatementOrigin, companion.getPLUSEQ()) || Intrinsics.areEqual(irStatementOrigin, companion.getMINUSEQ()) || Intrinsics.areEqual(irStatementOrigin, companion.getMULTEQ()) || Intrinsics.areEqual(irStatementOrigin, companion.getDIVEQ()) || Intrinsics.areEqual(irStatementOrigin, companion.getPERCEQ())) {
            return true;
        }
        return isAssignmentOperatorWithResult(irStatementOrigin);
    }

    public static final boolean isAssignmentOperatorWithResult(IrStatementOrigin irStatementOrigin) {
        irStatementOrigin.getClass();
        IrStatementOrigin.Companion companion = IrStatementOrigin.INSTANCE;
        return Intrinsics.areEqual(irStatementOrigin, companion.getPREFIX_INCR()) || Intrinsics.areEqual(irStatementOrigin, companion.getPREFIX_DECR()) || Intrinsics.areEqual(irStatementOrigin, companion.getPOSTFIX_INCR()) || Intrinsics.areEqual(irStatementOrigin, companion.getPOSTFIX_DECR());
    }

    public static final boolean isComparisonOperator(IrStatementOrigin irStatementOrigin) {
        irStatementOrigin.getClass();
        IrStatementOrigin.Companion companion = IrStatementOrigin.INSTANCE;
        return Intrinsics.areEqual(irStatementOrigin, companion.getLT()) || Intrinsics.areEqual(irStatementOrigin, companion.getGT()) || Intrinsics.areEqual(irStatementOrigin, companion.getLTEQ()) || Intrinsics.areEqual(irStatementOrigin, companion.getGTEQ());
    }
}
