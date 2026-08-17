package org.jetbrains.kotlin.fir.analysis.collectors.components;

import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J \u0010\u0013\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getReporter", "()Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "checkSettings", "checkAndCommitReportsOn", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "commitEverything", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractDiagnosticCollectorComponent extends FirVisitor<Unit, CheckerContext> {
    private final PendingDiagnosticReporter reporter;
    private final FirSession session;

    public AbstractDiagnosticCollectorComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter) {
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        this.session = firSession;
        this.reporter = pendingDiagnosticReporter;
    }

    public final void checkAndCommitReportsOn(FirElement element, DiagnosticContext context, boolean commitEverything) {
        element.getClass();
        context.getClass();
        AbstractKtSourceElement source = element.getSource();
        if (source == null) {
            return;
        }
        this.reporter.checkAndCommitReportsOn(source, context, commitEverything);
    }

    public void checkSettings(CheckerContext data) {
        data.getClass();
    }

    public final PendingDiagnosticReporter getReporter() {
        return this.reporter;
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, CheckerContext checkerContext) {
        visitElement(firElement, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    public void visitElement(FirElement element, CheckerContext data) {
        element.getClass();
        data.getClass();
    }
}
