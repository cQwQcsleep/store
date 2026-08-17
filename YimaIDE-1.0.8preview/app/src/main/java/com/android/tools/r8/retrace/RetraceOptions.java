package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import defpackage.f63;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RetraceOptions {
    static final /* synthetic */ boolean f = true;
    private final boolean a;
    private final boolean b;
    private final String c;
    private final DiagnosticsHandler d;
    private final MappingSupplier e;

    public static class Builder {
        private boolean a;
        private boolean b;
        private final DiagnosticsHandler c;
        private MappingSupplier d;
        private String e = RetraceOptions.defaultRegularExpression();

        public Builder(DiagnosticsHandler diagnosticsHandler) {
            this.c = diagnosticsHandler;
        }

        public RetraceOptions build() {
            if (this.c == null) {
                f63.a("DiagnosticsHandler not specified");
                return null;
            }
            if (this.d == null) {
                f63.a("ProguardMapSupplier not specified");
                return null;
            }
            if (this.e != null) {
                return new RetraceOptions(this.e, this.c, this.d, this.a, this.b);
            }
            f63.a("Regular expression not specified");
            return null;
        }

        public Builder setMappingSupplier(MappingSupplier<?> mappingSupplier) {
            this.d = mappingSupplier;
            return this;
        }

        public Builder setProguardMapProducer(ProguardMapProducer proguardMapProducer) {
            return setMappingSupplier(ProguardMappingSupplier.builder().setProguardMapProducer(proguardMapProducer).build());
        }

        public Builder setRegularExpression(String str) {
            this.e = str;
            return this;
        }

        public Builder setVerbose(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setVerifyMappingFileHash(boolean z) {
            this.b = z;
            return this;
        }
    }

    private RetraceOptions(String str, DiagnosticsHandler diagnosticsHandler, MappingSupplier mappingSupplier, boolean z, boolean z2) {
        this.c = str;
        this.d = diagnosticsHandler;
        this.e = mappingSupplier;
        this.a = z;
        this.b = z2;
        boolean z3 = f;
        if (!z3 && diagnosticsHandler == null) {
            x1f.a();
            throw null;
        }
        if (z3 || mappingSupplier != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static Builder builder() {
        return builder(new j());
    }

    public static String defaultRegularExpression() {
        return "(?:.*?\\bat\\s+%c\\.%m\\s*\\(%S\\)\\p{Z}*(?:~\\[.*\\])?)|(?:(?:(?:%c|.*)?[:\"]\\s+)?%c(?:(:|]).*)?)";
    }

    public DiagnosticsHandler getDiagnosticsHandler() {
        return this.d;
    }

    public MappingSupplier<?> getMappingSupplier() {
        return this.e;
    }

    public String getRegularExpression() {
        return this.c;
    }

    public boolean isVerbose() {
        return this.a;
    }

    public boolean isVerifyMappingFileHash() {
        return this.b;
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(diagnosticsHandler);
    }
}
