package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUpperBoundViolatedHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.TypeWillChangeAttribute;
import org.jetbrains.kotlin.fir.expressions.TypeWillChangeAttributeKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0002H\u0002¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirUpperBoundViolatedQualifiedAccessExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "withSourceRecursive", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUpperBoundViolatedQualifiedAccessExpressionChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirUpperBoundViolatedQualifiedAccessExpressionChecker INSTANCE = new FirUpperBoundViolatedQualifiedAccessExpressionChecker();

    private FirUpperBoundViolatedQualifiedAccessExpressionChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final void check$runTheCheck(DiagnosticReporter diagnosticReporter, FirCallableSymbol<? extends FirCallableDeclaration> firCallableSymbol, CheckerContext checkerContext, FirQualifiedAccessExpression firQualifiedAccessExpression, List<FirTypeParameterSymbol> list, ConeSubstitutor coneSubstitutor, List<? extends ConeTypeProjection> list2, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirConstructorSymbol firConstructorSymbol = firCallableSymbol instanceof FirConstructorSymbol ? (FirConstructorSymbol) firCallableSymbol : null;
        TypeAliasConstructorInfo<?> typeAliasConstructorInfo = firConstructorSymbol != null ? TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructorSymbol) : null;
        if (typeAliasConstructorInfo == null) {
            FirUpperBoundViolatedHelpersKt.checkUpperBoundViolated$default(checkerContext, diagnosticReporter, list, list2, coneSubstitutor, false, false, firQualifiedAccessExpression.getSource(), false, z, false, false, 1376, null);
            return;
        }
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutor.substituteOrSelf(ScopeUtilsKt.defaultType(typeAliasConstructorInfo.getTypeAliasSymbol()));
        coneKotlinTypeSubstituteOrSelf.getClass();
        FirUpperBoundViolatedHelpersKt.checkUpperBoundViolated$default(checkerContext, diagnosticReporter, null, (ConeClassLikeType) coneKotlinTypeSubstituteOrSelf, false, firQualifiedAccessExpression.getSource(), false, z, 80, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableSymbol firCallableSymbol;
        FirCallableSymbol resolvedCallableSymbol$default;
        List<FirTypeParameterSymbol> typeParameterSymbols;
        ArrayList arrayList;
        TypeWillChangeAttribute typeWillChangeAttributeTypeChangeRelatedTo;
        ConeKotlinType newType;
        ConeKotlinType coneKotlinTypeWithAttributes;
        ConeTypeProjection coneTypeProjectionReplaceType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        if (!(calleeReference instanceof FirResolvedErrorReference)) {
            if (calleeReference instanceof FirResolvedNamedReference) {
                resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null);
            } else {
                firCallableSymbol = null;
            }
            List<ConeTypeProjection> typeArgumentsWithSourceInfo = FirUpperBoundViolatedHelpersKt.toTypeArgumentsWithSourceInfo(firQualifiedAccessExpression.getTypeArguments());
            if (firCallableSymbol != null || (typeParameterSymbols = firCallableSymbol.getTypeParameterSymbols()) == null) {
            }
            List<ConeTypeProjection> list = typeArgumentsWithSourceInfo;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (!(((ConeTypeProjection) it.next()) instanceof ConeKotlinType)) {
                        return;
                    }
                }
            }
            if (typeArgumentsWithSourceInfo.size() != typeParameterSymbols.size()) {
                return;
            }
            ConeSubstitutor coneSubstitutorCreateSubstitutorForUpperBoundViolationCheck = FirUpperBoundViolatedHelpersKt.createSubstitutorForUpperBoundViolationCheck(typeParameterSymbols, typeArgumentsWithSourceInfo, checkerContext.getSession());
            if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ReportUpperBoundViolatedInCallArgumentInteractions)) {
                List<ConeTypeProjection> list2 = typeArgumentsWithSourceInfo;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for (ConeTypeProjection coneTypeProjection : list2) {
                    ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                    if (type != null && (typeWillChangeAttributeTypeChangeRelatedTo = TypeWillChangeAttributeKt.typeChangeRelatedTo(type, LanguageFeature.ReportUpperBoundViolatedInCallArgumentInteractions)) != null && (newType = typeWillChangeAttributeTypeChangeRelatedTo.getNewType()) != null && (coneKotlinTypeWithAttributes = TypeUtilsKt.withAttributes(newType, type.getAttributes())) != null && (coneTypeProjectionReplaceType = ConeTypeProjectionKt.replaceType(coneTypeProjection, coneKotlinTypeWithAttributes)) != null) {
                        coneTypeProjection = coneTypeProjectionReplaceType;
                    }
                    arrayList.add(coneTypeProjection);
                }
            } else {
                arrayList = null;
            }
            ConeSubstitutor coneSubstitutorCreateSubstitutorForUpperBoundViolationCheck2 = arrayList != null ? FirUpperBoundViolatedHelpersKt.createSubstitutorForUpperBoundViolationCheck(typeParameterSymbols, arrayList, checkerContext.getSession()) : null;
            if (coneSubstitutorCreateSubstitutorForUpperBoundViolationCheck2 == null) {
                check$runTheCheck(diagnosticReporter, firCallableSymbol, checkerContext, firQualifiedAccessExpression, typeParameterSymbols, coneSubstitutorCreateSubstitutorForUpperBoundViolationCheck, typeArgumentsWithSourceInfo, false);
                return;
            }
            ErrorDiagnosticDetector errorDiagnosticDetector = new ErrorDiagnosticDetector();
            check$runTheCheck(errorDiagnosticDetector, firCallableSymbol, checkerContext, firQualifiedAccessExpression, typeParameterSymbols, coneSubstitutorCreateSubstitutorForUpperBoundViolationCheck, typeArgumentsWithSourceInfo, false);
            check$runTheCheck(diagnosticReporter, firCallableSymbol, checkerContext, firQualifiedAccessExpression, typeParameterSymbols, coneSubstitutorCreateSubstitutorForUpperBoundViolationCheck2, arrayList, !errorDiagnosticDetector.getHasErrors());
            return;
        }
        if (((FirResolvedErrorReference) calleeReference).getDiagnostic() instanceof ConeInapplicableWrongReceiver) {
            return;
        } else {
            resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null);
        }
        firCallableSymbol = resolvedCallableSymbol$default;
        List<ConeTypeProjection> typeArgumentsWithSourceInfo2 = FirUpperBoundViolatedHelpersKt.toTypeArgumentsWithSourceInfo(firQualifiedAccessExpression.getTypeArguments());
        if (firCallableSymbol != null) {
        }
    }
}
