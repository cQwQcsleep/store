package org.jetbrains.kotlin.backend.jvm.lower;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetField;
import org.jetbrains.kotlin.ir.expressions.IrReturn;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\f\u0010\u0005\u001a\u00020\u0004*\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"findTailCall", "Lorg/jetbrains/kotlin/ir/IrStatement;", "", "functionReturnsUnit", "", "isGetFieldOfUnit", "Lorg/jetbrains/kotlin/ir/expressions/IrGetField;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class TailCallOptimizationLoweringKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final IrStatement findTailCall(List<? extends IrStatement> list, boolean z) {
        Object next;
        Iterator<T> it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((IrStatement) next) instanceof IrReturn));
        IrReturn irReturn = next instanceof IrReturn ? (IrReturn) next : null;
        IrExpression value = irReturn != null ? irReturn.getValue() : null;
        if (value instanceof IrGetField) {
            if (z && isGetFieldOfUnit((IrGetField) value)) {
                return findTailCall(list.subList(0, list.indexOf(irReturn)), z);
            }
        } else if (value == null) {
            return (IrStatement) CollectionsKt.lastOrNull(list);
        }
        return irReturn;
    }

    private static final boolean isGetFieldOfUnit(IrGetField irGetField) {
        return IrTypePredicatesKt.isUnit(irGetField.getType()) && Intrinsics.areEqual(irGetField.getSymbol().getOwner().getName(), Name.identifier(JvmAbi.INSTANCE_FIELD)) && Intrinsics.areEqual(IrUtilsKt.getFqNameWhenAvailable(IrUtilsKt.getParentAsClass(irGetField.getSymbol().getOwner())), new FqName("kotlin.Unit"));
    }
}
