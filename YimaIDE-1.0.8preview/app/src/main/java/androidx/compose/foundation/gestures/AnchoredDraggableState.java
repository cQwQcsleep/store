package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.gestures.AnchoredDraggableState;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0081\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n*\u0001m\b\u0007\u0018\u0000 \u0081\u0001*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u0081\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005B\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\u0004\u0010\bB4\b\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012!\u0010\t\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\u0004\b\u0004\u0010\u000fBD\b\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012#\b\u0002\u0010\t\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\u0004\b\u0004\u0010\u0010J\b\u0010L\u001a\u00020\u0016H\u0007J\u001d\u0010N\u001a\u00020\u00162\u0006\u0010O\u001a\u00028\u00002\u0006\u0010P\u001a\u00028\u0000H\u0007¢\u0006\u0002\u0010QJ#\u0010b\u001a\u00020c2\f\u0010d\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\b\b\u0002\u0010e\u001a\u00028\u0000¢\u0006\u0002\u0010fJ\u001c\u0010g\u001a\u00020c2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00160!H\u0086@¢\u0006\u0002\u0010iJ\u0016\u0010g\u001a\u00020\u00162\u0006\u0010j\u001a\u00020\u0016H\u0087@¢\u0006\u0002\u0010kJ\\\u0010o\u001a\u00020c2\b\b\u0002\u0010p\u001a\u00020q2B\u0010r\u001a>\b\u0001\u0012\u0004\u0012\u00020t\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0u\u0012\u0006\u0012\u0004\u0018\u00010\u00020s¢\u0006\u0002\bvH\u0086@¢\u0006\u0002\u0010wJy\u0010o\u001a\u00020c2\u0006\u0010A\u001a\u00028\u00002\b\b\u0002\u0010p\u001a\u00020q2W\u0010r\u001aS\b\u0001\u0012\u0004\u0012\u00020t\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(y\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(A\u0012\n\u0012\b\u0012\u0004\u0012\u00020c0u\u0012\u0006\u0012\u0004\u0018\u00010\u00020x¢\u0006\u0002\bvH\u0086@¢\u0006\u0002\u0010zJ\u0015\u0010{\u001a\u00020\u00162\u0006\u0010|\u001a\u00020\u0016H\u0000¢\u0006\u0002\b}J\u000e\u0010~\u001a\u00020\u00162\u0006\u0010|\u001a\u00020\u0016J\u0016\u0010\u007f\u001a\u00020\u000e2\u0006\u0010A\u001a\u00028\u0000H\u0002¢\u0006\u0003\u0010\u0080\u0001R5\u0010\t\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R5\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00160\nX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0012\"\u0004\b\u0019\u0010\u0014R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u001bX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR8\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00160!2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160!8\u0006@@X\u0087.¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R8\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00160)2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160)8\u0006@@X\u0087.¢\u0006\u0014\n\u0000\u0012\u0004\b+\u0010$\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u00020\u000e8@X\u0080\u0004¢\u0006\f\u0012\u0004\b1\u0010$\u001a\u0004\b2\u00103R\u000e\u00104\u001a\u000205X\u0082\u0004¢\u0006\u0002\n\u0000R+\u00107\u001a\u00028\u00002\u0006\u00106\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b8\u00109\"\u0004\b:\u0010\u0005R+\u0010=\u001a\u00028\u00002\u0006\u00106\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b@\u0010<\u001a\u0004\b>\u00109\"\u0004\b?\u0010\u0005R\u001b\u0010A\u001a\u00028\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bB\u00109R+\u0010E\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00168G@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0011\u0010M\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\bM\u00103R!\u0010N\u001a\u00020\u00168GX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bT\u0010D\u0012\u0004\bR\u0010$\u001a\u0004\bS\u0010GR+\u0010U\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00168F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010K\u001a\u0004\bV\u0010G\"\u0004\bW\u0010IR/\u0010Y\u001a\u0004\u0018\u00018\u00002\b\u00106\u001a\u0004\u0018\u00018\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\\\u0010<\u001a\u0004\bZ\u00109\"\u0004\b[\u0010\u0005R7\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u00000\u00078F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\ba\u0010<\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u0010\u0010l\u001a\u00020mX\u0082\u0004¢\u0006\u0004\n\u0002\u0010n¨\u0006\u0082\u0001"}, d2 = {"Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "T", "", "initialValue", "<init>", "(Ljava/lang/Object;)V", "anchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "(Ljava/lang/Object;Landroidx/compose/foundation/gestures/DraggableAnchors;)V", "confirmValueChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "newValue", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/Object;Landroidx/compose/foundation/gestures/DraggableAnchors;Lkotlin/jvm/functions/Function1;)V", "getConfirmValueChange$foundation", "()Lkotlin/jvm/functions/Function1;", "setConfirmValueChange$foundation", "(Lkotlin/jvm/functions/Function1;)V", "positionalThreshold", "", "totalDistance", "getPositionalThreshold$foundation", "setPositionalThreshold$foundation", "velocityThreshold", "Lkotlin/Function0;", "getVelocityThreshold$foundation", "()Lkotlin/jvm/functions/Function0;", "setVelocityThreshold$foundation", "(Lkotlin/jvm/functions/Function0;)V", "value", "Landroidx/compose/animation/core/AnimationSpec;", "snapAnimationSpec", "getSnapAnimationSpec$annotations", "()V", "getSnapAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "setSnapAnimationSpec$foundation", "(Landroidx/compose/animation/core/AnimationSpec;)V", "Landroidx/compose/animation/core/DecayAnimationSpec;", "decayAnimationSpec", "getDecayAnimationSpec$annotations", "getDecayAnimationSpec", "()Landroidx/compose/animation/core/DecayAnimationSpec;", "setDecayAnimationSpec$foundation", "(Landroidx/compose/animation/core/DecayAnimationSpec;)V", "usePreModifierChangeBehavior", "getUsePreModifierChangeBehavior$foundation$annotations", "getUsePreModifierChangeBehavior$foundation", "()Z", "dragMutex", "Landroidx/compose/foundation/MutatorMutex;", "<set-?>", "currentValue", "getCurrentValue", "()Ljava/lang/Object;", "setCurrentValue", "currentValue$delegate", "Landroidx/compose/runtime/MutableState;", "settledValue", "getSettledValue", "setSettledValue", "settledValue$delegate", "targetValue", "getTargetValue", "targetValue$delegate", "Landroidx/compose/runtime/State;", "offset", "getOffset", "()F", "setOffset", "(F)V", "offset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "requireOffset", "isAnimationRunning", "progress", "from", "to", "(Ljava/lang/Object;Ljava/lang/Object;)F", "getProgress$annotations", "getProgress", "progress$delegate", "lastVelocity", "getLastVelocity", "setLastVelocity", "lastVelocity$delegate", "dragTarget", "getDragTarget", "setDragTarget", "dragTarget$delegate", "getAnchors", "()Landroidx/compose/foundation/gestures/DraggableAnchors;", "setAnchors", "(Landroidx/compose/foundation/gestures/DraggableAnchors;)V", "anchors$delegate", "updateAnchors", "", "newAnchors", "newTarget", "(Landroidx/compose/foundation/gestures/DraggableAnchors;Ljava/lang/Object;)V", "settle", "animationSpec", "(Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "velocity", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "anchoredDragScope", "androidx/compose/foundation/gestures/AnchoredDraggableState$anchoredDragScope$1", "Landroidx/compose/foundation/gestures/AnchoredDraggableState$anchoredDragScope$1;", "anchoredDrag", "dragPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function3;", "Landroidx/compose/foundation/gestures/AnchoredDragScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function4;", "anchor", "(Ljava/lang/Object;Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newOffsetForDelta", "delta", "newOffsetForDelta$foundation", "dispatchRawDelta", "trySnapTo", "(Ljava/lang/Object;)Z", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AnchoredDraggableState<T> {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDraggableState$anchoredDragScope$1 anchoredDragScope;

    /* JADX INFO: renamed from: anchors$delegate, reason: from kotlin metadata */
    private final MutableState anchors;
    private Function1<? super T, Boolean> confirmValueChange;

    /* JADX INFO: renamed from: currentValue$delegate, reason: from kotlin metadata */
    private final MutableState currentValue;
    public DecayAnimationSpec<Float> decayAnimationSpec;
    private final MutatorMutex dragMutex;

    /* JADX INFO: renamed from: dragTarget$delegate, reason: from kotlin metadata */
    private final MutableState dragTarget;

    /* JADX INFO: renamed from: lastVelocity$delegate, reason: from kotlin metadata */
    private final MutableFloatState lastVelocity;

    /* JADX INFO: renamed from: offset$delegate, reason: from kotlin metadata */
    private final MutableFloatState offset;
    public Function1<? super Float, Float> positionalThreshold;

    /* JADX INFO: renamed from: progress$delegate, reason: from kotlin metadata */
    private final State progress;

    /* JADX INFO: renamed from: settledValue$delegate, reason: from kotlin metadata */
    private final MutableState settledValue;
    public AnimationSpec<Float> snapAnimationSpec;

    /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
    private final State targetValue;
    public Function0<Float> velocityThreshold;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$2", f = "AnchoredDraggable.kt", i = {}, l = {1128}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<AnchoredDragScope, DraggableAnchors<T>, Continuation<? super Unit>, Object> $block;
        int label;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "T", "latestAnchors", "Landroidx/compose/foundation/gestures/DraggableAnchors;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$2$2", f = "AnchoredDraggable.kt", i = {}, l = {1129}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class C00142 extends SuspendLambda implements Function2<DraggableAnchors<T>, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function3<AnchoredDragScope, DraggableAnchors<T>, Continuation<? super Unit>, Object> $block;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C00142(Function3<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super Continuation<? super Unit>, ? extends Object> function3, AnchoredDraggableState<T> anchoredDraggableState, Continuation<? super C00142> continuation) {
                super(2, continuation);
                this.$block = function3;
                this.this$0 = anchoredDraggableState;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00142 c00142 = new C00142(this.$block, this.this$0, continuation);
                c00142.L$0 = obj;
                return c00142;
            }

            public final Object invoke(DraggableAnchors<T> draggableAnchors, Continuation<? super Unit> continuation) {
                return create(draggableAnchors, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DraggableAnchors draggableAnchors = (DraggableAnchors) this.L$0;
                    Function3<AnchoredDragScope, DraggableAnchors<T>, Continuation<? super Unit>, Object> function3 = this.$block;
                    AnchoredDraggableState$anchoredDragScope$1 anchoredDraggableState$anchoredDragScope$1 = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
                    this.label = 1;
                    if (function3.invoke(anchoredDraggableState$anchoredDragScope$1, draggableAnchors, this) == coroutine_suspended) {
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
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(AnchoredDraggableState<T> anchoredDraggableState, Function3<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.this$0 = anchoredDraggableState;
            this.$block = function3;
        }

        public static DraggableAnchors b(AnchoredDraggableState anchoredDraggableState) {
            return anchoredDraggableState.getAnchors();
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$block, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
                Function0 function0 = new Function0() { // from class: androidx.compose.foundation.gestures.d
                    public final Object invoke() {
                        return AnchoredDraggableState.AnonymousClass2.b(anchoredDraggableState);
                    }
                };
                C00142 c00142 = new C00142(this.$block, this.this$0, null);
                this.label = 1;
                if (AnchoredDraggableKt.restartable(function0, c00142, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            T tClosestAnchor = this.this$0.getAnchors().closestAnchor(this.this$0.getOffset());
            if (tClosestAnchor != null) {
                if (Math.abs(this.this$0.getOffset() - this.this$0.getAnchors().positionOf(tClosestAnchor)) < 0.5f && ((Boolean) this.this$0.getConfirmValueChange$foundation().invoke(tClosestAnchor)).booleanValue()) {
                    this.this$0.setSettledValue(tClosestAnchor);
                    this.this$0.setCurrentValue(tClosestAnchor);
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableState", f = "AnchoredDraggable.kt", i = {}, l = {1173}, m = "anchoredDrag", n = {}, s = {}, v = 1)
    public static final class AnonymousClass3 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(AnchoredDraggableState<T> anchoredDraggableState, Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
            this.this$0 = anchoredDraggableState;
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.anchoredDrag(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$4", f = "AnchoredDraggable.kt", i = {}, l = {1175}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass4 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ T $targetValue;
        int label;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$4$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0018\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0004\u0012\u0002H\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "T", "<destruct>", "Lkotlin/Pair;", "Landroidx/compose/foundation/gestures/DraggableAnchors;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.AnchoredDraggableState$anchoredDrag$4$2", f = "AnchoredDraggable.kt", i = {}, l = {1177}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<Pair<? extends DraggableAnchors<T>, ? extends T>, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> $block;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(Function4<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function4, AnchoredDraggableState<T> anchoredDraggableState, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$block = function4;
                this.this$0 = anchoredDraggableState;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$block, this.this$0, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            public final Object invoke(Pair<? extends DraggableAnchors<T>, ? extends T> pair, Continuation<? super Unit> continuation) {
                return create(pair, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Pair pair = (Pair) this.L$0;
                    DraggableAnchors draggableAnchors = (DraggableAnchors) pair.component1();
                    Object objComponent2 = pair.component2();
                    Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> function4 = this.$block;
                    AnchoredDraggableState$anchoredDragScope$1 anchoredDraggableState$anchoredDragScope$1 = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
                    this.label = 1;
                    if (function4.invoke(anchoredDraggableState$anchoredDragScope$1, draggableAnchors, objComponent2, this) == coroutine_suspended) {
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
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(AnchoredDraggableState<T> anchoredDraggableState, T t, Function4<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super AnonymousClass4> continuation) {
            super(1, continuation);
            this.this$0 = anchoredDraggableState;
            this.$targetValue = t;
            this.$block = function4;
        }

        public static Pair b(AnchoredDraggableState anchoredDraggableState) {
            return TuplesKt.to(anchoredDraggableState.getAnchors(), anchoredDraggableState.getTargetValue());
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass4(this.this$0, this.$targetValue, this.$block, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.setDragTarget(this.$targetValue);
                final AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
                Function0 function0 = new Function0() { // from class: androidx.compose.foundation.gestures.e
                    public final Object invoke() {
                        return AnchoredDraggableState.AnonymousClass4.b(anchoredDraggableState);
                    }
                };
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$block, this.this$0, null);
                this.label = 1;
                if (AnchoredDraggableKt.restartable(function0, anonymousClass2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            if (((Boolean) this.this$0.getConfirmValueChange$foundation().invoke(this.$targetValue)).booleanValue()) {
                ((AnchoredDraggableState) this.this$0).anchoredDragScope.dragTo(this.this$0.getAnchors().positionOf(this.$targetValue), this.this$0.getLastVelocity());
                this.this$0.setSettledValue(this.$targetValue);
                this.this$0.setCurrentValue(this.$targetValue);
            }
            return Unit.INSTANCE;
        }
    }

    public AnchoredDraggableState(T t) {
        this.confirmValueChange = new Function1() { // from class: f40
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AnchoredDraggableState.c(obj));
            }
        };
        this.dragMutex = new MutatorMutex();
        this.currentValue = SnapshotStateKt.mutableStateOf$default(t, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.settledValue = SnapshotStateKt.mutableStateOf$default(t, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.targetValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: h40
            public final Object invoke() {
                return AnchoredDraggableState.a(this.b);
            }
        });
        this.offset = PrimitiveSnapshotStateKt.mutableFloatStateOf(Float.NaN);
        this.progress = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: j40
            public final Object invoke() {
                return Float.valueOf(AnchoredDraggableState.d(this.b));
            }
        });
        this.lastVelocity = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragTarget = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.anchors = SnapshotStateKt.mutableStateOf$default(AnchoredDraggableKt.emptyDraggableAnchors(), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.anchoredDragScope = new AnchoredDraggableState$anchoredDragScope$1(this);
    }

    public static Object a(AnchoredDraggableState anchoredDraggableState) {
        T tClosestAnchor;
        Object dragTarget = anchoredDraggableState.getDragTarget();
        if (dragTarget == null) {
            return (Float.isNaN(anchoredDraggableState.getOffset()) || (tClosestAnchor = anchoredDraggableState.getAnchors().closestAnchor(anchoredDraggableState.getOffset())) == null) ? anchoredDraggableState.getCurrentValue() : tClosestAnchor;
        }
        return dragTarget;
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, MutatePriority mutatePriority, Function3 function3, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(mutatePriority, function3, continuation);
    }

    public static boolean b(Object obj) {
        return true;
    }

    public static boolean c(Object obj) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static float d(AnchoredDraggableState anchoredDraggableState) {
        float fPositionOf = anchoredDraggableState.getAnchors().positionOf(anchoredDraggableState.getSettledValue());
        float fPositionOf2 = anchoredDraggableState.getAnchors().positionOf(anchoredDraggableState.getTargetValue()) - fPositionOf;
        float fAbs = Math.abs(fPositionOf2);
        if (Float.isNaN(fAbs) || fAbs <= 1.0E-6f) {
            return 1.0f;
        }
        float fRequireOffset = (anchoredDraggableState.requireOffset() - fPositionOf) / fPositionOf2;
        if (fRequireOffset < 1.0E-6f) {
            return 0.0f;
        }
        if (fRequireOffset > 0.999999f) {
            return 1.0f;
        }
        return fRequireOffset;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This constructor of AnchoredDraggableState has been deprecated. Please pass thresholds and animation specs to AnchoredDraggableDefaults.flingBehavior(..) instead, which can be passed to Modifier.anchoredDraggable.")
    public static /* synthetic */ void getDecayAnimationSpec$annotations() {
    }

    private final T getDragTarget() {
        return (T) this.dragTarget.getValue();
    }

    @Deprecated(message = "Use the progress function to query the progress between two specified anchors.", replaceWith = @ReplaceWith(expression = "progress(state.settledValue, state.targetValue)", imports = {}))
    public static /* synthetic */ void getProgress$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This constructor of AnchoredDraggableState has been deprecated. Please pass thresholds and animation specs to AnchoredDraggableDefaults.flingBehavior(..) instead, which can be passed to Modifier.anchoredDraggable.")
    public static /* synthetic */ void getSnapAnimationSpec$annotations() {
    }

    public static /* synthetic */ void getUsePreModifierChangeBehavior$foundation$annotations() {
    }

    private final void setAnchors(DraggableAnchors<T> draggableAnchors) {
        this.anchors.setValue(draggableAnchors);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentValue(T t) {
        this.currentValue.setValue(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDragTarget(T t) {
        this.dragTarget.setValue(t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLastVelocity(float f) {
        this.lastVelocity.setFloatValue(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOffset(float f) {
        this.offset.setFloatValue(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSettledValue(T t) {
        this.settledValue.setValue(t);
    }

    private final boolean trySnapTo(T targetValue) {
        MutatorMutex mutatorMutex = this.dragMutex;
        boolean zTryLock = mutatorMutex.tryLock();
        if (!zTryLock) {
            return zTryLock;
        }
        try {
            AnchoredDraggableState$anchoredDragScope$1 anchoredDraggableState$anchoredDragScope$1 = this.anchoredDragScope;
            float fPositionOf = getAnchors().positionOf(targetValue);
            if (!Float.isNaN(fPositionOf)) {
                AnchoredDragScope.dragTo$default(anchoredDraggableState$anchoredDragScope$1, fPositionOf, 0.0f, 2, null);
                setDragTarget(null);
            }
            setCurrentValue(targetValue);
            setSettledValue(targetValue);
            return zTryLock;
        } finally {
            mutatorMutex.unlock();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateAnchors$default(AnchoredDraggableState anchoredDraggableState, DraggableAnchors draggableAnchors, Object obj, int i, Object obj2) {
        if ((i & 2) != 0 && (Float.isNaN(anchoredDraggableState.getOffset()) || (obj = draggableAnchors.closestAnchor(anchoredDraggableState.getOffset())) == null)) {
            obj = anchoredDraggableState.getTargetValue();
        }
        anchoredDraggableState.updateAnchors(draggableAnchors, obj);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object anchoredDrag(T t, MutatePriority mutatePriority, Function4<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super T, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super Unit> continuation) {
        AnonymousClass3 anonymousClass3;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            int i = anonymousClass3.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(this, continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(this, continuation);
        }
        Object obj = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass3.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                if (getAnchors().hasPositionFor(t)) {
                    MutatorMutex mutatorMutex = this.dragMutex;
                    AnonymousClass4 anonymousClass4 = new AnonymousClass4(this, t, function4, null);
                    anonymousClass3.label = 1;
                    if (mutatorMutex.mutate(mutatePriority, anonymousClass4, anonymousClass3) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (((Boolean) this.confirmValueChange.invoke(t)).booleanValue()) {
                    setSettledValue(t);
                    setCurrentValue(t);
                }
                return Unit.INSTANCE;
            }
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            setDragTarget(null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            setDragTarget(null);
            throw th;
        }
    }

    public final float dispatchRawDelta(float delta) {
        float fNewOffsetForDelta$foundation = newOffsetForDelta$foundation(delta);
        float fRequireOffset = fNewOffsetForDelta$foundation - requireOffset();
        AnchoredDragScope.dragTo$default(this.anchoredDragScope, fNewOffsetForDelta$foundation, 0.0f, 2, null);
        return fRequireOffset;
    }

    public final DraggableAnchors<T> getAnchors() {
        return (DraggableAnchors) this.anchors.getValue();
    }

    public final Function1<T, Boolean> getConfirmValueChange$foundation() {
        return this.confirmValueChange;
    }

    public final T getCurrentValue() {
        return (T) this.currentValue.getValue();
    }

    public final DecayAnimationSpec<Float> getDecayAnimationSpec() {
        DecayAnimationSpec<Float> decayAnimationSpec = this.decayAnimationSpec;
        if (decayAnimationSpec != null) {
            return decayAnimationSpec;
        }
        Intrinsics.throwUninitializedPropertyAccessException("decayAnimationSpec");
        return null;
    }

    public final float getLastVelocity() {
        return this.lastVelocity.getFloatValue();
    }

    public final float getOffset() {
        return this.offset.getFloatValue();
    }

    public final Function1<Float, Float> getPositionalThreshold$foundation() {
        Function1 function1 = this.positionalThreshold;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("positionalThreshold");
        return null;
    }

    public final float getProgress() {
        return ((Number) this.progress.getValue()).floatValue();
    }

    public final T getSettledValue() {
        return (T) this.settledValue.getValue();
    }

    public final AnimationSpec<Float> getSnapAnimationSpec() {
        AnimationSpec<Float> animationSpec = this.snapAnimationSpec;
        if (animationSpec != null) {
            return animationSpec;
        }
        Intrinsics.throwUninitializedPropertyAccessException("snapAnimationSpec");
        return null;
    }

    public final T getTargetValue() {
        return (T) this.targetValue.getValue();
    }

    public final boolean getUsePreModifierChangeBehavior$foundation() {
        return (this.positionalThreshold == null || this.velocityThreshold == null || this.snapAnimationSpec == null || this.decayAnimationSpec == null) ? false : true;
    }

    public final Function0<Float> getVelocityThreshold$foundation() {
        Function0<Float> function0 = this.velocityThreshold;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("velocityThreshold");
        return null;
    }

    public final boolean isAnimationRunning() {
        return getDragTarget() != null;
    }

    public final float newOffsetForDelta$foundation(float delta) {
        return RangesKt.coerceIn((Float.isNaN(getOffset()) ? 0.0f : getOffset()) + delta, getAnchors().minPosition(), getAnchors().maxPosition());
    }

    public final float progress(T from, T to) {
        float fPositionOf = getAnchors().positionOf(from);
        float fPositionOf2 = getAnchors().positionOf(to);
        float fCoerceIn = (RangesKt.coerceIn(getOffset(), Math.min(fPositionOf, fPositionOf2), Math.max(fPositionOf, fPositionOf2)) - fPositionOf) / (fPositionOf2 - fPositionOf);
        if (Float.isNaN(fCoerceIn)) {
            return 1.0f;
        }
        if (fCoerceIn < 1.0E-6f) {
            return 0.0f;
        }
        if (fCoerceIn > 0.999999f) {
            return 1.0f;
        }
        return Math.abs(fCoerceIn);
    }

    public final float requireOffset() {
        if (Float.isNaN(getOffset())) {
            InlineClassHelperKt.throwIllegalStateException("The offset was read before being initialized. Did you access the offset in a phase before layout, like effects or composition?");
        }
        return getOffset();
    }

    public final void setConfirmValueChange$foundation(Function1<? super T, Boolean> function1) {
        this.confirmValueChange = function1;
    }

    public final void setDecayAnimationSpec$foundation(DecayAnimationSpec<Float> decayAnimationSpec) {
        this.decayAnimationSpec = decayAnimationSpec;
    }

    public final void setPositionalThreshold$foundation(Function1<? super Float, Float> function1) {
        this.positionalThreshold = function1;
    }

    public final void setSnapAnimationSpec$foundation(AnimationSpec<Float> animationSpec) {
        this.snapAnimationSpec = animationSpec;
    }

    public final void setVelocityThreshold$foundation(Function0<Float> function0) {
        this.velocityThreshold = function0;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "settle does not accept a velocity anymore. Please use FlingBehavior#performFling instead. See AnchoredDraggableSample.kt for example usages.")
    public final Object settle(float f, Continuation<? super Float> continuation) {
        if (!getUsePreModifierChangeBehavior$foundation()) {
            InlineClassHelperKt.throwIllegalArgumentException("AnchoredDraggableState was configured through a constructor without providing positional and velocity threshold. This overload of settle has been deprecated. Please refer to AnchoredDraggableState#settle(animationSpec) for more information.");
        }
        T currentValue = getCurrentValue();
        Object objComputeTarget = AnchoredDraggableKt.computeTarget(getAnchors(), requireOffset(), f, getPositionalThreshold$foundation(), getVelocityThreshold$foundation());
        return ((Boolean) this.confirmValueChange.invoke(objComputeTarget)).booleanValue() ? AnchoredDraggableKt.animateToWithDecay$default(this, objComputeTarget, f, null, null, continuation, 12, null) : AnchoredDraggableKt.animateToWithDecay$default(this, currentValue, f, null, null, continuation, 12, null);
    }

    public final void updateAnchors(DraggableAnchors<T> newAnchors, T newTarget) {
        if (Intrinsics.areEqual(getAnchors(), newAnchors)) {
            return;
        }
        setAnchors(newAnchors);
        if (trySnapTo(newTarget)) {
            return;
        }
        setDragTarget(newTarget);
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0001\u0010\u0007*\u00020\u0001J:\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0001\u0010\u0007*\u00020\u00012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\n0\tH\u0007J\u0087\u0001\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0001\u0010\u0007*\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\r0\t2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00152\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\n0\tH\u0007¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/gestures/AnchoredDraggableState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "T", "confirmValueChange", "Lkotlin/Function1;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "decayAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "positionalThreshold", "Lkotlin/ParameterName;", "name", "distance", "velocityThreshold", "Lkotlin/Function0;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Saver Saver$default(Companion companion, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, Function1 function1, Function0 function0, Function1 function2, int i, Object obj) {
            if ((i & 16) != 0) {
                function2 = new Function1() { // from class: v40
                    public final Object invoke(Object obj2) {
                        return Boolean.valueOf(AnchoredDraggableState.Companion.e(obj2));
                    }
                };
            }
            return companion.Saver(animationSpec, decayAnimationSpec, function1, function0, function2);
        }

        public static Object a(SaverScope saverScope, AnchoredDraggableState anchoredDraggableState) {
            return anchoredDraggableState.getCurrentValue();
        }

        public static Object b(SaverScope saverScope, AnchoredDraggableState anchoredDraggableState) {
            return anchoredDraggableState.getCurrentValue();
        }

        public static AnchoredDraggableState c(Object obj) {
            return new AnchoredDraggableState(obj);
        }

        public static Object d(SaverScope saverScope, AnchoredDraggableState anchoredDraggableState) {
            return anchoredDraggableState.getCurrentValue();
        }

        public static boolean e(Object obj) {
            return true;
        }

        public static boolean f(Object obj) {
            return true;
        }

        public static AnchoredDraggableState g(Function1 function1, Object obj) {
            return new AnchoredDraggableState(obj, (Function1<? super Object, Boolean>) function1);
        }

        public static AnchoredDraggableState h(Function1 function1, Function0 function0, AnimationSpec animationSpec, DecayAnimationSpec decayAnimationSpec, Function1 function2, Object obj) {
            return AnchoredDraggableKt.AnchoredDraggableState(obj, function1, function0, animationSpec, decayAnimationSpec, function2);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "This constructor of AnchoredDraggableState has been deprecated. Please pass thresholds and animation specs to AnchoredDraggableDefaults.flingBehavior(..) instead, which can be passed to Modifier.anchoredDraggable.")
        public final <T> Saver<AnchoredDraggableState<T>, T> Saver(final AnimationSpec<Float> snapAnimationSpec, final DecayAnimationSpec<Float> decayAnimationSpec, final Function1<? super Float, Float> positionalThreshold, final Function0<Float> velocityThreshold, final Function1<? super T, Boolean> confirmValueChange) {
            return SaverKt.Saver(new Function2() { // from class: p40
                public final Object invoke(Object obj, Object obj2) {
                    return AnchoredDraggableState.Companion.a((SaverScope) obj, (AnchoredDraggableState) obj2);
                }
            }, new Function1() { // from class: r40
                public final Object invoke(Object obj) {
                    return AnchoredDraggableState.Companion.h(positionalThreshold, velocityThreshold, snapAnimationSpec, decayAnimationSpec, confirmValueChange, obj);
                }
            });
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Saver Saver$default(Companion companion, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = new Function1() { // from class: s40
                    public final Object invoke(Object obj2) {
                        return Boolean.valueOf(AnchoredDraggableState.Companion.f(obj2));
                    }
                };
            }
            return companion.Saver(function1);
        }

        @Deprecated(level = DeprecationLevel.WARNING, message = "confirmValueChange is deprecated without replacement. Rather than relying on a callback to veto state changes, the anchor set should not include disallowed anchors. See androidx.compose.foundation.samples.AnchoredDraggableDynamicAnchorsSample for an example of using dynamic anchors over confirmValueChange.")
        public final <T> Saver<AnchoredDraggableState<T>, T> Saver(final Function1<? super T, Boolean> confirmValueChange) {
            return SaverKt.Saver(new Function2() { // from class: t40
                public final Object invoke(Object obj, Object obj2) {
                    return AnchoredDraggableState.Companion.b((SaverScope) obj, (AnchoredDraggableState) obj2);
                }
            }, new Function1() { // from class: u40
                public final Object invoke(Object obj) {
                    return AnchoredDraggableState.Companion.g(confirmValueChange, obj);
                }
            });
        }

        public final <T> Saver<AnchoredDraggableState<T>, T> Saver() {
            return SaverKt.Saver(new Function2() { // from class: w40
                public final Object invoke(Object obj, Object obj2) {
                    return AnchoredDraggableState.Companion.d((SaverScope) obj, (AnchoredDraggableState) obj2);
                }
            }, new Function1() { // from class: x40
                public final Object invoke(Object obj) {
                    return AnchoredDraggableState.Companion.c(obj);
                }
            });
        }
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, Object obj, MutatePriority mutatePriority, Function4 function4, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(obj, mutatePriority, function4, continuation);
    }

    public final Object settle(AnimationSpec<Float> animationSpec, Continuation<? super Unit> continuation) {
        T currentValue = getCurrentValue();
        T tClosestAnchor = getAnchors().closestAnchor(requireOffset());
        if (tClosestAnchor != null && ((Boolean) this.confirmValueChange.invoke(tClosestAnchor)).booleanValue()) {
            Object objAnimateTo = AnchoredDraggableKt.animateTo(this, tClosestAnchor, animationSpec, continuation);
            return objAnimateTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo : Unit.INSTANCE;
        }
        Object objAnimateTo2 = AnchoredDraggableKt.animateTo(this, currentValue, animationSpec, continuation);
        return objAnimateTo2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo2 : Unit.INSTANCE;
    }

    public AnchoredDraggableState(T t, DraggableAnchors<T> draggableAnchors) {
        this(t);
        setAnchors(draggableAnchors);
        trySnapTo(t);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "confirmValueChange is deprecated without replacement. Rather than relying on a callback to veto state changes, the anchor set should not include disallowed anchors. See androidx.compose.foundation.samples.AnchoredDraggableDynamicAnchorsSample for an example of using dynamic anchors over confirmValueChange.")
    public AnchoredDraggableState(T t, Function1<? super T, Boolean> function1) {
        this(t);
        this.confirmValueChange = function1;
    }

    public /* synthetic */ AnchoredDraggableState(Object obj, DraggableAnchors draggableAnchors, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, draggableAnchors, (i & 4) != 0 ? new Function1() { // from class: l40
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(AnchoredDraggableState.b(obj2));
            }
        } : function1);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "confirmValueChange is deprecated without replacement. Rather than relying on a callback to veto state changes, the anchor set should not include disallowed anchors. See androidx.compose.foundation.samples.AnchoredDraggableDynamicAnchorsSample for an example of using dynamic anchors over confirmValueChange.")
    public AnchoredDraggableState(T t, DraggableAnchors<T> draggableAnchors, Function1<? super T, Boolean> function1) {
        this(t, function1);
        setAnchors(draggableAnchors);
        trySnapTo(t);
    }

    public final Object anchoredDrag(MutatePriority mutatePriority, Function3<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object objMutate = this.dragMutex.mutate(mutatePriority, new AnonymousClass2(this, function3, null), continuation);
        return objMutate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMutate : Unit.INSTANCE;
    }
}
