package androidx.compose.foundation;

import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$onClickKeyUpEvent$2", f = "Clickable.kt", i = {0, 0}, l = {1194, 1198}, m = "invokeSuspend", n = {"minTime", "timeout"}, s = {"J$0", "J$1"}, v = 1)
public final class CombinedClickableNode$onClickKeyUpEvent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $keyCode;
    long J$0;
    long J$1;
    int label;
    final /* synthetic */ CombinedClickableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombinedClickableNode$onClickKeyUpEvent$2(CombinedClickableNode combinedClickableNode, long j, Continuation<? super CombinedClickableNode$onClickKeyUpEvent$2> continuation) {
        super(2, continuation);
        this.this$0 = combinedClickableNode;
        this.$keyCode = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CombinedClickableNode$onClickKeyUpEvent$2(this.this$0, this.$keyCode, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005f, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(r4 - r6, r10) == r0) goto L18;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        long doubleTapMinTimeMillis;
        long doubleTapTimeoutMillis;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ViewConfiguration viewConfiguration = (ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, CompositionLocalsKt.getLocalViewConfiguration());
            doubleTapMinTimeMillis = viewConfiguration.getDoubleTapMinTimeMillis();
            doubleTapTimeoutMillis = viewConfiguration.getDoubleTapTimeoutMillis();
            this.J$0 = doubleTapMinTimeMillis;
            this.J$1 = doubleTapTimeoutMillis;
            this.label = 1;
            if (DelayKt.delay(doubleTapMinTimeMillis, this) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            doubleTapTimeoutMillis = this.J$1;
            doubleTapMinTimeMillis = this.J$0;
            ResultKt.throwOnFailure(obj);
        } else {
            if (i != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.getOnClick().invoke();
        return Unit.INSTANCE;
        CombinedClickableNode.DoubleKeyClickState doubleKeyClickState = (CombinedClickableNode.DoubleKeyClickState) this.this$0.doubleKeyClickStates.get(this.$keyCode);
        if (doubleKeyClickState != null) {
            doubleKeyClickState.setDoubleTapMinTimeMillisElapsed(true);
        }
        this.label = 2;
    }
}
