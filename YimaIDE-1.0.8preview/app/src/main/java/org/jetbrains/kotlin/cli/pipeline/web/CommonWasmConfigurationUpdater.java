package org.jetbrains.kotlin.cli.pipeline.web;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.KotlinWasmCompilerArguments;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.js.config.ModuleKind;
import org.jetbrains.kotlin.platform.wasm.WasmTarget;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeys;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J%\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/CommonWasmConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/pipeline/web/CommonWebConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/common/arguments/KotlinWasmCompilerArguments;", "<init>", "()V", "fillConfiguration", Argument.Delimiters.none, "input", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "setupPlatformSpecificArgumentsAndServices", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "setupPlatformSpecificArgumentsAndServices$org_jetbrains_kotlin_cli_js", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonWasmConfigurationUpdater extends CommonWebConfigurationUpdater<KotlinWasmCompilerArguments> {
    public static final CommonWasmConfigurationUpdater INSTANCE = new CommonWasmConfigurationUpdater();

    private CommonWasmConfigurationUpdater() {
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater, org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater
    public void fillConfiguration(ArgumentsPipelineArtifact<KotlinWasmCompilerArguments> input, CompilerConfiguration configuration) {
        input.getClass();
        configuration.getClass();
        super.fillConfiguration(input, configuration);
        JSConfigurationKeysKt.setWasmCompilation(configuration, true);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.CommonWebConfigurationUpdater
    public void setupPlatformSpecificArgumentsAndServices$org_jetbrains_kotlin_cli_js(CompilerConfiguration configuration, KotlinWasmCompilerArguments arguments, Services services) {
        configuration.getClass();
        arguments.getClass();
        services.getClass();
        super.setupPlatformSpecificArgumentsAndServices$org_jetbrains_kotlin_cli_js(configuration, arguments, services);
        if (arguments.getGenerateDwarf()) {
            configuration.put(WasmConfigurationKeys.WASM_GENERATE_DWARF, Boolean.TRUE);
        }
        if (arguments.getDebuggerCustomFormatters()) {
            JSConfigurationKeysKt.setUseDebuggerCustomFormatters(configuration, true);
        }
        JSConfigurationKeysKt.setWasmCompilation(configuration, true);
        CompilerConfigurationKey compilerConfigurationKey = WasmConfigurationKeys.WASM_TARGET;
        String wasmTarget = arguments.getWasmTarget();
        configuration.putIfNotNull(compilerConfigurationKey, wasmTarget != null ? WasmTarget.INSTANCE.fromName(wasmTarget) : null);
        JSConfigurationKeysKt.setModuleKind(configuration, ModuleKind.ES);
        JSConfigurationKeysKt.setSourceMapIncludeMappingsFromUnavailableFiles(configuration, arguments.getIncludeUnavailableSourcesIntoSourceMap());
        JSConfigurationKeysKt.setDumpReachabilityInfoToFile(configuration, arguments.getIrDceDumpReachabilityInfoToFile());
    }
}
