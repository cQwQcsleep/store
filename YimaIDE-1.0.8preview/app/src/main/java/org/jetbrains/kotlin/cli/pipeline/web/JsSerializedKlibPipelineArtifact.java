package org.jetbrains.kotlin.cli.pipeline.web;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0005H\u0017b\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/JsSerializedKlibPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "outputKlibPath", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getOutputKlibPath", "()Ljava/lang/String;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "newConfiguration", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact$CliPipelineInternals;", "message", PipelineArtifact.OPT_IN_MESSAGE, "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JsSerializedKlibPipelineArtifact extends PipelineArtifact {
    private final CompilerConfiguration configuration;
    private final String outputKlibPath;

    public JsSerializedKlibPipelineArtifact(String str, CompilerConfiguration compilerConfiguration) {
        str.getClass();
        compilerConfiguration.getClass();
        this.outputKlibPath = str;
        this.configuration = compilerConfiguration;
    }

    public static /* synthetic */ JsSerializedKlibPipelineArtifact copy$default(JsSerializedKlibPipelineArtifact jsSerializedKlibPipelineArtifact, String str, CompilerConfiguration compilerConfiguration, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jsSerializedKlibPipelineArtifact.outputKlibPath;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = jsSerializedKlibPipelineArtifact.configuration;
        }
        return jsSerializedKlibPipelineArtifact.copy(str, compilerConfiguration);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOutputKlibPath() {
        return this.outputKlibPath;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final JsSerializedKlibPipelineArtifact copy(String outputKlibPath, CompilerConfiguration configuration) {
        outputKlibPath.getClass();
        configuration.getClass();
        return new JsSerializedKlibPipelineArtifact(outputKlibPath, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsSerializedKlibPipelineArtifact)) {
            return false;
        }
        JsSerializedKlibPipelineArtifact jsSerializedKlibPipelineArtifact = (JsSerializedKlibPipelineArtifact) other;
        return Intrinsics.areEqual(this.outputKlibPath, jsSerializedKlibPipelineArtifact.outputKlibPath) && Intrinsics.areEqual(this.configuration, jsSerializedKlibPipelineArtifact.configuration);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final String getOutputKlibPath() {
        return this.outputKlibPath;
    }

    public int hashCode() {
        return (this.outputKlibPath.hashCode() * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "JsSerializedKlibPipelineArtifact(outputKlibPath=" + this.outputKlibPath + ", configuration=" + this.configuration + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public JsSerializedKlibPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, null, newConfiguration, 1, null);
    }
}
