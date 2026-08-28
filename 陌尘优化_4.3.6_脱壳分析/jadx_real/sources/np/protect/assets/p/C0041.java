package np.protect.assets.p;

/* renamed from: np.protect.assets.p.۟۟۟ۡۤ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public final class C0041 {
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static double m449(float f, float f2) {
        return Double.longBitsToDouble(m452(Float.floatToIntBits(f), Float.floatToIntBits(f2)));
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static double m450(int i, int i2) {
        return Double.longBitsToDouble(m452(i, i2));
    }

    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    public static int m451(int i) {
        return (i & 1) == 1 ? i + 1 : i;
    }

    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    public static long m452(int i, int i2) {
        return (i & 4294967295L) | (i2 << 32);
    }
}
