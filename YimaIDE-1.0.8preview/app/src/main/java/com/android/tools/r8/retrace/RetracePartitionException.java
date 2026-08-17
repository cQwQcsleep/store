package com.android.tools.r8.retrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RetracePartitionException extends RuntimeException {
    public RetracePartitionException(String str) {
        super(str);
    }

    public RetracePartitionException(Exception exc) {
        super(exc);
    }

    public RetracePartitionException(String str, Throwable th) {
        super(str, th);
    }
}
