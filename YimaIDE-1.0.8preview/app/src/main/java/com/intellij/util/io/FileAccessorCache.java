package com.intellij.util.io;

import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.util.containers.SLRUMap;
import com.intellij.util.containers.hash.EqualityPolicy;
import com.intellij.util.io.FileAccessorCache;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class FileAccessorCache<K, T> implements EqualityPolicy<K> {
    private static final int SEGMENTS_COUNT = Math.max(Runtime.getRuntime().availableProcessors() / 8, 1);
    private final SLRUMap<K, Handle<T>> cache;
    private final List<Handle<T>> handlersToBeDisposed = new ArrayList();
    private final ReentrantLock cacheLock = new ReentrantLock();
    private final ReentrantLock[] resourceAllocationLocks = new ReentrantLock[SEGMENTS_COUNT];

    public static final class Handle<T> extends ResourceHandle<T> {
        private final Object key;
        private final FileAccessorCache<?, T> owner;
        private final AtomicInteger refCount;
        private final T resource;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 3 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "fileAccessor";
            } else if (i == 2) {
                objArr[0] = "owner";
            } else if (i != 3) {
                objArr[0] = "key";
            } else {
                objArr[0] = "com/intellij/util/io/FileAccessorCache$Handle";
            }
            if (i != 3) {
                objArr[1] = "com/intellij/util/io/FileAccessorCache$Handle";
            } else {
                objArr[1] = "get";
            }
            if (i != 3) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 3) {
                throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private <K> Handle(K k, T t, FileAccessorCache<K, T> fileAccessorCache) {
            if (k == null) {
                $$$reportNull$$$0(0);
            }
            if (t == null) {
                $$$reportNull$$$0(1);
            }
            if (fileAccessorCache == 0) {
                $$$reportNull$$$0(2);
            }
            this.refCount = new AtomicInteger(1);
            this.key = k;
            this.resource = t;
            this.owner = fileAccessorCache;
        }

        public void allocate() {
            this.refCount.incrementAndGet();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            release();
        }

        @Override // com.intellij.util.io.ResourceHandle
        public T get() {
            T t = this.resource;
            if (t == null) {
                $$$reportNull$$$0(3);
            }
            return t;
        }

        public void release() {
            if (this.refCount.decrementAndGet() == 0) {
                ((FileAccessorCache) this.owner).cacheLock.lock();
                try {
                    ((FileAccessorCache) this.owner).handlersToBeDisposed.add(this);
                } finally {
                    ((FileAccessorCache) this.owner).cacheLock.unlock();
                }
            }
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 2 || i == 3) ? 3 : 2];
        if (i == 2) {
            objArr[0] = "key";
        } else if (i != 3) {
            objArr[0] = "com/intellij/util/io/FileAccessorCache";
        } else {
            objArr[0] = "lambda";
        }
        if (i == 2 || i == 3) {
            objArr[1] = "com/intellij/util/io/FileAccessorCache";
        } else {
            objArr[1] = "get";
        }
        if (i == 2 || i == 3) {
            objArr[2] = "withUpdateLock";
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    public FileAccessorCache(int i, int i2) {
        this.cache = new SLRUMap<K, Handle<T>>(i, i2, this) { // from class: com.intellij.util.io.FileAccessorCache.1
            private static /* synthetic */ void $$$reportNull$$$0(int i3) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/intellij/util/io/FileAccessorCache$1", "onDropFromCache"));
            }

            @Override // com.intellij.util.containers.SLRUMap
            public void onDropFromCache(K k, Handle<T> handle) {
                if (handle == null) {
                    $$$reportNull$$$0(0);
                }
                handle.release();
            }
        };
        int i3 = 0;
        while (true) {
            ReentrantLock[] reentrantLockArr = this.resourceAllocationLocks;
            if (i3 >= reentrantLockArr.length) {
                return;
            }
            reentrantLockArr[i3] = new ReentrantLock();
            i3++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object a(FileAccessorCache fileAccessorCache, Handle handle) throws IOException {
        fileAccessorCache.getClass();
        fileAccessorCache.disposeAccessor(handle.resource);
        return null;
    }

    public static /* synthetic */ Handle b(FileAccessorCache fileAccessorCache, Object obj) {
        Handle<T> ifCached = fileAccessorCache.getIfCached(obj);
        return ifCached != null ? ifCached : fileAccessorCache.createHandle(obj);
    }

    private Handle<T> createHandle(K k) {
        try {
            Handle<T> handle = new Handle<>(k, createAccessor(k), this);
            handle.allocate();
            this.cacheLock.lock();
            try {
                this.cache.put(k, handle);
                return handle;
            } finally {
                this.cacheLock.unlock();
            }
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void runPostponedDisposals() throws Exception {
        this.cacheLock.lock();
        try {
            if (this.handlersToBeDisposed.isEmpty()) {
                this.cacheLock.unlock();
                return;
            }
            ArrayList<Handle> arrayList = new ArrayList(this.handlersToBeDisposed);
            this.handlersToBeDisposed.clear();
            this.cacheLock.unlock();
            IOException iOException = null;
            for (final Handle handle : arrayList) {
                try {
                    withUpdateLock(handle.key, new ThrowableComputable() { // from class: so4
                        public final Object compute() {
                            return FileAccessorCache.a(this.a, handle);
                        }
                    });
                } catch (IOException e) {
                    if (iOException == null) {
                        iOException = e;
                    } else {
                        iOException.addSuppressed(e);
                    }
                }
            }
            if (iOException == null) {
                return;
            }
            u8i.a(iOException);
        } catch (Throwable th) {
            this.cacheLock.unlock();
            throw th;
        }
    }

    private <R, E extends Exception> R withUpdateLock(K k, ThrowableComputable<R, E> throwableComputable) throws Exception {
        if (k == null) {
            $$$reportNull$$$0(2);
        }
        if (throwableComputable == null) {
            $$$reportNull$$$0(3);
        }
        int iAbs = Math.abs(getHashCode(k));
        ReentrantLock[] reentrantLockArr = this.resourceAllocationLocks;
        ReentrantLock reentrantLock = reentrantLockArr[iAbs % reentrantLockArr.length];
        reentrantLock.lock();
        try {
            return (R) throwableComputable.compute();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void clear() throws Exception {
        try {
            this.cacheLock.lock();
            try {
                this.cache.clear();
                this.cacheLock.unlock();
                runPostponedDisposals();
            } catch (Throwable th) {
                this.cacheLock.unlock();
                throw th;
            }
        } catch (Throwable th2) {
            runPostponedDisposals();
            throw th2;
        }
    }

    public abstract T createAccessor(K k) throws IOException;

    public abstract void disposeAccessor(T t) throws IOException;

    public final Handle<T> get(final K k) throws Exception {
        Handle<T> ifCached = getIfCached(k);
        if (ifCached != null) {
            return ifCached;
        }
        try {
            Handle<T> handle = (Handle) withUpdateLock(k, new ThrowableComputable() { // from class: to4
                public final Object compute() {
                    return FileAccessorCache.b(this.a, k);
                }
            });
            runPostponedDisposals();
            if (handle == null) {
                $$$reportNull$$$0(1);
            }
            return handle;
        } catch (Throwable th) {
            runPostponedDisposals();
            throw th;
        }
    }

    @Override // com.intellij.util.containers.hash.EqualityPolicy
    public int getHashCode(K k) {
        return k.hashCode();
    }

    public Handle<T> getIfCached(K k) {
        this.cacheLock.lock();
        try {
            Handle<T> handle = this.cache.get(k);
            if (handle != null) {
                handle.allocate();
            }
            return handle;
        } finally {
            this.cacheLock.unlock();
        }
    }

    @Override // com.intellij.util.containers.hash.EqualityPolicy
    public boolean isEqual(K k, K k2) {
        return k.equals(k2);
    }

    public boolean remove(K k) throws Exception {
        try {
            this.cacheLock.lock();
            try {
                boolean zRemove = this.cache.remove(k);
                this.cacheLock.unlock();
                runPostponedDisposals();
                return zRemove;
            } catch (Throwable th) {
                this.cacheLock.unlock();
                throw th;
            }
        } catch (Throwable th2) {
            runPostponedDisposals();
            throw th2;
        }
    }
}
