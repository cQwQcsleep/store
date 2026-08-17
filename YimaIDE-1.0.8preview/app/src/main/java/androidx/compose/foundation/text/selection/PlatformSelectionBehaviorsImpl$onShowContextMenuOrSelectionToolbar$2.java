package androidx.compose.foundation.text.selection;

import android.view.textclassifier.TextClassifier;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroid/view/textclassifier/TextClassifier;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2", f = "PlatformSelectionBehaviors.android.kt", i = {}, l = {172}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class PlatformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2 extends SuspendLambda implements Function2<TextClassifier, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-text-TextRange$-selection$0, reason: not valid java name */
    final /* synthetic */ long f61$$v$c$androidxcomposeuitextTextRange$selection$0;
    final /* synthetic */ CharSequence $text;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlatformSelectionBehaviorsImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlatformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2(PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl, CharSequence charSequence, long j, Continuation<? super PlatformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2> continuation) {
        super(2, continuation);
        this.this$0 = platformSelectionBehaviorsImpl;
        this.$text = charSequence;
        this.f61$$v$c$androidxcomposeuitextTextRange$selection$0 = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlatformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2 platformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2 = new PlatformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2(this.this$0, this.$text, this.f61$$v$c$androidxcomposeuitextTextRange$selection$0, continuation);
        platformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2.L$0 = obj;
        return platformSelectionBehaviorsImpl$onShowContextMenuOrSelectionToolbar$2;
    }

    public final Object invoke(TextClassifier textClassifier, Continuation<? super Unit> continuation) {
        return create(textClassifier, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TextClassifier textClassifier = (TextClassifier) this.L$0;
            PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl = this.this$0;
            CharSequence charSequence = this.$text;
            long j = this.f61$$v$c$androidxcomposeuitextTextRange$selection$0;
            this.label = 1;
            if (platformSelectionBehaviorsImpl.m1752classifyTextM8tDOmk(charSequence, j, textClassifier, this) == coroutine_suspended) {
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
