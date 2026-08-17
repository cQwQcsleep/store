package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.PsiElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.psi.KtStringTemplateExpression;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0011J\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/RedundantSingleExpressionStringTemplateChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirStringConcatenationCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;)V", "stringParentChildrenCount", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)Ljava/lang/Integer;", "Lcom/intellij/psi/PsiElement;", "(Lcom/intellij/psi/PsiElement;)Ljava/lang/Integer;", "Lcom/intellij/lang/LighterASTNode;", "source", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lcom/intellij/lang/LighterASTNode;Lorg/jetbrains/kotlin/KtLightSourceElement;)Ljava/lang/Integer;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantSingleExpressionStringTemplateChecker extends FirExpressionChecker<FirStringConcatenationCall> {
    public static final RedundantSingleExpressionStringTemplateChecker INSTANCE = new RedundantSingleExpressionStringTemplateChecker();

    private RedundantSingleExpressionStringTemplateChecker() {
        super(MppCheckerKind.Common);
    }

    private final Integer stringParentChildrenCount(LighterASTNode lighterASTNode, KtLightSourceElement ktLightSourceElement) {
        LighterASTNode lighterASTNode2 = (LighterASTNode) ktLightSourceElement.getTreeStructure().getParent(lighterASTNode);
        if (lighterASTNode2 == null || !Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.STRING_TEMPLATE)) {
            if (lighterASTNode2 != null) {
                return stringParentChildrenCount(lighterASTNode2, ktLightSourceElement);
            }
            return null;
        }
        List children = LightTreeUtilsKt.getChildren(lighterASTNode2, ktLightSourceElement.getTreeStructure());
        ArrayList arrayList = new ArrayList();
        for (Object obj : children) {
            if (((LighterASTNode) obj) instanceof PsiBuilder.Marker) {
                arrayList.add(obj);
            }
        }
        return Integer.valueOf(arrayList.size());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStringConcatenationCall firStringConcatenationCall) {
        Integer numStringParentChildrenCount;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStringConcatenationCall.getClass();
        for (FirExpression firExpression : firStringConcatenationCall.getArgumentList().getArguments()) {
            if (Intrinsics.areEqual(FirHelpersKt.fullyExpandedClassId(FirTypeUtilsKt.getResolvedType(firExpression), checkerContext.getSession()), StandardClassIds.INSTANCE.getString()) && (numStringParentChildrenCount = stringParentChildrenCount(firExpression)) != null && numStringParentChildrenCount.intValue() == 1) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), FirErrors.INSTANCE.getREDUNDANT_SINGLE_EXPRESSION_STRING_TEMPLATE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final Integer stringParentChildrenCount(PsiElement psiElement) {
        KtStringTemplateExpression parent = psiElement.getParent();
        if (parent instanceof KtStringTemplateExpression) {
            return Integer.valueOf(parent.getChildren().length);
        }
        if (parent == null) {
            return null;
        }
        return stringParentChildrenCount((PsiElement) parent);
    }

    private final Integer stringParentChildrenCount(FirStatement firStatement) {
        KtPsiSourceElement source = firStatement.getSource();
        if (source instanceof KtPsiSourceElement) {
            return stringParentChildrenCount(source.getPsi());
        }
        if (source instanceof KtLightSourceElement) {
            KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) source;
            return stringParentChildrenCount(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement);
        }
        if (source == null) {
            return null;
        }
        bu8.a();
        return null;
    }
}
