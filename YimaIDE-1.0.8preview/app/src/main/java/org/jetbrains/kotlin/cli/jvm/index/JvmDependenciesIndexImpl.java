package org.jetbrains.kotlin.cli.jvm.index;

import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;
import com.intellij.util.containers.IntArrayList;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndexImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001:\u0003GHIB\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ8\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0018\u00101\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u000602H\u0016JJ\u00103\u001a\b\u0012\u0004\u0012\u0002H504\"\b\b\u0000\u00105*\u0002062\u0006\u00107\u001a\u0002082\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u001a\u00109\u001a\u0016\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u000200\u0012\u0006\u0012\u0004\u0018\u0001H502H\u0016JJ\u0010:\u001a\b\u0012\u0004\u0012\u0002H504\"\b\b\u0000\u00105*\u0002062\u0006\u00107\u001a\u0002082\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u001a\u00109\u001a\u0016\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u000200\u0012\u0006\u0012\u0004\u0018\u0001H502H\u0002J9\u0010;\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0018\u0010<\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000602H\u0082\bJ>\u0010=\u001a\u0004\u0018\u00010&2\u0006\u0010>\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020%0\u00032\u0006\u0010@\u001a\u00020\u00112\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\u0002J6\u0010B\u001a\u0004\u0018\u00010&2\u0006\u0010>\u001a\u00020\u00112\f\u0010?\u001a\b\u0012\u0004\u0012\u00020%0\u00032\u0006\u0010@\u001a\u00020\u00112\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\u0002J\u001e\u0010C\u001a\u0004\u0018\u00010&*\u00020&2\u0006\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u000200H\u0002J\u001c\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\f\u0010F\u001a\b\u0012\u0004\u0012\u00020%0\u0003H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u001e8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u000f\u001a\u0004\b\u001f\u0010 R1\u0010\"\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020%\u0012\u0006\u0012\u0004\u0018\u00010&0$0#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u000f\u001a\u0004\b'\u0010(¨\u0006J"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;", "_roots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "shouldOnlyFindFirstClass", Argument.Delimiters.none, "<init>", "(Ljava/util/List;Z)V", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "roots", "getRoots", "()Ljava/util/List;", "roots$delegate", "Lkotlin/Lazy;", "maxIndex", Argument.Delimiters.none, "getMaxIndex", "()I", "rootCache", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$Cache;", "getRootCache", "()Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$Cache;", "rootCache$delegate", "lastClassSearch", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchRequest;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult;", "indexedRoots", "Lkotlin/sequences/Sequence;", "getIndexedRoots", "()Lkotlin/sequences/Sequence;", "indexedRoots$delegate", "packageCache", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "Lcom/intellij/openapi/vfs/VirtualFile;", "getPackageCache", "()[Ljava/util/Map;", "packageCache$delegate", "traverseDirectoriesInPackage", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "acceptedRootTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "continueSearch", "Lkotlin/Function2;", "findClasses", Argument.Delimiters.none, "T", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "findClassGivenDirectory", "searchClasses", "traverseIndex", "handleEntry", "travelPath", "rootIndex", "packagesPath", "fillCachesAfter", "cachesPath", "doTravelPath", "findChildPackage", "subPackageName", "rootType", ModuleXmlParser.PATH, "Cache", "ClassSearchRequest", "ClassSearchResult", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmDependenciesIndexImpl implements JvmDependenciesIndex {

    /* JADX INFO: renamed from: indexedRoots$delegate, reason: from kotlin metadata */
    private final Lazy indexedRoots;
    private Pair<ClassSearchRequest, ? extends ClassSearchResult> lastClassSearch;
    private final ReentrantLock lock;

    /* JADX INFO: renamed from: packageCache$delegate, reason: from kotlin metadata */
    private final Lazy packageCache;

    /* JADX INFO: renamed from: rootCache$delegate, reason: from kotlin metadata */
    private final Lazy rootCache;

    /* JADX INFO: renamed from: roots$delegate, reason: from kotlin metadata */
    private final Lazy roots;
    private final boolean shouldOnlyFindFirstClass;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0006H\u0086\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\u00020\n¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$Cache;", Argument.Delimiters.none, "<init>", "()V", "innerPackageCaches", "Ljava/util/HashMap;", Argument.Delimiters.none, "get", ModuleXmlParser.NAME, "rootIndices", "Lcom/intellij/util/containers/IntArrayList;", "getRootIndices$annotations", "getRootIndices", "()Lcom/intellij/util/containers/IntArrayList;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Cache {
        private final HashMap<String, Cache> innerPackageCaches = new HashMap<>();
        private final IntArrayList rootIndices = new IntArrayList(2);

        public static /* synthetic */ void getRootIndices$annotations() {
        }

        public final Cache get(String name) {
            name.getClass();
            HashMap<String, Cache> map = this.innerPackageCaches;
            Cache cache = map.get(name);
            if (cache == null) {
                cache = new Cache();
                map.put(name, cache);
            }
            return cache;
        }

        public final IntArrayList getRootIndices() {
            return this.rootIndices;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchRequest;", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "acceptedRootTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Ljava/util/Set;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getAcceptedRootTypes", "()Ljava/util/Set;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ClassSearchRequest {
        private final Set<JavaRoot.RootType> acceptedRootTypes;
        private final ClassId classId;

        /* JADX WARN: Multi-variable type inference failed */
        public ClassSearchRequest(ClassId classId, Set<? extends JavaRoot.RootType> set) {
            classId.getClass();
            set.getClass();
            this.classId = classId;
            this.acceptedRootTypes = set;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ClassSearchRequest copy$default(ClassSearchRequest classSearchRequest, ClassId classId, Set set, int i, Object obj) {
            if ((i & 1) != 0) {
                classId = classSearchRequest.classId;
            }
            if ((i & 2) != 0) {
                set = classSearchRequest.acceptedRootTypes;
            }
            return classSearchRequest.copy(classId, set);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ClassId getClassId() {
            return this.classId;
        }

        public final Set<JavaRoot.RootType> component2() {
            return this.acceptedRootTypes;
        }

        public final ClassSearchRequest copy(ClassId classId, Set<? extends JavaRoot.RootType> acceptedRootTypes) {
            classId.getClass();
            acceptedRootTypes.getClass();
            return new ClassSearchRequest(classId, acceptedRootTypes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ClassSearchRequest)) {
                return false;
            }
            ClassSearchRequest classSearchRequest = (ClassSearchRequest) other;
            return Intrinsics.areEqual(this.classId, classSearchRequest.classId) && Intrinsics.areEqual(this.acceptedRootTypes, classSearchRequest.acceptedRootTypes);
        }

        public final Set<JavaRoot.RootType> getAcceptedRootTypes() {
            return this.acceptedRootTypes;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public int hashCode() {
            return (this.classId.hashCode() * 31) + this.acceptedRootTypes.hashCode();
        }

        public String toString() {
            return "ClassSearchRequest(classId=" + this.classId + ", acceptedRootTypes=" + this.acceptedRootTypes + ')';
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JavaRoot.RootType.values().length];
            try {
                iArr[JavaRoot.RootType.BINARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[JavaRoot.RootType.BINARY_SIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[JavaRoot.RootType.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public JvmDependenciesIndexImpl(final List<JavaRoot> list, boolean z) {
        list.getClass();
        this.shouldOnlyFindFirstClass = z;
        this.lock = new ReentrantLock();
        this.roots = LazyKt.lazy(new Function0() { // from class: nw7
            public final Object invoke() {
                return JvmDependenciesIndexImpl.e(list);
            }
        });
        this.rootCache = LazyKt.lazy(new Function0() { // from class: ow7
            public final Object invoke() {
                return JvmDependenciesIndexImpl.f(this.b);
            }
        });
        this.indexedRoots = LazyKt.lazy(new Function0() { // from class: pw7
            public final Object invoke() {
                return JvmDependenciesIndexImpl.c(this.b);
            }
        });
        this.packageCache = LazyKt.lazy(new Function0() { // from class: qw7
            public final Object invoke() {
                return JvmDependenciesIndexImpl.a(this.b);
            }
        });
    }

    public static Object2ObjectOpenHashMap[] a(JvmDependenciesIndexImpl jvmDependenciesIndexImpl) {
        int size = jvmDependenciesIndexImpl.getRoots().size();
        Object2ObjectOpenHashMap[] object2ObjectOpenHashMapArr = new Object2ObjectOpenHashMap[size];
        for (int i = 0; i < size; i++) {
            object2ObjectOpenHashMapArr[i] = new Object2ObjectOpenHashMap();
        }
        return object2ObjectOpenHashMapArr;
    }

    public static boolean b(String str, VirtualFile virtualFile) {
        return !Intrinsics.areEqual(virtualFile.getExtension(), str);
    }

    public static Sequence c(JvmDependenciesIndexImpl jvmDependenciesIndexImpl) {
        return CollectionsKt.asSequence(jvmDependenciesIndexImpl.getRoots());
    }

    private final List<Cache> cachesPath(List<String> path) {
        ArrayList arrayList = new ArrayList(path.size() + 1);
        arrayList.add(getRootCache());
        Cache rootCache = getRootCache();
        Iterator<String> it = path.iterator();
        while (it.hasNext()) {
            rootCache = rootCache.get(it.next());
            arrayList.add(rootCache);
        }
        return arrayList;
    }

    public static boolean d(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    private final VirtualFile doTravelPath(int rootIndex, List<String> packagesPath, int fillCachesAfter, List<Cache> cachesPath) {
        JavaRoot javaRoot = getRoots().get(rootIndex);
        FqName prefixFqName = javaRoot.getPrefixFqName();
        List listPathSegments = prefixFqName != null ? prefixFqName.pathSegments() : null;
        VirtualFile file = javaRoot.getFile();
        int size = packagesPath.size();
        int i = 0;
        while (i < size) {
            String str = packagesPath.get(i);
            if (listPathSegments == null || i >= listPathSegments.size()) {
                file = findChildPackage(file, str, javaRoot.getType());
                if (file == null) {
                    return null;
                }
            } else if (!Intrinsics.areEqual(((Name) listPathSegments.get(i)).getIdentifier(), str)) {
                return null;
            }
            i++;
            if (i > fillCachesAfter) {
                cachesPath.get(i).getRootIndices().add(rootIndex);
            }
        }
        return file;
    }

    public static List e(List list) {
        return CollectionsKt.toList(list);
    }

    public static Cache f(JvmDependenciesIndexImpl jvmDependenciesIndexImpl) {
        Cache cache = new Cache();
        IntRange indices = CollectionsKt.getIndices(jvmDependenciesIndexImpl.getRoots());
        IntArrayList rootIndices = cache.getRootIndices();
        IntIterator it = indices.iterator();
        while (it.hasNext()) {
            rootIndices.add(it.nextInt());
        }
        cache.getRootIndices().add(jvmDependenciesIndexImpl.getMaxIndex());
        cache.getRootIndices().trimToSize();
        return cache;
    }

    private final VirtualFile findChildPackage(VirtualFile virtualFile, String str, JavaRoot.RootType rootType) {
        final String defaultExtension;
        VirtualFile virtualFileFindChild = virtualFile.findChild(str);
        if (virtualFileFindChild == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[rootType.ordinal()];
        if (i == 1) {
            defaultExtension = JavaClassFileType.INSTANCE.getDefaultExtension();
        } else if (i == 2) {
            defaultExtension = "sig";
        } else {
            if (i != 3) {
                bu8.a();
                return null;
            }
            defaultExtension = JavaFileType.INSTANCE.getDefaultExtension();
        }
        defaultExtension.getClass();
        VirtualFile virtualFileFindChild2 = virtualFile.findChild(str + '.' + defaultExtension);
        if (virtualFileFindChild2 != null && !virtualFileFindChild2.isDirectory()) {
            final Function1 function1 = new Function1() { // from class: lw7
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(JvmDependenciesIndexImpl.b(defaultExtension, (VirtualFile) obj));
                }
            };
            if (VfsUtilCore.processFilesRecursively(virtualFileFindChild, new Processor() { // from class: mw7
                public final boolean process(Object obj) {
                    return JvmDependenciesIndexImpl.d(function1, obj);
                }
            })) {
                return null;
            }
        }
        return virtualFileFindChild;
    }

    private final int getMaxIndex() {
        return getRoots().size();
    }

    private final Map<String, VirtualFile>[] getPackageCache() {
        return (Map[]) this.packageCache.getValue();
    }

    private final Cache getRootCache() {
        return (Cache) this.rootCache.getValue();
    }

    private final List<JavaRoot> getRoots() {
        return (List) this.roots.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:17:0x006c  */
    private final <T> Collection<T> searchClasses(ClassId classId, Set<? extends JavaRoot.RootType> acceptedRootTypes, Function2<? super VirtualFile, ? super JavaRoot.RootType, ? extends T> findClassGivenDirectory) {
        VirtualFile virtualFileTravelPath;
        int i;
        Object foundMultiple;
        List arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        FqName packageFqName = classId.getPackageFqName();
        List listPathSegments = packageFqName.pathSegments();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPathSegments, 10));
        Iterator<T> it = listPathSegments.iterator();
        while (true) {
            if (it.hasNext()) {
                String identifierOrNullIfSpecial = ((Name) it.next()).getIdentifierOrNullIfSpecial();
                if (identifierOrNullIfSpecial != null) {
                    arrayList3.add(identifierOrNullIfSpecial);
                }
            } else {
                List<Cache> listCachesPath = cachesPath(arrayList3);
                int i2 = -1;
                int lastIndex = CollectionsKt.getLastIndex(listCachesPath);
                int i3 = -1;
                loop1: while (true) {
                    if (i2 < lastIndex) {
                        IntArrayList rootIndices = listCachesPath.get(lastIndex).getRootIndices();
                        int size = rootIndices.size();
                        int i4 = 0;
                        while (true) {
                            if (i4 < size) {
                                int i5 = rootIndices.get(i4);
                                if (i5 > i3 && (virtualFileTravelPath = travelPath(i5, packageFqName, arrayList3, lastIndex, listCachesPath)) != null) {
                                    JavaRoot javaRoot = getRoots().get(i5);
                                    if (acceptedRootTypes.contains(javaRoot.getType())) {
                                        i = 1;
                                        Object objInvoke = findClassGivenDirectory.invoke(virtualFileTravelPath, javaRoot.getType());
                                        if (objInvoke != null) {
                                            arrayList.add(objInvoke);
                                            arrayList2.add(new ClassSearchResult.Found(virtualFileTravelPath, javaRoot));
                                            if (this.shouldOnlyFindFirstClass) {
                                                break;
                                            }
                                        } else {
                                            continue;
                                        }
                                    }
                                }
                                i4++;
                            } else {
                                if (!rootIndices.isEmpty()) {
                                    i3 = rootIndices.get(rootIndices.size() - 1);
                                }
                                lastIndex--;
                                i2 = -1;
                            }
                        }
                    }
                }
            }
            i = 1;
            break;
        }
        int size2 = arrayList2.size();
        if (size2 != 0) {
            foundMultiple = size2 != i ? new ClassSearchResult.FoundMultiple(arrayList2) : (ClassSearchResult) CollectionsKt.single(arrayList2);
        } else {
            foundMultiple = ClassSearchResult.NotFound.INSTANCE;
        }
        this.lastClassSearch = TuplesKt.to(new ClassSearchRequest(classId, acceptedRootTypes), foundMultiple);
        if (arrayList.isEmpty()) {
            arrayList = CollectionsKt.emptyList();
        }
        return arrayList;
    }

    private final VirtualFile travelPath(int rootIndex, FqName packageFqName, List<String> packagesPath, int fillCachesAfter, List<Cache> cachesPath) {
        if (rootIndex >= getMaxIndex()) {
            int size = cachesPath.size();
            for (int i = fillCachesAfter + 1; i < size; i++) {
                cachesPath.get(i).getRootIndices().add(getMaxIndex());
                cachesPath.get(i).getRootIndices().trimToSize();
            }
            return null;
        }
        Map<String, VirtualFile> map = getPackageCache()[rootIndex];
        String strAsString = packageFqName.asString();
        VirtualFile virtualFileDoTravelPath = map.get(strAsString);
        if (virtualFileDoTravelPath == null) {
            virtualFileDoTravelPath = doTravelPath(rootIndex, packagesPath, fillCachesAfter, cachesPath);
            map.put(strAsString, virtualFileDoTravelPath);
        }
        return virtualFileDoTravelPath;
    }

    @Override // org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex
    public <T> Collection<T> findClasses(ClassId classId, Set<? extends JavaRoot.RootType> acceptedRootTypes, Function2<? super VirtualFile, ? super JavaRoot.RootType, ? extends T> findClassGivenDirectory) {
        classId.getClass();
        acceptedRootTypes.getClass();
        findClassGivenDirectory.getClass();
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            Pair<ClassSearchRequest, ? extends ClassSearchResult> pair = this.lastClassSearch;
            List arrayList = null;
            if (pair != null) {
                ClassSearchRequest classSearchRequest = (ClassSearchRequest) pair.component1();
                ClassSearchResult classSearchResult = (ClassSearchResult) pair.component2();
                if (Intrinsics.areEqual(classSearchRequest.getClassId(), classId)) {
                    if (classSearchResult instanceof ClassSearchResult.NotFound) {
                        if (SetsKt.minus(acceptedRootTypes, classSearchRequest.getAcceptedRootTypes()).isEmpty()) {
                            arrayList = CollectionsKt.emptyList();
                        }
                    } else if (!(classSearchResult instanceof ClassSearchResult.Found)) {
                        if (!(classSearchResult instanceof ClassSearchResult.FoundMultiple)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        if (Intrinsics.areEqual(classSearchRequest.getAcceptedRootTypes(), acceptedRootTypes)) {
                            List<ClassSearchResult.Found> results = ((ClassSearchResult.FoundMultiple) classSearchResult).getResults();
                            arrayList = new ArrayList();
                            for (ClassSearchResult.Found found : results) {
                                Object objInvoke = findClassGivenDirectory.invoke(found.getPackageDirectory(), found.getRoot().getType());
                                if (objInvoke != null) {
                                    arrayList.add(objInvoke);
                                }
                            }
                        }
                    } else if (Intrinsics.areEqual(classSearchRequest.getAcceptedRootTypes(), acceptedRootTypes)) {
                        arrayList = CollectionsKt.listOfNotNull(findClassGivenDirectory.invoke(((ClassSearchResult.Found) classSearchResult).getPackageDirectory(), ((ClassSearchResult.Found) classSearchResult).getRoot().getType()));
                    }
                }
            }
            List listSearchClasses = arrayList != null ? arrayList : searchClasses(classId, acceptedRootTypes, findClassGivenDirectory);
            reentrantLock.unlock();
            return listSearchClasses;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex
    public Sequence<JavaRoot> getIndexedRoots() {
        return (Sequence) this.indexedRoots.getValue();
    }

    @Override // org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex
    public void traverseDirectoriesInPackage(FqName packageFqName, Set<? extends JavaRoot.RootType> acceptedRootTypes, Function2<? super VirtualFile, ? super JavaRoot.RootType, Boolean> continueSearch) {
        JvmDependenciesIndexImpl jvmDependenciesIndexImpl;
        FqName fqName;
        packageFqName.getClass();
        acceptedRootTypes.getClass();
        continueSearch.getClass();
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            List listPathSegments = packageFqName.pathSegments();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPathSegments, 10));
            Iterator it = listPathSegments.iterator();
            while (true) {
                if (!it.hasNext()) {
                    List<Cache> listCachesPath = cachesPath(arrayList);
                    int lastIndex = CollectionsKt.getLastIndex(listCachesPath);
                    int i = -1;
                    loop1: while (-1 < lastIndex) {
                        IntArrayList rootIndices = listCachesPath.get(lastIndex).getRootIndices();
                        int size = rootIndices.size();
                        int i2 = 0;
                        while (true) {
                            if (i2 >= size) {
                                JvmDependenciesIndexImpl jvmDependenciesIndexImpl2 = this;
                                FqName fqName2 = packageFqName;
                                if (!rootIndices.isEmpty()) {
                                    i = rootIndices.get(rootIndices.size() - 1);
                                }
                                lastIndex--;
                                this = jvmDependenciesIndexImpl2;
                                packageFqName = fqName2;
                            } else {
                                int i3 = rootIndices.get(i2);
                                if (i3 > i) {
                                    jvmDependenciesIndexImpl = this;
                                    fqName = packageFqName;
                                    VirtualFile virtualFileTravelPath = jvmDependenciesIndexImpl.travelPath(i3, fqName, arrayList, lastIndex, listCachesPath);
                                    if (virtualFileTravelPath != null) {
                                        JavaRoot javaRoot = jvmDependenciesIndexImpl.getRoots().get(i3);
                                        if (acceptedRootTypes.contains(javaRoot.getType()) && !((Boolean) continueSearch.invoke(virtualFileTravelPath, javaRoot.getType())).booleanValue()) {
                                            break;
                                        }
                                    }
                                } else {
                                    jvmDependenciesIndexImpl = this;
                                    fqName = packageFqName;
                                }
                                i2++;
                                this = jvmDependenciesIndexImpl;
                                packageFqName = fqName;
                            }
                        }
                    }
                    break;
                }
                String identifierOrNullIfSpecial = ((Name) it.next()).getIdentifierOrNullIfSpecial();
                if (identifierOrNullIfSpecial == null) {
                    break;
                } else {
                    arrayList.add(identifierOrNullIfSpecial);
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult;", Argument.Delimiters.none, "<init>", "()V", "Found", "FoundMultiple", "NotFound", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult$Found;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult$FoundMultiple;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult$NotFound;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ClassSearchResult {

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult$Found;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult;", "packageDirectory", "Lcom/intellij/openapi/vfs/VirtualFile;", "root", "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "<init>", "(Lcom/intellij/openapi/vfs/VirtualFile;Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;)V", "getPackageDirectory", "()Lcom/intellij/openapi/vfs/VirtualFile;", "getRoot", "()Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Found extends ClassSearchResult {
            private final VirtualFile packageDirectory;
            private final JavaRoot root;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Found(VirtualFile virtualFile, JavaRoot javaRoot) {
                super(null);
                virtualFile.getClass();
                javaRoot.getClass();
                this.packageDirectory = virtualFile;
                this.root = javaRoot;
            }

            public final VirtualFile getPackageDirectory() {
                return this.packageDirectory;
            }

            public final JavaRoot getRoot() {
                return this.root;
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult$FoundMultiple;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult;", "results", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult$Found;", "<init>", "(Ljava/util/List;)V", "getResults", "()Ljava/util/List;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class FoundMultiple extends ClassSearchResult {
            private final List<Found> results;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public FoundMultiple(List<Found> list) {
                super(null);
                list.getClass();
                this.results = list;
            }

            public final List<Found> getResults() {
                return this.results;
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult$NotFound;", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndexImpl$ClassSearchResult;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class NotFound extends ClassSearchResult {
            public static final NotFound INSTANCE = new NotFound();

            private NotFound() {
                super(null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof NotFound);
            }

            public int hashCode() {
                return -179084092;
            }

            public String toString() {
                return "NotFound";
            }
        }

        public /* synthetic */ ClassSearchResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ClassSearchResult() {
        }
    }
}
