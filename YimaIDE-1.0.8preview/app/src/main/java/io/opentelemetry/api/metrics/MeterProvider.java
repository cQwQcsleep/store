package io.opentelemetry.api.metrics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface MeterProvider {
    static MeterProvider noop() {
        return DefaultMeterProvider.getInstance();
    }

    default Meter get(String str) {
        return meterBuilder(str).build();
    }

    MeterBuilder meterBuilder(String str);
}
