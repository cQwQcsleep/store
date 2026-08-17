package com.intellij.util.ui;

import java.awt.EventQueue;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class EDT {
    private static Thread myEventDispatchThread;

    public static Thread getEventDispatchThreadOrNull() {
        return myEventDispatchThread;
    }

    public static boolean isCurrentThreadEdt() {
        Thread thread = myEventDispatchThread;
        if (thread == null) {
            return EventQueue.isDispatchThread();
        }
        return Thread.currentThread() == thread;
    }
}
