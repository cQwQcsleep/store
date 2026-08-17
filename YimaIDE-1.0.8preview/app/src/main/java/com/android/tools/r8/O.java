package com.android.tools.r8;

import com.android.tools.r8.internal.Kk0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O implements ClassFileConsumer {
    @Override // com.android.tools.r8.ClassFileConsumer
    public final void accept(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler) {
        throw new Kk0("Unexpected attempt to write a non-global artifact");
    }

    @Override // com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
    }
}
