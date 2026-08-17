package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.lexer.KtKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001,B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J=\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003H\u0016R\u00020\u0011R\u00020\u0013j\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u0017J5\u0010\u0018\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0019H\u0016R\u00020\u0011R\u00020\u0013j\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u001aJ$\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0 H\u0002J\u001c\u0010!\u001a\u0004\u0018\u00010\u001c*\u00020\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0 H\u0002J\u001c\u0010\"\u001a\u0004\u0018\u00010\u001c*\u00020\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0 H\u0002J-\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020%H\u0002R\u00020\u0011R\u00020\u0013j\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010&J5\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u00020\u0011R\u00020\u0013j\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010'J\u0010\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\bH\u0002J-\u0010*\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u00020\u0011R\u00020\u0013j\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010+R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirPrefixAndSuffixSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirExpressionSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/psi/KtExpression;", "<init>", "()V", "literalConstants", Argument.Delimiters.none, "Lcom/intellij/psi/tree/IElementType;", "kotlin.jvm.PlatformType", "isApplicable", Argument.Delimiters.none, "element", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkPsi", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lorg/jetbrains/kotlin/psi/KtExpression;)V", "checkLightTree", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "getLeaf", "Lcom/intellij/lang/LighterASTNode;", "direction", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirPrefixAndSuffixSyntaxChecker$Direction;", "treeStructure", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "prevLeaf", "nextLeaf", "checkLiteralPrefixOrSuffix", "prefixOrSuffix", "Lcom/intellij/psi/PsiElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lcom/intellij/psi/PsiElement;)V", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lcom/intellij/lang/LighterASTNode;Lorg/jetbrains/kotlin/KtSourceElement;)V", "illegalLiteralPrefixOrSuffix", "elementType", "report", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;)V", "Direction", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPrefixAndSuffixSyntaxChecker extends FirExpressionSyntaxChecker<FirStatement, KtExpression> {
    public static final FirPrefixAndSuffixSyntaxChecker INSTANCE = new FirPrefixAndSuffixSyntaxChecker();
    private static final List<IElementType> literalConstants = CollectionsKt.listOf(new IElementType[]{KtNodeTypes.CHARACTER_CONSTANT, KtNodeTypes.FLOAT_CONSTANT, KtNodeTypes.INTEGER_CONSTANT});

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirPrefixAndSuffixSyntaxChecker$Direction;", Argument.Delimiters.none, "offset", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;II)V", "getOffset", "()I", "PREVIOUS", "NEXT", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Direction {
        PREVIOUS(-1),
        NEXT(1);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final int offset;

        Direction(int i) {
            this.offset = i;
        }

        public static EnumEntries<Direction> getEntries() {
            return $ENTRIES;
        }

        public final int getOffset() {
            return this.offset;
        }
    }

    private FirPrefixAndSuffixSyntaxChecker() {
    }

    private final void checkLiteralPrefixOrSuffix(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, LighterASTNode lighterASTNode, KtSourceElement ktSourceElement) {
        IElementType tokenType = lighterASTNode.getTokenType();
        if (tokenType != null && illegalLiteralPrefixOrSuffix(tokenType)) {
            report(checkerContext, diagnosticReporter, new KtLightSourceElement(lighterASTNode, lighterASTNode.getStartOffset(), lighterASTNode.getEndOffset(), ktSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE));
        }
    }

    private final LighterASTNode getLeaf(LighterASTNode lighterASTNode, Direction direction, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        LighterASTNode lighterASTNode2 = (LighterASTNode) flyweightCapableTreeStructure.getParent(lighterASTNode);
        if (lighterASTNode2 == null) {
            return null;
        }
        List children = LightTreeUtilsKt.getChildren(lighterASTNode2, flyweightCapableTreeStructure);
        LighterASTNode lighterASTNode3 = (LighterASTNode) CollectionsKt.getOrNull(children, children.indexOf(lighterASTNode) - direction.getOffset());
        if (lighterASTNode3 == null && Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.BINARY_EXPRESSION)) {
            return getLeaf(lighterASTNode2, direction, flyweightCapableTreeStructure);
        }
        if (lighterASTNode3 == null) {
            return null;
        }
        List children2 = LightTreeUtilsKt.getChildren(lighterASTNode3, flyweightCapableTreeStructure);
        while (!children2.isEmpty()) {
            lighterASTNode3 = (LighterASTNode) (direction == Direction.PREVIOUS ? CollectionsKt.first(children2) : CollectionsKt.last(children2));
            children2 = LightTreeUtilsKt.getChildren(lighterASTNode3, flyweightCapableTreeStructure);
        }
        return lighterASTNode3;
    }

    private final boolean illegalLiteralPrefixOrSuffix(IElementType elementType) {
        return elementType == KtTokens.IDENTIFIER || elementType == KtTokens.INTEGER_LITERAL || elementType == KtTokens.FLOAT_LITERAL || (elementType instanceof KtKeywordToken);
    }

    private final LighterASTNode nextLeaf(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        return getLeaf(lighterASTNode, Direction.NEXT, flyweightCapableTreeStructure);
    }

    private final LighterASTNode prevLeaf(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        return getLeaf(lighterASTNode, Direction.PREVIOUS, flyweightCapableTreeStructure);
    }

    private final void report(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED(), (Object) "Literals must be surrounded by whitespace.", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, KtLightSourceElement ktLightSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        ktLightSourceElement.getClass();
        LighterASTNode lighterASTNodePrevLeaf = prevLeaf(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement.getTreeStructure());
        if (lighterASTNodePrevLeaf != null) {
            INSTANCE.checkLiteralPrefixOrSuffix(checkerContext, diagnosticReporter, lighterASTNodePrevLeaf, ktLightSourceElement);
        }
        LighterASTNode lighterASTNodeNextLeaf = nextLeaf(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement.getTreeStructure());
        if (lighterASTNodeNextLeaf != null) {
            INSTANCE.checkLiteralPrefixOrSuffix(checkerContext, diagnosticReporter, lighterASTNodeNextLeaf, ktLightSourceElement);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, KtPsiSourceElement ktPsiSourceElement, KtExpression ktExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        ktPsiSourceElement.getClass();
        ktExpression.getClass();
        PsiElement psiElementPrevLeaf$default = PsiUtilsKt.prevLeaf$default(ktExpression, false, 1, (Object) null);
        if (psiElementPrevLeaf$default != null) {
            INSTANCE.checkLiteralPrefixOrSuffix(checkerContext, diagnosticReporter, psiElementPrevLeaf$default);
        }
        PsiElement psiElementNextLeaf$default = PsiUtilsKt.nextLeaf$default(ktExpression, false, 1, (Object) null);
        if (psiElementNextLeaf$default != null) {
            INSTANCE.checkLiteralPrefixOrSuffix(checkerContext, diagnosticReporter, psiElementNextLeaf$default);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public boolean isApplicable(FirStatement element, KtSourceElement source) {
        element.getClass();
        source.getClass();
        if (source.getKind() instanceof KtFakeSourceElementKind) {
            return false;
        }
        return Intrinsics.areEqual(source.getElementType(), KtNodeTypes.STRING_TEMPLATE) || literalConstants.contains(source.getElementType());
    }

    private final void checkLiteralPrefixOrSuffix(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, PsiElement psiElement) {
        IElementType elementType = psiElement.getNode().getElementType();
        elementType.getClass();
        if (illegalLiteralPrefixOrSuffix(elementType)) {
            if (KtRealSourceElementKind.INSTANCE != null) {
                report(checkerContext, diagnosticReporter, new KtRealPsiSourceElement(psiElement));
            } else {
                bu8.a();
            }
        }
    }
}
