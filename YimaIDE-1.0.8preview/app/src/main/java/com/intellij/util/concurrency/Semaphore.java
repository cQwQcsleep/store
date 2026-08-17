package com.intellij.util.concurrency;

import com.intellij.openapi.progress.ProcessCanceledException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class Semaphore {
    private final Sync sync = new Sync();

    public static final class Sync extends AbstractQueuedSynchronizer {
        private Sync() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void down() {
            int state;
            do {
                state = getState();
            } while (!compareAndSetState(state, state + 1));
        }

        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public int tryAcquireShared(int i) {
            return getState() == 0 ? 1 : -1;
        }

        @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
        public boolean tryReleaseShared(int i) {
            int state;
            int i2;
            do {
                state = getState();
                if (state == 0) {
                    return false;
                }
                i2 = state - 1;
            } while (!compareAndSetState(state, i2));
            return i2 == 0;
        }
    }

    public Semaphore(int i) {
        if (i < 0) {
            qf1.a("A non-negative amount of 'downs' expected, found ", i);
            throw null;
        }
        for (int i2 = 0; i2 < i; i2++) {
            down();
        }
    }

    public void down() {
        this.sync.down();
    }

    public boolean tryUp() {
        return this.sync.releaseShared(1);
    }

    public void up() {
        tryUp();
    }

    public boolean waitFor(long j) {
        try {
            return waitForUnsafe(j);
        } catch (InterruptedException e) {
            throw new ProcessCanceledException(e);
        }
    }

    public boolean waitForUnsafe(long j) throws InterruptedException {
        if (this.sync.tryAcquireShared(1) >= 0) {
            return true;
        }
        return this.sync.tryAcquireSharedNanos(1, TimeUnit.MILLISECONDS.toNanos(j));
    }

    public void waitFor() {
        try {
            waitForUnsafe();
        } catch (InterruptedException e) {
            throw new ProcessCanceledException(e);
        }
    }

    public void waitForUnsafe() throws InterruptedException {
        this.sync.acquireSharedInterruptibly(1);
    }

    public Semaphore() {
    }
}
