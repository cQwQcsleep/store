package org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.GroupedKtSources;
import org.jetbrains.kotlin.cli.common.LegacyK2CliPipeline;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.modules.TargetId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerInput;", Argument.Delimiters.none, "targetId", "Lorg/jetbrains/kotlin/modules/TargetId;", "groupedSources", "Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/modules/TargetId;Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getTargetId", "()Lorg/jetbrains/kotlin/modules/TargetId;", "getGroupedSources", "()Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@LegacyK2CliPipeline
public final /* data */ class ModuleCompilerInput {
    private final CompilerConfiguration configuration;
    private final GroupedKtSources groupedSources;
    private final TargetId targetId;

    public ModuleCompilerInput(TargetId targetId, GroupedKtSources groupedKtSources, CompilerConfiguration compilerConfiguration) {
        targetId.getClass();
        groupedKtSources.getClass();
        compilerConfiguration.getClass();
        this.targetId = targetId;
        this.groupedSources = groupedKtSources;
        this.configuration = compilerConfiguration;
    }

    public static /* synthetic */ ModuleCompilerInput copy$default(ModuleCompilerInput moduleCompilerInput, TargetId targetId, GroupedKtSources groupedKtSources, CompilerConfiguration compilerConfiguration, int i, Object obj) {
        if ((i & 1) != 0) {
            targetId = moduleCompilerInput.targetId;
        }
        if ((i & 2) != 0) {
            groupedKtSources = moduleCompilerInput.groupedSources;
        }
        if ((i & 4) != 0) {
            compilerConfiguration = moduleCompilerInput.configuration;
        }
        return moduleCompilerInput.copy(targetId, groupedKtSources, compilerConfiguration);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TargetId getTargetId() {
        return this.targetId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final GroupedKtSources getGroupedSources() {
        return this.groupedSources;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final ModuleCompilerInput copy(TargetId targetId, GroupedKtSources groupedSources, CompilerConfiguration configuration) {
        targetId.getClass();
        groupedSources.getClass();
        configuration.getClass();
        return new ModuleCompilerInput(targetId, groupedSources, configuration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ModuleCompilerInput)) {
            return false;
        }
        ModuleCompilerInput moduleCompilerInput = (ModuleCompilerInput) other;
        return Intrinsics.areEqual(this.targetId, moduleCompilerInput.targetId) && Intrinsics.areEqual(this.groupedSources, moduleCompilerInput.groupedSources) && Intrinsics.areEqual(this.configuration, moduleCompilerInput.configuration);
    }

    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final GroupedKtSources getGroupedSources() {
        return this.groupedSources;
    }

    public final TargetId getTargetId() {
        return this.targetId;
    }

    public int hashCode() {
        return (((this.targetId.hashCode() * 31) + this.groupedSources.hashCode()) * 31) + this.configuration.hashCode();
    }

    public String toString() {
        return "ModuleCompilerInput(targetId=" + this.targetId + ", groupedSources=" + this.groupedSources + ", configuration=" + this.configuration + ')';
    }
}
