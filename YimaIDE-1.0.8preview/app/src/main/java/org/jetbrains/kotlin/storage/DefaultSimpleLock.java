package org.jetbrains.kotlin.storage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0002\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/storage/DefaultSimpleLock;", "Lorg/jetbrains/kotlin/storage/SimpleLock;", "lock", "Ljava/util/concurrent/locks/Lock;", "<init>", "(Ljava/util/concurrent/locks/Lock;)V", "getLock", "()Ljava/util/concurrent/locks/Lock;", HttpUrl.FRAGMENT_ENCODE_SET, "unlock", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class DefaultSimpleLock implements SimpleLock {
    private final Lock lock;

    public /* synthetic */ DefaultSimpleLock(Lock lock, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ReentrantLock() : lock);
    }

    public final Lock getLock() {
        return this.lock;
    }

    public void lock() {
        this.lock.lock();
    }

    public void unlock() {
        this.lock.unlock();
    }

    public DefaultSimpleLock(Lock lock) {
        lock.getClass();
        this.lock = lock;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public DefaultSimpleLock() {
        Lock lock = null;
        this(lock, 1, lock);
    }
}
