package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.InternalSymbolFinderAPI;
import org.jetbrains.kotlin.ir.SymbolFinder;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@InternalSymbolFinderAPI
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000Ê\u0001\u0002\b\u0012¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/SymbolFinderOverFir;", "Lorg/jetbrains/kotlin/ir/SymbolFinder;", "fir2irBuiltins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;)V", "findClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "findFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "findProperties", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "org.jetbrains.kotlin:fir2ir", "Lorg/jetbrains/kotlin/ir/InternalSymbolFinderAPI;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SymbolFinderOverFir extends SymbolFinder {
    private final Fir2IrBuiltinSymbolsContainer fir2irBuiltins;

    public SymbolFinderOverFir(Fir2IrBuiltinSymbolsContainer fir2IrBuiltinSymbolsContainer) {
        fir2IrBuiltinSymbolsContainer.getClass();
        this.fir2irBuiltins = fir2IrBuiltinSymbolsContainer;
    }

    public IrClassSymbol findClass(ClassId classId) {
        classId.getClass();
        return this.fir2irBuiltins.loadClassSafe$org_jetbrains_kotlin_fir2ir(classId);
    }

    public Iterable<IrSimpleFunctionSymbol> findFunctions(CallableId callableId) {
        callableId.getClass();
        return this.fir2irBuiltins.findFunctions$org_jetbrains_kotlin_fir2ir(callableId);
    }

    public Iterable<IrPropertySymbol> findProperties(CallableId callableId) {
        callableId.getClass();
        return this.fir2irBuiltins.findProperties$org_jetbrains_kotlin_fir2ir(callableId);
    }
}
