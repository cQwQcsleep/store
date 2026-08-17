package com.android.apksig.util;

import com.android.apksig.util.RunnablesProvider;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface RunnablesExecutor {
    public static final RunnablesExecutor SINGLE_THREADED = new RunnablesExecutor() { // from class: slc
        @Override // com.android.apksig.util.RunnablesExecutor
        public final void execute(RunnablesProvider runnablesProvider) {
            runnablesProvider.createRunnable().run();
        }
    };
    public static final RunnablesExecutor MULTI_THREADED = new AnonymousClass1();

    /* JADX INFO: renamed from: com.android.apksig.util.RunnablesExecutor$1, reason: invalid class name */
    public class AnonymousClass1 implements RunnablesExecutor {
        private final int PARALLELISM = Math.min(32, Runtime.getRuntime().availableProcessors());
        private final int QUEUE_SIZE = 4;

        public static /* synthetic */ void b(RunnablesProvider runnablesProvider, Phaser phaser) {
            runnablesProvider.createRunnable().run();
            phaser.arriveAndDeregister();
        }

        @Override // com.android.apksig.util.RunnablesExecutor
        public void execute(final RunnablesProvider runnablesProvider) {
            int i = this.PARALLELISM;
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(4), new ThreadPoolExecutor.CallerRunsPolicy());
            final Phaser phaser = new Phaser(1);
            for (int i2 = 0; i2 < this.PARALLELISM; i2++) {
                Runnable runnable = new Runnable() { // from class: com.android.apksig.util.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        RunnablesExecutor.AnonymousClass1.b(runnablesProvider, phaser);
                    }
                };
                phaser.register();
                threadPoolExecutor.execute(runnable);
            }
            phaser.arriveAndAwaitAdvance();
            threadPoolExecutor.shutdownNow();
        }
    }

    void execute(RunnablesProvider runnablesProvider);
}
