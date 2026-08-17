package org.jetbrains.kotlin.cli.jvm.modules;

import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.modules.CliJavaModuleResolver;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.idea.KotlinFileType;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinder;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModule;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleInfo;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0015\u0010\u001b\u001a\u00020\u001c*\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0082\u0002J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001a2\u0006\u0010 \u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/modules/CliJavaModuleResolver;", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver;", "moduleGraph", "Lorg/jetbrains/kotlin/cli/jvm/modules/JavaModuleGraph;", "userModules", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule;", "systemModules", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule$Explicit;", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/modules/JavaModuleGraph;Ljava/util/List;Ljava/util/List;Lcom/intellij/openapi/project/Project;)V", "virtualFileFinder", "Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinder;", "getVirtualFileFinder", "()Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinder;", "virtualFileFinder$delegate", "Lkotlin/Lazy;", "getAnnotationsForModuleOwnerOfClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaAnnotation;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "sourceModule", "findJavaModule", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "contains", Argument.Delimiters.none, "checkAccessibility", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleResolver$AccessError;", "fileFromOurModule", "referencedFile", "referencedPackage", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliJavaModuleResolver implements JavaModuleResolver {
    private final JavaModuleGraph moduleGraph;
    private final Project project;
    private final JavaModule sourceModule;
    private final List<JavaModule.Explicit> systemModules;
    private final List<JavaModule> userModules;

    /* JADX INFO: renamed from: virtualFileFinder$delegate, reason: from kotlin metadata */
    private final Lazy virtualFileFinder;

    public CliJavaModuleResolver(JavaModuleGraph javaModuleGraph, List<? extends JavaModule> list, List<JavaModule.Explicit> list2, Project project) {
        javaModuleGraph.getClass();
        list.getClass();
        list2.getClass();
        project.getClass();
        this.moduleGraph = javaModuleGraph;
        this.userModules = list;
        this.systemModules = list2;
        this.project = project;
        List<? extends JavaModule> list3 = list;
        if (!(list3 instanceof Collection) || !list3.isEmpty()) {
            Iterator<T> it = list3.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (((JavaModule) it.next()).isSourceModule() && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        this.virtualFileFinder = LazyKt.lazy(new Function0() { // from class: d02
            public final Object invoke() {
                return CliJavaModuleResolver.a(this.b);
            }
        });
        for (Object obj : this.userModules) {
            if (((JavaModule) obj).isSourceModule()) {
                this.sourceModule = (JavaModule) obj;
            }
        }
        obj = null;
        this.sourceModule = (JavaModule) obj;
    }

    public static VirtualFileFinder a(CliJavaModuleResolver cliJavaModuleResolver) {
        return VirtualFileFinder.SERVICE.getInstance(cliJavaModuleResolver.project, (ModuleInfo) null);
    }

    private final boolean contains(JavaModule javaModule, VirtualFile virtualFile) {
        javaModule.getClass();
        List<JavaModule.Root> moduleRoots = javaModule.getModuleRoots();
        if ((moduleRoots instanceof Collection) && moduleRoots.isEmpty()) {
            return false;
        }
        for (JavaModule.Root root : moduleRoots) {
            VirtualFile virtualFileComponent1 = root.component1();
            if (root.component2() && VfsUtilCore.isAncestor(virtualFileComponent1, virtualFile, false)) {
                return true;
            }
        }
        return false;
    }

    private final JavaModule findJavaModule(VirtualFile file) {
        Object obj = null;
        if (Intrinsics.areEqual(file.getFileSystem().getProtocol(), "jrt") || Intrinsics.areEqual(file.getExtension(), "sig")) {
            for (Object obj2 : this.systemModules) {
                if (contains((JavaModule.Explicit) obj2, file)) {
                    obj = obj2;
                    break;
                }
            }
            return (JavaModule) obj;
        }
        FileType fileType = file.getFileType();
        if (Intrinsics.areEqual(fileType, KotlinFileType.INSTANCE) || Intrinsics.areEqual(fileType, JavaFileType.INSTANCE)) {
            return this.sourceModule;
        }
        if (!Intrinsics.areEqual(fileType, JavaClassFileType.INSTANCE)) {
            return null;
        }
        for (Object obj3 : this.userModules) {
            if (contains((JavaModule) obj3, file)) {
                obj = obj3;
                break;
            }
        }
        return (JavaModule) obj;
    }

    private final VirtualFileFinder getVirtualFileFinder() {
        return (VirtualFileFinder) this.virtualFileFinder.getValue();
    }

    public JavaModuleResolver.AccessError checkAccessibility(VirtualFile fileFromOurModule, VirtualFile referencedFile, FqName referencedPackage) {
        referencedFile.getClass();
        JavaModule javaModuleFindJavaModule = fileFromOurModule != null ? findJavaModule(fileFromOurModule) : null;
        JavaModule javaModuleFindJavaModule2 = findJavaModule(referencedFile);
        if (Intrinsics.areEqual(javaModuleFindJavaModule != null ? javaModuleFindJavaModule.getName() : null, javaModuleFindJavaModule2 != null ? javaModuleFindJavaModule2.getName() : null)) {
            return null;
        }
        if (javaModuleFindJavaModule2 == null) {
            return JavaModuleResolver.AccessError.ModuleDoesNotReadUnnamedModule.INSTANCE;
        }
        if (javaModuleFindJavaModule != null && !this.moduleGraph.reads(javaModuleFindJavaModule.getName(), javaModuleFindJavaModule2.getName())) {
            return new JavaModuleResolver.AccessError.ModuleDoesNotReadModule(javaModuleFindJavaModule2.getName());
        }
        if (referencedPackage == null || javaModuleFindJavaModule2.exports(referencedPackage) || (javaModuleFindJavaModule != null && javaModuleFindJavaModule2.exportsTo(referencedPackage, javaModuleFindJavaModule.getName()))) {
            return null;
        }
        return new JavaModuleResolver.AccessError.ModuleDoesNotExportPackage(javaModuleFindJavaModule2.getName());
    }

    public List<JavaAnnotation> getAnnotationsForModuleOwnerOfClass(ClassId classId) {
        JavaModuleInfo moduleInfo;
        classId.getClass();
        VirtualFile virtualFileFindSourceOrBinaryVirtualFile = getVirtualFileFinder().findSourceOrBinaryVirtualFile(classId);
        if (virtualFileFindSourceOrBinaryVirtualFile == null) {
            return null;
        }
        JavaModule.Explicit explicitFindJavaModule = findJavaModule(virtualFileFindSourceOrBinaryVirtualFile);
        JavaModule.Explicit explicit = explicitFindJavaModule instanceof JavaModule.Explicit ? explicitFindJavaModule : null;
        if (explicit == null || (moduleInfo = explicit.getModuleInfo()) == null) {
            return null;
        }
        return moduleInfo.getAnnotations();
    }
}
