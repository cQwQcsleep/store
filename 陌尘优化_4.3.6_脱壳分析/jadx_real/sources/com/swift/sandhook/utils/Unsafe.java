package com.swift.sandhook.utils;

import android.util.Log;
import com.swift.sandhook.HookLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public final class Unsafe {
    private static final String TAG = "Unsafe";
    private static Method arrayBaseOffsetMethod;
    private static Method arrayIndexScaleMethod;
    private static Method getIntMethod;
    private static Method getLongMethod;
    private static Class objectArrayClass = Object[].class;
    private static volatile boolean supported;
    private static Object unsafe;
    private static Class unsafeClass;

    static {
        try {
            try {
                Class<?> cls = Class.forName(NPStringFog.decode("1D05034F030814065C3B1E1E000804"));
                unsafeClass = cls;
                Field declaredField = cls.getDeclaredField(NPStringFog.decode("1A1808340012060317"));
                declaredField.setAccessible(true);
                unsafe = declaredField.get(null);
            } catch (Exception unused) {
                Log.w(NPStringFog.decode("3B1E1E000804"), "Unsafe not found o.O");
            }
        } catch (Exception unused2) {
            Field declaredField2 = unsafeClass.getDeclaredField(NPStringFog.decode("3A38283E212F22"));
            declaredField2.setAccessible(true);
            unsafe = declaredField2.get(null);
        }
        if (unsafe != null) {
            try {
                arrayBaseOffsetMethod = unsafeClass.getDeclaredMethod(NPStringFog.decode("0F021F00172306161721160B120B15"), Class.class);
                arrayIndexScaleMethod = unsafeClass.getDeclaredMethod(NPStringFog.decode("0F021F00172809011716230E000204"), Class.class);
                getIntMethod = unsafeClass.getDeclaredMethod(NPStringFog.decode("091519280015"), Object.class, Long.TYPE);
                getLongMethod = unsafeClass.getDeclaredMethod(NPStringFog.decode("0915192D010F00"), Object.class, Long.TYPE);
                supported = true;
            } catch (Exception unused3) {
            }
        }
    }

    private Unsafe() {
    }

    public static int arrayBaseOffset(Class cls) {
        try {
            return ((Integer) arrayBaseOffsetMethod.invoke(unsafe, cls)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int arrayIndexScale(Class cls) {
        try {
            return ((Integer) arrayIndexScaleMethod.invoke(unsafe, cls)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int getInt(Object obj, long j) {
        try {
            return ((Integer) getIntMethod.invoke(unsafe, obj, Long.valueOf(j))).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static long getLong(Object obj, long j) {
        try {
            return ((Long) getLongMethod.invoke(unsafe, obj, Long.valueOf(j))).longValue();
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static long getObjectAddress(Object obj) {
        try {
            Object[] objArr = new Object[1];
            objArr[0] = obj;
            return arrayIndexScale(objectArrayClass) == 8 ? getLong(objArr, arrayBaseOffset(objectArrayClass)) : getInt(objArr, arrayBaseOffset(objectArrayClass)) & 4294967295L;
        } catch (Exception e) {
            HookLog.e(NPStringFog.decode("0915194101030D00111A500C050A130216014E151F130113"), e);
            return -1L;
        }
    }

    public static boolean support() {
        return supported;
    }
}
