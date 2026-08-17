package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.incremental.classpathDiff.InMemoryCacheWithEviction;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001%B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0012\u001a\u00020\u0013J'\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u0015\u001a\u00028\u00002\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0017¢\u0006\u0002\u0010\u0018J\u0006\u0010\u0019\u001a\u00020\u0013J\u0017\u0010\u001c\u001a\u00020\u00132\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\nH\u0082\bJ\"\u0010\u001e\u001a\u0002H\u0002\"\u0004\b\u0002\u0010\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\nH\u0082\b¢\u0006\u0002\u0010\u001fJ\u001b\u0010 \u001a\u00020!2\u0006\u0010\u0015\u001a\u00028\u0000H\u0001b\u0002\b$¢\u0006\u0004\b\"\u0010#R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/InMemoryCacheWithEviction;", "KEY", "VALUE", "", "maxTimePeriodsToKeepStrongReferences", "", "maxTimePeriodsToKeepSoftReferences", "maxMemoryUsageRatioToKeepStrongReferences", "", "memoryUsageRatio", "Lkotlin/Function0;", "<init>", "(IIDLkotlin/jvm/functions/Function0;)V", "currentTimePeriod", "Ljava/util/concurrent/atomic/AtomicInteger;", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/CacheEntryValue;", "newTimePeriod", "", "computeIfAbsent", "key", "valueProvider", "Lkotlin/Function1;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "evictEntries", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "writeLock", "action", "readLock", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getEntryState", "Lorg/jetbrains/kotlin/incremental/classpathDiff/InMemoryCacheWithEviction$EntryState;", "getEntryState$org_jetbrains_kotlin_incremental_compilation_impl", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/incremental/classpathDiff/InMemoryCacheWithEviction$EntryState;", "Lcom/google/common/annotations/VisibleForTesting;", "EntryState", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InMemoryCacheWithEviction<KEY, VALUE> {
    private final ConcurrentHashMap<KEY, CacheEntryValue<VALUE>> cache;
    private final AtomicInteger currentTimePeriod;
    private final ReentrantReadWriteLock lock;
    private final double maxMemoryUsageRatioToKeepStrongReferences;
    private final int maxTimePeriodsToKeepSoftReferences;
    private final int maxTimePeriodsToKeepStrongReferences;
    private final Function0<Double> memoryUsageRatio;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0081\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006Ê\u0001\u0002\b\b¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/InMemoryCacheWithEviction$EntryState;", "", "<init>", "(Ljava/lang/String;I)V", "STRONG_REF", "SOFT_REF", "ABSENT", "org.jetbrains.kotlin:incremental-compilation-impl", "Lcom/google/common/annotations/VisibleForTesting;"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public enum EntryState {
        STRONG_REF,
        SOFT_REF,
        ABSENT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<EntryState> getEntries() {
            return $ENTRIES;
        }
    }

    public InMemoryCacheWithEviction(int i, int i2, double d, Function0<Double> function0) {
        function0.getClass();
        this.maxTimePeriodsToKeepStrongReferences = i;
        this.maxTimePeriodsToKeepSoftReferences = i2;
        this.maxMemoryUsageRatioToKeepStrongReferences = d;
        this.memoryUsageRatio = function0;
        this.currentTimePeriod = new AtomicInteger(0);
        this.cache = new ConcurrentHashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }

    public static double c() {
        Runtime runtime = Runtime.getRuntime();
        return 1.0d - (runtime.freeMemory() / runtime.totalMemory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CacheEntryValue computeIfAbsent$lambda$0$0(Function1 function1, Object obj, InMemoryCacheWithEviction inMemoryCacheWithEviction, Object obj2) {
        return new CacheEntryValue(function1.invoke(obj), inMemoryCacheWithEviction.currentTimePeriod.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CacheEntryValue computeIfAbsent$lambda$0$1(Function1 function1, Object obj) {
        return (CacheEntryValue) function1.invoke(obj);
    }

    public final VALUE computeIfAbsent(final KEY key, final Function1<? super KEY, ? extends VALUE> valueProvider) {
        VALUE value;
        valueProvider.getClass();
        this.lock.readLock().lock();
        try {
            ConcurrentHashMap<KEY, CacheEntryValue<VALUE>> concurrentHashMap = this.cache;
            final Function1 function1 = new Function1() { // from class: rl6
                public final Object invoke(Object obj) {
                    return InMemoryCacheWithEviction.computeIfAbsent$lambda$0$0(valueProvider, key, this, obj);
                }
            };
            CacheEntryValue<VALUE> cacheEntryValueComputeIfAbsent = concurrentHashMap.computeIfAbsent(key, new Function() { // from class: sl6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return InMemoryCacheWithEviction.computeIfAbsent$lambda$0$1(function1, obj);
                }
            });
            cacheEntryValueComputeIfAbsent.getClass();
            CacheEntryValue<VALUE> cacheEntryValue = cacheEntryValueComputeIfAbsent;
            synchronized (cacheEntryValue) {
                try {
                    value = (VALUE) cacheEntryValue.get();
                    if (value == null) {
                        value = (VALUE) valueProvider.invoke(key);
                    }
                    cacheEntryValue.setStrongReference(value, this.currentTimePeriod.get());
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.lock.readLock().unlock();
            return value;
        } catch (Throwable th2) {
            this.lock.readLock().unlock();
            throw th2;
        }
    }

    public final void evictEntries() {
        this.lock.writeLock().lock();
        try {
            int i = (this.currentTimePeriod.get() - this.maxTimePeriodsToKeepStrongReferences) + 1;
            int i2 = i - this.maxTimePeriodsToKeepSoftReferences;
            double dDoubleValue = ((Number) this.memoryUsageRatio.invoke()).doubleValue();
            double d = this.maxMemoryUsageRatioToKeepStrongReferences;
            ConcurrentHashMap<KEY, CacheEntryValue<VALUE>> concurrentHashMap = this.cache;
            if (dDoubleValue > d) {
                Collection<CacheEntryValue<VALUE>> collectionValues = concurrentHashMap.values();
                collectionValues.getClass();
                Iterator<T> it = collectionValues.iterator();
                while (it.hasNext()) {
                    ((CacheEntryValue) it.next()).updateToSoftReference();
                }
            } else {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry<KEY, CacheEntryValue<VALUE>> entry : concurrentHashMap.entrySet()) {
                    if (entry.getValue().getLastUsed() < i) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                Iterator it2 = linkedHashMap.values().iterator();
                while (it2.hasNext()) {
                    ((CacheEntryValue) it2.next()).updateToSoftReference();
                }
            }
            ConcurrentHashMap<KEY, CacheEntryValue<VALUE>> concurrentHashMap2 = this.cache;
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry<KEY, CacheEntryValue<VALUE>> entry2 : concurrentHashMap2.entrySet()) {
                CacheEntryValue<VALUE> value = entry2.getValue();
                if (value.getLastUsed() < i2 || value.valueWasGarbageCollected()) {
                    linkedHashMap2.put(entry2.getKey(), entry2.getValue());
                }
            }
            Iterator it3 = linkedHashMap2.keySet().iterator();
            while (it3.hasNext()) {
                this.cache.remove(it3.next());
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public final EntryState getEntryState$org_jetbrains_kotlin_incremental_compilation_impl(KEY key) {
        EntryState entryState;
        this.lock.readLock().lock();
        try {
            CacheEntryValue<VALUE> cacheEntryValue = this.cache.get(key);
            if (cacheEntryValue != null) {
                synchronized (cacheEntryValue) {
                    try {
                        entryState = cacheEntryValue.valueIsAStrongReference() ? EntryState.STRONG_REF : EntryState.SOFT_REF;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (entryState == null) {
                    entryState = EntryState.ABSENT;
                }
            } else {
                entryState = EntryState.ABSENT;
            }
            this.lock.readLock().unlock();
            return entryState;
        } catch (Throwable th2) {
            this.lock.readLock().unlock();
            throw th2;
        }
    }

    public final void newTimePeriod() {
        this.currentTimePeriod.incrementAndGet();
    }

    public /* synthetic */ InMemoryCacheWithEviction(int i, int i2, double d, Function0 function0, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, d, (i3 & 8) != 0 ? new Function0() { // from class: ql6
            public final Object invoke() {
                return Double.valueOf(InMemoryCacheWithEviction.c());
            }
        } : function0);
    }
}
