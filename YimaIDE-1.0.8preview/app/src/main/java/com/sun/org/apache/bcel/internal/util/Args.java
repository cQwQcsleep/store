package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Args {
    public static int require(int i, int i2, String str) {
        if (i == i2) {
            return i;
        }
        throw new ClassFormatException(String.format("%s [Value must be 0: %,d]", str, Integer.valueOf(i)));
    }

    public static int require0(int i, String str) {
        return require(i, 0, str);
    }

    public static int requireU1(int i, String str) {
        if (i < 0 || i > 255) {
            throw new ClassFormatException(String.format("%s [Value out of range (0 - %,d) for type u1: %,d]", str, 255, Integer.valueOf(i)));
        }
        return i;
    }

    public static int requireU2(int i, int i2, int i3, String str) {
        if (i3 > 65535) {
            drd.a("%s programming error: max %,d > %,d", new Object[]{str, Integer.valueOf(i3), 65535});
            return 0;
        }
        if (i2 < 0) {
            drd.a("%s programming error: min %,d < 0", new Object[]{str, Integer.valueOf(i2)});
            return 0;
        }
        if (i < i2 || i > i3) {
            throw new ClassFormatException(String.format("%s [Value out of range (%,d - %,d) for type u2: %,d]", str, Integer.valueOf(i2), 65535, Integer.valueOf(i)));
        }
        return i;
    }

    public static int requireU4(int i, int i2, String str) {
        if (i2 < 0) {
            drd.a("%s programming error: min %,d < 0", new Object[]{str, Integer.valueOf(i2)});
            return 0;
        }
        if (i >= i2) {
            return i;
        }
        throw new ClassFormatException(String.format("%s [Value out of range (%,d - %,d) for type u2: %,d]", str, Integer.valueOf(i2), Integer.MAX_VALUE, Long.valueOf(((long) i) & 4294967295L)));
    }

    public static int requireU4(int i, String str) {
        return requireU4(i, 0, str);
    }

    public static int requireU2(int i, int i2, String str) {
        return requireU2(i, i2, 65535, str);
    }

    public static int requireU2(int i, String str) {
        return requireU2(i, 0, str);
    }
}
