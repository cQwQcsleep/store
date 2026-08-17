package org.jetbrains.kotlin.backend.common.actualizer;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.SymbolRemapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/actualizer/FunctionDefaultParametersActualizerVisitor;", "Lorg/jetbrains/kotlin/backend/common/actualizer/ActualizerVisitor;", "symbolRemapper", "Lorg/jetbrains/kotlin/ir/util/SymbolRemapper;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/util/SymbolRemapper;)V", "visitGetValue", "Lorg/jetbrains/kotlin/ir/expressions/IrGetValue;", "expression", "org.jetbrains.kotlin:ir.actualization"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class FunctionDefaultParametersActualizerVisitor extends ActualizerVisitor {
    private final SymbolRemapper symbolRemapper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionDefaultParametersActualizerVisitor(SymbolRemapper symbolRemapper) {
        super(symbolRemapper, true);
        symbolRemapper.getClass();
        this.symbolRemapper = symbolRemapper;
    }

    public static IrFunction d(FunctionDefaultParametersActualizerVisitor functionDefaultParametersActualizerVisitor, IrFunction irFunction) {
        irFunction.getClass();
        return functionDefaultParametersActualizerVisitor.symbolRemapper.getReferencedFunction(irFunction.getSymbol()).getOwner();
    }

    public static IrClass e(FunctionDefaultParametersActualizerVisitor functionDefaultParametersActualizerVisitor, IrClass irClass) {
        irClass.getClass();
        return functionDefaultParametersActualizerVisitor.symbolRemapper.getReferencedClass(irClass.getSymbol()).getOwner();
    }

    public IrGetValue visitGetValue(IrGetValue expression) {
        expression.getClass();
        IrGetValue irGetValueRemapSymbolParent = IrUtilsKt.remapSymbolParent(expression, new Function1() { // from class: org.jetbrains.kotlin.backend.common.actualizer.a
            public final Object invoke(Object obj) {
                return FunctionDefaultParametersActualizerVisitor.e(this.b, (IrClass) obj);
            }
        }, new Function1() { // from class: org.jetbrains.kotlin.backend.common.actualizer.b
            public final Object invoke(Object obj) {
                return FunctionDefaultParametersActualizerVisitor.d(this.b, (IrFunction) obj);
            }
        });
        IrDeclarationsKt.copyAttributes$default(irGetValueRemapSymbolParent, expression, false, 2, (Object) null);
        return irGetValueRemapSymbolParent;
    }
}
