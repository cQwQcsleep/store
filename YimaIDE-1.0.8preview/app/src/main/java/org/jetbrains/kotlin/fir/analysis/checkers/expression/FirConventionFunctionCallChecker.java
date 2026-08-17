package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.List;
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
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNotFunctionAsOperator;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013J-\u0010\u0014\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015J-\u0010\u0016\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirConventionFunctionCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "checkNotFunctionAsOperator", Argument.Delimiters.none, "callExpression", "receiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "checkNoGetSetMethods", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "checkCompareToTypeMismatch", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConventionFunctionCallChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirConventionFunctionCallChecker INSTANCE = new FirConventionFunctionCallChecker();

    private FirConventionFunctionCallChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkCompareToTypeMismatch(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirFunctionCall firFunctionCall) {
        if (firFunctionCall.getOrigin() == FirFunctionCallOrigin.Operator && Intrinsics.areEqual(firFunctionCall.getCalleeReference().getName(), OperatorNameConventions.COMPARE_TO)) {
            ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getResolvedType(firFunctionCall));
            if (ConeBuiltinTypeUtilsKt.isInt(coneKotlinTypeFullyExpandedType) || (coneKotlinTypeFullyExpandedType instanceof ConeDynamicType) || ConeTypeUtilsKt.hasError(coneKotlinTypeFullyExpandedType)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCOMPARE_TO_TYPE_MISMATCH(), (Object) FirTypeUtilsKt.getResolvedType(firFunctionCall), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final void checkNoGetSetMethods(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirFunctionCall firFunctionCall) {
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        FirErrorNamedReference firErrorNamedReference = calleeReference instanceof FirErrorNamedReference ? (FirErrorNamedReference) calleeReference : null;
        if (firErrorNamedReference == null) {
            return;
        }
        ConeDiagnostic diagnostic = firErrorNamedReference.getDiagnostic();
        ConeUnresolvedNameError coneUnresolvedNameError = diagnostic instanceof ConeUnresolvedNameError ? (ConeUnresolvedNameError) diagnostic : null;
        if (coneUnresolvedNameError == null) {
            return;
        }
        KtSourceElement source = firFunctionCall.getCalleeReference().getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE)) {
            Name name = coneUnresolvedNameError.getName();
            if (Intrinsics.areEqual(name, OperatorNameConventions.GET)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firErrorNamedReference.getSource(), FirErrors.INSTANCE.getNO_GET_METHOD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else if (Intrinsics.areEqual(name, OperatorNameConventions.SET)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firErrorNamedReference.getSource(), FirErrors.INSTANCE.getNO_SET_METHOD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final boolean checkNotFunctionAsOperator(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall, FirExpression firExpression) {
        FirExpression firExpressionUnwrapSmartcastExpression;
        List<ConeDiagnostic> nonFatalDiagnostics;
        FirBasedSymbol<?> symbol;
        FirExpression dispatchReceiver = firFunctionCall.getDispatchReceiver();
        Object obj = null;
        if (!((dispatchReceiver != null ? FirTypeUtilsKt.getResolvedType(dispatchReceiver) : null) instanceof ConeDynamicType) && firExpression != null && (firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(firExpression)) != null) {
            if (firExpressionUnwrapSmartcastExpression instanceof FirQualifiedAccessExpression) {
                nonFatalDiagnostics = ((FirQualifiedAccessExpression) firExpressionUnwrapSmartcastExpression).getNonFatalDiagnostics();
            } else if (firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier) {
                nonFatalDiagnostics = ((FirResolvedQualifier) firExpressionUnwrapSmartcastExpression).getNonFatalDiagnostics();
            }
            for (Object obj2 : nonFatalDiagnostics) {
                if (obj2 instanceof ConeNotFunctionAsOperator) {
                    obj = obj2;
                    break;
                }
            }
            ConeNotFunctionAsOperator coneNotFunctionAsOperator = (ConeNotFunctionAsOperator) obj;
            if (coneNotFunctionAsOperator != null && (symbol = coneNotFunctionAsOperator.getSymbol()) != null) {
                ClassId classId = ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getResolvedType(firExpressionUnwrapSmartcastExpression));
                classId.getClass();
                if (Intrinsics.areEqual(classId.getShortClassName(), OperatorNameConventions.ITERATOR)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpressionUnwrapSmartcastExpression.getSource(), FirErrors.INSTANCE.getITERATOR_MISSING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return false;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getCalleeReference().getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getNOT_FUNCTION_AS_OPERATOR(), (Object) (symbol instanceof FirPropertySymbol ? "Property" : "Object"), (Object) symbol, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        boolean zCheckNotFunctionAsOperator = checkNotFunctionAsOperator(checkerContext, diagnosticReporter, firFunctionCall, firFunctionCall.getDispatchReceiver());
        boolean zCheckNotFunctionAsOperator2 = checkNotFunctionAsOperator(checkerContext, diagnosticReporter, firFunctionCall, firFunctionCall.getExtensionReceiver());
        if (!zCheckNotFunctionAsOperator && !zCheckNotFunctionAsOperator2) {
            checkCompareToTypeMismatch(diagnosticReporter, checkerContext, firFunctionCall);
        }
        checkNoGetSetMethods(diagnosticReporter, checkerContext, firFunctionCall);
    }
}
