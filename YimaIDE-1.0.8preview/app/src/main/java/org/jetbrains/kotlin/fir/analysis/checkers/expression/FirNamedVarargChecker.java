package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0010H\u0002\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0001(\u0000ò\u0001\u0004\n\u00020\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirNamedVarargChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCall;)V", "isNamedSpread", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNamedVarargChecker extends FirExpressionChecker<FirCall> {
    public static final FirNamedVarargChecker INSTANCE = new FirNamedVarargChecker();

    private FirNamedVarargChecker() {
        super(MppCheckerKind.Common);
    }

    private static final void check$checkArgument(FirCall firCall, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtDiagnosticFactory0 ktDiagnosticFactory0, boolean z, FirExpression firExpression, boolean z2, ConeKotlinType coneKotlinType) {
        if (INSTANCE.isNamedSpread(firExpression)) {
            FirSpreadArgumentExpression firSpreadArgumentExpression = (FirSpreadArgumentExpression) firExpression;
            if (!firSpreadArgumentExpression.getIsFakeSpread() && firSpreadArgumentExpression.getIsNamed()) {
                if (z2) {
                    FirResolvable firResolvable = firCall instanceof FirResolvable ? (FirResolvable) firCall : null;
                    if ((firResolvable != null ? firResolvable.getCalleeReference() : null) instanceof FirResolvedErrorReference) {
                        return;
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firSpreadArgumentExpression.getSource(), ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
                return;
            }
            ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getResolvedType(firSpreadArgumentExpression.getExpression())));
            if ((coneRigidTypeLowerBoundIfFlexible instanceof ConeErrorType) || (firSpreadArgumentExpression.getExpression() instanceof FirCollectionLiteral) || ConeBuiltinTypeUtilsKt.isArrayType(coneRigidTypeLowerBoundIfFlexible)) {
                return;
            }
            if (z) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firSpreadArgumentExpression.getExpression().getSource(), FirErrors.INSTANCE.getASSIGNING_SINGLE_ELEMENT_TO_VARARG_IN_NAMED_FORM_ANNOTATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firSpreadArgumentExpression.getExpression().getSource(), (KtDiagnosticFactoryForDeprecation1) FirErrors.INSTANCE.getASSIGNING_SINGLE_ELEMENT_TO_VARARG_IN_NAMED_FORM_FUNCTION(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private final boolean isNamedSpread(FirExpression expression) {
        return (expression instanceof FirSpreadArgumentExpression) && ((FirSpreadArgumentExpression) expression).getIsNamed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCall firCall) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCall.getClass();
        if ((firCall instanceof FirFunctionCall) || (firCall instanceof FirAnnotation) || (firCall instanceof FirDelegatedConstructorCall) || (firCall instanceof FirCollectionLiteral)) {
            boolean z = firCall instanceof FirAnnotation;
            KtDiagnosticFactory0 redundant_spread_operator_in_named_form_in_annotation = z ? FirErrors.INSTANCE.getREDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_ANNOTATION() : FirErrors.INSTANCE.getREDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION();
            if (firCall instanceof FirCollectionLiteral) {
                for (FirExpression firExpression : firCall.getArgumentList().getArguments()) {
                    check$checkArgument(firCall, checkerContext, diagnosticReporter, redundant_spread_operator_in_named_form_in_annotation, z, firExpression, INSTANCE.isNamedSpread(firExpression), FirTypeUtilsKt.getResolvedType((FirExpression) firCall));
                }
                return;
            }
            FirArgumentList argumentList = firCall.getArgumentList();
            LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
            if (mapping == null) {
                return;
            }
            for (Map.Entry<FirExpression, FirValueParameter> entry : mapping.entrySet()) {
                FirExpression key = entry.getKey();
                FirValueParameter value = entry.getValue();
                if (value.getIsVararg()) {
                    if (key instanceof FirVarargArgumentsExpression) {
                        Iterator<T> it = ((FirVarargArgumentsExpression) key).getArguments().iterator();
                        while (it.hasNext()) {
                            check$checkArgument(firCall, checkerContext, diagnosticReporter, redundant_spread_operator_in_named_form_in_annotation, z, (FirExpression) it.next(), true, FirTypeUtilsKt.getConeType(value.getReturnTypeRef()));
                        }
                    } else {
                        check$checkArgument(firCall, checkerContext, diagnosticReporter, redundant_spread_operator_in_named_form_in_annotation, z, key, false, FirTypeUtilsKt.getConeType(value.getReturnTypeRef()));
                    }
                }
            }
        }
    }
}
