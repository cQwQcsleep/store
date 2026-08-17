package io.github.rosemoe.sora.text;

import android.util.Log;
import io.github.rosemoe.sora.lang.styling.Span;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SpanRecycler {
    private static SpanRecycler INSTANCE;
    private Thread recycleThread;
    private final BlockingQueue<List<Span>> taskQueue = new ArrayBlockingQueue(8);

    public class RecycleThread extends Thread {
        private static final String LOG_TAG = "SpanRecycler";

        public RecycleThread() {
            setDaemon(true);
            setName("SpanRecycleDaemon");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!isInterrupted()) {
                try {
                    try {
                        List list = (List) SpanRecycler.this.taskQueue.take();
                        int size = list.size();
                        int i = 0;
                        for (int i2 = 0; i2 < size && ((Span) list.remove((size - 1) - i2)).recycle(); i2++) {
                            i++;
                        }
                        Log.i(LOG_TAG, "Called recycle() on " + i + " spans");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.i(LOG_TAG, "Recycler exited");
                    }
                } catch (Exception e2) {
                    Log.w(LOG_TAG, e2);
                }
            }
            Log.i(LOG_TAG, "Recycler exited");
        }
    }

    private SpanRecycler() {
    }

    public static synchronized SpanRecycler getInstance() {
        try {
            if (INSTANCE == null) {
                INSTANCE = new SpanRecycler();
            }
        } catch (Throwable th) {
            throw th;
        }
        return INSTANCE;
    }

    public void recycle(List<Span> list) {
        if (list == null) {
            return;
        }
        Thread thread = this.recycleThread;
        if (thread == null || !thread.isAlive()) {
            RecycleThread recycleThread = new RecycleThread();
            this.recycleThread = recycleThread;
            recycleThread.start();
        }
        this.taskQueue.offer(list);
    }
}
