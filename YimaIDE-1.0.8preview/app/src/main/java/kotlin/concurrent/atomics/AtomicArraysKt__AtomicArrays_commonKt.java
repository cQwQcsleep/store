package kotlin.concurrent.atomics;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a-\u0010\u000b\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\n¢\u0006\u0002\u0010\r\u001a-\u0010\u000e\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\n¢\u0006\u0002\u0010\r\u001a-\u0010\u000f\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\n¢\u0006\u0002\u0010\r\u001a-\u0010\u0010\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\n¢\u0006\u0002\u0010\r\u001a@\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00130\u0005H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a-\u0010\u000b\u001a\u00020\u0013*\u00020\u00122\u0006\u0010\f\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\n¢\u0006\u0002\u0010\u0015\u001a-\u0010\u000e\u001a\u00020\u0013*\u00020\u00122\u0006\u0010\f\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\n¢\u0006\u0002\u0010\u0015\u001a-\u0010\u000f\u001a\u00020\u0013*\u00020\u00122\u0006\u0010\f\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\n¢\u0006\u0002\u0010\u0015\u001a-\u0010\u0010\u001a\u00020\u0013*\u00020\u00122\u0006\u0010\f\u001a\u00020\u0003H\u0087\u0080\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\n¢\u0006\u0002\u0010\u0015\u001aN\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0017\"\u0006\b\u0000\u0010\u0018\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00180\u0005H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\tb\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a9\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00180\u0017\"\u0006\b\u0000\u0010\u0018\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004b\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u001cb\u0002\b\n¢\u0006\u0002\u0010\u001b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"AtomicIntArray", "Lkotlin/concurrent/atomics/AtomicIntArray;", "size", "", "init", "Lkotlin/Function1;", "(ILkotlin/jvm/functions/Function1;)Ljava/util/concurrent/atomic/AtomicIntegerArray;", "Lkotlin/SinceKotlin;", "version", "2.1", "Lkotlin/concurrent/atomics/ExperimentalAtomicApi;", "fetchAndIncrementAt", "index", "(Ljava/util/concurrent/atomic/AtomicIntegerArray;I)I", "incrementAndFetchAt", "decrementAndFetchAt", "fetchAndDecrementAt", "AtomicLongArray", "Lkotlin/concurrent/atomics/AtomicLongArray;", "", "(ILkotlin/jvm/functions/Function1;)Ljava/util/concurrent/atomic/AtomicLongArray;", "(Ljava/util/concurrent/atomic/AtomicLongArray;I)J", "AtomicArray", "Lkotlin/concurrent/atomics/AtomicArray;", "T", "(ILkotlin/jvm/functions/Function1;)Ljava/util/concurrent/atomic/AtomicReferenceArray;", "atomicArrayOfNulls", "(I)Ljava/util/concurrent/atomic/AtomicReferenceArray;", "2.2", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/concurrent/atomics/AtomicArraysKt")
class AtomicArraysKt__AtomicArrays_commonKt {
    public static final /* synthetic */ <T> AtomicReferenceArray<T> AtomicArray(int i, Function1<? super Integer, ? extends T> function1) {
        function1.getClass();
        Intrinsics.reifiedOperationMarker(0, "T");
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = function1.invoke(Integer.valueOf(i2));
        }
        return new AtomicReferenceArray<>(objArr);
    }

    public static final AtomicIntegerArray AtomicIntArray(int i, Function1<? super Integer, Integer> function1) {
        function1.getClass();
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = function1.invoke(Integer.valueOf(i2)).intValue();
        }
        return new AtomicIntegerArray(iArr);
    }

    public static final AtomicLongArray AtomicLongArray(int i, Function1<? super Integer, Long> function1) {
        function1.getClass();
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = function1.invoke(Integer.valueOf(i2)).longValue();
        }
        return new AtomicLongArray(jArr);
    }

    public static final /* synthetic */ <T> AtomicReferenceArray<T> atomicArrayOfNulls(int i) {
        Intrinsics.reifiedOperationMarker(0, "T?");
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        return new AtomicReferenceArray<>(objArr);
    }

    public static final long decrementAndFetchAt(AtomicLongArray atomicLongArray, int i) {
        atomicLongArray.getClass();
        return atomicLongArray.addAndGet(i, -1L);
    }

    public static final long fetchAndDecrementAt(AtomicLongArray atomicLongArray, int i) {
        atomicLongArray.getClass();
        return atomicLongArray.getAndAdd(i, -1L);
    }

    public static final long fetchAndIncrementAt(AtomicLongArray atomicLongArray, int i) {
        atomicLongArray.getClass();
        return atomicLongArray.getAndAdd(i, 1L);
    }

    public static final long incrementAndFetchAt(AtomicLongArray atomicLongArray, int i) {
        atomicLongArray.getClass();
        return atomicLongArray.addAndGet(i, 1L);
    }

    public static final int decrementAndFetchAt(AtomicIntegerArray atomicIntegerArray, int i) {
        atomicIntegerArray.getClass();
        return atomicIntegerArray.addAndGet(i, -1);
    }

    public static final int fetchAndDecrementAt(AtomicIntegerArray atomicIntegerArray, int i) {
        atomicIntegerArray.getClass();
        return atomicIntegerArray.getAndAdd(i, -1);
    }

    public static final int fetchAndIncrementAt(AtomicIntegerArray atomicIntegerArray, int i) {
        atomicIntegerArray.getClass();
        return atomicIntegerArray.getAndAdd(i, 1);
    }

    public static final int incrementAndFetchAt(AtomicIntegerArray atomicIntegerArray, int i) {
        atomicIntegerArray.getClass();
        return atomicIntegerArray.addAndGet(i, 1);
    }
}
