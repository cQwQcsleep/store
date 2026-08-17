package org.jetbrains.kotlin.fir.analysis.checkers.config;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.CliFrontendDiagnostics;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/config/FirContextParametersLanguageVersionSettingsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/config/FirLanguageVersionSettingsChecker;", "<init>", "()V", "DIAGNOSTIC_MESSAGE", Argument.Delimiters.none, "getDIAGNOSTIC_MESSAGE", "()Ljava/lang/String;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContextParametersLanguageVersionSettingsChecker extends FirLanguageVersionSettingsChecker {
    public static final FirContextParametersLanguageVersionSettingsChecker INSTANCE = new FirContextParametersLanguageVersionSettingsChecker();
    private static final String DIAGNOSTIC_MESSAGE = "Experimental context receivers are superseded by context parameters.\nRemove the '-Xcontext-receivers' compiler argument and migrate to the new syntax.\n\nSee the context parameters proposal for more details: https://kotl.in/context-parameters";

    private FirContextParametersLanguageVersionSettingsChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.config.FirLanguageVersionSettingsChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ContextReceivers)) {
            return;
        }
        KtDiagnosticReportHelpersKt.report$default(checkerContext, diagnosticReporter, CliFrontendDiagnostics.INSTANCE.getCONTEXT_PARAMETERS_ARE_DEPRECATED(), DIAGNOSTIC_MESSAGE, null, 8, null);
    }

    public final String getDIAGNOSTIC_MESSAGE() {
        return DIAGNOSTIC_MESSAGE;
    }
}
