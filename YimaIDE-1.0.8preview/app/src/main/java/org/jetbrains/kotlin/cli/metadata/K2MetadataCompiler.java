package org.jetbrains.kotlin.cli.metadata;

import com.intellij.openapi.Disposable;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2MetadataCompilerArguments;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.utils.KotlinPaths;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "Use KotlinMetadataCompiler instead")
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J*\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u001a\u0010 \u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010\u0017\u001a\u00020\u0002H\u0014J\b\u0010#\u001a\u00020\u0002H\u0016J\b\u0010$\u001a\u00020\"H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/K2MetadataCompiler;", "Lorg/jetbrains/kotlin/cli/common/CLICompiler;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2MetadataCompilerArguments;", "<init>", "()V", "delegate", "Lorg/jetbrains/kotlin/cli/metadata/KotlinMetadataCompiler;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "defaultPerformanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "getDefaultPerformanceManager", "()Lorg/jetbrains/kotlin/util/PerformanceManager;", "createMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", Argument.Delimiters.none, "setupPlatformSpecificArgumentsAndServices", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "doExecute", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "paths", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "addPlatformOptions", Argument.Delimiters.none, Argument.Delimiters.none, "createArguments", "executableScriptFileName", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2MetadataCompiler extends CLICompiler<K2MetadataCompilerArguments> {
    private final KotlinMetadataCompiler delegate = new KotlinMetadataCompiler();

    public void addPlatformOptions(List<String> list, K2MetadataCompilerArguments k2MetadataCompilerArguments) {
        list.getClass();
        k2MetadataCompilerArguments.getClass();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public K2MetadataCompilerArguments createArguments() {
        return this.delegate.createArguments();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public BinaryVersion createMetadataVersion(int[] versionArray) {
        versionArray.getClass();
        return this.delegate.createMetadataVersion(versionArray);
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public ExitCode doExecute(K2MetadataCompilerArguments arguments, CompilerConfiguration configuration, Disposable rootDisposable, KotlinPaths paths) {
        arguments.getClass();
        configuration.getClass();
        rootDisposable.getClass();
        return this.delegate.doExecute(arguments, configuration, rootDisposable, paths);
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public String executableScriptFileName() {
        return this.delegate.executableScriptFileName();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public PerformanceManager getDefaultPerformanceManager() {
        return this.delegate.getDefaultPerformanceManager();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public TargetPlatform getPlatform() {
        return this.delegate.getPlatform();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public void setupPlatformSpecificArgumentsAndServices(CompilerConfiguration configuration, K2MetadataCompilerArguments arguments, Services services) {
        configuration.getClass();
        arguments.getClass();
        services.getClass();
        this.delegate.getDefaultPerformanceManager();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public /* bridge */ /* synthetic */ void addPlatformOptions(List list, CommonCompilerArguments commonCompilerArguments) {
        addPlatformOptions((List<String>) list, (K2MetadataCompilerArguments) commonCompilerArguments);
    }
}
