package com.android.tools.r8.internal;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.su, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2638su implements InterfaceFutureC2933wL {
    public static final C2638su c = new C2638su(null);
    public final Object b;

    static {
        Logger.getLogger(C2638su.class.getName());
    }

    public C2638su(Object obj) {
        this.b = obj;
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        timeUnit.getClass();
        return this.b;
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        return super.toString() + "[status=SUCCESS, result=[" + this.b + "]]";
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.b;
    }
}
