package io.opentelemetry.api.metrics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class DefaultMeterProvider implements MeterProvider {
    private static final DefaultMeterProvider INSTANCE = new DefaultMeterProvider();
    private static final MeterBuilder BUILDER_INSTANCE = new NoopMeterBuilder();

    public static class NoopMeterBuilder implements MeterBuilder {
        private NoopMeterBuilder() {
        }

        @Override // io.opentelemetry.api.metrics.MeterBuilder
        public Meter build() {
            return DefaultMeter.getInstance();
        }
    }

    private DefaultMeterProvider() {
    }

    public static MeterProvider getInstance() {
        return INSTANCE;
    }

    @Override // io.opentelemetry.api.metrics.MeterProvider
    public MeterBuilder meterBuilder(String str) {
        return BUILDER_INSTANCE;
    }
}
