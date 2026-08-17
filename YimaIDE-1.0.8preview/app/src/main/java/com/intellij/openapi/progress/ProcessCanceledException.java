package com.intellij.openapi.progress;

import com.intellij.openapi.diagnostic.ControlFlowException;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class ProcessCanceledException extends CancellationException implements ControlFlowException {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/intellij/openapi/progress/ProcessCanceledException", "<init>"));
    }

    public ProcessCanceledException(Throwable th) {
        super(th == null ? null : th.toString());
        if (th instanceof ProcessCanceledException) {
            nrd.a("Must not self-wrap ProcessCanceledException: ", th);
            throw null;
        }
        initCause(th);
    }

    public ProcessCanceledException() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProcessCanceledException(String str) {
        super(str);
        if (str == null) {
            $$$reportNull$$$0(0);
        }
    }
}
