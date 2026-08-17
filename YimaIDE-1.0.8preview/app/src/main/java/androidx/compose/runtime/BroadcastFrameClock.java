package androidx.compose.runtime;

import androidx.compose.runtime.internal.AwaiterQueue;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001aB\u0019\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010J(\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002H\u00120\u0014H\u0096@¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00042\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019R\u0016\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/BroadcastFrameClock;", "Landroidx/compose/runtime/MonotonicFrameClock;", "onNewAwaiters", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "queue", "Landroidx/compose/runtime/internal/AwaiterQueue;", "Landroidx/compose/runtime/BroadcastFrameClock$FrameAwaiter;", "hasAwaiters", "", "getHasAwaiters", "()Z", "sendFrame", "timeNanos", "", "withFrameNanos", "R", "onFrame", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "cancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "FrameAwaiter", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BroadcastFrameClock implements MonotonicFrameClock {
    public static final int $stable = 8;
    private final Function0<Unit> onNewAwaiters;
    private final AwaiterQueue<FrameAwaiter<?>> queue;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B)\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0005R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/BroadcastFrameClock$FrameAwaiter;", "R", "Landroidx/compose/runtime/internal/AwaiterQueue$Awaiter;", "onFrame", "Lkotlin/Function1;", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlinx/coroutines/CancellableContinuation;)V", "cancel", "", "resumeWithException", "exception", "", "resume", "timeNanos", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class FrameAwaiter<R> extends AwaiterQueue.Awaiter {
        private CancellableContinuation<? super R> continuation;
        private Function1<? super Long, ? extends R> onFrame;

        public FrameAwaiter(Function1<? super Long, ? extends R> function1, CancellableContinuation<? super R> cancellableContinuation) {
            this.continuation = cancellableContinuation;
            this.onFrame = function1;
        }

        @Override // androidx.compose.runtime.internal.AwaiterQueue.Awaiter
        public void cancel() {
            this.onFrame = null;
            this.continuation = null;
        }

        public final void resume(long timeNanos) {
            CancellableContinuation<? super R> cancellableContinuation;
            Object obj;
            Function1<? super Long, ? extends R> function1 = this.onFrame;
            if (function1 == null || (cancellableContinuation = this.continuation) == null) {
                return;
            }
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.constructor-impl(function1.invoke(Long.valueOf(timeNanos)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            cancellableContinuation.resumeWith(obj);
        }

        @Override // androidx.compose.runtime.internal.AwaiterQueue.Awaiter
        public void resumeWithException(Throwable exception) {
            CancellableContinuation<? super R> cancellableContinuation = this.continuation;
            if (cancellableContinuation != null) {
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(exception)));
            }
        }
    }

    public BroadcastFrameClock(Function0<Unit> function0) {
        this.onNewAwaiters = function0;
        this.queue = new AwaiterQueue<>();
    }

    public static Unit a(long j, FrameAwaiter frameAwaiter) {
        frameAwaiter.resume(j);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void cancel$default(BroadcastFrameClock broadcastFrameClock, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = new CancellationException("clock cancelled");
        }
        broadcastFrameClock.cancel(cancellationException);
    }

    public final void cancel(CancellationException cancellationException) {
        this.queue.fail(cancellationException);
    }

    public /* bridge */ <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) MonotonicFrameClock.DefaultImpls.fold(this, r, function2);
    }

    public /* bridge */ <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) MonotonicFrameClock.DefaultImpls.get(this, key);
    }

    public final boolean getHasAwaiters() {
        return this.queue.getHasAwaiters();
    }

    public /* bridge */ CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return MonotonicFrameClock.DefaultImpls.minusKey(this, key);
    }

    public /* bridge */ CoroutineContext plus(CoroutineContext coroutineContext) {
        return MonotonicFrameClock.DefaultImpls.plus(this, coroutineContext);
    }

    public final void sendFrame(final long timeNanos) {
        this.queue.flushAndDispatchAwaiters(new Function1() { // from class: androidx.compose.runtime.b
            public final Object invoke(Object obj) {
                return BroadcastFrameClock.a(timeNanos, (BroadcastFrameClock.FrameAwaiter) obj);
            }
        });
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public <R> Object withFrameNanos(Function1<? super Long, ? extends R> function1, Continuation<? super R> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellationHandle cancellationHandleAddAwaiter = this.queue.addAwaiter(new FrameAwaiter(function1, cancellableContinuationImpl), this.onNewAwaiters);
        cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: androidx.compose.runtime.BroadcastFrameClock$withFrameNanos$2$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                cancellationHandleAddAwaiter.cancel();
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BroadcastFrameClock() {
        Function0 function0 = null;
        this(function0, 1, function0);
    }

    public /* synthetic */ BroadcastFrameClock(Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : function0);
    }
}
