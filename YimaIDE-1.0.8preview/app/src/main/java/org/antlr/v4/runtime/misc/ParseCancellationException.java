package org.antlr.v4.runtime.misc;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ParseCancellationException extends CancellationException {
    public ParseCancellationException(Throwable th) {
        initCause(th);
    }

    public ParseCancellationException(String str) {
        super(str);
    }

    public ParseCancellationException() {
    }

    public ParseCancellationException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
