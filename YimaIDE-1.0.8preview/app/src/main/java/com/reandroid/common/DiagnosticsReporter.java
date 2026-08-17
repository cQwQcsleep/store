package com.reandroid.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DiagnosticsReporter {
    default boolean isDebugEnabled() {
        return false;
    }

    default boolean isReportEnabled() {
        return true;
    }

    default boolean isVerboseEnabled() {
        return false;
    }

    void report(DiagnosticMessage diagnosticMessage);
}
