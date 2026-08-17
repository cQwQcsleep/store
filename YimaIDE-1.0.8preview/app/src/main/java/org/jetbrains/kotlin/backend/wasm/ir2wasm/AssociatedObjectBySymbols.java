package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/AssociatedObjectBySymbols;", "", "klass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getter", "Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "isExternal", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;Z)V", "getKlass", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "getGetter", "()Lorg/jetbrains/kotlin/ir/symbols/IrFunctionSymbol;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class AssociatedObjectBySymbols {
    private final IrFunctionSymbol getter;
    private final boolean isExternal;
    private final IrClassSymbol klass;

    public AssociatedObjectBySymbols(IrClassSymbol irClassSymbol, IrFunctionSymbol irFunctionSymbol, boolean z) {
        irClassSymbol.getClass();
        irFunctionSymbol.getClass();
        this.klass = irClassSymbol;
        this.getter = irFunctionSymbol;
        this.isExternal = z;
    }

    public static /* synthetic */ AssociatedObjectBySymbols copy$default(AssociatedObjectBySymbols associatedObjectBySymbols, IrClassSymbol irClassSymbol, IrFunctionSymbol irFunctionSymbol, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            irClassSymbol = associatedObjectBySymbols.klass;
        }
        if ((i & 2) != 0) {
            irFunctionSymbol = associatedObjectBySymbols.getter;
        }
        if ((i & 4) != 0) {
            z = associatedObjectBySymbols.isExternal;
        }
        return associatedObjectBySymbols.copy(irClassSymbol, irFunctionSymbol, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrClassSymbol getKlass() {
        return this.klass;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final IrFunctionSymbol getGetter() {
        return this.getter;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsExternal() {
        return this.isExternal;
    }

    public final AssociatedObjectBySymbols copy(IrClassSymbol klass, IrFunctionSymbol getter, boolean isExternal) {
        klass.getClass();
        getter.getClass();
        return new AssociatedObjectBySymbols(klass, getter, isExternal);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssociatedObjectBySymbols)) {
            return false;
        }
        AssociatedObjectBySymbols associatedObjectBySymbols = (AssociatedObjectBySymbols) other;
        return Intrinsics.areEqual(this.klass, associatedObjectBySymbols.klass) && Intrinsics.areEqual(this.getter, associatedObjectBySymbols.getter) && this.isExternal == associatedObjectBySymbols.isExternal;
    }

    public final IrFunctionSymbol getGetter() {
        return this.getter;
    }

    public final IrClassSymbol getKlass() {
        return this.klass;
    }

    public int hashCode() {
        return (((this.klass.hashCode() * 31) + this.getter.hashCode()) * 31) + Boolean.hashCode(this.isExternal);
    }

    public final boolean isExternal() {
        return this.isExternal;
    }

    public String toString() {
        return "AssociatedObjectBySymbols(klass=" + this.klass + ", getter=" + this.getter + ", isExternal=" + this.isExternal + Util.C_PARAM_END;
    }
}
