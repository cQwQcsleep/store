package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirMissingDependencySupertypeUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencySupertypeInQualifiedAccessExpressionsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMissingDependencySupertypeInQualifiedAccessExpressionsChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirMissingDependencySupertypeInQualifiedAccessExpressionsChecker INSTANCE = new FirMissingDependencySupertypeInQualifiedAccessExpressionsChecker();

    private FirMissingDependencySupertypeInQualifiedAccessExpressionsChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        ConeKotlinType resolvedType;
        ConeKotlinType resolvedType2;
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        KtSourceElement source = firQualifiedAccessExpression.getSource();
        ConeKotlinType symbol = null;
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default == null) {
            FirExpression explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
            FirResolvedQualifier firResolvedQualifier = explicitReceiver instanceof FirResolvedQualifier ? (FirResolvedQualifier) explicitReceiver : null;
            if (firResolvedQualifier == null || (resolvedType2 = firResolvedQualifier.getResolvedLHSTypeForCallableReferenceOrNull()) == null) {
                resolvedType2 = explicitReceiver != null ? FirTypeUtilsKt.getResolvedType(explicitReceiver) : null;
            }
            if (resolvedType2 != null && (coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(resolvedType2)) != null) {
                symbol = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) checkerContext, coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound);
            }
            FirMissingDependencySupertypeUtilsKt.checkMissingDependencySuperTypes(checkerContext, diagnosticReporter, symbol, source);
            return;
        }
        SmartSet smartSetCreate = SmartSet.Companion.create();
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        FirClassifierSymbol<?> symbol2 = (dispatchReceiver == null || (resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver)) == null) ? null : ToSymbolUtilsKt.toSymbol(checkerContext, resolvedType);
        boolean zCheckMissingDependencySuperTypes = FirMissingDependencySupertypeUtilsKt.checkMissingDependencySuperTypes(checkerContext, diagnosticReporter, symbol2, source, false);
        if (symbol2 != null) {
            smartSetCreate.add(symbol2);
        }
        boolean z = (resolvedCallableSymbol$default instanceof FirConstructorSymbol) || (resolvedCallableSymbol$default instanceof FirAnonymousFunctionSymbol) || zCheckMissingDependencySuperTypes;
        ConeClassLikeLookupTag ownerLookupTag = FirVisibilityCheckerKt.getOwnerLookupTag(resolvedCallableSymbol$default);
        FirClassLikeSymbol<?> symbol3 = ownerLookupTag != null ? ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, ownerLookupTag) : null;
        if (symbol3 != null && smartSetCreate.add(symbol3)) {
            FirMissingDependencySupertypeUtilsKt.checkMissingDependencySuperTypes(checkerContext, diagnosticReporter, symbol3, source, z);
        }
        ConeKotlinType resolvedReceiverType = resolvedCallableSymbol$default.getResolvedReceiverType();
        symbol = resolvedReceiverType != null ? ToSymbolUtilsKt.toSymbol(checkerContext, resolvedReceiverType) : null;
        if (symbol == null || !smartSetCreate.add(symbol)) {
            return;
        }
        FirMissingDependencySupertypeUtilsKt.checkMissingDependencySuperTypes(checkerContext, diagnosticReporter, symbol, source, z);
    }
}
