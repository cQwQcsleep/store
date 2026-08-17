package org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ!\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0004R\u00020\rj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0011J-\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u00020\rR\u00020\u0014j\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/declaration/FirWebCommonAbstractNativeAnnotationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "requiredAnnotation", "Lorg/jetbrains/kotlin/name/ClassId;", "error", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;)V", "hasRequiredAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "org.jetbrains.kotlin:checkers.web.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirWebCommonAbstractNativeAnnotationChecker extends FirDeclarationChecker<FirNamedFunction> {
    private final KtDiagnosticFactory1<ConeKotlinType> error;
    private final ClassId requiredAnnotation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirWebCommonAbstractNativeAnnotationChecker(ClassId classId, KtDiagnosticFactory1<ConeKotlinType> ktDiagnosticFactory1) {
        super(MppCheckerKind.Platform);
        classId.getClass();
        ktDiagnosticFactory1.getClass();
        this.requiredAnnotation = classId;
        this.error = ktDiagnosticFactory1;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunction firNamedFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firNamedFunction.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firNamedFunction, this.requiredAnnotation, checkerContext.getSession());
        if (annotationByClassId == null) {
            return;
        }
        boolean z = (FirHelpersKt.isTopLevel(checkerContext) || Intrinsics.areEqual(firNamedFunction.getStatus().getVisibility(), Visibilities.Local.INSTANCE)) ? false : true;
        boolean zIsExtension = FirDeclarationUtilKt.isExtension(firNamedFunction);
        if ((!z || (!zIsExtension && FirWebCommonHelpersKt.isNativeObject(firNamedFunction.getSymbol(), checkerContext.getSession()))) && (z || zIsExtension)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunction.getSource(), (KtDiagnosticFactory1) this.error, (Object) FirTypeUtilsKt.getResolvedType(annotationByClassId), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    public final boolean hasRequiredAnnotation(CheckerContext checkerContext, FirFunction firFunction) {
        checkerContext.getClass();
        firFunction.getClass();
        return FirAnnotationUtilsKt.hasAnnotation((FirDeclaration) firFunction, this.requiredAnnotation, checkerContext.getSession());
    }
}
