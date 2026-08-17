package it.unimi.dsi.fastutil;

import defpackage.zqc;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class SafeMath {
    public static float safeDoubleToFloat(double d) {
        if (Double.isNaN(d)) {
            return Float.NaN;
        }
        if (Double.isInfinite(d)) {
            return d < 0.0d ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        }
        if (d < -3.4028234663852886E38d || 3.4028234663852886E38d < d) {
            o06.a(d, " can't be represented as float (out of range)");
            return 0.0f;
        }
        float f = (float) d;
        if (f == d) {
            return f;
        }
        o06.a(d, " can't be represented as float (imprecise)");
        return 0.0f;
    }

    public static byte safeIntToByte(int i) {
        if (i >= -128 && 127 >= i) {
            return (byte) i;
        }
        zqc.a(i, " can't be represented as byte (out of range)");
        return (byte) 0;
    }

    public static char safeIntToChar(int i) {
        if (i >= 0 && 65535 >= i) {
            return (char) i;
        }
        zqc.a(i, " can't be represented as char");
        return (char) 0;
    }

    public static short safeIntToShort(int i) {
        if (i >= -32768 && 32767 >= i) {
            return (short) i;
        }
        zqc.a(i, " can't be represented as short (out of range)");
        return (short) 0;
    }
}
