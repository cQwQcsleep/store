package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirErrorFunction;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirUnitExpression;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionReturnTypeMismatchChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReturnExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFunctionReturnTypeMismatchChecker extends FirExpressionChecker<FirReturnExpression> {
    public static final FirFunctionReturnTypeMismatchChecker INSTANCE = new FirFunctionReturnTypeMismatchChecker();

    private FirFunctionReturnTypeMismatchChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirReturnExpression firReturnExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firReturnExpression.getClass();
        KtSourceElement source = firReturnExpression.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE)) {
            return;
        }
        FirFunction firFunction = (FirFunction) firReturnExpression.getTarget().getLabeledElement();
        if (firFunction instanceof FirErrorFunction) {
            return;
        }
        KtSourceElement source2 = firReturnExpression.getSource();
        KtSourceElementKind kind = source2 != null ? source2.getKind() : null;
        if (DeclarationUtilsKt.getHasExplicitReturnType(firFunction.getSymbol()) || Intrinsics.areEqual(kind, KtRealSourceElementKind.INSTANCE) || (firFunction instanceof FirPropertyAccessor)) {
            FirExpression result = firReturnExpression.getResult();
            if (!(result instanceof FirWhenExpression) || ExhaustivenessStatusKt.isExhaustive((FirWhenExpression) result)) {
                ConeKotlinType coneType = firFunction instanceof FirConstructor ? checkerContext.getSession().getBuiltinTypes().getUnitType().getConeType() : TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firFunction.getReturnTypeRef()));
                if (firFunction instanceof FirAnonymousFunction) {
                    if ((kind instanceof KtFakeSourceElementKind.ImplicitReturn.FromLastStatement) && ConeBuiltinTypeUtilsKt.isUnit(coneType)) {
                        return;
                    }
                    if (((FirAnonymousFunction) firFunction).getIsLambda() && !ConeBuiltinTypeUtilsKt.isUnit(coneType)) {
                        if (!(result instanceof FirUnitExpression)) {
                            return;
                        }
                        KtSourceElement source3 = ((FirUnitExpression) result).getSource();
                        if (!((source3 != null ? source3.getKind() : null) instanceof KtFakeSourceElementKind.ImplicitUnit)) {
                            return;
                        }
                    }
                }
                ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(checkerContext.getSession());
                ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(result);
                if (FirHelpersKt.isSubtypeForTypeMismatch(typeContext, resolvedType, coneType)) {
                    KtSourceElement source4 = result.getSource();
                    if (!((source4 != null ? source4.getKind() : null) instanceof KtFakeSourceElementKind.ImplicitUnit) || ConeBuiltinTypeUtilsKt.isUnit(ConeTypeUtilsKt.lowerBoundIfFlexible(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneType)))) {
                        return;
                    }
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) result.getSource(), (KtDiagnosticFactory4<ConeKotlinType, ConeKotlinType, FirFunction, Boolean>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) FirErrors.INSTANCE.getRETURN_TYPE_MISMATCH()), coneType, resolvedType, firFunction, Boolean.FALSE, (128 & 128) != 0 ? null : null);
                    return;
                }
                if (FirTypeUtilsKt.isNullLiteral(result) && !ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneType)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) result.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNULL_FOR_NONNULL_TYPE(), (Object) coneType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    return;
                }
                boolean zIsTypeMismatchDueToNullability = InferenceUtilsKt.isTypeMismatchDueToNullability(TypeComponentsKt.getTypeContext(checkerContext.getSession()), resolvedType, coneType);
                if (result instanceof FirSmartCastExpression) {
                    FirSmartCastExpression firSmartCastExpression = (FirSmartCastExpression) result;
                    if (!firSmartCastExpression.isStable() && FirHelpersKt.isSubtypeForTypeMismatch(typeContext, FirTypeUtilsKt.getConeType(firSmartCastExpression.getSmartcastType()), coneType)) {
                        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firSmartCastExpression.getSource(), (KtDiagnosticFactory4<ConeKotlinType, FirExpression, String, Boolean>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) FirErrors.INSTANCE.getSMARTCAST_IMPOSSIBLE()), coneType, result, firSmartCastExpression.getSmartcastStability().getDescription(), Boolean.valueOf(zIsTypeMismatchDueToNullability), (128 & 128) != 0 ? null : null);
                        return;
                    }
                }
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) result.getSource(), (KtDiagnosticFactory4<ConeKotlinType, ConeKotlinType, FirFunction, Boolean>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) FirErrors.INSTANCE.getRETURN_TYPE_MISMATCH()), coneType, resolvedType, firFunction, Boolean.valueOf(zIsTypeMismatchDueToNullability), (128 & 128) != 0 ? null : null);
            }
        }
    }
}
