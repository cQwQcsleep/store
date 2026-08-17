package androidx.compose.foundation.text;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ClickableTextKt$ClickableText$pressIndicator$1$1 implements PointerInputEventHandler {
    final /* synthetic */ MutableState<TextLayoutResult> $layoutResult;
    final /* synthetic */ Function1<Integer, Unit> $onClick;

    /* JADX WARN: Multi-variable type inference failed */
    public ClickableTextKt$ClickableText$pressIndicator$1$1(MutableState<TextLayoutResult> mutableState, Function1<? super Integer, Unit> function1) {
        this.$layoutResult = mutableState;
        this.$onClick = function1;
    }

    public static Unit a(MutableState mutableState, Function1 function1, Offset offset) {
        TextLayoutResult textLayoutResult = (TextLayoutResult) mutableState.getValue();
        if (textLayoutResult != null) {
            function1.invoke(Integer.valueOf(textLayoutResult.getOffsetForPosition-k-4lQ0M(offset.unbox-impl())));
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        final MutableState<TextLayoutResult> mutableState = this.$layoutResult;
        final Function1<Integer, Unit> function1 = this.$onClick;
        Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, new Function1() { // from class: androidx.compose.foundation.text.c
            public final Object invoke(Object obj) {
                return ClickableTextKt$ClickableText$pressIndicator$1$1.a(mutableState, function1, (Offset) obj);
            }
        }, continuation, 7, null);
        return objDetectTapGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures$default : Unit.INSTANCE;
    }
}
