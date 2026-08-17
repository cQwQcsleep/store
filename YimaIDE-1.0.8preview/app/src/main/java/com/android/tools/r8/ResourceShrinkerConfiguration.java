package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResourceShrinkerConfiguration {
    public static ResourceShrinkerConfiguration DEFAULT_CONFIGURATION = new ResourceShrinkerConfiguration(false, true);
    private final boolean a;
    private final boolean b;

    public static class Builder {
        static final /* synthetic */ boolean c = true;
        private boolean a;
        private boolean b;

        private Builder() {
            this.a = false;
            this.b = true;
        }

        public ResourceShrinkerConfiguration build() {
            return new ResourceShrinkerConfiguration(this.a, this.b);
        }

        public Builder disablePreciseShrinking() {
            if (c || !this.a) {
                this.b = false;
                return this;
            }
            x1f.a();
            return null;
        }

        public Builder enableOptimizedShrinkingWithR8() {
            if (c || this.b) {
                this.a = true;
                return this;
            }
            x1f.a();
            return null;
        }
    }

    private ResourceShrinkerConfiguration(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder();
    }

    public boolean isOptimizedShrinking() {
        return this.a;
    }

    public boolean isPreciseShrinking() {
        return this.b;
    }
}
