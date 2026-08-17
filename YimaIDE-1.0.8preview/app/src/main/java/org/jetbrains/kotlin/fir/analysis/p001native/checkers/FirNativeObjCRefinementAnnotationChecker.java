package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0012R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementAnnotationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "hidesFromObjCSupportedTargets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "[Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "refinesInSwiftSupportedTargets", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjCRefinementAnnotationChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirNativeObjCRefinementAnnotationChecker INSTANCE = new FirNativeObjCRefinementAnnotationChecker();
    private static final KotlinTarget[] hidesFromObjCSupportedTargets;
    private static final KotlinTarget[] refinesInSwiftSupportedTargets;

    static {
        KotlinTarget kotlinTarget = KotlinTarget.FUNCTION;
        KotlinTarget kotlinTarget2 = KotlinTarget.PROPERTY;
        hidesFromObjCSupportedTargets = new KotlinTarget[]{kotlinTarget, kotlinTarget2, KotlinTarget.CLASS};
        refinesInSwiftSupportedTargets = new KotlinTarget[]{kotlinTarget, kotlinTarget2};
    }

    private FirNativeObjCRefinementAnnotationChecker() {
        super(MppCheckerKind.Platform);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (firRegularClass.getClassKind() != ClassKind.ANNOTATION_CLASS) {
            return;
        }
        FirSession session = checkerContext.getSession();
        ObjCExportMetaAnnotations objCExportMetaAnnotationsFindMetaAnnotations = FirNativeObjCRefinementAnnotationCheckerKt.findMetaAnnotations(firRegularClass.getAnnotations(), session);
        FirAnnotation hidesFromObjCAnnotation = objCExportMetaAnnotationsFindMetaAnnotations.getHidesFromObjCAnnotation();
        FirAnnotation refinesInSwiftAnnotation = objCExportMetaAnnotationsFindMetaAnnotations.getRefinesInSwiftAnnotation();
        if (hidesFromObjCAnnotation == null && refinesInSwiftAnnotation == null) {
            return;
        }
        if (hidesFromObjCAnnotation != null && refinesInSwiftAnnotation != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) refinesInSwiftAnnotation.getSource(), FirNativeErrors.INSTANCE.getREDUNDANT_SWIFT_REFINEMENT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        Set<KotlinTarget> allowedAnnotationTargets = FirAnnotationHelpersKt.getAllowedAnnotationTargets(firRegularClass, session);
        if (hidesFromObjCAnnotation != null && !SetsKt.minus(allowedAnnotationTargets, hidesFromObjCSupportedTargets).isEmpty()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) hidesFromObjCAnnotation.getSource(), FirNativeErrors.INSTANCE.getINVALID_OBJC_HIDES_TARGETS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (refinesInSwiftAnnotation == null || SetsKt.minus(allowedAnnotationTargets, refinesInSwiftSupportedTargets).isEmpty()) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) refinesInSwiftAnnotation.getSource(), FirNativeErrors.INSTANCE.getINVALID_REFINES_IN_SWIFT_TARGETS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
