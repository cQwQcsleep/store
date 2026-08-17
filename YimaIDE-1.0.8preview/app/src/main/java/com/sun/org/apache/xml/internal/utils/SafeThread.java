package com.sun.org.apache.xml.internal.utils;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SafeThread extends Thread {
    private static final AtomicInteger threadNumber = new AtomicInteger(1);
    private volatile boolean ran;

    public SafeThread(ThreadGroup threadGroup, Runnable runnable, String str) {
        super(threadGroup, runnable, str, 0L, false);
        this.ran = false;
    }

    private static String threadName() {
        return "SafeThread-" + threadNumber.getAndIncrement();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        if (Thread.currentThread() != this) {
            k2d.a("The run() method in a SafeThread cannot be called from another thread.");
            return;
        }
        synchronized (this) {
            if (this.ran) {
                throw new IllegalStateException("The run() method in a SafeThread cannot be called more than once.");
            }
            this.ran = true;
        }
        super.run();
    }

    public SafeThread(Runnable runnable, String str) {
        this(null, runnable, str);
    }

    public SafeThread(Runnable runnable) {
        this(null, runnable, threadName());
    }
}
