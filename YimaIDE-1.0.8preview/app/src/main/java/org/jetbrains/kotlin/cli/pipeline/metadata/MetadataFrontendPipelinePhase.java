package org.jetbrains.kotlin.cli.pipeline.metadata;

import com.intellij.openapi.Disposable;
import com.intellij.psi.PsiElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtPsiSourceFile;
import org.jetbrains.kotlin.backend.common.LoadMetadataKlibsKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.FirSessionConstructionUtilsKt;
import org.jetbrains.kotlin.cli.common.GroupedKtSources;
import org.jetbrains.kotlin.cli.common.GroupedKtSourcesKt;
import org.jetbrains.kotlin.cli.common.SessionWithSources;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.fir.FirDiagnosticsCompilerResultsReporter;
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.IncrementalCompilationContextUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironmentKt;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.config.K2MetadataConfigurationKeys;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.jvm.JvmPipelineUtilsKt;
import org.jetbrains.kotlin.cli.pipeline.metadata.MetadataFrontendPipelinePhase;
import org.jetbrains.kotlin.compiler.plugin.FirExtensionRegistrarConfigurationUtilKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.DependencyListForCliModule;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.ConvertToIrKt;
import org.jetbrains.kotlin.fir.pipeline.FirUtilsKt;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.session.IncrementalCompilationContext;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataFrontendPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataFrontendPipelineArtifact;", "<init>", "()V", "executePhase", "input", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataFrontendPipelinePhase extends PipelinePhase<ConfigurationPipelineArtifact, MetadataFrontendPipelineArtifact> {
    public static final MetadataFrontendPipelinePhase INSTANCE = new MetadataFrontendPipelinePhase();

    private MetadataFrontendPipelinePhase() {
        super("MetadataFrontendPipelinePhase", null, SetsKt.setOf(new Function3[]{PerformanceNotifications.AnalysisFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}), 2, null);
    }

    public static IncrementalCompilationContext a(IncrementalCompilationContext incrementalCompilationContext, List list) {
        list.getClass();
        return incrementalCompilationContext;
    }

    public static IncrementalCompilationContext c(CompilerConfiguration compilerConfiguration, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, AbstractProjectFileSearchScope abstractProjectFileSearchScope, List list) {
        list.getClass();
        return IncrementalCompilationContextUtilsKt.createContextForIncrementalCompilation(compilerConfiguration, vfsBasedProjectEnvironment, AbstractProjectEnvironment.getSearchScopeBySourceFiles$default(vfsBasedProjectEnvironment, list, false, 2, null), CollectionsKt.emptyList(), abstractProjectFileSearchScope);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit executePhase$lambda$7$0(PerformanceManager performanceManager, int i, int i2) {
        if (performanceManager != null) {
            performanceManager.addSourcesStats(i, i2);
        }
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public MetadataFrontendPipelineArtifact executePhase(ConfigurationPipelineArtifact input) throws IOException {
        List listEmptyList;
        Ref.ObjectRef objectRef;
        ArrayList arrayList;
        AbstractProjectFileSearchScope precompiledBinariesFileScope;
        File file;
        input.getClass();
        final CompilerConfiguration configuration = input.getConfiguration();
        Disposable rootDisposable = input.getRootDisposable();
        BaseDiagnosticsCollector diagnosticsCollector = CLIConfigurationKeysKt.getDiagnosticsCollector(configuration);
        MessageCollector messageCollector = CommonConfigurationKeysKt.getMessageCollector(configuration);
        StringBuilder sb = new StringBuilder("<");
        String moduleName = CommonConfigurationKeysKt.getModuleName(configuration);
        moduleName.getClass();
        sb.append(moduleName);
        sb.append('>');
        Name nameSpecial = Name.special(sb.toString());
        nameSpecial.getClass();
        boolean z = configuration.getBoolean(CommonConfigurationKeys.USE_LIGHT_TREE);
        DependencyListForCliModule.Companion companion = DependencyListForCliModule.INSTANCE;
        DependencyListForCliModule.Builder builder = new DependencyListForCliModule.Builder();
        DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule builderForDefaultDependenciesModule = new DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule(builder, builder.createData("<regular dependencies of " + nameSpecial + '>'), builder.createData("<dependsOn dependencies of " + nameSpecial + '>'), builder.createData("<friends dependencies of " + nameSpecial + '>'));
        List list = (List) configuration.get(K2MetadataConfigurationKeys.INSTANCE.getREFINES_PATHS());
        if (list != null) {
            List list2 = list;
            listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                listEmptyList.add(new File((String) it.next()));
            }
        } else {
            listEmptyList = null;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List<File> jvmClasspathRoots = JvmContentRootsKt.getJvmClasspathRoots(configuration);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : jvmClasspathRoots) {
            if (!listEmptyList.contains((File) obj)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(((File) it2.next()).getPath());
        }
        builderForDefaultDependenciesModule.dependencies(arrayList3);
        List<File> jvmModularRoots = JvmContentRootsKt.getJvmModularRoots(configuration);
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(jvmModularRoots, 10));
        Iterator<T> it3 = jvmModularRoots.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((File) it3.next()).getPath());
        }
        builderForDefaultDependenciesModule.dependencies(arrayList4);
        List listEmptyList2 = (List) configuration.get(K2MetadataConfigurationKeys.INSTANCE.getFRIEND_PATHS());
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        builderForDefaultDependenciesModule.friendDependencies(listEmptyList2);
        List list3 = listEmptyList;
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        Iterator it4 = list3.iterator();
        while (it4.hasNext()) {
            arrayList5.add(((File) it4.next()).getPath());
        }
        builderForDefaultDependenciesModule.dependsOnDependencies(arrayList5);
        DependencyListForCliModule dependencyListForCliModuleBuild = builder.build();
        List<ContentRoot> contentRoots = CLIConfigurationKeysKt.getContentRoots(configuration);
        ArrayList arrayList6 = new ArrayList();
        for (ContentRoot contentRoot : contentRoots) {
            JvmClasspathRoot jvmClasspathRoot = contentRoot instanceof JvmClasspathRoot ? (JvmClasspathRoot) contentRoot : null;
            String path = (jvmClasspathRoot == null || (file = jvmClasspathRoot.getFile()) == null) ? null : file.getPath();
            if (path != null) {
                arrayList6.add(path);
            }
        }
        List all = LoadMetadataKlibsKt.loadMetadataKlibs(arrayList6, configuration).getAll();
        final PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        KotlinCoreEnvironment kotlinCoreEnvironmentCreateForProduction = KotlinCoreEnvironment.INSTANCE.createForProduction(rootDisposable, configuration, EnvironmentConfigFiles.METADATA_CONFIG_FILES);
        if (perfManager != null) {
            perfManager.notifyCurrentPhaseFinishedIfNeeded();
            perfManager.notifyPhaseStarted(PhaseType.Analysis);
        }
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        List<FirExtensionRegistrar> compilerExtensions = FirExtensionRegistrarConfigurationUtilKt.getCompilerExtensions(configuration, FirExtensionRegistrar.INSTANCE);
        if (z) {
            final VfsBasedProjectEnvironment vfsBasedProjectEnvironment = VfsBasedProjectEnvironmentKt.toVfsBasedProjectEnvironment(kotlinCoreEnvironmentCreateForProduction);
            AbstractProjectFileSearchScope searchScopeForProjectLibraries = vfsBasedProjectEnvironment.getSearchScopeForProjectLibraries();
            GroupedKtSources groupedKtSourcesCollectSources = GroupedKtSourcesKt.collectSources(configuration, vfsBasedProjectEnvironment);
            List list4 = CollectionsKt.toList(CollectionsKt.plus(groupedKtSourcesCollectSources.getCommonSources(), groupedKtSourcesCollectSources.getPlatformSources()));
            objectRef2.element = list4;
            final AbstractProjectFileSearchScope abstractProjectFileSearchScopeCreateIncrementalCompilationScope = IncrementalCompilationContextUtilsKt.createIncrementalCompilationScope(configuration, vfsBasedProjectEnvironment, null);
            if (abstractProjectFileSearchScopeCreateIncrementalCompilationScope != null) {
                searchScopeForProjectLibraries = searchScopeForProjectLibraries.minus(abstractProjectFileSearchScopeCreateIncrementalCompilationScope);
            } else {
                abstractProjectFileSearchScopeCreateIncrementalCompilationScope = null;
            }
            objectRef = objectRef2;
            List<SessionWithSources> listPrepareMetadataSessions = FirSessionConstructionUtilsKt.prepareMetadataSessions(list4, configuration, vfsBasedProjectEnvironment, nameSpecial, compilerExtensions, searchScopeForProjectLibraries, dependencyListForCliModuleBuild, all, FirSessionConstructionUtilsKt.isCommonSourceForLt(groupedKtSourcesCollectSources), FirSessionConstructionUtilsKt.getFileBelongsToModuleForLt(groupedKtSourcesCollectSources), new Function1() { // from class: c0a
                public final Object invoke(Object obj2) {
                    return MetadataFrontendPipelinePhase.c(configuration, vfsBasedProjectEnvironment, abstractProjectFileSearchScopeCreateIncrementalCompilationScope, (List) obj2);
                }
            });
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPrepareMetadataSessions, 10));
            for (SessionWithSources sessionWithSources : listPrepareMetadataSessions) {
                FirSession session = sessionWithSources.getSession();
                arrayList.add(FirUtilsKt.resolveAndCheckFir(session, FirUtilsKt.buildFirViaLightTree(session, sessionWithSources.component2(), diagnosticsCollector, new Function2() { // from class: d0a
                    public final Object invoke(Object obj2, Object obj3) {
                        return MetadataFrontendPipelinePhase.executePhase$lambda$7$0(perfManager, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
                    }
                }), diagnosticsCollector));
            }
        } else {
            objectRef = objectRef2;
            VfsBasedProjectEnvironment vfsBasedProjectEnvironment2 = VfsBasedProjectEnvironmentKt.toVfsBasedProjectEnvironment(kotlinCoreEnvironmentCreateForProduction);
            AbstractProjectFileSearchScope searchScopeForProjectLibraries2 = vfsBasedProjectEnvironment2.getSearchScopeForProjectLibraries();
            List<KtFile> sourceFiles = kotlinCoreEnvironmentCreateForProduction.getSourceFiles();
            if (perfManager != null) {
                perfManager.addSourcesStats(sourceFiles.size(), kotlinCoreEnvironmentCreateForProduction.countLinesOfCode(sourceFiles));
            }
            List<KtFile> list5 = sourceFiles;
            ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list5, 10));
            Iterator<T> it5 = list5.iterator();
            while (it5.hasNext()) {
                arrayList7.add(new KtPsiSourceFile((KtFile) it5.next()));
            }
            objectRef.element = arrayList7;
            Iterator<KtFile> it6 = sourceFiles.iterator();
            while (it6.hasNext()) {
                AnalyzerWithCompilerReport.INSTANCE.reportSyntaxErrors((PsiElement) it6.next(), diagnosticsCollector);
            }
            final IncrementalCompilationContext incrementalCompilationContextCreateContextForIncrementalCompilation = IncrementalCompilationContextUtilsKt.createContextForIncrementalCompilation(vfsBasedProjectEnvironment2, configuration, vfsBasedProjectEnvironment2.getSearchScopeByPsiFiles(sourceFiles).plus(vfsBasedProjectEnvironment2.getSearchScopeForProjectJavaSources()));
            if (incrementalCompilationContextCreateContextForIncrementalCompilation != null && (precompiledBinariesFileScope = incrementalCompilationContextCreateContextForIncrementalCompilation.getPrecompiledBinariesFileScope()) != null) {
                searchScopeForProjectLibraries2 = searchScopeForProjectLibraries2.minus(precompiledBinariesFileScope);
            }
            List<SessionWithSources> listPrepareMetadataSessions2 = FirSessionConstructionUtilsKt.prepareMetadataSessions(sourceFiles, configuration, vfsBasedProjectEnvironment2, nameSpecial, compilerExtensions, searchScopeForProjectLibraries2, dependencyListForCliModuleBuild, all, FirSessionConstructionUtilsKt.isCommonSourceForPsi(), FirSessionConstructionUtilsKt.getFileBelongsToModuleForPsi(), new Function1() { // from class: e0a
                public final Object invoke(Object obj2) {
                    return MetadataFrontendPipelinePhase.a(incrementalCompilationContextCreateContextForIncrementalCompilation, (List) obj2);
                }
            });
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPrepareMetadataSessions2, 10));
            for (SessionWithSources sessionWithSources2 : listPrepareMetadataSessions2) {
                FirSession session2 = sessionWithSources2.getSession();
                arrayList.add(FirUtilsKt.resolveAndCheckFir(session2, FirUtilsKt.buildFirFromKtFiles(session2, sessionWithSources2.component2()), diagnosticsCollector));
            }
        }
        ConvertToIrKt.runPlatformCheckers(arrayList, diagnosticsCollector);
        boolean useLightTree = CommonConfigurationKeysKt.getUseLightTree(configuration);
        if (!useLightTree) {
            if (useLightTree) {
                bu8.a();
                return null;
            }
            UtilsKt.checkKotlinPackageUsageForPsi(configuration, JvmPipelineUtilsKt.asKtFilesList((List) objectRef.element));
        } else if (!arrayList.isEmpty()) {
            Iterator it7 = arrayList.iterator();
            while (it7.hasNext() && UtilsKt.checkKotlinPackageUsageForLightTree(configuration, ((SingleModuleFrontendOutput) it7.next()).getFir())) {
            }
        }
        FirDiagnosticsCompilerResultsReporter.INSTANCE.reportToMessageCollector(diagnosticsCollector, messageCollector, CLIConfigurationKeysKt.getRenderDiagnosticInternalName(configuration));
        return new MetadataFrontendPipelineArtifact(AllModulesFrontendOutput.m573constructorimpl(arrayList), configuration, (List) objectRef.element, null);
    }
}
