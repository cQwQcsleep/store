package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.psi.KtTypeConstraint;
import org.jetbrains.kotlin.psi.KtTypeParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J=\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0013J5\u0010\u0014\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirTypeParameterSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirDeclarationSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/psi/KtTypeParameter;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkPsi", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lorg/jetbrains/kotlin/psi/KtTypeParameter;)V", "checkPsiOrLightTree", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeParameterSyntaxChecker extends FirDeclarationSyntaxChecker<FirTypeParameter, KtTypeParameter> {
    public static final FirTypeParameterSyntaxChecker INSTANCE = new FirTypeParameterSyntaxChecker();

    private FirTypeParameterSyntaxChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter, KtPsiSourceElement ktPsiSourceElement, KtTypeParameter ktTypeParameter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeParameter.getClass();
        ktPsiSourceElement.getClass();
        ktTypeParameter.getClass();
        List<FirTypeRef> bounds = firTypeParameter.getBounds();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : bounds) {
            PsiElement psi = UtilsKt.getPsi((FirTypeRef) obj);
            if ((psi != null ? psi.getParent() : null) instanceof KtTypeConstraint) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list = (List) pair.component1();
        if (((List) pair.component2()).isEmpty() || list.isEmpty()) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktPsiSourceElement, FirErrors.INSTANCE.getMISPLACED_TYPE_PARAMETER_CONSTRAINTS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsiOrLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeParameter firTypeParameter, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeParameter.getClass();
        ktSourceElement.getClass();
        SourceNavigator sourceNavigatorForSource = SourceNavigator.INSTANCE.forSource(firTypeParameter.getSource());
        List<FirTypeRef> bounds = firTypeParameter.getBounds();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : bounds) {
            if (sourceNavigatorForSource.isInTypeConstraint((FirTypeRef) obj)) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list = (List) pair.component1();
        if (((List) pair.component2()).isEmpty() || list.isEmpty()) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getMISPLACED_TYPE_PARAMETER_CONSTRAINTS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public boolean isApplicable(FirTypeParameter element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        return element.getBounds().size() >= 2;
    }
}
