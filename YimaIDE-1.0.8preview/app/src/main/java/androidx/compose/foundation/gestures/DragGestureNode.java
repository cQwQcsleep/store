package androidx.compose.foundation.gestures;

import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.gestures.DragGestureNode;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.pointer.util.VelocityTracker;
import androidx.compose.ui.input.pointer.util.VelocityTrackerKt;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\b!\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B7\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJM\u0010H\u001a\u00020I2=\u0010J\u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110L¢\u0006\f\bM\u0012\b\bN\u0012\u0004\b\b(O\u0012\u0004\u0012\u00020I0\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0P\u0012\u0006\u0012\u0004\u0018\u00010Q0KH¦@¢\u0006\u0002\u0010RJ\u0017\u0010S\u001a\u00020I2\u0006\u0010T\u001a\u00020AH&¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020I2\u0006\u0010X\u001a\u00020YH&J\b\u0010Z\u001a\u00020\bH&J\b\u0010[\u001a\u00020?H\u0002J\u000e\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J\b\u0010]\u001a\u00020DH\u0002J\b\u0010^\u001a\u00020IH\u0002J\b\u0010a\u001a\u00020IH\u0016J'\u0010b\u001a\u00020I2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020hH\u0016¢\u0006\u0004\bi\u0010jJ\u0018\u0010k\u001a\u00020I2\u0006\u0010X\u001a\u00020l2\u0006\u0010e\u001a\u00020fH\u0016J\b\u0010m\u001a\u00020IH\u0016J\b\u0010n\u001a\u00020`H\u0002J\b\u0010o\u001a\u00020IH\u0016J\u0016\u0010p\u001a\u00020I2\u0006\u0010X\u001a\u00020qH\u0082@¢\u0006\u0002\u0010rJ\u0016\u0010s\u001a\u00020I2\u0006\u0010X\u001a\u00020YH\u0082@¢\u0006\u0002\u0010tJ\u000e\u0010u\u001a\u00020IH\u0082@¢\u0006\u0002\u0010vJ\u0006\u0010w\u001a\u00020IJH\u0010x\u001a\u00020I2\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010y\u001a\u00020\bJ\u0018\u0010z\u001a\u00020I2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020fH\u0002J\b\u0010{\u001a\u00020IH\u0002J8\u0010|\u001a\u00020I2\u0006\u0010}\u001a\u00020~2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\t\b\u0002\u0010\u0081\u0001\u001a\u00020A2\t\b\u0002\u0010\u0082\u0001\u001a\u00020\bH\u0002¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u001a\u0010\u0085\u0001\u001a\u00020I2\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0002¢\u0006\u0005\b\u0086\u0001\u0010VJ\t\u0010\u0087\u0001\u001a\u00020IH\u0002J+\u0010\u0088\u0001\u001a\u00020I2\u0006\u0010}\u001a\u00020~2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\u0006\u0010C\u001a\u00020DH\u0002¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\"\u0010\u008b\u0001\u001a\u00020I2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020f2\u0007\u0010\u008c\u0001\u001a\u00020)H\u0002J\"\u0010\u008d\u0001\u001a\u00020I2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020f2\u0007\u0010\u008c\u0001\u001a\u000203H\u0002J\"\u0010\u008e\u0001\u001a\u00020I2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020f2\u0007\u0010\u008c\u0001\u001a\u000208H\u0002J\"\u0010\u008f\u0001\u001a\u00020I2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020f2\u0007\u0010\u008c\u0001\u001a\u00020.H\u0002J-\u0010\u0090\u0001\u001a\u00020I2\u0007\u0010\u0091\u0001\u001a\u00020~2\u0007\u0010\u0092\u0001\u001a\u00020~2\u0007\u0010\u0093\u0001\u001a\u00020AH\u0002¢\u0006\u0006\b\u0094\u0001\u0010\u0095\u0001J$\u0010\u0096\u0001\u001a\u00020I2\u0007\u0010\u0097\u0001\u001a\u00020~2\u0007\u0010\u0098\u0001\u001a\u00020AH\u0002¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\u0012\u0010\u009b\u0001\u001a\u00020I2\u0007\u0010\u0097\u0001\u001a\u00020~H\u0002J\t\u0010\u009c\u0001\u001a\u00020IH\u0002J\u000f\u0010\u009d\u0001\u001a\u00020I2\u0006\u0010X\u001a\u00020\u001eR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R6\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0018\"\u0004\b'\u0010$R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\u00020.8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\u0002038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00109\u001a\u0002088BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u00020AX\u0082\u000e¢\u0006\u0004\n\u0002\u0010BR\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u0004\u0018\u00010FX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u00020AX\u0082\u000e¢\u0006\u0004\n\u0002\u0010BR\u0010\u0010_\u001a\u0004\u0018\u00010`X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u009e\u0001"}, d2 = {"Landroidx/compose/foundation/gestures/DragGestureNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/input/indirect/IndirectPointerInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "canDrag", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerType;", "", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "orientationLock", "Landroidx/compose/foundation/gestures/Orientation;", "<init>", "(Lkotlin/jvm/functions/Function1;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/gestures/Orientation;)V", "getOrientationLock", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientationLock", "(Landroidx/compose/foundation/gestures/Orientation;)V", "value", "getCanDrag", "()Lkotlin/jvm/functions/Function1;", "getEnabled", "()Z", "getInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "_canDrag", "channel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/compose/foundation/gestures/DragEvent;", "dragInteraction", "Landroidx/compose/foundation/interaction/DragInteraction$Start;", "isListeningForEvents", "isListeningForEvents$foundation", "setListeningForEvents$foundation", "(Z)V", "isListeningForPointerInputEvents", "isListeningForPointerInputEvents$foundation", "setListeningForPointerInputEvents$foundation", "_awaitDownState", "Landroidx/compose/foundation/gestures/DragDetectionState$AwaitDown;", "awaitDownState", "getAwaitDownState", "()Landroidx/compose/foundation/gestures/DragDetectionState$AwaitDown;", "_draggingState", "Landroidx/compose/foundation/gestures/DragDetectionState$Dragging;", "draggingState", "getDraggingState", "()Landroidx/compose/foundation/gestures/DragDetectionState$Dragging;", "_awaitTouchSlopState", "Landroidx/compose/foundation/gestures/DragDetectionState$AwaitTouchSlop;", "awaitTouchSlopState", "getAwaitTouchSlopState", "()Landroidx/compose/foundation/gestures/DragDetectionState$AwaitTouchSlop;", "_awaitGesturePickupState", "Landroidx/compose/foundation/gestures/DragDetectionState$AwaitGesturePickup;", "awaitGesturePickupState", "getAwaitGesturePickupState", "()Landroidx/compose/foundation/gestures/DragDetectionState$AwaitGesturePickup;", "currentDragState", "Landroidx/compose/foundation/gestures/DragDetectionState;", "velocityTracker", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "previousPositionOnScreen", "Landroidx/compose/ui/geometry/Offset;", "J", "touchSlopDetector", "Landroidx/compose/foundation/gestures/TouchSlopDetector;", "indirectPointerInputDragCycleDetector", "Landroidx/compose/foundation/gestures/IndirectPointerInputDragCycleDetector;", "nodeOffset", "drag", "", "forEachDelta", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", "name", "dragDelta", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStarted", "startedPosition", "onDragStarted-k-4lQ0M", "(J)V", "onDragStopped", "event", "Landroidx/compose/foundation/gestures/DragEvent$DragStopped;", "startDragImmediately", "requireVelocityTracker", "requireChannel", "requireTouchSlopDetector", "startListeningForEvents", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "onDetach", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onIndirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "onCancelIndirectPointerInput", "initializePointerInputNode", "onCancelPointerInput", "processDragStart", "Landroidx/compose/foundation/gestures/DragEvent$DragStarted;", "(Landroidx/compose/foundation/gestures/DragEvent$DragStarted;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processDragStop", "(Landroidx/compose/foundation/gestures/DragEvent$DragStopped;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processDragCancel", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "disposeInteractionSource", "update", "shouldResetPointerInputHandling", "processRawPointerEvent", "resetDragDetectionState", "moveToAwaitTouchSlopState", "initialDown", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "initialTouchSlopPositionChange", "verifyConsumptionInFinalPass", "moveToAwaitTouchSlopState-aWI9W7U", "(Landroidx/compose/ui/input/pointer/PointerInputChange;JJZ)V", "moveToDraggingState", "moveToDraggingState-0FcD4WY", "moveToAwaitDownState", "moveToAwaitGesturePickupState", "moveToAwaitGesturePickupState-rnUCldI", "(Landroidx/compose/ui/input/pointer/PointerInputChange;JLandroidx/compose/foundation/gestures/TouchSlopDetector;)V", "processInitialDownState", "state", "processAwaitTouchSlop", "processAwaitGesturePickup", "processDraggingState", "sendDragStart", "down", "slopTriggerChange", "overSlopOffset", "sendDragStart-0AR0LA0", "(Landroidx/compose/ui/input/pointer/PointerInputChange;Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "sendDragEvent", "change", "dragAmount", "sendDragEvent-Uv8p0NA", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)V", "sendDragStopped", "sendDragCancelled", "onDragEvent", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class DragGestureNode extends DelegatingNode implements PointerInputModifierNode, IndirectPointerInputModifierNode, CompositionLocalConsumerModifierNode {
    public static final int $stable = 8;
    private DragDetectionState.AwaitDown _awaitDownState;
    private DragDetectionState.AwaitGesturePickup _awaitGesturePickupState;
    private DragDetectionState.AwaitTouchSlop _awaitTouchSlopState;
    private final Function1<PointerType, Boolean> _canDrag = new Function1() { // from class: ux3
        public final Object invoke(Object obj) {
            return Boolean.valueOf(DragGestureNode.a(this.b, (PointerType) obj));
        }
    };
    private DragDetectionState.Dragging _draggingState;
    private Function1<? super PointerType, Boolean> canDrag;
    private Channel<DragEvent> channel;
    private DragDetectionState currentDragState;
    private DragInteraction.Start dragInteraction;
    private boolean enabled;
    private IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector;
    private MutableInteractionSource interactionSource;
    private boolean isListeningForEvents;
    private boolean isListeningForPointerInputEvents;
    private long nodeOffset;
    private Orientation orientationLock;
    private SuspendingPointerInputModifierNode pointerInputNode;
    private long previousPositionOnScreen;
    private TouchSlopDetector touchSlopDetector;
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements PointerInputEventHandler {

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$initializePointerInputNode$1$1", f = "Draggable.kt", i = {0}, l = {624}, m = "invokeSuspend", n = {"$this$coroutineScope"}, s = {"L$0"}, v = 1)
        public static final class C00151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
            final /* synthetic */ Function0<Unit> $onDragCancel;
            final /* synthetic */ Function1<PointerInputChange, Unit> $onDragEnd;
            final /* synthetic */ Function3<PointerInputChange, PointerInputChange, Offset, Unit> $onDragStart;
            final /* synthetic */ Function0<Boolean> $shouldAwaitTouchSlop;
            final /* synthetic */ PointerInputScope $this_SuspendingPointerInputModifierNode;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ DragGestureNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00151(PointerInputScope pointerInputScope, DragGestureNode dragGestureNode, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function1<? super PointerInputChange, Unit> function1, Function0<Unit> function0, Function0<Boolean> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function4, Continuation<? super C00151> continuation) {
                super(2, continuation);
                this.$this_SuspendingPointerInputModifierNode = pointerInputScope;
                this.this$0 = dragGestureNode;
                this.$onDragStart = function3;
                this.$onDragEnd = function1;
                this.$onDragCancel = function0;
                this.$shouldAwaitTouchSlop = function2;
                this.$onDrag = function4;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00151 c00151 = new C00151(this.$this_SuspendingPointerInputModifierNode, this.this$0, this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$shouldAwaitTouchSlop, this.$onDrag, continuation);
                c00151.L$0 = obj;
                return c00151;
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:24:0x0053  */
            /* JADX WARN: Code duplicated, block: B:29:0x0065  */
            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope;
                C00151 c00151;
                CancellationException cancellationException;
                Channel channel;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = (CoroutineScope) this.L$0;
                    try {
                        PointerInputScope pointerInputScope = this.$this_SuspendingPointerInputModifierNode;
                        Orientation orientationLock = this.this$0.getOrientationLock();
                        Function3<PointerInputChange, PointerInputChange, Offset, Unit> function3 = this.$onDragStart;
                        Function1<PointerInputChange, Unit> function1 = this.$onDragEnd;
                        Function0<Unit> function0 = this.$onDragCancel;
                        Function0<Boolean> function2 = this.$shouldAwaitTouchSlop;
                        Function2<PointerInputChange, Offset, Unit> function4 = this.$onDrag;
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        c00151 = this;
                        try {
                            if (DragGestureDetectorKt.detectDragGestures(pointerInputScope, orientationLock, function3, function1, function0, function2, function4, c00151) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } catch (CancellationException e) {
                            e = e;
                            cancellationException = e;
                            channel = c00151.this$0.channel;
                            if (channel != null) {
                                ChannelResult.box-impl(channel.trySend-JP2dKIU(DragEvent.DragCancelled.INSTANCE));
                            }
                            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                                throw cancellationException;
                            }
                        }
                    } catch (CancellationException e2) {
                        e = e2;
                        c00151 = this;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    coroutineScope = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (CancellationException e3) {
                        cancellationException = e3;
                        c00151 = this;
                        channel = c00151.this$0.channel;
                        if (channel != null) {
                            ChannelResult.box-impl(channel.trySend-JP2dKIU(DragEvent.DragCancelled.INSTANCE));
                        }
                        if (!CoroutineScopeKt.isActive(coroutineScope)) {
                            throw cancellationException;
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1() {
        }

        public static Unit a(VelocityTracker velocityTracker, PointerInputScope pointerInputScope, DragGestureNode dragGestureNode, PointerInputChange pointerInputChange) {
            VelocityTrackerKt.addPointerInputChange(velocityTracker, pointerInputChange);
            float maximumFlingVelocity = pointerInputScope.getViewConfiguration().getMaximumFlingVelocity();
            long j = velocityTracker.calculateVelocity-AH228Gc(VelocityKt.Velocity(maximumFlingVelocity, maximumFlingVelocity));
            velocityTracker.resetTracking();
            Channel channel = dragGestureNode.channel;
            if (channel != null) {
                ChannelResult.box-impl(channel.trySend-JP2dKIU(new DragEvent.DragStopped(DraggableKt.m589toValidVelocityTH1AsA0(j), false, null)));
            }
            return Unit.INSTANCE;
        }

        public static boolean b(DragGestureNode dragGestureNode) {
            return !dragGestureNode.getStartDragImmediately();
        }

        public static Unit c(DragGestureNode dragGestureNode, Ref.LongRef longRef, VelocityTracker velocityTracker, PointerInputChange pointerInputChange, Offset offset) {
            long jPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(dragGestureNode));
            if (!Offset.equals-impl0(jPositionOnScreen, longRef.element)) {
                dragGestureNode.nodeOffset = Offset.plus-MK-Hz9U(dragGestureNode.nodeOffset, Offset.minus-MK-Hz9U(jPositionOnScreen, longRef.element));
            }
            longRef.element = jPositionOnScreen;
            VelocityTrackerKt.addPointerInputChange-0AR0LA0(velocityTracker, pointerInputChange, dragGestureNode.nodeOffset);
            Channel channel = dragGestureNode.channel;
            if (channel != null) {
                ChannelResult.box-impl(channel.trySend-JP2dKIU(new DragEvent.DragDelta(offset.unbox-impl(), false, null)));
            }
            return Unit.INSTANCE;
        }

        public static Unit d(DragGestureNode dragGestureNode, VelocityTracker velocityTracker, PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2, Offset offset) {
            dragGestureNode.nodeOffset = Offset.Companion.getZero-F1C5BW0();
            if (((Boolean) dragGestureNode.getCanDrag().invoke(PointerType.box-impl(pointerInputChange.getType-T8wyACA()))).booleanValue()) {
                if (!dragGestureNode.getIsListeningForEvents()) {
                    dragGestureNode.startListeningForEvents();
                }
                VelocityTrackerKt.addPointerInputChange(velocityTracker, pointerInputChange);
                long j = Offset.minus-MK-Hz9U(pointerInputChange2.getPosition-F1C5BW0(), offset.unbox-impl());
                Channel channel = dragGestureNode.channel;
                if (channel != null) {
                    ChannelResult.box-impl(channel.trySend-JP2dKIU(new DragEvent.DragStarted(j, null)));
                }
            }
            return Unit.INSTANCE;
        }

        public static Unit e(DragGestureNode dragGestureNode) {
            Channel channel = dragGestureNode.channel;
            if (channel != null) {
                ChannelResult.box-impl(channel.trySend-JP2dKIU(DragEvent.DragCancelled.INSTANCE));
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(final PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            final VelocityTracker velocityTracker = new VelocityTracker();
            final Ref.LongRef longRef = new Ref.LongRef();
            longRef.element = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(DragGestureNode.this));
            final DragGestureNode dragGestureNode = DragGestureNode.this;
            Function3 function3 = new Function3() { // from class: androidx.compose.foundation.gestures.p
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return DragGestureNode.AnonymousClass1.d(dragGestureNode, velocityTracker, (PointerInputChange) obj, (PointerInputChange) obj2, (Offset) obj3);
                }
            };
            final DragGestureNode dragGestureNode2 = DragGestureNode.this;
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.q
                public final Object invoke(Object obj) {
                    return DragGestureNode.AnonymousClass1.a(velocityTracker, pointerInputScope, dragGestureNode2, (PointerInputChange) obj);
                }
            };
            final DragGestureNode dragGestureNode3 = DragGestureNode.this;
            Function0 function0 = new Function0() { // from class: androidx.compose.foundation.gestures.r
                public final Object invoke() {
                    return DragGestureNode.AnonymousClass1.e(dragGestureNode3);
                }
            };
            final DragGestureNode dragGestureNode4 = DragGestureNode.this;
            Function0 function2 = new Function0() { // from class: androidx.compose.foundation.gestures.s
                public final Object invoke() {
                    return Boolean.valueOf(DragGestureNode.AnonymousClass1.b(dragGestureNode4));
                }
            };
            final DragGestureNode dragGestureNode5 = DragGestureNode.this;
            Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C00151(pointerInputScope, DragGestureNode.this, function3, function1, function0, function2, new Function2() { // from class: androidx.compose.foundation.gestures.t
                public final Object invoke(Object obj, Object obj2) {
                    return DragGestureNode.AnonymousClass1.c(dragGestureNode5, longRef, velocityTracker, (PointerInputChange) obj, (Offset) obj2);
                }
            }, null), continuation);
            return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragCancel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {}, l = {667}, m = "processDragCancel", n = {}, s = {}, v = 1)
    public static final class C01071 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C01071(Continuation<? super C01071> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragCancel(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragStart$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {0, 1, 1}, l = {649, 652}, m = "processDragStart", n = {"event", "event", "interaction"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    public static final class C01081 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C01081(Continuation<? super C01081> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragStart(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$processDragStop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode", f = "Draggable.kt", i = {0}, l = {659}, m = "processDragStop", n = {"event"}, s = {"L$0"}, v = 1)
    public static final class C01091 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C01091(Continuation<? super C01091> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureNode.this.processDragStop(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1", f = "Draggable.kt", i = {0, 0, 1, 1, 2, 2, 3, 4, 5}, l = {499, 501, 503, 510, 512, 515}, m = "invokeSuspend", n = {"$this$launch", "event", "$this$launch", "event", "$this$launch", "event", "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$0", "L$0"}, v = 1)
    public static final class C01101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "processDelta", "Lkotlin/Function1;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "Lkotlin/ParameterName;", "name", "dragDelta"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureNode$startListeningForEvents$1$1", f = "Draggable.kt", i = {0}, l = {506}, m = "invokeSuspend", n = {"processDelta"}, s = {"L$0"}, v = 1)
        public static final class C00161 extends SuspendLambda implements Function2<Function1<? super DragEvent.DragDelta, ? extends Unit>, Continuation<? super Unit>, Object> {
            final /* synthetic */ Ref.ObjectRef<DragEvent> $event;
            /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DragGestureNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00161(Ref.ObjectRef<DragEvent> objectRef, DragGestureNode dragGestureNode, Continuation<? super C00161> continuation) {
                super(2, continuation);
                this.$event = objectRef;
                this.this$0 = dragGestureNode;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00161 c00161 = new C00161(this.$event, this.this$0, continuation);
                c00161.L$0 = obj;
                return c00161;
            }

            public final Object invoke(Function1<? super DragEvent.DragDelta, Unit> function1, Continuation<? super Unit> continuation) {
                return create(function1, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:11:0x002e  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0053 -> B:24:0x0056). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0059 -> B:26:0x005a). Please report as a decompilation issue!!! */
            public final Object invokeSuspend(Object obj) {
                Function1 function1;
                Object obj2;
                Ref.ObjectRef<DragEvent> objectRef;
                DragEvent dragEvent;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    function1 = (Function1) this.L$0;
                    obj2 = this.$event.element;
                    if (!(obj2 instanceof DragEvent.DragStopped) || (obj2 instanceof DragEvent.DragCancelled)) {
                        return Unit.INSTANCE;
                    }
                    DragEvent.DragDelta dragDelta = obj2 instanceof DragEvent.DragDelta ? (DragEvent.DragDelta) obj2 : null;
                    if (dragDelta != null) {
                        function1.invoke(dragDelta);
                    }
                    objectRef = this.$event;
                    Channel channel = this.this$0.channel;
                    if (channel != null) {
                        this.L$0 = function1;
                        this.L$1 = objectRef;
                        this.label = 1;
                        obj = channel.receive(this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        dragEvent = null;
                    }
                    objectRef.element = dragEvent;
                    obj2 = this.$event.element;
                    if (obj2 instanceof DragEvent.DragStopped) {
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                objectRef = (Ref.ObjectRef) this.L$1;
                function1 = (Function1) this.L$0;
                ResultKt.throwOnFailure(obj);
                dragEvent = (DragEvent) obj;
                objectRef.element = dragEvent;
                obj2 = this.$event.element;
                if (obj2 instanceof DragEvent.DragStopped) {
                }
                return Unit.INSTANCE;
            }
        }

        public C01101(Continuation<? super C01101> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01101 c01101 = DragGestureNode.this.new C01101(continuation);
            c01101.L$0 = obj;
            return c01101;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0032 A[PHI: r1 r3
          0x0032: PHI (r1v14 kotlin.jvm.internal.Ref$ObjectRef) = (r1v6 kotlin.jvm.internal.Ref$ObjectRef), (r1v19 kotlin.jvm.internal.Ref$ObjectRef) binds: [B:13:0x002f, B:36:0x00b6] A[DONT_GENERATE, DONT_INLINE]
          0x0032: PHI (r3v8 kotlinx.coroutines.CoroutineScope) = (r3v5 kotlinx.coroutines.CoroutineScope), (r3v10 kotlinx.coroutines.CoroutineScope) binds: [B:13:0x002f, B:36:0x00b6] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:19:0x005c A[PHI: r4
          0x005c: PHI (r4v7 kotlinx.coroutines.CoroutineScope) = 
          (r4v0 kotlinx.coroutines.CoroutineScope)
          (r4v3 kotlinx.coroutines.CoroutineScope)
          (r4v3 kotlinx.coroutines.CoroutineScope)
          (r4v3 kotlinx.coroutines.CoroutineScope)
          (r4v5 kotlinx.coroutines.CoroutineScope)
          (r4v8 kotlinx.coroutines.CoroutineScope)
         binds: [B:18:0x0054, B:45:0x00d8, B:47:0x00e7, B:41:0x00d1, B:30:0x008c, B:11:0x0025] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:21:0x0062  */
        /* JADX WARN: Code duplicated, block: B:23:0x006f  */
        /* JADX WARN: Code duplicated, block: B:26:0x0080  */
        /* JADX WARN: Code duplicated, block: B:31:0x008e  */
        /* JADX WARN: Code duplicated, block: B:34:0x00a2  */
        /* JADX WARN: Code duplicated, block: B:44:0x00d6 A[Catch: CancellationException -> 0x00d4, TryCatch #1 {CancellationException -> 0x00d4, blocks: (B:38:0x00b9, B:40:0x00bf, B:44:0x00d6, B:46:0x00da), top: B:57:0x00b9 }] */
        /* JADX WARN: Code duplicated, block: B:46:0x00da A[Catch: CancellationException -> 0x00d4, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x00d4, blocks: (B:38:0x00b9, B:40:0x00bf, B:44:0x00d6, B:46:0x00da), top: B:57:0x00b9 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x008c -> B:19:0x005c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00d1 -> B:19:0x005c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00d8 -> B:19:0x005c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00e7 -> B:19:0x005c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x00f7 -> B:11:0x0025). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                Method dump skipped, instruction units count: 272
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureNode.C01101.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public DragGestureNode(Function1<? super PointerType, Boolean> function1, boolean z, MutableInteractionSource mutableInteractionSource, Orientation orientation) {
        this.orientationLock = orientation;
        this.canDrag = function1;
        this.enabled = z;
        this.interactionSource = mutableInteractionSource;
        Offset.Companion companion = Offset.Companion;
        this.previousPositionOnScreen = companion.getUnspecified-F1C5BW0();
        this.nodeOffset = companion.getZero-F1C5BW0();
    }

    public static boolean a(DragGestureNode dragGestureNode, PointerType pointerType) {
        return ((Boolean) dragGestureNode.canDrag.invoke(pointerType)).booleanValue();
    }

    private final DragDetectionState.AwaitDown getAwaitDownState() {
        DragDetectionState.AwaitDown awaitDown = this._awaitDownState;
        if (awaitDown != null) {
            return awaitDown;
        }
        DragDetectionState.AwaitDown awaitDown2 = new DragDetectionState.AwaitDown(null, false, 3, null);
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

    private final SuspendingPointerInputModifierNode initializePointerInputNode() {
        return SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1());
    }

    private final void moveToAwaitDownState() {
        DragDetectionState.AwaitDown awaitDownState = getAwaitDownState();
        awaitDownState.setAwaitTouchSlop(DragDetectionState.AwaitDown.AwaitTouchSlop.NotInitialized);
        awaitDownState.setConsumedOnInitial(false);
        this.currentDragState = awaitDownState;
    }

    /* JADX INFO: renamed from: moveToAwaitGesturePickupState-rnUCldI, reason: not valid java name */
    private final void m576moveToAwaitGesturePickupStaternUCldI(PointerInputChange initialDown, long pointerId, TouchSlopDetector touchSlopDetector) {
        DragDetectionState.AwaitGesturePickup awaitGesturePickupState = getAwaitGesturePickupState();
        awaitGesturePickupState.setInitialDown(initialDown);
        awaitGesturePickupState.m547setPointerId0FcD4WY(pointerId);
        TouchSlopDetector.m717resetk4lQ0M$default(touchSlopDetector, 0L, 1, null);
        awaitGesturePickupState.setTouchSlopDetector(touchSlopDetector);
        this.currentDragState = awaitGesturePickupState;
    }

    /* JADX INFO: renamed from: moveToAwaitTouchSlopState-aWI9W7U, reason: not valid java name */
    private final void m577moveToAwaitTouchSlopStateaWI9W7U(PointerInputChange initialDown, long pointerId, long initialTouchSlopPositionChange, boolean verifyConsumptionInFinalPass) {
        DragDetectionState.AwaitTouchSlop awaitTouchSlopState = getAwaitTouchSlopState();
        awaitTouchSlopState.setInitialDown(initialDown);
        awaitTouchSlopState.m549setPointerId0FcD4WY(pointerId);
        TouchSlopDetector touchSlopDetector = this.touchSlopDetector;
        if (touchSlopDetector == null) {
            this.touchSlopDetector = new TouchSlopDetector(this.orientationLock, 0L, 2, null);
        } else {
            if (touchSlopDetector != null) {
                touchSlopDetector.setOrientation(this.orientationLock);
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
    public static /* synthetic */ void m578moveToAwaitTouchSlopStateaWI9W7U$default(DragGestureNode dragGestureNode, PointerInputChange pointerInputChange, long j, long j2, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: moveToAwaitTouchSlopState-aWI9W7U");
            return;
        }
        if ((i & 4) != 0) {
            j2 = Offset.Companion.getZero-F1C5BW0();
        }
        long j3 = j2;
        if ((i & 8) != 0) {
            z = false;
        }
        dragGestureNode.m577moveToAwaitTouchSlopStateaWI9W7U(pointerInputChange, j, j3, z);
    }

    /* JADX INFO: renamed from: moveToDraggingState-0FcD4WY, reason: not valid java name */
    private final void m579moveToDraggingState0FcD4WY(long pointerId) {
        DragDetectionState.Dragging draggingState = getDraggingState();
        draggingState.m551setPointerId0FcD4WY(pointerId);
        this.currentDragState = draggingState;
    }

    private final void processAwaitGesturePickup(PointerEvent pointerEvent, PointerEventPass pass, DragDetectionState.AwaitGesturePickup state) {
        boolean z;
        if (pass != PointerEventPass.Final) {
            return;
        }
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            } else {
                if (((PointerInputChange) changes.get(i)).isConsumed()) {
                    z = false;
                    break;
                }
                i++;
            }
        }
        List changes2 = pointerEvent.getChanges();
        int size2 = changes2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (((PointerInputChange) changes2.get(i2)).getPressed()) {
                if (pointerEvent.getChanges().isEmpty()) {
                    break;
                }
                if (z) {
                    long j = ((PointerInputChange) CollectionsKt.first(pointerEvent.getChanges())).getPosition-F1C5BW0();
                    PointerInputChange initialDown = state.getInitialDown();
                    initialDown.getClass();
                    long j2 = Offset.minus-MK-Hz9U(j, initialDown.getPosition-F1C5BW0());
                    PointerInputChange initialDown2 = state.getInitialDown();
                    if (initialDown2 != null) {
                        m578moveToAwaitTouchSlopStateaWI9W7U$default(this, initialDown2, state.getPointerId(), j2, false, 8, null);
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

    private final void processAwaitTouchSlop(PointerEvent pointerEvent, PointerEventPass pass, DragDetectionState.AwaitTouchSlop state) {
        Object obj;
        Object obj2;
        Object obj3;
        if (pass == PointerEventPass.Initial) {
            return;
        }
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        int i = 0;
        while (true) {
            obj = null;
            if (i >= size) {
                obj2 = null;
                break;
            }
            obj2 = changes.get(i);
            if (PointerId.equals-impl0(((PointerInputChange) obj2).getId-J3iCeTQ(), state.getPointerId())) {
                break;
            } else {
                i++;
            }
        }
        PointerInputChange pointerInputChange = (PointerInputChange) obj2;
        if (pointerInputChange == null) {
            List changes2 = pointerEvent.getChanges();
            int size2 = changes2.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size2) {
                    obj3 = null;
                    break;
                }
                obj3 = changes2.get(i2);
                if (((PointerInputChange) obj3).getPressed()) {
                    break;
                } else {
                    i2++;
                }
            }
            pointerInputChange = (PointerInputChange) obj3;
            if (pointerInputChange == null) {
                moveToAwaitDownState();
                return;
            }
            state.m549setPointerId0FcD4WY(pointerInputChange.getId-J3iCeTQ());
        }
        if (pass == PointerEventPass.Main) {
            if (pointerInputChange.isConsumed()) {
                PointerInputChange initialDown = state.getInitialDown();
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
                m576moveToAwaitGesturePickupStaternUCldI(initialDown, pointerId, touchSlopDetector);
            } else if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                List changes3 = pointerEvent.getChanges();
                int size3 = changes3.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    Object obj4 = changes3.get(i3);
                    if (((PointerInputChange) obj4).getPressed()) {
                        obj = obj4;
                        break;
                    }
                }
                PointerInputChange pointerInputChange2 = (PointerInputChange) obj;
                if (pointerInputChange2 == null) {
                    moveToAwaitDownState();
                } else {
                    state.m549setPointerId0FcD4WY(pointerInputChange2.getId-J3iCeTQ());
                }
            } else {
                long jM718addPositionsakrDWew = requireTouchSlopDetector().m718addPositionsakrDWew(pointerInputChange.getPosition-F1C5BW0(), pointerInputChange.getPreviousPosition-F1C5BW0(), DragGestureDetectorKt.m574pointerSlopE8SPZFQ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration()), pointerInputChange.getType-T8wyACA()));
                if ((SieveCacheKt.InvalidMapping & jM718addPositionsakrDWew) != 9205357640488583168L) {
                    pointerInputChange.consume();
                    PointerInputChange initialDown2 = state.getInitialDown();
                    initialDown2.getClass();
                    m581sendDragStart0AR0LA0(initialDown2, pointerInputChange, jM718addPositionsakrDWew);
                    m580sendDragEventUv8p0NA(pointerInputChange, jM718addPositionsakrDWew);
                    m579moveToDraggingState0FcD4WY(pointerInputChange.getId-J3iCeTQ());
                } else {
                    state.setVerifyConsumptionInFinalPass(true);
                }
            }
        }
        if (pass == PointerEventPass.Final && state.getVerifyConsumptionInFinalPass()) {
            if (!pointerInputChange.isConsumed()) {
                state.setVerifyConsumptionInFinalPass(false);
                return;
            }
            PointerInputChange initialDown3 = state.getInitialDown();
            if (initialDown3 == null) {
                w01.a("AwaitTouchSlop.initialDown was not initialized");
                return;
            }
            long pointerId2 = state.getPointerId();
            TouchSlopDetector touchSlopDetector2 = this.touchSlopDetector;
            if (touchSlopDetector2 != null) {
                m576moveToAwaitGesturePickupStaternUCldI(initialDown3, pointerId2, touchSlopDetector2);
            } else {
                w01.a("AwaitTouchSlop.touchSlopDetector was not initialized");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object processDragCancel(Continuation<? super Unit> continuation) {
        C01071 c01071;
        if (continuation instanceof C01071) {
            c01071 = (C01071) continuation;
            int i = c01071.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01071.label = i - Integer.MIN_VALUE;
            } else {
                c01071 = new C01071(continuation);
            }
        } else {
            c01071 = new C01071(continuation);
        }
        Object obj = c01071.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01071.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            DragInteraction.Start start = this.dragInteraction;
            if (start != null) {
                MutableInteractionSource mutableInteractionSource = this.interactionSource;
                if (mutableInteractionSource != null) {
                    DragInteraction.Cancel cancel = new DragInteraction.Cancel(start);
                    c01071.label = 1;
                    if (mutableInteractionSource.emit(cancel, c01071) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            onDragStopped(new DragEvent.DragStopped(Velocity.Companion.getZero-9UxMQ8M(), false, null));
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        this.dragInteraction = null;
        onDragStopped(new DragEvent.DragStopped(Velocity.Companion.getZero-9UxMQ8M(), false, null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object processDragStart(DragEvent.DragStarted dragStarted, Continuation<? super Unit> continuation) {
        C01081 c01081;
        MutableInteractionSource mutableInteractionSource;
        DragInteraction.Start start;
        DragEvent.DragStarted dragStarted2;
        DragInteraction.Start start2;
        if (continuation instanceof C01081) {
            c01081 = (C01081) continuation;
            int i = c01081.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01081.label = i - Integer.MIN_VALUE;
            } else {
                c01081 = new C01081(continuation);
            }
        } else {
            c01081 = new C01081(continuation);
        }
        Object obj = c01081.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01081.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            DragInteraction.Start start3 = this.dragInteraction;
            if (start3 != null && (mutableInteractionSource = this.interactionSource) != null) {
                DragInteraction.Cancel cancel = new DragInteraction.Cancel(start3);
                c01081.L$0 = dragStarted;
                c01081.label = 1;
                if (mutableInteractionSource.emit(cancel, c01081) != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            this.dragInteraction = start;
            mo521onDragStartedk4lQ0M(dragStarted.getStartPoint());
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            dragStarted = (DragEvent.DragStarted) c01081.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            start2 = (DragInteraction.Start) c01081.L$1;
            dragStarted2 = (DragEvent.DragStarted) c01081.L$0;
            ResultKt.throwOnFailure(obj);
        }
        start = start2;
        dragStarted = dragStarted2;
        this.dragInteraction = start;
        mo521onDragStartedk4lQ0M(dragStarted.getStartPoint());
        return Unit.INSTANCE;
        start = new DragInteraction.Start();
        MutableInteractionSource mutableInteractionSource2 = this.interactionSource;
        if (mutableInteractionSource2 != null) {
            c01081.L$0 = dragStarted;
            c01081.L$1 = start;
            c01081.label = 2;
            if (mutableInteractionSource2.emit(start, c01081) != coroutine_suspended) {
                dragStarted2 = dragStarted;
                start2 = start;
                start = start2;
                dragStarted = dragStarted2;
            }
            return coroutine_suspended;
        }
        this.dragInteraction = start;
        mo521onDragStartedk4lQ0M(dragStarted.getStartPoint());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object processDragStop(DragEvent.DragStopped dragStopped, Continuation<? super Unit> continuation) {
        C01091 c01091;
        if (continuation instanceof C01091) {
            c01091 = (C01091) continuation;
            int i = c01091.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01091.label = i - Integer.MIN_VALUE;
            } else {
                c01091 = new C01091(continuation);
            }
        } else {
            c01091 = new C01091(continuation);
        }
        Object obj = c01091.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01091.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            DragInteraction.Start start = this.dragInteraction;
            if (start != null) {
                MutableInteractionSource mutableInteractionSource = this.interactionSource;
                if (mutableInteractionSource != null) {
                    DragInteraction.Stop stop = new DragInteraction.Stop(start);
                    c01091.L$0 = dragStopped;
                    c01091.label = 1;
                    if (mutableInteractionSource.emit(stop, c01091) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            onDragStopped(dragStopped);
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        dragStopped = (DragEvent.DragStopped) c01091.L$0;
        ResultKt.throwOnFailure(obj);
        this.dragInteraction = null;
        onDragStopped(dragStopped);
        return Unit.INSTANCE;
    }

    private final void processDraggingState(PointerEvent pointerEvent, PointerEventPass pass, DragDetectionState.Dragging state) {
        Object obj;
        Object obj2;
        if (pass != PointerEventPass.Main) {
            return;
        }
        long pointerId = state.getPointerId();
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        int i = 0;
        while (true) {
            obj = null;
            if (i >= size) {
                obj2 = null;
                break;
            }
            obj2 = changes.get(i);
            if (PointerId.equals-impl0(((PointerInputChange) obj2).getId-J3iCeTQ(), pointerId)) {
                break;
            } else {
                i++;
            }
        }
        PointerInputChange pointerInputChange = (PointerInputChange) obj2;
        if (pointerInputChange == null) {
            return;
        }
        if (!PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
            if (pointerInputChange.isConsumed()) {
                sendDragCancelled();
                return;
            } else {
                if (Offset.getDistance-impl(PointerEventKt.positionChangeIgnoreConsumed(pointerInputChange)) == 0.0f) {
                    return;
                }
                m580sendDragEventUv8p0NA(pointerInputChange, PointerEventKt.positionChange(pointerInputChange));
                pointerInputChange.consume();
                return;
            }
        }
        List changes2 = pointerEvent.getChanges();
        int size2 = changes2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Object obj3 = changes2.get(i2);
            if (((PointerInputChange) obj3).getPressed()) {
                obj = obj3;
                break;
            }
        }
        PointerInputChange pointerInputChange2 = (PointerInputChange) obj;
        if (pointerInputChange2 != null) {
            state.m551setPointerId0FcD4WY(pointerInputChange2.getId-J3iCeTQ());
            return;
        }
        if (pointerInputChange.isConsumed() || !PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
            sendDragCancelled();
        } else {
            sendDragStopped(pointerInputChange);
        }
        moveToAwaitDownState();
    }

    private final void processInitialDownState(PointerEvent pointerEvent, PointerEventPass pass, DragDetectionState.AwaitDown state) {
        DragDetectionState.AwaitDown.AwaitTouchSlop awaitTouchSlop;
        if (!pointerEvent.getChanges().isEmpty() && TapGestureDetectorKt.isChangedToDown$default(pointerEvent, false, false, 2, null)) {
            PointerInputChange pointerInputChange = (PointerInputChange) CollectionsKt.first(pointerEvent.getChanges());
            if (WhenMappings.$EnumSwitchMapping$0[state.getAwaitTouchSlop().ordinal()] == 1) {
                awaitTouchSlop = !getStartDragImmediately() ? DragDetectionState.AwaitDown.AwaitTouchSlop.Yes : DragDetectionState.AwaitDown.AwaitTouchSlop.No;
            } else {
                awaitTouchSlop = state.getAwaitTouchSlop();
            }
            state.setAwaitTouchSlop(awaitTouchSlop);
            if (pass == PointerEventPass.Initial && awaitTouchSlop == DragDetectionState.AwaitDown.AwaitTouchSlop.No) {
                pointerInputChange.consume();
                state.setConsumedOnInitial(true);
            }
            if (pass == PointerEventPass.Main) {
                if (awaitTouchSlop == DragDetectionState.AwaitDown.AwaitTouchSlop.Yes) {
                    m578moveToAwaitTouchSlopStateaWI9W7U$default(this, pointerInputChange, pointerInputChange.getId-J3iCeTQ(), 0L, false, 12, null);
                } else if (state.getConsumedOnInitial()) {
                    Offset.Companion companion = Offset.Companion;
                    m581sendDragStart0AR0LA0(pointerInputChange, pointerInputChange, companion.getZero-F1C5BW0());
                    m580sendDragEventUv8p0NA(pointerInputChange, companion.getZero-F1C5BW0());
                    m579moveToDraggingState0FcD4WY(pointerInputChange.getId-J3iCeTQ());
                }
            }
        }
    }

    private final void processRawPointerEvent(PointerEvent pointerEvent, PointerEventPass pass) {
        DragDetectionState dragDetectionState = this.currentDragState;
        if (dragDetectionState == null) {
            w01.a("currentDragState should not be null");
            return;
        }
        if (dragDetectionState instanceof DragDetectionState.AwaitDown) {
            processInitialDownState(pointerEvent, pass, (DragDetectionState.AwaitDown) dragDetectionState);
            return;
        }
        if (dragDetectionState instanceof DragDetectionState.AwaitTouchSlop) {
            processAwaitTouchSlop(pointerEvent, pass, (DragDetectionState.AwaitTouchSlop) dragDetectionState);
            return;
        }
        if (dragDetectionState instanceof DragDetectionState.AwaitGesturePickup) {
            processAwaitGesturePickup(pointerEvent, pass, (DragDetectionState.AwaitGesturePickup) dragDetectionState);
        } else if (dragDetectionState instanceof DragDetectionState.Dragging) {
            processDraggingState(pointerEvent, pass, (DragDetectionState.Dragging) dragDetectionState);
        } else {
            bu8.a();
        }
    }

    private final Channel<DragEvent> requireChannel() {
        Channel<DragEvent> channel = this.channel;
        if (channel != null) {
            return channel;
        }
        w01.a("Events channel not initialized.");
        return null;
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

    private final void resetDragDetectionState() {
        moveToAwaitDownState();
        if (this.isListeningForEvents) {
            sendDragCancelled();
        }
        this.velocityTracker = null;
    }

    private final void sendDragCancelled() {
        requireChannel().trySend-JP2dKIU(DragEvent.DragCancelled.INSTANCE);
    }

    /* JADX INFO: renamed from: sendDragEvent-Uv8p0NA, reason: not valid java name */
    private final void m580sendDragEventUv8p0NA(PointerInputChange change, long dragAmount) {
        long jPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(getNode()));
        if (!Offset.equals-impl0(this.previousPositionOnScreen, Offset.Companion.getUnspecified-F1C5BW0()) && !Offset.equals-impl0(jPositionOnScreen, this.previousPositionOnScreen)) {
            this.nodeOffset = Offset.plus-MK-Hz9U(this.nodeOffset, Offset.minus-MK-Hz9U(jPositionOnScreen, this.previousPositionOnScreen));
        }
        this.previousPositionOnScreen = jPositionOnScreen;
        VelocityTrackerKt.addPointerInputChange-0AR0LA0(requireVelocityTracker(), change, this.nodeOffset);
        requireChannel().trySend-JP2dKIU(new DragEvent.DragDelta(dragAmount, false, null));
    }

    /* JADX INFO: renamed from: sendDragStart-0AR0LA0, reason: not valid java name */
    private final void m581sendDragStart0AR0LA0(PointerInputChange down, PointerInputChange slopTriggerChange, long overSlopOffset) {
        if (this.velocityTracker == null) {
            this.velocityTracker = new VelocityTracker();
        }
        VelocityTrackerKt.addPointerInputChange(requireVelocityTracker(), down);
        long j = Offset.minus-MK-Hz9U(slopTriggerChange.getPosition-F1C5BW0(), overSlopOffset);
        this.nodeOffset = Offset.Companion.getZero-F1C5BW0();
        if (((Boolean) this.canDrag.invoke(PointerType.box-impl(down.getType-T8wyACA()))).booleanValue()) {
            if (!this.isListeningForEvents) {
                if (this.channel == null) {
                    this.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
                }
                startListeningForEvents();
            }
            this.previousPositionOnScreen = LayoutCoordinatesKt.positionOnScreen(DelegatableNodeKt.requireLayoutCoordinates(this));
            requireChannel().trySend-JP2dKIU(new DragEvent.DragStarted(j, null));
        }
    }

    private final void sendDragStopped(PointerInputChange change) {
        VelocityTrackerKt.addPointerInputChange(requireVelocityTracker(), change);
        float maximumFlingVelocity = ((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration())).getMaximumFlingVelocity();
        long j = requireVelocityTracker().calculateVelocity-AH228Gc(VelocityKt.Velocity(maximumFlingVelocity, maximumFlingVelocity));
        requireVelocityTracker().resetTracking();
        requireChannel().trySend-JP2dKIU(new DragEvent.DragStopped(DraggableKt.m589toValidVelocityTH1AsA0(j), false, null));
        this.isListeningForPointerInputEvents = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startListeningForEvents() {
        this.isListeningForEvents = true;
        if (this.channel == null) {
            this.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        }
        BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new C01101(null), 3, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void update$default(DragGestureNode dragGestureNode, Function1 function1, boolean z, MutableInteractionSource mutableInteractionSource, Orientation orientation, boolean z2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: update");
            return;
        }
        if ((i & 1) != 0) {
            function1 = dragGestureNode.canDrag;
        }
        if ((i & 2) != 0) {
            z = dragGestureNode.enabled;
        }
        if ((i & 4) != 0) {
            mutableInteractionSource = dragGestureNode.interactionSource;
        }
        if ((i & 8) != 0) {
            orientation = dragGestureNode.orientationLock;
        }
        if ((i & 16) != 0) {
            z2 = false;
        }
        boolean z3 = z2;
        MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
        Function1 function2 = function1;
        dragGestureNode.update(function2, z, mutableInteractionSource2, orientation, z3);
    }

    public final void disposeInteractionSource() {
        DragInteraction.Start start = this.dragInteraction;
        if (start != null) {
            MutableInteractionSource mutableInteractionSource = this.interactionSource;
            if (mutableInteractionSource != null) {
                mutableInteractionSource.tryEmit(new DragInteraction.Cancel(start));
            }
            this.dragInteraction = null;
        }
    }

    public abstract Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation);

    public final Function1<PointerType, Boolean> getCanDrag() {
        return this.canDrag;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final MutableInteractionSource getInteractionSource() {
        return this.interactionSource;
    }

    public final Orientation getOrientationLock() {
        return this.orientationLock;
    }

    /* JADX INFO: renamed from: isListeningForEvents$foundation, reason: from getter */
    public final boolean getIsListeningForEvents() {
        return this.isListeningForEvents;
    }

    /* JADX INFO: renamed from: isListeningForPointerInputEvents$foundation, reason: from getter */
    public final boolean getIsListeningForPointerInputEvents() {
        return this.isListeningForPointerInputEvents;
    }

    public void onCancelIndirectPointerInput() {
        IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector = this.indirectPointerInputDragCycleDetector;
        if (indirectPointerInputDragCycleDetector != null) {
            indirectPointerInputDragCycleDetector.resetDragDetectionState();
        }
    }

    public void onCancelPointerInput() {
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.onCancelPointerInput();
        }
        if (ComposeFoundationFlags.isNonSuspendingPointerInputInDraggableEnabled && this.isListeningForPointerInputEvents) {
            resetDragDetectionState();
        }
        this.isListeningForPointerInputEvents = false;
    }

    public void onDetach() {
        this.isListeningForEvents = false;
        disposeInteractionSource();
        this.nodeOffset = Offset.Companion.getZero-F1C5BW0();
    }

    public final void onDragEvent(DragEvent event) {
        if ((event instanceof DragEvent.DragStarted) && !this.isListeningForEvents) {
            this.isListeningForEvents = true;
            startListeningForEvents();
        }
        requireChannel().trySend-JP2dKIU(event);
    }

    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M */
    public abstract void mo521onDragStartedk4lQ0M(long startedPosition);

    public abstract void onDragStopped(DragEvent.DragStopped event);

    public void onIndirectPointerEvent(IndirectPointerEvent event, PointerEventPass pass) {
        if (this.enabled) {
            if (this.indirectPointerInputDragCycleDetector == null) {
                this.indirectPointerInputDragCycleDetector = new IndirectPointerInputDragCycleDetector(this);
            }
            IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector = this.indirectPointerInputDragCycleDetector;
            if (indirectPointerInputDragCycleDetector != null) {
                indirectPointerInputDragCycleDetector.processIndirectPointerInputEvent(event, pass);
            }
        }
    }

    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public void mo582onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.isListeningForPointerInputEvents = true;
        boolean z = ComposeFoundationFlags.isNonSuspendingPointerInputInDraggableEnabled;
        boolean z2 = this.enabled;
        if (z) {
            if (z2) {
                if (this.currentDragState == null) {
                    this.currentDragState = getAwaitDownState();
                }
                processRawPointerEvent(pointerEvent, pass);
                return;
            }
            return;
        }
        if (z2 && this.pointerInputNode == null) {
            this.pointerInputNode = delegate(initializePointerInputNode());
        }
        SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
        if (suspendingPointerInputModifierNode != null) {
            suspendingPointerInputModifierNode.onPointerEvent-H0pRuoY(pointerEvent, pass, bounds);
        }
    }

    public final void setListeningForEvents$foundation(boolean z) {
        this.isListeningForEvents = z;
    }

    public final void setListeningForPointerInputEvents$foundation(boolean z) {
        this.isListeningForPointerInputEvents = z;
    }

    public final void setOrientationLock(Orientation orientation) {
        this.orientationLock = orientation;
    }

    /* JADX INFO: renamed from: startDragImmediately */
    public abstract boolean getStartDragImmediately();

    public final void update(Function1<? super PointerType, Boolean> canDrag, boolean enabled, MutableInteractionSource interactionSource, Orientation orientationLock, boolean shouldResetPointerInputHandling) {
        this.canDrag = canDrag;
        boolean z = true;
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (!enabled) {
                disposeInteractionSource();
                SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = this.pointerInputNode;
                if (suspendingPointerInputModifierNode != null) {
                    undelegate(suspendingPointerInputModifierNode);
                }
                this.pointerInputNode = null;
                this.indirectPointerInputDragCycleDetector = null;
            }
            shouldResetPointerInputHandling = true;
        }
        if (!Intrinsics.areEqual(this.interactionSource, interactionSource)) {
            disposeInteractionSource();
            this.interactionSource = interactionSource;
        }
        if (this.orientationLock != orientationLock) {
            this.orientationLock = orientationLock;
        } else {
            z = shouldResetPointerInputHandling;
        }
        if (z) {
            if (ComposeFoundationFlags.isNonSuspendingPointerInputInDraggableEnabled && this.isListeningForPointerInputEvents) {
                resetDragDetectionState();
            }
            IndirectPointerInputDragCycleDetector indirectPointerInputDragCycleDetector = this.indirectPointerInputDragCycleDetector;
            if (indirectPointerInputDragCycleDetector != null) {
                indirectPointerInputDragCycleDetector.resetDragDetectionState();
            }
            SuspendingPointerInputModifierNode suspendingPointerInputModifierNode2 = this.pointerInputNode;
            if (suspendingPointerInputModifierNode2 != null) {
                suspendingPointerInputModifierNode2.resetPointerInputHandler();
            }
        }
    }
}
