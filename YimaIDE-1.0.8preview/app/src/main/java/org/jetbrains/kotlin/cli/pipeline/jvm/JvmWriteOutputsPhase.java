package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CliCompilerUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmWriteOutputsPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmBackendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmBinaryPipelineArtifact;", "<init>", "()V", "executePhase", "input", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmWriteOutputsPhase extends PipelinePhase<JvmBackendPipelineArtifact, JvmBinaryPipelineArtifact> {
    public static final JvmWriteOutputsPhase INSTANCE = new JvmWriteOutputsPhase();

    private JvmWriteOutputsPhase() {
        super("JvmWriteOutputsPhase", null, null, 6, null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public JvmBinaryPipelineArtifact executePhase(JvmBackendPipelineArtifact input) throws Throwable {
        input.getClass();
        CompilerConfiguration configuration = input.getConfiguration();
        VfsBasedProjectEnvironment environment = input.getEnvironment();
        FqName mainClassFqName = input.getMainClassFqName();
        List<GenerationState> listComponent4 = input.component4();
        CliCompilerUtilsKt.writeOutputsIfNeeded(environment.getProject(), configuration, CommonConfigurationKeysKt.getMessageCollector(configuration), CLIConfigurationKeysKt.getDiagnosticsCollector(configuration).getHasErrors(), listComponent4, mainClassFqName);
        return new JvmBinaryPipelineArtifact(listComponent4, configuration);
    }
}
