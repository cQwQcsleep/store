package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.FirFindMainClassKt;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.JvmCompilerPipelineKt;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.compiler.plugin.ExtensionPointUtilsKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.jvm.JvmFir2IrExtensions;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFir2IrPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFir2IrPipelineArtifact;", "<init>", "()V", "executePhase", "input", "irGenerationExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/common/extensions/IrGenerationExtension;", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmFir2IrPipelinePhase extends PipelinePhase<JvmFrontendPipelineArtifact, JvmFir2IrPipelineArtifact> {
    public static final JvmFir2IrPipelinePhase INSTANCE = new JvmFir2IrPipelinePhase();

    private JvmFir2IrPipelinePhase() {
        super("JvmFir2IrPipelinePhase", SetsKt.setOf(PerformanceNotifications.TranslationToIrStarted.INSTANCE), SetsKt.setOf(new Function3[]{PerformanceNotifications.TranslationToIrFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}));
    }

    public final JvmFir2IrPipelineArtifact executePhase(JvmFrontendPipelineArtifact input, List<? extends IrGenerationExtension> irGenerationExtensions) {
        input.getClass();
        irGenerationExtensions.getClass();
        List<? extends SingleModuleFrontendOutput> listM35component1QYgrGdg = input.m35component1QYgrGdg();
        CompilerConfiguration configuration = input.getConfiguration();
        return new JvmFir2IrPipelineArtifact(JvmCompilerPipelineKt.m23convertToIrAndActualizeForJvm_I1IRw(listM35component1QYgrGdg, new JvmFir2IrExtensions(configuration), configuration, CLIConfigurationKeysKt.getDiagnosticsCollector(configuration), irGenerationExtensions), configuration, input.getEnvironment(), input.component4(), configuration.get(JVMConfigurationKeys.OUTPUT_JAR) != null ? FirFindMainClassKt.findMainClass(((SingleModuleFrontendOutput) CollectionsKt.last(listM35component1QYgrGdg)).getFir()) : null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public JvmFir2IrPipelineArtifact executePhase(JvmFrontendPipelineArtifact input) {
        input.getClass();
        return executePhase(input, ExtensionPointUtilsKt.getCompilerExtensions(input.getConfiguration(), IrGenerationExtension.Companion));
    }
}
