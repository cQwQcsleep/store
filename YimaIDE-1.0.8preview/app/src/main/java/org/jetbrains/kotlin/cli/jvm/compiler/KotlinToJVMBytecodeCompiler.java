package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.search.GlobalSearchScope;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.analyzer.AnalysisResult;
import org.jetbrains.kotlin.analyzer.AnalysisResult$RetryWithAdditionalRoots;
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext;
import org.jetbrains.kotlin.backend.jvm.JvmBackendExtension;
import org.jetbrains.kotlin.backend.jvm.JvmGeneratorExtensions;
import org.jetbrains.kotlin.backend.jvm.JvmGeneratorExtensionsImpl;
import org.jetbrains.kotlin.backend.jvm.JvmIrCodegenFactory;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.fir.FirDiagnosticsCompilerResultsReporter;
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleBuilder;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler;
import org.jetbrains.kotlin.cli.jvm.config.ClassicFrontendSpecificJvmConfigurationKeys;
import org.jetbrains.kotlin.cli.jvm.config.JavaSourceRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.codegen.ClassBuilderFactories;
import org.jetbrains.kotlin.codegen.ClassBuilderFactory;
import org.jetbrains.kotlin.codegen.JvmBackendClassResolverForModuleWithDependencies;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JVMConfigurationKeysKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.diagnostics.impl.DiagnosticsCollectorImpl;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendClassResolver;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendExtension;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.backend.jvm.LoadJvmKlibsKt;
import org.jetbrains.kotlin.ir.backend.jvm.serialization.JvmDescriptorMangler;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.declarations.impl.IrModuleFragmentImpl;
import org.jetbrains.kotlin.ir.util.SymbolTable;
import org.jetbrains.kotlin.load.java.JavaClassesTracker;
import org.jetbrains.kotlin.load.kotlin.ModuleVisibilityManager;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.modules.TargetId;
import org.jetbrains.kotlin.modules.TargetIdKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.progress.ProgressIndicatorAndCompilationCanceledStatus;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi2ir.generators.fragments.EvaluatorFragmentInfo;
import org.jetbrains.kotlin.resolve.jvm.KotlinJavaPsiFacade;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001BB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0000¢\u0006\u0002\b\u0012JY\u0010\u0013\u001a\u00020\n*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u001fJ0\u0010 \u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001b\u001a\u00020!H\u0002J\u000e\u0010\"\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010$2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u000b\u001a\u00020\fJ,\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0)2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010%\u001a\u00020$2\u0006\u0010\u001b\u001a\u00020!H\u0002J#\u0010,\u001a\u00020+*\u00020-2\u0006\u0010.\u001a\u00020\u00182\b\u0010/\u001a\u0004\u0018\u000100H\u0000¢\u0006\u0002\b1J\u0010\u00102\u001a\u0004\u0018\u00010$2\u0006\u0010\u000b\u001a\u00020\fJS\u00103\u001a\u0002042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u00182\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u00112\u0006\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010;H\u0000¢\u0006\u0002\b<J=\u0010=\u001a\u00020'2\u0006\u0010>\u001a\u0002042\u0006\u0010?\u001a\u00020'2\u0006\u00108\u001a\u00020*2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020\u00182\u0006\u0010@\u001a\u00020\nH\u0000¢\u0006\u0002\bAR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006C"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinToJVMBytecodeCompiler;", Argument.Delimiters.none, "<init>", "()V", "customClassBuilderFactory", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "Lorg/jetbrains/kotlin/codegen/ClassBuilderFactory;", "getCustomClassBuilderFactory", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "compileModules", Argument.Delimiters.none, "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "buildFile", "Ljava/io/File;", "chunk", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/modules/Module;", "compileModules$org_jetbrains_kotlin_cli_jvm", "runBackend", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinToJVMBytecodeCompiler$BackendInputForMultiModuleChunk;", "project", "Lcom/intellij/openapi/project/Project;", "compilerConfiguration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "diagnosticsReporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "allSourceFiles", "Lorg/jetbrains/kotlin/psi/KtFile;", "runBackend$org_jetbrains_kotlin_cli_jvm", "runFrontendAndGenerateIrUsingClassicFrontend", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "compileBunchOfSources", "repeatAnalysisIfNeeded", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "analyzeAndGenerate", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "convertToIr", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory;", "Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory$BackendInput;", "toBackendInput", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "configuration", "jvmBackendExtension", "Lorg/jetbrains/kotlin/backend/jvm/JvmBackendExtension;", "toBackendInput$org_jetbrains_kotlin_cli_jvm", "analyze", "runLowerings", "Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory$CodegenInput;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", ModuleXmlParser.MODULE, "codegenFactory", "backendInput", "firJvmBackendClassResolver", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendClassResolver;", "runLowerings$org_jetbrains_kotlin_cli_jvm", "runCodegen", "codegenInput", "state", "reportDiagnosticsToMessageCollector", "runCodegen$org_jetbrains_kotlin_cli_jvm", "BackendInputForMultiModuleChunk", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinToJVMBytecodeCompiler {
    public static final KotlinToJVMBytecodeCompiler INSTANCE = new KotlinToJVMBytecodeCompiler();
    private static final CompilerConfigurationKey<ClassBuilderFactory> customClassBuilderFactory = CompilerConfigurationKey.INSTANCE.create("customClassBuilderFactory");

    private KotlinToJVMBytecodeCompiler() {
    }

    public static AnalysisResult a(KotlinCoreEnvironment kotlinCoreEnvironment, List list, List list2) {
        Set setEmptySet;
        Project project = kotlinCoreEnvironment.getProject();
        List list3 = (List) kotlinCoreEnvironment.getConfiguration().get(JVMConfigurationKeys.MODULES);
        if (list3 != null) {
            setEmptySet = new HashSet();
            Iterator it = list3.iterator();
            while (it.hasNext()) {
                VirtualFile virtualFileFindLocalFile = kotlinCoreEnvironment.findLocalFile(((Module) it.next()).getOutputDir());
                if (virtualFileFindLocalFile != null) {
                    setEmptySet.add(virtualFileFindLocalFile);
                }
            }
        } else {
            setEmptySet = null;
        }
        if (setEmptySet == null) {
            setEmptySet = SetsKt.emptySet();
        }
        List list4 = list;
        GlobalSearchScope globalSearchScopeNewModuleSearchScope = TopDownAnalyzerFacadeForJVM.INSTANCE.newModuleSearchScope(project, list4);
        if (!setEmptySet.isEmpty()) {
            globalSearchScopeNewModuleSearchScope = globalSearchScopeNewModuleSearchScope.uniteWith(new VfsBasedProjectEnvironment.DirectoriesScope(project, setEmptySet));
            globalSearchScopeNewModuleSearchScope.getClass();
        }
        return TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration$default(project, list4, new NoScopeRecordCliBindingTrace(project), kotlinCoreEnvironment.getConfiguration(), new KotlinToJVMBytecodeCompiler$analyze$1$1(kotlinCoreEnvironment), null, globalSearchScopeNewModuleSearchScope, list2, null, null, null, 1824, null);
    }

    private final Pair<JvmIrCodegenFactory, JvmIrCodegenFactory.BackendInput> convertToIr(KotlinCoreEnvironment environment, AnalysisResult result, DiagnosticReporter diagnosticsReporter) {
        JvmIrCodegenFactory.BackendInput backendInputConvertToIr;
        CompilerConfiguration configuration = environment.getConfiguration();
        JvmIrCodegenFactory jvmIrCodegenFactory = new JvmIrCodegenFactory(configuration, (JvmDescriptorMangler) null, (SymbolTable) null, (JvmGeneratorExtensionsImpl) null, (EvaluatorFragmentInfo) null, (JvmIrCodegenFactory.IdeCodegenSettings) null, 62, (DefaultConstructorMarker) null);
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(environment.getConfiguration());
        PhaseType phaseType = PhaseType.TranslationToIr;
        if (perfManager == null) {
            backendInputConvertToIr = jvmIrCodegenFactory.convertToIr(environment.getSourceFiles(), configuration, result.getModuleDescriptor(), diagnosticsReporter, result.getBindingContext(), CommonConfigurationKeysKt.getLanguageVersionSettings(configuration), false, false);
        } else {
            try {
                perfManager.notifyPhaseStarted(phaseType);
                JvmIrCodegenFactory.BackendInput backendInputConvertToIr2 = jvmIrCodegenFactory.convertToIr(environment.getSourceFiles(), configuration, result.getModuleDescriptor(), diagnosticsReporter, result.getBindingContext(), CommonConfigurationKeysKt.getLanguageVersionSettings(configuration), false, false);
                perfManager.notifyPhaseFinished(phaseType);
                backendInputConvertToIr = backendInputConvertToIr2;
            } catch (Throwable th) {
                perfManager.notifyPhaseFinished(phaseType);
                throw th;
            }
        }
        return new Pair<>(jvmIrCodegenFactory, backendInputConvertToIr);
    }

    private final AnalysisResult repeatAnalysisIfNeeded(AnalysisResult result, KotlinCoreEnvironment environment) {
        if (!(result instanceof AnalysisResult$RetryWithAdditionalRoots)) {
            return result;
        }
        CompilerConfiguration configuration = environment.getConfiguration();
        boolean isReadOnly = configuration.getIsReadOnly();
        configuration.setReadOnly(false);
        AnalysisResult$RetryWithAdditionalRoots analysisResult$RetryWithAdditionalRoots = (AnalysisResult$RetryWithAdditionalRoots) result;
        JvmContentRootsKt.addJavaSourceRoots$default(configuration, analysisResult$RetryWithAdditionalRoots.getAdditionalJavaRoots(), null, 2, null);
        configuration.setReadOnly(isReadOnly);
        if (analysisResult$RetryWithAdditionalRoots.getAddToEnvironment()) {
            List<File> additionalJavaRoots = analysisResult$RetryWithAdditionalRoots.getAdditionalJavaRoots();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(additionalJavaRoots, 10));
            Iterator<T> it = additionalJavaRoots.iterator();
            while (it.hasNext()) {
                arrayList.add(new JavaSourceRoot((File) it.next(), null));
            }
            environment.updateClasspath(arrayList);
        }
        if (!analysisResult$RetryWithAdditionalRoots.getAdditionalClassPathRoots().isEmpty()) {
            List<File> additionalClassPathRoots = analysisResult$RetryWithAdditionalRoots.getAdditionalClassPathRoots();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(additionalClassPathRoots, 10));
            Iterator<T> it2 = additionalClassPathRoots.iterator();
            while (it2.hasNext()) {
                arrayList2.add(new JvmClasspathRoot((File) it2.next(), false));
            }
            environment.updateClasspath(arrayList2);
        }
        if (!analysisResult$RetryWithAdditionalRoots.getAdditionalKotlinRoots().isEmpty()) {
            environment.addKotlinSourceRoots(analysisResult$RetryWithAdditionalRoots.getAdditionalKotlinRoots());
        }
        KotlinJavaPsiFacade.getInstance(environment.getProject()).clearPackageCaches();
        JavaClassesTracker javaClassesTracker = (JavaClassesTracker) configuration.get(ClassicFrontendSpecificJvmConfigurationKeys.JAVA_CLASSES_TRACKER);
        if (javaClassesTracker != null) {
            javaClassesTracker.clear();
        }
        LookupTracker lookupTracker = (LookupTracker) configuration.get(CommonConfigurationKeys.LOOKUP_TRACKER);
        if (lookupTracker != null) {
            lookupTracker.clear();
        }
        CommonConfigurationKeysKt.getMessageCollector(configuration).clear();
        return repeatAnalysisIfNeeded(analyze(environment), environment);
    }

    private final BackendInputForMultiModuleChunk runFrontendAndGenerateIrUsingClassicFrontend(KotlinCoreEnvironment environment, CompilerConfiguration compilerConfiguration, List<? extends Module> chunk, DiagnosticReporter diagnosticsReporter) {
        AnalysisResult analysisResultRepeatAnalysisIfNeeded;
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(environment.getConfiguration());
        if (perfManager != null) {
            perfManager.notifyPhaseFinished(PhaseType.Initialization);
        }
        PhaseType phaseType = PhaseType.Analysis;
        if (perfManager == null) {
            KotlinToJVMBytecodeCompiler kotlinToJVMBytecodeCompiler = INSTANCE;
            analysisResultRepeatAnalysisIfNeeded = kotlinToJVMBytecodeCompiler.repeatAnalysisIfNeeded(kotlinToJVMBytecodeCompiler.analyze(environment), environment);
        } else {
            try {
                perfManager.notifyPhaseStarted(phaseType);
                KotlinToJVMBytecodeCompiler kotlinToJVMBytecodeCompiler2 = INSTANCE;
                analysisResultRepeatAnalysisIfNeeded = kotlinToJVMBytecodeCompiler2.repeatAnalysisIfNeeded(kotlinToJVMBytecodeCompiler2.analyze(environment), environment);
                perfManager.notifyPhaseFinished(phaseType);
            } catch (Throwable th) {
                perfManager.notifyPhaseFinished(phaseType);
                throw th;
            }
        }
        if (analysisResultRepeatAnalysisIfNeeded == null || !analysisResultRepeatAnalysisIfNeeded.getShouldGenerateCode()) {
            return null;
        }
        ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
        analysisResultRepeatAnalysisIfNeeded.throwIfError();
        FqName fqNameFindMainClass = chunk.size() == 1 && compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_JAR) != null ? FindMainClassKt.findMainClass(analysisResultRepeatAnalysisIfNeeded.getBindingContext(), CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), environment.getSourceFiles()) : null;
        Pair<JvmIrCodegenFactory, JvmIrCodegenFactory.BackendInput> pairConvertToIr = convertToIr(environment, analysisResultRepeatAnalysisIfNeeded, diagnosticsReporter);
        return new BackendInputForMultiModuleChunk((JvmIrCodegenFactory) pairConvertToIr.component1(), (JvmIrCodegenFactory.BackendInput) pairConvertToIr.component2(), analysisResultRepeatAnalysisIfNeeded.getModuleDescriptor(), null, null, fqNameFindMainClass, 24, null);
    }

    public static /* synthetic */ JvmIrCodegenFactory.CodegenInput runLowerings$org_jetbrains_kotlin_cli_jvm$default(KotlinToJVMBytecodeCompiler kotlinToJVMBytecodeCompiler, Project project, CompilerConfiguration compilerConfiguration, ModuleDescriptor moduleDescriptor, Module module, JvmIrCodegenFactory jvmIrCodegenFactory, JvmIrCodegenFactory.BackendInput backendInput, BaseDiagnosticsCollector baseDiagnosticsCollector, FirJvmBackendClassResolver firJvmBackendClassResolver, int i, Object obj) {
        return kotlinToJVMBytecodeCompiler.runLowerings$org_jetbrains_kotlin_cli_jvm(project, compilerConfiguration, moduleDescriptor, module, jvmIrCodegenFactory, backendInput, baseDiagnosticsCollector, (i & 128) != 0 ? null : firJvmBackendClassResolver);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final AnalysisResult analyze(final KotlinCoreEnvironment environment) throws UninitializedPropertyAccessException {
        environment.getClass();
        final List all = LoadJvmKlibsKt.loadJvmKlibs(environment.getConfiguration()).getAll();
        final List<KtFile> sourceFiles = environment.getSourceFiles();
        AnalyzerWithCompilerReport analyzerWithCompilerReport = new AnalyzerWithCompilerReport(environment.getConfiguration());
        analyzerWithCompilerReport.analyzeAndReport(sourceFiles, new Function0() { // from class: hd8
            public final Object invoke() {
                return KotlinToJVMBytecodeCompiler.a(environment, sourceFiles, all);
            }
        });
        AnalysisResult analysisResult = analyzerWithCompilerReport.getAnalysisResult();
        if (!analyzerWithCompilerReport.hasErrors() || (analysisResult instanceof AnalysisResult$RetryWithAdditionalRoots)) {
            return analysisResult;
        }
        return null;
    }

    public final GenerationState analyzeAndGenerate(KotlinCoreEnvironment environment) {
        AnalysisResult analysisResultRepeatAnalysisIfNeeded;
        environment.getClass();
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(environment.getConfiguration());
        if (perfManager != null) {
            perfManager.notifyPhaseFinished(PhaseType.Initialization);
        }
        PhaseType phaseType = PhaseType.Analysis;
        if (perfManager == null) {
            KotlinToJVMBytecodeCompiler kotlinToJVMBytecodeCompiler = INSTANCE;
            analysisResultRepeatAnalysisIfNeeded = kotlinToJVMBytecodeCompiler.repeatAnalysisIfNeeded(kotlinToJVMBytecodeCompiler.analyze(environment), environment);
            if (analysisResultRepeatAnalysisIfNeeded == null) {
                return null;
            }
        } else {
            try {
                perfManager.notifyPhaseStarted(phaseType);
                KotlinToJVMBytecodeCompiler kotlinToJVMBytecodeCompiler2 = INSTANCE;
                AnalysisResult analysisResultRepeatAnalysisIfNeeded2 = kotlinToJVMBytecodeCompiler2.repeatAnalysisIfNeeded(kotlinToJVMBytecodeCompiler2.analyze(environment), environment);
                if (analysisResultRepeatAnalysisIfNeeded2 == null) {
                    perfManager.notifyPhaseFinished(phaseType);
                    return null;
                }
                perfManager.notifyPhaseFinished(phaseType);
                analysisResultRepeatAnalysisIfNeeded = analysisResultRepeatAnalysisIfNeeded2;
            } catch (Throwable th) {
                perfManager.notifyPhaseFinished(phaseType);
                throw th;
            }
        }
        if (!analysisResultRepeatAnalysisIfNeeded.getShouldGenerateCode()) {
            return null;
        }
        analysisResultRepeatAnalysisIfNeeded.throwIfError();
        BaseDiagnosticsCollector diagnosticsCollectorImpl = new DiagnosticsCollectorImpl();
        Pair<JvmIrCodegenFactory, JvmIrCodegenFactory.BackendInput> pairConvertToIr = convertToIr(environment, analysisResultRepeatAnalysisIfNeeded, diagnosticsCollectorImpl);
        JvmIrCodegenFactory jvmIrCodegenFactory = (JvmIrCodegenFactory) pairConvertToIr.component1();
        JvmIrCodegenFactory.CodegenInput codegenInputRunLowerings$org_jetbrains_kotlin_cli_jvm$default = runLowerings$org_jetbrains_kotlin_cli_jvm$default(this, environment.getProject(), environment.getConfiguration(), analysisResultRepeatAnalysisIfNeeded.getModuleDescriptor(), null, jvmIrCodegenFactory, (JvmIrCodegenFactory.BackendInput) pairConvertToIr.component2(), diagnosticsCollectorImpl, null, 128, null);
        return runCodegen$org_jetbrains_kotlin_cli_jvm(codegenInputRunLowerings$org_jetbrains_kotlin_cli_jvm$default, codegenInputRunLowerings$org_jetbrains_kotlin_cli_jvm$default.getState(), jvmIrCodegenFactory, diagnosticsCollectorImpl, environment.getConfiguration(), true);
    }

    public final boolean compileBunchOfSources(KotlinCoreEnvironment environment) {
        environment.getClass();
        File outputDirectory = JVMConfigurationKeysKt.getOutputDirectory(environment.getConfiguration());
        outputDirectory.getClass();
        String path = outputDirectory.getPath();
        path.getClass();
        return compileModules$org_jetbrains_kotlin_cli_jvm(environment, null, CollectionsKt.listOf(new ModuleBuilder("test", path, "test")));
    }

    public final boolean compileModules$org_jetbrains_kotlin_cli_jvm(KotlinCoreEnvironment environment, File buildFile, List<? extends Module> chunk) {
        environment.getClass();
        chunk.getClass();
        ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
        CompilerConfiguration configuration = environment.getConfiguration();
        Project project = environment.getProject();
        ModuleVisibilityManager service = ModuleVisibilityManager.SERVICE.getInstance(project);
        Iterator<? extends Module> it = chunk.iterator();
        while (it.hasNext()) {
            service.addModule(it.next());
        }
        Iterator it2 = configuration.getList(JVMConfigurationKeys.FRIEND_PATHS).iterator();
        while (it2.hasNext()) {
            service.addFriendPath((String) it2.next());
        }
        if (CommonConfigurationKeysKt.getUseFir(configuration)) {
            k2d.a("Check failed.");
            return false;
        }
        MessageCollector messageCollector = CoreEnvironmentUtilsKt.getMessageCollector(environment);
        BaseDiagnosticsCollector diagnosticsCollectorImpl = new DiagnosticsCollectorImpl();
        BackendInputForMultiModuleChunk backendInputForMultiModuleChunkRunFrontendAndGenerateIrUsingClassicFrontend = runFrontendAndGenerateIrUsingClassicFrontend(environment, configuration, chunk, diagnosticsCollectorImpl);
        if (backendInputForMultiModuleChunkRunFrontendAndGenerateIrUsingClassicFrontend == null) {
            return true;
        }
        return runBackend$org_jetbrains_kotlin_cli_jvm(backendInputForMultiModuleChunkRunFrontendAndGenerateIrUsingClassicFrontend, project, chunk, configuration, messageCollector, diagnosticsCollectorImpl, buildFile, environment.getSourceFiles());
    }

    public final CompilerConfigurationKey<ClassBuilderFactory> getCustomClassBuilderFactory() {
        return customClassBuilderFactory;
    }

    public final boolean runBackend$org_jetbrains_kotlin_cli_jvm(BackendInputForMultiModuleChunk backendInputForMultiModuleChunk, Project project, List<? extends Module> list, CompilerConfiguration compilerConfiguration, MessageCollector messageCollector, BaseDiagnosticsCollector baseDiagnosticsCollector, File file, List<? extends KtFile> list2) {
        List<KtFile> sourceFiles;
        JvmIrCodegenFactory.BackendInput backendInputCopy$default;
        CompilerConfiguration compilerConfiguration2 = compilerConfiguration;
        backendInputForMultiModuleChunk.getClass();
        project.getClass();
        list.getClass();
        compilerConfiguration2.getClass();
        messageCollector.getClass();
        baseDiagnosticsCollector.getClass();
        VirtualFileSystem fileSystem = VirtualFileManager.getInstance().getFileSystem("file");
        ArrayList arrayList = new ArrayList(list.size());
        for (Module module : list) {
            ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
            if (list2 != null) {
                sourceFiles = CliCompilerUtilsKt.getSourceFiles(module, list2, fileSystem, list.size() > 1, file);
                if (!UtilsKt.checkKotlinPackageUsageForPsi(compilerConfiguration2, sourceFiles)) {
                    return false;
                }
            } else {
                sourceFiles = null;
            }
            CompilerConfiguration compilerConfigurationCreateConfigurationForModule = CoreEnvironmentUtilsKt.createConfigurationForModule(compilerConfiguration2, module, file);
            if (sourceFiles != null) {
                backendInputCopy$default = backendInputForMultiModuleChunk.getCodegenFactory().getModuleChunkBackendInput(backendInputForMultiModuleChunk.getBackendInput(), sourceFiles);
            } else {
                IrModuleFragment irModuleFragment = backendInputForMultiModuleChunk.getBackendInput().getIrModuleFragment();
                IrModuleFragmentImpl irModuleFragmentImpl = new IrModuleFragmentImpl(irModuleFragment.getDescriptor());
                List files = irModuleFragment.getFiles();
                List files2 = irModuleFragmentImpl.getFiles();
                for (Object obj : files) {
                    if (module.getSourceFiles().contains(((IrFile) obj).getFileEntry().getName())) {
                        files2.add(obj);
                    }
                }
                backendInputCopy$default = JvmIrCodegenFactory.BackendInput.copy$default(backendInputForMultiModuleChunk.getBackendInput(), irModuleFragmentImpl, (IrBuiltIns) null, (SymbolTable) null, (List) null, (JvmGeneratorExtensions) null, (JvmBackendExtension) null, (IrPluginContext) null, 126, (Object) null);
            }
            JvmIrCodegenFactory.BackendInput backendInputCopy$default2 = backendInputCopy$default;
            if (backendInputForMultiModuleChunk.getFirJvmBackendExtension() != null) {
                backendInputCopy$default2 = JvmIrCodegenFactory.BackendInput.copy$default(backendInputCopy$default2, (IrModuleFragment) null, (IrBuiltIns) null, (SymbolTable) null, (List) null, (JvmGeneratorExtensions) null, backendInputForMultiModuleChunk.getFirJvmBackendExtension(), (IrPluginContext) null, 95, (Object) null);
            }
            arrayList.add(runLowerings$org_jetbrains_kotlin_cli_jvm(project, compilerConfigurationCreateConfigurationForModule, backendInputForMultiModuleChunk.getModuleDescriptor(), module, backendInputForMultiModuleChunk.getCodegenFactory(), backendInputCopy$default2, baseDiagnosticsCollector, backendInputForMultiModuleChunk.getFirJvmBackendClassResolver()));
        }
        ArrayList arrayList2 = new ArrayList(list.size());
        Iterator it = arrayList.iterator();
        it.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            next.getClass();
            JvmIrCodegenFactory.CodegenInput codegenInput = (JvmIrCodegenFactory.CodegenInput) next;
            arrayList2.add(runCodegen$org_jetbrains_kotlin_cli_jvm(codegenInput, codegenInput.getState(), backendInputForMultiModuleChunk.getCodegenFactory(), baseDiagnosticsCollector, compilerConfiguration2, true));
            compilerConfiguration2 = compilerConfiguration;
        }
        return CliCompilerUtilsKt.writeOutputsIfNeeded(project, compilerConfiguration, messageCollector, false, arrayList2, backendInputForMultiModuleChunk.getMainClassFqName());
    }

    public final GenerationState runCodegen$org_jetbrains_kotlin_cli_jvm(JvmIrCodegenFactory.CodegenInput codegenInput, GenerationState state, JvmIrCodegenFactory codegenFactory, BaseDiagnosticsCollector diagnosticsReporter, CompilerConfiguration configuration, boolean reportDiagnosticsToMessageCollector) throws IOException {
        codegenInput.getClass();
        state.getClass();
        codegenFactory.getClass();
        diagnosticsReporter.getClass();
        configuration.getClass();
        ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
        codegenFactory.invokeCodegen(codegenInput);
        ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
        if (reportDiagnosticsToMessageCollector) {
            FirDiagnosticsCompilerResultsReporter.INSTANCE.reportToMessageCollector(diagnosticsReporter, (MessageCollector) configuration.getNotNull(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY), CLIConfigurationKeysKt.getRenderDiagnosticInternalName(configuration));
        }
        ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
        return state;
    }

    public final JvmIrCodegenFactory.CodegenInput runLowerings$org_jetbrains_kotlin_cli_jvm(Project project, CompilerConfiguration configuration, ModuleDescriptor moduleDescriptor, Module module, JvmIrCodegenFactory codegenFactory, JvmIrCodegenFactory.BackendInput backendInput, BaseDiagnosticsCollector diagnosticsReporter, FirJvmBackendClassResolver firJvmBackendClassResolver) {
        String moduleName;
        ModuleDescriptor moduleDescriptor2;
        FirJvmBackendClassResolver jvmBackendClassResolverForModuleWithDependencies;
        project.getClass();
        configuration.getClass();
        moduleDescriptor.getClass();
        codegenFactory.getClass();
        backendInput.getClass();
        diagnosticsReporter.getClass();
        CompilerConfigurationKey<ClassBuilderFactory> compilerConfigurationKey = customClassBuilderFactory;
        ClassBuilderFactory classBuilderFactory = ClassBuilderFactories.BINARIES;
        classBuilderFactory.getClass();
        ClassBuilderFactory classBuilderFactory2 = (ClassBuilderFactory) configuration.get(compilerConfigurationKey, classBuilderFactory);
        TargetId TargetId = module != null ? TargetIdKt.TargetId(module) : null;
        if (module == null || (moduleName = module.getName()) == null) {
            moduleName = CommonConfigurationKeysKt.getModuleName(configuration);
        }
        String str = moduleName;
        if (firJvmBackendClassResolver != null) {
            moduleDescriptor2 = moduleDescriptor;
            jvmBackendClassResolverForModuleWithDependencies = firJvmBackendClassResolver;
        } else {
            moduleDescriptor2 = moduleDescriptor;
            jvmBackendClassResolverForModuleWithDependencies = new JvmBackendClassResolverForModuleWithDependencies(moduleDescriptor2);
        }
        GenerationState generationState = new GenerationState(project, moduleDescriptor2, configuration, classBuilderFactory2, null, TargetId, str, jvmBackendClassResolverForModuleWithDependencies, false, diagnosticsReporter, null, 1296, null);
        ProgressIndicatorAndCompilationCanceledStatus.checkCanceled();
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        PhaseType phaseType = PhaseType.IrLowering;
        if (perfManager == null) {
            return codegenFactory.invokeLowerings(generationState, backendInput);
        }
        try {
            perfManager.notifyPhaseStarted(phaseType);
            return codegenFactory.invokeLowerings(generationState, backendInput);
        } finally {
            perfManager.notifyPhaseFinished(phaseType);
        }
    }

    public final JvmIrCodegenFactory.BackendInput toBackendInput$org_jetbrains_kotlin_cli_jvm(Fir2IrActualizedResult fir2IrActualizedResult, CompilerConfiguration compilerConfiguration, JvmBackendExtension jvmBackendExtension) {
        fir2IrActualizedResult.getClass();
        compilerConfiguration.getClass();
        IrModuleFragment irModuleFragment = fir2IrActualizedResult.getIrModuleFragment();
        IrBuiltIns irBuiltIns = fir2IrActualizedResult.getIrBuiltIns();
        SymbolTable symbolTable = fir2IrActualizedResult.getSymbolTable();
        List<IrProvider> irProviders = fir2IrActualizedResult.getComponents().getIrProviders();
        JvmGeneratorExtensionsImpl jvmGeneratorExtensionsImpl = new JvmGeneratorExtensionsImpl(compilerConfiguration, false, 2, (DefaultConstructorMarker) null);
        if (jvmBackendExtension == null) {
            jvmBackendExtension = JvmBackendExtension.Default.INSTANCE;
        }
        return new JvmIrCodegenFactory.BackendInput(irModuleFragment, irBuiltIns, symbolTable, irProviders, jvmGeneratorExtensionsImpl, jvmBackendExtension, fir2IrActualizedResult.getPluginContext());
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\rHÆ\u0003JK\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinToJVMBytecodeCompiler$BackendInputForMultiModuleChunk;", Argument.Delimiters.none, "codegenFactory", "Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory;", "backendInput", "Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory$BackendInput;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "firJvmBackendClassResolver", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendClassResolver;", "firJvmBackendExtension", "Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendExtension;", "mainClassFqName", "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory;Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory$BackendInput;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendClassResolver;Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendExtension;Lorg/jetbrains/kotlin/name/FqName;)V", "getCodegenFactory", "()Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory;", "getBackendInput", "()Lorg/jetbrains/kotlin/backend/jvm/JvmIrCodegenFactory$BackendInput;", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getFirJvmBackendClassResolver", "()Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendClassResolver;", "getFirJvmBackendExtension", "()Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendExtension;", "getMainClassFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class BackendInputForMultiModuleChunk {
        private final JvmIrCodegenFactory.BackendInput backendInput;
        private final JvmIrCodegenFactory codegenFactory;
        private final FirJvmBackendClassResolver firJvmBackendClassResolver;
        private final FirJvmBackendExtension firJvmBackendExtension;
        private final FqName mainClassFqName;
        private final ModuleDescriptor moduleDescriptor;

        public BackendInputForMultiModuleChunk(JvmIrCodegenFactory jvmIrCodegenFactory, JvmIrCodegenFactory.BackendInput backendInput, ModuleDescriptor moduleDescriptor, FirJvmBackendClassResolver firJvmBackendClassResolver, FirJvmBackendExtension firJvmBackendExtension, FqName fqName) {
            jvmIrCodegenFactory.getClass();
            backendInput.getClass();
            moduleDescriptor.getClass();
            this.codegenFactory = jvmIrCodegenFactory;
            this.backendInput = backendInput;
            this.moduleDescriptor = moduleDescriptor;
            this.firJvmBackendClassResolver = firJvmBackendClassResolver;
            this.firJvmBackendExtension = firJvmBackendExtension;
            this.mainClassFqName = fqName;
        }

        public static /* synthetic */ BackendInputForMultiModuleChunk copy$default(BackendInputForMultiModuleChunk backendInputForMultiModuleChunk, JvmIrCodegenFactory jvmIrCodegenFactory, JvmIrCodegenFactory.BackendInput backendInput, ModuleDescriptor moduleDescriptor, FirJvmBackendClassResolver firJvmBackendClassResolver, FirJvmBackendExtension firJvmBackendExtension, FqName fqName, int i, Object obj) {
            if ((i & 1) != 0) {
                jvmIrCodegenFactory = backendInputForMultiModuleChunk.codegenFactory;
            }
            if ((i & 2) != 0) {
                backendInput = backendInputForMultiModuleChunk.backendInput;
            }
            if ((i & 4) != 0) {
                moduleDescriptor = backendInputForMultiModuleChunk.moduleDescriptor;
            }
            if ((i & 8) != 0) {
                firJvmBackendClassResolver = backendInputForMultiModuleChunk.firJvmBackendClassResolver;
            }
            if ((i & 16) != 0) {
                firJvmBackendExtension = backendInputForMultiModuleChunk.firJvmBackendExtension;
            }
            if ((i & 32) != 0) {
                fqName = backendInputForMultiModuleChunk.mainClassFqName;
            }
            FirJvmBackendExtension firJvmBackendExtension2 = firJvmBackendExtension;
            FqName fqName2 = fqName;
            return backendInputForMultiModuleChunk.copy(jvmIrCodegenFactory, backendInput, moduleDescriptor, firJvmBackendClassResolver, firJvmBackendExtension2, fqName2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final JvmIrCodegenFactory getCodegenFactory() {
            return this.codegenFactory;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final JvmIrCodegenFactory.BackendInput getBackendInput() {
            return this.backendInput;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final ModuleDescriptor getModuleDescriptor() {
            return this.moduleDescriptor;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final FirJvmBackendClassResolver getFirJvmBackendClassResolver() {
            return this.firJvmBackendClassResolver;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final FirJvmBackendExtension getFirJvmBackendExtension() {
            return this.firJvmBackendExtension;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final FqName getMainClassFqName() {
            return this.mainClassFqName;
        }

        public final BackendInputForMultiModuleChunk copy(JvmIrCodegenFactory codegenFactory, JvmIrCodegenFactory.BackendInput backendInput, ModuleDescriptor moduleDescriptor, FirJvmBackendClassResolver firJvmBackendClassResolver, FirJvmBackendExtension firJvmBackendExtension, FqName mainClassFqName) {
            codegenFactory.getClass();
            backendInput.getClass();
            moduleDescriptor.getClass();
            return new BackendInputForMultiModuleChunk(codegenFactory, backendInput, moduleDescriptor, firJvmBackendClassResolver, firJvmBackendExtension, mainClassFqName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BackendInputForMultiModuleChunk)) {
                return false;
            }
            BackendInputForMultiModuleChunk backendInputForMultiModuleChunk = (BackendInputForMultiModuleChunk) other;
            return Intrinsics.areEqual(this.codegenFactory, backendInputForMultiModuleChunk.codegenFactory) && Intrinsics.areEqual(this.backendInput, backendInputForMultiModuleChunk.backendInput) && Intrinsics.areEqual(this.moduleDescriptor, backendInputForMultiModuleChunk.moduleDescriptor) && Intrinsics.areEqual(this.firJvmBackendClassResolver, backendInputForMultiModuleChunk.firJvmBackendClassResolver) && Intrinsics.areEqual(this.firJvmBackendExtension, backendInputForMultiModuleChunk.firJvmBackendExtension) && Intrinsics.areEqual(this.mainClassFqName, backendInputForMultiModuleChunk.mainClassFqName);
        }

        public final JvmIrCodegenFactory.BackendInput getBackendInput() {
            return this.backendInput;
        }

        public final JvmIrCodegenFactory getCodegenFactory() {
            return this.codegenFactory;
        }

        public final FirJvmBackendClassResolver getFirJvmBackendClassResolver() {
            return this.firJvmBackendClassResolver;
        }

        public final FirJvmBackendExtension getFirJvmBackendExtension() {
            return this.firJvmBackendExtension;
        }

        public final FqName getMainClassFqName() {
            return this.mainClassFqName;
        }

        public final ModuleDescriptor getModuleDescriptor() {
            return this.moduleDescriptor;
        }

        public int hashCode() {
            int iHashCode = ((((this.codegenFactory.hashCode() * 31) + this.backendInput.hashCode()) * 31) + this.moduleDescriptor.hashCode()) * 31;
            FirJvmBackendClassResolver firJvmBackendClassResolver = this.firJvmBackendClassResolver;
            int iHashCode2 = (iHashCode + (firJvmBackendClassResolver == null ? 0 : firJvmBackendClassResolver.hashCode())) * 31;
            FirJvmBackendExtension firJvmBackendExtension = this.firJvmBackendExtension;
            int iHashCode3 = (iHashCode2 + (firJvmBackendExtension == null ? 0 : firJvmBackendExtension.hashCode())) * 31;
            FqName fqName = this.mainClassFqName;
            return iHashCode3 + (fqName != null ? fqName.hashCode() : 0);
        }

        public String toString() {
            return "BackendInputForMultiModuleChunk(codegenFactory=" + this.codegenFactory + ", backendInput=" + this.backendInput + ", moduleDescriptor=" + this.moduleDescriptor + ", firJvmBackendClassResolver=" + this.firJvmBackendClassResolver + ", firJvmBackendExtension=" + this.firJvmBackendExtension + ", mainClassFqName=" + this.mainClassFqName + ')';
        }

        public /* synthetic */ BackendInputForMultiModuleChunk(JvmIrCodegenFactory jvmIrCodegenFactory, JvmIrCodegenFactory.BackendInput backendInput, ModuleDescriptor moduleDescriptor, FirJvmBackendClassResolver firJvmBackendClassResolver, FirJvmBackendExtension firJvmBackendExtension, FqName fqName, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(jvmIrCodegenFactory, backendInput, moduleDescriptor, (i & 8) != 0 ? null : firJvmBackendClassResolver, (i & 16) != 0 ? null : firJvmBackendExtension, (i & 32) != 0 ? null : fqName);
        }
    }
}
