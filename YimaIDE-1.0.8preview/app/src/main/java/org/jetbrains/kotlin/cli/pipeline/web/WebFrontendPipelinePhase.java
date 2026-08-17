package org.jetbrains.kotlin.cli.pipeline.web;

import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.FrontendConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.FirSessionConstructionUtilsKt;
import org.jetbrains.kotlin.cli.common.GroupedKtSources;
import org.jetbrains.kotlin.cli.common.GroupedKtSourcesKt;
import org.jetbrains.kotlin.cli.common.SessionWithSources;
import org.jetbrains.kotlin.cli.common.UtilsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.js.HelpersKt;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironmentKt;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationPipelineArtifact;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.cli.pipeline.web.WebFrontendPipelinePhase;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.DependencyListForCliModule;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.ConvertToIrKt;
import org.jetbrains.kotlin.fir.pipeline.FirUtilsKt;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.session.KlibIcData;
import org.jetbrains.kotlin.incremental.js.IncrementalDataProvider;
import org.jetbrains.kotlin.ir.backend.js.LoadWebKlibsKt;
import org.jetbrains.kotlin.ir.backend.js.MainModule;
import org.jetbrains.kotlin.ir.backend.js.ModulesStructure;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016Jc\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJu\u0010\u001c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¢\u0006\u0004\b#\u0010$J³\u0001\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\r\"\u0004\b\u0000\u0010'2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H'0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0014\b\b\u0010)\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020\u00190*2\u001a\b\b\u0010+\u001a\u0014\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00190,2\u001e\u0010-\u001a\u001a\u0012\u0004\u0012\u00020.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0\r\u0012\u0004\u0012\u00020&0,2\u0006\u0010\u0018\u001a\u00020\u0019H\u0082\b¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/WebFrontendPipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/ConfigurationPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/web/WebFrontendPipelineArtifact;", "<init>", "()V", "executePhase", "input", "compileModuleToAnalyzedFirWithPsi", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "moduleStructure", "Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;", "ktFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "libraries", Argument.Delimiters.none, "friendLibraries", "diagnosticsReporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "incrementalDataProvider", "Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;", "extensionStorage", "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "useWasmPlatform", Argument.Delimiters.none, "compileModuleToAnalyzedFirWithPsi-XGruv9M", "(Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;Z)Ljava/util/List;", "compileModulesToAnalyzedFirWithLightTree", "groupedSources", "Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;", "ktSourceFiles", "Lorg/jetbrains/kotlin/KtSourceFile;", "performanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "compileModulesToAnalyzedFirWithLightTree-XRluBCg", "(Lorg/jetbrains/kotlin/ir/backend/js/ModulesStructure;Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;Lorg/jetbrains/kotlin/util/PerformanceManager;Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;Z)Ljava/util/List;", "compileModuleToAnalyzedFir", "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "F", "files", "isCommonSource", "Lkotlin/Function1;", "fileBelongsToModule", "Lkotlin/Function2;", "buildResolveAndCheckFir", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WebFrontendPipelinePhase extends PipelinePhase<ConfigurationPipelineArtifact, WebFrontendPipelineArtifact> {
    public static final WebFrontendPipelinePhase INSTANCE = new WebFrontendPipelinePhase();

    private WebFrontendPipelinePhase() {
        super("JsFrontendPipelinePhase", null, SetsKt.setOf(new Function3[]{PerformanceNotifications.AnalysisFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}), 2, null);
    }

    public static boolean a(GroupedKtSources groupedKtSources, KtSourceFile ktSourceFile) {
        ktSourceFile.getClass();
        return ((Boolean) FirSessionConstructionUtilsKt.isCommonSourceForLt(groupedKtSources).invoke(ktSourceFile)).booleanValue();
    }

    public static boolean b(GroupedKtSources groupedKtSources, KtSourceFile ktSourceFile, String str) {
        ktSourceFile.getClass();
        str.getClass();
        return ((Boolean) FirSessionConstructionUtilsKt.getFileBelongsToModuleForLt(groupedKtSources).invoke(ktSourceFile, str)).booleanValue();
    }

    /* JADX INFO: renamed from: compileModuleToAnalyzedFirWithPsi-XGruv9M, reason: not valid java name */
    private final List<? extends SingleModuleFrontendOutput> m47compileModuleToAnalyzedFirWithPsiXGruv9M(ModulesStructure moduleStructure, List<? extends KtFile> ktFiles, List<String> libraries, List<String> friendLibraries, BaseDiagnosticsCollector diagnosticsReporter, IncrementalDataProvider incrementalDataProvider, CompilerPluginRegistrar.ExtensionStorage extensionStorage, boolean useWasmPlatform) {
        Iterator<? extends KtFile> it = ktFiles.iterator();
        while (it.hasNext()) {
            AnalyzerWithCompilerReport.INSTANCE.reportSyntaxErrors((PsiElement) it.next(), diagnosticsReporter);
        }
        Function1<KtFile, Boolean> function1IsCommonSourceForPsi = FirSessionConstructionUtilsKt.isCommonSourceForPsi();
        Function2<KtFile, String, Boolean> fileBelongsToModuleForPsi = FirSessionConstructionUtilsKt.getFileBelongsToModuleForPsi();
        List list = extensionStorage.get(FirExtensionRegistrarAdapter.INSTANCE);
        list.getClass();
        Object obj = moduleStructure.getCompilerConfiguration().get(CommonConfigurationKeys.MODULE_NAME);
        obj.getClass();
        Name nameSpecial = Name.special("<" + ((String) obj) + '>');
        nameSpecial.getClass();
        DependencyListForCliModule.Companion companion = DependencyListForCliModule.INSTANCE;
        DependencyListForCliModule.Builder builder = new DependencyListForCliModule.Builder();
        DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule builderForDefaultDependenciesModule = new DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule(builder, builder.createData("<regular dependencies of " + nameSpecial + '>'), builder.createData("<dependsOn dependencies of " + nameSpecial + '>'), builder.createData("<friends dependencies of " + nameSpecial + '>'));
        builderForDefaultDependenciesModule.dependencies(libraries);
        builderForDefaultDependenciesModule.friendDependencies(friendLibraries);
        DependencyListForCliModule dependencyListForCliModuleBuild = builder.build();
        List<SessionWithSources> listPrepareWasmSessions = useWasmPlatform ? FirSessionConstructionUtilsKt.prepareWasmSessions(ktFiles, moduleStructure.getCompilerConfiguration(), nameSpecial, moduleStructure.getKlibs().getAll(), dependencyListForCliModuleBuild, list, function1IsCommonSourceForPsi, fileBelongsToModuleForPsi, incrementalDataProvider != null ? new KlibIcData(incrementalDataProvider) : null) : FirSessionConstructionUtilsKt.prepareJsSessions(ktFiles, moduleStructure.getCompilerConfiguration(), nameSpecial, moduleStructure.getKlibs().getAll(), dependencyListForCliModuleBuild, list, function1IsCommonSourceForPsi, fileBelongsToModuleForPsi, incrementalDataProvider != null ? new KlibIcData(incrementalDataProvider) : null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPrepareWasmSessions, 10));
        for (SessionWithSources sessionWithSources : listPrepareWasmSessions) {
            arrayList.add(FirUtilsKt.buildResolveAndCheckFirFromKtFiles(sessionWithSources.getSession(), sessionWithSources.getFiles(), diagnosticsReporter));
        }
        ConvertToIrKt.runPlatformCheckers(arrayList, diagnosticsReporter);
        return AllModulesFrontendOutput.m573constructorimpl(arrayList);
    }

    /* JADX INFO: renamed from: compileModulesToAnalyzedFirWithLightTree-XRluBCg, reason: not valid java name */
    private final List<? extends SingleModuleFrontendOutput> m48compileModulesToAnalyzedFirWithLightTreeXRluBCg(ModulesStructure moduleStructure, final GroupedKtSources groupedSources, List<? extends KtSourceFile> ktSourceFiles, List<String> libraries, List<String> friendLibraries, BaseDiagnosticsCollector diagnosticsReporter, PerformanceManager performanceManager, IncrementalDataProvider incrementalDataProvider, CompilerPluginRegistrar.ExtensionStorage extensionStorage, boolean useWasmPlatform) {
        Function1 function1 = new Function1() { // from class: qlf
            public final Object invoke(Object obj) {
                return Boolean.valueOf(WebFrontendPipelinePhase.a(groupedSources, (KtSourceFile) obj));
            }
        };
        Function2 function2 = new Function2() { // from class: rlf
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(WebFrontendPipelinePhase.b(groupedSources, (KtSourceFile) obj, (String) obj2));
            }
        };
        List list = extensionStorage.get(FirExtensionRegistrarAdapter.INSTANCE);
        list.getClass();
        Object obj = moduleStructure.getCompilerConfiguration().get(CommonConfigurationKeys.MODULE_NAME);
        obj.getClass();
        Name nameSpecial = Name.special("<" + ((String) obj) + '>');
        nameSpecial.getClass();
        DependencyListForCliModule.Companion companion = DependencyListForCliModule.INSTANCE;
        DependencyListForCliModule.Builder builder = new DependencyListForCliModule.Builder();
        DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule builderForDefaultDependenciesModule = new DependencyListForCliModule.Builder.BuilderForDefaultDependenciesModule(builder, builder.createData("<regular dependencies of " + nameSpecial + '>'), builder.createData("<dependsOn dependencies of " + nameSpecial + '>'), builder.createData("<friends dependencies of " + nameSpecial + '>'));
        builderForDefaultDependenciesModule.dependencies(libraries);
        builderForDefaultDependenciesModule.friendDependencies(friendLibraries);
        DependencyListForCliModule dependencyListForCliModuleBuild = builder.build();
        List<SessionWithSources> listPrepareWasmSessions = useWasmPlatform ? FirSessionConstructionUtilsKt.prepareWasmSessions(ktSourceFiles, moduleStructure.getCompilerConfiguration(), nameSpecial, moduleStructure.getKlibs().getAll(), dependencyListForCliModuleBuild, list, function1, function2, incrementalDataProvider != null ? new KlibIcData(incrementalDataProvider) : null) : FirSessionConstructionUtilsKt.prepareJsSessions(ktSourceFiles, moduleStructure.getCompilerConfiguration(), nameSpecial, moduleStructure.getKlibs().getAll(), dependencyListForCliModuleBuild, list, function1, function2, incrementalDataProvider != null ? new KlibIcData(incrementalDataProvider) : null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPrepareWasmSessions, 10));
        for (SessionWithSources sessionWithSources : listPrepareWasmSessions) {
            arrayList.add(FirUtilsKt.buildResolveAndCheckFirViaLightTree(sessionWithSources.getSession(), sessionWithSources.getFiles(), diagnosticsReporter, performanceManager != null ? new WebFrontendPipelinePhase$compileModulesToAnalyzedFirWithLightTree$output$3$1$1(performanceManager) : null));
        }
        ConvertToIrKt.runPlatformCheckers(arrayList, diagnosticsReporter);
        return AllModulesFrontendOutput.m573constructorimpl(arrayList);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public WebFrontendPipelineArtifact executePhase(ConfigurationPipelineArtifact input) {
        ModulesStructure modulesStructure;
        boolean zCheckKotlinPackageUsageForPsi;
        List<? extends SingleModuleFrontendOutput> listM47compileModuleToAnalyzedFirWithPsiXGruv9M;
        input.getClass();
        CompilerConfiguration configuration = input.getConfiguration();
        KotlinCoreEnvironment kotlinCoreEnvironmentCreateForProduction = KotlinCoreEnvironment.INSTANCE.createForProduction(input.getRootDisposable(), configuration, EnvironmentConfigFiles.JS_CONFIG_FILES);
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(configuration);
        if (perfManager != null) {
            perfManager.notifyCurrentPhaseFinishedIfNeeded();
            perfManager.notifyPhaseStarted(PhaseType.Analysis);
        }
        MessageCollector messageCollector = CommonConfigurationKeysKt.getMessageCollector(configuration);
        BaseDiagnosticsCollector diagnosticsCollector = CLIConfigurationKeysKt.getDiagnosticsCollector(configuration);
        List<String> libraries = JSConfigurationKeysKt.getLibraries(configuration);
        List<String> friendLibraries = JSConfigurationKeysKt.getFriendLibraries(configuration);
        boolean wasmCompilation = JSConfigurationKeysKt.getWasmCompilation(configuration);
        ModulesStructure modulesStructure2 = new ModulesStructure(kotlinCoreEnvironmentCreateForProduction.getProject(), new MainModule.SourceFiles(kotlinCoreEnvironmentCreateForProduction.getSourceFiles()), configuration, LoadWebKlibsKt.loadWebKlibs(configuration, HelpersKt.getPlatformChecker(configuration)));
        CompilerPluginRegistrar.ExtensionStorage extensionsStorage = FrontendConfigurationKeysKt.getExtensionsStorage(configuration);
        if (extensionsStorage == null) {
            k2d.a("Extensions storage is not registered");
            return null;
        }
        if (CommonConfigurationKeysKt.getUseLightTree(configuration)) {
            GroupedKtSources groupedKtSourcesCollectSources = GroupedKtSourcesKt.collectSources(configuration, VfsBasedProjectEnvironmentKt.toVfsBasedProjectEnvironment(kotlinCoreEnvironmentCreateForProduction));
            if (groupedKtSourcesCollectSources.isEmpty() && !CLIConfigurationKeysKt.getAllowNoSourceFiles(configuration) && !JSConfigurationKeysKt.getJsIncrementalCompilationEnabled(configuration)) {
                if (!CLIConfigurationKeysKt.getPrintVersion(configuration)) {
                    CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "No source files", null, 4, null);
                }
                return null;
            }
            modulesStructure = modulesStructure2;
            listM47compileModuleToAnalyzedFirWithPsiXGruv9M = m48compileModulesToAnalyzedFirWithLightTreeXRluBCg(modulesStructure, groupedKtSourcesCollectSources, CollectionsKt.plus(groupedKtSourcesCollectSources.getCommonSources(), groupedKtSourcesCollectSources.getPlatformSources()), libraries, friendLibraries, CLIConfigurationKeysKt.getDiagnosticsCollector(configuration), CommonConfigurationKeysKt.getPerfManager(configuration), JSConfigurationKeysKt.getIncrementalDataProvider(configuration), extensionsStorage, wasmCompilation);
            List<? extends SingleModuleFrontendOutput> list = listM47compileModuleToAnalyzedFirWithPsiXGruv9M;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        zCheckKotlinPackageUsageForPsi = true;
                        break;
                    }
                    if (!UtilsKt.checkKotlinPackageUsageForLightTree(configuration, ((SingleModuleFrontendOutput) it.next()).getFir())) {
                        zCheckKotlinPackageUsageForPsi = false;
                        break;
                    }
                }
            } else {
                zCheckKotlinPackageUsageForPsi = true;
                break;
            }
        } else {
            modulesStructure = modulesStructure2;
            List<KtFile> sourceFiles = kotlinCoreEnvironmentCreateForProduction.getSourceFiles();
            if (sourceFiles.isEmpty() && !CLIConfigurationKeysKt.getAllowNoSourceFiles(configuration) && !JSConfigurationKeysKt.getJsIncrementalCompilationEnabled(configuration)) {
                if (!CLIConfigurationKeysKt.getPrintVersion(configuration)) {
                    CliDiagnosticReportingKt.report$default(configuration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "No source files", null, 4, null);
                }
                return null;
            }
            zCheckKotlinPackageUsageForPsi = UtilsKt.checkKotlinPackageUsageForPsi(configuration, sourceFiles);
            listM47compileModuleToAnalyzedFirWithPsiXGruv9M = m47compileModuleToAnalyzedFirWithPsiXGruv9M(modulesStructure, sourceFiles, libraries, friendLibraries, CLIConfigurationKeysKt.getDiagnosticsCollector(configuration), JSConfigurationKeysKt.getIncrementalDataProvider(configuration), extensionsStorage, wasmCompilation);
        }
        if (zCheckKotlinPackageUsageForPsi) {
            return new WebFrontendPipelineArtifact(listM47compileModuleToAnalyzedFirWithPsiXGruv9M, configuration, modulesStructure, messageCollector.hasErrors() || diagnosticsCollector.getHasErrors(), null);
        }
        return null;
    }
}
