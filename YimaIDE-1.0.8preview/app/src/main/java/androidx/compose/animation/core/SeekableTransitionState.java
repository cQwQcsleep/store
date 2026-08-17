package androidx.compose.animation.core;

import androidx.collection.MutableObjectList;
import androidx.compose.animation.core.SeekableTransitionState;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.Mutex$DefaultImpls;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u0000 Z*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002YZB\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010=\u001a\u00020\u001fH\u0002J\u000e\u0010>\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010@\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010A\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u0018\u0010B\u001a\u00020\u001f2\u0006\u0010C\u001a\u0002072\u0006\u0010D\u001a\u00020\u0018H\u0002J\u0016\u0010E\u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010FJ\"\u0010G\u001a\u00020\u001f2\b\b\u0001\u0010!\u001a\u00020 2\b\b\u0002\u0010\u0007\u001a\u00028\u0000H\u0086@¢\u0006\u0002\u0010HJ\u000e\u0010I\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\u000e\u0010J\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010?J\b\u0010K\u001a\u00020\u001fH\u0002J*\u0010L\u001a\u00020\u001f2\b\b\u0002\u0010\u0007\u001a\u00028\u00002\u0010\b\u0002\u0010M\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010NH\u0086@¢\u0006\u0002\u0010OJ\u001b\u0010P\u001a\u00020\u001f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0010¢\u0006\u0002\bQJ\r\u0010R\u001a\u00020\u001fH\u0010¢\u0006\u0002\bSJ\r\u0010T\u001a\u00020\u001fH\u0000¢\u0006\u0002\bUJ\r\u0010V\u001a\u00020\u001fH\u0000¢\u0006\u0002\bWJ\b\u0010X\u001a\u00020\u001fH\u0002R+\u0010\u0007\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0005R+\u0010\r\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\f\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u0005R\u001c\u0010\u0011\u001a\u00028\u0000X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u0005R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010!\u001a\u00020 2\u0006\u0010\u0006\u001a\u00020 8G@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010(\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010)X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020/X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001f0:X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001f0:X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006["}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState;", "S", "Landroidx/compose/animation/core/TransitionState;", "initialState", "<init>", "(Ljava/lang/Object;)V", "<set-?>", "targetState", "getTargetState", "()Ljava/lang/Object;", "setTargetState$animation_core", "targetState$delegate", "Landroidx/compose/runtime/MutableState;", "currentState", "getCurrentState", "setCurrentState$animation_core", "currentState$delegate", "composedTargetState", "getComposedTargetState$animation_core", "setComposedTargetState$animation_core", "Ljava/lang/Object;", "transition", "Landroidx/compose/animation/core/Transition;", "totalDurationNanos", "", "getTotalDurationNanos$animation_core", "()J", "setTotalDurationNanos$animation_core", "(J)V", "recalculateTotalDurationNanos", "Lkotlin/Function0;", "", "", "fraction", "getFraction", "()F", "setFraction", "(F)V", "fraction$delegate", "Landroidx/compose/runtime/MutableFloatState;", "compositionContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "getCompositionContinuation$animation_core", "()Lkotlinx/coroutines/CancellableContinuation;", "setCompositionContinuation$animation_core", "(Lkotlinx/coroutines/CancellableContinuation;)V", "compositionContinuationMutex", "Lkotlinx/coroutines/sync/Mutex;", "getCompositionContinuationMutex$animation_core", "()Lkotlinx/coroutines/sync/Mutex;", "mutatorMutex", "Landroidx/compose/animation/core/MutatorMutex;", "lastFrameTimeNanos", "initialValueAnimations", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "currentAnimation", "firstFrameLambda", "Lkotlin/Function1;", "durationScale", "animateOneFrameLambda", "endAllAnimations", "runAnimations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doOneFrame", "animateOneFrame", "recalculateAnimationValue", "animation", "deltaPlayTimeNanos", "snapTo", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "seekTo", "(FLjava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForCompositionAfterTargetStateChange", "waitForComposition", "moveAnimationToInitialState", "animateTo", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "(Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transitionConfigured", "transitionConfigured$animation_core", "transitionRemoved", "transitionRemoved$animation_core", "observeTotalDuration", "observeTotalDuration$animation_core", "onTotalDurationChanged", "onTotalDurationChanged$animation_core", "seekToFraction", "SeekingAnimationState", "Companion", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SeekableTransitionState<S> extends TransitionState<S> {
    private final Function1<Long, Unit> animateOneFrameLambda;
    private S composedTargetState;
    private CancellableContinuation<? super S> compositionContinuation;
    private final Mutex compositionContinuationMutex;
    private SeekingAnimationState currentAnimation;

    /* JADX INFO: renamed from: currentState$delegate, reason: from kotlin metadata */
    private final MutableState currentState;
    private float durationScale;
    private final Function1<Long, Unit> firstFrameLambda;

    /* JADX INFO: renamed from: fraction$delegate, reason: from kotlin metadata */
    private final MutableFloatState fraction;
    private final MutableObjectList<SeekingAnimationState> initialValueAnimations;
    private long lastFrameTimeNanos;
    private final MutatorMutex mutatorMutex;
    private final Function0<Unit> recalculateTotalDurationNanos;

    /* JADX INFO: renamed from: targetState$delegate, reason: from kotlin metadata */
    private final MutableState targetState;
    private long totalDurationNanos;
    private Transition<S> transition;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final AnimationVector1D ZeroVelocity = new AnimationVector1D(0.0f);
    private static final AnimationVector1D Target1 = new AnimationVector1D(1.0f);

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010*\u001a\u00020+H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001a\u0010$\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0007\"\u0004\b&\u0010\tR\u001a\u0010'\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0007\"\u0004\b)\u0010\t¨\u0006,"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState$SeekingAnimationState;", "", "<init>", "()V", "progressNanos", "", "getProgressNanos", "()J", "setProgressNanos", "(J)V", "animationSpec", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "Landroidx/compose/animation/core/AnimationVector1D;", "getAnimationSpec", "()Landroidx/compose/animation/core/VectorizedAnimationSpec;", "setAnimationSpec", "(Landroidx/compose/animation/core/VectorizedAnimationSpec;)V", "isComplete", "", "()Z", "setComplete", "(Z)V", "value", "", "getValue", "()F", "setValue", "(F)V", "start", "getStart", "()Landroidx/compose/animation/core/AnimationVector1D;", "setStart", "(Landroidx/compose/animation/core/AnimationVector1D;)V", "initialVelocity", "getInitialVelocity", "setInitialVelocity", "durationNanos", "getDurationNanos", "setDurationNanos", "animationSpecDuration", "getAnimationSpecDuration", "setAnimationSpecDuration", "toString", "", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SeekingAnimationState {
        public static final int $stable = 8;
        private VectorizedAnimationSpec<AnimationVector1D> animationSpec;
        private long animationSpecDuration;
        private long durationNanos;
        private AnimationVector1D initialVelocity;
        private boolean isComplete;
        private long progressNanos;
        private AnimationVector1D start = new AnimationVector1D(0.0f);
        private float value;

        public final VectorizedAnimationSpec<AnimationVector1D> getAnimationSpec() {
            return this.animationSpec;
        }

        public final long getAnimationSpecDuration() {
            return this.animationSpecDuration;
        }

        public final long getDurationNanos() {
            return this.durationNanos;
        }

        public final AnimationVector1D getInitialVelocity() {
            return this.initialVelocity;
        }

        public final long getProgressNanos() {
            return this.progressNanos;
        }

        public final AnimationVector1D getStart() {
            return this.start;
        }

        public final float getValue() {
            return this.value;
        }

        /* JADX INFO: renamed from: isComplete, reason: from getter */
        public final boolean getIsComplete() {
            return this.isComplete;
        }

        public final void setAnimationSpec(VectorizedAnimationSpec<AnimationVector1D> vectorizedAnimationSpec) {
            this.animationSpec = vectorizedAnimationSpec;
        }

        public final void setAnimationSpecDuration(long j) {
            this.animationSpecDuration = j;
        }

        public final void setComplete(boolean z) {
            this.isComplete = z;
        }

        public final void setDurationNanos(long j) {
            this.durationNanos = j;
        }

        public final void setInitialVelocity(AnimationVector1D animationVector1D) {
            this.initialVelocity = animationVector1D;
        }

        public final void setProgressNanos(long j) {
            this.progressNanos = j;
        }

        public final void setStart(AnimationVector1D animationVector1D) {
            this.start = animationVector1D;
        }

        public final void setValue(float f) {
            this.value = f;
        }

        public String toString() {
            return "progress nanos: " + this.progressNanos + ", animationSpec: " + this.animationSpec + ", isComplete: " + this.isComplete + ", value: " + this.value + ", start: " + this.start + ", initialVelocity: " + this.initialVelocity + ", durationNanos: " + this.durationNanos + ", animationSpecDuration: " + this.animationSpecDuration;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$animateTo$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2", f = "Transition.kt", i = {}, l = {600}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$animateTo$2$1", f = "Transition.kt", i = {0}, l = {2174, 613, 615, 669, 671}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"}, v = 1)
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
            final /* synthetic */ S $targetState;
            final /* synthetic */ Transition<S> $transition;
            Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = seekableTransitionState;
                this.$targetState = s;
                this.$transition = transition;
                this.$animationSpec = finiteAnimationSpec;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:32:0x00b9  */
            /* JADX WARN: Code duplicated, block: B:34:0x00c7  */
            /* JADX WARN: Code duplicated, block: B:36:0x00d3  */
            /* JADX WARN: Code duplicated, block: B:38:0x00dd  */
            /* JADX WARN: Code duplicated, block: B:39:0x00e8  */
            /* JADX WARN: Code duplicated, block: B:41:0x00eb  */
            /* JADX WARN: Code duplicated, block: B:43:0x00f5 A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:44:0x00f7  */
            /* JADX WARN: Code duplicated, block: B:45:0x00fd  */
            /* JADX WARN: Code duplicated, block: B:47:0x0100  */
            /* JADX WARN: Code duplicated, block: B:49:0x0116  */
            /* JADX WARN: Code duplicated, block: B:51:0x0127  */
            /* JADX WARN: Code duplicated, block: B:52:0x0129  */
            /* JADX WARN: Code duplicated, block: B:62:0x015c  */
            /* JADX WARN: Code duplicated, block: B:64:0x0166  */
            /* JADX WARN: Code duplicated, block: B:67:0x0199  */
            /* JADX WARN: Code duplicated, block: B:68:0x01aa  */
            /* JADX WARN: Code duplicated, block: B:73:0x01d7  */
            /* JADX WARN: Code restructure failed: missing block: B:74:0x01e6, code lost:
            
                if (r2.waitForComposition(r18) == r1) goto L75;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object invokeSuspend(Object obj) {
                Mutex mutex;
                SeekableTransitionState<S> seekableTransitionState;
                SeekableTransitionState<S> seekableTransitionState2;
                SeekableTransitionState<S> seekableTransitionState3;
                SeekingAnimationState seekingAnimationState;
                FiniteAnimationSpec<Float> finiteAnimationSpec;
                VectorizedAnimationSpec<AnimationVector1D> vectorizedAnimationSpecVectorize;
                VectorizedAnimationSpec<AnimationVector1D> animationSpec;
                AnimationVector1D zeroVelocity;
                long jRoundToLong;
                AnimationVector1D initialVelocity;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        S targetState = this.this$0.getTargetState();
                        if (!Intrinsics.areEqual(this.$targetState, targetState)) {
                            this.this$0.moveAnimationToInitialState();
                            this.this$0.setFraction(0.0f);
                            this.$transition.updateTarget$animation_core(this.$targetState);
                            this.$transition.setPlayTimeNanos(0L);
                            this.this$0.setCurrentState$animation_core(targetState);
                            this.this$0.setTargetState$animation_core(this.$targetState);
                        }
                        Mutex compositionContinuationMutex = this.this$0.getCompositionContinuationMutex();
                        SeekableTransitionState<S> seekableTransitionState4 = this.this$0;
                        this.L$0 = compositionContinuationMutex;
                        this.L$1 = seekableTransitionState4;
                        this.label = 1;
                        if (compositionContinuationMutex.lock((Object) null, this) != coroutine_suspended) {
                            mutex = compositionContinuationMutex;
                            seekableTransitionState = seekableTransitionState4;
                        }
                        return coroutine_suspended;
                    }
                    if (i == 1) {
                        seekableTransitionState = (SeekableTransitionState) this.L$1;
                        mutex = (Mutex) this.L$0;
                        ResultKt.throwOnFailure(obj);
                    } else {
                        if (i == 2) {
                            ResultKt.throwOnFailure(obj);
                            seekableTransitionState2 = this.this$0;
                            this.label = 3;
                            if (seekableTransitionState2.waitForCompositionAfterTargetStateChange(this) != coroutine_suspended) {
                                if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                    if (this.this$0.getFraction() < 1.0f) {
                                        seekingAnimationState = ((SeekableTransitionState) this.this$0).currentAnimation;
                                        finiteAnimationSpec = this.$animationSpec;
                                        if (finiteAnimationSpec != null) {
                                            vectorizedAnimationSpecVectorize = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                        } else {
                                            vectorizedAnimationSpecVectorize = null;
                                        }
                                        if (seekingAnimationState != null) {
                                            if (seekingAnimationState != null) {
                                                animationSpec = seekingAnimationState.getAnimationSpec();
                                            } else {
                                                animationSpec = null;
                                            }
                                            if (animationSpec != null) {
                                                long progressNanos = seekingAnimationState.getProgressNanos();
                                                AnimationVector1D start = seekingAnimationState.getStart();
                                                AnimationVector1D target1 = SeekableTransitionState.Companion.getTarget1();
                                                initialVelocity = seekingAnimationState.getInitialVelocity();
                                                if (initialVelocity == null) {
                                                    initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                }
                                                zeroVelocity = (AnimationVector1D) animationSpec.getVelocityFromNanos(progressNanos, start, target1, initialVelocity);
                                            } else if (seekingAnimationState != null) {
                                                zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            } else {
                                                zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            if (seekingAnimationState == null) {
                                                seekingAnimationState = new SeekingAnimationState();
                                            }
                                            seekingAnimationState.setAnimationSpec(vectorizedAnimationSpecVectorize);
                                            seekingAnimationState.setComplete(false);
                                            seekingAnimationState.setValue(this.this$0.getFraction());
                                            seekingAnimationState.getStart().set$animation_core(0, this.this$0.getFraction());
                                            seekingAnimationState.setDurationNanos(this.this$0.getTotalDurationNanos());
                                            seekingAnimationState.setProgressNanos(0L);
                                            seekingAnimationState.setInitialVelocity(zeroVelocity);
                                            if (vectorizedAnimationSpecVectorize != null) {
                                                jRoundToLong = vectorizedAnimationSpecVectorize.getDurationNanos(seekingAnimationState.getStart(), SeekableTransitionState.Companion.getTarget1(), zeroVelocity);
                                            } else {
                                                jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                            }
                                            seekingAnimationState.setAnimationSpecDuration(jRoundToLong);
                                            ((SeekableTransitionState) this.this$0).currentAnimation = seekingAnimationState;
                                        } else {
                                            if (seekingAnimationState != null) {
                                                animationSpec = seekingAnimationState.getAnimationSpec();
                                            } else {
                                                animationSpec = null;
                                            }
                                            if (animationSpec != null) {
                                                long progressNanos2 = seekingAnimationState.getProgressNanos();
                                                AnimationVector1D start2 = seekingAnimationState.getStart();
                                                AnimationVector1D target2 = SeekableTransitionState.Companion.getTarget1();
                                                initialVelocity = seekingAnimationState.getInitialVelocity();
                                                if (initialVelocity == null) {
                                                    initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                                }
                                                zeroVelocity = (AnimationVector1D) animationSpec.getVelocityFromNanos(progressNanos2, start2, target2, initialVelocity);
                                            } else if (seekingAnimationState != null) {
                                                zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            } else {
                                                zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            if (seekingAnimationState == null) {
                                                seekingAnimationState = new SeekingAnimationState();
                                            }
                                            seekingAnimationState.setAnimationSpec(vectorizedAnimationSpecVectorize);
                                            seekingAnimationState.setComplete(false);
                                            seekingAnimationState.setValue(this.this$0.getFraction());
                                            seekingAnimationState.getStart().set$animation_core(0, this.this$0.getFraction());
                                            seekingAnimationState.setDurationNanos(this.this$0.getTotalDurationNanos());
                                            seekingAnimationState.setProgressNanos(0L);
                                            seekingAnimationState.setInitialVelocity(zeroVelocity);
                                            if (vectorizedAnimationSpecVectorize != null) {
                                                jRoundToLong = vectorizedAnimationSpecVectorize.getDurationNanos(seekingAnimationState.getStart(), SeekableTransitionState.Companion.getTarget1(), zeroVelocity);
                                            } else {
                                                jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                            }
                                            seekingAnimationState.setAnimationSpecDuration(jRoundToLong);
                                            ((SeekableTransitionState) this.this$0).currentAnimation = seekingAnimationState;
                                        }
                                    }
                                    seekableTransitionState3 = this.this$0;
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.label = 4;
                                    if (seekableTransitionState3.runAnimations(this) != coroutine_suspended) {
                                        this.this$0.setCurrentState$animation_core(this.$targetState);
                                        SeekableTransitionState<S> seekableTransitionState5 = this.this$0;
                                        this.label = 5;
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                            return coroutine_suspended;
                        }
                        if (i == 3) {
                            ResultKt.throwOnFailure(obj);
                            if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                if (this.this$0.getFraction() < 1.0f) {
                                    seekingAnimationState = ((SeekableTransitionState) this.this$0).currentAnimation;
                                    finiteAnimationSpec = this.$animationSpec;
                                    if (finiteAnimationSpec != null) {
                                        vectorizedAnimationSpecVectorize = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                    } else {
                                        vectorizedAnimationSpecVectorize = null;
                                    }
                                    if (seekingAnimationState != null || !Intrinsics.areEqual(vectorizedAnimationSpecVectorize, seekingAnimationState.getAnimationSpec())) {
                                        if (seekingAnimationState != null) {
                                            animationSpec = seekingAnimationState.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        if (animationSpec != null) {
                                            long progressNanos3 = seekingAnimationState.getProgressNanos();
                                            AnimationVector1D start3 = seekingAnimationState.getStart();
                                            AnimationVector1D target3 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = seekingAnimationState.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            zeroVelocity = (AnimationVector1D) animationSpec.getVelocityFromNanos(progressNanos3, start3, target3, initialVelocity);
                                        } else if (seekingAnimationState != null || seekingAnimationState.getProgressNanos() == 0) {
                                            zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            long durationNanos = seekingAnimationState.getDurationNanos();
                                            if (durationNanos == Long.MIN_VALUE) {
                                                durationNanos = this.this$0.getTotalDurationNanos();
                                            }
                                            float f = durationNanos / 1.0E9f;
                                            zeroVelocity = f <= 0.0f ? SeekableTransitionState.Companion.getZeroVelocity() : new AnimationVector1D(1.0f / f);
                                        }
                                        if (seekingAnimationState == null) {
                                            seekingAnimationState = new SeekingAnimationState();
                                        }
                                        seekingAnimationState.setAnimationSpec(vectorizedAnimationSpecVectorize);
                                        seekingAnimationState.setComplete(false);
                                        seekingAnimationState.setValue(this.this$0.getFraction());
                                        seekingAnimationState.getStart().set$animation_core(0, this.this$0.getFraction());
                                        seekingAnimationState.setDurationNanos(this.this$0.getTotalDurationNanos());
                                        seekingAnimationState.setProgressNanos(0L);
                                        seekingAnimationState.setInitialVelocity(zeroVelocity);
                                        if (vectorizedAnimationSpecVectorize != null) {
                                            jRoundToLong = vectorizedAnimationSpecVectorize.getDurationNanos(seekingAnimationState.getStart(), SeekableTransitionState.Companion.getTarget1(), zeroVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                        }
                                        seekingAnimationState.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) this.this$0).currentAnimation = seekingAnimationState;
                                    }
                                }
                                seekableTransitionState3 = this.this$0;
                                this.L$0 = null;
                                this.L$1 = null;
                                this.label = 4;
                                if (seekableTransitionState3.runAnimations(this) != coroutine_suspended) {
                                    this.this$0.setCurrentState$animation_core(this.$targetState);
                                    SeekableTransitionState<S> seekableTransitionState6 = this.this$0;
                                    this.label = 5;
                                }
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                        if (i == 4) {
                            ResultKt.throwOnFailure(obj);
                            this.this$0.setCurrentState$animation_core(this.$targetState);
                            SeekableTransitionState<S> seekableTransitionState7 = this.this$0;
                            this.label = 5;
                        } else {
                            if (i != 5) {
                                k2d.a("call to 'resume' before 'invoke' with coroutine");
                                return null;
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                    }
                    this.this$0.setFraction(0.0f);
                    return Unit.INSTANCE;
                    S composedTargetState$animation_core = seekableTransitionState.getComposedTargetState$animation_core();
                    mutex.unlock((Object) null);
                    if (Intrinsics.areEqual(this.$targetState, composedTargetState$animation_core)) {
                        if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                            if (this.this$0.getFraction() < 1.0f) {
                                seekingAnimationState = ((SeekableTransitionState) this.this$0).currentAnimation;
                                finiteAnimationSpec = this.$animationSpec;
                                if (finiteAnimationSpec != null) {
                                    vectorizedAnimationSpecVectorize = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                } else {
                                    vectorizedAnimationSpecVectorize = null;
                                }
                                if (seekingAnimationState != null) {
                                    if (seekingAnimationState != null) {
                                        animationSpec = seekingAnimationState.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    if (animationSpec != null) {
                                        long progressNanos4 = seekingAnimationState.getProgressNanos();
                                        AnimationVector1D start4 = seekingAnimationState.getStart();
                                        AnimationVector1D target4 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = seekingAnimationState.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        zeroVelocity = (AnimationVector1D) animationSpec.getVelocityFromNanos(progressNanos4, start4, target4, initialVelocity);
                                    } else if (seekingAnimationState != null) {
                                        zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (seekingAnimationState == null) {
                                        seekingAnimationState = new SeekingAnimationState();
                                    }
                                    seekingAnimationState.setAnimationSpec(vectorizedAnimationSpecVectorize);
                                    seekingAnimationState.setComplete(false);
                                    seekingAnimationState.setValue(this.this$0.getFraction());
                                    seekingAnimationState.getStart().set$animation_core(0, this.this$0.getFraction());
                                    seekingAnimationState.setDurationNanos(this.this$0.getTotalDurationNanos());
                                    seekingAnimationState.setProgressNanos(0L);
                                    seekingAnimationState.setInitialVelocity(zeroVelocity);
                                    if (vectorizedAnimationSpecVectorize != null) {
                                        jRoundToLong = vectorizedAnimationSpecVectorize.getDurationNanos(seekingAnimationState.getStart(), SeekableTransitionState.Companion.getTarget1(), zeroVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                    }
                                    seekingAnimationState.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) this.this$0).currentAnimation = seekingAnimationState;
                                } else {
                                    if (seekingAnimationState != null) {
                                        animationSpec = seekingAnimationState.getAnimationSpec();
                                    } else {
                                        animationSpec = null;
                                    }
                                    if (animationSpec != null) {
                                        long progressNanos5 = seekingAnimationState.getProgressNanos();
                                        AnimationVector1D start5 = seekingAnimationState.getStart();
                                        AnimationVector1D target5 = SeekableTransitionState.Companion.getTarget1();
                                        initialVelocity = seekingAnimationState.getInitialVelocity();
                                        if (initialVelocity == null) {
                                            initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        zeroVelocity = (AnimationVector1D) animationSpec.getVelocityFromNanos(progressNanos5, start5, target5, initialVelocity);
                                    } else if (seekingAnimationState != null) {
                                        zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    } else {
                                        zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                    }
                                    if (seekingAnimationState == null) {
                                        seekingAnimationState = new SeekingAnimationState();
                                    }
                                    seekingAnimationState.setAnimationSpec(vectorizedAnimationSpecVectorize);
                                    seekingAnimationState.setComplete(false);
                                    seekingAnimationState.setValue(this.this$0.getFraction());
                                    seekingAnimationState.getStart().set$animation_core(0, this.this$0.getFraction());
                                    seekingAnimationState.setDurationNanos(this.this$0.getTotalDurationNanos());
                                    seekingAnimationState.setProgressNanos(0L);
                                    seekingAnimationState.setInitialVelocity(zeroVelocity);
                                    if (vectorizedAnimationSpecVectorize != null) {
                                        jRoundToLong = vectorizedAnimationSpecVectorize.getDurationNanos(seekingAnimationState.getStart(), SeekableTransitionState.Companion.getTarget1(), zeroVelocity);
                                    } else {
                                        jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                    }
                                    seekingAnimationState.setAnimationSpecDuration(jRoundToLong);
                                    ((SeekableTransitionState) this.this$0).currentAnimation = seekingAnimationState;
                                }
                            }
                            seekableTransitionState3 = this.this$0;
                            this.L$0 = null;
                            this.L$1 = null;
                            this.label = 4;
                            if (seekableTransitionState3.runAnimations(this) != coroutine_suspended) {
                                this.this$0.setCurrentState$animation_core(this.$targetState);
                                SeekableTransitionState<S> seekableTransitionState8 = this.this$0;
                                this.label = 5;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    SeekableTransitionState<S> seekableTransitionState9 = this.this$0;
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 2;
                    if (seekableTransitionState9.doOneFrame(this) != coroutine_suspended) {
                        seekableTransitionState2 = this.this$0;
                        this.label = 3;
                        if (seekableTransitionState2.waitForCompositionAfterTargetStateChange(this) != coroutine_suspended) {
                            if (!Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                                if (this.this$0.getFraction() < 1.0f) {
                                    seekingAnimationState = ((SeekableTransitionState) this.this$0).currentAnimation;
                                    finiteAnimationSpec = this.$animationSpec;
                                    if (finiteAnimationSpec != null) {
                                        vectorizedAnimationSpecVectorize = finiteAnimationSpec.vectorize((TwoWayConverter<Float, V>) VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
                                    } else {
                                        vectorizedAnimationSpecVectorize = null;
                                    }
                                    if (seekingAnimationState != null) {
                                        if (seekingAnimationState != null) {
                                            animationSpec = seekingAnimationState.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        if (animationSpec != null) {
                                            long progressNanos6 = seekingAnimationState.getProgressNanos();
                                            AnimationVector1D start6 = seekingAnimationState.getStart();
                                            AnimationVector1D target6 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = seekingAnimationState.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            zeroVelocity = (AnimationVector1D) animationSpec.getVelocityFromNanos(progressNanos6, start6, target6, initialVelocity);
                                        } else if (seekingAnimationState != null) {
                                            zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (seekingAnimationState == null) {
                                            seekingAnimationState = new SeekingAnimationState();
                                        }
                                        seekingAnimationState.setAnimationSpec(vectorizedAnimationSpecVectorize);
                                        seekingAnimationState.setComplete(false);
                                        seekingAnimationState.setValue(this.this$0.getFraction());
                                        seekingAnimationState.getStart().set$animation_core(0, this.this$0.getFraction());
                                        seekingAnimationState.setDurationNanos(this.this$0.getTotalDurationNanos());
                                        seekingAnimationState.setProgressNanos(0L);
                                        seekingAnimationState.setInitialVelocity(zeroVelocity);
                                        if (vectorizedAnimationSpecVectorize != null) {
                                            jRoundToLong = vectorizedAnimationSpecVectorize.getDurationNanos(seekingAnimationState.getStart(), SeekableTransitionState.Companion.getTarget1(), zeroVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                        }
                                        seekingAnimationState.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) this.this$0).currentAnimation = seekingAnimationState;
                                    } else {
                                        if (seekingAnimationState != null) {
                                            animationSpec = seekingAnimationState.getAnimationSpec();
                                        } else {
                                            animationSpec = null;
                                        }
                                        if (animationSpec != null) {
                                            long progressNanos7 = seekingAnimationState.getProgressNanos();
                                            AnimationVector1D start7 = seekingAnimationState.getStart();
                                            AnimationVector1D target7 = SeekableTransitionState.Companion.getTarget1();
                                            initialVelocity = seekingAnimationState.getInitialVelocity();
                                            if (initialVelocity == null) {
                                                initialVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                            }
                                            zeroVelocity = (AnimationVector1D) animationSpec.getVelocityFromNanos(progressNanos7, start7, target7, initialVelocity);
                                        } else if (seekingAnimationState != null) {
                                            zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        } else {
                                            zeroVelocity = SeekableTransitionState.Companion.getZeroVelocity();
                                        }
                                        if (seekingAnimationState == null) {
                                            seekingAnimationState = new SeekingAnimationState();
                                        }
                                        seekingAnimationState.setAnimationSpec(vectorizedAnimationSpecVectorize);
                                        seekingAnimationState.setComplete(false);
                                        seekingAnimationState.setValue(this.this$0.getFraction());
                                        seekingAnimationState.getStart().set$animation_core(0, this.this$0.getFraction());
                                        seekingAnimationState.setDurationNanos(this.this$0.getTotalDurationNanos());
                                        seekingAnimationState.setProgressNanos(0L);
                                        seekingAnimationState.setInitialVelocity(zeroVelocity);
                                        if (vectorizedAnimationSpecVectorize != null) {
                                            jRoundToLong = vectorizedAnimationSpecVectorize.getDurationNanos(seekingAnimationState.getStart(), SeekableTransitionState.Companion.getTarget1(), zeroVelocity);
                                        } else {
                                            jRoundToLong = MathKt.roundToLong(this.this$0.getTotalDurationNanos() * (1.0d - ((double) this.this$0.getFraction())));
                                        }
                                        seekingAnimationState.setAnimationSpecDuration(jRoundToLong);
                                        ((SeekableTransitionState) this.this$0).currentAnimation = seekingAnimationState;
                                    }
                                }
                                seekableTransitionState3 = this.this$0;
                                this.L$0 = null;
                                this.L$1 = null;
                                this.label = 4;
                                if (seekableTransitionState3.runAnimations(this) != coroutine_suspended) {
                                    this.this$0.setCurrentState$animation_core(this.$targetState);
                                    SeekableTransitionState<S> seekableTransitionState10 = this.this$0;
                                    this.label = 5;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }
                    return coroutine_suspended;
                } catch (Throwable th) {
                    mutex.unlock((Object) null);
                    throw th;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Transition<S> transition, SeekableTransitionState<S> seekableTransitionState, S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$transition = transition;
            this.this$0 = seekableTransitionState;
            this.$targetState = s;
            this.$animationSpec = finiteAnimationSpec;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$transition, this.this$0, this.$targetState, this.$animationSpec, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$targetState, this.$transition, this.$animationSpec, null);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            this.$transition.onTransitionEnd$animation_core();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$runAnimations$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {}, l = {354, 357}, m = "runAnimations", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SeekableTransitionState<S> seekableTransitionState, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.runAnimations(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3", f = "Transition.kt", i = {}, l = {489}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ float $fraction;
        final /* synthetic */ S $oldTargetState;
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1", f = "Transition.kt", i = {}, l = {511}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ float $fraction;
            final /* synthetic */ S $oldTargetState;
            final /* synthetic */ S $targetState;
            final /* synthetic */ Transition<S> $transition;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SeekableTransitionState<S> this$0;

            /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$seekTo$3$1$1", f = "Transition.kt", i = {}, l = {507}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class C00021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ SeekableTransitionState<S> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00021(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C00021> continuation) {
                    super(2, continuation);
                    this.this$0 = seekableTransitionState;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00021(this.this$0, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SeekableTransitionState<S> seekableTransitionState = this.this$0;
                        this.label = 1;
                        if (seekableTransitionState.runAnimations(this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(S s, S s2, SeekableTransitionState<S> seekableTransitionState, Transition<S> transition, float f, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$targetState = s;
                this.$oldTargetState = s2;
                this.this$0 = seekableTransitionState;
                this.$transition = transition;
                this.$fraction = f;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    boolean zAreEqual = Intrinsics.areEqual(this.$targetState, this.$oldTargetState);
                    SeekableTransitionState<S> seekableTransitionState = this.this$0;
                    if (zAreEqual) {
                        ((SeekableTransitionState) seekableTransitionState).currentAnimation = null;
                        if (Intrinsics.areEqual(this.this$0.getCurrentState(), this.$targetState)) {
                            return Unit.INSTANCE;
                        }
                    } else {
                        seekableTransitionState.moveAnimationToInitialState();
                    }
                    if (!Intrinsics.areEqual(this.$targetState, this.$oldTargetState)) {
                        this.$transition.updateTarget$animation_core(this.$targetState);
                        this.$transition.setPlayTimeNanos(0L);
                        this.this$0.setTargetState$animation_core(this.$targetState);
                        this.$transition.resetAnimationFraction$animation_core(this.$fraction);
                    }
                    this.this$0.setFraction(this.$fraction);
                    boolean zIsNotEmpty = ((SeekableTransitionState) this.this$0).initialValueAnimations.isNotEmpty();
                    SeekableTransitionState<S> seekableTransitionState2 = this.this$0;
                    if (zIsNotEmpty) {
                        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00021(seekableTransitionState2, null), 3, (Object) null);
                    } else {
                        ((SeekableTransitionState) seekableTransitionState2).lastFrameTimeNanos = Long.MIN_VALUE;
                    }
                    SeekableTransitionState<S> seekableTransitionState3 = this.this$0;
                    this.label = 1;
                    if (seekableTransitionState3.waitForCompositionAfterTargetStateChange(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                this.this$0.seekToFraction();
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(S s, S s2, SeekableTransitionState<S> seekableTransitionState, Transition<S> transition, float f, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$targetState = s;
            this.$oldTargetState = s2;
            this.this$0 = seekableTransitionState;
            this.$transition = transition;
            this.$fraction = f;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$targetState, this.$oldTargetState, this.this$0, this.$transition, this.$fraction, null);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$snapTo$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState$snapTo$2", f = "Transition.kt", i = {}, l = {458}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C00842 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ S $targetState;
        final /* synthetic */ Transition<S> $transition;
        int label;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00842(SeekableTransitionState<S> seekableTransitionState, S s, Transition<S> transition, Continuation<? super C00842> continuation) {
            super(1, continuation);
            this.this$0 = seekableTransitionState;
            this.$targetState = s;
            this.$transition = transition;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C00842(this.this$0, this.$targetState, this.$transition, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            float f;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.endAllAnimations();
                ((SeekableTransitionState) this.this$0).lastFrameTimeNanos = Long.MIN_VALUE;
                this.this$0.setFraction(0.0f);
                S s = this.$targetState;
                if (Intrinsics.areEqual(s, this.this$0.getCurrentState())) {
                    f = -4.0f;
                } else {
                    f = Intrinsics.areEqual(s, this.this$0.getTargetState()) ? -5.0f : -3.0f;
                }
                this.$transition.updateTarget$animation_core(this.$targetState);
                this.$transition.setPlayTimeNanos(0L);
                this.this$0.setTargetState$animation_core(this.$targetState);
                this.this$0.setFraction(0.0f);
                this.this$0.setCurrentState$animation_core(this.$targetState);
                this.$transition.resetAnimationFraction$animation_core(f);
                if (f == -3.0f) {
                    SeekableTransitionState<S> seekableTransitionState = this.this$0;
                    this.label = 1;
                    if (seekableTransitionState.waitForCompositionAfterTargetStateChange(this) == coroutine_suspended) {
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
            this.$transition.onTransitionEnd$animation_core();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$waitForComposition$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {0, 1}, l = {544, 2169}, m = "waitForComposition", n = {"expectedState", "expectedState"}, s = {"L$0", "L$0"}, v = 1)
    public static final class C00851 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00851(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C00851> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.waitForComposition(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.SeekableTransitionState$waitForCompositionAfterTargetStateChange$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.SeekableTransitionState", f = "Transition.kt", i = {0, 1}, l = {520, 2169}, m = "waitForCompositionAfterTargetStateChange", n = {"expectedState", "expectedState"}, s = {"L$0", "L$0"}, v = 1)
    public static final class C00861 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeekableTransitionState<S> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00861(SeekableTransitionState<S> seekableTransitionState, Continuation<? super C00861> continuation) {
            super(continuation);
            this.this$0 = seekableTransitionState;
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.waitForCompositionAfterTargetStateChange(this);
        }
    }

    public SeekableTransitionState(S s) {
        super(null);
        this.targetState = SnapshotStateKt.mutableStateOf$default(s, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.currentState = SnapshotStateKt.mutableStateOf$default(s, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.composedTargetState = s;
        this.recalculateTotalDurationNanos = new Function0() { // from class: g2d
            public final Object invoke() {
                return SeekableTransitionState.a(this.b);
            }
        };
        this.fraction = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.compositionContinuationMutex = MutexKt.Mutex$default(false, 1, (Object) null);
        this.mutatorMutex = new MutatorMutex();
        this.lastFrameTimeNanos = Long.MIN_VALUE;
        this.initialValueAnimations = new MutableObjectList<>(0, 1, null);
        this.firstFrameLambda = new Function1() { // from class: h2d
            public final Object invoke(Object obj) {
                return SeekableTransitionState.c(this.b, ((Long) obj).longValue());
            }
        };
        this.animateOneFrameLambda = new Function1() { // from class: i2d
            public final Object invoke(Object obj) {
                return SeekableTransitionState.b(this.b, ((Long) obj).longValue());
            }
        };
    }

    public static Unit a(SeekableTransitionState seekableTransitionState) {
        Transition<S> transition = seekableTransitionState.transition;
        seekableTransitionState.totalDurationNanos = transition != null ? transition.getTotalDurationNanos() : 0L;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateOneFrame(Continuation<? super Unit> continuation) {
        float durationScale = SuspendAnimationKt.getDurationScale(continuation.getContext());
        if (durationScale <= 0.0f) {
            endAllAnimations();
            return Unit.INSTANCE;
        }
        this.durationScale = durationScale;
        Object objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(this.animateOneFrameLambda, continuation);
        return objWithFrameNanos == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithFrameNanos : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object animateTo$default(SeekableTransitionState seekableTransitionState, Object obj, FiniteAnimationSpec finiteAnimationSpec, Continuation continuation, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = seekableTransitionState.getTargetState();
        }
        if ((i & 2) != 0) {
            finiteAnimationSpec = null;
        }
        return seekableTransitionState.animateTo(obj, finiteAnimationSpec, continuation);
    }

    public static Unit b(SeekableTransitionState seekableTransitionState, long j) {
        long j2 = j - seekableTransitionState.lastFrameTimeNanos;
        seekableTransitionState.lastFrameTimeNanos = j;
        long jRoundToLong = MathKt.roundToLong(j2 / ((double) seekableTransitionState.durationScale));
        if (seekableTransitionState.initialValueAnimations.isNotEmpty()) {
            MutableObjectList<SeekingAnimationState> mutableObjectList = seekableTransitionState.initialValueAnimations;
            Object[] objArr = mutableObjectList.content;
            int i = mutableObjectList._size;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                SeekingAnimationState seekingAnimationState = (SeekingAnimationState) objArr[i3];
                seekableTransitionState.recalculateAnimationValue(seekingAnimationState, jRoundToLong);
                seekingAnimationState.setComplete(true);
            }
            Transition<S> transition = seekableTransitionState.transition;
            if (transition != null) {
                transition.updateInitialValues$animation_core();
            }
            MutableObjectList<SeekingAnimationState> mutableObjectList2 = seekableTransitionState.initialValueAnimations;
            int i4 = mutableObjectList2._size;
            Object[] objArr2 = mutableObjectList2.content;
            IntRange intRangeUntil = RangesKt.until(0, i4);
            int first = intRangeUntil.getFirst();
            int last = intRangeUntil.getLast();
            if (first <= last) {
                while (true) {
                    objArr2[first - i2] = objArr2[first];
                    if (((SeekingAnimationState) objArr2[first]).getIsComplete()) {
                        i2++;
                    }
                    if (first == last) {
                        break;
                    }
                    first++;
                }
            }
            ArraysKt.fill(objArr2, (Object) null, i4 - i2, i4);
            mutableObjectList2._size -= i2;
        }
        SeekingAnimationState seekingAnimationState2 = seekableTransitionState.currentAnimation;
        if (seekingAnimationState2 != null) {
            seekingAnimationState2.setDurationNanos(seekableTransitionState.totalDurationNanos);
            seekableTransitionState.recalculateAnimationValue(seekingAnimationState2, jRoundToLong);
            seekableTransitionState.setFraction(seekingAnimationState2.getValue());
            if (seekingAnimationState2.getValue() == 1.0f) {
                seekableTransitionState.currentAnimation = null;
            }
            seekableTransitionState.seekToFraction();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(SeekableTransitionState seekableTransitionState, long j) {
        seekableTransitionState.lastFrameTimeNanos = j;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object doOneFrame(Continuation<? super Unit> continuation) {
        if (this.lastFrameTimeNanos == Long.MIN_VALUE) {
            Object objWithFrameNanos = MonotonicFrameClockKt.withFrameNanos(this.firstFrameLambda, continuation);
            return objWithFrameNanos == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithFrameNanos : Unit.INSTANCE;
        }
        Object objAnimateOneFrame = animateOneFrame(continuation);
        return objAnimateOneFrame == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateOneFrame : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endAllAnimations() {
        Transition<S> transition = this.transition;
        if (transition != null) {
            transition.clearInitialAnimations$animation_core();
        }
        this.initialValueAnimations.clear();
        if (this.currentAnimation != null) {
            this.currentAnimation = null;
            setFraction(1.0f);
            seekToFraction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveAnimationToInitialState() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        SeekingAnimationState seekingAnimationState = this.currentAnimation;
        if (seekingAnimationState == null) {
            if (this.totalDurationNanos <= 0 || getFraction() == 1.0f || Intrinsics.areEqual(getCurrentState(), getTargetState())) {
                seekingAnimationState = null;
            } else {
                seekingAnimationState = new SeekingAnimationState();
                seekingAnimationState.setValue(getFraction());
                long j = this.totalDurationNanos;
                seekingAnimationState.setDurationNanos(j);
                seekingAnimationState.setAnimationSpecDuration(MathKt.roundToLong(j * (1.0d - ((double) getFraction()))));
                seekingAnimationState.getStart().set$animation_core(0, getFraction());
            }
        }
        if (seekingAnimationState != null) {
            seekingAnimationState.setDurationNanos(this.totalDurationNanos);
            this.initialValueAnimations.add(seekingAnimationState);
            transition.setInitialAnimations$animation_core(seekingAnimationState);
        }
        this.currentAnimation = null;
    }

    private final void recalculateAnimationValue(SeekingAnimationState animation, long deltaPlayTimeNanos) {
        long progressNanos = animation.getProgressNanos() + deltaPlayTimeNanos;
        animation.setProgressNanos(progressNanos);
        long animationSpecDuration = animation.getAnimationSpecDuration();
        if (progressNanos >= animationSpecDuration) {
            animation.setValue(1.0f);
            return;
        }
        VectorizedAnimationSpec<AnimationVector1D> animationSpec = animation.getAnimationSpec();
        if (animationSpec == null) {
            float f = progressNanos / animationSpecDuration;
            animation.setValue((animation.getStart().get$animation_core(0) * (1.0f - f)) + (f * 1.0f));
        } else {
            AnimationVector1D start = animation.getStart();
            AnimationVector1D animationVector1D = Target1;
            AnimationVector1D initialVelocity = animation.getInitialVelocity();
            if (initialVelocity == null) {
                initialVelocity = ZeroVelocity;
            }
            animation.setValue(RangesKt.coerceIn(((AnimationVector1D) animationSpec.getValueFromNanos(progressNanos, start, animationVector1D, initialVelocity)).get$animation_core(0), 0.0f, 1.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object runAnimations(Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(this, continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(this, continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.initialValueAnimations.isEmpty() && this.currentAnimation == null) {
                return Unit.INSTANCE;
            }
            if (SuspendAnimationKt.getDurationScale(anonymousClass1.getContext()) == 0.0f) {
                endAllAnimations();
                this.lastFrameTimeNanos = Long.MIN_VALUE;
                return Unit.INSTANCE;
            }
            if (this.lastFrameTimeNanos == Long.MIN_VALUE) {
                Function1<Long, Unit> function1 = this.firstFrameLambda;
                anonymousClass1.label = 1;
                if (MonotonicFrameClockKt.withFrameNanos(function1, anonymousClass1) != coroutine_suspended) {
                }
            }
            return coroutine_suspended;
        }
        if (i2 != 1 && i2 != 2) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        do {
            if (!this.initialValueAnimations.isNotEmpty() && this.currentAnimation == null) {
                this.lastFrameTimeNanos = Long.MIN_VALUE;
                return Unit.INSTANCE;
            }
            anonymousClass1.label = 2;
        } while (animateOneFrame(anonymousClass1) != coroutine_suspended);
        return coroutine_suspended;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object seekTo$default(SeekableTransitionState seekableTransitionState, float f, Object obj, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = seekableTransitionState.getTargetState();
        }
        return seekableTransitionState.seekTo(f, obj, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void seekToFraction() {
        Transition<S> transition = this.transition;
        if (transition == null) {
            return;
        }
        transition.seekAnimations$animation_core(MathKt.roundToLong(((double) getFraction()) * transition.getTotalDurationNanos()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFraction(float f) {
        this.fraction.setFloatValue(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:27:0x0082  */
    /* JADX WARN: Code duplicated, block: B:29:0x0085  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object waitForComposition(Continuation<? super Unit> continuation) {
        C00851 c00851;
        Object targetState;
        Object obj;
        if (continuation instanceof C00851) {
            c00851 = (C00851) continuation;
            int i = c00851.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00851.label = i - Integer.MIN_VALUE;
            } else {
                c00851 = new C00851(this, continuation);
            }
        } else {
            c00851 = new C00851(this, continuation);
        }
        Object obj2 = c00851.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00851.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            targetState = getTargetState();
            Mutex mutex = this.compositionContinuationMutex;
            c00851.L$0 = targetState;
            c00851.label = 1;
            if (Mutex$DefaultImpls.lock$default(mutex, null, c00851, 1, null) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            Object obj3 = c00851.L$0;
            ResultKt.throwOnFailure(obj2);
            targetState = obj3;
        } else {
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            obj = c00851.L$0;
            ResultKt.throwOnFailure(obj2);
        }
        if (Intrinsics.areEqual(obj2, obj)) {
            return Unit.INSTANCE;
        }
        this.lastFrameTimeNanos = Long.MIN_VALUE;
        throw new CancellationException("targetState while waiting for composition");
        c00851.L$0 = targetState;
        c00851.label = 2;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(c00851), 1);
        cancellableContinuationImpl.initCancellability();
        setCompositionContinuation$animation_core(cancellableContinuationImpl);
        Mutex$DefaultImpls.unlock$default(getCompositionContinuationMutex(), null, 1, null);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(c00851);
        }
        if (result != coroutine_suspended) {
            obj = targetState;
            obj2 = result;
            if (Intrinsics.areEqual(obj2, obj)) {
                return Unit.INSTANCE;
            }
            this.lastFrameTimeNanos = Long.MIN_VALUE;
            throw new CancellationException("targetState while waiting for composition");
        }
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:32:0x0093  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Instruction removed from duplicated block: B:32:0x0093, please report this as an issue */
    public final Object waitForCompositionAfterTargetStateChange(Continuation<? super Unit> continuation) {
        C00861 c00861;
        Object targetState;
        Object obj;
        if (continuation instanceof C00861) {
            c00861 = (C00861) continuation;
            int i = c00861.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00861.label = i - Integer.MIN_VALUE;
            } else {
                c00861 = new C00861(this, continuation);
            }
        } else {
            c00861 = new C00861(this, continuation);
        }
        Object obj2 = c00861.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00861.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            targetState = getTargetState();
            Mutex mutex = this.compositionContinuationMutex;
            c00861.L$0 = targetState;
            c00861.label = 1;
            if (Mutex$DefaultImpls.lock$default(mutex, null, c00861, 1, null) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            Object obj3 = c00861.L$0;
            ResultKt.throwOnFailure(obj2);
            targetState = obj3;
        } else {
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            obj = c00861.L$0;
            ResultKt.throwOnFailure(obj2);
        }
        if (!Intrinsics.areEqual(obj2, obj)) {
            this.lastFrameTimeNanos = Long.MIN_VALUE;
            throw new CancellationException("snapTo() was canceled because state was changed to " + obj2 + " instead of " + obj);
        }
        return Unit.INSTANCE;
        if (!Intrinsics.areEqual(targetState, this.composedTargetState)) {
            c00861.L$0 = targetState;
            c00861.label = 2;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(c00861), 1);
            cancellableContinuationImpl.initCancellability();
            setCompositionContinuation$animation_core(cancellableContinuationImpl);
            Mutex$DefaultImpls.unlock$default(getCompositionContinuationMutex(), null, 1, null);
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(c00861);
            }
            if (result != coroutine_suspended) {
                obj = targetState;
                obj2 = result;
                if (!Intrinsics.areEqual(obj2, obj)) {
                    this.lastFrameTimeNanos = Long.MIN_VALUE;
                    throw new CancellationException("snapTo() was canceled because state was changed to " + obj2 + " instead of " + obj);
                }
            }
            return coroutine_suspended;
        }
        Mutex$DefaultImpls.unlock$default(this.compositionContinuationMutex, null, 1, null);
        return Unit.INSTANCE;
    }

    public final Object animateTo(S s, FiniteAnimationSpec<Float> finiteAnimationSpec, Continuation<? super Unit> continuation) {
        Object objMutate$default;
        Transition<S> transition = this.transition;
        return (transition != null && (objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass2(transition, this, s, finiteAnimationSpec, null), continuation, 1, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objMutate$default : Unit.INSTANCE;
    }

    public final S getComposedTargetState$animation_core() {
        return this.composedTargetState;
    }

    public final CancellableContinuation<S> getCompositionContinuation$animation_core() {
        return this.compositionContinuation;
    }

    /* JADX INFO: renamed from: getCompositionContinuationMutex$animation_core, reason: from getter */
    public final Mutex getCompositionContinuationMutex() {
        return this.compositionContinuationMutex;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getCurrentState() {
        return (S) this.currentState.getValue();
    }

    public final float getFraction() {
        return this.fraction.getFloatValue();
    }

    @Override // androidx.compose.animation.core.TransitionState
    public S getTargetState() {
        return (S) this.targetState.getValue();
    }

    /* JADX INFO: renamed from: getTotalDurationNanos$animation_core, reason: from getter */
    public final long getTotalDurationNanos() {
        return this.totalDurationNanos;
    }

    public final void observeTotalDuration$animation_core() {
        TransitionKt.getSeekableStateObserver().observeReads(this, TransitionKt.SeekableTransitionStateTotalDurationChanged, this.recalculateTotalDurationNanos);
    }

    public final void onTotalDurationChanged$animation_core() {
        long j = this.totalDurationNanos;
        observeTotalDuration$animation_core();
        long j2 = this.totalDurationNanos;
        if (j != j2) {
            SeekingAnimationState seekingAnimationState = this.currentAnimation;
            if (seekingAnimationState == null) {
                if (j2 != 0) {
                    seekToFraction();
                    return;
                }
                return;
            }
            long progressNanos = seekingAnimationState.getProgressNanos();
            long j3 = this.totalDurationNanos;
            if (progressNanos > j3) {
                endAllAnimations();
                return;
            }
            seekingAnimationState.setDurationNanos(j3);
            if (seekingAnimationState.getAnimationSpec() == null) {
                seekingAnimationState.setAnimationSpecDuration(MathKt.roundToLong((1.0d - ((double) seekingAnimationState.getStart().get$animation_core(0))) * this.totalDurationNanos));
            }
        }
    }

    public final Object seekTo(float f, S s, Continuation<? super Unit> continuation) {
        boolean z = false;
        if (0.0f <= f && f <= 1.0f) {
            z = true;
        }
        if (!z) {
            PreconditionsKt.throwIllegalArgumentException("Expecting fraction between 0 and 1. Got " + f);
        }
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        Object objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new AnonymousClass3(s, getTargetState(), this, transition, f, null), continuation, 1, null);
        return objMutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate$default : Unit.INSTANCE;
    }

    public final void setComposedTargetState$animation_core(S s) {
        this.composedTargetState = s;
    }

    public final void setCompositionContinuation$animation_core(CancellableContinuation<? super S> cancellableContinuation) {
        this.compositionContinuation = cancellableContinuation;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void setCurrentState$animation_core(S s) {
        this.currentState.setValue(s);
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void setTargetState$animation_core(S s) {
        this.targetState.setValue(s);
    }

    public final void setTotalDurationNanos$animation_core(long j) {
        this.totalDurationNanos = j;
    }

    public final Object snapTo(S s, Continuation<? super Unit> continuation) {
        Object objMutate$default;
        Transition<S> transition = this.transition;
        if (transition == null) {
            return Unit.INSTANCE;
        }
        return (!(Intrinsics.areEqual(getCurrentState(), s) && Intrinsics.areEqual(getTargetState(), s)) && (objMutate$default = MutatorMutex.mutate$default(this.mutatorMutex, null, new C00842(this, s, transition, null), continuation, 1, null)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objMutate$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionConfigured$animation_core(Transition<S> transition) {
        Transition<S> transition2 = this.transition;
        if (!(transition2 == null || Intrinsics.areEqual(transition, transition2))) {
            PreconditionsKt.throwIllegalStateException("An instance of SeekableTransitionState has been used in different Transitions. Previous instance: " + this.transition + ", new instance: " + transition);
        }
        this.transition = transition;
    }

    @Override // androidx.compose.animation.core.TransitionState
    public void transitionRemoved$animation_core() {
        this.transition = null;
        TransitionKt.getSeekableStateObserver().clear(this);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/compose/animation/core/SeekableTransitionState$Companion;", "", "<init>", "()V", "ZeroVelocity", "Landroidx/compose/animation/core/AnimationVector1D;", "getZeroVelocity", "()Landroidx/compose/animation/core/AnimationVector1D;", "Target1", "getTarget1", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AnimationVector1D getTarget1() {
            return SeekableTransitionState.Target1;
        }

        public final AnimationVector1D getZeroVelocity() {
            return SeekableTransitionState.ZeroVelocity;
        }

        private Companion() {
        }
    }
}
