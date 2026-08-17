package com.android.tools.r8;

import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface Z {
    Set<com.android.tools.r8.naming.mappinginformation.b> getMapVersions(DiagnosticsHandler diagnosticsHandler);

    void verifyMappingFileHash(DiagnosticsHandler diagnosticsHandler);
}
