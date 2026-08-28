package com.shadow.kotlin.coroutines.jvm.internal;

import com.shadow.kotlin.io.CloseableKt;
import java.lang.reflect.Method;

/* loaded from: /workspace/unpacked/classes2.dex */
abstract class ModuleNameRetriever {
    private static Cache cache;
    private static final Cache notOnJava9 = new Cache(null, null, null);

    final class Cache {
        public final Method getDescriptorMethod;
        public final Method getModuleMethod;
        public final Method nameMethod;

        public Cache(Method method, Method method2, Method method3) {
            this.getModuleMethod = method;
            this.getDescriptorMethod = method2;
            this.nameMethod = method3;
        }
    }

    public static String getModuleName(BaseContinuationImpl baseContinuationImpl) {
        CloseableKt.checkNotNullParameter(baseContinuationImpl, "continuation");
        Cache cache2 = cache;
        Cache cache3 = notOnJava9;
        if (cache2 == null) {
            try {
                Cache cache4 = new Cache(Class.class.getDeclaredMethod("getModule", null), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", null), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", null));
                cache = cache4;
                cache2 = cache4;
            } catch (Exception unused) {
                cache = cache3;
                cache2 = cache3;
            }
        }
        if (cache2 == cache3) {
            return null;
        }
        Method method = cache2.getModuleMethod;
        Object objInvoke = method != null ? method.invoke(baseContinuationImpl.getClass(), null) : null;
        if (objInvoke == null) {
            return null;
        }
        Method method2 = cache2.getDescriptorMethod;
        Object objInvoke2 = method2 != null ? method2.invoke(objInvoke, null) : null;
        if (objInvoke2 == null) {
            return null;
        }
        Method method3 = cache2.nameMethod;
        Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, null) : null;
        if (objInvoke3 instanceof String) {
            return (String) objInvoke3;
        }
        return null;
    }
}
