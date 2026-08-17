package com.android.tools.r8.internal;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class YW {
    public static final Method a;

    static {
        Method method;
        Method[] methods = Throwable.class.getMethods();
        KB.a(methods);
        int length = methods.length;
        int i = 0;
        while (true) {
            method = null;
            if (i >= length) {
                break;
            }
            Method method2 = methods[i];
            if (KB.a((Object) method2.getName(), (Object) "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                KB.b(parameterTypes, "getParameterTypes(...)");
                if (KB.a(parameterTypes.length == 1 ? parameterTypes[0] : null, Throwable.class)) {
                    method = method2;
                    break;
                }
            }
            i++;
        }
        a = method;
        int length2 = methods.length;
        for (int i2 = 0; i2 < length2 && !KB.a((Object) methods[i2].getName(), (Object) "getSuppressed"); i2++) {
        }
    }
}
