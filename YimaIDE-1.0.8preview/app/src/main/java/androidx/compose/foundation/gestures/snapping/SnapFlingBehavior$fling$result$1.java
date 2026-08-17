package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1", f = "SnapFlingBehavior.kt", i = {0}, l = {134, 150}, m = "invokeSuspend", n = {"remainingScrollOffset"}, s = {"L$0"}, v = 1)
public final class SnapFlingBehavior$fling$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AnimationResult<Float, AnimationVector1D>>, Object> {
    final /* synthetic */ float $initialVelocity;
    final /* synthetic */ Function1<Float, Unit> $onRemainingScrollOffsetUpdate;
    final /* synthetic */ ScrollScope $this_fling;
    Object L$0;
    int label;
    final /* synthetic */ SnapFlingBehavior this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SnapFlingBehavior$fling$result$1(SnapFlingBehavior snapFlingBehavior, float f, Function1<? super Float, Unit> function1, ScrollScope scrollScope, Continuation<? super SnapFlingBehavior$fling$result$1> continuation) {
        super(2, continuation);
        this.this$0 = snapFlingBehavior;
        this.$initialVelocity = f;
        this.$onRemainingScrollOffsetUpdate = function1;
        this.$this_fling = scrollScope;
    }

    public static Unit b(Ref.FloatRef floatRef, Function1 function1, float f) {
        float f2 = floatRef.element - f;
        floatRef.element = f2;
        function1.invoke(Float.valueOf(f2));
        return Unit.INSTANCE;
    }

    public static Unit d(Ref.FloatRef floatRef, Function1 function1, float f) {
        float f2 = floatRef.element - f;
        floatRef.element = f2;
        function1.invoke(Float.valueOf(f2));
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SnapFlingBehavior$fling$result$1(this.this$0, this.$initialVelocity, this.$onRemainingScrollOffsetUpdate, this.$this_fling, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AnimationResult<Float, AnimationVector1D>> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        final Ref.FloatRef floatRef;
        Object objTryApproach;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            float fCalculateApproachOffset = this.this$0.snapLayoutInfoProvider.calculateApproachOffset(this.$initialVelocity, DecayAnimationSpecKt.calculateTargetValue(this.this$0.decayAnimationSpec, 0.0f, this.$initialVelocity));
            if (Float.isNaN(fCalculateApproachOffset)) {
                InlineClassHelperKt.throwIllegalStateException("calculateApproachOffset returned NaN. Please use a valid value.");
            }
            floatRef = new Ref.FloatRef();
            float fAbs = Math.abs(fCalculateApproachOffset) * Math.signum(this.$initialVelocity);
            floatRef.element = fAbs;
            this.$onRemainingScrollOffsetUpdate.invoke(Boxing.boxFloat(fAbs));
            SnapFlingBehavior snapFlingBehavior = this.this$0;
            ScrollScope scrollScope = this.$this_fling;
            float f = floatRef.element;
            float f2 = this.$initialVelocity;
            final Function1<Float, Unit> function1 = this.$onRemainingScrollOffsetUpdate;
            Function1 function2 = new Function1() { // from class: androidx.compose.foundation.gestures.snapping.a
                public final Object invoke(Object obj2) {
                    return SnapFlingBehavior$fling$result$1.d(floatRef, function1, ((Float) obj2).floatValue());
                }
            };
            this.L$0 = floatRef;
            this.label = 1;
            objTryApproach = snapFlingBehavior.tryApproach(scrollScope, f, f2, function2, this);
            if (objTryApproach != coroutine_suspended) {
            }
        }
        if (i != 1) {
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        Ref.FloatRef floatRef2 = (Ref.FloatRef) this.L$0;
        ResultKt.throwOnFailure(obj);
        floatRef = floatRef2;
        objTryApproach = obj;
        AnimationState animationState = (AnimationState) objTryApproach;
        float fCalculateSnapOffset = this.this$0.snapLayoutInfoProvider.calculateSnapOffset(((Number) animationState.getVelocity()).floatValue());
        if (Float.isNaN(fCalculateSnapOffset)) {
            InlineClassHelperKt.throwIllegalStateException("calculateSnapOffset returned NaN. Please use a valid value.");
        }
        floatRef.element = fCalculateSnapOffset;
        ScrollScope scrollScope2 = this.$this_fling;
        AnimationState animationStateCopy$default = AnimationStateKt.copy$default(animationState, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
        AnimationSpec animationSpec = this.this$0.snapAnimationSpec;
        final Function1<Float, Unit> function3 = this.$onRemainingScrollOffsetUpdate;
        Function1 function4 = new Function1() { // from class: androidx.compose.foundation.gestures.snapping.b
            public final Object invoke(Object obj2) {
                return SnapFlingBehavior$fling$result$1.b(floatRef, function3, ((Float) obj2).floatValue());
            }
        };
        this.L$0 = null;
        this.label = 2;
        Object objAnimateWithTarget = SnapFlingBehaviorKt.animateWithTarget(scrollScope2, fCalculateSnapOffset, fCalculateSnapOffset, animationStateCopy$default, animationSpec, function4, this);
        return objAnimateWithTarget == coroutine_suspended ? coroutine_suspended : objAnimateWithTarget;
    }
}
