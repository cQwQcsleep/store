package org.jetbrains.kotlin.cli.pipeline.web;

import com.intellij.openapi.progress.ProcessCanceledException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.backend.common.phaser.PhaseEngine;
import org.jetbrains.kotlin.backend.common.serialization.metadata.KlibSingleFileMetadataSerializer;
import org.jetbrains.kotlin.backend.wasm.WasmLoweringPhasesKt;
import org.jetbrains.kotlin.backend.wasm.WasmPreSerializationLoweringContext;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.PreSerializationLoweringKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.web.WebKlibInliningPipelinePhase;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.PhaseConfig;
import org.jetbrains.kotlin.config.phaser.PhaserState;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.fir.pipeline.Fir2KlibMetadataSerializer;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.ir.KtDiagnosticReporterWithImplicitIrBasedContext;
import org.jetbrains.kotlin.ir.backend.js.JsLoweringPhasesKt;
import org.jetbrains.kotlin.ir.backend.js.JsPreSerializationLoweringContext;
import org.jetbrains.kotlin.ir.backend.js.KlibKt;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.progress.IncrementalNextRoundException;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J'\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/WebKlibInliningPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/web/JsFir2IrPipelineArtifact;", "<init>", "()V", "executePhase", "input", "processIncrementalCompilationRoundIfNeeded", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "frontendOutput", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "fir2IrResult", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "processIncrementalCompilationRoundIfNeeded-X5EfiWk", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;)V", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WebKlibInliningPipelinePhase extends PipelinePhase<JsFir2IrPipelineArtifact, JsFir2IrPipelineArtifact> {
    public static final WebKlibInliningPipelinePhase INSTANCE = new WebKlibInliningPipelinePhase();

    private WebKlibInliningPipelinePhase() {
        super("WebKlibInliningPipelinePhase", SetsKt.setOf(PerformanceNotifications.IrPreLoweringStarted.INSTANCE), SetsKt.setOf(new Function3[]{PerformanceNotifications.IrPreLoweringFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}));
    }

    public static KlibSingleFileMetadataSerializer a(CompilerConfiguration compilerConfiguration, List list, Fir2IrActualizedResult fir2IrActualizedResult) {
        return new Fir2KlibMetadataSerializer(compilerConfiguration, list, fir2IrActualizedResult, false);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.openapi.progress.ProcessCanceledException */
    /* JADX INFO: renamed from: processIncrementalCompilationRoundIfNeeded-X5EfiWk, reason: not valid java name */
    private final void m49processIncrementalCompilationRoundIfNeededX5EfiWk(final CompilerConfiguration configuration, final List<? extends SingleModuleFrontendOutput> frontendOutput, final Fir2IrActualizedResult fir2IrResult) throws ProcessCanceledException {
        if (CommonConfigurationKeysKt.getIncrementalCompilation(configuration) && KlibKt.shouldGoToNextIcRound(configuration, new Function0() { // from class: slf
            public final Object invoke() {
                return WebKlibInliningPipelinePhase.a(configuration, frontendOutput, fir2IrResult);
            }
        })) {
            throw new IncrementalNextRoundException();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.openapi.progress.ProcessCanceledException */
    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public JsFir2IrPipelineArtifact executePhase(JsFir2IrPipelineArtifact input) throws ProcessCanceledException {
        Fir2IrActualizedResult fir2IrActualizedResultRunPreSerializationLoweringPhases;
        input.getClass();
        Fir2IrActualizedResult result = input.getResult();
        List<? extends SingleModuleFrontendOutput> listM41component2QYgrGdg = input.m41component2QYgrGdg();
        CompilerConfiguration configuration = input.getConfiguration();
        m49processIncrementalCompilationRoundIfNeededX5EfiWk(configuration, listM41component2QYgrGdg, result);
        KtDiagnosticReporterWithImplicitIrBasedContext ktDiagnosticReporterWithImplicitIrBasedContext = new KtDiagnosticReporterWithImplicitIrBasedContext(CLIConfigurationKeysKt.getDiagnosticsCollector(configuration), CommonConfigurationKeysKt.getLanguageVersionSettings(configuration));
        if (JSConfigurationKeysKt.getWasmCompilation(configuration)) {
            PhaseConfig phaseConfig = CommonConfigurationKeysKt.getPhaseConfig(configuration);
            phaseConfig.getClass();
            fir2IrActualizedResultRunPreSerializationLoweringPhases = PreSerializationLoweringKt.runPreSerializationLoweringPhases(new PhaseEngine(phaseConfig, new PhaserState(null, 0, 0, 7, null), new WasmPreSerializationLoweringContext(result.getIrBuiltIns(), configuration, ktDiagnosticReporterWithImplicitIrBasedContext)), result, WasmLoweringPhasesKt.wasmLoweringsOfTheFirstPhase(CommonConfigurationKeysKt.getLanguageVersionSettings(configuration)));
        } else {
            PhaseConfig phaseConfig2 = CommonConfigurationKeysKt.getPhaseConfig(configuration);
            phaseConfig2.getClass();
            fir2IrActualizedResultRunPreSerializationLoweringPhases = PreSerializationLoweringKt.runPreSerializationLoweringPhases(new PhaseEngine(phaseConfig2, new PhaserState(null, 0, 0, 7, null), new JsPreSerializationLoweringContext(result.getIrBuiltIns(), configuration, ktDiagnosticReporterWithImplicitIrBasedContext)), result, JsLoweringPhasesKt.jsLoweringsOfTheFirstPhase(CommonConfigurationKeysKt.getLanguageVersionSettings(configuration)));
        }
        return JsFir2IrPipelineArtifact.m40copyjlVyY7s$default(input, fir2IrActualizedResultRunPreSerializationLoweringPhases, null, null, false, 14, null);
    }
}
