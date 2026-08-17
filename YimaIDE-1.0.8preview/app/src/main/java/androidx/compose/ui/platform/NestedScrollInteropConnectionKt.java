package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0001H\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0003H\u0002\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a/\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u00020\u0003*\u00020\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0016\u001a\u00020\u0003*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0017\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0007¢\u0006\u0002\u0010!\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0016\u001a\u00020\u0003*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"ceilAwayFromZero", "", "extractIntegerPixels", "", "composeToViewOffset", "offset", "reverseAxis", "toViewVelocity", "toOffset", "Landroidx/compose/ui/geometry/Offset;", "dx", "dy", "consumed", "", "available", "toOffset-moWRBKg", "(II[IJ)J", "toViewType", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "toViewType-GyEprt8", "(I)I", "ScrollingAxesThreshold", "scrollAxes", "getScrollAxes-k-4lQ0M", "(J)I", "Landroidx/compose/ui/unit/Velocity;", "minFlingVelocity", "scrollAxes-sF-c-tU", "(JF)I", "rememberNestedScrollInteropConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "hostView", "Landroid/view/View;", "(Landroid/view/View;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NestedScrollInteropConnectionKt {
    private static final float ScrollingAxesThreshold = 0.5f;

    private static final float ceilAwayFromZero(float f) {
        return (float) (f >= 0.0f ? Math.ceil(f) : Math.floor(f));
    }

    public static final int composeToViewOffset(float f) {
        return (ComposeUiFlags.isNestedScrollInteropIntegerPropagationEnabled ? extractIntegerPixels(f) : (int) ceilAwayFromZero(f)) * (-1);
    }

    private static final int extractIntegerPixels(float f) {
        return MathKt.roundToInt(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getScrollAxes-k-4lQ0M, reason: not valid java name */
    public static final int m5202getScrollAxesk4lQ0M(long j) {
        int i = Math.abs(Float.intBitsToFloat((int) (j >> 32))) >= 0.5f ? 1 : 0;
        return Math.abs(Float.intBitsToFloat((int) (j & 4294967295L))) >= 0.5f ? i | 2 : i;
    }

    public static final NestedScrollConnection rememberNestedScrollInteropConnection(View view, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            view = (View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView());
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1075877987, i, -1, "androidx.compose.ui.platform.rememberNestedScrollInteropConnection (NestedScrollInteropConnection.android.kt:292)");
        }
        ViewConfiguration viewConfiguration = (ViewConfiguration) composer.consume(CompositionLocalsKt.getLocalViewConfiguration());
        boolean zChanged = composer.changed(view) | composer.changed(viewConfiguration);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new NestedScrollInteropConnection(view, viewConfiguration.getMinimumFlingVelocity());
            composer.updateRememberedValue(objRememberedValue);
        }
        NestedScrollInteropConnection nestedScrollInteropConnection = (NestedScrollInteropConnection) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return nestedScrollInteropConnection;
    }

    private static final float reverseAxis(int i) {
        return i * (-1.0f);
    }

    /* JADX INFO: renamed from: scrollAxes-sF-c-tU, reason: not valid java name */
    private static final int m5203scrollAxessFctU(long j, float f) {
        int i = Math.abs(Velocity.m6260getXimpl(j)) >= f ? 1 : 0;
        return Math.abs(Velocity.m6261getYimpl(j)) >= f ? i | 2 : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toOffset-moWRBKg, reason: not valid java name */
    public static final long m5204toOffsetmoWRBKg(int i, int i2, int[] iArr, long j) {
        float fIntBitsToFloat = (!ComposeUiFlags.isNestedScrollInteropIntegerPropagationEnabled || Math.abs(iArr[0]) == 0) ? 0.0f : Float.intBitsToFloat((int) (j >> 32)) - reverseAxis(i);
        float fIntBitsToFloat2 = (!ComposeUiFlags.isNestedScrollInteropIntegerPropagationEnabled || Math.abs(iArr[1]) == 0) ? 0.0f : Float.intBitsToFloat((int) (j & 4294967295L)) - reverseAxis(i2);
        int i3 = (int) (j >> 32);
        int i4 = (int) (j & 4294967295L);
        return Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(Float.intBitsToFloat(i3) >= 0.0f ? RangesKt.coerceAtMost(reverseAxis(iArr[0]) + fIntBitsToFloat, Float.intBitsToFloat(i3)) : RangesKt.coerceAtLeast(reverseAxis(iArr[0]) + fIntBitsToFloat, Float.intBitsToFloat(i3)))) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat(i4) >= 0.0f ? RangesKt.coerceAtMost(reverseAxis(iArr[1]) + fIntBitsToFloat2, Float.intBitsToFloat(i4)) : RangesKt.coerceAtLeast(reverseAxis(iArr[1]) + fIntBitsToFloat2, Float.intBitsToFloat(i4)))) & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toViewType-GyEprt8, reason: not valid java name */
    public static final int m5205toViewTypeGyEprt8(int i) {
        return !NestedScrollSource.m4324equalsimpl0(i, NestedScrollSource.INSTANCE.m4336getUserInputWNlRxjI()) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float toViewVelocity(float f) {
        return f * (-1.0f);
    }
}
