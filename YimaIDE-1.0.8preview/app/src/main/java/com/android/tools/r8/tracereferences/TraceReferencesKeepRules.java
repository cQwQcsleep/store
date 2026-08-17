package com.android.tools.r8.tracereferences;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.internal.Oh0;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TraceReferencesKeepRules extends TraceReferencesConsumer.ForwardingConsumer {
    private final Oh0 c;
    private final StringConsumer d;
    private final boolean e;

    public static class Builder {
        private StringConsumer a;
        private boolean b;

        public TraceReferencesKeepRules build() {
            return new TraceReferencesKeepRules(new Oh0(), this.a, this.b);
        }

        public Builder setAllowObfuscation(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setOutputConsumer(StringConsumer stringConsumer) {
            this.a = stringConsumer;
            return this;
        }

        public Builder setOutputPath(Path path) {
            this.a = new StringConsumer.FileConsumer(path);
            return this;
        }
    }

    private TraceReferencesKeepRules(Oh0 oh0, StringConsumer stringConsumer, boolean z) {
        super(oh0);
        this.c = oh0;
        this.d = stringConsumer;
        this.e = z;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean allowObfuscation() {
        return this.e;
    }

    @Override // com.android.tools.r8.tracereferences.TraceReferencesConsumer.ForwardingConsumer, com.android.tools.r8.tracereferences.TraceReferencesConsumer
    public void finished(DiagnosticsHandler diagnosticsHandler) {
        super.finished(diagnosticsHandler);
        b bVar = new b(this.e);
        Oh0 oh0 = this.c;
        bVar.a(oh0.a, oh0.d, oh0.b, oh0.c);
        this.d.accept(bVar.a.toString(), diagnosticsHandler);
        this.d.finished(diagnosticsHandler);
    }
}
