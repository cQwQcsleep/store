package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJavaClassOnCompanionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaClassOnCompanionChecker extends FirExpressionChecker<FirPropertyAccessExpression> {
    public static final FirJavaClassOnCompanionChecker INSTANCE = new FirJavaClassOnCompanionChecker();

    private FirJavaClassOnCompanionChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirPropertyAccessExpression firPropertyAccessExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType type;
        FirRegularClassSymbol regularClassSymbol;
        FirClassLikeSymbol<?> containingClassSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firPropertyAccessExpression.getClass();
        FirNamedReference calleeReference = firPropertyAccessExpression.getCalleeReference();
        FirResolvedNamedReference firResolvedNamedReference = calleeReference instanceof FirResolvedNamedReference ? (FirResolvedNamedReference) calleeReference : null;
        if (firResolvedNamedReference == null) {
            return;
        }
        FirBasedSymbol<?> symbol = FirReferenceUtilsKt.getSymbol(firResolvedNamedReference);
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (Intrinsics.areEqual(firCallableSymbol != null ? firCallableSymbol.getCallableId() : null, JvmStandardClassIds.Callables.INSTANCE.getJavaClass())) {
            ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firPropertyAccessExpression);
            ConeClassLikeType coneClassLikeType = resolvedType instanceof ConeClassLikeType ? (ConeClassLikeType) resolvedType : null;
            if (coneClassLikeType == null) {
                return;
            }
            Object objSingleOrNull = ArraysKt.singleOrNull(coneClassLikeType.getTypeArguments());
            ConeKotlinTypeProjection coneKotlinTypeProjection = objSingleOrNull instanceof ConeKotlinTypeProjection ? (ConeKotlinTypeProjection) objSingleOrNull : null;
            if (coneKotlinTypeProjection == null || (type = coneKotlinTypeProjection.getType()) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, type)) == null || !regularClassSymbol.getRawStatus().isCompanion() || (containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(regularClassSymbol)) == null) {
                return;
            }
            FirExpression extensionReceiver = firPropertyAccessExpression.getExtensionReceiver();
            FirResolvedQualifier firResolvedQualifier = extensionReceiver instanceof FirResolvedQualifier ? (FirResolvedQualifier) extensionReceiver : null;
            if (firResolvedQualifier != null && Intrinsics.areEqual(firResolvedQualifier.getSymbol(), containingClassSymbol)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessExpression.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getJAVA_CLASS_ON_COMPANION(), (Object) coneClassLikeType, (Object) TypeConstructionUtilsKt.constructClassType$default(coneClassLikeType.getLookupTag(), new ConeClassLikeType[]{ScopeUtilsKt.defaultType(containingClassSymbol)}, coneClassLikeType.getIsMarkedNullable(), null, 4, null), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }
    }
}
