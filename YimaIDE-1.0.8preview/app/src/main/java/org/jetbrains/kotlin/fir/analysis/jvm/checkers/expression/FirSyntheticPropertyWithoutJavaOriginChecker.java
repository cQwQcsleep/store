package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

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
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.FirSimpleSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.calls.SyntheticsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertyAccessorSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirSyntheticPropertyWithoutJavaOriginChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntheticPropertyWithoutJavaOriginChecker extends FirExpressionChecker<FirPropertyAccessExpression> {
    public static final FirSyntheticPropertyWithoutJavaOriginChecker INSTANCE = new FirSyntheticPropertyWithoutJavaOriginChecker();

    private FirSyntheticPropertyWithoutJavaOriginChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirPropertyAccessExpression firPropertyAccessExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firPropertyAccessExpression.getClass();
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.DontCreateSyntheticPropertiesWithoutBaseJavaGetter)) {
            return;
        }
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firPropertyAccessExpression);
        FirNamedFunctionSymbol delegateFunctionSymbol = null;
        FirSimpleSyntheticPropertySymbol firSimpleSyntheticPropertySymbol = resolvedCallableSymbol instanceof FirSimpleSyntheticPropertySymbol ? (FirSimpleSyntheticPropertySymbol) resolvedCallableSymbol : null;
        if (firSimpleSyntheticPropertySymbol == null) {
            return;
        }
        Object orNull = CollectionsKt.getOrNull(checkerContext.getCallsOrAssignments(), checkerContext.getCallsOrAssignments().size() - 2);
        FirVariableAssignment firVariableAssignment = orNull instanceof FirVariableAssignment ? (FirVariableAssignment) orNull : null;
        boolean z = (firVariableAssignment != null ? firVariableAssignment.getLValue() : null) == firPropertyAccessExpression;
        if (!z) {
            FirSyntheticPropertyAccessorSymbol getterSymbol = firSimpleSyntheticPropertySymbol.getGetterSymbol();
            if (getterSymbol != null) {
                delegateFunctionSymbol = getterSymbol.getDelegateFunctionSymbol();
            }
        } else if (!z) {
            bu8.a();
            return;
        } else {
            FirSyntheticPropertyAccessorSymbol setterSymbol = firSimpleSyntheticPropertySymbol.getSetterSymbol();
            if (setterSymbol != null) {
                delegateFunctionSymbol = setterSymbol.getDelegateFunctionSymbol();
            }
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol = delegateFunctionSymbol;
        if (firNamedFunctionSymbol != null && SyntheticsKt.getNoJavaOrigin(firSimpleSyntheticPropertySymbol)) {
            if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidSyntheticPropertiesWithoutBaseJavaGetter)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessExpression.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getFUNCTION_CALL_EXPECTED(), (Object) firNamedFunctionSymbol.getName().asString(), (Object) Boolean.FALSE, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            } else {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessExpression.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getSYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN(), (Object) firNamedFunctionSymbol, (Object) firNamedFunctionSymbol.getName(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }
    }
}
