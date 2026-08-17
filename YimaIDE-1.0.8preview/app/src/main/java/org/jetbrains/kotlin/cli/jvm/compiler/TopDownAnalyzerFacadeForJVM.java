package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.K1DeprecationKt;
import org.jetbrains.kotlin.analyzer.AnalysisResult;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.builtins.jvm.JvmBuiltIns;
import org.jetbrains.kotlin.builtins.jvm.JvmBuiltInsPackageFragmentProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.config.ClassicFrontendSpecificJvmConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.ComponentProvider;
import org.jetbrains.kotlin.container.ContainerKt;
import org.jetbrains.kotlin.container.DslKt;
import org.jetbrains.kotlin.container.StorageComponentContainer;
import org.jetbrains.kotlin.context.ContextKt;
import org.jetbrains.kotlin.context.ModuleContext;
import org.jetbrains.kotlin.context.MutableModuleContext;
import org.jetbrains.kotlin.context.ProjectContext;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleCapability;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.descriptors.PackageFragmentProviderOptimized;
import org.jetbrains.kotlin.descriptors.impl.CompositePackageFragmentProvider;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.frontend.java.di.InjectionKt;
import org.jetbrains.kotlin.incremental.components.EnumWhenTracker;
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.javac.components.JavacBasedClassFinder;
import org.jetbrains.kotlin.javac.components.JavacBasedSourceElementFactory;
import org.jetbrains.kotlin.javac.components.StubJavaResolverCache;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.load.java.JavaClassesTracker;
import org.jetbrains.kotlin.load.java.lazy.ModuleClassResolver;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.impl.VirtualFileBoundJavaClass;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.load.kotlin.incremental.IncrementalPackageFragmentProvider;
import org.jetbrains.kotlin.load.kotlin.incremental.IncrementalPackagePartProvider;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCompilationComponents;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.modules.TargetId;
import org.jetbrains.kotlin.modules.TargetIdKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.CompilerEnvironment;
import org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer;
import org.jetbrains.kotlin.resolve.SealedClassInheritorsProvider;
import org.jetbrains.kotlin.resolve.TargetEnvironment;
import org.jetbrains.kotlin.resolve.TopDownAnalysisMode;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo;
import org.jetbrains.kotlin.resolve.calls.tower.ImplicitsExtensionsResolutionFilter;
import org.jetbrains.kotlin.resolve.jvm.JavaDescriptorResolver;
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisHandlerExtension;
import org.jetbrains.kotlin.resolve.jvm.extensions.PackageFragmentProviderExtension;
import org.jetbrains.kotlin.resolve.jvm.multiplatform.OptionalAnnotationPackageFragmentProvider;
import org.jetbrains.kotlin.resolve.lazy.KotlinCodeAnalyzer;
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactory;
import org.jetbrains.kotlin.resolve.lazy.declarations.FileBasedDeclarationProviderFactory;
import org.jetbrains.kotlin.resolve.scopes.optimization.OptimizingOptions;
import org.jetbrains.kotlin.serialization.deserialization.DeserializationConfiguration;
import org.jetbrains.kotlin.storage.StorageManager;
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001.B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J¨\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102 \b\u0002\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0004\u0012\u00020\u00160\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00112\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00192\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00192\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007JÎ\u0001\u0010 \u001a\u00020!2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102\u001e\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0004\u0012\u00020\u00160\u00142\b\b\u0002\u0010\"\u001a\u00020\u001f2\b\b\u0002\u0010\u0017\u001a\u00020\u00112\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00192\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00192\u001a\b\u0002\u0010%\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030'\u0012\u0006\u0012\u0004\u0018\u00010\u00010&H\u0007J\u001c\u0010(\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ>\u0010)\u001a\u00020*2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\b\u0010+\u001a\u0004\u0018\u00010,2\u001a\b\u0002\u0010-\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030'\u0012\u0006\u0012\u0004\u0018\u00010\u00010&H\u0002¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/TopDownAnalyzerFacadeForJVM;", Argument.Delimiters.none, "<init>", "()V", "analyzeFilesWithJavaIntegration", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "project", "Lcom/intellij/openapi/project/Project;", "files", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "packagePartProvider", "Lkotlin/Function1;", "Lcom/intellij/psi/search/GlobalSearchScope;", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "declarationProviderFactory", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/storage/StorageManager;", "Lorg/jetbrains/kotlin/resolve/lazy/declarations/DeclarationProviderFactory;", "sourceModuleSearchScope", "klibList", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "explicitModuleDependencyList", "Lorg/jetbrains/kotlin/descriptors/impl/ModuleDescriptorImpl;", "explicitModuleFriendsList", "explicitCompilerEnvironment", "Lorg/jetbrains/kotlin/resolve/TargetEnvironment;", "createContainer", "Lorg/jetbrains/kotlin/container/ComponentProvider;", "targetEnvironment", "implicitsResolutionFilter", "Lorg/jetbrains/kotlin/resolve/calls/tower/ImplicitsExtensionsResolutionFilter;", "moduleCapabilities", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "newModuleSearchScope", "createModuleContext", "Lorg/jetbrains/kotlin/context/MutableModuleContext;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "capabilities", "SourceOrBinaryModuleClassResolver", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TopDownAnalyzerFacadeForJVM {
    public static final TopDownAnalyzerFacadeForJVM INSTANCE = new TopDownAnalyzerFacadeForJVM();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/TopDownAnalyzerFacadeForJVM$SourceOrBinaryModuleClassResolver;", "Lorg/jetbrains/kotlin/load/java/lazy/ModuleClassResolver;", "sourceScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "<init>", "(Lcom/intellij/psi/search/GlobalSearchScope;)V", "compiledCodeResolver", "Lorg/jetbrains/kotlin/resolve/jvm/JavaDescriptorResolver;", "getCompiledCodeResolver", "()Lorg/jetbrains/kotlin/resolve/jvm/JavaDescriptorResolver;", "setCompiledCodeResolver", "(Lorg/jetbrains/kotlin/resolve/jvm/JavaDescriptorResolver;)V", "sourceCodeResolver", "getSourceCodeResolver", "setSourceCodeResolver", "resolveClass", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SourceOrBinaryModuleClassResolver implements ModuleClassResolver {
        public JavaDescriptorResolver compiledCodeResolver;
        public JavaDescriptorResolver sourceCodeResolver;
        private final GlobalSearchScope sourceScope;

        public SourceOrBinaryModuleClassResolver(GlobalSearchScope globalSearchScope) {
            globalSearchScope.getClass();
            this.sourceScope = globalSearchScope;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        public final JavaDescriptorResolver getCompiledCodeResolver() throws UninitializedPropertyAccessException {
            JavaDescriptorResolver javaDescriptorResolver = this.compiledCodeResolver;
            if (javaDescriptorResolver != null) {
                return javaDescriptorResolver;
            }
            Intrinsics.throwUninitializedPropertyAccessException("compiledCodeResolver");
            return null;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        public final JavaDescriptorResolver getSourceCodeResolver() throws UninitializedPropertyAccessException {
            JavaDescriptorResolver javaDescriptorResolver = this.sourceCodeResolver;
            if (javaDescriptorResolver != null) {
                return javaDescriptorResolver;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sourceCodeResolver");
            return null;
        }

        public ClassDescriptor resolveClass(JavaClass javaClass) {
            javaClass.getClass();
            return (((javaClass instanceof VirtualFileBoundJavaClass) && ((VirtualFileBoundJavaClass) javaClass).isFromSourceCodeInScope(this.sourceScope)) ? getSourceCodeResolver() : getCompiledCodeResolver()).resolveClass(javaClass);
        }

        public final void setCompiledCodeResolver(JavaDescriptorResolver javaDescriptorResolver) {
            javaDescriptorResolver.getClass();
            this.compiledCodeResolver = javaDescriptorResolver;
        }

        public final void setSourceCodeResolver(JavaDescriptorResolver javaDescriptorResolver) {
            javaDescriptorResolver.getClass();
            this.sourceCodeResolver = javaDescriptorResolver;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM$analyzeFilesWithJavaIntegration$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<StorageManager, Collection<KtFile>, FileBasedDeclarationProviderFactory> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2, FileBasedDeclarationProviderFactory.class, "<init>", "<init>(Lorg/jetbrains/kotlin/storage/StorageManager;Ljava/util/Collection;)V", 0);
        }

        public final FileBasedDeclarationProviderFactory invoke(StorageManager storageManager, Collection<KtFile> collection) {
            storageManager.getClass();
            collection.getClass();
            return new FileBasedDeclarationProviderFactory(storageManager, collection);
        }
    }

    private TopDownAnalyzerFacadeForJVM() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    @JvmStatic
    public static final AnalysisResult analyzeFilesWithJavaIntegration(Project project, Collection<? extends KtFile> files, BindingTrace trace, CompilerConfiguration configuration, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> packagePartProvider, Function2<? super StorageManager, ? super Collection<? extends KtFile>, ? extends DeclarationProviderFactory> declarationProviderFactory, GlobalSearchScope sourceModuleSearchScope, List<? extends KotlinLibrary> klibList, List<ModuleDescriptorImpl> explicitModuleDependencyList, List<ModuleDescriptorImpl> explicitModuleFriendsList, TargetEnvironment explicitCompilerEnvironment) {
        project.getClass();
        files.getClass();
        trace.getClass();
        configuration.getClass();
        packagePartProvider.getClass();
        declarationProviderFactory.getClass();
        sourceModuleSearchScope.getClass();
        klibList.getClass();
        explicitModuleDependencyList.getClass();
        explicitModuleFriendsList.getClass();
        explicitCompilerEnvironment.getClass();
        ComponentProvider componentProviderCreateContainer$default = createContainer$default(INSTANCE, project, files, trace, configuration, packagePartProvider, declarationProviderFactory, explicitCompilerEnvironment, sourceModuleSearchScope, klibList, null, explicitModuleDependencyList, explicitModuleFriendsList, null, 4608, null);
        ModuleDescriptor moduleDescriptor = (ModuleDescriptor) DslKt.getService(componentProviderCreateContainer$default, ModuleDescriptor.class);
        ModuleContext moduleContext = (ModuleContext) DslKt.getService(componentProviderCreateContainer$default, ModuleContext.class);
        Project project2 = project;
        List instances = AnalysisHandlerExtension.Companion.getInstances(project2);
        Iterator it = instances.iterator();
        while (it.hasNext()) {
            ComponentProvider componentProvider = componentProviderCreateContainer$default;
            ModuleDescriptor moduleDescriptor2 = moduleDescriptor;
            ModuleContext moduleContext2 = moduleContext;
            AnalysisResult analysisResultDoAnalysis = ((org.jetbrains.kotlin.resolve.extensions.AnalysisHandlerExtension) it.next()).doAnalysis(project2, moduleDescriptor2, moduleContext2, files, trace, componentProvider);
            if (analysisResultDoAnalysis != null) {
                AnalysisResult analysisResultAnalyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete = analyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete(componentProvider, moduleDescriptor2, instances, project, trace, files);
                return analysisResultAnalyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete != null ? analysisResultAnalyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete : analysisResultDoAnalysis;
            }
            componentProviderCreateContainer$default = componentProvider;
            moduleDescriptor = moduleDescriptor2;
            moduleContext = moduleContext2;
            project2 = project;
        }
        ModuleDescriptor moduleDescriptor3 = moduleDescriptor;
        ComponentProvider componentProvider2 = componentProviderCreateContainer$default;
        LazyTopDownAnalyzer.analyzeDeclarations$default((LazyTopDownAnalyzer) DslKt.getService(componentProvider2, LazyTopDownAnalyzer.class), TopDownAnalysisMode.TopLevelDeclarations, files, (DataFlowInfo) null, (ExpressionTypingContext) null, 12, (Object) null);
        AnalysisResult analysisResultAnalyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete2 = analyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete(componentProvider2, moduleDescriptor3, instances, project, trace, files);
        if (analysisResultAnalyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete2 != null) {
            return analysisResultAnalyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete2;
        }
        AnalysisResult.Companion companion = AnalysisResult.Companion;
        BindingContext bindingContext = trace.getBindingContext();
        bindingContext.getClass();
        return companion.success(bindingContext, moduleDescriptor3);
    }

    public static /* synthetic */ AnalysisResult analyzeFilesWithJavaIntegration$default(Project project, Collection collection, BindingTrace bindingTrace, CompilerConfiguration compilerConfiguration, Function1 function1, Function2 function2, GlobalSearchScope globalSearchScope, List list, List list2, List list3, TargetEnvironment targetEnvironment, int i, Object obj) {
        return analyzeFilesWithJavaIntegration(project, collection, bindingTrace, compilerConfiguration, function1, (i & 32) != 0 ? AnonymousClass1.INSTANCE : function2, (i & 64) != 0 ? INSTANCE.newModuleSearchScope(project, collection) : globalSearchScope, (i & 128) != 0 ? CollectionsKt.emptyList() : list, (i & 256) != 0 ? CollectionsKt.emptyList() : list2, (i & 512) != 0 ? CollectionsKt.emptyList() : list3, (i & 1024) != 0 ? CompilerEnvironment.INSTANCE : targetEnvironment);
    }

    private static final AnalysisResult analyzeFilesWithJavaIntegration$invokeExtensionsOnAnalysisComplete(ComponentProvider componentProvider, ModuleDescriptor moduleDescriptor, List<? extends org.jetbrains.kotlin.resolve.extensions.AnalysisHandlerExtension> list, Project project, BindingTrace bindingTrace, Collection<? extends KtFile> collection) {
        ((JavaClassesTracker) DslKt.getService(componentProvider, JavaClassesTracker.class)).onCompletedAnalysis(moduleDescriptor);
        Iterator<? extends org.jetbrains.kotlin.resolve.extensions.AnalysisHandlerExtension> it = list.iterator();
        while (it.hasNext()) {
            AnalysisResult analysisResultAnalysisCompleted = it.next().analysisCompleted(project, moduleDescriptor, bindingTrace, collection);
            if (analysisResultAnalysisCompleted != null) {
                return analysisResultAnalysisCompleted;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ComponentProvider createContainer$default(TopDownAnalyzerFacadeForJVM topDownAnalyzerFacadeForJVM, Project project, Collection collection, BindingTrace bindingTrace, CompilerConfiguration compilerConfiguration, Function1 function1, Function2 function2, TargetEnvironment targetEnvironment, GlobalSearchScope globalSearchScope, List list, ImplicitsExtensionsResolutionFilter implicitsExtensionsResolutionFilter, List list2, List list3, Map map, int i, Object obj) {
        return topDownAnalyzerFacadeForJVM.createContainer(project, collection, bindingTrace, compilerConfiguration, function1, function2, (i & 64) != 0 ? CompilerEnvironment.INSTANCE : targetEnvironment, (i & 128) != 0 ? topDownAnalyzerFacadeForJVM.newModuleSearchScope(project, collection) : globalSearchScope, (i & 256) != 0 ? CollectionsKt.emptyList() : list, (i & 512) != 0 ? null : implicitsExtensionsResolutionFilter, (i & 1024) != 0 ? CollectionsKt.emptyList() : list2, (i & 2048) != 0 ? CollectionsKt.emptyList() : list3, (i & 4096) != 0 ? MapsKt.emptyMap() : map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createContainer$useJavac(StorageComponentContainer storageComponentContainer) {
        ContainerKt.registerSingleton(storageComponentContainer, JavacBasedClassFinder.class);
        ContainerKt.registerSingleton(storageComponentContainer, StubJavaResolverCache.class);
        ContainerKt.registerSingleton(storageComponentContainer, JavacBasedSourceElementFactory.class);
    }

    private final MutableModuleContext createModuleContext(Project project, CompilerConfiguration configuration, TargetPlatform platform, Map<ModuleCapability<?>, ? extends Object> capabilities) {
        ProjectContext ProjectContext = ContextKt.ProjectContext(project, "TopDownAnalyzer for JVM");
        JvmBuiltIns jvmBuiltIns = new JvmBuiltIns(ProjectContext.getStorageManager(), JvmBuiltIns.Kind.FROM_DEPENDENCIES);
        Name nameSpecial = Name.special("<" + ((String) configuration.getNotNull(CommonConfigurationKeys.MODULE_NAME)) + '>');
        nameSpecial.getClass();
        MutableModuleContext mutableModuleContextContextForNewModule = ContextKt.ContextForNewModule(ProjectContext, nameSpecial, jvmBuiltIns, platform, capabilities);
        jvmBuiltIns.setBuiltInsModule(mutableModuleContextContextForNewModule.getModule());
        return mutableModuleContextContextForNewModule;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    public final ComponentProvider createContainer(Project project, Collection<? extends KtFile> files, BindingTrace trace, CompilerConfiguration configuration, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> packagePartProvider, Function2<? super StorageManager, ? super Collection<? extends KtFile>, ? extends DeclarationProviderFactory> declarationProviderFactory, TargetEnvironment targetEnvironment, GlobalSearchScope sourceModuleSearchScope, List<? extends KotlinLibrary> klibList, ImplicitsExtensionsResolutionFilter implicitsResolutionFilter, List<ModuleDescriptorImpl> explicitModuleDependencyList, List<ModuleDescriptorImpl> explicitModuleFriendsList, Map<ModuleCapability<?>, ? extends Object> moduleCapabilities) {
        ArrayList arrayList;
        project.getClass();
        files.getClass();
        trace.getClass();
        configuration.getClass();
        packagePartProvider.getClass();
        declarationProviderFactory.getClass();
        targetEnvironment.getClass();
        sourceModuleSearchScope.getClass();
        klibList.getClass();
        explicitModuleDependencyList.getClass();
        explicitModuleFriendsList.getClass();
        moduleCapabilities.getClass();
        JvmTarget jvmTarget = (JvmTarget) configuration.get(JVMConfigurationKeys.JVM_TARGET, JvmTarget.DEFAULT);
        LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(configuration);
        TargetPlatform targetPlatformJvmPlatformByTargetVersion = JvmPlatforms.INSTANCE.jvmPlatformByTargetVersion(jvmTarget);
        MutableModuleContext mutableModuleContextCreateModuleContext = createModuleContext(project, configuration, targetPlatformJvmPlatformByTargetVersion, moduleCapabilities);
        StorageManager storageManager = mutableModuleContextCreateModuleContext.getStorageManager();
        ModuleDescriptorImpl module = mutableModuleContextCreateModuleContext.getModule();
        IncrementalCompilationComponents incrementalCompilationComponents = (IncrementalCompilationComponents) configuration.get(JVMConfigurationKeys.INCREMENTAL_COMPILATION_COMPONENTS);
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
        List list = (List) configuration.get(JVMConfigurationKeys.MODULES);
        if (list != null) {
            List list2 = list;
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(TargetIdKt.TargetId((Module) it.next()));
            }
        } else {
            arrayList = null;
        }
        SourceOrBinaryModuleClassResolver sourceOrBinaryModuleClassResolver = new SourceOrBinaryModuleClassResolver(sourceModuleSearchScope);
        JvmBuiltIns jvmBuiltIns = new JvmBuiltIns(storageManager, JvmBuiltIns.Kind.FALLBACK);
        ModuleDescriptorImpl builtInsModule = jvmBuiltIns.getBuiltInsModule();
        builtInsModule.getClass();
        InjectionKt.initialize(jvmBuiltIns, builtInsModule, languageVersionSettings);
        ModuleDescriptorImpl builtInsModule2 = jvmBuiltIns.getBuiltInsModule();
        builtInsModule2.getClass();
        TopDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1 topDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1 = configuration.getBoolean(JVMConfigurationKeys.USE_JAVAC) ? TopDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1.INSTANCE : null;
        Name nameSpecial = Name.special("<dependencies of " + ((String) configuration.getNotNull(CommonConfigurationKeys.MODULE_NAME)) + '>');
        nameSpecial.getClass();
        ArrayList arrayList2 = arrayList;
        MutableModuleContext mutableModuleContextContextForNewModule$default = ContextKt.ContextForNewModule$default(mutableModuleContextCreateModuleContext, nameSpecial, module.getBuiltIns(), targetPlatformJvmPlatformByTargetVersion, null, 16, null);
        GlobalSearchScope globalSearchScopeNotScope = GlobalSearchScope.notScope(sourceModuleSearchScope);
        globalSearchScopeNotScope.getClass();
        DeclarationProviderFactory declarationProviderFactory2 = DeclarationProviderFactory.EMPTY;
        declarationProviderFactory2.getClass();
        StorageComponentContainer storageComponentContainerCreateContainerForLazyResolveWithJava$default = InjectionKt.createContainerForLazyResolveWithJava$default(targetPlatformJvmPlatformByTargetVersion, mutableModuleContextContextForNewModule$default, trace, declarationProviderFactory2, globalSearchScopeNotScope, sourceOrBinaryModuleClassResolver, targetEnvironment, do_nothing2, doNothing2, doNothing4, doNothing6, (PackagePartProvider) packagePartProvider.invoke(globalSearchScopeNotScope), languageVersionSettings, true, topDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1, (JavaClassesTracker) null, implicitsResolutionFilter, (SealedClassInheritorsProvider) null, (OptimizingOptions) null, (Class) null, 950272, (Object) null);
        LookupTracker.DO_NOTHING do_nothing3 = do_nothing2;
        sourceOrBinaryModuleClassResolver.setCompiledCodeResolver((JavaDescriptorResolver) DslKt.getService(storageComponentContainerCreateContainerForLazyResolveWithJava$default, JavaDescriptorResolver.class));
        mutableModuleContextContextForNewModule$default.setDependencies(CollectionsKt.listOf(new ModuleDescriptorImpl[]{mutableModuleContextContextForNewModule$default.getModule(), builtInsModule2}));
        mutableModuleContextContextForNewModule$default.initializeModuleContents(new CompositePackageFragmentProvider(CollectionsKt.listOf(new PackageFragmentProviderOptimized[]{sourceOrBinaryModuleClassResolver.getCompiledCodeResolver().getPackageFragmentProvider(), DslKt.getService(storageComponentContainerCreateContainerForLazyResolveWithJava$default, JvmBuiltInsPackageFragmentProvider.class), DslKt.getService(storageComponentContainerCreateContainerForLazyResolveWithJava$default, OptionalAnnotationPackageFragmentProvider.class)}), "CompositeProvider@TopDownAnalyzerForJvm for dependencies " + mutableModuleContextContextForNewModule$default.getModule()));
        ModuleDescriptorImpl module2 = mutableModuleContextContextForNewModule$default.getModule();
        PackagePartProvider incrementalPackagePartProvider = (PackagePartProvider) packagePartProvider.invoke(sourceModuleSearchScope);
        if (arrayList2 != null && incrementalCompilationComponents != null) {
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(incrementalCompilationComponents.getIncrementalCache((TargetId) it2.next()));
            }
            incrementalPackagePartProvider = new IncrementalPackagePartProvider(incrementalPackagePartProvider, arrayList3);
        }
        PackagePartProvider packagePartProvider2 = incrementalPackagePartProvider;
        ModuleDescriptorImpl moduleDescriptorImpl = module2;
        StorageManager storageManager2 = storageManager;
        StorageComponentContainer storageComponentContainerCreateContainerForLazyResolveWithJava$default2 = InjectionKt.createContainerForLazyResolveWithJava$default(targetPlatformJvmPlatformByTargetVersion, mutableModuleContextCreateModuleContext, trace, (DeclarationProviderFactory) declarationProviderFactory.invoke(storageManager, files), sourceModuleSearchScope, sourceOrBinaryModuleClassResolver, targetEnvironment, do_nothing3, doNothing2, doNothing4, doNothing6, packagePartProvider2, languageVersionSettings, true, topDownAnalyzerFacadeForJVM$createContainer$configureJavaClassFinder$1, (JavaClassesTracker) configuration.get(ClassicFrontendSpecificJvmConfigurationKeys.JAVA_CLASSES_TRACKER), implicitsResolutionFilter, (SealedClassInheritorsProvider) null, (OptimizingOptions) null, (Class) null, 917504, (Object) null);
        InjectionKt.initJvmBuiltInsForTopDownAnalysis(storageComponentContainerCreateContainerForLazyResolveWithJava$default2);
        IncrementalPackagePartProvider incrementalPackagePartProvider2 = packagePartProvider2 instanceof IncrementalPackagePartProvider ? (IncrementalPackagePartProvider) packagePartProvider2 : null;
        if (incrementalPackagePartProvider2 != null) {
            incrementalPackagePartProvider2.setDeserializationConfiguration((DeserializationConfiguration) DslKt.getService(storageComponentContainerCreateContainerForLazyResolveWithJava$default2, DeserializationConfiguration.class));
        }
        sourceOrBinaryModuleClassResolver.setSourceCodeResolver((JavaDescriptorResolver) DslKt.getService(storageComponentContainerCreateContainerForLazyResolveWithJava$default2, JavaDescriptorResolver.class));
        ArrayList arrayList4 = new ArrayList();
        if (incrementalCompilationComponents != null && arrayList2 != null) {
            for (Iterator it3 = arrayList2.iterator(); it3.hasNext(); it3 = it3) {
                arrayList4.add(new IncrementalPackageFragmentProvider(files, module, storageManager2, (TargetId) it3.next()));
            }
        }
        arrayList4.add(((JavaDescriptorResolver) DslKt.getService(storageComponentContainerCreateContainerForLazyResolveWithJava$default2, JavaDescriptorResolver.class)).getPackageFragmentProvider());
        Project project2 = project;
        Iterator it4 = PackageFragmentProviderExtension.Companion.getInstances(project2).iterator();
        while (it4.hasNext()) {
            Project project3 = project2;
            StorageManager storageManager3 = storageManager2;
            LookupTracker.DO_NOTHING do_nothing4 = do_nothing3;
            ModuleDescriptorImpl moduleDescriptorImpl2 = moduleDescriptorImpl;
            PackageFragmentProvider packageFragmentProvider = ((PackageFragmentProviderExtension) it4.next()).getPackageFragmentProvider(project3, module, storageManager3, trace, (ModuleInfo) null, do_nothing4);
            if (packageFragmentProvider != null) {
                arrayList4.add(packageFragmentProvider);
            }
            storageManager2 = storageManager3;
            moduleDescriptorImpl = moduleDescriptorImpl2;
            project2 = project;
            do_nothing3 = do_nothing4;
        }
        ModuleDescriptorImpl moduleDescriptorImpl3 = moduleDescriptorImpl;
        module.setDependencies(CollectionsKt.plus(CollectionsKt.plus(CollectionsKt.listOf(new ModuleDescriptorImpl[]{module, moduleDescriptorImpl3, builtInsModule2}), TopDownAnalyzerFacadeForJVMKt.getKlibModules(klibList, moduleDescriptorImpl3)), explicitModuleDependencyList), SetsKt.plus(SetsKt.setOf(moduleDescriptorImpl3), explicitModuleFriendsList));
        module.initialize(new CompositePackageFragmentProvider(CollectionsKt.plus(CollectionsKt.listOf(new PackageFragmentProvider[]{((KotlinCodeAnalyzer) DslKt.getService(storageComponentContainerCreateContainerForLazyResolveWithJava$default2, KotlinCodeAnalyzer.class)).getPackageFragmentProvider(), DslKt.getService(storageComponentContainerCreateContainerForLazyResolveWithJava$default2, OptionalAnnotationPackageFragmentProvider.class)}), arrayList4), "CompositeProvider@TopDownAnalzyerForJvm for " + module));
        return storageComponentContainerCreateContainerForLazyResolveWithJava$default2;
    }

    public final GlobalSearchScope newModuleSearchScope(Project project, Collection<? extends KtFile> files) {
        project.getClass();
        files.getClass();
        Collection<? extends KtFile> collection = files;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((KtFile) it.next()).getVirtualFile());
        }
        GlobalSearchScope globalSearchScopeUniteWith = GlobalSearchScope.filesScope(project, CollectionsKt.toSet(arrayList)).uniteWith(new AllJavaSourcesInProjectScope(project));
        globalSearchScopeUniteWith.getClass();
        return globalSearchScopeUniteWith;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    @JvmStatic
    public static final AnalysisResult analyzeFilesWithJavaIntegration(Project project, Collection<? extends KtFile> collection, BindingTrace bindingTrace, CompilerConfiguration compilerConfiguration, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1, Function2<? super StorageManager, ? super Collection<? extends KtFile>, ? extends DeclarationProviderFactory> function2) {
        project.getClass();
        collection.getClass();
        bindingTrace.getClass();
        compilerConfiguration.getClass();
        function1.getClass();
        function2.getClass();
        return analyzeFilesWithJavaIntegration$default(project, collection, bindingTrace, compilerConfiguration, function1, function2, null, null, null, null, null, 1984, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    @JvmStatic
    public static final AnalysisResult analyzeFilesWithJavaIntegration(Project project, Collection<? extends KtFile> collection, BindingTrace bindingTrace, CompilerConfiguration compilerConfiguration, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1, Function2<? super StorageManager, ? super Collection<? extends KtFile>, ? extends DeclarationProviderFactory> function2, GlobalSearchScope globalSearchScope) {
        project.getClass();
        collection.getClass();
        bindingTrace.getClass();
        compilerConfiguration.getClass();
        function1.getClass();
        function2.getClass();
        globalSearchScope.getClass();
        return analyzeFilesWithJavaIntegration$default(project, collection, bindingTrace, compilerConfiguration, function1, function2, globalSearchScope, null, null, null, null, 1920, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    @JvmStatic
    public static final AnalysisResult analyzeFilesWithJavaIntegration(Project project, Collection<? extends KtFile> collection, BindingTrace bindingTrace, CompilerConfiguration compilerConfiguration, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1, Function2<? super StorageManager, ? super Collection<? extends KtFile>, ? extends DeclarationProviderFactory> function2, GlobalSearchScope globalSearchScope, List<? extends KotlinLibrary> list) {
        project.getClass();
        collection.getClass();
        bindingTrace.getClass();
        compilerConfiguration.getClass();
        function1.getClass();
        function2.getClass();
        globalSearchScope.getClass();
        list.getClass();
        return analyzeFilesWithJavaIntegration$default(project, collection, bindingTrace, compilerConfiguration, function1, function2, globalSearchScope, list, null, null, null, 1792, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    @JvmStatic
    public static final AnalysisResult analyzeFilesWithJavaIntegration(Project project, Collection<? extends KtFile> collection, BindingTrace bindingTrace, CompilerConfiguration compilerConfiguration, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1, Function2<? super StorageManager, ? super Collection<? extends KtFile>, ? extends DeclarationProviderFactory> function2, GlobalSearchScope globalSearchScope, List<? extends KotlinLibrary> list, List<ModuleDescriptorImpl> list2) {
        project.getClass();
        collection.getClass();
        bindingTrace.getClass();
        compilerConfiguration.getClass();
        function1.getClass();
        function2.getClass();
        globalSearchScope.getClass();
        list.getClass();
        list2.getClass();
        return analyzeFilesWithJavaIntegration$default(project, collection, bindingTrace, compilerConfiguration, function1, function2, globalSearchScope, list, list2, null, null, 1536, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    @JvmStatic
    public static final AnalysisResult analyzeFilesWithJavaIntegration(Project project, Collection<? extends KtFile> collection, BindingTrace bindingTrace, CompilerConfiguration compilerConfiguration, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1, Function2<? super StorageManager, ? super Collection<? extends KtFile>, ? extends DeclarationProviderFactory> function2, GlobalSearchScope globalSearchScope, List<? extends KotlinLibrary> list, List<ModuleDescriptorImpl> list2, List<ModuleDescriptorImpl> list3) {
        project.getClass();
        collection.getClass();
        bindingTrace.getClass();
        compilerConfiguration.getClass();
        function1.getClass();
        function2.getClass();
        globalSearchScope.getClass();
        list.getClass();
        list2.getClass();
        list3.getClass();
        return analyzeFilesWithJavaIntegration$default(project, collection, bindingTrace, compilerConfiguration, function1, function2, globalSearchScope, list, list2, list3, null, 1024, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    @JvmStatic
    public static final AnalysisResult analyzeFilesWithJavaIntegration(Project project, Collection<? extends KtFile> collection, BindingTrace bindingTrace, CompilerConfiguration compilerConfiguration, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1) {
        project.getClass();
        collection.getClass();
        bindingTrace.getClass();
        compilerConfiguration.getClass();
        function1.getClass();
        return analyzeFilesWithJavaIntegration$default(project, collection, bindingTrace, compilerConfiguration, function1, null, null, null, null, null, null, 2016, null);
    }
}
