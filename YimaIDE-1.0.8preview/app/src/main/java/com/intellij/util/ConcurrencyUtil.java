package com.intellij.util;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.UserDataHolderEx;
import com.intellij.util.ConcurrencyUtil;
import java.util.Collection;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ConcurrencyUtil {
    /* JADX WARN: Code duplicated, block: B:14:0x002a  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 4 && i != 5 && i != 8 && i != 9 && i != 23 && i != 25 && i != 27 && i != 37 && i != 41) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 4 && i != 5 && i != 8 && i != 9 && i != 23 && i != 25 && i != 27 && i != 37 && i != 41) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
                objArr[0] = "map";
                break;
            case 2:
            case 11:
            case 17:
                objArr[0] = "key";
                break;
            case 3:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 12:
            case 18:
                objArr[0] = "defaultValue";
                break;
            case 4:
            case 5:
            case 8:
            case 9:
            case 13:
            case 14:
            case 15:
            case 23:
            case 25:
            case 27:
            case 37:
            case 41:
                objArr[0] = "com/intellij/util/ConcurrencyUtil";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "ref";
                break;
            case 10:
            case 16:
                objArr[0] = "holder";
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 24:
            case 26:
            case 35:
            case 38:
                objArr[0] = "name";
                break;
            case 28:
                objArr[0] = "executor";
                break;
            case 29:
                objArr[0] = "unit";
                break;
            case 30:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[0] = "threads";
                break;
            case 32:
            case 34:
                objArr[0] = "futures";
                break;
            case 33:
                objArr[0] = "timeUnit";
                break;
            case 36:
            case 39:
            case 43:
            case 45:
                objArr[0] = "runnable";
                break;
            case 40:
                objArr[0] = "delegate";
                break;
            case 42:
            case 44:
                objArr[0] = "lock";
                break;
            case 46:
                objArr[0] = "task";
                break;
            default:
                objArr[0] = "tasks";
                break;
        }
        if (i == 4 || i == 5 || i == 8 || i == 9) {
            objArr[1] = "cacheOrGet";
        } else if (i == 23) {
            objArr[1] = "newSingleScheduledThreadExecutor";
        } else if (i == 25 || i == 27) {
            objArr[1] = "newNamedThreadFactory";
        } else if (i == 37) {
            objArr[1] = "underThreadNameRunnable";
        } else if (i != 41) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    objArr[1] = "computeIfAbsent";
                    break;
                default:
                    objArr[1] = "com/intellij/util/ConcurrencyUtil";
                    break;
            }
        } else {
            objArr[1] = "once";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "cacheOrGet";
                break;
            case 4:
            case 5:
            case 8:
            case 9:
            case 13:
            case 14:
            case 15:
            case 23:
            case 25:
            case 27:
            case 37:
            case 41:
                break;
            case 10:
            case 11:
            case 12:
                objArr[2] = "computeIfAbsent";
                break;
            case 16:
            case 17:
            case 18:
                objArr[2] = "slowPath";
                break;
            case 19:
            case 20:
                objArr[2] = "newSingleThreadExecutor";
                break;
            case 21:
            case 22:
                objArr[2] = "newSingleScheduledThreadExecutor";
                break;
            case 24:
            case 26:
                objArr[2] = "newNamedThreadFactory";
                break;
            case 28:
            case 29:
                objArr[2] = "awaitQuiescence";
                break;
            case 30:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[2] = "joinAll";
                break;
            case 32:
            case 33:
            case 34:
                objArr[2] = "getAll";
                break;
            case 35:
            case 36:
                objArr[2] = "underThreadNameRunnable";
                break;
            case 38:
            case 39:
                objArr[2] = "runUnderThreadName";
                break;
            case 40:
                objArr[2] = "once";
                break;
            case 42:
            case 43:
            case 44:
            case 45:
                objArr[2] = "withLock";
                break;
            case 46:
                objArr[2] = "manifestExceptionsIn";
                break;
            default:
                objArr[2] = "invokeAll";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 5 && i != 8 && i != 9 && i != 23 && i != 25 && i != 27 && i != 37 && i != 41) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ Thread a(String str, boolean z, int i, Runnable runnable) {
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(z);
        thread.setPriority(i);
        return thread;
    }

    public static /* synthetic */ Object b(Object obj, Object obj2) {
        return obj2 == null ? obj : obj2;
    }

    public static <K, V> V cacheOrGet(ConcurrentMap<K, V> concurrentMap, K k, V v) {
        if (concurrentMap == null) {
            $$$reportNull$$$0(1);
        }
        if (k == null) {
            $$$reportNull$$$0(2);
        }
        if (v == null) {
            $$$reportNull$$$0(3);
        }
        V v2 = concurrentMap.get(k);
        if (v2 != null) {
            return v2;
        }
        V vPutIfAbsent = concurrentMap.putIfAbsent(k, v);
        if (vPutIfAbsent != null) {
            v = vPutIfAbsent;
        }
        if (v == null) {
            $$$reportNull$$$0(5);
        }
        return v;
    }

    public static <T> T computeIfAbsent(UserDataHolder userDataHolder, Key<T> key, Supplier<? extends T> supplier) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(10);
        }
        if (key == null) {
            $$$reportNull$$$0(11);
        }
        if (supplier == null) {
            $$$reportNull$$$0(12);
        }
        T t = (T) userDataHolder.getUserData(key);
        if (t != null) {
            return t;
        }
        if (userDataHolder instanceof UserDataHolderEx) {
            T t2 = (T) ((UserDataHolderEx) userDataHolder).putUserDataIfAbsent(key, supplier.get());
            if (t2 == null) {
                $$$reportNull$$$0(14);
            }
            return t2;
        }
        T t3 = (T) slowPath(userDataHolder, key, supplier);
        if (t3 == null) {
            $$$reportNull$$$0(15);
        }
        return t3;
    }

    public static void getAll(long j, TimeUnit timeUnit, Collection<? extends Future<?>> collection) throws ExecutionException, InterruptedException, TimeoutException {
        if (timeUnit == null) {
            $$$reportNull$$$0(33);
        }
        if (collection == null) {
            $$$reportNull$$$0(34);
        }
        long jNanoTime = System.nanoTime() + timeUnit.toNanos(j);
        for (Future<?> future : collection) {
            long jNanoTime2 = jNanoTime - System.nanoTime();
            if (jNanoTime2 < 0) {
                throw new TimeoutException();
            }
            try {
                future.get(jNanoTime2, TimeUnit.NANOSECONDS);
            } catch (CancellationException unused) {
            }
        }
    }

    public static ThreadFactory newNamedThreadFactory(final String str, final boolean z, final int i) {
        if (str == null) {
            $$$reportNull$$$0(24);
        }
        return new ThreadFactory() { // from class: fp2
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return ConcurrencyUtil.a(str, z, i, runnable);
            }
        };
    }

    public static ExecutorService newSameThreadExecutorService() {
        return new SameThreadExecutorService();
    }

    public static ThreadPoolExecutor newSingleThreadExecutor(String str, int i) {
        if (str == null) {
            $$$reportNull$$$0(20);
        }
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), newNamedThreadFactory(str, true, i));
    }

    public static void runUnderThreadName(String str, Runnable runnable) {
        if (str == null) {
            $$$reportNull$$$0(38);
        }
        if (runnable == null) {
            $$$reportNull$$$0(39);
        }
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        if (str.equals(name)) {
            runnable.run();
            return;
        }
        threadCurrentThread.setName(str);
        try {
            runnable.run();
        } finally {
            threadCurrentThread.setName(name);
        }
    }

    private static <T> T slowPath(UserDataHolder userDataHolder, Key<T> key, Supplier<? extends T> supplier) {
        if (userDataHolder == null) {
            $$$reportNull$$$0(16);
        }
        if (key == null) {
            $$$reportNull$$$0(17);
        }
        if (supplier == null) {
            $$$reportNull$$$0(18);
        }
        synchronized (userDataHolder) {
            try {
                T t = (T) userDataHolder.getUserData(key);
                if (t != null) {
                    return t;
                }
                T t2 = supplier.get();
                userDataHolder.putUserData(key, t2);
                return t2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static <E extends Throwable> void withLock(Lock lock, ThrowableRunnable<E> throwableRunnable) throws Throwable {
        if (lock == null) {
            $$$reportNull$$$0(44);
        }
        if (throwableRunnable == null) {
            $$$reportNull$$$0(45);
        }
        lock.lock();
        try {
            throwableRunnable.run();
        } finally {
            lock.unlock();
        }
    }

    public static ThreadPoolExecutor newSingleThreadExecutor(String str) {
        if (str == null) {
            $$$reportNull$$$0(19);
        }
        return newSingleThreadExecutor(str, 5);
    }

    public static <T> T cacheOrGet(AtomicReference<T> atomicReference, final T t) {
        if (atomicReference == null) {
            $$$reportNull$$$0(6);
        }
        if (t == null) {
            $$$reportNull$$$0(7);
        }
        T t2 = atomicReference.get();
        if (t2 != null) {
            return t2;
        }
        T tUpdateAndGet = atomicReference.updateAndGet(new UnaryOperator() { // from class: ep2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ConcurrencyUtil.b(t, obj);
            }
        });
        if (tUpdateAndGet == null) {
            $$$reportNull$$$0(9);
        }
        return tUpdateAndGet;
    }
}
