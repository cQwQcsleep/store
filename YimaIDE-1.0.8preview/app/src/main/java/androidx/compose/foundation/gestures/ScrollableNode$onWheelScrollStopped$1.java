package androidx.compose.foundation.gestures;

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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableNode$onWheelScrollStopped$1", f = "Scrollable.kt", i = {}, l = {404}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class ScrollableNode$onWheelScrollStopped$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-Velocity$-velocity$0, reason: not valid java name */
    final /* synthetic */ long f18$$v$c$androidxcomposeuiunitVelocity$velocity$0;
    int label;
    final /* synthetic */ ScrollableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollableNode$onWheelScrollStopped$1(ScrollableNode scrollableNode, long j, Continuation<? super ScrollableNode$onWheelScrollStopped$1> continuation) {
        super(2, continuation);
        this.this$0 = scrollableNode;
        this.f18$$v$c$androidxcomposeuiunitVelocity$velocity$0 = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ScrollableNode$onWheelScrollStopped$1(this.this$0, this.f18$$v$c$androidxcomposeuiunitVelocity$velocity$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ScrollingLogic scrollingLogic = this.this$0.scrollingLogic;
            long j = this.f18$$v$c$androidxcomposeuiunitVelocity$velocity$0;
            this.label = 1;
            if (scrollingLogic.m700onScrollStoppedBMRW4eQ(j, true, this) == coroutine_suspended) {
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
