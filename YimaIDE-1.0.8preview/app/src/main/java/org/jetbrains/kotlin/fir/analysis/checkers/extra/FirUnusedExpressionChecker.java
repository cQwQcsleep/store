package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBase;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBaseKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u0005H\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ)\u0010\t\u001a\u00060\nR\u00020\u0001H\u0014R\u00020\u0006R\u00020\u000bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002R\u00020\u0006R\u00020\u000bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u0014¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirUnusedExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase;", "<init>", "()V", "isEnabled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "createVisitor", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "reportUnused", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/KtSourceElement;)V", "UsageVisitor", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnusedExpressionChecker extends FirUnusedCheckerBase {
    public static final FirUnusedExpressionChecker INSTANCE = new FirUnusedExpressionChecker();

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00060\u0001R\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirUnusedExpressionChecker$UsageVisitor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "checkExpression", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UsageVisitor extends FirUnusedCheckerBase.UsageVisitorBase {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UsageVisitor(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
            super(FirUnusedExpressionChecker.INSTANCE, checkerContext, diagnosticReporter);
            checkerContext.getClass();
            diagnosticReporter.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBase.UsageVisitorBase
        public void checkExpression(FirExpression expression, FirUnusedCheckerBase.UsageState data) {
            expression.getClass();
            data.getClass();
            if (data.isUnused() && !FirUnusedCheckerBaseKt.hasSideEffect(expression)) {
                FirUnusedExpressionChecker.INSTANCE.reportUnused(getContext(), getReporter(), expression, expression.getSource());
            }
        }
    }

    private FirUnusedExpressionChecker() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reportUnused(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirExpression firExpression, KtSourceElement ktSourceElement) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, ((firExpression instanceof FirAnonymousFunctionExpression) && ((FirAnonymousFunctionExpression) firExpression).getAnonymousFunction().getIsLambda()) ? FirErrors.INSTANCE.getUNUSED_LAMBDA_EXPRESSION() : FirErrors.INSTANCE.getUNUSED_EXPRESSION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBase
    public FirUnusedCheckerBase.UsageVisitorBase createVisitor(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        return new UsageVisitor(checkerContext, diagnosticReporter);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnusedCheckerBase
    public boolean isEnabled(CheckerContext checkerContext) {
        checkerContext.getClass();
        return true;
    }
}
