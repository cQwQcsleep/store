package org.jetbrains.kotlin.cli.pipeline;

import com.intellij.openapi.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0003H\u0017J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lcom/intellij/openapi/Disposable;)V", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getRootDisposable", "()Lcom/intellij/openapi/Disposable;", "withCompilerConfiguration", "newConfiguration", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ConfigurationPipelineArtifact extends PipelineArtifact {
    private final CompilerConfiguration configuration;
    private final Disposable rootDisposable;

    public ConfigurationPipelineArtifact(CompilerConfiguration compilerConfiguration, Disposable disposable) {
        compilerConfiguration.getClass();
        disposable.getClass();
        this.configuration = compilerConfiguration;
        this.rootDisposable = disposable;
    }

    public static /* synthetic */ ConfigurationPipelineArtifact copy$default(ConfigurationPipelineArtifact configurationPipelineArtifact, CompilerConfiguration compilerConfiguration, Disposable disposable, int i, Object obj) {
        if ((i & 1) != 0) {
            compilerConfiguration = configurationPipelineArtifact.configuration;
        }
        if ((i & 2) != 0) {
            disposable = configurationPipelineArtifact.rootDisposable;
        }
        return configurationPipelineArtifact.copy(compilerConfiguration, disposable);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Disposable getRootDisposable() {
        return this.rootDisposable;
    }

    public final ConfigurationPipelineArtifact copy(CompilerConfiguration configuration, Disposable rootDisposable) {
        configuration.getClass();
        rootDisposable.getClass();
        return new ConfigurationPipelineArtifact(configuration, rootDisposable);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConfigurationPipelineArtifact)) {
            return false;
        }
        ConfigurationPipelineArtifact configurationPipelineArtifact = (ConfigurationPipelineArtifact) other;
        return Intrinsics.areEqual(this.configuration, configurationPipelineArtifact.configuration) && Intrinsics.areEqual(this.rootDisposable, configurationPipelineArtifact.rootDisposable);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final Disposable getRootDisposable() {
        return this.rootDisposable;
    }

    public int hashCode() {
        return (this.configuration.hashCode() * 31) + this.rootDisposable.hashCode();
    }

    public String toString() {
        return "ConfigurationPipelineArtifact(configuration=" + this.configuration + ", rootDisposable=" + this.rootDisposable + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public ConfigurationPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, newConfiguration, null, 2, null);
    }
}
