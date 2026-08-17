package androidx.core.os;

import android.os.Handler;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class ExecutorCompat {

    public static class HandlerExecutor implements Executor {
        private final Handler mHandler;

        public HandlerExecutor(Handler handler) {
            this.mHandler = (Handler) Preconditions.checkNotNull(handler);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (this.mHandler.post((Runnable) Preconditions.checkNotNull(runnable))) {
                return;
            }
            yd4.a(this.mHandler, " is shutting down");
        }
    }

    private ExecutorCompat() {
    }

    public static Executor create(Handler handler) {
        return new HandlerExecutor(handler);
    }
}
