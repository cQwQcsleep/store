package androidx.core.math;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class MathUtils {
    private MathUtils() {
    }

    public static long addExact(long j, long j2) {
        long j3 = j + j2;
        if ((j >= 0) == (j2 >= 0)) {
            if ((j >= 0) != (j3 >= 0)) {
                pv9.a("integer overflow");
                return 0L;
            }
        }
        return j3;
    }

    public static double clamp(double d, double d2, double d3) {
        if (d < d2) {
            return d2;
        }
        return d > d3 ? d3 : d;
    }

    public static long decrementExact(long j) {
        if (j != Long.MIN_VALUE) {
            return j - 1;
        }
        pv9.a("integer overflow");
        return 0L;
    }

    public static long incrementExact(long j) {
        if (j != Long.MAX_VALUE) {
            return j + 1;
        }
        pv9.a("integer overflow");
        return 0L;
    }

    public static long multiplyExact(long j, long j2) {
        long j3 = j * j2;
        if (j == 0 || j2 == 0 || (j3 / j == j2 && j3 / j2 == j)) {
            return j3;
        }
        pv9.a("integer overflow");
        return 0L;
    }

    public static long negateExact(long j) {
        if (j != Long.MIN_VALUE) {
            return -j;
        }
        pv9.a("integer overflow");
        return 0L;
    }

    public static long subtractExact(long j, long j2) {
        long j3 = j - j2;
        if ((j < 0) != (j2 < 0)) {
            if ((j < 0) != (j3 < 0)) {
                pv9.a("integer overflow");
                return 0L;
            }
        }
        return j3;
    }

    public static int toIntExact(long j) {
        if (j <= 2147483647L && j >= -2147483648L) {
            return (int) j;
        }
        pv9.a("integer overflow");
        return 0;
    }

    public static float clamp(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        return f > f3 ? f3 : f;
    }

    public static int clamp(int i, int i2, int i3) {
        if (i < i2) {
            return i2;
        }
        return i > i3 ? i3 : i;
    }

    public static long clamp(long j, long j2, long j3) {
        if (j < j2) {
            return j2;
        }
        return j > j3 ? j3 : j;
    }

    public static int negateExact(int i) {
        if (i != Integer.MIN_VALUE) {
            return -i;
        }
        pv9.a("integer overflow");
        return 0;
    }

    public static int decrementExact(int i) {
        if (i != Integer.MIN_VALUE) {
            return i - 1;
        }
        pv9.a("integer overflow");
        return 0;
    }

    public static int incrementExact(int i) {
        if (i != Integer.MAX_VALUE) {
            return i + 1;
        }
        pv9.a("integer overflow");
        return 0;
    }

    public static int multiplyExact(int i, int i2) {
        int i3 = i * i2;
        if (i == 0 || i2 == 0 || (i3 / i == i2 && i3 / i2 == i)) {
            return i3;
        }
        pv9.a("integer overflow");
        return 0;
    }

    public static int addExact(int i, int i2) {
        int i3 = i + i2;
        if ((i >= 0) == (i2 >= 0)) {
            if ((i >= 0) != (i3 >= 0)) {
                pv9.a("integer overflow");
                return 0;
            }
        }
        return i3;
    }

    public static int subtractExact(int i, int i2) {
        int i3 = i - i2;
        if ((i < 0) != (i2 < 0)) {
            if ((i < 0) != (i3 < 0)) {
                pv9.a("integer overflow");
                return 0;
            }
        }
        return i3;
    }
}
