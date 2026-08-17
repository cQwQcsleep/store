package androidx.compose.animation.core;

import androidx.compose.animation.core.SeekableTransitionState;
import androidx.compose.animation.core.Transition.DeferredAnimation;
import androidx.compose.animation.core.Transition.TransitionAnimationState;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a3\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00152\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0016\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00172\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0018\u001aa\u0010\u001f\u001a\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0 R\b\u0012\u0004\u0012\u0002H\"0\u0001\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010!*\u00020#*\b\u0012\u0004\u0012\u0002H\"0\u00012\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0%2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010&\u001a\\\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\"0\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u0010(\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\u00020\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010-\u001aA\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\"0\u00012\u0006\u0010/\u001a\u0002H\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0006\u00100\u001a\u00020\u0005H\u0001¢\u0006\u0002\u00101\u001a¦\u0001\u00102\u001a\b\u0012\u0004\u0012\u0002H\u000203\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010!*\u00020#*\b\u0012\u0004\u0012\u0002H\"0\u00012\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0%2*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u0002060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u0002H\u00020\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010:\u001am\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u000203\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010!*\u00020#*\b\u0012\u0004\u0012\u0002H\"0\u00012\u0006\u0010<\u001a\u0002H\u00022\u0006\u0010=\u001a\u0002H\u00022\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u0002062\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0%2\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010?\u001ai\u0010@\u001a\u00020\f\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u0002\"\b\b\u0002\u0010!*\u00020#*\b\u0012\u0004\u0012\u0002H\"0\u00012\u001c\u0010A\u001a\u0018\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0BR\b\u0012\u0004\u0012\u0002H\"0\u00012\u0006\u0010<\u001a\u0002H\u00022\u0006\u0010=\u001a\u0002H\u00022\f\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u000206H\u0003¢\u0006\u0002\u0010C\u001a\u0082\u0001\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001a03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\u001a0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010F\u001a\b\u0012\u0004\u0012\u00020G03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020G060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020G0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010H\u001a\b\u0012\u0004\u0012\u00020I03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020I060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020I0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010J\u001a\b\u0012\u0004\u0012\u00020K03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020K060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020K0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010L\u001a\b\u0012\u0004\u0012\u00020M03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020M060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020M0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010N\u001a\b\u0012\u0004\u0012\u00020\b03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010O\u001a\b\u0012\u0004\u0012\u00020P03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020P060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020P0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\u001a\u0082\u0001\u0010Q\u001a\b\u0012\u0004\u0012\u00020R03\"\u0004\b\u0000\u0010\"*\b\u0012\u0004\u0012\u0002H\"0\u00012*\b\n\u00104\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\"05\u0012\n\u0012\b\u0012\u0004\u0012\u00020R060\n¢\u0006\u0002\b,¢\u0006\u0002\b72\b\b\u0002\u0010\u0004\u001a\u00020\u00052&\u00108\u001a\"\u0012\u0013\u0012\u0011H\"¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020R0\n¢\u0006\u0002\b,H\u0087\b¢\u0006\u0002\u0010E\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n\u0000\"\u001e\u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001b\u0010\r\u001a\u00020\u000e8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\"\u000e\u0010\u0019\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"updateTransition", "Landroidx/compose/animation/core/Transition;", "T", "targetState", "label", "", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "AnimationDebugDurationScale", "", "SeekableTransitionStateTotalDurationChanged", "Lkotlin/Function1;", "Landroidx/compose/animation/core/SeekableTransitionState;", "", "SeekableStateObserver", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "getSeekableStateObserver", "()Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "SeekableStateObserver$delegate", "Lkotlin/Lazy;", "rememberTransition", "transitionState", "Landroidx/compose/animation/core/TransitionState;", "(Landroidx/compose/animation/core/TransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "NoReset", "", "ResetNoSnap", "ResetAnimationSnap", "ResetAnimationSnapCurrent", "ResetAnimationSnapTarget", "createDeferredAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "V", "S", "Landroidx/compose/animation/core/AnimationVector;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition$DeferredAnimation;", "createChildTransition", "transformToChildState", "Lkotlin/ParameterName;", "name", "parentState", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/animation/core/Transition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "createChildTransitionInternal", "initialState", "childLabel", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/core/Transition;", "animateValue", "Landroidx/compose/runtime/State;", "transitionSpec", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "state", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "createTransitionAnimation", "initialValue", "targetValue", "animationSpec", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "UpdateInitialAndTargetValues", "transitionAnimation", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/Transition$TransitionAnimationState;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/runtime/Composer;I)V", "animateFloat", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateDp", "Landroidx/compose/ui/unit/Dp;", "animateOffset", "Landroidx/compose/ui/geometry/Offset;", "animateSize", "Landroidx/compose/ui/geometry/Size;", "animateIntOffset", "Landroidx/compose/ui/unit/IntOffset;", "animateInt", "animateIntSize", "Landroidx/compose/ui/unit/IntSize;", "animateRect", "Landroidx/compose/ui/geometry/Rect;", "animation-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TransitionKt {
    public static final int AnimationDebugDurationScale = 1;
    private static final float NoReset = -1.0f;
    private static final float ResetAnimationSnap = -3.0f;
    private static final float ResetAnimationSnapCurrent = -4.0f;
    private static final float ResetAnimationSnapTarget = -5.0f;
    private static final float ResetNoSnap = -2.0f;
    private static final Function1<SeekableTransitionState<?>, Unit> SeekableTransitionStateTotalDurationChanged = new Function1() { // from class: kke
        public final Object invoke(Object obj) {
            return TransitionKt.d((SeekableTransitionState) obj);
        }
    };
    private static final Lazy SeekableStateObserver$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: lke
        public final Object invoke() {
            return TransitionKt.a();
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SeekableStateObserver_delegate$lambda$0$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    private static final <S, T, V extends AnimationVector> void UpdateInitialAndTargetValues(final Transition<S> transition, final Transition<S>.TransitionAnimationState<T, V> transitionAnimationState, final T t, final T t2, final FiniteAnimationSpec<T> finiteAnimationSpec, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(867041821);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(transition) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(transitionAnimationState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= (i & 512) == 0 ? composerStartRestartGroup.changed(t) : composerStartRestartGroup.changedInstance(t) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= (i & 4096) == 0 ? composerStartRestartGroup.changed(t2) : composerStartRestartGroup.changedInstance(t2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= (32768 & i) == 0 ? composerStartRestartGroup.changed(finiteAnimationSpec) : composerStartRestartGroup.changedInstance(finiteAnimationSpec) ? 16384 : 8192;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(867041821, i2, -1, "androidx.compose.animation.core.UpdateInitialAndTargetValues (Transition.kt:1907)");
            }
            if (transition.isSeeking()) {
                transitionAnimationState.updateInitialAndTargetValue$animation_core(t, t2, finiteAnimationSpec);
            } else {
                transitionAnimationState.updateTargetValue$animation_core(t2, finiteAnimationSpec);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pke
                public final Object invoke(Object obj, Object obj2) {
                    return TransitionKt.b(transition, transitionAnimationState, t, t2, finiteAnimationSpec, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static SnapshotStateObserver a() {
        SnapshotStateObserver snapshotStateObserver = new SnapshotStateObserver(new Function1() { // from class: nke
            public final Object invoke(Object obj) {
                return TransitionKt.SeekableStateObserver_delegate$lambda$0$0((Function0) obj);
            }
        });
        snapshotStateObserver.start();
        return snapshotStateObserver;
    }

    public static final <S> State<Dp> animateDp(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Dp>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Dp> function4, Composer composer, int i, int i2) {
        Object currentState;
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Dp>>() { // from class: androidx.compose.animation.core.TransitionKt.animateDp.1
                public final SpringSpec<Dp> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    composer2.startReplaceGroup(-1953972046);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1953972046, i3, -1, "androidx.compose.animation.core.animateDp.<anonymous> (Transition.kt:1977)");
                    }
                    SpringSpec<Dp> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.Companion)), 3, null);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSpring$default;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "DpAnimation";
        }
        String str2 = str;
        TwoWayConverter<Dp, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(Dp.Companion);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i5 & 14) ^ 6) > 4 && composer.changed(transition)) || (i5 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i6 = (i5 >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i6));
        int i7 = i5 & 14;
        int i8 = i7 ^ 6;
        boolean z2 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i6));
        boolean z3 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i5 >> 3) & 112)), vectorConverter, str2, composer, i7 | ((i5 << 6) & 458752));
    }

    public static final <S> State<Float> animateFloat(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Float>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Float> function4, Composer composer, int i, int i2) {
        Object currentState;
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Float>>() { // from class: androidx.compose.animation.core.TransitionKt.animateFloat.1
                public final SpringSpec<Float> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    composer2.startReplaceGroup(-985243360);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-985243360, i3, -1, "androidx.compose.animation.core.animateFloat.<anonymous> (Transition.kt:1947)");
                    }
                    SpringSpec<Float> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSpring$default;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "FloatAnimation";
        }
        String str2 = str;
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i5 & 14) ^ 6) > 4 && composer.changed(transition)) || (i5 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i6 = (i5 >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i6));
        int i7 = i5 & 14;
        int i8 = i7 ^ 6;
        boolean z2 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i6));
        boolean z3 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i5 >> 3) & 112)), vectorConverter, str2, composer, i7 | ((i5 << 6) & 458752));
    }

    public static final <S> State<Integer> animateInt(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Integer>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Integer> function4, Composer composer, int i, int i2) {
        Object currentState;
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Integer>>() { // from class: androidx.compose.animation.core.TransitionKt.animateInt.1
                public final SpringSpec<Integer> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    composer2.startReplaceGroup(2109424115);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2109424115, i3, -1, "androidx.compose.animation.core.animateInt.<anonymous> (Transition.kt:2101)");
                    }
                    SpringSpec<Integer> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, 1, 3, null);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSpring$default;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "IntAnimation";
        }
        String str2 = str;
        TwoWayConverter<Integer, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i5 & 14) ^ 6) > 4 && composer.changed(transition)) || (i5 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i6 = (i5 >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i6));
        int i7 = i5 & 14;
        int i8 = i7 ^ 6;
        boolean z2 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i6));
        boolean z3 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i5 >> 3) & 112)), vectorConverter, str2, composer, i7 | ((i5 << 6) & 458752));
    }

    public static final <S> State<IntOffset> animateIntOffset(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntOffset>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, IntOffset> function4, Composer composer, int i, int i2) {
        Object currentState;
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<IntOffset>>() { // from class: androidx.compose.animation.core.TransitionKt.animateIntOffset.1
                public final SpringSpec<IntOffset> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    composer2.startReplaceGroup(-428458074);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-428458074, i3, -1, "androidx.compose.animation.core.animateIntOffset.<anonymous> (Transition.kt:2070)");
                    }
                    SpringSpec<IntOffset> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.box-impl(IntOffset.constructor-impl(4294967297L)), 3, null);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSpring$default;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "IntOffsetAnimation";
        }
        String str2 = str;
        TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.Companion);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i5 & 14) ^ 6) > 4 && composer.changed(transition)) || (i5 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i6 = (i5 >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i6));
        int i7 = i5 & 14;
        int i8 = i7 ^ 6;
        boolean z2 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i6));
        boolean z3 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i5 >> 3) & 112)), vectorConverter, str2, composer, i7 | ((i5 << 6) & 458752));
    }

    public static final <S> State<IntSize> animateIntSize(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntSize>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, IntSize> function4, Composer composer, int i, int i2) {
        Object currentState;
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<IntSize>>() { // from class: androidx.compose.animation.core.TransitionKt.animateIntSize.1
                public final SpringSpec<IntSize> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    composer2.startReplaceGroup(811932052);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(811932052, i3, -1, "androidx.compose.animation.core.animateIntSize.<anonymous> (Transition.kt:2132)");
                    }
                    SpringSpec<IntSize> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.box-impl(IntSize.constructor-impl(4294967297L)), 3, null);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSpring$default;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "IntSizeAnimation";
        }
        String str2 = str;
        TwoWayConverter<IntSize, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntSize.Companion);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i5 & 14) ^ 6) > 4 && composer.changed(transition)) || (i5 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i6 = (i5 >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i6));
        int i7 = i5 & 14;
        int i8 = i7 ^ 6;
        boolean z2 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i6));
        boolean z3 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i5 >> 3) & 112)), vectorConverter, str2, composer, i7 | ((i5 << 6) & 458752));
    }

    public static final <S> State<Offset> animateOffset(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Offset>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Offset> function4, Composer composer, int i, int i2) {
        Object currentState;
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Offset>>() { // from class: androidx.compose.animation.core.TransitionKt.animateOffset.1
                public final SpringSpec<Offset> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    composer2.startReplaceGroup(-1662821959);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1662821959, i3, -1, "androidx.compose.animation.core.animateOffset.<anonymous> (Transition.kt:2007)");
                    }
                    SpringSpec<Offset> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.Companion)), 3, null);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSpring$default;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "OffsetAnimation";
        }
        String str2 = str;
        TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Offset.Companion);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i5 & 14) ^ 6) > 4 && composer.changed(transition)) || (i5 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i6 = (i5 >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i6));
        int i7 = i5 & 14;
        int i8 = i7 ^ 6;
        boolean z2 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i6));
        boolean z3 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i5 >> 3) & 112)), vectorConverter, str2, composer, i7 | ((i5 << 6) & 458752));
    }

    public static final <S> State<Rect> animateRect(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Rect>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Rect> function4, Composer composer, int i, int i2) {
        Object currentState;
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Rect>>() { // from class: androidx.compose.animation.core.TransitionKt.animateRect.1
                public final SpringSpec<Rect> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    composer2.startReplaceGroup(946173386);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(946173386, i3, -1, "androidx.compose.animation.core.animateRect.<anonymous> (Transition.kt:2162)");
                    }
                    SpringSpec<Rect> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.Companion), 3, null);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSpring$default;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "RectAnimation";
        }
        String str2 = str;
        TwoWayConverter<Rect, AnimationVector4D> vectorConverter = VectorConvertersKt.getVectorConverter(Rect.Companion);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i5 & 14) ^ 6) > 4 && composer.changed(transition)) || (i5 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i6 = (i5 >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i6));
        int i7 = i5 & 14;
        int i8 = i7 ^ 6;
        boolean z2 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i6));
        boolean z3 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i5 >> 3) & 112)), vectorConverter, str2, composer, i7 | ((i5 << 6) & 458752));
    }

    public static final <S> State<Size> animateSize(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Size>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Size> function4, Composer composer, int i, int i2) {
        Object currentState;
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Size>>() { // from class: androidx.compose.animation.core.TransitionKt.animateSize.1
                public final SpringSpec<Size> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    composer2.startReplaceGroup(493329511);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(493329511, i3, -1, "androidx.compose.animation.core.animateSize.<anonymous> (Transition.kt:2037)");
                    }
                    SpringSpec<Size> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.box-impl(VisibilityThresholdsKt.getVisibilityThreshold(Size.Companion)), 3, null);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return springSpecSpring$default;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                    return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "SizeAnimation";
        }
        String str2 = str;
        TwoWayConverter<Size, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Size.Companion);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i5 & 14) ^ 6) > 4 && composer.changed(transition)) || (i5 & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i6 = (i5 >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i6));
        int i7 = i5 & 14;
        int i8 = i7 ^ 6;
        boolean z2 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i6));
        boolean z3 = (i8 > 4 && composer.changed(transition)) || (i5 & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i5 >> 3) & 112)), vectorConverter, str2, composer, i7 | ((i5 << 6) & 458752));
    }

    public static final <S, T, V extends AnimationVector> State<T> animateValue(Transition<S> transition, TwoWayConverter<T, V> twoWayConverter, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<T>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, ? extends T> function4, Composer composer, int i, int i2) {
        Object currentState;
        Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<T>> function5 = (i2 & 2) != 0 ? new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<T>>() { // from class: androidx.compose.animation.core.TransitionKt.animateValue.1
            public final SpringSpec<T> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                composer2.startReplaceGroup(-2137771706);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2137771706, i3, -1, "androidx.compose.animation.core.animateValue.<anonymous> (Transition.kt:1834)");
                }
                SpringSpec<T> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return springSpecSpring$default;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
            }
        } : function3;
        String str2 = (i2 & 4) != 0 ? "ValueAnimation" : str;
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean z = (((i & 14) ^ 6) > 4 && composer.changed(transition)) || (i & 6) == 4;
            currentState = composer.rememberedValue();
            if (z || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    S currentState2 = transition.getCurrentState();
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    InlineMarker.finallyStart(1);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        int i3 = (i >> 9) & 112;
        Object objInvoke = function4.invoke(currentState, composer, Integer.valueOf(i3));
        int i4 = i & 14;
        int i5 = i4 ^ 6;
        boolean z2 = (i5 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$targetValue$1$1(transition));
            composer.updateRememberedValue(objRememberedValue);
        }
        Object objInvoke2 = function4.invoke(((State) objRememberedValue).getValue(), composer, Integer.valueOf(i3));
        boolean z3 = (i5 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new TransitionKt$animateValue$animationSpec$1$1(transition));
            composer.updateRememberedValue(objRememberedValue2);
        }
        return createTransitionAnimation(transition, objInvoke, objInvoke2, (FiniteAnimationSpec) function5.invoke(((State) objRememberedValue2).getValue(), composer, Integer.valueOf((i >> 3) & 112)), twoWayConverter, str2, composer, (57344 & (i << 9)) | i4 | (458752 & (i << 6)));
    }

    public static Unit b(Transition transition, Transition.TransitionAnimationState transitionAnimationState, Object obj, Object obj2, FiniteAnimationSpec finiteAnimationSpec, int i, Composer composer, int i2) {
        UpdateInitialAndTargetValues(transition, transitionAnimationState, obj, obj2, finiteAnimationSpec, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final <S, T> Transition<T> createChildTransition(Transition<S> transition, String str, Function3<? super S, ? super Composer, ? super Integer, ? extends T> function3, Composer composer, int i, int i2) {
        boolean z = true;
        if ((i2 & 1) != 0) {
            str = "ChildTransition";
        }
        String str2 = str;
        int i3 = i & 14;
        if (((i3 ^ 6) <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = transition.getCurrentState();
            composer.updateRememberedValue(objRememberedValue);
        }
        if (transition.isSeeking()) {
            objRememberedValue = transition.getCurrentState();
        }
        int i4 = (i >> 3) & 112;
        return createChildTransitionInternal(transition, function3.invoke(objRememberedValue, composer, Integer.valueOf(i4)), function3.invoke(transition.getTargetState(), composer, Integer.valueOf(i4)), str2, composer, i3 | ((i << 6) & 7168));
    }

    public static final <S, T> Transition<T> createChildTransitionInternal(final Transition<S> transition, T t, T t2, String str, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-198307638, i, -1, "androidx.compose.animation.core.createChildTransitionInternal (Transition.kt:1780)");
        }
        int i2 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i2 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Transition(new MutableTransitionState(t), transition, transition.getLabel() + " > " + str);
            composer.updateRememberedValue(objRememberedValue);
        }
        final Transition<T> transition2 = (Transition) objRememberedValue;
        if ((i2 <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean zChanged = composer.changed(transition2) | z;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: ike
                public final Object invoke(Object obj) {
                    return TransitionKt.createChildTransitionInternal$lambda$1$0(transition, transition2, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(transition2, (Function1) objRememberedValue2, composer, 0);
        if (transition.isSeeking()) {
            transition2.seek(t, t2, transition.getLastSeekedTimeNanos());
        } else {
            transition2.updateTarget$animation_core(t2);
            transition2.setSeeking$animation_core(false);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transition2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult createChildTransitionInternal$lambda$1$0(final Transition transition, final Transition transition2, DisposableEffectScope disposableEffectScope) {
        transition.addTransition$animation_core(transition2);
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$lambda$1$0$$inlined$onDispose$1
            public void dispose() {
                transition.removeTransition$animation_core(transition2);
            }
        };
    }

    public static final <S, T, V extends AnimationVector> Transition<S>.DeferredAnimation<T, V> createDeferredAnimation(final Transition<S> transition, TwoWayConverter<T, V> twoWayConverter, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            str = "DeferredAnimation";
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1714122528, i, -1, "androidx.compose.animation.core.createDeferredAnimation (Transition.kt:1738)");
        }
        int i3 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i3 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = transition.new DeferredAnimation(twoWayConverter, str);
            composer.updateRememberedValue(objRememberedValue);
        }
        final Transition<S>.DeferredAnimation<T, V> deferredAnimation = (Transition.DeferredAnimation) objRememberedValue;
        if ((i3 <= 4 || !composer.changed(transition)) && (i & 6) != 4) {
            z = false;
        }
        boolean zChangedInstance = composer.changedInstance(deferredAnimation) | z;
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: jke
                public final Object invoke(Object obj) {
                    return TransitionKt.createDeferredAnimation$lambda$1$0(transition, deferredAnimation, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(deferredAnimation, (Function1) objRememberedValue2, composer, 0);
        if (transition.isSeeking()) {
            deferredAnimation.setupSeeking$animation_core();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return deferredAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult createDeferredAnimation$lambda$1$0(final Transition transition, final Transition.DeferredAnimation deferredAnimation, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$lambda$1$0$$inlined$onDispose$1
            public void dispose() {
                transition.removeAnimation$animation_core(deferredAnimation);
            }
        };
    }

    public static final <S, T, V extends AnimationVector> State<T> createTransitionAnimation(final Transition<S> transition, T t, T t2, FiniteAnimationSpec<T> finiteAnimationSpec, TwoWayConverter<T, V> twoWayConverter, String str, Composer composer, int i) throws Throwable {
        Snapshot snapshot;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-304821198, i, -1, "androidx.compose.animation.core.createTransitionAnimation (Transition.kt:1869)");
        }
        int i2 = i & 14;
        int i3 = i2 ^ 6;
        boolean z = (i3 > 4 && composer.changed(transition)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            Snapshot.Companion companion = Snapshot.Companion;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                snapshot = snapshotMakeCurrentNonObservable;
                try {
                    Transition.TransitionAnimationState transitionAnimationState = transition.new TransitionAnimationState(t, AnimationStateKt.createZeroVectorFrom(twoWayConverter, t2), twoWayConverter, str);
                    companion.restoreNonObservable(currentThreadSnapshot, snapshot, readObserver);
                    composer.updateRememberedValue(transitionAnimationState);
                    objRememberedValue = transitionAnimationState;
                } catch (Throwable th) {
                    th = th;
                    companion.restoreNonObservable(currentThreadSnapshot, snapshot, readObserver);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                snapshot = snapshotMakeCurrentNonObservable;
            }
        }
        final Transition.TransitionAnimationState transitionAnimationState2 = (Transition.TransitionAnimationState) objRememberedValue;
        int i4 = (i >> 3) & 8;
        int i5 = i << 3;
        UpdateInitialAndTargetValues(transition, transitionAnimationState2, t, t2, finiteAnimationSpec, composer, (i4 << 9) | (i4 << 6) | i2 | (i5 & 896) | (i5 & 7168) | (57344 & i5));
        boolean zChanged = composer.changed(transitionAnimationState2) | ((i3 > 4 && composer.changed(transition)) || (i & 6) == 4);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: mke
                public final Object invoke(Object obj) {
                    return TransitionKt.createTransitionAnimation$lambda$1$0(transition, transitionAnimationState2, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(transitionAnimationState2, (Function1) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transitionAnimationState2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult createTransitionAnimation$lambda$1$0(final Transition transition, final Transition.TransitionAnimationState transitionAnimationState, DisposableEffectScope disposableEffectScope) {
        transition.addAnimation$animation_core(transitionAnimationState);
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$lambda$1$0$$inlined$onDispose$1
            public void dispose() {
                transition.removeAnimation$animation_core(transitionAnimationState);
            }
        };
    }

    public static Unit d(SeekableTransitionState seekableTransitionState) {
        seekableTransitionState.onTotalDurationChanged$animation_core();
        return Unit.INSTANCE;
    }

    public static final SnapshotStateObserver getSeekableStateObserver() {
        return (SnapshotStateObserver) SeekableStateObserver$delegate.getValue();
    }

    public static final <T> Transition<T> rememberTransition(TransitionState<T> transitionState, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1643203617, i, -1, "androidx.compose.animation.core.rememberTransition (Transition.kt:804)");
        }
        int i3 = (i & 14) ^ 6;
        boolean z = true;
        boolean z2 = (i3 > 4 && composer.changed(transitionState)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
            Snapshot.Companion companion = Snapshot.Companion;
            Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
            Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                Transition transition = new Transition((TransitionState) transitionState, str);
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                composer.updateRememberedValue(transition);
                objRememberedValue = transition;
            } catch (Throwable th) {
                companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                throw th;
            }
        }
        final Transition<T> transition2 = (Transition) objRememberedValue;
        if (transitionState instanceof SeekableTransitionState) {
            composer.startReplaceGroup(-1357607479);
            SeekableTransitionState seekableTransitionState = (SeekableTransitionState) transitionState;
            Object currentState = seekableTransitionState.getCurrentState();
            Object targetState = seekableTransitionState.getTargetState();
            if ((i3 <= 4 || !composer.changed(transitionState)) && (i & 6) != 4) {
                z = false;
            }
            Object objRememberedValue2 = composer.rememberedValue();
            if (z || objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = new TransitionKt$rememberTransition$1$1(transitionState, null);
                composer.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(currentState, targetState, (Function2) objRememberedValue2, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-1357145920);
            transition2.animateTo$animation_core(transitionState.getTargetState(), composer, 0);
            composer.endReplaceGroup();
        }
        boolean zChanged = composer.changed(transition2);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged || objRememberedValue3 == Composer.Companion.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: hke
                public final Object invoke(Object obj) {
                    return TransitionKt.rememberTransition$lambda$2$0(transition2, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        EffectsKt.DisposableEffect(transition2, (Function1) objRememberedValue3, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transition2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult rememberTransition$lambda$2$0(final Transition transition, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$rememberTransition$lambda$2$0$$inlined$onDispose$1
            public void dispose() {
                transition.onDisposed$animation_core();
            }
        };
    }

    public static final <T> Transition<T> updateTransition(T t, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2029166765, i, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:87)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new Transition(t, str);
            composer.updateRememberedValue(objRememberedValue);
        }
        final Transition<T> transition = (Transition) objRememberedValue;
        transition.animateTo$animation_core(t, composer, (i & 8) | 48 | (i & 14));
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: oke
                public final Object invoke(Object obj) {
                    return TransitionKt.updateTransition$lambda$1$0(transition, (DisposableEffectScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.DisposableEffect(transition, (Function1) objRememberedValue2, composer, 54);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult updateTransition$lambda$1$0(final Transition transition, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$lambda$1$0$$inlined$onDispose$1
            public void dispose() {
                transition.onDisposed$animation_core();
            }
        };
    }

    @Deprecated(message = "Use rememberTransition() instead", replaceWith = @ReplaceWith(expression = "rememberTransition(transitionState, label)", imports = {}))
    public static final <T> Transition<T> updateTransition(MutableTransitionState<T> mutableTransitionState, String str, Composer composer, int i, int i2) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(882913843, i, -1, "androidx.compose.animation.core.updateTransition (Transition.kt:863)");
        }
        Transition<T> transitionRememberTransition = rememberTransition(mutableTransitionState, str, composer, i & 126, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return transitionRememberTransition;
    }
}
