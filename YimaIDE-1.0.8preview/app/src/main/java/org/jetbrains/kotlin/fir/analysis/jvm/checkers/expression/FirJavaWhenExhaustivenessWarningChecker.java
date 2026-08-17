package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.java.enhancement.EnhancedTypeForWarningAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJavaWhenExhaustivenessWarningChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaWhenExhaustivenessWarningChecker extends FirExpressionChecker<FirWhenExpression> {
    public static final FirJavaWhenExhaustivenessWarningChecker INSTANCE = new FirJavaWhenExhaustivenessWarningChecker();

    private FirJavaWhenExhaustivenessWarningChecker() {
        super(MppCheckerKind.Platform);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression) {
        FirVariable subjectVariable;
        ConeKotlinType coneType;
        ConeKotlinType enhancedTypeForWarning;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firWhenExpression.getClass();
        if (!ExhaustivenessStatusKt.isExhaustive(firWhenExpression) || (subjectVariable = firWhenExpression.getSubjectVariable()) == null || (enhancedTypeForWarning = EnhancedTypeForWarningAttributeKt.getEnhancedTypeForWarning((coneType = FirTypeUtilsKt.getConeType(subjectVariable.getReturnTypeRef())))) == null || !TypeUtilsKt.canBeNull$default(ConeTypeUtilsKt.lowerBoundIfFlexible(enhancedTypeForWarning), checkerContext.getSession(), false, null, 6, null) || TypeUtilsKt.canBeNull$default(ConeTypeUtilsKt.lowerBoundIfFlexible(coneType), checkerContext.getSession(), false, null, 6, null)) {
            return;
        }
        List<FirWhenBranch> branches = firWhenExpression.getBranches();
        if (!(branches instanceof Collection) || !branches.isEmpty()) {
            for (FirWhenBranch firWhenBranch : branches) {
                boolean zIsNullableNothing = false;
                if (!firWhenBranch.getHasGuard()) {
                    FirAnnotationContainer condition = firWhenBranch.getCondition();
                    if (condition instanceof FirEqualityOperatorCall) {
                        zIsNullableNothing = ConeBuiltinTypeUtilsKt.isNullableNothing(FirTypeUtilsKt.getResolvedType(((FirCall) condition).getArgumentList().getArguments().get(1)));
                    } else if (condition instanceof FirTypeOperatorCall) {
                        FirTypeOperatorCall firTypeOperatorCall = (FirTypeOperatorCall) condition;
                        if (firTypeOperatorCall.getOperation() == FirOperation.IS && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(FirTypeUtilsKt.getConeType(firTypeOperatorCall.getConversionTypeRef()))) {
                            zIsNullableNothing = true;
                        }
                    }
                }
                if (zIsNullableNothing) {
                    return;
                }
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firWhenExpression.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getUNEXHAUSTIVE_WHEN_BASED_ON_JAVA_ANNOTATIONS(), (Object) enhancedTypeForWarning, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }
}
