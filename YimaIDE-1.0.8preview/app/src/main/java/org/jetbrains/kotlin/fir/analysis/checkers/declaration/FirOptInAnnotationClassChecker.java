package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Set;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirRetentionAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.resolve.checkers.OptInDescription;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOptInAnnotationClassChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInAnnotationClassChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirOptInAnnotationClassChecker INSTANCE = new FirOptInAnnotationClassChecker();

    private FirOptInAnnotationClassChecker() {
        super(MppCheckerKind.Common);
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
        if (FirAnnotationUtilsKt.getAnnotationByClassId(firRegularClass, OptInNames.INSTANCE.getREQUIRES_OPT_IN_CLASS_ID(), session) == null) {
            return;
        }
        if (FirRetentionAnnotationHelpersKt.getRetention(firRegularClass, session) == AnnotationRetention.SOURCE) {
            FirAnnotation retentionAnnotation = FirRetentionAnnotationHelpersKt.getRetentionAnnotation(firRegularClass, session);
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, retentionAnnotation != null ? retentionAnnotation.getSource() : null, FirErrors.INSTANCE.getOPT_IN_MARKER_WITH_WRONG_RETENTION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        Set setIntersect = CollectionsKt.intersect(FirAnnotationHelpersKt.getAllowedAnnotationTargets(firRegularClass, session), OptInDescription.Companion.getWRONG_TARGETS_FOR_MARKER());
        if (setIntersect.isEmpty()) {
            return;
        }
        FirAnnotation targetAnnotation = FirAnnotationHelpersKt.getTargetAnnotation(firRegularClass, session);
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (targetAnnotation != null ? targetAnnotation.getSource() : null), (KtDiagnosticFactory1) FirErrors.INSTANCE.getOPT_IN_MARKER_WITH_WRONG_TARGET(), (Object) CollectionsKt.joinToString$default(setIntersect, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOptInAnnotationClassChecker.check.1
            public Object get(Object obj) {
                return ((KotlinTarget) obj).getDescription();
            }
        }, 31, (Object) null), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }
}
