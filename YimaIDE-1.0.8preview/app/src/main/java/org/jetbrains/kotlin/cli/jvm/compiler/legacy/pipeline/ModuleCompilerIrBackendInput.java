package org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.actualizer.IrActualizedResult;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrPluginContext;
import org.jetbrains.kotlin.fir.backend.jvm.JvmFir2IrExtensions;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.util.SymbolTable;
import org.jetbrains.kotlin.modules.TargetId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\u000bHÆ\u0003J\t\u0010)\u001a\u00020\rHÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\t\u0010+\u001a\u00020\u0011HÆ\u0003J[\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0014\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00100\u001a\u000201HÖ\u0081\u0004J\n\u00102\u001a\u000203HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerIrBackendInput;", Argument.Delimiters.none, "targetId", "Lorg/jetbrains/kotlin/modules/TargetId;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/jvm/JvmFir2IrExtensions;", "irModuleFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "pluginContext", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;", "irActualizedResult", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizedResult;", "symbolTable", "Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "<init>", "(Lorg/jetbrains/kotlin/modules/TargetId;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/fir/backend/jvm/JvmFir2IrExtensions;Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizedResult;Lorg/jetbrains/kotlin/ir/util/SymbolTable;)V", "getTargetId", "()Lorg/jetbrains/kotlin/modules/TargetId;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/jvm/JvmFir2IrExtensions;", "getIrModuleFragment", "()Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "getComponents", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "getPluginContext", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrPluginContext;", "getIrActualizedResult", "()Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizedResult;", "getSymbolTable", "()Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ModuleCompilerIrBackendInput {
    private final Fir2IrComponents components;
    private final CompilerConfiguration configuration;
    private final JvmFir2IrExtensions extensions;
    private final IrActualizedResult irActualizedResult;
    private final IrModuleFragment irModuleFragment;
    private final Fir2IrPluginContext pluginContext;
    private final SymbolTable symbolTable;
    private final TargetId targetId;

    public ModuleCompilerIrBackendInput(TargetId targetId, CompilerConfiguration compilerConfiguration, JvmFir2IrExtensions jvmFir2IrExtensions, IrModuleFragment irModuleFragment, Fir2IrComponents fir2IrComponents, Fir2IrPluginContext fir2IrPluginContext, IrActualizedResult irActualizedResult, SymbolTable symbolTable) {
        targetId.getClass();
        compilerConfiguration.getClass();
        jvmFir2IrExtensions.getClass();
        irModuleFragment.getClass();
        fir2IrComponents.getClass();
        fir2IrPluginContext.getClass();
        symbolTable.getClass();
        this.targetId = targetId;
        this.configuration = compilerConfiguration;
        this.extensions = jvmFir2IrExtensions;
        this.irModuleFragment = irModuleFragment;
        this.components = fir2IrComponents;
        this.pluginContext = fir2IrPluginContext;
        this.irActualizedResult = irActualizedResult;
        this.symbolTable = symbolTable;
    }

    public static /* synthetic */ ModuleCompilerIrBackendInput copy$default(ModuleCompilerIrBackendInput moduleCompilerIrBackendInput, TargetId targetId, CompilerConfiguration compilerConfiguration, JvmFir2IrExtensions jvmFir2IrExtensions, IrModuleFragment irModuleFragment, Fir2IrComponents fir2IrComponents, Fir2IrPluginContext fir2IrPluginContext, IrActualizedResult irActualizedResult, SymbolTable symbolTable, int i, Object obj) {
        if ((i & 1) != 0) {
            targetId = moduleCompilerIrBackendInput.targetId;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = moduleCompilerIrBackendInput.configuration;
        }
        if ((i & 4) != 0) {
            jvmFir2IrExtensions = moduleCompilerIrBackendInput.extensions;
        }
        if ((i & 8) != 0) {
            irModuleFragment = moduleCompilerIrBackendInput.irModuleFragment;
        }
        if ((i & 16) != 0) {
            fir2IrComponents = moduleCompilerIrBackendInput.components;
        }
        if ((i & 32) != 0) {
            fir2IrPluginContext = moduleCompilerIrBackendInput.pluginContext;
        }
        if ((i & 64) != 0) {
            irActualizedResult = moduleCompilerIrBackendInput.irActualizedResult;
        }
        if ((i & 128) != 0) {
            symbolTable = moduleCompilerIrBackendInput.symbolTable;
        }
        IrActualizedResult irActualizedResult2 = irActualizedResult;
        SymbolTable symbolTable2 = symbolTable;
        Fir2IrComponents fir2IrComponents2 = fir2IrComponents;
        Fir2IrPluginContext fir2IrPluginContext2 = fir2IrPluginContext;
        return moduleCompilerIrBackendInput.copy(targetId, compilerConfiguration, jvmFir2IrExtensions, irModuleFragment, fir2IrComponents2, fir2IrPluginContext2, irActualizedResult2, symbolTable2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TargetId getTargetId() {
        return this.targetId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final JvmFir2IrExtensions getExtensions() {
        return this.extensions;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final IrModuleFragment getIrModuleFragment() {
        return this.irModuleFragment;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Fir2IrComponents getComponents() {
        return this.components;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Fir2IrPluginContext getPluginContext() {
        return this.pluginContext;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final IrActualizedResult getIrActualizedResult() {
        return this.irActualizedResult;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public final ModuleCompilerIrBackendInput copy(TargetId targetId, CompilerConfiguration configuration, JvmFir2IrExtensions extensions, IrModuleFragment irModuleFragment, Fir2IrComponents components, Fir2IrPluginContext pluginContext, IrActualizedResult irActualizedResult, SymbolTable symbolTable) {
        targetId.getClass();
        configuration.getClass();
        extensions.getClass();
        irModuleFragment.getClass();
        components.getClass();
        pluginContext.getClass();
        symbolTable.getClass();
        return new ModuleCompilerIrBackendInput(targetId, configuration, extensions, irModuleFragment, components, pluginContext, irActualizedResult, symbolTable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ModuleCompilerIrBackendInput)) {
            return false;
        }
        ModuleCompilerIrBackendInput moduleCompilerIrBackendInput = (ModuleCompilerIrBackendInput) other;
        return Intrinsics.areEqual(this.targetId, moduleCompilerIrBackendInput.targetId) && Intrinsics.areEqual(this.configuration, moduleCompilerIrBackendInput.configuration) && Intrinsics.areEqual(this.extensions, moduleCompilerIrBackendInput.extensions) && Intrinsics.areEqual(this.irModuleFragment, moduleCompilerIrBackendInput.irModuleFragment) && Intrinsics.areEqual(this.components, moduleCompilerIrBackendInput.components) && Intrinsics.areEqual(this.pluginContext, moduleCompilerIrBackendInput.pluginContext) && Intrinsics.areEqual(this.irActualizedResult, moduleCompilerIrBackendInput.irActualizedResult) && Intrinsics.areEqual(this.symbolTable, moduleCompilerIrBackendInput.symbolTable);
    }

    public final Fir2IrComponents getComponents() {
        return this.components;
    }

    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final JvmFir2IrExtensions getExtensions() {
        return this.extensions;
    }

    public final IrActualizedResult getIrActualizedResult() {
        return this.irActualizedResult;
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

    public final TargetId getTargetId() {
        return this.targetId;
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.targetId.hashCode() * 31) + this.configuration.hashCode()) * 31) + this.extensions.hashCode()) * 31) + this.irModuleFragment.hashCode()) * 31) + this.components.hashCode()) * 31) + this.pluginContext.hashCode()) * 31;
        IrActualizedResult irActualizedResult = this.irActualizedResult;
        return ((iHashCode + (irActualizedResult == null ? 0 : irActualizedResult.hashCode())) * 31) + this.symbolTable.hashCode();
    }

    public String toString() {
        return "ModuleCompilerIrBackendInput(targetId=" + this.targetId + ", configuration=" + this.configuration + ", extensions=" + this.extensions + ", irModuleFragment=" + this.irModuleFragment + ", components=" + this.components + ", pluginContext=" + this.pluginContext + ", irActualizedResult=" + this.irActualizedResult + ", symbolTable=" + this.symbolTable + ')';
    }
}
