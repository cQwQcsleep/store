package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirDeprecatedSmartCastChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSmartCastExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeprecatedSmartCastChecker extends FirExpressionChecker<FirSmartCastExpression> {
    public static final FirDeprecatedSmartCastChecker INSTANCE = new FirDeprecatedSmartCastChecker();

    private FirDeprecatedSmartCastChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirSmartCastExpression firSmartCastExpression) {
        KtSourceElement source;
        FirReference reference;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firSmartCastExpression.getClass();
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.UnstableSmartcastOnDelegatedProperties) || !firSmartCastExpression.isStable() || (source = firSmartCastExpression.getSource()) == null || (reference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toReference(firSmartCastExpression.getOriginalExpression(), checkerContext.getSession())) == null) {
            return;
        }
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(reference);
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        FirPropertySymbol firPropertySymbol = (FirPropertySymbol) (resolvedSymbol instanceof FirPropertySymbol ? resolvedSymbol : null);
        if (firPropertySymbol != null && ClassMembersKt.isDelegated(firPropertySymbol)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) FirErrors.INSTANCE.getDEPRECATED_SMARTCAST_ON_DELEGATED_PROPERTY(), (Object) FirTypeUtilsKt.getResolvedType(firSmartCastExpression), (Object) firPropertySymbol, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }
}
