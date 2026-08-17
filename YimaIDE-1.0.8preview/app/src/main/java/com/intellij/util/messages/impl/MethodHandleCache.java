package com.intellij.util.messages.impl;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class MethodHandleCache {
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final ClassValue<ConcurrentMap<Method, MethodHandle>> CACHE = new ConcurrentMapClassValue();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "method";
        } else {
            objArr[0] = "com/intellij/util/messages/impl/MethodHandleCache";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/util/messages/impl/MethodHandleCache";
        } else {
            objArr[1] = "compute";
        }
        if (i != 1) {
            objArr[2] = "compute";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public static /* synthetic */ MethodHandle a(Object[] objArr, Method method) {
        method.setAccessible(true);
        try {
            MethodHandle methodHandleUnreflect = LOOKUP.unreflect(method);
            return objArr == null ? methodHandleUnreflect : methodHandleUnreflect.asSpreader(Object[].class, objArr.length);
        } catch (IllegalAccessException e) {
            rc6.a(e);
            return null;
        }
    }

    public static MethodHandle compute(Method method, final Object[] objArr) {
        if (method == null) {
            $$$reportNull$$$0(0);
        }
        MethodHandle methodHandle = (MethodHandle) ((ConcurrentMap) CACHE.get(method.getDeclaringClass())).computeIfAbsent(method, new Function() { // from class: com.intellij.util.messages.impl.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MethodHandleCache.a(objArr, (Method) obj);
            }
        });
        if (methodHandle == null) {
            $$$reportNull$$$0(1);
        }
        return methodHandle;
    }

    public static final class ConcurrentMapClassValue extends ClassValue<ConcurrentMap<Method, MethodHandle>> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/intellij/util/messages/impl/MethodHandleCache$ConcurrentMapClassValue", "computeValue"));
        }

        private ConcurrentMapClassValue() {
        }

        @Override // java.lang.ClassValue
        /* JADX INFO: renamed from: computeValue, reason: avoid collision after fix types in other method */
        public ConcurrentMap<Method, MethodHandle> computeValue2(Class<?> cls) {
            if (cls == null) {
                $$$reportNull$$$0(0);
            }
            return new ConcurrentHashMap(8);
        }

        @Override // java.lang.ClassValue
        public /* bridge */ /* synthetic */ ConcurrentMap<Method, MethodHandle> computeValue(Class cls) {
            return computeValue2((Class<?>) cls);
        }
    }
}
