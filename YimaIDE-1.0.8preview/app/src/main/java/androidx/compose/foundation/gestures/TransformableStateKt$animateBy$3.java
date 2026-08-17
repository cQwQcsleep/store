package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/TransformScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$animateBy$3", f = "TransformableState.kt", i = {}, l = {230}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class TransformableStateKt$animateBy$3 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DelegatingAnimationSpec $animationSpec;
    final /* synthetic */ Ref.ObjectRef<AnimationData> $previousState;
    final /* synthetic */ AnimationData $targetState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransformableStateKt$animateBy$3(Ref.ObjectRef<AnimationData> objectRef, AnimationData animationData, DelegatingAnimationSpec delegatingAnimationSpec, Continuation<? super TransformableStateKt$animateBy$3> continuation) {
        super(2, continuation);
        this.$previousState = objectRef;
        this.$targetState = animationData;
        this.$animationSpec = delegatingAnimationSpec;
    }

    public static Unit b(Ref.ObjectRef objectRef, TransformScope transformScope, AnimationScope animationScope) {
        transformScope.mo545transformByd4ec7I(((AnimationData) objectRef.element).getZoom() == 0.0f ? 1.0f : ((AnimationData) animationScope.getValue()).getZoom() / ((AnimationData) objectRef.element).getZoom(), Offset.minus-MK-Hz9U(((AnimationData) animationScope.getValue()).m527getOffsetF1C5BW0(), ((AnimationData) objectRef.element).m527getOffsetF1C5BW0()), ((AnimationData) animationScope.getValue()).getDegrees() - ((AnimationData) objectRef.element).getDegrees());
        objectRef.element = animationScope.getValue();
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TransformableStateKt$animateBy$3 transformableStateKt$animateBy$3 = new TransformableStateKt$animateBy$3(this.$previousState, this.$targetState, this.$animationSpec, continuation);
        transformableStateKt$animateBy$3.L$0 = obj;
        return transformableStateKt$animateBy$3;
    }

    public final Object invoke(TransformScope transformScope, Continuation<? super Unit> continuation) {
        return create(transformScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final TransformScope transformScope = (TransformScope) this.L$0;
            AnimationState animationStateAnimationState$default = AnimationStateKt.AnimationState$default(AnimationDataConverter.INSTANCE, this.$previousState.element, TransformableStateKt.ZeroAnimationVelocity, 0L, 0L, false, 56, null);
            AnimationData animationData = this.$targetState;
            DelegatingAnimationSpec delegatingAnimationSpec = this.$animationSpec;
            final Ref.ObjectRef<AnimationData> objectRef = this.$previousState;
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.g0
                public final Object invoke(Object obj2) {
                    return TransformableStateKt$animateBy$3.b(objectRef, transformScope, (AnimationScope) obj2);
                }
            };
            this.label = 1;
            if (SuspendAnimationKt.animateTo$default(animationStateAnimationState$default, animationData, delegatingAnimationSpec, false, function1, this, 4, null) == coroutine_suspended) {
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
