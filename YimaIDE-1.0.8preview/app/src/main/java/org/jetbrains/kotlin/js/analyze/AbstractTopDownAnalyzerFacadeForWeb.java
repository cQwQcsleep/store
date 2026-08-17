package org.jetbrains.kotlin.js.analyze;

import com.intellij.openapi.project.Project;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.K1DeprecationKt;
import org.jetbrains.kotlin.analyzer.AnalysisResult;
import org.jetbrains.kotlin.analyzer.AnalysisResult$RetryWithAdditionalRoots;
import org.jetbrains.kotlin.builtins.DefaultBuiltIns;
import org.jetbrains.kotlin.builtins.functions.FunctionInterfaceFactoryKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.DslKt;
import org.jetbrains.kotlin.container.StorageComponentContainer;
import org.jetbrains.kotlin.context.ContextKt;
import org.jetbrains.kotlin.context.ModuleContext;
import org.jetbrains.kotlin.context.MutableModuleContext;
import org.jetbrains.kotlin.context.ProjectContext;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.frontend.js.di.InjectionKt;
import org.jetbrains.kotlin.incremental.components.EnumWhenTracker;
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.incremental.js.IncrementalDataProvider;
import org.jetbrains.kotlin.js.analyzer.JsAnalysisResult;
import org.jetbrains.kotlin.js.config.JSConfigurationKeys;
import org.jetbrains.kotlin.js.config.ModuleKind;
import org.jetbrains.kotlin.js.resolve.BindingContextSlicesJsKt;
import org.jetbrains.kotlin.js.resolve.JsPlatformAnalyzerServices;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.AnalyzingUtils;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.BindingTraceContext;
import org.jetbrains.kotlin.resolve.BindingTraceFilter;
import org.jetbrains.kotlin.resolve.DelegatingBindingTrace;
import org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer;
import org.jetbrains.kotlin.resolve.PlatformDependentAnalyzerServices;
import org.jetbrains.kotlin.resolve.TargetEnvironment;
import org.jetbrains.kotlin.resolve.TopDownAnalysisMode;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo;
import org.jetbrains.kotlin.resolve.diagnostics.KotlinSuppressCache;
import org.jetbrains.kotlin.resolve.extensions.AnalysisHandlerExtension;
import org.jetbrains.kotlin.resolve.lazy.declarations.FileBasedDeclarationProviderFactory;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003Jz\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0017H\u0007b\u0018\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\n\b!\u0012\u0006\b\n0\"8#J(\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H$Jh\u0010.\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010/\u001a\u0002002\u0006\u0010(\u001a\u00020)2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u00122\u000e\b\u0002\u00101\u001a\b\u0012\u0004\u0012\u00020%0\u0016H\u0007b\u0018\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\n\b!\u0012\u0006\b\n0\"8#J8\u00102\u001a\u00020\u001c2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u00104\u001a\u000205H\u0007b\u0018\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\n\b!\u0012\u0006\b\n0\"8#R\u0012\u0010\u0004\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/js/analyze/AbstractTopDownAnalyzerFacadeForWeb;", Argument.Delimiters.none, "<init>", "()V", "analyzerServices", "Lorg/jetbrains/kotlin/resolve/PlatformDependentAnalyzerServices;", "getAnalyzerServices", "()Lorg/jetbrains/kotlin/resolve/PlatformDependentAnalyzerServices;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "analyzeFiles", "Lorg/jetbrains/kotlin/js/analyzer/JsAnalysisResult;", "files", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "project", "Lcom/intellij/openapi/project/Project;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "moduleDescriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "friendModuleDescriptors", "targetEnvironment", "Lorg/jetbrains/kotlin/resolve/TargetEnvironment;", "thisIsBuiltInsModule", Argument.Delimiters.none, "customBuiltInsModule", "Lkotlin/Deprecated;", "message", K1DeprecationKt.K1_DEPRECATION_WARNING, "level", "Lkotlin/DeprecationLevel;", "ERROR", "loadIncrementalCacheMetadata", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "incrementalData", "Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;", "moduleContext", "Lorg/jetbrains/kotlin/context/ModuleContext;", "lookupTracker", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "analyzeFilesWithGivenTrace", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "additionalPackages", "checkForErrors", "allFiles", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractTopDownAnalyzerFacadeForWeb {
    public static /* synthetic */ JsAnalysisResult analyzeFiles$default(AbstractTopDownAnalyzerFacadeForWeb abstractTopDownAnalyzerFacadeForWeb, Collection collection, Project project, CompilerConfiguration compilerConfiguration, List list, List list2, TargetEnvironment targetEnvironment, boolean z, ModuleDescriptor moduleDescriptor, int i, Object obj) {
        if (obj == null) {
            return abstractTopDownAnalyzerFacadeForWeb.analyzeFiles(collection, project, compilerConfiguration, list, list2, targetEnvironment, (i & 64) != 0 ? false : z, (i & 128) != 0 ? null : moduleDescriptor);
        }
        c41.a("Super calls with default arguments not supported in this target, function: analyzeFiles");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JsAnalysisResult analyzeFilesWithGivenTrace$default(AbstractTopDownAnalyzerFacadeForWeb abstractTopDownAnalyzerFacadeForWeb, Collection collection, BindingTrace bindingTrace, ModuleContext moduleContext, CompilerConfiguration compilerConfiguration, TargetEnvironment targetEnvironment, Project project, List list, int i, Object obj) {
        if (obj == null) {
            return abstractTopDownAnalyzerFacadeForWeb.analyzeFilesWithGivenTrace(collection, bindingTrace, moduleContext, compilerConfiguration, targetEnvironment, project, (i & 64) != 0 ? CollectionsKt.emptyList() : list);
        }
        c41.a("Super calls with default arguments not supported in this target, function: analyzeFilesWithGivenTrace");
        return null;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    public final JsAnalysisResult analyzeFiles(Collection<? extends KtFile> files, Project project, CompilerConfiguration configuration, List<? extends ModuleDescriptor> moduleDescriptors, List<? extends ModuleDescriptor> friendModuleDescriptors, TargetEnvironment targetEnvironment, boolean thisIsBuiltInsModule, ModuleDescriptor customBuiltInsModule) {
        DefaultBuiltIns builtIns;
        files.getClass();
        project.getClass();
        configuration.getClass();
        moduleDescriptors.getClass();
        friendModuleDescriptors.getClass();
        targetEnvironment.getClass();
        if (thisIsBuiltInsModule && customBuiltInsModule != null) {
            w01.a("Can't simultaneously use custom built-ins module and set current module as built-ins");
            return null;
        }
        if (thisIsBuiltInsModule) {
            builtIns = new DefaultBuiltIns(false);
        } else {
            builtIns = customBuiltInsModule != null ? customBuiltInsModule.getBuiltIns() : JsPlatformAnalyzerServices.INSTANCE.getBuiltIns();
        }
        DefaultBuiltIns defaultBuiltIns = builtIns;
        Object obj = configuration.get(CommonConfigurationKeys.MODULE_NAME);
        obj.getClass();
        ProjectContext ProjectContext = ContextKt.ProjectContext(project, "TopDownAnalyzer for JS");
        Name nameSpecial = Name.special("<" + ((String) obj) + '>');
        nameSpecial.getClass();
        MutableModuleContext mutableModuleContextContextForNewModule$default = ContextKt.ContextForNewModule$default(ProjectContext, nameSpecial, defaultBuiltIns, getPlatform(), null, 16, null);
        ArrayList arrayList = new ArrayList();
        if (thisIsBuiltInsModule) {
            defaultBuiltIns.setBuiltInsModule(mutableModuleContextContextForNewModule$default.getModule());
            arrayList.add(FunctionInterfaceFactoryKt.functionInterfacePackageFragmentProvider(mutableModuleContextContextForNewModule$default.getStorageManager(), mutableModuleContextContextForNewModule$default.getModule()));
        }
        Set setPlus = SetsKt.plus(SetsKt.plus(SetsKt.mutableSetOf(new ModuleDescriptorImpl[]{mutableModuleContextContextForNewModule$default.getModule()}), moduleDescriptors), defaultBuiltIns.getBuiltInsModule());
        ModuleDescriptorImpl module = mutableModuleContextContextForNewModule$default.getModule();
        List<ModuleDescriptorImpl> list = CollectionsKt.toList(setPlus);
        list.getClass();
        Set<ModuleDescriptorImpl> set = CollectionsKt.toSet(friendModuleDescriptors);
        set.getClass();
        module.setDependencies(list, set);
        ModuleKind moduleKind = (ModuleKind) configuration.get(JSConfigurationKeys.MODULE_KIND, ModuleKind.PLAIN);
        BindingTraceContext bindingTraceContext = new BindingTraceContext(project);
        bindingTraceContext.record(BindingContextSlicesJsKt.MODULE_KIND, mutableModuleContextContextForNewModule$default.getModule(), moduleKind);
        return analyzeFilesWithGivenTrace(files, bindingTraceContext, mutableModuleContextContextForNewModule$default, configuration, targetEnvironment, project, arrayList);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    public final JsAnalysisResult analyzeFilesWithGivenTrace(Collection<? extends KtFile> files, BindingTrace trace, ModuleContext moduleContext, CompilerConfiguration configuration, TargetEnvironment targetEnvironment, Project project, List<? extends PackageFragmentProvider> additionalPackages) {
        Project project2;
        AnalysisResult analysisResultSuccess;
        Collection<? extends KtFile> collection;
        Project project3 = project;
        files.getClass();
        trace.getClass();
        moduleContext.getClass();
        configuration.getClass();
        targetEnvironment.getClass();
        project3.getClass();
        additionalPackages.getClass();
        LookupTracker.DO_NOTHING do_nothing = (LookupTracker) configuration.get(CommonConfigurationKeys.LOOKUP_TRACKER);
        if (do_nothing == null) {
            do_nothing = LookupTracker.DO_NOTHING.INSTANCE;
        }
        LookupTracker.DO_NOTHING do_nothing2 = do_nothing;
        ExpectActualTracker.DoNothing doNothing = (ExpectActualTracker) configuration.get(CommonConfigurationKeys.EXPECT_ACTUAL_TRACKER);
        if (doNothing == null) {
            doNothing = ExpectActualTracker.DoNothing.INSTANCE;
        }
        ExpectActualTracker.DoNothing doNothing2 = doNothing;
        InlineConstTracker.DoNothing doNothing3 = (InlineConstTracker) configuration.get(CommonConfigurationKeys.INLINE_CONST_TRACKER);
        if (doNothing3 == null) {
            doNothing3 = InlineConstTracker.DoNothing.INSTANCE;
        }
        InlineConstTracker.DoNothing doNothing4 = doNothing3;
        EnumWhenTracker.DoNothing doNothing5 = (EnumWhenTracker) configuration.get(CommonConfigurationKeys.ENUM_WHEN_TRACKER);
        if (doNothing5 == null) {
            doNothing5 = EnumWhenTracker.DoNothing.INSTANCE;
        }
        EnumWhenTracker.DoNothing doNothing6 = doNothing5;
        LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(configuration);
        IncrementalDataProvider incrementalDataProvider = (IncrementalDataProvider) configuration.get(JSConfigurationKeys.INCREMENTAL_DATA_PROVIDER);
        AnalysisResult analysisResult = null;
        StorageComponentContainer storageComponentContainerCreateContainerForJS = InjectionKt.createContainerForJS(moduleContext, trace, new FileBasedDeclarationProviderFactory(moduleContext.getStorageManager(), files), languageVersionSettings, do_nothing2, doNothing2, doNothing4, doNothing6, CollectionsKt.plus(additionalPackages, CollectionsKt.listOfNotNull(incrementalDataProvider != null ? loadIncrementalCacheMetadata(incrementalDataProvider, moduleContext, do_nothing2, languageVersionSettings) : null)), targetEnvironment, getAnalyzerServices(), getPlatform());
        List instances = AnalysisHandlerExtension.Companion.getInstances(project3);
        Iterator it = instances.iterator();
        while (true) {
            if (!it.hasNext()) {
                project2 = project3;
                analysisResultSuccess = null;
                break;
            }
            analysisResultSuccess = ((AnalysisHandlerExtension) it.next()).doAnalysis(project3, moduleContext.getModule(), moduleContext, files, trace, storageComponentContainerCreateContainerForJS);
            project2 = project3;
            if (analysisResultSuccess != null) {
                break;
            }
            project3 = project2;
        }
        if (analysisResultSuccess == null) {
            collection = files;
            LazyTopDownAnalyzer.analyzeDeclarations$default((LazyTopDownAnalyzer) DslKt.getService(storageComponentContainerCreateContainerForJS, LazyTopDownAnalyzer.class), TopDownAnalysisMode.TopLevelDeclarations, collection, (DataFlowInfo) null, (ExpressionTypingContext) null, 12, (Object) null);
            AnalysisResult.Companion companion = AnalysisResult.Companion;
            BindingContext bindingContext = trace.getBindingContext();
            bindingContext.getClass();
            analysisResultSuccess = companion.success(bindingContext, moduleContext.getModule());
        } else {
            collection = files;
        }
        Iterator it2 = instances.iterator();
        while (it2.hasNext()) {
            AnalysisResult analysisResultAnalysisCompleted = ((AnalysisHandlerExtension) it2.next()).analysisCompleted(project2, moduleContext.getModule(), trace, collection);
            if (analysisResultAnalysisCompleted != null) {
                analysisResult = analysisResultAnalysisCompleted;
                break;
            }
        }
        if (analysisResult != null) {
            analysisResultSuccess = analysisResult;
        }
        if (analysisResultSuccess instanceof JsAnalysisResult) {
            return (JsAnalysisResult) analysisResultSuccess;
        }
        DelegatingBindingTrace delegatingBindingTrace = new DelegatingBindingTrace(analysisResultSuccess.getBindingContext(), "DelegatingBindingTrace by AnalysisHandlerExtension", false, (BindingTraceFilter) null, false, (KotlinSuppressCache) null, 60, (DefaultConstructorMarker) null);
        return analysisResultSuccess instanceof AnalysisResult$RetryWithAdditionalRoots ? new JsAnalysisResult.RetryWithAdditionalRoots(delegatingBindingTrace, analysisResultSuccess.getModuleDescriptor(), ((AnalysisResult$RetryWithAdditionalRoots) analysisResultSuccess).getAdditionalKotlinRoots()) : JsAnalysisResult.Companion.success(delegatingBindingTrace, analysisResultSuccess.getModuleDescriptor(), analysisResultSuccess.getShouldGenerateCode());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    public final boolean checkForErrors(Collection<? extends KtFile> allFiles, BindingContext bindingContext) {
        allFiles.getClass();
        bindingContext.getClass();
        AnalyzingUtils.INSTANCE.throwExceptionOnErrors(bindingContext);
        Iterator<? extends KtFile> it = allFiles.iterator();
        while (it.hasNext()) {
            AnalyzingUtils.checkForSyntacticErrors(it.next());
        }
        return false;
    }

    public abstract PlatformDependentAnalyzerServices getAnalyzerServices();

    public abstract TargetPlatform getPlatform();

    public abstract PackageFragmentProvider loadIncrementalCacheMetadata(IncrementalDataProvider incrementalData, ModuleContext moduleContext, LookupTracker lookupTracker, LanguageVersionSettings languageVersionSettings);
}
