package org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline;

import com.intellij.core.CoreJavaFileManager;
import com.intellij.mock.MockProject;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.openapi.vfs.local.CoreLocalFileSystem;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.GlobalSearchScope;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.actualizer.IrActualizedResult;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.backend.jvm.JvmGeneratorExtensionsImpl;
import org.jetbrains.kotlin.backend.jvm.JvmIrCodegenFactory;
import org.jetbrains.kotlin.backend.jvm.JvmIrSpecialAnnotationSymbolProvider;
import org.jetbrains.kotlin.builtins.DefaultBuiltIns;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.LegacyK2CliPipeline;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.config.KotlinSourceRoot;
import org.jetbrains.kotlin.cli.jvm.compiler.ClasspathRootsResolver;
import org.jetbrains.kotlin.cli.jvm.compiler.CliCompilerUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.CliMetadataFinderFactory;
import org.jetbrains.kotlin.cli.jvm.compiler.CliVirtualFileFinderFactory;
import org.jetbrains.kotlin.cli.jvm.compiler.CompatKt;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.JavaLanguageLevelKt;
import org.jetbrains.kotlin.cli.jvm.compiler.JvmPackagePartProvider;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCliJavaFileManagerImpl;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.JvmCompilerPipelineKt;
import org.jetbrains.kotlin.cli.jvm.config.JavaSourceRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootBase;
import org.jetbrains.kotlin.cli.jvm.config.JvmModulePathRoot;
import org.jetbrains.kotlin.cli.jvm.config.VirtualJvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.index.JavaRoot;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesDynamicCompoundIndex;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndexImpl;
import org.jetbrains.kotlin.cli.jvm.index.SingleJavaFileRootsIndex;
import org.jetbrains.kotlin.cli.jvm.modules.CliJavaModuleFinder;
import org.jetbrains.kotlin.cli.jvm.modules.CliJavaModuleResolver;
import org.jetbrains.kotlin.codegen.ClassBuilderFactories;
import org.jetbrains.kotlin.codegen.ClassBuilderFactory;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.compiler.plugin.ExtensionPointUtilsKt;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.JVMConfigurationKeysKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.backend.Fir2IrCommonMemberStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.jvm.FirDirectJavaActualDeclarationExtractor;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendClassResolver;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendExtension;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.jvm.JvmFir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.pipeline.ConvertToIrKt;
import org.jetbrains.kotlin.fir.pipeline.Fir2IrActualizedResult;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.backend.jvm.serialization.JvmDescriptorMangler;
import org.jetbrains.kotlin.ir.backend.jvm.serialization.JvmIrMangler;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.util.SymbolTable;
import org.jetbrains.kotlin.load.kotlin.MetadataFinderFactory;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinderFactory;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.modules.TargetId;
import org.jetbrains.kotlin.psi2ir.generators.fragments.EvaluatorFragmentInfo;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModule;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleResolver;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a7\u0010\f\u001a\u00020\r*\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\u001e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f\u001a*\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0016\u0010'\u001a\u0004\u0018\u00010!*\u00020%2\u0006\u0010(\u001a\u00020)H\u0002\u001a&\u0010*\u001a\u0004\u0018\u00010!*\u00020%2\u0006\u0010\"\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006."}, d2 = {"convertAnalyzedFirToIr", "Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerIrBackendInput;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "targetId", "Lorg/jetbrains/kotlin/modules/TargetId;", "frontendOutput", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerEnvironment;", "convertAnalyzedFirToIr-P7isl5Q", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/modules/TargetId;Ljava/util/List;Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerEnvironment;)Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/ModuleCompilerIrBackendInput;", "convertToIrAndActualizeForJvm", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "fir2IrExtensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "diagnosticsReporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "irGeneratorExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/common/extensions/IrGenerationExtension;", "convertToIrAndActualizeForJvm-_I1IR-w", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;Ljava/util/Collection;)Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "generateCodeFromIr", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "input", "createProjectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "parentDisposable", "Lcom/intellij/openapi/Disposable;", "configFiles", "Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "contentRootToVirtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "root", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmContentRootBase;", "localFileSystem", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "jarFileSystem", "findJarRoot", "file", "Ljava/io/File;", "findExistingRoot", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmContentRoot;", "rootDescription", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmCompilerPipelineKt {
    public static List a(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        return CollectionsKt.listOfNotNull(FirDirectJavaActualDeclarationExtractor.INSTANCE.initializeIfNeeded(fir2IrComponents));
    }

    public static VirtualFile b(CoreLocalFileSystem coreLocalFileSystem, KotlinCoreEnvironment.ProjectEnvironment projectEnvironment, CompilerConfiguration compilerConfiguration, JvmContentRootBase jvmContentRootBase) {
        jvmContentRootBase.getClass();
        return contentRootToVirtualFile(jvmContentRootBase, coreLocalFileSystem, projectEnvironment.getJarFileSystem(), compilerConfiguration);
    }

    public static PackagePartProvider c(CompilerConfiguration compilerConfiguration, GlobalSearchScope globalSearchScope) {
        globalSearchScope.getClass();
        return new JvmPackagePartProvider(CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration), globalSearchScope);
    }

    private static final VirtualFile contentRootToVirtualFile(JvmContentRootBase jvmContentRootBase, VirtualFileSystem virtualFileSystem, VirtualFileSystem virtualFileSystem2, CompilerConfiguration compilerConfiguration) {
        if (jvmContentRootBase instanceof JvmClasspathRoot) {
            JvmClasspathRoot jvmClasspathRoot = (JvmClasspathRoot) jvmContentRootBase;
            return jvmClasspathRoot.getFile().isFile() ? findJarRoot(virtualFileSystem2, jvmClasspathRoot.getFile()) : findExistingRoot(virtualFileSystem, (JvmContentRoot) jvmContentRootBase, "Classpath entry", compilerConfiguration);
        }
        if (jvmContentRootBase instanceof JvmModulePathRoot) {
            JvmModulePathRoot jvmModulePathRoot = (JvmModulePathRoot) jvmContentRootBase;
            return jvmModulePathRoot.getFile().isFile() ? findJarRoot(virtualFileSystem2, jvmModulePathRoot.getFile()) : findExistingRoot(virtualFileSystem, (JvmContentRoot) jvmContentRootBase, "Java module root", compilerConfiguration);
        }
        if (jvmContentRootBase instanceof JavaSourceRoot) {
            return findExistingRoot(virtualFileSystem, (JvmContentRoot) jvmContentRootBase, "Java source root", compilerConfiguration);
        }
        if (jvmContentRootBase instanceof VirtualJvmClasspathRoot) {
            return ((VirtualJvmClasspathRoot) jvmContentRootBase).getFile();
        }
        qu7.a("Unexpected root: ", jvmContentRootBase);
        return null;
    }

    @LegacyK2CliPipeline
    /* JADX INFO: renamed from: convertAnalyzedFirToIr-P7isl5Q, reason: not valid java name */
    public static final ModuleCompilerIrBackendInput m22convertAnalyzedFirToIrP7isl5Q(CompilerConfiguration compilerConfiguration, TargetId targetId, List<? extends SingleModuleFrontendOutput> list, ModuleCompilerEnvironment moduleCompilerEnvironment) {
        compilerConfiguration.getClass();
        targetId.getClass();
        list.getClass();
        moduleCompilerEnvironment.getClass();
        JvmFir2IrExtensions jvmFir2IrExtensions = new JvmFir2IrExtensions(compilerConfiguration);
        Fir2IrActualizedResult fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw = m23convertToIrAndActualizeForJvm_I1IRw(list, jvmFir2IrExtensions, compilerConfiguration, moduleCompilerEnvironment.getDiagnosticsReporter(), ExtensionPointUtilsKt.getCompilerExtensions(compilerConfiguration, IrGenerationExtension.Companion));
        return new ModuleCompilerIrBackendInput(targetId, compilerConfiguration, jvmFir2IrExtensions, fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getIrModuleFragment(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getComponents(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getPluginContext(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getIrActualizedResult(), fir2IrActualizedResultM23convertToIrAndActualizeForJvm_I1IRw.getSymbolTable());
    }

    /* JADX INFO: renamed from: convertToIrAndActualizeForJvm-_I1IR-w, reason: not valid java name */
    public static final Fir2IrActualizedResult m23convertToIrAndActualizeForJvm_I1IRw(List<? extends SingleModuleFrontendOutput> list, Fir2IrExtensions fir2IrExtensions, CompilerConfiguration compilerConfiguration, BaseDiagnosticsCollector baseDiagnosticsCollector, Collection<? extends IrGenerationExtension> collection) {
        list.getClass();
        fir2IrExtensions.getClass();
        compilerConfiguration.getClass();
        baseDiagnosticsCollector.getClass();
        collection.getClass();
        return ConvertToIrKt.m579convertToIrAndActualizeMT2kVtw(list, fir2IrExtensions, Fir2IrConfiguration.INSTANCE.forJvmCompilation(compilerConfiguration, baseDiagnosticsCollector), collection, JvmIrMangler.INSTANCE, FirJvmVisibilityConverter.INSTANCE, DefaultBuiltIns.Companion.getInstance(), JvmCompilerPipelineKt$convertToIrAndActualizeForJvm$1.INSTANCE, JvmIrSpecialAnnotationSymbolProvider.INSTANCE, ((Boolean) CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue() ? new Function1() { // from class: dw7
            public final Object invoke(Object obj) {
                return JvmCompilerPipelineKt.d((Fir2IrComponents) obj);
            }
        } : new Function1() { // from class: ew7
            public final Object invoke(Object obj) {
                return JvmCompilerPipelineKt.a((Fir2IrComponents) obj);
            }
        }, (512 & 512) != 0 ? new Fir2IrCommonMemberStorage() : null, (512 & 1024) != 0 ? new Function1() { // from class: zx2
            public final Object invoke(Object obj) {
                return ConvertToIrKt.a((IrModuleFragment) obj);
            }
        } : null);
    }

    public static final VfsBasedProjectEnvironment createProjectEnvironment(final CompilerConfiguration compilerConfiguration, Disposable disposable, EnvironmentConfigFiles environmentConfigFiles) {
        String absolutePath;
        boolean z;
        Module module;
        compilerConfiguration.getClass();
        disposable.getClass();
        environmentConfigFiles.getClass();
        CompatKt.setupIdeaStandaloneExecution();
        KotlinCoreEnvironment.Companion companion = KotlinCoreEnvironment.INSTANCE;
        final KotlinCoreEnvironment.ProjectEnvironment projectEnvironment = new KotlinCoreEnvironment.ProjectEnvironment(disposable, companion.getOrCreateApplicationEnvironment(disposable, compilerConfiguration), compilerConfiguration);
        companion.configureProjectEnvironment(projectEnvironment, compilerConfiguration, environmentConfigFiles);
        MockProject project = projectEnvironment.getProject();
        project.getClass();
        final VirtualFileSystem localFileSystem = projectEnvironment.getEnvironment().getLocalFileSystem();
        localFileSystem.getClass();
        Object service = project.getService(CoreJavaFileManager.class);
        service.getClass();
        KotlinCliJavaFileManagerImpl kotlinCliJavaFileManagerImpl = (KotlinCliJavaFileManagerImpl) service;
        Integer num = (Integer) compilerConfiguration.get(JVMConfigurationKeys.JDK_RELEASE);
        CliJavaModuleFinder cliJavaModuleFinder = new CliJavaModuleFinder(JVMConfigurationKeysKt.getJdkHome(compilerConfiguration), compilerConfiguration, kotlinCliJavaFileManagerImpl, project, num);
        List list = (List) compilerConfiguration.get(JVMConfigurationKeys.MODULES);
        if (list == null || (module = (Module) CollectionsKt.singleOrNull(list)) == null || (absolutePath = module.getOutputDir()) == null) {
            File file = (File) compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_DIRECTORY);
            absolutePath = file != null ? file.getAbsolutePath() : null;
        }
        List<? extends ContentRoot> list2 = compilerConfiguration.getList(CLIConfigurationKeys.CONTENT_ROOTS);
        PsiManager psiManager = PsiManager.getInstance(project);
        psiManager.getClass();
        List list3 = compilerConfiguration.getList(JVMConfigurationKeys.ADDITIONAL_JAVA_MODULES);
        Function1 function1 = new Function1() { // from class: bw7
            public final Object invoke(Object obj) {
                return JvmCompilerPipelineKt.b(localFileSystem, projectEnvironment, compilerConfiguration, (JvmContentRootBase) obj);
            }
        };
        boolean z2 = !compilerConfiguration.getBoolean(CLIConfigurationKeys.ALLOW_KOTLIN_PACKAGE);
        VirtualFile virtualFileFindFileByPath = absolutePath != null ? localFileSystem.findFileByPath(absolutePath) : null;
        List<? extends ContentRoot> list4 = list2;
        if (!(list4 instanceof Collection) || !list4.isEmpty()) {
            Iterator it = list4.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (((ContentRoot) it.next()) instanceof KotlinSourceRoot) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        ClasspathRootsResolver classpathRootsResolver = new ClasspathRootsResolver(psiManager, compilerConfiguration, list3, function1, cliJavaModuleFinder, z2, virtualFileFindFileByPath, kotlinCliJavaFileManagerImpl, num, z);
        ClasspathRootsResolver.RootsAndModules rootsAndModulesConvertClasspathRoots = classpathRootsResolver.convertClasspathRoots(list2);
        List<JavaRoot> listComponent1 = rootsAndModulesConvertClasspathRoots.component1();
        List<JavaModule> listComponent2 = rootsAndModulesConvertClasspathRoots.component2();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listComponent1) {
            VirtualFile file2 = ((JavaRoot) obj).getFile();
            if (file2.isDirectory() || !Intrinsics.areEqual(file2.getExtension(), "java")) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list5 = (List) pair.component1();
        List list6 = (List) pair.component2();
        JvmDependenciesDynamicCompoundIndex jvmDependenciesDynamicCompoundIndex = new JvmDependenciesDynamicCompoundIndex(true);
        jvmDependenciesDynamicCompoundIndex.addIndex(new JvmDependenciesIndexImpl(list5, true));
        Iterator it2 = jvmDependenciesDynamicCompoundIndex.getIndexedRoots().iterator();
        while (it2.hasNext()) {
            projectEnvironment.addSourcesToClasspath(((JavaRoot) it2.next()).getFile());
        }
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(compilerConfiguration);
        project.registerService(JavaModuleResolver.class, new CliJavaModuleResolver(classpathRootsResolver.getJavaModuleGraph(), listComponent2, SequencesKt.toList(cliJavaModuleFinder.getSystemModules()), project));
        CliVirtualFileFinderFactory cliVirtualFileFinderFactory = new CliVirtualFileFinderFactory(jvmDependenciesDynamicCompoundIndex, num != null, perfManager);
        project.registerService(VirtualFileFinderFactory.class, cliVirtualFileFinderFactory);
        project.registerService(MetadataFinderFactory.class, new CliMetadataFinderFactory(cliVirtualFileFinderFactory));
        JavaLanguageLevelKt.setupHighestLanguageLevel(project);
        ProjectEnvironmentWithCoreEnvironmentEmulation projectEnvironmentWithCoreEnvironmentEmulation = new ProjectEnvironmentWithCoreEnvironmentEmulation(project, CollectionsKt.listOfNotNull(new VirtualFileSystem[]{projectEnvironment.getJarFileSystem(), projectEnvironment.getEnvironment().getJrtFileSystem(), localFileSystem}), new Function1() { // from class: cw7
            public final Object invoke(Object obj2) {
                return JvmCompilerPipelineKt.c(compilerConfiguration, (GlobalSearchScope) obj2);
            }
        }, listComponent1, compilerConfiguration);
        kotlinCliJavaFileManagerImpl.initialize(jvmDependenciesDynamicCompoundIndex, projectEnvironmentWithCoreEnvironmentEmulation.getPackagePartProviders(), new SingleJavaFileRootsIndex(list6), compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_PSI_CLASS_FILES_READING), perfManager);
        return projectEnvironmentWithCoreEnvironmentEmulation;
    }

    public static List d(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        return CollectionsKt.emptyList();
    }

    private static final VirtualFile findExistingRoot(VirtualFileSystem virtualFileSystem, JvmContentRoot jvmContentRoot, String str, CompilerConfiguration compilerConfiguration) {
        VirtualFile virtualFileFindFileByPath = virtualFileSystem.findFileByPath(jvmContentRoot.getFile().getAbsolutePath());
        if (virtualFileFindFileByPath == null) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getROOTS_RESOLUTION_WARNING(), str + " points to a non-existent location: " + jvmContentRoot.getFile(), null, 4, null);
        }
        return virtualFileFindFileByPath;
    }

    private static final VirtualFile findJarRoot(VirtualFileSystem virtualFileSystem, File file) {
        return virtualFileSystem.findFileByPath(file + "!/");
    }

    @LegacyK2CliPipeline
    public static final GenerationState generateCodeFromIr(ModuleCompilerIrBackendInput moduleCompilerIrBackendInput, ModuleCompilerEnvironment moduleCompilerEnvironment) throws Throwable {
        JvmIrCodegenFactory.CodegenInput codegenInputInvokeLowerings;
        JvmIrCodegenFactory jvmIrCodegenFactory;
        List actualizedExpectDeclarations;
        List actualizedExpectDeclarations2;
        moduleCompilerIrBackendInput.getClass();
        moduleCompilerEnvironment.getClass();
        Project project = moduleCompilerEnvironment.getProjectEnvironment().getProject();
        ModuleDescriptor descriptor = moduleCompilerIrBackendInput.getIrModuleFragment().getDescriptor();
        CompilerConfiguration configuration = moduleCompilerIrBackendInput.getConfiguration();
        ClassBuilderFactory classBuilderFactory = ClassBuilderFactories.BINARIES;
        classBuilderFactory.getClass();
        GenerationState generationState = new GenerationState(project, descriptor, configuration, classBuilderFactory, null, moduleCompilerIrBackendInput.getTargetId(), moduleCompilerIrBackendInput.getTargetId().getName(), new FirJvmBackendClassResolver(moduleCompilerIrBackendInput.getComponents()), false, moduleCompilerEnvironment.getDiagnosticsReporter(), null, 1296, null);
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(moduleCompilerIrBackendInput.getConfiguration());
        if (perfManager != null) {
            perfManager.notifyCurrentPhaseFinishedIfNeeded();
        }
        PhaseType phaseType = PhaseType.IrLowering;
        Set<FirDeclaration> setExtractFirDeclarations = null;
        if (perfManager == null) {
            IrModuleFragment irModuleFragment = moduleCompilerIrBackendInput.getIrModuleFragment();
            IrBuiltIns irBuiltIns = moduleCompilerIrBackendInput.getPluginContext().getIrBuiltIns();
            SymbolTable symbolTable = moduleCompilerIrBackendInput.getSymbolTable();
            List<IrProvider> irProviders = moduleCompilerIrBackendInput.getComponents().getIrProviders();
            JvmFir2IrExtensions extensions = moduleCompilerIrBackendInput.getExtensions();
            Fir2IrComponents components = moduleCompilerIrBackendInput.getComponents();
            IrActualizedResult irActualizedResult = moduleCompilerIrBackendInput.getIrActualizedResult();
            if (irActualizedResult != null && (actualizedExpectDeclarations2 = irActualizedResult.getActualizedExpectDeclarations()) != null) {
                setExtractFirDeclarations = VariousUtilsKt.extractFirDeclarations(actualizedExpectDeclarations2);
            }
            JvmIrCodegenFactory.BackendInput backendInput = new JvmIrCodegenFactory.BackendInput(irModuleFragment, irBuiltIns, symbolTable, irProviders, extensions, new FirJvmBackendExtension(components, setExtractFirDeclarations), moduleCompilerIrBackendInput.getPluginContext());
            jvmIrCodegenFactory = new JvmIrCodegenFactory(moduleCompilerIrBackendInput.getConfiguration(), (JvmDescriptorMangler) null, (SymbolTable) null, (JvmGeneratorExtensionsImpl) null, (EvaluatorFragmentInfo) null, (JvmIrCodegenFactory.IdeCodegenSettings) null, 62, (DefaultConstructorMarker) null);
            codegenInputInvokeLowerings = jvmIrCodegenFactory.invokeLowerings(generationState, backendInput);
        } else {
            try {
                perfManager.notifyPhaseStarted(phaseType);
                IrModuleFragment irModuleFragment2 = moduleCompilerIrBackendInput.getIrModuleFragment();
                IrBuiltIns irBuiltIns2 = moduleCompilerIrBackendInput.getPluginContext().getIrBuiltIns();
                SymbolTable symbolTable2 = moduleCompilerIrBackendInput.getSymbolTable();
                List<IrProvider> irProviders2 = moduleCompilerIrBackendInput.getComponents().getIrProviders();
                JvmFir2IrExtensions extensions2 = moduleCompilerIrBackendInput.getExtensions();
                Fir2IrComponents components2 = moduleCompilerIrBackendInput.getComponents();
                IrActualizedResult irActualizedResult2 = moduleCompilerIrBackendInput.getIrActualizedResult();
                if (irActualizedResult2 != null && (actualizedExpectDeclarations = irActualizedResult2.getActualizedExpectDeclarations()) != null) {
                    setExtractFirDeclarations = VariousUtilsKt.extractFirDeclarations(actualizedExpectDeclarations);
                }
                JvmIrCodegenFactory.BackendInput backendInput2 = new JvmIrCodegenFactory.BackendInput(irModuleFragment2, irBuiltIns2, symbolTable2, irProviders2, extensions2, new FirJvmBackendExtension(components2, setExtractFirDeclarations), moduleCompilerIrBackendInput.getPluginContext());
                JvmIrCodegenFactory jvmIrCodegenFactory2 = new JvmIrCodegenFactory(moduleCompilerIrBackendInput.getConfiguration(), (JvmDescriptorMangler) null, (SymbolTable) null, (JvmGeneratorExtensionsImpl) null, (EvaluatorFragmentInfo) null, (JvmIrCodegenFactory.IdeCodegenSettings) null, 62, (DefaultConstructorMarker) null);
                JvmIrCodegenFactory.CodegenInput codegenInputInvokeLowerings2 = jvmIrCodegenFactory2.invokeLowerings(generationState, backendInput2);
                perfManager.notifyPhaseFinished(phaseType);
                codegenInputInvokeLowerings = codegenInputInvokeLowerings2;
                jvmIrCodegenFactory = jvmIrCodegenFactory2;
            } catch (Throwable th) {
                perfManager.notifyPhaseFinished(phaseType);
                throw th;
            }
        }
        jvmIrCodegenFactory.invokeCodegen(codegenInputInvokeLowerings);
        PhaseType phaseType2 = PhaseType.Backend;
        if (perfManager == null) {
            if (JVMConfigurationKeysKt.getOutputDirectory(moduleCompilerIrBackendInput.getConfiguration()) != null) {
                CliCompilerUtilsKt.writeOutputsIfNeeded(moduleCompilerEnvironment.getProjectEnvironment().getProject(), moduleCompilerIrBackendInput.getConfiguration(), CommonConfigurationKeysKt.getMessageCollector(moduleCompilerIrBackendInput.getConfiguration()), moduleCompilerEnvironment.getDiagnosticsReporter().getHasErrors(), CollectionsKt.listOf(generationState), null);
            }
            return generationState;
        }
        try {
            perfManager.notifyPhaseStarted(phaseType2);
            if (JVMConfigurationKeysKt.getOutputDirectory(moduleCompilerIrBackendInput.getConfiguration()) != null) {
                CliCompilerUtilsKt.writeOutputsIfNeeded(moduleCompilerEnvironment.getProjectEnvironment().getProject(), moduleCompilerIrBackendInput.getConfiguration(), CommonConfigurationKeysKt.getMessageCollector(moduleCompilerIrBackendInput.getConfiguration()), moduleCompilerEnvironment.getDiagnosticsReporter().getHasErrors(), CollectionsKt.listOf(generationState), null);
            }
            Unit unit = Unit.INSTANCE;
            return generationState;
        } finally {
            perfManager.notifyPhaseFinished(phaseType2);
        }
    }
}
