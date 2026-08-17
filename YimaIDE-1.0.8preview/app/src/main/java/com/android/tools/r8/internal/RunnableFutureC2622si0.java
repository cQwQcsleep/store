package com.android.tools.r8.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: renamed from: com.android.tools.r8.internal.si0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RunnableFutureC2622si0 extends AbstractC0625Kq implements RunnableFuture {
    public volatile C2537ri0 i;

    public RunnableFutureC2622si0(Callable callable) {
        this.i = new C2537ri0(this, callable);
    }

    @Override // com.android.tools.r8.internal.C
    public final void a() {
        C2537ri0 c2537ri0;
        Object obj = this.b;
        if ((obj instanceof C2644t) && ((C2644t) obj).a && (c2537ri0 = this.i) != null) {
            Runnable runnable = (Runnable) c2537ri0.get();
            if (runnable instanceof Thread) {
                EB eb = new EB(c2537ri0);
                EB.a(eb, Thread.currentThread());
                if (c2537ri0.compareAndSet(runnable, eb)) {
                    try {
                        ((Thread) runnable).interrupt();
                        if (((Runnable) c2537ri0.getAndSet(GB.b)) == GB.c) {
                            LockSupport.unpark((Thread) runnable);
                        }
                    } catch (Throwable th) {
                        if (((Runnable) c2537ri0.getAndSet(GB.b)) == GB.c) {
                            LockSupport.unpark((Thread) runnable);
                        }
                        throw th;
                    }
                }
            }
        }
        this.i = null;
    }

    @Override // com.android.tools.r8.internal.C
    public final String b() {
        C2537ri0 c2537ri0 = this.i;
        if (c2537ri0 == null) {
            return super.b();
        }
        return "task=[" + c2537ri0 + "]";
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        C2537ri0 c2537ri0 = this.i;
        if (c2537ri0 != null) {
            c2537ri0.run();
        }
        this.i = null;
    }
}
