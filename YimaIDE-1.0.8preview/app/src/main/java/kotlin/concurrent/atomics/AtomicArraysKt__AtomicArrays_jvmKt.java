package kotlin.concurrent.atomics;

import defpackage.a32;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u0003\u001a%\u0010\b\u001a\u00020\u0002*\u00020\u0001H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u0003\u001a%\u0010\u0000\u001a\u00020\t*\u00020\nH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u000b\u001a%\u0010\b\u001a\u00020\n*\u00020\tH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u000b\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000eH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u000f\u001a7\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\r0\u000e\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\fH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u000f\u001aU\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010\u0016\u001aU\u0010\u0019\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010\u001a\u001aU\u0010\u001b\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010\u001a\u001aU\u0010\u0010\u001a\u00020\u0011*\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001c0\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010\u001d\u001aU\u0010\u0019\u001a\u00020\u001c*\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001c0\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010\u001e\u001aU\u0010\u001b\u001a\u00020\u001c*\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001c0\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010\u001e\u001aa\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\r0\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010\u001f\u001aa\u0010\u0019\u001a\u0002H\r\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\r0\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010 \u001aa\u0010\u001b\u001a\u0002H\r\"\u0004\b\u0000\u0010\r*\b\u0012\u0004\u0012\u0002H\r0\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\r0\u0015H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0017b\u0002\b\u0007b\u0002\b\u0018ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0002¢\u0006\u0002\u0010 \u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006!"}, d2 = {"asJavaAtomicArray", "Ljava/util/concurrent/atomic/AtomicIntegerArray;", "Lkotlin/concurrent/atomics/AtomicIntArray;", "(Ljava/util/concurrent/atomic/AtomicIntegerArray;)Ljava/util/concurrent/atomic/AtomicIntegerArray;", "Lkotlin/SinceKotlin;", "version", "2.1", "Lkotlin/concurrent/atomics/ExperimentalAtomicApi;", "asKotlinAtomicArray", "Ljava/util/concurrent/atomic/AtomicLongArray;", "Lkotlin/concurrent/atomics/AtomicLongArray;", "(Ljava/util/concurrent/atomic/AtomicLongArray;)Ljava/util/concurrent/atomic/AtomicLongArray;", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "T", "Lkotlin/concurrent/atomics/AtomicArray;", "(Ljava/util/concurrent/atomic/AtomicReferenceArray;)Ljava/util/concurrent/atomic/AtomicReferenceArray;", "updateAt", "", "index", "", "transform", "Lkotlin/Function1;", "(Ljava/util/concurrent/atomic/AtomicIntegerArray;ILkotlin/jvm/functions/Function1;)V", "2.2", "Lkotlin/internal/InlineOnly;", "updateAndFetchAt", "(Ljava/util/concurrent/atomic/AtomicIntegerArray;ILkotlin/jvm/functions/Function1;)I", "fetchAndUpdateAt", "", "(Ljava/util/concurrent/atomic/AtomicLongArray;ILkotlin/jvm/functions/Function1;)V", "(Ljava/util/concurrent/atomic/AtomicLongArray;ILkotlin/jvm/functions/Function1;)J", "(Ljava/util/concurrent/atomic/AtomicReferenceArray;ILkotlin/jvm/functions/Function1;)V", "(Ljava/util/concurrent/atomic/AtomicReferenceArray;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/concurrent/atomics/AtomicArraysKt")
class AtomicArraysKt__AtomicArrays_jvmKt extends AtomicArraysKt__AtomicArrays_commonKt {
    public static final AtomicIntegerArray asJavaAtomicArray(AtomicIntegerArray atomicIntegerArray) {
        atomicIntegerArray.getClass();
        return atomicIntegerArray;
    }

    public static final AtomicIntegerArray asKotlinAtomicArray(AtomicIntegerArray atomicIntegerArray) {
        atomicIntegerArray.getClass();
        return atomicIntegerArray;
    }

    private static final long fetchAndUpdateAt(AtomicLongArray atomicLongArray, int i, Function1<? super Long, Long> function1) {
        atomicLongArray.getClass();
        function1.getClass();
        while (true) {
            long j = atomicLongArray.get(i);
            AtomicLongArray atomicLongArray2 = atomicLongArray;
            int i2 = i;
            if (atomicLongArray2.compareAndSet(i2, j, function1.invoke(Long.valueOf(j)).longValue())) {
                return j;
            }
            atomicLongArray = atomicLongArray2;
            i = i2;
        }
    }

    private static final long updateAndFetchAt(AtomicLongArray atomicLongArray, int i, Function1<? super Long, Long> function1) {
        atomicLongArray.getClass();
        function1.getClass();
        while (true) {
            long j = atomicLongArray.get(i);
            long jLongValue = function1.invoke(Long.valueOf(j)).longValue();
            AtomicLongArray atomicLongArray2 = atomicLongArray;
            int i2 = i;
            if (atomicLongArray2.compareAndSet(i2, j, jLongValue)) {
                return jLongValue;
            }
            atomicLongArray = atomicLongArray2;
            i = i2;
        }
    }

    private static final void updateAt(AtomicLongArray atomicLongArray, int i, Function1<? super Long, Long> function1) {
        atomicLongArray.getClass();
        function1.getClass();
        while (true) {
            long j = atomicLongArray.get(i);
            AtomicLongArray atomicLongArray2 = atomicLongArray;
            int i2 = i;
            if (atomicLongArray2.compareAndSet(i2, j, function1.invoke(Long.valueOf(j)).longValue())) {
                return;
            }
            atomicLongArray = atomicLongArray2;
            i = i2;
        }
    }

    public static final AtomicLongArray asJavaAtomicArray(AtomicLongArray atomicLongArray) {
        atomicLongArray.getClass();
        return atomicLongArray;
    }

    public static final AtomicLongArray asKotlinAtomicArray(AtomicLongArray atomicLongArray) {
        atomicLongArray.getClass();
        return atomicLongArray;
    }

    public static final <T> AtomicReferenceArray<T> asJavaAtomicArray(AtomicReferenceArray<T> atomicReferenceArray) {
        atomicReferenceArray.getClass();
        return atomicReferenceArray;
    }

    public static final <T> AtomicReferenceArray<T> asKotlinAtomicArray(AtomicReferenceArray<T> atomicReferenceArray) {
        atomicReferenceArray.getClass();
        return atomicReferenceArray;
    }

    private static final int fetchAndUpdateAt(AtomicIntegerArray atomicIntegerArray, int i, Function1<? super Integer, Integer> function1) {
        int i2;
        atomicIntegerArray.getClass();
        function1.getClass();
        do {
            i2 = atomicIntegerArray.get(i);
        } while (!atomicIntegerArray.compareAndSet(i, i2, function1.invoke(Integer.valueOf(i2)).intValue()));
        return i2;
    }

    private static final int updateAndFetchAt(AtomicIntegerArray atomicIntegerArray, int i, Function1<? super Integer, Integer> function1) {
        int i2;
        int iIntValue;
        atomicIntegerArray.getClass();
        function1.getClass();
        do {
            i2 = atomicIntegerArray.get(i);
            iIntValue = function1.invoke(Integer.valueOf(i2)).intValue();
        } while (!atomicIntegerArray.compareAndSet(i, i2, iIntValue));
        return iIntValue;
    }

    private static final void updateAt(AtomicIntegerArray atomicIntegerArray, int i, Function1<? super Integer, Integer> function1) {
        int i2;
        atomicIntegerArray.getClass();
        function1.getClass();
        do {
            i2 = atomicIntegerArray.get(i);
        } while (!atomicIntegerArray.compareAndSet(i, i2, function1.invoke(Integer.valueOf(i2)).intValue()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void updateAt(AtomicReferenceArray<T> atomicReferenceArray, int i, Function1<? super T, ? extends T> function1) {
        a32 a32Var;
        atomicReferenceArray.getClass();
        function1.getClass();
        do {
            a32Var = (Object) atomicReferenceArray.get(i);
        } while (!atomicReferenceArray.compareAndSet(i, a32Var, function1.invoke(a32Var)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.lang.Object] */
    private static final <T> T fetchAndUpdateAt(AtomicReferenceArray<T> atomicReferenceArray, int i, Function1<? super T, ? extends T> function1) {
        ?? r0;
        atomicReferenceArray.getClass();
        function1.getClass();
        do {
            r0 = (Object) atomicReferenceArray.get(i);
        } while (!atomicReferenceArray.compareAndSet(i, r0, function1.invoke(r0)));
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> T updateAndFetchAt(AtomicReferenceArray<T> atomicReferenceArray, int i, Function1<? super T, ? extends T> function1) {
        a32 a32Var;
        T tInvoke;
        atomicReferenceArray.getClass();
        function1.getClass();
        do {
            a32Var = (Object) atomicReferenceArray.get(i);
            tInvoke = function1.invoke(a32Var);
        } while (!atomicReferenceArray.compareAndSet(i, a32Var, tInvoke));
        return tInvoke;
    }
}
