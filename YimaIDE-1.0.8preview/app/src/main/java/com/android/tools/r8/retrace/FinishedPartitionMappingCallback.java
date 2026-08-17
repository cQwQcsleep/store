package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.retrace.FinishedPartitionMappingCallback;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface FinishedPartitionMappingCallback {
    public static final FinishedPartitionMappingCallback EMPTY_INSTANCE = new FinishedPartitionMappingCallback() { // from class: ys4
        @Override // com.android.tools.r8.retrace.FinishedPartitionMappingCallback
        public final void finished(DiagnosticsHandler diagnosticsHandler) {
            FinishedPartitionMappingCallback.a(diagnosticsHandler);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    static /* synthetic */ void a(DiagnosticsHandler diagnosticsHandler) {
    }

    static FinishedPartitionMappingCallback empty() {
        return EMPTY_INSTANCE;
    }

    void finished(DiagnosticsHandler diagnosticsHandler);
}
