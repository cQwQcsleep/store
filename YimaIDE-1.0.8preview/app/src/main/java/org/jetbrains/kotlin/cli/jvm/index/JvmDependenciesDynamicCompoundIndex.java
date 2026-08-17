package org.jetbrains.kotlin.cli.jvm.index;

import com.intellij.openapi.vfs.VirtualFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesDynamicCompoundIndex;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001J\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010JJ\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0017\"\b\b\u0000\u0010\u0018*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u001a\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u0001H\u00180 H\u0016J8\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0018\u0010%\u001a\u0014\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00030 H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0007j\b\u0012\u0004\u0012\u00020\u0001`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesDynamicCompoundIndex;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;", "shouldOnlyFindFirstClass", Argument.Delimiters.none, "<init>", "(Z)V", "indices", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "addIndex", Argument.Delimiters.none, "index", "addNewIndexForRoots", "roots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "indexedRoots", "Lkotlin/sequences/Sequence;", "getIndexedRoots", "()Lkotlin/sequences/Sequence;", "findClasses", Argument.Delimiters.none, "T", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "acceptedRootTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "findClassGivenDirectory", "Lkotlin/Function2;", "Lcom/intellij/openapi/vfs/VirtualFile;", "traverseDirectoriesInPackage", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "continueSearch", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmDependenciesDynamicCompoundIndex implements JvmDependenciesIndex {
    private final ArrayList<JvmDependenciesIndex> indices = new ArrayList<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final boolean shouldOnlyFindFirstClass;

    public JvmDependenciesDynamicCompoundIndex(boolean z) {
        this.shouldOnlyFindFirstClass = z;
    }

    public static Sequence b(JvmDependenciesIndex jvmDependenciesIndex) {
        jvmDependenciesIndex.getClass();
        return jvmDependenciesIndex.getIndexedRoots();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object findClasses$lambda$0$0(ClassId classId, Set set, Function2 function2, JvmDependenciesIndex jvmDependenciesIndex) {
        jvmDependenciesIndex.getClass();
        return CollectionsKt.firstOrNull(jvmDependenciesIndex.findClasses(classId, set, function2));
    }

    public final void addIndex(JvmDependenciesIndex index) {
        index.getClass();
        ReentrantReadWriteLock reentrantReadWriteLock = this.lock;
        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            this.indices.add(index);
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
        } finally {
            while (i < readHoldCount) {
                lock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final JvmDependenciesIndex addNewIndexForRoots(Iterable<JavaRoot> roots) {
        JvmDependenciesIndexImpl jvmDependenciesIndexImpl;
        roots.getClass();
        ReentrantReadWriteLock.ReadLock lock = this.lock.readLock();
        lock.lock();
        try {
            HashSet hashSet = SequencesKt.toHashSet(getIndexedRoots());
            ArrayList arrayList = new ArrayList();
            for (JavaRoot javaRoot : roots) {
                if (!hashSet.contains(javaRoot)) {
                    arrayList.add(javaRoot);
                }
            }
            if (arrayList.isEmpty()) {
                jvmDependenciesIndexImpl = null;
            } else {
                JvmDependenciesIndexImpl jvmDependenciesIndexImpl2 = new JvmDependenciesIndexImpl(arrayList, this.shouldOnlyFindFirstClass);
                addIndex(jvmDependenciesIndexImpl2);
                jvmDependenciesIndexImpl = jvmDependenciesIndexImpl2;
            }
            return jvmDependenciesIndexImpl;
        } finally {
            lock.unlock();
        }
    }

    @Override // org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex
    public <T> Collection<T> findClasses(final ClassId classId, final Set<? extends JavaRoot.RootType> acceptedRootTypes, final Function2<? super VirtualFile, ? super JavaRoot.RootType, ? extends T> findClassGivenDirectory) {
        List listListOfNotNull;
        classId.getClass();
        acceptedRootTypes.getClass();
        findClassGivenDirectory.getClass();
        ReentrantReadWriteLock.ReadLock lock = this.lock.readLock();
        lock.lock();
        try {
            boolean z = this.shouldOnlyFindFirstClass;
            ArrayList<JvmDependenciesIndex> arrayList = this.indices;
            if (z) {
                listListOfNotNull = CollectionsKt.listOfNotNull(SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(arrayList), new Function1() { // from class: kw7
                    public final Object invoke(Object obj) {
                        return JvmDependenciesDynamicCompoundIndex.findClasses$lambda$0$0(classId, acceptedRootTypes, findClassGivenDirectory, (JvmDependenciesIndex) obj);
                    }
                })));
            } else {
                ArrayList arrayList2 = new ArrayList();
                Iterator<T> it = arrayList.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList2, ((JvmDependenciesIndex) it.next()).findClasses(classId, acceptedRootTypes, findClassGivenDirectory));
                }
                listListOfNotNull = arrayList2;
            }
            return listListOfNotNull;
        } finally {
            lock.unlock();
        }
    }

    @Override // org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex
    public Sequence<JavaRoot> getIndexedRoots() {
        return SequencesKt.flatMap(CollectionsKt.asSequence(this.indices), new Function1() { // from class: jw7
            public final Object invoke(Object obj) {
                return JvmDependenciesDynamicCompoundIndex.b((JvmDependenciesIndex) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex
    public void traverseDirectoriesInPackage(FqName packageFqName, Set<? extends JavaRoot.RootType> acceptedRootTypes, Function2<? super VirtualFile, ? super JavaRoot.RootType, Boolean> continueSearch) {
        packageFqName.getClass();
        acceptedRootTypes.getClass();
        continueSearch.getClass();
        ReentrantReadWriteLock.ReadLock lock = this.lock.readLock();
        lock.lock();
        try {
            Iterator<T> it = this.indices.iterator();
            while (it.hasNext()) {
                ((JvmDependenciesIndex) it.next()).traverseDirectoriesInPackage(packageFqName, acceptedRootTypes, continueSearch);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            lock.unlock();
        }
    }
}
