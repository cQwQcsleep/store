package androidx.compose.runtime;

import androidx.compose.runtime.NextFrameEndCallbackQueue;
import androidx.compose.runtime.internal.AtomicBoolean;
import androidx.compose.runtime.internal.AtomicInt;
import androidx.compose.runtime.internal.AwaiterQueue;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001:\u0001\u001aB\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u0006\u0010\u0014\u001a\u00020\u0004J\u0006\u0010\u0015\u001a\u00020\u0004J\u0014\u0010\u0016\u001a\u00020\u00042\f\b\u0002\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/NextFrameEndCallbackQueue;", "", "onNewAwaiters", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "isFrameOngoing", "Landroidx/compose/runtime/internal/AtomicBoolean;", "Landroidx/compose/runtime/internal/AtomicInt;", "frameEndQueue", "Landroidx/compose/runtime/internal/AwaiterQueue;", "Landroidx/compose/runtime/NextFrameEndCallbackQueue$NextFrameEndAwaiter;", "hasAwaiters", "", "getHasAwaiters", "()Z", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "markFrameStarted", "markFrameComplete", "cancel", "cancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "NextFrameEndAwaiter", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NextFrameEndCallbackQueue {
    public static final int $stable = 8;
    private final Function0<Unit> onNewAwaiters;
    private final AtomicInt isFrameOngoing = AtomicBoolean.m2531constructorimpl(false);
    private final AwaiterQueue<NextFrameEndAwaiter> frameEndQueue = new AwaiterQueue<>();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0006\u0010\b\u001a\u00020\u0004J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0016\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/compose/runtime/NextFrameEndCallbackQueue$NextFrameEndAwaiter;", "Landroidx/compose/runtime/internal/AwaiterQueue$Awaiter;", "onNextFrameEnd", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "cancel", "resume", "resumeWithException", "exception", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class NextFrameEndAwaiter extends AwaiterQueue.Awaiter {
        private Function0<Unit> onNextFrameEnd;

        public NextFrameEndAwaiter(Function0<Unit> function0) {
            this.onNextFrameEnd = function0;
        }

        @Override // androidx.compose.runtime.internal.AwaiterQueue.Awaiter
        public void cancel() {
            this.onNextFrameEnd = null;
        }

        public final void resume() {
            Function0<Unit> function0 = this.onNextFrameEnd;
            if (function0 != null) {
                function0.invoke();
            }
        }

        @Override // androidx.compose.runtime.internal.AwaiterQueue.Awaiter
        public void resumeWithException(Throwable exception) throws Throwable {
            throw exception;
        }
    }

    public NextFrameEndCallbackQueue(final Function0<Unit> function0) {
        this.onNewAwaiters = new Function0() { // from class: xha
            public final Object invoke() {
                return NextFrameEndCallbackQueue.a(this.b, function0);
            }
        };
    }

    public static Unit a(NextFrameEndCallbackQueue nextFrameEndCallbackQueue, Function0 function0) {
        if (!AtomicBoolean.m2535getimpl(nextFrameEndCallbackQueue.isFrameOngoing)) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(NextFrameEndAwaiter nextFrameEndAwaiter) {
        nextFrameEndAwaiter.resume();
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void cancel$default(NextFrameEndCallbackQueue nextFrameEndCallbackQueue, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = new CancellationException("scheduler cancelled");
        }
        nextFrameEndCallbackQueue.cancel(cancellationException);
    }

    public final void cancel(CancellationException cancellationException) {
        this.frameEndQueue.fail(cancellationException);
    }

    public final boolean getHasAwaiters() {
        return this.frameEndQueue.getHasAwaiters();
    }

    public final void markFrameComplete() {
        AtomicBoolean.m2538setimpl(this.isFrameOngoing, false);
        this.frameEndQueue.flushAndDispatchAwaiters(new Function1() { // from class: androidx.compose.runtime.f
            public final Object invoke(Object obj) {
                return NextFrameEndCallbackQueue.b((NextFrameEndCallbackQueue.NextFrameEndAwaiter) obj);
            }
        });
    }

    public final void markFrameStarted() {
        AtomicBoolean.m2538setimpl(this.isFrameOngoing, true);
    }

    public final CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
        return this.frameEndQueue.addAwaiter(new NextFrameEndAwaiter(action), this.onNewAwaiters);
    }
}
