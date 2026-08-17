package io.github.rosemoe.sora.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class IntPair {
    public static int getFirst(long j) {
        return (int) (j >> 32);
    }

    public static int getSecond(long j) {
        return (int) (j & 4294967295L);
    }

    public static float getSecondAsFloat(long j) {
        return Float.intBitsToFloat(getSecond(j));
    }

    public static long pack(int i, int i2) {
        return toUnsignedLong(i2) | (toUnsignedLong(i) << 32);
    }

    public static long packIntFloat(int i, float f) {
        return pack(i, Float.floatToRawIntBits(f));
    }

    private static long toUnsignedLong(int i) {
        return ((long) i) & 4294967295L;
    }
}
