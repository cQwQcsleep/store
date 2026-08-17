package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DataResourceConsumer {
    void accept(DataDirectoryResource dataDirectoryResource, DiagnosticsHandler diagnosticsHandler);

    void accept(DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler);

    void finished(DiagnosticsHandler diagnosticsHandler);
}
