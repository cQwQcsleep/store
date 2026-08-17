package org.jetbrains.kotlin.fir.analysis.checkers.type;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeAnnotationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeAnnotationChecker extends FirTypeChecker<FirResolvedTypeRef> {
    public static final FirTypeAnnotationChecker INSTANCE = new FirTypeAnnotationChecker();

    private FirTypeAnnotationChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedTypeRef firResolvedTypeRef) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedTypeRef.getClass();
        for (FirAnnotation firAnnotation : firResolvedTypeRef.getAnnotations()) {
            if (firAnnotation.getSource() != null) {
                AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
                Set<KotlinTarget> allowedAnnotationTargets = FirAnnotationHelpersKt.getAllowedAnnotationTargets(firAnnotation, checkerContext.getSession());
                if (useSiteTarget != null) {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory3<String, String, Set<KotlinTarget>>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getWRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET()), "type usage", useSiteTarget.getRenderName(), allowedAnnotationTargets, (64 & 64) != 0 ? null : null);
                } else if (!allowedAnnotationTargets.contains(KotlinTarget.TYPE)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_ANNOTATION_TARGET(), (Object) "type usage", (Object) allowedAnnotationTargets, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
                if (Intrinsics.areEqual(FirAnnotationUtilsKt.toAnnotationClassId(firAnnotation, checkerContext.getSession()), StandardClassIds$Annotations.INSTANCE.getExtensionFunctionType())) {
                    if (FunctionalTypeUtilsKt.isSomeFunctionType(firResolvedTypeRef.getConeType(), checkerContext.getSession())) {
                        if (firResolvedTypeRef.getConeType().getTypeArguments().length <= 1) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getWRONG_EXTENSION_FUNCTION_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    } else if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidExtensionFunctionTypeOnNonFunctionTypes)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getWRONG_EXTENSION_FUNCTION_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    } else {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getWRONG_EXTENSION_FUNCTION_TYPE_WARNING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
        }
    }
}
