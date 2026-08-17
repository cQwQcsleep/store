package org.jetbrains.kotlin.cli.pipeline.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.metadata.AbstractMetadataSerializer;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0005H\u0017J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J)\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0007HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataSerializationArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "outputInfo", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "destination", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/lang/String;)V", "getOutputInfo", "()Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getDestination", "()Ljava/lang/String;", "withCompilerConfiguration", "newConfiguration", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class MetadataSerializationArtifact extends PipelineArtifact {
    private final CompilerConfiguration configuration;
    private final String destination;
    private final AbstractMetadataSerializer.OutputInfo outputInfo;

    public MetadataSerializationArtifact(AbstractMetadataSerializer.OutputInfo outputInfo, CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        str.getClass();
        this.outputInfo = outputInfo;
        this.configuration = compilerConfiguration;
        this.destination = str;
    }

    public static /* synthetic */ MetadataSerializationArtifact copy$default(MetadataSerializationArtifact metadataSerializationArtifact, AbstractMetadataSerializer.OutputInfo outputInfo, CompilerConfiguration compilerConfiguration, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            outputInfo = metadataSerializationArtifact.outputInfo;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = metadataSerializationArtifact.configuration;
        }
        if ((i & 4) != 0) {
            str = metadataSerializationArtifact.destination;
        }
        return metadataSerializationArtifact.copy(outputInfo, compilerConfiguration, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AbstractMetadataSerializer.OutputInfo getOutputInfo() {
        return this.outputInfo;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    public final MetadataSerializationArtifact copy(AbstractMetadataSerializer.OutputInfo outputInfo, CompilerConfiguration configuration, String destination) {
        configuration.getClass();
        destination.getClass();
        return new MetadataSerializationArtifact(outputInfo, configuration, destination);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetadataSerializationArtifact)) {
            return false;
        }
        MetadataSerializationArtifact metadataSerializationArtifact = (MetadataSerializationArtifact) other;
        return Intrinsics.areEqual(this.outputInfo, metadataSerializationArtifact.outputInfo) && Intrinsics.areEqual(this.configuration, metadataSerializationArtifact.configuration) && Intrinsics.areEqual(this.destination, metadataSerializationArtifact.destination);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final AbstractMetadataSerializer.OutputInfo getOutputInfo() {
        return this.outputInfo;
    }

    public int hashCode() {
        AbstractMetadataSerializer.OutputInfo outputInfo = this.outputInfo;
        return ((((outputInfo == null ? 0 : outputInfo.hashCode()) * 31) + this.configuration.hashCode()) * 31) + this.destination.hashCode();
    }

    public String toString() {
        return "MetadataSerializationArtifact(outputInfo=" + this.outputInfo + ", configuration=" + this.configuration + ", destination=" + this.destination + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public MetadataSerializationArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, null, newConfiguration, null, 5, null);
    }
}
