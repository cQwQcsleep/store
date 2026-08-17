package org.jetbrains.kotlin.fir.analysis.js.checkers.expression;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsModuleCheckUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ(\u0010\u000e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00100\u000f2\u0006\u0010\f\u001a\u00020\u0002H\u0002J-\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/expression/FirJsModuleQualifiedAccessChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "extractModuleCalleeSymbols", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "checkReifiedTypeParameters", "expr", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsModuleQualifiedAccessChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirJsModuleQualifiedAccessChecker INSTANCE = new FirJsModuleQualifiedAccessChecker();

    private FirJsModuleQualifiedAccessChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkReifiedTypeParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default;
        FirRegularClassSymbol regularClassSymbol;
        FirFunctionCall firFunctionCall = firQualifiedAccessExpression instanceof FirFunctionCall ? (FirFunctionCall) firQualifiedAccessExpression : null;
        if (firFunctionCall == null || (resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null)) == null) {
            return;
        }
        for (Pair pair : CollectionsKt.zip(resolvedNamedFunctionSymbol$default.getTypeParameterSymbols(), firFunctionCall.getTypeArguments())) {
            FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) pair.component1();
            FirTypeProjection firTypeProjection = (FirTypeProjection) pair.component2();
            if (firTypeParameterSymbol.isReified() && (firTypeProjection instanceof FirTypeProjectionWithVariance)) {
                FirTypeProjectionWithVariance firTypeProjectionWithVariance = (FirTypeProjectionWithVariance) firTypeProjection;
                ConeKotlinType coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(firTypeProjectionWithVariance.getTypeRef());
                if (coneTypeOrNull != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneTypeOrNull)) != null) {
                    KtSourceElement source = firTypeProjectionWithVariance.getSource();
                    if (source == null) {
                        FirFunctionCall firFunctionCall2 = (FirFunctionCall) firQualifiedAccessExpression;
                        KtSourceElement source2 = firFunctionCall2.getCalleeReference().getSource();
                        source = source2 == null ? firFunctionCall2.getSource() : source2;
                    }
                    FirJsModuleCheckUtilsKt.checkJsModuleUsage(checkerContext, diagnosticReporter, regularClassSymbol, source);
                }
            }
        }
    }

    private final List<Pair<FirBasedSymbol<?>, AbstractKtSourceElement>> extractModuleCalleeSymbols(FirQualifiedAccessExpression expression) {
        FirBasedSymbol resolvedBaseSymbol$default = FirReferenceUtilsKt.toResolvedBaseSymbol$default(expression.getCalleeReference(), false, 1, null);
        if (resolvedBaseSymbol$default != null && ContainingClassUtilsKt.getContainingClassSymbol((FirBasedSymbol<?>) resolvedBaseSymbol$default) == null) {
            return CollectionsKt.listOf(TuplesKt.to(resolvedBaseSymbol$default, expression.getCalleeReference().getSource()));
        }
        FirExpression dispatchReceiver = expression.getDispatchReceiver();
        FirExpression firExpressionUnwrapSmartcastExpression = dispatchReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(dispatchReceiver) : null;
        if (firExpressionUnwrapSmartcastExpression == null) {
            return CollectionsKt.listOfNotNull(resolvedBaseSymbol$default != null ? TuplesKt.to(resolvedBaseSymbol$default, expression.getCalleeReference().getSource()) : null);
        }
        if (!(firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier)) {
            return CollectionsKt.emptyList();
        }
        FirResolvedQualifier firResolvedQualifier = (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression;
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (expression instanceof FirCallableReferenceAccess) {
            return CollectionsKt.listOfNotNull(new Pair[]{symbol != null ? TuplesKt.to(symbol, firResolvedQualifier.getSource()) : null, resolvedBaseSymbol$default != null ? TuplesKt.to(resolvedBaseSymbol$default, ((FirCallableReferenceAccess) expression).getCalleeReference().getSource()) : null});
        }
        return CollectionsKt.listOfNotNull(symbol != null ? TuplesKt.to(symbol, expression.getCalleeReference().getSource()) : null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        checkReifiedTypeParameters(checkerContext, diagnosticReporter, firQualifiedAccessExpression);
        for (Pair<FirBasedSymbol<?>, AbstractKtSourceElement> pair : extractModuleCalleeSymbols(firQualifiedAccessExpression)) {
            FirBasedSymbol firBasedSymbol = (FirBasedSymbol) pair.component1();
            KtSourceElement source = (AbstractKtSourceElement) pair.component2();
            if (source == null) {
                source = firQualifiedAccessExpression.getSource();
            }
            FirJsModuleCheckUtilsKt.checkJsModuleUsage(checkerContext, diagnosticReporter, firBasedSymbol, source);
        }
    }
}
