package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExpectRefinementChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectRefinementChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirExpectRefinementChecker INSTANCE = new FirExpectRefinementChecker();

    private FirExpectRefinementChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        boolean zHasAnnotation = FirAnnotationUtilsKt.hasAnnotation(firDeclaration, StandardClassIds$Annotations.INSTANCE.getExpectRefinement(), checkerContext.getSession());
        boolean z = firDeclaration instanceof FirMemberDeclaration;
        boolean z2 = z && ((FirMemberDeclaration) firDeclaration).getStatus().isExpect();
        if (zHasAnnotation && (!z2 || !FirHelpersKt.isTopLevel(checkerContext))) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), FirErrors.INSTANCE.getEXPECT_REFINEMENT_ANNOTATION_WRONG_TARGET(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (z) {
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
            Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> expectForActual = ExpectActualAttributesKt.getExpectForActual(firMemberDeclaration.getSymbol());
            if (expectForActual == null) {
                expectForActual = MapsKt.emptyMap();
            }
            boolean zContainsKey = expectForActual.containsKey(ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE);
            if (zContainsKey && firMemberDeclaration.getStatus().isExpect() && !firMemberDeclaration.getStatus().isActual() && FirHelpersKt.isTopLevel(checkerContext)) {
                if (!zHasAnnotation) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firMemberDeclaration.getSource(), FirErrors.INSTANCE.getEXPECT_REFINEMENT_ANNOTATION_MISSING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                LanguageFeature languageFeature = LanguageFeature.ExpectRefinement;
                if (LanguageVersionUtilsKt.isDisabled(checkerContext, languageFeature)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firMemberDeclaration.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(languageFeature, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
            if (zContainsKey || !zHasAnnotation) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firMemberDeclaration.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getACTUAL_WITHOUT_EXPECT(), (Object) firMemberDeclaration.getSymbol(), (Object) expectForActual, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }
}
