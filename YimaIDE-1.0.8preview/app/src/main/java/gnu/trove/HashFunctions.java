package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class HashFunctions {
    public static int hash(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public static int hash(long j) {
        return (int) (j ^ (j >> 32));
    }

    public static int hash(int i) {
        return i;
    }

    public static int hash(float f) {
        return Float.floatToIntBits(f * 6.6360896E8f);
    }

    public static int hash(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
