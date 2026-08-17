package org.jetbrains.kotlin.cli.common;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtVirtualFileSourceFile;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.GroupedKtSourcesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.compiler.CoreEnvironmentUtilsKt;
import org.jetbrains.kotlin.cli.jvm.compiler.SourceFileWithModule;
import org.jetbrains.kotlin.cli.jvm.compiler.ValidSourceFilesFilter;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironmentKt;
import org.jetbrains.kotlin.compiler.plugin.ExtensionPointUtilsKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.CompilerConfigurationExtension;
import org.jetbrains.kotlin.extensions.PreprocessedFileCreator;
import org.jetbrains.kotlin.fir.extensions.CollectAdditionalSourceFilesExtension;
import org.jetbrains.kotlin.idea.KotlinFileType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001aD\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\n2\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eH\u0002\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"allFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;", "getAllFiles", "(Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;)Ljava/util/List;", "ktSourceFileComparator", "Ljava/util/Comparator;", "collectSources", "compilerConfiguration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "applyFirProcessSourcesExtension", Argument.Delimiters.none, "environment", "configuration", "findVirtualFile", "Lkotlin/Function1;", "Ljava/io/File;", "Lcom/intellij/openapi/vfs/VirtualFile;", ModuleXmlParser.SOURCES, "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class GroupedKtSourcesKt {
    private static final Comparator<KtSourceFile> ktSourceFileComparator = new Comparator() { // from class: j06
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return GroupedKtSourcesKt.c((KtSourceFile) obj, (KtSourceFile) obj2);
        }
    };

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.common.GroupedKtSourcesKt$collectSources$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<File, VirtualFile> {
        final /* synthetic */ VfsBasedProjectEnvironment $projectEnvironment;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(VfsBasedProjectEnvironment vfsBasedProjectEnvironment) {
            super(1, Intrinsics.Kotlin.class, "findVirtualFile", "collectSources$findVirtualFile(Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Ljava/io/File;)Lcom/intellij/openapi/vfs/VirtualFile;", 0);
            this.$projectEnvironment = vfsBasedProjectEnvironment;
        }

        public final VirtualFile invoke(File file) {
            file.getClass();
            return GroupedKtSourcesKt.collectSources$findVirtualFile(this.$projectEnvironment, file);
        }
    }

    public static Iterable a(PreprocessedFileCreator preprocessedFileCreator, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, CompilerConfiguration compilerConfiguration, VirtualFile virtualFile) {
        virtualFile.getClass();
        List listListOf = CollectionsKt.listOf(new KtVirtualFileSourceFile(preprocessedFileCreator.create(virtualFile)));
        if (Intrinsics.areEqual(virtualFile.getExtension(), "kt")) {
            return listListOf;
        }
        List list = listListOf;
        Iterable<KtSourceFile> iterableApplyFirProcessSourcesExtension = applyFirProcessSourcesExtension(vfsBasedProjectEnvironment, compilerConfiguration, new GroupedKtSourcesKt$collectSources$3$1(vfsBasedProjectEnvironment), list);
        return iterableApplyFirProcessSourcesExtension == null ? list : iterableApplyFirProcessSourcesExtension;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Iterable<KtSourceFile> applyFirProcessSourcesExtension(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, CompilerConfiguration compilerConfiguration, Function1<? super File, ? extends VirtualFile> function1, Iterable<? extends KtSourceFile> iterable) {
        List compilerExtensions = ExtensionPointUtilsKt.getCompilerExtensions(compilerConfiguration, CollectAdditionalSourceFilesExtension.INSTANCE);
        ArrayList arrayList = new ArrayList();
        for (Object obj : compilerExtensions) {
            if (((CollectAdditionalSourceFilesExtension) obj).isApplicable(compilerConfiguration)) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return iterable;
        }
        Iterator it = arrayList.iterator();
        Iterable iterableCollectSources = iterable;
        while (it.hasNext()) {
            iterableCollectSources = ((CollectAdditionalSourceFilesExtension) it.next()).collectSources(vfsBasedProjectEnvironment, compilerConfiguration, function1, iterableCollectSources);
        }
        return iterableCollectSources;
    }

    public static boolean b(CompilerConfiguration compilerConfiguration, Ref.BooleanRef booleanRef, VfsBasedProjectEnvironment vfsBasedProjectEnvironment, VirtualFile virtualFile, boolean z) {
        virtualFile.getClass();
        String extension = virtualFile.getExtension();
        if (extension != null) {
            int iHashCode = extension.hashCode();
            if (iHashCode != 3433) {
                if (iHashCode == 3254818 && extension.equals("java")) {
                    return false;
                }
            } else if (extension.equals("kt")) {
                return true;
            }
        }
        if (!VirtualFileUtil.isFile(virtualFile)) {
            return false;
        }
        collectSources$ensurePluginsConfigured(booleanRef, vfsBasedProjectEnvironment);
        boolean zAreEqual = Intrinsics.areEqual(virtualFile.getFileType(), KotlinFileType.INSTANCE);
        if (z && !zAreEqual) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getROOTS_RESOLUTION_ERROR(), "Source entry is not a Kotlin file: " + virtualFile.getPath(), null, 4, null);
        }
        return zAreEqual;
    }

    public static int c(KtSourceFile ktSourceFile, KtSourceFile ktSourceFile2) {
        String path = ktSourceFile.getPath();
        if (path == null) {
            k2d.a("Expected a file with a well-defined path");
            return 0;
        }
        String path2 = ktSourceFile2.getPath();
        if (path2 != null) {
            return path.compareTo(path2);
        }
        k2d.a("Expected a file with a well-defined path");
        return 0;
    }

    public static final GroupedKtSources collectSources(final CompilerConfiguration compilerConfiguration, final VfsBasedProjectEnvironment vfsBasedProjectEnvironment) {
        compilerConfiguration.getClass();
        vfsBasedProjectEnvironment.getClass();
        Set<KtSourceFile> setCollectSources$createSet = collectSources$createSet(compilerConfiguration);
        Set<KtSourceFile> setCollectSources$createSet2 = collectSources$createSet(compilerConfiguration);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        final PreprocessedFileCreator preprocessedFileCreator = new PreprocessedFileCreator(vfsBasedProjectEnvironment.getProject());
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        for (SourceFileWithModule sourceFileWithModule : CoreEnvironmentUtilsKt.allSourceFilesSequence(CoreEnvironmentUtilsKt.getSourceRootsCheckingForDuplicates(compilerConfiguration), compilerConfiguration, null, new AnonymousClass1(vfsBasedProjectEnvironment), new ValidSourceFilesFilter() { // from class: k06
            @Override // org.jetbrains.kotlin.cli.jvm.compiler.ValidSourceFilesFilter
            public final boolean invoke(Object obj, boolean z) {
                return GroupedKtSourcesKt.b(compilerConfiguration, booleanRef, vfsBasedProjectEnvironment, (VirtualFile) obj, z);
            }
        }, new Function1() { // from class: l06
            public final Object invoke(Object obj) {
                return GroupedKtSourcesKt.a(preprocessedFileCreator, vfsBasedProjectEnvironment, compilerConfiguration, (VirtualFile) obj);
            }
        })) {
            for (KtSourceFile ktSourceFile : sourceFileWithModule.getSourceFiles()) {
                if (sourceFileWithModule.getIsCommon()) {
                    setCollectSources$createSet2.add(ktSourceFile);
                } else {
                    setCollectSources$createSet.add(ktSourceFile);
                }
                String moduleName = sourceFileWithModule.getModuleName();
                if (moduleName != null) {
                    Object linkedHashSet = linkedHashMap.get(moduleName);
                    if (linkedHashSet == null) {
                        linkedHashSet = new LinkedHashSet();
                        linkedHashMap.put(moduleName, linkedHashSet);
                    }
                    ((Set) linkedHashSet).add(ktSourceFile);
                }
            }
        }
        return new GroupedKtSources(setCollectSources$createSet, setCollectSources$createSet2, linkedHashMap);
    }

    private static final Set<KtSourceFile> collectSources$createSet(CompilerConfiguration compilerConfiguration) {
        return CommonConfigurationKeysKt.getDontSortSourceFiles(compilerConfiguration) ? new LinkedHashSet() : new TreeSet(ktSourceFileComparator);
    }

    private static final void collectSources$ensurePluginsConfigured(Ref.BooleanRef booleanRef, VfsBasedProjectEnvironment vfsBasedProjectEnvironment) {
        if (booleanRef.element) {
            return;
        }
        Iterator<CompilerConfigurationExtension> it = CompilerConfigurationExtension.INSTANCE.getInstances(vfsBasedProjectEnvironment.getProject()).iterator();
        while (it.hasNext()) {
            it.next().updateFileRegistry();
        }
        booleanRef.element = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VirtualFile collectSources$findVirtualFile(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, File file) {
        List<VirtualFileSystem> knownFileSystems = vfsBasedProjectEnvironment.getKnownFileSystems();
        String path = FilesKt.normalize(file).getPath();
        path.getClass();
        return VfsBasedProjectEnvironmentKt.findFileByPath(knownFileSystems, path, "file");
    }

    public static final List<KtSourceFile> getAllFiles(GroupedKtSources groupedKtSources) {
        groupedKtSources.getClass();
        return CollectionsKt.plus(groupedKtSources.getPlatformSources(), groupedKtSources.getCommonSources());
    }
}
