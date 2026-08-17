package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirContextParameterInCalledSignatureChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u0010H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u000f*\u00020\u0013H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirContextParameterInCalledSignatureChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "hasContextualFunctionTypeInSignature", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "hasContextParametersFullyExpanded", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContextParameterInCalledSignatureChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirContextParameterInCalledSignatureChecker INSTANCE = new FirContextParameterInCalledSignatureChecker();

    private FirContextParameterInCalledSignatureChecker() {
        super(MppCheckerKind.Platform);
    }

    public static boolean b(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return CompilerConeAttributesKt.getHasContextParameters(coneKotlinType);
    }

    private final boolean hasContextParametersFullyExpanded(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        return ConeTypeUtilsKt.contains(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType), new Function1() { // from class: c05
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirContextParameterInCalledSignatureChecker.b((ConeKotlinType) obj));
            }
        });
    }

    private final boolean hasContextualFunctionTypeInSignature(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        ConeKotlinType resolvedReceiverType = firCallableSymbol.getResolvedReceiverType();
        if ((resolvedReceiverType != null && hasContextParametersFullyExpanded(checkerContext, resolvedReceiverType)) || hasContextParametersFullyExpanded(checkerContext, firCallableSymbol.getResolvedReturnType())) {
            return true;
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = firCallableSymbol.getTypeParameterSymbols();
        if (!(typeParameterSymbols instanceof Collection) || !typeParameterSymbols.isEmpty()) {
            Iterator<T> it = typeParameterSymbols.iterator();
            while (it.hasNext()) {
                List<FirResolvedTypeRef> resolvedBounds = ((FirTypeParameterSymbol) it.next()).getResolvedBounds();
                if (!(resolvedBounds instanceof Collection) || !resolvedBounds.isEmpty()) {
                    Iterator<T> it2 = resolvedBounds.iterator();
                    while (it2.hasNext()) {
                        if (INSTANCE.hasContextParametersFullyExpanded(checkerContext, ((FirResolvedTypeRef) it2.next()).getConeType())) {
                            return true;
                        }
                    }
                }
            }
        }
        FirFunctionSymbol firFunctionSymbol = firCallableSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) firCallableSymbol : null;
        if (firFunctionSymbol == null) {
            return false;
        }
        List<FirValueParameterSymbol> valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols();
        if ((valueParameterSymbols instanceof Collection) && valueParameterSymbols.isEmpty()) {
            return false;
        }
        Iterator<T> it3 = valueParameterSymbols.iterator();
        while (it3.hasNext()) {
            if (INSTANCE.hasContextParametersFullyExpanded(checkerContext, ((FirValueParameterSymbol) it3.next()).getResolvedReturnType())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirCallableSymbol<?> resolvedCallableSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ContextParameters) || LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ContextReceivers) || (resolvedCallableSymbol = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression)) == null || !hasContextualFunctionTypeInSignature(checkerContext, resolvedCallableSymbol)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), FirErrors.INSTANCE.getUNSUPPORTED_CONTEXTUAL_DECLARATION_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
