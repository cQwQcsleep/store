package org.jetbrains.kotlin.fir.analysis.collectors.components;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.impl.PendingDiagnosticsReporterImpl;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.resolve.calls.InferenceError;
import org.jetbrains.kotlin.fir.resolve.calls.InferredEmptyIntersectionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithSingleCandidate;
import org.jetbrains.kotlin.resolve.calls.inference.model.InferredEmptyIntersectionWarning;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/components/LossDiagnosticCollectorComponent;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;)V", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LossDiagnosticCollectorComponent extends AbstractDiagnosticCollectorComponent {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LossDiagnosticCollectorComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter) {
        super(firSession, pendingDiagnosticReporter);
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent
    /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
    public void visitElement2(FirElement element, CheckerContext data) {
        element.getClass();
        data.getClass();
        if ((element instanceof FirDiagnosticHolder) && data.getSuppressedDiagnostics().isEmpty() && (getReporter() instanceof PendingDiagnosticsReporterImpl)) {
            FirDiagnosticHolder firDiagnosticHolder = (FirDiagnosticHolder) element;
            KtSourceElement source = firDiagnosticHolder.getSource();
            ConeDiagnostic diagnostic = firDiagnosticHolder.getDiagnostic();
            if (diagnostic instanceof ConeDiagnosticWithSingleCandidate) {
                List<ResolutionDiagnostic> diagnostics = ((ConeDiagnosticWithSingleCandidate) diagnostic).getCandidate().getDiagnostics();
                if ((diagnostics instanceof Collection) && diagnostics.isEmpty()) {
                    return;
                }
                for (ResolutionDiagnostic resolutionDiagnostic : diagnostics) {
                    boolean z = false;
                    if (resolutionDiagnostic instanceof InferredEmptyIntersectionDiagnostic) {
                        if (!((InferredEmptyIntersectionDiagnostic) resolutionDiagnostic).getIsError()) {
                            z = true;
                        }
                    } else if (resolutionDiagnostic instanceof InferenceError) {
                        z = ((InferenceError) resolutionDiagnostic).getConstraintError() instanceof InferredEmptyIntersectionWarning;
                    }
                    if (!z) {
                    }
                }
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticReporter) getReporter(), (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getOTHER_ERROR_WITH_REASON(), (Object) diagnostic.getReason(), (DiagnosticContext) data, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, CheckerContext checkerContext) {
        visitElement2(firElement, checkerContext);
        return Unit.INSTANCE;
    }
}
