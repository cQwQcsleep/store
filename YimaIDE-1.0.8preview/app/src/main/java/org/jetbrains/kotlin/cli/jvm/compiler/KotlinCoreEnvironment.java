package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.codeInsight.ExternalAnnotationsManager;
import com.intellij.codeInsight.InferredAnnotationsManager;
import com.intellij.core.CoreApplicationEnvironment;
import com.intellij.core.CoreJavaFileManager;
import com.intellij.core.CoreProjectEnvironment;
import com.intellij.core.JavaCoreProjectEnvironment;
import com.intellij.lang.java.JavaParserDefinition;
import com.intellij.mock.MockProject;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.TransactionGuard;
import com.intellij.openapi.application.TransactionGuardImpl;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.ExtensionsArea;
import com.intellij.openapi.extensions.ProjectExtensionPointName;
import com.intellij.openapi.extensions.impl.ExtensionsAreaImpl;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.PersistentFSConstants;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSetFactory;
import com.intellij.openapi.vfs.VirtualFileSetFactoryKt;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.openapi.vfs.impl.ZipHandler;
import com.intellij.pom.java.InternalPersistentJavaLanguageLevelReaderService;
import com.intellij.psi.PsiElementFinder;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiNameHelper;
import com.intellij.psi.impl.JavaClassSupersImpl;
import com.intellij.psi.impl.PsiElementFinderImpl;
import com.intellij.psi.impl.PsiJavaModuleModificationTracker;
import com.intellij.psi.impl.PsiNameHelperImpl;
import com.intellij.psi.impl.PsiTreeChangePreprocessor;
import com.intellij.psi.impl.file.impl.JavaFileManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.JavaClassSupers;
import com.intellij.util.lang.UrlClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipFile;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.K1Deprecation;
import org.jetbrains.kotlin.asJava.KotlinAsJavaSupport;
import org.jetbrains.kotlin.asJava.LightClassGenerationSupport;
import org.jetbrains.kotlin.asJava.finder.JavaElementFinder;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.FrontendConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.CliModuleVisibilityManagerImpl;
import org.jetbrains.kotlin.cli.common.CompilerSystemProperties;
import org.jetbrains.kotlin.cli.common.PropertiesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.config.KotlinSourceRoot;
import org.jetbrains.kotlin.cli.common.environment.UtilKt;
import org.jetbrains.kotlin.cli.common.extensions.ScriptEvaluationExtension;
import org.jetbrains.kotlin.cli.common.extensions.ShellExtension;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.jvm.config.JavaSourceRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootBase;
import org.jetbrains.kotlin.cli.jvm.config.JvmModulePathRoot;
import org.jetbrains.kotlin.cli.jvm.config.VirtualJvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.index.JavaRoot;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesDynamicCompoundIndex;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndexImpl;
import org.jetbrains.kotlin.cli.jvm.index.SingleJavaFileRootsIndex;
import org.jetbrains.kotlin.cli.jvm.modules.CliJavaModuleFinder;
import org.jetbrains.kotlin.cli.jvm.modules.CliJavaModuleResolver;
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar;
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar;
import org.jetbrains.kotlin.compiler.plugin.ExtensionRegistrationUtilsKt;
import org.jetbrains.kotlin.config.AppendJavaSourceRootsHandlerKeyKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.CollectAdditionalSourcesExtension;
import org.jetbrains.kotlin.extensions.CompilerConfigurationExtension;
import org.jetbrains.kotlin.extensions.DeclarationAttributeAltererExtension;
import org.jetbrains.kotlin.extensions.PreprocessedVirtualFileFactoryExtension;
import org.jetbrains.kotlin.extensions.ProcessSourcesBeforeCompilingExtension;
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor;
import org.jetbrains.kotlin.extensions.TypeAttributeTranslatorExtension;
import org.jetbrains.kotlin.extensions.internal.CandidateInterceptor;
import org.jetbrains.kotlin.extensions.internal.TypeResolutionInterceptor;
import org.jetbrains.kotlin.idea.KotlinFileType;
import org.jetbrains.kotlin.load.java.structure.impl.source.JavaElementSourceFactory;
import org.jetbrains.kotlin.load.java.structure.impl.source.JavaFixedElementSourceFactory;
import org.jetbrains.kotlin.load.kotlin.KotlinBinaryClassCache;
import org.jetbrains.kotlin.load.kotlin.MetadataFinderFactory;
import org.jetbrains.kotlin.load.kotlin.ModuleVisibilityManager;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinderFactory;
import org.jetbrains.kotlin.modules.Module;
import org.jetbrains.kotlin.parsing.KotlinParserDefinition;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.resolve.CodeAnalyzerInitializer;
import org.jetbrains.kotlin.resolve.diagnostics.DiagnosticSuppressor;
import org.jetbrains.kotlin.resolve.extensions.AssignResolutionAltererExtension;
import org.jetbrains.kotlin.resolve.extensions.ExtraImportsProviderExtension;
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension;
import org.jetbrains.kotlin.resolve.jvm.KotlinJavaPsiFacade;
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisHandlerExtension;
import org.jetbrains.kotlin.resolve.jvm.extensions.PackageFragmentProviderExtension;
import org.jetbrains.kotlin.resolve.jvm.extensions.SyntheticJavaResolveExtension;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModule;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleResolver;
import org.jetbrains.kotlin.resolve.lazy.declarations.CliDeclarationProviderFactoryService;
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactoryService;
import org.jetbrains.kotlin.serialization.DescriptorSerializerPlugin;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.utils.PathUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 E2\u00020\u0001:\u0002DEB!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0014\u0010\"\u001a\u00020\u001f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u0014J\u000e\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020'J\u0014\u0010/\u001a\u0002002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014J\u0010\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u000203H\u0002J\u001c\u00104\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010\u00142\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\u0014J\u0012\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u0004\u0018\u0001082\u0006\u0010<\u001a\u00020=J\u001a\u0010>\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020?2\u0006\u0010@\u001a\u00020=H\u0002J\u0012\u0010A\u001a\u0004\u0018\u0001082\u0006\u0010B\u001a\u00020$H\u0002J\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u001bj\b\u0012\u0004\u0012\u00020\u001c`\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0011\u0010 \u001a\u00020,8F¢\u0006\u0006\u001a\u0004\b-\u0010.¨\u0006F"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", Argument.Delimiters.none, "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$ProjectEnvironment;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "configFiles", "Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$ProjectEnvironment;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;)V", "getProjectEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$ProjectEnvironment;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "sourceFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtFile;", "rootsIndex", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesDynamicCompoundIndex;", "packagePartProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/compiler/JvmPackagePartProvider;", "getPackagePartProviders", "()Ljava/util/List;", "classpathRootsResolver", "Lorg/jetbrains/kotlin/cli/jvm/compiler/ClasspathRootsResolver;", "initialRoots", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "Lkotlin/collections/ArrayList;", "collectAdditionalSources", Argument.Delimiters.none, "project", "Lcom/intellij/mock/MockProject;", "addKotlinSourceRoots", "rootDirs", "Ljava/io/File;", "createPackagePartProvider", "scope", "Lcom/intellij/psi/search/GlobalSearchScope;", "applicationEnvironment", "Lcom/intellij/core/CoreApplicationEnvironment;", "getApplicationEnvironment", "()Lcom/intellij/core/CoreApplicationEnvironment;", "Lcom/intellij/openapi/project/Project;", "getProject", "()Lcom/intellij/openapi/project/Project;", "countLinesOfCode", Argument.Delimiters.none, "updateClasspathFromRootsIndex", "index", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;", "updateClasspath", "contentRoots", "Lorg/jetbrains/kotlin/cli/common/config/ContentRoot;", "contentRootToVirtualFile", "Lcom/intellij/openapi/vfs/VirtualFile;", "root", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmContentRootBase;", "findLocalFile", ModuleXmlParser.PATH, Argument.Delimiters.none, "findExistingRoot", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmContentRoot;", "rootDescription", "findJarRoot", "file", "getSourceFiles", "ProjectEnvironment", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinCoreEnvironment {
    private static final Object APPLICATION_LOCK;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG;
    private static KotlinCoreApplicationEnvironment ourApplicationEnvironment;
    private static int ourProjectCount;
    private final ClasspathRootsResolver classpathRootsResolver;
    private final CompilerConfiguration configuration;
    private final ArrayList<JavaRoot> initialRoots;
    private final List<JvmPackagePartProvider> packagePartProviders;
    private final ProjectEnvironment projectEnvironment;
    private final JvmDependenciesDynamicCompoundIndex rootsIndex;
    private final List<KtFile> sourceFiles;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<JvmContentRootBase, VirtualFile> {
        public AnonymousClass2(Object obj) {
            super(1, obj, KotlinCoreEnvironment.class, "contentRootToVirtualFile", "contentRootToVirtualFile(Lorg/jetbrains/kotlin/cli/jvm/config/JvmContentRootBase;)Lcom/intellij/openapi/vfs/VirtualFile;", 0);
        }

        public final VirtualFile invoke(JvmContentRootBase jvmContentRootBase) {
            jvmContentRootBase.getClass();
            return ((KotlinCoreEnvironment) ((CallableReference) this).receiver).contentRootToVirtualFile(jvmContentRootBase);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\u0013\u001a\u00020\u0011H\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$ProjectEnvironment;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreProjectEnvironment;", "disposable", "Lcom/intellij/openapi/Disposable;", "applicationEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironment;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lcom/intellij/openapi/Disposable;Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironment;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "jarFileSystem", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "getJarFileSystem", "()Lcom/intellij/openapi/vfs/VirtualFileSystem;", "extensionRegistered", Argument.Delimiters.none, "preregisterServices", Argument.Delimiters.none, "registerExtensionsFromPlugins", "registerJavaPsiFacade", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ProjectEnvironment extends KotlinCoreProjectEnvironment {
        private boolean extensionRegistered;
        private final VirtualFileSystem jarFileSystem;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Code duplicated, block: B:29:0x009a  */
        public ProjectEnvironment(Disposable disposable, KotlinCoreApplicationEnvironment kotlinCoreApplicationEnvironment, CompilerConfiguration compilerConfiguration) {
            VirtualFileSystem jarFileSystem;
            List list;
            super(disposable, kotlinCoreApplicationEnvironment);
            disposable.getClass();
            kotlinCoreApplicationEnvironment.getClass();
            compilerConfiguration.getClass();
            UtilKt.setIdeaIoUseFallback();
            Boolean bool = (Boolean) compilerConfiguration.get(JVMConfigurationKeys.USE_FAST_JAR_FILE_SYSTEM);
            boolean zBooleanValue = compilerConfiguration.getBoolean(CommonConfigurationKeys.USE_FIR) || CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).getLanguageVersion().getUsesK2();
            if (Intrinsics.areEqual(bool, Boolean.TRUE) && !zBooleanValue) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getINITIALIZATION_WARNING(), "Using new faster version of JAR FS: it should make your build faster, but the new implementation is not thoroughly tested with language versions below 2.0", null, 4, null);
            } else if (Intrinsics.areEqual(bool, Boolean.FALSE) && zBooleanValue) {
                CliDiagnosticReportingKt.reportInfo$default(compilerConfiguration, "Using outdated version of JAR FS: it might make your build slower", null, 2, null);
            }
            zBooleanValue = bool != null ? bool.booleanValue() : zBooleanValue;
            if (!compilerConfiguration.getBoolean(JVMConfigurationKeys.USE_PSI_CLASS_FILES_READING) && zBooleanValue) {
                VirtualFileSystem fastJarFileSystem = kotlinCoreApplicationEnvironment.getFastJarFileSystem();
                if (fastJarFileSystem == null) {
                    CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getINITIALIZATION_WARNING(), "Your JDK doesn't seem to support mapped buffer unmapping, so the slower (old) version of JAR FS will be used", null, 4, null);
                    jarFileSystem = kotlinCoreApplicationEnvironment.getJarFileSystem();
                } else {
                    File file = (File) compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_JAR);
                    if (file == null || (list = (List) compilerConfiguration.get(CLIConfigurationKeys.CONTENT_ROOTS)) == null) {
                        jarFileSystem = fastJarFileSystem;
                    } else {
                        List<ContentRoot> list2 = list;
                        if ((list2 instanceof Collection) && list2.isEmpty()) {
                            jarFileSystem = fastJarFileSystem;
                        } else {
                            for (ContentRoot contentRoot : list2) {
                                if ((contentRoot instanceof JvmClasspathRoot) && Intrinsics.areEqual(((JvmClasspathRoot) contentRoot).getFile().getPath(), file.getPath())) {
                                    CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getINITIALIZATION_WARNING(), "JAR from the classpath " + file.getPath() + " is reused as output JAR, so the slower (old) version of JAR FS will be used", null, 4, null);
                                    jarFileSystem = kotlinCoreApplicationEnvironment.getJarFileSystem();
                                }
                            }
                            jarFileSystem = fastJarFileSystem;
                        }
                    }
                }
            } else {
                jarFileSystem = kotlinCoreApplicationEnvironment.getJarFileSystem();
            }
            jarFileSystem.getClass();
            this.jarFileSystem = jarFileSystem;
        }

        public final VirtualFileSystem getJarFileSystem() {
            return this.jarFileSystem;
        }

        public void preregisterServices() {
            Companion companion = KotlinCoreEnvironment.INSTANCE;
            ExtensionsAreaImpl extensionArea = ((CoreProjectEnvironment) this).project.getExtensionArea();
            extensionArea.getClass();
            companion.registerProjectExtensionPoints(extensionArea);
        }

        public final void registerExtensionsFromPlugins(CompilerConfiguration configuration) {
            configuration.getClass();
            if (this.extensionRegistered) {
                return;
            }
            Companion companion = KotlinCoreEnvironment.INSTANCE;
            MockProject mockProject = ((CoreProjectEnvironment) this).project;
            mockProject.getClass();
            companion.registerPluginExtensionPoints(mockProject);
            MockProject mockProject2 = ((CoreProjectEnvironment) this).project;
            mockProject2.getClass();
            companion.registerExtensionsFromPlugins$org_jetbrains_kotlin_cli_base(mockProject2, configuration);
            this.extensionRegistered = true;
        }

        public void registerJavaPsiFacade() {
            MockProject mockProject = ((CoreProjectEnvironment) this).project;
            Object service = mockProject.getService(JavaFileManager.class);
            service.getClass();
            mockProject.registerService(CoreJavaFileManager.class, (CoreJavaFileManager) service);
            Companion companion = KotlinCoreEnvironment.INSTANCE;
            MockProject mockProject2 = ((CoreProjectEnvironment) this).project;
            mockProject2.getClass();
            companion.registerKotlinLightClassSupport(mockProject2);
            mockProject.registerService(ExternalAnnotationsManager.class, new MockExternalAnnotationsManager());
            mockProject.registerService(InferredAnnotationsManager.class, new MockInferredAnnotationsManager());
            super.registerJavaPsiFacade();
        }
    }

    static {
        Logger logger = Logger.getInstance(KotlinCoreEnvironment.class);
        logger.getClass();
        LOG = logger;
        APPLICATION_LOCK = new Object();
    }

    private KotlinCoreEnvironment(ProjectEnvironment projectEnvironment, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles) throws IllegalAccessException, NoSuchFieldException {
        String absolutePath;
        boolean z;
        Module module;
        this.projectEnvironment = projectEnvironment;
        this.configuration = compilerConfiguration;
        ArrayList arrayList = new ArrayList();
        this.sourceFiles = arrayList;
        this.packagePartProviders = new ArrayList();
        this.initialRoots = new ArrayList<>();
        INSTANCE.configureProjectEnvironment(projectEnvironment, compilerConfiguration, environmentConfigFiles);
        MockProject project = projectEnvironment.getProject();
        project.getClass();
        project.registerService(DeclarationProviderFactoryService.class, new CliDeclarationProviderFactoryService(arrayList));
        CollectionsKt.addAll(arrayList, CoreEnvironmentUtilsKt.createSourceFilesFromSourceRoots$default(compilerConfiguration, project, CoreEnvironmentUtilsKt.getSourceRootsCheckingForDuplicates(compilerConfiguration), null, 8, null));
        collectAdditionalSources(project);
        if (!CommonConfigurationKeysKt.getDontSortSourceFiles(compilerConfiguration) && arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment$special$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((KtFile) t).getVirtualFile().getPath(), ((KtFile) t2).getVirtualFile().getPath());
                }
            });
        }
        Object service = project.getService(CoreJavaFileManager.class);
        service.getClass();
        KotlinCliJavaFileManagerImpl kotlinCliJavaFileManagerImpl = (KotlinCliJavaFileManagerImpl) service;
        File file = (File) compilerConfiguration.get(JVMConfigurationKeys.JDK_HOME);
        Integer num = (Integer) compilerConfiguration.get(JVMConfigurationKeys.JDK_RELEASE);
        CliJavaModuleFinder cliJavaModuleFinder = new CliJavaModuleFinder(file, compilerConfiguration, kotlinCliJavaFileManagerImpl, project, num);
        List list = (List) compilerConfiguration.get(JVMConfigurationKeys.MODULES);
        if (list == null || (module = (Module) CollectionsKt.singleOrNull(list)) == null || (absolutePath = module.getOutputDir()) == null) {
            File file2 = (File) compilerConfiguration.get(JVMConfigurationKeys.OUTPUT_DIRECTORY);
            absolutePath = file2 != null ? file2.getAbsolutePath() : null;
        }
        List<? extends ContentRoot> list2 = compilerConfiguration.getList(CLIConfigurationKeys.CONTENT_ROOTS);
        PsiManager psiManager = PsiManager.getInstance(project);
        psiManager.getClass();
        List list3 = compilerConfiguration.getList(JVMConfigurationKeys.ADDITIONAL_JAVA_MODULES);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(this);
        boolean z2 = !compilerConfiguration.getBoolean(CLIConfigurationKeys.ALLOW_KOTLIN_PACKAGE);
        VirtualFile virtualFileFindLocalFile = absolutePath != null ? findLocalFile(absolutePath) : null;
        List<? extends ContentRoot> list4 = list2;
        if (!(list4 instanceof Collection) || !list4.isEmpty()) {
            Iterator it = list4.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((ContentRoot) it.next()) instanceof KotlinSourceRoot) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        ClasspathRootsResolver classpathRootsResolver = new ClasspathRootsResolver(psiManager, compilerConfiguration, list3, anonymousClass2, cliJavaModuleFinder, z2, virtualFileFindLocalFile, kotlinCliJavaFileManagerImpl, num, z);
        this.classpathRootsResolver = classpathRootsResolver;
        ClasspathRootsResolver.RootsAndModules rootsAndModulesConvertClasspathRoots = classpathRootsResolver.convertClasspathRoots(list2);
        List<JavaRoot> listComponent1 = rootsAndModulesConvertClasspathRoots.component1();
        List<JavaModule> listComponent2 = rootsAndModulesConvertClasspathRoots.component2();
        this.initialRoots.addAll(listComponent1);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : listComponent1) {
            VirtualFile file3 = ((JavaRoot) obj).getFile();
            if (file3.isDirectory() || !Intrinsics.areEqual(file3.getExtension(), "java")) {
                arrayList2.add(obj);
            } else {
                arrayList3.add(obj);
            }
        }
        Pair pair = new Pair(arrayList2, arrayList3);
        List list5 = (List) pair.component1();
        List list6 = (List) pair.component2();
        JvmDependenciesDynamicCompoundIndex jvmDependenciesDynamicCompoundIndex = new JvmDependenciesDynamicCompoundIndex(true);
        jvmDependenciesDynamicCompoundIndex.addIndex(new JvmDependenciesIndexImpl(list5, true));
        updateClasspathFromRootsIndex(jvmDependenciesDynamicCompoundIndex);
        this.rootsIndex = jvmDependenciesDynamicCompoundIndex;
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(this.configuration);
        kotlinCliJavaFileManagerImpl.initialize(jvmDependenciesDynamicCompoundIndex, this.packagePartProviders, new SingleJavaFileRootsIndex(list6), this.configuration.getBoolean(JVMConfigurationKeys.USE_PSI_CLASS_FILES_READING), perfManager);
        project.registerService(JavaModuleResolver.class, new CliJavaModuleResolver(this.classpathRootsResolver.getJavaModuleGraph(), listComponent2, SequencesKt.toList(cliJavaModuleFinder.getSystemModules()), project));
        CliVirtualFileFinderFactory cliVirtualFileFinderFactory = new CliVirtualFileFinderFactory(jvmDependenciesDynamicCompoundIndex, num != null, perfManager);
        project.registerService(VirtualFileFinderFactory.class, cliVirtualFileFinderFactory);
        project.registerService(MetadataFinderFactory.class, new CliMetadataFinderFactory(cliVirtualFileFinderFactory));
        project.putUserData(AppendJavaSourceRootsHandlerKeyKt.getAPPEND_JAVA_SOURCE_ROOTS_HANDLER_KEY(), new Function1() { // from class: hb8
            public final Object invoke(Object obj2) {
                return KotlinCoreEnvironment.b(this.b, (List) obj2);
            }
        });
        JavaLanguageLevelKt.setupHighestLanguageLevel(project);
    }

    private static final Unit _init_$lambda$4(KotlinCoreEnvironment kotlinCoreEnvironment, List<? extends File> list) {
        throw null;
    }

    public static File a(JavaRoot javaRoot) {
        javaRoot.getClass();
        VirtualFile file = javaRoot.getFile();
        VirtualFile virtualFileForJar = VfsUtilCore.getVirtualFileForJar(file);
        if (virtualFileForJar != null) {
            file = virtualFileForJar;
        }
        return VfsUtilCore.virtualToIoFile(file);
    }

    public static Unit b(KotlinCoreEnvironment kotlinCoreEnvironment, List list) {
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new JavaSourceRoot((File) it.next(), null));
        }
        kotlinCoreEnvironment.updateClasspath(arrayList);
        return Unit.INSTANCE;
    }

    private final void collectAdditionalSources(MockProject project) {
        List<KtFile> listDistinct = this.sourceFiles;
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        int i = 0;
        while (!listDistinct.isEmpty()) {
            int i2 = i + 1;
            if (i > 10) {
                k2d.a("Unable to collect additional sources in reasonable number of iterations");
                return;
            }
            hashSet.addAll(listDistinct);
            ArrayList arrayList = new ArrayList();
            for (CollectAdditionalSourcesExtension collectAdditionalSourcesExtension : CollectAdditionalSourcesExtension.INSTANCE.getInstances(project)) {
                List<KtFile> list = listDistinct;
                Iterable iterableEmptyList = (Collection) map.get(collectAdditionalSourcesExtension);
                if (iterableEmptyList == null) {
                    iterableEmptyList = CollectionsKt.emptyList();
                }
                Collection<KtFile> collectionCollectAdditionalSourcesAndUpdateConfiguration = collectAdditionalSourcesExtension.collectAdditionalSourcesAndUpdateConfiguration(CollectionsKt.minus(list, iterableEmptyList), this.configuration, project);
                if (!collectionCollectAdditionalSourcesAndUpdateConfiguration.isEmpty()) {
                    arrayList.addAll(collectionCollectAdditionalSourcesAndUpdateConfiguration);
                    map.put(collectAdditionalSourcesExtension, collectionCollectAdditionalSourcesAndUpdateConfiguration);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (!hashSet.contains((KtFile) obj)) {
                    arrayList2.add(obj);
                }
            }
            listDistinct = CollectionsKt.distinct(arrayList2);
            CollectionsKt.addAll(this.sourceFiles, listDistinct);
            i = i2;
        }
    }

    @JvmStatic
    @K1Deprecation
    public static final void configureProjectEnvironment(ProjectEnvironment projectEnvironment, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles) throws IllegalAccessException, NoSuchFieldException {
        INSTANCE.configureProjectEnvironment(projectEnvironment, compilerConfiguration, environmentConfigFiles);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VirtualFile contentRootToVirtualFile(JvmContentRootBase root) {
        if (root instanceof JvmClasspathRoot) {
            JvmClasspathRoot jvmClasspathRoot = (JvmClasspathRoot) root;
            return jvmClasspathRoot.getFile().isFile() ? findJarRoot(jvmClasspathRoot.getFile()) : findExistingRoot((JvmContentRoot) root, "Classpath entry");
        }
        if (root instanceof JvmModulePathRoot) {
            JvmModulePathRoot jvmModulePathRoot = (JvmModulePathRoot) root;
            return jvmModulePathRoot.getFile().isFile() ? findJarRoot(jvmModulePathRoot.getFile()) : findExistingRoot((JvmContentRoot) root, "Java module root");
        }
        if (root instanceof JavaSourceRoot) {
            return findExistingRoot((JvmContentRoot) root, "Java source root");
        }
        if (root instanceof VirtualJvmClasspathRoot) {
            return ((VirtualJvmClasspathRoot) root).getFile();
        }
        qu7.a("Unexpected root: ", root);
        return null;
    }

    @JvmStatic
    @K1Deprecation
    public static final KotlinCoreEnvironment createForParallelTests(Disposable disposable, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles) {
        return INSTANCE.createForParallelTests(disposable, compilerConfiguration, environmentConfigFiles);
    }

    @JvmStatic
    @K1Deprecation
    public static final KotlinCoreEnvironment createForProduction(Disposable disposable, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles) {
        return INSTANCE.createForProduction(disposable, compilerConfiguration, environmentConfigFiles);
    }

    @JvmStatic
    @K1Deprecation
    public static final KotlinCoreEnvironment createForTests(Disposable disposable, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles) {
        return INSTANCE.createForTests(disposable, compilerConfiguration, environmentConfigFiles);
    }

    @JvmStatic
    @K1Deprecation
    public static final void disposeApplicationEnvironment() {
        INSTANCE.disposeApplicationEnvironment();
    }

    private final VirtualFile findExistingRoot(JvmContentRoot root, String rootDescription) {
        String absolutePath = root.getFile().getAbsolutePath();
        absolutePath.getClass();
        VirtualFile virtualFileFindLocalFile = findLocalFile(absolutePath);
        if (virtualFileFindLocalFile == null) {
            CliDiagnosticReportingKt.report$default(this.configuration, CliDiagnostics.INSTANCE.getROOTS_RESOLUTION_WARNING(), rootDescription + " points to a non-existent location: " + root.getFile(), null, 4, null);
        }
        return virtualFileFindLocalFile;
    }

    private final VirtualFile findJarRoot(File file) {
        return this.projectEnvironment.getJarFileSystem().findFileByPath(file + "!/");
    }

    private final CoreApplicationEnvironment getApplicationEnvironment() {
        CoreApplicationEnvironment environment = this.projectEnvironment.getEnvironment();
        environment.getClass();
        return environment;
    }

    @JvmStatic
    @K1Deprecation
    public static final void registerApplicationServices(KotlinCoreApplicationEnvironment kotlinCoreApplicationEnvironment) {
        INSTANCE.registerApplicationServices(kotlinCoreApplicationEnvironment);
    }

    @JvmStatic
    @K1Deprecation
    public static final void registerKotlinLightClassSupport(MockProject mockProject) {
        INSTANCE.registerKotlinLightClassSupport(mockProject);
    }

    @JvmStatic
    @K1Deprecation
    public static final void registerPluginExtensionPoints(MockProject mockProject) {
        INSTANCE.registerPluginExtensionPoints(mockProject);
    }

    @JvmStatic
    @K1Deprecation
    public static final void registerProjectExtensionPoints(ExtensionsArea extensionsArea) {
        INSTANCE.registerProjectExtensionPoints(extensionsArea);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use registerProjectServices(project) instead.", replaceWith = @ReplaceWith(expression = "registerProjectServices(projectEnvironment.project)", imports = {}))
    @JvmStatic
    @K1Deprecation
    public static final void registerProjectServices(JavaCoreProjectEnvironment javaCoreProjectEnvironment, MessageCollector messageCollector) {
        INSTANCE.registerProjectServices(javaCoreProjectEnvironment, messageCollector);
    }

    @JvmStatic
    @K1Deprecation
    public static final void resetApplicationManager(Application application) throws Exception {
        INSTANCE.resetApplicationManager(application);
    }

    private final void updateClasspathFromRootsIndex(JvmDependenciesIndex index) {
        Iterator it = index.getIndexedRoots().iterator();
        while (it.hasNext()) {
            this.projectEnvironment.addSourcesToClasspath(((JavaRoot) it.next()).getFile());
        }
    }

    public final void addKotlinSourceRoots(List<? extends File> rootDirs) {
        rootDirs.getClass();
        List<? extends File> list = rootDirs;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            String absolutePath = ((File) it.next()).getAbsolutePath();
            absolutePath.getClass();
            arrayList.add(new KotlinSourceRoot(absolutePath, false, null));
        }
        CollectionsKt.addAll(this.sourceFiles, SetsKt.minus(CollectionsKt.toSet(CoreEnvironmentUtilsKt.createSourceFilesFromSourceRoots$default(this.configuration, getProject(), arrayList, null, 8, null)), this.sourceFiles));
    }

    public final int countLinesOfCode(List<? extends KtFile> sourceFiles) {
        sourceFiles.getClass();
        Iterator<T> it = sourceFiles.iterator();
        int lineBreakCount = 0;
        while (it.hasNext()) {
            String text = ((KtFile) it.next()).getText();
            lineBreakCount += StringUtil.getLineBreakCount(text) + (!StringUtil.endsWithLineBreak(text) ? 1 : 0);
        }
        return lineBreakCount;
    }

    public final JvmPackagePartProvider createPackagePartProvider(GlobalSearchScope scope) {
        scope.getClass();
        JvmPackagePartProvider jvmPackagePartProvider = new JvmPackagePartProvider(CommonConfigurationKeysKt.getLanguageVersionSettings(this.configuration), scope);
        jvmPackagePartProvider.addRoots(this.initialRoots, this.configuration);
        this.packagePartProviders.add(jvmPackagePartProvider);
        return jvmPackagePartProvider;
    }

    public final VirtualFile findLocalFile(String path) {
        path.getClass();
        return getApplicationEnvironment().getLocalFileSystem().findFileByPath(path);
    }

    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final List<JvmPackagePartProvider> getPackagePartProviders() {
        return this.packagePartProviders;
    }

    public final Project getProject() {
        MockProject project = this.projectEnvironment.getProject();
        project.getClass();
        return project;
    }

    public final ProjectEnvironment getProjectEnvironment() {
        return this.projectEnvironment;
    }

    public final List<KtFile> getSourceFiles() {
        List<ProcessSourcesBeforeCompilingExtension> instances = ProcessSourcesBeforeCompilingExtension.INSTANCE.getInstances(getProject());
        List<KtFile> list = this.sourceFiles;
        list.getClass();
        List<KtFile> listProcessSources = list;
        Iterator<T> it = instances.iterator();
        while (it.hasNext()) {
            listProcessSources = ((ProcessSourcesBeforeCompilingExtension) it.next()).processSources(listProcessSources, this.configuration);
        }
        return CollectionsKt.toList(listProcessSources);
    }

    public final List<File> updateClasspath(List<? extends ContentRoot> contentRoots) {
        contentRoots.getClass();
        List<JavaRoot> listMinus = CollectionsKt.minus(this.classpathRootsResolver.convertClasspathRoots(contentRoots).getRoots(), this.initialRoots);
        JvmDependenciesIndex jvmDependenciesIndexAddNewIndexForRoots = this.rootsIndex.addNewIndexForRoots(listMinus);
        if (jvmDependenciesIndexAddNewIndexForRoots == null) {
            return null;
        }
        updateClasspathFromRootsIndex(jvmDependenciesIndexAddNewIndexForRoots);
        if (this.packagePartProviders.isEmpty()) {
            this.initialRoots.addAll(listMinus);
        } else {
            Iterator<JvmPackagePartProvider> it = this.packagePartProviders.iterator();
            while (it.hasNext()) {
                it.next().addRoots(listMinus, this.configuration);
            }
        }
        CompilerConfiguration compilerConfiguration = this.configuration;
        CompilerConfigurationKey<List<ContentRoot>> compilerConfigurationKey = CLIConfigurationKeys.CONTENT_ROOTS;
        compilerConfiguration.addAll(compilerConfigurationKey, CollectionsKt.minus(contentRoots, compilerConfiguration.getList(compilerConfigurationKey)));
        return SequencesKt.toList(SequencesKt.map(jvmDependenciesIndexAddNewIndexForRoots.getIndexedRoots(), new Function1() { // from class: ib8
            public final Object invoke(Object obj) {
                return KotlinCoreEnvironment.a((JavaRoot) obj);
            }
        }));
    }

    @Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0012H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J \u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u001bH\u0007J \u0010\"\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u001bH\u0007J \u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u001bH\u0007J\u0018\u0010#\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0018\u0010(\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0018\u0010)\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0018\u0010*\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J \u0010*\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010+\u001a\u00020,H\u0007J\b\u0010-\u001a\u00020.H\u0007J\u0014\u0010/\u001a\u00020.2\n\b\u0002\u00100\u001a\u0004\u0018\u000101H\u0007J\u001c\u00102\u001a\u00020.*\u00020\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J \u00103\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010+\u001a\u00020,H\u0002J\u0018\u00104\u001a\u00020.2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00105\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020.2\u0006\u00108\u001a\u000209H\u0007J\u001d\u0010:\u001a\u00020.2\u0006\u00108\u001a\u0002092\u0006\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b;J\u0010\u0010<\u001a\u00020.2\u0006\u0010$\u001a\u00020\fH\u0002J\u0010\u0010=\u001a\u00020.2\u0006\u0010$\u001a\u00020\fH\u0007J\u0010\u0010>\u001a\u00020.2\u0006\u0010?\u001a\u00020@H\u0007J\u001a\u0010A\u001a\u00020.2\u0006\u0010\u001c\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010DH\u0007J\u0010\u0010A\u001a\u00020.2\u0006\u00108\u001a\u000209H\u0007J\u0010\u0010E\u001a\u00020.2\u0006\u0010\u001c\u001a\u00020BH\u0007J\u0010\u0010F\u001a\u00020.2\u0006\u00108\u001a\u000209H\u0007R\u0013\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010$\u001a\u0004\u0018\u00010\f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b%\u0010\u0003\u001a\u0004\b&\u0010'\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006G"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$Companion;", Argument.Delimiters.none, "<init>", "()V", "LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "APPLICATION_LOCK", "getAPPLICATION_LOCK$annotations", "getAPPLICATION_LOCK", "()Ljava/lang/Object;", "ourApplicationEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironment;", "ourProjectCount", Argument.Delimiters.none, "underApplicationLock", "R", "action", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "createForProduction", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "projectDisposable", "Lcom/intellij/openapi/Disposable;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "configFiles", "Lorg/jetbrains/kotlin/cli/jvm/compiler/EnvironmentConfigFiles;", "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment$ProjectEnvironment;", "createForTests", "parentDisposable", "initialConfiguration", "extensionConfigs", "createForParallelTests", "createProjectEnvironmentForTests", "applicationEnvironment", "getApplicationEnvironment$annotations", "getApplicationEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironment;", "getOrCreateApplicationEnvironmentForProduction", "getOrCreateApplicationEnvironmentForTests", "getOrCreateApplicationEnvironment", "environmentMode", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode;", "disposeApplicationEnvironment", Argument.Delimiters.none, "resetApplicationManager", "applicationToReset", "Lcom/intellij/openapi/application/Application;", "configureProjectEnvironment", "createApplicationEnvironment", "registerApplicationExtensionPointsAndExtensionsFrom", "configFilePath", Argument.Delimiters.none, "registerPluginExtensionPoints", "project", "Lcom/intellij/mock/MockProject;", "registerExtensionsFromPlugins", "registerExtensionsFromPlugins$org_jetbrains_kotlin_cli_base", "registerApplicationServicesForCLI", "registerApplicationServices", "registerProjectExtensionPoints", "area", "Lcom/intellij/openapi/extensions/ExtensionsArea;", "registerProjectServices", "Lcom/intellij/core/JavaCoreProjectEnvironment;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "registerProjectServicesForCLI", "registerKotlinLightClassSupport", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static CharSequence a(URL url) {
            String file = url.getFile();
            file.getClass();
            return file;
        }

        public static String c(Object obj) {
            obj.getClass();
            return registerExtensionsFromPlugins$createErrorMessage(obj);
        }

        private final KotlinCoreApplicationEnvironment createApplicationEnvironment(Disposable parentDisposable, CompilerConfiguration configuration, KotlinCoreApplicationEnvironmentMode environmentMode) {
            KotlinCoreApplicationEnvironment kotlinCoreApplicationEnvironmentCreate = KotlinCoreApplicationEnvironment.Companion.create(parentDisposable, environmentMode);
            registerApplicationExtensionPointsAndExtensionsFrom(configuration, "extensions/compiler-cli-root.xml");
            registerApplicationServicesForCLI(kotlinCoreApplicationEnvironmentCreate);
            registerApplicationServices(kotlinCoreApplicationEnvironmentCreate);
            return kotlinCoreApplicationEnvironmentCreate;
        }

        public static /* synthetic */ void getAPPLICATION_LOCK$annotations() {
        }

        @K1Deprecation
        public static /* synthetic */ void getApplicationEnvironment$annotations() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void getOrCreateApplicationEnvironment$lambda$0$0() {
            synchronized (KotlinCoreEnvironment.INSTANCE.getAPPLICATION_LOCK()) {
                KotlinCoreEnvironment.ourApplicationEnvironment = null;
                Unit unit = Unit.INSTANCE;
            }
        }

        private final void registerApplicationExtensionPointsAndExtensionsFrom(CompilerConfiguration configuration, String configFilePath) {
            File file;
            List urls;
            String str = (String) configuration.get(CLIConfigurationKeys.INTELLIJ_PLUGIN_ROOT);
            if (str != null) {
                file = new File(str);
            } else {
                File resourcePathForClass = PathUtil.getResourcePathForClass(CompilerSystemProperties.class);
                String strJoinToString$default = null;
                if (!registerApplicationExtensionPointsAndExtensionsFrom$hasConfigFile(resourcePathForClass, configFilePath)) {
                    resourcePathForClass = null;
                }
                if (resourcePathForClass == null) {
                    File file2 = (File) configuration.get(CLIConfigurationKeys.PATH_TO_KOTLIN_COMPILER_JAR);
                    if (file2 != null) {
                        if (!registerApplicationExtensionPointsAndExtensionsFrom$hasConfigFile(file2, configFilePath)) {
                            file2 = null;
                        }
                        file = file2;
                    } else {
                        file = null;
                    }
                    if (file == null) {
                        StringBuilder sb = new StringBuilder("Unable to find extension point configuration ");
                        sb.append(configFilePath);
                        sb.append(" (cp:\n  ");
                        UrlClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                        UrlClassLoader urlClassLoader = contextClassLoader instanceof UrlClassLoader ? contextClassLoader : null;
                        if (urlClassLoader != null && (urls = urlClassLoader.getUrls()) != null) {
                            strJoinToString$default = CollectionsKt.joinToString$default(urls, "\n  ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: kb8
                                public final Object invoke(Object obj) {
                                    return KotlinCoreEnvironment.Companion.a((URL) obj);
                                }
                            }, 30, (Object) null);
                        }
                        sb.append(strJoinToString$default);
                        sb.append(')');
                        throw new IllegalStateException(sb.toString());
                    }
                } else {
                    file = resourcePathForClass;
                }
            }
            CoreApplicationEnvironment.registerExtensionPointAndExtensions(FileSystems.getDefault().getPath(file.getPath(), new String[0]), configFilePath, ApplicationManager.getApplication().getExtensionArea());
        }

        private static final boolean registerApplicationExtensionPointsAndExtensionsFrom$hasConfigFile(File file, String str) {
            if (file.isDirectory()) {
                return new File(file, "META-INF" + File.separator + str).exists();
            }
            try {
                ZipFile zipFile = new ZipFile(file);
                try {
                    StringBuilder sb = new StringBuilder("META-INF/");
                    sb.append(str);
                    boolean z = zipFile.getEntry(sb.toString()) != null;
                    CloseableKt.closeFinally(zipFile, (Throwable) null);
                    return z;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(zipFile, th);
                        throw th2;
                    }
                }
            } catch (Throwable unused) {
                return false;
            }
        }

        private final void registerApplicationServicesForCLI(KotlinCoreApplicationEnvironment applicationEnvironment) {
            applicationEnvironment.registerFileType(PlainTextFileType.INSTANCE, "xml");
            applicationEnvironment.registerParserDefinition(new JavaParserDefinition());
        }

        private static final String registerExtensionsFromPlugins$createErrorMessage(Object obj) {
            return "The provided plugin " + obj.getClass().getName() + " is not compatible with this version of compiler";
        }

        public static /* synthetic */ void resetApplicationManager$default(Companion companion, Application application, int i, Object obj) throws Exception {
            if ((i & 1) != 0) {
                application = null;
            }
            companion.resetApplicationManager(application);
        }

        @JvmStatic
        @K1Deprecation
        public final void configureProjectEnvironment(ProjectEnvironment projectEnvironment, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles) throws IllegalAccessException, NoSuchFieldException {
            projectEnvironment.getClass();
            compilerConfiguration.getClass();
            environmentConfigFiles.getClass();
            Field declaredField = PersistentFSConstants.class.getDeclaredField("ourMaxIntellisenseFileSize");
            declaredField.setAccessible(true);
            declaredField.setInt(null, FileUtilRt.LARGE_FOR_CONTENT_LOADING);
            projectEnvironment.registerExtensionsFromPlugins(compilerConfiguration);
            projectEnvironment.getProject().registerService(ModuleVisibilityManager.class, new CliModuleVisibilityManagerImpl(environmentConfigFiles == EnvironmentConfigFiles.JVM_CONFIG_FILES));
            registerProjectServicesForCLI(projectEnvironment);
            MockProject project = projectEnvironment.getProject();
            project.getClass();
            registerProjectServices(project);
            CompilerConfigurationExtension.Companion companion = CompilerConfigurationExtension.INSTANCE;
            MockProject project2 = projectEnvironment.getProject();
            project2.getClass();
            Iterator<CompilerConfigurationExtension> it = companion.getInstances(project2).iterator();
            while (it.hasNext()) {
                it.next().updateConfiguration(compilerConfiguration);
            }
        }

        @JvmStatic
        @K1Deprecation
        public final KotlinCoreEnvironment createForParallelTests(Disposable projectDisposable, CompilerConfiguration initialConfiguration, EnvironmentConfigFiles extensionConfigs) {
            projectDisposable.getClass();
            initialConfiguration.getClass();
            extensionConfigs.getClass();
            CompilerConfiguration compilerConfigurationCopy = initialConfiguration.copy();
            return new KotlinCoreEnvironment(new ProjectEnvironment(projectDisposable, getOrCreateApplicationEnvironmentForTests(projectDisposable, compilerConfigurationCopy), compilerConfigurationCopy), compilerConfigurationCopy, extensionConfigs, null);
        }

        @JvmStatic
        @K1Deprecation
        public final KotlinCoreEnvironment createForProduction(Disposable projectDisposable, CompilerConfiguration configuration, EnvironmentConfigFiles configFiles) {
            projectDisposable.getClass();
            configuration.getClass();
            configFiles.getClass();
            CompatKt.setupIdeaStandaloneExecution();
            return new KotlinCoreEnvironment(new ProjectEnvironment(projectDisposable, getOrCreateApplicationEnvironment(projectDisposable, configuration), configuration), configuration, configFiles, null);
        }

        @JvmStatic
        @K1Deprecation
        public final KotlinCoreEnvironment createForTests(Disposable parentDisposable, CompilerConfiguration initialConfiguration, EnvironmentConfigFiles extensionConfigs) {
            parentDisposable.getClass();
            initialConfiguration.getClass();
            extensionConfigs.getClass();
            CompilerConfiguration compilerConfigurationCopy = initialConfiguration.copy();
            return new KotlinCoreEnvironment(new ProjectEnvironment(parentDisposable, createApplicationEnvironment(parentDisposable, compilerConfigurationCopy, KotlinCoreApplicationEnvironmentMode.UnitTest.INSTANCE), compilerConfigurationCopy), compilerConfigurationCopy, extensionConfigs, null);
        }

        @K1Deprecation
        public final ProjectEnvironment createProjectEnvironmentForTests(Disposable projectDisposable, CompilerConfiguration configuration) {
            projectDisposable.getClass();
            configuration.getClass();
            return new ProjectEnvironment(projectDisposable, createApplicationEnvironment(projectDisposable, configuration, KotlinCoreApplicationEnvironmentMode.UnitTest.INSTANCE), configuration);
        }

        @JvmStatic
        @K1Deprecation
        public final void disposeApplicationEnvironment() {
            synchronized (getAPPLICATION_LOCK()) {
                KotlinCoreApplicationEnvironment kotlinCoreApplicationEnvironment = KotlinCoreEnvironment.ourApplicationEnvironment;
                if (kotlinCoreApplicationEnvironment == null) {
                    return;
                }
                KotlinCoreEnvironment.ourApplicationEnvironment = null;
                Disposer.dispose(kotlinCoreApplicationEnvironment.getParentDisposable());
                KotlinCoreEnvironment.INSTANCE.resetApplicationManager(kotlinCoreApplicationEnvironment.getApplication());
                ZipHandler.clearFileAccessorCache();
                Unit unit = Unit.INSTANCE;
            }
        }

        public final Object getAPPLICATION_LOCK() {
            return KotlinCoreEnvironment.APPLICATION_LOCK;
        }

        public final KotlinCoreApplicationEnvironment getApplicationEnvironment() {
            return KotlinCoreEnvironment.ourApplicationEnvironment;
        }

        @K1Deprecation
        public final KotlinCoreApplicationEnvironment getOrCreateApplicationEnvironment(Disposable projectDisposable, CompilerConfiguration configuration, KotlinCoreApplicationEnvironmentMode environmentMode) {
            KotlinCoreApplicationEnvironment kotlinCoreApplicationEnvironment;
            projectDisposable.getClass();
            configuration.getClass();
            environmentMode.getClass();
            synchronized (getAPPLICATION_LOCK()) {
                if (KotlinCoreEnvironment.ourApplicationEnvironment == null) {
                    Disposable disposableNewDisposable = Disposer.newDisposable("Disposable for the KotlinCoreApplicationEnvironment");
                    disposableNewDisposable.getClass();
                    KotlinCoreEnvironment.ourApplicationEnvironment = KotlinCoreEnvironment.INSTANCE.createApplicationEnvironment(disposableNewDisposable, configuration, environmentMode);
                    KotlinCoreEnvironment.ourProjectCount = 0;
                    Disposer.register(disposableNewDisposable, new Disposable() { // from class: lb8
                        public final void dispose() {
                            KotlinCoreEnvironment.Companion.getOrCreateApplicationEnvironment$lambda$0$0();
                        }
                    });
                }
                try {
                    final boolean z = !Intrinsics.areEqual(PropertiesKt.toBooleanLenient(CompilerSystemProperties.KOTLIN_COMPILER_ENVIRONMENT_KEEPALIVE_PROPERTY.getValue()), Boolean.TRUE);
                    Disposer.register(projectDisposable, new Disposable() { // from class: org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment$Companion$getOrCreateApplicationEnvironment$1$2
                        public void dispose() {
                            KotlinCoreEnvironment.Companion companion = KotlinCoreEnvironment.INSTANCE;
                            Object application_lock = companion.getAPPLICATION_LOCK();
                            boolean z2 = z;
                            synchronized (application_lock) {
                                try {
                                    KotlinCoreEnvironment.ourProjectCount--;
                                    if (KotlinCoreEnvironment.ourProjectCount <= 0) {
                                        if (z2) {
                                            companion.disposeApplicationEnvironment();
                                        } else {
                                            KotlinCoreApplicationEnvironment kotlinCoreApplicationEnvironment2 = KotlinCoreEnvironment.ourApplicationEnvironment;
                                            if (kotlinCoreApplicationEnvironment2 != null) {
                                                kotlinCoreApplicationEnvironment2.idleCleanup();
                                            }
                                        }
                                    }
                                    Unit unit = Unit.INSTANCE;
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        }
                    });
                    KotlinCoreEnvironment.ourProjectCount++;
                    kotlinCoreApplicationEnvironment = KotlinCoreEnvironment.ourApplicationEnvironment;
                    kotlinCoreApplicationEnvironment.getClass();
                } catch (Throwable th) {
                    KotlinCoreEnvironment.ourProjectCount++;
                    throw th;
                }
            }
            return kotlinCoreApplicationEnvironment;
        }

        @K1Deprecation
        public final KotlinCoreApplicationEnvironment getOrCreateApplicationEnvironmentForProduction(Disposable projectDisposable, CompilerConfiguration configuration) {
            projectDisposable.getClass();
            configuration.getClass();
            return getOrCreateApplicationEnvironment(projectDisposable, configuration, KotlinCoreApplicationEnvironmentMode.Production.INSTANCE);
        }

        @K1Deprecation
        public final KotlinCoreApplicationEnvironment getOrCreateApplicationEnvironmentForTests(Disposable projectDisposable, CompilerConfiguration configuration) {
            projectDisposable.getClass();
            configuration.getClass();
            return getOrCreateApplicationEnvironment(projectDisposable, configuration, KotlinCoreApplicationEnvironmentMode.UnitTest.INSTANCE);
        }

        @JvmStatic
        @K1Deprecation
        public final void registerApplicationServices(KotlinCoreApplicationEnvironment applicationEnvironment) {
            applicationEnvironment.getClass();
            KotlinFileType kotlinFileType = KotlinFileType.INSTANCE;
            applicationEnvironment.registerFileType(kotlinFileType, "kt");
            applicationEnvironment.registerFileType(kotlinFileType, KotlinParserDefinition.STD_SCRIPT_SUFFIX);
            applicationEnvironment.registerParserDefinition(new KotlinParserDefinition());
            applicationEnvironment.getApplication().registerService(KotlinBinaryClassCache.class, new KotlinBinaryClassCache());
            applicationEnvironment.getApplication().registerService(JavaClassSupers.class, JavaClassSupersImpl.class);
            applicationEnvironment.getApplication().registerService(TransactionGuard.class, TransactionGuardImpl.class);
            applicationEnvironment.getApplication().registerService(VirtualFileSetFactory.class, VirtualFileSetFactoryKt.getCompactVirtualFileSetFactory());
            applicationEnvironment.getApplication().registerService(InternalPersistentJavaLanguageLevelReaderService.class, new InternalPersistentJavaLanguageLevelReaderService.DefaultImpl());
        }

        public final void registerExtensionsFromPlugins$org_jetbrains_kotlin_cli_base(MockProject project, CompilerConfiguration configuration) {
            CompilerConfiguration compilerConfiguration = configuration;
            project.getClass();
            compilerConfiguration.getClass();
            for (ComponentRegistrar componentRegistrar : compilerConfiguration.getList(ComponentRegistrar.INSTANCE.getPLUGIN_COMPONENT_REGISTRARS())) {
                try {
                    componentRegistrar.registerProjectComponents(project, compilerConfiguration);
                } catch (AbstractMethodError e) {
                    String strRegisterExtensionsFromPlugins$createErrorMessage = registerExtensionsFromPlugins$createErrorMessage(componentRegistrar);
                    if (Intrinsics.areEqual(componentRegistrar.getClass().getSimpleName(), "ScriptingCompilerConfigurationComponentRegistrar")) {
                        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_PLUGIN_INITIALIZATION_WARNING(), "Default scripting plugin is disabled: " + strRegisterExtensionsFromPlugins$createErrorMessage, null, 4, null);
                        compilerConfiguration = configuration;
                    } else {
                        compilerConfiguration = configuration;
                        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_PLUGIN_INITIALIZATION_ERROR(), strRegisterExtensionsFromPlugins$createErrorMessage + ".\n" + CollectionsKt.joinToString$default(CollectionsKt.take(StringsKt.lines(ExceptionsKt.stackTraceToString(e)), 6), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), null, 4, null);
                    }
                }
            }
            CompilerPluginRegistrar.ExtensionStorage extensionsStorage = FrontendConfigurationKeysKt.getExtensionsStorage(compilerConfiguration);
            if (extensionsStorage == null) {
                return;
            }
            Iterator it = compilerConfiguration.getList(CompilerPluginRegistrar.INSTANCE.getCOMPILER_PLUGIN_REGISTRARS()).iterator();
            while (it.hasNext()) {
                ((CompilerPluginRegistrar) it.next()).registerExtensions(extensionsStorage, compilerConfiguration);
            }
            Function1 function1 = (Function1) compilerConfiguration.get(ExtensionRegistrationUtilsKt.getTEST_ONLY_PLUGIN_REGISTRATION_CALLBACK());
            if (function1 != null) {
                function1.invoke(extensionsStorage);
            }
            Function1 function2 = (Function1) compilerConfiguration.get(ExtensionRegistrationUtilsKt.getTEST_ONLY_PROJECT_CONFIGURATION_CALLBACK());
            if (function2 != null) {
                function2.invoke(project);
            }
            ExtensionRegistrationUtilsKt.registerInProject(extensionsStorage, project, new Function1() { // from class: jb8
                public final Object invoke(Object obj) {
                    return KotlinCoreEnvironment.Companion.c(obj);
                }
            });
        }

        @JvmStatic
        @K1Deprecation
        public final void registerKotlinLightClassSupport(MockProject project) {
            project.getClass();
            CliTraceHolder cliTraceHolder = new CliTraceHolder(project);
            CliLightClassGenerationSupport cliLightClassGenerationSupport = new CliLightClassGenerationSupport(cliTraceHolder, project);
            CliKotlinAsJavaSupport cliKotlinAsJavaSupport = new CliKotlinAsJavaSupport(project, cliTraceHolder);
            project.registerService(LightClassGenerationSupport.class, cliLightClassGenerationSupport);
            project.registerService(CliLightClassGenerationSupport.class, cliLightClassGenerationSupport);
            project.registerService(KotlinAsJavaSupport.class, cliKotlinAsJavaSupport);
            project.registerService(CodeAnalyzerInitializer.class, cliTraceHolder);
            ProjectExtensionPointName projectExtensionPointName = PsiElementFinder.EP;
            projectExtensionPointName.getPoint(project).registerExtension(new JavaElementFinder(project));
            projectExtensionPointName.getPoint(project).registerExtension(new PsiElementFinderImpl(project));
        }

        @JvmStatic
        @K1Deprecation
        public final void registerPluginExtensionPoints(MockProject project) {
            project.getClass();
            SyntheticResolveExtension.Companion.registerExtensionPoint(project);
            SyntheticJavaResolveExtension.Companion.registerExtensionPoint(project);
            AnalysisHandlerExtension.Companion.registerExtensionPoint(project);
            PackageFragmentProviderExtension.Companion.registerExtensionPoint(project);
            StorageComponentContainerContributor.INSTANCE.registerExtensionPoint(project);
            DeclarationAttributeAltererExtension.Companion.registerExtensionPoint(project);
            TypeResolutionInterceptor.INSTANCE.registerExtensionPoint(project);
            CandidateInterceptor.INSTANCE.registerExtensionPoint(project);
            DescriptorSerializerPlugin.Companion.registerExtensionPoint(project);
            TypeAttributeTranslatorExtension.Companion.registerExtensionPoint(project);
            AssignResolutionAltererExtension.Companion.registerExtensionPoint(project);
            DiagnosticSuppressor.Companion.registerExtensionPoint(project);
            PreprocessedVirtualFileFactoryExtension.INSTANCE.registerExtensionPoint(project);
            CompilerConfigurationExtension.INSTANCE.registerExtensionPoint(project);
            CollectAdditionalSourcesExtension.INSTANCE.registerExtensionPoint(project);
            ProcessSourcesBeforeCompilingExtension.INSTANCE.registerExtensionPoint(project);
            ExtraImportsProviderExtension.Companion.registerExtensionPoint(project);
            ScriptEvaluationExtension.INSTANCE.registerExtensionPoint(project);
            ShellExtension.INSTANCE.registerExtensionPoint(project);
        }

        @JvmStatic
        @K1Deprecation
        public final void registerProjectExtensionPoints(ExtensionsArea area) {
            area.getClass();
            CoreApplicationEnvironment.registerExtensionPoint(area, PsiTreeChangePreprocessor.EP.getName(), PsiTreeChangePreprocessor.class);
            CoreApplicationEnvironment.registerExtensionPoint(area, PsiElementFinder.EP.getName(), PsiElementFinder.class);
            IdeaExtensionPoints.INSTANCE.registerVersionSpecificProjectExtensionPoints(area);
        }

        @JvmStatic
        @K1Deprecation
        public final void registerProjectServices(MockProject project) {
            project.getClass();
            project.registerService(JavaElementSourceFactory.class, JavaFixedElementSourceFactory.class);
            project.registerService(PsiJavaModuleModificationTracker.class, PsiJavaModuleModificationTracker.class);
            project.registerService(KotlinJavaPsiFacade.class, new KotlinJavaPsiFacade(project));
            if (project.getService(PsiNameHelper.class) == null) {
                project.registerService(PsiNameHelper.class, PsiNameHelperImpl.class);
            }
        }

        @K1Deprecation
        public final void registerProjectServicesForCLI(JavaCoreProjectEnvironment projectEnvironment) {
            projectEnvironment.getClass();
        }

        @JvmStatic
        @K1Deprecation
        public final void resetApplicationManager(Application applicationToReset) throws Exception {
            Application application = ApplicationManager.getApplication();
            if (application == null) {
                return;
            }
            if (applicationToReset == null || Intrinsics.areEqual(applicationToReset, application)) {
                try {
                    Field declaredField = ApplicationManager.class.getDeclaredField("ourApplication");
                    declaredField.setAccessible(true);
                    declaredField.set(null, null);
                } catch (Exception e) {
                    if (application.isUnitTestMode()) {
                        throw e;
                    }
                }
            }
        }

        public final <R> R underApplicationLock(Function0<? extends R> action) {
            R r;
            action.getClass();
            synchronized (getAPPLICATION_LOCK()) {
                try {
                    r = (R) action.invoke();
                    InlineMarker.finallyStart(1);
                } finally {
                    InlineMarker.finallyStart(1);
                    InlineMarker.finallyEnd(1);
                }
            }
            return r;
        }

        private Companion() {
        }

        @JvmStatic
        @K1Deprecation
        public final KotlinCoreEnvironment createForProduction(ProjectEnvironment projectEnvironment, CompilerConfiguration configuration, EnvironmentConfigFiles configFiles) {
            projectEnvironment.getClass();
            configuration.getClass();
            configFiles.getClass();
            return new KotlinCoreEnvironment(projectEnvironment, configuration, configFiles, null);
        }

        @JvmStatic
        @K1Deprecation
        public final KotlinCoreEnvironment createForTests(ProjectEnvironment projectEnvironment, CompilerConfiguration initialConfiguration, EnvironmentConfigFiles extensionConfigs) {
            projectEnvironment.getClass();
            initialConfiguration.getClass();
            extensionConfigs.getClass();
            return new KotlinCoreEnvironment(projectEnvironment, initialConfiguration, extensionConfigs, null);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use registerProjectServices(project) instead.", replaceWith = @ReplaceWith(expression = "registerProjectServices(projectEnvironment.project)", imports = {}))
        @JvmStatic
        @K1Deprecation
        public final void registerProjectServices(JavaCoreProjectEnvironment projectEnvironment, MessageCollector messageCollector) {
            projectEnvironment.getClass();
            MockProject project = projectEnvironment.getProject();
            project.getClass();
            registerProjectServices(project);
        }

        @K1Deprecation
        public final KotlinCoreApplicationEnvironment getOrCreateApplicationEnvironment(Disposable projectDisposable, CompilerConfiguration configuration) {
            KotlinCoreApplicationEnvironmentMode kotlinCoreApplicationEnvironmentMode;
            projectDisposable.getClass();
            configuration.getClass();
            boolean testEnvironment = CLIConfigurationKeysKt.getTestEnvironment(configuration);
            if (!testEnvironment) {
                kotlinCoreApplicationEnvironmentMode = KotlinCoreApplicationEnvironmentMode.Production.INSTANCE;
            } else if (testEnvironment) {
                kotlinCoreApplicationEnvironmentMode = KotlinCoreApplicationEnvironmentMode.UnitTest.INSTANCE;
            } else {
                bu8.a();
                return null;
            }
            return getOrCreateApplicationEnvironment(projectDisposable, configuration, kotlinCoreApplicationEnvironmentMode);
        }
    }

    @JvmStatic
    @K1Deprecation
    public static final void registerProjectServices(MockProject mockProject) {
        INSTANCE.registerProjectServices(mockProject);
    }

    @JvmStatic
    @K1Deprecation
    public static final KotlinCoreEnvironment createForProduction(ProjectEnvironment projectEnvironment, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles) {
        return INSTANCE.createForProduction(projectEnvironment, compilerConfiguration, environmentConfigFiles);
    }

    @JvmStatic
    @K1Deprecation
    public static final KotlinCoreEnvironment createForTests(ProjectEnvironment projectEnvironment, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles) {
        return INSTANCE.createForTests(projectEnvironment, compilerConfiguration, environmentConfigFiles);
    }

    public /* synthetic */ KotlinCoreEnvironment(ProjectEnvironment projectEnvironment, CompilerConfiguration compilerConfiguration, EnvironmentConfigFiles environmentConfigFiles, DefaultConstructorMarker defaultConstructorMarker) {
        this(projectEnvironment, compilerConfiguration, environmentConfigFiles);
    }
}
