package com.intellij.serviceContainer;

import com.intellij.openapi.progress.ProcessCanceledException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class AlreadyDisposedException extends ProcessCanceledException {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/intellij/serviceContainer/AlreadyDisposedException", "<init>"));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlreadyDisposedException(String str) {
        super(str);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
    }
}
