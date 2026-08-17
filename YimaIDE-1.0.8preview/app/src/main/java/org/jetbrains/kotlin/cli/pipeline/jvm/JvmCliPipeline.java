package org.jetbrains.kotlin.cli.pipeline.jvm;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler;
import org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.FrontendFilesForPluginsGenerationPipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.PipelineContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.config.phaser.CompilerPhase;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J&\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\f\u0012\u0002\b\u00030\n2\u0006\u0010\r\u001a\u00020\u0002H\u0016J \u0010\u000e\u001a\u001a\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\f\u0012\u0004\u0012\u00020\u000f0\nH\u0002J \u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\f\u0012\u0004\u0012\u00020\u00110\nH\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u0002H\u0014J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0012\u001a\u00020\u0013*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmCliPipeline;", "Lorg/jetbrains/kotlin/cli/pipeline/AbstractCliPipeline;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "defaultPerformanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "<init>", "(Lorg/jetbrains/kotlin/util/PerformanceManager;)V", "getDefaultPerformanceManager", "()Lorg/jetbrains/kotlin/util/PerformanceManager;", "createCompoundPhase", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "arguments", "createRegularPipeline", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmBinaryPipelineArtifact;", "createScriptPipeline", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmScriptPipelineArtifact;", "scriptingModeEnabled", Argument.Delimiters.none, "getScriptingModeEnabled", "(Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;)Z", "isKaptMode", "createPerformanceManager", "services", "Lorg/jetbrains/kotlin/config/Services;", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmCliPipeline extends AbstractCliPipeline<K2JVMCompilerArguments> {
    private final PerformanceManager defaultPerformanceManager;

    public JvmCliPipeline(PerformanceManager performanceManager) {
        performanceManager.getClass();
        this.defaultPerformanceManager = performanceManager;
    }

    private final CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<K2JVMCompilerArguments>, JvmBinaryPipelineArtifact> createRegularPipeline() {
        return PhaseBuildersKt.then(PhaseBuildersKt.then(PhaseBuildersKt.then(PhaseBuildersKt.then(PhaseBuildersKt.then(JvmConfigurationPipelinePhase.INSTANCE, JvmFrontendPipelinePhase.INSTANCE), new FrontendFilesForPluginsGenerationPipelinePhase()), JvmFir2IrPipelinePhase.INSTANCE), JvmBackendPipelinePhase.INSTANCE), JvmWriteOutputsPhase.INSTANCE);
    }

    private final CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<K2JVMCompilerArguments>, JvmScriptPipelineArtifact> createScriptPipeline() {
        return PhaseBuildersKt.then(JvmConfigurationPipelinePhase.INSTANCE, JvmScriptPipelinePhase.INSTANCE);
    }

    private final boolean getScriptingModeEnabled(K2JVMCompilerArguments k2JVMCompilerArguments) {
        if (k2JVMCompilerArguments.getBuildFile() != null || k2JVMCompilerArguments.getVersion() || k2JVMCompilerArguments.getAllowNoSourceFiles()) {
            return false;
        }
        return k2JVMCompilerArguments.getScript() || k2JVMCompilerArguments.getExpression() != null || k2JVMCompilerArguments.getRepl() || k2JVMCompilerArguments.getFreeArgs().isEmpty();
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline
    public CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<K2JVMCompilerArguments>, ?> createCompoundPhase(K2JVMCompilerArguments arguments) {
        arguments.getClass();
        return getScriptingModeEnabled(arguments) ? createScriptPipeline() : createRegularPipeline();
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline
    public PerformanceManager createPerformanceManager(K2JVMCompilerArguments arguments, Services services) {
        arguments.getClass();
        services.getClass();
        PerformanceManager performanceManagerCreateCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm = K2JVMCompiler.INSTANCE.createCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm(arguments, services);
        return performanceManagerCreateCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm == null ? getDefaultPerformanceManager() : performanceManagerCreateCustomPerformanceManagerOrNull$org_jetbrains_kotlin_cli_jvm;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline
    public PerformanceManager getDefaultPerformanceManager() {
        return this.defaultPerformanceManager;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline
    public boolean isKaptMode(K2JVMCompilerArguments arguments) {
        arguments.getClass();
        String[] pluginOptions = arguments.getPluginOptions();
        if (pluginOptions != null) {
            for (String str : pluginOptions) {
                if (StringsKt.startsWith$default(str, "plugin:org.jetbrains.kotlin.kapt3", false, 2, (Object) null)) {
                    return true;
                }
            }
        }
        return false;
    }
}
