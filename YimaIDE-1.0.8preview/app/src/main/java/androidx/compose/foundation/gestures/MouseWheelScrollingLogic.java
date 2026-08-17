package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.MouseWheelScrollingLogic;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Velocity;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001:\u0001SBR\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00121\u0010\u0006\u001a-\b\u0001\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ%\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ\f\u0010 \u001a\u00020\r*\u00020\u0016H\u0002J\u000e\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020)J;\u0010*\u001a\u00020\r*\u00020\u00032'\u0010+\u001a#\b\u0001\u0012\u0004\u0012\u00020,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007¢\u0006\u0002\b-H\u0082@¢\u0006\u0002\u0010.J\u001f\u0010/\u001a\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0004\b0\u00101J\u0014\u00102\u001a\u0004\u0018\u00010#*\b\u0012\u0004\u0012\u00020#0\"H\u0002J\u0018\u00103\u001a\u00020#*\b\u0012\u0004\u0012\u00020#0\"H\u0082@¢\u0006\u0002\u00104J$\u00105\u001a\b\u0012\u0004\u0012\u0002H706\"\u0004\b\u0000\u001072\u000e\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H709H\u0002J\u001b\u0010:\u001a\u00020\u001e*\u00020\u00032\u0006\u0010;\u001a\u00020<H\u0002¢\u0006\u0004\b=\u0010>J\u0010\u0010A\u001a\u00020\r2\u0006\u0010;\u001a\u00020#H\u0002J*\u0010B\u001a\u00020\r*\u00020\u00032\u0006\u0010;\u001a\u00020#2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DH\u0082@¢\u0006\u0002\u0010FJY\u0010G\u001a\u00020\r*\u00020,2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020J0I2\u0006\u0010K\u001a\u00020D2\u0006\u0010L\u001a\u00020M2!\u0010N\u001a\u001d\u0012\u0013\u0012\u00110D¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(P\u0012\u0004\u0012\u00020\u001e0OH\u0082@¢\u0006\u0002\u0010QJ\u0014\u0010B\u001a\u00020D*\u00020,2\u0006\u0010R\u001a\u00020DH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R;\u0010\u0006\u001a-\b\u0001\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010\u001d\u001a\u00020\u001e*\u00020\u00168Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Landroidx/compose/foundation/gestures/MouseWheelScrollingLogic;", "", "scrollingLogic", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "mouseWheelScrollConfig", "Landroidx/compose/foundation/gestures/ScrollConfig;", "onScrollStopped", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Velocity;", "Lkotlin/ParameterName;", "name", "velocity", "Lkotlin/coroutines/Continuation;", "", "density", "Landroidx/compose/ui/unit/Density;", "<init>", "(Landroidx/compose/foundation/gestures/ScrollingLogic;Landroidx/compose/foundation/gestures/ScrollConfig;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/unit/Density;)V", "Lkotlin/jvm/functions/Function2;", "updateDensity", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "isConsumed", "", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "consume", "channel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/compose/foundation/gestures/MouseWheelScrollingLogic$MouseWheelScrollDelta;", "isScrolling", "receivingMouseWheelEventsJob", "Lkotlinx/coroutines/Job;", "startReceivingMouseWheelEvents", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "userScroll", "block", "Landroidx/compose/foundation/gestures/NestedScrollScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/gestures/ScrollingLogic;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onMouseWheel", "onMouseWheel-O0kMr_c", "(Landroidx/compose/ui/input/pointer/PointerEvent;J)Z", "sumOrNull", "busyReceive", "(Lkotlinx/coroutines/channels/Channel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "untilNull", "Lkotlin/sequences/Sequence;", "E", "builderAction", "Lkotlin/Function0;", "canConsumeDelta", "scrollDelta", "Landroidx/compose/ui/geometry/Offset;", "canConsumeDelta-Uv8p0NA", "(Landroidx/compose/foundation/gestures/ScrollingLogic;J)Z", "velocityTracker", "Landroidx/compose/foundation/gestures/MouseWheelVelocityTracker;", "trackVelocity", "dispatchMouseWheelScroll", "threshold", "", "speed", "(Landroidx/compose/foundation/gestures/ScrollingLogic;Landroidx/compose/foundation/gestures/MouseWheelScrollingLogic$MouseWheelScrollDelta;FFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateMouseWheelScroll", "animationState", "Landroidx/compose/animation/core/AnimationState;", "Landroidx/compose/animation/core/AnimationVector1D;", "targetValue", "durationMillis", "", "shouldCancelAnimation", "Lkotlin/Function1;", "lastValue", "(Landroidx/compose/foundation/gestures/NestedScrollScope;Landroidx/compose/animation/core/AnimationState;FILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delta", "MouseWheelScrollDelta", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MouseWheelScrollingLogic {
    public static final int $stable = 8;
    private Density density;
    private boolean isScrolling;
    private final ScrollConfig mouseWheelScrollConfig;
    private final Function2<Velocity, Continuation<? super Unit>, Object> onScrollStopped;
    private Job receivingMouseWheelEventsJob;
    private final ScrollingLogic scrollingLogic;
    private final Channel<MouseWheelScrollDelta> channel = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
    private final MouseWheelVelocityTracker velocityTracker = new MouseWheelVelocityTracker();

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollingLogic$busyReceive$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/gestures/MouseWheelScrollingLogic$MouseWheelScrollDelta;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollingLogic$busyReceive$2", f = "MouseWheelScrollable.kt", i = {0}, l = {198}, m = "invokeSuspend", n = {"job"}, s = {"L$0"}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MouseWheelScrollDelta>, Object> {
        final /* synthetic */ Channel<MouseWheelScrollDelta> $this_busyReceive;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Channel<MouseWheelScrollDelta> channel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_busyReceive = channel;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_busyReceive, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MouseWheelScrollDelta> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) throws Throwable {
            Throwable th;
            Job job;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Job jobLaunch$default = BuildersKt.launch$default((CoroutineScope) this.L$0, (CoroutineContext) null, (CoroutineStart) null, new MouseWheelScrollingLogic$busyReceive$2$job$1(null), 3, (Object) null);
                try {
                    Channel<MouseWheelScrollDelta> channel = this.$this_busyReceive;
                    this.L$0 = jobLaunch$default;
                    this.label = 1;
                    Object objReceive = channel.receive(this);
                    if (objReceive == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj = objReceive;
                    job = jobLaunch$default;
                } catch (Throwable th2) {
                    th = th2;
                    job = jobLaunch$default;
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    throw th;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                job = (Job) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Throwable th3) {
                    th = th3;
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    throw th;
                }
            }
            MouseWheelScrollDelta mouseWheelScrollDelta = (MouseWheelScrollDelta) obj;
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            return mouseWheelScrollDelta;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollingLogic$dispatchMouseWheelScroll$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollingLogic", f = "MouseWheelScrollable.kt", i = {0, 0, 0}, l = {288, 346}, m = "dispatchMouseWheelScroll", n = {"$this$dispatchMouseWheelScroll", "targetValue", "speed"}, s = {"L$0", "L$1", "F$0"}, v = 1)
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
            return MouseWheelScrollingLogic.this.dispatchMouseWheelScroll(null, null, 0.0f, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollingLogic$dispatchMouseWheelScroll$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollingLogic$dispatchMouseWheelScroll$3", f = "MouseWheelScrollable.kt", i = {0, 0, 1, 1, 1, 2, 2}, l = {297, 310, 334}, m = "invokeSuspend", n = {"$this$userScroll", "requiredAnimation", "$this$userScroll", "requiredAnimation", "durationMillis", "$this$userScroll", "requiredAnimation"}, s = {"L$0", "L$1", "L$0", "L$1", "I$0", "L$0", "L$1"}, v = 1)
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> $animationState;
        final /* synthetic */ float $speed;
        final /* synthetic */ Ref.ObjectRef<MouseWheelScrollDelta> $targetScrollDelta;
        final /* synthetic */ Ref.FloatRef $targetValue;
        final /* synthetic */ ScrollingLogic $this_dispatchMouseWheelScroll;
        final /* synthetic */ float $threshold;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ MouseWheelScrollingLogic this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Ref.FloatRef floatRef, Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef, Ref.ObjectRef<MouseWheelScrollDelta> objectRef2, float f, MouseWheelScrollingLogic mouseWheelScrollingLogic, float f2, ScrollingLogic scrollingLogic, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$targetValue = floatRef;
            this.$animationState = objectRef;
            this.$targetScrollDelta = objectRef2;
            this.$threshold = f;
            this.this$0 = mouseWheelScrollingLogic;
            this.$speed = f2;
            this.$this_dispatchMouseWheelScroll = scrollingLogic;
        }

        public static boolean b(MouseWheelScrollingLogic mouseWheelScrollingLogic, Ref.ObjectRef objectRef, Ref.FloatRef floatRef, ScrollingLogic scrollingLogic, Ref.BooleanRef booleanRef, float f) {
            MouseWheelScrollDelta mouseWheelScrollDeltaSumOrNull = mouseWheelScrollingLogic.sumOrNull(mouseWheelScrollingLogic.channel);
            if (mouseWheelScrollDeltaSumOrNull != null) {
                mouseWheelScrollingLogic.trackVelocity(mouseWheelScrollDeltaSumOrNull);
                MouseWheelScrollDelta mouseWheelScrollDeltaPlus = ((MouseWheelScrollDelta) objectRef.element).plus(mouseWheelScrollDeltaSumOrNull);
                objectRef.element = mouseWheelScrollDeltaPlus;
                float fM705toSingleAxisDeltaFromAnglek4lQ0M = ComposeFoundationFlags.isMouseWheel1DAxisLockingEnabled ? scrollingLogic.m705toSingleAxisDeltaFromAnglek4lQ0M(scrollingLogic.m701reverseIfNeededMKHz9U(mouseWheelScrollDeltaPlus.m628getValueF1C5BW0())) : scrollingLogic.m703toFloatk4lQ0M(scrollingLogic.m701reverseIfNeededMKHz9U(mouseWheelScrollDeltaPlus.m628getValueF1C5BW0()));
                floatRef.element = fM705toSingleAxisDeltaFromAnglek4lQ0M;
                booleanRef.element = !MouseWheelScrollableKt.isLowScrollingDelta(fM705toSingleAxisDeltaFromAnglek4lQ0M - f);
            }
            return mouseWheelScrollDeltaSumOrNull != null;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$targetValue, this.$animationState, this.$targetScrollDelta, this.$threshold, this.this$0, this.$speed, this.$this_dispatchMouseWheelScroll, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
            return create(nestedScrollScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x006d  */
        /* JADX WARN: Code duplicated, block: B:17:0x0091  */
        /* JADX WARN: Code duplicated, block: B:19:0x009b  */
        /* JADX WARN: Code duplicated, block: B:26:0x012f  */
        /* JADX WARN: Code duplicated, block: B:29:0x0152  */
        /* JADX WARN: Code duplicated, block: B:31:0x0161  */
        /* JADX WARN: Code duplicated, block: B:35:0x0186  */
        /* JADX WARN: Code duplicated, block: B:40:0x0185 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0152 -> B:30:0x0153). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0161 -> B:13:0x0069). Please report as a decompilation issue!!! */
        public final Object invokeSuspend(Object obj) {
            NestedScrollScope nestedScrollScope;
            Ref.BooleanRef booleanRef;
            NestedScrollScope nestedScrollScope2;
            Ref.BooleanRef booleanRef2;
            int i;
            Object objDispatchMouseWheelScroll$waitNextScrollDelta;
            Ref.BooleanRef booleanRef3;
            Ref.BooleanRef booleanRef4;
            NestedScrollScope nestedScrollScope3;
            Object objDispatchMouseWheelScroll$waitNextScrollDelta2;
            AnonymousClass3 anonymousClass3 = this;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = anonymousClass3.label;
            int i3 = 2;
            if (i2 != 0) {
                if (i2 == 1) {
                    Ref.BooleanRef booleanRef5 = (Ref.BooleanRef) anonymousClass3.L$2;
                    Ref.BooleanRef booleanRef6 = (Ref.BooleanRef) anonymousClass3.L$1;
                    NestedScrollScope nestedScrollScope4 = (NestedScrollScope) anonymousClass3.L$0;
                    ResultKt.throwOnFailure(obj);
                    booleanRef5.element = ((Boolean) obj).booleanValue();
                    anonymousClass3 = this;
                    booleanRef = booleanRef6;
                    nestedScrollScope = nestedScrollScope4;
                    i3 = 2;
                } else {
                    if (i2 == 2) {
                        i = anonymousClass3.I$0;
                        Ref.BooleanRef booleanRef7 = (Ref.BooleanRef) anonymousClass3.L$1;
                        NestedScrollScope nestedScrollScope5 = (NestedScrollScope) anonymousClass3.L$0;
                        ResultKt.throwOnFailure(obj);
                        booleanRef2 = booleanRef7;
                        nestedScrollScope2 = nestedScrollScope5;
                        if (booleanRef2.element) {
                            booleanRef = booleanRef2;
                            nestedScrollScope = nestedScrollScope2;
                        } else {
                            anonymousClass3.L$0 = nestedScrollScope2;
                            anonymousClass3.L$1 = booleanRef2;
                            anonymousClass3.L$2 = booleanRef2;
                            anonymousClass3.label = 3;
                            objDispatchMouseWheelScroll$waitNextScrollDelta = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(anonymousClass3.this$0, anonymousClass3.$targetScrollDelta, anonymousClass3.$targetValue, anonymousClass3.$this_dispatchMouseWheelScroll, anonymousClass3.$animationState, 50 - ((long) i), anonymousClass3);
                            if (objDispatchMouseWheelScroll$waitNextScrollDelta != coroutine_suspended) {
                                booleanRef3 = booleanRef2;
                            }
                        }
                        return coroutine_suspended;
                    }
                    if (i2 != 3) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    Ref.BooleanRef booleanRef8 = (Ref.BooleanRef) anonymousClass3.L$2;
                    booleanRef3 = (Ref.BooleanRef) anonymousClass3.L$1;
                    NestedScrollScope nestedScrollScope6 = (NestedScrollScope) anonymousClass3.L$0;
                    ResultKt.throwOnFailure(obj);
                    booleanRef2 = booleanRef8;
                    nestedScrollScope2 = nestedScrollScope6;
                    objDispatchMouseWheelScroll$waitNextScrollDelta = obj;
                }
                booleanRef2.element = ((Boolean) objDispatchMouseWheelScroll$waitNextScrollDelta).booleanValue();
                booleanRef = booleanRef3;
                nestedScrollScope = nestedScrollScope2;
                i3 = 2;
            } else {
                ResultKt.throwOnFailure(obj);
                NestedScrollScope nestedScrollScope7 = (NestedScrollScope) anonymousClass3.L$0;
                Ref.BooleanRef booleanRef9 = new Ref.BooleanRef();
                booleanRef9.element = true;
                nestedScrollScope = nestedScrollScope7;
                booleanRef = booleanRef9;
            }
            while (booleanRef.element) {
                booleanRef.element = false;
                float fFloatValue = anonymousClass3.$targetValue.element - ((Number) ((AnimationState) anonymousClass3.$animationState.element).getValue()).floatValue();
                if (!((MouseWheelScrollDelta) anonymousClass3.$targetScrollDelta.element).getShouldApplyImmediately() || Math.abs(fFloatValue) < anonymousClass3.$threshold) {
                    booleanRef4 = booleanRef;
                    nestedScrollScope3 = nestedScrollScope;
                    anonymousClass3.this$0.dispatchMouseWheelScroll(nestedScrollScope3, fFloatValue);
                    MouseWheelScrollingLogic mouseWheelScrollingLogic = anonymousClass3.this$0;
                    Ref.ObjectRef<MouseWheelScrollDelta> objectRef = anonymousClass3.$targetScrollDelta;
                    Ref.FloatRef floatRef = anonymousClass3.$targetValue;
                    ScrollingLogic scrollingLogic = anonymousClass3.$this_dispatchMouseWheelScroll;
                    Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef2 = anonymousClass3.$animationState;
                    anonymousClass3.L$0 = nestedScrollScope3;
                    anonymousClass3.L$1 = booleanRef4;
                    anonymousClass3.L$2 = booleanRef4;
                    anonymousClass3.label = 1;
                    objDispatchMouseWheelScroll$waitNextScrollDelta2 = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(mouseWheelScrollingLogic, objectRef, floatRef, scrollingLogic, objectRef2, 50L, anonymousClass3);
                    if (objDispatchMouseWheelScroll$waitNextScrollDelta2 != coroutine_suspended) {
                        booleanRef4.element = ((Boolean) objDispatchMouseWheelScroll$waitNextScrollDelta2).booleanValue();
                        anonymousClass3 = this;
                        booleanRef = booleanRef4;
                        nestedScrollScope = nestedScrollScope3;
                        i3 = 2;
                    }
                } else {
                    float fSignum = Math.signum(fFloatValue) * anonymousClass3.$threshold;
                    anonymousClass3.this$0.dispatchMouseWheelScroll(nestedScrollScope, fSignum);
                    Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef3 = anonymousClass3.$animationState;
                    Object obj2 = objectRef3.element;
                    objectRef3.element = AnimationStateKt.copy$default((AnimationState) obj2, ((Number) ((AnimationState) obj2).getValue()).floatValue() + fSignum, 0.0f, 0L, 0L, false, 30, (Object) null);
                    int iCoerceAtMost = RangesKt.coerceAtMost(MathKt.roundToInt(Math.abs(anonymousClass3.$targetValue.element - ((Number) ((AnimationState) anonymousClass3.$animationState.element).getValue()).floatValue()) / anonymousClass3.$speed), 100);
                    final MouseWheelScrollingLogic mouseWheelScrollingLogic2 = anonymousClass3.this$0;
                    AnimationState animationState = (AnimationState) anonymousClass3.$animationState.element;
                    final Ref.FloatRef floatRef2 = anonymousClass3.$targetValue;
                    float f = floatRef2.element;
                    final Ref.ObjectRef<MouseWheelScrollDelta> objectRef4 = anonymousClass3.$targetScrollDelta;
                    final ScrollingLogic scrollingLogic2 = anonymousClass3.$this_dispatchMouseWheelScroll;
                    final Ref.BooleanRef booleanRef10 = booleanRef;
                    Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.x
                        public final Object invoke(Object obj3) {
                            return Boolean.valueOf(MouseWheelScrollingLogic.AnonymousClass3.b(mouseWheelScrollingLogic2, objectRef4, floatRef2, scrollingLogic2, booleanRef10, ((Float) obj3).floatValue()));
                        }
                    };
                    booleanRef2 = booleanRef10;
                    anonymousClass3.L$0 = nestedScrollScope;
                    anonymousClass3.L$1 = booleanRef2;
                    anonymousClass3.L$2 = null;
                    anonymousClass3.I$0 = iCoerceAtMost;
                    anonymousClass3.label = i3;
                    Object objAnimateMouseWheelScroll = mouseWheelScrollingLogic2.animateMouseWheelScroll(nestedScrollScope, animationState, f, iCoerceAtMost, function1, anonymousClass3);
                    nestedScrollScope2 = nestedScrollScope;
                    if (objAnimateMouseWheelScroll != coroutine_suspended) {
                        i = iCoerceAtMost;
                        if (booleanRef2.element) {
                            anonymousClass3.L$0 = nestedScrollScope2;
                            anonymousClass3.L$1 = booleanRef2;
                            anonymousClass3.L$2 = booleanRef2;
                            anonymousClass3.label = 3;
                            objDispatchMouseWheelScroll$waitNextScrollDelta = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(anonymousClass3.this$0, anonymousClass3.$targetScrollDelta, anonymousClass3.$targetValue, anonymousClass3.$this_dispatchMouseWheelScroll, anonymousClass3.$animationState, 50 - ((long) i), anonymousClass3);
                            if (objDispatchMouseWheelScroll$waitNextScrollDelta != coroutine_suspended) {
                                booleanRef3 = booleanRef2;
                                booleanRef2.element = ((Boolean) objDispatchMouseWheelScroll$waitNextScrollDelta).booleanValue();
                                booleanRef = booleanRef3;
                                nestedScrollScope = nestedScrollScope2;
                                i3 = 2;
                                while (booleanRef.element) {
                                    booleanRef.element = false;
                                    float fFloatValue2 = anonymousClass3.$targetValue.element - ((Number) ((AnimationState) anonymousClass3.$animationState.element).getValue()).floatValue();
                                    if (((MouseWheelScrollDelta) anonymousClass3.$targetScrollDelta.element).getShouldApplyImmediately()) {
                                        booleanRef4 = booleanRef;
                                        nestedScrollScope3 = nestedScrollScope;
                                        anonymousClass3.this$0.dispatchMouseWheelScroll(nestedScrollScope3, fFloatValue2);
                                        MouseWheelScrollingLogic mouseWheelScrollingLogic3 = anonymousClass3.this$0;
                                        Ref.ObjectRef<MouseWheelScrollDelta> objectRef5 = anonymousClass3.$targetScrollDelta;
                                        Ref.FloatRef floatRef3 = anonymousClass3.$targetValue;
                                        ScrollingLogic scrollingLogic3 = anonymousClass3.$this_dispatchMouseWheelScroll;
                                        Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef6 = anonymousClass3.$animationState;
                                        anonymousClass3.L$0 = nestedScrollScope3;
                                        anonymousClass3.L$1 = booleanRef4;
                                        anonymousClass3.L$2 = booleanRef4;
                                        anonymousClass3.label = 1;
                                        objDispatchMouseWheelScroll$waitNextScrollDelta2 = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(mouseWheelScrollingLogic3, objectRef5, floatRef3, scrollingLogic3, objectRef6, 50L, anonymousClass3);
                                        if (objDispatchMouseWheelScroll$waitNextScrollDelta2 != coroutine_suspended) {
                                            booleanRef4.element = ((Boolean) objDispatchMouseWheelScroll$waitNextScrollDelta2).booleanValue();
                                            anonymousClass3 = this;
                                            booleanRef = booleanRef4;
                                            nestedScrollScope = nestedScrollScope3;
                                            i3 = 2;
                                        }
                                    } else {
                                        booleanRef4 = booleanRef;
                                        nestedScrollScope3 = nestedScrollScope;
                                        anonymousClass3.this$0.dispatchMouseWheelScroll(nestedScrollScope3, fFloatValue2);
                                        MouseWheelScrollingLogic mouseWheelScrollingLogic4 = anonymousClass3.this$0;
                                        Ref.ObjectRef<MouseWheelScrollDelta> objectRef7 = anonymousClass3.$targetScrollDelta;
                                        Ref.FloatRef floatRef4 = anonymousClass3.$targetValue;
                                        ScrollingLogic scrollingLogic4 = anonymousClass3.$this_dispatchMouseWheelScroll;
                                        Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef8 = anonymousClass3.$animationState;
                                        anonymousClass3.L$0 = nestedScrollScope3;
                                        anonymousClass3.L$1 = booleanRef4;
                                        anonymousClass3.L$2 = booleanRef4;
                                        anonymousClass3.label = 1;
                                        objDispatchMouseWheelScroll$waitNextScrollDelta2 = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(mouseWheelScrollingLogic4, objectRef7, floatRef4, scrollingLogic4, objectRef8, 50L, anonymousClass3);
                                        if (objDispatchMouseWheelScroll$waitNextScrollDelta2 != coroutine_suspended) {
                                            booleanRef4.element = ((Boolean) objDispatchMouseWheelScroll$waitNextScrollDelta2).booleanValue();
                                            anonymousClass3 = this;
                                            booleanRef = booleanRef4;
                                            nestedScrollScope = nestedScrollScope3;
                                            i3 = 2;
                                        }
                                    }
                                }
                            }
                        } else {
                            booleanRef = booleanRef2;
                            nestedScrollScope = nestedScrollScope2;
                            while (booleanRef.element) {
                                booleanRef.element = false;
                                float fFloatValue3 = anonymousClass3.$targetValue.element - ((Number) ((AnimationState) anonymousClass3.$animationState.element).getValue()).floatValue();
                                if (((MouseWheelScrollDelta) anonymousClass3.$targetScrollDelta.element).getShouldApplyImmediately()) {
                                    booleanRef4 = booleanRef;
                                    nestedScrollScope3 = nestedScrollScope;
                                    anonymousClass3.this$0.dispatchMouseWheelScroll(nestedScrollScope3, fFloatValue3);
                                    MouseWheelScrollingLogic mouseWheelScrollingLogic5 = anonymousClass3.this$0;
                                    Ref.ObjectRef<MouseWheelScrollDelta> objectRef9 = anonymousClass3.$targetScrollDelta;
                                    Ref.FloatRef floatRef5 = anonymousClass3.$targetValue;
                                    ScrollingLogic scrollingLogic5 = anonymousClass3.$this_dispatchMouseWheelScroll;
                                    Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef10 = anonymousClass3.$animationState;
                                    anonymousClass3.L$0 = nestedScrollScope3;
                                    anonymousClass3.L$1 = booleanRef4;
                                    anonymousClass3.L$2 = booleanRef4;
                                    anonymousClass3.label = 1;
                                    objDispatchMouseWheelScroll$waitNextScrollDelta2 = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(mouseWheelScrollingLogic5, objectRef9, floatRef5, scrollingLogic5, objectRef10, 50L, anonymousClass3);
                                    if (objDispatchMouseWheelScroll$waitNextScrollDelta2 != coroutine_suspended) {
                                        booleanRef4.element = ((Boolean) objDispatchMouseWheelScroll$waitNextScrollDelta2).booleanValue();
                                        anonymousClass3 = this;
                                        booleanRef = booleanRef4;
                                        nestedScrollScope = nestedScrollScope3;
                                        i3 = 2;
                                    }
                                } else {
                                    booleanRef4 = booleanRef;
                                    nestedScrollScope3 = nestedScrollScope;
                                    anonymousClass3.this$0.dispatchMouseWheelScroll(nestedScrollScope3, fFloatValue3);
                                    MouseWheelScrollingLogic mouseWheelScrollingLogic6 = anonymousClass3.this$0;
                                    Ref.ObjectRef<MouseWheelScrollDelta> objectRef11 = anonymousClass3.$targetScrollDelta;
                                    Ref.FloatRef floatRef6 = anonymousClass3.$targetValue;
                                    ScrollingLogic scrollingLogic6 = anonymousClass3.$this_dispatchMouseWheelScroll;
                                    Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef12 = anonymousClass3.$animationState;
                                    anonymousClass3.L$0 = nestedScrollScope3;
                                    anonymousClass3.L$1 = booleanRef4;
                                    anonymousClass3.L$2 = booleanRef4;
                                    anonymousClass3.label = 1;
                                    objDispatchMouseWheelScroll$waitNextScrollDelta2 = MouseWheelScrollingLogic.dispatchMouseWheelScroll$waitNextScrollDelta(mouseWheelScrollingLogic6, objectRef11, floatRef6, scrollingLogic6, objectRef12, 50L, anonymousClass3);
                                    if (objDispatchMouseWheelScroll$waitNextScrollDelta2 != coroutine_suspended) {
                                        booleanRef4.element = ((Boolean) objDispatchMouseWheelScroll$waitNextScrollDelta2).booleanValue();
                                        anonymousClass3 = this;
                                        booleanRef = booleanRef4;
                                        nestedScrollScope = nestedScrollScope3;
                                        i3 = 2;
                                    }
                                }
                            }
                        }
                    }
                }
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollingLogic$startReceivingMouseWheelEvents$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollingLogic$startReceivingMouseWheelEvents$1", f = "MouseWheelScrollable.kt", i = {0, 1}, l = {135, 138}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"}, v = 1)
    public static final class C01121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C01121(Continuation<? super C01121> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01121 c01121 = MouseWheelScrollingLogic.this.new C01121(continuation);
            c01121.L$0 = obj;
            return c01121;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0080, code lost:
        
            if (r5.dispatchMouseWheelScroll(r6, r7, r8, r9, r10) == r0) goto L29;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0083 -> B:41:0x0034). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) throws Throwable {
            Throwable th;
            C01121 c01121;
            CoroutineScope coroutineScope;
            Throwable th2;
            CoroutineScope coroutineScope2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i != 0) {
                    try {
                        try {
                            if (i == 1) {
                                coroutineScope = (CoroutineScope) this.L$0;
                                ResultKt.throwOnFailure(obj);
                            } else {
                                if (i != 2) {
                                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                                    return null;
                                }
                                coroutineScope = (CoroutineScope) this.L$0;
                                ResultKt.throwOnFailure(obj);
                                c01121 = this;
                                coroutineScope2 = coroutineScope;
                                this = c01121;
                            }
                            MouseWheelScrollDelta mouseWheelScrollDelta = (MouseWheelScrollDelta) obj;
                            float f = MouseWheelScrollingLogic.this.density.toPx-0680j_4(MouseWheelScrollableKt.AnimationThreshold);
                            float f2 = MouseWheelScrollingLogic.this.density.toPx-0680j_4(MouseWheelScrollableKt.AnimationSpeed);
                            MouseWheelScrollingLogic mouseWheelScrollingLogic = MouseWheelScrollingLogic.this;
                            ScrollingLogic scrollingLogic = mouseWheelScrollingLogic.scrollingLogic;
                            this.L$0 = coroutineScope;
                            this.label = 2;
                            c01121 = this;
                        } catch (Throwable th3) {
                            th2 = th3;
                            th = th2;
                            MouseWheelScrollingLogic.this.receivingMouseWheelEventsJob = null;
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        c01121 = this;
                        MouseWheelScrollingLogic.this.receivingMouseWheelEventsJob = null;
                        throw th;
                    }
                } else {
                    ResultKt.throwOnFailure(obj);
                    coroutineScope2 = (CoroutineScope) this.L$0;
                }
                boolean zIsActive = JobKt.isActive(coroutineScope2.getCoroutineContext());
                MouseWheelScrollingLogic mouseWheelScrollingLogic2 = MouseWheelScrollingLogic.this;
                if (!zIsActive) {
                    mouseWheelScrollingLogic2.receivingMouseWheelEventsJob = null;
                    return Unit.INSTANCE;
                }
                Channel channel = mouseWheelScrollingLogic2.channel;
                this.L$0 = coroutineScope2;
                this.label = 1;
                Object objReceive = channel.receive(this);
                if (objReceive != coroutine_suspended) {
                    coroutineScope = coroutineScope2;
                    obj = objReceive;
                    MouseWheelScrollDelta mouseWheelScrollDelta2 = (MouseWheelScrollDelta) obj;
                    float f3 = MouseWheelScrollingLogic.this.density.toPx-0680j_4(MouseWheelScrollableKt.AnimationThreshold);
                    float f4 = MouseWheelScrollingLogic.this.density.toPx-0680j_4(MouseWheelScrollableKt.AnimationSpeed);
                    MouseWheelScrollingLogic mouseWheelScrollingLogic3 = MouseWheelScrollingLogic.this;
                    ScrollingLogic scrollingLogic2 = mouseWheelScrollingLogic3.scrollingLogic;
                    this.L$0 = coroutineScope;
                    this.label = 2;
                    c01121 = this;
                }
                return coroutine_suspended;
            } catch (Throwable th5) {
                th2 = th5;
                c01121 = this;
                th = th2;
                MouseWheelScrollingLogic.this.receivingMouseWheelEventsJob = null;
                throw th;
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollingLogic$untilNull$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "E", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollingLogic$untilNull$1", f = "MouseWheelScrollable.kt", i = {0}, l = {207}, m = "invokeSuspend", n = {"$this$sequence"}, s = {"L$0"}, v = 1)
    public static final class C01131<E> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super E>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<E> $builderAction;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01131(Function0<? extends E> function0, Continuation<? super C01131> continuation) {
            super(2, continuation);
            this.$builderAction = function0;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01131 c01131 = new C01131(this.$builderAction, continuation);
            c01131.L$0 = obj;
            return c01131;
        }

        public final Object invoke(SequenceScope<? super E> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x002c  */
        /* JADX WARN: Code duplicated, block: B:13:0x0038 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:14:0x0039  */
        /* JADX WARN: Code duplicated, block: B:16:0x003c  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0036 -> B:15:0x003a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0039 -> B:15:0x003a). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:16:0x003c
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L1c
                if (r1 != r3) goto L16
                java.lang.Object r1 = r5.L$1
                java.lang.Object r4 = r5.L$0
                kotlin.sequences.SequenceScope r4 = (kotlin.sequences.SequenceScope) r4
                kotlin.ResultKt.throwOnFailure(r6)
                goto L3a
            L16:
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                k2d.a(r5)
                return r2
            L1c:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                kotlin.sequences.SequenceScope r6 = (kotlin.sequences.SequenceScope) r6
                r4 = r6
            L24:
                kotlin.jvm.functions.Function0<E> r6 = r5.$builderAction
                java.lang.Object r1 = r6.invoke()
                if (r1 == 0) goto L39
                r5.L$0 = r4
                r5.L$1 = r1
                r5.label = r3
                java.lang.Object r6 = r4.yield(r1, r5)
                if (r6 != r0) goto L3a
                return r0
            L39:
                r1 = r2
            L3a:
                if (r1 != 0) goto L24
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.MouseWheelScrollingLogic.C01131.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollingLogic$userScroll$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollingLogic", f = "MouseWheelScrollable.kt", i = {}, l = {150}, m = "userScroll", n = {}, s = {}, v = 1)
    public static final class C01141 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C01141(Continuation<? super C01141> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MouseWheelScrollingLogic.this.userScroll(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollingLogic$userScroll$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollingLogic$userScroll$2", f = "MouseWheelScrollable.kt", i = {}, l = {150}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01152 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<NestedScrollScope, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ ScrollingLogic $this_userScroll;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01152(ScrollingLogic scrollingLogic, Function2<? super NestedScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super C01152> continuation) {
            super(2, continuation);
            this.$this_userScroll = scrollingLogic;
            this.$block = function2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01152(this.$this_userScroll, this.$block, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ScrollingLogic scrollingLogic = this.$this_userScroll;
                MutatePriority mutatePriority = MutatePriority.UserInput;
                Function2<NestedScrollScope, Continuation<? super Unit>, Object> function2 = this.$block;
                this.label = 1;
                if (scrollingLogic.scroll(mutatePriority, function2, this) == coroutine_suspended) {
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

    public MouseWheelScrollingLogic(ScrollingLogic scrollingLogic, ScrollConfig scrollConfig, Function2<? super Velocity, ? super Continuation<? super Unit>, ? extends Object> function2, Density density) {
        this.scrollingLogic = scrollingLogic;
        this.mouseWheelScrollConfig = scrollConfig;
        this.onScrollStopped = function2;
        this.density = density;
    }

    public static Unit a(Ref.FloatRef floatRef, MouseWheelScrollingLogic mouseWheelScrollingLogic, NestedScrollScope nestedScrollScope, Function1 function1, AnimationScope animationScope) {
        float fFloatValue = ((Number) animationScope.getValue()).floatValue() - floatRef.element;
        if (!MouseWheelScrollableKt.isLowScrollingDelta(fFloatValue)) {
            if (!MouseWheelScrollableKt.isLowScrollingDelta(fFloatValue - mouseWheelScrollingLogic.dispatchMouseWheelScroll(nestedScrollScope, fFloatValue))) {
                animationScope.cancelAnimation();
                return Unit.INSTANCE;
            }
            floatRef.element += fFloatValue;
        }
        if (((Boolean) function1.invoke(Float.valueOf(floatRef.element))).booleanValue()) {
            animationScope.cancelAnimation();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object animateMouseWheelScroll(final NestedScrollScope nestedScrollScope, AnimationState<Float, AnimationVector1D> animationState, float f, int i, final Function1<? super Float, Boolean> function1, Continuation<? super Unit> continuation) {
        final Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = animationState.getValue().floatValue();
        Object objAnimateTo = SuspendAnimationKt.animateTo(animationState, Boxing.boxFloat(f), AnimationSpecKt.tween$default(i, 0, EasingKt.getLinearEasing(), 2, null), true, new Function1() { // from class: i7a
            public final Object invoke(Object obj) {
                return MouseWheelScrollingLogic.a(floatRef, this, nestedScrollScope, function1, (AnimationScope) obj);
            }
        }, continuation);
        return objAnimateTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo : Unit.INSTANCE;
    }

    public static MouseWheelScrollDelta b(Channel channel) {
        return (MouseWheelScrollDelta) ChannelResult.getOrNull-impl(channel.tryReceive-PtdJZtk());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object busyReceive(Channel<MouseWheelScrollDelta> channel, Continuation<? super MouseWheelScrollDelta> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(channel, null), continuation);
    }

    /* JADX INFO: renamed from: canConsumeDelta-Uv8p0NA, reason: not valid java name */
    private final boolean m622canConsumeDeltaUv8p0NA(ScrollingLogic scrollingLogic, long j) {
        float fM705toSingleAxisDeltaFromAnglek4lQ0M = ComposeFoundationFlags.isMouseWheel1DAxisLockingEnabled ? scrollingLogic.m705toSingleAxisDeltaFromAnglek4lQ0M(scrollingLogic.m701reverseIfNeededMKHz9U(j)) : scrollingLogic.m703toFloatk4lQ0M(scrollingLogic.m701reverseIfNeededMKHz9U(j));
        if (fM705toSingleAxisDeltaFromAnglek4lQ0M == 0.0f) {
            return false;
        }
        return fM705toSingleAxisDeltaFromAnglek4lQ0M > 0.0f ? scrollingLogic.getScrollableState().getCanScrollForward() : scrollingLogic.getScrollableState().getCanScrollBackward();
    }

    private final void consume(PointerEvent pointerEvent) {
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            ((PointerInputChange) changes.get(i)).consume();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0103, code lost:
    
        if (r0.invoke(r1, r9) == r10) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object dispatchMouseWheelScroll(ScrollingLogic scrollingLogic, MouseWheelScrollDelta mouseWheelScrollDelta, float f, float f2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Ref.FloatRef floatRef;
        float f3;
        ScrollingLogic scrollingLogic2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 != 0) {
            if (i2 == 1) {
                f3 = anonymousClass2.F$0;
                floatRef = (Ref.FloatRef) anonymousClass2.L$1;
                scrollingLogic2 = (ScrollingLogic) anonymousClass2.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i2 != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = mouseWheelScrollDelta;
        trackVelocity(mouseWheelScrollDelta);
        MouseWheelScrollDelta mouseWheelScrollDeltaSumOrNull = sumOrNull(this.channel);
        if (mouseWheelScrollDeltaSumOrNull != null) {
            trackVelocity(mouseWheelScrollDeltaSumOrNull);
            objectRef.element = ((MouseWheelScrollDelta) objectRef.element).plus(mouseWheelScrollDeltaSumOrNull);
        }
        Ref.FloatRef floatRef2 = new Ref.FloatRef();
        float fM703toFloatk4lQ0M = scrollingLogic.m703toFloatk4lQ0M(scrollingLogic.m701reverseIfNeededMKHz9U(((MouseWheelScrollDelta) objectRef.element).m628getValueF1C5BW0()));
        floatRef2.element = fM703toFloatk4lQ0M;
        if (MouseWheelScrollableKt.isLowScrollingDelta(fM703toFloatk4lQ0M)) {
            return Unit.INSTANCE;
        }
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = AnimationStateKt.AnimationState$default(0.0f, 0.0f, 0L, 0L, false, 30, null);
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(floatRef2, objectRef2, objectRef, f, this, f2, scrollingLogic, null);
        anonymousClass2.L$0 = scrollingLogic;
        anonymousClass2.L$1 = floatRef2;
        anonymousClass2.F$0 = f2;
        anonymousClass2.label = 1;
        if (userScroll(scrollingLogic, anonymousClass3, anonymousClass2) != coroutine_suspended) {
            floatRef = floatRef2;
            f3 = f2;
            scrollingLogic2 = scrollingLogic;
        }
        return coroutine_suspended;
        long jM706toVelocityadjELrA = this.velocityTracker.calculateVelocity-9UxMQ8M();
        if (Velocity.equals-impl0(jM706toVelocityadjELrA, Velocity.Companion.getZero-9UxMQ8M())) {
            jM706toVelocityadjELrA = scrollingLogic2.m706toVelocityadjELrA(scrollingLogic2.reverseIfNeeded(Math.signum(floatRef.element)) * Math.min(Math.abs(floatRef.element) / 100.0f, f3) * 1000.0f);
        }
        Function2<Velocity, Continuation<? super Unit>, Object> function2 = this.onScrollStopped;
        Velocity velocity = Velocity.box-impl(jM706toVelocityadjELrA);
        anonymousClass2.L$0 = null;
        anonymousClass2.L$1 = null;
        anonymousClass2.label = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    public static final Object dispatchMouseWheelScroll$waitNextScrollDelta(MouseWheelScrollingLogic mouseWheelScrollingLogic, Ref.ObjectRef<MouseWheelScrollDelta> objectRef, Ref.FloatRef floatRef, ScrollingLogic scrollingLogic, Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef2, long j, Continuation<? super Boolean> continuation) {
        MouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1 mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1;
        ScrollingLogic scrollingLogic2;
        Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef3;
        Ref.ObjectRef<MouseWheelScrollDelta> objectRef4;
        Ref.FloatRef floatRef2;
        MouseWheelScrollingLogic mouseWheelScrollingLogic2 = mouseWheelScrollingLogic;
        if (continuation instanceof MouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1) {
            mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1 = (MouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1) continuation;
            int i = mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.label = i - Integer.MIN_VALUE;
            } else {
                mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1 = new MouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1(continuation);
            }
        } else {
            mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1 = new MouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1(continuation);
        }
        Object objWithTimeoutOrNull = mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.label;
        boolean z = false;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objWithTimeoutOrNull);
            if (j < 0) {
                return Boxing.boxBoolean(false);
            }
            MouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$2 mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$2 = new MouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$2(mouseWheelScrollingLogic2, null);
            mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$0 = mouseWheelScrollingLogic2;
            mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$1 = objectRef;
            mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$2 = floatRef;
            scrollingLogic2 = scrollingLogic;
            mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$3 = scrollingLogic2;
            objectRef3 = objectRef2;
            mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$4 = objectRef3;
            mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.label = 1;
            objWithTimeoutOrNull = TimeoutKt.withTimeoutOrNull(j, mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$2, mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1);
            if (objWithTimeoutOrNull == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef4 = objectRef;
            floatRef2 = floatRef;
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            Ref.ObjectRef<AnimationState<Float, AnimationVector1D>> objectRef5 = (Ref.ObjectRef) mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$4;
            ScrollingLogic scrollingLogic3 = (ScrollingLogic) mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$3;
            floatRef2 = (Ref.FloatRef) mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$2;
            objectRef4 = (Ref.ObjectRef) mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$1;
            MouseWheelScrollingLogic mouseWheelScrollingLogic3 = (MouseWheelScrollingLogic) mouseWheelScrollingLogic$dispatchMouseWheelScroll$waitNextScrollDelta$1.L$0;
            ResultKt.throwOnFailure(objWithTimeoutOrNull);
            objectRef3 = objectRef5;
            scrollingLogic2 = scrollingLogic3;
            mouseWheelScrollingLogic2 = mouseWheelScrollingLogic3;
        }
        MouseWheelScrollDelta mouseWheelScrollDelta = (MouseWheelScrollDelta) objWithTimeoutOrNull;
        if (mouseWheelScrollDelta != null) {
            MouseWheelScrollDelta mouseWheelScrollDeltaM625copy9KIMszo$default = MouseWheelScrollDelta.m625copy9KIMszo$default(mouseWheelScrollDelta, 0L, 0L, ((MouseWheelScrollDelta) objectRef4.element).getShouldApplyImmediately(), 3, null);
            objectRef4.element = mouseWheelScrollDeltaM625copy9KIMszo$default;
            floatRef2.element = ComposeFoundationFlags.isMouseWheel1DAxisLockingEnabled ? scrollingLogic2.m705toSingleAxisDeltaFromAnglek4lQ0M(scrollingLogic2.m701reverseIfNeededMKHz9U(mouseWheelScrollDeltaM625copy9KIMszo$default.m628getValueF1C5BW0())) : scrollingLogic2.m703toFloatk4lQ0M(scrollingLogic2.m701reverseIfNeededMKHz9U(mouseWheelScrollDeltaM625copy9KIMszo$default.m628getValueF1C5BW0()));
            objectRef3.element = AnimationStateKt.AnimationState$default(0.0f, 0.0f, 0L, 0L, false, 30, null);
            mouseWheelScrollingLogic2.trackVelocity(mouseWheelScrollDelta);
            z = !MouseWheelScrollableKt.isLowScrollingDelta(floatRef2.element);
        }
        return Boxing.boxBoolean(z);
    }

    private final boolean isConsumed(PointerEvent pointerEvent) {
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            if (((PointerInputChange) changes.get(i)).isConsumed()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: onMouseWheel-O0kMr_c, reason: not valid java name */
    private final boolean m623onMouseWheelO0kMr_c(PointerEvent pointerEvent, long bounds) {
        long jMo523calculateMouseWheelScroll8xgXZGE = this.mouseWheelScrollConfig.mo523calculateMouseWheelScroll8xgXZGE(this.density, pointerEvent, bounds);
        if (m622canConsumeDeltaUv8p0NA(this.scrollingLogic, jMo523calculateMouseWheelScroll8xgXZGE)) {
            return ChannelResult.isSuccess-impl(this.channel.trySend-JP2dKIU(new MouseWheelScrollDelta(jMo523calculateMouseWheelScroll8xgXZGE, ((PointerInputChange) CollectionsKt.first(pointerEvent.getChanges())).getUptimeMillis(), !this.mouseWheelScrollConfig.isSmoothScrollingEnabled() || this.mouseWheelScrollConfig.isPreciseWheelScroll(pointerEvent), null)));
        }
        return this.isScrolling;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MouseWheelScrollDelta sumOrNull(final Channel<MouseWheelScrollDelta> channel) {
        MouseWheelScrollDelta mouseWheelScrollDeltaPlus = null;
        for (MouseWheelScrollDelta mouseWheelScrollDelta : untilNull(new Function0() { // from class: h7a
            public final Object invoke() {
                return MouseWheelScrollingLogic.b(channel);
            }
        })) {
            mouseWheelScrollDeltaPlus = mouseWheelScrollDeltaPlus == null ? mouseWheelScrollDelta : mouseWheelScrollDeltaPlus.plus(mouseWheelScrollDelta);
        }
        return mouseWheelScrollDeltaPlus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trackVelocity(MouseWheelScrollDelta scrollDelta) {
        this.velocityTracker.addDelta-Uv8p0NA(scrollDelta.getTimeMillis(), scrollDelta.m628getValueF1C5BW0());
    }

    private final <E> Sequence<E> untilNull(Function0<? extends E> builderAction) {
        return SequencesKt.sequence(new C01131(builderAction, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object userScroll(ScrollingLogic scrollingLogic, Function2<? super NestedScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        C01141 c01141;
        if (continuation instanceof C01141) {
            c01141 = (C01141) continuation;
            int i = c01141.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01141.label = i - Integer.MIN_VALUE;
            } else {
                c01141 = new C01141(continuation);
            }
        } else {
            c01141 = new C01141(continuation);
        }
        Object obj = c01141.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01141.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            this.isScrolling = true;
            C01152 c01152 = new C01152(scrollingLogic, function2, null);
            c01141.label = 1;
            if (SupervisorKt.supervisorScope(c01152, c01141) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.isScrolling = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public final void m624onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        int i = 0;
        if (!ComposeFoundationFlags.isMouseWheel1DAxisLockingEnabled) {
            if (pass == PointerEventPass.Main && PointerEventType.equals-impl0(pointerEvent.getType-7fucELk(), PointerEventType.Companion.getScroll-7fucELk())) {
                List changes = pointerEvent.getChanges();
                int size = changes.size();
                while (i < size) {
                    if (((PointerInputChange) changes.get(i)).isConsumed()) {
                        return;
                    } else {
                        i++;
                    }
                }
                if (m623onMouseWheelO0kMr_c(pointerEvent, bounds)) {
                    consume(pointerEvent);
                    return;
                }
                return;
            }
            return;
        }
        if (PointerEventType.equals-impl0(pointerEvent.getType-7fucELk(), PointerEventType.Companion.getScroll-7fucELk())) {
            List changes2 = pointerEvent.getChanges();
            int size2 = changes2.size();
            while (i < size2) {
                if (((PointerInputChange) changes2.get(i)).isConsumed()) {
                    return;
                } else {
                    i++;
                }
            }
            if (pass == PointerEventPass.Initial && this.isScrolling) {
                m623onMouseWheelO0kMr_c(pointerEvent, bounds);
                consume(pointerEvent);
            }
            if (pass == PointerEventPass.Main && !this.isScrolling && m623onMouseWheelO0kMr_c(pointerEvent, bounds)) {
                consume(pointerEvent);
            }
        }
    }

    public final void startReceivingMouseWheelEvents(CoroutineScope coroutineScope) {
        if (this.receivingMouseWheelEventsJob == null) {
            this.receivingMouseWheelEventsJob = BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01121(null), 3, (Object) null);
        }
    }

    public final void updateDensity(Density density) {
        this.density = density;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0000H\u0086\u0002J\u0010\u0010\u0012\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J.\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/gestures/MouseWheelScrollingLogic$MouseWheelScrollDelta;", "", "value", "Landroidx/compose/ui/geometry/Offset;", "timeMillis", "", "shouldApplyImmediately", "", "<init>", "(JJZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getValue-F1C5BW0", "()J", "J", "getTimeMillis", "getShouldApplyImmediately", "()Z", "plus", "other", "component1", "component1-F1C5BW0", "component2", "component3", "copy", "copy-9KIMszo", "(JJZ)Landroidx/compose/foundation/gestures/MouseWheelScrollingLogic$MouseWheelScrollDelta;", "equals", "hashCode", "", "toString", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class MouseWheelScrollDelta {
        private final boolean shouldApplyImmediately;
        private final long timeMillis;
        private final long value;

        private MouseWheelScrollDelta(long j, long j2, boolean z) {
            this.value = j;
            this.timeMillis = j2;
            this.shouldApplyImmediately = z;
        }

        /* JADX INFO: renamed from: copy-9KIMszo$default, reason: not valid java name */
        public static /* synthetic */ MouseWheelScrollDelta m625copy9KIMszo$default(MouseWheelScrollDelta mouseWheelScrollDelta, long j, long j2, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                j = mouseWheelScrollDelta.value;
            }
            long j3 = j;
            if ((i & 2) != 0) {
                j2 = mouseWheelScrollDelta.timeMillis;
            }
            long j4 = j2;
            if ((i & 4) != 0) {
                z = mouseWheelScrollDelta.shouldApplyImmediately;
            }
            return mouseWheelScrollDelta.m627copy9KIMszo(j3, j4, z);
        }

        /* JADX INFO: renamed from: component1-F1C5BW0, reason: not valid java name and from getter */
        public final long getValue() {
            return this.value;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTimeMillis() {
            return this.timeMillis;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getShouldApplyImmediately() {
            return this.shouldApplyImmediately;
        }

        /* JADX INFO: renamed from: copy-9KIMszo, reason: not valid java name */
        public final MouseWheelScrollDelta m627copy9KIMszo(long value, long timeMillis, boolean shouldApplyImmediately) {
            return new MouseWheelScrollDelta(value, timeMillis, shouldApplyImmediately, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MouseWheelScrollDelta)) {
                return false;
            }
            MouseWheelScrollDelta mouseWheelScrollDelta = (MouseWheelScrollDelta) other;
            return Offset.equals-impl0(this.value, mouseWheelScrollDelta.value) && this.timeMillis == mouseWheelScrollDelta.timeMillis && this.shouldApplyImmediately == mouseWheelScrollDelta.shouldApplyImmediately;
        }

        public final boolean getShouldApplyImmediately() {
            return this.shouldApplyImmediately;
        }

        public final long getTimeMillis() {
            return this.timeMillis;
        }

        /* JADX INFO: renamed from: getValue-F1C5BW0, reason: not valid java name */
        public final long m628getValueF1C5BW0() {
            return this.value;
        }

        public int hashCode() {
            return (((Offset.hashCode-impl(this.value) * 31) + Long.hashCode(this.timeMillis)) * 31) + Boolean.hashCode(this.shouldApplyImmediately);
        }

        public final MouseWheelScrollDelta plus(MouseWheelScrollDelta other) {
            return new MouseWheelScrollDelta(Offset.plus-MK-Hz9U(this.value, other.value), Math.max(this.timeMillis, other.timeMillis), this.shouldApplyImmediately, null);
        }

        public String toString() {
            return "MouseWheelScrollDelta(value=" + ((Object) Offset.toString-impl(this.value)) + ", timeMillis=" + this.timeMillis + ", shouldApplyImmediately=" + this.shouldApplyImmediately + ')';
        }

        public /* synthetic */ MouseWheelScrollDelta(long j, long j2, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, j2, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float dispatchMouseWheelScroll(NestedScrollScope nestedScrollScope, float f) {
        ScrollingLogic scrollingLogic = this.scrollingLogic;
        return scrollingLogic.m703toFloatk4lQ0M(scrollingLogic.m701reverseIfNeededMKHz9U(nestedScrollScope.mo629scrollByOzD1aCk(scrollingLogic.m704toOffsettuRUvjQ(scrollingLogic.reverseIfNeeded(f)), NestedScrollSource.Companion.getUserInput-WNlRxjI())));
    }
}
