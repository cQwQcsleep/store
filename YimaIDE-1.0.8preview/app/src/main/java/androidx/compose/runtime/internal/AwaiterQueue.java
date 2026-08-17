package androidx.compose.runtime.internal;

import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.CancellationHandle;
import androidx.compose.runtime.OneShotCancellationHandle;
import androidx.compose.runtime.internal.AwaiterQueue;
import androidx.compose.runtime.internal.AwaiterQueue.Awaiter;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001!B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u00002\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019¢\u0006\u0002\u0010\u001bJ\u001a\u0010\u001c\u001a\u00020\u001a2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001a0\u001eJ\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\nR\u0014\u0010\u0006\u001a\u00060\u0003j\u0002`\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Landroidx/compose/runtime/internal/AwaiterQueue;", "A", "Landroidx/compose/runtime/internal/AwaiterQueue$Awaiter;", "", "<init>", "()V", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "failureCause", "", "pendingAwaitersCountUnlocked", "Landroidx/compose/runtime/internal/AtomicAwaitersCount;", "Landroidx/compose/runtime/internal/AtomicInt;", "awaiters", "Landroidx/collection/MutableObjectList;", "spareList", "hasAwaiters", "", "getHasAwaiters", "()Z", "addAwaiter", "Landroidx/compose/runtime/CancellationHandle;", "awaiter", "onFirstAwaiter", "Lkotlin/Function0;", "", "(Landroidx/compose/runtime/internal/AwaiterQueue$Awaiter;Lkotlin/jvm/functions/Function0;)Landroidx/compose/runtime/CancellationHandle;", "flushAndDispatchAwaiters", "resume", "Lkotlin/Function1;", "fail", "cause", "Awaiter", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AwaiterQueue<A extends Awaiter> {
    public static final int $stable = 8;
    private Throwable failureCause;
    private final Object lock = new Object();
    private final AtomicInt pendingAwaitersCountUnlocked = AtomicAwaitersCount.m2514constructorimpl();
    private MutableObjectList<A> awaiters = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);
    private MutableObjectList<A> spareList = new MutableObjectList<>(0, 1, (DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/internal/AwaiterQueue$Awaiter;", "", "<init>", "()V", "cancel", "", "resumeWithException", "exception", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static abstract class Awaiter {
        public static final int $stable = 0;

        public abstract void cancel();

        public abstract void resumeWithException(Throwable exception);
    }

    public static Unit a(Awaiter awaiter, AwaiterQueue awaiterQueue, Ref.IntRef intRef) {
        int i;
        awaiter.cancel();
        AtomicInt atomicInt = awaiterQueue.pendingAwaitersCountUnlocked;
        int i2 = intRef.element;
        do {
            i = atomicInt.get();
        } while (!atomicInt.compareAndSet(i, ((i >>> 27) & 15) == i2 ? i - 1 : i));
        return Unit.INSTANCE;
    }

    public final CancellationHandle addAwaiter(final A awaiter, Function0<Unit> onFirstAwaiter) {
        int i;
        int i2;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        synchronized (this.lock) {
            Throwable th = this.failureCause;
            if (th != null) {
                awaiter.resumeWithException(th);
                return CancellationHandle.INSTANCE.getEmpty$runtime();
            }
            AtomicInt atomicInt = this.pendingAwaitersCountUnlocked;
            do {
                i = atomicInt.get();
                i2 = i + 1;
            } while (!atomicInt.compareAndSet(i, i2));
            boolean z = true;
            if ((134217727 & i2) != 1) {
                z = false;
            }
            intRef.element = (i2 >>> 27) & 15;
            this.awaiters.add(awaiter);
            if (z && onFirstAwaiter != null) {
                try {
                    onFirstAwaiter.invoke();
                } catch (Throwable th2) {
                    fail(th2);
                }
            }
            return new OneShotCancellationHandle(new Function0() { // from class: tj0
                public final Object invoke() {
                    return AwaiterQueue.a(awaiter, this, intRef);
                }
            });
        }
    }

    public final void fail(Throwable cause) {
        int i;
        synchronized (this.lock) {
            try {
                if (this.failureCause != null) {
                    return;
                }
                this.failureCause = cause;
                MutableObjectList<A> mutableObjectList = this.awaiters;
                Object[] objArr = ((ObjectList) mutableObjectList).content;
                int i2 = ((ObjectList) mutableObjectList)._size;
                for (int i3 = 0; i3 < i2; i3++) {
                    ((Awaiter) objArr[i3]).resumeWithException(cause);
                }
                this.awaiters.clear();
                AtomicInt atomicInt = this.pendingAwaitersCountUnlocked;
                do {
                    i = atomicInt.get();
                } while (!atomicInt.compareAndSet(i, AtomicAwaitersCount.m2525packimpl(atomicInt, ((i >>> 27) & 15) + 1, 0)));
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void flushAndDispatchAwaiters(Function1<? super A, Unit> resume) {
        int i;
        int i2;
        synchronized (this.lock) {
            try {
                MutableObjectList<A> mutableObjectList = this.awaiters;
                this.awaiters = this.spareList;
                this.spareList = mutableObjectList;
                AtomicInt atomicInt = this.pendingAwaitersCountUnlocked;
                do {
                    i = atomicInt.get();
                } while (!atomicInt.compareAndSet(i, AtomicAwaitersCount.m2525packimpl(atomicInt, ((i >>> 27) & 15) + 1, 0)));
                int size = mutableObjectList.getSize();
                for (i2 = 0; i2 < size; i2++) {
                    resume.invoke(mutableObjectList.get(i2));
                }
                mutableObjectList.clear();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean getHasAwaiters() {
        return (this.pendingAwaitersCountUnlocked.get() & 134217727) > 0;
    }
}
