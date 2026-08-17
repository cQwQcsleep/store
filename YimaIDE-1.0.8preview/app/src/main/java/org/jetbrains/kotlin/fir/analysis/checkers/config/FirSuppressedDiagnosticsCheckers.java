package org.jetbrains.kotlin.fir.analysis.checkers.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractKtDiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.CliFrontendDiagnostics;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirComposedDiagnosticRendererFactoryKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/config/FirSuppressedDiagnosticsCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/config/FirLanguageVersionSettingsChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSuppressedDiagnosticsCheckers extends FirLanguageVersionSettingsChecker {
    public static final FirSuppressedDiagnosticsCheckers INSTANCE = new FirSuppressedDiagnosticsCheckers();

    private FirSuppressedDiagnosticsCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.config.FirLanguageVersionSettingsChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        Map map = (Map) FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession()).getFlag(AnalysisFlags.INSTANCE.getWarningLevels());
        if (map.isEmpty()) {
            return;
        }
        List<AbstractKtDiagnosticFactory> allDiagnosticFactories = FirComposedDiagnosticRendererFactoryKt.getRegisteredDiagnosticFactoriesStorage(checkerContext.getSession()).getAllDiagnosticFactories();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(allDiagnosticFactories, 10)), 16));
        for (Object obj : allDiagnosticFactories) {
            linkedHashMap.put(((AbstractKtDiagnosticFactory) obj).getName(), obj);
        }
        for (String str : map.keySet()) {
            AbstractKtDiagnosticFactory abstractKtDiagnosticFactory = (AbstractKtDiagnosticFactory) linkedHashMap.get(str);
            if (abstractKtDiagnosticFactory == null) {
                KtDiagnosticReportHelpersKt.report$default(checkerContext, diagnosticReporter, CliFrontendDiagnostics.INSTANCE.getMISSING_DIAGNOSTIC_NAME(), "Warning with name \"" + str + "\" does not exist", null, 8, null);
            } else if (abstractKtDiagnosticFactory.getSeverity() == Severity.ERROR) {
                KtDiagnosticReportHelpersKt.report$default(checkerContext, diagnosticReporter, CliFrontendDiagnostics.INSTANCE.getERROR_SEVERITY_CHANGED(), "Diagnostic \"" + str + "\" is an error. Changing the severity of errors is prohibited", null, 8, null);
            }
        }
    }
}
