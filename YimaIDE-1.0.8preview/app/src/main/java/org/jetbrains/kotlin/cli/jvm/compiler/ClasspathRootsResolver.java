package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaModule;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.light.LightJavaModule;
import com.intellij.psi.search.GlobalSearchScope;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.analyzer.CompilationErrorException;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageUtil;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.compiler.ClasspathRootsResolver;
import org.jetbrains.kotlin.cli.jvm.config.JavaSourceRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRootBase;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootBase;
import org.jetbrains.kotlin.cli.jvm.config.JvmModulePathRoot;
import org.jetbrains.kotlin.cli.jvm.index.JavaRoot;
import org.jetbrains.kotlin.cli.jvm.modules.CliJavaModuleFinder;
import org.jetbrains.kotlin.cli.jvm.modules.JavaModuleGraph;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNamesUtilKt;
import org.jetbrains.kotlin.resolve.jvm.KotlinCliJavaFileManager;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModule;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleInfo;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 J2\u00020\u0001:\u0003HIJBo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\u0014\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0007J2\u0010&\u001a\u00020#2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u0002J\u001e\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020-\u0018\u00010,2\u0006\u0010.\u001a\u00020\fH\u0002J\u001a\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010.\u001a\u00020\f2\u0006\u00101\u001a\u00020\u0010H\u0002J\u0012\u00102\u001a\u0004\u0018\u0001032\u0006\u0010.\u001a\u00020\fH\u0002J\"\u00104\u001a\u0004\u0018\u00010\f2\u0006\u0010.\u001a\u00020\f2\u000e\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010706H\u0002J\u0012\u00108\u001a\u0004\u0018\u0001072\u0006\u00109\u001a\u00020\fH\u0002J$\u0010:\u001a\u00020;2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002030\u00072\f\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>H\u0002J\u001c\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020\b2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\fH\u0002J\u001c\u0010C\u001a\u00020;2\u0006\u0010A\u001a\u00020\b2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\fH\u0002J$\u0010D\u001a\u00020;2\u0006\u0010E\u001a\u00020F2\u0006\u0010A\u001a\u00020\b2\n\b\u0002\u0010B\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010G\u001a\u00020;2\u0006\u0010A\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u001e\u001a\u00070\u001f¢\u0006\u0002\b X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/ClasspathRootsResolver;", Argument.Delimiters.none, "psiManager", "Lcom/intellij/psi/PsiManager;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "additionalModules", Argument.Delimiters.none, Argument.Delimiters.none, "contentRootToVirtualFile", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/cli/jvm/config/JvmContentRootBase;", "Lcom/intellij/openapi/vfs/VirtualFile;", "javaModuleFinder", "Lorg/jetbrains/kotlin/cli/jvm/modules/CliJavaModuleFinder;", "requireStdlibModule", Argument.Delimiters.none, "outputDirectory", "javaFileManager", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinCliJavaFileManager;", "jdkRelease", Argument.Delimiters.none, "hasKotlinSources", "<init>", "(Lcom/intellij/psi/PsiManager;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lorg/jetbrains/kotlin/cli/jvm/modules/CliJavaModuleFinder;ZLcom/intellij/openapi/vfs/VirtualFile;Lorg/jetbrains/kotlin/resolve/jvm/KotlinCliJavaFileManager;Ljava/lang/Integer;Z)V", "Ljava/lang/Integer;", "javaModuleGraph", "Lorg/jetbrains/kotlin/cli/jvm/modules/JavaModuleGraph;", "getJavaModuleGraph", "()Lorg/jetbrains/kotlin/cli/jvm/modules/JavaModuleGraph;", "searchScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "Lorg/jetbrains/annotations/NotNull;", "reportErrors", "convertClasspathRoots", "Lorg/jetbrains/kotlin/cli/jvm/compiler/ClasspathRootsResolver$RootsAndModules;", "contentRoots", "Lorg/jetbrains/kotlin/cli/common/config/ContentRoot;", "computeRoots", ModuleXmlParser.JAVA_SOURCE_ROOTS, "Lorg/jetbrains/kotlin/cli/jvm/compiler/ClasspathRootsResolver$RootWithPrefix;", "jvmClasspathRoots", "jvmModulePathRoots", "findSourceModuleInfo", "Lkotlin/Pair;", "Lcom/intellij/psi/PsiJavaModule;", "root", "modularSourceRoot", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule$Explicit;", "hasOutputDirectoryInClasspath", "modularBinaryRoot", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule;", "tryLoadVersionSpecificModuleInfo", "manifest", "Lkotlin/Lazy;", "Ljava/util/jar/Attributes;", "readManifestAttributes", "jarRoot", "addModularRoots", Argument.Delimiters.none, ModuleXmlParser.MODULES, CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "reportError", "message", "file", "reportWarning", "report", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", K2JsArgumentConstants.RUNTIME_DIAGNOSTIC_LOG, "RootsAndModules", "RootWithPrefix", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClasspathRootsResolver {

    @Deprecated
    public static final String AUTOMATIC_MODULE_NAME = "Automatic-Module-Name";
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String IS_MULTI_RELEASE = "Multi-Release";
    private final List<String> additionalModules;
    private final CompilerConfiguration configuration;
    private final Function1<JvmContentRootBase, VirtualFile> contentRootToVirtualFile;
    private final KotlinCliJavaFileManager javaFileManager;
    private final CliJavaModuleFinder javaModuleFinder;
    private final JavaModuleGraph javaModuleGraph;
    private final Integer jdkRelease;
    private final VirtualFile outputDirectory;
    private final PsiManager psiManager;
    private final boolean reportErrors;
    private final boolean requireStdlibModule;
    private final GlobalSearchScope searchScope;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/ClasspathRootsResolver$RootWithPrefix;", Argument.Delimiters.none, "root", "Lcom/intellij/openapi/vfs/VirtualFile;", ModuleXmlParser.JAVA_SOURCE_PACKAGE_PREFIX, Argument.Delimiters.none, "<init>", "(Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/String;)V", "getRoot", "()Lcom/intellij/openapi/vfs/VirtualFile;", "getPackagePrefix", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class RootWithPrefix {
        private final String packagePrefix;
        private final VirtualFile root;

        public RootWithPrefix(VirtualFile virtualFile, String str) {
            virtualFile.getClass();
            this.root = virtualFile;
            this.packagePrefix = str;
        }

        public static /* synthetic */ RootWithPrefix copy$default(RootWithPrefix rootWithPrefix, VirtualFile virtualFile, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                virtualFile = rootWithPrefix.root;
            }
            if ((i & 2) != 0) {
                str = rootWithPrefix.packagePrefix;
            }
            return rootWithPrefix.copy(virtualFile, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final VirtualFile getRoot() {
            return this.root;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPackagePrefix() {
            return this.packagePrefix;
        }

        public final RootWithPrefix copy(VirtualFile root, String packagePrefix) {
            root.getClass();
            return new RootWithPrefix(root, packagePrefix);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RootWithPrefix)) {
                return false;
            }
            RootWithPrefix rootWithPrefix = (RootWithPrefix) other;
            return Intrinsics.areEqual(this.root, rootWithPrefix.root) && Intrinsics.areEqual(this.packagePrefix, rootWithPrefix.packagePrefix);
        }

        public final String getPackagePrefix() {
            return this.packagePrefix;
        }

        public final VirtualFile getRoot() {
            return this.root;
        }

        public int hashCode() {
            int iHashCode = this.root.hashCode() * 31;
            String str = this.packagePrefix;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "RootWithPrefix(root=" + this.root + ", packagePrefix=" + this.packagePrefix + ')';
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/ClasspathRootsResolver$RootsAndModules;", Argument.Delimiters.none, "roots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", ModuleXmlParser.MODULES, "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getRoots", "()Ljava/util/List;", "getModules", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class RootsAndModules {
        private final List<JavaModule> modules;
        private final List<JavaRoot> roots;

        public RootsAndModules(List<JavaRoot> list, List<? extends JavaModule> list2) {
            list.getClass();
            list2.getClass();
            this.roots = list;
            this.modules = list2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ RootsAndModules copy$default(RootsAndModules rootsAndModules, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = rootsAndModules.roots;
            }
            if ((i & 2) != 0) {
                list2 = rootsAndModules.modules;
            }
            return rootsAndModules.copy(list, list2);
        }

        public final List<JavaRoot> component1() {
            return this.roots;
        }

        public final List<JavaModule> component2() {
            return this.modules;
        }

        public final RootsAndModules copy(List<JavaRoot> roots, List<? extends JavaModule> modules) {
            roots.getClass();
            modules.getClass();
            return new RootsAndModules(roots, modules);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RootsAndModules)) {
                return false;
            }
            RootsAndModules rootsAndModules = (RootsAndModules) other;
            return Intrinsics.areEqual(this.roots, rootsAndModules.roots) && Intrinsics.areEqual(this.modules, rootsAndModules.modules);
        }

        public final List<JavaModule> getModules() {
            return this.modules;
        }

        public final List<JavaRoot> getRoots() {
            return this.roots;
        }

        public int hashCode() {
            return (this.roots.hashCode() * 31) + this.modules.hashCode();
        }

        public String toString() {
            return "RootsAndModules(roots=" + this.roots + ", modules=" + this.modules + ')';
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ClasspathRootsResolver(PsiManager psiManager, CompilerConfiguration compilerConfiguration, List<String> list, Function1<? super JvmContentRootBase, ? extends VirtualFile> function1, CliJavaModuleFinder cliJavaModuleFinder, boolean z, VirtualFile virtualFile, KotlinCliJavaFileManager kotlinCliJavaFileManager, Integer num, boolean z2) {
        psiManager.getClass();
        compilerConfiguration.getClass();
        list.getClass();
        function1.getClass();
        cliJavaModuleFinder.getClass();
        kotlinCliJavaFileManager.getClass();
        this.psiManager = psiManager;
        this.configuration = compilerConfiguration;
        this.additionalModules = list;
        this.contentRootToVirtualFile = function1;
        this.javaModuleFinder = cliJavaModuleFinder;
        this.requireStdlibModule = z;
        this.outputDirectory = virtualFile;
        this.javaFileManager = kotlinCliJavaFileManager;
        this.jdkRelease = num;
        this.javaModuleGraph = new JavaModuleGraph(cliJavaModuleFinder);
        GlobalSearchScope globalSearchScopeAllScope = GlobalSearchScope.allScope(psiManager.getProject());
        globalSearchScopeAllScope.getClass();
        this.searchScope = globalSearchScopeAllScope;
        this.reportErrors = z2;
    }

    public static Attributes a(ClasspathRootsResolver classpathRootsResolver, VirtualFile virtualFile) {
        return classpathRootsResolver.readManifestAttributes(virtualFile);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.analyzer.CompilationErrorException */
    private final void addModularRoots(List<? extends JavaModule> modules, List<JavaRoot> result) throws CompilationErrorException {
        List listPlus;
        ArrayList arrayList = new ArrayList();
        for (Object obj : modules) {
            if (obj instanceof JavaModule.Explicit) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((JavaModule) obj2).isSourceModule()) {
                arrayList2.add(obj2);
            }
        }
        if (arrayList2.size() > 1) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                reportError("Too many source module declarations found", ((JavaModule.Explicit) it.next()).getModuleInfoFile());
            }
            return;
        }
        for (JavaModule javaModule : modules) {
            JavaModule javaModuleFindModule = this.javaModuleFinder.findModule(javaModule.getName());
            if (javaModuleFindModule == null) {
                this.javaModuleFinder.addUserModule(javaModule);
            } else if (!Intrinsics.areEqual(javaModule.getModuleRoots(), javaModuleFindModule.getModuleRoots())) {
                VirtualFile virtualFileAddModularRoots$getRootFile = addModularRoots$getRootFile(javaModule);
                VirtualFile virtualFileAddModularRoots$getRootFile2 = addModularRoots$getRootFile(javaModuleFindModule);
                reportWarning("The root is ignored because a module with the same name '" + javaModule.getName() + "' has been found earlier on the module path" + (virtualFileAddModularRoots$getRootFile2 == null ? Argument.Delimiters.none : " at: " + virtualFileAddModularRoots$getRootFile2.getPath()), virtualFileAddModularRoots$getRootFile);
            }
        }
        if (SequencesKt.none(this.javaModuleFinder.getAllObservableModules())) {
            return;
        }
        JavaModule.Explicit explicit = (JavaModule.Explicit) CollectionsKt.singleOrNull(arrayList2);
        boolean zContains = this.additionalModules.contains("ALL-MODULE-PATH");
        if (zContains && explicit != null) {
            reportError$default(this, "-Xadd-modules=ALL-MODULE-PATH can only be used when compiling the unnamed module", null, 2, null);
            return;
        }
        if (explicit != null) {
            listPlus = CollectionsKt.plus(CollectionsKt.listOf(explicit.getName()), this.additionalModules);
        } else if (zContains) {
            List<? extends JavaModule> list = modules;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList3.add(((JavaModule) it2.next()).getName());
            }
            listPlus = arrayList3;
        } else {
            listPlus = CollectionsKt.plus(CliJavaModuleUtilsKt.computeDefaultRootModules(this.javaModuleFinder), this.additionalModules);
        }
        LinkedHashSet allDependencies = this.javaModuleGraph.getAllDependencies(listPlus);
        if (allDependencies == null || !allDependencies.isEmpty()) {
            Iterator it3 = allDependencies.iterator();
            while (it3.hasNext()) {
                if (this.javaModuleFinder.findModule((String) it3.next()) instanceof JavaModule.Automatic) {
                    Iterator<? extends JavaModule> it4 = modules.iterator();
                    while (it4.hasNext()) {
                        JavaModule.Automatic automatic = (JavaModule) it4.next();
                        if (automatic instanceof JavaModule.Automatic) {
                            allDependencies.add(automatic.getName());
                        }
                    }
                    break;
                }
            }
        }
        log("Loading modules: " + allDependencies);
        Iterator it5 = allDependencies.iterator();
        it5.getClass();
        while (it5.hasNext()) {
            Object next = it5.next();
            next.getClass();
            String str = (String) next;
            JavaModule javaModuleFindModule2 = this.javaModuleFinder.findModule(str);
            if (javaModuleFindModule2 == null) {
                reportError$default(this, "Module " + str + " cannot be found in the module graph", null, 2, null);
            } else {
                result.addAll(CliJavaModuleUtilsKt.getJavaModuleRoots(javaModuleFindModule2));
            }
        }
        if (!this.requireStdlibModule || explicit == null || this.javaModuleGraph.reads(explicit.getName(), JavaModuleKt.KOTLIN_STDLIB_MODULE_NAME)) {
            return;
        }
        reportError("The Kotlin standard library is not found in the module graph. Please ensure you have the 'requires kotlin.stdlib' clause in your module definition", explicit.getModuleInfoFile());
    }

    private static final VirtualFile addModularRoots$getRootFile(JavaModule javaModule) {
        VirtualFile file;
        JavaModule.Root root = (JavaModule.Root) CollectionsKt.firstOrNull(javaModule.getModuleRoots());
        if (root == null || (file = root.getFile()) == null) {
            return null;
        }
        VirtualFile virtualFileForJar = VfsUtilCore.getVirtualFileForJar(file);
        return virtualFileForJar == null ? file : virtualFileForJar;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.analyzer.CompilationErrorException */
    private final RootsAndModules computeRoots(List<RootWithPrefix> javaSourceRoots, List<? extends VirtualFile> jvmClasspathRoots, List<? extends VirtualFile> jvmModulePathRoots) throws CompilationErrorException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean z = true;
        boolean z2 = CollectionsKt.contains(jvmClasspathRoots, this.outputDirectory) || CollectionsKt.contains(jvmModulePathRoots, this.outputDirectory);
        for (RootWithPrefix rootWithPrefix : javaSourceRoots) {
            VirtualFile root = rootWithPrefix.getRoot();
            String packagePrefix = rootWithPrefix.getPackagePrefix();
            JavaModule.Explicit explicitModularSourceRoot = modularSourceRoot(root, z2);
            if (explicitModularSourceRoot != null) {
                arrayList2.add(explicitModularSourceRoot);
            } else {
                JavaRoot.RootType rootType = JavaRoot.RootType.SOURCE;
                FqName fqName = null;
                if (packagePrefix != null) {
                    if (FqNamesUtilKt.isValidJavaFqName(packagePrefix)) {
                        fqName = new FqName(packagePrefix);
                    } else {
                        reportWarning$default(this, "Invalid package prefix name is ignored: ".concat(packagePrefix), null, 2, null);
                    }
                }
                arrayList.add(new JavaRoot(root, rootType, fqName));
            }
        }
        Iterator<? extends VirtualFile> it = jvmClasspathRoots.iterator();
        while (it.hasNext()) {
            arrayList.add(new JavaRoot(it.next(), JavaRoot.RootType.BINARY, null, 4, null));
        }
        if (arrayList2.isEmpty()) {
            z = false;
            break;
        }
        Iterator it2 = arrayList2.iterator();
        loop3: while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            List moduleRoots = ((JavaModule) it2.next()).getModuleRoots();
            if (!(moduleRoots instanceof Collection) || !moduleRoots.isEmpty()) {
                Iterator it3 = moduleRoots.iterator();
                while (it3.hasNext()) {
                    if (Intrinsics.areEqual(((JavaModule.Root) it3.next()).getFile(), this.outputDirectory)) {
                        break loop3;
                    }
                }
            }
        }
        for (VirtualFile virtualFile : jvmModulePathRoots) {
            if (!z || !Intrinsics.areEqual(virtualFile, this.outputDirectory)) {
                JavaModule javaModuleModularBinaryRoot = modularBinaryRoot(virtualFile);
                if (javaModuleModularBinaryRoot != null) {
                    arrayList2.add(javaModuleModularBinaryRoot);
                }
            }
        }
        Integer num = this.jdkRelease;
        if (num == null || num.intValue() >= 9) {
            addModularRoots(arrayList2, arrayList);
        } else {
            arrayList.add(new JavaRoot(this.javaModuleFinder.getNonModuleRoot().getFile(), JavaRoot.RootType.BINARY_SIG, null, 4, null));
        }
        return new RootsAndModules(arrayList, arrayList2);
    }

    private final Pair<VirtualFile, PsiJavaModule> findSourceModuleInfo(VirtualFile root) {
        PsiFile psiFileFindFile;
        if (root.isDirectory()) {
            root = root.findChild("module-info.java");
        } else if (!Intrinsics.areEqual(root.getName(), "module-info.java")) {
            root = null;
        }
        if (root == null || (psiFileFindFile = this.psiManager.findFile(root)) == null) {
            return null;
        }
        PsiElement[] children = psiFileFindFile.getChildren();
        children.getClass();
        int length = children.length;
        int i = 0;
        boolean z = false;
        PsiElement psiElement = null;
        while (true) {
            if (i >= length) {
                if (!z) {
                    break;
                }
                break;
            }
            PsiElement psiElement2 = children[i];
            if (psiElement2 instanceof PsiJavaModule) {
                if (!z) {
                    z = true;
                    psiElement = psiElement2;
                }
            }
            i++;
            psiElement = null;
            break;
        }
        PsiJavaModule psiJavaModule = psiElement instanceof PsiJavaModule ? (PsiJavaModule) psiElement : null;
        if (psiJavaModule == null) {
            return null;
        }
        return TuplesKt.to(root, psiJavaModule);
    }

    private final void log(String message) {
        CliDiagnosticReportingKt.reportLog$default(this.configuration, message, null, 2, null);
    }

    private final JavaModule modularBinaryRoot(final VirtualFile root) {
        VirtualFile virtualFile;
        boolean zAreEqual = Intrinsics.areEqual(root.getFileSystem().getProtocol(), "jar");
        Lazy<? extends Attributes> lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: kz1
            public final Object invoke() {
                return ClasspathRootsResolver.a(this.b, root);
            }
        });
        VirtualFile virtualFileFindChild = root.findChild("module-info.class");
        if (virtualFileFindChild != null) {
            virtualFile = virtualFileFindChild;
        } else if (zAreEqual) {
            virtualFileFindChild = tryLoadVersionSpecificModuleInfo(root, lazy);
            virtualFile = virtualFileFindChild;
        } else {
            virtualFile = null;
        }
        if (virtualFile != null) {
            JavaModuleInfo javaModuleInfo = JavaModuleInfo.Companion.read(virtualFile, this.javaFileManager, this.searchScope);
            if (javaModuleInfo == null) {
                return null;
            }
            return new JavaModule.Explicit(javaModuleInfo, CollectionsKt.listOf(new JavaModule.Root(root, true, false, 4, (DefaultConstructorMarker) null)), virtualFile, false, 8, (DefaultConstructorMarker) null);
        }
        if (!zAreEqual) {
            return null;
        }
        List listListOf = CollectionsKt.listOf(new JavaModule.Root(root, true, false, 4, (DefaultConstructorMarker) null));
        Attributes attributes = (Attributes) lazy.getValue();
        String value = attributes != null ? attributes.getValue(AUTOMATIC_MODULE_NAME) : null;
        if (value != null) {
            return new JavaModule.Automatic(value, listListOf);
        }
        File fileVirtualToIoFile = VfsUtilCore.virtualToIoFile(root);
        fileVirtualToIoFile.getClass();
        String strModuleName = LightJavaModule.moduleName(FilesKt.getNameWithoutExtension(fileVirtualToIoFile));
        strModuleName.getClass();
        if (strModuleName.length() != 0) {
            return new JavaModule.Automatic(strModuleName, listListOf);
        }
        VirtualFile virtualFileForJar = VfsUtilCore.getVirtualFileForJar(root);
        if (virtualFileForJar == null) {
            virtualFileForJar = root;
        }
        reportError("Cannot infer automatic module name for the file", virtualFileForJar);
        return null;
    }

    private final JavaModule.Explicit modularSourceRoot(VirtualFile root, boolean hasOutputDirectoryInClasspath) {
        List listListOf;
        Pair<VirtualFile, PsiJavaModule> pairFindSourceModuleInfo = findSourceModuleInfo(root);
        if (pairFindSourceModuleInfo == null) {
            return null;
        }
        VirtualFile virtualFile = (VirtualFile) pairFindSourceModuleInfo.component1();
        PsiJavaModule psiJavaModule = (PsiJavaModule) pairFindSourceModuleInfo.component2();
        JavaModule.Root root2 = new JavaModule.Root(root, false, false, 4, (DefaultConstructorMarker) null);
        if (hasOutputDirectoryInClasspath) {
            VirtualFile virtualFile2 = this.outputDirectory;
            virtualFile2.getClass();
            listListOf = CollectionsKt.listOf(new JavaModule.Root[]{root2, new JavaModule.Root(virtualFile2, true, false, 4, (DefaultConstructorMarker) null)});
        } else {
            listListOf = CollectionsKt.listOf(root2);
        }
        return new JavaModule.Explicit(JavaModuleInfo.Companion.create(psiJavaModule), listListOf, virtualFile, false, 8, (DefaultConstructorMarker) null);
    }

    private final Attributes readManifestAttributes(VirtualFile jarRoot) {
        VirtualFile virtualFileFindChild = jarRoot.findChild("META-INF");
        VirtualFile virtualFileFindChild2 = virtualFileFindChild != null ? virtualFileFindChild.findChild("MANIFEST.MF") : null;
        if (virtualFileFindChild2 != null) {
            try {
                InputStream inputStream = virtualFileFindChild2.getInputStream();
                if (inputStream != null) {
                    return new Manifest(inputStream).getMainAttributes();
                }
            } catch (IOException unused) {
            }
        }
        return null;
    }

    private final void report(KtSourcelessDiagnosticFactory factory, String message, VirtualFile file) {
        CliDiagnosticReportingKt.report(this.configuration, factory, message, file != null ? CompilerMessageLocation.INSTANCE.create(MessageUtil.virtualFileToPath(file)) : null);
    }

    private final void reportError(String message, VirtualFile file) {
        if (this.reportErrors) {
            report(CliDiagnostics.INSTANCE.getCLASSPATH_RESOLUTION_ERROR(), message, file);
        }
    }

    public static /* synthetic */ void reportError$default(ClasspathRootsResolver classpathRootsResolver, String str, VirtualFile virtualFile, int i, Object obj) {
        if ((i & 2) != 0) {
            virtualFile = null;
        }
        classpathRootsResolver.reportError(str, virtualFile);
    }

    private final void reportWarning(String message, VirtualFile file) {
        report(CliDiagnostics.INSTANCE.getCLASSPATH_RESOLUTION_WARNING(), message, file);
    }

    public static /* synthetic */ void reportWarning$default(ClasspathRootsResolver classpathRootsResolver, String str, VirtualFile virtualFile, int i, Object obj) {
        if ((i & 2) != 0) {
            virtualFile = null;
        }
        classpathRootsResolver.reportWarning(str, virtualFile);
    }

    private final VirtualFile tryLoadVersionSpecificModuleInfo(VirtualFile root, Lazy<? extends Attributes> manifest) {
        VirtualFile virtualFileFindChild;
        String value;
        VirtualFile virtualFileFindChild2 = root.findChild("META-INF");
        if (virtualFileFindChild2 != null && (virtualFileFindChild = virtualFileFindChild2.findChild("versions")) != null) {
            Attributes attributes = (Attributes) manifest.getValue();
            if (!Intrinsics.areEqual((attributes == null || (value = attributes.getValue(IS_MULTI_RELEASE)) == null) ? null : Boolean.valueOf(StringsKt.equals(value, "true", true)), Boolean.TRUE)) {
                return null;
            }
            VirtualFile[] children = virtualFileFindChild.getChildren();
            children.getClass();
            ArrayList arrayList = new ArrayList();
            for (VirtualFile virtualFile : children) {
                String name = virtualFile.getName();
                name.getClass();
                Integer intOrNull = StringsKt.toIntOrNull(name);
                if (intOrNull != null && intOrNull.intValue() >= 9) {
                    arrayList.add(virtualFile);
                }
            }
            Iterator it = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.cli.jvm.compiler.ClasspathRootsResolver$tryLoadVersionSpecificModuleInfo$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    String name2 = ((VirtualFile) t).getName();
                    name2.getClass();
                    Integer numValueOf = Integer.valueOf(Integer.parseInt(name2));
                    String name3 = ((VirtualFile) t2).getName();
                    name3.getClass();
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(Integer.parseInt(name3)));
                }
            }).iterator();
            while (it.hasNext()) {
                VirtualFile virtualFileFindChild3 = ((VirtualFile) it.next()).findChild("module-info.class");
                if (virtualFileFindChild3 != null) {
                    return virtualFileFindChild3;
                }
            }
        }
        return null;
    }

    public final RootsAndModules convertClasspathRoots(List<? extends ContentRoot> contentRoots) {
        VirtualFile virtualFile;
        contentRoots.getClass();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (ContentRoot contentRoot : contentRoots) {
            if ((contentRoot instanceof JvmContentRootBase) && (virtualFile = (VirtualFile) this.contentRootToVirtualFile.invoke(contentRoot)) != null) {
                JvmContentRootBase jvmContentRootBase = (JvmContentRootBase) contentRoot;
                if (jvmContentRootBase instanceof JavaSourceRoot) {
                    arrayList.add(new RootWithPrefix(virtualFile, ((JavaSourceRoot) contentRoot).getPackagePrefix()));
                } else if (jvmContentRootBase instanceof JvmClasspathRootBase) {
                    arrayList2.add(virtualFile);
                } else {
                    if (!(jvmContentRootBase instanceof JvmModulePathRoot)) {
                        w04.a("Unknown root type: ", contentRoot);
                        return null;
                    }
                    arrayList3.add(virtualFile);
                }
            }
        }
        return computeRoots(arrayList, arrayList2, arrayList3);
    }

    public final JavaModuleGraph getJavaModuleGraph() {
        return this.javaModuleGraph;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/ClasspathRootsResolver$Companion;", Argument.Delimiters.none, "<init>", "()V", "AUTOMATIC_MODULE_NAME", Argument.Delimiters.none, "IS_MULTI_RELEASE", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
