package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface AndroidResourceConsumer {
    void accept(AndroidResourceOutput androidResourceOutput, DiagnosticsHandler diagnosticsHandler);

    default void finished(DiagnosticsHandler diagnosticsHandler) {
    }
}
