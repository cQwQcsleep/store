package androidx.compose.animation;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.geometry.Offset;
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
@DebugMetadata(c = "androidx.compose.animation.SharedElement$updateExitVelocity$1", f = "SharedElement.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class SharedElement$updateExitVelocity$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-Velocity$-velocity$0, reason: not valid java name */
    final /* synthetic */ long f0$$v$c$androidxcomposeuiunitVelocity$velocity$0;
    int label;
    final /* synthetic */ SharedElement this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedElement$updateExitVelocity$1(SharedElement sharedElement, long j, Continuation<? super SharedElement$updateExitVelocity$1> continuation) {
        super(2, continuation);
        this.this$0 = sharedElement;
        this.f0$$v$c$androidxcomposeuiunitVelocity$velocity$0 = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SharedElement$updateExitVelocity$1(this.this$0, this.f0$$v$c$androidxcomposeuiunitVelocity$velocity$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        SharedElement$updateExitVelocity$1 sharedElement$updateExitVelocity$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Animatable animatable = this.this$0.momentumAnimation;
            Offset offset = Offset.box-impl(Offset.Companion.getZero-F1C5BW0());
            SpringSpec springSpec = SharedElementKt.DefaultMomentumSpring;
            Offset offset2 = Offset.box-impl(SharedElementKt.m145toOffsetTH1AsA0(this.f0$$v$c$androidxcomposeuiunitVelocity$velocity$0));
            this.label = 1;
            sharedElement$updateExitVelocity$1 = this;
            if (Animatable.animateTo$default(animatable, offset, springSpec, offset2, null, sharedElement$updateExitVelocity$1, 8, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            sharedElement$updateExitVelocity$1 = this;
        }
        sharedElement$updateExitVelocity$1.this$0.animationSpecFinalized = true;
        return Unit.INSTANCE;
    }
}
