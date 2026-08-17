package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0081\u0080\u0004b\u0002\b\u0006¢\u0006\u0004\b\u0004\u0010\u0005\u001a%\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0081\u0080\u0004b\u0002\b\u0006¢\u0006\u0004\b\b\u0010\u0005\u001a%\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\nH\u0081\u0080\u0004b\u0002\b\u0006¢\u0006\u0004\b\u000b\u0010\f\u001a%\u0010\r\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\nH\u0081\u0080\u0004b\u0002\b\u0006¢\u0006\u0004\b\u000e\u0010\f\u001a\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010H\u0081\u0080\u0004b\u0002\b\u0006\u001a\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u0012H\u0081\u0080\u0004b\u0002\b\u0006\u001a\u001f\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0010H\u0081\u0088\u0004b\u0002\b\u0006b\u0002\b\u0016¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0010H\u0081\u0088\u0004b\u0002\b\u0006b\u0002\b\u0016\u001a\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u0010H\u0081\u0088\u0004b\u0002\b\u0006b\u0002\b\u0016\u001a\u001f\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0019H\u0081\u0088\u0004b\u0002\b\u0006b\u0002\b\u0016¢\u0006\u0002\u0010\u001b\u001a\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0010H\u0081\u0080\u0004b\u0002\b\u0006\u001a\u001b\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u001dH\u0081\u0080\u0004b\u0002\b\u0006¢\u0006\u0002\u0010\u001f\u001a\u001a\u0010 \u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u0012H\u0081\u0088\u0004b\u0002\b\u0006b\u0002\b\u0016\u001a\u001f\u0010!\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0019H\u0081\u0088\u0004b\u0002\b\u0006b\u0002\b\u0016¢\u0006\u0002\u0010\"\u001a\u0016\u0010#\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0012H\u0081\u0080\u0004b\u0002\b\u0006\u001a\u001b\u0010$\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u001dH\u0081\u0080\u0004b\u0002\b\u0006¢\u0006\u0002\u0010%\u001a\u0016\u0010&\u001a\u00020'2\u0006\u0010\u0014\u001a\u00020\u0010H\u0081\u0088\u0004b\u0002\b\u0016\u001a\u001e\u0010&\u001a\u00020'2\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u0010H\u0081\u0088\u0004b\u0002\b\u0016\u001a\u0016\u0010)\u001a\u00020'2\u0006\u0010\u0014\u001a\u00020\u0012H\u0081\u0088\u0004b\u0002\b\u0016\u001a\u001a\u0010)\u001a\u00020'2\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u0010H\u0080\u0080\u0004¨\u0006*"}, d2 = {"uintRemainder", "Lkotlin/UInt;", "v1", "v2", "uintRemainder-J1ME1BU", "(II)I", "Lkotlin/PublishedApi;", "uintDivide", "uintDivide-J1ME1BU", "ulongDivide", "Lkotlin/ULong;", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "uintCompare", "", "ulongCompare", "", "uintToULong", "value", "(I)J", "Lkotlin/internal/InlineOnly;", "uintToLong", "uintToFloat", "", "floatToUInt", "(F)I", "uintToDouble", "", "doubleToUInt", "(D)I", "ulongToFloat", "floatToULong", "(F)J", "ulongToDouble", "doubleToULong", "(D)J", "uintToString", "", "base", "ulongToString", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class UnsignedKt {
    public static final int doubleToUInt(double d) {
        if (Double.isNaN(d) || d <= 0.0d) {
            return 0;
        }
        if (d >= 4.294967295E9d) {
            return -1;
        }
        return d <= 2.147483647E9d ? UInt.m133constructorimpl((int) d) : UInt.m133constructorimpl(UInt.m133constructorimpl((int) (d - 2.147483647E9d)) + Integer.MAX_VALUE);
    }

    public static final long doubleToULong(double d) {
        if (Double.isNaN(d) || d <= 0.0d) {
            return 0L;
        }
        if (d >= 1.8446744073709552E19d) {
            return -1L;
        }
        return d < 9.223372036854776E18d ? ULong.m212constructorimpl((long) d) : ULong.m212constructorimpl(ULong.m212constructorimpl((long) (d - 9.223372036854776E18d)) - Long.MIN_VALUE);
    }

    private static final int floatToUInt(float f) {
        return doubleToUInt(f);
    }

    private static final long floatToULong(float f) {
        return doubleToULong(f);
    }

    public static final int uintCompare(int i, int i2) {
        return Intrinsics.compare(i ^ IntCompanionObject.MIN_VALUE, i2 ^ IntCompanionObject.MIN_VALUE);
    }

    /* JADX INFO: renamed from: uintDivide-J1ME1BU, reason: not valid java name */
    public static final int m389uintDivideJ1ME1BU(int i, int i2) {
        return UInt.m133constructorimpl((int) ((((long) i) & 4294967295L) / (((long) i2) & 4294967295L)));
    }

    /* JADX INFO: renamed from: uintRemainder-J1ME1BU, reason: not valid java name */
    public static final int m390uintRemainderJ1ME1BU(int i, int i2) {
        return UInt.m133constructorimpl((int) ((((long) i) & 4294967295L) % (((long) i2) & 4294967295L)));
    }

    public static final double uintToDouble(int i) {
        return ((double) (Integer.MAX_VALUE & i)) + (((double) ((i >>> 31) << 30)) * 2.0d);
    }

    private static final float uintToFloat(int i) {
        return (float) uintToDouble(i);
    }

    private static final long uintToLong(int i) {
        return ((long) i) & 4294967295L;
    }

    private static final String uintToString(int i) {
        return String.valueOf(((long) i) & 4294967295L);
    }

    private static final long uintToULong(int i) {
        return ULong.m212constructorimpl(((long) i) & 4294967295L);
    }

    public static final int ulongCompare(long j, long j2) {
        return Intrinsics.compare(j ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
    }

    /* JADX INFO: renamed from: ulongDivide-eb3DHEI, reason: not valid java name */
    public static final long m391ulongDivideeb3DHEI(long j, long j2) {
        if (j2 < 0) {
            return Long.compareUnsigned(j, j2) < 0 ? ULong.m212constructorimpl(0L) : ULong.m212constructorimpl(1L);
        }
        if (j >= 0) {
            return ULong.m212constructorimpl(j / j2);
        }
        long j3 = ((j >>> 1) / j2) << 1;
        return ULong.m212constructorimpl(j3 + ((long) (Long.compareUnsigned(ULong.m212constructorimpl(j - (j3 * j2)), ULong.m212constructorimpl(j2)) < 0 ? 0 : 1)));
    }

    /* JADX INFO: renamed from: ulongRemainder-eb3DHEI, reason: not valid java name */
    public static final long m392ulongRemaindereb3DHEI(long j, long j2) {
        if (j2 < 0) {
            return Long.compareUnsigned(j, j2) < 0 ? j : ULong.m212constructorimpl(j - j2);
        }
        if (j >= 0) {
            return ULong.m212constructorimpl(j % j2);
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if (Long.compareUnsigned(ULong.m212constructorimpl(j3), ULong.m212constructorimpl(j2)) < 0) {
            j2 = 0;
        }
        return ULong.m212constructorimpl(j3 - j2);
    }

    public static final double ulongToDouble(long j) {
        return ((j >>> 11) * 2048.0d) + (j & 2047);
    }

    private static final float ulongToFloat(long j) {
        return (float) ulongToDouble(j);
    }

    public static final String ulongToString(long j, int i) {
        if (j >= 0) {
            String string = Long.toString(j, CharsKt.checkRadix(i));
            string.getClass();
            return string;
        }
        long j2 = i;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        StringBuilder sb = new StringBuilder();
        String string2 = Long.toString(j3, CharsKt.checkRadix(i));
        string2.getClass();
        sb.append(string2);
        String string3 = Long.toString(j4, CharsKt.checkRadix(i));
        string3.getClass();
        sb.append(string3);
        return sb.toString();
    }

    private static final String uintToString(int i, int i2) {
        return ulongToString(((long) i) & 4294967295L, i2);
    }

    private static final String ulongToString(long j) {
        return ulongToString(j, 10);
    }
}
