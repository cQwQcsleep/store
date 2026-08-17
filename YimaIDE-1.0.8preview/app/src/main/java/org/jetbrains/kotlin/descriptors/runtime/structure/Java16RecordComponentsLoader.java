package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0001H\u0002J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0001H\u0002J\u0014\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\u0001J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\u0001R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/Java16RecordComponentsLoader;", Argument.Delimiters.none, "<init>", "()V", "_cache", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/Java16RecordComponentsLoader$Cache;", "buildCache", "recordComponent", "initCache", "loadGetType", "Ljava/lang/Class;", "loadGetAccessor", "Ljava/lang/reflect/Method;", "Cache", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class Java16RecordComponentsLoader {
    public static final Java16RecordComponentsLoader INSTANCE = new Java16RecordComponentsLoader();
    private static Cache _cache;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/Java16RecordComponentsLoader$Cache;", Argument.Delimiters.none, "getType", "Ljava/lang/reflect/Method;", "getAccessor", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getGetType", "()Ljava/lang/reflect/Method;", "getGetAccessor", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Cache {
        private final Method getAccessor;
        private final Method getType;

        public Cache(Method method, Method method2) {
            this.getType = method;
            this.getAccessor = method2;
        }

        public final Method getGetAccessor() {
            return this.getAccessor;
        }

        public final Method getGetType() {
            return this.getType;
        }
    }

    private Java16RecordComponentsLoader() {
    }

    private final Cache buildCache(Object recordComponent) {
        Class<?> cls = recordComponent.getClass();
        try {
            return new Cache(cls.getMethod("getType", null), cls.getMethod("getAccessor", null));
        } catch (NoSuchMethodException unused) {
            return new Cache(null, null);
        }
    }

    private final Cache initCache(Object recordComponent) {
        Cache cache = _cache;
        if (cache != null) {
            return cache;
        }
        Cache cacheBuildCache = buildCache(recordComponent);
        _cache = cacheBuildCache;
        return cacheBuildCache;
    }

    public final Method loadGetAccessor(Object recordComponent) throws IllegalAccessException, InvocationTargetException {
        recordComponent.getClass();
        Method getAccessor = initCache(recordComponent).getGetAccessor();
        if (getAccessor == null) {
            return null;
        }
        Object objInvoke = getAccessor.invoke(recordComponent, null);
        objInvoke.getClass();
        return (Method) objInvoke;
    }

    public final Class<?> loadGetType(Object recordComponent) throws IllegalAccessException, InvocationTargetException {
        recordComponent.getClass();
        Method getType = initCache(recordComponent).getGetType();
        if (getType == null) {
            return null;
        }
        Object objInvoke = getType.invoke(recordComponent, null);
        objInvoke.getClass();
        return (Class) objInvoke;
    }
}
