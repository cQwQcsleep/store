package androidx.compose.ui.input.indirect;

import android.view.InputDevice;
import android.view.MotionEvent;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001a-\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\u0010\u0010\u001a\u0015\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010\u0012\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"nativeEvent", "Landroid/view/MotionEvent;", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "getNativeEvent", "(Landroidx/compose/ui/input/indirect/IndirectPointerEvent;)Landroid/view/MotionEvent;", "IndirectPointerEvent", "motionEvent", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "previousMotionEvent", "IndirectPointerEvent-eAXfkT4", "(Landroid/view/MotionEvent;ILandroid/view/MotionEvent;)Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "convertActionToIndirectPointerEventType", "Landroidx/compose/ui/input/indirect/IndirectPointerEventType;", "actionMasked", "", "(I)I", "indirectPrimaryDirectionalScrollAxis", "(Landroid/view/MotionEvent;)I", "RATIO_CUTOFF", "", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidIndirectPointerEvent_androidKt {
    private static final float RATIO_CUTOFF = 5.0f;

    /* JADX INFO: renamed from: IndirectPointerEvent-eAXfkT4, reason: not valid java name */
    public static final IndirectPointerEvent m3956IndirectPointerEventeAXfkT4(MotionEvent motionEvent, int i, MotionEvent motionEvent2) {
        int actionIndex;
        long j;
        long eventTime;
        long jM2881constructorimpl;
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 1) {
            actionIndex = actionMasked != 6 ? -1 : motionEvent.getActionIndex();
        } else {
            actionIndex = 0;
        }
        Integer numValueOf = motionEvent2 != null ? Integer.valueOf(motionEvent2.getActionMasked()) : null;
        boolean z2 = (numValueOf != null && numValueOf.intValue() == 0) || (numValueOf != null && numValueOf.intValue() == 5) || (numValueOf != null && numValueOf.intValue() == 2);
        long eventTime2 = motionEvent.getEventTime();
        int pointerCount = motionEvent.getPointerCount();
        ArrayList arrayList = new ArrayList(pointerCount);
        int i2 = 0;
        while (i2 < pointerCount) {
            int pointerId = motionEvent.getPointerId(i2);
            long jM4436constructorimpl = PointerId.m4436constructorimpl(pointerId);
            long jM2881constructorimpl2 = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(motionEvent.getX(i2))) << 32) | (((long) Float.floatToRawIntBits(motionEvent.getY(i2))) & 4294967295L));
            actionIndex = actionIndex;
            boolean z3 = i2 != actionIndex;
            int iFindPointerIndex = motionEvent2 != null ? motionEvent2.findPointerIndex(pointerId) : -1;
            if (iFindPointerIndex >= 0) {
                motionEvent2.getClass();
                j = jM2881constructorimpl2;
                z = z2;
                eventTime = motionEvent2.getEventTime();
                jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(motionEvent2.getX(iFindPointerIndex))) << 32) | (((long) Float.floatToRawIntBits(motionEvent2.getY(iFindPointerIndex))) & 4294967295L));
            } else {
                j = jM2881constructorimpl2;
                eventTime = eventTime2;
                jM2881constructorimpl = j;
                z = false;
            }
            int i3 = pointerCount;
            arrayList.add(new IndirectPointerInputChange(jM4436constructorimpl, eventTime2, j, z3, motionEvent.getPressure(i2), eventTime, jM2881constructorimpl, z, null));
            i2++;
            pointerCount = i3;
        }
        return new AndroidIndirectPointerEvent(arrayList, convertActionToIndirectPointerEventType(actionMasked), i, motionEvent, null);
    }

    /* JADX INFO: renamed from: IndirectPointerEvent-eAXfkT4$default, reason: not valid java name */
    public static /* synthetic */ IndirectPointerEvent m3957IndirectPointerEventeAXfkT4$default(MotionEvent motionEvent, int i, MotionEvent motionEvent2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m3965getNonenZO2Niw();
        }
        if ((i2 & 4) != 0) {
            motionEvent2 = null;
        }
        return m3956IndirectPointerEventeAXfkT4(motionEvent, i, motionEvent2);
    }

    public static final int convertActionToIndirectPointerEventType(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    return IndirectPointerEventType.INSTANCE.m3975getMove4ZHQPSE();
                }
                if (i != 5) {
                    if (i != 6) {
                        return IndirectPointerEventType.INSTANCE.m3978getUnknown4ZHQPSE();
                    }
                }
            }
            return IndirectPointerEventType.INSTANCE.m3977getRelease4ZHQPSE();
        }
        return IndirectPointerEventType.INSTANCE.m3976getPress4ZHQPSE();
    }

    public static final MotionEvent getNativeEvent(IndirectPointerEvent indirectPointerEvent) {
        indirectPointerEvent.getClass();
        return ((AndroidIndirectPointerEvent) indirectPointerEvent).getNativeEvent();
    }

    public static final int indirectPrimaryDirectionalScrollAxis(MotionEvent motionEvent) {
        if (!motionEvent.isFromSource(2097152)) {
            w01.a("MotionEvent must be a touch navigation source");
            return 0;
        }
        InputDevice device = motionEvent.getDevice();
        if (device != null) {
            InputDevice.MotionRange motionRange = device.getMotionRange(0);
            InputDevice.MotionRange motionRange2 = device.getMotionRange(1);
            if (motionRange != null && motionRange2 == null) {
                return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m3966getXnZO2Niw();
            }
            if (motionRange2 != null && motionRange == null) {
                return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m3967getYnZO2Niw();
            }
            if (motionRange != null && motionRange2 != null) {
                float range = motionRange.getRange();
                float range2 = motionRange2.getRange();
                if (range > range2 && (range2 == 0.0f || range / range2 >= RATIO_CUTOFF)) {
                    return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m3966getXnZO2Niw();
                }
                if (range2 > range && (range == 0.0f || range2 / range >= RATIO_CUTOFF)) {
                    return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m3967getYnZO2Niw();
                }
            }
        }
        return IndirectPointerEventPrimaryDirectionalMotionAxis.INSTANCE.m3965getNonenZO2Niw();
    }
}
