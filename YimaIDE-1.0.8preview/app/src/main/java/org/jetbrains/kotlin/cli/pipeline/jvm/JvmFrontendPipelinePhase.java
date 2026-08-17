package org.jetbrains.kotlin.cli.pipeline.jvm;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtPsiSourceFile;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.FirSessionConstructionUtilsKt;
import org.jetbrains.kotlin.cli.common.FirSessionProducer;
import org.jetbrains.kotlin.cli.common.GroupedKtSources;
import org.jetbrains.kotlin.cli.common.GroupedKtSourcesKt;
import org.jetbrains.kotlin.cli.common.SessionConstructionUtils;
import org.jetbrains.kotlin.cli.common.SessionWithSources;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport;
import org.jetbrains.kotlin.cli.common.modules.ModuleChunk;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler;
import org.jetbrains.kotlin.cli.jvm.K2JVMCompilerKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CliCompilerUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.IncrementalCompilationContextUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironmentKt;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.JvmCompilerPipelineKt;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmModulePathRoot;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.PipelineStepException;
import org.jetbrains.kotlin.cli.pipeline.SuccessfulPipelineExecutionException;
import org.jetbrains.kotlin.cli.pipeline.jvm.JvmFrontendPipelinePhase;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar;
import org.jetbrains.kotlin.compiler.plugin.ExtensionPointUtilsKt;
import org.jetbrains.kotlin.compiler.plugin.FirExtensionRegistrarConfigurationUtilKt;
import org.jetbrains.kotlin.compilerRunner.ArgumentUtils;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.HmppCliModule;
import org.jetbrains.kotlin.config.HmppCliModuleStructure;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.DependencyListForCliModule;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.extensions.FirAnalysisHandlerExtension;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.ConvertToIrKt;
import org.jetbrains.kotlin.fir.pipeline.FirUtilsKt;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.session.AbstractFirMetadataSessionFactory;
import org.jetbrains.kotlin.fir.session.FirJsSessionFactory;
import org.jetbrains.kotlin.fir.session.FirJvmIncrementalCompilationSymbolProviders;
import org.jetbrains.kotlin.fir.session.FirJvmIncrementalCompilationSymbolProvidersKt;
import org.jetbrains.kotlin.fir.session.FirJvmSessionFactory;
import org.jetbrains.kotlin.fir.session.IncrementalCompilationContext;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.modules.JavaRootPath;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.multiplatform.IsCommonSourceKt;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.fileUtils.FileUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001>B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\"\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bH\u0002JG\u0010\u0018\u001a\u00020\u0019\"\b\b\u0000\u0010\u001a*\u00020\u001b*\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010\u00132\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u001d2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u00020\u00160\u001f¢\u0006\u0002\b H\u0002J\u001e\u0010!\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002J¬\u0001\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0#0\u0013\"\u0004\b\u0000\u0010$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H$0\u00132\u0006\u0010&\u001a\u00020'2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\u00160\u001f2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\u00160\u001f2\u0018\u00100\u001a\u0014\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0016012\u001a\u00102\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H$0\u0013\u0012\u0006\u0012\u0004\u0018\u0001030\u001fJ,\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u00020\u000f2\f\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00108\u001a\u000209J\u001d\u0010:\u001a\u0004\u0018\u00010\u00162\u0006\u0010;\u001a\u00020<2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010=¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFrontendPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFrontendPipelineArtifact;", "<init>", "()V", "executePhase", "input", "createEnvironmentAndSources", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFrontendPipelinePhase$EnvironmentAndSources;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "targetDescription", Argument.Delimiters.none, "groupKtFiles", "Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;", "ktFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "checkNotSupportedPlugins", Argument.Delimiters.none, "compilerConfiguration", "collectIncompatiblePluginNamesTo", Argument.Delimiters.none, "T", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "supportsK2", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "checkIfScriptsInCommonSources", "prepareJvmSessions", "Lorg/jetbrains/kotlin/cli/common/SessionWithSources;", "F", "files", "rootModuleName", "Lorg/jetbrains/kotlin/name/Name;", "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "librariesScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "libraryList", "Lorg/jetbrains/kotlin/fir/DependencyListForCliModule;", "isCommonSource", "isScript", "fileBelongsToModule", "Lkotlin/Function2;", "createProviderAndScopeForIncrementalCompilation", "Lorg/jetbrains/kotlin/fir/session/IncrementalCompilationContext;", "dumpModel", "dir", "chunk", "Lorg/jetbrains/kotlin/modules/Module;", "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "runAnalysisHandlerExtensions", "project", "Lcom/intellij/openapi/project/Project;", "(Lcom/intellij/openapi/project/Project;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/lang/Boolean;", "EnvironmentAndSources", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmFrontendPipelinePhase extends PipelinePhase<ConfigurationPipelineArtifact, JvmFrontendPipelineArtifact> {
    public static final JvmFrontendPipelinePhase INSTANCE = new JvmFrontendPipelinePhase();

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/JvmFrontendPipelinePhase$EnvironmentAndSources;", Argument.Delimiters.none, "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", ModuleXmlParser.SOURCES, "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Lkotlin/jvm/functions/Function0;)V", "getEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "getSources", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class EnvironmentAndSources {
        private final VfsBasedProjectEnvironment environment;
        private final Function0<GroupedKtSources> sources;

        public EnvironmentAndSources(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, Function0<GroupedKtSources> function0) {
            vfsBasedProjectEnvironment.getClass();
            function0.getClass();
            this.environment = vfsBasedProjectEnvironment;
            this.sources = function0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ EnvironmentAndSources copy$default(EnvironmentAndSources environmentAndSources, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, Function0 function0, int i, Object obj) {
            if ((i & 1) != 0) {
                vfsBasedProjectEnvironment = environmentAndSources.environment;
            }
            if ((i & 2) != 0) {
                function0 = environmentAndSources.sources;
            }
            return environmentAndSources.copy(vfsBasedProjectEnvironment, function0);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final VfsBasedProjectEnvironment getEnvironment() {
            return this.environment;
        }

        public final Function0<GroupedKtSources> component2() {
            return this.sources;
        }

        public final EnvironmentAndSources copy(VfsBasedProjectEnvironment environment, Function0<GroupedKtSources> sources) {
            environment.getClass();
            sources.getClass();
            return new EnvironmentAndSources(environment, sources);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EnvironmentAndSources)) {
                return false;
            }
            EnvironmentAndSources environmentAndSources = (EnvironmentAndSources) other;
            return Intrinsics.areEqual(this.environment, environmentAndSources.environment) && Intrinsics.areEqual(this.sources, environmentAndSources.sources);
        }

        public final VfsBasedProjectEnvironment getEnvironment() {
            return this.environment;
        }

        public final Function0<GroupedKtSources> getSources() {
            return this.sources;
        }

        public int hashCode() {
            return (this.environment.hashCode() * 31) + this.sources.hashCode();
        }

        public String toString() {
            return "EnvironmentAndSources(environment=" + this.environment + ", sources=" + this.sources + ')';
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.pipeline.jvm.JvmFrontendPipelinePhase$checkIfScriptsInCommonSources$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<KtFile, String> {
        final /* synthetic */ File $cwd;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(File file) {
            super(1, Intrinsics.Kotlin.class, "renderFile", "checkIfScriptsInCommonSources$renderFile(Ljava/io/File;Lorg/jetbrains/kotlin/psi/KtFile;)Ljava/lang/String;", 0);
            this.$cwd = file;
        }

        public final String invoke(KtFile ktFile) {
            ktFile.getClass();
            return JvmFrontendPipelinePhase.checkIfScriptsInCommonSources$renderFile(this.$cwd, ktFile);
        }
    }

    private JvmFrontendPipelinePhase() {
        super("JvmFrontendPipelinePhase", null, SetsKt.setOf(new Function3[]{PerformanceNotifications.AnalysisFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}), 2, null);
    }

    public static CharSequence b(Module module) {
        return module.getName();
    }

    private final boolean checkIfScriptsInCommonSources(CompilerConfiguration configuration, List<? extends KtFile> ktFiles) {
        List<HmppCliModule> modules;
        HmppCliModuleStructure hmppCliModuleStructure = (HmppCliModuleStructure) configuration.get(CommonConfigurationKeys.HMPP_MODULE_STRUCTURE);
        HmppCliModule hmppCliModule = (hmppCliModuleStructure == null || (modules = hmppCliModuleStructure.getModules()) == null) ? null : (HmppCliModule) CollectionsKt.lastOrNull(modules);
        ArrayList arrayList = new ArrayList();
        for (Object obj : ktFiles) {
            KtFile ktFile = (KtFile) obj;
            if (ktFile.isScript()) {
                if (!Intrinsics.areEqual(IsCommonSourceKt.isCommonSource(ktFile), Boolean.TRUE)) {
                    if (!Intrinsics.areEqual(IsCommonSourceKt.getHmppModuleName(ktFile), hmppCliModule != null ? hmppCliModule.getName() : null)) {
                    }
                }
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        File absoluteFile = new File(".").getAbsoluteFile();
        CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Script files in common source roots are not supported. Misplaced files:\n    " + CollectionsKt.joinToString$default(arrayList, "\n    ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new AnonymousClass1(absoluteFile), 30, (Object) null), null, 4, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String checkIfScriptsInCommonSources$renderFile(File file, KtFile ktFile) {
        File file2 = new File(ktFile.getVirtualFilePath());
        file.getClass();
        return FileUtilsKt.descendantRelativeTo(file2, file).getPath();
    }

    private final boolean checkNotSupportedPlugins(CompilerConfiguration compilerConfiguration) {
        ArrayList arrayList = new ArrayList();
        JvmFrontendPipelinePhase jvmFrontendPipelinePhase = INSTANCE;
        jvmFrontendPipelinePhase.collectIncompatiblePluginNamesTo((List) compilerConfiguration.get(ComponentRegistrar.INSTANCE.getPLUGIN_COMPONENT_REGISTRARS()), arrayList, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.jvm.JvmFrontendPipelinePhase$checkNotSupportedPlugins$notSupportedPlugins$1$1
            public Object get(Object obj) {
                return Boolean.valueOf(((ComponentRegistrar) obj).getSupportsK2());
            }
        });
        jvmFrontendPipelinePhase.collectIncompatiblePluginNamesTo((List) compilerConfiguration.get(CompilerPluginRegistrar.INSTANCE.getCOMPILER_PLUGIN_REGISTRARS()), arrayList, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.cli.pipeline.jvm.JvmFrontendPipelinePhase$checkNotSupportedPlugins$notSupportedPlugins$1$2
            public Object get(Object obj) {
                return Boolean.valueOf(((CompilerPluginRegistrar) obj).getSupportsK2());
            }
        });
        if (arrayList.isEmpty()) {
            return true;
        }
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_PLUGIN_INITIALIZATION_ERROR(), StringsKt.trimMargin$default("\n                    |There are some plugins incompatible with language version 2.0:\n                    |" + CollectionsKt.joinToString$default(arrayList, "\n|", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: xw7
            public final Object invoke(Object obj) {
                return JvmFrontendPipelinePhase.j((String) obj);
            }
        }, 30, (Object) null) + "\n                    |Please use language version 1.9 or below\n                ", (String) null, 1, (Object) null), null, 4, null);
        return false;
    }

    private final <T> void collectIncompatiblePluginNamesTo(List<? extends T> list, List<String> list2, Function1<? super T, Boolean> function1) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (T t : list) {
                if (!((Boolean) function1.invoke(t)).booleanValue() && !Intrinsics.areEqual(t.getClass().getCanonicalName(), CLICompiler.SCRIPT_PLUGIN_REGISTRAR_NAME)) {
                    arrayList.add(t);
                }
            }
            List<String> list3 = list2;
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                list3.add(Reflection.getOrCreateKotlinClass(it.next().getClass()).getQualifiedName());
            }
        }
    }

    private final EnvironmentAndSources createEnvironmentAndSources(final CompilerConfiguration configuration, Disposable rootDisposable, String targetDescription) {
        EnvironmentAndSources environmentAndSources;
        final BaseDiagnosticsCollector diagnosticsCollector = CLIConfigurationKeysKt.getDiagnosticsCollector(configuration);
        boolean useLightTree = CommonConfigurationKeysKt.getUseLightTree(configuration);
        if (useLightTree) {
            final VfsBasedProjectEnvironment vfsBasedProjectEnvironmentCreateProjectEnvironment = JvmCompilerPipelineKt.createProjectEnvironment(configuration, rootDisposable, EnvironmentConfigFiles.JVM_CONFIG_FILES);
            environmentAndSources = new EnvironmentAndSources(vfsBasedProjectEnvironmentCreateProjectEnvironment, new Function0() { // from class: hx7
                public final Object invoke() {
                    return JvmFrontendPipelinePhase.i(configuration, vfsBasedProjectEnvironmentCreateProjectEnvironment);
                }
            });
        } else {
            if (useLightTree) {
                bu8.a();
                return null;
            }
            final KotlinCoreEnvironment kotlinCoreEnvironmentCreateCoreEnvironment = K2JVMCompiler.INSTANCE.createCoreEnvironment(rootDisposable, configuration, targetDescription);
            if (kotlinCoreEnvironmentCreateCoreEnvironment == null) {
                return null;
            }
            environmentAndSources = new EnvironmentAndSources(VfsBasedProjectEnvironmentKt.toVfsBasedProjectEnvironment(kotlinCoreEnvironmentCreateCoreEnvironment), new Function0() { // from class: ix7
                public final Object invoke() {
                    return JvmFrontendPipelinePhase.l(kotlinCoreEnvironmentCreateCoreEnvironment, diagnosticsCollector);
                }
            });
        }
        if (CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE.checkHasErrors(configuration)) {
            return null;
        }
        return environmentAndSources;
    }

    public static IncrementalCompilationContext d(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, CompilerConfiguration compilerConfiguration, AbstractProjectFileSearchScope abstractProjectFileSearchScope, List list) {
        list.getClass();
        return IncrementalCompilationContextUtilsKt.createContextForIncrementalCompilation(compilerConfiguration, vfsBasedProjectEnvironment, AbstractProjectEnvironment.getSearchScopeBySourceFiles$default(vfsBasedProjectEnvironment, list, false, 2, null), CollectionsKt.emptyList(), abstractProjectFileSearchScope);
    }

    private static final File dumpModel$file(Ref.IntRef intRef, File file, String str) {
        String str2;
        if (intRef.element != 0) {
            str2 = "." + intRef.element;
        } else {
            str2 = Argument.Delimiters.none;
        }
        return new File(file, str + str2 + ".xml");
    }

    public static FirSession e(AbstractProjectFileSearchScope abstractProjectFileSearchScope, List list, CompilerConfiguration compilerConfiguration, FirJvmSessionFactory.Context context, final Ref.BooleanRef booleanRef, final Ref.ObjectRef objectRef, final Function1 function1, final VfsBasedProjectEnvironment vfsBasedProjectEnvironment, final List list2, final FirModuleData firModuleData, boolean z, Function1 function2) {
        list2.getClass();
        firModuleData.getClass();
        function2.getClass();
        return FirJvmSessionFactory.INSTANCE.createSourceSession(firModuleData, abstractProjectFileSearchScope, new Function1() { // from class: ww7
            public final Object invoke(Object obj) {
                return JvmFrontendPipelinePhase.prepareJvmSessions$lambda$3$0(booleanRef, objectRef, function1, list2, firModuleData, vfsBasedProjectEnvironment, (FirSession) obj);
            }
        }, list, compilerConfiguration, context, true, z, function2);
    }

    public static AbstractFirMetadataSessionFactory.Context g(final FirJvmSessionFactory.Context context) {
        return new AbstractFirMetadataSessionFactory.Context(new Function0() { // from class: yw7
            public final Object invoke() {
                return JvmFrontendPipelinePhase.prepareJvmSessions$lambda$0$0(context);
            }
        }, new Function0() { // from class: zw7
            public final Object invoke() {
                return JvmFrontendPipelinePhase.prepareJvmSessions$lambda$0$1();
            }
        });
    }

    private final GroupedKtSources groupKtFiles(List<? extends KtFile> ktFiles) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (KtFile ktFile : ktFiles) {
            KtPsiSourceFile ktPsiSourceFile = new KtPsiSourceFile(ktFile);
            String hmppModuleName = IsCommonSourceKt.getHmppModuleName(ktFile);
            if (hmppModuleName != null) {
                linkedHashSet2.add(ktPsiSourceFile);
                Object linkedHashSet3 = linkedHashMap.get(hmppModuleName);
                if (linkedHashSet3 == null) {
                    linkedHashSet3 = new LinkedHashSet();
                    linkedHashMap.put(hmppModuleName, linkedHashSet3);
                }
                ((Set) linkedHashSet3).add(ktPsiSourceFile);
            } else if (Intrinsics.areEqual(IsCommonSourceKt.isCommonSource(ktFile), Boolean.TRUE)) {
                linkedHashSet2.add(ktPsiSourceFile);
            } else {
                linkedHashSet.add(ktPsiSourceFile);
            }
        }
        return new GroupedKtSources(linkedHashSet, linkedHashSet2, linkedHashMap);
    }

    public static boolean h(KtSourceFile ktSourceFile) {
        ktSourceFile.getClass();
        KtPsiSourceFile ktPsiSourceFile = ktSourceFile instanceof KtPsiSourceFile ? (KtPsiSourceFile) ktSourceFile : null;
        PsiFile psiFile = ktPsiSourceFile != null ? ktPsiSourceFile.getPsiFile() : null;
        KtFile ktFile = psiFile instanceof KtFile ? (KtFile) psiFile : null;
        return ktFile != null && ktFile.isScript();
    }

    public static GroupedKtSources i(CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment) {
        return GroupedKtSourcesKt.collectSources(compilerConfiguration, vfsBasedProjectEnvironment);
    }

    public static CharSequence j(String str) {
        return "  " + str;
    }

    public static FirSession k(Name name, List list, CompilerConfiguration compilerConfiguration, FirJvmSessionFactory.Context context) {
        return FirJvmSessionFactory.INSTANCE.createSharedLibrarySession(name, (List<? extends FirExtensionRegistrar>) list, CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), context);
    }

    public static GroupedKtSources l(KotlinCoreEnvironment kotlinCoreEnvironment, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        List<? extends KtFile> sourceFiles = kotlinCoreEnvironment.getSourceFiles();
        Iterator<T> it = sourceFiles.iterator();
        while (it.hasNext()) {
            AnalyzerWithCompilerReport.INSTANCE.reportSyntaxErrors((PsiElement) it.next(), baseDiagnosticsCollector);
        }
        return INSTANCE.groupKtFiles(sourceFiles);
    }

    public static FirSession m(DependencyListForCliModule dependencyListForCliModule, List list, CompilerConfiguration compilerConfiguration, FirJvmSessionFactory.Context context, FirSession firSession) {
        firSession.getClass();
        return FirJvmSessionFactory.INSTANCE.createLibrarySession(firSession, dependencyListForCliModule.getModuleDataProvider(), list, CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirJvmSessionFactory.Context prepareJvmSessions$lambda$0$0(FirJvmSessionFactory.Context context) {
        return context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public static final FirJsSessionFactory.Context prepareJvmSessions$lambda$0$1() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirJvmIncrementalCompilationSymbolProviders prepareJvmSessions$lambda$3$0(Ref.BooleanRef booleanRef, Ref.ObjectRef objectRef, Function1 function1, List list, FirModuleData firModuleData, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, FirSession firSession) {
        FirJvmIncrementalCompilationSymbolProviders firJvmIncrementalCompilationSymbolProvidersCreateSymbolProviders;
        firSession.getClass();
        if (booleanRef.element) {
            return (FirJvmIncrementalCompilationSymbolProviders) objectRef.element;
        }
        booleanRef.element = true;
        IncrementalCompilationContext incrementalCompilationContext = (IncrementalCompilationContext) function1.invoke(list);
        if (incrementalCompilationContext == null || (firJvmIncrementalCompilationSymbolProvidersCreateSymbolProviders = FirJvmIncrementalCompilationSymbolProvidersKt.createSymbolProviders(incrementalCompilationContext, firSession, firModuleData, vfsBasedProjectEnvironment)) == null) {
            return null;
        }
        objectRef.element = firJvmIncrementalCompilationSymbolProvidersCreateSymbolProviders;
        return firJvmIncrementalCompilationSymbolProvidersCreateSymbolProviders;
    }

    public final void dumpModel(String dir, List<? extends Module> chunk, CompilerConfiguration configuration, CommonCompilerArguments arguments) {
        File fileDumpModel$file;
        dir.getClass();
        chunk.getClass();
        configuration.getClass();
        arguments.getClass();
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str = "model-" + ((Module) CollectionsKt.first(chunk)).getName();
        Ref.IntRef intRef = new Ref.IntRef();
        do {
            fileDumpModel$file = dumpModel$file(intRef, file, str);
            intRef.element++;
        } while (fileDumpModel$file.exists());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDumpModel$file), Charsets.UTF_8), 8192);
        try {
            XMLStreamWriter xMLStreamWriterCreateXMLStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(bufferedWriter);
            xMLStreamWriterCreateXMLStreamWriter.writeStartDocument("UTF-8", "1.0");
            PrettyPrintDepth prettyPrintDepth = new PrettyPrintDepth(0);
            JvmFrontendPipelinePhaseKt.start(xMLStreamWriterCreateXMLStreamWriter, ModuleXmlParser.MODULES, prettyPrintDepth);
            JvmFrontendPipelinePhaseKt.start(xMLStreamWriterCreateXMLStreamWriter, "compilerArguments", prettyPrintDepth);
            for (String str2 : ArgumentUtils.convertArgumentsToStringList(arguments)) {
                JvmFrontendPipelinePhaseKt.empty(xMLStreamWriterCreateXMLStreamWriter, "arg", prettyPrintDepth);
                xMLStreamWriterCreateXMLStreamWriter.writeAttribute("value", str2);
            }
            JvmFrontendPipelinePhaseKt.end(xMLStreamWriterCreateXMLStreamWriter, prettyPrintDepth);
            for (Module module : chunk) {
                JvmFrontendPipelinePhaseKt.start(xMLStreamWriterCreateXMLStreamWriter, ModuleXmlParser.MODULE, prettyPrintDepth);
                xMLStreamWriterCreateXMLStreamWriter.writeAttribute("timestamp", String.valueOf(System.currentTimeMillis()));
                xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.NAME, module.getName());
                xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.TYPE, module.getType());
                xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.OUTPUT_DIR, module.getOutputDir());
                for (String str3 : module.getFriendPaths()) {
                    JvmFrontendPipelinePhaseKt.empty(xMLStreamWriterCreateXMLStreamWriter, ModuleXmlParser.FRIEND_DIR, prettyPrintDepth);
                    xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.PATH, str3);
                }
                for (String str4 : module.getSourceFiles()) {
                    JvmFrontendPipelinePhaseKt.empty(xMLStreamWriterCreateXMLStreamWriter, ModuleXmlParser.SOURCES, prettyPrintDepth);
                    xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.PATH, str4);
                }
                for (JavaRootPath javaRootPath : module.getJavaSourceRoots()) {
                    JvmFrontendPipelinePhaseKt.start(xMLStreamWriterCreateXMLStreamWriter, ModuleXmlParser.JAVA_SOURCE_ROOTS, prettyPrintDepth);
                    xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.PATH, javaRootPath.getPath());
                    String packagePrefix = javaRootPath.getPackagePrefix();
                    if (packagePrefix != null) {
                        xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.JAVA_SOURCE_PACKAGE_PREFIX, packagePrefix);
                    }
                    JvmFrontendPipelinePhaseKt.end(xMLStreamWriterCreateXMLStreamWriter, prettyPrintDepth);
                }
                List<ContentRoot> listEmptyList = (List) configuration.get(CLIConfigurationKeys.CONTENT_ROOTS);
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                for (ContentRoot contentRoot : listEmptyList) {
                    if (contentRoot instanceof JvmClasspathRoot) {
                        JvmFrontendPipelinePhaseKt.empty(xMLStreamWriterCreateXMLStreamWriter, ModuleXmlParser.CLASSPATH, prettyPrintDepth);
                        xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.PATH, ((JvmClasspathRoot) contentRoot).getFile().getAbsolutePath());
                    } else if (contentRoot instanceof JvmModulePathRoot) {
                        JvmFrontendPipelinePhaseKt.empty(xMLStreamWriterCreateXMLStreamWriter, "modulepath", prettyPrintDepth);
                        xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.PATH, ((JvmModulePathRoot) contentRoot).getFile().getAbsolutePath());
                    }
                }
                for (String str5 : module.getCommonSourceFiles()) {
                    JvmFrontendPipelinePhaseKt.empty(xMLStreamWriterCreateXMLStreamWriter, ModuleXmlParser.COMMON_SOURCES, prettyPrintDepth);
                    xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.PATH, str5);
                }
                String modularJdkRoot = module.getModularJdkRoot();
                if (modularJdkRoot != null) {
                    JvmFrontendPipelinePhaseKt.empty(xMLStreamWriterCreateXMLStreamWriter, ModuleXmlParser.MODULAR_JDK_ROOT, prettyPrintDepth);
                    xMLStreamWriterCreateXMLStreamWriter.writeAttribute(ModuleXmlParser.PATH, modularJdkRoot);
                }
                JvmFrontendPipelinePhaseKt.end(xMLStreamWriterCreateXMLStreamWriter, prettyPrintDepth);
            }
            JvmFrontendPipelinePhaseKt.end(xMLStreamWriterCreateXMLStreamWriter, prettyPrintDepth);
            xMLStreamWriterCreateXMLStreamWriter.writeCharacters("\n");
            xMLStreamWriterCreateXMLStreamWriter.writeEndDocument();
            xMLStreamWriterCreateXMLStreamWriter.flush();
            xMLStreamWriterCreateXMLStreamWriter.close();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedWriter, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(bufferedWriter, th);
                throw th2;
            }
        }
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public JvmFrontendPipelineArtifact executePhase(ConfigurationPipelineArtifact input) {
        AbstractProjectFileSearchScope abstractProjectFileSearchScopePlus;
        String moduleName;
        input.getClass();
        final CompilerConfiguration configuration = input.getConfiguration();
        Disposable rootDisposable = input.getRootDisposable();
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        ModuleChunk moduleChunk = CLIConfigurationKeysKt.getModuleChunk(configuration);
        moduleChunk.getClass();
        String strTargetDescription = K2JVMCompilerKt.targetDescription(moduleChunk);
        if (perfManager != null) {
            perfManager.setTargetDescription(strTargetDescription);
        }
        if (!checkNotSupportedPlugins(configuration)) {
            if (perfManager != null) {
                perfManager.notifyPhaseFinished(PhaseType.Initialization);
            }
            return null;
        }
        EnvironmentAndSources environmentAndSourcesCreateEnvironmentAndSources = createEnvironmentAndSources(configuration, rootDisposable, strTargetDescription);
        if (environmentAndSourcesCreateEnvironmentAndSources == null) {
            if (perfManager != null) {
                perfManager.notifyPhaseFinished(PhaseType.Initialization);
            }
            return null;
        }
        final VfsBasedProjectEnvironment environment = environmentAndSourcesCreateEnvironmentAndSources.getEnvironment();
        Function0<GroupedKtSources> function0Component2 = environmentAndSourcesCreateEnvironmentAndSources.component2();
        Boolean boolRunAnalysisHandlerExtensions = runAnalysisHandlerExtensions(environment.getProject(), configuration);
        if (boolRunAnalysisHandlerExtensions != null) {
            boolean zBooleanValue = boolRunAnalysisHandlerExtensions.booleanValue();
            if (zBooleanValue) {
                throw new SuccessfulPipelineExecutionException();
            }
            if (!zBooleanValue) {
                throw new PipelineStepException(true);
            }
            bu8.a();
            return null;
        }
        GroupedKtSources groupedKtSources = (GroupedKtSources) function0Component2.invoke();
        List<KtSourceFile> allFiles = GroupedKtSourcesKt.getAllFiles(groupedKtSources);
        if (perfManager != null) {
            perfManager.notifyPhaseFinished(PhaseType.Initialization);
        }
        if (allFiles.isEmpty() && !CLIConfigurationKeysKt.getAllowNoSourceFiles(configuration) && CLIConfigurationKeysKt.getBuildFile(configuration) == null) {
            if (!CLIConfigurationKeysKt.getPrintVersion(configuration)) {
                CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "No source files", null, 4, null);
            }
            return null;
        }
        if (perfManager != null) {
            perfManager.notifyPhaseStarted(PhaseType.Analysis);
        }
        boolean useLightTree = CommonConfigurationKeysKt.getUseLightTree(configuration);
        if (useLightTree) {
            abstractProjectFileSearchScopePlus = AbstractProjectFileSearchScope.EMPTY.INSTANCE;
        } else {
            if (useLightTree) {
                bu8.a();
                return null;
            }
            List<KtSourceFile> list = allFiles;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                KtPsiSourceFile ktPsiSourceFile = (KtSourceFile) it.next();
                ktPsiSourceFile.getClass();
                KtFile psiFile = ktPsiSourceFile.getPsiFile();
                psiFile.getClass();
                arrayList.add(psiFile);
            }
            abstractProjectFileSearchScopePlus = environment.getSearchScopeByPsiFiles(arrayList).plus(environment.getSearchScopeForProjectJavaSources());
            if (checkIfScriptsInCommonSources(configuration, arrayList)) {
                return null;
            }
        }
        AbstractProjectFileSearchScope searchScopeForProjectLibraries = environment.getSearchScopeForProjectLibraries();
        final AbstractProjectFileSearchScope abstractProjectFileSearchScopeCreateIncrementalCompilationScope = IncrementalCompilationContextUtilsKt.createIncrementalCompilationScope(configuration, environment, abstractProjectFileSearchScopePlus);
        if (abstractProjectFileSearchScopeCreateIncrementalCompilationScope != null) {
            searchScopeForProjectLibraries = searchScopeForProjectLibraries.minus(abstractProjectFileSearchScopeCreateIncrementalCompilationScope);
        } else {
            abstractProjectFileSearchScopeCreateIncrementalCompilationScope = null;
        }
        if (moduleChunk.getModules().size() > 1) {
            List<Module> modules = moduleChunk.getModules();
            modules.getClass();
            moduleName = CollectionsKt.joinToString$default(modules, "+", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: ex7
                public final Object invoke(Object obj) {
                    return JvmFrontendPipelinePhase.b((Module) obj);
                }
            }, 30, (Object) null);
        } else {
            moduleName = CommonConfigurationKeysKt.getModuleName(configuration);
            moduleName.getClass();
        }
        List<Module> modules2 = moduleChunk.getModules();
        modules2.getClass();
        List listEmptyList = CollectionsKt.emptyList();
        Iterator<T> it2 = modules2.iterator();
        while (it2.hasNext()) {
            listEmptyList = CollectionsKt.plus(listEmptyList, ((Module) it2.next()).getFriendPaths());
        }
        DependencyListForCliModule dependencyListForCliModuleCreateLibraryListForJvm = CliCompilerUtilsKt.createLibraryListForJvm(moduleName, configuration, listEmptyList);
        Name nameSpecial = Name.special("<" + moduleName + '>');
        nameSpecial.getClass();
        List listPrepareJvmSessions = prepareJvmSessions(allFiles, nameSpecial, configuration, environment, searchScopeForProjectLibraries, dependencyListForCliModuleCreateLibraryListForJvm, FirSessionConstructionUtilsKt.isCommonSourceForLt(groupedKtSources), new Function1() { // from class: fx7
            public final Object invoke(Object obj) {
                return Boolean.valueOf(JvmFrontendPipelinePhase.h((KtSourceFile) obj));
            }
        }, FirSessionConstructionUtilsKt.getFileBelongsToModuleForLt(groupedKtSources), new Function1() { // from class: gx7
            public final Object invoke(Object obj) {
                return JvmFrontendPipelinePhase.d(environment, configuration, abstractProjectFileSearchScopeCreateIncrementalCompilationScope, (List) obj);
            }
        });
        JvmFrontendPipelinePhase$executePhase$countFilesAndLines$1 jvmFrontendPipelinePhase$executePhase$countFilesAndLines$1 = perfManager == null ? null : new JvmFrontendPipelinePhase$executePhase$countFilesAndLines$1(perfManager);
        BaseDiagnosticsCollector diagnosticsCollector = CLIConfigurationKeysKt.getDiagnosticsCollector(configuration);
        List<SessionWithSources> list2 = listPrepareJvmSessions;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (SessionWithSources sessionWithSources : list2) {
            FirSession session = sessionWithSources.getSession();
            List listComponent2 = sessionWithSources.component2();
            arrayList2.add(FirUtilsKt.resolveAndCheckFir(session, CommonConfigurationKeysKt.getUseLightTree(configuration) ? FirUtilsKt.buildFirViaLightTree(session, listComponent2, diagnosticsCollector, jvmFrontendPipelinePhase$executePhase$countFilesAndLines$1) : FirUtilsKt.buildFirFromKtFiles(session, JvmPipelineUtilsKt.asKtFilesList(listComponent2)), diagnosticsCollector));
        }
        ConvertToIrKt.runPlatformCheckers(arrayList2, diagnosticsCollector);
        boolean useLightTree2 = CommonConfigurationKeysKt.getUseLightTree(configuration);
        if (useLightTree2) {
            if (!arrayList2.isEmpty()) {
                Iterator it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    if (!UtilsKt.checkKotlinPackageUsageForLightTree(configuration, ((SingleModuleFrontendOutput) it3.next()).getFir())) {
                        return null;
                    }
                }
            }
            return new JvmFrontendPipelineArtifact(AllModulesFrontendOutput.m573constructorimpl(arrayList2), configuration, environment, allFiles, null);
        }
        if (useLightTree2) {
            bu8.a();
            return null;
        }
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator it4 = list2.iterator();
            while (it4.hasNext()) {
                if (!UtilsKt.checkKotlinPackageUsageForPsi(configuration, JvmPipelineUtilsKt.asKtFilesList(((SessionWithSources) it4.next()).component2()))) {
                    return null;
                }
            }
        }
        return new JvmFrontendPipelineArtifact(AllModulesFrontendOutput.m573constructorimpl(arrayList2), configuration, environment, allFiles, null);
    }

    public final <F> List<SessionWithSources<F>> prepareJvmSessions(List<? extends F> files, final Name rootModuleName, final CompilerConfiguration configuration, final VfsBasedProjectEnvironment projectEnvironment, AbstractProjectFileSearchScope librariesScope, final DependencyListForCliModule libraryList, Function1<? super F, Boolean> isCommonSource, Function1<? super F, Boolean> isScript, Function2<? super F, ? super String, Boolean> fileBelongsToModule, final Function1<? super List<? extends F>, IncrementalCompilationContext> createProviderAndScopeForIncrementalCompilation) {
        files.getClass();
        rootModuleName.getClass();
        configuration.getClass();
        projectEnvironment.getClass();
        librariesScope.getClass();
        libraryList.getClass();
        isCommonSource.getClass();
        isScript.getClass();
        fileBelongsToModule.getClass();
        createProviderAndScopeForIncrementalCompilation.getClass();
        final List<FirExtensionRegistrar> compilerExtensions = FirExtensionRegistrarConfigurationUtilKt.getCompilerExtensions(configuration, FirExtensionRegistrar.INSTANCE);
        final AbstractProjectFileSearchScope searchScopeForProjectJavaSources = projectEnvironment.getSearchScopeForProjectJavaSources();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final FirJvmSessionFactory.Context context = new FirJvmSessionFactory.Context(configuration, projectEnvironment, librariesScope, false, 8, null);
        return SessionConstructionUtils.INSTANCE.prepareSessions(files, configuration, rootModuleName, JvmPlatforms.INSTANCE.getUnspecifiedJvmPlatform(), false, libraryList, compilerExtensions, isCommonSource, isScript, fileBelongsToModule, new Function0() { // from class: ax7
            public final Object invoke() {
                return JvmFrontendPipelinePhase.g(context);
            }
        }, new Function0() { // from class: bx7
            public final Object invoke() {
                return JvmFrontendPipelinePhase.k(rootModuleName, compilerExtensions, configuration, context);
            }
        }, new Function1() { // from class: cx7
            public final Object invoke(Object obj) {
                return JvmFrontendPipelinePhase.m(libraryList, compilerExtensions, configuration, context, (FirSession) obj);
            }
        }, new FirSessionProducer() { // from class: dx7
            @Override // org.jetbrains.kotlin.cli.common.FirSessionProducer
            public final FirSession createSession(List list, FirModuleData firModuleData, boolean z, Function1 function1) {
                return JvmFrontendPipelinePhase.e(searchScopeForProjectJavaSources, compilerExtensions, configuration, context, booleanRef, objectRef, createProviderAndScopeForIncrementalCompilation, projectEnvironment, list, firModuleData, z, function1);
            }
        }, (16384 & 16384) != 0 ? null : null);
    }

    public final Boolean runAnalysisHandlerExtensions(Project project, CompilerConfiguration configuration) {
        project.getClass();
        configuration.getClass();
        List compilerExtensions = ExtensionPointUtilsKt.getCompilerExtensions(configuration, FirAnalysisHandlerExtension.INSTANCE);
        ArrayList arrayList = new ArrayList();
        for (Object obj : compilerExtensions) {
            if (((FirAnalysisHandlerExtension) obj).isApplicable(configuration)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (arrayList == null) {
            return null;
        }
        boolean z = true;
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (!((FirAnalysisHandlerExtension) it.next()).doAnalysis(project, configuration)) {
                    z = false;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }
}
