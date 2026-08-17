package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ+\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b¢\u0006\u0002\u0010\u001cR\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR/\u0010\r\u001a\u0004\u0018\u00018\u00002\b\u0010\f\u001a\u0004\u0018\u00018\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0013\u001a\u0004\u0018\u00018\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000bR\u001c\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001f¨\u0006 "}, d2 = {"Landroidx/compose/animation/core/DeferredTargetAnimation;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "", "vectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "<init>", "(Landroidx/compose/animation/core/TwoWayConverter;)V", "pendingTarget", "getPendingTarget", "()Ljava/lang/Object;", "<set-?>", "_pendingTarget", "get_pendingTarget", "set_pendingTarget", "(Ljava/lang/Object;)V", "_pendingTarget$delegate", "Landroidx/compose/runtime/MutableState;", "target", "getTarget", "animatable", "Landroidx/compose/animation/core/Animatable;", "updateTarget", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "(Ljava/lang/Object;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/animation/core/FiniteAnimationSpec;)Ljava/lang/Object;", "isIdle", "", "()Z", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DeferredTargetAnimation<T, V extends AnimationVector> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: _pendingTarget$delegate, reason: from kotlin metadata */
    private final MutableState _pendingTarget = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
    private Animatable<T, V> animatable;
    private final TwoWayConverter<T, V> vectorConverter;

    /* JADX INFO: renamed from: androidx.compose.animation.core.DeferredTargetAnimation$updateTarget$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.DeferredTargetAnimation$updateTarget$1", f = "DeferredTargetAnimation.kt", i = {}, l = {76}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Animatable<T, V> $anim;
        final /* synthetic */ FiniteAnimationSpec<T> $animationSpec;
        final /* synthetic */ T $target;
        int label;
        final /* synthetic */ DeferredTargetAnimation<T, V> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Animatable<T, V> animatable, DeferredTargetAnimation<T, V> deferredTargetAnimation, T t, FiniteAnimationSpec<T> finiteAnimationSpec, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$anim = animatable;
            this.this$0 = deferredTargetAnimation;
            this.$target = t;
            this.$animationSpec = finiteAnimationSpec;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$anim, this.this$0, this.$target, this.$animationSpec, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (!Intrinsics.areEqual(this.$anim.getTargetValue(), this.this$0.get_pendingTarget())) {
                    Animatable<T, V> animatable = this.$anim;
                    T t = this.$target;
                    FiniteAnimationSpec<T> finiteAnimationSpec = this.$animationSpec;
                    this.label = 1;
                    if (Animatable.animateTo$default(animatable, t, finiteAnimationSpec, null, null, this, 12, null) == coroutine_suspended) {
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

    public DeferredTargetAnimation(TwoWayConverter<T, V> twoWayConverter) {
        this.vectorConverter = twoWayConverter;
    }

    private final T getTarget() {
        Animatable<T, V> animatable = this.animatable;
        if (animatable != null) {
            return animatable.getTargetValue();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final T get_pendingTarget() {
        return (T) this._pendingTarget.getValue();
    }

    private final void set_pendingTarget(T t) {
        this._pendingTarget.setValue(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object updateTarget$default(DeferredTargetAnimation deferredTargetAnimation, Object obj, CoroutineScope coroutineScope, FiniteAnimationSpec finiteAnimationSpec, int i, Object obj2) {
        if ((i & 4) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return deferredTargetAnimation.updateTarget(obj, coroutineScope, finiteAnimationSpec);
    }

    public final T getPendingTarget() {
        return get_pendingTarget();
    }

    public final boolean isIdle() {
        if (!Intrinsics.areEqual(get_pendingTarget(), getTarget())) {
            return false;
        }
        Animatable<T, V> animatable = this.animatable;
        return animatable == null || !animatable.isRunning();
    }

    public final T updateTarget(T target, CoroutineScope coroutineScope, FiniteAnimationSpec<T> animationSpec) {
        T t;
        Animatable<T, V> animatable;
        set_pendingTarget(target);
        Animatable<T, V> animatable2 = this.animatable;
        if (animatable2 == null) {
            t = target;
            Animatable<T, V> animatable3 = new Animatable<>(t, this.vectorConverter, null, null, 12, null);
            this.animatable = animatable3;
            animatable = animatable3;
        } else {
            t = target;
            animatable = animatable2;
        }
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(animatable, this, t, animationSpec, null), 3, (Object) null);
        return animatable.getValue();
    }
}
