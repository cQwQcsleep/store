package org.jetbrains.kotlin.cli.pipeline.metadata;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.FrontendPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0005H\u0017J\u0017\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0018\u0010\fJ\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J4\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020%HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataFrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "frontendOutput", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "sourceFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getFrontendOutput-QYgrGdg", "()Ljava/util/List;", "Ljava/util/List;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getSourceFiles", "withCompilerConfiguration", "newConfiguration", "withNewFrontendOutputImpl", "newFrontendOutput", "withNewFrontendOutputImpl-06ismGs", "(Ljava/util/List;)Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "component1", "component1-QYgrGdg", "component2", "component3", "copy", "copy-BQXXH2g", "(Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;)Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataFrontendPipelineArtifact;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class MetadataFrontendPipelineArtifact extends FrontendPipelineArtifact {
    private final CompilerConfiguration configuration;
    private final List<? extends SingleModuleFrontendOutput> frontendOutput;
    private final List<KtSourceFile> sourceFiles;

    /* JADX WARN: Multi-variable type inference failed */
    private MetadataFrontendPipelineArtifact(List<? extends SingleModuleFrontendOutput> list, CompilerConfiguration compilerConfiguration, List<? extends KtSourceFile> list2) {
        list.getClass();
        compilerConfiguration.getClass();
        list2.getClass();
        this.frontendOutput = list;
        this.configuration = compilerConfiguration;
        this.sourceFiles = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-BQXXH2g$default, reason: not valid java name */
    public static /* synthetic */ MetadataFrontendPipelineArtifact m37copyBQXXH2g$default(MetadataFrontendPipelineArtifact metadataFrontendPipelineArtifact, List list, CompilerConfiguration compilerConfiguration, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = metadataFrontendPipelineArtifact.frontendOutput;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = metadataFrontendPipelineArtifact.configuration;
        }
        if ((i & 4) != 0) {
            list2 = metadataFrontendPipelineArtifact.sourceFiles;
        }
        return metadataFrontendPipelineArtifact.m39copyBQXXH2g(list, compilerConfiguration, list2);
    }

    /* JADX INFO: renamed from: component1-QYgrGdg, reason: not valid java name */
    public final List<? extends SingleModuleFrontendOutput> m38component1QYgrGdg() {
        return this.frontendOutput;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final List<KtSourceFile> component3() {
        return this.sourceFiles;
    }

    /* JADX INFO: renamed from: copy-BQXXH2g, reason: not valid java name */
    public final MetadataFrontendPipelineArtifact m39copyBQXXH2g(List<? extends SingleModuleFrontendOutput> frontendOutput, CompilerConfiguration configuration, List<? extends KtSourceFile> sourceFiles) {
        frontendOutput.getClass();
        configuration.getClass();
        sourceFiles.getClass();
        return new MetadataFrontendPipelineArtifact(frontendOutput, configuration, sourceFiles, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetadataFrontendPipelineArtifact)) {
            return false;
        }
        MetadataFrontendPipelineArtifact metadataFrontendPipelineArtifact = (MetadataFrontendPipelineArtifact) other;
        return AllModulesFrontendOutput.m575equalsimpl0(this.frontendOutput, metadataFrontendPipelineArtifact.frontendOutput) && Intrinsics.areEqual(this.configuration, metadataFrontendPipelineArtifact.configuration) && Intrinsics.areEqual(this.sourceFiles, metadataFrontendPipelineArtifact.sourceFiles);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.FrontendPipelineArtifact, org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.FrontendPipelineArtifact
    /* JADX INFO: renamed from: getFrontendOutput-QYgrGdg */
    public List<? extends SingleModuleFrontendOutput> mo31getFrontendOutputQYgrGdg() {
        return this.frontendOutput;
    }

    public final List<KtSourceFile> getSourceFiles() {
        return this.sourceFiles;
    }

    public int hashCode() {
        return (((AllModulesFrontendOutput.m576hashCodeimpl(this.frontendOutput) * 31) + this.configuration.hashCode()) * 31) + this.sourceFiles.hashCode();
    }

    public String toString() {
        return "MetadataFrontendPipelineArtifact(frontendOutput=" + ((Object) AllModulesFrontendOutput.m577toStringimpl(this.frontendOutput)) + ", configuration=" + this.configuration + ", sourceFiles=" + this.sourceFiles + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public MetadataFrontendPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return m37copyBQXXH2g$default(this, null, newConfiguration, null, 5, null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.FrontendPipelineArtifact
    /* JADX INFO: renamed from: withNewFrontendOutputImpl-06ismGs */
    public FrontendPipelineArtifact mo32withNewFrontendOutputImpl06ismGs(List<? extends SingleModuleFrontendOutput> newFrontendOutput) {
        newFrontendOutput.getClass();
        return m37copyBQXXH2g$default(this, newFrontendOutput, null, null, 6, null);
    }

    public /* synthetic */ MetadataFrontendPipelineArtifact(List list, CompilerConfiguration compilerConfiguration, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, compilerConfiguration, list2);
    }
}
