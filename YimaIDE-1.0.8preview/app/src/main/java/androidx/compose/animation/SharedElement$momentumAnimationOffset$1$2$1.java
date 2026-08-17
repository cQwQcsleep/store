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
@DebugMetadata(c = "androidx.compose.animation.SharedElement$momentumAnimationOffset$1$2$1", f = "SharedElement.kt", i = {}, l = {102}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class SharedElement$momentumAnimationOffset$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SpringSpec<Offset> $spring;
    int label;
    final /* synthetic */ SharedElement this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedElement$momentumAnimationOffset$1$2$1(SharedElement sharedElement, SpringSpec<Offset> springSpec, Continuation<? super SharedElement$momentumAnimationOffset$1$2$1> continuation) {
        super(2, continuation);
        this.this$0 = sharedElement;
        this.$spring = springSpec;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SharedElement$momentumAnimationOffset$1$2$1(this.this$0, this.$spring, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Animatable animatable = this.this$0.momentumAnimation;
            Offset offset = Offset.box-impl(Offset.Companion.getZero-F1C5BW0());
            SpringSpec<Offset> springSpec = this.$spring;
            this.label = 1;
            if (Animatable.animateTo$default(animatable, offset, springSpec, null, null, this, 12, null) == coroutine_suspended) {
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
