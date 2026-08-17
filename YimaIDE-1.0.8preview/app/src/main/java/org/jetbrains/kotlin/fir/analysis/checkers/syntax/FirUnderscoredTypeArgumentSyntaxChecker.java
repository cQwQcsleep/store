package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUnderscoreHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtTypeProjection;
import org.jetbrains.kotlin.psi.KtTypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J=\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003H\u0016R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0015J5\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0017H\u0016R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0018R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirUnderscoredTypeArgumentSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirExpressionSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lcom/intellij/psi/PsiElement;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "MESSAGE", Argument.Delimiters.none, "checkPsi", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lcom/intellij/psi/PsiElement;)V", "checkLightTree", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnderscoredTypeArgumentSyntaxChecker extends FirExpressionSyntaxChecker<FirFunctionCall, PsiElement> {
    public static final FirUnderscoredTypeArgumentSyntaxChecker INSTANCE = new FirUnderscoredTypeArgumentSyntaxChecker();

    private FirUnderscoredTypeArgumentSyntaxChecker() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall, KtLightSourceElement ktLightSourceElement) {
        LighterASTNode lighterASTNode;
        List<LighterASTNode> listAnnotations;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        ktLightSourceElement.getClass();
        Iterator<FirTypeProjection> it = firFunctionCall.getTypeArguments().iterator();
        while (it.hasNext()) {
            KtSourceElement source = it.next().getSource();
            if (source == null || (lighterASTNode = source.getLighterASTNode()) == null) {
                checkerContext = checkerContext;
                diagnosticReporter = diagnosticReporter;
            } else if (FirUnderscoreHelpersKt.isUnderscore(String.valueOf(LightTreePositioningStrategiesKt.userType(ktLightSourceElement.getTreeStructure(), lighterASTNode))) && (listAnnotations = LightTreePositioningStrategiesKt.annotations(ktLightSourceElement.getTreeStructure(), lighterASTNode)) != null) {
                Iterator<LighterASTNode> it2 = listAnnotations.iterator();
                while (it2.hasNext()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) FirSourceUtilsKt.buildChildSourceElement(ktLightSourceElement, it2.next()), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Underscore type arguments cannot be annotated.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall, KtPsiSourceElement ktPsiSourceElement, PsiElement psiElement) {
        KtTypeReference typeReference;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        ktPsiSourceElement.getClass();
        psiElement.getClass();
        Iterator<FirTypeProjection> it = firFunctionCall.getTypeArguments().iterator();
        while (it.hasNext()) {
            KtSourceElement source = it.next().getSource();
            PsiElement psi = source != null ? KtSourceElementKt.getPsi(source) : null;
            KtTypeProjection ktTypeProjection = psi instanceof KtTypeProjection ? (KtTypeProjection) psi : null;
            if (ktTypeProjection != null && (typeReference = ktTypeProjection.getTypeReference()) != null && typeReference.isPlaceholder()) {
                for (KtAnnotationEntry ktAnnotationEntry : typeReference.getAnnotationEntries()) {
                    if (KtRealSourceElementKind.INSTANCE == null) {
                        bu8.a();
                        return;
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) new KtRealPsiSourceElement(ktAnnotationEntry), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Underscore type arguments cannot be annotated.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public boolean isApplicable(FirFunctionCall element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        return !element.getTypeArguments().isEmpty();
    }
}
