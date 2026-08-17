package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOptInMarkedDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInMarkedDeclarationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirOptInMarkedDeclarationChecker INSTANCE = new FirOptInMarkedDeclarationChecker();

    private FirOptInMarkedDeclarationChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirReceiverParameter receiverParameter;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        for (FirAnnotation firAnnotation : firDeclaration.getAnnotations()) {
            if (FirAnnotationHelpersKt.getAnnotationClassForOptInMarker(firAnnotation, checkerContext.getSession()) != null) {
                AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
                if ((firDeclaration instanceof FirPropertyAccessor) && ((FirPropertyAccessor) firDeclaration).getIsGetter()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getOPT_IN_MARKER_ON_WRONG_TARGET(), (Object) "getter", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                if (firDeclaration instanceof FirValueParameter) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getOPT_IN_MARKER_ON_WRONG_TARGET(), (Object) "parameter", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                if ((firDeclaration instanceof FirProperty) && (((FirProperty) firDeclaration).getSymbol() instanceof FirLocalPropertySymbol)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getOPT_IN_MARKER_ON_WRONG_TARGET(), (Object) "variable", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                if ((firDeclaration instanceof FirBackingField) || useSiteTarget == AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getOPT_IN_MARKER_ON_WRONG_TARGET(), (Object) "field", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
        if ((firDeclaration instanceof FirCallableDeclaration) && (receiverParameter = ((FirCallableDeclaration) firDeclaration).getReceiverParameter()) != null) {
            for (FirAnnotation firAnnotation2 : receiverParameter.getAnnotations()) {
                if (FirAnnotationHelpersKt.getAnnotationClassForOptInMarker(firAnnotation2, checkerContext.getSession()) != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getOPT_IN_MARKER_ON_WRONG_TARGET(), (Object) "parameter", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }
}
