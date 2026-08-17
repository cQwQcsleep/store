package org.jetbrains.kotlin.fir.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.actualizer.IrActualizedResult;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrPluginContext;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.util.SymbolTable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\rHÆ\u0003JG\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", Argument.Delimiters.none, "irModuleFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "pluginContext", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;", "irActualizedResult", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizedResult;", "irBuiltIns", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "symbolTable", "Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizedResult;Lorg/jetbrains/kotlin/ir/IrBuiltIns;Lorg/jetbrains/kotlin/ir/util/SymbolTable;)V", "getIrModuleFragment", "()Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "getComponents", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "getPluginContext", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;", "getIrActualizedResult", "()Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizedResult;", "getIrBuiltIns", "()Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "getSymbolTable", "()Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class Fir2IrActualizedResult {
    private final Fir2IrComponents components;
    private final IrActualizedResult irActualizedResult;
    private final IrBuiltIns irBuiltIns;
    private final IrModuleFragment irModuleFragment;
    private final Fir2IrPluginContext pluginContext;
    private final SymbolTable symbolTable;

    public Fir2IrActualizedResult(IrModuleFragment irModuleFragment, Fir2IrComponents fir2IrComponents, Fir2IrPluginContext fir2IrPluginContext, IrActualizedResult irActualizedResult, IrBuiltIns irBuiltIns, SymbolTable symbolTable) {
        irModuleFragment.getClass();
        fir2IrComponents.getClass();
        fir2IrPluginContext.getClass();
        irBuiltIns.getClass();
        symbolTable.getClass();
        this.irModuleFragment = irModuleFragment;
        this.components = fir2IrComponents;
        this.pluginContext = fir2IrPluginContext;
        this.irActualizedResult = irActualizedResult;
        this.irBuiltIns = irBuiltIns;
        this.symbolTable = symbolTable;
    }

    public static /* synthetic */ Fir2IrActualizedResult copy$default(Fir2IrActualizedResult fir2IrActualizedResult, IrModuleFragment irModuleFragment, Fir2IrComponents fir2IrComponents, Fir2IrPluginContext fir2IrPluginContext, IrActualizedResult irActualizedResult, IrBuiltIns irBuiltIns, SymbolTable symbolTable, int i, Object obj) {
        if ((i & 1) != 0) {
            irModuleFragment = fir2IrActualizedResult.irModuleFragment;
        }
        if ((i & 2) != 0) {
            fir2IrComponents = fir2IrActualizedResult.components;
        }
        if ((i & 4) != 0) {
            fir2IrPluginContext = fir2IrActualizedResult.pluginContext;
        }
        if ((i & 8) != 0) {
            irActualizedResult = fir2IrActualizedResult.irActualizedResult;
        }
        if ((i & 16) != 0) {
            irBuiltIns = fir2IrActualizedResult.irBuiltIns;
        }
        if ((i & 32) != 0) {
            symbolTable = fir2IrActualizedResult.symbolTable;
        }
        IrBuiltIns irBuiltIns2 = irBuiltIns;
        SymbolTable symbolTable2 = symbolTable;
        return fir2IrActualizedResult.copy(irModuleFragment, fir2IrComponents, fir2IrPluginContext, irActualizedResult, irBuiltIns2, symbolTable2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrModuleFragment getIrModuleFragment() {
        return this.irModuleFragment;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Fir2IrComponents getComponents() {
        return this.components;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Fir2IrPluginContext getPluginContext() {
        return this.pluginContext;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final IrActualizedResult getIrActualizedResult() {
        return this.irActualizedResult;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final IrBuiltIns getIrBuiltIns() {
        return this.irBuiltIns;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public final Fir2IrActualizedResult copy(IrModuleFragment irModuleFragment, Fir2IrComponents components, Fir2IrPluginContext pluginContext, IrActualizedResult irActualizedResult, IrBuiltIns irBuiltIns, SymbolTable symbolTable) {
        irModuleFragment.getClass();
        components.getClass();
        pluginContext.getClass();
        irBuiltIns.getClass();
        symbolTable.getClass();
        return new Fir2IrActualizedResult(irModuleFragment, components, pluginContext, irActualizedResult, irBuiltIns, symbolTable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Fir2IrActualizedResult)) {
            return false;
        }
        Fir2IrActualizedResult fir2IrActualizedResult = (Fir2IrActualizedResult) other;
        return Intrinsics.areEqual(this.irModuleFragment, fir2IrActualizedResult.irModuleFragment) && Intrinsics.areEqual(this.components, fir2IrActualizedResult.components) && Intrinsics.areEqual(this.pluginContext, fir2IrActualizedResult.pluginContext) && Intrinsics.areEqual(this.irActualizedResult, fir2IrActualizedResult.irActualizedResult) && Intrinsics.areEqual(this.irBuiltIns, fir2IrActualizedResult.irBuiltIns) && Intrinsics.areEqual(this.symbolTable, fir2IrActualizedResult.symbolTable);
    }

    public final Fir2IrComponents getComponents() {
        return this.components;
    }

    public final IrActualizedResult getIrActualizedResult() {
        return this.irActualizedResult;
    }

    public final IrBuiltIns getIrBuiltIns() {
        return this.irBuiltIns;
    }

    public final IrModuleFragment getIrModuleFragment() {
        return this.irModuleFragment;
    }

    public final Fir2IrPluginContext getPluginContext() {
        return this.pluginContext;
    }

    public final SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public int hashCode() {
        int iHashCode = ((((this.irModuleFragment.hashCode() * 31) + this.components.hashCode()) * 31) + this.pluginContext.hashCode()) * 31;
        IrActualizedResult irActualizedResult = this.irActualizedResult;
        return ((((iHashCode + (irActualizedResult == null ? 0 : irActualizedResult.hashCode())) * 31) + this.irBuiltIns.hashCode()) * 31) + this.symbolTable.hashCode();
    }

    public String toString() {
        return "Fir2IrActualizedResult(irModuleFragment=" + this.irModuleFragment + ", components=" + this.components + ", pluginContext=" + this.pluginContext + ", irActualizedResult=" + this.irActualizedResult + ", irBuiltIns=" + this.irBuiltIns + ", symbolTable=" + this.symbolTable + ')';
    }
}
