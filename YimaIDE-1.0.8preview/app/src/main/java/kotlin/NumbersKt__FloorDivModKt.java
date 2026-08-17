package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\u0010\t\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0000\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\nH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\nH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\nH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\nH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\nH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\nH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\nH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\nH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\u0000\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\f*\u00020\f2\u0006\u0010\u0003\u001a\u00020\fH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\r*\u00020\f2\u0006\u0010\u0003\u001a\u00020\rH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\r*\u00020\r2\u0006\u0010\u0003\u001a\u00020\fH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b\u001a,\u0010\t\u001a\u00020\r*\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007b\u0002\b\b¨\u0006\u000e"}, d2 = {"floorDiv", "", "", "other", "Lkotlin/SinceKotlin;", "version", "1.5", "Lkotlin/internal/InlineOnly;", "Lkotlin/internal/IntrinsicConstEvaluation;", "mod", "", "", "", "", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/NumbersKt")
class NumbersKt__FloorDivModKt extends NumbersKt__BigIntegersKt {
    private static final long floorDiv(byte b, long j) {
        long j2 = b;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    private static final double mod(float f, double d) {
        double d2 = ((double) f) % d;
        return (d2 == 0.0d || Math.signum(d2) == Math.signum(d)) ? d2 : d2 + d;
    }

    private static final int floorDiv(byte b, short s) {
        int i = b / s;
        return ((b ^ s) >= 0 || s * i == b) ? i : i - 1;
    }

    private static final int floorDiv(byte b, int i) {
        int i2 = b / i;
        return ((b ^ i) >= 0 || i * i2 == b) ? i2 : i2 - 1;
    }

    private static final int floorDiv(byte b, byte b2) {
        int i = b / b2;
        return ((b ^ b2) >= 0 || b2 * i == b) ? i : i - 1;
    }

    private static final int floorDiv(short s, byte b) {
        int i = s / b;
        return ((s ^ b) >= 0 || b * i == s) ? i : i - 1;
    }

    private static final int floorDiv(short s, short s2) {
        int i = s / s2;
        return ((s ^ s2) >= 0 || s2 * i == s) ? i : i - 1;
    }

    private static final short mod(byte b, short s) {
        int i = b % s;
        return (short) (i + (s & (((i ^ s) & ((-i) | i)) >> 31)));
    }

    private static final int floorDiv(short s, int i) {
        int i2 = s / i;
        return ((s ^ i) >= 0 || i * i2 == s) ? i2 : i2 - 1;
    }

    private static final int mod(byte b, int i) {
        int i2 = b % i;
        return i2 + (i & (((i2 ^ i) & ((-i2) | i2)) >> 31));
    }

    private static final long floorDiv(short s, long j) {
        long j2 = s;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    private static final long mod(byte b, long j) {
        long j2 = ((long) b) % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    private static final int floorDiv(int i, byte b) {
        int i2 = i / b;
        return ((i ^ b) >= 0 || b * i2 == i) ? i2 : i2 - 1;
    }

    private static final byte mod(short s, byte b) {
        int i = s % b;
        return (byte) (i + (b & (((i ^ b) & ((-i) | i)) >> 31)));
    }

    private static final int floorDiv(int i, short s) {
        int i2 = i / s;
        return ((i ^ s) >= 0 || s * i2 == i) ? i2 : i2 - 1;
    }

    private static final short mod(short s, short s2) {
        int i = s % s2;
        return (short) (i + (s2 & (((i ^ s2) & ((-i) | i)) >> 31)));
    }

    private static final int floorDiv(int i, int i2) {
        int i3 = i / i2;
        return ((i ^ i2) >= 0 || i2 * i3 == i) ? i3 : i3 - 1;
    }

    private static final int mod(short s, int i) {
        int i2 = s % i;
        return i2 + (i & (((i2 ^ i) & ((-i2) | i2)) >> 31));
    }

    private static final long floorDiv(int i, long j) {
        long j2 = i;
        long j3 = j2 / j;
        return ((j2 ^ j) >= 0 || j * j3 == j2) ? j3 : j3 - 1;
    }

    private static final long mod(short s, long j) {
        long j2 = ((long) s) % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    private static final long floorDiv(long j, byte b) {
        long j2 = b;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    private static final byte mod(int i, byte b) {
        int i2 = i % b;
        return (byte) (i2 + (b & (((i2 ^ b) & ((-i2) | i2)) >> 31)));
    }

    private static final long floorDiv(long j, short s) {
        long j2 = s;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    private static final short mod(int i, short s) {
        int i2 = i % s;
        return (short) (i2 + (s & (((i2 ^ s) & ((-i2) | i2)) >> 31)));
    }

    private static final long floorDiv(long j, int i) {
        long j2 = i;
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    private static final int mod(int i, int i2) {
        int i3 = i % i2;
        return i3 + (i2 & (((i3 ^ i2) & ((-i3) | i3)) >> 31));
    }

    private static final long floorDiv(long j, long j2) {
        long j3 = j / j2;
        return ((j ^ j2) >= 0 || j2 * j3 == j) ? j3 : j3 - 1;
    }

    private static final long mod(int i, long j) {
        long j2 = ((long) i) % j;
        return j2 + (j & (((j2 ^ j) & ((-j2) | j2)) >> 63));
    }

    private static final byte mod(long j, byte b) {
        long j2 = b;
        long j3 = j % j2;
        return (byte) (j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63)));
    }

    private static final short mod(long j, short s) {
        long j2 = s;
        long j3 = j % j2;
        return (short) (j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63)));
    }

    private static final int mod(long j, int i) {
        long j2 = i;
        long j3 = j % j2;
        return (int) (j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63)));
    }

    private static final long mod(long j, long j2) {
        long j3 = j % j2;
        return j3 + (j2 & (((j3 ^ j2) & ((-j3) | j3)) >> 63));
    }

    private static final float mod(float f, float f2) {
        float f3 = f % f2;
        return (f3 == 0.0f || Math.signum(f3) == Math.signum(f2)) ? f3 : f3 + f2;
    }

    private static final byte mod(byte b, byte b2) {
        int i = b % b2;
        return (byte) (i + (b2 & (((i ^ b2) & ((-i) | i)) >> 31)));
    }

    private static final double mod(double d, float f) {
        double d2 = f;
        double d3 = d % d2;
        return (d3 == 0.0d || Math.signum(d3) == Math.signum(d2)) ? d3 : d3 + d2;
    }

    private static final double mod(double d, double d2) {
        double d3 = d % d2;
        return (d3 == 0.0d || Math.signum(d3) == Math.signum(d2)) ? d3 : d3 + d2;
    }
}
