package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0005\u001a'\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u0014\u0010\r\u001a\u00020\u000e*\u00020\u0001H\u0087\b¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001c\u0010\u0011\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0001H\u0087\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001c\u0010\u0015\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0001H\u0087\u0002¢\u0006\u0004\b\u0016\u0010\u0014\u001a\u001c\u0010\u0011\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u000eH\u0087\u0002¢\u0006\u0004\b\u0017\u0010\u0014\u001a\u001c\u0010\u0015\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u000eH\u0087\u0002¢\u0006\u0004\b\u0018\u0010\u0014\u001a\u0013\u0010\u0019\u001a\u00020\u0001*\u00020\u000eH\u0007¢\u0006\u0004\b\u001a\u0010\u0010¨\u0006\u001b"}, d2 = {"IntOffset", "Landroidx/compose/ui/unit/IntOffset;", "x", "", "y", "(II)J", "lerp", "start", "stop", "fraction", "", "lerp-81ZRxRo", "(JJF)J", "toOffset", "Landroidx/compose/ui/geometry/Offset;", "toOffset--gyyYBs", "(J)J", "plus", "offset", "plus-Nv-tHpc", "(JJ)J", "minus", "minus-Nv-tHpc", "plus-oCl6YwE", "minus-oCl6YwE", "round", "round-k-4lQ0M", "ui-unit"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class IntOffsetKt {
    public static final long IntOffset(int i, int i2) {
        return IntOffset.m6144constructorimpl((((long) i2) & 4294967295L) | (((long) i) << 32));
    }

    /* JADX INFO: renamed from: lerp-81ZRxRo, reason: not valid java name */
    public static final long m6162lerp81ZRxRo(long j, long j2, float f) {
        return IntOffset.m6144constructorimpl((((long) MathHelpersKt.lerp(IntOffset.m6150getXimpl(j), IntOffset.m6150getXimpl(j2), f)) << 32) | (((long) MathHelpersKt.lerp(IntOffset.m6151getYimpl(j), IntOffset.m6151getYimpl(j2), f)) & 4294967295L));
    }

    /* JADX INFO: renamed from: minus-Nv-tHpc, reason: not valid java name */
    public static final long m6163minusNvtHpc(long j, long j2) {
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j >> 32)) - IntOffset.m6150getXimpl(j2))) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)) - IntOffset.m6151getYimpl(j2))) & 4294967295L));
    }

    /* JADX INFO: renamed from: minus-oCl6YwE, reason: not valid java name */
    public static final long m6164minusoCl6YwE(long j, long j2) {
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m6150getXimpl(j) - Float.intBitsToFloat((int) (j2 >> 32)))) << 32) | (((long) Float.floatToRawIntBits(IntOffset.m6151getYimpl(j) - Float.intBitsToFloat((int) (j2 & 4294967295L)))) & 4294967295L));
    }

    /* JADX INFO: renamed from: plus-Nv-tHpc, reason: not valid java name */
    public static final long m6165plusNvtHpc(long j, long j2) {
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j >> 32)) + IntOffset.m6150getXimpl(j2))) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)) + IntOffset.m6151getYimpl(j2))) & 4294967295L));
    }

    /* JADX INFO: renamed from: plus-oCl6YwE, reason: not valid java name */
    public static final long m6166plusoCl6YwE(long j, long j2) {
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m6150getXimpl(j) + Float.intBitsToFloat((int) (j2 >> 32)))) << 32) | (((long) Float.floatToRawIntBits(IntOffset.m6151getYimpl(j) + Float.intBitsToFloat((int) (j2 & 4294967295L)))) & 4294967295L));
    }

    /* JADX INFO: renamed from: round-k-4lQ0M, reason: not valid java name */
    public static final long m6167roundk4lQ0M(long j) {
        int iRound = Math.round(Float.intBitsToFloat((int) (j >> 32)));
        return IntOffset.m6144constructorimpl((((long) Math.round(Float.intBitsToFloat((int) (j & 4294967295L)))) & 4294967295L) | (((long) iRound) << 32));
    }

    /* JADX INFO: renamed from: toOffset--gyyYBs, reason: not valid java name */
    public static final long m6168toOffsetgyyYBs(long j) {
        float fM6150getXimpl = IntOffset.m6150getXimpl(j);
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(IntOffset.m6151getYimpl(j))) & 4294967295L) | (Float.floatToRawIntBits(fM6150getXimpl) << 32));
    }
}
