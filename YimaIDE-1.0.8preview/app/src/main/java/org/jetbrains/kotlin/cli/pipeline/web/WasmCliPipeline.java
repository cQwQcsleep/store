package org.jetbrains.kotlin.cli.pipeline.web;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt;
import org.jetbrains.kotlin.cli.common.arguments.KotlinWasmCompilerArguments;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PipelineContext;
import org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmBackendPipelinePhase;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.CompilerPhase;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\"\u0010\u0007\u001a\u001c\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\bH\u0016R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/WasmCliPipeline;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebCliPipeline;", "Lorg/jetbrains/kotlin/cli/common/arguments/KotlinWasmCompilerArguments;", "defaultPerformanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "<init>", "(Lorg/jetbrains/kotlin/util/PerformanceManager;)V", "createCodeGenerationPhase", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelineArtifact;", "webConfigurationPhase", "Lorg/jetbrains/kotlin/cli/pipeline/web/WasmConfigurationPhase;", "getWebConfigurationPhase", "()Lorg/jetbrains/kotlin/cli/pipeline/web/WasmConfigurationPhase;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WasmCliPipeline extends WebCliPipeline<KotlinWasmCompilerArguments> {
    private final WasmConfigurationPhase webConfigurationPhase;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WasmCliPipeline(PerformanceManager performanceManager) {
        super(performanceManager);
        performanceManager.getClass();
        this.webConfigurationPhase = WasmConfigurationPhase.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebCliPipeline
    public CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<KotlinWasmCompilerArguments>, ? extends WebBackendPipelineArtifact> createCodeGenerationPhase() {
        return PhaseBuildersKt.then(WasmConfigurationPhase.INSTANCE, WasmBackendPipelinePhase.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.web.WebCliPipeline
    public WasmConfigurationPhase getWebConfigurationPhase() {
        return this.webConfigurationPhase;
    }
}
