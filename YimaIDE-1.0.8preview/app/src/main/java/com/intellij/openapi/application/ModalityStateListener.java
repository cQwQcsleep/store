package com.intellij.openapi.application;

import java.util.EventListener;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface ModalityStateListener extends EventListener {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modalEntity", "com/intellij/openapi/application/ModalityStateListener", "beforeModalityStateChanged"));
    }

    default void beforeModalityStateChanged(boolean z, Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(0);
        }
    }
}
