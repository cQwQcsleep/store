package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.utils.FirScriptCustomizationKind;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirForLoopStatementAssignmentChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirLoop;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirForLoopStatementAssignmentChecker extends FirExpressionChecker<FirLoop> {
    public static final FirForLoopStatementAssignmentChecker INSTANCE = new FirForLoopStatementAssignmentChecker();

    private FirForLoopStatementAssignmentChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirLoop firLoop) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firLoop.getClass();
        if (checkerContext.getContainingElements().size() >= 2) {
            KtSourceElement source = checkerContext.getContainingElements().get(checkerContext.getContainingElements().size() - 2).getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DesugaredForLoop.INSTANCE) && checkerContext.getContainingElements().size() >= 3) {
                FirElement firElement = checkerContext.getContainingElements().get(checkerContext.getContainingElements().size() - 3);
                if (firElement instanceof FirBlock) {
                    return;
                }
                if (firElement instanceof FirReturnExpression) {
                    KtSourceElement source2 = ((FirReturnExpression) firElement).getSource();
                    if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitReturn.FromLastStatement.INSTANCE)) {
                        return;
                    }
                }
                if (firElement instanceof FirProperty) {
                    FirDeclarationOrigin origin = ((FirProperty) firElement).getOrigin();
                    FirDeclarationOrigin.ScriptCustomization scriptCustomization = origin instanceof FirDeclarationOrigin.ScriptCustomization ? (FirDeclarationOrigin.ScriptCustomization) origin : null;
                    if ((scriptCustomization != null ? scriptCustomization.getKind() : null) == FirScriptCustomizationKind.RESULT_PROPERTY) {
                        return;
                    }
                }
                if (firElement instanceof FirErrorExpression) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firLoop.getSource(), FirErrors.INSTANCE.getEXPRESSION_EXPECTED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }
}
