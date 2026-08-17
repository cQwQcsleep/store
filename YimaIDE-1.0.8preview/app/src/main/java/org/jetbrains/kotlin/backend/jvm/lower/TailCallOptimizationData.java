package org.jetbrains.kotlin.backend.jvm.lower;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrReturn;
import org.jetbrains.kotlin.ir.expressions.IrSyntheticBody;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.IrWhen;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\tH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/TailCallOptimizationData;", "", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;)V", "getFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "returnsUnit", "", "getReturnsUnit", "()Z", "tailCalls", "", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "getTailCalls", "()Ljava/util/Set;", "findCallsOnTailPositionWithoutImmediateReturn", "", "Lorg/jetbrains/kotlin/ir/IrStatement;", "immediateReturn", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class TailCallOptimizationData {
    private final IrSimpleFunction function;
    private final boolean returnsUnit;
    private final Set<IrCall> tailCalls;

    public TailCallOptimizationData(IrSimpleFunction irSimpleFunction) {
        irSimpleFunction.getClass();
        this.function = irSimpleFunction;
        boolean zIsUnit = IrTypePredicatesKt.isUnit(irSimpleFunction.getReturnType());
        this.returnsUnit = zIsUnit;
        this.tailCalls = new LinkedHashSet();
        IrBlockBody body = irSimpleFunction.getBody();
        if (body instanceof IrBlockBody) {
            IrStatement irStatementFindTailCall = TailCallOptimizationLoweringKt.findTailCall(body.getStatements(), zIsUnit);
            if (irStatementFindTailCall != null) {
                findCallsOnTailPositionWithoutImmediateReturn$default(this, irStatementFindTailCall, false, 1, null);
                return;
            }
            return;
        }
        if (body instanceof IrExpressionBody) {
            findCallsOnTailPositionWithoutImmediateReturn(((IrExpressionBody) body).getExpression(), true);
        } else {
            if ((body instanceof IrSyntheticBody) || body == null) {
                return;
            }
            bu8.a();
            throw null;
        }
    }

    private final void findCallsOnTailPositionWithoutImmediateReturn(IrStatement irStatement, boolean z) {
        if (irStatement instanceof IrCall) {
            IrCall irCall = (IrCall) irStatement;
            if (IrUtilsKt.isSuspend(irCall) && !z && (this.returnsUnit || Intrinsics.areEqual(irCall.getType(), this.function.getReturnType()))) {
                this.tailCalls.add(irStatement);
                return;
            }
        }
        if (irStatement instanceof IrBlock) {
            IrStatement irStatementFindTailCall = TailCallOptimizationLoweringKt.findTailCall(((IrBlock) irStatement).getStatements(), this.returnsUnit);
            if (irStatementFindTailCall != null) {
                findCallsOnTailPositionWithoutImmediateReturn$default(this, irStatementFindTailCall, false, 1, null);
                return;
            }
            return;
        }
        if (irStatement instanceof IrWhen) {
            Iterator it = ((IrWhen) irStatement).getBranches().iterator();
            while (it.hasNext()) {
                findCallsOnTailPositionWithoutImmediateReturn$default(this, ((IrBranch) it.next()).getResult(), false, 1, null);
            }
        } else if (irStatement instanceof IrReturn) {
            findCallsOnTailPositionWithoutImmediateReturn(((IrReturn) irStatement).getValue(), true);
        } else if (irStatement instanceof IrTypeOperatorCall) {
            IrTypeOperatorCall irTypeOperatorCall = (IrTypeOperatorCall) irStatement;
            if (irTypeOperatorCall.getOperator() == IrTypeOperator.IMPLICIT_COERCION_TO_UNIT) {
                findCallsOnTailPositionWithoutImmediateReturn$default(this, irTypeOperatorCall.getArgument(), false, 1, null);
            }
        }
    }

    public static /* synthetic */ void findCallsOnTailPositionWithoutImmediateReturn$default(TailCallOptimizationData tailCallOptimizationData, IrStatement irStatement, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        tailCallOptimizationData.findCallsOnTailPositionWithoutImmediateReturn(irStatement, z);
    }

    public final IrSimpleFunction getFunction() {
        return this.function;
    }

    public final boolean getReturnsUnit() {
        return this.returnsUnit;
    }

    public final Set<IrCall> getTailCalls() {
        return this.tailCalls;
    }
}
