package org.jetbrains.kotlin.cli.pipeline.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.pipeline.Fir2IrPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PipelineArtifact;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0005H\u0017J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\fHÆ\u0003JC\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0083\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFir2IrPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/Fir2IrPipelineArtifact;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "sourceFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "mainClassFqName", "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Ljava/util/List;Lorg/jetbrains/kotlin/name/FqName;)V", "getResult", "()Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "getSourceFiles", "()Ljava/util/List;", "getMainClassFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "withCompilerConfiguration", "newConfiguration", "component1", "component2", "component3", "component4", "component5", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JvmFir2IrPipelineArtifact extends Fir2IrPipelineArtifact {
    private final CompilerConfiguration configuration;
    private final VfsBasedProjectEnvironment environment;
    private final FqName mainClassFqName;
    private final Fir2IrActualizedResult result;
    private final List<KtSourceFile> sourceFiles;

    /* JADX WARN: Multi-variable type inference failed */
    public JvmFir2IrPipelineArtifact(Fir2IrActualizedResult fir2IrActualizedResult, CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, List<? extends KtSourceFile> list, FqName fqName) {
        fir2IrActualizedResult.getClass();
        compilerConfiguration.getClass();
        vfsBasedProjectEnvironment.getClass();
        list.getClass();
        this.result = fir2IrActualizedResult;
        this.configuration = compilerConfiguration;
        this.environment = vfsBasedProjectEnvironment;
        this.sourceFiles = list;
        this.mainClassFqName = fqName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JvmFir2IrPipelineArtifact copy$default(JvmFir2IrPipelineArtifact jvmFir2IrPipelineArtifact, Fir2IrActualizedResult fir2IrActualizedResult, CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, List list, FqName fqName, int i, Object obj) {
        if ((i & 1) != 0) {
            fir2IrActualizedResult = jvmFir2IrPipelineArtifact.result;
        }
        if ((i & 2) != 0) {
            compilerConfiguration = jvmFir2IrPipelineArtifact.configuration;
        }
        if ((i & 4) != 0) {
            vfsBasedProjectEnvironment = jvmFir2IrPipelineArtifact.environment;
        }
        if ((i & 8) != 0) {
            list = jvmFir2IrPipelineArtifact.sourceFiles;
        }
        if ((i & 16) != 0) {
            fqName = jvmFir2IrPipelineArtifact.mainClassFqName;
        }
        FqName fqName2 = fqName;
        VfsBasedProjectEnvironment vfsBasedProjectEnvironment2 = vfsBasedProjectEnvironment;
        return jvmFir2IrPipelineArtifact.copy(fir2IrActualizedResult, compilerConfiguration, vfsBasedProjectEnvironment2, list, fqName2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Fir2IrActualizedResult getResult() {
        return this.result;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final VfsBasedProjectEnvironment getEnvironment() {
        return this.environment;
    }

    public final List<KtSourceFile> component4() {
        return this.sourceFiles;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final FqName getMainClassFqName() {
        return this.mainClassFqName;
    }

    public final JvmFir2IrPipelineArtifact copy(Fir2IrActualizedResult result, CompilerConfiguration configuration, VfsBasedProjectEnvironment environment, List<? extends KtSourceFile> sourceFiles, FqName mainClassFqName) {
        result.getClass();
        configuration.getClass();
        environment.getClass();
        sourceFiles.getClass();
        return new JvmFir2IrPipelineArtifact(result, configuration, environment, sourceFiles, mainClassFqName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JvmFir2IrPipelineArtifact)) {
            return false;
        }
        JvmFir2IrPipelineArtifact jvmFir2IrPipelineArtifact = (JvmFir2IrPipelineArtifact) other;
        return Intrinsics.areEqual(this.result, jvmFir2IrPipelineArtifact.result) && Intrinsics.areEqual(this.configuration, jvmFir2IrPipelineArtifact.configuration) && Intrinsics.areEqual(this.environment, jvmFir2IrPipelineArtifact.environment) && Intrinsics.areEqual(this.sourceFiles, jvmFir2IrPipelineArtifact.sourceFiles) && Intrinsics.areEqual(this.mainClassFqName, jvmFir2IrPipelineArtifact.mainClassFqName);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.Fir2IrPipelineArtifact, org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final VfsBasedProjectEnvironment getEnvironment() {
        return this.environment;
    }

    public final FqName getMainClassFqName() {
        return this.mainClassFqName;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.Fir2IrPipelineArtifact
    public Fir2IrActualizedResult getResult() {
        return this.result;
    }

    public final List<KtSourceFile> getSourceFiles() {
        return this.sourceFiles;
    }

    public int hashCode() {
        int iHashCode = ((((((this.result.hashCode() * 31) + this.configuration.hashCode()) * 31) + this.environment.hashCode()) * 31) + this.sourceFiles.hashCode()) * 31;
        FqName fqName = this.mainClassFqName;
        return iHashCode + (fqName == null ? 0 : fqName.hashCode());
    }

    public String toString() {
        return "JvmFir2IrPipelineArtifact(result=" + this.result + ", configuration=" + this.configuration + ", environment=" + this.environment + ", sourceFiles=" + this.sourceFiles + ", mainClassFqName=" + this.mainClassFqName + ')';
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public JvmFir2IrPipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) {
        newConfiguration.getClass();
        return copy$default(this, null, newConfiguration, null, null, null, 29, null);
    }
}
