package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ProgramConsumer {
    void finished(DiagnosticsHandler diagnosticsHandler);

    default DataResourceConsumer getDataResourceConsumer() {
        return null;
    }
}
