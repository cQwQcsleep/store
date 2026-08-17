package com.android.tools.r8;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface AndroidResourceProvider {
    default void finished(DiagnosticsHandler diagnosticsHandler) {
    }

    Collection<AndroidResourceInput> getAndroidResources() throws ResourceException;
}
