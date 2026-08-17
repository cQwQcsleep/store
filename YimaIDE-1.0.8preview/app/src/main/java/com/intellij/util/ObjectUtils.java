package com.intellij.util;

import androidx.collection.ScatterMapKt;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ObjectUtils {
    public static final Object NULL = sentinel("ObjectUtils.NULL");

    public static final class Sentinel {
        private final String myName;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/intellij/util/ObjectUtils$Sentinel", "<init>"));
        }

        public Sentinel(String str) {
            if (str == null) {
                $$$reportNull$$$0(0);
            }
            this.myName = str;
        }

        public String toString() {
            return this.myName;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 4 || i == 8 || i == 10) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 4 || i == 8 || i == 10) ? 2 : 3];
        switch (i) {
            case 2:
                objArr[0] = "ofInterface";
                break;
            case 3:
            case 4:
            case 8:
            case 10:
                objArr[0] = "com/intellij/util/ObjectUtils";
                break;
            case 5:
                objArr[0] = "array";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "o";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 19:
                objArr[0] = "defaultValue";
                break;
            case 11:
            case 12:
            case 16:
                objArr[0] = "clazz";
                break;
            case 13:
                objArr[0] = "convertor";
                break;
            case 14:
                objArr[0] = "function";
                break;
            case 15:
            case 17:
                objArr[0] = "consumer";
                break;
            case 18:
                objArr[0] = "condition";
                break;
            case 20:
                objArr[0] = "indexComparator";
                break;
            default:
                objArr[0] = "name";
                break;
        }
        if (i == 3) {
            objArr[1] = "sentinel";
        } else if (i == 4) {
            objArr[1] = "assertNotNull";
        } else if (i == 8 || i == 10) {
            objArr[1] = "notNull";
        } else {
            objArr[1] = "com/intellij/util/ObjectUtils";
        }
        switch (i) {
            case 3:
            case 4:
            case 8:
            case 10:
                break;
            case 5:
                objArr[2] = "assertAllElementsNotNull";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "coalesce";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
                objArr[2] = "notNull";
                break;
            case 11:
                objArr[2] = "tryCast";
                break;
            case 12:
            case 13:
                objArr[2] = "doIfCast";
                break;
            case 14:
                objArr[2] = "doIfNotNull";
                break;
            case 15:
                objArr[2] = "consumeIfNotNull";
                break;
            case 16:
            case 17:
                objArr[2] = "consumeIfCast";
                break;
            case 18:
                objArr[2] = "nullizeByCondition";
                break;
            case 19:
                objArr[2] = "nullizeIfDefaultValue";
                break;
            case 20:
                objArr[2] = "binarySearch";
                break;
            default:
                objArr[2] = "sentinel";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 4 && i != 8 && i != 10) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static int binarySearch(int i, int i2, IntUnaryOperator intUnaryOperator) {
        if (intUnaryOperator == null) {
            $$$reportNull$$$0(20);
        }
        int i3 = i2 - 1;
        while (i <= i3) {
            int i4 = (i + i3) >>> 1;
            int iApplyAsInt = intUnaryOperator.applyAsInt(i4);
            if (iApplyAsInt < 0) {
                i = i4 + 1;
            } else {
                if (iApplyAsInt <= 0) {
                    return i4;
                }
                i3 = i4 - 1;
            }
        }
        return -(i + 1);
    }

    public static <T> T chooseNotNull(T t, T t2) {
        return t == null ? t2 : t;
    }

    public static <T> T coalesce(T t, T t2) {
        return (T) chooseNotNull(t, t2);
    }

    public static <T> T notNull(T t, T t2) {
        if (t2 == null) {
            $$$reportNull$$$0(7);
        }
        if (t == null) {
            t = t2;
        }
        if (t == null) {
            $$$reportNull$$$0(8);
        }
        return t;
    }

    public static String objectInfo(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj + " (" + obj.getClass().getName() + ")";
    }

    public static Object sentinel(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        return new Sentinel(str);
    }

    public static <T> T tryCast(Object obj, Class<T> cls) {
        if (cls == null) {
            $$$reportNull$$$0(11);
        }
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }
}
