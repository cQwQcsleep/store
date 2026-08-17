package org.jetbrains.kotlin.cli.pipeline.web.wasm;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.backend.wasm.WasmIrModuleConfiguration;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.ic.ModuleArtifact;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class WasmBackendPipelinePhase$compileIncrementally$fragmentCompiler$2 extends FunctionReferenceImpl implements Function2<List<? extends ModuleArtifact>, CompilerConfiguration, List<? extends WasmIrModuleConfiguration>> {
    public static final WasmBackendPipelinePhase$compileIncrementally$fragmentCompiler$2 INSTANCE = new WasmBackendPipelinePhase$compileIncrementally$fragmentCompiler$2();

    public WasmBackendPipelinePhase$compileIncrementally$fragmentCompiler$2() {
        super(2, KotlinIr2WasmIrCompilerICKt.class, "compileIncrementallySingleModule", "compileIncrementallySingleModule(Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/List;", 1);
    }

    public final List<WasmIrModuleConfiguration> invoke(List<? extends ModuleArtifact> list, CompilerConfiguration compilerConfiguration) {
        list.getClass();
        compilerConfiguration.getClass();
        return KotlinIr2WasmIrCompilerICKt.compileIncrementallySingleModule(list, compilerConfiguration);
    }
}
