package androidx.compose.ui.spatial;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ULong;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b-\u001a\u0019\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0080\b\u001aA\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020#H\u0080\b\u001a\u0011\u0010'\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u0010)\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u0010*\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0019\u0010+\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0001H\u0080\b\u001a\u0011\u0010,\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u0010-\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a!\u0010.\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0080\b\u001a\u0019\u0010/\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u0001H\u0080\b\u001a\u0011\u00100\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00101\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00102\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00103\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00104\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000eH\u0080\b\u001a!\u00105\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010&\u001a\u00020#H\u0080\b\u001a\u0011\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u00020\u000eH\u0080\b\u001a\u0011\u00108\u001a\u00020\u00012\u0006\u00107\u001a\u00020\u000eH\u0080\b\u001a)\u00109\u001a\u00020#2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u000eH\u0080\b\u001a\r\u0010>\u001a\u00020\u000e*\u00020#H\u0080\b\u001aP\u0010?\u001a\u00020\u00012\u0006\u0010@\u001a\u00020\u00012\u0006\u0010A\u001a\u00020\u00012\u0006\u0010B\u001a\u00020\u00012\u0006\u0010C\u001a\u00020\u00012\u0006\u0010D\u001a\u00020\u00012\u0006\u0010E\u001a\u00020\u00012\u0006\u0010F\u001a\u00020\u00012\u0006\u0010G\u001a\u00020\u00012\u0006\u0010H\u001a\u00020\u0001H\u0000\u001a8\u0010I\u001a\u00020\u00012\u0006\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u00012\u0006\u0010M\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\u00012\u0006\u0010O\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010\r\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0014\u0010\u0011\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\"\u000e\u0010\u0013\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0015\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"LongsPerItem", "", "InitialSize", "Lower25Bits", "Lower10Bits", "MaxSupportedId", "MaxSupportedLastChildOffset", "BitOffsetForParentId", "BitOffsetForLastChildOffset", "BitOffsetForUpdated", "BitOffsetForFocusable", "BitOffsetForGesturable", "BitOffsetForHasCallbacks", "EverythingButLastChildOffset", "", "getEverythingButLastChildOffset", "()J", "EverythingButParentId", "getEverythingButParentId", "PackedIntsLowestBit", "PackedIntsHighestBit", "TombStone", "getTombStone", "AxisNorth", "AxisSouth", "AxisWest", "AxisEast", "packXY", "x", "y", "packMeta", "itemId", "parentId", "lastChildOffset", "updated", "", "focusable", "gesturable", "hasCallbacks", "unpackMetaValue", "meta", "unpackMetaParentId", "unpackMetaLastChildOffset", "metaWithParentId", "metaMarkUpdated", "metaUnMarkUpdated", "metaMarkFlags", "metaWithLastChildOffset", "unpackMetaFocusable", "unpackMetaGesturable", "unpackMetaUpdated", "unpackMetaHasCallbacks", "metaMarkUpdatedIfHasCallbacks", "metaMarkUpdatedAndHasCallbacks", "unpackX", "xy", "unpackY", "rectIntersectsRect", "srcLT", "srcRB", "destLT", "destRB", "toLong", "distanceScore", "axis", "queryL", "queryT", "queryR", "queryB", "l", "t", "r", "b", "distanceScoreAlongAxis", "distanceMin", "distanceMax", "queryCrossAxisMax", "queryCrossAxisMin", "crossAxisMax", "crossAxisMin", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class RectListKt {
    public static final int AxisEast = 3;
    public static final int AxisNorth = 0;
    public static final int AxisSouth = 1;
    public static final int AxisWest = 2;
    public static final int BitOffsetForFocusable = 61;
    public static final int BitOffsetForGesturable = 62;
    public static final int BitOffsetForHasCallbacks = 63;
    public static final int BitOffsetForLastChildOffset = 50;
    public static final int BitOffsetForParentId = 25;
    public static final int BitOffsetForUpdated = 60;
    public static final int InitialSize = 64;
    public static final int LongsPerItem = 3;
    public static final int Lower10Bits = 1023;
    private static final int Lower25Bits = 33554431;
    private static final int MaxSupportedId = 33554431;
    public static final int MaxSupportedLastChildOffset = 1023;
    private static final long PackedIntsHighestBit = -9223372034707292160L;
    private static final long PackedIntsLowestBit = 4294967297L;
    private static final long EverythingButLastChildOffset = ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(1023) << 50) ^ (-1));
    private static final long EverythingButParentId = ULong.constructor-impl(ULong.constructor-impl(ULong.constructor-impl(33554431) << 25) ^ (-1));
    private static final long TombStone = (((long) Math.min(0, 1023)) << 50) | 1125899906842623L;

    public static final int distanceScore(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (i == 0) {
            return distanceScoreAlongAxis(i3, i9, i4, i2, i8, i6);
        }
        if (i == 1) {
            return distanceScoreAlongAxis(i7, i5, i4, i2, i8, i6);
        }
        if (i == 2) {
            return distanceScoreAlongAxis(i2, i8, i5, i3, i9, i7);
        }
        if (i != 3) {
            return Integer.MAX_VALUE;
        }
        return distanceScoreAlongAxis(i6, i4, i5, i3, i9, i7);
    }

    public static final int distanceScoreAlongAxis(int i, int i2, int i3, int i4, int i5, int i6) {
        return ((i - i2) + 1) * ((((i3 - i4) + Math.max(i4, i6)) - Math.min(i3, i5)) + 1);
    }

    public static final long getEverythingButLastChildOffset() {
        return EverythingButLastChildOffset;
    }

    public static final long getEverythingButParentId() {
        return EverythingButParentId;
    }

    public static final long getTombStone() {
        return TombStone;
    }

    public static final long metaMarkFlags(long j, boolean z, boolean z2) {
        return (j & (-6917529027641081857L)) | ((z ? 1L : 0L) * 2305843009213693952L) | ((z2 ? 1L : 0L) * 4611686018427387904L);
    }

    public static final long metaMarkUpdated(long j) {
        return j | 1152921504606846976L;
    }

    public static final long metaMarkUpdatedAndHasCallbacks(long j, boolean z, boolean z2) {
        return (j & 8070450532247928831L) | ((z ? 1L : 0L) * 1152921504606846976L) | ((z2 ? 1L : 0L) * Long.MIN_VALUE);
    }

    public static final long metaMarkUpdatedIfHasCallbacks(long j) {
        return j | (((j >> 63) & 1) << 60);
    }

    public static final long metaUnMarkUpdated(long j) {
        return j & (-1152921504606846977L);
    }

    public static final long metaWithLastChildOffset(long j, int i) {
        return (j & getEverythingButLastChildOffset()) | (((long) Math.min(i, 1023)) << 50);
    }

    public static final long metaWithParentId(long j, int i) {
        return (j & getEverythingButParentId()) | (((long) (i & 33554431)) << 25);
    }

    public static final long packMeta(int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4) {
        return ((z3 ? 1L : 0L) << 62) | ((z4 ? 1L : 0L) << 63) | ((z2 ? 1L : 0L) << 61) | ((z ? 1L : 0L) << 60) | (((long) Math.min(i3, 1023)) << 50) | (((long) (i2 & 33554431)) << 25) | ((long) (i & 33554431));
    }

    public static final long packXY(int i, int i2) {
        return (((long) i2) & 4294967295L) | (((long) i) << 32);
    }

    public static final boolean rectIntersectsRect(long j, long j2, long j3, long j4) {
        return ((((j4 - j) - 4294967297L) | ((j2 - j3) - 4294967297L)) & (-9223372034707292160L)) == 0;
    }

    public static final long toLong(boolean z) {
        return z ? 1L : 0L;
    }

    public static final int unpackMetaFocusable(long j) {
        return ((int) (j >> 61)) & 1;
    }

    public static final int unpackMetaGesturable(long j) {
        return ((int) (j >> 62)) & 1;
    }

    public static final int unpackMetaHasCallbacks(long j) {
        return ((int) (j >> 63)) & 1;
    }

    public static final int unpackMetaLastChildOffset(long j) {
        return ((int) (j >> 50)) & 1023;
    }

    public static final int unpackMetaParentId(long j) {
        return ((int) (j >> 25)) & 33554431;
    }

    public static final int unpackMetaUpdated(long j) {
        return ((int) (j >> 60)) & 1;
    }

    public static final int unpackMetaValue(long j) {
        return ((int) j) & 33554431;
    }

    public static final int unpackX(long j) {
        return (int) (j >> 32);
    }

    public static final int unpackY(long j) {
        return (int) j;
    }
}
