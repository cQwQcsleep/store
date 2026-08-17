package org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.FirAbstractWebCheckerUtils;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ%\u0010\u000b\u001a\u00020\f2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0016R\u00020\rj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0011J-\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0002H\u0016R\u00020\rR\u00020\u0014j\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/expression/FirAbstractReifiedOnDeclarationWithoutRuntimeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "webCheckerUtils", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/FirAbstractWebCheckerUtils;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/FirAbstractWebCheckerUtils;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;)V", "isDeclarationWithoutRuntime", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;)Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers.web.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractReifiedOnDeclarationWithoutRuntimeChecker extends FirExpressionChecker<FirFunctionCall> {
    private final KtDiagnosticFactory1<ConeKotlinType> diagnostic;
    private final FirAbstractWebCheckerUtils webCheckerUtils;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractReifiedOnDeclarationWithoutRuntimeChecker(FirAbstractWebCheckerUtils firAbstractWebCheckerUtils, KtDiagnosticFactory1<ConeKotlinType> ktDiagnosticFactory1) {
        super(MppCheckerKind.Common);
        firAbstractWebCheckerUtils.getClass();
        ktDiagnosticFactory1.getClass();
        this.webCheckerUtils = firAbstractWebCheckerUtils;
        this.diagnostic = ktDiagnosticFactory1;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (resolvedNamedFunctionSymbol$default == null) {
            return;
        }
        for (Pair pair : CollectionsKt.zip(resolvedNamedFunctionSymbol$default.getTypeParameterSymbols(), firFunctionCall.getTypeArguments())) {
            FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) pair.component1();
            FirTypeProjection firTypeProjection = (FirTypeProjection) pair.component2();
            if (firTypeParameterSymbol.isReified() && (firTypeProjection instanceof FirTypeProjectionWithVariance)) {
                FirTypeProjectionWithVariance firTypeProjectionWithVariance = (FirTypeProjectionWithVariance) firTypeProjection;
                ConeKotlinType coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(firTypeProjectionWithVariance.getTypeRef());
                if (coneTypeOrNull != null) {
                    FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(checkerContext, coneTypeOrNull);
                    if (symbol == null || !isDeclarationWithoutRuntime(checkerContext, symbol)) {
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                    } else {
                        KtSourceElement source = firTypeProjectionWithVariance.getSource();
                        if (source == null) {
                            source = firFunctionCall.getSource();
                        }
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) this.diagnostic, (Object) coneTypeOrNull, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                    checkerContext = checkerContext2;
                    diagnosticReporter = diagnosticReporter2;
                }
            }
        }
    }

    public boolean isDeclarationWithoutRuntime(CheckerContext checkerContext, FirClassifierSymbol<?> firClassifierSymbol) {
        checkerContext.getClass();
        firClassifierSymbol.getClass();
        return this.webCheckerUtils.isNativeOrExternalInterface(firClassifierSymbol, checkerContext.getSession());
    }
}
