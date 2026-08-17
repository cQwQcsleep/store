package com.google.common.util.concurrent;

import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public final class LazyLogger {
    private final Object lock = new Object();
    private volatile Logger logger;
    private final String loggerName;

    public LazyLogger(Class<?> cls) {
        this.loggerName = cls.getName();
    }

    public Logger get() {
        Logger logger = this.logger;
        if (logger != null) {
            return logger;
        }
        synchronized (this.lock) {
            try {
                Logger logger2 = this.logger;
                if (logger2 != null) {
                    return logger2;
                }
                Logger logger3 = Logger.getLogger(this.loggerName);
                this.logger = logger3;
                return logger3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
