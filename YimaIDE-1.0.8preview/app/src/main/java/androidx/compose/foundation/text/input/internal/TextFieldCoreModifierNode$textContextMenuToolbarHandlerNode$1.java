package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import androidx.compose.foundation.text.selection.PlatformSelectionBehaviors;
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
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1", f = "TextFieldCoreModifier.kt", i = {}, l = {209, 210}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ TextFieldCoreModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1(TextFieldCoreModifierNode textFieldCoreModifierNode, Continuation<? super TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1> continuation) {
        super(1, continuation);
        this.this$0 = textFieldCoreModifierNode;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new TextFieldCoreModifierNode$textContextMenuToolbarHandlerNode$1(this.this$0, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return create(continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0061, code lost:
    
        if (r7.mo1748onShowSelectionToolbarSbBc2M(r1, r4, r6) == r0) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TextFieldSelectionState textFieldSelectionState = this.this$0.textFieldSelectionState;
            this.label = 1;
            if (textFieldSelectionState.updateClipboardEntry(this) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            if (i != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.textFieldSelectionState.setTextToolbarShown$foundation(true);
        return Unit.INSTANCE;
        PlatformSelectionBehaviors platformSelectionBehaviors = this.this$0.platformSelectionBehaviors;
        if (platformSelectionBehaviors != null) {
            CharSequence text = this.this$0.textFieldSelectionState.getTextFieldState().getVisualText().getText();
            long selection = this.this$0.textFieldSelectionState.getTextFieldState().getVisualText().getSelection();
            this.label = 2;
        }
        this.this$0.textFieldSelectionState.setTextToolbarShown$foundation(true);
        return Unit.INSTANCE;
    }
}
