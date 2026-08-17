package org.jetbrains.kotlin.cli.pipeline.web.wasm;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.IrModuleInfo;
import org.jetbrains.kotlin.backend.wasm.LoweredIrWithExtraArtifacts;
import org.jetbrains.kotlin.backend.wasm.WasmCompilerKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.KlibKt;
import org.jetbrains.kotlin.ir.backend.js.MainModule;
import org.jetbrains.kotlin.ir.backend.js.ModulesStructure;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WholeWorldCompilerBase;", "Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WasmCompilerBase;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "noCrossFileOptimisations", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Z)V", "loadIr", "Lorg/jetbrains/kotlin/backend/common/IrModuleInfo;", "modulesStructure", "Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;", "lowerIr", "Lorg/jetbrains/kotlin/backend/wasm/LoweredIrWithExtraArtifacts;", "irModuleInfo", "mainModule", "Lorg/jetbrains/kotlin/ir/backend/js/MainModule;", "exportedDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class WholeWorldCompilerBase extends WasmCompilerBase {
    private final boolean noCrossFileOptimisations;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WholeWorldCompilerBase(CompilerConfiguration compilerConfiguration, boolean z) {
        super(compilerConfiguration);
        compilerConfiguration.getClass();
        this.noCrossFileOptimisations = z;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilerBase
    public IrModuleInfo loadIr(ModulesStructure modulesStructure) {
        modulesStructure.getClass();
        return KlibKt.loadIr$default(modulesStructure, getIrFactory(), (Set) null, true, 4, (Object) null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilerBase
    public LoweredIrWithExtraArtifacts lowerIr(IrModuleInfo irModuleInfo, MainModule mainModule, Set<FqName> exportedDeclarations) {
        irModuleInfo.getClass();
        mainModule.getClass();
        exportedDeclarations.getClass();
        WasmConfigurationKeysKt.setWasmDisableCrossFileOptimisations(getConfiguration(), this.noCrossFileOptimisations);
        return WasmCompilerKt.compileToLoweredIr(irModuleInfo, mainModule, getConfiguration(), exportedDeclarations);
    }
}
