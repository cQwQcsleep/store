package org.jetbrains.kotlin.cli.pipeline.web;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.pipeline.Fir2IrPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0007H\u0017b\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J8\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001¢\u0006\u0004\b \u0010!J\u0014\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0083\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/JsFir2IrPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/Fir2IrPipelineArtifact;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "frontendOutput", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "hasErrors", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getResult", "()Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "getFrontendOutput-QYgrGdg", "()Ljava/util/List;", "Ljava/util/List;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getHasErrors", "()Z", "withCompilerConfiguration", "newConfiguration", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact$CliPipelineInternals;", "message", PipelineArtifact.OPT_IN_MESSAGE, "component1", "component2", "component2-QYgrGdg", "component3", "component4", "copy", "copy-jlVyY7s", "(Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Z)Lorg/jetbrains/kotlin/cli/pipeline/web/JsFir2IrPipelineArtifact;", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JsFir2IrPipelineArtifact extends Fir2IrPipelineArtifact {
    private final CompilerConfiguration configuration;
    private final List<? extends SingleModuleFrontendOutput> frontendOutput;
    private final boolean hasErrors;
    private final Fir2IrActualizedResult result;

    private JsFir2IrPipelineArtifact(Fir2IrActualizedResult fir2IrActualizedResult, List<? extends SingleModuleFrontendOutput> list, CompilerConfiguration compilerConfiguration, boolean z) {
        fir2IrActualizedResult.getClass();
        list.getClass();
        compilerConfiguration.getClass();
        this.result = fir2IrActualizedResult;
        this.frontendOutput = list;
        this.configuration = compilerConfiguration;
        this.hasErrors = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-jlVyY7s$default, reason: not valid java name */
    public static /* synthetic */ JsFir2IrPipelineArtifact m40copyjlVyY7s$default(JsFir2IrPipelineArtifact jsFir2IrPipelineArtifact, Fir2IrActualizedResult fir2IrActualizedResult, List list, CompilerConfiguration compilerConfiguration, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            fir2IrActualizedResult = jsFir2IrPipelineArtifact.result;
        }
        if ((i & 2) != 0) {
            list = jsFir2IrPipelineArtifact.frontendOutput;
        }
        if ((i & 4) != 0) {
            compilerConfiguration = jsFir2IrPipelineArtifact.configuration;
        }
        if ((i & 8) != 0) {
            z = jsFir2IrPipelineArtifact.hasErrors;
        }
        return jsFir2IrPipelineArtifact.m42copyjlVyY7s(fir2IrActualizedResult, list, compilerConfiguration, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Fir2IrActualizedResult getResult() {
        return this.result;
    }

    /* JADX INFO: renamed from: component2-QYgrGdg, reason: not valid java name */
    public final List<? extends SingleModuleFrontendOutput> m41component2QYgrGdg() {
        return this.frontendOutput;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getHasErrors() {
        return this.hasErrors;
    }

    /* JADX INFO: renamed from: copy-jlVyY7s, reason: not valid java name */
    public final JsFir2IrPipelineArtifact m42copyjlVyY7s(Fir2IrActualizedResult result, List<? extends SingleModuleFrontendOutput> frontendOutput, CompilerConfiguration configuration, boolean hasErrors) {
        result.getClass();
        frontendOutput.getClass();
        configuration.getClass();
        return new JsFir2IrPipelineArtifact(result, frontendOutput, configuration, hasErrors, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsFir2IrPipelineArtifact)) {
            return false;
        }
        JsFir2IrPipelineArtifact jsFir2IrPipelineArtifact = (JsFir2IrPipelineArtifact) other;
        return Intrinsics.areEqual(this.result, jsFir2IrPipelineArtifact.result) && AllModulesFrontendOutput.m575equalsimpl0(this.frontendOutput, jsFir2IrPipelineArtifact.frontendOutput) && Intrinsics.areEqual(this.configuration, jsFir2IrPipelineArtifact.configuration) && this.hasErrors == jsFir2IrPipelineArtifact.hasErrors;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.Fir2IrPipelineArtifact, org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: getFrontendOutput-QYgrGdg, reason: not valid java name */
    public final List<? extends SingleModuleFrontendOutput> m43getFrontendOutputQYgrGdg() {
        return this.frontendOutput;
    }

    public final boolean getHasErrors() {
        return this.hasErrors;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.Fir2IrPipelineArtifact
    public Fir2IrActualizedResult getResult() {
        return this.result;
    }

    public int hashCode() {
        return (((((this.result.hashCode() * 31) + AllModulesFrontendOutput.m576hashCodeimpl(this.frontendOutput)) * 31) + this.configuration.hashCode()) * 31) + Boolean.hashCode(this.hasErrors);
    }

    public String toString() {
        return "JsFir2IrPipelineArtifact(result=" + this.result + ", frontendOutput=" + ((Object) AllModulesFrontendOutput.m577toStringimpl(this.frontendOutput)) + ", configuration=" + this.configuration + ", hasErrors=" + this.hasErrors + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public JsFir2IrPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return m40copyjlVyY7s$default(this, null, null, newConfiguration, false, 11, null);
    }

    public /* synthetic */ JsFir2IrPipelineArtifact(Fir2IrActualizedResult fir2IrActualizedResult, List list, CompilerConfiguration compilerConfiguration, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(fir2IrActualizedResult, list, compilerConfiguration, z);
    }
}
