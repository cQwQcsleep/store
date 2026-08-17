package androidx.compose.material3;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationDrawerKt$Scrim$dismissDrawer$1$1 implements PointerInputEventHandler {
    final /* synthetic */ Function0<Unit> $onClose;

    public NavigationDrawerKt$Scrim$dismissDrawer$1$1(Function0<Unit> function0) {
        this.$onClose = function0;
    }

    public static Unit a(Function0 function0, Offset offset) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        final Function0<Unit> function0 = this.$onClose;
        Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, (Function1) null, (Function1) null, (Function3) null, new Function1() { // from class: androidx.compose.material3.b3
            public final Object invoke(Object obj) {
                return NavigationDrawerKt$Scrim$dismissDrawer$1$1.a(function0, (Offset) obj);
            }
        }, continuation, 7, (Object) null);
        return objDetectTapGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures$default : Unit.INSTANCE;
    }
}
