package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContextFactory;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContextKt;
import org.jetbrains.kotlin.fir.FirSourceElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirActualAnnotationsMatchExpectChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.mpp.SourceElementMarker;
import org.jetbrains.kotlin.resolve.calls.mpp.AbstractExpectActualAnnotationMatchChecker;
import org.jetbrains.kotlin.resolve.calls.mpp.ExpectActualMatchingContext;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualAnnotationsIncompatibilityType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJG\u0010\u000e\u001a\u00020\u00072\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirActualAnnotationsMatchExpectChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkAnnotationsMatch", "expectSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "actualSymbol", "expectContainingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirActualAnnotationsMatchExpectChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirActualAnnotationsMatchExpectChecker INSTANCE = new FirActualAnnotationsMatchExpectChecker();

    private FirActualAnnotationsMatchExpectChecker() {
        super(MppCheckerKind.Common);
    }

    public static FirAnnotation b(ExpectActualMatchingContext.AnnotationCallInfo annotationCallInfo) {
        annotationCallInfo.getClass();
        Object annotationSymbol = annotationCallInfo.getAnnotationSymbol();
        annotationSymbol.getClass();
        return (FirAnnotation) annotationSymbol;
    }

    private final void checkAnnotationsMatch(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol<?> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2, FirRegularClassSymbol firRegularClassSymbol) {
        AbstractExpectActualAnnotationMatchChecker.Incompatibility incompatibilityAreAnnotationsCompatible = AbstractExpectActualAnnotationMatchChecker.INSTANCE.areAnnotationsCompatible(firBasedSymbol, firBasedSymbol2, firRegularClassSymbol, FirExpectActualMatchingContextFactory.create$default(FirExpectActualMatchingContextKt.getExpectActualMatchingContextFactory(checkerContext.getSession()), checkerContext.getSession(), checkerContext.getScopeSession(), false, 4, null));
        if (incompatibilityAreAnnotationsCompatible == null) {
            return;
        }
        SourceElementMarker actualAnnotationTargetElement = incompatibilityAreAnnotationsCompatible.getActualAnnotationTargetElement();
        actualAnnotationTargetElement.getClass();
        KtSourceElement element = ((FirSourceElement) actualAnnotationTargetElement).getElement();
        AbstractKtSourceElement abstractKtSourceElementRequireNotNull = KtDiagnosticReportHelpersKt.requireNotNull(firBasedSymbol2.getSource());
        KtDiagnosticFactory4<FirBasedSymbol<?>, FirBasedSymbol<?>, KtSourceElement, ExpectActualAnnotationsIncompatibilityType<FirAnnotation>> actual_annotations_not_match_expect = FirErrors.INSTANCE.getACTUAL_ANNOTATIONS_NOT_MATCH_EXPECT();
        DeclarationSymbolMarker expectSymbol = incompatibilityAreAnnotationsCompatible.getExpectSymbol();
        expectSymbol.getClass();
        FirBasedSymbol firBasedSymbol3 = (FirBasedSymbol) expectSymbol;
        DeclarationSymbolMarker actualSymbol = incompatibilityAreAnnotationsCompatible.getActualSymbol();
        actualSymbol.getClass();
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, abstractKtSourceElementRequireNotNull, (KtDiagnosticFactory4<FirBasedSymbol, FirBasedSymbol, KtSourceElement, ExpectActualAnnotationsIncompatibilityType>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) actual_annotations_not_match_expect), firBasedSymbol3, (FirBasedSymbol) actualSymbol, element, incompatibilityAreAnnotationsCompatible.getType().mapAnnotationType(new Function1() { // from class: ay4
            public final Object invoke(Object obj) {
                return FirActualAnnotationsMatchExpectChecker.b((ExpectActualMatchingContext.AnnotationCallInfo) obj);
            }
        }), (128 & 128) != 0 ? null : null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirBasedSymbol<FirDeclaration> symbol;
        FirBasedSymbol<?> singleMatchedExpectForActualOrNull;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (!(firDeclaration instanceof FirMemberDeclaration) || LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.MultiPlatformProjects) || LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.MultiplatformRestrictions)) {
            return;
        }
        FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
        if (firMemberDeclaration.getStatus().isActual() && (singleMatchedExpectForActualOrNull = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull((symbol = firMemberDeclaration.getSymbol()))) != null) {
            Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            FirRegularClassSymbol firRegularClassSymbol = objLastOrNull instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) objLastOrNull : null;
            FirBasedSymbol<?> singleMatchedExpectForActualOrNull2 = firRegularClassSymbol != null ? ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull(firRegularClassSymbol) : null;
            checkAnnotationsMatch(checkerContext, diagnosticReporter, singleMatchedExpectForActualOrNull, symbol, singleMatchedExpectForActualOrNull2 instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) singleMatchedExpectForActualOrNull2 : null);
        }
    }
}
