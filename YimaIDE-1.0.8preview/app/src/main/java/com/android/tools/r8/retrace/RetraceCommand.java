package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.retrace.RetraceCommand;
import defpackage.f63;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RetraceCommand {
    static final /* synthetic */ boolean d = true;
    private final StackTraceSupplier a;
    private final Consumer b;
    private final RetraceOptions c;

    private RetraceCommand(StackTraceSupplier stackTraceSupplier, Consumer consumer, RetraceOptions retraceOptions) {
        this.a = stackTraceSupplier;
        this.b = consumer;
        this.c = retraceOptions;
        boolean z = d;
        if (!z && stackTraceSupplier == null && !retraceOptions.isVerifyMappingFileHash()) {
            x1f.a();
            throw null;
        }
        if (z || consumer != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static Builder builder() {
        return new Builder(new i());
    }

    public RetraceOptions getOptions() {
        return this.c;
    }

    public Consumer<List<String>> getRetracedStackTraceConsumer() {
        return this.b;
    }

    public StackTraceSupplier getStackTraceSupplier() {
        return this.a;
    }

    public StackTraceSupplier getStacktraceSupplier() {
        return getStackTraceSupplier();
    }

    public boolean printMemory() {
        return System.getProperty("com.android.tools.r8.printmemory") != null;
    }

    public boolean printTimes() {
        return System.getProperty("com.android.tools.r8.printtimes") != null;
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(diagnosticsHandler);
    }

    public static class Builder {
        private boolean a;
        private final DiagnosticsHandler b;
        private MappingSupplier c;
        private String d;
        private StackTraceSupplier e;
        private Consumer f;
        private boolean g;

        private Builder(DiagnosticsHandler diagnosticsHandler) {
            this.d = "(?:.*?\\bat\\s+%c\\.%m\\s*\\(%S\\)\\p{Z}*(?:~\\[.*\\])?)|(?:(?:(?:%c|.*)?[:\"]\\s+)?%c(?:(:|]).*)?)";
            this.g = false;
            this.b = diagnosticsHandler;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List a(C1975l7 c1975l7) {
            Object obj = c1975l7.a;
            c1975l7.a = null;
            return (List) obj;
        }

        public RetraceCommand build() {
            DiagnosticsHandler diagnosticsHandler = this.b;
            if (diagnosticsHandler == null) {
                f63.a("DiagnosticsHandler not specified");
                return null;
            }
            if (this.c == null) {
                f63.a("ProguardMapSupplier not specified");
                return null;
            }
            if (this.e == null && !this.g) {
                f63.a("StackTrace not specified");
                return null;
            }
            if (this.f != null) {
                return new RetraceCommand(this.e, this.f, RetraceOptions.builder(diagnosticsHandler).setRegularExpression(this.d).setMappingSupplier(this.c).setVerbose(this.a).setVerifyMappingFileHash(this.g).build());
            }
            f63.a("RetracedStackConsumer not specified");
            return null;
        }

        public Builder setMappingSupplier(MappingSupplier<?> mappingSupplier) {
            this.c = mappingSupplier;
            return this;
        }

        public Builder setRegularExpression(String str) {
            this.d = str;
            return this;
        }

        public Builder setRetracedStackTraceConsumer(Consumer<List<String>> consumer) {
            this.f = consumer;
            return this;
        }

        public Builder setStackTrace(List<String> list) {
            final C1975l7 c1975l7 = new C1975l7(list);
            return setStackTrace(new StackTraceSupplier() { // from class: ijc
                @Override // com.android.tools.r8.retrace.StackTraceSupplier
                public final List get() {
                    return RetraceCommand.Builder.a(c1975l7);
                }
            });
        }

        public Builder setVerbose(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setVerifyMappingFileHash(boolean z) {
            this.g = z;
            return this;
        }

        public Builder setStackTrace(StackTraceSupplier stackTraceSupplier) {
            this.e = stackTraceSupplier;
            return this;
        }
    }
}
