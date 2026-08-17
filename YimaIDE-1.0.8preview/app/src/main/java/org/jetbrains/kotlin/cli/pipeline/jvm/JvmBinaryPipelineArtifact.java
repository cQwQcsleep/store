package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006H\u0017J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J#\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmBinaryPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "outputs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getOutputs", "()Ljava/util/List;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "newConfiguration", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JvmBinaryPipelineArtifact extends PipelineArtifact {
    private final CompilerConfiguration configuration;
    private final List<GenerationState> outputs;

    public JvmBinaryPipelineArtifact(List<GenerationState> list, CompilerConfiguration compilerConfiguration) {
        list.getClass();
        compilerConfiguration.getClass();
        this.outputs = list;
        this.configuration = compilerConfiguration;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JvmBinaryPipelineArtifact copy$default(JvmBinaryPipelineArtifact jvmBinaryPipelineArtifact, List list, CompilerConfiguration compilerConfiguration, int i, Object obj) {
        if ((i & 1) != 0) {
            list = jvmBinaryPipelineArtifact.outputs;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = jvmBinaryPipelineArtifact.configuration;
        }
        return jvmBinaryPipelineArtifact.copy(list, compilerConfiguration);
    }

    public final List<GenerationState> component1() {
        return this.outputs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final JvmBinaryPipelineArtifact copy(List<GenerationState> outputs, CompilerConfiguration configuration) {
        outputs.getClass();
        configuration.getClass();
        return new JvmBinaryPipelineArtifact(outputs, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JvmBinaryPipelineArtifact)) {
            return false;
        }
        JvmBinaryPipelineArtifact jvmBinaryPipelineArtifact = (JvmBinaryPipelineArtifact) other;
        return Intrinsics.areEqual(this.outputs, jvmBinaryPipelineArtifact.outputs) && Intrinsics.areEqual(this.configuration, jvmBinaryPipelineArtifact.configuration);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final List<GenerationState> getOutputs() {
        return this.outputs;
    }

    public int hashCode() {
        return (this.outputs.hashCode() * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "JvmBinaryPipelineArtifact(outputs=" + this.outputs + ", configuration=" + this.configuration + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public JvmBinaryPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, null, newConfiguration, 1, null);
    }
}
