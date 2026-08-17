package org.jetbrains.kotlin.cli.pipeline;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 \u000b2\u00020\u0001:\u0002\n\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005H'R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", Argument.Delimiters.none, "<init>", "()V", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "newConfiguration", "CliPipelineInternals", "Companion", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PipelineArtifact {
    public static final String OPT_IN_MESSAGE = "This method is intended to be used only by utility `withNewDiagnosticCollector`";

    @Retention(RetentionPolicy.RUNTIME)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact$CliPipelineInternals;", Argument.Delimiters.none, "message", Argument.Delimiters.none, "()Ljava/lang/String;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public @interface CliPipelineInternals {
        String message();
    }

    public abstract CompilerConfiguration getConfiguration();

    @CliPipelineInternals(message = OPT_IN_MESSAGE)
    public abstract PipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration);
}
