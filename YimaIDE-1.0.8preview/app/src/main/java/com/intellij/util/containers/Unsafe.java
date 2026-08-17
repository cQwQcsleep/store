package com.intellij.util.containers;

import com.intellij.util.ReflectionUtil;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import org.lsposed.hiddenapibypass.HiddenApiBypass;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class Unsafe {
    private static final MethodHandle arrayBaseOffset;
    private static final MethodHandle arrayIndexScale;
    private static final MethodHandle compareAndSwapInt;
    private static final MethodHandle compareAndSwapLong;
    private static final MethodHandle compareAndSwapObject;
    private static final MethodHandle getObjectVolatile;
    private static final MethodHandle objectFieldOffset;
    private static final MethodHandle putObjectVolatile;

    static {
        HiddenApiBypass.addHiddenApiExemptions(new String[0]);
        try {
            Class cls = Void.TYPE;
            Class cls2 = Long.TYPE;
            putObjectVolatile = find("putObjectVolatile", cls, Object.class, cls2, Object.class);
            getObjectVolatile = find("getObjectVolatile", Object.class, Object.class, cls2);
            Class cls3 = Boolean.TYPE;
            compareAndSwapObject = find("compareAndSwapObject", cls3, Object.class, cls2, Object.class, Object.class);
            Class cls4 = Integer.TYPE;
            compareAndSwapInt = find("compareAndSwapInt", cls3, Object.class, cls2, cls4, cls4);
            compareAndSwapLong = find("compareAndSwapLong", cls3, Object.class, cls2, cls2, cls2);
            objectFieldOffset = find("objectFieldOffset", cls2, Field.class);
            arrayBaseOffset = find("arrayBaseOffset", cls4, Class.class);
            arrayIndexScale = find("arrayIndexScale", cls4, Class.class);
        } catch (Throwable th) {
            throw new Error(th);
        }
    }

    public static int arrayBaseOffset(Class<?> cls) {
        try {
            return (int) arrayBaseOffset.invokeExact(cls);
        } catch (Throwable th) {
            rc6.a(th);
            return 0;
        }
    }

    public static int arrayIndexScale(Class<?> cls) {
        try {
            return (int) arrayIndexScale.invokeExact(cls);
        } catch (Throwable th) {
            rc6.a(th);
            return 0;
        }
    }

    public static boolean compareAndSwapInt(Object obj, long j, int i, int i2) {
        try {
            return (boolean) compareAndSwapInt.invokeExact(obj, j, i, i2);
        } catch (Throwable th) {
            rc6.a(th);
            return false;
        }
    }

    public static boolean compareAndSwapLong(Object obj, long j, long j2, long j3) {
        try {
            return (boolean) compareAndSwapLong.invokeExact(obj, j, j2, j3);
        } catch (Throwable th) {
            rc6.a(th);
            return false;
        }
    }

    public static boolean compareAndSwapObject(Object obj, long j, Object obj2, Object obj3) {
        try {
            return (boolean) compareAndSwapObject.invokeExact(obj, j, obj2, obj3);
        } catch (Throwable th) {
            rc6.a(th);
            return false;
        }
    }

    public static void copyMemory(Object obj, long j, Object obj2, long j2, long j3) {
        try {
            HiddenApiBypass.invoke(Unsafe.class, ReflectionUtil.getUnsafe(), "copyMemory", new Object[]{obj, Long.valueOf(j), obj2, Long.valueOf(j2), Long.valueOf(j3)});
        } catch (Throwable th) {
            rc6.a(th);
        }
    }

    private static MethodHandle find(String str, Class<?> cls, Class<?>... clsArr) throws Exception {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Object unsafe = ReflectionUtil.getUnsafe();
        return lookup.findVirtual(unsafe.getClass(), str, MethodType.methodType(cls, clsArr)).bindTo(unsafe);
    }

    public static int getAndAddInt(Object obj, long j, int i) {
        try {
            return ((Integer) HiddenApiBypass.invoke(Unsafe.class, ReflectionUtil.getUnsafe(), "getAndAddInt", new Object[]{obj, Long.valueOf(j), Integer.valueOf(i)})).intValue();
        } catch (Throwable th) {
            rc6.a(th);
            return 0;
        }
    }

    public static Object getObjectVolatile(Object obj, long j) {
        try {
            return (Object) getObjectVolatile.invokeExact(obj, j);
        } catch (Throwable th) {
            rc6.a(th);
            return null;
        }
    }

    public static long objectFieldOffset(Field field) {
        try {
            return (long) objectFieldOffset.invokeExact(field);
        } catch (Throwable th) {
            rc6.a(th);
            return 0L;
        }
    }

    public static void putObjectVolatile(Object obj, long j, Object obj2) {
        try {
            (void) putObjectVolatile.invokeExact(obj, j, obj2);
        } catch (Throwable th) {
            rc6.a(th);
        }
    }
}
