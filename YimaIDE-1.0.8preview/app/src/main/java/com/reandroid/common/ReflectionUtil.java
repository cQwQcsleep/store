package com.reandroid.common;

import com.reandroid.dex.key.TypeKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ReflectionUtil {
    public static boolean isInstance(Class<?> cls, Class<?> cls2) {
        if (cls.equals(cls2) || cls2 == Object.class) {
            return true;
        }
        if (cls == Object.class) {
            return false;
        }
        if (!cls.isInterface() && isInstance(cls.getSuperclass(), cls2)) {
            return true;
        }
        for (Class<?> cls3 : cls.getInterfaces()) {
            if (isInstance(cls3, cls2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInstanceReflection(TypeKey typeKey, TypeKey typeKey2) {
        try {
            return isInstance(Class.forName(typeKey.getSourceName()), Class.forName(typeKey2.getSourceName()));
        } catch (Throwable unused) {
            return false;
        }
    }
}
