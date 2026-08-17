package org.jetbrains.kotlin.backend.jvm.lower;

import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.jvm.lower.JvmOptimizationLoweringKt;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\u001a&\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0002\u001a\"\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u001a(\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¨\u0006\u000f"}, d2 = {"getInlineableValueForTemporaryVal", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "statement", "Lorg/jetbrains/kotlin/ir/IrStatement;", "isEliminationForbidden", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "", "optimizeGetValue", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "removeUnnecessaryTemporaryVariables", "", "statements", "", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmOptimizationLoweringKt {
    public static boolean a(Function1 function1, IrStatement irStatement) {
        irStatement.getClass();
        return getInlineableValueForTemporaryVal(irStatement, function1) != null;
    }

    public static boolean b(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    private static final IrExpression getInlineableValueForTemporaryVal(IrStatement irStatement, Function1<? super IrVariable, Boolean> function1) {
        IrExpression inlineableValueForTemporaryVal;
        IrVariable irVariable = irStatement instanceof IrVariable ? (IrVariable) irStatement : null;
        if (irVariable == null) {
            return null;
        }
        IrDeclarationOrigin origin = irVariable.getOrigin();
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.INSTANCE;
        if (!Intrinsics.areEqual(origin, companion.getIR_TEMPORARY_VARIABLE()) || irVariable.isVar() || ((Boolean) function1.invoke(irVariable)).booleanValue()) {
            return null;
        }
        IrGetValue initializer = irVariable.getInitializer();
        if (initializer instanceof IrConst) {
            return initializer;
        }
        if (initializer instanceof IrGetValue) {
            IrVariable owner = initializer.getSymbol().getOwner();
            if (owner instanceof IrVariable) {
                if (owner.isVar()) {
                    return null;
                }
                if (Intrinsics.areEqual(owner.getOrigin(), companion.getIR_TEMPORARY_VARIABLE()) && (inlineableValueForTemporaryVal = getInlineableValueForTemporaryVal(owner, function1)) != null) {
                    return inlineableValueForTemporaryVal;
                }
            } else if (!(owner instanceof IrValueParameter) || ((IrValueParameter) owner).isAssignable()) {
                return null;
            }
            return initializer;
        }
        return null;
    }

    public static final IrExpression optimizeGetValue(IrGetValue irGetValue, Function1<? super IrVariable, Boolean> function1) {
        irGetValue.getClass();
        function1.getClass();
        IrConst inlineableValueForTemporaryVal = getInlineableValueForTemporaryVal(irGetValue.getSymbol().getOwner(), function1);
        if (inlineableValueForTemporaryVal instanceof IrConst) {
            IrConst irConst = inlineableValueForTemporaryVal;
            return BuildersKt.IrConstImpl(irGetValue.getStartOffset(), irGetValue.getEndOffset(), irConst.getType(), irConst.getKind(), irConst.getValue());
        }
        if (!(inlineableValueForTemporaryVal instanceof IrGetValue)) {
            return irGetValue;
        }
        IrGetValue irGetValue2 = (IrGetValue) inlineableValueForTemporaryVal;
        return BuildersKt.IrGetValueImpl(irGetValue.getStartOffset(), irGetValue.getEndOffset(), irGetValue2.getType(), irGetValue2.getSymbol(), irGetValue2.getOrigin());
    }

    public static final void removeUnnecessaryTemporaryVariables(List<IrStatement> list, final Function1<? super IrVariable, Boolean> function1) {
        list.getClass();
        function1.getClass();
        final Function1 function2 = new Function1() { // from class: mz7
            public final Object invoke(Object obj) {
                return Boolean.valueOf(JvmOptimizationLoweringKt.a(function1, (IrStatement) obj));
            }
        };
        list.removeIf(new Predicate() { // from class: nz7
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return JvmOptimizationLoweringKt.b(function2, obj);
            }
        });
        if (list.size() == 2) {
            IrVariable irVariable = (IrStatement) list.get(0);
            IrGetValue irGetValue = (IrStatement) list.get(1);
            if (irVariable instanceof IrVariable) {
                IrVariable irVariable2 = irVariable;
                if (Intrinsics.areEqual(irVariable2.getOrigin(), IrDeclarationOrigin.INSTANCE.getIR_TEMPORARY_VARIABLE()) && (irGetValue instanceof IrGetValue) && Intrinsics.areEqual(irVariable2.getSymbol(), irGetValue.getSymbol())) {
                    list.clear();
                    IrExpression initializer = irVariable2.getInitializer();
                    if (initializer != null) {
                        list.add(initializer);
                    }
                }
            }
        }
    }
}
