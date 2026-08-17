package org.jetbrains.kotlin.fir.analysis.checkers.type;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirOptionalExpectationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirOptionalExpectationTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptionalExpectationTypeChecker extends FirTypeChecker<FirResolvedTypeRef> {
    public static final FirOptionalExpectationTypeChecker INSTANCE = new FirOptionalExpectationTypeChecker();

    private FirOptionalExpectationTypeChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedTypeRef firResolvedTypeRef) {
        FirRegularClassSymbol regularClassSymbol;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        List<FirAnnotation> annotations;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedTypeRef.getClass();
        KtSourceElement source = firResolvedTypeRef.getSource();
        if (((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, firResolvedTypeRef.getConeType())) == null || !FirOptionalExpectationHelpersKt.isOptionalAnnotationClass(regularClassSymbol, checkerContext.getSession())) {
            return;
        }
        if (FirLanguageSettingsComponentKt.isMetadataCompilation(checkerContext.getSession()) || FirModuleDataKt.getModuleData(checkerContext.getSession()).getIsCommon()) {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
        } else {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getOPTIONAL_DECLARATION_USAGE_IN_NON_COMMON_SOURCE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        FirAnnotationContainer firAnnotationContainer = (FirAnnotationContainer) CollectionsKt.lastOrNull(checkerContext2.getAnnotationContainers());
        if (firAnnotationContainer != null && (annotations = firAnnotationContainer.getAnnotations()) != null) {
            List<FirAnnotation> list = annotations;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(((FirAnnotation) it.next()).getAnnotationTypeRef(), firResolvedTypeRef)) {
                        return;
                    }
                }
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getOPTIONAL_DECLARATION_OUTSIDE_OF_ANNOTATION_ENTRY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
