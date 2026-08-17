package com.intellij.util.io;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ClosedStorageException extends IOException {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/intellij/util/io/ClosedStorageException", "<init>"));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClosedStorageException(String str) {
        super(str);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
    }
}
