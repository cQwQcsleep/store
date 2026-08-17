package androidx.compose.material3;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ClockDialNode$pointerInputDragNode$1 implements PointerInputEventHandler {
    final /* synthetic */ ClockDialNode this$0;

    public ClockDialNode$pointerInputDragNode$1(ClockDialNode clockDialNode) {
        this.this$0 = clockDialNode;
    }

    public static Unit a(ClockDialNode clockDialNode) {
        BuildersKt.launch$default(clockDialNode.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new ClockDialNode$pointerInputDragNode$1$1$1(clockDialNode, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit b(ClockDialNode clockDialNode, PointerInputChange pointerInputChange, Offset offset) {
        BuildersKt.launch$default(clockDialNode.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new ClockDialNode$pointerInputDragNode$1$2$1(clockDialNode, offset, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        final ClockDialNode clockDialNode = this.this$0;
        Function0 function0 = new Function0() { // from class: androidx.compose.material3.t
            public final Object invoke() {
                return ClockDialNode$pointerInputDragNode$1.a(clockDialNode);
            }
        };
        final ClockDialNode clockDialNode2 = this.this$0;
        Object objDetectDragGestures$default = DragGestureDetectorKt.detectDragGestures$default(pointerInputScope, (Function1) null, function0, (Function0) null, new Function2() { // from class: androidx.compose.material3.u
            public final Object invoke(Object obj, Object obj2) {
                return ClockDialNode$pointerInputDragNode$1.b(clockDialNode2, (PointerInputChange) obj, (Offset) obj2);
            }
        }, continuation, 5, (Object) null);
        return objDetectDragGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectDragGestures$default : Unit.INSTANCE;
    }
}
