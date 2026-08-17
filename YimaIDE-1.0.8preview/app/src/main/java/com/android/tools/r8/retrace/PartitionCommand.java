package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.PartitionMapConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PartitionCommand {
    private final DiagnosticsHandler a;
    private final ProguardMapProducer b;
    private final PartitionMapConsumer c;

    public static class Builder {
        private final DiagnosticsHandler a;
        private ProguardMapProducer b;
        private PartitionMapConsumer c;

        private Builder(b bVar) {
            this.a = bVar;
        }

        public PartitionCommand build() {
            ProguardMapProducer proguardMapProducer = this.b;
            if (proguardMapProducer == null) {
                throw new RetracePartitionException("ProguardMapSupplier not specified");
            }
            PartitionMapConsumer partitionMapConsumer = this.c;
            if (partitionMapConsumer != null) {
                return new PartitionCommand((b) this.a, proguardMapProducer, partitionMapConsumer);
            }
            throw new RetracePartitionException("PartitionMapConsumer not specified");
        }

        public Builder setPartitionMapConsumer(PartitionMapConsumer partitionMapConsumer) {
            this.c = partitionMapConsumer;
            return this;
        }

        public Builder setProguardMapProducer(ProguardMapProducer proguardMapProducer) {
            this.b = proguardMapProducer;
            return this;
        }
    }

    private PartitionCommand(b bVar, ProguardMapProducer proguardMapProducer, PartitionMapConsumer partitionMapConsumer) {
        this.a = bVar;
        this.b = proguardMapProducer;
        this.c = partitionMapConsumer;
    }

    public static Builder builder() {
        return new Builder(new b());
    }

    public DiagnosticsHandler getDiagnosticsHandler() {
        return this.a;
    }

    public PartitionMapConsumer getPartitionMapConsumer() {
        return this.c;
    }

    public ProguardMapProducer getProguardMapProducer() {
        return this.b;
    }
}
