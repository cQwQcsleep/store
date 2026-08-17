package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ9\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013JA\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "checkReferenceIsToAllowedMember", "referredSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkCapturedTypeInMutableReference", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallableReferenceChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirCallableReferenceChecker INSTANCE = new FirCallableReferenceChecker();

    private FirCallableReferenceChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkCapturedTypeInMutableReference(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableReferenceAccess firCallableReferenceAccess, FirBasedSymbol<?> firBasedSymbol, KtSourceElement ktSourceElement) {
        if (InferenceUtilsKt.isKMutableProperty(FirTypeUtilsKt.getResolvedType(firCallableReferenceAccess), checkerContext.getSession()) && (firBasedSymbol instanceof FirCallableSymbol) && ConeTypeUtilsKt.hasCapture(checkerContext.getReturnTypeCalculator().tryCalculateReturnType((FirCallableSymbol<?>) firBasedSymbol).getConeType())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getMUTABLE_PROPERTY_WITH_CAPTURED_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkReferenceIsToAllowedMember(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol<?> firBasedSymbol, KtSourceElement ktSourceElement) {
        if (firBasedSymbol instanceof FirConstructorSymbol) {
            FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol);
            if ((containingClassSymbol != null ? FirHelpersKt.getClassKind(containingClassSymbol) : null) == ClassKind.ANNOTATION_CLASS) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getCALLABLE_REFERENCE_TO_ANNOTATION_CONSTRUCTOR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        if (firBasedSymbol instanceof FirCallableSymbol) {
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) firBasedSymbol;
            if (DeclarationUtilsKt.isExtensionMember(firCallableSymbol) && !Intrinsics.areEqual(firCallableSymbol.getResolvedStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getEXTENSION_IN_CLASS_REFERENCE_NOT_ALLOWED(), (Object) firBasedSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (FirCallableSymbolKt.getHasContextParameters(firCallableSymbol) && LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ContextParameters)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getCALLABLE_REFERENCE_TO_CONTEXTUAL_DECLARATION(), (Object) firBasedSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x003d  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        if (firQualifiedAccessExpression instanceof FirCallableReferenceAccess) {
            FirCallableReferenceAccess firCallableReferenceAccess = (FirCallableReferenceAccess) firQualifiedAccessExpression;
            if (firCallableReferenceAccess.getHasQuestionMarkAtLHS()) {
                FirExpression explicitReceiver = firCallableReferenceAccess.getExplicitReceiver();
                if ((explicitReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver) : null) instanceof FirResolvedQualifier) {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                } else {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableReferenceAccess.getSource(), FirErrors.INSTANCE.getSAFE_CALLABLE_REFERENCE_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                }
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            }
            FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(firCallableReferenceAccess.getCalleeReference());
            if (resolved == null) {
                return;
            }
            FirBasedSymbol<?> resolvedSymbol = resolved.getResolvedSymbol();
            KtSourceElement source = resolved.getSource();
            if (source == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
                return;
            }
            checkReferenceIsToAllowedMember(checkerContext2, diagnosticReporter2, resolvedSymbol, source);
            checkCapturedTypeInMutableReference(checkerContext2, diagnosticReporter2, firCallableReferenceAccess, resolvedSymbol, source);
        }
    }
}
