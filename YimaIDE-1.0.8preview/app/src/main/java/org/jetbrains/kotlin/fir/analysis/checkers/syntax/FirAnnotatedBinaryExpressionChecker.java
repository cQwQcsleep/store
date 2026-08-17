package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.psi.KtAnnotatedExpression;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtPsiUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J=\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0003H\u0016R\u00020\u0010R\u00020\u0012j\u0006\u0010\u0011\u001a\u00020\u0010j\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0002\u0010\u0016J5\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0018H\u0016R\u00020\u0010R\u00020\u0012j\u0006\u0010\u0011\u001a\u00020\u0010j\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0002\u0010\u0019R\u0018\u0010\u000b\u001a\u00020\u0007*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u001a\u001a\u00020\u0007*\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0018\u0010\u001d\u001a\u00020\u0007*\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001c¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirAnnotatedBinaryExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirExpressionSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lcom/intellij/psi/PsiElement;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "hasAnnotatedLhs", "getHasAnnotatedLhs", "(Lorg/jetbrains/kotlin/KtSourceElement;)Z", "checkPsi", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lcom/intellij/psi/PsiElement;)V", "checkLightTree", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "isStatementContainer", "Lcom/intellij/lang/LighterASTNode;", "(Lcom/intellij/lang/LighterASTNode;)Z", "isContainerNodeForControlStructureBody", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotatedBinaryExpressionChecker extends FirExpressionSyntaxChecker<FirStatement, PsiElement> {
    public static final FirAnnotatedBinaryExpressionChecker INSTANCE = new FirAnnotatedBinaryExpressionChecker();

    private FirAnnotatedBinaryExpressionChecker() {
    }

    private final boolean getHasAnnotatedLhs(KtSourceElement ktSourceElement) {
        KtBinaryExpression psi = KtSourceElementKt.getPsi(ktSourceElement);
        KtBinaryExpression ktBinaryExpression = psi instanceof KtBinaryExpression ? psi : null;
        if ((ktBinaryExpression != null ? ktBinaryExpression.getLeft() : null) instanceof KtAnnotatedExpression) {
            return true;
        }
        LighterASTNode lighterASTNode = (LighterASTNode) ArraysKt.firstOrNull(LightTreePositioningStrategiesKt.getChildrenArray(ktSourceElement.getTreeStructure(), ktSourceElement.getLighterASTNode()));
        return Intrinsics.areEqual(lighterASTNode != null ? lighterASTNode.getTokenType() : null, KtNodeTypes.ANNOTATED_EXPRESSION);
    }

    private final boolean isContainerNodeForControlStructureBody(LighterASTNode lighterASTNode) {
        return Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.BODY) || Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.ELSE) || Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.THEN);
    }

    private final boolean isStatementContainer(LighterASTNode lighterASTNode) {
        return Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.BLOCK) || Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.WHEN_ENTRY) || isContainerNodeForControlStructureBody(lighterASTNode);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, KtLightSourceElement ktLightSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        ktLightSourceElement.getClass();
        LighterASTNode lighterASTNode = ktLightSourceElement.getLighterASTNode();
        Object parent = ktLightSourceElement.getTreeStructure().getParent(lighterASTNode);
        while (true) {
            LighterASTNode lighterASTNode2 = (LighterASTNode) parent;
            LighterASTNode lighterASTNode3 = lighterASTNode;
            lighterASTNode = lighterASTNode2;
            if (!Intrinsics.areEqual(lighterASTNode != null ? lighterASTNode.getTokenType() : null, KtNodeTypes.BINARY_EXPRESSION)) {
                if (lighterASTNode == null || !isStatementContainer(lighterASTNode)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktLightSourceElement, FirErrors.INSTANCE.getANNOTATIONS_ON_BLOCK_LEVEL_EXPRESSION_ON_THE_SAME_LINE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            if (!Intrinsics.areEqual(ArraysKt.firstOrNull(LightTreePositioningStrategiesKt.getChildrenArray(ktLightSourceElement.getTreeStructure(), lighterASTNode)), lighterASTNode3)) {
                return;
            } else {
                parent = ktLightSourceElement.getTreeStructure().getParent(lighterASTNode);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, KtPsiSourceElement ktPsiSourceElement, PsiElement psiElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        ktPsiSourceElement.getClass();
        psiElement.getClass();
        PsiElement psi = ktPsiSourceElement.getPsi();
        PsiElement parent = psi.getParent();
        while (true) {
            PsiElement psiElement2 = parent;
            PsiElement psiElement3 = psi;
            psi = psiElement2;
            if (!(psi instanceof KtBinaryExpression)) {
                if (KtPsiUtil.isStatementContainer(psi)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktPsiSourceElement, FirErrors.INSTANCE.getANNOTATIONS_ON_BLOCK_LEVEL_EXPRESSION_ON_THE_SAME_LINE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
                return;
            } else {
                KtBinaryExpression ktBinaryExpression = (KtBinaryExpression) psi;
                if (!Intrinsics.areEqual(ktBinaryExpression.getLeft(), psiElement3)) {
                    return;
                } else {
                    parent = ktBinaryExpression.getParent();
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public boolean isApplicable(FirStatement element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        return (source.getKind() instanceof KtRealSourceElementKind) && Intrinsics.areEqual(source.getElementType(), KtNodeTypes.BINARY_EXPRESSION) && getHasAnnotatedLhs(source);
    }
}
