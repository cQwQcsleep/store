package com.google.common.util.concurrent;

import com.google.common.util.concurrent.Runnables;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class Runnables {
    private static final Runnable EMPTY_RUNNABLE = new Runnable() { // from class: rlc
        @Override // java.lang.Runnable
        public final void run() {
            Runnables.a();
        }
    };

    private Runnables() {
    }

    public static /* synthetic */ void a() {
    }

    public static Runnable doNothing() {
        return EMPTY_RUNNABLE;
    }
}
