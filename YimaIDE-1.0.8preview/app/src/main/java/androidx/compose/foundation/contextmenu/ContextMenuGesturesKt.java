package androidx.compose.foundation.contextmenu;

import androidx.compose.foundation.contextmenu.ContextMenuGesturesKt;
import androidx.compose.foundation.text.contextmenu.gestures.RightClickGesturesKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0000¨\u0006\b"}, d2 = {"contextMenuGestures", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/contextmenu/ContextMenuState;", "onOpenGesture", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ContextMenuGesturesKt {
    public static Unit a(ContextMenuState contextMenuState, Offset offset) {
        contextMenuState.setStatus(new ContextMenuState.Status.Open(offset.unbox-impl(), null));
        return Unit.INSTANCE;
    }

    public static final Modifier contextMenuGestures(Modifier modifier, final Function1<? super Offset, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, ContextMenuKey.INSTANCE, new PointerInputEventHandler() { // from class: androidx.compose.foundation.contextmenu.ContextMenuGesturesKt.contextMenuGestures.2
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objOnRightClickDown = RightClickGesturesKt.onRightClickDown(pointerInputScope, function1, continuation);
                return objOnRightClickDown == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objOnRightClickDown : Unit.INSTANCE;
            }
        });
    }

    public static final Modifier contextMenuGestures(Modifier modifier, final ContextMenuState contextMenuState) {
        return contextMenuGestures(modifier, (Function1<? super Offset, Unit>) new Function1() { // from class: av2
            public final Object invoke(Object obj) {
                return ContextMenuGesturesKt.a(contextMenuState, (Offset) obj);
            }
        });
    }
}
