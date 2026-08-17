package androidx.compose.foundation.gestures;

import android.view.KeyEvent;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.foundation.FocusedBoundsObserverNode;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.ScrollableNode;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.relocation.BringIntoViewResponderNode;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.focus.FocusTargetModifierNodeKt;
import androidx.compose.ui.focus.Focusability;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollNodeKt;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Velocity;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004BO\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u00105\u001a\u0002062\u0006\u00107\u001a\u00020/H\u0016¢\u0006\u0004\b8\u00109JM\u0010:\u001a\u0002062=\u0010;\u001a9\b\u0001\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110=¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(>\u0012\u0004\u0012\u0002060<\u0012\n\u0012\b\u0012\u0004\u0012\u00020600\u0012\u0006\u0012\u0004\u0018\u0001010(H\u0096@¢\u0006\u0002\u0010?J\u0017\u0010@\u001a\u0002062\u0006\u0010A\u001a\u00020/H\u0016¢\u0006\u0004\bB\u00109J\u0010\u0010C\u001a\u0002062\u0006\u0010D\u001a\u00020EH\u0016J\u0017\u0010F\u001a\u0002062\u0006\u0010G\u001a\u00020HH\u0002¢\u0006\u0004\bI\u00109J\b\u0010J\u001a\u00020\u000eH\u0016J\b\u0010K\u001a\u000206H\u0002JN\u0010L\u001a\u0002062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\b\u0010M\u001a\u000206H\u0016J\b\u0010N\u001a\u000206H\u0002J\b\u0010O\u001a\u000206H\u0016J\u0017\u0010P\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020QH\u0016¢\u0006\u0004\bR\u0010SJ\u0017\u0010T\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020QH\u0016¢\u0006\u0004\bU\u0010SJ'\u0010V\u001a\u0002062\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\H\u0016¢\u0006\u0004\b]\u0010^J\f\u0010_\u001a\u000206*\u00020`H\u0016J\b\u0010a\u001a\u000206H\u0002J\b\u0010b\u001a\u000206H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010'\u001a4\u0012\u0013\u0012\u00110)¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110)¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u000e\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010.\u001a \b\u0001\u0012\u0004\u0012\u00020/\u0012\n\u0012\b\u0012\u0004\u0012\u00020/00\u0012\u0006\u0012\u0004\u0018\u000101\u0018\u00010(X\u0082\u000e¢\u0006\u0004\n\u0002\u00102R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableNode;", "Landroidx/compose/foundation/gestures/DragGestureNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/foundation/gestures/OnScrollChangedDispatcher;", "state", "Landroidx/compose/foundation/gestures/ScrollableState;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "enabled", "", "reverseDirection", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "bringIntoViewSpec", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "<init>", "(Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/OverscrollEffect;Landroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/foundation/gestures/Orientation;ZZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/gestures/BringIntoViewSpec;)V", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "nestedScrollDispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "scrollableContainerNode", "Landroidx/compose/foundation/gestures/ScrollableContainerNode;", "defaultFlingBehavior", "Landroidx/compose/foundation/gestures/ScrollableDefaultFlingBehavior;", "scrollingLogic", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "nestedScrollConnection", "Landroidx/compose/foundation/gestures/ScrollableNestedScrollConnection;", "focusTargetModifierNode", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "contentInViewNode", "Landroidx/compose/foundation/gestures/ContentInViewNode;", "scrollByAction", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "x", "y", "scrollByOffsetAction", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "mouseWheelScrollingLogic", "Landroidx/compose/foundation/gestures/MouseWheelScrollingLogic;", "dispatchScrollDeltaInfo", "", "delta", "dispatchScrollDeltaInfo-k-4lQ0M", "(J)V", "drag", "forEachDelta", "Lkotlin/Function1;", "Landroidx/compose/foundation/gestures/DragEvent$DragDelta;", "dragDelta", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDragStarted", "startedPosition", "onDragStarted-k-4lQ0M", "onDragStopped", "event", "Landroidx/compose/foundation/gestures/DragEvent$DragStopped;", "onWheelScrollStopped", "velocity", "Landroidx/compose/ui/unit/Velocity;", "onWheelScrollStopped-TH1AsA0", "startDragImmediately", "ensureMouseWheelScrollNodeInitialized", "update", "onAttach", "updateDefaultFlingBehavior", "onDensityChange", "onKeyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onPreKeyEvent", "onPreKeyEvent-ZmokQxo", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "setScrollSemanticsActions", "clearScrollSemanticsActions", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ScrollableNode extends DragGestureNode implements KeyInputModifierNode, SemanticsModifierNode, OnScrollChangedDispatcher {
    public static final int $stable = 8;
    private final ContentInViewNode contentInViewNode;
    private final ScrollableDefaultFlingBehavior defaultFlingBehavior;
    private FlingBehavior flingBehavior;
    private final FocusTargetModifierNode focusTargetModifierNode;
    private MouseWheelScrollingLogic mouseWheelScrollingLogic;
    private final ScrollableNestedScrollConnection nestedScrollConnection;
    private final NestedScrollDispatcher nestedScrollDispatcher;
    private OverscrollEffect overscrollEffect;
    private Function2<? super Float, ? super Float, Boolean> scrollByAction;
    private Function2<? super Offset, ? super Continuation<? super Offset>, ? extends Object> scrollByOffsetAction;
    private final ScrollableContainerNode scrollableContainerNode;
    private final ScrollingLogic scrollingLogic;
    private final boolean shouldAutoInvalidate;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$ensureMouseWheelScrollNodeInitialized$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends AdaptedFunctionReference implements Function2<Velocity, Continuation<? super Unit>, Object>, SuspendFunction {
        public AnonymousClass1(Object obj) {
            super(2, obj, ScrollableNode.class, "onWheelScrollStopped", "onWheelScrollStopped-TH1AsA0(J)V", 4);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m690invokesFctU(((Velocity) obj).unbox-impl(), (Continuation) obj2);
        }

        /* JADX INFO: renamed from: invoke-sF-c-tU, reason: not valid java name */
        public final Object m690invokesFctU(long j, Continuation<? super Unit> continuation) {
            return ScrollableNode.ensureMouseWheelScrollNodeInitialized$onWheelScrollStopped((ScrollableNode) ((AdaptedFunctionReference) this).receiver, j, continuation);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$onDragStopped$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableNode$onDragStopped$1", f = "Scrollable.kt", i = {}, l = {395}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DragEvent.DragStopped $event;
        int label;
        final /* synthetic */ ScrollableNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01241(DragEvent.DragStopped dragStopped, ScrollableNode scrollableNode, Continuation<? super C01241> continuation) {
            super(2, continuation);
            this.$event = dragStopped;
            this.this$0 = scrollableNode;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01241(this.$event, this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                float f = this.$event.getIsIndirectPointerEvent() ? -1.0f : 1.0f;
                ScrollingLogic scrollingLogic = this.this$0.scrollingLogic;
                long j = Velocity.times-adjELrA(this.$event.getVelocity(), f);
                this.label = 1;
                if (scrollingLogic.m700onScrollStoppedBMRW4eQ(j, false, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableNode$setScrollSemanticsActions$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "offset"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableNode$setScrollSemanticsActions$2", f = "Scrollable.kt", i = {}, l = {579}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<Offset, Continuation<? super Offset>, Object> {
        /* synthetic */ long J$0;
        int label;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = ScrollableNode.this.new AnonymousClass2(continuation);
            anonymousClass2.J$0 = ((Offset) obj).unbox-impl();
            return anonymousClass2;
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m691invoke3MmeM6k(((Offset) obj).unbox-impl(), (Continuation) obj2);
        }

        /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
        public final Object m691invoke3MmeM6k(long j, Continuation<? super Offset> continuation) {
            return create(Offset.box-impl(j), continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    return obj;
                }
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            long j = this.J$0;
            ScrollingLogic scrollingLogic = ScrollableNode.this.scrollingLogic;
            this.label = 1;
            Object objM684semanticsScrollByd4ec7I = ScrollableKt.m684semanticsScrollByd4ec7I(scrollingLogic, j, this);
            return objM684semanticsScrollByd4ec7I == coroutine_suspended ? coroutine_suspended : objM684semanticsScrollByd4ec7I;
        }
    }

    public ScrollableNode(ScrollableState scrollableState, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, Orientation orientation, boolean z, boolean z2, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec) {
        super(ScrollableKt.getCanDragCalculation(), z, mutableInteractionSource, orientation);
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        NestedScrollDispatcher nestedScrollDispatcher = new NestedScrollDispatcher();
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        this.scrollableContainerNode = delegate(new ScrollableContainerNode(z));
        ScrollableDefaultFlingBehavior scrollableDefaultFlingBehaviorPlatformScrollableDefaultFlingBehavior = Scrollable_androidKt.platformScrollableDefaultFlingBehavior();
        this.defaultFlingBehavior = scrollableDefaultFlingBehaviorPlatformScrollableDefaultFlingBehavior;
        OverscrollEffect overscrollEffect2 = this.overscrollEffect;
        FlingBehavior flingBehavior2 = this.flingBehavior;
        ScrollingLogic scrollingLogic = new ScrollingLogic(scrollableState, overscrollEffect2, flingBehavior2 == null ? scrollableDefaultFlingBehaviorPlatformScrollableDefaultFlingBehavior : flingBehavior2, orientation, z2, nestedScrollDispatcher, this, new Function0() { // from class: pyc
            public final Object invoke() {
                return Boolean.valueOf(ScrollableNode.e(this.b));
            }
        });
        this.scrollingLogic = scrollingLogic;
        ScrollableNestedScrollConnection scrollableNestedScrollConnection = new ScrollableNestedScrollConnection(scrollingLogic, z);
        this.nestedScrollConnection = scrollableNestedScrollConnection;
        this.focusTargetModifierNode = delegate(FocusTargetModifierNodeKt.FocusTargetModifierNode-PYyLHbc$default(Focusability.Companion.getNever-LCbbffg(), (Function2) null, 2, (Object) null));
        ContentInViewNode contentInViewNodeDelegate = delegate(new ContentInViewNode(orientation, scrollingLogic, z2, bringIntoViewSpec, new Function0() { // from class: qyc
            public final Object invoke() {
                return ScrollableNode.b(this.b);
            }
        }));
        this.contentInViewNode = contentInViewNodeDelegate;
        delegate(NestedScrollNodeKt.nestedScrollModifierNode(scrollableNestedScrollConnection, nestedScrollDispatcher));
        delegate(new BringIntoViewResponderNode(contentInViewNodeDelegate));
        if (ComposeFoundationFlags.isKeepInViewFocusObservationChangeEnabled) {
            return;
        }
        delegate(new FocusedBoundsObserverNode(new Function1() { // from class: ryc
            public final Object invoke(Object obj) {
                return ScrollableNode.c(this.b, (LayoutCoordinates) obj);
            }
        }));
    }

    public static Rect b(ScrollableNode scrollableNode) {
        return FocusTargetModifierNodeKt.getFocusedRect(scrollableNode.focusTargetModifierNode);
    }

    public static Unit c(ScrollableNode scrollableNode, LayoutCoordinates layoutCoordinates) {
        scrollableNode.contentInViewNode.onFocusBoundsChanged(layoutCoordinates);
        return Unit.INSTANCE;
    }

    private final void clearScrollSemanticsActions() {
        this.scrollByAction = null;
        this.scrollByOffsetAction = null;
    }

    public static boolean d(ScrollableNode scrollableNode, float f, float f2) {
        BuildersKt.launch$default(scrollableNode.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new ScrollableNode$setScrollSemanticsActions$1$1(scrollableNode, f, f2, null), 3, (Object) null);
        return true;
    }

    public static boolean e(ScrollableNode scrollableNode) {
        return scrollableNode.isAttached();
    }

    private final void ensureMouseWheelScrollNodeInitialized() {
        if (this.mouseWheelScrollingLogic == null) {
            this.mouseWheelScrollingLogic = new MouseWheelScrollingLogic(this.scrollingLogic, AndroidScrollable_androidKt.platformScrollConfig(this), new AnonymousClass1(this), DelegatableNodeKt.requireDensity(this));
        }
        MouseWheelScrollingLogic mouseWheelScrollingLogic = this.mouseWheelScrollingLogic;
        if (mouseWheelScrollingLogic != null) {
            mouseWheelScrollingLogic.startReceivingMouseWheelEvents(getCoroutineScope());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object ensureMouseWheelScrollNodeInitialized$onWheelScrollStopped(ScrollableNode scrollableNode, long j, Continuation continuation) {
        scrollableNode.m687onWheelScrollStoppedTH1AsA0(j);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: onWheelScrollStopped-TH1AsA0, reason: not valid java name */
    private final void m687onWheelScrollStoppedTH1AsA0(long velocity) {
        BuildersKt.launch$default(this.nestedScrollDispatcher.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new ScrollableNode$onWheelScrollStopped$1(this, velocity, null), 3, (Object) null);
    }

    private final void setScrollSemanticsActions() {
        this.scrollByAction = new Function2() { // from class: syc
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ScrollableNode.d(this.b, ((Float) obj).floatValue(), ((Float) obj2).floatValue()));
            }
        };
        this.scrollByOffsetAction = new AnonymousClass2(null);
    }

    private final void updateDefaultFlingBehavior() {
        if (isAttached()) {
            this.defaultFlingBehavior.updateDensity(DelegatableNodeKt.requireDensity(this));
        }
    }

    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (getEnabled() && (this.scrollByAction == null || this.scrollByOffsetAction == null)) {
            setScrollSemanticsActions();
        }
        Function2<? super Float, ? super Float, Boolean> function2 = this.scrollByAction;
        if (function2 != null) {
            SemanticsPropertiesKt.scrollBy$default(semanticsPropertyReceiver, (String) null, function2, 1, (Object) null);
        }
        Function2<? super Offset, ? super Continuation<? super Offset>, ? extends Object> function3 = this.scrollByOffsetAction;
        if (function3 != null) {
            SemanticsPropertiesKt.scrollByOffset(semanticsPropertyReceiver, function3);
        }
    }

    @Override // androidx.compose.foundation.gestures.OnScrollChangedDispatcher
    /* JADX INFO: renamed from: dispatchScrollDeltaInfo-k-4lQ0M */
    public void mo632dispatchScrollDeltaInfok4lQ0M(long delta) {
        if (isAttached()) {
            DelegatableNodeKt.dispatchOnScrollChanged-Uv8p0NA(this, delta);
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public Object drag(Function2<? super Function1<? super DragEvent.DragDelta, Unit>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        ScrollingLogic scrollingLogic = this.scrollingLogic;
        Object objScroll = scrollingLogic.scroll(MutatePriority.UserInput, new ScrollableNode$drag$2$1(function2, scrollingLogic, null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    public boolean getShouldAutoInvalidate() {
        return this.shouldAutoInvalidate;
    }

    public void onAttach() {
        updateDefaultFlingBehavior();
        MouseWheelScrollingLogic mouseWheelScrollingLogic = this.mouseWheelScrollingLogic;
        if (mouseWheelScrollingLogic != null) {
            mouseWheelScrollingLogic.updateDensity(DelegatableNodeKt.requireDensity(this));
        }
    }

    public void onDensityChange() {
        onCancelPointerInput();
        updateDefaultFlingBehavior();
        MouseWheelScrollingLogic mouseWheelScrollingLogic = this.mouseWheelScrollingLogic;
        if (mouseWheelScrollingLogic != null) {
            mouseWheelScrollingLogic.updateDensity(DelegatableNodeKt.requireDensity(this));
        }
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: onDragStarted-k-4lQ0M */
    public void mo521onDragStartedk4lQ0M(long startedPosition) {
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    public void onDragStopped(DragEvent.DragStopped event) {
        BuildersKt.launch$default(this.nestedScrollDispatcher.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new C01241(event, this, null), 3, (Object) null);
    }

    /* JADX INFO: renamed from: onKeyEvent-ZmokQxo, reason: not valid java name */
    public boolean m688onKeyEventZmokQxo(KeyEvent event) {
        long j;
        if (!getEnabled()) {
            return false;
        }
        long j2 = KeyEvent_androidKt.getKey-ZmokQxo(event);
        Key.Companion companion = Key.Companion;
        if ((!Key.equals-impl0(j2, companion.getPageDown-EK5gGoQ()) && !Key.equals-impl0(KeyEvent_androidKt.getKey-ZmokQxo(event), companion.getPageUp-EK5gGoQ())) || !KeyEventType.equals-impl0(KeyEvent_androidKt.getType-ZmokQxo(event), KeyEventType.Companion.getKeyDown-CS__XNY()) || KeyEvent_androidKt.isCtrlPressed-ZmokQxo(event)) {
            return false;
        }
        boolean zIsVertical = this.scrollingLogic.isVertical();
        ContentInViewNode contentInViewNode = this.contentInViewNode;
        if (zIsVertical) {
            int iM538getViewportSizeYbymL2g$foundation = (int) (contentInViewNode.getViewportSize() & 4294967295L);
            j = Offset.constructor-impl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(Key.equals-impl0(KeyEvent_androidKt.getKey-ZmokQxo(event), companion.getPageUp-EK5gGoQ()) ? iM538getViewportSizeYbymL2g$foundation : -iM538getViewportSizeYbymL2g$foundation)) & 4294967295L));
        } else {
            int iM538getViewportSizeYbymL2g$foundation2 = (int) (contentInViewNode.getViewportSize() >> 32);
            j = Offset.constructor-impl((((long) Float.floatToRawIntBits(Key.equals-impl0(KeyEvent_androidKt.getKey-ZmokQxo(event), companion.getPageUp-EK5gGoQ()) ? iM538getViewportSizeYbymL2g$foundation2 : -iM538getViewportSizeYbymL2g$foundation2)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
        }
        BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new ScrollableNode$onKeyEvent$1(this, j, null), 3, (Object) null);
        return true;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo582onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            if (((Boolean) getCanDrag().invoke(PointerType.box-impl(((PointerInputChange) changes.get(i)).getType-T8wyACA()))).booleanValue()) {
                super.mo582onPointerEventH0pRuoY(pointerEvent, pass, bounds);
                break;
            }
        }
        if (getEnabled()) {
            if (pass == PointerEventPass.Initial && PointerEventType.equals-impl0(pointerEvent.getType-7fucELk(), PointerEventType.Companion.getScroll-7fucELk())) {
                ensureMouseWheelScrollNodeInitialized();
            }
            MouseWheelScrollingLogic mouseWheelScrollingLogic = this.mouseWheelScrollingLogic;
            if (mouseWheelScrollingLogic != null) {
                mouseWheelScrollingLogic.m624onPointerEventH0pRuoY(pointerEvent, pass, bounds);
            }
        }
    }

    /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo, reason: not valid java name */
    public boolean m689onPreKeyEventZmokQxo(KeyEvent event) {
        return false;
    }

    @Override // androidx.compose.foundation.gestures.DragGestureNode
    /* JADX INFO: renamed from: startDragImmediately */
    public boolean getStartDragImmediately() {
        return this.scrollingLogic.shouldScrollImmediately();
    }

    public final void update(ScrollableState state, Orientation orientation, OverscrollEffect overscrollEffect, boolean enabled, boolean reverseDirection, FlingBehavior flingBehavior, MutableInteractionSource interactionSource, BringIntoViewSpec bringIntoViewSpec) {
        boolean z;
        if (getEnabled() != enabled) {
            this.nestedScrollConnection.setEnabled(enabled);
            this.scrollableContainerNode.update(enabled);
            z = true;
        } else {
            z = false;
        }
        boolean z2 = z;
        boolean zUpdate = this.scrollingLogic.update(state, orientation, overscrollEffect, reverseDirection, flingBehavior == null ? this.defaultFlingBehavior : flingBehavior, this.nestedScrollDispatcher);
        this.contentInViewNode.update(orientation, reverseDirection, bringIntoViewSpec);
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        update(ScrollableKt.getCanDragCalculation(), enabled, interactionSource, this.scrollingLogic.isVertical() ? Orientation.Vertical : Orientation.Horizontal, zUpdate);
        if (z2) {
            clearScrollSemanticsActions();
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
    }
}
