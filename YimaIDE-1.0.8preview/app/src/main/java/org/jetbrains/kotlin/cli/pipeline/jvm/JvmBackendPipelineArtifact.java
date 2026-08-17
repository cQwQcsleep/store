package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0003H\u0017J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J9\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0083\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmBackendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "mainClassFqName", "Lorg/jetbrains/kotlin/name/FqName;", "outputs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Lorg/jetbrains/kotlin/name/FqName;Ljava/util/List;)V", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "getMainClassFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getOutputs", "()Ljava/util/List;", "withCompilerConfiguration", "newConfiguration", "component1", "component2", "component3", "component4", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JvmBackendPipelineArtifact extends PipelineArtifact {
    private final CompilerConfiguration configuration;
    private final VfsBasedProjectEnvironment environment;
    private final FqName mainClassFqName;
    private final List<GenerationState> outputs;

    public JvmBackendPipelineArtifact(CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, FqName fqName, List<GenerationState> list) {
        compilerConfiguration.getClass();
        vfsBasedProjectEnvironment.getClass();
        list.getClass();
        this.configuration = compilerConfiguration;
        this.environment = vfsBasedProjectEnvironment;
        this.mainClassFqName = fqName;
        this.outputs = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JvmBackendPipelineArtifact copy$default(JvmBackendPipelineArtifact jvmBackendPipelineArtifact, CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, FqName fqName, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            compilerConfiguration = jvmBackendPipelineArtifact.configuration;
        }
        if ((i & 2) != 0) {
            vfsBasedProjectEnvironment = jvmBackendPipelineArtifact.environment;
        }
        if ((i & 4) != 0) {
            fqName = jvmBackendPipelineArtifact.mainClassFqName;
        }
        if ((i & 8) != 0) {
            list = jvmBackendPipelineArtifact.outputs;
        }
        return jvmBackendPipelineArtifact.copy(compilerConfiguration, vfsBasedProjectEnvironment, fqName, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final VfsBasedProjectEnvironment getEnvironment() {
        return this.environment;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final FqName getMainClassFqName() {
        return this.mainClassFqName;
    }

    public final List<GenerationState> component4() {
        return this.outputs;
    }

    public final JvmBackendPipelineArtifact copy(CompilerConfiguration configuration, VfsBasedProjectEnvironment environment, FqName mainClassFqName, List<GenerationState> outputs) {
        configuration.getClass();
        environment.getClass();
        outputs.getClass();
        return new JvmBackendPipelineArtifact(configuration, environment, mainClassFqName, outputs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JvmBackendPipelineArtifact)) {
            return false;
        }
        JvmBackendPipelineArtifact jvmBackendPipelineArtifact = (JvmBackendPipelineArtifact) other;
        return Intrinsics.areEqual(this.configuration, jvmBackendPipelineArtifact.configuration) && Intrinsics.areEqual(this.environment, jvmBackendPipelineArtifact.environment) && Intrinsics.areEqual(this.mainClassFqName, jvmBackendPipelineArtifact.mainClassFqName) && Intrinsics.areEqual(this.outputs, jvmBackendPipelineArtifact.outputs);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final VfsBasedProjectEnvironment getEnvironment() {
        return this.environment;
    }

    public final FqName getMainClassFqName() {
        return this.mainClassFqName;
    }

    public final List<GenerationState> getOutputs() {
        return this.outputs;
    }

    public int hashCode() {
        int iHashCode = ((this.configuration.hashCode() * 31) + this.environment.hashCode()) * 31;
        FqName fqName = this.mainClassFqName;
        return ((iHashCode + (fqName == null ? 0 : fqName.hashCode())) * 31) + this.outputs.hashCode();
    }

    public String toString() {
        return "JvmBackendPipelineArtifact(configuration=" + this.configuration + ", environment=" + this.environment + ", mainClassFqName=" + this.mainClassFqName + ", outputs=" + this.outputs + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public JvmBackendPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, newConfiguration, null, null, null, 14, null);
    }
}
