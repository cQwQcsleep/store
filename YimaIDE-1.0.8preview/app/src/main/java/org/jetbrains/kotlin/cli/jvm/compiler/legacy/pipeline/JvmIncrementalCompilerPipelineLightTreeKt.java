package org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.FirJvmSessionConstructionUtilsKt;
import org.jetbrains.kotlin.cli.common.FirSessionConstructionUtilsKt;
import org.jetbrains.kotlin.cli.common.LegacyK2CliPipeline;
import org.jetbrains.kotlin.cli.common.SessionWithSources;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.jvm.compiler.IncrementalCompilationContextUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.JvmIncrementalCompilerPipelineLightTreeKt;
import org.jetbrains.kotlin.compiler.plugin.FirExtensionRegistrarConfigurationUtilKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;
import org.jetbrains.kotlin.fir.pipeline.AllModulesFrontendOutput;
import org.jetbrains.kotlin.fir.pipeline.ConvertToIrKt;
import org.jetbrains.kotlin.fir.pipeline.FirUtilsKt;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.session.IncrementalCompilationContext;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0002\u0010\u000e\u001aG\u0010\u0000\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011H\u0003¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"compileModuleToAnalyzedFirViaLightTreeIncrementally", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "compilerConfiguration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "input", "Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerInput;", "diagnosticsReporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "incrementalExcludesScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerInput;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;)Ljava/util/List;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/FrontendContext;", "previousStepsSymbolProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "friendPaths", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/FrontendContext;Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerInput;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;Ljava/util/List;Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;Ljava/util/List;)Ljava/util/List;", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmIncrementalCompilerPipelineLightTreeKt {
    @LegacyK2CliPipeline
    private static final List<? extends SingleModuleFrontendOutput> compileModuleToAnalyzedFirViaLightTreeIncrementally(final FrontendContext frontendContext, ModuleCompilerInput moduleCompilerInput, BaseDiagnosticsCollector baseDiagnosticsCollector, final List<? extends FirSymbolProvider> list, AbstractProjectFileSearchScope abstractProjectFileSearchScope, List<String> list2) {
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(frontendContext.getConfiguration());
        PhaseType phaseType = PhaseType.Analysis;
        final Function2 jvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$1 = null;
        if (perfManager == null) {
            AbstractProjectFileSearchScope searchScopeForProjectLibraries = frontendContext.getProjectEnvironment().getSearchScopeForProjectLibraries();
            final AbstractProjectFileSearchScope abstractProjectFileSearchScopeCreateIncrementalCompilationScope = IncrementalCompilationContextUtilsKt.createIncrementalCompilationScope(frontendContext.getConfiguration(), frontendContext.getProjectEnvironment(), abstractProjectFileSearchScope);
            if (abstractProjectFileSearchScopeCreateIncrementalCompilationScope != null) {
                searchScopeForProjectLibraries = searchScopeForProjectLibraries.minus(abstractProjectFileSearchScopeCreateIncrementalCompilationScope);
            } else {
                abstractProjectFileSearchScopeCreateIncrementalCompilationScope = null;
            }
            AbstractProjectFileSearchScope abstractProjectFileSearchScope2 = searchScopeForProjectLibraries;
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(moduleCompilerInput.getGroupedSources().getCommonSources());
            arrayList.addAll(moduleCompilerInput.getGroupedSources().getPlatformSources());
            List listPrepareJvmSessions = FirJvmSessionConstructionUtilsKt.prepareJvmSessions(frontendContext, arrayList, moduleCompilerInput.getTargetId().getName(), list2, abstractProjectFileSearchScope2, FirSessionConstructionUtilsKt.isCommonSourceForLt(moduleCompilerInput.getGroupedSources()), new Function1() { // from class: kx7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(JvmIncrementalCompilerPipelineLightTreeKt.compileModuleToAnalyzedFirViaLightTreeIncrementally$lambda$0$2((KtSourceFile) obj));
                }
            }, FirSessionConstructionUtilsKt.getFileBelongsToModuleForLt(moduleCompilerInput.getGroupedSources()), new Function1() { // from class: lx7
                public final Object invoke(Object obj) {
                    return JvmIncrementalCompilerPipelineLightTreeKt.compileModuleToAnalyzedFirViaLightTreeIncrementally$lambda$0$3(frontendContext, list, abstractProjectFileSearchScopeCreateIncrementalCompilationScope, (List) obj);
                }
            });
            jvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$1 = perfManager != null ? new JvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$1(perfManager) : null;
            List<SessionWithSources> list3 = listPrepareJvmSessions;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            for (SessionWithSources sessionWithSources : list3) {
                arrayList2.add(FirUtilsKt.buildResolveAndCheckFirViaLightTree(sessionWithSources.getSession(), sessionWithSources.component2(), baseDiagnosticsCollector, jvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$1));
            }
            ConvertToIrKt.runPlatformCheckers(arrayList2, baseDiagnosticsCollector);
            return AllModulesFrontendOutput.m573constructorimpl(arrayList2);
        }
        try {
            perfManager.notifyPhaseStarted(phaseType);
            AbstractProjectFileSearchScope searchScopeForProjectLibraries2 = frontendContext.getProjectEnvironment().getSearchScopeForProjectLibraries();
            Function2 function2CreateIncrementalCompilationScope = IncrementalCompilationContextUtilsKt.createIncrementalCompilationScope(frontendContext.getConfiguration(), frontendContext.getProjectEnvironment(), abstractProjectFileSearchScope);
            if (function2CreateIncrementalCompilationScope != null) {
                searchScopeForProjectLibraries2 = searchScopeForProjectLibraries2.minus(function2CreateIncrementalCompilationScope);
                jvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$1 = function2CreateIncrementalCompilationScope;
            }
            AbstractProjectFileSearchScope abstractProjectFileSearchScope3 = searchScopeForProjectLibraries2;
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(moduleCompilerInput.getGroupedSources().getCommonSources());
            arrayList3.addAll(moduleCompilerInput.getGroupedSources().getPlatformSources());
            List listPrepareJvmSessions2 = FirJvmSessionConstructionUtilsKt.prepareJvmSessions(frontendContext, arrayList3, moduleCompilerInput.getTargetId().getName(), list2, abstractProjectFileSearchScope3, FirSessionConstructionUtilsKt.isCommonSourceForLt(moduleCompilerInput.getGroupedSources()), new Function1() { // from class: kx7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(JvmIncrementalCompilerPipelineLightTreeKt.compileModuleToAnalyzedFirViaLightTreeIncrementally$lambda$0$2((KtSourceFile) obj));
                }
            }, FirSessionConstructionUtilsKt.getFileBelongsToModuleForLt(moduleCompilerInput.getGroupedSources()), new Function1() { // from class: lx7
                public final Object invoke(Object obj) {
                    return JvmIncrementalCompilerPipelineLightTreeKt.compileModuleToAnalyzedFirViaLightTreeIncrementally$lambda$0$3(frontendContext, list, jvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$1, (List) obj);
                }
            });
            JvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$1 jvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$2 = new JvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$1(perfManager);
            List<SessionWithSources> list4 = listPrepareJvmSessions2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            for (SessionWithSources sessionWithSources2 : list4) {
                arrayList4.add(FirUtilsKt.buildResolveAndCheckFirViaLightTree(sessionWithSources2.getSession(), sessionWithSources2.component2(), baseDiagnosticsCollector, jvmIncrementalCompilerPipelineLightTreeKt$compileModuleToAnalyzedFirViaLightTreeIncrementally$1$countFilesAndLines$2));
            }
            ConvertToIrKt.runPlatformCheckers(arrayList4, baseDiagnosticsCollector);
            return AllModulesFrontendOutput.m573constructorimpl(arrayList4);
        } finally {
            perfManager.notifyPhaseFinished(phaseType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean compileModuleToAnalyzedFirViaLightTreeIncrementally$lambda$0$2(KtSourceFile ktSourceFile) {
        ktSourceFile.getClass();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IncrementalCompilationContext compileModuleToAnalyzedFirViaLightTreeIncrementally$lambda$0$3(FrontendContext frontendContext, List list, AbstractProjectFileSearchScope abstractProjectFileSearchScope, List list2) {
        list2.getClass();
        return IncrementalCompilationContextUtilsKt.createContextForIncrementalCompilation(frontendContext.getConfiguration(), frontendContext.getProjectEnvironment(), AbstractProjectEnvironment.getSearchScopeBySourceFiles$default(frontendContext.getProjectEnvironment(), list2, false, 2, null), list, abstractProjectFileSearchScope);
    }

    @IncrementalCompilationApi
    public static final List<? extends SingleModuleFrontendOutput> compileModuleToAnalyzedFirViaLightTreeIncrementally(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, MessageCollector messageCollector, CompilerConfiguration compilerConfiguration, ModuleCompilerInput moduleCompilerInput, BaseDiagnosticsCollector baseDiagnosticsCollector, AbstractProjectFileSearchScope abstractProjectFileSearchScope) {
        vfsBasedProjectEnvironment.getClass();
        messageCollector.getClass();
        compilerConfiguration.getClass();
        moduleCompilerInput.getClass();
        baseDiagnosticsCollector.getClass();
        return compileModuleToAnalyzedFirViaLightTreeIncrementally(new MinimizedFrontendContext(vfsBasedProjectEnvironment, messageCollector, FirExtensionRegistrarConfigurationUtilKt.getCompilerExtensions(compilerConfiguration, FirExtensionRegistrar.INSTANCE), compilerConfiguration), moduleCompilerInput, baseDiagnosticsCollector, (List<? extends FirSymbolProvider>) CollectionsKt.emptyList(), abstractProjectFileSearchScope, (List<String>) CollectionsKt.emptyList());
    }
}
