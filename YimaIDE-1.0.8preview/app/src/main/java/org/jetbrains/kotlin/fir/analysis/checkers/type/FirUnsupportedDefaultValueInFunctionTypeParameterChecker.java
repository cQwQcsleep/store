package org.jetbrains.kotlin.fir.analysis.checkers.type;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnsupportedDefaultValueInFunctionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ-\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\rH\u0002R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirUnsupportedDefaultValueInFunctionTypeParameterChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirFunctionalTypeParameterSyntaxChecker;", "<init>", "()V", "checkPsiOrLightTree", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "element", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;Lorg/jetbrains/kotlin/KtSourceElement;)V", "report", "defaultValueSource", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnsupportedDefaultValueInFunctionTypeParameterChecker extends FirFunctionalTypeParameterSyntaxChecker {
    public static final FirUnsupportedDefaultValueInFunctionTypeParameterChecker INSTANCE = new FirUnsupportedDefaultValueInFunctionTypeParameterChecker();

    private FirUnsupportedDefaultValueInFunctionTypeParameterChecker() {
    }

    private final void report(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement) {
        ConeUnsupportedDefaultValueInFunctionType coneUnsupportedDefaultValueInFunctionType = new ConeUnsupportedDefaultValueInFunctionType(ktSourceElement);
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) coneUnsupportedDefaultValueInFunctionType.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) coneUnsupportedDefaultValueInFunctionType.getReason(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsiOrLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionTypeParameter firFunctionTypeParameter, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionTypeParameter.getClass();
        ktSourceElement.getClass();
        KtSourceElement defaultValueForParameter = FirHelpersKt.getDefaultValueForParameter(ktSourceElement);
        if (defaultValueForParameter == null) {
            return;
        }
        report(diagnosticReporter, checkerContext, defaultValueForParameter);
    }
}
