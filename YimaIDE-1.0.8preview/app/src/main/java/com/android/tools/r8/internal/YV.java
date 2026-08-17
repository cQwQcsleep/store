package com.android.tools.r8.internal;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.origin.PathOrigin;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface YV {
    void a(int i, String str, ByteDataView byteDataView, DiagnosticsHandler diagnosticsHandler);

    void a(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler);

    void a(DiagnosticsHandler diagnosticsHandler);

    void a(String str, DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler);

    void a(String str, DiagnosticsHandler diagnosticsHandler);

    PathOrigin getOrigin();

    Path getPath();

    void open();
}
