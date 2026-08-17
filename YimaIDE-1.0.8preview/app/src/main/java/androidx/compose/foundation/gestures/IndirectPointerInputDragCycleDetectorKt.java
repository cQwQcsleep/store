package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerEventPrimaryDirectionalMotionAxis;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0003¢\u0006\u0004\b\u0007\u0010\b\u001a'\u0010\t\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0003¢\u0006\u0004\b\n\u0010\b\u001a\f\u0010\u000b\u001a\u00020\f*\u00020\u0002H\u0003\u001a\f\u0010\r\u001a\u00020\f*\u00020\u0002H\u0003\u001a\f\u0010\u000e\u001a\u00020\f*\u00020\u0002H\u0003\u001a1\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0010\u001a\u00020\fH\u0003¢\u0006\u0004\b\u0011\u0010\u0012\u001a'\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0014\u0010\b\u001a'\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0015\u0010\u0016\u001a'\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0018\u0010\b\u001a?\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0001H\u0002¢\u0006\u0004\b \u0010!\"\u000e\u0010\"\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"positionChange", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "positionChange-_bfSUIo", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;)J", "positionChangeIgnoreConsumed", "positionChangeIgnoreConsumed-_bfSUIo", "changedToUpIgnoreConsumed", "", "changedToDown", "changedToDownIgnoreConsumed", "positionChangeInternal", "ignoreConsumed", "positionChangeInternal-wfG_k4k", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;Z)J", "primaryAxisPosition", "primaryAxisPosition-_bfSUIo", "primaryAxisPosition-grjNGvw", "(JLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;)J", "primaryAxisPreviousPosition", "primaryAxisPreviousPosition-_bfSUIo", "addIndirectPointerInputChange", "", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "event", "smoother", "Landroidx/compose/foundation/gestures/IndirectPointerInputEventSmoother;", "nodeOffset", "addIndirectPointerInputChange-Qf4Zb88", "(Landroidx/compose/ui/input/pointer/util/VelocityTracker;Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;Landroidx/compose/foundation/gestures/IndirectPointerInputEventSmoother;J)V", "SmoothingFactor", "", "PixelSensibility", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class IndirectPointerInputDragCycleDetectorKt {
    private static final int PixelSensibility = 2;
    private static final int SmoothingFactor = 3;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addIndirectPointerInputChange-Qf4Zb88, reason: not valid java name */
    public static final void m613addIndirectPointerInputChangeQf4Zb88(VelocityTracker velocityTracker, IndirectPointerInputChange indirectPointerInputChange, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis, IndirectPointerInputEventSmoother indirectPointerInputEventSmoother, long j) {
        velocityTracker.addPosition-Uv8p0NA(indirectPointerInputChange.getUptimeMillis(), Offset.plus-MK-Hz9U(m619primaryAxisPositiongrjNGvw(indirectPointerInputEventSmoother.m621smoothEventPositiontuRUvjQ(indirectPointerInputChange), orientation, indirectPointerEventPrimaryDirectionalMotionAxis), j));
    }

    private static final boolean changedToDown(IndirectPointerInputChange indirectPointerInputChange) {
        return (indirectPointerInputChange.isConsumed() || indirectPointerInputChange.getPreviousPressed() || !indirectPointerInputChange.getPressed()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean changedToDownIgnoreConsumed(IndirectPointerInputChange indirectPointerInputChange) {
        return !indirectPointerInputChange.getPreviousPressed() && indirectPointerInputChange.getPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean changedToUpIgnoreConsumed(IndirectPointerInputChange indirectPointerInputChange) {
        return indirectPointerInputChange.getPreviousPressed() && !indirectPointerInputChange.getPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: positionChange-_bfSUIo, reason: not valid java name */
    public static final long m614positionChange_bfSUIo(IndirectPointerInputChange indirectPointerInputChange, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis) {
        return m616positionChangeInternalwfG_k4k(indirectPointerInputChange, orientation, indirectPointerEventPrimaryDirectionalMotionAxis, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: positionChangeIgnoreConsumed-_bfSUIo, reason: not valid java name */
    public static final long m615positionChangeIgnoreConsumed_bfSUIo(IndirectPointerInputChange indirectPointerInputChange, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis) {
        return m616positionChangeInternalwfG_k4k(indirectPointerInputChange, orientation, indirectPointerEventPrimaryDirectionalMotionAxis, true);
    }

    /* JADX INFO: renamed from: positionChangeInternal-wfG_k4k, reason: not valid java name */
    private static final long m616positionChangeInternalwfG_k4k(IndirectPointerInputChange indirectPointerInputChange, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis, boolean z) {
        return (z || !indirectPointerInputChange.isConsumed()) ? Offset.minus-MK-Hz9U(m618primaryAxisPosition_bfSUIo(indirectPointerInputChange, orientation, indirectPointerEventPrimaryDirectionalMotionAxis), m620primaryAxisPreviousPosition_bfSUIo(indirectPointerInputChange, orientation, indirectPointerEventPrimaryDirectionalMotionAxis)) : Offset.Companion.getZero-F1C5BW0();
    }

    /* JADX INFO: renamed from: positionChangeInternal-wfG_k4k$default, reason: not valid java name */
    public static /* synthetic */ long m617positionChangeInternalwfG_k4k$default(IndirectPointerInputChange indirectPointerInputChange, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return m616positionChangeInternalwfG_k4k(indirectPointerInputChange, orientation, indirectPointerEventPrimaryDirectionalMotionAxis, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: primaryAxisPosition-_bfSUIo, reason: not valid java name */
    public static final long m618primaryAxisPosition_bfSUIo(IndirectPointerInputChange indirectPointerInputChange, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis) {
        float fIntBitsToFloat;
        if (orientation == null) {
            return indirectPointerInputChange.getPosition-F1C5BW0();
        }
        IndirectPointerEventPrimaryDirectionalMotionAxis.Companion companion = IndirectPointerEventPrimaryDirectionalMotionAxis.Companion;
        if (indirectPointerEventPrimaryDirectionalMotionAxis == null ? false : IndirectPointerEventPrimaryDirectionalMotionAxis.equals-impl0(indirectPointerEventPrimaryDirectionalMotionAxis.unbox-impl(), companion.getX-nZO2Niw())) {
            fIntBitsToFloat = Float.intBitsToFloat((int) (indirectPointerInputChange.getPosition-F1C5BW0() >> 32));
        } else {
            if (!(indirectPointerEventPrimaryDirectionalMotionAxis != null ? IndirectPointerEventPrimaryDirectionalMotionAxis.equals-impl0(indirectPointerEventPrimaryDirectionalMotionAxis.unbox-impl(), companion.getY-nZO2Niw()) : false)) {
                return indirectPointerInputChange.getPosition-F1C5BW0();
            }
            fIntBitsToFloat = Float.intBitsToFloat((int) (indirectPointerInputChange.getPosition-F1C5BW0() & 4294967295L));
        }
        if (orientation == Orientation.Horizontal) {
            return Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
        }
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat)) & 4294967295L));
    }

    /* JADX INFO: renamed from: primaryAxisPosition-grjNGvw, reason: not valid java name */
    private static final long m619primaryAxisPositiongrjNGvw(long j, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis) {
        float fIntBitsToFloat;
        if (orientation == null) {
            return j;
        }
        IndirectPointerEventPrimaryDirectionalMotionAxis.Companion companion = IndirectPointerEventPrimaryDirectionalMotionAxis.Companion;
        if (indirectPointerEventPrimaryDirectionalMotionAxis == null ? false : IndirectPointerEventPrimaryDirectionalMotionAxis.equals-impl0(indirectPointerEventPrimaryDirectionalMotionAxis.unbox-impl(), companion.getX-nZO2Niw())) {
            fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        } else {
            if (!(indirectPointerEventPrimaryDirectionalMotionAxis != null ? IndirectPointerEventPrimaryDirectionalMotionAxis.equals-impl0(indirectPointerEventPrimaryDirectionalMotionAxis.unbox-impl(), companion.getY-nZO2Niw()) : false)) {
                return j;
            }
            fIntBitsToFloat = Float.intBitsToFloat((int) (j & 4294967295L));
        }
        if (orientation == Orientation.Horizontal) {
            return Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
        }
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat)) & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: primaryAxisPreviousPosition-_bfSUIo, reason: not valid java name */
    public static final long m620primaryAxisPreviousPosition_bfSUIo(IndirectPointerInputChange indirectPointerInputChange, Orientation orientation, IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis) {
        float fIntBitsToFloat;
        if (orientation == null) {
            return indirectPointerInputChange.getPreviousPosition-F1C5BW0();
        }
        IndirectPointerEventPrimaryDirectionalMotionAxis.Companion companion = IndirectPointerEventPrimaryDirectionalMotionAxis.Companion;
        if (indirectPointerEventPrimaryDirectionalMotionAxis == null ? false : IndirectPointerEventPrimaryDirectionalMotionAxis.equals-impl0(indirectPointerEventPrimaryDirectionalMotionAxis.unbox-impl(), companion.getX-nZO2Niw())) {
            fIntBitsToFloat = Float.intBitsToFloat((int) (indirectPointerInputChange.getPreviousPosition-F1C5BW0() >> 32));
        } else {
            if (!(indirectPointerEventPrimaryDirectionalMotionAxis != null ? IndirectPointerEventPrimaryDirectionalMotionAxis.equals-impl0(indirectPointerEventPrimaryDirectionalMotionAxis.unbox-impl(), companion.getY-nZO2Niw()) : false)) {
                return indirectPointerInputChange.getPreviousPosition-F1C5BW0();
            }
            fIntBitsToFloat = Float.intBitsToFloat((int) (indirectPointerInputChange.getPreviousPosition-F1C5BW0() & 4294967295L));
        }
        if (orientation == Orientation.Horizontal) {
            return Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
        }
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat)) & 4294967295L));
    }
}
