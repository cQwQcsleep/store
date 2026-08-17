package org.jetbrains.kotlin.fir.analysis.checkers.type;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirDefinitelyNotNullableChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirIntersectionTypeRefChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDefinitelyNotNullableChecker extends FirTypeChecker<FirIntersectionTypeRef> {
    public static final FirDefinitelyNotNullableChecker INSTANCE = new FirDefinitelyNotNullableChecker();

    private FirDefinitelyNotNullableChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirIntersectionTypeRef firIntersectionTypeRef) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firIntersectionTypeRef.getClass();
        if (firIntersectionTypeRef.isMarkedNullable()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firIntersectionTypeRef.getSource(), FirErrors.INSTANCE.getNULLABLE_ON_DEFINITELY_NOT_NULLABLE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (!TypeUtilsKt.isLeftValidForDefinitelyNotNullable(firIntersectionTypeRef, checkerContext.getSession())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firIntersectionTypeRef.getLeftType().getSource(), FirErrors.INSTANCE.getINCORRECT_LEFT_COMPONENT_OF_INTERSECTION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (TypeUtilsKt.isRightValidForDefinitelyNotNullable(firIntersectionTypeRef)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firIntersectionTypeRef.getRightType().getSource(), FirErrors.INSTANCE.getINCORRECT_RIGHT_COMPONENT_OF_INTERSECTION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
