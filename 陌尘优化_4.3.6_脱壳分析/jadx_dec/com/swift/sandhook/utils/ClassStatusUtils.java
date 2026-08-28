package com.swift.sandhook.utils;

import com.swift.sandhook.SandHookConfig;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class ClassStatusUtils {
    static Field fieldStatusOfClass;

    static {
        try {
            Field declaredField = Class.class.getDeclaredField("status");
            fieldStatusOfClass = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException unused) {
        }
    }

    public static int getClassStatus(Class cls, boolean z) {
        int i = 0;
        if (cls == null) {
            return 0;
        }
        try {
            i = fieldStatusOfClass.getInt(cls);
        } catch (Throwable unused) {
        }
        return z ? (int) (toUnsignedLong(i) >> 28) : i;
    }

    public static boolean isInitialized(Class cls) {
        if (fieldStatusOfClass == null) {
            return true;
        }
        if (SandHookConfig.SDK_INT >= 28) {
            return getClassStatus(cls, true) == 14;
        }
        int i = SandHookConfig.SDK_INT;
        int classStatus = getClassStatus(cls, false);
        return i == 27 ? classStatus == 11 : classStatus == 10;
    }

    public static boolean isStaticAndNoInited(Member member) {
        if (member == null || (member instanceof Constructor)) {
            return false;
        }
        return Modifier.isStatic(member.getModifiers()) && !isInitialized(member.getDeclaringClass());
    }

    public static long toUnsignedLong(int i) {
        return i & 4294967295L;
    }
}
