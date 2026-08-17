package com.reandroid.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ObjectsUtil {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj) throws ClassCastException {
        return obj;
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static boolean equalsArray(Object[] objArr, Object[] objArr2) {
        int length;
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || (length = objArr.length) != objArr2.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equals(objArr[i], objArr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static <T> T getNull() throws ClassCastException {
        return null;
    }

    public static int hash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return ((((((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3)) * 31) + hash(obj4)) * 31) + hash(obj5);
    }

    public static int hashElements(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int length = 961 + objArr.length;
        for (Object obj : objArr) {
            length = (length * 31) + hash(obj);
        }
        return length;
    }

    public static byte of(byte b) {
        return b;
    }

    public static double of(double d) {
        return d;
    }

    public static float of(float f) {
        return f;
    }

    public static int of(int i) {
        return i;
    }

    public static long of(long j) {
        return j;
    }

    public static <T> T of(T t) {
        return t;
    }

    public static String of(String str) {
        return str;
    }

    public static short of(short s) {
        return s;
    }

    public static int hash(Object obj, Object obj2) {
        return ((hash(obj) + 31) * 31) + hash(obj2);
    }

    public static int hash(Object obj, Object obj2, Object obj3) {
        return ((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3);
    }

    public static int hash(Object obj, Object obj2, Object obj3, Object obj4) {
        return ((((((hash(obj) + 31) * 31) + hash(obj2)) * 31) + hash(obj3)) * 31) + hash(obj4);
    }

    public static int hash(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
