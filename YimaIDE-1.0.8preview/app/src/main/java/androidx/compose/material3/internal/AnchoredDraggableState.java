package androidx.compose.material3.internal;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Pair;
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

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u0000 i*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001iBy\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b\u0012\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\r0\u000b\u0012#\b\u0002\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u0004\b\u0011\u0010\u0012B\u0089\u0001\b\u0016\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b\u0012\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\r0\u000b\u0012#\b\u0002\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u0004\b\u0011\u0010\u0015J\u0006\u00108\u001a\u00020\u0006J#\u0010K\u001a\u00020L2\f\u0010M\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\b\b\u0002\u0010N\u001a\u00028\u0000¢\u0006\u0002\u0010OJ\u0016\u0010P\u001a\u00020L2\u0006\u0010Q\u001a\u00020\u0006H\u0086@¢\u0006\u0002\u0010RJ%\u0010S\u001a\u00028\u00002\u0006\u00101\u001a\u00020\u00062\u0006\u0010#\u001a\u00028\u00002\u0006\u0010Q\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010TJ\u001d\u0010U\u001a\u00028\u00002\u0006\u00101\u001a\u00020\u00062\u0006\u0010#\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010VJ\\\u0010Y\u001a\u00020L2\b\b\u0002\u0010Z\u001a\u00020[2B\u0010\\\u001a>\b\u0001\u0012\u0004\u0012\u00020X\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0^\u0012\u0006\u0012\u0004\u0018\u00010\u00020]¢\u0006\u0002\b_H\u0086@¢\u0006\u0002\u0010`Jy\u0010Y\u001a\u00020L2\u0006\u0010*\u001a\u00028\u00002\b\b\u0002\u0010Z\u001a\u00020[2W\u0010\\\u001aS\b\u0001\u0012\u0004\u0012\u00020X\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0014¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(*\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0^\u0012\u0006\u0012\u0004\u0018\u00010\u00020a¢\u0006\u0002\b_H\u0086@¢\u0006\u0002\u0010bJ\u0015\u0010c\u001a\u00020\u00062\u0006\u0010d\u001a\u00020\u0006H\u0000¢\u0006\u0002\beJ\u000e\u0010f\u001a\u00020\u00062\u0006\u0010d\u001a\u00020\u0006J\u0015\u0010g\u001a\u00020\u00102\u0006\u0010*\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010hR/\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\r0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R/\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u001fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R+\u0010#\u001a\u00028\u00002\u0006\u0010\"\u001a\u00028\u00008F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001b\u0010*\u001a\u00028\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b+\u0010%R\u001b\u0010.\u001a\u00028\u00008@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b0\u0010-\u001a\u0004\b/\u0010%R+\u00101\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00068F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0011\u00109\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b9\u0010:R\u001b\u0010;\u001a\u00020\u00068GX\u0086\u0084\u0002¢\u0006\f\n\u0004\b=\u0010-\u001a\u0004\b<\u00103R+\u0010>\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00068F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bA\u00107\u001a\u0004\b?\u00103\"\u0004\b@\u00105R/\u0010B\u001a\u0004\u0018\u00018\u00002\b\u0010\"\u001a\u0004\u0018\u00018\u00008B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010)\u001a\u0004\bC\u0010%\"\u0004\bD\u0010'R7\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u00148F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bJ\u0010)\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u000e\u0010W\u001a\u00020XX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Landroidx/compose/material3/internal/AnchoredDraggableState;", "T", "", "initialValue", "positionalThreshold", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "totalDistance", "velocityThreshold", "Lkotlin/Function0;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "confirmValueChange", "newValue", "", "<init>", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "anchors", "Landroidx/compose/material3/internal/DraggableAnchors;", "(Ljava/lang/Object;Landroidx/compose/material3/internal/DraggableAnchors;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "getPositionalThreshold$material3", "()Lkotlin/jvm/functions/Function1;", "getVelocityThreshold$material3", "()Lkotlin/jvm/functions/Function0;", "getAnimationSpec", "getConfirmValueChange$material3", "dragMutex", "Landroidx/compose/material3/internal/InternalMutatorMutex;", "draggableState", "Landroidx/compose/foundation/gestures/DraggableState;", "getDraggableState$material3", "()Landroidx/compose/foundation/gestures/DraggableState;", "<set-?>", "currentValue", "getCurrentValue", "()Ljava/lang/Object;", "setCurrentValue", "(Ljava/lang/Object;)V", "currentValue$delegate", "Landroidx/compose/runtime/MutableState;", "targetValue", "getTargetValue", "targetValue$delegate", "Landroidx/compose/runtime/State;", "closestValue", "getClosestValue$material3", "closestValue$delegate", "offset", "getOffset", "()F", "setOffset", "(F)V", "offset$delegate", "Landroidx/compose/runtime/MutableFloatState;", "requireOffset", "isAnimationRunning", "()Z", NotificationCompat.CATEGORY_PROGRESS, "getProgress", "progress$delegate", "lastVelocity", "getLastVelocity", "setLastVelocity", "lastVelocity$delegate", "dragTarget", "getDragTarget", "setDragTarget", "dragTarget$delegate", "getAnchors", "()Landroidx/compose/material3/internal/DraggableAnchors;", "setAnchors", "(Landroidx/compose/material3/internal/DraggableAnchors;)V", "anchors$delegate", "updateAnchors", "", "newAnchors", "newTarget", "(Landroidx/compose/material3/internal/DraggableAnchors;Ljava/lang/Object;)V", "settle", "velocity", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeTarget", "(FLjava/lang/Object;F)Ljava/lang/Object;", "computeTargetWithoutThresholds", "(FLjava/lang/Object;)Ljava/lang/Object;", "anchoredDragScope", "Landroidx/compose/material3/internal/AnchoredDragScope;", "anchoredDrag", "dragPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function4;", "(Ljava/lang/Object;Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function4;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newOffsetForDelta", "delta", "newOffsetForDelta$material3", "dispatchRawDelta", "trySnapTo", "(Ljava/lang/Object;)Z", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AnchoredDraggableState<T> {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AnchoredDragScope anchoredDragScope;

    /* JADX INFO: renamed from: anchors$delegate, reason: from kotlin metadata */
    private final MutableState anchors;
    private final Function0<AnimationSpec<Float>> animationSpec;

    /* JADX INFO: renamed from: closestValue$delegate, reason: from kotlin metadata */
    private final State closestValue;
    private final Function1<T, Boolean> confirmValueChange;

    /* JADX INFO: renamed from: currentValue$delegate, reason: from kotlin metadata */
    private final MutableState currentValue;
    private final InternalMutatorMutex dragMutex;

    /* JADX INFO: renamed from: dragTarget$delegate, reason: from kotlin metadata */
    private final MutableState dragTarget;
    private final DraggableState draggableState;

    /* JADX INFO: renamed from: lastVelocity$delegate, reason: from kotlin metadata */
    private final MutableFloatState lastVelocity;

    /* JADX INFO: renamed from: offset$delegate, reason: from kotlin metadata */
    private final MutableFloatState offset;
    private final Function1<Float, Float> positionalThreshold;

    /* JADX INFO: renamed from: progress$delegate, reason: from kotlin metadata */
    private final State progress;

    /* JADX INFO: renamed from: targetValue$delegate, reason: from kotlin metadata */
    private final State targetValue;
    private final Function0<Float> velocityThreshold;

    /* JADX INFO: renamed from: androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.internal.AnchoredDraggableState", f = "AnchoredDraggable.kt", i = {}, l = {517}, m = "anchoredDrag", n = {}, s = {})
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AnchoredDraggableState<T> anchoredDraggableState, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = anchoredDraggableState;
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.anchoredDrag(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$2", f = "AnchoredDraggable.kt", i = {}, l = {518}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<AnchoredDragScope, DraggableAnchors<T>, Continuation<? super Unit>, Object> $block;
        int label;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX INFO: renamed from: androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "T", "latestAnchors", "Landroidx/compose/material3/internal/DraggableAnchors;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$2$2", f = "AnchoredDraggable.kt", i = {}, l = {519}, m = "invokeSuspend", n = {}, s = {})
        public static final class C00162 extends SuspendLambda implements Function2<DraggableAnchors<T>, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function3<AnchoredDragScope, DraggableAnchors<T>, Continuation<? super Unit>, Object> $block;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C00162(Function3<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super Continuation<? super Unit>, ? extends Object> function3, AnchoredDraggableState<T> anchoredDraggableState, Continuation<? super C00162> continuation) {
                super(2, continuation);
                this.$block = function3;
                this.this$0 = anchoredDraggableState;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00162 c00162 = new C00162(this.$block, this.this$0, continuation);
                c00162.L$0 = obj;
                return c00162;
            }

            public final Object invoke(DraggableAnchors<T> draggableAnchors, Continuation<? super Unit> continuation) {
                return create(draggableAnchors, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DraggableAnchors<T> draggableAnchors = (DraggableAnchors) this.L$0;
                    Function3<AnchoredDragScope, DraggableAnchors<T>, Continuation<? super Unit>, Object> function3 = this.$block;
                    AnchoredDragScope anchoredDragScope = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
                    this.label = 1;
                    if (function3.invoke(anchoredDragScope, draggableAnchors, this) == coroutine_suspended) {
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
                Function0 function0 = new Function0() { // from class: androidx.compose.material3.internal.d
                    public final Object invoke() {
                        return AnchoredDraggableState.AnonymousClass2.b(anchoredDraggableState);
                    }
                };
                C00162 c00162 = new C00162(this.$block, this.this$0, null);
                this.label = 1;
                if (AnchoredDraggableKt.restartable(function0, c00162, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.internal.AnchoredDraggableState", f = "AnchoredDraggable.kt", i = {}, l = {564}, m = "anchoredDrag", n = {}, s = {})
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

    /* JADX INFO: renamed from: androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$4", f = "AnchoredDraggable.kt", i = {}, l = {566}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass4 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Function4<AnchoredDragScope, DraggableAnchors<T>, T, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ T $targetValue;
        int label;
        final /* synthetic */ AnchoredDraggableState<T> this$0;

        /* JADX INFO: renamed from: androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$4$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0018\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0004\u0012\u0002H\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "T", "<destruct>", "Lkotlin/Pair;", "Landroidx/compose/material3/internal/DraggableAnchors;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.material3.internal.AnchoredDraggableState$anchoredDrag$4$2", f = "AnchoredDraggable.kt", i = {}, l = {568}, m = "invokeSuspend", n = {}, s = {})
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
                    AnchoredDragScope anchoredDragScope = ((AnchoredDraggableState) this.this$0).anchoredDragScope;
                    this.label = 1;
                    if (function4.invoke(anchoredDragScope, draggableAnchors, objComponent2, this) == coroutine_suspended) {
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
                Function0 function0 = new Function0() { // from class: androidx.compose.material3.internal.e
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
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AnchoredDraggableState(T t, Function1<? super Float, Float> function1, Function0<Float> function0, Function0<? extends AnimationSpec<Float>> function2, Function1<? super T, Boolean> function3) {
        this.positionalThreshold = function1;
        this.velocityThreshold = function0;
        this.animationSpec = function2;
        this.confirmValueChange = function3;
        this.dragMutex = new InternalMutatorMutex();
        this.draggableState = new AnchoredDraggableState$draggableState$1(this);
        this.currentValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
        this.targetValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: i40
            public final Object invoke() {
                return AnchoredDraggableState.a(this.b);
            }
        });
        this.closestValue = SnapshotStateKt.derivedStateOf(new Function0() { // from class: k40
            public final Object invoke() {
                return AnchoredDraggableState.f(this.b);
            }
        });
        this.offset = PrimitiveSnapshotStateKt.mutableFloatStateOf(Float.NaN);
        this.progress = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: m40
            public final Object invoke() {
                return Float.valueOf(AnchoredDraggableState.c(this.b));
            }
        });
        this.lastVelocity = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragTarget = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.anchors = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AnchoredDraggableKt.emptyDraggableAnchors(), null, 2, null);
        this.anchoredDragScope = new AnchoredDragScope(this) { // from class: androidx.compose.material3.internal.AnchoredDraggableState$anchoredDragScope$1
            final /* synthetic */ AnchoredDraggableState<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // androidx.compose.material3.internal.AnchoredDragScope
            public void dragTo(float newOffset, float lastKnownVelocity) {
                this.this$0.setOffset(newOffset);
                this.this$0.setLastVelocity(lastKnownVelocity);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object a(AnchoredDraggableState anchoredDraggableState) {
        Object dragTarget = anchoredDraggableState.getDragTarget();
        if (dragTarget != null) {
            return dragTarget;
        }
        float offset = anchoredDraggableState.getOffset();
        return !Float.isNaN(offset) ? anchoredDraggableState.computeTarget(offset, anchoredDraggableState.getCurrentValue(), 0.0f) : anchoredDraggableState.getCurrentValue();
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

    /* JADX WARN: Multi-variable type inference failed */
    public static float c(AnchoredDraggableState anchoredDraggableState) {
        float fPositionOf = anchoredDraggableState.getAnchors().positionOf(anchoredDraggableState.getCurrentValue());
        float fPositionOf2 = anchoredDraggableState.getAnchors().positionOf(anchoredDraggableState.getClosestValue$material3()) - fPositionOf;
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

    private final T computeTarget(float offset, T currentValue, float velocity) {
        DraggableAnchors<T> anchors = getAnchors();
        float fPositionOf = anchors.positionOf(currentValue);
        float fFloatValue = ((Number) this.velocityThreshold.invoke()).floatValue();
        if (fPositionOf != offset && !Float.isNaN(fPositionOf)) {
            if (fPositionOf < offset) {
                if (velocity >= fFloatValue) {
                    T tClosestAnchor = anchors.closestAnchor(offset, true);
                    tClosestAnchor.getClass();
                    return tClosestAnchor;
                }
                T tClosestAnchor2 = anchors.closestAnchor(offset, true);
                tClosestAnchor2.getClass();
                if (offset >= Math.abs(fPositionOf + Math.abs(((Number) this.positionalThreshold.invoke(Float.valueOf(Math.abs(anchors.positionOf(tClosestAnchor2) - fPositionOf)))).floatValue()))) {
                    return tClosestAnchor2;
                }
            } else {
                if (velocity <= (-fFloatValue)) {
                    T tClosestAnchor3 = anchors.closestAnchor(offset, false);
                    tClosestAnchor3.getClass();
                    return tClosestAnchor3;
                }
                T tClosestAnchor4 = anchors.closestAnchor(offset, false);
                tClosestAnchor4.getClass();
                float fAbs = Math.abs(fPositionOf - Math.abs(((Number) this.positionalThreshold.invoke(Float.valueOf(Math.abs(fPositionOf - anchors.positionOf(tClosestAnchor4))))).floatValue()));
                if (offset >= 0.0f ? offset <= fAbs : Math.abs(offset) >= fAbs) {
                    return tClosestAnchor4;
                }
            }
        }
        return currentValue;
    }

    private final T computeTargetWithoutThresholds(float offset, T currentValue) {
        DraggableAnchors<T> anchors = getAnchors();
        float fPositionOf = anchors.positionOf(currentValue);
        if (fPositionOf != offset && !Float.isNaN(fPositionOf)) {
            if (fPositionOf < offset) {
                T tClosestAnchor = anchors.closestAnchor(offset, true);
                if (tClosestAnchor != null) {
                    return tClosestAnchor;
                }
            } else {
                T tClosestAnchor2 = anchors.closestAnchor(offset, false);
                if (tClosestAnchor2 != null) {
                    return tClosestAnchor2;
                }
            }
        }
        return currentValue;
    }

    public static boolean d(Object obj) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit e(AnchoredDraggableState anchoredDraggableState, Object obj) {
        AnchoredDragScope anchoredDragScope = anchoredDraggableState.anchoredDragScope;
        float fPositionOf = anchoredDraggableState.getAnchors().positionOf(obj);
        if (!Float.isNaN(fPositionOf)) {
            AnchoredDragScope.dragTo$default(anchoredDragScope, fPositionOf, 0.0f, 2, null);
            anchoredDraggableState.setDragTarget(null);
        }
        anchoredDraggableState.setCurrentValue(obj);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Object f(AnchoredDraggableState anchoredDraggableState) {
        Object dragTarget = anchoredDraggableState.getDragTarget();
        if (dragTarget != null) {
            return dragTarget;
        }
        float offset = anchoredDraggableState.getOffset();
        return !Float.isNaN(offset) ? anchoredDraggableState.computeTargetWithoutThresholds(offset, anchoredDraggableState.getCurrentValue()) : anchoredDraggableState.getCurrentValue();
    }

    private final T getDragTarget() {
        return this.dragTarget.getValue();
    }

    private final void setAnchors(DraggableAnchors<T> draggableAnchors) {
        this.anchors.setValue(draggableAnchors);
    }

    private final void setCurrentValue(T t) {
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

    private final boolean trySnapTo(final T targetValue) {
        return this.dragMutex.tryMutate(new Function0() { // from class: n40
            public final Object invoke() {
                return AnchoredDraggableState.e(this.b, targetValue);
            }
        });
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
                if (getAnchors().hasAnchorFor(t)) {
                    InternalMutatorMutex internalMutatorMutex = this.dragMutex;
                    AnonymousClass4 anonymousClass4 = new AnonymousClass4(this, t, function4, null);
                    anonymousClass3.label = 1;
                    if (internalMutatorMutex.mutate(mutatePriority, anonymousClass4, anonymousClass3) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
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
            T tClosestAnchor = getAnchors().closestAnchor(getOffset());
            if (tClosestAnchor != null && Math.abs(getOffset() - getAnchors().positionOf(tClosestAnchor)) <= 0.5f && ((Boolean) this.confirmValueChange.invoke(tClosestAnchor)).booleanValue()) {
                setCurrentValue(tClosestAnchor);
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            setDragTarget(null);
            T tClosestAnchor2 = getAnchors().closestAnchor(getOffset());
            if (tClosestAnchor2 != null && Math.abs(getOffset() - getAnchors().positionOf(tClosestAnchor2)) <= 0.5f && ((Boolean) this.confirmValueChange.invoke(tClosestAnchor2)).booleanValue()) {
                setCurrentValue(tClosestAnchor2);
            }
            throw th;
        }
    }

    public final float dispatchRawDelta(float delta) {
        float fNewOffsetForDelta$material3 = newOffsetForDelta$material3(delta);
        float offset = Float.isNaN(getOffset()) ? 0.0f : getOffset();
        setOffset(fNewOffsetForDelta$material3);
        return fNewOffsetForDelta$material3 - offset;
    }

    public final DraggableAnchors<T> getAnchors() {
        return (DraggableAnchors) this.anchors.getValue();
    }

    public final Function0<AnimationSpec<Float>> getAnimationSpec() {
        return this.animationSpec;
    }

    public final T getClosestValue$material3() {
        return (T) this.closestValue.getValue();
    }

    public final Function1<T, Boolean> getConfirmValueChange$material3() {
        return this.confirmValueChange;
    }

    public final T getCurrentValue() {
        return this.currentValue.getValue();
    }

    /* JADX INFO: renamed from: getDraggableState$material3, reason: from getter */
    public final DraggableState getDraggableState() {
        return this.draggableState;
    }

    public final float getLastVelocity() {
        return this.lastVelocity.getFloatValue();
    }

    public final float getOffset() {
        return this.offset.getFloatValue();
    }

    public final Function1<Float, Float> getPositionalThreshold$material3() {
        return this.positionalThreshold;
    }

    public final float getProgress() {
        return ((Number) this.progress.getValue()).floatValue();
    }

    public final T getTargetValue() {
        return (T) this.targetValue.getValue();
    }

    public final Function0<Float> getVelocityThreshold$material3() {
        return this.velocityThreshold;
    }

    public final boolean isAnimationRunning() {
        return getDragTarget() != null;
    }

    public final float newOffsetForDelta$material3(float delta) {
        return RangesKt.coerceIn((Float.isNaN(getOffset()) ? 0.0f : getOffset()) + delta, getAnchors().minAnchor(), getAnchors().maxAnchor());
    }

    public final float requireOffset() {
        if (!Float.isNaN(getOffset())) {
            return getOffset();
        }
        k2d.a("The offset was read before being initialized. Did you access the offset in a phase before layout, like effects or composition?");
        return 0.0f;
    }

    public final Object settle(float f, Continuation<? super Unit> continuation) {
        T currentValue = getCurrentValue();
        T tComputeTarget = computeTarget(requireOffset(), currentValue, f);
        if (((Boolean) this.confirmValueChange.invoke(tComputeTarget)).booleanValue()) {
            Object objAnimateTo = AnchoredDraggableKt.animateTo(this, tComputeTarget, f, continuation);
            return objAnimateTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo : Unit.INSTANCE;
        }
        Object objAnimateTo2 = AnchoredDraggableKt.animateTo(this, currentValue, f, continuation);
        return objAnimateTo2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo2 : Unit.INSTANCE;
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

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J{\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0001\u0010\u0007*\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u000e0\r2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b0\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\t¨\u0006\u0014"}, d2 = {"Landroidx/compose/material3/internal/AnchoredDraggableState$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/internal/AnchoredDraggableState;", "T", "animationSpec", "Lkotlin/Function0;", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "Lkotlin/Function1;", "", "positionalThreshold", "Lkotlin/ParameterName;", "name", "distance", "velocityThreshold", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static Object a(SaverScope saverScope, AnchoredDraggableState anchoredDraggableState) {
            return anchoredDraggableState.getCurrentValue();
        }

        public static AnchoredDraggableState b(Function1 function1, Function0 function0, Function0 function2, Function1 function3, Object obj) {
            return new AnchoredDraggableState(obj, function1, function0, function2, function3);
        }

        public final <T> Saver<AnchoredDraggableState<T>, T> Saver(final Function0<? extends AnimationSpec<Float>> animationSpec, final Function1<? super T, Boolean> confirmValueChange, final Function1<? super Float, Float> positionalThreshold, final Function0<Float> velocityThreshold) {
            return SaverKt.Saver(new Function2() { // from class: o40
                public final Object invoke(Object obj, Object obj2) {
                    return AnchoredDraggableState.Companion.a((SaverScope) obj, (AnchoredDraggableState) obj2);
                }
            }, new Function1() { // from class: q40
                public final Object invoke(Object obj) {
                    return AnchoredDraggableState.Companion.b(positionalThreshold, velocityThreshold, animationSpec, confirmValueChange, obj);
                }
            });
        }

        private Companion() {
        }
    }

    public static /* synthetic */ Object anchoredDrag$default(AnchoredDraggableState anchoredDraggableState, Object obj, MutatePriority mutatePriority, Function4 function4, Continuation continuation, int i, Object obj2) {
        if ((i & 2) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return anchoredDraggableState.anchoredDrag(obj, mutatePriority, function4, continuation);
    }

    public /* synthetic */ AnchoredDraggableState(Object obj, Function1 function1, Function0 function0, Function0 function2, Function1 function3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, function1, function0, function2, (i & 16) != 0 ? new Function1() { // from class: e40
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(AnchoredDraggableState.b(obj2));
            }
        } : function3);
    }

    public /* synthetic */ AnchoredDraggableState(Object obj, DraggableAnchors draggableAnchors, Function1 function1, Function0 function0, Function0 function2, Function1 function3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, draggableAnchors, function1, function0, function2, (i & 32) != 0 ? new Function1() { // from class: g40
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(AnchoredDraggableState.d(obj2));
            }
        } : function3);
    }

    public AnchoredDraggableState(T t, DraggableAnchors<T> draggableAnchors, Function1<? super Float, Float> function1, Function0<Float> function0, Function0<? extends AnimationSpec<Float>> function2, Function1<? super T, Boolean> function3) {
        this(t, function1, function0, function2, function3);
        setAnchors(draggableAnchors);
        trySnapTo(t);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object anchoredDrag(MutatePriority mutatePriority, Function3<? super AnchoredDragScope, ? super DraggableAnchors<T>, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
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
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                InternalMutatorMutex internalMutatorMutex = this.dragMutex;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this, function3, null);
                anonymousClass1.label = 1;
                if (internalMutatorMutex.mutate(mutatePriority, anonymousClass2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            T tClosestAnchor = getAnchors().closestAnchor(getOffset());
            if (tClosestAnchor != null && Math.abs(getOffset() - getAnchors().positionOf(tClosestAnchor)) <= 0.5f && ((Boolean) this.confirmValueChange.invoke(tClosestAnchor)).booleanValue()) {
                setCurrentValue(tClosestAnchor);
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            T tClosestAnchor2 = getAnchors().closestAnchor(getOffset());
            if (tClosestAnchor2 != null && Math.abs(getOffset() - getAnchors().positionOf(tClosestAnchor2)) <= 0.5f && ((Boolean) this.confirmValueChange.invoke(tClosestAnchor2)).booleanValue()) {
                setCurrentValue(tClosestAnchor2);
            }
            throw th;
        }
    }
}
