package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDataClassConsistentDataCopyAnnotationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDataClassConsistentDataCopyAnnotationChecker extends FirDeclarationChecker<FirClass> {
    public static final FirDataClassConsistentDataCopyAnnotationChecker INSTANCE = new FirDataClassConsistentDataCopyAnnotationChecker();

    private FirDataClassConsistentDataCopyAnnotationChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firClass, standardClassIds$Annotations.getConsistentCopyVisibility(), checkerContext.getSession());
        FirAnnotation annotationByClassId2 = FirAnnotationUtilsKt.getAnnotationByClassId(firClass, standardClassIds$Annotations.getExposedCopyVisibility(), checkerContext.getSession());
        if (annotationByClassId != null && (!(firClass instanceof FirRegularClass) || !((FirRegularClass) firClass).getStatus().isData())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirErrors.INSTANCE.getDATA_CLASS_CONSISTENT_COPY_WRONG_ANNOTATION_TARGET(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (annotationByClassId2 != null && (!(firClass instanceof FirRegularClass) || !((FirRegularClass) firClass).getStatus().isData())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId2.getSource(), FirErrors.INSTANCE.getDATA_CLASS_CONSISTENT_COPY_WRONG_ANNOTATION_TARGET(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (annotationByClassId != null && annotationByClassId2 != null) {
            KtSourceElement source = annotationByClassId2.getSource();
            FirErrors firErrors = FirErrors.INSTANCE;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, firErrors.getDATA_CLASS_CONSISTENT_COPY_AND_EXPOSED_COPY_ARE_INCOMPATIBLE_ANNOTATIONS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), firErrors.getDATA_CLASS_CONSISTENT_COPY_AND_EXPOSED_COPY_ARE_INCOMPATIBLE_ANNOTATIONS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(firClass, checkerContext.getSession());
        boolean zAreEqual = Intrinsics.areEqual(firConstructorSymbolPrimaryConstructorIfAny != null ? firConstructorSymbolPrimaryConstructorIfAny.getResolvedStatus().getVisibility() : null, Visibilities.Public.INSTANCE);
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.DataClassCopyRespectsConstructorVisibility);
        if (annotationByClassId != null && (zAreEqual || zIsEnabled)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getREDUNDANT_ANNOTATION(), (Object) standardClassIds$Annotations.getConsistentCopyVisibility(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        if (annotationByClassId2 == null || !zAreEqual) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getREDUNDANT_ANNOTATION(), (Object) standardClassIds$Annotations.getExposedCopyVisibility(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }
}
