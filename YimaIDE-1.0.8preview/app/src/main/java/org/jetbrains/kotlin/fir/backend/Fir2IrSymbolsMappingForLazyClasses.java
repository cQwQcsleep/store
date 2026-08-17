package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.util.SymbolRemapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", Argument.Delimiters.none, "remapper", "Lorg/jetbrains/kotlin/ir/util/SymbolRemapper;", "<init>", "(Lorg/jetbrains/kotlin/ir/util/SymbolRemapper;)V", "value", Argument.Delimiters.none, "isRemapperEnabled", "()Z", "enableRemapper", Argument.Delimiters.none, "remapFunctionSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "symbol", "remapPropertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrSymbolsMappingForLazyClasses {
    private boolean isRemapperEnabled;
    private final SymbolRemapper remapper;

    public Fir2IrSymbolsMappingForLazyClasses(SymbolRemapper symbolRemapper) {
        symbolRemapper.getClass();
        this.remapper = symbolRemapper;
    }

    public final void enableRemapper() {
        if (this.isRemapperEnabled) {
            w01.a("Remapper is already enabled");
        } else {
            this.isRemapperEnabled = true;
        }
    }

    /* JADX INFO: renamed from: isRemapperEnabled, reason: from getter */
    public final boolean getIsRemapperEnabled() {
        return this.isRemapperEnabled;
    }

    public final IrSimpleFunctionSymbol remapFunctionSymbol(IrSimpleFunctionSymbol symbol) {
        symbol.getClass();
        return this.isRemapperEnabled ? this.remapper.getReferencedSimpleFunction(symbol) : symbol;
    }

    public final IrPropertySymbol remapPropertySymbol(IrPropertySymbol symbol) {
        symbol.getClass();
        return this.isRemapperEnabled ? this.remapper.getReferencedProperty(symbol) : symbol;
    }
}
