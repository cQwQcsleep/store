package org.jetbrains.kotlin.fir.analysis.checkers;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002\u0011\u0012B\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0002\u0013\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier;", "Node", Argument.Delimiters.none, "node", "token", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "<init>", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;)V", "getNode", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getToken", "()Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "FirPsiModifier", "FirLightModifier", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier$FirLightModifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier$FirPsiModifier;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirModifier<Node> {
    private final Node node;
    private final KtModifierKeywordToken token;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier$FirLightModifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier;", "Lcom/intellij/lang/LighterASTNode;", "node", "token", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "offsetDelta", Argument.Delimiters.none, "<init>", "(Lcom/intellij/lang/LighterASTNode;Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;Lcom/intellij/util/diff/FlyweightCapableTreeStructure;I)V", "getTree", "()Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirLightModifier extends FirModifier<LighterASTNode> {
        private final int offsetDelta;
        private final FlyweightCapableTreeStructure<LighterASTNode> tree;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FirLightModifier(LighterASTNode lighterASTNode, KtModifierKeywordToken ktModifierKeywordToken, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, int i) {
            super(lighterASTNode, ktModifierKeywordToken, null);
            lighterASTNode.getClass();
            ktModifierKeywordToken.getClass();
            flyweightCapableTreeStructure.getClass();
            this.tree = flyweightCapableTreeStructure;
            this.offsetDelta = i;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirModifier
        public KtSourceElement getSource() {
            return new KtLightSourceElement(getNode(), getNode().getStartOffset() + this.offsetDelta, getNode().getEndOffset() + this.offsetDelta, this.tree, KtRealSourceElementKind.INSTANCE);
        }

        public final FlyweightCapableTreeStructure<LighterASTNode> getTree() {
            return this.tree;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier$FirPsiModifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier;", "Lcom/intellij/lang/ASTNode;", "node", "token", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "<init>", "(Lcom/intellij/lang/ASTNode;Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;)V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirPsiModifier extends FirModifier<ASTNode> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FirPsiModifier(ASTNode aSTNode, KtModifierKeywordToken ktModifierKeywordToken) {
            super(aSTNode, ktModifierKeywordToken, null);
            aSTNode.getClass();
            ktModifierKeywordToken.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirModifier
        public KtSourceElement getSource() {
            PsiElement psi = getNode().getPsi();
            psi.getClass();
            if (KtRealSourceElementKind.INSTANCE != null) {
                return new KtRealPsiSourceElement(psi);
            }
            bu8.a();
            return null;
        }
    }

    private FirModifier(Node node, KtModifierKeywordToken ktModifierKeywordToken) {
        this.node = node;
        this.token = ktModifierKeywordToken;
    }

    public final Node getNode() {
        return this.node;
    }

    public abstract KtSourceElement getSource();

    public final KtModifierKeywordToken getToken() {
        return this.token;
    }

    public /* synthetic */ FirModifier(Object obj, KtModifierKeywordToken ktModifierKeywordToken, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, ktModifierKeywordToken);
    }
}
