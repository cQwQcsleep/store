package it.unimi.dsi.fastutil;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class HashCommon {
    public static int arraySize(int i, float f) {
        long jMax = Math.max(2L, nextPowerOfTwo((long) Math.ceil(i / f)));
        if (jMax <= 1073741824) {
            return (int) jMax;
        }
        u56.a(i, f);
        return 0;
    }

    public static int double2int(double d) {
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d);
        return (int) (jDoubleToRawLongBits ^ (jDoubleToRawLongBits >>> 32));
    }

    public static int float2int(float f) {
        return Float.floatToRawIntBits(f);
    }

    public static int long2int(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int maxFill(int i, float f) {
        return Math.min((int) Math.ceil(i * f), i - 1);
    }

    public static long mix(long j) {
        long j2 = j * (-7046029254386353131L);
        long j3 = j2 ^ (j2 >>> 32);
        return j3 ^ (j3 >>> 16);
    }

    public static long nextPowerOfTwo(long j) {
        return 1 << (64 - Long.numberOfLeadingZeros(j - 1));
    }

    public static int nextPowerOfTwo(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }

    public static int mix(int i) {
        int i2 = i * (-1640531527);
        return i2 ^ (i2 >>> 16);
    }
}
