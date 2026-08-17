package org.jetbrains.kotlin.fir.analysis.checkers.declaration.crv;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.ReturnValueCheckerMode;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0014\u0010\r\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0014\u0010\u000e\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J-\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0002H\u0016R\u00020\u0011R\u00020\u0013j\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirReturnValueAnnotationsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "oldMustUse", "Lorg/jetbrains/kotlin/name/ClassId;", "isMustUseReturnValue", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isOldMustUse", "isIgnorableValue", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReturnValueAnnotationsChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirReturnValueAnnotationsChecker INSTANCE = new FirReturnValueAnnotationsChecker();
    private static final ClassId oldMustUse;

    static {
        FqName base_kotlin_package = StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE();
        Name nameIdentifier = Name.identifier("MustUseReturnValue");
        nameIdentifier.getClass();
        oldMustUse = new ClassId(base_kotlin_package, nameIdentifier);
    }

    private FirReturnValueAnnotationsChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean isIgnorableValue(FirAnnotation firAnnotation, FirSession firSession) {
        return Intrinsics.areEqual(FirAnnotationUtilsKt.toAnnotationClassId(firAnnotation, firSession), StandardClassIds$Annotations.INSTANCE.getIgnorableReturnValue());
    }

    private final boolean isMustUseReturnValue(FirAnnotation firAnnotation, FirSession firSession) {
        return Intrinsics.areEqual(FirAnnotationUtilsKt.toAnnotationClassId(firAnnotation, firSession), StandardClassIds$Annotations.INSTANCE.getMustUseReturnValues());
    }

    private final boolean isOldMustUse(FirAnnotation firAnnotation, FirSession firSession) {
        return Intrinsics.areEqual(FirAnnotationUtilsKt.toAnnotationClassId(firAnnotation, firSession), oldMustUse);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (checkerContext.get$languageVersionSettings().getFlag(AnalysisFlags.getReturnValueCheckerMode()) != ReturnValueCheckerMode.DISABLED) {
            return;
        }
        FirSession session = checkerContext.getSession();
        for (FirAnnotation firAnnotation : firDeclaration.getAnnotations()) {
            FirReturnValueAnnotationsChecker firReturnValueAnnotationsChecker = INSTANCE;
            if (firReturnValueAnnotationsChecker.isMustUseReturnValue(firAnnotation, session) || firReturnValueAnnotationsChecker.isIgnorableValue(firAnnotation, session) || firReturnValueAnnotationsChecker.isOldMustUse(firAnnotation, session)) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getIGNORABILITY_ANNOTATIONS_WITH_CHECKER_DISABLED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }
}
