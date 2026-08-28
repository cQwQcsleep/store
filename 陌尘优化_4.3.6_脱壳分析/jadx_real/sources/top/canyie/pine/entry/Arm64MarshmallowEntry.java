package top.canyie.pine.entry;

import java.lang.reflect.Method;

/* loaded from: /workspace/unpacked/classes.dex */
public class Arm64MarshmallowEntry {
    static boolean booleanBridge(long j, long j2) {
        return booleanBridge(j, j2, 0L);
    }

    static boolean booleanBridge(long j, long j2, long j3) {
        return booleanBridge(j, j2, j3, 0L);
    }

    static boolean booleanBridge(long j, long j2, long j3, long j4) {
        return booleanBridge(j, j2, j3, j4, 0L);
    }

    static boolean booleanBridge(long j, long j2, long j3, long j4, long j5) {
        return booleanBridge(j, j2, j3, j4, j5, 0L);
    }

    static boolean booleanBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return booleanBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static boolean booleanBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.booleanBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static byte byteBridge(long j, long j2) {
        return byteBridge(j, j2, 0L);
    }

    static byte byteBridge(long j, long j2, long j3) {
        return byteBridge(j, j2, j3, 0L);
    }

    static byte byteBridge(long j, long j2, long j3, long j4) {
        return byteBridge(j, j2, j3, j4, 0L);
    }

    static byte byteBridge(long j, long j2, long j3, long j4, long j5) {
        return byteBridge(j, j2, j3, j4, j5, 0L);
    }

    static byte byteBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return byteBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static byte byteBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.byteBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static char charBridge(long j, long j2) {
        return charBridge(j, j2, 0L);
    }

    static char charBridge(long j, long j2, long j3) {
        return charBridge(j, j2, j3, 0L);
    }

    static char charBridge(long j, long j2, long j3, long j4) {
        return charBridge(j, j2, j3, j4, 0L);
    }

    static char charBridge(long j, long j2, long j3, long j4, long j5) {
        return charBridge(j, j2, j3, j4, j5, 0L);
    }

    static char charBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return charBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static char charBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.charBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static double doubleBridge(long j, long j2) {
        return doubleBridge(j, j2, 0L);
    }

    static double doubleBridge(long j, long j2, long j3) {
        return doubleBridge(j, j2, j3, 0L);
    }

    static double doubleBridge(long j, long j2, long j3, long j4) {
        return doubleBridge(j, j2, j3, j4, 0L);
    }

    static double doubleBridge(long j, long j2, long j3, long j4, long j5) {
        return doubleBridge(j, j2, j3, j4, j5, 0L);
    }

    static double doubleBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return doubleBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static double doubleBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.doubleBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static float floatBridge(long j, long j2) {
        return floatBridge(j, j2, 0L);
    }

    static float floatBridge(long j, long j2, long j3) {
        return floatBridge(j, j2, j3, 0L);
    }

    static float floatBridge(long j, long j2, long j3, long j4) {
        return floatBridge(j, j2, j3, j4, 0L);
    }

    static float floatBridge(long j, long j2, long j3, long j4, long j5) {
        return floatBridge(j, j2, j3, j4, j5, 0L);
    }

    static float floatBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return floatBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static float floatBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.floatBridge(j, j2, j3, j4, j5, j6, j7);
    }

    public static Method getBridge(String str, int i) throws NoSuchMethodException, SecurityException {
        if (i < 2) {
            i = 2;
        }
        Class[] clsArr = new Class[i];
        for (int i2 = 0; i2 < i; i2++) {
            clsArr[i2] = Long.TYPE;
        }
        try {
            Method declaredMethod = Arm64MarshmallowEntry.class.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static int intBridge(long j, long j2) {
        return intBridge(j, j2, 0L);
    }

    static int intBridge(long j, long j2, long j3) {
        return intBridge(j, j2, j3, 0L);
    }

    static int intBridge(long j, long j2, long j3, long j4) {
        return intBridge(j, j2, j3, j4, 0L);
    }

    static int intBridge(long j, long j2, long j3, long j4, long j5) {
        return intBridge(j, j2, j3, j4, j5, 0L);
    }

    static int intBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return intBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static int intBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.intBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static long longBridge(long j, long j2) {
        return longBridge(j, j2, 0L);
    }

    static long longBridge(long j, long j2, long j3) {
        return longBridge(j, j2, j3, 0L);
    }

    static long longBridge(long j, long j2, long j3, long j4) {
        return longBridge(j, j2, j3, j4, 0L);
    }

    static long longBridge(long j, long j2, long j3, long j4, long j5) {
        return longBridge(j, j2, j3, j4, j5, 0L);
    }

    static long longBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return longBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static long longBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.longBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static Object objectBridge(long j, long j2) {
        return objectBridge(j, j2, 0L);
    }

    static Object objectBridge(long j, long j2, long j3) {
        return objectBridge(j, j2, j3, 0L);
    }

    static Object objectBridge(long j, long j2, long j3, long j4) {
        return objectBridge(j, j2, j3, j4, 0L);
    }

    static Object objectBridge(long j, long j2, long j3, long j4, long j5) {
        return objectBridge(j, j2, j3, j4, j5, 0L);
    }

    static Object objectBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return objectBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static Object objectBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.objectBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static short shortBridge(long j, long j2) {
        return shortBridge(j, j2, 0L);
    }

    static short shortBridge(long j, long j2, long j3) {
        return shortBridge(j, j2, j3, 0L);
    }

    static short shortBridge(long j, long j2, long j3, long j4) {
        return shortBridge(j, j2, j3, j4, 0L);
    }

    static short shortBridge(long j, long j2, long j3, long j4, long j5) {
        return shortBridge(j, j2, j3, j4, j5, 0L);
    }

    static short shortBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        return shortBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static short shortBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Arm64Entry.shortBridge(j, j2, j3, j4, j5, j6, j7);
    }

    static void voidBridge(long j, long j2) {
        voidBridge(j, j2, 0L);
    }

    static void voidBridge(long j, long j2, long j3) {
        voidBridge(j, j2, j3, 0L);
    }

    static void voidBridge(long j, long j2, long j3, long j4) {
        voidBridge(j, j2, j3, j4, 0L);
    }

    static void voidBridge(long j, long j2, long j3, long j4, long j5) {
        voidBridge(j, j2, j3, j4, j5, 0L);
    }

    static void voidBridge(long j, long j2, long j3, long j4, long j5, long j6) {
        voidBridge(j, j2, j3, j4, j5, j6, 0L);
    }

    static void voidBridge(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        Arm64Entry.voidBridge(j, j2, j3, j4, j5, j6, j7);
    }
}
