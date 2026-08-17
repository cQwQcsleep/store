package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedReifiedParameterReference;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeParameterInQualifiedAccess;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeParameterInQualifiedAccessChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "checkExpressionItself", "checkExplicitReceiver", "coneTypeParameterInQualifiedAccess", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeTypeParameterInQualifiedAccess;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeParameterInQualifiedAccess", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeTypeParameterInQualifiedAccess;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeParameterInQualifiedAccessChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirTypeParameterInQualifiedAccessChecker INSTANCE = new FirTypeParameterInQualifiedAccessChecker();

    private FirTypeParameterInQualifiedAccessChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkExplicitReceiver(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        ConeKotlinType resolvedType;
        ConeTypeParameterInQualifiedAccess coneTypeParameterInQualifiedAccess;
        FirTypeParameterSymbol symbol;
        FirExpression explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
        FirResolvedReifiedParameterReference firResolvedReifiedParameterReference = explicitReceiver instanceof FirResolvedReifiedParameterReference ? (FirResolvedReifiedParameterReference) explicitReceiver : null;
        if (firResolvedReifiedParameterReference == null || (symbol = firResolvedReifiedParameterReference.getSymbol()) == null) {
            if (explicitReceiver == null || (resolvedType = FirTypeUtilsKt.getResolvedType(explicitReceiver)) == null || (coneTypeParameterInQualifiedAccess = getConeTypeParameterInQualifiedAccess(resolvedType)) == null) {
                return;
            } else {
                symbol = coneTypeParameterInQualifiedAccess.getSymbol();
            }
        }
        FirTypeParameterSymbol firTypeParameterSymbol = symbol;
        if (firQualifiedAccessExpression instanceof FirCallableReferenceAccess) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirCallableReferenceAccess) firQualifiedAccessExpression).getSource(), FirErrors.INSTANCE.getCALLABLE_REFERENCE_LHS_NOT_A_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (explicitReceiver != null ? explicitReceiver.getSource() : null), (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPE_PARAMETER_ON_LHS_OF_DOT(), (Object) firTypeParameterSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final void checkExpressionItself(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        ConeTypeParameterInQualifiedAccess coneTypeParameterInQualifiedAccess;
        KtSourceElement source;
        FirGetClassCall firGetClassCall = (FirGetClassCall) CollectionsKt.lastOrNull(checkerContext.getGetClassCalls());
        if (Intrinsics.areEqual(firGetClassCall != null ? firGetClassCall.getArgument() : null, firQualifiedAccessExpression)) {
            return;
        }
        FirStatement firStatement = (FirStatement) CollectionsKt.getOrNull(checkerContext.getCallsOrAssignments(), checkerContext.getCallsOrAssignments().size() - 2);
        if (((firStatement instanceof FirQualifiedAccessExpression) && Intrinsics.areEqual(((FirQualifiedAccessExpression) firStatement).getExplicitReceiver(), firQualifiedAccessExpression)) || (coneTypeParameterInQualifiedAccess = getConeTypeParameterInQualifiedAccess(FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression))) == null || (source = firQualifiedAccessExpression.getCalleeReference().getSource()) == null) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPE_PARAMETER_IS_NOT_AN_EXPRESSION(), (Object) coneTypeParameterInQualifiedAccess.getSymbol(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    private final ConeTypeParameterInQualifiedAccess getConeTypeParameterInQualifiedAccess(ConeKotlinType coneKotlinType) {
        ConeErrorType coneErrorType = coneKotlinType instanceof ConeErrorType ? (ConeErrorType) coneKotlinType : null;
        ConeDiagnostic diagnostic = coneErrorType != null ? coneErrorType.getDiagnostic() : null;
        if (diagnostic instanceof ConeTypeParameterInQualifiedAccess) {
            return (ConeTypeParameterInQualifiedAccess) diagnostic;
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        checkExplicitReceiver(checkerContext, diagnosticReporter, firQualifiedAccessExpression);
        checkExpressionItself(checkerContext, diagnosticReporter, firQualifiedAccessExpression);
    }
}
