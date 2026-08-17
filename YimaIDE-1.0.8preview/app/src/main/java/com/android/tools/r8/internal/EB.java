package com.android.tools.r8.internal;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EB extends AbstractOwnableSynchronizer implements Runnable {
    public final GB b;

    public EB(GB gb) {
        this.b = gb;
    }

    public static void a(EB eb, Thread thread) {
        eb.setExclusiveOwnerThread(thread);
    }

    @Override // java.lang.Runnable
    public final void run() {
    }

    public final String toString() {
        return this.b.toString();
    }
}
