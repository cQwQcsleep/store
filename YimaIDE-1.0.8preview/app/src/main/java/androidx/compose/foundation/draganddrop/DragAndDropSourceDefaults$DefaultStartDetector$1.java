package androidx.compose.foundation.draganddrop;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/draganddrop/DragAndDropStartDetectorScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1", f = "AndroidDragAndDropSource.android.kt", i = {}, l = {33}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class DragAndDropSourceDefaults$DefaultStartDetector$1 extends SuspendLambda implements Function2<DragAndDropStartDetectorScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    public DragAndDropSourceDefaults$DefaultStartDetector$1(Continuation<? super DragAndDropSourceDefaults$DefaultStartDetector$1> continuation) {
        super(2, continuation);
    }

    public static Unit b(DragAndDropStartDetectorScope dragAndDropStartDetectorScope, Offset offset) {
        dragAndDropStartDetectorScope.mo475requestDragAndDropTransferk4lQ0M(offset.unbox-impl());
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DragAndDropSourceDefaults$DefaultStartDetector$1 dragAndDropSourceDefaults$DefaultStartDetector$1 = new DragAndDropSourceDefaults$DefaultStartDetector$1(continuation);
        dragAndDropSourceDefaults$DefaultStartDetector$1.L$0 = obj;
        return dragAndDropSourceDefaults$DefaultStartDetector$1;
    }

    public final Object invoke(DragAndDropStartDetectorScope dragAndDropStartDetectorScope, Continuation<? super Unit> continuation) {
        return create(dragAndDropStartDetectorScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final DragAndDropStartDetectorScope dragAndDropStartDetectorScope = (DragAndDropStartDetectorScope) this.L$0;
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.draganddrop.a
                public final Object invoke(Object obj2) {
                    return DragAndDropSourceDefaults$DefaultStartDetector$1.b(dragAndDropStartDetectorScope, (Offset) obj2);
                }
            };
            this.label = 1;
            if (TapGestureDetectorKt.detectTapGestures$default(dragAndDropStartDetectorScope, null, function1, null, null, this, 13, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
