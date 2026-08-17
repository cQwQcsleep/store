package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.retrace.RetraceBuilderBase;
import com.android.tools.r8.retrace.StackTraceElementProxy;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class RetraceBuilderBase<B extends RetraceBuilderBase<B, T, ST>, T, ST extends StackTraceElementProxy<T, ST>> {
    protected DiagnosticsHandler diagnosticsHandler;
    protected boolean isVerbose;
    protected StackTraceLineParser<T, ST> stackTraceLineParser;

    public abstract B self();

    public B setDiagnosticsHandler(DiagnosticsHandler diagnosticsHandler) {
        this.diagnosticsHandler = diagnosticsHandler;
        return (B) self();
    }

    public B setStackTraceLineParser(StackTraceLineParser<T, ST> stackTraceLineParser) {
        this.stackTraceLineParser = stackTraceLineParser;
        return (B) self();
    }

    public B setVerbose(boolean z) {
        this.isVerbose = z;
        return (B) self();
    }
}
