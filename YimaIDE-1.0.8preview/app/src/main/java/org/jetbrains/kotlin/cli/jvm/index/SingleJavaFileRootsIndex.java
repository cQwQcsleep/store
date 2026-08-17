package org.jetbrains.kotlin.cli.jvm.index;

import com.intellij.openapi.vfs.VirtualFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00030\bj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0003`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/SingleJavaFileRootsIndex;", Argument.Delimiters.none, "roots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "<init>", "(Ljava/util/List;)V", "classIdsInRoots", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/name/ClassId;", "Lkotlin/collections/ArrayList;", "findJavaSourceClass", "Lcom/intellij/openapi/vfs/VirtualFile;", "classId", "hasPackage", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "findJavaSourceClasses", "getClassIdsForRootAt", "index", Argument.Delimiters.none, "JavaSourceClassIdReader", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SingleJavaFileRootsIndex {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Name PACKAGE_INFO_CLASS_NAME;
    private final ArrayList<List<ClassId>> classIdsInRoots;
    private final List<JavaRoot> roots;

    static {
        Name nameIdentifier = Name.identifier("package-info");
        nameIdentifier.getClass();
        PACKAGE_INFO_CLASS_NAME = nameIdentifier;
    }

    public SingleJavaFileRootsIndex(List<JavaRoot> list) {
        list.getClass();
        this.roots = list;
        Iterator<JavaRoot> it = list.iterator();
        while (it.hasNext()) {
            it.next().getFile().isDirectory();
        }
        this.classIdsInRoots = new ArrayList<>(this.roots.size());
    }

    private final List<ClassId> getClassIdsForRootAt(int index) {
        int size = this.classIdsInRoots.size();
        if (size <= index) {
            while (true) {
                this.classIdsInRoots.add(new JavaSourceClassIdReader(this.roots.get(size).getFile()).readClassIds());
                if (size == index) {
                    break;
                }
                size++;
            }
        }
        List<ClassId> list = this.classIdsInRoots.get(index);
        list.getClass();
        return list;
    }

    public final VirtualFile findJavaSourceClass(ClassId classId) {
        Object next;
        classId.getClass();
        Iterator it = CollectionsKt.getIndices(this.roots).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!getClassIdsForRootAt(((Number) next).intValue()).contains(classId));
        Integer num = (Integer) next;
        if (num == null) {
            return null;
        }
        return this.roots.get(num.intValue()).getFile();
    }

    public final List<ClassId> findJavaSourceClasses(FqName packageFqName) {
        packageFqName.getClass();
        IntRange indices = CollectionsKt.getIndices(this.roots);
        ArrayList arrayList = new ArrayList();
        IntIterator it = indices.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, getClassIdsForRootAt(it.nextInt()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (Intrinsics.areEqual(((ClassId) obj).getPackageFqName(), packageFqName)) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public final boolean hasPackage(FqName packageFqName) {
        packageFqName.getClass();
        int size = this.roots.size();
        for (int i = 0; i < size; i++) {
            List<ClassId> classIdsForRootAt = getClassIdsForRootAt(i);
            if (!(classIdsForRootAt instanceof Collection) || !classIdsForRootAt.isEmpty()) {
                Iterator<T> it = classIdsForRootAt.iterator();
                while (it.hasNext()) {
                    if (((ClassId) it.next()).getPackageFqName().startsWith(packageFqName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/SingleJavaFileRootsIndex$Companion;", Argument.Delimiters.none, "<init>", "()V", "PACKAGE_INFO_CLASS_NAME", "Lorg/jetbrains/kotlin/name/Name;", "getPACKAGE_INFO_CLASS_NAME$org_jetbrains_kotlin_cli_base", "()Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Name getPACKAGE_INFO_CLASS_NAME$org_jetbrains_kotlin_cli_base() {
            return SingleJavaFileRootsIndex.PACKAGE_INFO_CLASS_NAME;
        }

        private Companion() {
        }
    }
}
