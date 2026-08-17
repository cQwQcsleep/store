package com.android.tools.r8;

import com.android.tools.r8.internal.C2742u50;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FeatureSplit {
    public static final FeatureSplit BASE = new H();
    private ProgramConsumer a;
    private final List b;
    private final AndroidResourceProvider c;
    private final AndroidResourceConsumer d;

    public static class Builder {
        private ProgramConsumer a;
        private final ArrayList b;
        private AndroidResourceProvider c;
        private AndroidResourceConsumer d;
        private final DiagnosticsHandler e;

        private Builder(C2742u50 c2742u50) {
            this.b = new ArrayList();
        }

        public Builder addProgramResourceProvider(ProgramResourceProvider programResourceProvider) {
            this.b.add(programResourceProvider);
            return this;
        }

        public FeatureSplit build() {
            return new FeatureSplit(this.a, this.b, this.c, this.d);
        }

        public Builder setAndroidResourceConsumer(AndroidResourceConsumer androidResourceConsumer) {
            this.d = androidResourceConsumer;
            return this;
        }

        public Builder setAndroidResourceProvider(AndroidResourceProvider androidResourceProvider) {
            this.c = androidResourceProvider;
            return this;
        }

        public Builder setProgramConsumer(ProgramConsumer programConsumer) {
            this.a = programConsumer;
            return this;
        }
    }

    private FeatureSplit(ProgramConsumer programConsumer, ArrayList arrayList, AndroidResourceProvider androidResourceProvider, AndroidResourceConsumer androidResourceConsumer) {
        this.a = programConsumer;
        this.b = arrayList;
        this.c = androidResourceProvider;
        this.d = androidResourceConsumer;
    }

    public static Builder a(C2742u50 c2742u50) {
        return new Builder(c2742u50);
    }

    public AndroidResourceConsumer getAndroidResourceConsumer() {
        return this.d;
    }

    public AndroidResourceProvider getAndroidResourceProvider() {
        return this.c;
    }

    public ProgramConsumer getProgramConsumer() {
        return this.a;
    }

    public List<ProgramResourceProvider> getProgramResourceProviders() {
        return this.b;
    }

    public boolean isBase() {
        return false;
    }

    public final void a(b0 b0Var) {
        this.a = b0Var;
    }
}
