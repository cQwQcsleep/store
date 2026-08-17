package org.jetbrains.kotlin.cli.pipeline.web.wasm;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.backend.wasm.WasmLoweringPhasesKt;
import org.jetbrains.kotlin.cli.common.CreatePhaseConfigKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.KotlinWasmCompilerArguments;
import org.jetbrains.kotlin.cli.js.HelpersKt;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.platform.wasm.WasmPlatforms;
import org.jetbrains.kotlin.platform.wasm.WasmTarget;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeys;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/wasm/WasmConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/common/arguments/KotlinWasmCompilerArguments;", "<init>", "()V", "fillConfiguration", Argument.Delimiters.none, "input", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WasmConfigurationUpdater extends ConfigurationUpdater<KotlinWasmCompilerArguments> {
    public static final WasmConfigurationUpdater INSTANCE = new WasmConfigurationUpdater();

    private WasmConfigurationUpdater() {
    }

    private final void fillConfiguration(CompilerConfiguration configuration, KotlinWasmCompilerArguments arguments) {
        boolean zBooleanValue;
        HelpersKt.initializeFinalArtifactConfiguration(configuration, arguments);
        configuration.put(WasmConfigurationKeys.WASM_ENABLE_ARRAY_RANGE_CHECKS, Boolean.valueOf(arguments.getWasmEnableArrayRangeChecks()));
        configuration.put(WasmConfigurationKeys.WASM_DEBUG, Boolean.valueOf(arguments.getWasmDebug()));
        configuration.put(WasmConfigurationKeys.WASM_ENABLE_ASSERTS, Boolean.valueOf(arguments.getWasmEnableAsserts()));
        configuration.put(WasmConfigurationKeys.WASM_GENERATE_WAT, Boolean.valueOf(arguments.getWasmGenerateWat()));
        configuration.put(WasmConfigurationKeys.WASM_USE_TRAPS_INSTEAD_OF_EXCEPTIONS, Boolean.valueOf(arguments.getWasmUseTrapsInsteadOfExceptions()));
        String wasmTarget = arguments.getWasmTarget();
        WasmTarget wasmTargetFromName = wasmTarget != null ? WasmTarget.INSTANCE.fromName(wasmTarget) : null;
        CompilerConfigurationKey compilerConfigurationKey = WasmConfigurationKeys.WASM_USE_NEW_EXCEPTION_PROPOSAL;
        Boolean wasmUseNewExceptionProposal = arguments.getWasmUseNewExceptionProposal();
        if (wasmUseNewExceptionProposal != null) {
            zBooleanValue = wasmUseNewExceptionProposal.booleanValue();
        } else {
            zBooleanValue = wasmTargetFromName == WasmTarget.WASI;
        }
        configuration.put(compilerConfigurationKey, Boolean.valueOf(zBooleanValue));
        configuration.put(WasmConfigurationKeys.WASM_NO_JS_TAG, Boolean.valueOf(arguments.getWasmNoJsTag()));
        configuration.put(WasmConfigurationKeys.WASM_GENERATE_DWARF, Boolean.valueOf(arguments.getGenerateDwarf()));
        configuration.put(WasmConfigurationKeys.WASM_FORCE_DEBUG_FRIENDLY_COMPILATION, Boolean.valueOf(arguments.getForceDebugFriendlyCompilation()));
        configuration.put(WasmConfigurationKeys.WASM_INCLUDED_MODULE_ONLY, Boolean.valueOf(arguments.getWasmIncludedModuleOnly()));
        configuration.put(WasmConfigurationKeys.WASM_INTERNAL_LOCAL_VARIABLE_PREFIX, arguments.getWasmInternalLocalVariablePrefix());
        configuration.putIfNotNull(WasmConfigurationKeys.WASM_TARGET, wasmTargetFromName);
        configuration.putIfNotNull(WasmConfigurationKeys.DCE_DUMP_DECLARATION_IR_SIZES_TO_FILE, arguments.getIrDceDumpDeclarationIrSizesToFile());
        JSConfigurationKeysKt.setPropertyLazyInitialization(configuration, arguments.getIrPropertyLazyInitialization());
        CommonConfigurationKeysKt.setTargetPlatform(configuration, WasmPlatforms.INSTANCE.wasmPlatformByTargets(CollectionsKt.listOf(WasmConfigurationKeysKt.getWasmTarget(configuration))));
        WasmConfigurationKeysKt.setWasmGenerateClosedWorldMultimodule(configuration, arguments.getWasmGenerateClosedWorldMultimodule());
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater
    public void fillConfiguration(ArgumentsPipelineArtifact<KotlinWasmCompilerArguments> input, CompilerConfiguration configuration) {
        input.getClass();
        configuration.getClass();
        if (JSConfigurationKeysKt.getWasmCompilation(configuration)) {
            KotlinWasmCompilerArguments kotlinWasmCompilerArguments = (KotlinWasmCompilerArguments) input.getArguments();
            fillConfiguration(configuration, kotlinWasmCompilerArguments);
            if (kotlinWasmCompilerArguments.getIncludes() != null) {
                PhaseConfig phaseConfigCreatePhaseConfig$default = CreatePhaseConfigKt.createPhaseConfig$default(kotlinWasmCompilerArguments, null, 2, null);
                if (kotlinWasmCompilerArguments.getListPhases()) {
                    CreatePhaseConfigKt.listPhases(phaseConfigCreatePhaseConfig$default, WasmLoweringPhasesKt.getWasmLowerings());
                }
                CommonConfigurationKeysKt.setPhaseConfig(configuration, phaseConfigCreatePhaseConfig$default);
            }
        }
    }
}
