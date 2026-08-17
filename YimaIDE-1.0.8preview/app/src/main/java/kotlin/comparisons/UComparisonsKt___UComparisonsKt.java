package kotlin.comparisons;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a/\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0004\b\n\u0010\u000b\u001a/\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\fH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0004\b\r\u0010\u000e\u001a/\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0004\b\u0010\u0010\u0011\u001a;\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bb\u0002\b\u0015¢\u0006\u0004\b\u0013\u0010\u0014\u001a;\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0087\u0088\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bb\u0002\b\u0015¢\u0006\u0004\b\u0016\u0010\u0017\u001a;\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0087\u0088\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bb\u0002\b\u0015¢\u0006\u0004\b\u0018\u0010\u0019\u001a;\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0087\u0088\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bb\u0002\b\u0015¢\u0006\u0004\b\u001a\u0010\u001b\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u001c\u001a\u00020\u001d\"\u00020\u0001H\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( b\u0002\b!¢\u0006\u0004\b\u001e\u0010\u001f\u001a7\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\n\u0010\u001c\u001a\u00020\"\"\u00020\tH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( b\u0002\b!¢\u0006\u0004\b#\u0010$\u001a7\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\n\u0010\u001c\u001a\u00020%\"\u00020\fH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( b\u0002\b!¢\u0006\u0004\b&\u0010'\u001a7\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\n\u0010\u001c\u001a\u00020(\"\u00020\u000fH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( b\u0002\b!¢\u0006\u0004\b)\u0010*\u001a/\u0010+\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0004\b,\u0010\u0005\u001a/\u0010+\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\tH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0004\b-\u0010\u000b\u001a/\u0010+\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\fH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0004\b.\u0010\u000e\u001a/\u0010+\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0004\b/\u0010\u0011\u001a;\u0010+\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0087\u0088\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bb\u0002\b\u0015¢\u0006\u0004\b0\u0010\u0014\u001a;\u0010+\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\tH\u0087\u0088\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bb\u0002\b\u0015¢\u0006\u0004\b1\u0010\u0017\u001a;\u0010+\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0087\u0088\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bb\u0002\b\u0015¢\u0006\u0004\b2\u0010\u0019\u001a;\u0010+\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0087\u0088\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\bb\u0002\b\u0015¢\u0006\u0004\b3\u0010\u001b\u001a7\u0010+\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\u001c\u001a\u00020\u001d\"\u00020\u0001H\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( b\u0002\b!¢\u0006\u0004\b4\u0010\u001f\u001a7\u0010+\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\n\u0010\u001c\u001a\u00020\"\"\u00020\tH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( b\u0002\b!¢\u0006\u0004\b5\u0010$\u001a7\u0010+\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\n\u0010\u001c\u001a\u00020%\"\u00020\fH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( b\u0002\b!¢\u0006\u0004\b6\u0010'\u001a7\u0010+\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\n\u0010\u001c\u001a\u00020(\"\u00020\u000fH\u0087\u0080\u0004b\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b( b\u0002\b!¢\u0006\u0004\b7\u0010*¨\u00068"}, d2 = {"maxOf", "Lkotlin/UInt;", "a", "b", "maxOf-J1ME1BU", "(II)I", "Lkotlin/SinceKotlin;", "version", "1.5", "Lkotlin/ULong;", "maxOf-eb3DHEI", "(JJ)J", "Lkotlin/UByte;", "maxOf-Kr8caGY", "(BB)B", "Lkotlin/UShort;", "maxOf-5PvTz6A", "(SS)S", "c", "maxOf-WZ9TVnA", "(III)I", "Lkotlin/internal/InlineOnly;", "maxOf-sambcqE", "(JJJ)J", "maxOf-b33U2AM", "(BBB)B", "maxOf-VKSA0NQ", "(SSS)S", "other", "Lkotlin/UIntArray;", "maxOf-Md2H83M", "(I[I)I", "1.4", "Lkotlin/ExperimentalUnsignedTypes;", "Lkotlin/ULongArray;", "maxOf-R03FKyM", "(J[J)J", "Lkotlin/UByteArray;", "maxOf-Wr6uiD8", "(B[B)B", "Lkotlin/UShortArray;", "maxOf-t1qELG4", "(S[S)S", "minOf", "minOf-J1ME1BU", "minOf-eb3DHEI", "minOf-Kr8caGY", "minOf-5PvTz6A", "minOf-WZ9TVnA", "minOf-sambcqE", "minOf-b33U2AM", "minOf-VKSA0NQ", "minOf-Md2H83M", "minOf-R03FKyM", "minOf-Wr6uiD8", "minOf-t1qELG4", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/comparisons/UComparisonsKt")
public class UComparisonsKt___UComparisonsKt {
    /* JADX INFO: renamed from: maxOf-5PvTz6A, reason: not valid java name */
    public static final short m1242maxOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) >= 0 ? s : s2;
    }

    /* JADX INFO: renamed from: maxOf-J1ME1BU, reason: not valid java name */
    public static int m1243maxOfJ1ME1BU(int i, int i2) {
        return Integer.compareUnsigned(i, i2) >= 0 ? i : i2;
    }

    /* JADX INFO: renamed from: maxOf-Kr8caGY, reason: not valid java name */
    public static final byte m1244maxOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & UByte.MAX_VALUE, b2 & UByte.MAX_VALUE) >= 0 ? b : b2;
    }

    /* JADX INFO: renamed from: maxOf-Md2H83M, reason: not valid java name */
    public static final int m1245maxOfMd2H83M(int i, int... iArr) {
        iArr.getClass();
        int iM194getSizeimpl = UIntArray.m194getSizeimpl(iArr);
        for (int i2 = 0; i2 < iM194getSizeimpl; i2++) {
            i = m1243maxOfJ1ME1BU(i, UIntArray.m193getpVg5ArA(iArr, i2));
        }
        return i;
    }

    /* JADX INFO: renamed from: maxOf-R03FKyM, reason: not valid java name */
    public static final long m1246maxOfR03FKyM(long j, long... jArr) {
        jArr.getClass();
        int iM273getSizeimpl = ULongArray.m273getSizeimpl(jArr);
        for (int i = 0; i < iM273getSizeimpl; i++) {
            j = m1251maxOfeb3DHEI(j, ULongArray.m272getsVKNKU(jArr, i));
        }
        return j;
    }

    /* JADX INFO: renamed from: maxOf-VKSA0NQ, reason: not valid java name */
    private static final short m1247maxOfVKSA0NQ(short s, short s2, short s3) {
        return m1242maxOf5PvTz6A(s, m1242maxOf5PvTz6A(s2, s3));
    }

    /* JADX INFO: renamed from: maxOf-WZ9TVnA, reason: not valid java name */
    private static final int m1248maxOfWZ9TVnA(int i, int i2, int i3) {
        return m1243maxOfJ1ME1BU(i, m1243maxOfJ1ME1BU(i2, i3));
    }

    /* JADX INFO: renamed from: maxOf-Wr6uiD8, reason: not valid java name */
    public static final byte m1249maxOfWr6uiD8(byte b, byte... bArr) {
        bArr.getClass();
        int iM115getSizeimpl = UByteArray.m115getSizeimpl(bArr);
        for (int i = 0; i < iM115getSizeimpl; i++) {
            b = m1244maxOfKr8caGY(b, UByteArray.m114getw2LRezQ(bArr, i));
        }
        return b;
    }

    /* JADX INFO: renamed from: maxOf-b33U2AM, reason: not valid java name */
    private static final byte m1250maxOfb33U2AM(byte b, byte b2, byte b3) {
        return m1244maxOfKr8caGY(b, m1244maxOfKr8caGY(b2, b3));
    }

    /* JADX INFO: renamed from: maxOf-eb3DHEI, reason: not valid java name */
    public static long m1251maxOfeb3DHEI(long j, long j2) {
        return Long.compareUnsigned(j, j2) >= 0 ? j : j2;
    }

    /* JADX INFO: renamed from: maxOf-sambcqE, reason: not valid java name */
    private static final long m1252maxOfsambcqE(long j, long j2, long j3) {
        return m1251maxOfeb3DHEI(j, m1251maxOfeb3DHEI(j2, j3));
    }

    /* JADX INFO: renamed from: maxOf-t1qELG4, reason: not valid java name */
    public static final short m1253maxOft1qELG4(short s, short... sArr) {
        sArr.getClass();
        int iM378getSizeimpl = UShortArray.m378getSizeimpl(sArr);
        for (int i = 0; i < iM378getSizeimpl; i++) {
            s = m1242maxOf5PvTz6A(s, UShortArray.m377getMh2AYeg(sArr, i));
        }
        return s;
    }

    /* JADX INFO: renamed from: minOf-5PvTz6A, reason: not valid java name */
    public static final short m1254minOf5PvTz6A(short s, short s2) {
        return Intrinsics.compare(s & UShort.MAX_VALUE, 65535 & s2) <= 0 ? s : s2;
    }

    /* JADX INFO: renamed from: minOf-J1ME1BU, reason: not valid java name */
    public static int m1255minOfJ1ME1BU(int i, int i2) {
        return Integer.compareUnsigned(i, i2) <= 0 ? i : i2;
    }

    /* JADX INFO: renamed from: minOf-Kr8caGY, reason: not valid java name */
    public static final byte m1256minOfKr8caGY(byte b, byte b2) {
        return Intrinsics.compare(b & UByte.MAX_VALUE, b2 & UByte.MAX_VALUE) <= 0 ? b : b2;
    }

    /* JADX INFO: renamed from: minOf-Md2H83M, reason: not valid java name */
    public static final int m1257minOfMd2H83M(int i, int... iArr) {
        iArr.getClass();
        int iM194getSizeimpl = UIntArray.m194getSizeimpl(iArr);
        for (int i2 = 0; i2 < iM194getSizeimpl; i2++) {
            i = m1255minOfJ1ME1BU(i, UIntArray.m193getpVg5ArA(iArr, i2));
        }
        return i;
    }

    /* JADX INFO: renamed from: minOf-R03FKyM, reason: not valid java name */
    public static final long m1258minOfR03FKyM(long j, long... jArr) {
        jArr.getClass();
        int iM273getSizeimpl = ULongArray.m273getSizeimpl(jArr);
        for (int i = 0; i < iM273getSizeimpl; i++) {
            j = m1263minOfeb3DHEI(j, ULongArray.m272getsVKNKU(jArr, i));
        }
        return j;
    }

    /* JADX INFO: renamed from: minOf-VKSA0NQ, reason: not valid java name */
    private static final short m1259minOfVKSA0NQ(short s, short s2, short s3) {
        return m1254minOf5PvTz6A(s, m1254minOf5PvTz6A(s2, s3));
    }

    /* JADX INFO: renamed from: minOf-WZ9TVnA, reason: not valid java name */
    private static final int m1260minOfWZ9TVnA(int i, int i2, int i3) {
        return m1255minOfJ1ME1BU(i, m1255minOfJ1ME1BU(i2, i3));
    }

    /* JADX INFO: renamed from: minOf-Wr6uiD8, reason: not valid java name */
    public static final byte m1261minOfWr6uiD8(byte b, byte... bArr) {
        bArr.getClass();
        int iM115getSizeimpl = UByteArray.m115getSizeimpl(bArr);
        for (int i = 0; i < iM115getSizeimpl; i++) {
            b = m1256minOfKr8caGY(b, UByteArray.m114getw2LRezQ(bArr, i));
        }
        return b;
    }

    /* JADX INFO: renamed from: minOf-b33U2AM, reason: not valid java name */
    private static final byte m1262minOfb33U2AM(byte b, byte b2, byte b3) {
        return m1256minOfKr8caGY(b, m1256minOfKr8caGY(b2, b3));
    }

    /* JADX INFO: renamed from: minOf-eb3DHEI, reason: not valid java name */
    public static long m1263minOfeb3DHEI(long j, long j2) {
        return Long.compareUnsigned(j, j2) <= 0 ? j : j2;
    }

    /* JADX INFO: renamed from: minOf-sambcqE, reason: not valid java name */
    private static final long m1264minOfsambcqE(long j, long j2, long j3) {
        return m1263minOfeb3DHEI(j, m1263minOfeb3DHEI(j2, j3));
    }

    /* JADX INFO: renamed from: minOf-t1qELG4, reason: not valid java name */
    public static final short m1265minOft1qELG4(short s, short... sArr) {
        sArr.getClass();
        int iM378getSizeimpl = UShortArray.m378getSizeimpl(sArr);
        for (int i = 0; i < iM378getSizeimpl; i++) {
            s = m1254minOf5PvTz6A(s, UShortArray.m377getMh2AYeg(sArr, i));
        }
        return s;
    }
}
