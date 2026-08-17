package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposablePropertyReferenceChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceAccessChecker;", "<init>", "()V", "check", "", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;)V", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposablePropertyReferenceChecker extends FirExpressionChecker<FirCallableReferenceAccess> {
    public static final ComposablePropertyReferenceChecker INSTANCE = new ComposablePropertyReferenceChecker();

    private ComposablePropertyReferenceChecker() {
        super(MppCheckerKind.Common);
    }

    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableReferenceAccess firCallableReferenceAccess) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableReferenceAccess.getClass();
        FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(firCallableReferenceAccess.getCalleeReference(), false, 1, (Object) null);
        if (resolvedPropertySymbol$default == null || resolvedPropertySymbol$default.getHasDelegate() || !FirUtilsKt.isComposable(resolvedPropertySymbol$default, checkerContext.getSession())) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, firCallableReferenceAccess.getSource(), ComposeErrors.INSTANCE.getCOMPOSABLE_PROPERTY_REFERENCE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
