package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.unit.IntOffset;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1", f = "LazyLayoutItemAnimation.kt", i = {0}, l = {141, 148}, m = "invokeSuspend", n = {"finalSpec"}, s = {"L$0"}, v = 1)
public final class LazyLayoutItemAnimation$animatePlacementDelta$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FiniteAnimationSpec<IntOffset> $spec;
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutItemAnimation$animatePlacementDelta$1(LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec<IntOffset> finiteAnimationSpec, long j, Continuation<? super LazyLayoutItemAnimation$animatePlacementDelta$1> continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$totalDelta = j;
    }

    public static Unit b(LazyLayoutItemAnimation lazyLayoutItemAnimation, long j, Animatable animatable) {
        lazyLayoutItemAnimation.m1113setPlacementDeltagyyYBs(IntOffset.minus-qkQi6aY(((IntOffset) animatable.getValue()).unbox-impl(), j));
        lazyLayoutItemAnimation.onLayerPropertyChanged.invoke();
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyLayoutItemAnimation$animatePlacementDelta$1(this.this$0, this.$spec, this.$totalDelta, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a3, code lost:
    
        if (androidx.compose.animation.core.Animatable.animateTo$default(r12, r4, r5, null, r7, r8, 4, null) == r0) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FiniteAnimationSpec finiteAnimationSpec;
        LazyLayoutItemAnimation$animatePlacementDelta$1 lazyLayoutItemAnimation$animatePlacementDelta$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i != 0) {
                if (i == 1) {
                    finiteAnimationSpec = (FiniteAnimationSpec) this.L$0;
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    lazyLayoutItemAnimation$animatePlacementDelta$1 = this;
                }
                lazyLayoutItemAnimation$animatePlacementDelta$1.this$0.setPlacementAnimationInProgress(false);
                lazyLayoutItemAnimation$animatePlacementDelta$1.this$0.isRunningMovingAwayAnimation = false;
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            boolean zIsRunning = this.this$0.placementDeltaAnimation.isRunning();
            finiteAnimationSpec = this.$spec;
            if (zIsRunning) {
                finiteAnimationSpec = finiteAnimationSpec instanceof SpringSpec ? (SpringSpec) finiteAnimationSpec : LazyLayoutItemAnimationKt.InterruptionSpec;
            }
            if (this.this$0.placementDeltaAnimation.isRunning()) {
                FiniteAnimationSpec finiteAnimationSpec2 = finiteAnimationSpec;
                final long j = IntOffset.minus-qkQi6aY(((IntOffset) this.this$0.placementDeltaAnimation.getValue()).unbox-impl(), this.$totalDelta);
                Animatable animatable = this.this$0.placementDeltaAnimation;
                IntOffset intOffset = IntOffset.box-impl(j);
                final LazyLayoutItemAnimation lazyLayoutItemAnimation = this.this$0;
                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.c
                    public final Object invoke(Object obj2) {
                        return LazyLayoutItemAnimation$animatePlacementDelta$1.b(lazyLayoutItemAnimation, j, (Animatable) obj2);
                    }
                };
                this.L$0 = null;
                this.label = 2;
                lazyLayoutItemAnimation$animatePlacementDelta$1 = this;
            } else {
                Animatable animatable2 = this.this$0.placementDeltaAnimation;
                IntOffset intOffset2 = IntOffset.box-impl(this.$totalDelta);
                this.L$0 = finiteAnimationSpec;
                this.label = 1;
                if (animatable2.snapTo(intOffset2, this) == coroutine_suspended) {
                }
            }
            return coroutine_suspended;
            this.this$0.onLayerPropertyChanged.invoke();
            FiniteAnimationSpec finiteAnimationSpec3 = finiteAnimationSpec;
            final long j2 = IntOffset.minus-qkQi6aY(((IntOffset) this.this$0.placementDeltaAnimation.getValue()).unbox-impl(), this.$totalDelta);
            Animatable animatable3 = this.this$0.placementDeltaAnimation;
            IntOffset intOffset3 = IntOffset.box-impl(j2);
            final LazyLayoutItemAnimation lazyLayoutItemAnimation2 = this.this$0;
            Function1 function2 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.c
                public final Object invoke(Object obj2) {
                    return LazyLayoutItemAnimation$animatePlacementDelta$1.b(lazyLayoutItemAnimation2, j2, (Animatable) obj2);
                }
            };
            this.L$0 = null;
            this.label = 2;
            lazyLayoutItemAnimation$animatePlacementDelta$1 = this;
        } catch (CancellationException unused) {
        }
    }
}
