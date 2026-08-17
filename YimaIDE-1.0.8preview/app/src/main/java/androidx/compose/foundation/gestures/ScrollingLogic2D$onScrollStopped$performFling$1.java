package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/Velocity;", "velocity"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollingLogic2D$onScrollStopped$performFling$1", f = "Scrollable2D.kt", i = {0, 1, 1, 2, 2}, l = {378, 381, 384}, m = "invokeSuspend", n = {"velocity", "velocity", "available", "velocity", "velocityLeft"}, s = {"J$0", "J$0", "J$1", "J$0", "J$1"}, v = 1)
public final class ScrollingLogic2D$onScrollStopped$performFling$1 extends SuspendLambda implements Function2<Velocity, Continuation<? super Velocity>, Object> {
    /* synthetic */ long J$0;
    long J$1;
    int label;
    final /* synthetic */ ScrollingLogic2D this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLogic2D$onScrollStopped$performFling$1(ScrollingLogic2D scrollingLogic2D, Continuation<? super ScrollingLogic2D$onScrollStopped$performFling$1> continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic2D;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScrollingLogic2D$onScrollStopped$performFling$1 scrollingLogic2D$onScrollStopped$performFling$1 = new ScrollingLogic2D$onScrollStopped$performFling$1(this.this$0, continuation);
        scrollingLogic2D$onScrollStopped$performFling$1.J$0 = ((Velocity) obj).unbox-impl();
        return scrollingLogic2D$onScrollStopped$performFling$1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m714invokesFctU(((Velocity) obj).unbox-impl(), (Continuation) obj2);
    }

    /* JADX INFO: renamed from: invoke-sF-c-tU, reason: not valid java name */
    public final Object m714invokesFctU(long j, Continuation<? super Velocity> continuation) {
        return create(Velocity.box-impl(j), continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0084, code lost:
    
        if (r0 == r6) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object obj2;
        long j;
        Object objMo672doFlingAnimationQWom1Mo;
        long j2;
        long j3;
        long j4;
        Object obj3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            long j5 = this.J$0;
            NestedScrollDispatcher nestedScrollDispatcher = this.this$0.nestedScrollDispatcher;
            this.J$0 = j5;
            this.label = 1;
            obj2 = nestedScrollDispatcher.dispatchPreFling-QWom1Mo(j5, this);
            if (obj2 != coroutine_suspended) {
                j = j5;
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            j = this.J$0;
            ResultKt.throwOnFailure(obj);
            obj2 = obj;
        } else if (i == 2) {
            j3 = this.J$1;
            j2 = this.J$0;
            ResultKt.throwOnFailure(obj);
            objMo672doFlingAnimationQWom1Mo = obj;
            long j6 = ((Velocity) objMo672doFlingAnimationQWom1Mo).unbox-impl();
            NestedScrollDispatcher nestedScrollDispatcher2 = this.this$0.nestedScrollDispatcher;
            long j7 = Velocity.minus-AH228Gc(j3, j6);
            this.J$0 = j2;
            this.J$1 = j6;
            this.label = 3;
            j4 = j6;
            obj3 = nestedScrollDispatcher2.dispatchPostFling-RZ2iAVY(j7, j4, this);
        } else {
            if (i != 3) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            long j8 = this.J$1;
            long j9 = this.J$0;
            ResultKt.throwOnFailure(obj);
            j2 = j9;
            j4 = j8;
            obj3 = obj;
        }
        return Velocity.box-impl(Velocity.minus-AH228Gc(j2, Velocity.minus-AH228Gc(j4, ((Velocity) obj3).unbox-impl())));
        long j10 = Velocity.minus-AH228Gc(j, ((Velocity) obj2).unbox-impl());
        ScrollingLogic2D scrollingLogic2D = this.this$0;
        this.J$0 = j;
        this.J$1 = j10;
        this.label = 2;
        objMo672doFlingAnimationQWom1Mo = scrollingLogic2D.mo672doFlingAnimationQWom1Mo(j10, this);
        if (objMo672doFlingAnimationQWom1Mo != coroutine_suspended) {
            j2 = j;
            j3 = j10;
            long j11 = ((Velocity) objMo672doFlingAnimationQWom1Mo).unbox-impl();
            NestedScrollDispatcher nestedScrollDispatcher3 = this.this$0.nestedScrollDispatcher;
            long j12 = Velocity.minus-AH228Gc(j3, j11);
            this.J$0 = j2;
            this.J$1 = j11;
            this.label = 3;
            j4 = j11;
            obj3 = nestedScrollDispatcher3.dispatchPostFling-RZ2iAVY(j12, j4, this);
        }
        return coroutine_suspended;
    }
}
