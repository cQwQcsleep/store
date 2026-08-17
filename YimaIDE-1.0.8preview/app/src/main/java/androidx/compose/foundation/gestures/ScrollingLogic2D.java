package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000}\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u001c\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ#\u0010!\u001a\u00020 *\u00020\u001a2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0017H\u0002¢\u0006\u0004\b$\u0010%J\u0015\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020 ¢\u0006\u0004\b(\u0010)J\u0015\u0010&\u001a\u00020\f2\u0006\u0010*\u001a\u00020+¢\u0006\u0004\b,\u0010)J\u0017\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020 H\u0016¢\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020 2\u0006\u0010.\u001a\u00020 H\u0002¢\u0006\u0004\b2\u00100J\u0018\u00103\u001a\u0002042\u0006\u00105\u001a\u00020+H\u0086@¢\u0006\u0004\b6\u00107J\u0018\u00108\u001a\u00020+2\u0006\u00109\u001a\u00020+H\u0096@¢\u0006\u0004\b:\u00107J\u0006\u0010;\u001a\u00020\fJA\u0010.\u001a\u0002042\b\b\u0002\u0010<\u001a\u00020=2'\u0010>\u001a#\b\u0001\u0012\u0004\u0012\u00020@\u0012\n\u0012\b\u0012\u0004\u0012\u0002040A\u0012\u0006\u0012\u0004\u0018\u00010B0?¢\u0006\u0002\bCH\u0086@¢\u0006\u0002\u0010DJ(\u0010E\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollingLogic2D;", "Landroidx/compose/foundation/gestures/ScrollLogic;", "scrollableState", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "nestedScrollDispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "isScrollableNodeAttached", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/gestures/Scrollable2DState;Landroidx/compose/foundation/OverscrollEffect;Landroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;Lkotlin/jvm/functions/Function0;)V", "getScrollableState", "()Landroidx/compose/foundation/gestures/Scrollable2DState;", "setScrollableState", "(Landroidx/compose/foundation/gestures/Scrollable2DState;)V", "value", "isFlinging", "()Z", "latestScrollSource", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "I", "outerStateScope", "Landroidx/compose/foundation/gestures/Scroll2DScope;", "nestedScrollScope", "androidx/compose/foundation/gestures/ScrollingLogic2D$nestedScrollScope$1", "Landroidx/compose/foundation/gestures/ScrollingLogic2D$nestedScrollScope$1;", "performScrollForOverscroll", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "performScroll", "delta", "source", "performScroll-3eAAhYA", "(Landroidx/compose/foundation/gestures/Scroll2DScope;JI)J", "shouldDispatchOverscroll", "offset", "shouldDispatchOverscroll-k-4lQ0M", "(J)Z", "velocity", "Landroidx/compose/ui/unit/Velocity;", "shouldDispatchOverscroll-TH1AsA0", "performRawScroll", "scroll", "performRawScroll-MK-Hz9U", "(J)J", "dispatchRawDelta", "dispatchRawDelta-MK-Hz9U", "onScrollStopped", "", "initialVelocity", "onScrollStopped-sF-c-tU", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doFlingAnimation", "available", "doFlingAnimation-QWom1Mo", "shouldScrollImmediately", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/NestedScrollScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ScrollingLogic2D implements ScrollLogic {
    private FlingBehavior flingBehavior;
    private boolean isFlinging;
    private final Function0<Boolean> isScrollableNodeAttached;
    private NestedScrollDispatcher nestedScrollDispatcher;
    private OverscrollEffect overscrollEffect;
    private Scrollable2DState scrollableState;
    private int latestScrollSource = NestedScrollSource.Companion.getUserInput-WNlRxjI();
    private Scroll2DScope outerStateScope = Scrollable2DKt.NoOpScrollScope;
    private final ScrollingLogic2D$nestedScrollScope$1 nestedScrollScope = new NestedScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic2D$nestedScrollScope$1
        @Override // androidx.compose.foundation.gestures.NestedScrollScope
        /* JADX INFO: renamed from: scrollBy-OzD1aCk */
        public long mo629scrollByOzD1aCk(long offset, int source) {
            return this.this$0.m710performScroll3eAAhYA(this.this$0.outerStateScope, offset, source);
        }

        @Override // androidx.compose.foundation.gestures.NestedScrollScope
        /* JADX INFO: renamed from: scrollByWithOverscroll-OzD1aCk */
        public long mo630scrollByWithOverscrollOzD1aCk(long offset, int source) {
            this.this$0.latestScrollSource = source;
            OverscrollEffect overscrollEffect = this.this$0.overscrollEffect;
            if (overscrollEffect != null && this.this$0.m713shouldDispatchOverscrollk4lQ0M(offset)) {
                return overscrollEffect.mo301applyToScrollRhakbz0(offset, this.this$0.latestScrollSource, this.this$0.performScrollForOverscroll);
            }
            return this.this$0.m710performScroll3eAAhYA(this.this$0.outerStateScope, offset, source);
        }
    };
    private final Function1<Offset, Offset> performScrollForOverscroll = new Function1() { // from class: androidx.compose.foundation.gestures.e0
        public final Object invoke(Object obj) {
            return ScrollingLogic2D.a(this.b, (Offset) obj);
        }
    };

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollingLogic2D$scroll$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic2D$scroll$2", f = "Scrollable2D.kt", i = {}, l = {479}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<NestedScrollScope, Continuation<? super Unit>, Object> $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function2<? super NestedScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$block = function2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = ScrollingLogic2D.this.new AnonymousClass2(this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
            return create(scroll2DScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ScrollingLogic2D.this.outerStateScope = (Scroll2DScope) this.L$0;
                Function2<NestedScrollScope, Continuation<? super Unit>, Object> function2 = this.$block;
                ScrollingLogic2D$nestedScrollScope$1 scrollingLogic2D$nestedScrollScope$1 = ScrollingLogic2D.this.nestedScrollScope;
                this.label = 1;
                if (function2.invoke(scrollingLogic2D$nestedScrollScope$1, this) == coroutine_suspended) {
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

    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.foundation.gestures.ScrollingLogic2D$nestedScrollScope$1] */
    public ScrollingLogic2D(Scrollable2DState scrollable2DState, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, NestedScrollDispatcher nestedScrollDispatcher, Function0<Boolean> function0) {
        this.scrollableState = scrollable2DState;
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        this.isScrollableNodeAttached = function0;
    }

    public static Offset a(ScrollingLogic2D scrollingLogic2D, Offset offset) {
        return Offset.box-impl(scrollingLogic2D.m710performScroll3eAAhYA(scrollingLogic2D.outerStateScope, offset.unbox-impl(), scrollingLogic2D.latestScrollSource));
    }

    /* JADX INFO: renamed from: dispatchRawDelta-MK-Hz9U, reason: not valid java name */
    private final long m709dispatchRawDeltaMKHz9U(long scroll) {
        return this.scrollableState.mo543dispatchRawDeltaMKHz9U(scroll);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long doFlingAnimation_QWom1Mo$toDecomposedOffset(float f, long j) {
        if (Float.isNaN(Scrollable2DKt.m678getAngleTH1AsA0(j))) {
            return Offset.constructor-impl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(f)) & 4294967295L));
        }
        return Offset.constructor-impl((((long) Float.floatToRawIntBits(Math.abs(((float) Math.cos(Scrollable2DKt.m678getAngleTH1AsA0(j))) * f) * Math.signum(Velocity.getX-impl(j)))) << 32) | (((long) Float.floatToRawIntBits(Math.abs(((float) Math.sin(Scrollable2DKt.m678getAngleTH1AsA0(j))) * f) * Math.signum(Velocity.getY-impl(j)))) & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long doFlingAnimation_QWom1Mo$toDecomposedVelocity(float f, long j) {
        return Float.isNaN(Scrollable2DKt.m678getAngleTH1AsA0(j)) ? VelocityKt.Velocity(0.0f, f) : VelocityKt.Velocity(Math.abs(((float) Math.cos(Scrollable2DKt.m678getAngleTH1AsA0(j))) * f) * Math.signum(Velocity.getX-impl(j)), Math.abs(((float) Math.sin(Scrollable2DKt.m678getAngleTH1AsA0(j))) * f) * Math.signum(Velocity.getY-impl(j)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float doFlingAnimation_QWom1Mo$toMagnitudeFloat(long j) {
        return (float) Math.sqrt(((float) Math.pow(Float.intBitsToFloat((int) (j >> 32)), 2.0d)) + ((float) Math.pow(Float.intBitsToFloat((int) (j & 4294967295L)), 2.0d)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: performScroll-3eAAhYA, reason: not valid java name */
    public final long m710performScroll3eAAhYA(Scroll2DScope scroll2DScope, long j, int i) {
        long j2 = this.nestedScrollDispatcher.dispatchPreScroll-OzD1aCk(j, i);
        long j3 = Offset.minus-MK-Hz9U(j, j2);
        long jMo544scrollByMKHz9U = scroll2DScope.mo544scrollByMKHz9U(j3);
        return Offset.plus-MK-Hz9U(Offset.plus-MK-Hz9U(j2, jMo544scrollByMKHz9U), this.nestedScrollDispatcher.dispatchPostScroll-DzOQY0M(jMo544scrollByMKHz9U, Offset.minus-MK-Hz9U(j3, jMo544scrollByMKHz9U), i));
    }

    public static /* synthetic */ Object scroll$default(ScrollingLogic2D scrollingLogic2D, MutatePriority mutatePriority, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            mutatePriority = MutatePriority.Default;
        }
        return scrollingLogic2D.scroll(mutatePriority, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // androidx.compose.foundation.gestures.ScrollLogic
    /* JADX INFO: renamed from: doFlingAnimation-QWom1Mo */
    public Object mo672doFlingAnimationQWom1Mo(long j, Continuation<? super Velocity> continuation) throws Throwable {
        ScrollingLogic2D$doFlingAnimation$1 scrollingLogic2D$doFlingAnimation$1;
        ScrollingLogic2D scrollingLogic2D;
        Throwable th;
        Ref.LongRef longRef;
        if (continuation instanceof ScrollingLogic2D$doFlingAnimation$1) {
            scrollingLogic2D$doFlingAnimation$1 = (ScrollingLogic2D$doFlingAnimation$1) continuation;
            int i = scrollingLogic2D$doFlingAnimation$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                scrollingLogic2D$doFlingAnimation$1.label = i - Integer.MIN_VALUE;
            } else {
                scrollingLogic2D$doFlingAnimation$1 = new ScrollingLogic2D$doFlingAnimation$1(this, continuation);
            }
        } else {
            scrollingLogic2D$doFlingAnimation$1 = new ScrollingLogic2D$doFlingAnimation$1(this, continuation);
        }
        Object obj = scrollingLogic2D$doFlingAnimation$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = scrollingLogic2D$doFlingAnimation$1.label;
        if (i2 != 0) {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            longRef = (Ref.LongRef) scrollingLogic2D$doFlingAnimation$1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                scrollingLogic2D = this;
                scrollingLogic2D.isFlinging = false;
                return Velocity.box-impl(longRef.element);
            } catch (Throwable th2) {
                th = th2;
                scrollingLogic2D = this;
                scrollingLogic2D.isFlinging = false;
                throw th;
            }
        }
        ResultKt.throwOnFailure(obj);
        Ref.LongRef longRef2 = new Ref.LongRef();
        longRef2.element = j;
        this.isFlinging = true;
        try {
            MutatePriority mutatePriority = MutatePriority.Default;
            scrollingLogic2D = this;
            try {
                ScrollingLogic2D$doFlingAnimation$2 scrollingLogic2D$doFlingAnimation$2 = new ScrollingLogic2D$doFlingAnimation$2(scrollingLogic2D, j, longRef2, null);
                scrollingLogic2D$doFlingAnimation$1.L$0 = longRef2;
                scrollingLogic2D$doFlingAnimation$1.label = 1;
                if (scrollingLogic2D.scroll(mutatePriority, scrollingLogic2D$doFlingAnimation$2, scrollingLogic2D$doFlingAnimation$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                longRef = longRef2;
                scrollingLogic2D.isFlinging = false;
                return Velocity.box-impl(longRef.element);
            } catch (Throwable th3) {
                th = th3;
                th = th;
                scrollingLogic2D.isFlinging = false;
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            scrollingLogic2D = this;
        }
    }

    public final Scrollable2DState getScrollableState() {
        return this.scrollableState;
    }

    @Override // androidx.compose.foundation.gestures.ScrollLogic
    /* JADX INFO: renamed from: isFlinging, reason: from getter */
    public boolean getIsFlinging() {
        return this.isFlinging;
    }

    /* JADX INFO: renamed from: onScrollStopped-sF-c-tU, reason: not valid java name */
    public final Object m711onScrollStoppedsFctU(long j, Continuation<? super Unit> continuation) {
        ScrollingLogic2D$onScrollStopped$performFling$1 scrollingLogic2D$onScrollStopped$performFling$1 = new ScrollingLogic2D$onScrollStopped$performFling$1(this, null);
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        if (overscrollEffect == null || !m712shouldDispatchOverscrollTH1AsA0(j)) {
            Object objInvoke = scrollingLogic2D$onScrollStopped$performFling$1.invoke(Velocity.box-impl(j), continuation);
            return objInvoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvoke : Unit.INSTANCE;
        }
        Object objMo300applyToFlingBMRW4eQ = overscrollEffect.mo300applyToFlingBMRW4eQ(j, scrollingLogic2D$onScrollStopped$performFling$1, continuation);
        return objMo300applyToFlingBMRW4eQ == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo300applyToFlingBMRW4eQ : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.gestures.ScrollLogic
    /* JADX INFO: renamed from: performRawScroll-MK-Hz9U */
    public long mo673performRawScrollMKHz9U(long scroll) {
        return this.scrollableState.isScrollInProgress() ? Offset.Companion.getZero-F1C5BW0() : m709dispatchRawDeltaMKHz9U(scroll);
    }

    public final Object scroll(MutatePriority mutatePriority, Function2<? super NestedScrollScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objScroll = this.scrollableState.scroll(mutatePriority, new AnonymousClass2(function2, null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }

    public final void setScrollableState(Scrollable2DState scrollable2DState) {
        this.scrollableState = scrollable2DState;
    }

    /* JADX INFO: renamed from: shouldDispatchOverscroll-TH1AsA0, reason: not valid java name */
    public final boolean m712shouldDispatchOverscrollTH1AsA0(long velocity) {
        Scrollable2DState scrollable2DState = this.scrollableState;
        float f = Velocity.getX-impl(velocity);
        return scrollable2DState.mo542canScrollk4lQ0M(Offset.constructor-impl((((long) Float.floatToRawIntBits(Velocity.getY-impl(velocity))) & 4294967295L) | (Float.floatToRawIntBits(f) << 32)));
    }

    /* JADX INFO: renamed from: shouldDispatchOverscroll-k-4lQ0M, reason: not valid java name */
    public final boolean m713shouldDispatchOverscrollk4lQ0M(long offset) {
        return this.scrollableState.mo542canScrollk4lQ0M(offset);
    }

    public final boolean shouldScrollImmediately() {
        if (this.scrollableState.isScrollInProgress()) {
            return true;
        }
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        return overscrollEffect != null ? overscrollEffect.isInProgress() : false;
    }

    public final boolean update(Scrollable2DState scrollableState, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, NestedScrollDispatcher nestedScrollDispatcher) {
        boolean z;
        if (Intrinsics.areEqual(this.scrollableState, scrollableState)) {
            z = false;
        } else {
            this.scrollableState = scrollableState;
            z = true;
        }
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
        return z;
    }
}
