package io.opentelemetry.api.trace;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class DefaultTracerProvider implements TracerProvider {
    private static final TracerProvider INSTANCE = new DefaultTracerProvider();

    private DefaultTracerProvider() {
    }

    public static TracerProvider getInstance() {
        return INSTANCE;
    }

    @Override // io.opentelemetry.api.trace.TracerProvider
    public Tracer get(String str) {
        return DefaultTracer.getInstance();
    }

    @Override // io.opentelemetry.api.trace.TracerProvider
    public Tracer get(String str, String str2) {
        return DefaultTracer.getInstance();
    }
}
