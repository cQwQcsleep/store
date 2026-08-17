package org.jdom2.internal;

import defpackage.cac;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ReflectionConstructor {
    public static final <E> E construct(String str, Class<E> cls) {
        try {
            Class<?> cls2 = Class.forName(str);
            if (cls.isAssignableFrom(cls2)) {
                return cls.cast(cls2.getConstructor(null).newInstance(null));
            }
            throw new ClassCastException("Class '" + str + "' is not assignable to '" + cls.getName() + "'.");
        } catch (ClassNotFoundException e) {
            p06.a("Unable to locate class '", str, "'.", e);
            return null;
        } catch (IllegalAccessException e2) {
            cac.a("Unable to access class constructor '", str, e2);
            return null;
        } catch (InstantiationException e3) {
            cac.a("Unable to instantiate class '", str, e3);
            return null;
        } catch (NoSuchMethodException e4) {
            p06.a("Unable to locate class no-arg constructor '", str, "'.", e4);
            return null;
        } catch (SecurityException e5) {
            cac.a("Unable to access class constructor '", str, e5);
            return null;
        } catch (InvocationTargetException e6) {
            cac.a("Unable to call class constructor '", str, e6);
            return null;
        }
    }
}
