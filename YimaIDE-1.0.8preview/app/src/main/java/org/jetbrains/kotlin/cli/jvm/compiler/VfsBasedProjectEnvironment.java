package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.PsiElementFinder;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.DelegatingGlobalSearchScope;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.ProjectScope;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtIoFileSourceFile;
import org.jetbrains.kotlin.KtPsiSourceFile;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtVirtualFileSourceFile;
import org.jetbrains.kotlin.asJava.finder.JavaElementFinder;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.java.FirJavaAnnotationProvider;
import org.jetbrains.kotlin.fir.java.FirJavaAnnotationProviderKt;
import org.jetbrains.kotlin.fir.java.FirJavaElementFinder;
import org.jetbrains.kotlin.fir.java.FirJavaFacadeForSource;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment;
import org.jetbrains.kotlin.fir.session.environment.AbstractProjectFileSearchScope;
import org.jetbrains.kotlin.load.java.JavaClassFinderImplKt;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinderFactory;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u000278B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b\u000b\u0010\fB-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b\u000b\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J,\u0010\u001e\u001a\u0014  *\t\u0018\u00010\t¢\u0006\u0002\b\u001f0\t¢\u0006\u0002\b\u001f*\b\u0012\u0004\u0012\u00020!0\u00052\u0006\u0010\"\u001a\u00020#H\u0002J\u001e\u0010$\u001a\u00020\u00162\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010\"\u001a\u00020#H\u0016J\u001e\u0010(\u001a\u00020\u00162\f\u0010%\u001a\b\u0012\u0004\u0012\u00020)0&2\u0006\u0010\"\u001a\u00020#H\u0016J\u0016\u0010*\u001a\u00020\u00162\f\u0010+\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016J\u0016\u0010,\u001a\u00020\u00162\f\u0010-\u001a\b\u0012\u0004\u0012\u00020.0&H\u0016J\u0014\u0010/\u001a\u00020\u00162\f\u0010%\u001a\b\u0012\u0004\u0012\u0002000&J\b\u00101\u001a\u00020\u0016H\u0016J\b\u00102\u001a\u00020\u0016H\u0016J \u00103\u001a\u0002042\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u00105\u001a\u0002062\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectEnvironment;", "project", "Lcom/intellij/openapi/project/Project;", "knownFileSystems", Argument.Delimiters.none, "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "getPackagePartProviderFn", "Lkotlin/Function1;", "Lcom/intellij/psi/search/GlobalSearchScope;", "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "<init>", "(Lcom/intellij/openapi/project/Project;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "fileSystem", "(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFileSystem;Lkotlin/jvm/functions/Function1;)V", "getProject", "()Lcom/intellij/openapi/project/Project;", "getKnownFileSystems", "()Ljava/util/List;", "getKotlinClassFinder", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "fileSearchScope", "Lorg/jetbrains/kotlin/fir/session/environment/AbstractProjectFileSearchScope;", "getJavaModuleResolver", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver;", "getPackagePartProvider", "registerAsJavaElementFinder", Argument.Delimiters.none, "firSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "toSearchScope", "Lorg/jetbrains/annotations/NotNull;", "kotlin.jvm.PlatformType", "Lcom/intellij/openapi/vfs/VirtualFile;", "allowOutOfProjectRoots", Argument.Delimiters.none, "getSearchScopeByIoFiles", "files", Argument.Delimiters.none, "Ljava/io/File;", "getSearchScopeBySourceFiles", "Lorg/jetbrains/kotlin/KtSourceFile;", "getSearchScopeByDirectories", "directories", "getSearchScopeByClassPath", "paths", "Ljava/nio/file/Path;", "getSearchScopeByPsiFiles", "Lcom/intellij/psi/PsiFile;", "getSearchScopeForProjectLibraries", "getSearchScopeForProjectJavaSources", "getFirJavaFacade", "Lorg/jetbrains/kotlin/fir/java/FirJavaFacadeForSource;", "baseModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "ClassPathScope", "DirectoriesScope", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class VfsBasedProjectEnvironment implements AbstractProjectEnvironment {
    private final Function1<GlobalSearchScope, PackagePartProvider> getPackagePartProviderFn;
    private final List<VirtualFileSystem> knownFileSystems;
    private final Project project;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0096\u0002J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004RJ\u0010\t\u001a>\u0012\u0004\u0012\u00020\u000b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00060\fj\b\u0012\u0004\u0012\u00020\u0006`\r0\nj\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00060\fj\b\u0012\u0004\u0012\u00020\u0006`\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment$ClassPathScope;", "Lcom/intellij/psi/search/DelegatingGlobalSearchScope;", "project", "Lcom/intellij/openapi/project/Project;", "roots", Argument.Delimiters.none, "Lcom/intellij/openapi/vfs/VirtualFile;", "<init>", "(Lcom/intellij/openapi/project/Project;Ljava/lang/Iterable;)V", "fileSystemsToRoots", "Ljava/util/HashMap;", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "Lkotlin/collections/HashMap;", "contains", Argument.Delimiters.none, "file", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ClassPathScope extends DelegatingGlobalSearchScope {
        private final HashMap<VirtualFileSystem, HashSet<VirtualFile>> fileSystemsToRoots;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ClassPathScope(Project project, Iterable<? extends VirtualFile> iterable) {
            super(GlobalSearchScope.allScope(project));
            project.getClass();
            iterable.getClass();
            this.fileSystemsToRoots = new HashMap<>();
            for (VirtualFile virtualFile : iterable) {
                VirtualFileSystem fileSystem = virtualFile.getFileSystem();
                fileSystem.getClass();
                HashMap<VirtualFileSystem, HashSet<VirtualFile>> map = this.fileSystemsToRoots;
                HashSet<VirtualFile> hashSet = map.get(fileSystem);
                if (hashSet == null) {
                    hashSet = new HashSet<>();
                    map.put(fileSystem, hashSet);
                }
                hashSet.add(virtualFile);
            }
        }

        public static CharSequence a(VirtualFile virtualFile) {
            virtualFile.getClass();
            String path = virtualFile.getPath();
            path.getClass();
            return path;
        }

        public boolean contains(VirtualFile file) {
            file.getClass();
            HashSet<VirtualFile> hashSet = this.fileSystemsToRoots.get(file.getFileSystem());
            if (hashSet == null) {
                return false;
            }
            String path = file.getPath();
            path.getClass();
            int iIndexOf$default = StringsKt.indexOf$default(path, "!/", 0, false, 6, (Object) null);
            if (iIndexOf$default >= 0) {
                VirtualFileSystem fileSystem = file.getFileSystem();
                String path2 = file.getPath();
                path2.getClass();
                return CollectionsKt.contains(hashSet, fileSystem.findFileByPath(path2.substring(0, iIndexOf$default + 2)));
            }
            while (!hashSet.contains(file)) {
                file = file.getParent();
                if (file == null) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("All files under: ");
            Collection<HashSet<VirtualFile>> collectionValues = this.fileSystemsToRoots.values();
            collectionValues.getClass();
            sb.append(CollectionsKt.joinToString$default(CollectionsKt.flatten(collectionValues), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.cli.jvm.compiler.e
                public final Object invoke(Object obj) {
                    return VfsBasedProjectEnvironment.ClassPathScope.a((VirtualFile) obj);
                }
            }, 31, (Object) null));
            return sb.toString();
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0096\u0002J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\t\u001a&\u0012\u000e\u0012\f0\u000b¢\u0006\u0002\b\f¢\u0006\u0002\b\r0\nj\u0012\u0012\u000e\u0012\f0\u000b¢\u0006\u0002\b\f¢\u0006\u0002\b\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment$DirectoriesScope;", "Lcom/intellij/psi/search/DelegatingGlobalSearchScope;", "project", "Lcom/intellij/openapi/project/Project;", "directories", Argument.Delimiters.none, "Lcom/intellij/openapi/vfs/VirtualFile;", "<init>", "(Lcom/intellij/openapi/project/Project;Ljava/util/Set;)V", "fileSystems", "Ljava/util/HashSet;", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlin/collections/HashSet;", "contains", Argument.Delimiters.none, "file", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DirectoriesScope extends DelegatingGlobalSearchScope {
        private final Set<VirtualFile> directories;
        private final HashSet<VirtualFileSystem> fileSystems;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DirectoriesScope(Project project, Set<? extends VirtualFile> set) {
            super(GlobalSearchScope.allScope(project));
            project.getClass();
            set.getClass();
            this.directories = set;
            HashSet<VirtualFileSystem> hashSet = new HashSet<>();
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                hashSet.add(((VirtualFile) it.next()).getFileSystem());
            }
            this.fileSystems = hashSet;
        }

        public boolean contains(VirtualFile file) {
            file.getClass();
            if (!this.fileSystems.contains(file.getFileSystem())) {
                return false;
            }
            while (!this.directories.contains(file)) {
                file = file.getParent();
                if (file == null) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return "All files under: " + this.directories;
        }
    }

    public VfsBasedProjectEnvironment(Project project, List<? extends VirtualFileSystem> list, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1) {
        project.getClass();
        list.getClass();
        function1.getClass();
        this.project = project;
        this.knownFileSystems = list;
        this.getPackagePartProviderFn = function1;
    }

    public static void a(ExtensionPoint extensionPoint) {
        List extensionList = extensionPoint.getExtensionList();
        extensionList.getClass();
        List list = extensionList;
        if ((list instanceof Collection) && list.isEmpty()) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((PsiElementFinder) it.next()) instanceof FirJavaElementFinder) {
                extensionPoint.unregisterExtension(FirJavaElementFinder.class);
                return;
            }
        }
    }

    private final GlobalSearchScope toSearchScope(List<? extends VirtualFile> list, boolean z) {
        if (list.isEmpty()) {
            list = null;
        }
        if (list != null) {
            Project project = this.project;
            GlobalSearchScope globalSearchScopeFilesWithLibrariesScope = z ? GlobalSearchScope.filesWithLibrariesScope(project, list) : GlobalSearchScope.filesWithoutLibrariesScope(project, list);
            if (globalSearchScopeFilesWithLibrariesScope != null) {
                return globalSearchScopeFilesWithLibrariesScope;
            }
        }
        return GlobalSearchScope.EMPTY_SCOPE;
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public FirJavaFacadeForSource getFirJavaFacade(FirSession firSession, FirModuleData baseModuleData, AbstractProjectFileSearchScope fileSearchScope) {
        firSession.getClass();
        baseModuleData.getClass();
        fileSearchScope.getClass();
        FirJavaAnnotationProvider javaAnnotationProvider = FirJavaAnnotationProviderKt.getJavaAnnotationProvider(firSession);
        Project project = this.project;
        GlobalSearchScope globalSearchScopeAsPsiSearchScope = VfsBasedProjectEnvironmentKt.asPsiSearchScope(fileSearchScope);
        globalSearchScopeAsPsiSearchScope.getClass();
        return new FirJavaFacadeForSource(firSession, baseModuleData, JavaClassFinderImplKt.createJavaClassFinder(project, globalSearchScopeAsPsiSearchScope, javaAnnotationProvider));
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public JavaModuleResolver getJavaModuleResolver() {
        return JavaModuleResolver.SERVICE.getInstance(this.project);
    }

    public final List<VirtualFileSystem> getKnownFileSystems() {
        return this.knownFileSystems;
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public KotlinClassFinder getKotlinClassFinder(AbstractProjectFileSearchScope fileSearchScope) {
        fileSearchScope.getClass();
        VirtualFileFinderFactory service = VirtualFileFinderFactory.SERVICE.getInstance(this.project);
        GlobalSearchScope globalSearchScopeAsPsiSearchScope = VfsBasedProjectEnvironmentKt.asPsiSearchScope(fileSearchScope);
        globalSearchScopeAsPsiSearchScope.getClass();
        return service.create(globalSearchScopeAsPsiSearchScope);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public PackagePartProvider getPackagePartProvider(AbstractProjectFileSearchScope fileSearchScope) {
        fileSearchScope.getClass();
        Function1<GlobalSearchScope, PackagePartProvider> function1 = this.getPackagePartProviderFn;
        GlobalSearchScope globalSearchScopeAsPsiSearchScope = VfsBasedProjectEnvironmentKt.asPsiSearchScope(fileSearchScope);
        globalSearchScopeAsPsiSearchScope.getClass();
        return (PackagePartProvider) function1.invoke(globalSearchScopeAsPsiSearchScope);
    }

    public final Project getProject() {
        return this.project;
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public AbstractProjectFileSearchScope getSearchScopeByClassPath(Iterable<? extends Path> paths) {
        paths.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<? extends Path> it = paths.iterator();
        while (true) {
            VirtualFile virtualFileFindFileByPath = null;
            if (!it.hasNext()) {
                break;
            }
            Path next = it.next();
            if (Files.isDirectory(next, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                List<VirtualFileSystem> list = this.knownFileSystems;
                String absolutePath = next.toFile().getAbsolutePath();
                absolutePath.getClass();
                virtualFileFindFileByPath = VfsBasedProjectEnvironmentKt.findFileByPath(list, absolutePath, "file");
            } else if (Files.isRegularFile(next, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                virtualFileFindFileByPath = VfsBasedProjectEnvironmentKt.findFileByPath(this.knownFileSystems, next.toFile().getAbsolutePath() + "!/", "jar");
            }
            if (virtualFileFindFileByPath != null) {
                arrayList.add(virtualFileFindFileByPath);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        ClassPathScope classPathScope = arrayList != null ? new ClassPathScope(this.project, arrayList) : GlobalSearchScope.EMPTY_SCOPE;
        classPathScope.getClass();
        return new PsiBasedProjectFileSearchScope(classPathScope);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public AbstractProjectFileSearchScope getSearchScopeByDirectories(Iterable<? extends File> directories) {
        directories.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<? extends File> it = directories.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            File next = it.next();
            List<VirtualFileSystem> list = this.knownFileSystems;
            String absolutePath = next.getAbsolutePath();
            absolutePath.getClass();
            VirtualFile virtualFileFindFileByPath$default = VfsBasedProjectEnvironmentKt.findFileByPath$default(list, absolutePath, null, 2, null);
            if (virtualFileFindFileByPath$default != null) {
                arrayList.add(virtualFileFindFileByPath$default);
            }
        }
        Set set = CollectionsKt.toSet(arrayList);
        Set set2 = set.isEmpty() ? null : set;
        DirectoriesScope directoriesScope = set2 != null ? new DirectoriesScope(this.project, set2) : GlobalSearchScope.EMPTY_SCOPE;
        directoriesScope.getClass();
        return new PsiBasedProjectFileSearchScope(directoriesScope);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public AbstractProjectFileSearchScope getSearchScopeByIoFiles(Iterable<? extends File> files, boolean allowOutOfProjectRoots) {
        files.getClass();
        ArrayList arrayList = new ArrayList();
        for (File file : files) {
            List<VirtualFileSystem> list = this.knownFileSystems;
            String absolutePath = file.getAbsolutePath();
            absolutePath.getClass();
            VirtualFile virtualFileFindFileByPath$default = VfsBasedProjectEnvironmentKt.findFileByPath$default(list, absolutePath, null, 2, null);
            if (virtualFileFindFileByPath$default != null) {
                arrayList.add(virtualFileFindFileByPath$default);
            }
        }
        GlobalSearchScope searchScope = toSearchScope(arrayList, allowOutOfProjectRoots);
        searchScope.getClass();
        return new PsiBasedProjectFileSearchScope(searchScope);
    }

    public final AbstractProjectFileSearchScope getSearchScopeByPsiFiles(Iterable<? extends PsiFile> files) {
        files.getClass();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(files, 10));
        Iterator<? extends PsiFile> it = files.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getVirtualFile());
        }
        GlobalSearchScope globalSearchScopeFilesWithoutLibrariesScope = GlobalSearchScope.filesWithoutLibrariesScope(this.project, arrayList);
        globalSearchScopeFilesWithoutLibrariesScope.getClass();
        return new PsiBasedProjectFileSearchScope(globalSearchScopeFilesWithoutLibrariesScope);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public AbstractProjectFileSearchScope getSearchScopeBySourceFiles(Iterable<? extends KtSourceFile> files, boolean allowOutOfProjectRoots) {
        VirtualFile virtualFileFindFileByPath$default;
        files.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<? extends KtSourceFile> it = files.iterator();
        while (it.hasNext()) {
            KtPsiSourceFile ktPsiSourceFile = (KtSourceFile) it.next();
            if (ktPsiSourceFile instanceof KtPsiSourceFile) {
                virtualFileFindFileByPath$default = ktPsiSourceFile.getPsiFile().getVirtualFile();
            } else if (ktPsiSourceFile instanceof KtVirtualFileSourceFile) {
                virtualFileFindFileByPath$default = ((KtVirtualFileSourceFile) ktPsiSourceFile).getVirtualFile();
            } else if (ktPsiSourceFile instanceof KtIoFileSourceFile) {
                List<VirtualFileSystem> list = this.knownFileSystems;
                String absolutePath = ((KtIoFileSourceFile) ktPsiSourceFile).getFile().getAbsolutePath();
                absolutePath.getClass();
                virtualFileFindFileByPath$default = VfsBasedProjectEnvironmentKt.findFileByPath$default(list, absolutePath, null, 2, null);
            } else {
                virtualFileFindFileByPath$default = null;
            }
            if (virtualFileFindFileByPath$default != null) {
                arrayList.add(virtualFileFindFileByPath$default);
            }
        }
        GlobalSearchScope searchScope = toSearchScope(arrayList, allowOutOfProjectRoots);
        searchScope.getClass();
        return new PsiBasedProjectFileSearchScope(searchScope);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public AbstractProjectFileSearchScope getSearchScopeForProjectJavaSources() {
        return new PsiBasedProjectFileSearchScope(new AllJavaSourcesInProjectScope(this.project));
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public AbstractProjectFileSearchScope getSearchScopeForProjectLibraries() {
        GlobalSearchScope librariesScope = ProjectScope.getLibrariesScope(this.project);
        librariesScope.getClass();
        return new PsiBasedProjectFileSearchScope(librariesScope);
    }

    @Override // org.jetbrains.kotlin.fir.session.environment.AbstractProjectEnvironment
    public void registerAsJavaElementFinder(FirSession firSession) {
        firSession.getClass();
        final ExtensionPoint point = PsiElementFinder.EP.getPoint(this.project);
        List extensionList = point.getExtensionList();
        extensionList.getClass();
        List list = extensionList;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((PsiElementFinder) it.next()) instanceof JavaElementFinder) {
                    point.unregisterExtension(JavaElementFinder.class);
                    break;
                }
            }
        }
        List extensionList2 = point.getExtensionList();
        extensionList2.getClass();
        List list2 = extensionList2;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                if (((PsiElementFinder) it2.next()) instanceof FirJavaElementFinder) {
                    point.unregisterExtension(FirJavaElementFinder.class);
                    break;
                }
            }
        }
        FirJavaElementFinder firJavaElementFinder = new FirJavaElementFinder(firSession, this.project);
        firSession.register(Reflection.getOrCreateKotlinClass(FirJavaElementFinder.class), firJavaElementFinder);
        PsiElementFinder.EP.getPoint(this.project).registerExtension(firJavaElementFinder);
        Disposer.register(this.project, new Disposable() { // from class: ubf
            public final void dispose() {
                VfsBasedProjectEnvironment.a(point);
            }
        });
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VfsBasedProjectEnvironment(Project project, VirtualFileSystem virtualFileSystem, Function1<? super GlobalSearchScope, ? extends PackagePartProvider> function1) {
        this(project, (List<? extends VirtualFileSystem>) CollectionsKt.listOf(virtualFileSystem), function1);
        project.getClass();
        virtualFileSystem.getClass();
        function1.getClass();
    }
}
