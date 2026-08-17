package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.animation.core.VectorConvertersKt;
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
@DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableStateKt$animatePanBy$2", f = "TransformableState.kt", i = {}, l = {182}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class TransformableStateKt$animatePanBy$2 extends SuspendLambda implements Function2<TransformScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-offset$0, reason: not valid java name */
    final /* synthetic */ long f22$$v$c$androidxcomposeuigeometryOffset$offset$0;
    final /* synthetic */ AnimationSpec<Offset> $animationSpec;
    final /* synthetic */ Ref.LongRef $previous;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransformableStateKt$animatePanBy$2(Ref.LongRef longRef, long j, AnimationSpec<Offset> animationSpec, Continuation<? super TransformableStateKt$animatePanBy$2> continuation) {
        super(2, continuation);
        this.$previous = longRef;
        this.f22$$v$c$androidxcomposeuigeometryOffset$offset$0 = j;
        this.$animationSpec = animationSpec;
    }

    public static Unit b(Ref.LongRef longRef, TransformScope transformScope, AnimationScope animationScope) {
        TransformScope.m724transformByd4ec7I$default(transformScope, 0.0f, Offset.minus-MK-Hz9U(((Offset) animationScope.getValue()).unbox-impl(), longRef.element), 0.0f, 5, null);
        longRef.element = ((Offset) animationScope.getValue()).unbox-impl();
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TransformableStateKt$animatePanBy$2 transformableStateKt$animatePanBy$2 = new TransformableStateKt$animatePanBy$2(this.$previous, this.f22$$v$c$androidxcomposeuigeometryOffset$offset$0, this.$animationSpec, continuation);
        transformableStateKt$animatePanBy$2.L$0 = obj;
        return transformableStateKt$animatePanBy$2;
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
            AnimationState animationState = new AnimationState(VectorConvertersKt.getVectorConverter(Offset.Companion), Offset.box-impl(this.$previous.element), null, 0L, 0L, false, 60, null);
            Offset offset = Offset.box-impl(this.f22$$v$c$androidxcomposeuigeometryOffset$offset$0);
            AnimationSpec<Offset> animationSpec = this.$animationSpec;
            final Ref.LongRef longRef = this.$previous;
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.h0
                public final Object invoke(Object obj2) {
                    return TransformableStateKt$animatePanBy$2.b(longRef, transformScope, (AnimationScope) obj2);
                }
            };
            this.label = 1;
            if (SuspendAnimationKt.animateTo$default(animationState, offset, animationSpec, false, function1, this, 4, null) == coroutine_suspended) {
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
