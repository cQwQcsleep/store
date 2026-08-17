package androidx.compose.foundation.text.input.internal.selection;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$3", f = "TextFieldSelectionState.android.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ TextFieldSelectionState $this_with;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$3(TextFieldSelectionState textFieldSelectionState, Continuation<? super TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$3> continuation) {
        super(1, continuation);
        this.$this_with = textFieldSelectionState;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new TextFieldSelectionState_androidKt$addBasicTextFieldTextContextMenuComponents$1$1$1$3(this.$this_with, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return create(continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TextFieldSelectionState textFieldSelectionState = this.$this_with;
            this.label = 1;
            if (textFieldSelectionState.paste(this) == coroutine_suspended) {
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
