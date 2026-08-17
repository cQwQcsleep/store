package com.android.tools.r8.internal;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class GB extends AtomicReference implements Runnable {
    public static final FB b = new FB();
    public static final FB c = new FB();

    public final void a(Thread thread) {
        Runnable runnable = (Runnable) get();
        EB eb = null;
        boolean z = false;
        int i = 0;
        while (true) {
            boolean z2 = runnable instanceof EB;
            if (!z2 && runnable != c) {
                break;
            }
            if (z2) {
                eb = (EB) runnable;
            }
            i++;
            if (i > 1000) {
                FB fb = c;
                if (runnable == fb || compareAndSet(runnable, fb)) {
                    z = Thread.interrupted() || z;
                    LockSupport.park(eb);
                }
            } else {
                Thread.yield();
            }
            runnable = (Runnable) get();
        }
        if (z) {
            thread.interrupt();
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        RunnableFutureC2622si0 runnableFutureC2622si0;
        Object objCall;
        Thread threadCurrentThread = Thread.currentThread();
        if (compareAndSet(null, threadCurrentThread)) {
            C2537ri0 c2537ri0 = (C2537ri0) this;
            boolean zIsDone = c2537ri0.e.isDone();
            if (zIsDone) {
                objCall = null;
            } else {
                try {
                    objCall = ((C2537ri0) this).d.call();
                } catch (Throwable th) {
                    try {
                        if (th instanceof InterruptedException) {
                            Thread.currentThread().interrupt();
                        }
                        if (zIsDone) {
                            return;
                        }
                        runnableFutureC2622si0 = c2537ri0.e;
                        runnableFutureC2622si0.getClass();
                        boolean zA = C.g.a(runnableFutureC2622si0, (Object) null, new C2815v(th));
                        if (zA) {
                            return;
                        } else {
                            return;
                        }
                    } finally {
                        if (!compareAndSet(threadCurrentThread, b)) {
                            a(threadCurrentThread);
                        }
                        if (!zIsDone) {
                            runnableFutureC2622si0 = c2537ri0.e;
                            runnableFutureC2622si0.getClass();
                            if (C.g.a(runnableFutureC2622si0, (Object) null, C.h)) {
                                C.a((C) runnableFutureC2622si0);
                            }
                        }
                    }
                }
            }
            if (!compareAndSet(threadCurrentThread, b)) {
                a(threadCurrentThread);
            }
            if (zIsDone) {
                return;
            }
            RunnableFutureC2622si0 runnableFutureC2622si1 = c2537ri0.e;
            runnableFutureC2622si1.getClass();
            if (objCall == null) {
                objCall = C.h;
            }
            if (C.g.a(runnableFutureC2622si1, (Object) null, objCall)) {
                C.a((C) runnableFutureC2622si1);
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == b) {
            str = "running=[DONE]";
        } else if (runnable instanceof EB) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return str + ", " + ((C2537ri0) this).d.toString();
    }
}
