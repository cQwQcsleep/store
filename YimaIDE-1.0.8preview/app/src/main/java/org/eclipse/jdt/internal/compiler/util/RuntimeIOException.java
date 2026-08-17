package org.eclipse.jdt.internal.compiler.util;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class RuntimeIOException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public RuntimeIOException(String str, IOException iOException) {
        super(str, iOException);
    }

    @Override // java.lang.Throwable
    public synchronized IOException getCause() {
        return (IOException) super.getCause();
    }

    public RuntimeIOException(IOException iOException) {
        super(iOException);
    }
}
