package org.jetbrains.kotlin.backend.common.ir;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrSetValue;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;
import org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H$J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ir/AbstractValueRemapper;", "Lorg/jetbrains/kotlin/ir/visitors/IrElementTransformerVoid;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "remapValue", "Lorg/jetbrains/kotlin/ir/symbols/IrValueSymbol;", "oldValue", "visitGetValue", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "visitSetValue", "Lorg/jetbrains/kotlin/ir/expressions/IrSetValue;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class AbstractValueRemapper extends IrElementTransformerVoid {
    public abstract IrValueSymbol remapValue(IrValueSymbol oldValue);

    public IrExpression visitGetValue(IrGetValue expression) {
        expression.getClass();
        IrValueSymbol irValueSymbolRemapValue = remapValue(expression.getSymbol());
        return irValueSymbolRemapValue == null ? expression : BuildersKt.IrGetValueImpl(expression.getStartOffset(), expression.getEndOffset(), irValueSymbolRemapValue, expression.getOrigin());
    }

    public IrExpression visitSetValue(IrSetValue expression) {
        expression.getClass();
        transformChildrenVoid(expression);
        IrValueSymbol irValueSymbolRemapValue = remapValue(expression.getSymbol());
        if (irValueSymbolRemapValue == null) {
            return expression;
        }
        org.jetbrains.kotlin.ir.util.IrUtilsKt.isAssignable(irValueSymbolRemapValue.getOwner());
        return BuildersKt.IrSetValueImpl(expression.getStartOffset(), expression.getEndOffset(), expression.getType(), irValueSymbolRemapValue, expression.getValue(), expression.getOrigin());
    }
}
