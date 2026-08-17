package com.intellij.util;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import com.intellij.openapi.diagnostic.ControlFlowException;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.lang.CompoundRuntimeException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ReflectionUtil {
    private static final Logger LOG = Logger.getInstance(ReflectionUtil.class);
    private static final Object unsafe;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 4 || i == 7 || i == 32 || i == 45 || i == 53 || i == 68 || i == 81 || i == 84 || i == 48 || i == 49 || i == 70 || i == 71 || i == 74 || i == 75) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 4 || i == 7 || i == 32 || i == 45 || i == 53 || i == 68 || i == 81 || i == 84 || i == 48 || i == 49 || i == 70 || i == 71 || i == 74 || i == 75) ? 2 : 3];
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 32:
            case 45:
            case 48:
            case 49:
            case 53:
            case 68:
            case 70:
            case 71:
            case 74:
            case 75:
            case 81:
            case 84:
                objArr[0] = "com/intellij/util/ReflectionUtil";
                break;
            case 2:
            case 5:
            case 13:
            default:
                objArr[0] = "clazz";
                break;
            case 3:
            case 14:
            case 16:
            case 19:
            case 22:
            case 25:
            case 28:
                objArr[0] = "name";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 37:
            case 39:
            case 43:
                objArr[0] = "fieldName";
                break;
            case 8:
                objArr[0] = "rootClass";
                break;
            case 9:
            case 11:
                objArr[0] = "checker";
                break;
            case 10:
                objArr[0] = "visited";
                break;
            case 12:
                objArr[0] = "interfaces";
                break;
            case 15:
                objArr[0] = "object";
                break;
            case 17:
            case 40:
            case 41:
            case PartialGapBuffer.SURROUNDING_SIZE /* 64 */:
            case 65:
            case 66:
                objArr[0] = "field";
                break;
            case 18:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[0] = "methods";
                break;
            case 20:
            case 23:
            case 26:
            case 35:
                objArr[0] = "parameters";
                break;
            case 21:
            case 24:
            case 27:
            case 29:
            case 30:
            case 44:
            case 46:
            case 47:
            case 50:
                objArr[0] = "aClass";
                break;
            case 33:
                objArr[0] = "instanceClass";
                break;
            case 34:
                objArr[0] = "methodName";
                break;
            case 36:
            case 38:
            case 42:
            case 78:
                objArr[0] = "objectClass";
                break;
            case 51:
                objArr[0] = "constructor";
                break;
            case 52:
                objArr[0] = "args";
                break;
            case 54:
            case 57:
            case 62:
                objArr[0] = "from";
                break;
            case 55:
            case 58:
            case 63:
                objArr[0] = "to";
                break;
            case 56:
            case 59:
                objArr[0] = "fields";
                break;
            case 60:
                objArr[0] = "first";
                break;
            case 61:
                objArr[0] = "second";
                break;
            case 67:
                objArr[0] = "fqn";
                break;
            case 69:
                objArr[0] = "type";
                break;
            case 72:
                objArr[0] = "ownerClass";
                break;
            case 73:
                objArr[0] = "fieldType";
                break;
            case 76:
                objArr[0] = "ancestor";
                break;
            case 77:
                objArr[0] = "descendant";
                break;
            case 79:
            case 82:
                objArr[0] = "superInterface";
                break;
            case 80:
            case 83:
                objArr[0] = "handler";
                break;
        }
        if (i == 1) {
            objArr[1] = "collectFields";
        } else if (i == 4) {
            objArr[1] = "findField";
        } else if (i == 7) {
            objArr[1] = "findAssignableField";
        } else if (i == 32) {
            objArr[1] = "filterRealMethods";
        } else if (i == 45) {
            objArr[1] = "getDefaultConstructor";
        } else if (i == 53) {
            objArr[1] = "createInstance";
        } else if (i == 68) {
            objArr[1] = "forName";
        } else if (i == 81 || i == 84) {
            objArr[1] = "proxy";
        } else if (i == 48 || i == 49) {
            objArr[1] = "newInstance";
        } else if (i == 70 || i == 71) {
            objArr[1] = "boxType";
        } else if (i == 74) {
            objArr[1] = "getTheOnlyVolatileInstanceFieldOfClass";
        } else if (i != 75) {
            objArr[1] = "com/intellij/util/ReflectionUtil";
        } else {
            objArr[1] = "getUnsafe";
        }
        switch (i) {
            case 1:
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 32:
            case 45:
            case 48:
            case 49:
            case 53:
            case 68:
            case 70:
            case 71:
            case 74:
            case 75:
            case 81:
            case 84:
                break;
            case 2:
            case 3:
                objArr[2] = "findField";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "findAssignableField";
                break;
            case 8:
            case 9:
                objArr[2] = "findFieldInHierarchy";
                break;
            case 10:
            case 11:
            case 12:
                objArr[2] = "processInterfaces";
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "resetField";
                break;
            case 18:
            case 19:
            case 20:
                objArr[2] = "findMethod";
                break;
            case 21:
            case 22:
            case 23:
                objArr[2] = "getMethod";
                break;
            case 24:
            case 25:
            case 26:
                objArr[2] = "getDeclaredMethod";
                break;
            case 27:
            case 28:
                objArr[2] = "getDeclaredField";
                break;
            case 29:
                objArr[2] = "getClassPublicMethods";
                break;
            case 30:
                objArr[2] = "getClassDeclaredMethods";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[2] = "filterRealMethods";
                break;
            case 33:
            case 34:
            case 35:
                objArr[2] = "getMethodDeclaringClass";
                break;
            case 36:
            case 37:
                objArr[2] = "getField";
                break;
            case 38:
            case 39:
                objArr[2] = "getStaticFieldValue";
                break;
            case 40:
                objArr[2] = "getFieldValue";
                break;
            case 41:
                objArr[2] = "isInstanceField";
                break;
            case 42:
            case 43:
                objArr[2] = "setField";
                break;
            case 44:
                objArr[2] = "getDefaultConstructor";
                break;
            case 46:
            case 47:
                objArr[2] = "newInstance";
                break;
            case 50:
                objArr[2] = "createAsDataClass";
                break;
            case 51:
            case 52:
                objArr[2] = "createInstance";
                break;
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                objArr[2] = "copyFields";
                break;
            case 60:
            case 61:
                objArr[2] = "comparePublicNonFinalFields";
                break;
            case 62:
            case 63:
            case PartialGapBuffer.SURROUNDING_SIZE /* 64 */:
                objArr[2] = "copyFieldValue";
                break;
            case 65:
                objArr[2] = "isPublic";
                break;
            case 66:
                objArr[2] = "isFinal";
                break;
            case 67:
                objArr[2] = "forName";
                break;
            case 69:
                objArr[2] = "boxType";
                break;
            case 72:
            case 73:
                objArr[2] = "getTheOnlyVolatileInstanceFieldOfClass";
                break;
            case 76:
            case 77:
                objArr[2] = "isAssignable";
                break;
            case 78:
                objArr[2] = "dumpFields";
                break;
            case 79:
            case 80:
            case 82:
            case 83:
                objArr[2] = "proxy";
                break;
            default:
                objArr[2] = "collectFields";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 4 && i != 7 && i != 32 && i != 45 && i != 53 && i != 68 && i != 81 && i != 84 && i != 48 && i != 49 && i != 70 && i != 71 && i != 74 && i != 75) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    static {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Object staticFieldValue = getStaticFieldValue(cls, cls, "theUnsafe");
            unsafe = staticFieldValue;
            if (staticFieldValue != null) {
                return;
            }
            f63.a("Could not find 'theUnsafe' field in the Unsafe class");
        } catch (ClassNotFoundException e) {
            rc6.a(e);
        }
    }

    private ReflectionUtil() {
    }

    public static /* synthetic */ boolean a(String str, Class cls, Field field) {
        if (str.equals(field.getName())) {
            return cls == null || cls.isAssignableFrom(field.getType());
        }
        return false;
    }

    public static List<Field> collectFields(Class<?> cls) {
        if (cls == null) {
            $$$reportNull$$$0(0);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Class<?>> it = JBIterableClassTraverser.classTraverser(cls).iterator();
        while (it.hasNext()) {
            Collections.addAll(arrayList, it.next().getDeclaredFields());
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002a  */
    private static <T> T createAsDataClass(Class<T> cls) {
        if (cls == null) {
            $$$reportNull$$$0(50);
        }
        for (Annotation annotation : cls.getAnnotations()) {
            String name = annotation.annotationType().getName();
            if (name.equals("kotlin.Metadata") || name.equals("kotlin.jvm.internal.KotlinClass")) {
                Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
                SmartList<Constructor> smartList = new SmartList();
                SmartList smartList2 = null;
                for (Constructor<?> constructor : declaredConstructors) {
                    try {
                        constructor.setAccessible(true);
                    } catch (Throwable unused) {
                    }
                    try {
                        if (constructor.getParameterCount() == 0) {
                            return (T) constructor.newInstance(null);
                        }
                        Class<?>[] parameterTypes = constructor.getParameterTypes();
                        for (Class<?> cls2 : parameterTypes) {
                            if (cls2.getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) {
                                smartList.add(constructor);
                            }
                        }
                        return (T) constructor.newInstance(new Object[parameterTypes.length]);
                    } catch (Exception e) {
                        if (smartList2 == null) {
                            smartList2 = new SmartList();
                        }
                        smartList2.add(new Exception("Failed to call constructor: " + constructor.toString(), e));
                    }
                }
                for (Constructor constructor2 : smartList) {
                    try {
                        constructor2.setAccessible(true);
                    } catch (Throwable unused2) {
                    }
                    try {
                        return (T) constructor2.newInstance(null);
                    } catch (Exception e2) {
                        if (smartList2 == null) {
                            smartList2 = new SmartList();
                        }
                        smartList2.add(new Exception("Failed to call constructor: " + constructor2.toString(), e2));
                    }
                }
                if (smartList2 != null) {
                    if (smartList2.size() == 1) {
                        ExceptionUtil.rethrow((Throwable) smartList2.get(0));
                    } else {
                        ExceptionUtil.rethrow(new CompoundRuntimeException(smartList2));
                    }
                }
            }
        }
        return null;
    }

    public static <T> T createInstance(Constructor<T> constructor, Object... objArr) {
        if (constructor == null) {
            $$$reportNull$$$0(51);
        }
        if (objArr == null) {
            $$$reportNull$$$0(52);
        }
        try {
            T tNewInstance = constructor.newInstance(objArr);
            if (tNewInstance == null) {
                $$$reportNull$$$0(53);
            }
            return tNewInstance;
        } catch (Exception e) {
            rc6.a(e);
            return null;
        }
    }

    public static Field findAssignableField(Class<?> cls, final Class<?> cls2, final String str) throws NoSuchFieldException {
        if (cls == null) {
            $$$reportNull$$$0(5);
        }
        if (str == null) {
            $$$reportNull$$$0(6);
        }
        Field fieldFindFieldInHierarchy = findFieldInHierarchy(cls, new Predicate() { // from class: iac
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ReflectionUtil.a(str, cls2, (Field) obj);
            }
        });
        if (fieldFindFieldInHierarchy != null) {
            return fieldFindFieldInHierarchy;
        }
        throw new NoSuchFieldException("Class: " + cls + " fieldName: " + str + " fieldType: " + cls2);
    }

    public static Class<?> findCallerClass(int i) {
        return ReflectionUtilRt.findCallerClass(i + 1);
    }

    public static Field findFieldInHierarchy(Class<?> cls, Predicate<? super Field> predicate) {
        if (cls == null) {
            $$$reportNull$$$0(8);
        }
        if (predicate == null) {
            $$$reportNull$$$0(9);
        }
        for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
            for (Field field : superclass.getDeclaredFields()) {
                if (predicate.test(field)) {
                    field.setAccessible(true);
                    return field;
                }
            }
        }
        return processInterfaces(cls.getInterfaces(), new HashSet(), predicate);
    }

    public static Class<?> getCallerClass(int i) {
        Class<?> clsFindCallerClass = findCallerClass(i);
        int i2 = i + 1;
        while (clsFindCallerClass != null && clsFindCallerClass.getClassLoader() == null) {
            clsFindCallerClass = findCallerClass(i2);
            i2++;
        }
        return clsFindCallerClass == null ? findCallerClass(i - 1) : clsFindCallerClass;
    }

    @Deprecated
    public static Method getDeclaredMethod(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls == null) {
            $$$reportNull$$$0(24);
        }
        if (str == null) {
            $$$reportNull$$$0(25);
        }
        if (clsArr == null) {
            $$$reportNull$$$0(26);
        }
        try {
            return makeAccessible(cls.getDeclaredMethod(str, clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static <T> T getField(Class<?> cls, Object obj, Class<T> cls2, String str) {
        if (cls == null) {
            $$$reportNull$$$0(36);
        }
        if (str == null) {
            $$$reportNull$$$0(37);
        }
        try {
            return (T) getFieldValue(findAssignableField(cls, cls2, str), obj);
        } catch (NoSuchFieldException e) {
            LOG.debug(e);
            return null;
        }
    }

    public static <T> T getFieldValue(Field field, Object obj) {
        if (field == null) {
            $$$reportNull$$$0(40);
        }
        try {
            return (T) field.get(obj);
        } catch (IllegalAccessException e) {
            LOG.debug(e);
            return null;
        }
    }

    public static Class<?> getMethodDeclaringClass(Class<?> cls, String str, Class<?>... clsArr) {
        if (cls == null) {
            $$$reportNull$$$0(33);
        }
        if (str == null) {
            $$$reportNull$$$0(34);
        }
        if (clsArr == null) {
            $$$reportNull$$$0(35);
        }
        try {
            return cls.getMethod(str, clsArr).getDeclaringClass();
        } catch (NoSuchMethodException unused) {
            while (cls != null) {
                try {
                    return cls.getDeclaredMethod(str, clsArr).getDeclaringClass();
                } catch (NoSuchMethodException unused2) {
                    cls = cls.getSuperclass();
                }
            }
            return null;
        }
    }

    public static <T> T getStaticFieldValue(Class<?> cls, Class<T> cls2, String str) {
        if (cls == null) {
            $$$reportNull$$$0(38);
        }
        if (str == null) {
            $$$reportNull$$$0(39);
        }
        try {
            Field fieldFindAssignableField = findAssignableField(cls, cls2, str);
            if (!isInstanceField(fieldFindAssignableField)) {
                return (T) getFieldValue(fieldFindAssignableField, null);
            }
            throw new IllegalArgumentException("Field " + cls + "." + str + " is not static");
        } catch (NoSuchFieldException e) {
            LOG.debug(e);
            return null;
        }
    }

    @Deprecated
    public static Object getUnsafe() {
        Object obj = unsafe;
        if (obj == null) {
            $$$reportNull$$$0(75);
        }
        return obj;
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2) {
        if (cls == null) {
            $$$reportNull$$$0(76);
        }
        if (cls2 == null) {
            $$$reportNull$$$0(77);
        }
        return cls == cls2 || cls.isAssignableFrom(cls2);
    }

    public static boolean isInstanceField(Field field) {
        if (field == null) {
            $$$reportNull$$$0(41);
        }
        return !Modifier.isStatic(field.getModifiers());
    }

    private static Method makeAccessible(Method method) {
        method.setAccessible(true);
        return method;
    }

    public static <T> T newInstance(Class<T> cls, boolean z) {
        T t;
        if (cls == null) {
            $$$reportNull$$$0(47);
        }
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(null);
            try {
                declaredConstructor.setAccessible(true);
            } catch (SecurityException unused) {
            }
            T tNewInstance = declaredConstructor.newInstance(null);
            if (tNewInstance == null) {
                $$$reportNull$$$0(48);
            }
            return tNewInstance;
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                Throwable targetException = ((InvocationTargetException) e).getTargetException();
                if ((targetException instanceof ControlFlowException) && (targetException instanceof RuntimeException)) {
                    throw ((RuntimeException) targetException);
                }
            }
            if (z && (t = (T) createAsDataClass(cls)) != null) {
                return t;
            }
            ExceptionUtilRt.rethrowUnchecked(e);
            rc6.a(e);
            return null;
        }
    }

    private static Field processInterfaces(Class<?>[] clsArr, Set<? super Class<?>> set, Predicate<? super Field> predicate) {
        if (set == null) {
            $$$reportNull$$$0(10);
        }
        if (predicate == null) {
            $$$reportNull$$$0(11);
        }
        if (clsArr == null) {
            $$$reportNull$$$0(12);
        }
        for (Class<?> cls : clsArr) {
            if (set.add(cls)) {
                for (Field field : cls.getDeclaredFields()) {
                    if (predicate.test(field)) {
                        field.setAccessible(true);
                        return field;
                    }
                }
                Field fieldProcessInterfaces = processInterfaces(cls.getInterfaces(), set, predicate);
                if (fieldProcessInterfaces != null) {
                    return fieldProcessInterfaces;
                }
            }
        }
        return null;
    }

    public static <T> T proxy(Class<? extends T> cls, InvocationHandler invocationHandler) {
        if (cls == null) {
            $$$reportNull$$$0(79);
        }
        if (invocationHandler == null) {
            $$$reportNull$$$0(80);
        }
        T tCast = cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler));
        if (tCast == null) {
            $$$reportNull$$$0(81);
        }
        return tCast;
    }

    public static <T> T newInstance(Class<T> cls) {
        if (cls == null) {
            $$$reportNull$$$0(46);
        }
        return (T) newInstance(cls, true);
    }
}
