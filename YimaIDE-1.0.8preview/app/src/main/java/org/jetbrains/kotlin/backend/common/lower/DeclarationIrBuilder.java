package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.builders.IrBuilderWithScope;
import org.jetbrains.kotlin.ir.builders.IrGeneratorContext;
import org.jetbrains.kotlin.ir.builders.Scope;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/DeclarationIrBuilder;", "Lorg/jetbrains/kotlin/ir/builders/IrBuilderWithScope;", "generatorContext", "Lorg/jetbrains/kotlin/ir/builders/IrGeneratorContext;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "startOffset", "", "endOffset", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/builders/IrGeneratorContext;Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;II)V", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DeclarationIrBuilder extends IrBuilderWithScope {
    public /* synthetic */ DeclarationIrBuilder(IrGeneratorContext irGeneratorContext, IrSymbol irSymbol, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(irGeneratorContext, irSymbol, (i3 & 4) != 0 ? -1 : i, (i3 & 8) != 0 ? -1 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeclarationIrBuilder(IrGeneratorContext irGeneratorContext, IrSymbol irSymbol, int i, int i2) {
        super(irGeneratorContext, new Scope(irSymbol), i, i2);
        irGeneratorContext.getClass();
        irSymbol.getClass();
    }
}
