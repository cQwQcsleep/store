package kotlin.concurrent.atomics;

import defpackage.a32;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u0003\u001a%\u0010\b\u001a\u00020\u0002*\u00020\u0001H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u0003\u001a%\u0010\u0000\u001a\u00020\t*\u00020\nH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u000b\u001a%\u0010\b\u001a\u00020\n*\u00020\tH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u000b\u001a%\u0010\u0000\u001a\u00020\f*\u00020\rH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u000e\u001a%\u0010\b\u001a\u00020\r*\u00020\fH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u000e\u001a7\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u0011H\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u0012\u001a7\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0011\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u000fH\u0087\u0080\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006b\u0002\b\u0007¢\u0006\u0002\u0010\u0012\u001aM\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010\u0018\u001aM\u0010\u001b\u001a\u00020\u0017*\u00020\u00022\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010\u001c\u001aM\u0010\u001d\u001a\u00020\u0017*\u00020\u00022\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010\u001c\u001aM\u0010\u0013\u001a\u00020\u0014*\u00020\n2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010\u001f\u001aM\u0010\u001b\u001a\u00020\u001e*\u00020\n2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010 \u001aM\u0010\u001d\u001a\u00020\u001e*\u00020\n2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010 \u001aY\u0010\u0013\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00112\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00100\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010!\u001aY\u0010\u001b\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00112\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00100\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010\"\u001aY\u0010\u001d\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00112\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00100\u0016H\u0087\u0088\u0004b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0019b\u0002\b\u0007b\u0002\b\u001aø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0002¢\u0006\u0002\u0010\"\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006#"}, d2 = {"asJavaAtomic", "Ljava/util/concurrent/atomic/AtomicInteger;", "Lkotlin/concurrent/atomics/AtomicInt;", "(Ljava/util/concurrent/atomic/AtomicInteger;)Ljava/util/concurrent/atomic/AtomicInteger;", "Lkotlin/SinceKotlin;", "version", "2.1", "Lkotlin/concurrent/atomics/ExperimentalAtomicApi;", "asKotlinAtomic", "Ljava/util/concurrent/atomic/AtomicLong;", "Lkotlin/concurrent/atomics/AtomicLong;", "(Ljava/util/concurrent/atomic/AtomicLong;)Ljava/util/concurrent/atomic/AtomicLong;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Lkotlin/concurrent/atomics/AtomicBoolean;", "(Ljava/util/concurrent/atomic/AtomicBoolean;)Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicReference;", "T", "Lkotlin/concurrent/atomics/AtomicReference;", "(Ljava/util/concurrent/atomic/AtomicReference;)Ljava/util/concurrent/atomic/AtomicReference;", "update", "", "transform", "Lkotlin/Function1;", "", "(Ljava/util/concurrent/atomic/AtomicInteger;Lkotlin/jvm/functions/Function1;)V", "2.2", "Lkotlin/internal/InlineOnly;", "fetchAndUpdate", "(Ljava/util/concurrent/atomic/AtomicInteger;Lkotlin/jvm/functions/Function1;)I", "updateAndFetch", "", "(Ljava/util/concurrent/atomic/AtomicLong;Lkotlin/jvm/functions/Function1;)V", "(Ljava/util/concurrent/atomic/AtomicLong;Lkotlin/jvm/functions/Function1;)J", "(Ljava/util/concurrent/atomic/AtomicReference;Lkotlin/jvm/functions/Function1;)V", "(Ljava/util/concurrent/atomic/AtomicReference;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/concurrent/atomics/AtomicsKt")
class AtomicsKt__Atomics_jvmKt extends AtomicsKt__Atomics_commonKt {
    public static final AtomicBoolean asJavaAtomic(AtomicBoolean atomicBoolean) {
        atomicBoolean.getClass();
        return atomicBoolean;
    }

    public static final AtomicBoolean asKotlinAtomic(AtomicBoolean atomicBoolean) {
        atomicBoolean.getClass();
        return atomicBoolean;
    }

    private static final int fetchAndUpdate(AtomicInteger atomicInteger, Function1<? super Integer, Integer> function1) {
        int i;
        atomicInteger.getClass();
        function1.getClass();
        do {
            i = atomicInteger.get();
        } while (!atomicInteger.compareAndSet(i, function1.invoke(Integer.valueOf(i)).intValue()));
        return i;
    }

    private static final void update(AtomicInteger atomicInteger, Function1<? super Integer, Integer> function1) {
        int i;
        atomicInteger.getClass();
        function1.getClass();
        do {
            i = atomicInteger.get();
        } while (!atomicInteger.compareAndSet(i, function1.invoke(Integer.valueOf(i)).intValue()));
    }

    private static final int updateAndFetch(AtomicInteger atomicInteger, Function1<? super Integer, Integer> function1) {
        int i;
        int iIntValue;
        atomicInteger.getClass();
        function1.getClass();
        do {
            i = atomicInteger.get();
            iIntValue = function1.invoke(Integer.valueOf(i)).intValue();
        } while (!atomicInteger.compareAndSet(i, iIntValue));
        return iIntValue;
    }

    public static final AtomicInteger asJavaAtomic(AtomicInteger atomicInteger) {
        atomicInteger.getClass();
        return atomicInteger;
    }

    public static final AtomicInteger asKotlinAtomic(AtomicInteger atomicInteger) {
        atomicInteger.getClass();
        return atomicInteger;
    }

    public static final AtomicLong asJavaAtomic(AtomicLong atomicLong) {
        atomicLong.getClass();
        return atomicLong;
    }

    public static final AtomicLong asKotlinAtomic(AtomicLong atomicLong) {
        atomicLong.getClass();
        return atomicLong;
    }

    public static final <T> AtomicReference<T> asJavaAtomic(AtomicReference<T> atomicReference) {
        atomicReference.getClass();
        return atomicReference;
    }

    public static final <T> AtomicReference<T> asKotlinAtomic(AtomicReference<T> atomicReference) {
        atomicReference.getClass();
        return atomicReference;
    }

    private static final long fetchAndUpdate(AtomicLong atomicLong, Function1<? super Long, Long> function1) {
        long j;
        atomicLong.getClass();
        function1.getClass();
        do {
            j = atomicLong.get();
        } while (!atomicLong.compareAndSet(j, function1.invoke(Long.valueOf(j)).longValue()));
        return j;
    }

    private static final void update(AtomicLong atomicLong, Function1<? super Long, Long> function1) {
        long j;
        atomicLong.getClass();
        function1.getClass();
        do {
            j = atomicLong.get();
        } while (!atomicLong.compareAndSet(j, function1.invoke(Long.valueOf(j)).longValue()));
    }

    private static final long updateAndFetch(AtomicLong atomicLong, Function1<? super Long, Long> function1) {
        long j;
        long jLongValue;
        atomicLong.getClass();
        function1.getClass();
        do {
            j = atomicLong.get();
            jLongValue = function1.invoke(Long.valueOf(j)).longValue();
        } while (!atomicLong.compareAndSet(j, jLongValue));
        return jLongValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void update(AtomicReference<T> atomicReference, Function1<? super T, ? extends T> function1) {
        a32 a32Var;
        atomicReference.getClass();
        function1.getClass();
        do {
            a32Var = (Object) atomicReference.get();
        } while (!atomicReference.compareAndSet(a32Var, function1.invoke(a32Var)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.lang.Object] */
    private static final <T> T fetchAndUpdate(AtomicReference<T> atomicReference, Function1<? super T, ? extends T> function1) {
        ?? r0;
        atomicReference.getClass();
        function1.getClass();
        do {
            r0 = (Object) atomicReference.get();
        } while (!atomicReference.compareAndSet(r0, function1.invoke(r0)));
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> T updateAndFetch(AtomicReference<T> atomicReference, Function1<? super T, ? extends T> function1) {
        a32 a32Var;
        T tInvoke;
        atomicReference.getClass();
        function1.getClass();
        do {
            a32Var = (Object) atomicReference.get();
            tInvoke = function1.invoke(a32Var);
        } while (!atomicReference.compareAndSet(a32Var, tInvoke));
        return tInvoke;
    }
}
