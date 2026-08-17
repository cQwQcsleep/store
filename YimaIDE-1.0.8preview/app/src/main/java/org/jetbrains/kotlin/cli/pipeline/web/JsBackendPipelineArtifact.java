package org.jetbrains.kotlin.cli.pipeline.web;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.transformers.irToJs.CompilationOutputs;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0007H\u0017b\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/JsBackendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebBackendPipelineArtifact;", "outputs", "Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/CompilationOutputs;", ModuleXmlParser.OUTPUT_DIR, "Ljava/io/File;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/CompilationOutputs;Ljava/io/File;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getOutputs", "()Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/CompilationOutputs;", "getOutputDir", "()Ljava/io/File;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "newConfiguration", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact$CliPipelineInternals;", "message", PipelineArtifact.OPT_IN_MESSAGE, "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JsBackendPipelineArtifact extends WebBackendPipelineArtifact {
    private final CompilerConfiguration configuration;
    private final File outputDir;
    private final CompilationOutputs outputs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsBackendPipelineArtifact(CompilationOutputs compilationOutputs, File file, CompilerConfiguration compilerConfiguration) {
        super(null);
        compilationOutputs.getClass();
        file.getClass();
        compilerConfiguration.getClass();
        this.outputs = compilationOutputs;
        this.outputDir = file;
        this.configuration = compilerConfiguration;
    }

    public static /* synthetic */ JsBackendPipelineArtifact copy$default(JsBackendPipelineArtifact jsBackendPipelineArtifact, CompilationOutputs compilationOutputs, File file, CompilerConfiguration compilerConfiguration, int i, Object obj) {
        if ((i & 1) != 0) {
            compilationOutputs = jsBackendPipelineArtifact.outputs;
        }
        if ((i & 2) != 0) {
            file = jsBackendPipelineArtifact.outputDir;
        }
        if ((i & 4) != 0) {
            compilerConfiguration = jsBackendPipelineArtifact.configuration;
        }
        return jsBackendPipelineArtifact.copy(compilationOutputs, file, compilerConfiguration);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CompilationOutputs getOutputs() {
        return this.outputs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final File getOutputDir() {
        return this.outputDir;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final JsBackendPipelineArtifact copy(CompilationOutputs outputs, File outputDir, CompilerConfiguration configuration) {
        outputs.getClass();
        outputDir.getClass();
        configuration.getClass();
        return new JsBackendPipelineArtifact(outputs, outputDir, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsBackendPipelineArtifact)) {
            return false;
        }
        JsBackendPipelineArtifact jsBackendPipelineArtifact = (JsBackendPipelineArtifact) other;
        return Intrinsics.areEqual(this.outputs, jsBackendPipelineArtifact.outputs) && Intrinsics.areEqual(this.outputDir, jsBackendPipelineArtifact.outputDir) && Intrinsics.areEqual(this.configuration, jsBackendPipelineArtifact.configuration);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final File getOutputDir() {
        return this.outputDir;
    }

    public final CompilationOutputs getOutputs() {
        return this.outputs;
    }

    public int hashCode() {
        return (((this.outputs.hashCode() * 31) + this.outputDir.hashCode()) * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "JsBackendPipelineArtifact(outputs=" + this.outputs + ", outputDir=" + this.outputDir + ", configuration=" + this.configuration + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public JsBackendPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, null, null, newConfiguration, 3, null);
    }
}
