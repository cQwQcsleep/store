package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirDesugaredAssignmentValueReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionAnnotationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionAnnotationChecker extends FirExpressionChecker<FirStatement> {
    public static final FirExpressionAnnotationChecker INSTANCE = new FirExpressionAnnotationChecker();

    private FirExpressionAnnotationChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0053  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        boolean z;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        if ((firStatement instanceof FirDeclaration) || (firStatement instanceof FirErrorExpression)) {
            return;
        }
        if ((firStatement instanceof FirVariableAssignment) && (((FirVariableAssignment) firStatement).getLValue() instanceof FirDesugaredAssignmentValueReferenceExpression)) {
            return;
        }
        List<FirAnnotation> annotations = firStatement.getAnnotations();
        if (annotations.isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        if (firStatement instanceof FirBlock) {
            KtSourceElement source = ((FirBlock) firStatement).getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtRealSourceElementKind.INSTANCE)) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        for (FirAnnotation firAnnotation : annotations) {
            AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
            if (useSiteTarget == null) {
                useSiteTarget = FirAnnotationHelpersKt.getDefaultUseSiteTarget(checkerContext, firStatement, firAnnotation);
            }
            AnnotationUseSiteTarget annotationUseSiteTarget = useSiteTarget;
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef());
            Object arrayList = map.get(coneType);
            if (arrayList == null) {
                arrayList = new ArrayList();
                map.put(coneType, arrayList);
            }
            List list = (List) arrayList;
            Set<KotlinTarget> allowedAnnotationTargets = FirAnnotationHelpersKt.getAllowedAnnotationTargets(firAnnotation, checkerContext.getSession());
            if (!z && !allowedAnnotationTargets.contains(KotlinTarget.EXPRESSION)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_ANNOTATION_TARGET(), (Object) "expression", (Object) allowedAnnotationTargets, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            } else if (annotationUseSiteTarget != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getANNOTATION_WITH_USE_SITE_TARGET_ON_EXPRESSION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            FirAnnotationHelpersKt.checkRepeatedAnnotation(checkerContext, diagnosticReporter, annotationUseSiteTarget, (List<AnnotationUseSiteTarget>) list, firAnnotation, firAnnotation.getSource());
            list.add(annotationUseSiteTarget);
        }
    }
}
