package com.android.tools.r8;

import com.android.tools.r8.internal.Kk0;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class P implements DexIndexedConsumer {
    @Override // com.android.tools.r8.DexIndexedConsumer
    public final void accept(int i, ByteDataView byteDataView, Set set, DiagnosticsHandler diagnosticsHandler) {
        throw new Kk0("Unexpected attempt to write a non-global artifact");
    }

    @Override // com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
    public final void finished(DiagnosticsHandler diagnosticsHandler) {
    }
}
