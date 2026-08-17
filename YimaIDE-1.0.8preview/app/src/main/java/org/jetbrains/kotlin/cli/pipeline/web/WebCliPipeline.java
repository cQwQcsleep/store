package org.jetbrains.kotlin.cli.pipeline.web;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt;
import org.jetbrains.kotlin.cli.common.arguments.CommonJsAndWasmCompilerArguments;
import org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.FrontendFilesForPluginsGenerationPipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.PipelineContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.CompilerPhase;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0002\b\u00030\u000bH&J+\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0002\b\u00030\u000b2\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010J \u0010\u0011\u001a\u001a\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0004\u0012\u00020\u00120\u000bH\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR*\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0004\u0012\u00020\u00140\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/WebCliPipeline;", "T", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;", "Lorg/jetbrains/kotlin/cli/pipeline/AbstractCliPipeline;", "defaultPerformanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "<init>", "(Lorg/jetbrains/kotlin/util/PerformanceManager;)V", "getDefaultPerformanceManager", "()Lorg/jetbrains/kotlin/util/PerformanceManager;", "createCodeGenerationPhase", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "createCompoundPhase", "arguments", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;)Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "createKlibSerializationPhase", "Lorg/jetbrains/kotlin/cli/pipeline/web/JsSerializedKlibPipelineArtifact;", "webConfigurationPhase", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", "getWebConfigurationPhase", "()Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class WebCliPipeline<T extends CommonJsAndWasmCompilerArguments> extends AbstractCliPipeline<T> {
    private final PerformanceManager defaultPerformanceManager;

    public WebCliPipeline(PerformanceManager performanceManager) {
        performanceManager.getClass();
        this.defaultPerformanceManager = performanceManager;
    }

    private final CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<T>, JsSerializedKlibPipelineArtifact> createKlibSerializationPhase() {
        return PhaseBuildersKt.then(PhaseBuildersKt.then(PhaseBuildersKt.then(PhaseBuildersKt.then(PhaseBuildersKt.then(getWebConfigurationPhase(), WebFrontendPipelinePhase.INSTANCE), new FrontendFilesForPluginsGenerationPipelinePhase()), WebFir2IrPipelinePhase.INSTANCE), WebKlibInliningPipelinePhase.INSTANCE), WebKlibSerializationPipelinePhase.INSTANCE);
    }

    public abstract CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<T>, ?> createCodeGenerationPhase();

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline
    public CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<T>, ?> createCompoundPhase(T arguments) {
        arguments.getClass();
        return arguments.getIncludes() != null ? createCodeGenerationPhase() : createKlibSerializationPhase();
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline
    public PerformanceManager getDefaultPerformanceManager() {
        return this.defaultPerformanceManager;
    }

    public abstract CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<T>, ConfigurationPipelineArtifact> getWebConfigurationPhase();
}
