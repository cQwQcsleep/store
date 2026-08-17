package org.jetbrains.kotlin.fir.analysis.collectors;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.MutableCheckerContext;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculatorForFullBodyResolve;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0014¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/CliDiagnosticsCollector;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/AbstractDiagnosticCollector;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "createComponents", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/DiagnosticCollectorComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lkotlin/jvm/functions/Function1;)V", "createVisitor", "Lorg/jetbrains/kotlin/fir/analysis/collectors/CheckerRunningDiagnosticCollectorVisitor;", "components", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliDiagnosticsCollector extends AbstractDiagnosticCollector {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CliDiagnosticsCollector(FirSession firSession, ScopeSession scopeSession, Function1<? super PendingDiagnosticReporter, DiagnosticCollectorComponents> function1) {
        super(firSession, scopeSession, function1);
        firSession.getClass();
        scopeSession.getClass();
        function1.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector
    public CheckerRunningDiagnosticCollectorVisitor createVisitor(DiagnosticCollectorComponents components) {
        components.getClass();
        return new CheckerRunningDiagnosticCollectorVisitor(new MutableCheckerContext(this, ReturnTypeCalculatorForFullBodyResolve.INSTANCE.getDefault()), components);
    }
}
