package org.jetbrains.kotlin.cli.pipeline;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0005H&¢\u0006\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "<init>", "()V", "frontendOutput", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "getFrontendOutput-QYgrGdg", "()Ljava/util/List;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withNewFrontendOutputImpl", "newFrontendOutput", "withNewFrontendOutputImpl-06ismGs", "(Ljava/util/List;)Lorg/jetbrains/kotlin/cli/pipeline/FrontendPipelineArtifact;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FrontendPipelineArtifact extends PipelineArtifact {
    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public abstract CompilerConfiguration getConfiguration();

    /* JADX INFO: renamed from: getFrontendOutput-QYgrGdg, reason: not valid java name */
    public abstract List<? extends SingleModuleFrontendOutput> mo31getFrontendOutputQYgrGdg();

    /* JADX INFO: renamed from: withNewFrontendOutputImpl-06ismGs, reason: not valid java name */
    public abstract FrontendPipelineArtifact mo32withNewFrontendOutputImpl06ismGs(List<? extends SingleModuleFrontendOutput> newFrontendOutput);
}
