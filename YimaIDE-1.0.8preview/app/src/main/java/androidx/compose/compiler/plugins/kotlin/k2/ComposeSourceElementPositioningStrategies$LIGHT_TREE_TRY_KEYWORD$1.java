package androidx.compose.compiler.plugins.kotlin.k2;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategyKt;
import org.jetbrains.kotlin.lexer.KtKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0016¨\u0006\f"}, d2 = {"androidx/compose/compiler/plugins/kotlin/k2/ComposeSourceElementPositioningStrategies$LIGHT_TREE_TRY_KEYWORD$1", "Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", "mark", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "node", "Lcom/intellij/lang/LighterASTNode;", "startOffset", Argument.Delimiters.none, "endOffset", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComposeSourceElementPositioningStrategies$LIGHT_TREE_TRY_KEYWORD$1 extends LightTreePositioningStrategy {
    @Override // org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategy
    public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
        node.getClass();
        tree.getClass();
        KtKeywordToken ktKeywordToken = KtTokens.TRY_KEYWORD;
        ktKeywordToken.getClass();
        LighterASTNode lighterASTNodeFindChildByType = LightTreePositioningStrategiesKt.findChildByType(tree, node, (IElementType) ktKeywordToken);
        if (lighterASTNodeFindChildByType == null) {
            lighterASTNodeFindChildByType = node;
        }
        return LightTreePositioningStrategyKt.markElement(lighterASTNodeFindChildByType, startOffset, endOffset, tree, node);
    }
}
