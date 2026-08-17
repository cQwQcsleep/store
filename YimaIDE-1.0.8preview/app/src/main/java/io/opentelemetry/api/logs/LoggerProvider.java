package io.opentelemetry.api.logs;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface LoggerProvider {
    static LoggerProvider noop() {
        return DefaultLoggerProvider.getInstance();
    }
}
