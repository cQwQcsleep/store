package org.jetbrains.kotlin.incremental;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtIoFileSourceFile;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtVirtualFileSourceFile;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.build.SourcesUtilsKt;
import org.jetbrains.kotlin.build.report.BuildReporter;
import org.jetbrains.kotlin.build.report.metrics.BuildPerformanceMetric;
import org.jetbrains.kotlin.build.report.metrics.BuildTimeMetric;
import org.jetbrains.kotlin.cli.CompilerConfigurationCreationKt;
import org.jetbrains.kotlin.cli.common.ArgumentsKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.GroupedKtSources;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.common.config.ContentRootsKt;
import org.jetbrains.kotlin.cli.common.environment.UtilKt;
import org.jetbrains.kotlin.cli.common.fir.FirDiagnosticsCompilerResultsReporterKt;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.GroupingMessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleBuilder;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.JvmArgumentsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CliCompilerUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CoreEnvironmentUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.FirFindMainClassKt;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.JvmCompilerPipelineKt;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.JvmIncrementalCompilerPipelineLightTreeKt;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.ModuleCompilerEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.ModuleCompilerInput;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.ModuleCompilerIrBackendInput;
import org.jetbrains.kotlin.cli.jvm.config.ClassicFrontendSpecificJvmConfigurationKeys;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.plugins.PluginCliParser;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.compiler.plugin.ExtensionPointUtilsKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.diagnostics.impl.DiagnosticsCollectorImpl;
import org.jetbrains.kotlin.fir.backend.jvm.JvmFir2IrExtensions;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.incremental.IncrementalFirJvmCompilerRunner;
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.load.java.JavaClassesTracker;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCompilationComponents;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.modules.TargetId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms;
import org.jetbrains.kotlin.progress.CompilationCanceledException;
import org.jetbrains.kotlin.progress.CompilationCanceledStatus;
import org.jetbrains.kotlin.progress.ProgressIndicatorAndCompilationCanceledStatus;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;
import org.jetbrains.kotlin.utils.KotlinPaths;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J^\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t0\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030!2\u0006\u0010+\u001a\u00020\u0012H\u0014R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0015\u001a\u00020\u0016X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/incremental/IncrementalFirJvmCompilerRunner;", "Lorg/jetbrains/kotlin/incremental/IncrementalJvmCompilerRunner;", "workingDir", "Ljava/io/File;", "reporter", "Lorg/jetbrains/kotlin/build/report/BuildReporter;", "Lorg/jetbrains/kotlin/build/report/metrics/BuildTimeMetric;", "Lorg/jetbrains/kotlin/build/report/metrics/BuildPerformanceMetric;", "outputDirs", Argument.Delimiters.none, "classpathChanges", "Lorg/jetbrains/kotlin/incremental/ClasspathChanges;", "kotlinSourceFilesExtensions", Argument.Delimiters.none, Argument.Delimiters.none, "icFeatures", "Lorg/jetbrains/kotlin/incremental/IncrementalCompilationFeatures;", "generateCompilerRefIndex", Argument.Delimiters.none, "compilationCanceledStatus", "Lorg/jetbrains/kotlin/progress/CompilationCanceledStatus;", "lookupTrackerDelegate", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "<init>", "(Ljava/io/File;Lorg/jetbrains/kotlin/build/report/BuildReporter;Ljava/util/Collection;Lorg/jetbrains/kotlin/incremental/ClasspathChanges;Ljava/util/Set;Lorg/jetbrains/kotlin/incremental/IncrementalCompilationFeatures;ZLorg/jetbrains/kotlin/progress/CompilationCanceledStatus;Lorg/jetbrains/kotlin/incremental/components/LookupTracker;)V", "getCompilationCanceledStatus", "()Lorg/jetbrains/kotlin/progress/CompilationCanceledStatus;", "getLookupTrackerDelegate", "()Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "runCompiler", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "sourcesToCompile", Argument.Delimiters.none, "args", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "caches", "Lorg/jetbrains/kotlin/incremental/IncrementalJvmCachesManager;", "services", "Lorg/jetbrains/kotlin/config/Services;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "allSources", "isIncremental", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class IncrementalFirJvmCompilerRunner extends IncrementalJvmCompilerRunner {
    private final CompilationCanceledStatus compilationCanceledStatus;
    private final LookupTracker lookupTrackerDelegate;

    public /* synthetic */ IncrementalFirJvmCompilerRunner(File file, BuildReporter buildReporter, Collection collection, ClasspathChanges classpathChanges, Set set, IncrementalCompilationFeatures incrementalCompilationFeatures, boolean z, CompilationCanceledStatus compilationCanceledStatus, LookupTracker lookupTracker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, buildReporter, collection, classpathChanges, (i & 16) != 0 ? SourcesUtilsKt.getDEFAULT_KOTLIN_SOURCE_FILES_EXTENSIONS() : set, (i & 32) != 0 ? IncrementalCompilationFeatures.Companion.getDEFAULT_CONFIGURATION() : incrementalCompilationFeatures, (i & 64) != 0 ? false : z, (i & 128) != 0 ? null : compilationCanceledStatus, (i & 256) != 0 ? LookupTracker.DO_NOTHING.INSTANCE : lookupTracker);
    }

    public static Unit o(LinkedHashSet linkedHashSet, LinkedHashSet linkedHashSet2, Map map, VirtualFile virtualFile, boolean z, String str) {
        virtualFile.getClass();
        KtVirtualFileSourceFile ktVirtualFileSourceFile = new KtVirtualFileSourceFile(virtualFile);
        if (z) {
            linkedHashSet.add(ktVirtualFileSourceFile);
        } else {
            linkedHashSet2.add(ktVirtualFileSourceFile);
        }
        if (str != null) {
            Object linkedHashSet3 = map.get(str);
            if (linkedHashSet3 == null) {
                linkedHashSet3 = new LinkedHashSet();
                map.put(str, linkedHashSet3);
            }
            ((Set) linkedHashSet3).add(ktVirtualFileSourceFile);
        }
        return Unit.INSTANCE;
    }

    private static final List<? extends SingleModuleFrontendOutput> runCompiler$firIncrementalCycle(Map<String, Set<KtSourceFile>> map, LinkedHashSet<KtSourceFile> linkedHashSet, LinkedHashSet<KtSourceFile> linkedHashSet2, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, MessageCollector messageCollector, CompilerConfiguration compilerConfiguration, TargetId targetId, DiagnosticsCollectorImpl diagnosticsCollectorImpl, Ref.ObjectRef<AbstractProjectFileSearchScope> objectRef, Ref.ObjectRef<FqName> objectRef2, LinkedHashSet<KtSourceFile> linkedHashSet3, boolean z, IncrementalJvmCachesManager incrementalJvmCachesManager, LinkedHashSet<File> linkedHashSet4, IncrementalFirJvmCompilerRunner incrementalFirJvmCompilerRunner, boolean z2) throws IOException {
        while (true) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
            Iterator<T> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Set set = (Set) entry.getValue();
                LinkedHashSet linkedHashSet5 = new LinkedHashSet();
                for (Object obj : set) {
                    KtSourceFile ktSourceFile = (KtSourceFile) obj;
                    if (linkedHashSet3 == null || !linkedHashSet3.isEmpty()) {
                        Iterator<T> it2 = linkedHashSet3.iterator();
                        while (it2.hasNext()) {
                            if (Intrinsics.areEqual(((KtSourceFile) it2.next()).getPath(), ktSourceFile.getPath())) {
                                linkedHashSet5.add(obj);
                                break;
                            }
                        }
                    }
                }
                linkedHashMap.put(key, linkedHashSet5);
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : linkedHashSet) {
                KtSourceFile ktSourceFile2 = (KtSourceFile) obj2;
                if (linkedHashSet3 == null || !linkedHashSet3.isEmpty()) {
                    Iterator<T> it3 = linkedHashSet3.iterator();
                    while (it3.hasNext()) {
                        if (Intrinsics.areEqual(((KtSourceFile) it3.next()).getPath(), ktSourceFile2.getPath())) {
                            arrayList.add(obj2);
                            break;
                        }
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj3 : linkedHashSet2) {
                KtSourceFile ktSourceFile3 = (KtSourceFile) obj3;
                if (linkedHashSet3 == null || !linkedHashSet3.isEmpty()) {
                    Iterator<T> it4 = linkedHashSet3.iterator();
                    while (it4.hasNext()) {
                        if (Intrinsics.areEqual(((KtSourceFile) it4.next()).getPath(), ktSourceFile3.getPath())) {
                            arrayList2.add(obj3);
                            break;
                        }
                    }
                }
            }
            List<? extends SingleModuleFrontendOutput> listCompileModuleToAnalyzedFirViaLightTreeIncrementally = JvmIncrementalCompilerPipelineLightTreeKt.compileModuleToAnalyzedFirViaLightTreeIncrementally(vfsBasedProjectEnvironment, messageCollector, compilerConfiguration, new ModuleCompilerInput(targetId, new GroupedKtSources(arrayList2, arrayList, linkedHashMap), compilerConfiguration), diagnosticsCollectorImpl, (AbstractProjectFileSearchScope) objectRef.element);
            if (objectRef2.element == null && compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_JAR) != null) {
                objectRef2.element = FirFindMainClassKt.findMainClass(((SingleModuleFrontendOutput) CollectionsKt.last(listCompileModuleToAnalyzedFirViaLightTreeIncrementally)).getFir());
            }
            Iterator<T> it5 = linkedHashSet3.iterator();
            while (it5.hasNext()) {
                String path = ((KtSourceFile) it5.next()).getPath();
                path.getClass();
                linkedHashSet4.add(new File(path));
            }
            if (diagnosticsCollectorImpl.getHasErrors()) {
                FirDiagnosticsCompilerResultsReporterKt.reportToMessageCollector(diagnosticsCollectorImpl, messageCollector, z);
                return null;
            }
            LinkedHashSet linkedHashSet6 = IncrementalFirCacheUtilsKt.collectNewDirtySources-hYvPtPY(listCompileModuleToAnalyzedFirViaLightTreeIncrementally, targetId, compilerConfiguration, incrementalJvmCachesManager, linkedHashSet4, incrementalFirJvmCompilerRunner.getReporter());
            if (!z2 || linkedHashSet6.isEmpty()) {
                return listCompileModuleToAnalyzedFirViaLightTreeIncrementally;
            }
            incrementalJvmCachesManager.getPlatformCache().markDirty(linkedHashSet6);
            AbstractProjectFileSearchScope searchScopeByIoFiles$default = AbstractProjectEnvironment.getSearchScopeByIoFiles$default(vfsBasedProjectEnvironment, incrementalJvmCachesManager.getInputsCache().getOutputForSourceFiles(linkedHashSet6), false, 2, null);
            AbstractProjectFileSearchScope abstractProjectFileSearchScope = (AbstractProjectFileSearchScope) objectRef.element;
            if (searchScopeByIoFiles$default.isEmpty()) {
                searchScopeByIoFiles$default = abstractProjectFileSearchScope;
            } else if (abstractProjectFileSearchScope != null && !abstractProjectFileSearchScope.isEmpty()) {
                searchScopeByIoFiles$default = abstractProjectFileSearchScope.plus(searchScopeByIoFiles$default);
            }
            objectRef.element = searchScopeByIoFiles$default;
            incrementalJvmCachesManager.getInputsCache().removeOutputForSourceFiles(linkedHashSet6);
            Iterator it6 = linkedHashSet6.iterator();
            while (it6.hasNext()) {
                linkedHashSet3.add(new KtIoFileSourceFile((File) it6.next()));
            }
            Iterator<T> it7 = vfsBasedProjectEnvironment.getKnownFileSystems().iterator();
            while (it7.hasNext()) {
                ((VirtualFileSystem) it7.next()).refresh(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BinaryVersion runCompiler$lambda$2$0(int[] iArr) {
        iArr.getClass();
        return new MetadataVersion(Arrays.copyOf(iArr, iArr.length));
    }

    public final CompilationCanceledStatus getCompilationCanceledStatus() {
        return this.compilationCanceledStatus;
    }

    public LookupTracker getLookupTrackerDelegate() {
        return this.lookupTrackerDelegate;
    }

    /* JADX WARN: Code duplicated, block: B:113:0x0394 A[Catch: all -> 0x02dc, TRY_LEAVE, TryCatch #27 {all -> 0x02dc, blocks: (B:72:0x02c5, B:75:0x02cf, B:83:0x02eb, B:85:0x033b, B:111:0x038c, B:113:0x0394), top: B:161:0x02c5 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:130:0x02eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:140:0x02cf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, org.jetbrains.kotlin.config.Services] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2 */
    public Pair<ExitCode, Collection<File>> runCompiler(List<? extends File> sourcesToCompile, K2JVMCompilerArguments args, IncrementalJvmCachesManager caches, Services services, MessageCollector messageCollector, List<? extends File> allSources, boolean isIncremental) throws Throwable {
        CompilerMessageSourceLocation compilerMessageSourceLocation;
        Ref.ObjectRef objectRef;
        boolean renderDiagnosticInternalName;
        List<? extends SingleModuleFrontendOutput> listRunCompiler$firIncrementalCycle;
        GroupingMessageCollector groupingMessageCollector = services;
        String str = "Compilation was canceled";
        sourcesToCompile.getClass();
        args.getClass();
        caches.getClass();
        groupingMessageCollector.getClass();
        messageCollector.getClass();
        allSources.getClass();
        ProgressIndicatorAndCompilationCanceledStatus.setCompilationCanceledStatus(this.compilationCanceledStatus);
        GroupingMessageCollector groupingMessageCollector2 = new GroupingMessageCollector(messageCollector, args.getAllWarningsAsErrors(), args.getReportAllWarnings());
        List listPlus = CollectionsKt.plus(allSources, IncrementalFirJvmCompilerRunnerKt.javaSources(args));
        String moduleName = args.getModuleName();
        if (moduleName == null) {
            moduleName = "main";
        }
        TargetId targetId = new TargetId(moduleName, ModuleXmlParser.TYPE_PRODUCTION);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = sourcesToCompile.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(new KtIoFileSourceFile((File) it.next()));
        }
        String[] commonSources = args.getCommonSources();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (String str2 : commonSources) {
            linkedHashSet2.add(new File(str2));
        }
        ExitCode exitCode = ExitCode.OK;
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        Disposable disposableNewDisposable = Disposer.newDisposable("Disposable for " + Reflection.getOrCreateKotlinClass(IncrementalFirJvmCompilerRunner.class).getSimpleName() + ".runCompiler");
        disposableNewDisposable.getClass();
        try {
            try {
                try {
                    try {
                        try {
                            CompilerConfiguration compilerConfigurationCreate$default = CompilerConfigurationCreationKt.create$default(CompilerConfiguration.INSTANCE, null, null, 3, null);
                            compilerConfigurationCreate$default.put(CLIConfigurationKeys.ORIGINAL_MESSAGE_COLLECTOR_KEY, messageCollector);
                            CommonConfigurationKeysKt.setTargetPlatform(compilerConfigurationCreate$default, JvmPlatforms.INSTANCE.getDefaultJvmPlatform());
                            CommonConfigurationKeysKt.setMessageCollector(compilerConfigurationCreate$default, groupingMessageCollector2);
                            ArgumentsKt.setupCommonArguments(compilerConfigurationCreate$default, args, new Function1() { // from class: km6
                                public final Object invoke(Object obj) {
                                    return IncrementalFirJvmCompilerRunner.runCompiler$lambda$2$0((int[]) obj);
                                }
                            });
                            if (UtilsKt.incrementalCompilationIsEnabled(args)) {
                                try {
                                    try {
                                        compilerConfigurationCreate$default.putIfNotNull(CommonConfigurationKeys.LOOKUP_TRACKER, groupingMessageCollector.get(LookupTracker.class));
                                        compilerConfigurationCreate$default.putIfNotNull(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER, groupingMessageCollector.get(ExpectActualTracker.class));
                                        compilerConfigurationCreate$default.putIfNotNull(CommonConfigurationKeys.INLINE_CONST_TRACKER, groupingMessageCollector.get(InlineConstTracker.class));
                                        compilerConfigurationCreate$default.putIfNotNull(CommonConfigurationKeys.FILE_MAPPING_TRACKER, groupingMessageCollector.get(ICFileMappingTracker.class));
                                        compilerConfigurationCreate$default.putIfNotNull(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS, groupingMessageCollector.get(IncrementalCompilationComponents.class));
                                        compilerConfigurationCreate$default.putIfNotNull(ClassicFrontendSpecificJvmConfigurationKeys.JAVA_CLASSES_TRACKER, groupingMessageCollector.get(JavaClassesTracker.class));
                                    } catch (CompilationCanceledException unused) {
                                        linkedHashSet3 = linkedHashSet3;
                                        compilerMessageSourceLocation = null;
                                        groupingMessageCollector2.report(CompilerMessageSeverity.INFO, str, compilerMessageSourceLocation);
                                        Pair<ExitCode, Collection<File>> pair = TuplesKt.to(ExitCode.OK, linkedHashSet3);
                                        groupingMessageCollector2.flush();
                                        Disposer.dispose(disposableNewDisposable);
                                        return pair;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    groupingMessageCollector = groupingMessageCollector2;
                                }
                            }
                            try {
                                JvmArgumentsKt.setupJvmSpecificArguments(compilerConfigurationCreate$default, args);
                                KotlinPaths kotlinPathsComputeKotlinPaths = ArgumentsKt.computeKotlinPaths(compilerConfigurationCreate$default, args);
                                if (CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE.checkHasErrorsAndReportToMessageCollector(compilerConfigurationCreate$default)) {
                                    Pair<ExitCode, Collection<File>> pair2 = TuplesKt.to(ExitCode.COMPILATION_ERROR, CollectionsKt.emptyList());
                                    groupingMessageCollector2.flush();
                                    Disposer.dispose(disposableNewDisposable);
                                    return pair2;
                                }
                                ExitCode exitCodeLoadPluginsSafe = PluginCliParser.loadPluginsSafe(ArraysKt.toList(args.getPluginClasspaths()), ArraysKt.toMutableList(args.getPluginOptions()), ArraysKt.toList(args.getPluginConfigurations()), ArraysKt.toList(args.getPluginOrderConstraints()), compilerConfigurationCreate$default, disposableNewDisposable);
                                disposableNewDisposable = disposableNewDisposable;
                                try {
                                    try {
                                        if (exitCodeLoadPluginsSafe != exitCode) {
                                            try {
                                                Pair<ExitCode, Collection<File>> pair3 = TuplesKt.to(exitCodeLoadPluginsSafe, CollectionsKt.emptyList());
                                                groupingMessageCollector2.flush();
                                                Disposer.dispose(disposableNewDisposable);
                                                return pair3;
                                            } catch (CompilationCanceledException unused2) {
                                                disposableNewDisposable = disposableNewDisposable;
                                                linkedHashSet3 = linkedHashSet3;
                                                compilerMessageSourceLocation = null;
                                                groupingMessageCollector2.report(CompilerMessageSeverity.INFO, str, compilerMessageSourceLocation);
                                                Pair<ExitCode, Collection<File>> pair4 = TuplesKt.to(ExitCode.OK, linkedHashSet3);
                                                groupingMessageCollector2.flush();
                                                Disposer.dispose(disposableNewDisposable);
                                                return pair4;
                                            }
                                        }
                                        try {
                                            try {
                                                JvmArgumentsKt.configureJavaModulesContentRoots(compilerConfigurationCreate$default, args);
                                                JvmArgumentsKt.configureStandardLibs(compilerConfigurationCreate$default, kotlinPathsComputeKotlinPaths, args);
                                                JvmArgumentsKt.configureAdvancedJvmOptions(compilerConfigurationCreate$default, args);
                                                JvmArgumentsKt.configureKlibPaths(compilerConfigurationCreate$default, args);
                                                JvmContentRootsKt.configureJdkClasspathRoots(compilerConfigurationCreate$default);
                                                JvmArgumentsKt.configureJdkHome(compilerConfigurationCreate$default, args);
                                                String destination = args.getDestination();
                                                if (destination == null) {
                                                    destination = ".";
                                                }
                                                File file = new File(destination);
                                                String path = file.getPath();
                                                path.getClass();
                                                str = "Compilation was canceled";
                                                compilerMessageSourceLocation = null;
                                                try {
                                                    try {
                                                        if (StringsKt.endsWith$default(path, ".jar", false, 2, (Object) null)) {
                                                            try {
                                                                compilerConfigurationCreate$default.put(JVMConfigurationKeys.OUTPUT_JAR, file);
                                                            } catch (CompilationCanceledException unused3) {
                                                                groupingMessageCollector2 = groupingMessageCollector2;
                                                                disposableNewDisposable = disposableNewDisposable;
                                                                linkedHashSet3 = linkedHashSet3;
                                                                str = str;
                                                                groupingMessageCollector2.report(CompilerMessageSeverity.INFO, str, compilerMessageSourceLocation);
                                                                Pair<ExitCode, Collection<File>> pair5 = TuplesKt.to(ExitCode.OK, linkedHashSet3);
                                                                groupingMessageCollector2.flush();
                                                                Disposer.dispose(disposableNewDisposable);
                                                                return pair5;
                                                            }
                                                        } else {
                                                            compilerConfigurationCreate$default.put(JVMConfigurationKeys.OUTPUT_DIRECTORY, file);
                                                        }
                                                        CompilerConfigurationKey<List<Module>> compilerConfigurationKey = JVMConfigurationKeys.MODULES;
                                                        String name = targetId.getName();
                                                        String path2 = file.getPath();
                                                        path2.getClass();
                                                        compilerConfigurationCreate$default.addAll(compilerConfigurationKey, CollectionsKt.listOf(new ModuleBuilder(name, path2, targetId.getType())));
                                                        IncrementalFirJvmCompilerRunnerKt.configureBaseRoots(compilerConfigurationCreate$default, args);
                                                        IncrementalFirJvmCompilerRunnerKt.configureSourceRootsFromSources(compilerConfigurationCreate$default, listPlus, linkedHashSet2, args.getJavaPackagePrefix());
                                                        UtilKt.setIdeaIoUseFallback();
                                                        VfsBasedProjectEnvironment vfsBasedProjectEnvironmentCreateProjectEnvironment = JvmCompilerPipelineKt.createProjectEnvironment(compilerConfigurationCreate$default, disposableNewDisposable, EnvironmentConfigFiles.JVM_CONFIG_FILES);
                                                        try {
                                                            final LinkedHashSet linkedHashSet4 = new LinkedHashSet();
                                                            final LinkedHashSet linkedHashSet5 = new LinkedHashSet();
                                                            final LinkedHashMap linkedHashMap = new LinkedHashMap();
                                                            CoreEnvironmentUtilsKt.forAllFiles$default(ContentRootsKt.getKotlinSourceRoots(compilerConfigurationCreate$default), compilerConfigurationCreate$default, vfsBasedProjectEnvironmentCreateProjectEnvironment.getProject(), null, new Function3() { // from class: lm6
                                                                public final Object invoke(Object obj, Object obj2, Object obj3) {
                                                                    return IncrementalFirJvmCompilerRunner.o(linkedHashSet5, linkedHashSet4, linkedHashMap, (VirtualFile) obj, ((Boolean) obj2).booleanValue(), (String) obj3);
                                                                }
                                                            }, 4, null);
                                                            DiagnosticsCollectorImpl diagnosticsCollectorImpl = new DiagnosticsCollectorImpl();
                                                            PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(compilerConfigurationCreate$default);
                                                            ModuleCompilerEnvironment moduleCompilerEnvironment = new ModuleCompilerEnvironment(vfsBasedProjectEnvironmentCreateProjectEnvironment, diagnosticsCollectorImpl);
                                                            if (perfManager == null) {
                                                                objectRef = new Ref.ObjectRef();
                                                                disposableNewDisposable = disposableNewDisposable;
                                                                renderDiagnosticInternalName = CLIConfigurationKeysKt.getRenderDiagnosticInternalName(compilerConfigurationCreate$default);
                                                                groupingMessageCollector2 = groupingMessageCollector2;
                                                                listRunCompiler$firIncrementalCycle = runCompiler$firIncrementalCycle(linkedHashMap, linkedHashSet5, linkedHashSet4, vfsBasedProjectEnvironmentCreateProjectEnvironment, messageCollector, compilerConfigurationCreate$default, targetId, diagnosticsCollectorImpl, new Ref.ObjectRef(), objectRef, linkedHashSet, renderDiagnosticInternalName, caches, linkedHashSet3, this, isIncremental);
                                                                linkedHashSet3 = linkedHashSet3;
                                                                if (listRunCompiler$firIncrementalCycle == null) {
                                                                    Pair<ExitCode, Collection<File>> pair6 = TuplesKt.to(ExitCode.COMPILATION_ERROR, linkedHashSet3);
                                                                    groupingMessageCollector2.flush();
                                                                    Disposer.dispose(disposableNewDisposable);
                                                                    return pair6;
                                                                }
                                                                JvmFir2IrExtensions jvmFir2IrExtensions = new JvmFir2IrExtensions(compilerConfigurationCreate$default);
                                                                Fir2IrActualizedResult fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw = JvmCompilerPipelineKt.m23convertToIrAndActualizeForJvm_I1IRw(listRunCompiler$firIncrementalCycle, jvmFir2IrExtensions, compilerConfigurationCreate$default, moduleCompilerEnvironment.getDiagnosticsReporter(), ExtensionPointUtilsKt.getCompilerExtensions(compilerConfigurationCreate$default, IrGenerationExtension.Companion));
                                                                GenerationState generationStateGenerateCodeFromIr = JvmCompilerPipelineKt.generateCodeFromIr(new ModuleCompilerIrBackendInput(targetId, compilerConfigurationCreate$default, jvmFir2IrExtensions, fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getIrModuleFragment(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getComponents(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getPluginContext(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getIrActualizedResult(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getSymbolTable()), moduleCompilerEnvironment);
                                                                FirDiagnosticsCompilerResultsReporterKt.reportToMessageCollector(diagnosticsCollectorImpl, messageCollector, renderDiagnosticInternalName);
                                                                str = str;
                                                                CliCompilerUtilsKt.writeOutputsIfNeeded(vfsBasedProjectEnvironmentCreateProjectEnvironment.getProject(), compilerConfigurationCreate$default, messageCollector, false, CollectionsKt.listOf(generationStateGenerateCodeFromIr), (FqName) objectRef.element);
                                                                groupingMessageCollector2.flush();
                                                                Disposer.dispose(disposableNewDisposable);
                                                                return TuplesKt.to(exitCode, linkedHashSet3);
                                                            }
                                                            try {
                                                                StringBuilder sb = new StringBuilder();
                                                                try {
                                                                    sb.append(targetId.getName());
                                                                    sb.append('-');
                                                                    sb.append(targetId.getType());
                                                                    perfManager.setTargetDescription(sb.toString());
                                                                    perfManager.notifyPhaseFinished(PhaseType.Initialization);
                                                                    try {
                                                                        objectRef = new Ref.ObjectRef();
                                                                        disposableNewDisposable = disposableNewDisposable;
                                                                        try {
                                                                            renderDiagnosticInternalName = CLIConfigurationKeysKt.getRenderDiagnosticInternalName(compilerConfigurationCreate$default);
                                                                            groupingMessageCollector2 = groupingMessageCollector2;
                                                                            try {
                                                                                try {
                                                                                    listRunCompiler$firIncrementalCycle = runCompiler$firIncrementalCycle(linkedHashMap, linkedHashSet5, linkedHashSet4, vfsBasedProjectEnvironmentCreateProjectEnvironment, messageCollector, compilerConfigurationCreate$default, targetId, diagnosticsCollectorImpl, new Ref.ObjectRef(), objectRef, linkedHashSet, renderDiagnosticInternalName, caches, linkedHashSet3, this, isIncremental);
                                                                                    linkedHashSet3 = linkedHashSet3;
                                                                                    try {
                                                                                        if (listRunCompiler$firIncrementalCycle == null) {
                                                                                            try {
                                                                                                Pair<ExitCode, Collection<File>> pair7 = TuplesKt.to(ExitCode.COMPILATION_ERROR, linkedHashSet3);
                                                                                                groupingMessageCollector2.flush();
                                                                                                Disposer.dispose(disposableNewDisposable);
                                                                                                return pair7;
                                                                                            } catch (CompilationCanceledException unused4) {
                                                                                                str = str;
                                                                                                compilerMessageSourceLocation = null;
                                                                                                groupingMessageCollector2 = groupingMessageCollector2;
                                                                                            }
                                                                                        } else {
                                                                                            try {
                                                                                                JvmFir2IrExtensions jvmFir2IrExtensions2 = new JvmFir2IrExtensions(compilerConfigurationCreate$default);
                                                                                                Fir2IrActualizedResult fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw2 = JvmCompilerPipelineKt.m23convertToIrAndActualizeForJvm_I1IRw(listRunCompiler$firIncrementalCycle, jvmFir2IrExtensions2, compilerConfigurationCreate$default, moduleCompilerEnvironment.getDiagnosticsReporter(), ExtensionPointUtilsKt.getCompilerExtensions(compilerConfigurationCreate$default, IrGenerationExtension.Companion));
                                                                                                GenerationState generationStateGenerateCodeFromIr2 = JvmCompilerPipelineKt.generateCodeFromIr(new ModuleCompilerIrBackendInput(targetId, compilerConfigurationCreate$default, jvmFir2IrExtensions2, fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw2.getIrModuleFragment(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw2.getComponents(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw2.getPluginContext(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw2.getIrActualizedResult(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw2.getSymbolTable()), moduleCompilerEnvironment);
                                                                                                FirDiagnosticsCompilerResultsReporterKt.reportToMessageCollector(diagnosticsCollectorImpl, messageCollector, renderDiagnosticInternalName);
                                                                                                str = str;
                                                                                                try {
                                                                                                    CliCompilerUtilsKt.writeOutputsIfNeeded(vfsBasedProjectEnvironmentCreateProjectEnvironment.getProject(), compilerConfigurationCreate$default, messageCollector, false, CollectionsKt.listOf(generationStateGenerateCodeFromIr2), (FqName) objectRef.element);
                                                                                                    groupingMessageCollector2.flush();
                                                                                                    Disposer.dispose(disposableNewDisposable);
                                                                                                    return TuplesKt.to(exitCode, linkedHashSet3);
                                                                                                } catch (RuntimeException e) {
                                                                                                    e = e;
                                                                                                    if (!(e.getCause() instanceof CompilationCanceledException)) {
                                                                                                        throw e;
                                                                                                    }
                                                                                                    GroupingMessageCollector groupingMessageCollector3 = groupingMessageCollector2;
                                                                                                    groupingMessageCollector3.report(CompilerMessageSeverity.INFO, str, null);
                                                                                                    Pair<ExitCode, Collection<File>> pair8 = TuplesKt.to(ExitCode.OK, linkedHashSet3);
                                                                                                    groupingMessageCollector3.flush();
                                                                                                    Disposer.dispose(disposableNewDisposable);
                                                                                                    return pair8;
                                                                                                } catch (CompilationCanceledException unused5) {
                                                                                                    groupingMessageCollector2 = groupingMessageCollector2;
                                                                                                    compilerMessageSourceLocation = null;
                                                                                                    groupingMessageCollector2.report(CompilerMessageSeverity.INFO, str, compilerMessageSourceLocation);
                                                                                                    Pair<ExitCode, Collection<File>> pair9 = TuplesKt.to(ExitCode.OK, linkedHashSet3);
                                                                                                    groupingMessageCollector2.flush();
                                                                                                    Disposer.dispose(disposableNewDisposable);
                                                                                                    return pair9;
                                                                                                }
                                                                                            } catch (CompilationCanceledException unused6) {
                                                                                                str = str;
                                                                                            }
                                                                                        }
                                                                                    } catch (RuntimeException e2) {
                                                                                        e = e2;
                                                                                        str = str;
                                                                                    }
                                                                                } catch (Throwable th2) {
                                                                                    th = th2;
                                                                                }
                                                                            } catch (RuntimeException e3) {
                                                                                e = e3;
                                                                                str = str;
                                                                                linkedHashSet3 = linkedHashSet3;
                                                                                if (!(e.getCause() instanceof CompilationCanceledException)) {
                                                                                    throw e;
                                                                                }
                                                                                GroupingMessageCollector groupingMessageCollector4 = groupingMessageCollector2;
                                                                                groupingMessageCollector4.report(CompilerMessageSeverity.INFO, str, null);
                                                                                Pair<ExitCode, Collection<File>> pair10 = TuplesKt.to(ExitCode.OK, linkedHashSet3);
                                                                                groupingMessageCollector4.flush();
                                                                                Disposer.dispose(disposableNewDisposable);
                                                                                return pair10;
                                                                            } catch (CompilationCanceledException unused7) {
                                                                                str = str;
                                                                                linkedHashSet3 = linkedHashSet3;
                                                                            }
                                                                        } catch (RuntimeException e4) {
                                                                            e = e4;
                                                                            linkedHashSet3 = linkedHashSet3;
                                                                            str = str;
                                                                            if (!(e.getCause() instanceof CompilationCanceledException)) {
                                                                                throw e;
                                                                            }
                                                                            GroupingMessageCollector groupingMessageCollector5 = groupingMessageCollector2;
                                                                            groupingMessageCollector5.report(CompilerMessageSeverity.INFO, str, null);
                                                                            Pair<ExitCode, Collection<File>> pair11 = TuplesKt.to(ExitCode.OK, linkedHashSet3);
                                                                            groupingMessageCollector5.flush();
                                                                            Disposer.dispose(disposableNewDisposable);
                                                                            return pair11;
                                                                        } catch (CompilationCanceledException unused8) {
                                                                            str = str;
                                                                            groupingMessageCollector2 = groupingMessageCollector2;
                                                                        }
                                                                    } catch (CompilationCanceledException unused9) {
                                                                        disposableNewDisposable = disposableNewDisposable;
                                                                    }
                                                                } catch (CompilationCanceledException unused10) {
                                                                    groupingMessageCollector2 = groupingMessageCollector2;
                                                                    disposableNewDisposable = disposableNewDisposable;
                                                                    linkedHashSet3 = linkedHashSet3;
                                                                    str = str;
                                                                }
                                                            } catch (CompilationCanceledException unused11) {
                                                                compilerMessageSourceLocation = null;
                                                                groupingMessageCollector2 = groupingMessageCollector2;
                                                                disposableNewDisposable = disposableNewDisposable;
                                                                linkedHashSet3 = linkedHashSet3;
                                                                str = str;
                                                            }
                                                        } catch (CompilationCanceledException unused12) {
                                                            compilerMessageSourceLocation = null;
                                                            groupingMessageCollector2 = groupingMessageCollector2;
                                                        }
                                                    } catch (RuntimeException e5) {
                                                        e = e5;
                                                        disposableNewDisposable = disposableNewDisposable;
                                                    }
                                                } catch (CompilationCanceledException unused13) {
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                                groupingMessageCollector2 = groupingMessageCollector2;
                                                disposableNewDisposable = disposableNewDisposable;
                                            }
                                        } catch (CompilationCanceledException unused14) {
                                            disposableNewDisposable = disposableNewDisposable;
                                        }
                                        groupingMessageCollector2.report(CompilerMessageSeverity.INFO, str, compilerMessageSourceLocation);
                                        Pair<ExitCode, Collection<File>> pair12 = TuplesKt.to(ExitCode.OK, linkedHashSet3);
                                        groupingMessageCollector2.flush();
                                        Disposer.dispose(disposableNewDisposable);
                                        return pair12;
                                    } catch (RuntimeException e6) {
                                        e = e6;
                                        str = "Compilation was canceled";
                                        groupingMessageCollector2 = groupingMessageCollector2;
                                        disposableNewDisposable = disposableNewDisposable;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    groupingMessageCollector = groupingMessageCollector2;
                                    disposableNewDisposable = disposableNewDisposable;
                                }
                                groupingMessageCollector = groupingMessageCollector2;
                                groupingMessageCollector.flush();
                                Disposer.dispose(disposableNewDisposable);
                                throw th;
                            } catch (CompilationCanceledException unused15) {
                            }
                            groupingMessageCollector2 = groupingMessageCollector2;
                            compilerMessageSourceLocation = null;
                        } catch (CompilationCanceledException unused16) {
                            str = "Compilation was canceled";
                            linkedHashSet3 = linkedHashSet3;
                            groupingMessageCollector2 = groupingMessageCollector2;
                            compilerMessageSourceLocation = null;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (RuntimeException e7) {
                    e = e7;
                    str = "Compilation was canceled";
                    groupingMessageCollector2 = groupingMessageCollector2;
                }
            } catch (CompilationCanceledException unused17) {
                str = "Compilation was canceled";
                groupingMessageCollector2 = groupingMessageCollector2;
                compilerMessageSourceLocation = null;
                linkedHashSet3 = linkedHashSet3;
            }
        } catch (Throwable th6) {
            th = th6;
            groupingMessageCollector2 = groupingMessageCollector2;
        }
        groupingMessageCollector2.report(CompilerMessageSeverity.INFO, str, compilerMessageSourceLocation);
        Pair<ExitCode, Collection<File>> pair13 = TuplesKt.to(ExitCode.OK, linkedHashSet3);
        groupingMessageCollector2.flush();
        Disposer.dispose(disposableNewDisposable);
        return pair13;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IncrementalFirJvmCompilerRunner(File file, BuildReporter<BuildTimeMetric, BuildPerformanceMetric> buildReporter, Collection<? extends File> collection, ClasspathChanges classpathChanges, Set<String> set, IncrementalCompilationFeatures incrementalCompilationFeatures, boolean z, CompilationCanceledStatus compilationCanceledStatus, LookupTracker lookupTracker) {
        super(file, buildReporter, collection, classpathChanges, set, incrementalCompilationFeatures, z, compilationCanceledStatus, (LookupTracker) null, 256, (DefaultConstructorMarker) null);
        file.getClass();
        buildReporter.getClass();
        classpathChanges.getClass();
        set.getClass();
        incrementalCompilationFeatures.getClass();
        lookupTracker.getClass();
        this.compilationCanceledStatus = compilationCanceledStatus;
        this.lookupTrackerDelegate = lookupTracker;
    }

    public /* bridge */ /* synthetic */ Pair runCompiler(List list, CommonCompilerArguments commonCompilerArguments, IncrementalCachesManager incrementalCachesManager, Services services, MessageCollector messageCollector, List list2, boolean z) {
        return runCompiler((List<? extends File>) list, (K2JVMCompilerArguments) commonCompilerArguments, (IncrementalJvmCachesManager) incrementalCachesManager, services, messageCollector, (List<? extends File>) list2, z);
    }
}
