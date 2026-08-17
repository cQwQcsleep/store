package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.IntOffset;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2", f = "ContentInViewNode.kt", i = {}, l = {282}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class ContentInViewNode$launchAnimation$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-IntOffset$-viewportAdjustmentForReverseScroll$0, reason: not valid java name */
    final /* synthetic */ long f11x677d03b0;
    final /* synthetic */ UpdatableAnimationState $animationState;
    final /* synthetic */ BringIntoViewSpec $bringIntoViewSpec;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ContentInViewNode this$0;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1", f = "ContentInViewNode.kt", i = {}, l = {289}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {

        /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-unit-IntOffset$-viewportAdjustmentForReverseScroll$0, reason: not valid java name */
        final /* synthetic */ long f12x677d03b0;
        final /* synthetic */ Job $animationJob;
        final /* synthetic */ UpdatableAnimationState $animationState;
        final /* synthetic */ BringIntoViewSpec $bringIntoViewSpec;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ContentInViewNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(UpdatableAnimationState updatableAnimationState, ContentInViewNode contentInViewNode, BringIntoViewSpec bringIntoViewSpec, long j, Job job, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$animationState = updatableAnimationState;
            this.this$0 = contentInViewNode;
            this.$bringIntoViewSpec = bringIntoViewSpec;
            this.f12x677d03b0 = j;
            this.$animationJob = job;
        }

        public static Unit b(ContentInViewNode contentInViewNode, UpdatableAnimationState updatableAnimationState, BringIntoViewSpec bringIntoViewSpec) {
            ContentInViewNode contentInViewNode2;
            Rect focusedChildBounds;
            boolean zM533isMaxVisibleEQwtKw$default;
            BringIntoViewRequestPriorityQueue bringIntoViewRequestPriorityQueue = contentInViewNode.bringIntoViewRequests;
            while (true) {
                if (bringIntoViewRequestPriorityQueue.requests.getSize() == 0) {
                    contentInViewNode2 = contentInViewNode;
                    break;
                }
                Rect rect = (Rect) ((ContentInViewNode.Request) bringIntoViewRequestPriorityQueue.requests.last()).getCurrentBounds().invoke();
                if (rect == null) {
                    contentInViewNode2 = contentInViewNode;
                    zM533isMaxVisibleEQwtKw$default = true;
                } else {
                    contentInViewNode2 = contentInViewNode;
                    zM533isMaxVisibleEQwtKw$default = ContentInViewNode.m533isMaxVisibleEQwtKw$default(contentInViewNode2, rect, 0L, 0L, 3, null);
                }
                if (!zM533isMaxVisibleEQwtKw$default) {
                    break;
                }
                ((ContentInViewNode.Request) bringIntoViewRequestPriorityQueue.requests.removeAt(bringIntoViewRequestPriorityQueue.requests.getSize() - 1)).getContinuation().resumeWith(Result.constructor-impl(Unit.INSTANCE));
                contentInViewNode = contentInViewNode2;
            }
            if (contentInViewNode2.trackingFocusedChild && (focusedChildBounds = contentInViewNode2.getFocusedChildBounds()) != null && ContentInViewNode.m533isMaxVisibleEQwtKw$default(contentInViewNode2, focusedChildBounds, 0L, 0L, 3, null)) {
                contentInViewNode2.trackingFocusedChild = false;
            }
            updatableAnimationState.setValue(contentInViewNode2.m529calculateScrollDeltaI_oMVgE(bringIntoViewSpec, IntOffset.Companion.getZero-nOcc-ac()));
            return Unit.INSTANCE;
        }

        public static Unit d(ContentInViewNode contentInViewNode, UpdatableAnimationState updatableAnimationState, Job job, NestedScrollScope nestedScrollScope, float f) {
            float f2 = contentInViewNode.reverseDirection ? 1.0f : -1.0f;
            ScrollingLogic scrollingLogic = contentInViewNode.scrollingLogic;
            float fM703toFloatk4lQ0M = f2 * scrollingLogic.m703toFloatk4lQ0M(scrollingLogic.m701reverseIfNeededMKHz9U(nestedScrollScope.mo629scrollByOzD1aCk(scrollingLogic.m701reverseIfNeededMKHz9U(scrollingLogic.m704toOffsettuRUvjQ(f2 * f)), NestedScrollSource.Companion.getUserInput-WNlRxjI())));
            if (Math.abs(fM703toFloatk4lQ0M) < Math.abs(f)) {
                JobKt.cancel$default(job, "Scroll animation cancelled because scroll was not consumed (" + fM703toFloatk4lQ0M + " < " + f + ')', (Throwable) null, 2, (Object) null);
            }
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animationState, this.this$0, this.$bringIntoViewSpec, this.f12x677d03b0, this.$animationJob, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
            return create(nestedScrollScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
                this.$animationState.setValue(this.this$0.m529calculateScrollDeltaI_oMVgE(this.$bringIntoViewSpec, this.f12x677d03b0));
                final UpdatableAnimationState updatableAnimationState = this.$animationState;
                final ContentInViewNode contentInViewNode = this.this$0;
                final Job job = this.$animationJob;
                Function1<? super Float, Unit> function1 = new Function1() { // from class: androidx.compose.foundation.gestures.h
                    public final Object invoke(Object obj2) {
                        return ContentInViewNode$launchAnimation$2.AnonymousClass1.d(contentInViewNode, updatableAnimationState, job, nestedScrollScope, ((Float) obj2).floatValue());
                    }
                };
                final ContentInViewNode contentInViewNode2 = this.this$0;
                final UpdatableAnimationState updatableAnimationState2 = this.$animationState;
                final BringIntoViewSpec bringIntoViewSpec = this.$bringIntoViewSpec;
                Function0<Unit> function0 = new Function0() { // from class: androidx.compose.foundation.gestures.i
                    public final Object invoke() {
                        return ContentInViewNode$launchAnimation$2.AnonymousClass1.b(contentInViewNode2, updatableAnimationState2, bringIntoViewSpec);
                    }
                };
                this.label = 1;
                if (updatableAnimationState.animateToZero(function1, function0, this) == coroutine_suspended) {
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
    public ContentInViewNode$launchAnimation$2(ContentInViewNode contentInViewNode, UpdatableAnimationState updatableAnimationState, BringIntoViewSpec bringIntoViewSpec, long j, Continuation<? super ContentInViewNode$launchAnimation$2> continuation) {
        super(2, continuation);
        this.this$0 = contentInViewNode;
        this.$animationState = updatableAnimationState;
        this.$bringIntoViewSpec = bringIntoViewSpec;
        this.f11x677d03b0 = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ContentInViewNode$launchAnimation$2 contentInViewNode$launchAnimation$2 = new ContentInViewNode$launchAnimation$2(this.this$0, this.$animationState, this.$bringIntoViewSpec, this.f11x677d03b0, continuation);
        contentInViewNode$launchAnimation$2.L$0 = obj;
        return contentInViewNode$launchAnimation$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Job job = JobKt.getJob(((CoroutineScope) this.L$0).getCoroutineContext());
                    this.this$0.isAnimationRunning = true;
                    ScrollingLogic scrollingLogic = this.this$0.scrollingLogic;
                    MutatePriority mutatePriority = MutatePriority.Default;
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animationState, this.this$0, this.$bringIntoViewSpec, this.f11x677d03b0, job, null);
                    this.label = 1;
                    if (scrollingLogic.scroll(mutatePriority, anonymousClass1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                this.this$0.bringIntoViewRequests.resumeAndRemoveAll();
                this.this$0.isAnimationRunning = false;
                this.this$0.bringIntoViewRequests.cancelAndRemoveAll(null);
                this.this$0.trackingFocusedChild = false;
                return Unit.INSTANCE;
            } catch (CancellationException e) {
                throw e;
            }
        } catch (Throwable th) {
            this.this$0.isAnimationRunning = false;
            this.this$0.bringIntoViewRequests.cancelAndRemoveAll(null);
            this.this$0.trackingFocusedChild = false;
            throw th;
        }
    }
}
