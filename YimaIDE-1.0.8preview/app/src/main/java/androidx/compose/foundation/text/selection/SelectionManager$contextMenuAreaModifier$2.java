package androidx.compose.foundation.text.selection;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionManager$contextMenuAreaModifier$2", f = "SelectionManager.kt", i = {}, l = {208}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class SelectionManager$contextMenuAreaModifier$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionManager$contextMenuAreaModifier$2(SelectionManager selectionManager, Continuation<? super SelectionManager$contextMenuAreaModifier$2> continuation) {
        super(1, continuation);
        this.this$0 = selectionManager;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new SelectionManager$contextMenuAreaModifier$2(this.this$0, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return create(continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Pair<AnnotatedString, TextRange> contextTextAndSelection$foundation = this.this$0.getContextTextAndSelection$foundation();
            if (contextTextAndSelection$foundation != null) {
                SelectionManager selectionManager = this.this$0;
                AnnotatedString annotatedString = (AnnotatedString) contextTextAndSelection$foundation.component1();
                long j = ((TextRange) contextTextAndSelection$foundation.component2()).unbox-impl();
                PlatformSelectionBehaviors platformSelectionBehaviors$foundation = selectionManager.getPlatformSelectionBehaviors();
                if (platformSelectionBehaviors$foundation != null) {
                    this.label = 1;
                    if (platformSelectionBehaviors$foundation.mo1748onShowSelectionToolbarSbBc2M(annotatedString, j, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
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
