package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VectorizedAnimationSpec;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006JJ\u0010\u0014\u001a\u00020\u00152!\u0010\u0016\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00150\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u001cH\u0086@\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0002\u0010\u001dR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/gestures/UpdatableAnimationState;", "", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "<init>", "(Landroidx/compose/animation/core/AnimationSpec;)V", "vectorizedSpec", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "Landroidx/compose/animation/core/AnimationVector1D;", "lastFrameTime", "", "lastVelocity", "isRunning", "", "value", "getValue", "()F", "setValue", "(F)V", "animateToZero", "", "beforeFrame", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "valueDelta", "afterFrame", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class UpdatableAnimationState {

    @Deprecated
    public static final float VisibilityThreshold = 0.01f;
    private boolean isRunning;
    private long lastFrameTime = Long.MIN_VALUE;
    private AnimationVector1D lastVelocity = ZeroVector;
    private float value;
    private final VectorizedAnimationSpec<AnimationVector1D> vectorizedSpec;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final AnimationVector1D ZeroVector = new AnimationVector1D(0.0f);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.UpdatableAnimationState$animateToZero$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.UpdatableAnimationState", f = "UpdatableAnimationState.kt", i = {0, 0, 0, 1}, l = {100, 151}, m = "animateToZero", n = {"beforeFrame", "afterFrame", "durationScale", "afterFrame"}, s = {"L$0", "L$1", "F$0", "L$0"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UpdatableAnimationState.this.animateToZero(null, null, this);
        }
    }

    public UpdatableAnimationState(AnimationSpec<Float> animationSpec) {
        this.vectorizedSpec = animationSpec.vectorize(VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE));
    }

    public static Unit a(UpdatableAnimationState updatableAnimationState, Function1 function1, long j) {
        float f = updatableAnimationState.value;
        updatableAnimationState.value = 0.0f;
        function1.invoke(Float.valueOf(f));
        return Unit.INSTANCE;
    }

    public static Unit b(UpdatableAnimationState updatableAnimationState, float f, Function1 function1, long j) {
        if (updatableAnimationState.lastFrameTime == Long.MIN_VALUE) {
            updatableAnimationState.lastFrameTime = j;
        }
        AnimationVector1D animationVector1D = new AnimationVector1D(updatableAnimationState.value);
        long durationNanos = f == 0.0f ? updatableAnimationState.vectorizedSpec.getDurationNanos(new AnimationVector1D(updatableAnimationState.value), ZeroVector, updatableAnimationState.lastVelocity) : MathKt.roundToLong((j - updatableAnimationState.lastFrameTime) / f);
        VectorizedAnimationSpec<AnimationVector1D> vectorizedAnimationSpec = updatableAnimationState.vectorizedSpec;
        AnimationVector1D animationVector1D2 = ZeroVector;
        float value = ((AnimationVector1D) vectorizedAnimationSpec.getValueFromNanos(durationNanos, animationVector1D, animationVector1D2, updatableAnimationState.lastVelocity)).getValue();
        updatableAnimationState.lastVelocity = (AnimationVector1D) updatableAnimationState.vectorizedSpec.getVelocityFromNanos(durationNanos, animationVector1D, animationVector1D2, updatableAnimationState.lastVelocity);
        updatableAnimationState.lastFrameTime = j;
        float f2 = updatableAnimationState.value - value;
        updatableAnimationState.value = value;
        function1.invoke(Float.valueOf(f2));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0076 A[Catch: all -> 0x0036, PHI: r13 r14 r15
      0x0076: PHI (r13v4 float) = (r13v2 float), (r13v5 float) binds: [B:29:0x0070, B:36:0x0099] A[DONT_GENERATE, DONT_INLINE]
      0x0076: PHI (r14v5 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>) = 
      (r14v2 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>)
      (r14v6 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>)
     binds: [B:29:0x0070, B:36:0x0099] A[DONT_GENERATE, DONT_INLINE]
      0x0076: PHI (r15v16 kotlin.jvm.functions.Function0<kotlin.Unit>) = (r15v8 kotlin.jvm.functions.Function0<kotlin.Unit>), (r15v17 kotlin.jvm.functions.Function0<kotlin.Unit>) binds: [B:29:0x0070, B:36:0x0099] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TryCatch #0 {all -> 0x0036, blocks: (B:13:0x0031, B:43:0x00b9, B:20:0x0049, B:35:0x0094, B:30:0x0076, B:32:0x0080, B:37:0x009b, B:40:0x00a7), top: B:48:0x0027 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x0080 A[Catch: all -> 0x0036, TryCatch #0 {all -> 0x0036, blocks: (B:13:0x0031, B:43:0x00b9, B:20:0x0049, B:35:0x0094, B:30:0x0076, B:32:0x0080, B:37:0x009b, B:40:0x00a7), top: B:48:0x0027 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x0093  */
    /* JADX WARN: Code duplicated, block: B:35:0x0094 A[Catch: all -> 0x0036, PHI: r13 r14 r15
      0x0094: PHI (r13v5 float) = (r13v4 float), (r13v8 float) binds: [B:33:0x0091, B:21:0x004c] A[DONT_GENERATE, DONT_INLINE]
      0x0094: PHI (r14v6 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>) = 
      (r14v5 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>)
      (r14v9 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>)
     binds: [B:33:0x0091, B:21:0x004c] A[DONT_GENERATE, DONT_INLINE]
      0x0094: PHI (r15v17 kotlin.jvm.functions.Function0<kotlin.Unit>) = (r15v16 kotlin.jvm.functions.Function0<kotlin.Unit>), (r15v18 kotlin.jvm.functions.Function0<kotlin.Unit>) binds: [B:33:0x0091, B:21:0x004c] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x0036, blocks: (B:13:0x0031, B:43:0x00b9, B:20:0x0049, B:35:0x0094, B:30:0x0076, B:32:0x0080, B:37:0x009b, B:40:0x00a7), top: B:48:0x0027 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x009b A[Catch: all -> 0x0036, PHI: r14 r15
      0x009b: PHI (r14v3 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>) = 
      (r14v5 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>)
      (r14v6 kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit>)
     binds: [B:31:0x007e, B:36:0x0099] A[DONT_GENERATE, DONT_INLINE]
      0x009b: PHI (r15v11 kotlin.jvm.functions.Function0<kotlin.Unit>) = (r15v16 kotlin.jvm.functions.Function0<kotlin.Unit>), (r15v17 kotlin.jvm.functions.Function0<kotlin.Unit>) binds: [B:31:0x007e, B:36:0x0099] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x0036, blocks: (B:13:0x0031, B:43:0x00b9, B:20:0x0049, B:35:0x0094, B:30:0x0076, B:32:0x0080, B:37:0x009b, B:40:0x00a7), top: B:48:0x0027 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:40:0x00a7 A[Catch: all -> 0x0036, TryCatch #0 {all -> 0x0036, blocks: (B:13:0x0031, B:43:0x00b9, B:20:0x0049, B:35:0x0094, B:30:0x0076, B:32:0x0080, B:37:0x009b, B:40:0x00a7), top: B:48:0x0027 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0091 -> B:35:0x0094). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object animateToZero(kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> r13, kotlin.jvm.functions.Function0<kotlin.Unit> r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.UpdatableAnimationState.animateToZero(kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final float getValue() {
        return this.value;
    }

    public final void setValue(float f) {
        this.value = f;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\n\u001a\u00020\u000b*\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Landroidx/compose/foundation/gestures/UpdatableAnimationState$Companion;", "", "<init>", "()V", "VisibilityThreshold", "", "ZeroVector", "Landroidx/compose/animation/core/AnimationVector1D;", "getZeroVector", "()Landroidx/compose/animation/core/AnimationVector1D;", "isZeroish", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AnimationVector1D getZeroVector() {
            return UpdatableAnimationState.ZeroVector;
        }

        public final boolean isZeroish(float f) {
            return Math.abs(f) < 0.01f;
        }

        private Companion() {
        }
    }
}
