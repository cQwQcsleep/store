package org.jetbrains.kotlin.fir.backend;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0018*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\b\u0010$\u001a\u00020\u000eH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0015\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u001d\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u001d\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u001d\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0018¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u001d\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0018¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001a¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrSyntheticIrBuiltinsSymbolsContainer;", Argument.Delimiters.none, "<init>", "()V", "primitiveIntegralTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "primitiveFloatingPointTypes", "getPrimitiveFloatingPointTypes", "()Ljava/util/List;", "primitiveNumericIrTypes", "primitiveIrTypesWithComparisons", "getPrimitiveIrTypesWithComparisons", "eqeqeqSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getEqeqeqSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "eqeqSymbol", "getEqeqSymbol", "noWhenBranchMatchedExceptionSymbol", "getNoWhenBranchMatchedExceptionSymbol", "checkNotNullSymbol", "getCheckNotNullSymbol", "lessFunByOperandType", Argument.Delimiters.none, "getLessFunByOperandType", "()Ljava/util/Map;", "lessOrEqualFunByOperandType", "getLessOrEqualFunByOperandType", "greaterOrEqualFunByOperandType", "getGreaterOrEqualFunByOperandType", "greaterFunByOperandType", "getGreaterFunByOperandType", "ieee754equalsFunByOperandType", "getIeee754equalsFunByOperandType", "symbols", "symbol", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrSyntheticIrBuiltinsSymbolsContainer {
    private final IrSimpleFunctionSymbol checkNotNullSymbol;
    private final IrSimpleFunctionSymbol eqeqSymbol;
    private final IrSimpleFunctionSymbol eqeqeqSymbol;
    private final Map<PrimitiveType, IrSimpleFunctionSymbol> greaterFunByOperandType;
    private final Map<PrimitiveType, IrSimpleFunctionSymbol> greaterOrEqualFunByOperandType;
    private final Map<PrimitiveType, IrSimpleFunctionSymbol> ieee754equalsFunByOperandType;
    private final Map<PrimitiveType, IrSimpleFunctionSymbol> lessFunByOperandType;
    private final Map<PrimitiveType, IrSimpleFunctionSymbol> lessOrEqualFunByOperandType;
    private final IrSimpleFunctionSymbol noWhenBranchMatchedExceptionSymbol;
    private final List<PrimitiveType> primitiveFloatingPointTypes;
    private final List<PrimitiveType> primitiveIntegralTypes;
    private final List<PrimitiveType> primitiveIrTypesWithComparisons;
    private final List<PrimitiveType> primitiveNumericIrTypes;

    public Fir2IrSyntheticIrBuiltinsSymbolsContainer() {
        List<PrimitiveType> listListOf = CollectionsKt.listOf(new PrimitiveType[]{PrimitiveType.BYTE, PrimitiveType.SHORT, PrimitiveType.INT, PrimitiveType.LONG});
        this.primitiveIntegralTypes = listListOf;
        List<PrimitiveType> listListOf2 = CollectionsKt.listOf(new PrimitiveType[]{PrimitiveType.FLOAT, PrimitiveType.DOUBLE});
        this.primitiveFloatingPointTypes = listListOf2;
        List<PrimitiveType> listPlus = CollectionsKt.plus(listListOf, listListOf2);
        this.primitiveNumericIrTypes = listPlus;
        List<PrimitiveType> listPlus2 = CollectionsKt.plus(listPlus, PrimitiveType.CHAR);
        this.primitiveIrTypesWithComparisons = listPlus2;
        this.eqeqeqSymbol = symbol();
        this.eqeqSymbol = symbol();
        this.noWhenBranchMatchedExceptionSymbol = symbol();
        this.checkNotNullSymbol = symbol();
        this.lessFunByOperandType = symbols(listPlus2);
        this.lessOrEqualFunByOperandType = symbols(listPlus2);
        this.greaterOrEqualFunByOperandType = symbols(listPlus2);
        this.greaterFunByOperandType = symbols(listPlus2);
        this.ieee754equalsFunByOperandType = symbols(listListOf2);
    }

    private final IrSimpleFunctionSymbol symbol() {
        return new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
    }

    private final Map<PrimitiveType, IrSimpleFunctionSymbol> symbols(List<? extends PrimitiveType> list) {
        List<? extends PrimitiveType> list2 = list;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
        for (Object obj : list2) {
            linkedHashMap.put(obj, symbol());
        }
        return linkedHashMap;
    }

    public final IrSimpleFunctionSymbol getCheckNotNullSymbol() {
        return this.checkNotNullSymbol;
    }

    public final IrSimpleFunctionSymbol getEqeqSymbol() {
        return this.eqeqSymbol;
    }

    public final IrSimpleFunctionSymbol getEqeqeqSymbol() {
        return this.eqeqeqSymbol;
    }

    public final Map<PrimitiveType, IrSimpleFunctionSymbol> getGreaterFunByOperandType() {
        return this.greaterFunByOperandType;
    }

    public final Map<PrimitiveType, IrSimpleFunctionSymbol> getGreaterOrEqualFunByOperandType() {
        return this.greaterOrEqualFunByOperandType;
    }

    public final Map<PrimitiveType, IrSimpleFunctionSymbol> getIeee754equalsFunByOperandType() {
        return this.ieee754equalsFunByOperandType;
    }

    public final Map<PrimitiveType, IrSimpleFunctionSymbol> getLessFunByOperandType() {
        return this.lessFunByOperandType;
    }

    public final Map<PrimitiveType, IrSimpleFunctionSymbol> getLessOrEqualFunByOperandType() {
        return this.lessOrEqualFunByOperandType;
    }

    public final IrSimpleFunctionSymbol getNoWhenBranchMatchedExceptionSymbol() {
        return this.noWhenBranchMatchedExceptionSymbol;
    }

    public final List<PrimitiveType> getPrimitiveFloatingPointTypes() {
        return this.primitiveFloatingPointTypes;
    }

    public final List<PrimitiveType> getPrimitiveIrTypesWithComparisons() {
        return this.primitiveIrTypesWithComparisons;
    }
}
