package com.android.tools.r8;

import java.io.IOException;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ClassFileResourceProvider {
    default void finished(DiagnosticsHandler diagnosticsHandler) throws IOException {
    }

    Set<String> getClassDescriptors();

    ProgramResource getProgramResource(String str);
}
