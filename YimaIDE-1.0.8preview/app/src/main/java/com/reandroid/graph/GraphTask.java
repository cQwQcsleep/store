package com.reandroid.graph;

import com.reandroid.common.DiagnosticMessage;
import com.reandroid.common.DiagnosticsReporter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class GraphTask implements DiagnosticsReporter {
    private DiagnosticsReporter reporter;
    private String reporterTag = getClass().getSimpleName();

    public abstract void apply();

    public void debug(String str) {
        DiagnosticsReporter reporter;
        if (!isDebugEnabled() || (reporter = getReporter()) == null) {
            return;
        }
        reporter.report(new DiagnosticMessage.StringMessage(DiagnosticMessage.Type.DEBUG, getReporterTag(), str));
    }

    public DiagnosticsReporter getReporter() {
        return this.reporter;
    }

    public String getReporterTag() {
        return this.reporterTag;
    }

    public void info(String str) {
        DiagnosticsReporter reporter = getReporter();
        if (reporter == null || !reporter.isReportEnabled()) {
            return;
        }
        reporter.report(new DiagnosticMessage.StringMessage(DiagnosticMessage.Type.INFO, getReporterTag(), str));
    }

    @Override // com.reandroid.common.DiagnosticsReporter
    public boolean isDebugEnabled() {
        DiagnosticsReporter reporter = getReporter();
        return reporter != null && isReportEnabled() && reporter.isDebugEnabled();
    }

    @Override // com.reandroid.common.DiagnosticsReporter
    public boolean isReportEnabled() {
        DiagnosticsReporter reporter = getReporter();
        if (reporter != null) {
            return reporter.isReportEnabled();
        }
        return false;
    }

    @Override // com.reandroid.common.DiagnosticsReporter
    public boolean isVerboseEnabled() {
        DiagnosticsReporter reporter = getReporter();
        return reporter != null && isReportEnabled() && reporter.isVerboseEnabled();
    }

    @Override // com.reandroid.common.DiagnosticsReporter
    public void report(DiagnosticMessage diagnosticMessage) {
        DiagnosticsReporter reporter = getReporter();
        if (reporter == null || !reporter.isReportEnabled()) {
            return;
        }
        reporter.report(diagnosticMessage);
    }

    public GraphTask setReporter(DiagnosticsReporter diagnosticsReporter) {
        if (diagnosticsReporter != this) {
            this.reporter = diagnosticsReporter;
            return this;
        }
        w01.a("Can not set reporter to itself");
        return null;
    }

    public GraphTask setReporterTag(String str) {
        this.reporterTag = str;
        return this;
    }

    public void verbose(String str) {
        DiagnosticsReporter reporter;
        if (!isVerboseEnabled() || (reporter = getReporter()) == null) {
            return;
        }
        reporter.report(new DiagnosticMessage.StringMessage(DiagnosticMessage.Type.VERBOSE, getReporterTag(), str));
    }

    public void warn(String str) {
        DiagnosticsReporter reporter = getReporter();
        if (reporter == null || !reporter.isReportEnabled()) {
            return;
        }
        reporter.report(new DiagnosticMessage.StringMessage(DiagnosticMessage.Type.WARN, getReporterTag(), str));
    }
}
