package io.opentelemetry.api.trace;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface TracerProvider {
    static TracerProvider noop() {
        return DefaultTracerProvider.getInstance();
    }

    Tracer get(String str);

    Tracer get(String str, String str2);

    default TracerBuilder tracerBuilder(String str) {
        return DefaultTracerBuilder.getInstance();
    }
}
