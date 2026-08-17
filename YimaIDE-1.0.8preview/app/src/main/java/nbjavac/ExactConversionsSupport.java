package nbjavac;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ExactConversionsSupport {
    private ExactConversionsSupport() {
    }

    public static boolean isDoubleToByteExact(double d) {
        return d == ((double) ((byte) ((int) d))) && !isNegativeZero(d);
    }

    public static boolean isDoubleToCharExact(double d) {
        return d == ((double) ((char) ((int) d))) && !isNegativeZero(d);
    }

    public static boolean isDoubleToFloatExact(double d) {
        return d == ((double) ((float) d)) || d != d;
    }

    public static boolean isDoubleToIntExact(double d) {
        return d == ((double) ((int) d)) && !isNegativeZero(d);
    }

    public static boolean isDoubleToLongExact(double d) {
        return (d != ((double) ((long) d)) || d == 9.223372036854776E18d || isNegativeZero(d)) ? false : true;
    }

    public static boolean isDoubleToShortExact(double d) {
        return d == ((double) ((short) ((int) d))) && !isNegativeZero(d);
    }

    public static boolean isFloatToByteExact(float f) {
        return f == ((float) ((byte) ((int) f))) && !isNegativeZero(f);
    }

    public static boolean isFloatToCharExact(float f) {
        return f == ((float) ((char) ((int) f))) && !isNegativeZero(f);
    }

    public static boolean isFloatToIntExact(float f) {
        return (f != ((float) ((int) f)) || f == 2.1474836E9f || isNegativeZero(f)) ? false : true;
    }

    public static boolean isFloatToLongExact(float f) {
        return (f != ((float) ((long) f)) || f == 9.223372E18f || isNegativeZero(f)) ? false : true;
    }

    public static boolean isFloatToShortExact(float f) {
        return f == ((float) ((short) ((int) f))) && !isNegativeZero(f);
    }

    public static boolean isIntToByteExact(int i) {
        return i == ((byte) i);
    }

    public static boolean isIntToCharExact(int i) {
        return i == ((char) i);
    }

    public static boolean isIntToFloatExact(int i) {
        return i == ((int) ((float) i)) && i != Integer.MAX_VALUE;
    }

    public static boolean isIntToShortExact(int i) {
        return i == ((short) i);
    }

    public static boolean isLongToByteExact(long j) {
        return j == ((long) ((byte) ((int) j)));
    }

    public static boolean isLongToCharExact(long j) {
        return j == ((long) ((char) ((int) j)));
    }

    public static boolean isLongToDoubleExact(long j) {
        return j == ((long) ((double) j)) && j != Long.MAX_VALUE;
    }

    public static boolean isLongToFloatExact(long j) {
        return j == ((long) ((float) j)) && j != Long.MAX_VALUE;
    }

    public static boolean isLongToIntExact(long j) {
        return j == ((long) ((int) j));
    }

    public static boolean isLongToShortExact(long j) {
        return j == ((long) ((short) ((int) j)));
    }

    private static boolean isNegativeZero(double d) {
        return Double.doubleToRawLongBits(d) == Long.MIN_VALUE;
    }

    private static boolean isNegativeZero(float f) {
        return Float.floatToRawIntBits(f) == Integer.MIN_VALUE;
    }
}
