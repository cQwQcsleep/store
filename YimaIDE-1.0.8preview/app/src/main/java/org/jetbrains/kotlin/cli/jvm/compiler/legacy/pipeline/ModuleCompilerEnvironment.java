package org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.LegacyK2CliPipeline;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerEnvironment;", Argument.Delimiters.none, "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "diagnosticsReporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;)V", "getProjectEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "getDiagnosticsReporter", "()Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@LegacyK2CliPipeline
public final /* data */ class ModuleCompilerEnvironment {
    private final BaseDiagnosticsCollector diagnosticsReporter;
    private final VfsBasedProjectEnvironment projectEnvironment;

    public ModuleCompilerEnvironment(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        vfsBasedProjectEnvironment.getClass();
        baseDiagnosticsCollector.getClass();
        this.projectEnvironment = vfsBasedProjectEnvironment;
        this.diagnosticsReporter = baseDiagnosticsCollector;
    }

    public static /* synthetic */ ModuleCompilerEnvironment copy$default(ModuleCompilerEnvironment moduleCompilerEnvironment, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, BaseDiagnosticsCollector baseDiagnosticsCollector, int i, Object obj) {
        if ((i & 1) != 0) {
            vfsBasedProjectEnvironment = moduleCompilerEnvironment.projectEnvironment;
        }
        if ((i & 2) != 0) {
            baseDiagnosticsCollector = moduleCompilerEnvironment.diagnosticsReporter;
        }
        return moduleCompilerEnvironment.copy(vfsBasedProjectEnvironment, baseDiagnosticsCollector);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final VfsBasedProjectEnvironment getProjectEnvironment() {
        return this.projectEnvironment;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final BaseDiagnosticsCollector getDiagnosticsReporter() {
        return this.diagnosticsReporter;
    }

    public final ModuleCompilerEnvironment copy(VfsBasedProjectEnvironment projectEnvironment, BaseDiagnosticsCollector diagnosticsReporter) {
        projectEnvironment.getClass();
        diagnosticsReporter.getClass();
        return new ModuleCompilerEnvironment(projectEnvironment, diagnosticsReporter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ModuleCompilerEnvironment)) {
            return false;
        }
        ModuleCompilerEnvironment moduleCompilerEnvironment = (ModuleCompilerEnvironment) other;
        return Intrinsics.areEqual(this.projectEnvironment, moduleCompilerEnvironment.projectEnvironment) && Intrinsics.areEqual(this.diagnosticsReporter, moduleCompilerEnvironment.diagnosticsReporter);
    }

    public final BaseDiagnosticsCollector getDiagnosticsReporter() {
        return this.diagnosticsReporter;
    }

    public final VfsBasedProjectEnvironment getProjectEnvironment() {
        return this.projectEnvironment;
    }

    public int hashCode() {
        return (this.projectEnvironment.hashCode() * 31) + this.diagnosticsReporter.hashCode();
    }

    public String toString() {
        return "ModuleCompilerEnvironment(projectEnvironment=" + this.projectEnvironment + ", diagnosticsReporter=" + this.diagnosticsReporter + ')';
    }
}
