package org.jetbrains.kotlin.cli.jvm.index;

import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001JL\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\b\b\u0000\u0010\t*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u001a\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u0001H\t0\u0010H&J:\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00170\u0010H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;", Argument.Delimiters.none, "indexedRoots", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "getIndexedRoots", "()Lkotlin/sequences/Sequence;", "findClasses", Argument.Delimiters.none, "T", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "acceptedRootTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "findClassGivenDirectory", "Lkotlin/Function2;", "Lcom/intellij/openapi/vfs/VirtualFile;", "traverseDirectoriesInPackage", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "continueSearch", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface JvmDependenciesIndex {
    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Collection findClasses$default(JvmDependenciesIndex jvmDependenciesIndex, ClassId classId, Set set, Function2 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: findClasses");
            return null;
        }
        if ((i & 2) != 0) {
            set = JavaRoot.INSTANCE.getSourceAndBinary();
        }
        return jvmDependenciesIndex.findClasses(classId, set, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void traverseDirectoriesInPackage$default(JvmDependenciesIndex jvmDependenciesIndex, FqName fqName, Set set, Function2 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: traverseDirectoriesInPackage");
            return;
        }
        if ((i & 2) != 0) {
            set = JavaRoot.INSTANCE.getSourceAndBinary();
        }
        jvmDependenciesIndex.traverseDirectoriesInPackage(fqName, set, function2);
    }

    <T> Collection<T> findClasses(ClassId classId, Set<? extends JavaRoot.RootType> acceptedRootTypes, Function2<? super VirtualFile, ? super JavaRoot.RootType, ? extends T> findClassGivenDirectory);

    Sequence<JavaRoot> getIndexedRoots();

    void traverseDirectoriesInPackage(FqName packageFqName, Set<? extends JavaRoot.RootType> acceptedRootTypes, Function2<? super VirtualFile, ? super JavaRoot.RootType, Boolean> continueSearch);
}
