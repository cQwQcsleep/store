package org.jetbrains.kotlin.cli.pipeline.web.wasm;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.IrModuleInfo;
import org.jetbrains.kotlin.backend.wasm.LoweredIrWithExtraArtifacts;
import org.jetbrains.kotlin.backend.wasm.WasmIrModuleConfiguration;
import org.jetbrains.kotlin.backend.wasm.ic.IrFactoryImplForWasmIC;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.MainModule;
import org.jetbrains.kotlin.ir.backend.js.ModulesStructure;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H&J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u0011H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WasmCompilerBase;", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "irFactory", "Lorg/jetbrains/kotlin/backend/wasm/ic/IrFactoryImplForWasmIC;", "getIrFactory", "()Lorg/jetbrains/kotlin/backend/wasm/ic/IrFactoryImplForWasmIC;", "loadIr", "Lorg/jetbrains/kotlin/backend/common/IrModuleInfo;", "modulesStructure", "Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;", "lowerIr", "Lorg/jetbrains/kotlin/backend/wasm/LoweredIrWithExtraArtifacts;", "irModuleInfo", "mainModule", "Lorg/jetbrains/kotlin/ir/backend/js/MainModule;", "exportedDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "compileIr", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/wasm/WasmIrModuleConfiguration;", "loweredIr", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class WasmCompilerBase {
    private final CompilerConfiguration configuration;

    public WasmCompilerBase(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        this.configuration = compilerConfiguration;
    }

    public abstract List<WasmIrModuleConfiguration> compileIr(LoweredIrWithExtraArtifacts loweredIr);

    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public abstract IrFactoryImplForWasmIC getIrFactory();

    public abstract IrModuleInfo loadIr(ModulesStructure modulesStructure);

    public abstract LoweredIrWithExtraArtifacts lowerIr(IrModuleInfo irModuleInfo, MainModule mainModule, Set<FqName> exportedDeclarations);
}
