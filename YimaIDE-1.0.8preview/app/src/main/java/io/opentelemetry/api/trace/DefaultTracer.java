package io.opentelemetry.api.trace;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class DefaultTracer implements Tracer {
    private static final Tracer INSTANCE = new DefaultTracer();

    private DefaultTracer() {
    }

    public static Tracer getInstance() {
        return INSTANCE;
    }
}
