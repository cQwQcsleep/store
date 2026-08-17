package androidx.compose.foundation.gestures;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic2D", f = "Scrollable2D.kt", i = {0}, l = {434}, m = "doFlingAnimation-QWom1Mo", n = {"result"}, s = {"L$0"}, v = 1)
public final class ScrollingLogic2D$doFlingAnimation$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ScrollingLogic2D this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLogic2D$doFlingAnimation$1(ScrollingLogic2D scrollingLogic2D, Continuation<? super ScrollingLogic2D$doFlingAnimation$1> continuation) {
        super(continuation);
        this.this$0 = scrollingLogic2D;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo672doFlingAnimationQWom1Mo(0L, this);
    }
}
