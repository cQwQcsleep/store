package org.jetbrains.kotlin.fir.analysis.collectors.components;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.LanguageVersionSettingsCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.config.FirLanguageVersionSettingsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/components/LanguageVersionSettingsDiagnosticComponent;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "checkers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/LanguageVersionSettingsCheckers;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/LanguageVersionSettingsCheckers;)V", "checkSettings", Argument.Delimiters.none, "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LanguageVersionSettingsDiagnosticComponent extends AbstractDiagnosticCollectorComponent {
    private final LanguageVersionSettingsCheckers checkers;

    public /* synthetic */ LanguageVersionSettingsDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, LanguageVersionSettingsCheckers languageVersionSettingsCheckers, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, pendingDiagnosticReporter, (i & 4) != 0 ? CheckersComponentKt.getCheckersComponent(firSession).getLanguageVersionSettingsCheckers() : languageVersionSettingsCheckers);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent
    public void checkSettings(CheckerContext data) {
        data.getClass();
        Iterator<FirLanguageVersionSettingsChecker> it = this.checkers.getLanguageVersionSettingsCheckers().iterator();
        while (it.hasNext()) {
            it.next().check(data, getReporter());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LanguageVersionSettingsDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, LanguageVersionSettingsCheckers languageVersionSettingsCheckers) {
        super(firSession, pendingDiagnosticReporter);
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        languageVersionSettingsCheckers.getClass();
        this.checkers = languageVersionSettingsCheckers;
    }
}
