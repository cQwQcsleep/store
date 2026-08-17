package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.material3.ModalWideNavigationRailState;
import androidx.compose.material3.internal.AnchoredDragScope;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchors;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0001\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0019\u001a\u00020\u001aH\u0096@¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u001aH\u0096@¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001d\u001a\u00020\u001aH\u0096@¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u000eH\u0096@¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u0007H\u0080@¢\u0006\u0004\b\"\u0010#J0\u0010'\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010!\u001a\u00020\u0007H\u0082@¢\u0006\u0002\u0010(R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0011\u0010$\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006)"}, d2 = {"Landroidx/compose/material3/ModalWideNavigationRailState;", "Landroidx/compose/material3/WideNavigationRailState;", "state", "density", "Landroidx/compose/ui/unit/Density;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "<init>", "(Landroidx/compose/material3/WideNavigationRailState;Landroidx/compose/ui/unit/Density;Landroidx/compose/animation/core/AnimationSpec;)V", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "anchoredDraggableState", "Landroidx/compose/material3/internal/AnchoredDraggableState;", "Landroidx/compose/material3/WideNavigationRailValue;", "getAnchoredDraggableState$material3", "()Landroidx/compose/material3/internal/AnchoredDraggableState;", "currentValue", "getCurrentValue", "()Landroidx/compose/material3/WideNavigationRailValue;", "targetValue", "getTargetValue", "isAnimating", "", "()Z", "expand", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collapse", "toggle", "snapTo", "(Landroidx/compose/material3/WideNavigationRailValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "settle", "velocity", "settle$material3", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentOffset", "getCurrentOffset", "()F", "animateTo", "(Landroidx/compose/material3/WideNavigationRailValue;Landroidx/compose/animation/core/AnimationSpec;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ModalWideNavigationRailState implements WideNavigationRailState {
    public static final int $stable = 8;
    private final /* synthetic */ WideNavigationRailState $$delegate_0;
    private final AnchoredDraggableState<WideNavigationRailValue> anchoredDraggableState;
    private final AnimationSpec<Float> animationSpec;

    /* JADX INFO: renamed from: androidx.compose.material3.ModalWideNavigationRailState$animateTo$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/material3/internal/AnchoredDragScope;", "anchors", "Landroidx/compose/material3/internal/DraggableAnchors;", "Landroidx/compose/material3/WideNavigationRailValue;", "latestTarget"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.ModalWideNavigationRailState$animateTo$2", f = "WideNavigationRailState.kt", i = {}, l = {240}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function4<AnchoredDragScope, DraggableAnchors<WideNavigationRailValue>, WideNavigationRailValue, Continuation<? super Unit>, Object> {
        final /* synthetic */ AnimationSpec<Float> $animationSpec;
        final /* synthetic */ float $velocity;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f, AnimationSpec<Float> animationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(4, continuation);
            this.$velocity = f;
            this.$animationSpec = animationSpec;
        }

        public static Unit b(AnchoredDragScope anchoredDragScope, Ref.FloatRef floatRef, float f, float f2) {
            anchoredDragScope.dragTo(f, f2);
            floatRef.element = f;
            return Unit.INSTANCE;
        }

        public final Object invoke(AnchoredDragScope anchoredDragScope, DraggableAnchors<WideNavigationRailValue> draggableAnchors, WideNavigationRailValue wideNavigationRailValue, Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = ModalWideNavigationRailState.this.new AnonymousClass2(this.$velocity, this.$animationSpec, continuation);
            anonymousClass2.L$0 = anchoredDragScope;
            anonymousClass2.L$1 = draggableAnchors;
            anonymousClass2.L$2 = wideNavigationRailValue;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final AnchoredDragScope anchoredDragScope = (AnchoredDragScope) this.L$0;
                float fPositionOf = ((DraggableAnchors) this.L$1).positionOf((WideNavigationRailValue) this.L$2);
                if (!Float.isNaN(fPositionOf)) {
                    final Ref.FloatRef floatRef = new Ref.FloatRef();
                    float currentOffset = Float.isNaN(ModalWideNavigationRailState.this.getCurrentOffset()) ? 0.0f : ModalWideNavigationRailState.this.getCurrentOffset();
                    floatRef.element = currentOffset;
                    float f = this.$velocity;
                    AnimationSpec<Float> animationSpec = this.$animationSpec;
                    Function2 function2 = new Function2() { // from class: androidx.compose.material3.r2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ModalWideNavigationRailState.AnonymousClass2.b(anchoredDragScope, floatRef, ((Float) obj2).floatValue(), ((Float) obj3).floatValue());
                        }
                    };
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 1;
                    if (SuspendAnimationKt.animate(currentOffset, fPositionOf, f, animationSpec, function2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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

    public ModalWideNavigationRailState(WideNavigationRailState wideNavigationRailState, final Density density, AnimationSpec<Float> animationSpec) {
        this.$$delegate_0 = wideNavigationRailState;
        this.animationSpec = animationSpec;
        this.anchoredDraggableState = new AnchoredDraggableState<>(wideNavigationRailState.getTargetValue(), new Function1() { // from class: b4a
            public final Object invoke(Object obj) {
                return Float.valueOf(ModalWideNavigationRailState.a(((Float) obj).floatValue()));
            }
        }, new Function0() { // from class: c4a
            public final Object invoke() {
                return Float.valueOf(ModalWideNavigationRailState.c(density));
            }
        }, new Function0() { // from class: d4a
            public final Object invoke() {
                return ModalWideNavigationRailState.b(this.b);
            }
        }, null, 16, null);
    }

    public static float a(float f) {
        return f * 0.5f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateTo(WideNavigationRailValue wideNavigationRailValue, AnimationSpec<Float> animationSpec, float f, Continuation<? super Unit> continuation) {
        Object objAnchoredDrag$default = AnchoredDraggableState.anchoredDrag$default(this.anchoredDraggableState, wideNavigationRailValue, null, new AnonymousClass2(f, animationSpec, null), continuation, 2, null);
        return objAnchoredDrag$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnchoredDrag$default : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(ModalWideNavigationRailState modalWideNavigationRailState, WideNavigationRailValue wideNavigationRailValue, AnimationSpec animationSpec, float f, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            animationSpec = modalWideNavigationRailState.animationSpec;
        }
        if ((i & 4) != 0) {
            f = modalWideNavigationRailState.anchoredDraggableState.getLastVelocity();
        }
        return modalWideNavigationRailState.animateTo(wideNavigationRailValue, animationSpec, f, continuation);
    }

    public static AnimationSpec b(ModalWideNavigationRailState modalWideNavigationRailState) {
        return modalWideNavigationRailState.animationSpec;
    }

    public static float c(Density density) {
        return density.mo4557toPx0680j_4(Dp.m6022constructorimpl(400.0f));
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public Object collapse(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = animateTo$default(this, WideNavigationRailValue.Collapsed, null, 0.0f, continuation, 6, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public Object expand(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = animateTo$default(this, WideNavigationRailValue.Expanded, null, 0.0f, continuation, 6, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    public final AnchoredDraggableState<WideNavigationRailValue> getAnchoredDraggableState$material3() {
        return this.anchoredDraggableState;
    }

    public final AnimationSpec<Float> getAnimationSpec() {
        return this.animationSpec;
    }

    public final float getCurrentOffset() {
        return this.anchoredDraggableState.getOffset();
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public WideNavigationRailValue getCurrentValue() {
        return this.anchoredDraggableState.getCurrentValue();
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public WideNavigationRailValue getTargetValue() {
        return this.anchoredDraggableState.getTargetValue();
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public boolean isAnimating() {
        return this.anchoredDraggableState.isAnimationRunning();
    }

    public final Object settle$material3(float f, Continuation<? super Unit> continuation) {
        Object obj = this.anchoredDraggableState.settle(f, continuation);
        return obj == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? obj : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public Object snapTo(WideNavigationRailValue wideNavigationRailValue, Continuation<? super Unit> continuation) {
        Object objSnapTo = AnchoredDraggableKt.snapTo(this.anchoredDraggableState, wideNavigationRailValue, continuation);
        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public Object toggle(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = animateTo$default(this, WideNavigationRailStateKt.not(getTargetValue()), null, 0.0f, continuation, 6, null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }
}
