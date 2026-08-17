package org.jetbrains.kotlin.container;

import com.intellij.util.containers.ContainerUtil;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\t2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0006R7\u0010\u0004\u001a+\u0012\u0012\u0012\u0010\u0012\u0002\b\u00030\u0006¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b\u0012\u000e\u0012\f0\t¢\u0006\u0002\b\u0007¢\u0006\u0002\b\b0\u0005¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/container/ClassTraversalCache;", Argument.Delimiters.none, "<init>", "()V", "cache", "Ljava/util/concurrent/ConcurrentMap;", "Ljava/lang/Class;", "Lorg/jetbrains/annotations/NotNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lorg/jetbrains/kotlin/container/ClassInfo;", "getClassInfo", "c", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ClassTraversalCache {
    public static final ClassTraversalCache INSTANCE = new ClassTraversalCache();
    private static final ConcurrentMap<Class<?>, ClassInfo> cache;

    static {
        ConcurrentMap<Class<?>, ClassInfo> concurrentHashMap = System.getProperty("idea.system.path") != null ? new ConcurrentHashMap<>() : ContainerUtil.createConcurrentWeakKeySoftValueMap();
        concurrentHashMap.getClass();
        cache = concurrentHashMap;
    }

    private ClassTraversalCache() {
    }

    public final ClassInfo getClassInfo(Class<?> c) {
        c.getClass();
        ConcurrentMap<Class<?>, ClassInfo> concurrentMap = cache;
        ClassInfo classInfo = concurrentMap.get(c);
        if (classInfo != null) {
            return classInfo;
        }
        ClassInfo classInfoTraverseClass = CacheKt.traverseClass(c);
        concurrentMap.put(c, classInfoTraverseClass);
        return classInfoTraverseClass;
    }
}
