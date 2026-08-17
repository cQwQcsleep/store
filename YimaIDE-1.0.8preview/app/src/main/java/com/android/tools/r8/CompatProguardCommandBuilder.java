package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CompatProguardCommandBuilder extends R8Command.Builder {
    public CompatProguardCommandBuilder(boolean z) {
        super(new h0());
        setProguardCompatibility(z);
    }

    public CompatProguardCommandBuilder(boolean z, DiagnosticsHandler diagnosticsHandler) {
        super(diagnosticsHandler);
        setProguardCompatibility(z);
    }

    public CompatProguardCommandBuilder() {
        this(true);
    }
}
