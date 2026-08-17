package com.sun.org.apache.xml.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ThreadControllerWrapper {
    private static ThreadController m_tpool = new ThreadController();

    public static class ThreadController {
        public Thread run(Runnable runnable, int i) {
            SafeThread safeThread = new SafeThread(runnable);
            safeThread.start();
            return safeThread;
        }

        public void waitThread(Thread thread, Runnable runnable) throws InterruptedException {
            thread.join();
        }
    }

    public static Thread runThread(Runnable runnable, int i) {
        return m_tpool.run(runnable, i);
    }

    public static void waitThread(Thread thread, Runnable runnable) throws InterruptedException {
        m_tpool.waitThread(thread, runnable);
    }
}
