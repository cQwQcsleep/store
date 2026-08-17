package io.opentelemetry.api.trace;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class DefaultTracerBuilder implements TracerBuilder {
    private static final DefaultTracerBuilder INSTANCE = new DefaultTracerBuilder();

    public static TracerBuilder getInstance() {
        return INSTANCE;
    }
}
