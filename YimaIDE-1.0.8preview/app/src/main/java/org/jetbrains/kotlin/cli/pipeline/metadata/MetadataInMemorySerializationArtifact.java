package org.jetbrains.kotlin.cli.pipeline.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.library.SerializedMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0005H\u0017J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataInMemorySerializationArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "metadata", "Lorg/jetbrains/kotlin/library/SerializedMetadata;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/library/SerializedMetadata;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getMetadata", "()Lorg/jetbrains/kotlin/library/SerializedMetadata;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "newConfiguration", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class MetadataInMemorySerializationArtifact extends PipelineArtifact {
    private final CompilerConfiguration configuration;
    private final SerializedMetadata metadata;

    public MetadataInMemorySerializationArtifact(SerializedMetadata serializedMetadata, CompilerConfiguration compilerConfiguration) {
        serializedMetadata.getClass();
        compilerConfiguration.getClass();
        this.metadata = serializedMetadata;
        this.configuration = compilerConfiguration;
    }

    public static /* synthetic */ MetadataInMemorySerializationArtifact copy$default(MetadataInMemorySerializationArtifact metadataInMemorySerializationArtifact, SerializedMetadata serializedMetadata, CompilerConfiguration compilerConfiguration, int i, Object obj) {
        if ((i & 1) != 0) {
            serializedMetadata = metadataInMemorySerializationArtifact.metadata;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = metadataInMemorySerializationArtifact.configuration;
        }
        return metadataInMemorySerializationArtifact.copy(serializedMetadata, compilerConfiguration);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SerializedMetadata getMetadata() {
        return this.metadata;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final MetadataInMemorySerializationArtifact copy(SerializedMetadata metadata, CompilerConfiguration configuration) {
        metadata.getClass();
        configuration.getClass();
        return new MetadataInMemorySerializationArtifact(metadata, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetadataInMemorySerializationArtifact)) {
            return false;
        }
        MetadataInMemorySerializationArtifact metadataInMemorySerializationArtifact = (MetadataInMemorySerializationArtifact) other;
        return Intrinsics.areEqual(this.metadata, metadataInMemorySerializationArtifact.metadata) && Intrinsics.areEqual(this.configuration, metadataInMemorySerializationArtifact.configuration);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final SerializedMetadata getMetadata() {
        return this.metadata;
    }

    public int hashCode() {
        return (this.metadata.hashCode() * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "MetadataInMemorySerializationArtifact(metadata=" + this.metadata + ", configuration=" + this.configuration + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public MetadataInMemorySerializationArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, null, newConfiguration, 1, null);
    }
}
