package androidx.compose.ui.geometry;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0005\u001a\"\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0086\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u001c\u0010\u001a\u001a\u00020\u0001*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001c\u0010\u001a\u001a\u00020\u0001*\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\u001d\u0010 \u001a\u001c\u0010\u001a\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\u001d\u0010!\u001a\u0013\u0010\"\u001a\u00020#*\u00020\u0001H\u0007¢\u0006\u0004\b$\u0010%\"\u001f\u0010\u0006\u001a\u00020\u0007*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u001f\u0010\f\u001a\u00020\u0007*\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000b\"\u001e\u0010&\u001a\u00020'*\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b(\u0010\t\u001a\u0004\b)\u0010*¨\u0006+"}, d2 = {"Size", "Landroidx/compose/ui/geometry/Size;", "width", "", "height", "(FF)J", "isSpecified", "", "isSpecified-uvyYCjk$annotations", "(J)V", "isSpecified-uvyYCjk", "(J)Z", "isUnspecified", "isUnspecified-uvyYCjk$annotations", "isUnspecified-uvyYCjk", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-TmRCtEA", "(JLkotlin/jvm/functions/Function0;)J", "lerp", "start", "stop", "fraction", "lerp-VgWVRYQ", "(JJF)J", "times", "", "size", "times-d16Qtg0", "(IJ)J", "", "(DJ)J", "(FJ)J", "toRect", "Landroidx/compose/ui/geometry/Rect;", "toRect-uvyYCjk", "(J)Landroidx/compose/ui/geometry/Rect;", "center", "Landroidx/compose/ui/geometry/Offset;", "getCenter-uvyYCjk$annotations", "getCenter-uvyYCjk", "(J)J", "ui-geometry"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SizeKt {
    public static final long Size(float f, float f2) {
        return Size.m2949constructorimpl((((long) Float.floatToRawIntBits(f2)) & 4294967295L) | (Float.floatToRawIntBits(f) << 32));
    }

    /* JADX INFO: renamed from: getCenter-uvyYCjk, reason: not valid java name */
    public static final long m2968getCenteruvyYCjk(long j) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) / 2.0f;
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)) / 2.0f)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32));
    }

    /* JADX INFO: renamed from: getCenter-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m2969getCenteruvyYCjk$annotations(long j) {
    }

    /* JADX INFO: renamed from: isSpecified-uvyYCjk, reason: not valid java name */
    public static final boolean m2970isSpecifieduvyYCjk(long j) {
        return j != InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: renamed from: isSpecified-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m2971isSpecifieduvyYCjk$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-uvyYCjk, reason: not valid java name */
    public static final boolean m2972isUnspecifieduvyYCjk(long j) {
        return j == InlineClassHelperKt.UnspecifiedPackedFloats;
    }

    /* JADX INFO: renamed from: isUnspecified-uvyYCjk$annotations, reason: not valid java name */
    public static /* synthetic */ void m2973isUnspecifieduvyYCjk$annotations(long j) {
    }

    /* JADX INFO: renamed from: lerp-VgWVRYQ, reason: not valid java name */
    public static final long m2974lerpVgWVRYQ(long j, long j2, float f) {
        float fLerp = MathHelpersKt.lerp(Float.intBitsToFloat((int) (j >> 32)), Float.intBitsToFloat((int) (j2 >> 32)), f);
        float fLerp2 = MathHelpersKt.lerp(Float.intBitsToFloat((int) (j & 4294967295L)), Float.intBitsToFloat((int) (j2 & 4294967295L)), f);
        return Size.m2949constructorimpl((((long) Float.floatToRawIntBits(fLerp)) << 32) | (((long) Float.floatToRawIntBits(fLerp2)) & 4294967295L));
    }

    /* JADX INFO: renamed from: takeOrElse-TmRCtEA, reason: not valid java name */
    public static final long m2975takeOrElseTmRCtEA(long j, Function0<Size> function0) {
        return j != InlineClassHelperKt.UnspecifiedPackedFloats ? j : ((Size) function0.invoke()).m2963unboximpl();
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m2978timesd16Qtg0(int i, long j) {
        return Size.m2961times7Ah8Wj8(j, i);
    }

    /* JADX INFO: renamed from: toRect-uvyYCjk, reason: not valid java name */
    public static final Rect m2979toRectuvyYCjk(long j) {
        return RectKt.m2929Recttz77jQw(Offset.INSTANCE.m2905getZeroF1C5BW0(), j);
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m2976timesd16Qtg0(double d, long j) {
        return Size.m2961times7Ah8Wj8(j, (float) d);
    }

    /* JADX INFO: renamed from: times-d16Qtg0, reason: not valid java name */
    public static final long m2977timesd16Qtg0(float f, long j) {
        return Size.m2961times7Ah8Wj8(j, f);
    }
}
