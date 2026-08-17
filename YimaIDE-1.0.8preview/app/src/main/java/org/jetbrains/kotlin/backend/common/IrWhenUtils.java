package org.jetbrains.kotlin.backend.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrWhen;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\u0005\"\n\b\u0000\u0010\u0006\u0018\u0001*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0086\bJ2\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r0\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/IrWhenUtils;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "matchConditions", "", "T", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "ororSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "condition", "leafConditionPredicate", "Lkotlin/Function1;", "", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrWhenUtils {
    public static final IrWhenUtils INSTANCE = new IrWhenUtils();

    private IrWhenUtils() {
    }

    public final List<IrExpression> matchConditions(IrFunctionSymbol ororSymbol, IrExpression condition, Function1<? super IrExpression, Boolean> leafConditionPredicate) {
        List<IrExpression> listMatchConditions;
        ororSymbol.getClass();
        condition.getClass();
        leafConditionPredicate.getClass();
        if (condition instanceof IrWhen) {
            IrWhen irWhen = (IrWhen) condition;
            if (Intrinsics.areEqual(irWhen.getOrigin(), IrStatementOrigin.Companion.getWHEN_COMMA())) {
                IrTypePredicatesKt.isBoolean(condition.getType());
                ArrayList arrayList = new ArrayList();
                for (IrBranch irBranch : irWhen.getBranches()) {
                    if (IrUtilsKt.isElseBranch(irBranch)) {
                        IrUtilsKt.isTrueConst(irBranch.getCondition());
                        listMatchConditions = matchConditions(ororSymbol, irBranch.getResult(), leafConditionPredicate);
                        if (listMatchConditions == null) {
                            return null;
                        }
                    } else if (!IrUtilsKt.isTrueConst(irBranch.getResult()) || (listMatchConditions = matchConditions(ororSymbol, irBranch.getCondition(), leafConditionPredicate)) == null) {
                        return null;
                    }
                    CollectionsKt.addAll(arrayList, listMatchConditions);
                }
                if (arrayList.isEmpty()) {
                    return null;
                }
                return arrayList;
            }
        }
        if (condition instanceof IrCall) {
            IrCall irCall = (IrCall) condition;
            if (Intrinsics.areEqual(irCall.getSymbol(), ororSymbol)) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<IrExpression> it = irCall.getArguments().iterator();
                it.getClass();
                while (it.hasNext()) {
                    IrExpression next = it.next();
                    next.getClass();
                    List<IrExpression> listMatchConditions2 = matchConditions(ororSymbol, next, leafConditionPredicate);
                    if (listMatchConditions2 == null) {
                        return null;
                    }
                    CollectionsKt.addAll(arrayList2, listMatchConditions2);
                }
                if (arrayList2.isEmpty()) {
                    return null;
                }
                return arrayList2;
            }
        }
        if (((Boolean) leafConditionPredicate.invoke(condition)).booleanValue()) {
            return CollectionsKt.arrayListOf(new IrExpression[]{condition});
        }
        return null;
    }

    public final /* synthetic */ <T extends IrExpression> List<T> matchConditions(IrFunctionSymbol ororSymbol, IrExpression condition) {
        ororSymbol.getClass();
        condition.getClass();
        Intrinsics.needClassReification();
        List<IrExpression> listMatchConditions = matchConditions(ororSymbol, condition, new Function1<IrExpression, Boolean>() { // from class: org.jetbrains.kotlin.backend.common.IrWhenUtils.matchConditions.1
            public final Boolean invoke(IrExpression irExpression) {
                irExpression.getClass();
                Intrinsics.reifiedOperationMarker(3, "T");
                return Boolean.valueOf(Objects.nonNull(irExpression));
            }
        });
        if (listMatchConditions == null) {
            return null;
        }
        List<IrExpression> list = listMatchConditions;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (IrExpression irExpression : list) {
            Intrinsics.reifiedOperationMarker(1, "T");
            arrayList.add(irExpression);
        }
        return arrayList;
    }
}
