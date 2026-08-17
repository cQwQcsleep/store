package org.jetbrains.kotlin.backend.common.actualizer;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/actualizer/IrExtraActualDeclarationExtractor;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "extract", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "expectIrClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "expectTopLevelCallables", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithName;", "expectCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "org.jetbrains.kotlin:ir.actualization"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrExtraActualDeclarationExtractor {
    public abstract List<IrSymbol> extract(List<? extends IrDeclarationWithName> expectTopLevelCallables, CallableId expectCallableId);

    public abstract IrClassSymbol extract(IrClass expectIrClass);
}
