package com.intellij.serialization;

import androidx.collection.ScatterMapKt;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ClassUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 5 || i == 6 || i == 7 || i == 8 || i == 19) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 5 || i == 6 || i == 7 || i == 8 || i == 19) ? 2 : 3];
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 19:
                objArr[0] = "com/intellij/serialization/ClassUtil";
                break;
            case 4:
            default:
                objArr[0] = "type";
                break;
            case 9:
            case 15:
                objArr[0] = "aClass";
                break;
            case 10:
            case 11:
                objArr[0] = "object";
                break;
            case 12:
                objArr[0] = "value";
                break;
            case 13:
                objArr[0] = "valueClass";
                break;
            case 14:
            case 16:
                objArr[0] = "variable";
                break;
            case 17:
                objArr[0] = "classType";
                break;
            case 18:
                objArr[0] = "anInterface";
                break;
        }
        if (i == 1 || i == 2 || i == 3) {
            objArr[1] = "getRawType";
        } else if (i == 5 || i == 6 || i == 7 || i == 8) {
            objArr[1] = "typeToClass";
        } else if (i != 19) {
            objArr[1] = "com/intellij/serialization/ClassUtil";
        } else {
            objArr[1] = "declarationToString";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 19:
                break;
            case 4:
                objArr[2] = "typeToClass";
                break;
            case 9:
                objArr[2] = "isPrimitive";
                break;
            case 10:
                objArr[2] = "isMutableCollection";
                break;
            case 11:
                objArr[2] = "isMutableMap";
                break;
            case 12:
            case 13:
                objArr[2] = "stringToEnum";
                break;
            case 14:
            case 15:
                objArr[2] = "resolveVariableInHierarchy";
                break;
            case 16:
            case 17:
                objArr[2] = "resolveVariable";
                break;
            case 18:
                objArr[2] = "declarationToString";
                break;
            default:
                objArr[2] = "getRawType";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 5 && i != 6 && i != 7 && i != 8 && i != 19) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private ClassUtil() {
    }

    public static boolean isMutableCollection(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(10);
        }
        if (obj != Collections.EMPTY_LIST && obj != Collections.EMPTY_SET && (obj instanceof Collection)) {
            Class<?> cls = obj.getClass();
            String simpleName = cls.getSimpleName();
            if (!simpleName.equals("EmptyList") && !simpleName.startsWith("Unmodifiable") && !simpleName.equals("EmptySet")) {
                String name = cls.getName();
                if (!name.equals("java.util.Arrays$ArrayList") && !name.equals("java.util.Collections$SingletonList") && !name.equals("com.intellij.util.containers.FreezableArrayList") && !name.equals("java.util.Collections$SingletonSet")) {
                    Class<?> declaringClass = cls.getDeclaringClass();
                    return declaringClass == null || !"ImmutableCollections".equals(declaringClass.getSimpleName());
                }
            }
        }
        return false;
    }

    public static boolean isMutableMap(Map<?, ?> map) {
        if (map == null) {
            $$$reportNull$$$0(11);
        }
        if (map == Collections.EMPTY_MAP) {
            return false;
        }
        String simpleName = map.getClass().getSimpleName();
        return (simpleName.equals("EmptyMap") || simpleName.equals("UnmodifiableMap") || simpleName.equals("ImmutableMap") || simpleName.equals("SingletonMap")) ? false : true;
    }

    public static boolean isPrimitive(Class<?> cls) {
        if (cls == null) {
            $$$reportNull$$$0(9);
        }
        return cls.isPrimitive() || cls == String.class || cls == Integer.class || cls == Long.class || cls == Boolean.class || cls == Double.class || cls == Float.class || cls.isEnum() || Date.class.isAssignableFrom(cls);
    }

    public static Object stringToEnum(String str, Class<? extends Enum<?>> cls, boolean z) {
        if (str == null) {
            $$$reportNull$$$0(12);
        }
        if (cls == null) {
            $$$reportNull$$$0(13);
        }
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        if (!z) {
            for (Enum r2 : enumArr) {
                if (r2.toString().equals(str)) {
                    return r2;
                }
            }
        }
        for (Enum r1 : enumArr) {
            if (r1.toString().equalsIgnoreCase(str)) {
                return r1;
            }
        }
        return null;
    }

    public static Class<?> typeToClass(Type type) {
        if (type == null) {
            $$$reportNull$$$0(4);
        }
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof TypeVariable) {
            Type type2 = ((TypeVariable) type).getBounds()[0];
            Class<?> cls = type2 instanceof Class ? (Class) type2 : (Class) ((ParameterizedType) type2).getRawType();
            if (cls == null) {
                $$$reportNull$$$0(6);
            }
            return cls;
        }
        if (type instanceof WildcardType) {
            Class<?> cls2 = (Class) ((WildcardType) type).getUpperBounds()[0];
            if (cls2 == null) {
                $$$reportNull$$$0(7);
            }
            return cls2;
        }
        Class<?> cls3 = (Class) ((ParameterizedType) type).getRawType();
        if (cls3 == null) {
            $$$reportNull$$$0(8);
        }
        return cls3;
    }
}
