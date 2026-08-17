package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0007*\u0004\u0018\u00010\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirRecursiveProblemChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "shouldBeReported", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceElementKind;", "getShouldBeReported", "(Lorg/jetbrains/kotlin/KtSourceElementKind;)Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRecursiveProblemChecker extends FirExpressionChecker<FirStatement> {
    public static final FirRecursiveProblemChecker INSTANCE = new FirRecursiveProblemChecker();

    private FirRecursiveProblemChecker() {
        super(MppCheckerKind.Common);
    }

    private static final void check$checkConeType(FirStatement firStatement, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType) {
        if (coneKotlinType != null && FirHelpersKt.hasDiagnosticKind(coneKotlinType, DiagnosticKind.RecursionInImplicitTypes)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirExpression) firStatement).getSource(), FirErrors.INSTANCE.getTYPECHECKER_HAS_RUN_INTO_RECURSIVE_PROBLEM(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (coneKotlinType instanceof ConeClassLikeType) {
            ConeTypeProjection[] typeArguments = ((ConeClassLikeType) coneKotlinType).getTypeArguments();
            for (ConeTypeProjection coneTypeProjection : typeArguments) {
                check$checkConeType(firStatement, checkerContext, diagnosticReporter, ConeTypeProjectionKt.getType(coneTypeProjection));
            }
        }
    }

    private final boolean getShouldBeReported(KtSourceElementKind ktSourceElementKind) {
        return !(ktSourceElementKind instanceof KtFakeSourceElementKind);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        if (firStatement instanceof FirExpression) {
            FirExpression firExpression = (FirExpression) firStatement;
            KtSourceElement source = firExpression.getSource();
            if (getShouldBeReported(source != null ? source.getKind() : null)) {
                check$checkConeType(firStatement, checkerContext, diagnosticReporter, FirTypeUtilsKt.getResolvedType(firExpression));
            }
        }
    }
}
