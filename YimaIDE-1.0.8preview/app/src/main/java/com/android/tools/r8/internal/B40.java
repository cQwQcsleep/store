package com.android.tools.r8.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class B40 {
    public static final AbstractC3082y40 a;

    static {
        AbstractC3082y40 c3166z40;
        try {
            c3166z40 = new A40();
        } catch (NoSuchMethodException unused) {
            c3166z40 = new C3166z40();
        }
        a = c3166z40;
    }

    public static String a(AccessibleObject accessibleObject, boolean z) {
        String string;
        if (accessibleObject instanceof Field) {
            StringBuilder sb = new StringBuilder("field '");
            Field field = (Field) accessibleObject;
            sb.append(field.getDeclaringClass().getName() + "#" + field.getName());
            sb.append("'");
            string = sb.toString();
        } else if (accessibleObject instanceof Method) {
            Method method = (Method) accessibleObject;
            StringBuilder sb2 = new StringBuilder(method.getName());
            a(method, sb2);
            string = "method '" + method.getDeclaringClass().getName() + "#" + sb2.toString() + "'";
        } else if (accessibleObject instanceof Constructor) {
            string = "constructor '" + a((Constructor) accessibleObject) + "'";
        } else {
            string = "<unknown AccessibleObject> " + accessibleObject.toString();
        }
        if (!z || !Character.isLowerCase(string.charAt(0))) {
            return string;
        }
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static void a(AccessibleObject accessibleObject) {
        try {
            accessibleObject.setAccessible(true);
        } catch (Exception e) {
            throw new C1729iD(C40.a("Failed making ", a(accessibleObject, false), " accessible; either increase its visibility or write a custom TypeAdapter for its declaring type."), e);
        }
    }

    public static String a(Constructor constructor) {
        StringBuilder sb = new StringBuilder(constructor.getDeclaringClass().getName());
        a(constructor, sb);
        return sb.toString();
    }

    public static void a(AccessibleObject accessibleObject, StringBuilder sb) {
        Class<?>[] parameterTypes;
        sb.append('(');
        if (accessibleObject instanceof Method) {
            parameterTypes = ((Method) accessibleObject).getParameterTypes();
        } else {
            parameterTypes = ((Constructor) accessibleObject).getParameterTypes();
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(parameterTypes[i].getSimpleName());
        }
        sb.append(')');
    }
}
