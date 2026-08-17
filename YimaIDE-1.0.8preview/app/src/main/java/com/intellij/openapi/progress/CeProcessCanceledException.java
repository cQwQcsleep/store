package com.intellij.openapi.progress;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CeProcessCanceledException extends ProcessCanceledException {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "e";
        } else {
            objArr[0] = "com/intellij/openapi/progress/CeProcessCanceledException";
        }
        if (i != 1) {
            objArr[1] = "com/intellij/openapi/progress/CeProcessCanceledException";
        } else {
            objArr[1] = "getCause";
        }
        if (i != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CeProcessCanceledException(CancellationException cancellationException) {
        super(cancellationException);
        if (cancellationException == null) {
            $$$reportNull$$$0(0);
        }
    }

    @Override // java.lang.Throwable
    public synchronized CancellationException getCause() {
        CancellationException cancellationException;
        cancellationException = (CancellationException) super.getCause();
        if (cancellationException == null) {
            $$$reportNull$$$0(1);
        }
        return cancellationException;
    }
}
