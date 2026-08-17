package org.jetbrains.kotlin.backend.common.linkage.partial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0000\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"getCopyAndClear", "", "T", "", "isPartialLinkageRuntimeError", "", "Lorg/jetbrains/kotlin/ir/IrStatement;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PartialLinkageUtilsKt {
    public static final <T> Collection<T> getCopyAndClear(Collection<T> collection) {
        collection.getClass();
        if (collection.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection);
        collection.clear();
        return arrayList;
    }

    public static final boolean isPartialLinkageRuntimeError(IrStatement irStatement) {
        irStatement.getClass();
        if (irStatement instanceof IrCall) {
            return Intrinsics.areEqual(((IrCall) irStatement).getOrigin(), IrStatementOrigin.Companion.getPARTIAL_LINKAGE_RUNTIME_ERROR());
        }
        if (!(irStatement instanceof IrContainerExpression)) {
            return false;
        }
        IrContainerExpression irContainerExpression = (IrContainerExpression) irStatement;
        if (Intrinsics.areEqual(irContainerExpression.getOrigin(), IrStatementOrigin.Companion.getPARTIAL_LINKAGE_RUNTIME_ERROR())) {
            return true;
        }
        List statements = irContainerExpression.getStatements();
        if (!(statements instanceof Collection) || !statements.isEmpty()) {
            Iterator it = statements.iterator();
            while (it.hasNext()) {
                if (isPartialLinkageRuntimeError((IrStatement) it.next())) {
                    return true;
                }
            }
        }
        return false;
    }
}
