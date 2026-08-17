package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J7\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/PropertySymbols;", Argument.Delimiters.none, "propertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "getterSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "setterSymbol", "backingFieldSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "<init>", "(Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;)V", "getPropertySymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "getGetterSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getSetterSymbol", "getBackingFieldSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "component1", "component2", "component3", "component4", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class PropertySymbols {
    private final IrFieldSymbol backingFieldSymbol;
    private final IrSimpleFunctionSymbol getterSymbol;
    private final IrPropertySymbol propertySymbol;
    private final IrSimpleFunctionSymbol setterSymbol;

    public PropertySymbols(IrPropertySymbol irPropertySymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol2, IrFieldSymbol irFieldSymbol) {
        irPropertySymbol.getClass();
        this.propertySymbol = irPropertySymbol;
        this.getterSymbol = irSimpleFunctionSymbol;
        this.setterSymbol = irSimpleFunctionSymbol2;
        this.backingFieldSymbol = irFieldSymbol;
    }

    public static /* synthetic */ PropertySymbols copy$default(PropertySymbols propertySymbols, IrPropertySymbol irPropertySymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol2, IrFieldSymbol irFieldSymbol, int i, Object obj) {
        if ((i & 1) != 0) {
            irPropertySymbol = propertySymbols.propertySymbol;
        }
        if ((i & 2) != 0) {
            irSimpleFunctionSymbol = propertySymbols.getterSymbol;
        }
        if ((i & 4) != 0) {
            irSimpleFunctionSymbol2 = propertySymbols.setterSymbol;
        }
        if ((i & 8) != 0) {
            irFieldSymbol = propertySymbols.backingFieldSymbol;
        }
        return propertySymbols.copy(irPropertySymbol, irSimpleFunctionSymbol, irSimpleFunctionSymbol2, irFieldSymbol);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrPropertySymbol getPropertySymbol() {
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

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final IrFieldSymbol getBackingFieldSymbol() {
        return this.backingFieldSymbol;
    }

    public final PropertySymbols copy(IrPropertySymbol propertySymbol, IrSimpleFunctionSymbol getterSymbol, IrSimpleFunctionSymbol setterSymbol, IrFieldSymbol backingFieldSymbol) {
        propertySymbol.getClass();
        return new PropertySymbols(propertySymbol, getterSymbol, setterSymbol, backingFieldSymbol);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PropertySymbols)) {
            return false;
        }
        PropertySymbols propertySymbols = (PropertySymbols) other;
        return Intrinsics.areEqual(this.propertySymbol, propertySymbols.propertySymbol) && Intrinsics.areEqual(this.getterSymbol, propertySymbols.getterSymbol) && Intrinsics.areEqual(this.setterSymbol, propertySymbols.setterSymbol) && Intrinsics.areEqual(this.backingFieldSymbol, propertySymbols.backingFieldSymbol);
    }

    public final IrFieldSymbol getBackingFieldSymbol() {
        return this.backingFieldSymbol;
    }

    public final IrSimpleFunctionSymbol getGetterSymbol() {
        return this.getterSymbol;
    }

    public final IrPropertySymbol getPropertySymbol() {
        return this.propertySymbol;
    }

    public final IrSimpleFunctionSymbol getSetterSymbol() {
        return this.setterSymbol;
    }

    public int hashCode() {
        int iHashCode = this.propertySymbol.hashCode() * 31;
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = this.getterSymbol;
        int iHashCode2 = (iHashCode + (irSimpleFunctionSymbol == null ? 0 : irSimpleFunctionSymbol.hashCode())) * 31;
        IrSimpleFunctionSymbol irSimpleFunctionSymbol2 = this.setterSymbol;
        int iHashCode3 = (iHashCode2 + (irSimpleFunctionSymbol2 == null ? 0 : irSimpleFunctionSymbol2.hashCode())) * 31;
        IrFieldSymbol irFieldSymbol = this.backingFieldSymbol;
        return iHashCode3 + (irFieldSymbol != null ? irFieldSymbol.hashCode() : 0);
    }

    public String toString() {
        return "PropertySymbols(propertySymbol=" + this.propertySymbol + ", getterSymbol=" + this.getterSymbol + ", setterSymbol=" + this.setterSymbol + ", backingFieldSymbol=" + this.backingFieldSymbol + ')';
    }
}
