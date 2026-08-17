package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.ui.MotionDurationScale;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u001ap\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u000726\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00010\tH\u0086@¢\u0006\u0002\u0010\u000e\u001a^\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u001026\u0010\b\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00010\tH\u0086@¢\u0006\u0002\u0010\u0011\u001a\u0096\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00162\u0006\u0010\u0002\u001a\u0002H\u00122\u0006\u0010\u0004\u001a\u0002H\u00122\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u0001H\u00122\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00120\u000726\u0010\b\u001a2\u0012\u0013\u0012\u0011H\u0012¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u0011H\u0012¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00010\tH\u0086@¢\u0006\u0002\u0010\u0017\u001aw\u0010\u0018\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192\u0006\u0010\u0004\u001a\u0002H\u00122\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u001b2%\b\u0002\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0086@¢\u0006\u0002\u0010\u001f\u001am\u0010\u000f\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00120 2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2%\b\u0002\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0086@¢\u0006\u0002\u0010!\u001as\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130#2\b\b\u0002\u0010$\u001a\u00020%2%\b\u0002\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0080@¢\u0006\u0002\u0010&\u001aW\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(\"\u0004\b\u0001\u0010\u0012\"\b\b\u0002\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130#2!\u0010)\u001a\u001d\u0012\u0013\u0012\u00110%¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(*\u0012\u0004\u0012\u0002H(0\u001cH\u0082@¢\u0006\u0002\u0010+\u001a<\u00100\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u0019H\u0000\u001a\u0085\u0001\u00102\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d2\u0006\u0010*\u001a\u00020%2\u0006\u0010,\u001a\u00020\u00032\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130#2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192#\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0002\u001a\u0085\u0001\u00104\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0012\"\b\b\u0001\u0010\u0013*\u00020\u0014*\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d2\u0006\u0010*\u001a\u00020%2\u0006\u00105\u001a\u00020%2\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130#2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u00192#\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00130\u001d\u0012\u0004\u0012\u00020\u00010\u001c¢\u0006\u0002\b\u001eH\u0002\"\u0018\u0010,\u001a\u00020\u0003*\u00020-8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u00066"}, d2 = {"animate", "", "initialValue", "", "targetValue", "initialVelocity", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "velocity", "(FFFLandroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateDecay", "Landroidx/compose/animation/core/FloatDecayAnimationSpec;", "(FFLandroidx/compose/animation/core/FloatDecayAnimationSpec;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "(Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateTo", "Landroidx/compose/animation/core/AnimationState;", "sequentialAnimation", "", "Lkotlin/Function1;", "Landroidx/compose/animation/core/AnimationScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/animation/core/AnimationState;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/compose/animation/core/DecayAnimationSpec;", "(Landroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/DecayAnimationSpec;ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animation", "Landroidx/compose/animation/core/Animation;", "startTimeNanos", "", "(Landroidx/compose/animation/core/AnimationState;Landroidx/compose/animation/core/Animation;JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callWithFrameNanos", "R", "onFrame", "frameTimeNanos", "(Landroidx/compose/animation/core/Animation;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "durationScale", "Lkotlin/coroutines/CoroutineContext;", "getDurationScale", "(Lkotlin/coroutines/CoroutineContext;)F", "updateState", "state", "doAnimationFrameWithScale", "anim", "doAnimationFrame", "playTimeNanos", "animation-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SuspendAnimationKt {

    /* JADX INFO: renamed from: androidx.compose.animation.core.SuspendAnimationKt$animate$4, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SuspendAnimationKt", f = "SuspendAnimation.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {231, 280}, m = "animate", n = {"$this$animate", "animation", "block", "lateInitScope", "$this$animate", "animation", "block", "lateInitScope"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"}, v = 1)
    public static final class AnonymousClass4<T, V extends AnimationVector> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SuspendAnimationKt.animate(null, null, 0L, null, this);
        }
    }

    public static Unit a(Ref.ObjectRef objectRef, float f, Animation animation, AnimationState animationState, Function1 function1, long j) {
        Object obj = objectRef.element;
        obj.getClass();
        doAnimationFrameWithScale((AnimationScope) obj, j, f, animation, animationState, function1);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:58:0x012d  */
    /* JADX WARN: Code duplicated, block: B:61:0x0136  */
    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    public static final <T, V extends AnimationVector> Object animate(final AnimationState<T, V> animationState, Animation<T, V> animation, long j, final Function1<? super AnimationScope<T, V>, Unit> function1, Continuation<? super Unit> continuation) {
        AnonymousClass4 anonymousClass4;
        final Ref.ObjectRef objectRef;
        final AnimationState<T, V> animationState2;
        AnimationState<T, V> animationState3;
        Ref.ObjectRef objectRef2;
        Function1<? super AnimationScope<T, V>, Unit> function2;
        AnimationScope animationScope;
        AnimationScope animationScope2;
        Function1 function3;
        final Animation<T, V> animation2 = animation;
        if (continuation instanceof AnonymousClass4) {
            anonymousClass4 = (AnonymousClass4) continuation;
            int i = anonymousClass4.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass4.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass4 = new AnonymousClass4(continuation);
            }
        } else {
            anonymousClass4 = new AnonymousClass4(continuation);
        }
        AnonymousClass4 anonymousClass5 = anonymousClass4;
        Object obj = anonymousClass5.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass5.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            final T valueFromNanos = animation2.getValueFromNanos(0L);
            final AnimationVector velocityVectorFromNanos = animation2.getVelocityVectorFromNanos(0L);
            objectRef = new Ref.ObjectRef();
            if (j == Long.MIN_VALUE) {
                try {
                    final float durationScale = getDurationScale(anonymousClass5.getContext());
                    animationState2 = animationState;
                    try {
                        Function1 function4 = new Function1() { // from class: pud
                            public final Object invoke(Object obj2) {
                                return SuspendAnimationKt.g(objectRef, valueFromNanos, animation2, velocityVectorFromNanos, animationState2, durationScale, function1, ((Long) obj2).longValue());
                            }
                        };
                        objectRef2 = objectRef;
                        try {
                            anonymousClass5.L$0 = animationState2;
                            anonymousClass5.L$1 = animation2;
                            anonymousClass5.L$2 = function1;
                            anonymousClass5.L$3 = objectRef2;
                            anonymousClass5.label = 1;
                            if (callWithFrameNanos(animation2, function4, anonymousClass5) != coroutine_suspended) {
                                animationState3 = animationState2;
                                function2 = function1;
                                objectRef = objectRef2;
                            }
                            return coroutine_suspended;
                        } catch (CancellationException e) {
                            e = e;
                            animationState3 = animationState2;
                            objectRef = objectRef2;
                            animationScope = (AnimationScope) objectRef.element;
                            if (animationScope != null) {
                                animationScope.setRunning$animation_core(false);
                            }
                            animationScope2 = (AnimationScope) objectRef.element;
                            if (animationScope2 != null && animationScope2.getLastFrameTimeNanos() == animationState3.getLastFrameTimeNanos()) {
                                animationState3.setRunning$animation_core(false);
                            }
                            throw e;
                        }
                    } catch (CancellationException e2) {
                        e = e2;
                        animationState3 = animationState2;
                        animationScope = (AnimationScope) objectRef.element;
                        if (animationScope != null) {
                            animationScope.setRunning$animation_core(false);
                        }
                        animationScope2 = (AnimationScope) objectRef.element;
                        if (animationScope2 != null) {
                            animationState3.setRunning$animation_core(false);
                        }
                        throw e;
                    }
                } catch (CancellationException e3) {
                    e = e3;
                    animationState2 = animationState;
                }
            } else {
                objectRef2 = objectRef;
                try {
                    AnimationScope animationScope3 = new AnimationScope(valueFromNanos, animation2.getTypeConverter(), velocityVectorFromNanos, j, animation2.getTargetValue(), j, true, new Function0() { // from class: qud
                        public final Object invoke() {
                            return SuspendAnimationKt.d(animationState);
                        }
                    });
                    doAnimationFrameWithScale(animationScope3, j, getDurationScale(anonymousClass5.getContext()), animation2, animationState, function1);
                    objectRef2.element = animationScope3;
                    animationState3 = animationState;
                    animation2 = animation;
                    function2 = function1;
                    objectRef = objectRef2;
                } catch (CancellationException e4) {
                    e = e4;
                    animationState3 = animationState;
                    objectRef = objectRef2;
                    animationScope = (AnimationScope) objectRef.element;
                    if (animationScope != null) {
                        animationScope.setRunning$animation_core(false);
                    }
                    animationScope2 = (AnimationScope) objectRef.element;
                    if (animationScope2 != null) {
                        animationState3.setRunning$animation_core(false);
                    }
                    throw e;
                }
            }
        } else {
            if (i2 != 1 && i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            objectRef = (Ref.ObjectRef) anonymousClass5.L$3;
            function2 = (Function1) anonymousClass5.L$2;
            animation2 = (Animation) anonymousClass5.L$1;
            animationState3 = (AnimationState) anonymousClass5.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (CancellationException e5) {
                e = e5;
                animationScope = (AnimationScope) objectRef.element;
                if (animationScope != null) {
                    animationScope.setRunning$animation_core(false);
                }
                animationScope2 = (AnimationScope) objectRef.element;
                if (animationScope2 != null) {
                    animationState3.setRunning$animation_core(false);
                }
                throw e;
            }
        }
        do {
            Object obj2 = objectRef.element;
            obj2.getClass();
            if (!((AnimationScope) obj2).isRunning()) {
                return Unit.INSTANCE;
            }
            final float durationScale2 = getDurationScale(anonymousClass5.getContext());
            final Ref.ObjectRef objectRef3 = objectRef;
            final Function1<? super AnimationScope<T, V>, Unit> function5 = function2;
            final Animation<T, V> animation3 = animation2;
            final AnimationState<T, V> animationState4 = animationState3;
            try {
                function3 = new Function1() { // from class: rud
                    public final Object invoke(Object obj3) {
                        return SuspendAnimationKt.a(objectRef3, durationScale2, animation3, animationState4, function5, ((Long) obj3).longValue());
                    }
                };
                objectRef = objectRef3;
                animation2 = animation3;
                animationState3 = animationState4;
                function2 = function5;
                anonymousClass5.L$0 = animationState3;
                anonymousClass5.L$1 = animation2;
                anonymousClass5.L$2 = function2;
                anonymousClass5.L$3 = objectRef;
                anonymousClass5.label = 2;
            } catch (CancellationException e6) {
                e = e6;
                objectRef = objectRef3;
                animationState3 = animationState4;
                animationScope = (AnimationScope) objectRef.element;
                if (animationScope != null) {
                    animationScope.setRunning$animation_core(false);
                }
                animationScope2 = (AnimationScope) objectRef.element;
                if (animationScope2 != null) {
                    animationState3.setRunning$animation_core(false);
                }
                throw e;
            }
        } while (callWithFrameNanos(animation2, function3, anonymousClass5) != coroutine_suspended);
        return coroutine_suspended;
    }

    public static /* synthetic */ Object animate$default(AnimationState animationState, Animation animation, long j, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            j = Long.MIN_VALUE;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: xud
                public final Object invoke(Object obj2) {
                    return SuspendAnimationKt.i((AnimationScope) obj2);
                }
            };
        }
        return animate(animationState, animation, j2, function1, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit animate$lambda$3$0(AnimationState animationState) {
        animationState.setRunning$animation_core(false);
        return Unit.INSTANCE;
    }

    public static final <T, V extends AnimationVector> Object animateDecay(AnimationState<T, V> animationState, DecayAnimationSpec<T> decayAnimationSpec, boolean z, Function1<? super AnimationScope<T, V>, Unit> function1, Continuation<? super Unit> continuation) {
        Object objAnimate = animate(animationState, new DecayAnimation((DecayAnimationSpec) decayAnimationSpec, (TwoWayConverter<T, AnimationVector>) animationState.getTypeConverter(), (Object) animationState.getValue(), animationState.getVelocityVector()), z ? animationState.getLastFrameTimeNanos() : Long.MIN_VALUE, function1, continuation);
        return objAnimate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateDecay$default(AnimationState animationState, DecayAnimationSpec decayAnimationSpec, boolean z, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function1 = new Function1() { // from class: sud
                public final Object invoke(Object obj2) {
                    return SuspendAnimationKt.f((AnimationScope) obj2);
                }
            };
        }
        return animateDecay(animationState, decayAnimationSpec, z, function1, (Continuation<? super Unit>) continuation);
    }

    public static final <T, V extends AnimationVector> Object animateTo(AnimationState<T, V> animationState, T t, AnimationSpec<T> animationSpec, boolean z, Function1<? super AnimationScope<T, V>, Unit> function1, Continuation<? super Unit> continuation) {
        Object objAnimate = animate(animationState, new TargetBasedAnimation(animationSpec, animationState.getTypeConverter(), animationState.getValue(), t, animationState.getVelocityVector()), z ? animationState.getLastFrameTimeNanos() : Long.MIN_VALUE, function1, continuation);
        return objAnimate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$default(AnimationState animationState, Object obj, AnimationSpec animationSpec, boolean z, Function1 function1, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        AnimationSpec animationSpec2 = animationSpec;
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: yud
                public final Object invoke(Object obj3) {
                    return SuspendAnimationKt.b((AnimationScope) obj3);
                }
            };
        }
        return animateTo(animationState, obj, animationSpec2, z2, function1, continuation);
    }

    public static Unit b(AnimationScope animationScope) {
        return Unit.INSTANCE;
    }

    public static Unit c(Function2 function2, TwoWayConverter twoWayConverter, AnimationScope animationScope) {
        function2.invoke(animationScope.getValue(), twoWayConverter.getConvertFromVector().invoke(animationScope.getVelocityVector()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <R, T, V extends AnimationVector> Object callWithFrameNanos(Animation<T, V> animation, final Function1<? super Long, ? extends R> function1, Continuation<? super R> continuation) {
        return animation.getIsInfinite() ? InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(function1, continuation) : MonotonicFrameClockKt.withFrameNanos(new Function1() { // from class: wud
            public final Object invoke(Object obj) {
                return SuspendAnimationKt.e(function1, ((Long) obj).longValue());
            }
        }, continuation);
    }

    public static Unit d(AnimationState animationState) {
        animationState.setRunning$animation_core(false);
        return Unit.INSTANCE;
    }

    private static final <T, V extends AnimationVector> void doAnimationFrame(AnimationScope<T, V> animationScope, long j, long j2, Animation<T, V> animation, AnimationState<T, V> animationState, Function1<? super AnimationScope<T, V>, Unit> function1) {
        animationScope.setLastFrameTimeNanos$animation_core(j);
        animationScope.setValue$animation_core(animation.getValueFromNanos(j2));
        animationScope.setVelocityVector$animation_core(animation.getVelocityVectorFromNanos(j2));
        if (animation.isFinishedFromNanos(j2)) {
            animationScope.setFinishedTimeNanos$animation_core(animationScope.getLastFrameTimeNanos());
            animationScope.setRunning$animation_core(false);
        }
        updateState(animationScope, animationState);
        function1.invoke(animationScope);
    }

    private static final <T, V extends AnimationVector> void doAnimationFrameWithScale(AnimationScope<T, V> animationScope, long j, float f, Animation<T, V> animation, AnimationState<T, V> animationState, Function1<? super AnimationScope<T, V>, Unit> function1) {
        doAnimationFrame(animationScope, j, f == 0.0f ? animation.getDurationNanos() : (long) ((j - animationScope.getStartTimeNanos()) / f), animation, animationState, function1);
    }

    public static Object e(Function1 function1, long j) {
        return function1.invoke(Long.valueOf(j));
    }

    public static Unit f(AnimationScope animationScope) {
        return Unit.INSTANCE;
    }

    public static Unit g(Ref.ObjectRef objectRef, Object obj, Animation animation, AnimationVector animationVector, final AnimationState animationState, float f, Function1 function1, long j) {
        AnimationScope animationScope = new AnimationScope(obj, animation.getTypeConverter(), animationVector, j, animation.getTargetValue(), j, true, new Function0() { // from class: tud
            public final Object invoke() {
                return SuspendAnimationKt.animate$lambda$3$0(animationState);
            }
        });
        doAnimationFrameWithScale(animationScope, j, f, animation, animationState, function1);
        objectRef.element = animationScope;
        return Unit.INSTANCE;
    }

    public static final float getDurationScale(CoroutineContext coroutineContext) {
        MotionDurationScale motionDurationScale = coroutineContext.get(MotionDurationScale.Key);
        float scaleFactor = motionDurationScale != null ? motionDurationScale.getScaleFactor() : 1.0f;
        if (!(scaleFactor >= 0.0f)) {
            PreconditionsKt.throwIllegalStateException("negative scale factor");
        }
        return scaleFactor;
    }

    public static Unit h(Function2 function2, AnimationScope animationScope) {
        function2.invoke(animationScope.getValue(), Float.valueOf(((AnimationVector1D) animationScope.getVelocityVector()).getValue()));
        return Unit.INSTANCE;
    }

    public static Unit i(AnimationScope animationScope) {
        return Unit.INSTANCE;
    }

    public static final <T, V extends AnimationVector> void updateState(AnimationScope<T, V> animationScope, AnimationState<T, V> animationState) {
        animationState.setValue$animation_core(animationScope.getValue());
        AnimationVectorsKt.copyFrom(animationState.getVelocityVector(), animationScope.getVelocityVector());
        animationState.setFinishedTimeNanos$animation_core(animationScope.getFinishedTimeNanos());
        animationState.setLastFrameTimeNanos$animation_core(animationScope.getLastFrameTimeNanos());
        animationState.setRunning$animation_core(animationScope.isRunning());
    }

    public static /* synthetic */ Object animate$default(TwoWayConverter twoWayConverter, Object obj, Object obj2, Object obj3, AnimationSpec animationSpec, Function2 function2, Continuation continuation, int i, Object obj4) {
        if ((i & 8) != 0) {
            obj3 = null;
        }
        if ((i & 16) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animate(twoWayConverter, obj, obj2, obj3, animationSpec, function2, continuation);
    }

    public static /* synthetic */ Object animate$default(float f, float f2, float f3, AnimationSpec animationSpec, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            f3 = 0.0f;
        }
        if ((i & 8) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        }
        return animate(f, f2, f3, animationSpec, function2, continuation);
    }

    public static final Object animateDecay(float f, float f2, FloatDecayAnimationSpec floatDecayAnimationSpec, final Function2<? super Float, ? super Float, Unit> function2, Continuation<? super Unit> continuation) {
        Object objAnimate$default = animate$default(AnimationStateKt.AnimationState$default(f, f2, 0L, 0L, false, 28, null), AnimationKt.DecayAnimation(floatDecayAnimationSpec, f, f2), 0L, new Function1() { // from class: vud
            public final Object invoke(Object obj) {
                return SuspendAnimationKt.h(function2, (AnimationScope) obj);
            }
        }, continuation, 2, null);
        return objAnimate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate$default : Unit.INSTANCE;
    }

    public static final <T, V extends AnimationVector> Object animate(final TwoWayConverter<T, V> twoWayConverter, T t, T t2, T t3, AnimationSpec<T> animationSpec, final Function2<? super T, ? super T, Unit> function2, Continuation<? super Unit> continuation) {
        AnimationVector animationVectorNewInstance;
        if (t3 == null || (animationVectorNewInstance = (AnimationVector) twoWayConverter.getConvertToVector().invoke(t3)) == null) {
            animationVectorNewInstance = AnimationVectorsKt.newInstance((AnimationVector) twoWayConverter.getConvertToVector().invoke(t));
        }
        AnimationVector animationVector = animationVectorNewInstance;
        Object objAnimate$default = animate$default(new AnimationState(twoWayConverter, t, animationVector, 0L, 0L, false, 56, null), new TargetBasedAnimation(animationSpec, twoWayConverter, t, t2, animationVector), 0L, new Function1() { // from class: uud
            public final Object invoke(Object obj) {
                return SuspendAnimationKt.c(function2, twoWayConverter, (AnimationScope) obj);
            }
        }, continuation, 2, null);
        return objAnimate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate$default : Unit.INSTANCE;
    }

    public static final Object animate(float f, float f2, float f3, AnimationSpec<Float> animationSpec, Function2<? super Float, ? super Float, Unit> function2, Continuation<? super Unit> continuation) {
        Object objAnimate = animate(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), Boxing.boxFloat(f), Boxing.boxFloat(f2), Boxing.boxFloat(f3), animationSpec, function2, continuation);
        return objAnimate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimate : Unit.INSTANCE;
    }
}
