package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrWhen;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0007"}, d2 = {"parseSafeCall", "Lorg/jetbrains/kotlin/backend/jvm/lower/SafeCallInfo;", "Lorg/jetbrains/kotlin/ir/expressions/IrBlock;", "irBuiltIns", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "parseElvis", "Lorg/jetbrains/kotlin/backend/jvm/lower/ElvisInfo;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmSafeCallChainFoldingLoweringKt {
    public static final ElvisInfo parseElvis(IrBlock irBlock, IrBuiltIns irBuiltIns) {
        IrExpression initializer;
        irBlock.getClass();
        irBuiltIns.getClass();
        if (irBlock.getStatements().size() != 2) {
            return null;
        }
        Object obj = irBlock.getStatements().get(0);
        IrVariable irVariable = obj instanceof IrVariable ? (IrVariable) obj : null;
        if (irVariable == null) {
            return null;
        }
        Object obj2 = irBlock.getStatements().get(1);
        IrWhen irWhen = obj2 instanceof IrWhen ? (IrWhen) obj2 : null;
        if (irWhen == null || irWhen.getBranches().size() != 2 || (initializer = irVariable.getInitializer()) == null) {
            return null;
        }
        IrBranch irBranch = (IrBranch) irWhen.getBranches().get(0);
        IrCall condition = irBranch.getCondition();
        if (!(condition instanceof IrCall)) {
            return null;
        }
        IrCall irCall = condition;
        if (!Intrinsics.areEqual(irCall.getSymbol(), irBuiltIns.getEqeqSymbol())) {
            return null;
        }
        IrGetValue irGetValue = (IrExpression) irCall.getArguments().get(0);
        if (!(irGetValue instanceof IrGetValue) || !Intrinsics.areEqual(irGetValue.getSymbol(), irVariable.getSymbol())) {
            return null;
        }
        IrConst irConst = (IrExpression) irCall.getArguments().get(1);
        if (!(irConst instanceof IrConst) || irConst.getValue() != null) {
            return null;
        }
        IrExpression result = irBranch.getResult();
        IrGetValue result2 = ((IrBranch) irWhen.getBranches().get(1)).getResult();
        if ((result2 instanceof IrGetValue) && Intrinsics.areEqual(result2.getSymbol(), irVariable.getSymbol())) {
            return new ElvisInfo(irBlock, irVariable, initializer, result);
        }
        return null;
    }

    public static final SafeCallInfo parseSafeCall(IrBlock irBlock, IrBuiltIns irBuiltIns) {
        irBlock.getClass();
        irBuiltIns.getClass();
        if (irBlock.getStatements().size() != 2) {
            return null;
        }
        Object obj = irBlock.getStatements().get(0);
        IrVariable irVariable = obj instanceof IrVariable ? (IrVariable) obj : null;
        if (irVariable == null) {
            return null;
        }
        Object obj2 = irBlock.getStatements().get(1);
        IrWhen irWhen = obj2 instanceof IrWhen ? (IrWhen) obj2 : null;
        if (irWhen == null || irWhen.getBranches().size() != 2) {
            return null;
        }
        IrBranch irBranch = (IrBranch) irWhen.getBranches().get(0);
        IrCall condition = irBranch.getCondition();
        if (!(condition instanceof IrCall)) {
            return null;
        }
        IrCall irCall = condition;
        if (!Intrinsics.areEqual(irCall.getSymbol(), irBuiltIns.getEqeqSymbol())) {
            return null;
        }
        IrGetValue irGetValue = (IrExpression) irCall.getArguments().get(0);
        if (!(irGetValue instanceof IrGetValue) || !Intrinsics.areEqual(irGetValue.getSymbol(), irVariable.getSymbol())) {
            return null;
        }
        IrConst irConst = (IrExpression) irCall.getArguments().get(1);
        if (!(irConst instanceof IrConst) || irConst.getValue() != null) {
            return null;
        }
        IrConst result = irBranch.getResult();
        if ((result instanceof IrConst) && result.getValue() == null) {
            return new SafeCallInfo(irBlock, irVariable, irBranch, (IrBranch) irWhen.getBranches().get(1));
        }
        return null;
    }
}
