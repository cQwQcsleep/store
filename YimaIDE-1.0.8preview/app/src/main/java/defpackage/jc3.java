package defpackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class jc3 {
    public static final jc3 a = new jc3();
    public static final ExecutorService b = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: ic3
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return jc3.a(runnable);
        }
    });
    public static final int c = 8;

    public static Thread a(Runnable runnable) {
        Thread thread = new Thread(runnable, "debug-report-worker");
        thread.setDaemon(true);
        return thread;
    }

    public final String b(String str) {
        str.getClass();
        if (str.length() <= 65536) {
            return str;
        }
        return StringsKt.take(str, 65536) + "\n（已截断）";
    }

    public final ExecutorService c() {
        return b;
    }
}
