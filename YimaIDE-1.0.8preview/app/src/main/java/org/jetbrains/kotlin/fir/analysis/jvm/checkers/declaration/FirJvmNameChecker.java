package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.java.JavaUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\f\u0010\u0015\u001a\u00020\u0007*\u00020\u0016H\u0002R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmNameChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "isRenamableFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "isValueClassThatRequiresMangling", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmNameChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJvmNameChecker INSTANCE = new FirJvmNameChecker();

    private FirJvmNameChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean isRenamableFunction(FirFunction function) {
        return (ContainingClassUtilsKt.getContainingClassSymbol(function) == null && function.getIsLocal() && !(function instanceof FirPropertyAccessor)) ? false : true;
    }

    private final boolean isValueClassThatRequiresMangling(FirRegularClass firRegularClass) {
        return (firRegularClass.getStatus().isInline() || firRegularClass.getStatus().isValue()) && !Intrinsics.areEqual(firRegularClass.getName(), StandardClassIds.INSTANCE.getResult().getShortClassName());
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00d8  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        FirAnnotation firAnnotationFindJvmNameAnnotation = JavaUtilsKt.findJvmNameAnnotation(firDeclaration);
        if (firAnnotationFindJvmNameAnnotation == null) {
            return;
        }
        FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotationFindJvmNameAnnotation, StandardNames.NAME, false, 2, null);
        if (firExpressionFindArgumentByName$default != null && Intrinsics.areEqual(FirTypeUtilsKt.getResolvedType(firExpressionFindArgumentByName$default), checkerContext.getSession().getBuiltinTypes().getStringType().getConeType())) {
            FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName$default instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName$default : null;
            Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
            String str = value instanceof String ? (String) value : null;
            if (str == null) {
                return;
            }
            if (!Name.isValidIdentifier(str)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotationFindJvmNameAnnotation.getSource(), FirJvmErrors.INSTANCE.getILLEGAL_JVM_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            if ((firDeclaration instanceof FirFunction) && !isRenamableFunction((FirFunction) firDeclaration)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotationFindJvmNameAnnotation.getSource(), FirJvmErrors.INSTANCE.getINAPPLICABLE_JVM_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            if (firDeclaration instanceof FirCallableDeclaration) {
                FirRegularClass containingClass = ContainingClassUtilsKt.getContainingClass((FirCallableDeclaration) firDeclaration);
                FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
                if (!firMemberDeclaration.getStatus().isOverride()) {
                    if (containingClass == null) {
                        if (containingClass != null) {
                            return;
                        } else {
                            return;
                        }
                    }
                    Modality modality = containingClass.getStatus().getModality();
                    Modality modality2 = Modality.FINAL;
                    if (modality == modality2 || firMemberDeclaration.getStatus().getModality() == modality2 || Intrinsics.areEqual(firMemberDeclaration.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
                        if (containingClass != null || !isValueClassThatRequiresMangling(containingClass)) {
                            return;
                        }
                    }
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotationFindJvmNameAnnotation.getSource(), FirJvmErrors.INSTANCE.getINAPPLICABLE_JVM_NAME(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
