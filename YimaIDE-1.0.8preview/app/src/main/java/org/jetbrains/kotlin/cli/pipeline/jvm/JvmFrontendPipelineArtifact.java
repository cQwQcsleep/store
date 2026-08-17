package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.pipeline.FrontendPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0005H\u0017J\u0017\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J>\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0083\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004J\n\u0010)\u001a\u00020*HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "frontendOutput", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "sourceFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getFrontendOutput-QYgrGdg", "()Ljava/util/List;", "Ljava/util/List;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "getSourceFiles", "withCompilerConfiguration", "newConfiguration", "withNewFrontendOutputImpl", "newFrontendOutput", "withNewFrontendOutputImpl-06ismGs", "(Ljava/util/List;)Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "component1", "component1-QYgrGdg", "component2", "component3", "component4", "copy", "copy-tySObjA", "(Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Ljava/util/List;)Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFrontendPipelineArtifact;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JvmFrontendPipelineArtifact extends FrontendPipelineArtifact {
    private final CompilerConfiguration configuration;
    private final VfsBasedProjectEnvironment environment;
    private final List<? extends SingleModuleFrontendOutput> frontendOutput;
    private final List<KtSourceFile> sourceFiles;

    /* JADX WARN: Multi-variable type inference failed */
    private JvmFrontendPipelineArtifact(List<? extends SingleModuleFrontendOutput> list, CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, List<? extends KtSourceFile> list2) {
        list.getClass();
        compilerConfiguration.getClass();
        vfsBasedProjectEnvironment.getClass();
        list2.getClass();
        this.frontendOutput = list;
        this.configuration = compilerConfiguration;
        this.environment = vfsBasedProjectEnvironment;
        this.sourceFiles = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-tySObjA$default, reason: not valid java name */
    public static /* synthetic */ JvmFrontendPipelineArtifact m34copytySObjA$default(JvmFrontendPipelineArtifact jvmFrontendPipelineArtifact, List list, CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = jvmFrontendPipelineArtifact.frontendOutput;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = jvmFrontendPipelineArtifact.configuration;
        }
        if ((i & 4) != 0) {
            vfsBasedProjectEnvironment = jvmFrontendPipelineArtifact.environment;
        }
        if ((i & 8) != 0) {
            list2 = jvmFrontendPipelineArtifact.sourceFiles;
        }
        return jvmFrontendPipelineArtifact.m36copytySObjA(list, compilerConfiguration, vfsBasedProjectEnvironment, list2);
    }

    /* JADX INFO: renamed from: component1-QYgrGdg, reason: not valid java name */
    public final List<? extends SingleModuleFrontendOutput> m35component1QYgrGdg() {
        return this.frontendOutput;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final VfsBasedProjectEnvironment getEnvironment() {
        return this.environment;
    }

    public final List<KtSourceFile> component4() {
        return this.sourceFiles;
    }

    /* JADX INFO: renamed from: copy-tySObjA, reason: not valid java name */
    public final JvmFrontendPipelineArtifact m36copytySObjA(List<? extends SingleModuleFrontendOutput> frontendOutput, CompilerConfiguration configuration, VfsBasedProjectEnvironment environment, List<? extends KtSourceFile> sourceFiles) {
        frontendOutput.getClass();
        configuration.getClass();
        environment.getClass();
        sourceFiles.getClass();
        return new JvmFrontendPipelineArtifact(frontendOutput, configuration, environment, sourceFiles, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JvmFrontendPipelineArtifact)) {
            return false;
        }
        JvmFrontendPipelineArtifact jvmFrontendPipelineArtifact = (JvmFrontendPipelineArtifact) other;
        return AllModulesFrontendOutput.m575equalsimpl0(this.frontendOutput, jvmFrontendPipelineArtifact.frontendOutput) && Intrinsics.areEqual(this.configuration, jvmFrontendPipelineArtifact.configuration) && Intrinsics.areEqual(this.environment, jvmFrontendPipelineArtifact.environment) && Intrinsics.areEqual(this.sourceFiles, jvmFrontendPipelineArtifact.sourceFiles);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.FrontendPipelineArtifact, org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final VfsBasedProjectEnvironment getEnvironment() {
        return this.environment;
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
        return (((((AllModulesFrontendOutput.m576hashCodeimpl(this.frontendOutput) * 31) + this.configuration.hashCode()) * 31) + this.environment.hashCode()) * 31) + this.sourceFiles.hashCode();
    }

    public String toString() {
        return "JvmFrontendPipelineArtifact(frontendOutput=" + ((Object) AllModulesFrontendOutput.m577toStringimpl(this.frontendOutput)) + ", configuration=" + this.configuration + ", environment=" + this.environment + ", sourceFiles=" + this.sourceFiles + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public JvmFrontendPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return m34copytySObjA$default(this, null, newConfiguration, null, null, 13, null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.FrontendPipelineArtifact
    /* JADX INFO: renamed from: withNewFrontendOutputImpl-06ismGs */
    public FrontendPipelineArtifact mo32withNewFrontendOutputImpl06ismGs(List<? extends SingleModuleFrontendOutput> newFrontendOutput) {
        newFrontendOutput.getClass();
        return m34copytySObjA$default(this, newFrontendOutput, null, null, null, 14, null);
    }

    public /* synthetic */ JvmFrontendPipelineArtifact(List list, CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, compilerConfiguration, vfsBasedProjectEnvironment, list2);
    }
}
