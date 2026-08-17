package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RetraceFailedException extends Exception {
    public RetraceFailedException() {
        super("Retrace failed to complete");
    }

    public RetraceFailedException(String str, Throwable th) {
        super(str, th);
    }
}
