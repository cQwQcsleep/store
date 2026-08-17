package org.jetbrains.kotlin.cli.metadata;

import com.intellij.openapi.Disposable;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2MetadataCompilerArguments;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageUtil;
import org.jetbrains.kotlin.cli.common.messages.OutputMessageUtil;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.pipeline.metadata.MetadataCliPipeline;
import org.jetbrains.kotlin.cli.pipeline.metadata.MetadataConfigurationUpdater;
import org.jetbrains.kotlin.codegen.CompilationException;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.metadata.builtins.BuiltInsBinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.platform.CommonPlatforms;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.utils.KotlinPaths;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\"B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\u0010\u001a\u00020\u0002H\u0016J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0014J\u001a\u0010\u0015\u001a\u00020\u0012*\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0007\u001a\u00020\u0002H\u0014J*\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/KotlinMetadataCompiler;", "Lorg/jetbrains/kotlin/cli/common/CLICompiler;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2MetadataCompilerArguments;", "<init>", "()V", "doExecutePhased", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "basicMessageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "createArguments", "setupPlatformSpecificArgumentsAndServices", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "addPlatformOptions", Argument.Delimiters.none, Argument.Delimiters.none, "doExecute", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "paths", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "executableScriptFileName", "createMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinMetadataCompiler extends CLICompiler<K2MetadataCompilerArguments> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final void main(String[] strArr) {
        INSTANCE.main(strArr);
    }

    public void addPlatformOptions(List<String> list, K2MetadataCompilerArguments k2MetadataCompilerArguments) {
        list.getClass();
        k2MetadataCompilerArguments.getClass();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public K2MetadataCompilerArguments createArguments() {
        return new K2MetadataCompilerArguments();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public BinaryVersion createMetadataVersion(int[] versionArray) {
        versionArray.getClass();
        return new BuiltInsBinaryVersion(Arrays.copyOf(versionArray, versionArray.length));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public ExitCode doExecute(K2MetadataCompilerArguments arguments, CompilerConfiguration configuration, Disposable rootDisposable, KotlinPaths paths) throws NoWhenBranchMatchedException {
        AbstractMetadataSerializer k1LegacyMetadataSerializer;
        arguments.getClass();
        configuration.getClass();
        rootDisposable.getClass();
        MessageCollector messageCollector = (MessageCollector) configuration.getNotNull(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY);
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        ExitCode exitCodeLoadPlugins = loadPlugins(paths, arguments, configuration, rootDisposable);
        ExitCode exitCode = ExitCode.OK;
        if (exitCodeLoadPlugins != exitCode) {
            return exitCodeLoadPlugins;
        }
        MetadataConfigurationUpdater.INSTANCE.fillConfiguration(configuration, arguments, rootDisposable);
        String moduleName = CommonConfigurationKeysKt.getModuleName(configuration);
        KotlinCoreEnvironment kotlinCoreEnvironmentCreateForProduction = KotlinCoreEnvironment.INSTANCE.createForProduction(rootDisposable, configuration, EnvironmentConfigFiles.METADATA_CONFIG_FILES);
        List<KtFile> sourceFiles = kotlinCoreEnvironmentCreateForProduction.getSourceFiles();
        if (perfManager != null) {
            perfManager.setTargetDescription(moduleName);
            perfManager.setOutputKind(arguments.getMetadataKlib() ? "KLib" : "metadata");
            perfManager.addSourcesStats(sourceFiles.size(), kotlinCoreEnvironmentCreateForProduction.countLinesOfCode(sourceFiles));
        }
        if (kotlinCoreEnvironmentCreateForProduction.getSourceFiles().isEmpty()) {
            if (arguments.getVersion()) {
                return exitCode;
            }
            MessageCollector.report$default(messageCollector, CompilerMessageSeverity.ERROR, "No source files", null, 4, null);
            return ExitCode.COMPILATION_ERROR;
        }
        UtilsKt.checkKotlinPackageUsageForPsi(kotlinCoreEnvironmentCreateForProduction.getConfiguration(), kotlinCoreEnvironmentCreateForProduction.getSourceFiles());
        try {
            boolean metadataKlib = arguments.getMetadataKlib();
            if (metadataKlib) {
                k1LegacyMetadataSerializer = new K1MetadataKlibSerializer(configuration, kotlinCoreEnvironmentCreateForProduction);
            } else {
                if (metadataKlib) {
                    throw new NoWhenBranchMatchedException();
                }
                k1LegacyMetadataSerializer = new K1LegacyMetadataSerializer(configuration, kotlinCoreEnvironmentCreateForProduction, true, null, 8, null);
            }
            k1LegacyMetadataSerializer.analyzeAndSerialize();
            return exitCode;
        } catch (CompilationException e) {
            CompilerMessageSeverity compilerMessageSeverity = CompilerMessageSeverity.EXCEPTION;
            String strRenderException = OutputMessageUtil.renderException(e);
            strRenderException.getClass();
            messageCollector.report(compilerMessageSeverity, strRenderException, MessageUtil.psiElementToMessageLocation(e.getElement()));
            return ExitCode.INTERNAL_ERROR;
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public ExitCode doExecutePhased(K2MetadataCompilerArguments arguments, Services services, MessageCollector basicMessageCollector) {
        arguments.getClass();
        services.getClass();
        basicMessageCollector.getClass();
        return new MetadataCliPipeline(getDefaultPerformanceManager()).execute(arguments, services, basicMessageCollector);
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public String executableScriptFileName() {
        return "kotlinc";
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public TargetPlatform getPlatform() {
        return CommonPlatforms.INSTANCE.getDefaultCommonPlatform();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public void setupPlatformSpecificArgumentsAndServices(CompilerConfiguration configuration, K2MetadataCompilerArguments arguments, Services services) {
        configuration.getClass();
        arguments.getClass();
        services.getClass();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/KotlinMetadataCompiler$Companion;", Argument.Delimiters.none, "<init>", "()V", "main", Argument.Delimiters.none, "args", Argument.Delimiters.none, Argument.Delimiters.none, "([Ljava/lang/String;)V", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void main(String[] args) {
            args.getClass();
            CLICompiler.INSTANCE.doMain(new KotlinMetadataCompiler(), args);
        }

        private Companion() {
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public /* bridge */ /* synthetic */ void addPlatformOptions(List list, CommonCompilerArguments commonCompilerArguments) {
        addPlatformOptions((List<String>) list, (K2MetadataCompilerArguments) commonCompilerArguments);
    }
}
