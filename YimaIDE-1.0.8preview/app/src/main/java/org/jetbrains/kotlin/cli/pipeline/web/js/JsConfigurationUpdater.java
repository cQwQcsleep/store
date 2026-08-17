package org.jetbrains.kotlin.cli.pipeline.web.js;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CreatePhaseConfigKt;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments;
import org.jetbrains.kotlin.cli.js.HelpersKt;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater;
import org.jetbrains.kotlin.cli.pipeline.SuccessfulPipelineExecutionException;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.ir.backend.js.JsLoweringPhasesKt;
import org.jetbrains.kotlin.js.config.EcmaVersion;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.js.config.ModuleKind;
import org.jetbrains.kotlin.platform.js.JsPlatforms;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0002J\u001d\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/js/JsConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationUpdater;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "<init>", "()V", "fillConfiguration", Argument.Delimiters.none, "input", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "initializeAndCheckTargetVersion", "Lorg/jetbrains/kotlin/js/config/EcmaVersion;", "checkWasmArgumentsUsage", "checkWasmArgumentsUsage$org_jetbrains_kotlin_cli_js", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JsConfigurationUpdater extends ConfigurationUpdater<K2JSCompilerArguments> {
    public static final JsConfigurationUpdater INSTANCE = new JsConfigurationUpdater();

    private JsConfigurationUpdater() {
    }

    private final void fillConfiguration(CompilerConfiguration configuration, K2JSCompilerArguments arguments) {
        List listEmptyList;
        CompilerConfiguration compilerConfiguration;
        List listSplit$default;
        EcmaVersion ecmaVersionInitializeAndCheckTargetVersion = initializeAndCheckTargetVersion(arguments, configuration);
        JSConfigurationKeysKt.setOptimizeGeneratedJs(configuration, arguments.getOptimizeGeneratedJs());
        boolean zBooleanValue = ecmaVersionInitializeAndCheckTargetVersion == EcmaVersion.es2015;
        ModuleKind moduleKind = JSConfigurationKeysKt.getModuleKind(configuration);
        if (moduleKind == null && (moduleKind = HelpersKt.getModuleKindMap().get(arguments.getModuleKind())) == null) {
            moduleKind = ModuleKind.ES;
            if (!zBooleanValue) {
                moduleKind = null;
            }
            if (moduleKind == null) {
                moduleKind = ModuleKind.UMD;
            }
        }
        JSConfigurationKeysKt.setModuleKind(configuration, moduleKind);
        HelpersKt.initializeFinalArtifactConfiguration(configuration, arguments);
        String irKeep = arguments.getIrKeep();
        if (irKeep == null || (listSplit$default = StringsKt.split$default(irKeep, new String[]{Argument.Delimiters.default}, false, 0, 6, (Object) null)) == null) {
            listEmptyList = null;
        } else {
            listEmptyList = new ArrayList();
            for (Object obj : listSplit$default) {
                if (((String) obj).length() != 0) {
                    listEmptyList.add(obj);
                }
            }
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        JSConfigurationKeysKt.setKeep(configuration, listEmptyList);
        JSConfigurationKeysKt.setSafeExternalBoolean(configuration, arguments.getIrSafeExternalBoolean());
        JSConfigurationKeysKt.setMinimizedMemberNames(configuration, arguments.getIrMinimizedMemberNames());
        JSConfigurationKeysKt.setPropertyLazyInitialization(configuration, arguments.getIrPropertyLazyInitialization());
        JSConfigurationKeysKt.setGeneratePolyfills(configuration, arguments.getGeneratePolyfills());
        JSConfigurationKeysKt.setGenerateInlineAnonymousFunctions(configuration, arguments.getIrGenerateInlineAnonymousFunctions());
        Boolean useEsClasses = arguments.getUseEsClasses();
        JSConfigurationKeysKt.setUseEs6Classes(configuration, useEsClasses != null ? useEsClasses.booleanValue() : zBooleanValue);
        Boolean useEsGenerators = arguments.getUseEsGenerators();
        JSConfigurationKeysKt.setCompileSuspendAsJsGenerator(configuration, useEsGenerators != null ? useEsGenerators.booleanValue() : zBooleanValue);
        Boolean useEsArrowFunctions = arguments.getUseEsArrowFunctions();
        if (useEsArrowFunctions != null) {
            zBooleanValue = useEsArrowFunctions.booleanValue();
        }
        JSConfigurationKeysKt.setCompileLambdasAsEs6ArrowFunctions(configuration, zBooleanValue);
        Boolean compileLongAsBigInt = arguments.getCompileLongAsBigInt();
        JSConfigurationKeysKt.setCompileLongAsBigint(configuration, compileLongAsBigInt != null ? compileLongAsBigInt.booleanValue() : false);
        CommonConfigurationKeysKt.setTargetPlatform(configuration, JsPlatforms.INSTANCE.getDefaultJsPlatform());
        String irSafeExternalBooleanDiagnostic = arguments.getIrSafeExternalBooleanDiagnostic();
        if (irSafeExternalBooleanDiagnostic != null) {
            JSConfigurationKeysKt.setSafeExternalBooleanDiagnostic(configuration, irSafeExternalBooleanDiagnostic);
        }
        String platformArgumentsProviderJsExpression = arguments.getPlatformArgumentsProviderJsExpression();
        if (platformArgumentsProviderJsExpression != null) {
            JSConfigurationKeysKt.setDefinePlatformMainFunctionArguments(configuration, platformArgumentsProviderJsExpression);
        }
        if (arguments.getScript()) {
            compilerConfiguration = configuration;
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "K/JS does not support Kotlin script (*.kts) files", null, 4, null);
        } else {
            compilerConfiguration = configuration;
        }
        if (!arguments.getFreeArgs().isEmpty() || UtilsKt.incrementalCompilationIsEnabledForJs(arguments)) {
            return;
        }
        if (arguments.getVersion()) {
            throw new SuccessfulPipelineExecutionException();
        }
        String includes = arguments.getIncludes();
        if (includes == null || includes.length() == 0) {
            CliDiagnosticReportingKt.report(compilerConfiguration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "Specify at least one source file or directory", null);
        }
    }

    private final EcmaVersion initializeAndCheckTargetVersion(K2JSCompilerArguments arguments, CompilerConfiguration configuration) {
        EcmaVersion targetVersion = HelpersKt.getTargetVersion(arguments);
        if (targetVersion == null) {
            CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_ERROR(), "Unsupported ECMA version: " + arguments.getTarget(), null, 4, null);
        }
        return targetVersion;
    }

    public final void checkWasmArgumentsUsage$org_jetbrains_kotlin_cli_js(K2JSCompilerArguments arguments, CompilerConfiguration configuration) {
        CompilerConfiguration compilerConfiguration;
        arguments.getClass();
        configuration.getClass();
        if (arguments.getIrDceDumpReachabilityInfoToFile() != null) {
            compilerConfiguration = configuration;
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_WARNING(), "Dumping the reachability info to a file is not supported for Kotlin/JS.", null, 4, null);
        } else {
            compilerConfiguration = configuration;
        }
        if (arguments.getIrDceDumpDeclarationIrSizesToFile() != null) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_WARNING(), "Dumping the sizes of declarations to file is not supported for Kotlin/JS.", null, 4, null);
        }
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater
    public void fillConfiguration(ArgumentsPipelineArtifact<K2JSCompilerArguments> input, CompilerConfiguration configuration) {
        input.getClass();
        configuration.getClass();
        if (JSConfigurationKeysKt.getWasmCompilation(configuration)) {
            return;
        }
        K2JSCompilerArguments k2JSCompilerArguments = (K2JSCompilerArguments) input.getArguments();
        fillConfiguration(configuration, k2JSCompilerArguments);
        checkWasmArgumentsUsage$org_jetbrains_kotlin_cli_js(k2JSCompilerArguments, configuration);
        if (k2JSCompilerArguments.getIncludes() != null) {
            PhaseConfig phaseConfigCreatePhaseConfig$default = CreatePhaseConfigKt.createPhaseConfig$default(k2JSCompilerArguments, null, 2, null);
            if (k2JSCompilerArguments.getListPhases()) {
                CreatePhaseConfigKt.listPhases(phaseConfigCreatePhaseConfig$default, JsLoweringPhasesKt.getJsLowerings());
            }
            CommonConfigurationKeysKt.setPhaseConfig(configuration, phaseConfigCreatePhaseConfig$default);
        }
    }
}
