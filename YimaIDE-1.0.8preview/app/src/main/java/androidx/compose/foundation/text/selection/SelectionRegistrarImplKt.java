package androidx.compose.foundation.text.selection;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"inARow", "", "boxATopLeft", "Landroidx/compose/ui/geometry/Offset;", "boxABottomRight", "boxBTopLeft", "boxBBottomRight", "inARow-zwwh4xc", "(JJJJ)Z", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectionRegistrarImplKt {
    /* JADX INFO: renamed from: inARow-zwwh4xc, reason: not valid java name */
    public static final boolean m1801inARowzwwh4xc(long j, long j2, long j3, long j4) {
        int i = (int) (j2 & 4294967295L);
        int i2 = (int) (j & 4294967295L);
        float fIntBitsToFloat = Float.intBitsToFloat(i) - Float.intBitsToFloat(i2);
        int i3 = (int) (j2 >> 32);
        int i4 = (int) (j >> 32);
        float fIntBitsToFloat2 = Float.intBitsToFloat(i3) - Float.intBitsToFloat(i4);
        int i5 = (int) (j4 & 4294967295L);
        int i6 = (int) (4294967295L & j3);
        float fIntBitsToFloat3 = Float.intBitsToFloat(i5) - Float.intBitsToFloat(i6);
        int i7 = (int) (j4 >> 32);
        int i8 = (int) (j3 >> 32);
        float fIntBitsToFloat4 = Float.intBitsToFloat(i7) - Float.intBitsToFloat(i8);
        float fMax = Math.max(0.0f, Math.min(Float.intBitsToFloat(i), Float.intBitsToFloat(i5)) - Math.max(Float.intBitsToFloat(i2), Float.intBitsToFloat(i6)));
        float fMax2 = Math.max(0.0f, Math.min(Float.intBitsToFloat(i3), Float.intBitsToFloat(i7)) - Math.max(Float.intBitsToFloat(i4), Float.intBitsToFloat(i8)));
        return ((fMax > (fIntBitsToFloat * 0.5f) ? 1 : (fMax == (fIntBitsToFloat * 0.5f) ? 0 : -1)) >= 0 || (fMax > (fIntBitsToFloat3 * 0.5f) ? 1 : (fMax == (fIntBitsToFloat3 * 0.5f) ? 0 : -1)) >= 0) && ((fMax2 > (fIntBitsToFloat2 * 0.5f) ? 1 : (fMax2 == (fIntBitsToFloat2 * 0.5f) ? 0 : -1)) < 0 && (fMax2 > (fIntBitsToFloat4 * 0.5f) ? 1 : (fMax2 == (fIntBitsToFloat4 * 0.5f) ? 0 : -1)) < 0);
    }
}
