package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirQualifiedAccessJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.SubstitutionUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirQualifiedAccessJavaNullabilityWarningChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirQualifiedAccessJavaNullabilityWarningChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirQualifiedAccessJavaNullabilityWarningChecker INSTANCE = new FirQualifiedAccessJavaNullabilityWarningChecker();

    private FirQualifiedAccessJavaNullabilityWarningChecker() {
        super(MppCheckerKind.Common);
    }

    public static boolean b(CheckerContext checkerContext, FirCallableSymbol firCallableSymbol, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return FirExpressionJavaNullabilityWarningCheckersKt.shouldSuppressWarningForExtensionReceiver(checkerContext, firCallableSymbol, coneKotlinType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(final CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        final FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
        if (resolvedCallableSymbol == null) {
            return;
        }
        ConeSubstitutor coneSubstitutorCreateConeSubstitutorFromTypeArguments$default = SubstitutionUtilsKt.createConeSubstitutorFromTypeArguments$default(firQualifiedAccessExpression, resolvedCallableSymbol, checkerContext.getSession(), false, false, 12, null);
        FirExpressionJavaNullabilityWarningCheckersKt.checkDispatchReceiver(checkerContext, diagnosticReporter, firQualifiedAccessExpression, resolvedCallableSymbol);
        FirExpression extensionReceiver = firQualifiedAccessExpression.getExtensionReceiver();
        if (extensionReceiver != null) {
            ConeKotlinType resolvedReceiverType = resolvedCallableSymbol.getResolvedReceiverType();
            FirExpressionJavaNullabilityWarningCheckersKt.checkExpressionForEnhancedTypeMismatch(diagnosticReporter, checkerContext, extensionReceiver, resolvedReceiverType != null ? coneSubstitutorCreateConeSubstitutorFromTypeArguments$default.substituteOrSelf(resolvedReceiverType) : null, FirJvmErrors.INSTANCE.getRECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS(), new Function1() { // from class: fc5
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(FirQualifiedAccessJavaNullabilityWarningChecker.b(checkerContext, resolvedCallableSymbol, (ConeKotlinType) obj));
                }
            });
        }
        for (Pair pair : CollectionsKt.zip(firQualifiedAccessExpression.getContextArguments(), resolvedCallableSymbol.getContextParameterSymbols())) {
            FirExpressionJavaNullabilityWarningCheckersKt.checkExpressionForEnhancedTypeMismatch$default(diagnosticReporter, checkerContext, (FirExpression) pair.component1(), coneSubstitutorCreateConeSubstitutorFromTypeArguments$default.substituteOrSelf(((FirValueParameterSymbol) pair.component2()).getResolvedReturnType()), FirJvmErrors.INSTANCE.getTYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS(), null, 16, null);
        }
        if (firQualifiedAccessExpression instanceof FirFunctionCall) {
            FirArgumentList argumentList = ((FirCall) firQualifiedAccessExpression).getArgumentList();
            LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
            if (mapping != null) {
                for (Map.Entry<FirExpression, FirValueParameter> entry : mapping.entrySet()) {
                    FirExpressionJavaNullabilityWarningCheckersKt.checkExpressionForEnhancedTypeMismatch$default(diagnosticReporter, checkerContext, entry.getKey(), coneSubstitutorCreateConeSubstitutorFromTypeArguments$default.substituteOrSelf(FirTypeUtilsKt.getConeType(entry.getValue().getReturnTypeRef())), FirJvmErrors.INSTANCE.getTYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS(), null, 16, null);
                }
            }
        }
    }
}
