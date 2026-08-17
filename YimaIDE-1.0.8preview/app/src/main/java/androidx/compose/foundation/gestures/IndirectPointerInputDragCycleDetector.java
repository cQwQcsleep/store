package androidx.compose.foundation.gestures;

import androidx.collection.SieveCacheKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerEventPrimaryDirectionalMotionAxis;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.VelocityKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u00002\u00020\u0001:\u0001YB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020\u001fH\u0002J\u0016\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u00020-J3\u00103\u001a\u00020-2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\b\b\u0002\u00108\u001a\u00020!2\b\b\u0002\u00109\u001a\u00020:H\u0002¢\u0006\u0004\b;\u0010<J\u0017\u0010=\u001a\u00020-2\u0006\u00106\u001a\u000207H\u0002¢\u0006\u0004\b>\u0010?J\b\u0010@\u001a\u00020-H\u0002J'\u0010A\u001a\u00020-2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010#\u001a\u00020$H\u0002¢\u0006\u0004\bB\u0010CJ \u0010D\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\tH\u0002J \u0010F\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u0013H\u0002J \u0010G\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u0018H\u0002J \u0010H\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010E\u001a\u00020\u000eH\u0002J1\u0010I\u001a\u00020-2\u0006\u0010J\u001a\u0002052\u0006\u0010K\u001a\u0002052\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020!H\u0002¢\u0006\u0004\bO\u0010PJ)\u0010Q\u001a\u00020-2\u0006\u0010R\u001a\u0002052\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010S\u001a\u00020!H\u0002¢\u0006\u0004\bT\u0010UJ\u001f\u0010V\u001a\u00020-2\u0006\u0010R\u001a\u0002052\b\u0010L\u001a\u0004\u0018\u00010MH\u0002¢\u0006\u0002\bWJ\b\u0010X\u001a\u00020-H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020!X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\"¨\u0006Z"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector;", "", "node", "Landroidx/compose/foundation/gestures/DragGestureNode;", "<init>", "(Landroidx/compose/foundation/gestures/DragGestureNode;)V", "getNode", "()Landroidx/compose/foundation/gestures/DragGestureNode;", "_awaitDownState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown;", "awaitDownState", "getAwaitDownState", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown;", "_draggingState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$Dragging;", "draggingState", "getDraggingState", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$Dragging;", "_awaitTouchSlopState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitTouchSlop;", "awaitTouchSlopState", "getAwaitTouchSlopState", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitTouchSlop;", "_awaitGesturePickupState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitGesturePickup;", "awaitGesturePickupState", "getAwaitGesturePickupState", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitGesturePickup;", "currentDragState", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "velocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "previousPositionOnScreen", "Landroidx/compose/ui/geometry/Offset;", "J", "touchSlopDetector", "Landroidx/compose/foundation/gestures/TouchSlopDetector;", "touchSmooth", "Landroidx/compose/foundation/gestures/IndirectPointerInputEventSmoother;", "offsetSmoother", "Landroidx/compose/foundation/gestures/OffsetSmoother;", "nodeOffset", "requireTouchSlopDetector", "requireVelocityTracker", "processIndirectPointerInputEvent", "", "indirectPointerInputEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "resetDragDetectionState", "moveToAwaitTouchSlopState", "initialDown", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "initialTouchSlopPositionChange", "verifyConsumptionInFinalPass", "", "moveToAwaitTouchSlopState-aWI9W7U", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;JJZ)V", "moveToDraggingState", "moveToDraggingState-0FcD4WY", "(J)V", "moveToAwaitDownState", "moveToAwaitGesturePickupState", "moveToAwaitGesturePickupState-rnUCldI", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;JLandroidx/compose/foundation/gestures/TouchSlopDetector;)V", "processInitialDownState", "state", "processAwaitTouchSlop", "processAwaitGesturePickup", "processDraggingState", "sendDragStart", "down", "slopTriggerChange", "primaryDirectionalMotionAxis", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "overSlopOffset", "sendDragStart-3f7A7Is", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;J)V", "sendDragEvent", "change", "dragAmount", "sendDragEvent-Eu1f8Dk", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;J)V", "sendDragStopped", "sendDragStopped-k92h6UU", "sendDragCancelled", "DragDetectionState", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndirectPointerInputDragCycleDetector {
    public static final int $stable = 8;
    private DragDetectionState.AwaitDown _awaitDownState;
    private DragDetectionState.AwaitGesturePickup _awaitGesturePickupState;
    private DragDetectionState.AwaitTouchSlop _awaitTouchSlopState;
    private DragDetectionState.Dragging _draggingState;
    private DragDetectionState currentDragState;
    private final DragGestureNode node;
    private long nodeOffset;
    private final OffsetSmoother offsetSmoother;
    private long previousPositionOnScreen;
    private TouchSlopDetector touchSlopDetector;
    private final IndirectPointerInputEventSmoother touchSmooth;
    private VelocityTracker velocityTracker;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DragDetectionState.AwaitDown.AwaitTouchSlop.values().length];
            try {
                iArr[DragDetectionState.AwaitDown.AwaitTouchSlop.NotInitialized.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public IndirectPointerInputDragCycleDetector(DragGestureNode dragGestureNode) {
        this.node = dragGestureNode;
        Offset.Companion companion = Offset.Companion;
        this.previousPositionOnScreen = companion.getUnspecified-F1C5BW0();
        this.touchSmooth = new IndirectPointerInputEventSmoother();
        this.offsetSmoother = new OffsetSmoother();
        this.nodeOffset = companion.getZero-F1C5BW0();
    }

    private final DragDetectionState.AwaitDown getAwaitDownState() {
        DragDetectionState.AwaitDown awaitDown = this._awaitDownState;
        if (awaitDown != null) {
            return awaitDown;
        }
        DragDetectionState.AwaitDown.AwaitTouchSlop awaitTouchSlop = null;
        DragDetectionState.AwaitDown awaitDown2 = new DragDetectionState.AwaitDown(awaitTouchSlop, false, 3, awaitTouchSlop);
        this._awaitDownState = awaitDown2;
        return awaitDown2;
    }

    private final DragDetectionState.AwaitGesturePickup getAwaitGesturePickupState() {
        DragDetectionState.AwaitGesturePickup awaitGesturePickup = this._awaitGesturePickupState;
        if (awaitGesturePickup != null) {
            return awaitGesturePickup;
        }
        DragDetectionState.AwaitGesturePickup awaitGesturePickup2 = new DragDetectionState.AwaitGesturePickup(null, 0L, null, 7, null);
        this._awaitGesturePickupState = awaitGesturePickup2;
        return awaitGesturePickup2;
    }

    private final DragDetectionState.AwaitTouchSlop getAwaitTouchSlopState() {
        DragDetectionState.AwaitTouchSlop awaitTouchSlop = this._awaitTouchSlopState;
        if (awaitTouchSlop != null) {
            return awaitTouchSlop;
        }
        DragDetectionState.AwaitTouchSlop awaitTouchSlop2 = new DragDetectionState.AwaitTouchSlop(null, 0L, false, 7, null);
        this._awaitTouchSlopState = awaitTouchSlop2;
        return awaitTouchSlop2;
    }

    private final DragDetectionState.Dragging getDraggingState() {
        DragDetectionState.Dragging dragging = this._draggingState;
        if (dragging != null) {
            return dragging;
        }
        DragDetectionState.Dragging dragging2 = new DragDetectionState.Dragging(0L, 1, null);
        this._draggingState = dragging2;
        return dragging2;
    }

    private final void moveToAwaitDownState() {
        DragDetectionState.AwaitDown awaitDownState = getAwaitDownState();
        awaitDownState.setAwaitTouchSlop(DragDetectionState.AwaitDown.AwaitTouchSlop.NotInitialized);
        awaitDownState.setConsumedOnInitial(false);
        this.currentDragState = awaitDownState;
    }

    /* JADX INFO: renamed from: moveToAwaitGesturePickupState-rnUCldI, reason: not valid java name */
    private final void m595moveToAwaitGesturePickupStaternUCldI(IndirectPointerInputChange initialDown, long pointerId, TouchSlopDetector touchSlopDetector) {
        DragDetectionState.AwaitGesturePickup awaitGesturePickupState = getAwaitGesturePickupState();
        awaitGesturePickupState.setInitialDown(initialDown);
        awaitGesturePickupState.m603setPointerId0FcD4WY(pointerId);
        TouchSlopDetector.m717resetk4lQ0M$default(touchSlopDetector, 0L, 1, null);
        awaitGesturePickupState.setTouchSlopDetector(touchSlopDetector);
        this.currentDragState = awaitGesturePickupState;
    }

    /* JADX INFO: renamed from: moveToAwaitTouchSlopState-aWI9W7U, reason: not valid java name */
    private final void m596moveToAwaitTouchSlopStateaWI9W7U(IndirectPointerInputChange initialDown, long pointerId, long initialTouchSlopPositionChange, boolean verifyConsumptionInFinalPass) {
        DragDetectionState.AwaitTouchSlop awaitTouchSlopState = getAwaitTouchSlopState();
        awaitTouchSlopState.setInitialDown(initialDown);
        awaitTouchSlopState.m605setPointerId0FcD4WY(pointerId);
        TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
        if (touchSlopDetector == null) {
            this.touchSlopDetector = new TouchSlopDetector(this.node.getOrientationLock(), 0L, 2, null);
        } else {
            if (touchSlopDetector != null) {
                touchSlopDetector.setOrientation(this.node.getOrientationLock());
            }
            TouchSlopDetector touchSlopDetector2 = this.touchSlopDetector;
            if (touchSlopDetector2 != null) {
                touchSlopDetector2.m721resetk4lQ0M(initialTouchSlopPositionChange);
            }
        }
        awaitTouchSlopState.setVerifyConsumptionInFinalPass(verifyConsumptionInFinalPass);
        this.currentDragState = awaitTouchSlopState;
    }

    /* JADX INFO: renamed from: moveToAwaitTouchSlopState-aWI9W7U$default, reason: not valid java name */
    public static /* synthetic */ void m597moveToAwaitTouchSlopStateaWI9W7U$default(IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector, IndirectPointerInputChange indirectPointerInputChange, long j, long j2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            j2 = Offset.Companion.getZero-F1C5BW0();
        }
        long j3 = j2;
        if ((i & 8) != 0) {
            z = false;
        }
        indirectPointerInputDragCycleDetector.m596moveToAwaitTouchSlopStateaWI9W7U(indirectPointerInputChange, j, j3, z);
    }

    /* JADX INFO: renamed from: moveToDraggingState-0FcD4WY, reason: not valid java name */
    private final void m598moveToDraggingState0FcD4WY(long pointerId) {
        DragDetectionState.Dragging draggingState = getDraggingState();
        draggingState.m607setPointerId0FcD4WY(pointerId);
        this.currentDragState = draggingState;
    }

    private final void processAwaitGesturePickup(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass, DragDetectionState.AwaitGesturePickup state) {
        boolean z;
        if (pass != PointerEventPass.Final) {
            return;
        }
        List changes = indirectPointerInputEvent.getChanges();
        int size = changes.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            } else {
                if (((IndirectPointerInputChange) changes.get(i)).isConsumed()) {
                    z = false;
                    break;
                }
                i++;
            }
        }
        List changes2 = indirectPointerInputEvent.getChanges();
        int size2 = changes2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (((IndirectPointerInputChange) changes2.get(i2)).getPressed()) {
                if (indirectPointerInputEvent.getChanges().isEmpty()) {
                    break;
                }
                if (z) {
                    long jM618primaryAxisPosition_bfSUIo = IndirectPointerInputDragCycleDetectorKt.m618primaryAxisPosition_bfSUIo((IndirectPointerInputChange) CollectionsKt.first(indirectPointerInputEvent.getChanges()), this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw()));
                    IndirectPointerInputChange initialDown = state.getInitialDown();
                    initialDown.getClass();
                    long j = Offset.minus-MK-Hz9U(jM618primaryAxisPosition_bfSUIo, IndirectPointerInputDragCycleDetectorKt.m618primaryAxisPosition_bfSUIo(initialDown, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw())));
                    IndirectPointerInputChange initialDown2 = state.getInitialDown();
                    if (initialDown2 != null) {
                        m597moveToAwaitTouchSlopStateaWI9W7U$default(this, initialDown2, state.getPointerId(), j, false, 8, null);
                        return;
                    } else {
                        w01.a("AwaitGesturePickup.initialDown was not initialized.");
                        return;
                    }
                }
                return;
            }
        }
        moveToAwaitDownState();
    }

    private final void processAwaitTouchSlop(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass, DragDetectionState.AwaitTouchSlop state) {
        Object obj;
        Object obj2;
        Object obj3;
        if (pass == PointerEventPass.Initial) {
            return;
        }
        List changes = indirectPointerInputEvent.getChanges();
        int size = changes.size();
        int i = 0;
        while (true) {
            obj = null;
            if (i >= size) {
                obj2 = null;
                break;
            }
            obj2 = changes.get(i);
            if (PointerId.equals-impl0(((IndirectPointerInputChange) obj2).getId-J3iCeTQ(), state.getPointerId())) {
                break;
            } else {
                i++;
            }
        }
        IndirectPointerInputChange indirectPointerInputChange = (IndirectPointerInputChange) obj2;
        if (indirectPointerInputChange == null) {
            List changes2 = indirectPointerInputEvent.getChanges();
            int size2 = changes2.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size2) {
                    obj3 = null;
                    break;
                }
                obj3 = changes2.get(i2);
                if (((IndirectPointerInputChange) obj3).getPressed()) {
                    break;
                } else {
                    i2++;
                }
            }
            indirectPointerInputChange = (IndirectPointerInputChange) obj3;
            if (indirectPointerInputChange == null) {
                moveToAwaitDownState();
                return;
            }
            state.m605setPointerId0FcD4WY(indirectPointerInputChange.getId-J3iCeTQ());
        }
        IndirectPointerInputChange indirectPointerInputChange2 = indirectPointerInputChange;
        if (pass == PointerEventPass.Main) {
            if (indirectPointerInputChange2.isConsumed()) {
                IndirectPointerInputChange initialDown = state.getInitialDown();
                if (initialDown == null) {
                    w01.a("AwaitTouchSlop.initialDown was not initialized");
                    return;
                }
                long pointerId = state.getPointerId();
                TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
                if (touchSlopDetector == null) {
                    w01.a("AwaitTouchSlop.touchSlopDetector was not initialized");
                    return;
                }
                m595moveToAwaitGesturePickupStaternUCldI(initialDown, pointerId, touchSlopDetector);
            } else if (IndirectPointerInputDragCycleDetectorKt.changedToUpIgnoreConsumed(indirectPointerInputChange2)) {
                List changes3 = indirectPointerInputEvent.getChanges();
                int size3 = changes3.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    Object obj4 = changes3.get(i3);
                    if (((IndirectPointerInputChange) obj4).getPressed()) {
                        obj = obj4;
                        break;
                    }
                }
                IndirectPointerInputChange indirectPointerInputChange3 = (IndirectPointerInputChange) obj;
                if (indirectPointerInputChange3 == null) {
                    moveToAwaitDownState();
                } else {
                    state.m605setPointerId0FcD4WY(indirectPointerInputChange3.getId-J3iCeTQ());
                }
            } else {
                long jM718addPositionsakrDWew = requireTouchSlopDetector().m718addPositionsakrDWew(IndirectPointerInputDragCycleDetectorKt.m618primaryAxisPosition_bfSUIo(indirectPointerInputChange2, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw())), IndirectPointerInputDragCycleDetectorKt.m620primaryAxisPreviousPosition_bfSUIo(indirectPointerInputChange2, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw())), DragGestureDetectorKt.m574pointerSlopE8SPZFQ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.node, CompositionLocalsKt.getLocalViewConfiguration()), PointerType.Companion.getTouch-T8wyACA()));
                if ((SieveCacheKt.InvalidMapping & jM718addPositionsakrDWew) != 9205357640488583168L) {
                    indirectPointerInputChange2.consume();
                    IndirectPointerInputChange initialDown2 = state.getInitialDown();
                    initialDown2.getClass();
                    m600sendDragStart3f7A7Is(initialDown2, indirectPointerInputChange2, IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw()), jM718addPositionsakrDWew);
                    m599sendDragEventEu1f8Dk(indirectPointerInputChange2, IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw()), jM718addPositionsakrDWew);
                    m598moveToDraggingState0FcD4WY(indirectPointerInputChange2.getId-J3iCeTQ());
                } else {
                    state.setVerifyConsumptionInFinalPass(true);
                }
            }
        }
        if (pass == PointerEventPass.Final && state.getVerifyConsumptionInFinalPass()) {
            if (!indirectPointerInputChange2.isConsumed()) {
                state.setVerifyConsumptionInFinalPass(false);
                return;
            }
            IndirectPointerInputChange initialDown3 = state.getInitialDown();
            if (initialDown3 == null) {
                w01.a("AwaitTouchSlop.initialDown was not initialized");
                return;
            }
            long pointerId2 = state.getPointerId();
            TouchSlopDetector touchSlopDetector2 = this.touchSlopDetector;
            if (touchSlopDetector2 != null) {
                m595moveToAwaitGesturePickupStaternUCldI(initialDown3, pointerId2, touchSlopDetector2);
            } else {
                w01.a("AwaitTouchSlop.touchSlopDetector was not initialized");
            }
        }
    }

    private final void processDraggingState(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass, DragDetectionState.Dragging state) {
        Object obj;
        Object obj2;
        if (pass != PointerEventPass.Main) {
            return;
        }
        long pointerId = state.getPointerId();
        List changes = indirectPointerInputEvent.getChanges();
        int size = changes.size();
        int i = 0;
        while (true) {
            obj = null;
            if (i >= size) {
                obj2 = null;
                break;
            }
            obj2 = changes.get(i);
            if (PointerId.equals-impl0(((IndirectPointerInputChange) obj2).getId-J3iCeTQ(), pointerId)) {
                break;
            } else {
                i++;
            }
        }
        IndirectPointerInputChange indirectPointerInputChange = (IndirectPointerInputChange) obj2;
        if (indirectPointerInputChange == null) {
            return;
        }
        if (!IndirectPointerInputDragCycleDetectorKt.changedToUpIgnoreConsumed(indirectPointerInputChange)) {
            if (indirectPointerInputChange.isConsumed()) {
                sendDragCancelled();
                return;
            } else {
                if (Offset.getDistance-impl(IndirectPointerInputDragCycleDetectorKt.m615positionChangeIgnoreConsumed_bfSUIo(indirectPointerInputChange, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw()))) == 0.0f) {
                    return;
                }
                m599sendDragEventEu1f8Dk(indirectPointerInputChange, IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw()), IndirectPointerInputDragCycleDetectorKt.m614positionChange_bfSUIo(indirectPointerInputChange, this.node.getOrientationLock(), IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw())));
                indirectPointerInputChange.consume();
                return;
            }
        }
        List changes2 = indirectPointerInputEvent.getChanges();
        int size2 = changes2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Object obj3 = changes2.get(i2);
            if (((IndirectPointerInputChange) obj3).getPressed()) {
                obj = obj3;
                break;
            }
        }
        IndirectPointerInputChange indirectPointerInputChange2 = (IndirectPointerInputChange) obj;
        if (indirectPointerInputChange2 != null) {
            state.m607setPointerId0FcD4WY(indirectPointerInputChange2.getId-J3iCeTQ());
            return;
        }
        if (indirectPointerInputChange.isConsumed() || !IndirectPointerInputDragCycleDetectorKt.changedToUpIgnoreConsumed(indirectPointerInputChange)) {
            sendDragCancelled();
        } else {
            m601sendDragStoppedk92h6UU(indirectPointerInputChange, IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw()));
        }
        moveToAwaitDownState();
    }

    private final void processInitialDownState(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass, DragDetectionState.AwaitDown state) {
        if (indirectPointerInputEvent.getChanges().isEmpty()) {
            return;
        }
        List changes = indirectPointerInputEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            if (!IndirectPointerInputDragCycleDetectorKt.changedToDownIgnoreConsumed((IndirectPointerInputChange) changes.get(i))) {
                return;
            }
        }
        IndirectPointerInputChange indirectPointerInputChange = (IndirectPointerInputChange) CollectionsKt.first(indirectPointerInputEvent.getChanges());
        DragDetectionState.AwaitDown.AwaitTouchSlop awaitTouchSlop = WhenMappings.$EnumSwitchMapping$0[state.getAwaitTouchSlop().ordinal()] == 1 ? !this.node.getStartDragImmediately() ? DragDetectionState.AwaitDown.AwaitTouchSlop.Yes : DragDetectionState.AwaitDown.AwaitTouchSlop.No : state.getAwaitTouchSlop();
        state.setAwaitTouchSlop(awaitTouchSlop);
        if (pass == PointerEventPass.Initial && awaitTouchSlop == DragDetectionState.AwaitDown.AwaitTouchSlop.No) {
            indirectPointerInputChange.consume();
            state.setConsumedOnInitial(true);
        }
        if (pass == PointerEventPass.Main) {
            if (awaitTouchSlop == DragDetectionState.AwaitDown.AwaitTouchSlop.Yes) {
                m597moveToAwaitTouchSlopStateaWI9W7U$default(this, indirectPointerInputChange, indirectPointerInputChange.getId-J3iCeTQ(), 0L, false, 12, null);
                return;
            }
            if (state.getConsumedOnInitial()) {
                IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis = IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw());
                Offset.Companion companion = Offset.Companion;
                m600sendDragStart3f7A7Is(indirectPointerInputChange, indirectPointerInputChange, indirectPointerEventPrimaryDirectionalMotionAxis, companion.getZero-F1C5BW0());
                m599sendDragEventEu1f8Dk(indirectPointerInputChange, IndirectPointerEventPrimaryDirectionalMotionAxis.box-impl(indirectPointerInputEvent.getPrimaryDirectionalMotionAxis-nZO2Niw()), companion.getZero-F1C5BW0());
                m598moveToDraggingState0FcD4WY(indirectPointerInputChange.getId-J3iCeTQ());
            }
        }
    }

    private final TouchSlopDetector requireTouchSlopDetector() {
        TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
        if (touchSlopDetector != null) {
            return touchSlopDetector;
        }
        w01.a("Touch slop detector not initialized.");
        return null;
    }

    private final VelocityTracker requireVelocityTracker() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            return velocityTracker;
        }
        w01.a("Velocity Tracker not initialized.");
        return null;
    }

    private final void sendDragCancelled() {
        this.node.onDragEvent(DragEvent.DragCancelled.INSTANCE);
    }

    /* JADX INFO: renamed from: sendDragEvent-Eu1f8Dk, reason: not valid java name */
    private final void m599sendDragEventEu1f8Dk(IndirectPointerInputChange change, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis, long dragAmount) {
        long jPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(this.node));
        if (!Offset.equals-impl0(this.previousPositionOnScreen, Offset.Companion.getUnspecified-F1C5BW0()) && !Offset.equals-impl0(jPositionOnScreen, this.previousPositionOnScreen)) {
            this.nodeOffset = Offset.plus-MK-Hz9U(this.nodeOffset, Offset.minus-MK-Hz9U(jPositionOnScreen, this.previousPositionOnScreen));
        }
        this.previousPositionOnScreen = jPositionOnScreen;
        Orientation orientationLock = this.node.getOrientationLock();
        orientationLock.getClass();
        if (Math.abs(DraggableKt.m587toFloat3MmeM6k(dragAmount, orientationLock)) > 2.0f) {
            IndirectPointerInputDragCycleDetectorKt.m613addIndirectPointerInputChangeQf4Zb88(requireVelocityTracker(), change, this.node.getOrientationLock(), primaryDirectionalMotionAxis, this.touchSmooth, this.nodeOffset);
            this.node.onDragEvent(new DragEvent.DragDelta(this.offsetSmoother.m631smoothEventPositionMKHz9U(dragAmount), true, null));
        }
    }

    /* JADX INFO: renamed from: sendDragStart-3f7A7Is, reason: not valid java name */
    private final void m600sendDragStart3f7A7Is(IndirectPointerInputChange down, IndirectPointerInputChange slopTriggerChange, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis, long overSlopOffset) {
        if (this.velocityTracker == null) {
            this.velocityTracker = new VelocityTracker();
        }
        this.nodeOffset = Offset.Companion.getZero-F1C5BW0();
        IndirectPointerInputDragCycleDetectorKt.m613addIndirectPointerInputChangeQf4Zb88(requireVelocityTracker(), down, this.node.getOrientationLock(), primaryDirectionalMotionAxis, this.touchSmooth, this.nodeOffset);
        long j = Offset.minus-MK-Hz9U(IndirectPointerInputDragCycleDetectorKt.m618primaryAxisPosition_bfSUIo(slopTriggerChange, this.node.getOrientationLock(), primaryDirectionalMotionAxis), overSlopOffset);
        if (((Boolean) this.node.getCanDrag().invoke(PointerType.box-impl(PointerType.Companion.getTouch-T8wyACA()))).booleanValue()) {
            this.previousPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(this.node));
            this.node.onDragEvent(new DragEvent.DragStarted(j, null));
        }
        this.offsetSmoother.reset();
    }

    /* JADX INFO: renamed from: sendDragStopped-k92h6UU, reason: not valid java name */
    private final void m601sendDragStoppedk92h6UU(IndirectPointerInputChange change, IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxis) {
        IndirectPointerInputDragCycleDetectorKt.m613addIndirectPointerInputChangeQf4Zb88(requireVelocityTracker(), change, this.node.getOrientationLock(), primaryDirectionalMotionAxis, this.touchSmooth, this.nodeOffset);
        float maximumFlingVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.node, CompositionLocalsKt.getLocalViewConfiguration())).getMaximumFlingVelocity();
        long j = requireVelocityTracker().calculateVelocity-AH228Gc(VelocityKt.Velocity(maximumFlingVelocity, maximumFlingVelocity));
        requireVelocityTracker().resetTracking();
        this.node.onDragEvent(new DragEvent.DragStopped(DraggableKt.m589toValidVelocityTH1AsA0(j), true, null));
    }

    public final DragGestureNode getNode() {
        return this.node;
    }

    public final void processIndirectPointerInputEvent(IndirectPointerEvent indirectPointerInputEvent, PointerEventPass pass) {
        if (this.currentDragState == null) {
            this.currentDragState = getAwaitDownState();
        }
        DragDetectionState dragDetectionState = this.currentDragState;
        if (dragDetectionState == null) {
            w01.a("currentDragState should not be null");
            return;
        }
        if (dragDetectionState instanceof DragDetectionState.AwaitDown) {
            processInitialDownState(indirectPointerInputEvent, pass, (DragDetectionState.AwaitDown) dragDetectionState);
            return;
        }
        if (dragDetectionState instanceof DragDetectionState.AwaitTouchSlop) {
            processAwaitTouchSlop(indirectPointerInputEvent, pass, (DragDetectionState.AwaitTouchSlop) dragDetectionState);
            return;
        }
        if (dragDetectionState instanceof DragDetectionState.AwaitGesturePickup) {
            processAwaitGesturePickup(indirectPointerInputEvent, pass, (DragDetectionState.AwaitGesturePickup) dragDetectionState);
        } else if (dragDetectionState instanceof DragDetectionState.Dragging) {
            processDraggingState(indirectPointerInputEvent, pass, (DragDetectionState.Dragging) dragDetectionState);
        } else {
            bu8.a();
        }
    }

    public final void resetDragDetectionState() {
        moveToAwaitDownState();
        if (this.node.getIsListeningForEvents()) {
            sendDragCancelled();
        }
        this.velocityTracker = null;
        this.offsetSmoother.reset();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "", "<init>", "()V", "AwaitDown", "AwaitTouchSlop", "AwaitGesturePickup", "Dragging", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitGesturePickup;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitTouchSlop;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$Dragging;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static abstract class DragDetectionState {
        public static final int $stable = 0;

        public /* synthetic */ DragDetectionState(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private DragDetectionState() {
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0010B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "awaitTouchSlop", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;", "consumedOnInitial", "", "<init>", "(Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;Z)V", "getAwaitTouchSlop", "()Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;", "setAwaitTouchSlop", "(Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;)V", "getConsumedOnInitial", "()Z", "setConsumedOnInitial", "(Z)V", "AwaitTouchSlop", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class AwaitDown extends DragDetectionState {
            public static final int $stable = 8;
            private AwaitTouchSlop awaitTouchSlop;
            private boolean consumedOnInitial;

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitDown$AwaitTouchSlop;", "", "<init>", "(Ljava/lang/String;I)V", "Yes", "No", "NotInitialized", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
            public enum AwaitTouchSlop {
                Yes,
                No,
                NotInitialized;

                private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

                public static EnumEntries<AwaitTouchSlop> getEntries() {
                    return $ENTRIES;
                }
            }

            public /* synthetic */ AwaitDown(AwaitTouchSlop awaitTouchSlop, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? AwaitTouchSlop.NotInitialized : awaitTouchSlop, (i & 2) != 0 ? false : z);
            }

            public final AwaitTouchSlop getAwaitTouchSlop() {
                return this.awaitTouchSlop;
            }

            public final boolean getConsumedOnInitial() {
                return this.consumedOnInitial;
            }

            public final void setAwaitTouchSlop(AwaitTouchSlop awaitTouchSlop) {
                this.awaitTouchSlop = awaitTouchSlop;
            }

            public final void setConsumedOnInitial(boolean z) {
                this.consumedOnInitial = z;
            }

            public AwaitDown(AwaitTouchSlop awaitTouchSlop, boolean z) {
                super(null);
                this.awaitTouchSlop = awaitTouchSlop;
                this.consumedOnInitial = z;
            }

            /* JADX WARN: Illegal instructions before constructor call */
            public AwaitDown() {
                AwaitTouchSlop awaitTouchSlop = null;
                this(awaitTouchSlop, false, 3, awaitTouchSlop);
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001c\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$Dragging;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "<init>", "(JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getPointerId-J3iCeTQ", "()J", "setPointerId-0FcD4WY", "(J)V", "J", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Dragging extends DragDetectionState {
            public static final int $stable = 8;
            private long pointerId;

            public /* synthetic */ Dragging(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? PointerId.constructor-impl(Long.MAX_VALUE) : j, null);
            }

            /* JADX INFO: renamed from: getPointerId-J3iCeTQ, reason: not valid java name and from getter */
            public final long getPointerId() {
                return this.pointerId;
            }

            /* JADX INFO: renamed from: setPointerId-0FcD4WY, reason: not valid java name */
            public final void m607setPointerId0FcD4WY(long j) {
                this.pointerId = j;
            }

            private Dragging(long j) {
                super(null);
                this.pointerId = j;
            }

            public /* synthetic */ Dragging(long j, DefaultConstructorMarker defaultConstructorMarker) {
                this(j);
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitGesturePickup;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "initialDown", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "touchSlopDetector", "Landroidx/compose/foundation/gestures/TouchSlopDetector;", "<init>", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;JLandroidx/compose/foundation/gestures/TouchSlopDetector;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getInitialDown", "()Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "setInitialDown", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;)V", "getPointerId-J3iCeTQ", "()J", "setPointerId-0FcD4WY", "(J)V", "J", "getTouchSlopDetector", "()Landroidx/compose/foundation/gestures/TouchSlopDetector;", "setTouchSlopDetector", "(Landroidx/compose/foundation/gestures/TouchSlopDetector;)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class AwaitGesturePickup extends DragDetectionState {
            public static final int $stable = 8;
            private IndirectPointerInputChange initialDown;
            private long pointerId;
            private TouchSlopDetector touchSlopDetector;

            public /* synthetic */ AwaitGesturePickup(IndirectPointerInputChange indirectPointerInputChange, long j, TouchSlopDetector touchSlopDetector, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : indirectPointerInputChange, (i & 2) != 0 ? PointerId.constructor-impl(Long.MAX_VALUE) : j, (i & 4) != 0 ? null : touchSlopDetector, null);
            }

            public final IndirectPointerInputChange getInitialDown() {
                return this.initialDown;
            }

            /* JADX INFO: renamed from: getPointerId-J3iCeTQ, reason: not valid java name and from getter */
            public final long getPointerId() {
                return this.pointerId;
            }

            public final TouchSlopDetector getTouchSlopDetector() {
                return this.touchSlopDetector;
            }

            public final void setInitialDown(IndirectPointerInputChange indirectPointerInputChange) {
                this.initialDown = indirectPointerInputChange;
            }

            /* JADX INFO: renamed from: setPointerId-0FcD4WY, reason: not valid java name */
            public final void m603setPointerId0FcD4WY(long j) {
                this.pointerId = j;
            }

            public final void setTouchSlopDetector(TouchSlopDetector touchSlopDetector) {
                this.touchSlopDetector = touchSlopDetector;
            }

            private AwaitGesturePickup(IndirectPointerInputChange indirectPointerInputChange, long j, TouchSlopDetector touchSlopDetector) {
                super(null);
                this.initialDown = indirectPointerInputChange;
                this.pointerId = j;
                this.touchSlopDetector = touchSlopDetector;
            }

            public /* synthetic */ AwaitGesturePickup(IndirectPointerInputChange indirectPointerInputChange, long j, TouchSlopDetector touchSlopDetector, DefaultConstructorMarker defaultConstructorMarker) {
                this(indirectPointerInputChange, j, touchSlopDetector);
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState$AwaitTouchSlop;", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector$DragDetectionState;", "initialDown", "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "verifyConsumptionInFinalPass", "", "<init>", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;JZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getInitialDown", "()Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "setInitialDown", "(Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;)V", "getPointerId-J3iCeTQ", "()J", "setPointerId-0FcD4WY", "(J)V", "J", "getVerifyConsumptionInFinalPass", "()Z", "setVerifyConsumptionInFinalPass", "(Z)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class AwaitTouchSlop extends DragDetectionState {
            public static final int $stable = 8;
            private IndirectPointerInputChange initialDown;
            private long pointerId;
            private boolean verifyConsumptionInFinalPass;

            public /* synthetic */ AwaitTouchSlop(IndirectPointerInputChange indirectPointerInputChange, long j, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : indirectPointerInputChange, (i & 2) != 0 ? PointerId.constructor-impl(Long.MAX_VALUE) : j, (i & 4) != 0 ? false : z, null);
            }

            public final IndirectPointerInputChange getInitialDown() {
                return this.initialDown;
            }

            /* JADX INFO: renamed from: getPointerId-J3iCeTQ, reason: not valid java name and from getter */
            public final long getPointerId() {
                return this.pointerId;
            }

            public final boolean getVerifyConsumptionInFinalPass() {
                return this.verifyConsumptionInFinalPass;
            }

            public final void setInitialDown(IndirectPointerInputChange indirectPointerInputChange) {
                this.initialDown = indirectPointerInputChange;
            }

            /* JADX INFO: renamed from: setPointerId-0FcD4WY, reason: not valid java name */
            public final void m605setPointerId0FcD4WY(long j) {
                this.pointerId = j;
            }

            public final void setVerifyConsumptionInFinalPass(boolean z) {
                this.verifyConsumptionInFinalPass = z;
            }

            private AwaitTouchSlop(IndirectPointerInputChange indirectPointerInputChange, long j, boolean z) {
                super(null);
                this.initialDown = indirectPointerInputChange;
                this.pointerId = j;
                this.verifyConsumptionInFinalPass = z;
            }

            public /* synthetic */ AwaitTouchSlop(IndirectPointerInputChange indirectPointerInputChange, long j, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
                this(indirectPointerInputChange, j, z);
            }
        }
    }
}
