package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00028\u0000H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012J=\u0010\u0013\u001a\u00020\u00072\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0001H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0016J5\u0010\u0017\u001a\u00020\u00072\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0018H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0019J5\u0010\u001a\u001a\u00020\u00072\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001cÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirSyntaxChecker;", "D", "Lorg/jetbrains/kotlin/fir/FirElement;", "P", "Lcom/intellij/psi/PsiElement;", Argument.Delimiters.none, "checkSyntax", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "element", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirElement;)V", "isApplicable", Argument.Delimiters.none, "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/KtSourceElement;)Z", "checkPsi", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lcom/intellij/psi/PsiElement;)V", "checkLightTree", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "checkPsiOrLightTree", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirSyntaxChecker<D extends FirElement, P extends PsiElement> {
    default void checkLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, D d, KtLightSourceElement ktLightSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        d.getClass();
        ktLightSourceElement.getClass();
        checkPsiOrLightTree(checkerContext, diagnosticReporter, d, ktLightSourceElement);
    }

    default void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, D d, KtPsiSourceElement ktPsiSourceElement, P p) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        d.getClass();
        ktPsiSourceElement.getClass();
        p.getClass();
        checkPsiOrLightTree(checkerContext, diagnosticReporter, d, ktPsiSourceElement);
    }

    default void checkPsiOrLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, D d, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        d.getClass();
        ktSourceElement.getClass();
    }

    default void checkSyntax(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, D d) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        d.getClass();
        KtPsiSourceElement source = d.getSource();
        if (source != null && isApplicable(d, source)) {
            if (source instanceof KtPsiSourceElement) {
                KtPsiSourceElement ktPsiSourceElement = source;
                PsiElement psi = ktPsiSourceElement.getPsi();
                psi.getClass();
                checkPsi(checkerContext, diagnosticReporter, d, ktPsiSourceElement, psi);
                return;
            }
            if (source instanceof KtLightSourceElement) {
                checkLightTree(checkerContext, diagnosticReporter, d, (KtLightSourceElement) source);
            } else {
                bu8.a();
            }
        }
    }

    default boolean isApplicable(D element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        return true;
    }
}
