package org.jetbrains.kotlin.cli.pipeline.metadata;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt;
import org.jetbrains.kotlin.cli.common.arguments.K2MetadataCompilerArguments;
import org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline;
import org.jetbrains.kotlin.cli.pipeline.ArgumentsPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.FrontendFilesForPluginsGenerationPipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.PipelineContext;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.phaser.CompilerPhase;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J&\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\f\u0012\u0002\b\u00030\n2\u0006\u0010\r\u001a\u00020\u0002H\u0016J\"\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010\r\u001a\u00020\u0002H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataCliPipeline;", "Lorg/jetbrains/kotlin/cli/pipeline/AbstractCliPipeline;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2MetadataCompilerArguments;", "defaultPerformanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "<init>", "(Lorg/jetbrains/kotlin/util/PerformanceManager;)V", "getDefaultPerformanceManager", "()Lorg/jetbrains/kotlin/util/PerformanceManager;", "createCompoundPhase", "Lorg/jetbrains/kotlin/config/phaser/CompilerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineContext;", "Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "arguments", "serializerPhase", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataFrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataSerializationArtifact;", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataCliPipeline extends AbstractCliPipeline<K2MetadataCompilerArguments> {
    private final PerformanceManager defaultPerformanceManager;

    public MetadataCliPipeline(PerformanceManager performanceManager) {
        performanceManager.getClass();
        this.defaultPerformanceManager = performanceManager;
    }

    private final CompilerPhase<PipelineContext, MetadataFrontendPipelineArtifact, MetadataSerializationArtifact> serializerPhase(K2MetadataCompilerArguments arguments) {
        return arguments.getLegacyMetadataJar() ? MetadataLegacySerializerPhase.INSTANCE : PhaseBuildersKt.then(MetadataKlibInMemorySerializerPhase.INSTANCE, MetadataKlibFileWriterPhase.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline
    public CompilerPhase<PipelineContext, ArgumentsPipelineArtifact<K2MetadataCompilerArguments>, ?> createCompoundPhase(K2MetadataCompilerArguments arguments) {
        arguments.getClass();
        return PhaseBuildersKt.then(PhaseBuildersKt.then(PhaseBuildersKt.then(MetadataConfigurationPipelinePhase.INSTANCE, MetadataFrontendPipelinePhase.INSTANCE), new FrontendFilesForPluginsGenerationPipelinePhase()), serializerPhase(arguments));
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractCliPipeline
    public PerformanceManager getDefaultPerformanceManager() {
        return this.defaultPerformanceManager;
    }
}
