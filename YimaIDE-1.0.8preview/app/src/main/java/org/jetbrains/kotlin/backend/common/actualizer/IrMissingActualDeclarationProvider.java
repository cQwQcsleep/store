package org.jetbrains.kotlin.backend.common.actualizer;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH&¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/actualizer/IrMissingActualDeclarationProvider;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "provideSymbolForMissingActual", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "expectSymbol", "containingExpectClassSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "containingActualClassSymbol", "org.jetbrains.kotlin:ir.actualization"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrMissingActualDeclarationProvider {
    public abstract IrSymbol provideSymbolForMissingActual(IrSymbol expectSymbol, IrClassSymbol containingExpectClassSymbol, IrClassSymbol containingActualClassSymbol);
}
