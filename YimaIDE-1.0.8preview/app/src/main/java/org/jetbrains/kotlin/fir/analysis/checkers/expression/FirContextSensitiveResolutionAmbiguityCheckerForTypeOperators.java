package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.resolve.ContextSensitiveResolutionKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirContextSensitiveResolutionAmbiguityCheckerForTypeOperators;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeOperatorCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContextSensitiveResolutionAmbiguityCheckerForTypeOperators extends FirExpressionChecker<FirTypeOperatorCall> {
    public static final FirContextSensitiveResolutionAmbiguityCheckerForTypeOperators INSTANCE = new FirContextSensitiveResolutionAmbiguityCheckerForTypeOperators();

    private FirContextSensitiveResolutionAmbiguityCheckerForTypeOperators() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeOperatorCall firTypeOperatorCall) {
        FirClassLikeSymbol<?> classLikeSymbol;
        List qualifier;
        FirQualifierPart firQualifierPart;
        Name name;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeOperatorCall.getClass();
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ContextSensitiveResolutionUsingExpectedType)) {
            return;
        }
        FirResolvedTypeRef conversionTypeRef = firTypeOperatorCall.getConversionTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = conversionTypeRef instanceof FirResolvedTypeRef ? conversionTypeRef : null;
        if (firResolvedTypeRef == null || !FirContextSensitiveResolutionAmbiguityCheckerKt.getShouldWarn(firResolvedTypeRef.getResolvedSymbolOrigin()) || (firResolvedTypeRef instanceof FirErrorTypeRef) || (classLikeSymbol = FirHelpersKt.toClassLikeSymbol(firResolvedTypeRef, checkerContext.getSession())) == null) {
            return;
        }
        FirTypeRef delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef();
        FirUserTypeRef firUserTypeRef = delegatedTypeRef instanceof FirUserTypeRef ? (FirUserTypeRef) delegatedTypeRef : null;
        if (firUserTypeRef == null || (qualifier = firUserTypeRef.getQualifier()) == null || (firQualifierPart = (FirQualifierPart) CollectionsKt.singleOrNull(qualifier)) == null || (name = firQualifierPart.getName()) == null) {
            return;
        }
        Iterator it = ContextSensitiveResolutionKt.getParentChainForContextSensitiveResolutionOfTypes(FirTypeUtilsKt.getResolvedType((FirExpression) CollectionsKt.first(firTypeOperatorCall.getArgumentList().getArguments())), checkerContext.getSession()).iterator();
        while (it.hasNext()) {
            List<FirClassifierSymbol<?>> classifiers = FirScopeKt.getClassifiers(FirHelpersKt.declaredMemberScope(checkerContext, (FirRegularClassSymbol) it.next()), name);
            if (!classifiers.isEmpty()) {
                if (classifiers.contains(classLikeSymbol)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedTypeRef.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getCONTEXT_SENSITIVE_RESOLUTION_AMBIGUITY(), (Object) classLikeSymbol, (Object) classifiers, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                return;
            }
        }
    }
}
