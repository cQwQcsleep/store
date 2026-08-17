package org.jetbrains.kotlin.cli.pipeline.web.wasm;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.backend.common.IrModuleInfo;
import org.jetbrains.kotlin.backend.wasm.LoweredIrWithExtraArtifacts;
import org.jetbrains.kotlin.backend.wasm.WasmCompilerKt;
import org.jetbrains.kotlin.backend.wasm.WasmIrModuleConfiguration;
import org.jetbrains.kotlin.backend.wasm.ic.IrFactoryImplForWasmIC;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.KlibKt;
import org.jetbrains.kotlin.ir.backend.js.MainModule;
import org.jetbrains.kotlin.ir.backend.js.ModulesStructure;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u0012H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\f¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/SingleModuleCompiler;", "Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WasmCompilerBase;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "irFactory", "Lorg/jetbrains/kotlin/backend/wasm/ic/IrFactoryImplForWasmIC;", "isWasmStdlib", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/backend/wasm/ic/IrFactoryImplForWasmIC;Z)V", "getIrFactory", "()Lorg/jetbrains/kotlin/backend/wasm/ic/IrFactoryImplForWasmIC;", "()Z", "loadIr", "Lorg/jetbrains/kotlin/backend/common/IrModuleInfo;", "modulesStructure", "Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;", "lowerIr", "Lorg/jetbrains/kotlin/backend/wasm/LoweredIrWithExtraArtifacts;", "irModuleInfo", "mainModule", "Lorg/jetbrains/kotlin/ir/backend/js/MainModule;", "exportedDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "compileIr", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/wasm/WasmIrModuleConfiguration;", "loweredIr", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SingleModuleCompiler extends WasmCompilerBase {
    private final IrFactoryImplForWasmIC irFactory;
    private final boolean isWasmStdlib;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleModuleCompiler(CompilerConfiguration compilerConfiguration, IrFactoryImplForWasmIC irFactoryImplForWasmIC, boolean z) {
        super(compilerConfiguration);
        compilerConfiguration.getClass();
        irFactoryImplForWasmIC.getClass();
        this.irFactory = irFactoryImplForWasmIC;
        this.isWasmStdlib = z;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilerBase
    public List<WasmIrModuleConfiguration> compileIr(LoweredIrWithExtraArtifacts loweredIr) {
        loweredIr.getClass();
        return CollectionsKt.listOf(KotlinIr2WasmIrCompilerKt.access$compileSingleModuleToWasmIr(getConfiguration(), loweredIr, getIrFactory(), this.isWasmStdlib, loweredIr.getBackendContext().getIrModuleFragment(), false));
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilerBase
    public IrFactoryImplForWasmIC getIrFactory() {
        return this.irFactory;
    }

    /* JADX INFO: renamed from: isWasmStdlib, reason: from getter */
    public final boolean getIsWasmStdlib() {
        return this.isWasmStdlib;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilerBase
    public IrModuleInfo loadIr(ModulesStructure modulesStructure) {
        modulesStructure.getClass();
        return KlibKt.loadIrForSingleModule(modulesStructure, getIrFactory());
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilerBase
    public LoweredIrWithExtraArtifacts lowerIr(IrModuleInfo irModuleInfo, MainModule mainModule, Set<FqName> exportedDeclarations) {
        irModuleInfo.getClass();
        mainModule.getClass();
        exportedDeclarations.getClass();
        WasmConfigurationKeysKt.setWasmDisableCrossFileOptimisations(getConfiguration(), true);
        return WasmCompilerKt.compileToLoweredIr(irModuleInfo, mainModule, getConfiguration(), exportedDeclarations);
    }
}
