package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.TimePickerKt$TimeInputImpl$1$1", f = "TimePicker.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class TimePickerKt$TimeInputImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<TextFieldValue> $hourValue$delegate;
    final /* synthetic */ MutableState<TextFieldValue> $minuteValue$delegate;
    final /* synthetic */ TimePickerState $state;
    final /* synthetic */ Ref<Boolean> $userOverride;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$TimeInputImpl$1$1(Ref<Boolean> ref, TimePickerState timePickerState, MutableState<TextFieldValue> mutableState, MutableState<TextFieldValue> mutableState2, Continuation<? super TimePickerKt$TimeInputImpl$1$1> continuation) {
        super(2, continuation);
        this.$userOverride = ref;
        this.$state = timePickerState;
        this.$hourValue$delegate = mutableState;
        this.$minuteValue$delegate = mutableState2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TimePickerKt$TimeInputImpl$1$1(this.$userOverride, this.$state, this.$hourValue$delegate, this.$minuteValue$delegate, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (Intrinsics.areEqual(this.$userOverride.getValue(), Boxing.boxBoolean(true))) {
            this.$hourValue$delegate.setValue(TimePickerKt.TimeInputImpl$hourTextValue(this.$state));
            this.$minuteValue$delegate.setValue(TimePickerKt.TimeInputImpl$minuteTextValue(this.$state));
        }
        this.$userOverride.setValue(Boxing.boxBoolean(true));
        return Unit.INSTANCE;
    }
}
