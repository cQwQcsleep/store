package androidx.compose.animation.core;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotLongStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\b\u0086\u0001\u0087\u0001\u0088\u0001\u0089\u0001B1\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0000\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB#\b\u0011\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\nB\u001b\b\u0010\u0012\u0006\u0010\u000b\u001a\u00028\u0000\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\fB#\b\u0011\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\r\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\u000eJ\b\u0010T\u001a\u00020'H\u0002J\u001d\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020'2\u0006\u0010X\u001a\u00020YH\u0000¢\u0006\u0002\bZJ\u001d\u0010U\u001a\u00020V2\u0006\u0010[\u001a\u00020'2\u0006\u0010\\\u001a\u00020%H\u0000¢\u0006\u0002\bZJ\u0015\u0010]\u001a\u00020V2\u0006\u0010W\u001a\u00020'H\u0000¢\u0006\u0002\b^J\r\u0010_\u001a\u00020VH\u0000¢\u0006\u0002\b`J\r\u0010a\u001a\u00020VH\u0000¢\u0006\u0002\bbJ'\u0010c\u001a\u00020V2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u00002\u0006\u00100\u001a\u00020'H\u0007¢\u0006\u0004\bd\u0010eJ\u0019\u0010f\u001a\u00020%2\n\u0010g\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0002\bhJ\u0019\u0010i\u001a\u00020%2\n\u0010g\u001a\u0006\u0012\u0002\b\u00030\u0000H\u0000¢\u0006\u0002\bjJ'\u0010k\u001a\u00020%2\u0018\u0010l\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030>R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\bmJ'\u0010n\u001a\u00020V2\u0018\u0010l\u001a\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030>R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\boJ\u0017\u0010p\u001a\u00020V2\u0006\u0010\u0017\u001a\u00028\u0000H\u0000¢\u0006\u0004\bq\u0010\u001aJ\u0017\u0010r\u001a\u00020V2\u0006\u0010\u0017\u001a\u00028\u0000H\u0001¢\u0006\u0004\bs\u0010tJ\u0015\u0010u\u001a\u00020V2\u0006\u00100\u001a\u00020'H\u0000¢\u0006\u0002\bvJ\u0015\u0010w\u001a\u00020V2\u0006\u0010x\u001a\u00020yH\u0000¢\u0006\u0002\bzJ\u0015\u0010{\u001a\u00020V2\u0006\u0010|\u001a\u00020YH\u0000¢\u0006\u0002\b}J\r\u0010~\u001a\u00020VH\u0000¢\u0006\u0002\b\u007fJ\u000f\u0010\u0080\u0001\u001a\u00020VH\u0000¢\u0006\u0003\b\u0081\u0001J\t\u0010\u0082\u0001\u001a\u00020\u0007H\u0016J\t\u0010\u0083\u0001\u001a\u00020VH\u0002J)\u0010n\u001a\u00020V2\u001a\u0010\u0084\u0001\u001a\u0015\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0085\u0001R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0000¢\u0006\u0002\boR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00008\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R+\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u00008F@@X\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u001aR7\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u001c\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0011\u0010$\u001a\u00020%8F¢\u0006\u0006\u001a\u0004\b$\u0010&R+\u0010(\u001a\u00020'2\u0006\u0010\u0016\u001a\u00020'8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u00100\u001a\u00020'2\u0006\u0010/\u001a\u00020'8G@GX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u0010*\"\u0004\b2\u0010,R+\u00103\u001a\u00020'2\u0006\u0010\u0016\u001a\u00020'8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010.\u001a\u0004\b4\u0010*\"\u0004\b5\u0010,R+\u00107\u001a\u00020%2\u0006\u0010\u0016\u001a\u00020%8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b;\u0010\u001c\u001a\u0004\b8\u0010&\"\u0004\b9\u0010:R&\u0010<\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030>R\b\u0012\u0004\u0012\u00028\u00000\u00000=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010?\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000=X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010@\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000A8F¢\u0006\u0006\u001a\u0004\bB\u0010CR)\u0010D\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0002\b\u0003\u0012\u0002\b\u00030>R\b\u0012\u0004\u0012\u00028\u00000\u00000A8F¢\u0006\u0006\u001a\u0004\bE\u0010CR+\u0010F\u001a\u00020%2\u0006\u0010\u0016\u001a\u00020%8G@AX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010\u001c\u001a\u0004\bF\u0010&\"\u0004\bG\u0010:R\u001a\u0010I\u001a\u00020'X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010*\"\u0004\bK\u0010,R\u001a\u0010L\u001a\u00020%8FX\u0087\u0004¢\u0006\f\u0012\u0004\bM\u0010N\u001a\u0004\bO\u0010&R\u001b\u0010P\u001a\u00020'8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bQ\u0010*¨\u0006\u008a\u0001²\u0006\u000b\u0010\u008b\u0001\u001a\u00020%X\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/animation/core/Transition;", "S", "", "transitionState", "Landroidx/compose/animation/core/TransitionState;", "parentTransition", "label", "", "<init>", "(Landroidx/compose/animation/core/TransitionState;Landroidx/compose/animation/core/Transition;Ljava/lang/String;)V", "(Landroidx/compose/animation/core/TransitionState;Ljava/lang/String;)V", "initialState", "(Ljava/lang/Object;Ljava/lang/String;)V", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;)V", "getParentTransition", "()Landroidx/compose/animation/core/Transition;", "getLabel", "()Ljava/lang/String;", "currentState", "getCurrentState", "()Ljava/lang/Object;", "<set-?>", "targetState", "getTargetState", "setTargetState$animation_core", "(Ljava/lang/Object;)V", "targetState$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/animation/core/Transition$Segment;", "segment", "getSegment", "()Landroidx/compose/animation/core/Transition$Segment;", "setSegment", "(Landroidx/compose/animation/core/Transition$Segment;)V", "segment$delegate", "isRunning", "", "()Z", "", "_playTimeNanos", "get_playTimeNanos", "()J", "set_playTimeNanos", "(J)V", "_playTimeNanos$delegate", "Landroidx/compose/runtime/MutableLongState;", "value", "playTimeNanos", "getPlayTimeNanos", "setPlayTimeNanos", "startTimeNanos", "getStartTimeNanos$animation_core", "setStartTimeNanos$animation_core", "startTimeNanos$delegate", "updateChildrenNeeded", "getUpdateChildrenNeeded", "setUpdateChildrenNeeded", "(Z)V", "updateChildrenNeeded$delegate", "_animations", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "_transitions", "transitions", "", "getTransitions", "()Ljava/util/List;", "animations", "getAnimations", "isSeeking", "setSeeking$animation_core", "isSeeking$delegate", "lastSeekedTimeNanos", "getLastSeekedTimeNanos$animation_core", "setLastSeekedTimeNanos$animation_core", "hasInitialValueAnimations", "getHasInitialValueAnimations$annotations", "()V", "getHasInitialValueAnimations", "totalDurationNanos", "getTotalDurationNanos", "totalDurationNanos$delegate", "Landroidx/compose/runtime/State;", "calculateTotalDurationNanos", "onFrame", "", "frameTimeNanos", "durationScale", "", "onFrame$animation_core", "scaledPlayTimeNanos", "scaleToEnd", "onTransitionStart", "onTransitionStart$animation_core", "onDisposed", "onDisposed$animation_core", "onTransitionEnd", "onTransitionEnd$animation_core", "setPlaytimeAfterInitialAndTargetStateEstablished", "seek", "(Ljava/lang/Object;Ljava/lang/Object;J)V", "addTransition", "transition", "addTransition$animation_core", "removeTransition", "removeTransition$animation_core", "addAnimation", "animation", "addAnimation$animation_core", "removeAnimation", "removeAnimation$animation_core", "updateTarget", "updateTarget$animation_core", "animateTo", "animateTo$animation_core", "(Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)V", "seekAnimations", "seekAnimations$animation_core", "setInitialAnimations", "animationState", "Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "setInitialAnimations$animation_core", "resetAnimationFraction", "fraction", "resetAnimationFraction$animation_core", "clearInitialAnimations", "clearInitialAnimations$animation_core", "updateInitialValues", "updateInitialValues$animation_core", "toString", "onChildAnimationUpdated", "deferredAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "TransitionAnimationState", "SegmentImpl", "Segment", "DeferredAnimation", "animation-core", "runFrameLoop"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Transition<S> {
    public static final int $stable = 0;
    private final SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> _animations;

    /* JADX INFO: renamed from: _playTimeNanos$delegate, reason: from kotlin metadata */
    private final MutableLongState _playTimeNanos;
    private final SnapshotStateList<Transition<?>> _transitions;

    /* JADX INFO: renamed from: isSeeking$delegate, reason: from kotlin metadata */
    private final MutableState isSeeking;
    private final String label;
    private long lastSeekedTimeNanos;
    private final Transition<?> parentTransition;

    /* JADX INFO: renamed from: segment$delegate, reason: from kotlin metadata */
    private final MutableState segment;

    /* JADX INFO: renamed from: startTimeNanos$delegate, reason: from kotlin metadata */
    private final MutableLongState startTimeNanos;

    /* JADX INFO: renamed from: targetState$delegate, reason: from kotlin metadata */
    private final MutableState targetState;

    /* JADX INFO: renamed from: totalDurationNanos$delegate, reason: from kotlin metadata */
    private final State totalDurationNanos;
    private final TransitionState<S> transitionState;

    /* JADX INFO: renamed from: updateChildrenNeeded$delegate, reason: from kotlin metadata */
    private final MutableState updateChildrenNeeded;

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0087\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0002*\u00020\u00032\u00020\u0004:\u0001'B%\b\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJT\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u001a2#\u0010\u001b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001e0\u001c¢\u0006\u0002\b\u001f2!\u0010 \u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0004\u0012\u00028\u00010\u001cJ\r\u0010$\u001a\u00020%H\u0000¢\u0006\u0002\b&R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR{\u0010\u0012\u001a*\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0010R\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000R\b\u0012\u0004\u0012\u00028\u00000\u00112.\u0010\u000f\u001a*\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0010R\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0000R\b\u0012\u0004\u0012\u00028\u00000\u00118@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006("}, d2 = {"Landroidx/compose/animation/core/Transition$DeferredAnimation;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "label", "", "<init>", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;)V", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "getLabel", "()Ljava/lang/String;", "<set-?>", "Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "Landroidx/compose/animation/core/Transition;", "data", "getData$animation_core", "()Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "setData$animation_core", "(Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;)V", "data$delegate", "Landroidx/compose/runtime/MutableState;", "animate", "Landroidx/compose/runtime/State;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "Lkotlin/ParameterName;", "name", "state", "setupSeeking", "", "setupSeeking$animation_core", "DeferredAnimationData", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class DeferredAnimation<T, V extends AnimationVector> {

        /* JADX INFO: renamed from: data$delegate, reason: from kotlin metadata */
        private final MutableState data = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        private final String label;
        private final TwoWayConverter<T, V> typeConverter;

        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0080\u0004\u0018\u0000*\u0004\b\u0003\u0010\u0001*\b\b\u0004\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004Bm\u0012\u001c\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0006R\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012#\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b0\t¢\u0006\u0002\b\f\u0012!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00028\u00030\t¢\u0006\u0004\b\u0011\u0010\u0012J\u0014\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\nR'\u0010\u0005\u001a\u0018\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u0006R\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R7\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u000b0\t¢\u0006\u0002\b\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R5\u0010\r\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00028\u00030\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001e\u001a\u00028\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Landroidx/compose/animation/core/Transition$DeferredAnimation$DeferredAnimationData;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/State;", "animation", "Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "Landroidx/compose/animation/core/Transition;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "Lkotlin/ParameterName;", "name", "state", "<init>", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/core/Transition$TransitionAnimationState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getAnimation", "()Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "getTransitionSpec", "()Lkotlin/jvm/functions/Function1;", "setTransitionSpec", "(Lkotlin/jvm/functions/Function1;)V", "getTargetValueByState", "setTargetValueByState", "updateAnimationStates", "", "segment", "value", "getValue", "()Ljava/lang/Object;", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public final class DeferredAnimationData<T, V extends AnimationVector> implements State<T> {
            private final Transition<S>.TransitionAnimationState<T, V> animation;
            private Function1<? super S, ? extends T> targetValueByState;
            private Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec;

            public DeferredAnimationData(Transition<S>.TransitionAnimationState<T, V> transitionAnimationState, Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> function1, Function1<? super S, ? extends T> function2) {
                this.animation = transitionAnimationState;
                this.transitionSpec = function1;
                this.targetValueByState = function2;
            }

            public final Transition<S>.TransitionAnimationState<T, V> getAnimation() {
                return this.animation;
            }

            public final Function1<S, T> getTargetValueByState() {
                return this.targetValueByState;
            }

            public final Function1<Segment<S>, FiniteAnimationSpec<T>> getTransitionSpec() {
                return this.transitionSpec;
            }

            public T getValue() {
                updateAnimationStates(Transition.this.getSegment());
                return this.animation.getValue();
            }

            public final void setTargetValueByState(Function1<? super S, ? extends T> function1) {
                this.targetValueByState = function1;
            }

            public final void setTransitionSpec(Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> function1) {
                this.transitionSpec = function1;
            }

            public final void updateAnimationStates(Segment<S> segment) {
                Object objInvoke = this.targetValueByState.invoke(segment.getTargetState());
                if (!Transition.this.isSeeking()) {
                    this.animation.updateTargetValue$animation_core((T) objInvoke, (FiniteAnimationSpec) this.transitionSpec.invoke(segment));
                } else {
                    this.animation.updateInitialAndTargetValue$animation_core((T) this.targetValueByState.invoke(segment.getInitialState()), (T) objInvoke, (FiniteAnimationSpec) this.transitionSpec.invoke(segment));
                }
            }
        }

        public DeferredAnimation(TwoWayConverter<T, V> twoWayConverter, String str) {
            this.typeConverter = twoWayConverter;
            this.label = str;
        }

        public final State<T> animate(Function1<? super Segment<S>, ? extends FiniteAnimationSpec<T>> transitionSpec, Function1<? super S, ? extends T> targetValueByState) {
            Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> data$animation_core = getData$animation_core();
            if (data$animation_core == null) {
                Transition<S> transition = Transition.this;
                data$animation_core = new DeferredAnimationData<>(transition.new TransitionAnimationState(targetValueByState.invoke(transition.getCurrentState()), AnimationStateKt.createZeroVectorFrom(this.typeConverter, targetValueByState.invoke(Transition.this.getCurrentState())), this.typeConverter, this.label), transitionSpec, targetValueByState);
                Transition<S> transition2 = Transition.this;
                setData$animation_core(data$animation_core);
                transition2.addAnimation$animation_core(data$animation_core.getAnimation());
            }
            Transition<S> transition3 = Transition.this;
            data$animation_core.setTargetValueByState(targetValueByState);
            data$animation_core.setTransitionSpec(transitionSpec);
            data$animation_core.updateAnimationStates(transition3.getSegment());
            return data$animation_core;
        }

        public final Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> getData$animation_core() {
            return (DeferredAnimationData) this.data.getValue();
        }

        public final String getLabel() {
            return this.label;
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public final void setData$animation_core(Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> deferredAnimationData) {
            this.data.setValue(deferredAnimationData);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void setupSeeking$animation_core() {
            Transition<S>.DeferredAnimationData<T, V>.DeferredAnimationData<T, V> data$animation_core = getData$animation_core();
            if (data$animation_core != null) {
                Transition<S> transition = Transition.this;
                data$animation_core.getAnimation().updateInitialAndTargetValue$animation_core(data$animation_core.getTargetValueByState().invoke(transition.getSegment().getInitialState()), data$animation_core.getTargetValueByState().invoke(transition.getSegment().getTargetState()), (FiniteAnimationSpec) data$animation_core.getTransitionSpec().invoke(transition.getSegment()));
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u001a\u0010\b\u001a\u00020\t*\u00028\u00012\u0006\u0010\u0006\u001a\u00028\u0001H\u0096\u0004¢\u0006\u0002\u0010\nR\u0012\u0010\u0003\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Landroidx/compose/animation/core/Transition$Segment;", "S", "", "initialState", "getInitialState", "()Ljava/lang/Object;", "targetState", "getTargetState", "isTransitioningTo", "", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Segment<S> {

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static <S> boolean isTransitioningTo(Segment<S> segment, S s, S s2) {
                return Segment.super.isTransitioningTo(s, s2);
            }
        }

        S getInitialState();

        S getTargetState();

        default boolean isTransitioningTo(S s, S s2) {
            return Intrinsics.areEqual(s, getInitialState()) && Intrinsics.areEqual(s2, getTargetState());
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0016\u0010\u0003\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00028\u0001X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0011"}, d2 = {"Landroidx/compose/animation/core/Transition$SegmentImpl;", "S", "Landroidx/compose/animation/core/Transition$Segment;", "initialState", "targetState", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getInitialState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTargetState", "equals", "", "other", "", "hashCode", "", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SegmentImpl<S> implements Segment<S> {
        private final S initialState;
        private final S targetState;

        public SegmentImpl(S s, S s2) {
            this.initialState = s;
            this.targetState = s2;
        }

        public boolean equals(Object other) {
            if (!(other instanceof Segment)) {
                return false;
            }
            Segment segment = (Segment) other;
            return Intrinsics.areEqual(getInitialState(), segment.getInitialState()) && Intrinsics.areEqual(getTargetState(), segment.getTargetState());
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public S getInitialState() {
            return this.initialState;
        }

        @Override // androidx.compose.animation.core.Transition.Segment
        public S getTargetState() {
            return this.targetState;
        }

        public int hashCode() {
            S initialState = getInitialState();
            int iHashCode = (initialState != null ? initialState.hashCode() : 0) * 31;
            S targetState = getTargetState();
            return iHashCode + (targetState != null ? targetState.hashCode() : 0);
        }
    }

    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u001d\b\u0087\u0004\u0018\u0000*\u0004\b\u0001\u0010\u0001*\b\b\u0002\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B5\b\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0001\u0012\u0006\u0010\u0006\u001a\u00028\u0002\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020F2\u0006\u0010R\u001a\u000200H\u0000¢\u0006\u0002\bSJ\u0015\u0010T\u001a\u00020P2\u0006\u0010Q\u001a\u00020FH\u0000¢\u0006\u0002\bUJ\r\u0010V\u001a\u00020PH\u0000¢\u0006\u0002\bWJ!\u0010Y\u001a\u00020P2\b\b\u0002\u0010\u0005\u001a\u00028\u00012\b\b\u0002\u0010Z\u001a\u000200H\u0002¢\u0006\u0002\u0010[J\r\u0010\\\u001a\u00020PH\u0000¢\u0006\u0002\b]J\u0015\u0010^\u001a\u00020P2\u0006\u0010_\u001a\u000207H\u0000¢\u0006\u0002\b`J\u0015\u0010a\u001a\u00020P2\u0006\u0010b\u001a\u00020*H\u0000¢\u0006\u0002\bcJ\r\u0010d\u001a\u00020PH\u0000¢\u0006\u0002\beJ\b\u0010f\u001a\u00020\nH\u0016J%\u0010g\u001a\u00020P2\u0006\u0010\u0012\u001a\u00028\u00012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001bH\u0000¢\u0006\u0004\bh\u0010iJ-\u0010j\u001a\u00020P2\u0006\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0012\u001a\u00028\u00012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001bH\u0000¢\u0006\u0004\bk\u0010lR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R+\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00028\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00010\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u001b8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\u0018\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 RC\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\"2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\"8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010\u0018\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010)\u001a\u0004\u0018\u00010*X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R+\u00101\u001a\u0002002\u0006\u0010\u0011\u001a\u0002008@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b6\u0010\u0018\u001a\u0004\b2\u00103\"\u0004\b4\u00105R+\u00108\u001a\u0002072\u0006\u0010\u0011\u001a\u0002078@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u000e\u0010?\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010@\u001a\u00028\u00012\u0006\u0010\u0011\u001a\u00028\u00018V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bC\u0010\u0018\u001a\u0004\bA\u0010\u0014\"\u0004\bB\u0010\u0016R\u0010\u0010D\u001a\u00028\u0002X\u0082\u000e¢\u0006\u0004\n\u0002\u0010ER+\u0010G\u001a\u00020F2\u0006\u0010\u0011\u001a\u00020F8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010M\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u000e\u0010N\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\b\u0012\u0004\u0012\u00028\u00010\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006m"}, d2 = {"Landroidx/compose/animation/core/Transition$TransitionAnimationState;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/State;", "initialValue", "initialVelocityVector", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "label", "", "<init>", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;)V", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "getLabel", "()Ljava/lang/String;", "<set-?>", "targetValue", "getTargetValue", "()Ljava/lang/Object;", "setTargetValue", "(Ljava/lang/Object;)V", "targetValue$delegate", "Landroidx/compose/runtime/MutableState;", "defaultSpring", "Landroidx/compose/animation/core/SpringSpec;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "animationSpec", "getAnimationSpec", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "animationSpec$delegate", "Landroidx/compose/animation/core/TargetBasedAnimation;", "animation", "getAnimation", "()Landroidx/compose/animation/core/TargetBasedAnimation;", "setAnimation", "(Landroidx/compose/animation/core/TargetBasedAnimation;)V", "animation$delegate", "initialValueState", "Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "getInitialValueState$animation_core", "()Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "setInitialValueState$animation_core", "(Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;)V", "initialValueAnimation", "", "isFinished", "isFinished$animation_core", "()Z", "setFinished$animation_core", "(Z)V", "isFinished$delegate", "", "resetSnapValue", "getResetSnapValue$animation_core", "()F", "setResetSnapValue$animation_core", "(F)V", "resetSnapValue$delegate", "Landroidx/compose/runtime/MutableFloatState;", "useOnlyInitialValue", "value", "getValue", "setValue$animation_core", "value$delegate", "velocityVector", "Landroidx/compose/animation/core/AnimationVector;", "", "durationNanos", "getDurationNanos$animation_core", "()J", "setDurationNanos$animation_core", "(J)V", "durationNanos$delegate", "Landroidx/compose/runtime/MutableLongState;", "isSeeking", "onPlayTimeChanged", "", "playTimeNanos", "scaleToEnd", "onPlayTimeChanged$animation_core", "seekTo", "seekTo$animation_core", "updateInitialValue", "updateInitialValue$animation_core", "interruptionSpec", "updateAnimation", "isInterrupted", "(Ljava/lang/Object;Z)V", "resetAnimation", "resetAnimation$animation_core", "resetAnimationValue", "fraction", "resetAnimationValue$animation_core", "setInitialValueAnimation", "animationState", "setInitialValueAnimation$animation_core", "clearInitialAnimation", "clearInitialAnimation$animation_core", "toString", "updateTargetValue", "updateTargetValue$animation_core", "(Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "updateInitialAndTargetValue", "updateInitialAndTargetValue$animation_core", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class TransitionAnimationState<T, V extends AnimationVector> implements State<T> {

        /* JADX INFO: renamed from: animation$delegate, reason: from kotlin metadata */
        private final MutableState animation;

        /* JADX INFO: renamed from: animationSpec$delegate, reason: from kotlin metadata */
        private final MutableState animationSpec;
        private final SpringSpec<T> defaultSpring;

        /* JADX INFO: renamed from: durationNanos$delegate, reason: from kotlin metadata */
        private final MutableLongState durationNanos;
        private TargetBasedAnimation<T, V> initialValueAnimation;
        private SeekableTransitionState.SeekingAnimationState initialValueState;
        private final FiniteAnimationSpec<T> interruptionSpec;

        /* JADX INFO: renamed from: isFinished$delegate, reason: from kotlin metadata */
        private final MutableState isFinished;
        private boolean isSeeking;
        private final String label;

        /* JADX INFO: renamed from: resetSnapValue$delegate, reason: from kotlin metadata */
        private final MutableFloatState resetSnapValue;

        /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
        private final MutableState targetValue;
        private final TwoWayConverter<T, V> typeConverter;
        private boolean useOnlyInitialValue;

        /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
        private final MutableState value;
        private V velocityVector;

        public TransitionAnimationState(T t, V v, TwoWayConverter<T, V> twoWayConverter, String str) {
            Object objInvoke;
            this.typeConverter = twoWayConverter;
            this.label = str;
            this.targetValue = SnapshotStateKt.mutableStateOf$default(t, (SnapshotMutationPolicy) null, 2, (Object) null);
            SpringSpec<T> springSpecSpring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
            this.defaultSpring = springSpecSpring$default;
            this.animationSpec = SnapshotStateKt.mutableStateOf$default(springSpecSpring$default, (SnapshotMutationPolicy) null, 2, (Object) null);
            this.animation = SnapshotStateKt.mutableStateOf$default(new TargetBasedAnimation(getAnimationSpec(), twoWayConverter, t, getTargetValue(), v), (SnapshotMutationPolicy) null, 2, (Object) null);
            this.isFinished = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, (SnapshotMutationPolicy) null, 2, (Object) null);
            this.resetSnapValue = PrimitiveSnapshotStateKt.mutableFloatStateOf(-1.0f);
            this.value = SnapshotStateKt.mutableStateOf$default(t, (SnapshotMutationPolicy) null, 2, (Object) null);
            this.velocityVector = v;
            this.durationNanos = SnapshotLongStateKt.mutableLongStateOf(getAnimation().getDurationNanos());
            Float f = VisibilityThresholdsKt.getVisibilityThresholdMap().get(twoWayConverter);
            if (f != null) {
                float fFloatValue = f.floatValue();
                AnimationVector animationVector = (AnimationVector) twoWayConverter.getConvertToVector().invoke(t);
                int size = animationVector.getSize();
                for (int i = 0; i < size; i++) {
                    animationVector.set$animation_core(i, fFloatValue);
                }
                objInvoke = this.typeConverter.getConvertFromVector().invoke(animationVector);
            } else {
                objInvoke = null;
            }
            this.interruptionSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, objInvoke, 3, null);
        }

        private final T getTargetValue() {
            return (T) this.targetValue.getValue();
        }

        private final void setAnimation(TargetBasedAnimation<T, V> targetBasedAnimation) {
            this.animation.setValue(targetBasedAnimation);
        }

        private final void setAnimationSpec(FiniteAnimationSpec<T> finiteAnimationSpec) {
            this.animationSpec.setValue(finiteAnimationSpec);
        }

        private final void setTargetValue(T t) {
            this.targetValue.setValue(t);
        }

        private final void updateAnimation(T initialValue, boolean isInterrupted) {
            TargetBasedAnimation<T, V> targetBasedAnimation = this.initialValueAnimation;
            if (Intrinsics.areEqual(targetBasedAnimation != null ? targetBasedAnimation.getTargetValue() : null, getTargetValue())) {
                setAnimation(new TargetBasedAnimation<>(this.interruptionSpec, this.typeConverter, initialValue, initialValue, AnimationVectorsKt.newInstance(this.velocityVector)));
                this.useOnlyInitialValue = true;
                setDurationNanos$animation_core(getAnimation().getDurationNanos());
                return;
            }
            AnimationSpec animationSpec = (!isInterrupted || this.isSeeking || (getAnimationSpec() instanceof SpringSpec)) ? getAnimationSpec() : this.interruptionSpec;
            if (Transition.this.getPlayTimeNanos() > 0) {
                animationSpec = AnimationSpecKt.delayed(animationSpec, Transition.this.getPlayTimeNanos());
            }
            setAnimation(new TargetBasedAnimation<>(animationSpec, this.typeConverter, initialValue, getTargetValue(), this.velocityVector));
            setDurationNanos$animation_core(getAnimation().getDurationNanos());
            this.useOnlyInitialValue = false;
            Transition.this.onChildAnimationUpdated();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void updateAnimation$default(TransitionAnimationState transitionAnimationState, Object obj, boolean z, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = transitionAnimationState.getValue();
            }
            if ((i & 2) != 0) {
                z = false;
            }
            transitionAnimationState.updateAnimation(obj, z);
        }

        public final void clearInitialAnimation$animation_core() {
            this.initialValueAnimation = null;
            this.initialValueState = null;
            this.useOnlyInitialValue = false;
        }

        public final TargetBasedAnimation<T, V> getAnimation() {
            return (TargetBasedAnimation) this.animation.getValue();
        }

        public final FiniteAnimationSpec<T> getAnimationSpec() {
            return (FiniteAnimationSpec) this.animationSpec.getValue();
        }

        public final long getDurationNanos$animation_core() {
            return this.durationNanos.getLongValue();
        }

        /* JADX INFO: renamed from: getInitialValueState$animation_core, reason: from getter */
        public final SeekableTransitionState.SeekingAnimationState getInitialValueState() {
            return this.initialValueState;
        }

        public final String getLabel() {
            return this.label;
        }

        public final float getResetSnapValue$animation_core() {
            return this.resetSnapValue.getFloatValue();
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public T getValue() {
            return (T) this.value.getValue();
        }

        public final boolean isFinished$animation_core() {
            return ((Boolean) this.isFinished.getValue()).booleanValue();
        }

        public final void onPlayTimeChanged$animation_core(long playTimeNanos, boolean scaleToEnd) {
            if (scaleToEnd) {
                playTimeNanos = getAnimation().getDurationNanos();
            }
            setValue$animation_core(getAnimation().getValueFromNanos(playTimeNanos));
            this.velocityVector = (V) getAnimation().getVelocityVectorFromNanos(playTimeNanos);
            if (getAnimation().isFinishedFromNanos(playTimeNanos)) {
                setFinished$animation_core(true);
            }
        }

        public final void resetAnimation$animation_core() {
            setResetSnapValue$animation_core(-2.0f);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void resetAnimationValue$animation_core(float fraction) {
            if (fraction != -4.0f && fraction != -5.0f) {
                setResetSnapValue$animation_core(fraction);
                return;
            }
            TargetBasedAnimation<T, V> targetBasedAnimation = this.initialValueAnimation;
            if (targetBasedAnimation != null) {
                getAnimation().setMutableInitialValue$animation_core(targetBasedAnimation.getTargetValue());
                this.initialValueState = null;
                this.initialValueAnimation = null;
            }
            Object initialValue = fraction == -4.0f ? getAnimation().getInitialValue() : getAnimation().getTargetValue();
            getAnimation().setMutableInitialValue$animation_core(initialValue);
            getAnimation().setMutableTargetValue$animation_core(initialValue);
            setValue$animation_core(initialValue);
            setDurationNanos$animation_core(getAnimation().getDurationNanos());
        }

        public final void seekTo$animation_core(long playTimeNanos) {
            if (getResetSnapValue$animation_core() == -1.0f) {
                this.isSeeking = true;
                if (Intrinsics.areEqual(getAnimation().getTargetValue(), getAnimation().getInitialValue())) {
                    setValue$animation_core(getAnimation().getTargetValue());
                } else {
                    setValue$animation_core(getAnimation().getValueFromNanos(playTimeNanos));
                    this.velocityVector = (V) getAnimation().getVelocityVectorFromNanos(playTimeNanos);
                }
            }
        }

        public final void setDurationNanos$animation_core(long j) {
            this.durationNanos.setLongValue(j);
        }

        public final void setFinished$animation_core(boolean z) {
            this.isFinished.setValue(Boolean.valueOf(z));
        }

        public final void setInitialValueAnimation$animation_core(SeekableTransitionState.SeekingAnimationState animationState) {
            if (!Intrinsics.areEqual(getAnimation().getTargetValue(), getAnimation().getInitialValue())) {
                this.initialValueAnimation = getAnimation();
                this.initialValueState = animationState;
            }
            setAnimation(new TargetBasedAnimation<>(this.interruptionSpec, this.typeConverter, getValue(), getValue(), AnimationVectorsKt.newInstance(this.velocityVector)));
            setDurationNanos$animation_core(getAnimation().getDurationNanos());
            this.useOnlyInitialValue = true;
        }

        public final void setInitialValueState$animation_core(SeekableTransitionState.SeekingAnimationState seekingAnimationState) {
            this.initialValueState = seekingAnimationState;
        }

        public final void setResetSnapValue$animation_core(float f) {
            this.resetSnapValue.setFloatValue(f);
        }

        public void setValue$animation_core(T t) {
            this.value.setValue(t);
        }

        public String toString() {
            return "current value: " + getValue() + ", target: " + getTargetValue() + ", spec: " + getAnimationSpec();
        }

        public final void updateInitialAndTargetValue$animation_core(T initialValue, T targetValue, FiniteAnimationSpec<T> animationSpec) {
            setTargetValue(targetValue);
            setAnimationSpec(animationSpec);
            if (Intrinsics.areEqual(getAnimation().getInitialValue(), initialValue) && Intrinsics.areEqual(getAnimation().getTargetValue(), targetValue)) {
                return;
            }
            updateAnimation$default(this, initialValue, false, 2, null);
        }

        public final void updateInitialValue$animation_core() {
            TargetBasedAnimation<T, V> targetBasedAnimation;
            SeekableTransitionState.SeekingAnimationState seekingAnimationState = this.initialValueState;
            if (seekingAnimationState == null || (targetBasedAnimation = this.initialValueAnimation) == null) {
                return;
            }
            long jRoundToLong = MathKt.roundToLong(seekingAnimationState.getDurationNanos() * ((double) seekingAnimationState.getValue()));
            T valueFromNanos = targetBasedAnimation.getValueFromNanos(jRoundToLong);
            if (this.useOnlyInitialValue) {
                getAnimation().setMutableTargetValue$animation_core(valueFromNanos);
            }
            getAnimation().setMutableInitialValue$animation_core(valueFromNanos);
            setDurationNanos$animation_core(getAnimation().getDurationNanos());
            if (getResetSnapValue$animation_core() == -2.0f || this.useOnlyInitialValue) {
                setValue$animation_core(valueFromNanos);
            } else {
                seekTo$animation_core(Transition.this.getPlayTimeNanos());
            }
            if (jRoundToLong < seekingAnimationState.getDurationNanos()) {
                seekingAnimationState.setComplete(false);
            } else {
                this.initialValueState = null;
                this.initialValueAnimation = null;
            }
        }

        public final void updateTargetValue$animation_core(T targetValue, FiniteAnimationSpec<T> animationSpec) {
            if (this.useOnlyInitialValue) {
                TargetBasedAnimation<T, V> targetBasedAnimation = this.initialValueAnimation;
                if (Intrinsics.areEqual(targetValue, targetBasedAnimation != null ? targetBasedAnimation.getTargetValue() : null)) {
                    return;
                }
            }
            if (Intrinsics.areEqual(getTargetValue(), targetValue) && getResetSnapValue$animation_core() == -1.0f) {
                return;
            }
            setTargetValue(targetValue);
            setAnimationSpec(animationSpec);
            updateAnimation(getResetSnapValue$animation_core() == -3.0f ? targetValue : getValue(), !isFinished$animation_core());
            setFinished$animation_core(getResetSnapValue$animation_core() == -3.0f);
            if (getResetSnapValue$animation_core() >= 0.0f) {
                setValue$animation_core(getAnimation().getValueFromNanos((long) (getAnimation().getDurationNanos() * getResetSnapValue$animation_core())));
            } else if (getResetSnapValue$animation_core() == -3.0f) {
                setValue$animation_core(targetValue);
            }
            this.useOnlyInitialValue = false;
            setResetSnapValue$animation_core(-1.0f);
        }
    }

    public Transition(TransitionState<S> transitionState, Transition<?> transition, String str) {
        this.transitionState = transitionState;
        this.parentTransition = transition;
        this.label = str;
        this.targetState = SnapshotStateKt.mutableStateOf$default(getCurrentState(), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.segment = SnapshotStateKt.mutableStateOf$default(new SegmentImpl(getCurrentState(), getCurrentState()), (SnapshotMutationPolicy) null, 2, (Object) null);
        this._playTimeNanos = SnapshotLongStateKt.mutableLongStateOf(0L);
        this.startTimeNanos = SnapshotLongStateKt.mutableLongStateOf(Long.MIN_VALUE);
        Boolean bool = Boolean.FALSE;
        this.updateChildrenNeeded = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this._animations = SnapshotStateKt.mutableStateListOf();
        this._transitions = SnapshotStateKt.mutableStateListOf();
        this.isSeeking = SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.totalDurationNanos = SnapshotStateKt.derivedStateOf(new Function0() { // from class: fke
            public final Object invoke() {
                return Long.valueOf(Transition.d(this.b));
            }
        });
        transitionState.transitionConfigured$animation_core(this);
    }

    public static Unit a(Transition transition, Object obj, int i, Composer composer, int i2) {
        transition.animateTo$animation_core(obj, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean animateTo$lambda$0$0(Transition transition) {
        return !Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState()) || transition.isRunning() || transition.getUpdateChildrenNeeded();
    }

    private static final boolean animateTo$lambda$1(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult animateTo$lambda$2$0(CoroutineScope coroutineScope, Transition transition, DisposableEffectScope disposableEffectScope) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new Transition$animateTo$1$1$1(transition, null), 1, (Object) null);
        return new DisposableEffectResult() { // from class: androidx.compose.animation.core.Transition$animateTo$lambda$2$0$$inlined$onDispose$1
            public void dispose() {
            }
        };
    }

    private final long calculateTotalDurationNanos() {
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        long jMax = 0;
        for (int i = 0; i < size; i++) {
            jMax = Math.max(jMax, ((TransitionAnimationState) snapshotStateList.get(i)).getDurationNanos$animation_core());
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            jMax = Math.max(jMax, ((Transition) snapshotStateList2.get(i2)).calculateTotalDurationNanos());
        }
        return jMax;
    }

    public static long d(Transition transition) {
        return transition.calculateTotalDurationNanos();
    }

    public static /* synthetic */ void getHasInitialValueAnimations$annotations() {
    }

    private final boolean getUpdateChildrenNeeded() {
        return ((Boolean) this.updateChildrenNeeded.getValue()).booleanValue();
    }

    private final long get_playTimeNanos() {
        return this._playTimeNanos.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onChildAnimationUpdated() {
        setUpdateChildrenNeeded(true);
        if (isSeeking()) {
            SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
            int size = snapshotStateList.size();
            long jMax = 0;
            for (int i = 0; i < size; i++) {
                TransitionAnimationState transitionAnimationState = (TransitionAnimationState) snapshotStateList.get(i);
                jMax = Math.max(jMax, transitionAnimationState.getDurationNanos$animation_core());
                transitionAnimationState.seekTo$animation_core(this.lastSeekedTimeNanos);
            }
            setUpdateChildrenNeeded(false);
        }
    }

    private final void setSegment(Segment<S> segment) {
        this.segment.setValue(segment);
    }

    private final void setUpdateChildrenNeeded(boolean z) {
        this.updateChildrenNeeded.setValue(Boolean.valueOf(z));
    }

    private final void set_playTimeNanos(long j) {
        this._playTimeNanos.setLongValue(j);
    }

    public final boolean addAnimation$animation_core(Transition<S>.TransitionAnimationState<?, ?> animation) {
        return this._animations.add(animation);
    }

    public final boolean addTransition$animation_core(Transition<?> transition) {
        return this._transitions.add(transition);
    }

    public final void animateTo$animation_core(final S s, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1493585151);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(s) : composerStartRestartGroup.changedInstance(s) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(this) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1493585151, i2, -1, "androidx.compose.animation.core.Transition.animateTo (Transition.kt:1180)");
            }
            if (isSeeking()) {
                composerStartRestartGroup.startReplaceGroup(416369985);
            } else {
                composerStartRestartGroup.startReplaceGroup(466062241);
                updateTarget$animation_core(s);
                int i3 = i2 & 112;
                boolean z = i3 == 32;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z || objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: cke
                        public final Object invoke() {
                            return Boolean.valueOf(Transition.animateTo$lambda$0$0(this.b));
                        }
                    });
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                if (animateTo$lambda$1((State) objRememberedValue)) {
                    composerStartRestartGroup.startReplaceGroup(466470356);
                    Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    Composer.Companion companion = Composer.Companion;
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue2;
                    boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope) | (i3 == 32);
                    Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
                        objRememberedValue3 = new Function1() { // from class: dke
                            public final Object invoke(Object obj) {
                                return Transition.animateTo$lambda$2$0(coroutineScope, this, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    EffectsKt.DisposableEffect(coroutineScope, this, (Function1) objRememberedValue3, composerStartRestartGroup, i3);
                } else {
                    composerStartRestartGroup.startReplaceGroup(416369985);
                }
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: eke
                public final Object invoke(Object obj, Object obj2) {
                    return Transition.a(this.b, s, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final void clearInitialAnimations$animation_core() {
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((TransitionAnimationState) snapshotStateList.get(i)).clearInitialAnimation$animation_core();
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((Transition) snapshotStateList2.get(i2)).clearInitialAnimations$animation_core();
        }
    }

    public final List<Transition<S>.TransitionAnimationState<?, ?>> getAnimations() {
        return this._animations;
    }

    public final S getCurrentState() {
        return this.transitionState.getCurrentState();
    }

    public final boolean getHasInitialValueAnimations() {
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            if (((TransitionAnimationState) snapshotStateList.get(i)).getInitialValueState() != null) {
                return true;
            }
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (((Transition) snapshotStateList2.get(i2)).getHasInitialValueAnimations()) {
                return true;
            }
        }
        return false;
    }

    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: getLastSeekedTimeNanos$animation_core, reason: from getter */
    public final long getLastSeekedTimeNanos() {
        return this.lastSeekedTimeNanos;
    }

    public final Transition<?> getParentTransition() {
        return this.parentTransition;
    }

    public final long getPlayTimeNanos() {
        Transition<?> transition = this.parentTransition;
        return transition != null ? transition.getPlayTimeNanos() : get_playTimeNanos();
    }

    public final Segment<S> getSegment() {
        return (Segment) this.segment.getValue();
    }

    public final long getStartTimeNanos$animation_core() {
        return this.startTimeNanos.getLongValue();
    }

    public final S getTargetState() {
        return (S) this.targetState.getValue();
    }

    public final long getTotalDurationNanos() {
        return ((Number) this.totalDurationNanos.getValue()).longValue();
    }

    public final List<Transition<?>> getTransitions() {
        return this._transitions;
    }

    public final boolean isRunning() {
        return getStartTimeNanos$animation_core() != Long.MIN_VALUE;
    }

    public final boolean isSeeking() {
        return ((Boolean) this.isSeeking.getValue()).booleanValue();
    }

    public final void onDisposed$animation_core() {
        onTransitionEnd$animation_core();
        this.transitionState.transitionRemoved$animation_core();
    }

    public final void onFrame$animation_core(long scaledPlayTimeNanos, boolean scaleToEnd) {
        boolean z = true;
        if (getStartTimeNanos$animation_core() == Long.MIN_VALUE) {
            onTransitionStart$animation_core(scaledPlayTimeNanos);
        } else if (!this.transitionState.isRunning$animation_core()) {
            this.transitionState.setRunning$animation_core(true);
        }
        setUpdateChildrenNeeded(false);
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            TransitionAnimationState transitionAnimationState = (TransitionAnimationState) snapshotStateList.get(i);
            if (!transitionAnimationState.isFinished$animation_core()) {
                transitionAnimationState.onPlayTimeChanged$animation_core(scaledPlayTimeNanos, scaleToEnd);
            }
            if (!transitionAnimationState.isFinished$animation_core()) {
                z = false;
            }
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Transition transition = (Transition) snapshotStateList2.get(i2);
            if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                transition.onFrame$animation_core(scaledPlayTimeNanos, scaleToEnd);
            }
            if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                z = false;
            }
        }
        if (z) {
            onTransitionEnd$animation_core();
        }
    }

    public final void onTransitionEnd$animation_core() {
        setStartTimeNanos$animation_core(Long.MIN_VALUE);
        TransitionState<S> transitionState = this.transitionState;
        if (transitionState instanceof MutableTransitionState) {
            ((MutableTransitionState) transitionState).setCurrentState$animation_core(getTargetState());
        }
        setPlayTimeNanos(0L);
        this.transitionState.setRunning$animation_core(false);
        SnapshotStateList<Transition<?>> snapshotStateList = this._transitions;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((Transition) snapshotStateList.get(i)).onTransitionEnd$animation_core();
        }
    }

    public final void onTransitionStart$animation_core(long frameTimeNanos) {
        setStartTimeNanos$animation_core(frameTimeNanos);
        this.transitionState.setRunning$animation_core(true);
    }

    public final void removeAnimation$animation_core(Transition<S>.DeferredAnimation<?, ?> deferredAnimation) {
        Transition<S>.TransitionAnimationState<?, ?> animation;
        Transition<S>.DeferredAnimationData<?, ?>.DeferredAnimationData<?, V> data$animation_core = deferredAnimation.getData$animation_core();
        if (data$animation_core == 0 || (animation = data$animation_core.getAnimation()) == null) {
            return;
        }
        removeAnimation$animation_core(animation);
    }

    public final boolean removeTransition$animation_core(Transition<?> transition) {
        return this._transitions.remove(transition);
    }

    public final void resetAnimationFraction$animation_core(float fraction) {
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((TransitionAnimationState) snapshotStateList.get(i)).resetAnimationValue$animation_core(fraction);
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((Transition) snapshotStateList2.get(i2)).resetAnimationFraction$animation_core(fraction);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void seek(S initialState, S targetState, long playTimeNanos) {
        setStartTimeNanos$animation_core(Long.MIN_VALUE);
        this.transitionState.setRunning$animation_core(false);
        if (!isSeeking() || !Intrinsics.areEqual(getCurrentState(), initialState) || !Intrinsics.areEqual(getTargetState(), targetState)) {
            if (!Intrinsics.areEqual(getCurrentState(), initialState)) {
                TransitionState<S> transitionState = this.transitionState;
                if (transitionState instanceof MutableTransitionState) {
                    ((MutableTransitionState) transitionState).setCurrentState$animation_core(initialState);
                }
            }
            setTargetState$animation_core(targetState);
            setSeeking$animation_core(true);
            setSegment(new SegmentImpl(initialState, targetState));
        }
        SnapshotStateList<Transition<?>> snapshotStateList = this._transitions;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            Transition transition = (Transition) snapshotStateList.get(i);
            transition.getClass();
            if (transition.isSeeking()) {
                transition.seek(transition.getCurrentState(), transition.getTargetState(), playTimeNanos);
            }
        }
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList2 = this._animations;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((TransitionAnimationState) snapshotStateList2.get(i2)).seekTo$animation_core(playTimeNanos);
        }
        this.lastSeekedTimeNanos = playTimeNanos;
    }

    public final void seekAnimations$animation_core(long playTimeNanos) {
        if (getStartTimeNanos$animation_core() == Long.MIN_VALUE) {
            setStartTimeNanos$animation_core(playTimeNanos);
        }
        setPlayTimeNanos(playTimeNanos);
        setUpdateChildrenNeeded(false);
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((TransitionAnimationState) snapshotStateList.get(i)).seekTo$animation_core(playTimeNanos);
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Transition transition = (Transition) snapshotStateList2.get(i2);
            if (!Intrinsics.areEqual(transition.getTargetState(), transition.getCurrentState())) {
                transition.seekAnimations$animation_core(playTimeNanos);
            }
        }
    }

    public final void setInitialAnimations$animation_core(SeekableTransitionState.SeekingAnimationState animationState) {
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((TransitionAnimationState) snapshotStateList.get(i)).setInitialValueAnimation$animation_core(animationState);
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((Transition) snapshotStateList2.get(i2)).setInitialAnimations$animation_core(animationState);
        }
    }

    public final void setLastSeekedTimeNanos$animation_core(long j) {
        this.lastSeekedTimeNanos = j;
    }

    public final void setPlayTimeNanos(long j) {
        if (this.parentTransition == null) {
            set_playTimeNanos(j);
        }
    }

    public final void setSeeking$animation_core(boolean z) {
        this.isSeeking.setValue(Boolean.valueOf(z));
    }

    public final void setStartTimeNanos$animation_core(long j) {
        this.startTimeNanos.setLongValue(j);
    }

    public final void setTargetState$animation_core(S s) {
        this.targetState.setValue(s);
    }

    public String toString() {
        List<Transition<S>.TransitionAnimationState<?, ?>> animations = getAnimations();
        int size = animations.size();
        String str = "Transition animation values: ";
        for (int i = 0; i < size; i++) {
            str = str + animations.get(i) + ", ";
        }
        return str;
    }

    public final void updateInitialValues$animation_core() {
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((TransitionAnimationState) snapshotStateList.get(i)).updateInitialValue$animation_core();
        }
        SnapshotStateList<Transition<?>> snapshotStateList2 = this._transitions;
        int size2 = snapshotStateList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((Transition) snapshotStateList2.get(i2)).updateInitialValues$animation_core();
        }
    }

    public final void updateTarget$animation_core(S targetState) {
        if (Intrinsics.areEqual(getTargetState(), targetState)) {
            return;
        }
        setSegment(new SegmentImpl(getTargetState(), targetState));
        if (!Intrinsics.areEqual(getCurrentState(), getTargetState())) {
            this.transitionState.setCurrentState$animation_core(getTargetState());
        }
        setTargetState$animation_core(targetState);
        if (!isRunning()) {
            setUpdateChildrenNeeded(true);
        }
        SnapshotStateList<Transition<S>.TransitionAnimationState<?, ?>> snapshotStateList = this._animations;
        int size = snapshotStateList.size();
        for (int i = 0; i < size; i++) {
            ((TransitionAnimationState) snapshotStateList.get(i)).resetAnimation$animation_core();
        }
    }

    public final void removeAnimation$animation_core(Transition<S>.TransitionAnimationState<?, ?> animation) {
        this._animations.remove(animation);
    }

    public /* synthetic */ Transition(TransitionState transitionState, Transition transition, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(transitionState, transition, (i & 4) != 0 ? null : str);
    }

    public /* synthetic */ Transition(TransitionState transitionState, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(transitionState, (i & 2) != 0 ? null : str);
    }

    public Transition(TransitionState<S> transitionState, String str) {
        this(transitionState, null, str);
    }

    public Transition(S s, String str) {
        this(new MutableTransitionState(s), null, str);
    }

    public /* synthetic */ Transition(MutableTransitionState mutableTransitionState, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableTransitionState, (i & 2) != 0 ? null : str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Transition(MutableTransitionState<S> mutableTransitionState, String str) {
        this(mutableTransitionState, null, str);
        mutableTransitionState.getClass();
    }

    public final void onFrame$animation_core(long frameTimeNanos, float durationScale) {
        if (getStartTimeNanos$animation_core() == Long.MIN_VALUE) {
            onTransitionStart$animation_core(frameTimeNanos);
        }
        long startTimeNanos$animation_core = frameTimeNanos - getStartTimeNanos$animation_core();
        if (durationScale != 0.0f) {
            startTimeNanos$animation_core = MathKt.roundToLong(startTimeNanos$animation_core / ((double) durationScale));
        }
        setPlayTimeNanos(startTimeNanos$animation_core);
        onFrame$animation_core(startTimeNanos$animation_core, durationScale == 0.0f);
    }
}
