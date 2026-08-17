package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.Severity;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ErrorDiagnosticDetector;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "()V", "value", Argument.Delimiters.none, "hasErrors", "getHasErrors", "()Z", "hasWarningsForWError", "getHasWarningsForWError", "report", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ErrorDiagnosticDetector extends DiagnosticReporter {
    private boolean hasErrors;
    private boolean hasWarningsForWError;

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public boolean getHasErrors() {
        return this.hasErrors;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public boolean getHasWarningsForWError() {
        return this.hasWarningsForWError;
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticReporter
    public void report(KtDiagnostic diagnostic, DiagnosticContext context) {
        Severity severity;
        context.getClass();
        if (diagnostic == null || (severity = diagnostic.getSeverity()) == null) {
            return;
        }
        boolean z = true;
        this.hasErrors = getHasErrors() || severity.isError();
        if (!getHasWarningsForWError() && !severity.isErrorWhenWError()) {
            z = false;
        }
        this.hasWarningsForWError = z;
    }
}
