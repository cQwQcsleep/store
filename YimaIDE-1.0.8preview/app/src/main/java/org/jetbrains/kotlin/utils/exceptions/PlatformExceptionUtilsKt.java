package org.jetbrains.kotlin.utils.exceptions;

import com.intellij.openapi.diagnostic.ControlFlowException;
import com.intellij.openapi.project.IndexNotReadyException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0006"}, d2 = {"shouldIjPlatformExceptionBeRethrown", "", "exception", "", "rethrowIntellijPlatformExceptionIfNeeded", "", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PlatformExceptionUtilsKt {
    public static final void rethrowIntellijPlatformExceptionIfNeeded(Throwable th) throws Throwable {
        th.getClass();
        if (shouldIjPlatformExceptionBeRethrown(th)) {
            throw th;
        }
    }

    public static final boolean shouldIjPlatformExceptionBeRethrown(Throwable th) {
        th.getClass();
        return (th instanceof CancellationException) || (th instanceof ControlFlowException) || (th instanceof IndexNotReadyException);
    }
}
