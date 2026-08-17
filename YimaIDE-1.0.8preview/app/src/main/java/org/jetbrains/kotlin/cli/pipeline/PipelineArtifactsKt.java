package org.jetbrains.kotlin.cli.pipeline;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005\u001a#\u0010\u0006\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0007*\u0002H\u00012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"withNewDiagnosticCollector", "A", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "newDiagnosticsCollector", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "(Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;)Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "withNewFrontendOutput", "Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "newFrontendOutput", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "withNewFrontendOutput-YuMBOKc", "(Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;Ljava/util/List;)Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PipelineArtifactsKt {
    public static final <A extends PipelineArtifact> A withNewDiagnosticCollector(A a, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        a.getClass();
        baseDiagnosticsCollector.getClass();
        CompilerConfiguration compilerConfigurationCopy = a.getConfiguration().copy();
        CLIConfigurationKeysKt.setDiagnosticsCollector(compilerConfigurationCopy, baseDiagnosticsCollector);
        A a2 = (A) a.withCompilerConfiguration(compilerConfigurationCopy);
        a2.getClass();
        return a2;
    }

    /* JADX INFO: renamed from: withNewFrontendOutput-YuMBOKc, reason: not valid java name */
    public static final <A extends FrontendPipelineArtifact> A m33withNewFrontendOutputYuMBOKc(A a, List<? extends SingleModuleFrontendOutput> list) {
        a.getClass();
        list.getClass();
        A a2 = (A) a.mo32withNewFrontendOutputImpl06ismGs(list);
        a2.getClass();
        return a2;
    }
}
