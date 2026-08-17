package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.text.Charsets;
import kotlin.text.UStringsKt;
import org.eclipse.jdt.internal.compiler.parser.TerminalTokens;
import org.jetbrains.kotlin.backend.jvm.JvmSyntheticAccessorGenerator;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\u001d\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\fH\u0002¢\u0006\u0002\u0010\u0011\u001a\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\fH\u0002¢\u0006\u0002\u0010\u0014\u001a\u001f\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0017\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0001H\u0002¢\u0006\u0004\b \u0010!\u001a'\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0001H\u0002¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010%\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0001H\u0002¢\u0006\u0004\b&\u0010!\u001a%\u0010'\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010(\u001a\u00020\fH\u0002¢\u0006\u0002\u0010)\u001a%\u0010*\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010(\u001a\u00020\fH\u0002¢\u0006\u0002\u0010)\u001a?\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00012\u0006\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0002¢\u0006\u0004\b2\u00103\u001a/\u0010+\u001a\u00020,2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u00101\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0002¢\u0006\u0004\b4\u00105\u001a\u0015\u00106\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0001¢\u0006\u0004\b7\u0010\u001c\u001a%\u00108\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010(\u001a\u00020\fH\u0002¢\u0006\u0002\u0010)\u001a'\u00109\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010(\u001a\u00020\f¢\u0006\u0002\u0010)\u001a\n\u00109\u001a\u00020\b*\u00020:\u001a\n\u0010;\u001a\u00020:*\u00020:\u001a,\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020=2\u0006\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010(\u001a\u00020\fH\u0002\u001a*\u0010?\u001a\u00020=2\u0006\u0010>\u001a\u00020=2\u0006\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010(\u001a\u00020\f\u001a\"\u0010@\u001a\u00020=2\u0006\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010(\u001a\u00020\f\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082D¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082D¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082D¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082D¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006A"}, d2 = {"k0", "Lkotlin/ULong;", "J", "k1", "k2", "kMul", "kGoldenRatio", "toLongLE", "", "b", "", "i", "", "toIntLE", "fetch64", JvmSyntheticAccessorGenerator.SUPER_QUALIFIER_SUFFIX_MARKER, "pos", "([BI)J", "fetch32", "Lkotlin/UInt;", "([BI)I", "rotate", "value", "shift", "rotate-4PLdz1A", "(JI)J", "shiftMix", "shiftMix-VKZWuLQ", "(J)J", "hashLen16", "u", "v", "hashLen16-PWzV0Is", "(JJ)J", "mul", "hashLen16-nJd8lh8", "(JJJ)J", "hash128to64", "hash128to64-PWzV0Is", "hashLen0to16", "len", "([BII)J", "hashLen17to32", "weakHashLen32WithSeeds", "Lkotlin/ULongArray;", "w", "x", "y", "z", "a", "weakHashLen32WithSeeds-8jibXXU", "(JJJJJJ)[J", "weakHashLen32WithSeeds-RQJlUXk", "([BIJJ)[J", "bswap", "bswap-VKZWuLQ", "hashLen33to64", "cityHash64", "", "cityHash64String", "cityMurmur", "Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "seed", "cityHash128WithSeed", "cityHash128", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class CityHashKt {
    private static final long k0 = -4348849565147123417L;
    private static final long k1 = -5435081209227447693L;
    private static final long k2 = -7286425919675154353L;
    private static final long kGoldenRatio = -7046029254386353131L;
    private static final long kMul = -7070675565921424023L;

    /* JADX INFO: renamed from: bswap-VKZWuLQ, reason: not valid java name */
    public static final long m207bswapVKZWuLQ(long j) {
        return ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j) & 255) << 56) | ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j >>> 8) & 255) << 48)) | ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j >>> 16) & 255) << 40)) | ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j >>> 24) & 255) << 32)) | ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j >>> 32) & 255) << 24)) | ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j >>> 40) & 255) << 16)) | ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j >>> 48) & 255) << 8)) | ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j >>> 56) & 255)));
    }

    public static final Hash128Bits cityHash128(byte[] bArr, int i, int i2) {
        bArr.getClass();
        return i2 >= 16 ? cityHash128WithSeed(new Hash128Bits(fetch64(bArr, i), ULong.constructor-impl(fetch64(bArr, i + 8) + k0), null), bArr, i + 16, i2 - 16) : cityHash128WithSeed(new Hash128Bits(k0, k1, null), bArr, i, i2);
    }

    public static /* synthetic */ Hash128Bits cityHash128$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return cityHash128(bArr, i, i2);
    }

    public static final Hash128Bits cityHash128WithSeed(Hash128Bits hash128Bits, byte[] bArr, int i, int i2) {
        long j;
        long j2;
        long[] jArrM214weakHashLen32WithSeedsRQJlUXk;
        long[] jArrM214weakHashLen32WithSeedsRQJlUXk2;
        int i3;
        byte[] bArr2 = bArr;
        hash128Bits.getClass();
        bArr2.getClass();
        if (i2 < 128) {
            return cityMurmur(hash128Bits, bArr, i, i2);
        }
        long[] jArr = ULongArray.constructor-impl(2);
        long[] jArr2 = ULongArray.constructor-impl(2);
        long jM229component1sVKNKU = hash128Bits.getLowBytes();
        long jM230component2sVKNKU = hash128Bits.getHighBytes();
        long j3 = ULong.constructor-impl(i2);
        long j4 = k1;
        long j5 = ULong.constructor-impl(j3 * j4);
        ULongArray.set-k8EXiF4(jArr, 0, ULong.constructor-impl(ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(jM230component2sVKNKU ^ j4), 49) * j4) + fetch64(bArr, i)));
        ULongArray.set-k8EXiF4(jArr, 1, ULong.constructor-impl(ULong.constructor-impl(m211rotate4PLdz1A(ULongArray.get-s-VKNKU(jArr, 0), 42) * j4) + fetch64(bArr2, i + 8)));
        ULongArray.set-k8EXiF4(jArr2, 0, ULong.constructor-impl(ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(jM230component2sVKNKU + j5), 35) * j4) + jM229component1sVKNKU));
        ULongArray.set-k8EXiF4(jArr2, 1, ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(fetch64(bArr2, i + 88) + jM229component1sVKNKU), 53) * j4));
        int i4 = i2;
        long j6 = j5;
        int i5 = i;
        long j7 = jM230component2sVKNKU;
        long[] jArr3 = jArr2;
        while (true) {
            long jM211rotate4PLdz1A = m211rotate4PLdz1A(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(jM229component1sVKNKU + j7) + ULongArray.get-s-VKNKU(jArr, 0)) + fetch64(bArr2, i5 + 8)), 37);
            long j8 = k1;
            long j9 = ULong.constructor-impl(jM211rotate4PLdz1A * j8);
            long j10 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(ULong.constructor-impl(j7 + ULongArray.get-s-VKNKU(jArr, 1)) + fetch64(bArr2, i5 + 48)), 42) * j8);
            long j11 = ULong.constructor-impl(j9 ^ ULongArray.get-s-VKNKU(jArr3, 1));
            long j12 = ULong.constructor-impl(j10 + ULong.constructor-impl(ULongArray.get-s-VKNKU(jArr, 0) + fetch64(bArr2, i5 + 40)));
            long j13 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(j6 + ULongArray.get-s-VKNKU(jArr3, 0)), 33) * j8);
            long[] jArrM214weakHashLen32WithSeedsRQJlUXk3 = m214weakHashLen32WithSeedsRQJlUXk(bArr2, i5, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArr, 1) * j8), ULong.constructor-impl(j11 + ULongArray.get-s-VKNKU(jArr3, 0)));
            int i6 = i5;
            long[] jArrM214weakHashLen32WithSeedsRQJlUXk4 = m214weakHashLen32WithSeedsRQJlUXk(bArr2, i6 + 32, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArr3, 1) + j13), ULong.constructor-impl(j12 + fetch64(bArr2, i6 + 16)));
            int i7 = i6 + 64;
            long j14 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j13 + j12) + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 0)) + fetch64(bArr2, i6 + 72)), 37) * j8);
            long j15 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(ULong.constructor-impl(j12 + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 1)) + fetch64(bArr2, i6 + TerminalTokens.TokenNameimport)), 42) * j8);
            j = ULong.constructor-impl(j14 ^ ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk4, 1));
            j7 = ULong.constructor-impl(j15 + ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 0) + fetch64(bArr2, i6 + TerminalTokens.TokenNameUNSIGNED_RIGHT_SHIFT_EQUAL)));
            j2 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(j11 + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk4, 0)), 33) * j8);
            jArrM214weakHashLen32WithSeedsRQJlUXk = m214weakHashLen32WithSeedsRQJlUXk(bArr2, i7, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 1) * j8), ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk4, 0) + j));
            jArrM214weakHashLen32WithSeedsRQJlUXk2 = m214weakHashLen32WithSeedsRQJlUXk(bArr2, i6 + 96, ULong.constructor-impl(j2 + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk4, 1)), ULong.constructor-impl(fetch64(bArr2, i6 + 80) + j7));
            i3 = i6 + 128;
            i4 -= 128;
            if (i4 < 128) {
                break;
            }
            bArr2 = bArr;
            i5 = i3;
            jArr3 = jArrM214weakHashLen32WithSeedsRQJlUXk2;
            j6 = j;
            jArr = jArrM214weakHashLen32WithSeedsRQJlUXk;
            jM229component1sVKNKU = j2;
        }
        long jM211rotate4PLdz1A2 = m211rotate4PLdz1A(ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0) + j), 49);
        long j16 = k0;
        long j17 = ULong.constructor-impl(j2 + ULong.constructor-impl(jM211rotate4PLdz1A2 * j16));
        long j18 = ULong.constructor-impl(ULong.constructor-impl(j7 * j16) + m211rotate4PLdz1A(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 1), 37));
        long j19 = ULong.constructor-impl(ULong.constructor-impl(j * j16) + m211rotate4PLdz1A(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 0), 27));
        ULongArray.set-k8EXiF4(jArrM214weakHashLen32WithSeedsRQJlUXk2, 0, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 0) * 9));
        ULongArray.set-k8EXiF4(jArrM214weakHashLen32WithSeedsRQJlUXk, 0, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0) * j16));
        long j20 = j18;
        int i8 = 0;
        long j21 = j17;
        while (i8 < i4) {
            int i9 = i8 + 32;
            long jM211rotate4PLdz1A3 = m211rotate4PLdz1A(ULong.constructor-impl(j20 + j21), 42);
            long j22 = k0;
            long j23 = ULong.constructor-impl(ULong.constructor-impl(jM211rotate4PLdz1A3 * j22) + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 1));
            int i10 = (i3 + i4) - i9;
            ULongArray.set-k8EXiF4(jArrM214weakHashLen32WithSeedsRQJlUXk2, 0, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 0) + fetch64(bArr2, i10 + 16)));
            long j24 = ULong.constructor-impl(ULong.constructor-impl(j21 * j22) + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 0));
            j19 = ULong.constructor-impl(j19 + ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 1) + fetch64(bArr2, i10)));
            ULongArray.set-k8EXiF4(jArrM214weakHashLen32WithSeedsRQJlUXk2, 1, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 1) + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0)));
            jArrM214weakHashLen32WithSeedsRQJlUXk = m214weakHashLen32WithSeedsRQJlUXk(bArr2, i10, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0) + j19), ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 1));
            ULongArray.set-k8EXiF4(jArrM214weakHashLen32WithSeedsRQJlUXk, 0, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0) * j22));
            bArr2 = bArr;
            i8 = i9;
            j20 = j23;
            j21 = j24;
        }
        long jM209hashLen16PWzV0Is = m209hashLen16PWzV0Is(j21, ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0));
        long jM209hashLen16PWzV0Is2 = m209hashLen16PWzV0Is(ULong.constructor-impl(j20 + j19), ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 0));
        return new Hash128Bits(ULong.constructor-impl(m209hashLen16PWzV0Is(ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 1) + jM209hashLen16PWzV0Is), ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 1)) + jM209hashLen16PWzV0Is2), m209hashLen16PWzV0Is(ULong.constructor-impl(jM209hashLen16PWzV0Is + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk2, 1)), ULong.constructor-impl(jM209hashLen16PWzV0Is2 + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 1))), null);
    }

    public static /* synthetic */ Hash128Bits cityHash128WithSeed$default(Hash128Bits hash128Bits, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length;
        }
        return cityHash128WithSeed(hash128Bits, bArr, i, i2);
    }

    public static final long cityHash64(byte[] bArr, int i, int i2) {
        bArr.getClass();
        if (i2 <= 32) {
            return i2 <= 16 ? hashLen0to16(bArr, i, i2) : hashLen17to32(bArr, i, i2);
        }
        if (i2 <= 64) {
            return hashLen33to64(bArr, i, i2);
        }
        int i3 = i + i2;
        long jFetch64 = fetch64(bArr, i3 - 40);
        long j = ULong.constructor-impl(fetch64(bArr, i3 - 16) + fetch64(bArr, i3 - 56));
        long jM209hashLen16PWzV0Is = m209hashLen16PWzV0Is(ULong.constructor-impl(fetch64(bArr, i3 - 48) + ULong.constructor-impl(((long) UInt.constructor-impl(i2)) & 4294967295L)), fetch64(bArr, i3 - 24));
        long[] jArrM214weakHashLen32WithSeedsRQJlUXk = m214weakHashLen32WithSeedsRQJlUXk(bArr, i3 - 64, ULong.constructor-impl(i2), jM209hashLen16PWzV0Is);
        long j2 = k1;
        byte[] bArr2 = bArr;
        long[] jArrM214weakHashLen32WithSeedsRQJlUXk2 = m214weakHashLen32WithSeedsRQJlUXk(bArr2, i3 - 32, ULong.constructor-impl(j + j2), jFetch64);
        long j3 = ULong.constructor-impl(ULong.constructor-impl(jFetch64 * j2) + fetch64(bArr, i));
        long[] jArrM214weakHashLen32WithSeedsRQJlUXk3 = jArrM214weakHashLen32WithSeedsRQJlUXk2;
        int i4 = (i2 - 1) & (-64);
        long j4 = jM209hashLen16PWzV0Is;
        int i5 = i;
        while (true) {
            long jM211rotate4PLdz1A = m211rotate4PLdz1A(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j3 + j) + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0)) + fetch64(bArr2, i5 + 8)), 37);
            long j5 = k1;
            long j6 = ULong.constructor-impl(jM211rotate4PLdz1A * j5);
            long j7 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(ULong.constructor-impl(j + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 1)) + fetch64(bArr2, i5 + 48)), 42) * j5);
            long j8 = ULong.constructor-impl(j6 ^ ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 1));
            j = ULong.constructor-impl(j7 + ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0) + fetch64(bArr2, i5 + 40)));
            long j9 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(j4 + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 0)), 33) * j5);
            jArrM214weakHashLen32WithSeedsRQJlUXk = m214weakHashLen32WithSeedsRQJlUXk(bArr2, i5, ULong.constructor-impl(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 1) * j5), ULong.constructor-impl(j8 + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 0)));
            int i6 = i5;
            jArrM214weakHashLen32WithSeedsRQJlUXk3 = m214weakHashLen32WithSeedsRQJlUXk(bArr2, i6 + 32, ULong.constructor-impl(j9 + ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 1)), ULong.constructor-impl(fetch64(bArr2, i6 + 16) + j));
            i5 = i6 + 64;
            i4 -= 64;
            if (i4 == 0) {
                return m209hashLen16PWzV0Is(ULong.constructor-impl(ULong.constructor-impl(m209hashLen16PWzV0Is(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 0), ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 0)) + ULong.constructor-impl(m212shiftMixVKZWuLQ(j) * j5)) + j8), ULong.constructor-impl(m209hashLen16PWzV0Is(ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk, 1), ULongArray.get-s-VKNKU(jArrM214weakHashLen32WithSeedsRQJlUXk3, 1)) + j9));
            }
            bArr2 = bArr;
            j4 = j8;
            j3 = j9;
        }
    }

    public static /* synthetic */ long cityHash64$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return cityHash64(bArr, i, i2);
    }

    public static final String cityHash64String(String str) {
        str.getClass();
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        bytes.getClass();
        return UStringsKt.toString-JSWoG40(cityHash64$default(bytes, 0, 0, 6, null), 36);
    }

    private static final Hash128Bits cityMurmur(Hash128Bits hash128Bits, byte[] bArr, int i, int i2) {
        long j;
        long j2;
        long jM212shiftMixVKZWuLQ;
        long jM229component1sVKNKU = hash128Bits.getLowBytes();
        long jM230component2sVKNKU = hash128Bits.getHighBytes();
        if (i2 <= 16) {
            long j3 = k1;
            j = ULong.constructor-impl(m212shiftMixVKZWuLQ(ULong.constructor-impl(jM229component1sVKNKU * j3)) * j3);
            j2 = ULong.constructor-impl(ULong.constructor-impl(j3 * jM230component2sVKNKU) + hashLen0to16(bArr, i, i2));
            jM212shiftMixVKZWuLQ = m212shiftMixVKZWuLQ(ULong.constructor-impl((i2 >= 8 ? fetch64(bArr, i) : j2) + j));
        } else {
            int i3 = i + i2;
            long jM209hashLen16PWzV0Is = m209hashLen16PWzV0Is(ULong.constructor-impl(fetch64(bArr, i3 - 8) + k1), jM229component1sVKNKU);
            long jM209hashLen16PWzV0Is2 = m209hashLen16PWzV0Is(ULong.constructor-impl(ULong.constructor-impl(i2) + jM230component2sVKNKU), ULong.constructor-impl(fetch64(bArr, i3 - 16) + jM209hashLen16PWzV0Is));
            long j4 = jM209hashLen16PWzV0Is2;
            long j5 = jM209hashLen16PWzV0Is;
            long j6 = jM230component2sVKNKU;
            long j7 = ULong.constructor-impl(jM229component1sVKNKU + jM209hashLen16PWzV0Is2);
            int i4 = i2;
            int i5 = i;
            do {
                long jFetch64 = fetch64(bArr, i5);
                long j8 = k1;
                j7 = ULong.constructor-impl(ULong.constructor-impl(j7 ^ ULong.constructor-impl(m212shiftMixVKZWuLQ(ULong.constructor-impl(jFetch64 * j8)) * j8)) * j8);
                j6 = ULong.constructor-impl(j6 ^ j7);
                j5 = ULong.constructor-impl(ULong.constructor-impl(j5 ^ ULong.constructor-impl(m212shiftMixVKZWuLQ(ULong.constructor-impl(fetch64(bArr, i5 + 8) * j8)) * j8)) * j8);
                j4 = ULong.constructor-impl(j4 ^ j5);
                i5 += 16;
                i4 -= 16;
            } while (i4 > 16);
            j = j7;
            jM230component2sVKNKU = j6;
            j2 = j5;
            jM212shiftMixVKZWuLQ = j4;
        }
        long jM209hashLen16PWzV0Is3 = m209hashLen16PWzV0Is(j, j2);
        long jM209hashLen16PWzV0Is4 = m209hashLen16PWzV0Is(jM212shiftMixVKZWuLQ, jM230component2sVKNKU);
        return new Hash128Bits(ULong.constructor-impl(jM209hashLen16PWzV0Is3 ^ jM209hashLen16PWzV0Is4), m209hashLen16PWzV0Is(jM209hashLen16PWzV0Is4, jM209hashLen16PWzV0Is3), null);
    }

    private static final int fetch32(byte[] bArr, int i) {
        return UInt.constructor-impl(toIntLE(bArr, i));
    }

    private static final long fetch64(byte[] bArr, int i) {
        return ULong.constructor-impl(toLongLE(bArr, i));
    }

    /* JADX INFO: renamed from: hash128to64-PWzV0Is, reason: not valid java name */
    private static final long m208hash128to64PWzV0Is(long j, long j2) {
        long j3 = ULong.constructor-impl(j ^ j2);
        long j4 = kMul;
        long j5 = ULong.constructor-impl(j3 * j4);
        long j6 = ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j5 ^ ULong.constructor-impl(j5 >>> 47)) ^ j2) * j4);
        return ULong.constructor-impl(ULong.constructor-impl(j6 ^ ULong.constructor-impl(j6 >>> 47)) * j4);
    }

    private static final long hashLen0to16(byte[] bArr, int i, int i2) {
        if (i2 >= 8) {
            long j = k2;
            long j2 = ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(i2) * ULong.constructor-impl(2L)) + j);
            long j3 = ULong.constructor-impl(fetch64(bArr, i) + j);
            long jFetch64 = fetch64(bArr, (i + i2) - 8);
            return m210hashLen16nJd8lh8(ULong.constructor-impl(ULong.constructor-impl(m211rotate4PLdz1A(jFetch64, 37) * j2) + j3), ULong.constructor-impl(ULong.constructor-impl(m211rotate4PLdz1A(j3, 25) + jFetch64) * j2), j2);
        }
        if (i2 >= 4) {
            return m210hashLen16nJd8lh8(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(((long) fetch32(bArr, i)) & 4294967295L) << 3) + ULong.constructor-impl(((long) UInt.constructor-impl(i2)) & 4294967295L)), ULong.constructor-impl(((long) fetch32(bArr, (i + i2) - 4)) & 4294967295L), ULong.constructor-impl(k2 + ULong.constructor-impl(ULong.constructor-impl(i2) * ULong.constructor-impl(2L))));
        }
        if (i2 <= 0) {
            return k2;
        }
        byte b = UByte.constructor-impl(bArr[i]);
        byte b2 = UByte.constructor-impl(bArr[i + (i2 >>> 1)]);
        byte b3 = UByte.constructor-impl(bArr[(i + i2) - 1]);
        int i3 = UInt.constructor-impl(UInt.constructor-impl(b & 255) + UInt.constructor-impl(UInt.constructor-impl(b2 & 255) << 8));
        long j4 = ULong.constructor-impl(ULong.constructor-impl(i2) + ULong.constructor-impl(((long) UInt.constructor-impl(UInt.constructor-impl(b3 & 255) << 2)) & 4294967295L));
        long j5 = k2;
        return ULong.constructor-impl(m212shiftMixVKZWuLQ(ULong.constructor-impl(ULong.constructor-impl(j4 * k0) ^ ULong.constructor-impl(ULong.constructor-impl(((long) i3) & 4294967295L) * j5))) * j5);
    }

    /* JADX INFO: renamed from: hashLen16-PWzV0Is, reason: not valid java name */
    private static final long m209hashLen16PWzV0Is(long j, long j2) {
        return m208hash128to64PWzV0Is(j, j2);
    }

    /* JADX INFO: renamed from: hashLen16-nJd8lh8, reason: not valid java name */
    private static final long m210hashLen16nJd8lh8(long j, long j2, long j3) {
        long j4 = ULong.constructor-impl(ULong.constructor-impl(j ^ j2) * j3);
        long j5 = ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j4 ^ ULong.constructor-impl(j4 >>> 47)) ^ j2) * j3);
        return ULong.constructor-impl(ULong.constructor-impl(j5 ^ ULong.constructor-impl(j5 >>> 47)) * j3);
    }

    private static final long hashLen17to32(byte[] bArr, int i, int i2) {
        long j = k2;
        long j2 = ULong.constructor-impl(ULong.constructor-impl(((long) UInt.constructor-impl(i2 * 2)) & 4294967295L) + j);
        long j3 = ULong.constructor-impl(fetch64(bArr, i) * k1);
        long jFetch64 = fetch64(bArr, i + 8);
        int i3 = i + i2;
        long j4 = ULong.constructor-impl(fetch64(bArr, i3 - 8) * j2);
        return m210hashLen16nJd8lh8(ULong.constructor-impl(ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(j3 + jFetch64), 43) + m211rotate4PLdz1A(j4, 30)) + ULong.constructor-impl(fetch64(bArr, i3 - 16) * j)), ULong.constructor-impl(ULong.constructor-impl(j3 + m211rotate4PLdz1A(ULong.constructor-impl(jFetch64 + j), 18)) + j4), j2);
    }

    private static final long hashLen33to64(byte[] bArr, int i, int i2) {
        long j = k2;
        long j2 = ULong.constructor-impl(ULong.constructor-impl(((long) UInt.constructor-impl(i2 * 2)) & 4294967295L) + j);
        long j3 = ULong.constructor-impl(fetch64(bArr, i) * j);
        long jFetch64 = fetch64(bArr, i + 8);
        int i3 = i + i2;
        long jFetch65 = fetch64(bArr, i3 - 24);
        long jFetch66 = fetch64(bArr, i3 - 32);
        long j4 = ULong.constructor-impl(fetch64(bArr, i + 16) * j);
        long j5 = ULong.constructor-impl(fetch64(bArr, i + 24) * ULong.constructor-impl(9L));
        long jFetch67 = fetch64(bArr, i3 - 8);
        long j6 = ULong.constructor-impl(fetch64(bArr, i3 - 16) * j2);
        long j7 = j3 + jFetch67;
        long j8 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(j7), 43) + ULong.constructor-impl(ULong.constructor-impl(m211rotate4PLdz1A(jFetch64, 30) + jFetch65) * ULong.constructor-impl(9L)));
        long j9 = ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j7) ^ jFetch66) + j5) + ULong.constructor-impl(1L));
        long j10 = ULong.constructor-impl(m207bswapVKZWuLQ(ULong.constructor-impl(ULong.constructor-impl(j8 + j9) * j2)) + j6);
        long j11 = j4 + j5;
        long j12 = ULong.constructor-impl(m211rotate4PLdz1A(ULong.constructor-impl(j11), 42) + jFetch65);
        long j13 = ULong.constructor-impl(ULong.constructor-impl(m207bswapVKZWuLQ(ULong.constructor-impl(ULong.constructor-impl(j9 + j10) * j2)) + jFetch67) * j2);
        long j14 = ULong.constructor-impl(ULong.constructor-impl(j11) + jFetch65);
        return ULong.constructor-impl(ULong.constructor-impl(m212shiftMixVKZWuLQ(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j14 + ULong.constructor-impl(m207bswapVKZWuLQ(ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(j12 + j14) * j2) + j13)) + jFetch64)) * j2) + jFetch66) + j6)) * j2) + j12);
    }

    /* JADX INFO: renamed from: rotate-4PLdz1A, reason: not valid java name */
    private static final long m211rotate4PLdz1A(long j, int i) {
        if (i == 0) {
            return j;
        }
        return ULong.constructor-impl(ULong.constructor-impl(j << (64 - i)) | ULong.constructor-impl(j >>> i));
    }

    /* JADX INFO: renamed from: shiftMix-VKZWuLQ, reason: not valid java name */
    private static final long m212shiftMixVKZWuLQ(long j) {
        return ULong.constructor-impl(j ^ ULong.constructor-impl(j >>> 47));
    }

    private static final int toIntLE(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) + ((bArr[i + 2] & 255) << 16) + ((bArr[i + 1] & 255) << 8) + (bArr[i] & 255);
    }

    private static final long toLongLE(byte[] bArr, int i) {
        return (((long) bArr[i + 7]) << 56) + (((long) (bArr[i + 6] & 255)) << 48) + (((long) (bArr[i + 5] & 255)) << 40) + (((long) (bArr[i + 4] & 255)) << 32) + (((long) (bArr[i + 3] & 255)) << 24) + ((long) ((bArr[i + 2] & 255) << 16)) + ((long) ((bArr[i + 1] & 255) << 8)) + ((long) (bArr[i] & 255));
    }

    /* JADX INFO: renamed from: weakHashLen32WithSeeds-8jibXXU, reason: not valid java name */
    private static final long[] m213weakHashLen32WithSeeds8jibXXU(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = ULong.constructor-impl(j5 + j);
        long jM211rotate4PLdz1A = m211rotate4PLdz1A(ULong.constructor-impl(ULong.constructor-impl(j6 + j7) + j4), 21);
        long j8 = ULong.constructor-impl(ULong.constructor-impl(j2 + j7) + j3);
        return new long[]{ULong.constructor-impl(j8 + j4), ULong.constructor-impl(ULong.constructor-impl(jM211rotate4PLdz1A + m211rotate4PLdz1A(j8, 44)) + j7)};
    }

    /* JADX INFO: renamed from: weakHashLen32WithSeeds-RQJlUXk, reason: not valid java name */
    private static final long[] m214weakHashLen32WithSeedsRQJlUXk(byte[] bArr, int i, long j, long j2) {
        return m213weakHashLen32WithSeeds8jibXXU(fetch64(bArr, i), fetch64(bArr, i + 8), fetch64(bArr, i + 16), fetch64(bArr, i + 24), j, j2);
    }

    public static final long cityHash64(String str) {
        str.getClass();
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        bytes.getClass();
        return cityHash64$default(bytes, 0, 0, 6, null);
    }
}
