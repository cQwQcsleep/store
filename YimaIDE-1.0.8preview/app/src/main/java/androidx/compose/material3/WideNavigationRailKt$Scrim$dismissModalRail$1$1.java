package androidx.compose.material3;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WideNavigationRailKt$Scrim$dismissModalRail$1$1 implements PointerInputEventHandler {
    final /* synthetic */ MutableState<Boolean> $dismiss$delegate;

    public WideNavigationRailKt$Scrim$dismissModalRail$1$1(MutableState<Boolean> mutableState) {
        this.$dismiss$delegate = mutableState;
    }

    public static Unit a(MutableState mutableState, Offset offset) {
        WideNavigationRailKt.Scrim_3J_VO9M$lambda$29(mutableState, true);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        final MutableState<Boolean> mutableState = this.$dismiss$delegate;
        Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, (Function1) null, (Function1) null, (Function3) null, new Function1() { // from class: androidx.compose.material3.r5
            public final Object invoke(Object obj) {
                return WideNavigationRailKt$Scrim$dismissModalRail$1$1.a(mutableState, (Offset) obj);
            }
        }, continuation, 7, (Object) null);
        return objDetectTapGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures$default : Unit.INSTANCE;
    }
}
