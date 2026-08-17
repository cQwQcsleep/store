package net.schmizz.concurrent;

import java.lang.Throwable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import net.schmizz.sshj.common.LoggerFactory;
import org.slf4j.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class Promise<V, T extends Throwable> {
    private final ExceptionChainer<T> chainer;
    private final Condition cond;
    private final ReentrantLock lock;
    private final Logger log;
    private final String name;
    private T pendingEx;
    private V val;

    public Promise(String str, ExceptionChainer<T> exceptionChainer, ReentrantLock reentrantLock, LoggerFactory loggerFactory) {
        this.name = str;
        this.chainer = exceptionChainer;
        reentrantLock = reentrantLock == null ? new ReentrantLock() : reentrantLock;
        this.lock = reentrantLock;
        this.log = loggerFactory.getLogger(getClass());
        this.cond = reentrantLock.newCondition();
    }

    public void clear() {
        this.lock.lock();
        try {
            this.pendingEx = null;
            deliver(null);
        } finally {
            this.lock.unlock();
        }
    }

    public void deliver(V v) {
        this.lock.lock();
        try {
            this.log.debug("Setting <<{}>> to `{}`", this.name, v);
            this.val = v;
            this.cond.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

    public void deliverError(Throwable th) {
        this.lock.lock();
        try {
            this.pendingEx = (T) this.chainer.chain(th);
            this.cond.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

    public boolean hasWaiters() {
        this.lock.lock();
        try {
            return this.lock.hasWaiters(this.cond);
        } finally {
            this.lock.unlock();
        }
    }

    public boolean inError() {
        this.lock.lock();
        try {
            return this.pendingEx != null;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isDelivered() {
        this.lock.lock();
        try {
            return this.pendingEx == null && this.val != null;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isFulfilled() {
        this.lock.lock();
        try {
            return (this.pendingEx == null && this.val == null) ? false : true;
        } finally {
            this.lock.unlock();
        }
    }

    public void lock() {
        this.lock.lock();
    }

    public V retrieve(long j, TimeUnit timeUnit) throws Throwable {
        V vTryRetrieve = tryRetrieve(j, timeUnit);
        if (vTryRetrieve != null) {
            return vTryRetrieve;
        }
        throw this.chainer.chain(new TimeoutException("Timeout expired: " + j + " " + timeUnit));
    }

    public String toString() {
        return this.name;
    }

    public V tryRetrieve(long j, TimeUnit timeUnit) throws Throwable {
        this.lock.lock();
        try {
            try {
                T t = this.pendingEx;
                if (t != null) {
                    throw t;
                }
                V v = this.val;
                if (v != null) {
                    this.lock.unlock();
                    return v;
                }
                this.log.debug("Awaiting <<{}>>", this.name);
                if (j == 0) {
                    while (this.val == null && this.pendingEx == null) {
                        this.cond.await();
                    }
                } else if (!this.cond.await(j, timeUnit)) {
                    this.lock.unlock();
                    return null;
                }
                T t2 = this.pendingEx;
                if (t2 != null) {
                    this.log.error("<<{}>> woke to: {}", this.name, t2.toString());
                    throw this.pendingEx;
                }
                V v2 = this.val;
                this.lock.unlock();
                return v2;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw this.chainer.chain(e);
            }
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public void unlock() {
        this.lock.unlock();
    }

    public Promise(String str, ExceptionChainer<T> exceptionChainer, LoggerFactory loggerFactory) {
        this(str, exceptionChainer, null, loggerFactory);
    }

    public V retrieve() throws Throwable {
        return tryRetrieve(0L, TimeUnit.SECONDS);
    }
}
