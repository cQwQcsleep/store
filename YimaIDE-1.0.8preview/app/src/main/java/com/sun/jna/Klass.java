package com.sun.jna;

import defpackage.r78;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class Klass {
    private Klass() {
    }

    public static <T> T newInstance(Class<T> cls) {
        try {
            return cls.getDeclaredConstructor(null).newInstance(null);
        } catch (IllegalAccessException e) {
            r78.a(cls, e);
            return null;
        } catch (IllegalArgumentException e2) {
            r78.a(cls, e2);
            return null;
        } catch (InstantiationException e3) {
            r78.a(cls, e3);
            return null;
        } catch (NoSuchMethodException e4) {
            r78.a(cls, e4);
            return null;
        } catch (SecurityException e5) {
            r78.a(cls, e5);
            return null;
        } catch (InvocationTargetException e6) {
            if (e6.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e6.getCause());
            }
            r78.a(cls, e6);
            return null;
        }
    }
}
