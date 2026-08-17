package io.opentelemetry.api.logs;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class DefaultLoggerProvider implements LoggerProvider {
    private static final LoggerProvider INSTANCE = new DefaultLoggerProvider();
    private static final LoggerBuilder NOOP_BUILDER = new NoopLoggerBuilder();

    public static class NoopLoggerBuilder implements LoggerBuilder {
        private NoopLoggerBuilder() {
        }
    }

    private DefaultLoggerProvider() {
    }

    public static LoggerProvider getInstance() {
        return INSTANCE;
    }
}
