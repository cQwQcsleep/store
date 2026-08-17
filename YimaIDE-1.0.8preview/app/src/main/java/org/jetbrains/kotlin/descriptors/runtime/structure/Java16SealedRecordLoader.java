package org.jetbrains.kotlin.descriptors.runtime.structure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0019\u0010\b\u001a\u0004\u0018\u00010\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b¢\u0006\u0002\u0010\fJ#\u0010\r\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0018\u00010\u000e2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b¢\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u0004\u0018\u00010\t2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b¢\u0006\u0002\u0010\fJ\u001f\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b¢\u0006\u0002\u0010\u0012R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/Java16SealedRecordLoader;", Argument.Delimiters.none, "<init>", "()V", "_cache", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/Java16SealedRecordLoader$Cache;", "buildCache", "initCache", "loadIsSealed", Argument.Delimiters.none, "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Boolean;", "loadGetPermittedSubclasses", Argument.Delimiters.none, "(Ljava/lang/Class;)[Ljava/lang/Class;", "loadIsRecord", "loadGetRecordComponents", "(Ljava/lang/Class;)[Ljava/lang/Object;", "Cache", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Java16SealedRecordLoader {
    public static final Java16SealedRecordLoader INSTANCE = new Java16SealedRecordLoader();
    private static Cache _cache;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/structure/Java16SealedRecordLoader$Cache;", Argument.Delimiters.none, "isSealed", "Ljava/lang/reflect/Method;", "getPermittedSubclasses", "isRecord", "getRecordComponents", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "()Ljava/lang/reflect/Method;", "getGetPermittedSubclasses", "getGetRecordComponents", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Cache {
        private final Method getPermittedSubclasses;
        private final Method getRecordComponents;
        private final Method isRecord;
        private final Method isSealed;

        public Cache(Method method, Method method2, Method method3, Method method4) {
            this.isSealed = method;
            this.getPermittedSubclasses = method2;
            this.isRecord = method3;
            this.getRecordComponents = method4;
        }

        public final Method getGetPermittedSubclasses() {
            return this.getPermittedSubclasses;
        }

        public final Method getGetRecordComponents() {
            return this.getRecordComponents;
        }

        /* JADX INFO: renamed from: isRecord, reason: from getter */
        public final Method getIsRecord() {
            return this.isRecord;
        }

        /* JADX INFO: renamed from: isSealed, reason: from getter */
        public final Method getIsSealed() {
            return this.isSealed;
        }
    }

    private Java16SealedRecordLoader() {
    }

    private final Cache buildCache() {
        try {
            return new Cache(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null));
        } catch (NoSuchMethodException unused) {
            return new Cache(null, null, null, null);
        }
    }

    private final Cache initCache() {
        Cache cache = _cache;
        if (cache != null) {
            return cache;
        }
        Cache cacheBuildCache = buildCache();
        _cache = cacheBuildCache;
        return cacheBuildCache;
    }

    public final Class<?>[] loadGetPermittedSubclasses(Class<?> clazz) throws IllegalAccessException, InvocationTargetException {
        clazz.getClass();
        Method getPermittedSubclasses = initCache().getGetPermittedSubclasses();
        if (getPermittedSubclasses == null) {
            return null;
        }
        Object objInvoke = getPermittedSubclasses.invoke(clazz, null);
        objInvoke.getClass();
        return (Class[]) objInvoke;
    }

    public final Object[] loadGetRecordComponents(Class<?> clazz) {
        clazz.getClass();
        Method getRecordComponents = initCache().getGetRecordComponents();
        if (getRecordComponents == null) {
            return null;
        }
        return (Object[]) getRecordComponents.invoke(clazz, null);
    }

    public final Boolean loadIsRecord(Class<?> clazz) throws IllegalAccessException, InvocationTargetException {
        clazz.getClass();
        Method isRecord = initCache().getIsRecord();
        if (isRecord == null) {
            return null;
        }
        Object objInvoke = isRecord.invoke(clazz, null);
        objInvoke.getClass();
        return (Boolean) objInvoke;
    }

    public final Boolean loadIsSealed(Class<?> clazz) throws IllegalAccessException, InvocationTargetException {
        clazz.getClass();
        Method isSealed = initCache().getIsSealed();
        if (isSealed == null) {
            return null;
        }
        Object objInvoke = isSealed.invoke(clazz, null);
        objInvoke.getClass();
        return (Boolean) objInvoke;
    }
}
