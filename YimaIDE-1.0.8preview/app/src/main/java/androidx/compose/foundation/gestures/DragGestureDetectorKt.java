package androidx.compose.foundation.gestures;

import androidx.collection.ScatterMapKt;
import androidx.collection.SieveCacheKt;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aV\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000426\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b\r\u0010\u000e\u001a0\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u0012H\u0086@¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0080\u0001\u0010\u0018\u001a\u00020\f*\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010\u001f\u001aè\u0001\u0010\u0018\u001a\u00020\f*\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010!2M\b\u0002\u0010\u001a\u001aG\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\f0\"2#\b\u0002\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010'\u001a\u0080\u0001\u0010(\u001a\u00020\f*\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010\u001f\u001aV\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000426\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b+\u0010\u000e\u001a^\u0010,\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.26\u0010/\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b0\u00101\u001a0\u00102\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u0012H\u0086@¢\u0006\u0004\b3\u0010\u0014\u001a\u001e\u00104\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b5\u0010\u0017\u001a\u0080\u0001\u00106\u001a\u00020\f*\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c26\u00107\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010\u001f\u001aV\u00108\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000426\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b9\u0010\u000e\u001a^\u0010:\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.26\u0010/\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0004\b;\u00101\u001a0\u0010<\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u0012H\u0086@¢\u0006\u0004\b=\u0010\u0014\u001a\u001e\u0010>\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0004\b?\u0010\u0017\u001a\u0080\u0001\u0010@\u001a\u00020\f*\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u00122\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u001c26\u0010A\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\f0\u0006H\u0086@¢\u0006\u0002\u0010\u001f\u001aP\u0010\u000f\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\f0\u00122\b\u0010B\u001a\u0004\u0018\u00010!2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\u0012H\u0080H¢\u0006\u0004\bD\u0010E\u001a2\u0010F\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\u0012H\u0082H¢\u0006\u0004\bH\u0010\u0014\u001aT\u0010I\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010-\u001a\u00020.2\b\u0010B\u001a\u0004\u0018\u00010!2\b\b\u0002\u0010J\u001a\u00020\n2\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0\u0006H\u0080H¢\u0006\u0004\bK\u0010L\u001a$\u0010M\u001a\u00020\u0010*\u00020\u00022\u0006\u0010J\u001a\u00020\u00012\b\b\u0002\u0010N\u001a\u00020OH\u0080@¢\u0006\u0002\u0010P\u001a\u001e\u0010Q\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@¢\u0006\u0004\bR\u0010\u0017\u001a\u001b\u0010S\u001a\u00020\u0010*\u00020T2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¢\u0006\u0004\bU\u0010V\u001a\u001b\u0010\\\u001a\u00020**\u00020]2\u0006\u0010-\u001a\u00020.H\u0000¢\u0006\u0004\b^\u0010_\"\u0010\u0010W\u001a\u00020XX\u0082\u0004¢\u0006\u0004\n\u0002\u0010Y\"\u0010\u0010Z\u001a\u00020XX\u0082\u0004¢\u0006\u0004\n\u0002\u0010Y\"\u000e\u0010[\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006`"}, d2 = {"awaitTouchSlopOrCancellation", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "onTouchSlopReached", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "change", "Landroidx/compose/ui/geometry/Offset;", "overSlop", "", "awaitTouchSlopOrCancellation-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "drag", "", "onDrag", "Lkotlin/Function1;", "drag-jO51t88", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDragOrCancellation", "awaitDragOrCancellation-rnUCldI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDragGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDragStart", "onDragEnd", "Lkotlin/Function0;", "onDragCancel", "dragAmount", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "orientationLock", "Landroidx/compose/foundation/gestures/Orientation;", "Lkotlin/Function3;", "down", "slopTriggerChange", "overSlopOffset", "shouldAwaitTouchSlop", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDragGesturesAfterLongPress", "awaitVerticalTouchSlopOrCancellation", "", "awaitVerticalTouchSlopOrCancellation-jO51t88", "awaitVerticalPointerSlopOrCancellation", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "onPointerSlopReached", "awaitVerticalPointerSlopOrCancellation-gDDlDlE", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verticalDrag", "verticalDrag-jO51t88", "awaitVerticalDragOrCancellation", "awaitVerticalDragOrCancellation-rnUCldI", "detectVerticalDragGestures", "onVerticalDrag", "awaitHorizontalTouchSlopOrCancellation", "awaitHorizontalTouchSlopOrCancellation-jO51t88", "awaitHorizontalPointerSlopOrCancellation", "awaitHorizontalPointerSlopOrCancellation-gDDlDlE", "horizontalDrag", "horizontalDrag-jO51t88", "awaitHorizontalDragOrCancellation", "awaitHorizontalDragOrCancellation-rnUCldI", "detectHorizontalDragGestures", "onHorizontalDrag", "orientation", "motionConsumed", "drag-VnAYq1g", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JLkotlin/jvm/functions/Function1;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDragOrUp", "hasDragged", "awaitDragOrUp-jO51t88", "awaitPointerSlopOrCancellation", "initialPositionChange", "awaitPointerSlopOrCancellation-6ksA65w", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILandroidx/compose/foundation/gestures/Orientation;JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitAllPointersUpWithSlopDetection", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerInputChange;Landroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitLongPressOrCancellation", "awaitLongPressOrCancellation-rnUCldI", "isPointerUp", "Landroidx/compose/ui/input/pointer/PointerEvent;", "isPointerUp-DmW0f2w", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)Z", "mouseSlop", "Landroidx/compose/ui/unit/Dp;", "F", "defaultTouchSlop", "mouseToTouchSlopRatio", "pointerSlop", "Landroidx/compose/ui/platform/ViewConfiguration;", "pointerSlop-E8SPZFQ", "(Landroidx/compose/ui/platform/ViewConfiguration;I)F", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DragGestureDetectorKt {
    private static final float defaultTouchSlop;
    private static final float mouseSlop;
    private static final float mouseToTouchSlopRatio;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$awaitAllPointersUpWithSlopDetection$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt", f = "DragGestureDetector.kt", i = {0, 0, 0, 0, 0, 0}, l = {897}, m = "awaitAllPointersUpWithSlopDetection", n = {"$this$awaitAllPointersUpWithSlopDetection", "pass", "pointer", "touchSlopDetector", "pointerSlopReached", "touchSlop"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "F$0"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DragGestureDetectorKt.awaitAllPointersUpWithSlopDetection(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGestures$13", f = "DragGestureDetector.kt", i = {0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.ClonedMetadataCount, ScatterMapKt.ClonedMetadataCount, ScatterMapKt.ClonedMetadataCount}, l = {248, 254, 1116, 1158, 279, 1197, 1240, 1252}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "initialDown", "awaitTouchSlop", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default$iv", "pointer$iv", "touchSlopDetector$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w_u24default$iv", "pointer$iv", "touchSlopDetector$iv", "dragEvent$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "drag", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w$iv", "pointer$iv", "touchSlopDetector$iv", "touchSlop$iv", "$this$awaitEachGesture", "down", "$this$awaitPointerSlopOrCancellation_u2d6ksA65w$iv", "pointer$iv", "touchSlopDetector$iv", "dragEvent$iv", "touchSlop$iv", "$this$drag_u2dVnAYq1g$iv", "$this$awaitDragOrUp_u2djO51t88$iv$iv", "pointer$iv$iv"}, s = {"L$0", "L$0", "L$1", "Z$0", "L$0", "L$1", "L$2", "L$4", "L$5", "F$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "F$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4", "L$5", "F$0", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "F$0", "L$0", "L$2", "L$3"}, v = 1)
    public static final class AnonymousClass13 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function1<PointerInputChange, Unit> $onDragEnd;
        final /* synthetic */ Function3<PointerInputChange, PointerInputChange, Offset, Unit> $onDragStart;
        final /* synthetic */ Orientation $orientationLock;
        final /* synthetic */ Ref.LongRef $overSlop;
        final /* synthetic */ Function0<Boolean> $shouldAwaitTouchSlop;
        float F$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass13(Function0<Boolean> function0, Ref.LongRef longRef, Orientation orientation, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Function0<Unit> function1, Function1<? super PointerInputChange, Unit> function4, Continuation<? super AnonymousClass13> continuation) {
            super(2, continuation);
            this.$shouldAwaitTouchSlop = function0;
            this.$overSlop = longRef;
            this.$orientationLock = orientation;
            this.$onDragStart = function3;
            this.$onDrag = function2;
            this.$onDragCancel = function1;
            this.$onDragEnd = function4;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass13 anonymousClass13 = new AnonymousClass13(this.$shouldAwaitTouchSlop, this.$overSlop, this.$orientationLock, this.$onDragStart, this.$onDrag, this.$onDragCancel, this.$onDragEnd, continuation);
            anonymousClass13.L$0 = obj;
            return anonymousClass13;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:234:0x021b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:37:0x0200  */
        /* JADX WARN: Code duplicated, block: B:40:0x0215 A[LOOP:8: B:36:0x01fe->B:40:0x0215, LOOP_END] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:155:0x049a -> B:156:0x04a1). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:161:0x04b8 -> B:82:0x02d2). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:169:0x0513 -> B:171:0x0516). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x01b9 -> B:76:0x02be). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0220 -> B:45:0x0221). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x0282 -> B:76:0x02be). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x02af -> B:73:0x02b4). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:98:0x0343 -> B:88:0x02f3). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r27) {
            /*
                Method dump skipped, instruction units count: 1506
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.AnonymousClass13.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5", f = "DragGestureDetector.kt", i = {0, 1, 2}, l = {359, 360, 365}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "$this$awaitEachGesture"}, s = {"L$0", "L$0", "L$0"}, v = 1)
    public static final class AnonymousClass5 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<PointerInputChange, Offset, Unit> $onDrag;
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onDragEnd = function0;
            this.$onDragCancel = function2;
            this.$onDrag = function3;
        }

        public static Unit b(Function2 function2, PointerInputChange pointerInputChange) {
            function2.invoke(pointerInputChange, Offset.box-impl(PointerEventKt.positionChange(pointerInputChange)));
            pointerInputChange.consume();
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$onDragStart, this.$onDragEnd, this.$onDragCancel, this.$onDrag, continuation);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:29:0x0062  */
        /* JADX WARN: Code duplicated, block: B:30:0x0063 A[Catch: CancellationException -> 0x00b5, PHI: r1 r8 r12
          0x0063: PHI (r1v7 androidx.compose.ui.input.pointer.AwaitPointerEventScope) = 
          (r1v4 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
          (r1v8 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
         binds: [B:16:0x002e, B:28:0x0060] A[DONT_GENERATE, DONT_INLINE]
          0x0063: PHI (r8v6 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5) = 
          (r8v2 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5)
          (r8v7 androidx.compose.foundation.gestures.DragGestureDetectorKt$detectDragGesturesAfterLongPress$5)
         binds: [B:16:0x002e, B:28:0x0060] A[DONT_GENERATE, DONT_INLINE]
          0x0063: PHI (r12v8 java.lang.Object) = (r12v0 java.lang.Object), (r12v13 java.lang.Object) binds: [B:16:0x002e, B:28:0x0060] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {CancellationException -> 0x00b5, blocks: (B:36:0x008b, B:38:0x0093, B:40:0x00a5, B:42:0x00b1, B:45:0x00b8, B:46:0x00bb, B:47:0x00c1, B:30:0x0063, B:32:0x0067, B:27:0x0052, B:23:0x004a), top: B:57:0x004a }] */
        /* JADX WARN: Code duplicated, block: B:32:0x0067 A[Catch: CancellationException -> 0x00b5, TryCatch #2 {CancellationException -> 0x00b5, blocks: (B:36:0x008b, B:38:0x0093, B:40:0x00a5, B:42:0x00b1, B:45:0x00b8, B:46:0x00bb, B:47:0x00c1, B:30:0x0063, B:32:0x0067, B:27:0x0052, B:23:0x004a), top: B:57:0x004a }] */
        /* JADX WARN: Code duplicated, block: B:35:0x008a  */
        /* JADX WARN: Code duplicated, block: B:38:0x0093 A[Catch: CancellationException -> 0x00b5, TryCatch #2 {CancellationException -> 0x00b5, blocks: (B:36:0x008b, B:38:0x0093, B:40:0x00a5, B:42:0x00b1, B:45:0x00b8, B:46:0x00bb, B:47:0x00c1, B:30:0x0063, B:32:0x0067, B:27:0x0052, B:23:0x004a), top: B:57:0x004a }] */
        /* JADX WARN: Code duplicated, block: B:40:0x00a5 A[Catch: CancellationException -> 0x00b5, TryCatch #2 {CancellationException -> 0x00b5, blocks: (B:36:0x008b, B:38:0x0093, B:40:0x00a5, B:42:0x00b1, B:45:0x00b8, B:46:0x00bb, B:47:0x00c1, B:30:0x0063, B:32:0x0067, B:27:0x0052, B:23:0x004a), top: B:57:0x004a }] */
        /* JADX WARN: Code duplicated, block: B:42:0x00b1 A[Catch: CancellationException -> 0x00b5, TryCatch #2 {CancellationException -> 0x00b5, blocks: (B:36:0x008b, B:38:0x0093, B:40:0x00a5, B:42:0x00b1, B:45:0x00b8, B:46:0x00bb, B:47:0x00c1, B:30:0x0063, B:32:0x0067, B:27:0x0052, B:23:0x004a), top: B:57:0x004a }] */
        /* JADX WARN: Code duplicated, block: B:47:0x00c1 A[Catch: CancellationException -> 0x00b5, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x00b5, blocks: (B:36:0x008b, B:38:0x0093, B:40:0x00a5, B:42:0x00b1, B:45:0x00b8, B:46:0x00bb, B:47:0x00c1, B:30:0x0063, B:32:0x0067, B:27:0x0052, B:23:0x004a), top: B:57:0x004a }] */
        /* JADX WARN: Code duplicated, block: B:59:0x00b8 A[SYNTHETIC] */
        public final Object invokeSuspend(Object obj) {
            CancellationException cancellationException;
            AnonymousClass5 anonymousClass5;
            AwaitPointerEventScope awaitPointerEventScope;
            AwaitPointerEventScope awaitPointerEventScope2;
            List changes;
            int size;
            int i;
            PointerInputChange pointerInputChange;
            PointerInputChange pointerInputChange2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 != 0) {
                try {
                    if (i2 == 1) {
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        anonymousClass5 = this;
                        long j = ((PointerInputChange) obj).getId-J3iCeTQ();
                        anonymousClass5.L$0 = awaitPointerEventScope;
                        anonymousClass5.label = 2;
                        obj = DragGestureDetectorKt.m561awaitLongPressOrCancellationrnUCldI(awaitPointerEventScope, j, anonymousClass5);
                        if (obj == coroutine_suspended) {
                            pointerInputChange2 = (PointerInputChange) obj;
                            if (pointerInputChange2 != null) {
                                anonymousClass5.$onDragStart.invoke(Offset.box-impl(pointerInputChange2.getPosition-F1C5BW0()));
                                long j2 = pointerInputChange2.getId-J3iCeTQ();
                                final Function2<PointerInputChange, Offset, Unit> function2 = anonymousClass5.$onDrag;
                                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.k
                                    public final Object invoke(Object obj2) {
                                        return DragGestureDetectorKt.AnonymousClass5.b(function2, (PointerInputChange) obj2);
                                    }
                                };
                                anonymousClass5.L$0 = awaitPointerEventScope;
                                anonymousClass5.label = 3;
                                obj = DragGestureDetectorKt.m571dragjO51t88(awaitPointerEventScope, j2, function1, anonymousClass5);
                                if (obj != coroutine_suspended) {
                                    awaitPointerEventScope2 = awaitPointerEventScope;
                                }
                            }
                        }
                        return coroutine_suspended;
                    }
                    if (i2 == 2) {
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        anonymousClass5 = this;
                        pointerInputChange2 = (PointerInputChange) obj;
                        if (pointerInputChange2 != null) {
                            anonymousClass5.$onDragStart.invoke(Offset.box-impl(pointerInputChange2.getPosition-F1C5BW0()));
                            long j3 = pointerInputChange2.getId-J3iCeTQ();
                            final Function2 function3 = anonymousClass5.$onDrag;
                            Function1 function4 = new Function1() { // from class: androidx.compose.foundation.gestures.k
                                public final Object invoke(Object obj2) {
                                    return DragGestureDetectorKt.AnonymousClass5.b(function3, (PointerInputChange) obj2);
                                }
                            };
                            anonymousClass5.L$0 = awaitPointerEventScope;
                            anonymousClass5.label = 3;
                            obj = DragGestureDetectorKt.m571dragjO51t88(awaitPointerEventScope, j3, function4, anonymousClass5);
                            if (obj != coroutine_suspended) {
                                awaitPointerEventScope2 = awaitPointerEventScope;
                            }
                            return coroutine_suspended;
                        }
                    } else {
                        if (i2 != 3) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        anonymousClass5 = this;
                    }
                    if (((Boolean) obj).booleanValue()) {
                        changes = awaitPointerEventScope2.getCurrentEvent().getChanges();
                        size = changes.size();
                        for (i = 0; i < size; i++) {
                            pointerInputChange = (PointerInputChange) changes.get(i);
                            if (PointerEventKt.changedToUp(pointerInputChange)) {
                                pointerInputChange.consume();
                            }
                        }
                        anonymousClass5.$onDragEnd.invoke();
                    } else {
                        anonymousClass5.$onDragCancel.invoke();
                    }
                } catch (CancellationException e) {
                    cancellationException = e;
                    anonymousClass5 = this;
                    anonymousClass5.$onDragCancel.invoke();
                    throw cancellationException;
                }
            } else {
                ResultKt.throwOnFailure(obj);
                AwaitPointerEventScope awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                try {
                    this.L$0 = awaitPointerEventScope3;
                    this.label = 1;
                    anonymousClass5 = this;
                    try {
                        obj = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope3, false, null, anonymousClass5, 2, null);
                        if (obj != coroutine_suspended) {
                            awaitPointerEventScope = awaitPointerEventScope3;
                            long j4 = ((PointerInputChange) obj).getId-J3iCeTQ();
                            anonymousClass5.L$0 = awaitPointerEventScope;
                            anonymousClass5.label = 2;
                            obj = DragGestureDetectorKt.m561awaitLongPressOrCancellationrnUCldI(awaitPointerEventScope, j4, anonymousClass5);
                            if (obj == coroutine_suspended) {
                                pointerInputChange2 = (PointerInputChange) obj;
                                if (pointerInputChange2 != null) {
                                    anonymousClass5.$onDragStart.invoke(Offset.box-impl(pointerInputChange2.getPosition-F1C5BW0()));
                                    long j5 = pointerInputChange2.getId-J3iCeTQ();
                                    final Function2 function5 = anonymousClass5.$onDrag;
                                    Function1 function6 = new Function1() { // from class: androidx.compose.foundation.gestures.k
                                        public final Object invoke(Object obj2) {
                                            return DragGestureDetectorKt.AnonymousClass5.b(function5, (PointerInputChange) obj2);
                                        }
                                    };
                                    anonymousClass5.L$0 = awaitPointerEventScope;
                                    anonymousClass5.label = 3;
                                    obj = DragGestureDetectorKt.m571dragjO51t88(awaitPointerEventScope, j5, function6, anonymousClass5);
                                    if (obj != coroutine_suspended) {
                                        awaitPointerEventScope2 = awaitPointerEventScope;
                                        if (((Boolean) obj).booleanValue()) {
                                            changes = awaitPointerEventScope2.getCurrentEvent().getChanges();
                                            size = changes.size();
                                            while (i < size) {
                                                pointerInputChange = (PointerInputChange) changes.get(i);
                                                if (PointerEventKt.changedToUp(pointerInputChange)) {
                                                    pointerInputChange.consume();
                                                }
                                            }
                                            anonymousClass5.$onDragEnd.invoke();
                                        } else {
                                            anonymousClass5.$onDragCancel.invoke();
                                        }
                                    }
                                }
                            }
                        }
                        return coroutine_suspended;
                    } catch (CancellationException e2) {
                        e = e2;
                        cancellationException = e;
                        anonymousClass5.$onDragCancel.invoke();
                        throw cancellationException;
                    }
                } catch (CancellationException e3) {
                    e = e3;
                    anonymousClass5 = this;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectHorizontalDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1}, l = {702, 705, 713}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "overSlop"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    public static final class C01055 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        final /* synthetic */ Function2<PointerInputChange, Float, Unit> $onHorizontalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01055(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Float, Unit> function2, Function0<Unit> function0, Function0<Unit> function3, Continuation<? super C01055> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onHorizontalDrag = function2;
            this.$onDragEnd = function0;
            this.$onDragCancel = function3;
        }

        public static Unit b(Ref.FloatRef floatRef, PointerInputChange pointerInputChange, float f) {
            pointerInputChange.consume();
            floatRef.element = f;
            return Unit.INSTANCE;
        }

        public static Unit d(Function2 function2, PointerInputChange pointerInputChange) {
            function2.invoke(pointerInputChange, Float.valueOf(Float.intBitsToFloat((int) (PointerEventKt.positionChange(pointerInputChange) >> 32))));
            pointerInputChange.consume();
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01055 c01055 = new C01055(this.$onDragStart, this.$onHorizontalDrag, this.$onDragEnd, this.$onDragCancel, continuation);
            c01055.L$0 = obj;
            return c01055;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0076  */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00a3, code lost:
        
            if (r14 == r0) goto L24;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            C01055 c01055;
            AwaitPointerEventScope awaitPointerEventScope;
            final Ref.FloatRef floatRef;
            C01055 c01056;
            AwaitPointerEventScope awaitPointerEventScope2;
            PointerInputChange pointerInputChange;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AwaitPointerEventScope awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                this.L$0 = awaitPointerEventScope3;
                this.label = 1;
                c01055 = this;
                obj = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope3, false, null, c01055, 2, null);
                if (obj != coroutine_suspended) {
                    awaitPointerEventScope = awaitPointerEventScope3;
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                AwaitPointerEventScope awaitPointerEventScope4 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c01055 = this;
                awaitPointerEventScope = awaitPointerEventScope4;
            } else {
                if (i == 2) {
                    floatRef = (Ref.FloatRef) this.L$1;
                    awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c01056 = this;
                    pointerInputChange = (PointerInputChange) obj;
                    if (pointerInputChange != null) {
                        c01056.$onDragStart.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                        c01056.$onHorizontalDrag.invoke(pointerInputChange, Boxing.boxFloat(floatRef.element));
                        long j = pointerInputChange.getId-J3iCeTQ();
                        final Function2<PointerInputChange, Float, Unit> function2 = c01056.$onHorizontalDrag;
                        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.m
                            public final Object invoke(Object obj2) {
                                return DragGestureDetectorKt.C01055.d(function2, (PointerInputChange) obj2);
                            }
                        };
                        c01056.L$0 = null;
                        c01056.L$1 = null;
                        c01056.label = 3;
                        obj = DragGestureDetectorKt.m572horizontalDragjO51t88(awaitPointerEventScope2, j, function1, c01056);
                    }
                    return Unit.INSTANCE;
                }
                if (i != 3) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                c01056 = this;
            }
            if (((Boolean) obj).booleanValue()) {
                c01056.$onDragEnd.invoke();
            } else {
                c01056.$onDragCancel.invoke();
            }
            return Unit.INSTANCE;
            PointerInputChange pointerInputChange2 = (PointerInputChange) obj;
            floatRef = new Ref.FloatRef();
            C01055 c01057 = c01055;
            long j2 = pointerInputChange2.getId-J3iCeTQ();
            int i2 = pointerInputChange2.getType-T8wyACA();
            Function2 function3 = new Function2() { // from class: androidx.compose.foundation.gestures.l
                public final Object invoke(Object obj2, Object obj3) {
                    return DragGestureDetectorKt.C01055.b(floatRef, (PointerInputChange) obj2, ((Float) obj3).floatValue());
                }
            };
            c01057.L$0 = awaitPointerEventScope;
            c01057.L$1 = floatRef;
            c01057.label = 2;
            obj = DragGestureDetectorKt.m559awaitHorizontalPointerSlopOrCancellationgDDlDlE(awaitPointerEventScope, j2, i2, function3, c01057);
            c01056 = c01057;
            if (obj != coroutine_suspended) {
                awaitPointerEventScope2 = awaitPointerEventScope;
                pointerInputChange = (PointerInputChange) obj;
                if (pointerInputChange != null) {
                    c01056.$onDragStart.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                    c01056.$onHorizontalDrag.invoke(pointerInputChange, Boxing.boxFloat(floatRef.element));
                    long j3 = pointerInputChange.getId-J3iCeTQ();
                    final Function2 function4 = c01056.$onHorizontalDrag;
                    Function1 function5 = new Function1() { // from class: androidx.compose.foundation.gestures.m
                        public final Object invoke(Object obj2) {
                            return DragGestureDetectorKt.C01055.d(function4, (PointerInputChange) obj2);
                        }
                    };
                    c01056.L$0 = null;
                    c01056.L$1 = null;
                    c01056.label = 3;
                    obj = DragGestureDetectorKt.m572horizontalDragjO51t88(awaitPointerEventScope2, j3, function5, c01056);
                }
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DragGestureDetectorKt$detectVerticalDragGestures$5", f = "DragGestureDetector.kt", i = {0, 1, 1}, l = {533, 536, 544}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "overSlop"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    public static final class C01065 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<Unit> $onDragCancel;
        final /* synthetic */ Function0<Unit> $onDragEnd;
        final /* synthetic */ Function1<Offset, Unit> $onDragStart;
        final /* synthetic */ Function2<PointerInputChange, Float, Unit> $onVerticalDrag;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01065(Function1<? super Offset, Unit> function1, Function2<? super PointerInputChange, ? super Float, Unit> function2, Function0<Unit> function0, Function0<Unit> function3, Continuation<? super C01065> continuation) {
            super(2, continuation);
            this.$onDragStart = function1;
            this.$onVerticalDrag = function2;
            this.$onDragEnd = function0;
            this.$onDragCancel = function3;
        }

        public static Unit b(Ref.FloatRef floatRef, PointerInputChange pointerInputChange, float f) {
            pointerInputChange.consume();
            floatRef.element = f;
            return Unit.INSTANCE;
        }

        public static Unit d(Function2 function2, PointerInputChange pointerInputChange) {
            function2.invoke(pointerInputChange, Float.valueOf(Float.intBitsToFloat((int) (PointerEventKt.positionChange(pointerInputChange) & 4294967295L))));
            pointerInputChange.consume();
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01065 c01065 = new C01065(this.$onDragStart, this.$onVerticalDrag, this.$onDragEnd, this.$onDragCancel, continuation);
            c01065.L$0 = obj;
            return c01065;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0076  */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00a3, code lost:
        
            if (r14 == r0) goto L24;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            C01065 c01065;
            AwaitPointerEventScope awaitPointerEventScope;
            final Ref.FloatRef floatRef;
            C01065 c01066;
            AwaitPointerEventScope awaitPointerEventScope2;
            PointerInputChange pointerInputChange;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AwaitPointerEventScope awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                this.L$0 = awaitPointerEventScope3;
                this.label = 1;
                c01065 = this;
                obj = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope3, false, null, c01065, 2, null);
                if (obj != coroutine_suspended) {
                    awaitPointerEventScope = awaitPointerEventScope3;
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                AwaitPointerEventScope awaitPointerEventScope4 = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c01065 = this;
                awaitPointerEventScope = awaitPointerEventScope4;
            } else {
                if (i == 2) {
                    floatRef = (Ref.FloatRef) this.L$1;
                    awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c01066 = this;
                    pointerInputChange = (PointerInputChange) obj;
                    if (pointerInputChange != null) {
                        c01066.$onDragStart.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                        c01066.$onVerticalDrag.invoke(pointerInputChange, Boxing.boxFloat(floatRef.element));
                        long j = pointerInputChange.getId-J3iCeTQ();
                        final Function2<PointerInputChange, Float, Unit> function2 = c01066.$onVerticalDrag;
                        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.o
                            public final Object invoke(Object obj2) {
                                return DragGestureDetectorKt.C01065.d(function2, (PointerInputChange) obj2);
                            }
                        };
                        c01066.L$0 = null;
                        c01066.L$1 = null;
                        c01066.label = 3;
                        obj = DragGestureDetectorKt.m575verticalDragjO51t88(awaitPointerEventScope2, j, function1, c01066);
                    }
                    return Unit.INSTANCE;
                }
                if (i != 3) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                c01066 = this;
            }
            if (((Boolean) obj).booleanValue()) {
                c01066.$onDragEnd.invoke();
            } else {
                c01066.$onDragCancel.invoke();
            }
            return Unit.INSTANCE;
            PointerInputChange pointerInputChange2 = (PointerInputChange) obj;
            floatRef = new Ref.FloatRef();
            C01065 c01067 = c01065;
            long j2 = pointerInputChange2.getId-J3iCeTQ();
            int i2 = pointerInputChange2.getType-T8wyACA();
            Function2 function3 = new Function2() { // from class: androidx.compose.foundation.gestures.n
                public final Object invoke(Object obj2, Object obj3) {
                    return DragGestureDetectorKt.C01065.b(floatRef, (PointerInputChange) obj2, ((Float) obj3).floatValue());
                }
            };
            c01067.L$0 = awaitPointerEventScope;
            c01067.L$1 = floatRef;
            c01067.label = 2;
            obj = DragGestureDetectorKt.m567awaitVerticalPointerSlopOrCancellationgDDlDlE(awaitPointerEventScope, j2, i2, function3, c01067);
            c01066 = c01067;
            if (obj != coroutine_suspended) {
                awaitPointerEventScope2 = awaitPointerEventScope;
                pointerInputChange = (PointerInputChange) obj;
                if (pointerInputChange != null) {
                    c01066.$onDragStart.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                    c01066.$onVerticalDrag.invoke(pointerInputChange, Boxing.boxFloat(floatRef.element));
                    long j3 = pointerInputChange.getId-J3iCeTQ();
                    final Function2 function4 = c01066.$onVerticalDrag;
                    Function1 function5 = new Function1() { // from class: androidx.compose.foundation.gestures.o
                        public final Object invoke(Object obj2) {
                            return DragGestureDetectorKt.C01065.d(function4, (PointerInputChange) obj2);
                        }
                    };
                    c01066.L$0 = null;
                    c01066.L$1 = null;
                    c01066.label = 3;
                    obj = DragGestureDetectorKt.m575verticalDragjO51t88(awaitPointerEventScope2, j3, function5, c01066);
                }
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    static {
        float f = Dp.constructor-impl(0.125f);
        mouseSlop = f;
        float f2 = Dp.constructor-impl(18.0f);
        defaultTouchSlop = f2;
        mouseToTouchSlopRatio = f / f2;
    }

    public static boolean a() {
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:28:0x00c7 A[LOOP:0: B:24:0x00af->B:28:0x00c7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:66:0x00d2 A[EDGE_INSN: B:66:0x00d2->B:30:0x00d2 BREAK  A[LOOP:0: B:24:0x00af->B:28:0x00c7], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x009a -> B:23:0x00a1). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitAllPointersUpWithSlopDetection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r18, androidx.compose.ui.input.pointer.PointerInputChange r19, androidx.compose.ui.input.pointer.PointerEventPass r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) {
        /*
            Method dump skipped, instruction units count: 353
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.awaitAllPointersUpWithSlopDetection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.ui.input.pointer.PointerInputChange, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object awaitAllPointersUpWithSlopDetection$default(AwaitPointerEventScope awaitPointerEventScope, PointerInputChange pointerInputChange, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitAllPointersUpWithSlopDetection(awaitPointerEventScope, pointerInputChange, pointerEventPass, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x007b  */
    /* JADX WARN: Code duplicated, block: B:27:0x008f A[LOOP:0: B:23:0x0079->B:27:0x008f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:54:0x0093 A[EDGE_INSN: B:54:0x0093->B:29:0x0093 BREAK  A[LOOP:0: B:23:0x0079->B:27:0x008f], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0065 -> B:22:0x006a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m556awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r20) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m556awaitDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: awaitDragOrUp-jO51t88, reason: not valid java name */
    private static final Object m557awaitDragOrUpjO51t88(AwaitPointerEventScope awaitPointerEventScope, long j, Function1<? super PointerInputChange, Boolean> function1, Continuation<? super PointerInputChange> continuation) {
        Object obj;
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = j;
        while (true) {
            InlineMarker.mark(0);
            Object obj2 = null;
            Object objAwaitPointerEvent$default = AwaitPointerEventScope.awaitPointerEvent$default(awaitPointerEventScope, (PointerEventPass) null, continuation, 1, (Object) null);
            InlineMarker.mark(1);
            PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent$default;
            List changes = pointerEvent.getChanges();
            int size = changes.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    obj = null;
                    break;
                }
                obj = changes.get(i);
                if (Boolean.valueOf(PointerId.equals-impl0(((PointerInputChange) obj).getId-J3iCeTQ(), longRef.element)).booleanValue()) {
                    break;
                }
                i++;
            }
            PointerInputChange pointerInputChange = (PointerInputChange) obj;
            if (pointerInputChange == null) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                List changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    Object obj3 = changes2.get(i2);
                    if (Boolean.valueOf(((PointerInputChange) obj3).getPressed()).booleanValue()) {
                        obj2 = obj3;
                        break;
                    }
                }
                PointerInputChange pointerInputChange2 = (PointerInputChange) obj2;
                if (pointerInputChange2 == null) {
                    return pointerInputChange;
                }
                longRef.element = pointerInputChange2.getId-J3iCeTQ();
            } else if (((Boolean) function1.invoke(pointerInputChange)).booleanValue()) {
                return pointerInputChange;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x007b  */
    /* JADX WARN: Code duplicated, block: B:27:0x008f A[LOOP:0: B:23:0x0079->B:27:0x008f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:56:0x0093 A[EDGE_INSN: B:56:0x0093->B:29:0x0093 BREAK  A[LOOP:0: B:23:0x0079->B:27:0x008f], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0065 -> B:22:0x006a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m558awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r20) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m558awaitHorizontalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:29:0x00df A[LOOP:0: B:25:0x00c6->B:29:0x00df, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x00e5 A[EDGE_INSN: B:67:0x00e5->B:31:0x00e5 BREAK  A[LOOP:0: B:25:0x00c6->B:29:0x00df], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0189 -> B:61:0x018a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    public static final java.lang.Object m559awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, int r20, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r22) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m559awaitHorizontalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:29:0x00eb A[LOOP:0: B:25:0x00d1->B:29:0x00eb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x00f8 A[EDGE_INSN: B:67:0x00f8->B:31:0x00f8 BREAK  A[LOOP:0: B:25:0x00d1->B:29:0x00eb], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0194 -> B:61:0x0198). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitHorizontalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m560awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r19, long r20, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r22, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instruction units count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m560awaitHorizontalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3, types: [kotlin.jvm.internal.Ref$ObjectRef] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX INFO: renamed from: awaitLongPressOrCancellation-rnUCldI, reason: not valid java name */
    public static final Object m561awaitLongPressOrCancellationrnUCldI(AwaitPointerEventScope awaitPointerEventScope, long j, Continuation<? super PointerInputChange> continuation) {
        DragGestureDetectorKt$awaitLongPressOrCancellation$1 dragGestureDetectorKt$awaitLongPressOrCancellation$1;
        Object obj;
        PointerInputChange pointerInputChange;
        Ref.BooleanRef booleanRef;
        if (continuation instanceof DragGestureDetectorKt$awaitLongPressOrCancellation$1) {
            dragGestureDetectorKt$awaitLongPressOrCancellation$1 = (DragGestureDetectorKt$awaitLongPressOrCancellation$1) continuation;
            int i = dragGestureDetectorKt$awaitLongPressOrCancellation$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                dragGestureDetectorKt$awaitLongPressOrCancellation$1.label = i - Integer.MIN_VALUE;
            } else {
                dragGestureDetectorKt$awaitLongPressOrCancellation$1 = new DragGestureDetectorKt$awaitLongPressOrCancellation$1(continuation);
            }
        } else {
            dragGestureDetectorKt$awaitLongPressOrCancellation$1 = new DragGestureDetectorKt$awaitLongPressOrCancellation$1(continuation);
        }
        Object obj2 = dragGestureDetectorKt$awaitLongPressOrCancellation$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = dragGestureDetectorKt$awaitLongPressOrCancellation$1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj2);
                if (m573isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), j)) {
                    return null;
                }
                List changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                int size = changes.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        obj = null;
                        break;
                    }
                    obj = changes.get(i3);
                    if (PointerId.equals-impl0(((PointerInputChange) obj).getId-J3iCeTQ(), j)) {
                        break;
                    }
                    i3++;
                }
                pointerInputChange = (PointerInputChange) obj;
                if (pointerInputChange == null) {
                    return null;
                }
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = pointerInputChange;
                long longPressTimeoutMillis = awaitPointerEventScope.getViewConfiguration().getLongPressTimeoutMillis();
                Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                DragGestureDetectorKt$awaitLongPressOrCancellation$2 dragGestureDetectorKt$awaitLongPressOrCancellation$2 = new DragGestureDetectorKt$awaitLongPressOrCancellation$2(booleanRef2, objectRef2, objectRef, null);
                dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$0 = pointerInputChange;
                dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$1 = objectRef;
                dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$2 = booleanRef2;
                dragGestureDetectorKt$awaitLongPressOrCancellation$1.label = 1;
                if (awaitPointerEventScope.withTimeout(longPressTimeoutMillis, dragGestureDetectorKt$awaitLongPressOrCancellation$2, dragGestureDetectorKt$awaitLongPressOrCancellation$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef = booleanRef2;
                j = objectRef;
            } else {
                if (i2 != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                booleanRef = (Ref.BooleanRef) dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$2;
                Ref.ObjectRef objectRef3 = (Ref.ObjectRef) dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$1;
                pointerInputChange = (PointerInputChange) dragGestureDetectorKt$awaitLongPressOrCancellation$1.L$0;
                ResultKt.throwOnFailure(obj2);
                j = objectRef3;
            }
            if (!booleanRef.element) {
                return null;
            }
            PointerInputChange pointerInputChange2 = (PointerInputChange) ((Ref.ObjectRef) j).element;
            return pointerInputChange2 == null ? pointerInputChange : pointerInputChange2;
        } catch (PointerEventTimeoutCancellationException unused) {
            PointerInputChange pointerInputChange3 = (PointerInputChange) j.element;
            return pointerInputChange3 == null ? pointerInputChange : pointerInputChange3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:30:0x00eb A[LOOP:0: B:26:0x00d2->B:30:0x00eb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:69:0x00f1 A[EDGE_INSN: B:69:0x00f1->B:32:0x00f1 BREAK  A[LOOP:0: B:26:0x00d2->B:30:0x00eb], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x018f -> B:63:0x0193). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-6ksA65w, reason: not valid java name */
    public static final java.lang.Object m562awaitPointerSlopOrCancellation6ksA65w(androidx.compose.ui.input.pointer.AwaitPointerEventScope r18, long r19, int r21, androidx.compose.foundation.gestures.Orientation r22, long r23, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r26) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m562awaitPointerSlopOrCancellation6ksA65w(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, androidx.compose.foundation.gestures.Orientation, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-6ksA65w$$forInline, reason: not valid java name */
    private static final Object m563awaitPointerSlopOrCancellation6ksA65w$$forInline(AwaitPointerEventScope awaitPointerEventScope, long j, int i, Orientation orientation, long j2, Function2<? super PointerInputChange, ? super Offset, Unit> function2, Continuation<? super PointerInputChange> continuation) {
        int i2;
        Object obj;
        TouchSlopDetector touchSlopDetector;
        float f;
        Continuation<? super PointerInputChange> continuation2;
        Object obj2;
        Continuation<? super PointerInputChange> continuation3 = continuation;
        if (m573isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), j)) {
            return null;
        }
        float fM574pointerSlopE8SPZFQ = m574pointerSlopE8SPZFQ(awaitPointerEventScope.getViewConfiguration(), i);
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = j;
        TouchSlopDetector touchSlopDetector2 = new TouchSlopDetector(orientation, j2, null);
        while (true) {
            int i3 = 0;
            InlineMarker.mark(0);
            Object objAwaitPointerEvent$default = AwaitPointerEventScope.awaitPointerEvent$default(awaitPointerEventScope, (PointerEventPass) null, continuation3, 1, (Object) null);
            InlineMarker.mark(1);
            PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent$default;
            List changes = pointerEvent.getChanges();
            int size = changes.size();
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    i2 = i3;
                    obj = null;
                    break;
                }
                obj = changes.get(i4);
                i2 = i3;
                if (Boolean.valueOf(PointerId.equals-impl0(((PointerInputChange) obj).getId-J3iCeTQ(), longRef.element)).booleanValue()) {
                    break;
                }
                i4++;
                i3 = i2;
            }
            PointerInputChange pointerInputChange = (PointerInputChange) obj;
            if (pointerInputChange == null || pointerInputChange.isConsumed()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                List changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                while (true) {
                    if (i2 >= size2) {
                        obj2 = null;
                        break;
                    }
                    obj2 = changes2.get(i2);
                    if (Boolean.valueOf(((PointerInputChange) obj2).getPressed()).booleanValue()) {
                        break;
                    }
                    i2++;
                }
                PointerInputChange pointerInputChange2 = (PointerInputChange) obj2;
                if (pointerInputChange2 == null) {
                    return null;
                }
                longRef.element = pointerInputChange2.getId-J3iCeTQ();
                continuation2 = continuation;
                f = fM574pointerSlopE8SPZFQ;
                touchSlopDetector = touchSlopDetector2;
            } else {
                TouchSlopDetector touchSlopDetector3 = touchSlopDetector2;
                float f2 = fM574pointerSlopE8SPZFQ;
                long jM718addPositionsakrDWew = touchSlopDetector3.m718addPositionsakrDWew(pointerInputChange.getPosition-F1C5BW0(), pointerInputChange.getPreviousPosition-F1C5BW0(), f2);
                touchSlopDetector = touchSlopDetector3;
                f = f2;
                if ((SieveCacheKt.InvalidMapping & jM718addPositionsakrDWew) != 9205357640488583168L) {
                    function2.invoke(pointerInputChange, Offset.box-impl(jM718addPositionsakrDWew));
                    if (pointerInputChange.isConsumed()) {
                        return pointerInputChange;
                    }
                    TouchSlopDetector.m717resetk4lQ0M$default(touchSlopDetector, 0L, 1, null);
                    continuation2 = continuation;
                } else {
                    PointerEventPass pointerEventPass = PointerEventPass.Final;
                    InlineMarker.mark(i2);
                    continuation2 = continuation;
                    awaitPointerEventScope.awaitPointerEvent(pointerEventPass, continuation2);
                    InlineMarker.mark(1);
                    if (pointerInputChange.isConsumed()) {
                        return null;
                    }
                }
            }
            touchSlopDetector2 = touchSlopDetector;
            fM574pointerSlopE8SPZFQ = f;
            continuation3 = continuation2;
        }
    }

    /* JADX INFO: renamed from: awaitPointerSlopOrCancellation-6ksA65w$default, reason: not valid java name */
    public static /* synthetic */ Object m564awaitPointerSlopOrCancellation6ksA65w$default(AwaitPointerEventScope awaitPointerEventScope, long j, int i, Orientation orientation, long j2, Function2 function2, Continuation continuation, int i2, Object obj) {
        Object obj2;
        Object obj3;
        long j3 = (i2 & 8) != 0 ? Offset.Companion.getZero-F1C5BW0() : j2;
        long j4 = j;
        if (m573isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), j4)) {
            return null;
        }
        float fM574pointerSlopE8SPZFQ = m574pointerSlopE8SPZFQ(awaitPointerEventScope.getViewConfiguration(), i);
        TouchSlopDetector touchSlopDetector = new TouchSlopDetector(orientation, j3, null);
        while (true) {
            int i3 = 0;
            InlineMarker.mark(0);
            Object objAwaitPointerEvent$default = AwaitPointerEventScope.awaitPointerEvent$default(awaitPointerEventScope, (PointerEventPass) null, continuation, 1, (Object) null);
            InlineMarker.mark(1);
            PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent$default;
            List changes = pointerEvent.getChanges();
            int size = changes.size();
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    obj2 = null;
                    break;
                }
                obj2 = changes.get(i4);
                if (PointerId.equals-impl0(((PointerInputChange) obj2).getId-J3iCeTQ(), j4)) {
                    break;
                }
                i4++;
            }
            PointerInputChange pointerInputChange = (PointerInputChange) obj2;
            if (pointerInputChange == null || pointerInputChange.isConsumed()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                List changes2 = pointerEvent.getChanges();
                int size2 = changes2.size();
                while (true) {
                    if (i3 >= size2) {
                        obj3 = null;
                        break;
                    }
                    obj3 = changes2.get(i3);
                    if (((PointerInputChange) obj3).getPressed()) {
                        break;
                    }
                    i3++;
                }
                PointerInputChange pointerInputChange2 = (PointerInputChange) obj3;
                if (pointerInputChange2 == null) {
                    return null;
                }
                j4 = pointerInputChange2.getId-J3iCeTQ();
            } else {
                TouchSlopDetector touchSlopDetector2 = touchSlopDetector;
                long jM718addPositionsakrDWew = touchSlopDetector2.m718addPositionsakrDWew(pointerInputChange.getPosition-F1C5BW0(), pointerInputChange.getPreviousPosition-F1C5BW0(), fM574pointerSlopE8SPZFQ);
                if ((SieveCacheKt.InvalidMapping & jM718addPositionsakrDWew) != 9205357640488583168L) {
                    function2.invoke(pointerInputChange, Offset.box-impl(jM718addPositionsakrDWew));
                    if (pointerInputChange.isConsumed()) {
                        return pointerInputChange;
                    }
                    TouchSlopDetector.m717resetk4lQ0M$default(touchSlopDetector2, 0L, 1, null);
                } else {
                    PointerEventPass pointerEventPass = PointerEventPass.Final;
                    InlineMarker.mark(0);
                    awaitPointerEventScope.awaitPointerEvent(pointerEventPass, continuation);
                    InlineMarker.mark(1);
                    if (pointerInputChange.isConsumed()) {
                        return null;
                    }
                }
                touchSlopDetector = touchSlopDetector2;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:29:0x00e7 A[LOOP:0: B:25:0x00cf->B:29:0x00e7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x00ed A[EDGE_INSN: B:67:0x00ed->B:31:0x00ed BREAK  A[LOOP:0: B:25:0x00cf->B:29:0x00e7], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x017e -> B:61:0x0184). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m565awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r18, long r19, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super androidx.compose.ui.geometry.Offset, kotlin.Unit> r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r22) {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m565awaitTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x007b  */
    /* JADX WARN: Code duplicated, block: B:27:0x008f A[LOOP:0: B:23:0x0079->B:27:0x008f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:56:0x0093 A[EDGE_INSN: B:56:0x0093->B:29:0x0093 BREAK  A[LOOP:0: B:23:0x0079->B:27:0x008f], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0065 -> B:22:0x006a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalDragOrCancellation-rnUCldI, reason: not valid java name */
    public static final java.lang.Object m566awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r20) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m566awaitVerticalDragOrCancellationrnUCldI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:29:0x00df A[LOOP:0: B:25:0x00c6->B:29:0x00df, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x00e5 A[EDGE_INSN: B:67:0x00e5->B:31:0x00e5 BREAK  A[LOOP:0: B:25:0x00c6->B:29:0x00df], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x018c -> B:61:0x018d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalPointerSlopOrCancellation-gDDlDlE, reason: not valid java name */
    public static final java.lang.Object m567awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, int r20, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r21, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r22) {
        /*
            Method dump skipped, instruction units count: 409
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m567awaitVerticalPointerSlopOrCancellationgDDlDlE(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:29:0x00eb A[LOOP:0: B:25:0x00d1->B:29:0x00eb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x00f8 A[EDGE_INSN: B:67:0x00f8->B:31:0x00f8 BREAK  A[LOOP:0: B:25:0x00d1->B:29:0x00eb], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0197 -> B:61:0x019b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: awaitVerticalTouchSlopOrCancellation-jO51t88, reason: not valid java name */
    public static final java.lang.Object m568awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r19, long r20, kotlin.jvm.functions.Function2<? super androidx.compose.ui.input.pointer.PointerInputChange, ? super java.lang.Float, kotlin.Unit> r22, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r23) {
        /*
            Method dump skipped, instruction units count: 428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m568awaitVerticalTouchSlopOrCancellationjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static Unit b() {
        return Unit.INSTANCE;
    }

    public static Unit c() {
        return Unit.INSTANCE;
    }

    public static Unit d() {
        return Unit.INSTANCE;
    }

    public static final Object detectDragGestures(PointerInputScope pointerInputScope, final Function1<? super Offset, Unit> function1, final Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super Unit> continuation) {
        Object objDetectDragGestures = detectDragGestures(pointerInputScope, null, new Function3() { // from class: nx3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return DragGestureDetectorKt.o(function1, (PointerInputChange) obj, (PointerInputChange) obj2, (Offset) obj3);
            }
        }, new Function1() { // from class: ox3
            public final Object invoke(Object obj) {
                return DragGestureDetectorKt.i(function0, (PointerInputChange) obj);
            }
        }, function2, new Function0() { // from class: px3
            public final Object invoke() {
                return Boolean.valueOf(DragGestureDetectorKt.e());
            }
        }, function3, continuation);
        return objDetectDragGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDragGestures : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectDragGestures$default(PointerInputScope pointerInputScope, Orientation orientation, Function3 function3, Function1 function1, Function0 function0, Function0 function2, Function2 function4, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function3 = new Function3() { // from class: qx3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return DragGestureDetectorKt.p((PointerInputChange) obj2, (PointerInputChange) obj3, (Offset) obj4);
                }
            };
        }
        Function3 function5 = function3;
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: rx3
                public final Object invoke(Object obj2) {
                    return DragGestureDetectorKt.m((PointerInputChange) obj2);
                }
            };
        }
        Function1 function6 = function1;
        if ((i & 8) != 0) {
            function0 = new Function0() { // from class: sx3
                public final Object invoke() {
                    return DragGestureDetectorKt.h();
                }
            };
        }
        Function0 function7 = function0;
        if ((i & 16) != 0) {
            function2 = new Function0() { // from class: tx3
                public final Object invoke() {
                    return Boolean.valueOf(DragGestureDetectorKt.a());
                }
            };
        }
        return detectDragGestures(pointerInputScope, orientation, function5, function6, function7, function2, function4, continuation);
    }

    public static final Object detectDragGesturesAfterLongPress(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass5(function1, function0, function2, function3, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectDragGesturesAfterLongPress$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function2, Function2 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: cx3
                public final Object invoke(Object obj2) {
                    return DragGestureDetectorKt.q((Offset) obj2);
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: dx3
                public final Object invoke() {
                    return DragGestureDetectorKt.k();
                }
            };
        }
        if ((i & 4) != 0) {
            function2 = new Function0() { // from class: ex3
                public final Object invoke() {
                    return DragGestureDetectorKt.n();
                }
            };
        }
        Function0 function4 = function2;
        return detectDragGesturesAfterLongPress(pointerInputScope, function1, function0, function4, function3, continuation);
    }

    public static final Object detectHorizontalDragGestures(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Float, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C01055(function1, function3, function0, function2, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectHorizontalDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function2, Function2 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: bx3
                public final Object invoke(Object obj2) {
                    return DragGestureDetectorKt.r((Offset) obj2);
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: lx3
                public final Object invoke() {
                    return DragGestureDetectorKt.s();
                }
            };
        }
        if ((i & 4) != 0) {
            function2 = new Function0() { // from class: mx3
                public final Object invoke() {
                    return DragGestureDetectorKt.f();
                }
            };
        }
        Function0 function4 = function2;
        return detectHorizontalDragGestures(pointerInputScope, function1, function0, function4, function3, continuation);
    }

    public static final Object detectVerticalDragGestures(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function0<Unit> function0, Function0<Unit> function2, Function2<? super PointerInputChange, ? super Float, Unit> function3, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C01065(function1, function3, function0, function2, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectVerticalDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function2, Function2 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: ix3
                public final Object invoke(Object obj2) {
                    return DragGestureDetectorKt.j((Offset) obj2);
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: jx3
                public final Object invoke() {
                    return DragGestureDetectorKt.l();
                }
            };
        }
        if ((i & 4) != 0) {
            function2 = new Function0() { // from class: kx3
                public final Object invoke() {
                    return DragGestureDetectorKt.d();
                }
            };
        }
        Function0 function4 = function2;
        return detectVerticalDragGestures(pointerInputScope, function1, function0, function4, function3, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ba A[LOOP:0: B:24:0x009f->B:28:0x00ba, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:69:0x00c4 A[EDGE_INSN: B:69:0x00c4->B:30:0x00c4 BREAK  A[LOOP:0: B:24:0x009f->B:28:0x00ba], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x008b -> B:23:0x0091). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: drag-VnAYq1g, reason: not valid java name */
    public static final java.lang.Object m569dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope r18, long r19, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r21, androidx.compose.foundation.gestures.Orientation r22, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, java.lang.Boolean> r23, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r24) {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m569dragVnAYq1g(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, androidx.compose.foundation.gestures.Orientation, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: drag-VnAYq1g$$forInline, reason: not valid java name */
    private static final Object m570dragVnAYq1g$$forInline(AwaitPointerEventScope awaitPointerEventScope, long j, Function1<? super PointerInputChange, Unit> function1, Orientation orientation, Function1<? super PointerInputChange, Boolean> function2, Continuation<? super PointerInputChange> continuation) {
        Object obj;
        PointerInputChange pointerInputChange;
        Object obj2;
        float fIntBitsToFloat;
        long j2 = j;
        if (m573isPointerUpDmW0f2w(awaitPointerEventScope.getCurrentEvent(), j2)) {
            return null;
        }
        while (true) {
            Ref.LongRef longRef = new Ref.LongRef();
            longRef.element = j2;
            while (true) {
                int i = 0;
                InlineMarker.mark(0);
                Object objAwaitPointerEvent$default = AwaitPointerEventScope.awaitPointerEvent$default(awaitPointerEventScope, (PointerEventPass) null, continuation, 1, (Object) null);
                InlineMarker.mark(1);
                PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent$default;
                List changes = pointerEvent.getChanges();
                int size = changes.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        obj = null;
                        break;
                    }
                    obj = changes.get(i2);
                    if (Boolean.valueOf(PointerId.equals-impl0(((PointerInputChange) obj).getId-J3iCeTQ(), longRef.element)).booleanValue()) {
                        break;
                    }
                    i2++;
                }
                pointerInputChange = (PointerInputChange) obj;
                if (pointerInputChange == null) {
                    pointerInputChange = null;
                    break;
                }
                if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                    List changes2 = pointerEvent.getChanges();
                    int size2 = changes2.size();
                    while (true) {
                        if (i >= size2) {
                            obj2 = null;
                            break;
                        }
                        obj2 = changes2.get(i);
                        if (Boolean.valueOf(((PointerInputChange) obj2).getPressed()).booleanValue()) {
                            break;
                        }
                        i++;
                    }
                    PointerInputChange pointerInputChange2 = (PointerInputChange) obj2;
                    if (pointerInputChange2 == null) {
                        break;
                    }
                    longRef.element = pointerInputChange2.getId-J3iCeTQ();
                } else {
                    long jPositionChangeIgnoreConsumed = PointerEventKt.positionChangeIgnoreConsumed(pointerInputChange);
                    if (orientation == null) {
                        fIntBitsToFloat = Offset.getDistance-impl(jPositionChangeIgnoreConsumed);
                    } else {
                        fIntBitsToFloat = orientation == Orientation.Vertical ? Float.intBitsToFloat((int) (jPositionChangeIgnoreConsumed & 4294967295L)) : Float.intBitsToFloat((int) (jPositionChangeIgnoreConsumed >> 32));
                    }
                    if (Boolean.valueOf((fIntBitsToFloat == 0.0f ? 1 : 0) ^ 1).booleanValue()) {
                        break;
                    }
                }
            }
            if (pointerInputChange == null || ((Boolean) function2.invoke(pointerInputChange)).booleanValue()) {
                return null;
            }
            if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                return pointerInputChange;
            }
            function1.invoke(pointerInputChange);
            j2 = pointerInputChange.getId-J3iCeTQ();
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0049 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x0054  */
    /* JADX WARN: Code duplicated, block: B:24:0x005a  */
    /* JADX WARN: Code duplicated, block: B:26:0x005f  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0047 -> B:18:0x004a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: drag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m571dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r4, long r5, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = (androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1 r0 = new androidx.compose.foundation.gestures.DragGestureDetectorKt$drag$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            java.lang.Object r5 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
            kotlin.ResultKt.throwOnFailure(r8)
            r7 = r4
            r4 = r5
            goto L4a
        L33:
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            k2d.a(r4)
            r4 = 0
            return r4
        L3a:
            kotlin.ResultKt.throwOnFailure(r8)
        L3d:
            r0.L$0 = r4
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = m556awaitDragOrCancellationrnUCldI(r4, r5, r0)
            if (r8 != r1) goto L4a
            return r1
        L4a:
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            if (r8 != 0) goto L54
            r4 = 0
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        L54:
            boolean r5 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUpIgnoreConsumed(r8)
            if (r5 == 0) goto L5f
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r4
        L5f:
            r7.invoke(r8)
            long r5 = r8.getId-J3iCeTQ()
            goto L3d
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m571dragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static boolean e() {
        return true;
    }

    public static Unit f() {
        return Unit.INSTANCE;
    }

    public static Unit g(Offset offset) {
        return Unit.INSTANCE;
    }

    public static Unit h() {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0097  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ad A[LOOP:0: B:24:0x0095->B:28:0x00ad, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:75:0x00b7 A[EDGE_INSN: B:75:0x00b7->B:30:0x00b7 BREAK  A[LOOP:0: B:24:0x0095->B:28:0x00ad], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0081 -> B:23:0x0087). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: horizontalDrag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m572horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m572horizontalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static Unit i(Function0 function0, PointerInputChange pointerInputChange) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isPointerUp-DmW0f2w, reason: not valid java name */
    public static final boolean m573isPointerUpDmW0f2w(PointerEvent pointerEvent, long j) {
        Object obj;
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                obj = null;
                break;
            }
            obj = changes.get(i);
            if (PointerId.equals-impl0(((PointerInputChange) obj).getId-J3iCeTQ(), j)) {
                break;
            }
            i++;
        }
        PointerInputChange pointerInputChange = (PointerInputChange) obj;
        if (pointerInputChange != null && pointerInputChange.getPressed()) {
            z = true;
        }
        return true ^ z;
    }

    public static Unit j(Offset offset) {
        return Unit.INSTANCE;
    }

    public static Unit k() {
        return Unit.INSTANCE;
    }

    public static Unit l() {
        return Unit.INSTANCE;
    }

    public static Unit m(PointerInputChange pointerInputChange) {
        return Unit.INSTANCE;
    }

    public static Unit n() {
        return Unit.INSTANCE;
    }

    public static Unit o(Function1 function1, PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2, Offset offset) {
        function1.invoke(Offset.box-impl(pointerInputChange2.getPosition-F1C5BW0()));
        return Unit.INSTANCE;
    }

    public static Unit p(PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2, Offset offset) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: pointerSlop-E8SPZFQ, reason: not valid java name */
    public static final float m574pointerSlopE8SPZFQ(ViewConfiguration viewConfiguration, int i) {
        return PointerType.equals-impl0(i, PointerType.Companion.getMouse-T8wyACA()) ? viewConfiguration.getTouchSlop() * mouseToTouchSlopRatio : viewConfiguration.getTouchSlop();
    }

    public static Unit q(Offset offset) {
        return Unit.INSTANCE;
    }

    public static Unit r(Offset offset) {
        return Unit.INSTANCE;
    }

    public static Unit s() {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0097  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ad A[LOOP:0: B:24:0x0095->B:28:0x00ad, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:75:0x00b7 A[EDGE_INSN: B:75:0x00b7->B:30:0x00b7 BREAK  A[LOOP:0: B:24:0x0095->B:28:0x00ad], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0081 -> B:23:0x0087). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: renamed from: verticalDrag-jO51t88, reason: not valid java name */
    public static final java.lang.Object m575verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, long r18, kotlin.jvm.functions.Function1<? super androidx.compose.ui.input.pointer.PointerInputChange, kotlin.Unit> r20, kotlin.coroutines.Continuation<? super java.lang.Boolean> r21) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.DragGestureDetectorKt.m575verticalDragjO51t88(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object detectDragGestures(PointerInputScope pointerInputScope, Orientation orientation, Function3<? super PointerInputChange, ? super PointerInputChange, ? super Offset, Unit> function3, Function1<? super PointerInputChange, Unit> function1, Function0<Unit> function0, Function0<Boolean> function2, Function2<? super PointerInputChange, ? super Offset, Unit> function4, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass13(function2, new Ref.LongRef(), orientation, function3, function4, function0, function1, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectDragGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function0 function0, Function0 function2, Function2 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: fx3
                public final Object invoke(Object obj2) {
                    return DragGestureDetectorKt.g((Offset) obj2);
                }
            };
        }
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: gx3
                public final Object invoke() {
                    return DragGestureDetectorKt.c();
                }
            };
        }
        if ((i & 4) != 0) {
            function2 = new Function0() { // from class: hx3
                public final Object invoke() {
                    return DragGestureDetectorKt.b();
                }
            };
        }
        Function0 function4 = function2;
        return detectDragGestures(pointerInputScope, function1, function0, function4, function3, continuation);
    }
}
