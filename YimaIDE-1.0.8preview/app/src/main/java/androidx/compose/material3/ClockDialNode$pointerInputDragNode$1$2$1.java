package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.ClockDialNode$pointerInputDragNode$1$2$1", f = "TimePicker.kt", i = {}, l = {1539}, m = "invokeSuspend", n = {}, s = {})
public final class ClockDialNode$pointerInputDragNode$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Offset $dragAmount;
    int label;
    final /* synthetic */ ClockDialNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClockDialNode$pointerInputDragNode$1$2$1(ClockDialNode clockDialNode, Offset offset, Continuation<? super ClockDialNode$pointerInputDragNode$1$2$1> continuation) {
        super(2, continuation);
        this.this$0 = clockDialNode;
        this.$dragAmount = offset;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ClockDialNode$pointerInputDragNode$1$2$1(this.this$0, this.$dragAmount, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ClockDialNode$pointerInputDragNode$1$2$1 clockDialNode$pointerInputDragNode$1$2$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.offsetX += Float.intBitsToFloat((int) (this.$dragAmount.m2899unboximpl() >> 32));
            this.this$0.offsetY += Float.intBitsToFloat((int) (this.$dragAmount.m2899unboximpl() & 4294967295L));
            AnalogTimePickerState analogTimePickerState = this.this$0.state;
            float fAtan = TimePickerKt.atan(this.this$0.offsetY - IntOffset.m6151getYimpl(this.this$0.m214getCenternOccac()), this.this$0.offsetX - IntOffset.m6150getXimpl(this.this$0.m214getCenternOccac()));
            AnimationSpec animationSpec = this.this$0.animationSpec;
            this.label = 1;
            clockDialNode$pointerInputDragNode$1$2$1 = this;
            if (AnalogTimePickerState.rotateTo$default(analogTimePickerState, fAtan, animationSpec, false, clockDialNode$pointerInputDragNode$1$2$1, 4, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            clockDialNode$pointerInputDragNode$1$2$1 = this;
        }
        TimePickerKt.m1145moveSelectord3b8Pxo(clockDialNode$pointerInputDragNode$1$2$1.this$0.state, clockDialNode$pointerInputDragNode$1$2$1.this$0.offsetX, clockDialNode$pointerInputDragNode$1$2$1.this$0.offsetY, clockDialNode$pointerInputDragNode$1$2$1.this$0.getMaxDist(), clockDialNode$pointerInputDragNode$1$2$1.this$0.m214getCenternOccac());
        return Unit.INSTANCE;
    }
}
