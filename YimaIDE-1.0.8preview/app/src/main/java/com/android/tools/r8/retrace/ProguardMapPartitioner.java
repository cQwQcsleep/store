package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.YY;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ProguardMapPartitioner {
    static ProguardMapPartitionerBuilder<?, ?> builder(DiagnosticsHandler diagnosticsHandler) {
        return new YY.a(diagnosticsHandler);
    }

    MappingPartitionMetadata run() throws IOException;
}
