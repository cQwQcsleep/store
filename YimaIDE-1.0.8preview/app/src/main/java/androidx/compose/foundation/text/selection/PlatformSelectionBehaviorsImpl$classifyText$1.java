package androidx.compose.foundation.text.selection;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.PlatformSelectionBehaviorsImpl", f = "PlatformSelectionBehaviors.android.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {369, 380}, m = "classifyText-M8tDOmk", n = {"text", "textClassifier", "$this$withLock_u24default$iv", "$v$c$androidx-compose-ui-text-TextRange$-selection$0", "text", "textClassification", "$this$withLock_u24default$iv", "$v$c$androidx-compose-ui-text-TextRange$-selection$0"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "J$0"}, v = 1)
public final class PlatformSelectionBehaviorsImpl$classifyText$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlatformSelectionBehaviorsImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlatformSelectionBehaviorsImpl$classifyText$1(PlatformSelectionBehaviorsImpl platformSelectionBehaviorsImpl, Continuation<? super PlatformSelectionBehaviorsImpl$classifyText$1> continuation) {
        super(continuation);
        this.this$0 = platformSelectionBehaviorsImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m1752classifyTextM8tDOmk(null, 0L, null, this);
    }
}
