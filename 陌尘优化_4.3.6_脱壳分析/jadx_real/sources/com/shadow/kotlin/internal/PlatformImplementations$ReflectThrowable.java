package com.shadow.kotlin.internal;

import com.shadow.kotlin.io.CloseableKt;
import java.lang.reflect.Method;

/* loaded from: /workspace/unpacked/classes2.dex */
abstract class PlatformImplementations$ReflectThrowable {
    public static final Method addSuppressed;

    static {
        Method method;
        Method[] methods = Throwable.class.getMethods();
        CloseableKt.checkNotNull(methods);
        int length = methods.length;
        int i = 0;
        while (true) {
            method = null;
            if (i >= length) {
                break;
            }
            Method method2 = methods[i];
            if (CloseableKt.areEqual(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                CloseableKt.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
                if (CloseableKt.areEqual(parameterTypes.length == 1 ? parameterTypes[0] : null, Throwable.class)) {
                    method = method2;
                    break;
                }
            }
            i++;
        }
        addSuppressed = method;
        int length2 = methods.length;
        for (int i2 = 0; i2 < length2 && !CloseableKt.areEqual(methods[i2].getName(), "getSuppressed"); i2++) {
        }
    }
}
