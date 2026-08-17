package org.jetbrains.kotlin.cli.pipeline.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifactWithExitCode;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0005H\u0017J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmScriptPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifactWithExitCode;", "exitCode", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/ExitCode;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getExitCode", "()Lorg/jetbrains/kotlin/cli/common/ExitCode;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "newConfiguration", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JvmScriptPipelineArtifact extends PipelineArtifactWithExitCode {
    private final CompilerConfiguration configuration;
    private final ExitCode exitCode;

    public JvmScriptPipelineArtifact(ExitCode exitCode, CompilerConfiguration compilerConfiguration) {
        exitCode.getClass();
        compilerConfiguration.getClass();
        this.exitCode = exitCode;
        this.configuration = compilerConfiguration;
    }

    public static /* synthetic */ JvmScriptPipelineArtifact copy$default(JvmScriptPipelineArtifact jvmScriptPipelineArtifact, ExitCode exitCode, CompilerConfiguration compilerConfiguration, int i, Object obj) {
        if ((i & 1) != 0) {
            exitCode = jvmScriptPipelineArtifact.exitCode;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = jvmScriptPipelineArtifact.configuration;
        }
        return jvmScriptPipelineArtifact.copy(exitCode, compilerConfiguration);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ExitCode getExitCode() {
        return this.exitCode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final JvmScriptPipelineArtifact copy(ExitCode exitCode, CompilerConfiguration configuration) {
        exitCode.getClass();
        configuration.getClass();
        return new JvmScriptPipelineArtifact(exitCode, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JvmScriptPipelineArtifact)) {
            return false;
        }
        JvmScriptPipelineArtifact jvmScriptPipelineArtifact = (JvmScriptPipelineArtifact) other;
        return this.exitCode == jvmScriptPipelineArtifact.exitCode && Intrinsics.areEqual(this.configuration, jvmScriptPipelineArtifact.configuration);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifactWithExitCode
    public ExitCode getExitCode() {
        return this.exitCode;
    }

    public int hashCode() {
        return (this.exitCode.hashCode() * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "JvmScriptPipelineArtifact(exitCode=" + this.exitCode + ", configuration=" + this.configuration + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public JvmScriptPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, null, newConfiguration, 1, null);
    }
}
