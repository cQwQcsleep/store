package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"primaryConstructorParameter", "", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "getPrimaryConstructorParameter", "(Lorg/jetbrains/kotlin/ir/declarations/IrField;)Z", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class InitializersLoweringKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getPrimaryConstructorParameter(IrField irField) {
        IrExpressionBody initializer = irField.getInitializer();
        IrExpression expression = initializer != null ? initializer.getExpression() : null;
        IrGetValue irGetValue = expression instanceof IrGetValue ? (IrGetValue) expression : null;
        return Intrinsics.areEqual(irGetValue != null ? irGetValue.getOrigin() : null, IrStatementOrigin.Companion.getINITIALIZE_PROPERTY_FROM_PARAMETER());
    }
}
