package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.BringIntoViewRequestPriorityQueue;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Rect;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuation;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0006J9\u0010\u000f\u001a\u00020\u00102#\u0010\u0011\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00100\u0012H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0006\u0010\u0017\u001a\u00020\u0010J9\u0010\u0018\u001a\u00020\u00102#\u0010\u0011\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\f0\u0012H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0010\u0010\u0019\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/gestures/BringIntoViewRequestPriorityQueue;", "", "<init>", "()V", "requests", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/foundation/gestures/ContentInViewNode$Request;", "size", "", "getSize", "()I", "isEmpty", "", "enqueue", "request", "forEachFromSmallest", "", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Rect;", "Lkotlin/ParameterName;", "name", "bounds", "resumeAndRemoveAll", "resumeAndRemoveWhile", "cancelAndRemoveAll", "cause", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BringIntoViewRequestPriorityQueue {
    public static final int $stable = MutableVector.$stable;
    private final MutableVector<ContentInViewNode.Request> requests = new MutableVector<>(new ContentInViewNode.Request[16], 0);

    public static Unit a(BringIntoViewRequestPriorityQueue bringIntoViewRequestPriorityQueue, ContentInViewNode.Request request, Throwable th) {
        bringIntoViewRequestPriorityQueue.requests.remove(request);
        return Unit.INSTANCE;
    }

    public final void cancelAndRemoveAll(Throwable cause) {
        MutableVector<ContentInViewNode.Request> mutableVector = this.requests;
        int size = mutableVector.getSize();
        CancellableContinuation[] cancellableContinuationArr = new CancellableContinuation[size];
        for (int i = 0; i < size; i++) {
            cancellableContinuationArr[i] = ((ContentInViewNode.Request) mutableVector.content[i]).getContinuation();
        }
        for (int i2 = 0; i2 < size; i2++) {
            cancellableContinuationArr[i2].cancel(cause);
        }
        if (this.requests.getSize() == 0) {
            return;
        }
        InlineClassHelperKt.throwIllegalStateException("uncancelled requests present");
    }

    public final boolean enqueue(final ContentInViewNode.Request request) {
        Rect rect = (Rect) request.getCurrentBounds().invoke();
        if (rect == null) {
            CancellableContinuation<Unit> continuation = request.getContinuation();
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.constructor-impl(Unit.INSTANCE));
            return false;
        }
        request.getContinuation().invokeOnCancellation(new Function1() { // from class: m01
            public final Object invoke(Object obj) {
                return BringIntoViewRequestPriorityQueue.a(this.b, request, (Throwable) obj);
            }
        });
        IntRange intRangeUntil = RangesKt.until(0, this.requests.getSize());
        int first = intRangeUntil.getFirst();
        int last = intRangeUntil.getLast();
        if (first <= last) {
            while (true) {
                Rect rect2 = (Rect) ((ContentInViewNode.Request) this.requests.content[last]).getCurrentBounds().invoke();
                if (rect2 != null) {
                    Rect rectIntersect = rect.intersect(rect2);
                    if (!Intrinsics.areEqual(rectIntersect, rect)) {
                        if (!Intrinsics.areEqual(rectIntersect, rect2)) {
                            CancellationException cancellationException = new CancellationException("bringIntoView call interrupted by a newer, non-overlapping call");
                            int size = this.requests.getSize() - 1;
                            if (size <= last) {
                                while (true) {
                                    ((ContentInViewNode.Request) this.requests.content[last]).getContinuation().cancel(cancellationException);
                                    if (size == last) {
                                        break;
                                    }
                                    size++;
                                }
                            }
                        }
                    } else {
                        this.requests.add(last + 1, request);
                        return true;
                    }
                }
                if (last != first) {
                    last--;
                }
            }
        }
        this.requests.add(0, request);
        return true;
    }

    public final void forEachFromSmallest(Function1<? super Rect, Unit> block) {
        MutableVector mutableVector = this.requests;
        int size = mutableVector.getSize() - 1;
        Object[] objArr = mutableVector.content;
        if (size < objArr.length) {
            while (size >= 0) {
                block.invoke(((ContentInViewNode.Request) objArr[size]).getCurrentBounds().invoke());
                size--;
            }
        }
    }

    public final int getSize() {
        return this.requests.getSize();
    }

    public final boolean isEmpty() {
        return this.requests.getSize() == 0;
    }

    public final void resumeAndRemoveAll() {
        IntRange intRangeUntil = RangesKt.until(0, this.requests.getSize());
        int first = intRangeUntil.getFirst();
        int last = intRangeUntil.getLast();
        if (first <= last) {
            while (true) {
                ((ContentInViewNode.Request) this.requests.content[first]).getContinuation().resumeWith(Result.constructor-impl(Unit.INSTANCE));
                if (first == last) {
                    break;
                } else {
                    first++;
                }
            }
        }
        this.requests.clear();
    }

    public final void resumeAndRemoveWhile(Function1<? super Rect, Boolean> block) {
        while (this.requests.getSize() != 0 && ((Boolean) block.invoke(((ContentInViewNode.Request) this.requests.last()).getCurrentBounds().invoke())).booleanValue()) {
            ((ContentInViewNode.Request) this.requests.removeAt(this.requests.getSize() - 1)).getContinuation().resumeWith(Result.constructor-impl(Unit.INSTANCE));
        }
    }
}
