package com.android.tools.r8;

import java.io.IOException;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ProgramResourceProvider {
    default void finished(DiagnosticsHandler diagnosticsHandler) throws IOException {
    }

    default DataResourceProvider getDataResourceProvider() {
        return null;
    }

    Collection<ProgramResource> getProgramResources() throws ResourceException;
}
