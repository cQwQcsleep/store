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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/view/textclassifier/TextClassifier;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1", f = "PlatformSelectionBehaviors.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TextClassifier>, Object> {
    int label;
    final /* synthetic */ PlatformSelectionBehaviorsImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1(PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl, Continuation<? super PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1> continuation) {
        super(2, continuation);
        this.this$0 = platformSelectionBehaviorsImpl;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlatformSelectionBehaviorsImpl$requireTextClassificationSession$2$textClassificationSession$1$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TextClassifier> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        TextClassifier textClassifierCreateTextClassificationSession = TextClassifierHelperMethods.INSTANCE.createTextClassificationSession(this.this$0.context, this.this$0.selectedTextType);
        this.this$0.textClassificationSession = textClassifierCreateTextClassificationSession;
        return textClassifierCreateTextClassificationSession;
    }
}
