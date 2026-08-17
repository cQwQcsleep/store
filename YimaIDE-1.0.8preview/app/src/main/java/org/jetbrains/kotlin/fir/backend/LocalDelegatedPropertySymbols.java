package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/LocalDelegatedPropertySymbols;", Argument.Delimiters.none, "propertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;", "getterSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "setterSymbol", "<init>", "(Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;)V", "getPropertySymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;", "getGetterSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getSetterSymbol", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class LocalDelegatedPropertySymbols {
    private final IrSimpleFunctionSymbol getterSymbol;
    private final IrLocalDelegatedPropertySymbol propertySymbol;
    private final IrSimpleFunctionSymbol setterSymbol;

    public LocalDelegatedPropertySymbols(IrLocalDelegatedPropertySymbol irLocalDelegatedPropertySymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol2) {
        irLocalDelegatedPropertySymbol.getClass();
        irSimpleFunctionSymbol.getClass();
        this.propertySymbol = irLocalDelegatedPropertySymbol;
        this.getterSymbol = irSimpleFunctionSymbol;
        this.setterSymbol = irSimpleFunctionSymbol2;
    }

    public static /* synthetic */ LocalDelegatedPropertySymbols copy$default(LocalDelegatedPropertySymbols localDelegatedPropertySymbols, IrLocalDelegatedPropertySymbol irLocalDelegatedPropertySymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol2, int i, Object obj) {
        if ((i & 1) != 0) {
            irLocalDelegatedPropertySymbol = localDelegatedPropertySymbols.propertySymbol;
        }
        if ((i & 2) != 0) {
            irSimpleFunctionSymbol = localDelegatedPropertySymbols.getterSymbol;
        }
        if ((i & 4) != 0) {
            irSimpleFunctionSymbol2 = localDelegatedPropertySymbols.setterSymbol;
        }
        return localDelegatedPropertySymbols.copy(irLocalDelegatedPropertySymbol, irSimpleFunctionSymbol, irSimpleFunctionSymbol2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrLocalDelegatedPropertySymbol getPropertySymbol() {
        return this.propertySymbol;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final IrSimpleFunctionSymbol getGetterSymbol() {
        return this.getterSymbol;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final IrSimpleFunctionSymbol getSetterSymbol() {
        return this.setterSymbol;
    }

    public final LocalDelegatedPropertySymbols copy(IrLocalDelegatedPropertySymbol propertySymbol, IrSimpleFunctionSymbol getterSymbol, IrSimpleFunctionSymbol setterSymbol) {
        propertySymbol.getClass();
        getterSymbol.getClass();
        return new LocalDelegatedPropertySymbols(propertySymbol, getterSymbol, setterSymbol);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocalDelegatedPropertySymbols)) {
            return false;
        }
        LocalDelegatedPropertySymbols localDelegatedPropertySymbols = (LocalDelegatedPropertySymbols) other;
        return Intrinsics.areEqual(this.propertySymbol, localDelegatedPropertySymbols.propertySymbol) && Intrinsics.areEqual(this.getterSymbol, localDelegatedPropertySymbols.getterSymbol) && Intrinsics.areEqual(this.setterSymbol, localDelegatedPropertySymbols.setterSymbol);
    }

    public final IrSimpleFunctionSymbol getGetterSymbol() {
        return this.getterSymbol;
    }

    public final IrLocalDelegatedPropertySymbol getPropertySymbol() {
        return this.propertySymbol;
    }

    public final IrSimpleFunctionSymbol getSetterSymbol() {
        return this.setterSymbol;
    }

    public int hashCode() {
        int iHashCode = ((this.propertySymbol.hashCode() * 31) + this.getterSymbol.hashCode()) * 31;
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = this.setterSymbol;
        return iHashCode + (irSimpleFunctionSymbol == null ? 0 : irSimpleFunctionSymbol.hashCode());
    }

    public String toString() {
        return "LocalDelegatedPropertySymbols(propertySymbol=" + this.propertySymbol + ", getterSymbol=" + this.getterSymbol + ", setterSymbol=" + this.setterSymbol + ')';
    }
}
