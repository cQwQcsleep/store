package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.lexer.KtKeywordToken;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.SimpleTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCatchParameterChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTryExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;)V", "isProhibitedNothing", Argument.Delimiters.none, "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCatchParameterChecker extends FirExpressionChecker<FirTryExpression> {
    public static final FirCatchParameterChecker INSTANCE = new FirCatchParameterChecker();

    private FirCatchParameterChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean isProhibitedNothing(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        return LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ProhibitNothingAsCatchParameter) && ConeBuiltinTypeUtilsKt.isNothing(coneKotlinType);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTryExpression firTryExpression) {
        AbstractKtSourceElement abstractKtSourceElement;
        ConeTypeParameterLookupTag lookupTag;
        FirTypeParameterSymbol typeParameterSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTryExpression.getClass();
        Iterator<FirCatch> it = firTryExpression.getCatches().iterator();
        while (it.hasNext()) {
            FirProperty parameter = it.next().getParameter();
            AbstractKtSourceElement source = parameter.getSource();
            if (source != null) {
                KtSourceElement source2 = parameter.getSource();
                if ((source2 != null ? FirHelpersKt.getDefaultValueForParameter(source2) : null) != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, source, FirErrors.INSTANCE.getCATCH_PARAMETER_WITH_DEFAULT_VALUE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                KtKeywordToken valOrVarKeyword = FirKeywordUtilsKt.getValOrVarKeyword(source);
                if (valOrVarKeyword != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, source, FirErrors.INSTANCE.getVAL_OR_VAR_ON_CATCH_PARAMETER(), valOrVarKeyword, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                RigidTypeMarker coneType = FirTypeUtilsKt.getConeType(parameter.getReturnTypeRef());
                if ((coneType instanceof ConeTypeParameterType) || (coneType instanceof ConeDefinitelyNotNullType)) {
                    SimpleTypeMarker simpleTypeMarkerOriginalIfDefinitelyNotNullable = TypeComponentsKt.getTypeContext(checkerContext.getSession()).originalIfDefinitelyNotNullable(coneType);
                    ConeTypeParameterType coneTypeParameterType = simpleTypeMarkerOriginalIfDefinitelyNotNullable instanceof ConeTypeParameterType ? (ConeTypeParameterType) simpleTypeMarkerOriginalIfDefinitelyNotNullable : null;
                    if (coneTypeParameterType == null || (lookupTag = coneTypeParameterType.getLookupTag()) == null || (typeParameterSymbol = lookupTag.getTypeParameterSymbol()) == null || !typeParameterSymbol.isReified()) {
                        abstractKtSourceElement = source;
                        KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirErrors.INSTANCE.getTYPE_PARAMETER_IN_CATCH_CLAUSE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    } else {
                        FirHelpersKt.requireFeatureSupport$default(checkerContext, diagnosticReporter, parameter, LanguageFeature.AllowReifiedTypeInCatchClause, (SourceElementPositioningStrategy) null, 8, (Object) null);
                        abstractKtSourceElement = source;
                    }
                } else {
                    abstractKtSourceElement = source;
                }
                FirSession session = checkerContext.getSession();
                if (!FirHelpersKt.isSubtypeOfThrowable(coneType, session) || isProhibitedNothing(checkerContext, coneType)) {
                    KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirErrors.INSTANCE.getTHROWABLE_TYPE_MISMATCH(), coneType, Boolean.valueOf(InferenceUtilsKt.isTypeMismatchDueToNullability(TypeComponentsKt.getTypeContext(checkerContext.getSession()), coneType, session.getBuiltinTypes().getThrowableType().getConeType())), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
            }
        }
    }
}
