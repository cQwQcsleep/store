package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u0005\u0012!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00120\u0005¢\u0006\u0004\b\u0016\u0010\u0017JM\u0010\u0018\u001a\u00020\u00122=\u0010\u0019\u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00120\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001aH\u0096@¢\u0006\u0002\u0010\u001fJ\u0017\u0010\r\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000eH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0016J\u008a\u0001\u0010$\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u00052!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00120\u0005J\u0014\u0010%\u001a\u00020\u000e*\u00020\u000eH\u0082\b¢\u0006\u0004\b&\u0010'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00120\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Landroidx/compose/foundation/gestures/Draggable2DNode;", "Landroidx/compose/foundation/gestures/DragGestureNode;", "state", "Landroidx/compose/foundation/gestures/Draggable2DState;", "canDrag", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerType;", "", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "startDragImmediately", "reverseDirection", "onDragStarted", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ParameterName;", "name", "startedPosition", "", "onDragStopped", "Landroidx/compose/ui/unit/Velocity;", "velocity", "<init>", "(Landroidx/compose/foundation/gestures/Draggable2DState;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;ZZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "drag", "forEachDelta", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "dragDelta", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStarted-k-4lQ0M", "(J)V", "event", "Landroidx/compose/foundation/gestures/DragEvent$DragStopped;", "update", "reverseIfNeeded", "reverseIfNeeded-MK-Hz9U", "(J)J", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Draggable2DNode extends DragGestureNode {
    public static final int $stable = 8;
    private Function1<? super Offset, Unit> onDragStarted;
    private Function1<? super Velocity, Unit> onDragStopped;
    private boolean reverseDirection;
    private boolean startDragImmediately;
    private Draggable2DState state;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.Draggable2DNode$drag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Drag2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.Draggable2DNode$drag$2", f = "Draggable2D.kt", i = {}, l = {257}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<Drag2DScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<Function1<? super DragEvent.DragDelta, Unit>, Continuation<? super Unit>, Object> $forEachDelta;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ Draggable2DNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Draggable2DNode draggable2DNode, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$forEachDelta = function2;
            this.this$0 = draggable2DNode;
        }

        public static Unit b(Drag2DScope drag2DScope, Draggable2DNode draggable2DNode, DragEvent.DragDelta dragDelta) {
            long delta = dragDelta.getDelta();
            if (draggable2DNode.reverseDirection) {
                delta = Offset.constructor-impl((-9223372034707292160L) ^ delta);
            }
            drag2DScope.mo541dragByk4lQ0M(delta);
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$forEachDelta, this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(Drag2DScope drag2DScope, Continuation<? super Unit> continuation) {
            return create(drag2DScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final Drag2DScope drag2DScope = (Drag2DScope) this.L$0;
                Function2<Function1<? super DragEvent.DragDelta, Unit>, Continuation<? super Unit>, Object> function2 = this.$forEachDelta;
                final Draggable2DNode draggable2DNode = this.this$0;
                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.u
                    public final Object invoke(Object obj2) {
                        return Draggable2DNode.AnonymousClass2.b(drag2DScope, draggable2DNode, (DragEvent.DragDelta) obj2);
                    }
                };
                this.label = 1;
                if (function2.invoke(function1, this) == coroutine_suspended) {
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

    public Draggable2DNode(Draggable2DState draggable2DState, Function1<? super PointerType, Boolean> function1, boolean z, MutableInteractionSource mutableInteractionSource, boolean z2, boolean z3, Function1<? super Offset, Unit> function2, Function1<? super Velocity, Unit> function3) {
        super(function1, z, mutableInteractionSource, null);
        this.state = draggable2DState;
        this.startDragImmediately = z2;
        this.reverseDirection = z3;
        this.onDragStarted = function2;
        this.onDragStopped = function3;
    }

    /* JADX INFO: renamed from: reverseIfNeeded-MK-Hz9U, reason: not valid java name */
    private final long m584reverseIfNeededMKHz9U(long j) {
        return this.reverseDirection ? Offset.constructor-impl(j ^ (-9223372034707292160L)) : j;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objDrag = this.state.drag(MutatePriority.UserInput, new AnonymousClass2(function2, this, null), continuation);
        return objDrag == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDrag : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M */
    public void mo521onDragStartedk4lQ0M(long startedPosition) {
        this.onDragStarted.invoke(Offset.box-impl(startedPosition));
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public void onDragStopped(DragEvent.DragStopped event) {
        this.onDragStopped.invoke(Velocity.box-impl(event.getVelocity()));
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: startDragImmediately, reason: from getter */
    public boolean getStartDragImmediately() {
        return this.startDragImmediately;
    }

    public final void update(Draggable2DState state, Function1<? super PointerType, Boolean> canDrag, boolean enabled, MutableInteractionSource interactionSource, boolean startDragImmediately, boolean reverseDirection, Function1<? super Offset, Unit> onDragStarted, Function1<? super Velocity, Unit> onDragStopped) {
        boolean z;
        boolean z2 = true;
        if (Intrinsics.areEqual(this.state, state)) {
            z = false;
        } else {
            this.state = state;
            z = true;
        }
        if (this.reverseDirection != reverseDirection) {
            this.reverseDirection = reverseDirection;
        } else {
            z2 = z;
        }
        this.onDragStarted = onDragStarted;
        this.onDragStopped = onDragStopped;
        this.startDragImmediately = startDragImmediately;
        update(canDrag, enabled, interactionSource, null, z2);
    }
}
