package org.jetbrains.kotlin.cli.pipeline.web;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.wasm.WasmCompilerResult;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u001e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\bH\u0017b\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J-\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/WasmBackendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelineArtifact;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/wasm/WasmCompilerResult;", ModuleXmlParser.OUTPUT_DIR, "Ljava/io/File;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Ljava/util/List;Ljava/io/File;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getResult", "()Ljava/util/List;", "getOutputDir", "()Ljava/io/File;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "newConfiguration", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact$CliPipelineInternals;", "message", PipelineArtifact.OPT_IN_MESSAGE, "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class WasmBackendPipelineArtifact extends WebBackendPipelineArtifact {
    private final CompilerConfiguration configuration;
    private final File outputDir;
    private final List<WasmCompilerResult> result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WasmBackendPipelineArtifact(List<WasmCompilerResult> list, File file, CompilerConfiguration compilerConfiguration) {
        super(null);
        list.getClass();
        file.getClass();
        compilerConfiguration.getClass();
        this.result = list;
        this.outputDir = file;
        this.configuration = compilerConfiguration;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WasmBackendPipelineArtifact copy$default(WasmBackendPipelineArtifact wasmBackendPipelineArtifact, List list, File file, CompilerConfiguration compilerConfiguration, int i, Object obj) {
        if ((i & 1) != 0) {
            list = wasmBackendPipelineArtifact.result;
        }
        if ((i & 2) != 0) {
            file = wasmBackendPipelineArtifact.outputDir;
        }
        if ((i & 4) != 0) {
            compilerConfiguration = wasmBackendPipelineArtifact.configuration;
        }
        return wasmBackendPipelineArtifact.copy(list, file, compilerConfiguration);
    }

    public final List<WasmCompilerResult> component1() {
        return this.result;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final File getOutputDir() {
        return this.outputDir;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final WasmBackendPipelineArtifact copy(List<WasmCompilerResult> result, File outputDir, CompilerConfiguration configuration) {
        result.getClass();
        outputDir.getClass();
        configuration.getClass();
        return new WasmBackendPipelineArtifact(result, outputDir, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WasmBackendPipelineArtifact)) {
            return false;
        }
        WasmBackendPipelineArtifact wasmBackendPipelineArtifact = (WasmBackendPipelineArtifact) other;
        return Intrinsics.areEqual(this.result, wasmBackendPipelineArtifact.result) && Intrinsics.areEqual(this.outputDir, wasmBackendPipelineArtifact.outputDir) && Intrinsics.areEqual(this.configuration, wasmBackendPipelineArtifact.configuration);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final File getOutputDir() {
        return this.outputDir;
    }

    public final List<WasmCompilerResult> getResult() {
        return this.result;
    }

    public int hashCode() {
        return (((this.result.hashCode() * 31) + this.outputDir.hashCode()) * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "WasmBackendPipelineArtifact(result=" + this.result + ", outputDir=" + this.outputDir + ", configuration=" + this.configuration + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public WasmBackendPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, null, null, newConfiguration, 3, null);
    }
}
