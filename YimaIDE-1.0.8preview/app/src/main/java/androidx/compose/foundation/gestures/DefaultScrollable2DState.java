package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J?\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132'\u0010\u0014\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0015¢\u0006\u0002\b\u0018H\u0096@¢\u0006\u0002\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\"\u0010#R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006$"}, d2 = {"Landroidx/compose/foundation/gestures/DefaultScrollable2DState;", "Landroidx/compose/foundation/gestures/Scrollable2DState;", "onDelta", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getOnDelta", "()Lkotlin/jvm/functions/Function1;", "scrollScope", "Landroidx/compose/foundation/gestures/Scroll2DScope;", "scrollMutex", "Landroidx/compose/foundation/MutatorMutex;", "isScrollingState", "Landroidx/compose/runtime/MutableState;", "", "scroll", "", "scrollPriority", "Landroidx/compose/foundation/MutatePriority;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/MutatePriority;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchRawDelta", "delta", "dispatchRawDelta-MK-Hz9U", "(J)J", "isScrollInProgress", "()Z", "canScroll", "offset", "canScroll-k-4lQ0M", "(J)Z", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class DefaultScrollable2DState implements Scrollable2DState {
    private final Function1<Offset, Offset> onDelta;
    private final Scroll2DScope scrollScope = new Scroll2DScope() { // from class: androidx.compose.foundation.gestures.DefaultScrollable2DState$scrollScope$1
        @Override // androidx.compose.foundation.gestures.Scroll2DScope
        /* JADX INFO: renamed from: scrollBy-MK-Hz9U, reason: not valid java name */
        public long mo544scrollByMKHz9U(long delta) {
            return (Float.isNaN(Float.intBitsToFloat((int) (delta >> 32))) || Float.isNaN(Float.intBitsToFloat((int) (4294967295L & delta)))) ? Offset.Companion.getZero-F1C5BW0() : ((Offset) this.this$0.getOnDelta().invoke(Offset.box-impl(delta))).unbox-impl();
        }
    };
    private final MutatorMutex scrollMutex = new MutatorMutex();
    private final MutableState<Boolean> isScrollingState = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, (SnapshotMutationPolicy) null, 2, (Object) null);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DefaultScrollable2DState$scroll$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.DefaultScrollable2DState$scroll$2", f = "Scrollable2DState.kt", i = {}, l = {146}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<Scroll2DScope, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ MutatePriority $scrollPriority;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.DefaultScrollable2DState$scroll$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/Scroll2DScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.DefaultScrollable2DState$scroll$2$1", f = "Scrollable2DState.kt", i = {}, l = {149}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<Scroll2DScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function2<Scroll2DScope, Continuation<? super Unit>, Object> $block;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ DefaultScrollable2DState this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(DefaultScrollable2DState defaultScrollable2DState, Function2<? super Scroll2DScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = defaultScrollable2DState;
                this.$block = function2;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$block, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(Scroll2DScope scroll2DScope, Continuation<? super Unit> continuation) {
                return create(scroll2DScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Scroll2DScope scroll2DScope = (Scroll2DScope) this.L$0;
                        this.this$0.isScrollingState.setValue(Boxing.boxBoolean(true));
                        Function2<Scroll2DScope, Continuation<? super Unit>, Object> function2 = this.$block;
                        this.label = 1;
                        if (function2.invoke(scroll2DScope, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    this.this$0.isScrollingState.setValue(Boxing.boxBoolean(false));
                    this = Unit.INSTANCE;
                    return this;
                } catch (Throwable th) {
                    this.this$0.isScrollingState.setValue(Boxing.boxBoolean(false));
                    throw th;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(MutatePriority mutatePriority, Function2<? super Scroll2DScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$scrollPriority = mutatePriority;
            this.$block = function2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultScrollable2DState.this.new AnonymousClass2(this.$scrollPriority, this.$block, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutatorMutex mutatorMutex = DefaultScrollable2DState.this.scrollMutex;
                Scroll2DScope scroll2DScope = DefaultScrollable2DState.this.scrollScope;
                MutatePriority mutatePriority = this.$scrollPriority;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(DefaultScrollable2DState.this, this.$block, null);
                this.label = 1;
                if (mutatorMutex.mutateWith(scroll2DScope, mutatePriority, anonymousClass1, this) == coroutine_suspended) {
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

    public DefaultScrollable2DState(Function1<? super Offset, Offset> function1) {
        this.onDelta = function1;
    }

    @Override // androidx.compose.foundation.gestures.Scrollable2DState
    /* JADX INFO: renamed from: canScroll-k-4lQ0M, reason: not valid java name */
    public boolean mo542canScrollk4lQ0M(long offset) {
        return true;
    }

    @Override // androidx.compose.foundation.gestures.Scrollable2DState
    /* JADX INFO: renamed from: dispatchRawDelta-MK-Hz9U, reason: not valid java name */
    public long mo543dispatchRawDeltaMKHz9U(long delta) {
        return ((Offset) this.onDelta.invoke(Offset.box-impl(delta))).unbox-impl();
    }

    public final Function1<Offset, Offset> getOnDelta() {
        return this.onDelta;
    }

    @Override // androidx.compose.foundation.gestures.Scrollable2DState
    public boolean isScrollInProgress() {
        return ((Boolean) this.isScrollingState.getValue()).booleanValue();
    }

    @Override // androidx.compose.foundation.gestures.Scrollable2DState
    public Object scroll(MutatePriority mutatePriority, Function2<? super Scroll2DScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass2(mutatePriority, function2, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }
}
