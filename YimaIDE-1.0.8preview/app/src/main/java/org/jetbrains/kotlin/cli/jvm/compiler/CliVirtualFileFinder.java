package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.CliVirtualFileFinder;
import org.jetbrains.kotlin.cli.jvm.index.JavaRoot;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinder;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0017\u0010\u0010\u001a\t\u0018\u00010\r¢\u0006\u0002\b\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0018H\u0016J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J(\u0010\u001c\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0015H\u0002J\u001f\u0010 \u001a\t\u0018\u00010\r¢\u0006\u0002\b\u00112\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u0016H\u0002J-\u0010#\u001a\t\u0018\u00010\r¢\u0006\u0002\b\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u00162\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0015H\u0002J\u0017\u0010#\u001a\t\u0018\u00010\r¢\u0006\u0002\b\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001a\u0010$\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u0016H\u0002J\u001a\u0010%\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/CliVirtualFileFinder;", "Lorg/jetbrains/kotlin/load/kotlin/VirtualFileFinder;", "index", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;", "scope", "Lcom/intellij/psi/search/GlobalSearchScope;", "enableSearchInCtSym", Argument.Delimiters.none, "perfManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;Lcom/intellij/psi/search/GlobalSearchScope;ZLorg/jetbrains/kotlin/util/PerformanceManager;)V", "findVirtualFileWithHeader", "Lcom/intellij/openapi/vfs/VirtualFile;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "findSourceOrBinaryVirtualFile", "Lorg/jetbrains/annotations/Nullable;", "findMetadata", "Ljava/io/InputStream;", "findMetadataTopLevelClassesInPackage", Argument.Delimiters.none, Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "hasMetadataPackage", "fqName", "findBuiltInsData", "findClass", "fileName", "rootType", "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "findSigFileIfEnabled", "dir", "simpleName", "findBinaryOrSigClass", "findBinaryClass", "findSourceClass", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliVirtualFileFinder extends VirtualFileFinder {
    private final boolean enableSearchInCtSym;
    private final JvmDependenciesIndex index;
    private final GlobalSearchScope scope;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CliVirtualFileFinder(JvmDependenciesIndex jvmDependenciesIndex, GlobalSearchScope globalSearchScope, boolean z, PerformanceManager performanceManager) {
        super(performanceManager);
        jvmDependenciesIndex.getClass();
        globalSearchScope.getClass();
        this.index = jvmDependenciesIndex;
        this.scope = globalSearchScope;
        this.enableSearchInCtSym = z;
    }

    public static VirtualFile a(String str, CliVirtualFileFinder cliVirtualFileFinder, VirtualFile virtualFile, JavaRoot.RootType rootType) {
        virtualFile.getClass();
        rootType.getClass();
        VirtualFile virtualFileFindChild = virtualFile.findChild(str + ".class");
        if (virtualFileFindChild == null) {
            virtualFileFindChild = cliVirtualFileFinder.findSigFileIfEnabled(virtualFile, str);
        }
        if (virtualFileFindChild == null || !virtualFileFindChild.isValid()) {
            return null;
        }
        return virtualFileFindChild;
    }

    public static boolean b(Ref.BooleanRef booleanRef, VirtualFile virtualFile, JavaRoot.RootType rootType) {
        virtualFile.getClass();
        rootType.getClass();
        boolean z = booleanRef.element;
        VirtualFile[] children = virtualFile.getChildren();
        children.getClass();
        boolean z2 = false;
        for (VirtualFile virtualFile2 : children) {
            if (Intrinsics.areEqual(virtualFile2.getExtension(), "kotlin_metadata")) {
                z2 = true;
                break;
            }
        }
        boolean z3 = z | z2;
        booleanRef.element = z3;
        return !z3;
    }

    public static boolean c(ObjectOpenHashSet objectOpenHashSet, VirtualFile virtualFile, JavaRoot.RootType rootType) {
        virtualFile.getClass();
        rootType.getClass();
        VirtualFile[] children = virtualFile.getChildren();
        children.getClass();
        for (VirtualFile virtualFile2 : children) {
            if (Intrinsics.areEqual(virtualFile2.getExtension(), "kotlin_metadata")) {
                objectOpenHashSet.add(virtualFile2.getNameWithoutExtension());
            }
        }
        return true;
    }

    public static VirtualFile d(String str, VirtualFile virtualFile, JavaRoot.RootType rootType) {
        virtualFile.getClass();
        rootType.getClass();
        VirtualFile virtualFileFindChild = virtualFile.findChild(str);
        if (virtualFileFindChild == null || !virtualFileFindChild.isValid()) {
            return null;
        }
        return virtualFileFindChild;
    }

    private final VirtualFile findBinaryClass(ClassId classId, String fileName) {
        return findClass(classId, fileName, JavaRoot.INSTANCE.getOnlyBinary());
    }

    private final VirtualFile findBinaryOrSigClass(ClassId classId, final String simpleName, Set<? extends JavaRoot.RootType> rootType) {
        Object next;
        Iterator it = this.index.findClasses(classId, rootType, new Function2() { // from class: h02
            public final Object invoke(Object obj, Object obj2) {
                return CliVirtualFileFinder.a(simpleName, this, (VirtualFile) obj, (JavaRoot.RootType) obj2);
            }
        }).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (this.scope.contains((VirtualFile) next)) {
                return (VirtualFile) next;
            }
        }
        next = null;
        return (VirtualFile) next;
    }

    private final VirtualFile findClass(ClassId classId, final String fileName, Set<? extends JavaRoot.RootType> rootType) {
        Object next;
        Iterator it = this.index.findClasses(classId, rootType, new Function2() { // from class: i02
            public final Object invoke(Object obj, Object obj2) {
                return CliVirtualFileFinder.d(fileName, (VirtualFile) obj, (JavaRoot.RootType) obj2);
            }
        }).iterator();
        while (it.hasNext()) {
            next = it.next();
            if (this.scope.contains((VirtualFile) next)) {
                return (VirtualFile) next;
            }
        }
        next = null;
        return (VirtualFile) next;
    }

    private final VirtualFile findSigFileIfEnabled(VirtualFile dir, String simpleName) {
        if (!this.enableSearchInCtSym) {
            return null;
        }
        return dir.findChild(simpleName + ".sig");
    }

    private final VirtualFile findSourceClass(ClassId classId, String fileName) {
        return findClass(classId, fileName, JavaRoot.INSTANCE.getOnlySource());
    }

    public InputStream findBuiltInsData(FqName packageFqName) {
        packageFqName.getClass();
        Name nameSpecial = Name.special("<builtins-metadata>");
        nameSpecial.getClass();
        VirtualFile virtualFileFindBinaryClass = findBinaryClass(new ClassId(packageFqName, nameSpecial), BuiltInSerializerProtocol.INSTANCE.getBuiltInsFileName(packageFqName));
        if (virtualFileFindBinaryClass != null) {
            return virtualFileFindBinaryClass.getInputStream();
        }
        return null;
    }

    public InputStream findMetadata(ClassId classId) {
        classId.getClass();
        classId.isNestedClass();
        VirtualFile virtualFileFindBinaryClass = findBinaryClass(classId, classId.getShortClassName().asString() + ".kotlin_metadata");
        if (virtualFileFindBinaryClass != null) {
            return virtualFileFindBinaryClass.getInputStream();
        }
        return null;
    }

    public Set<String> findMetadataTopLevelClassesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        final ObjectOpenHashSet objectOpenHashSet = new ObjectOpenHashSet();
        JvmDependenciesIndex.traverseDirectoriesInPackage$default(this.index, packageFqName, null, new Function2() { // from class: g02
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(CliVirtualFileFinder.c(objectOpenHashSet, (VirtualFile) obj, (JavaRoot.RootType) obj2));
            }
        }, 2, null);
        return objectOpenHashSet;
    }

    @Override // org.jetbrains.kotlin.load.kotlin.VirtualFileFinder
    public VirtualFile findSourceOrBinaryVirtualFile(ClassId classId) {
        classId.getClass();
        VirtualFile virtualFileFindBinaryOrSigClass = findBinaryOrSigClass(classId);
        if (virtualFileFindBinaryOrSigClass != null) {
            return virtualFileFindBinaryOrSigClass;
        }
        return findSourceClass(classId, classId.getRelativeClassName().asString() + ".java");
    }

    @Override // org.jetbrains.kotlin.load.kotlin.VirtualFileFinder
    public VirtualFile findVirtualFileWithHeader(ClassId classId) {
        classId.getClass();
        return findBinaryOrSigClass(classId);
    }

    public boolean hasMetadataPackage(FqName fqName) {
        fqName.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        JvmDependenciesIndex.traverseDirectoriesInPackage$default(this.index, fqName, null, new Function2() { // from class: f02
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(CliVirtualFileFinder.b(booleanRef, (VirtualFile) obj, (JavaRoot.RootType) obj2));
            }
        }, 2, null);
        return booleanRef.element;
    }

    private final VirtualFile findBinaryOrSigClass(ClassId classId) {
        return findBinaryOrSigClass(classId, StringsKt.replace$default(classId.getRelativeClassName().asString(), '.', '$', false, 4, (Object) null), JavaRoot.INSTANCE.getOnlyBinary());
    }
}
