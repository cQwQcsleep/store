package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineExposedLessVisibleTypeQualifiedAccessChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInlineExposedLessVisibleTypeQualifiedAccessChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirInlineExposedLessVisibleTypeQualifiedAccessChecker INSTANCE = new FirInlineExposedLessVisibleTypeQualifiedAccessChecker();

    private FirInlineExposedLessVisibleTypeQualifiedAccessChecker() {
        super(MppCheckerKind.Platform);
    }

    private static final void check$reportIfLessVisible(ConeKotlinType coneKotlinType, CheckerContext checkerContext, FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression, FirCallableSymbol<?> firCallableSymbol, boolean z) {
        EffectiveVisibility effectiveVisibilityLessVisibleVisibilityOrNull;
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType)});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinType2 = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
            FirClassLikeSymbol<?> classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext, coneKotlinType2);
            if (classLikeSymbol != null && (effectiveVisibilityLessVisibleVisibilityOrNull = inlineFunctionBodyContext.lessVisibleVisibilityOrNull(classLikeSymbol, z)) != null) {
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), (KtDiagnosticFactoryForDeprecation4<FirCallableSymbol<?>, EffectiveVisibility, ConeKotlinType, EffectiveVisibility>) ((KtDiagnosticFactoryForDeprecation4<Object, Object, Object, Object>) FirErrors.INSTANCE.getLESS_VISIBLE_TYPE_IN_INLINE_ACCESSED_SIGNATURE()), firCallableSymbol, effectiveVisibilityLessVisibleVisibilityOrNull, coneKotlinType2, inlineFunctionBodyContext.getInlineFunEffectiveVisibility(), (128 & 128) != 0 ? null : null);
            }
            if (coneKotlinType2 instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType2;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinType2 instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType2).getOriginal());
            } else if (coneKotlinType2 instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType2).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType2.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        ConeClassLikeType coneClassLikeTypeDefaultType;
        ConeKotlinType resolvedType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = checkerContext.getInlineFunctionBodyContext();
        if (inlineFunctionBodyContext == null || Intrinsics.areEqual(inlineFunctionBodyContext.getInlineFunEffectiveVisibility(), EffectiveVisibility.Public.INSTANCE)) {
            return;
        }
        List<FirStatement> callsOrAssignments = checkerContext.getCallsOrAssignments();
        if (!(callsOrAssignments instanceof Collection) || !callsOrAssignments.isEmpty()) {
            Iterator<T> it = callsOrAssignments.iterator();
            while (it.hasNext()) {
                if (((FirStatement) it.next()) instanceof FirAnnotation) {
                    return;
                }
            }
        }
        FirCallableSymbol<?> resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
        if (resolvedCallableSymbol == null || (resolvedCallableSymbol.getResolvedStatus().getEffectiveVisibility() instanceof EffectiveVisibility.Local)) {
            return;
        }
        Iterator<T> it2 = resolvedCallableSymbol.getContextParameterSymbols().iterator();
        while (it2.hasNext()) {
            check$reportIfLessVisible(((FirValueParameterSymbol) it2.next()).getResolvedReturnType(), checkerContext, inlineFunctionBodyContext, diagnosticReporter, firQualifiedAccessExpression, resolvedCallableSymbol, false);
        }
        FirReceiverParameterSymbol receiverParameterSymbol = resolvedCallableSymbol.getReceiverParameterSymbol();
        if (receiverParameterSymbol != null && (resolvedType = receiverParameterSymbol.getResolvedType()) != null) {
            check$reportIfLessVisible(resolvedType, checkerContext, inlineFunctionBodyContext, diagnosticReporter, firQualifiedAccessExpression, resolvedCallableSymbol, false);
        }
        if (resolvedCallableSymbol instanceof FirFunctionSymbol) {
            Iterator<T> it3 = ((FirFunctionSymbol) resolvedCallableSymbol).getValueParameterSymbols().iterator();
            while (it3.hasNext()) {
                check$reportIfLessVisible(((FirValueParameterSymbol) it3.next()).getResolvedReturnType(), checkerContext, inlineFunctionBodyContext, diagnosticReporter, firQualifiedAccessExpression, resolvedCallableSymbol, false);
            }
        }
        Iterator<T> it4 = resolvedCallableSymbol.getTypeParameterSymbols().iterator();
        while (it4.hasNext()) {
            Iterator<T> it5 = ((FirTypeParameterSymbol) it4.next()).getResolvedBounds().iterator();
            while (it5.hasNext()) {
                check$reportIfLessVisible(((FirResolvedTypeRef) it5.next()).getConeType(), checkerContext, inlineFunctionBodyContext, diagnosticReporter, firQualifiedAccessExpression, resolvedCallableSymbol, false);
            }
        }
        check$reportIfLessVisible(resolvedCallableSymbol.getResolvedReturnType(), checkerContext, inlineFunctionBodyContext, diagnosticReporter, firQualifiedAccessExpression, resolvedCallableSymbol, false);
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        if (dispatchReceiver != null) {
            if (!(dispatchReceiver instanceof FirResolvedQualifier)) {
                check$reportIfLessVisible(FirTypeUtilsKt.getResolvedType(dispatchReceiver), checkerContext, inlineFunctionBodyContext, diagnosticReporter, firQualifiedAccessExpression, resolvedCallableSymbol, true);
                return;
            }
            FirClassLikeSymbol<?> firClassLikeSymbolResolvedSymbolOrCompanionSymbol = FirHelpersKt.resolvedSymbolOrCompanionSymbol(checkerContext, (FirResolvedQualifier) dispatchReceiver);
            if (firClassLikeSymbolResolvedSymbolOrCompanionSymbol == null || (coneClassLikeTypeDefaultType = ScopeUtilsKt.defaultType(firClassLikeSymbolResolvedSymbolOrCompanionSymbol)) == null) {
                return;
            }
            check$reportIfLessVisible(coneClassLikeTypeDefaultType, checkerContext, inlineFunctionBodyContext, diagnosticReporter, firQualifiedAccessExpression, resolvedCallableSymbol, true);
        }
    }
}
