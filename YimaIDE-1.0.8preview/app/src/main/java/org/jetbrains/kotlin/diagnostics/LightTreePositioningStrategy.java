package org.jetbrains.kotlin.diagnostics;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J4\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0012H\u0016J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0012H\u0017b\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/LightTreePositioningStrategy;", Argument.Delimiters.none, "<init>", "()V", "markKtDiagnostic", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "element", "Lorg/jetbrains/kotlin/KtSourceElement;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "mark", "node", "Lcom/intellij/lang/LighterASTNode;", "startOffset", Argument.Delimiters.none, "endOffset", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "isValid", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticLossRisk;", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class LightTreePositioningStrategy {
    @DiagnosticLossRisk
    public boolean isValid(LighterASTNode node, FlyweightCapableTreeStructure<LighterASTNode> tree) {
        node.getClass();
        tree.getClass();
        return !LightTreePositioningStrategyKt.hasSyntaxErrors(node, tree);
    }

    public List<TextRange> mark(LighterASTNode node, int startOffset, int endOffset, FlyweightCapableTreeStructure<LighterASTNode> tree) {
        node.getClass();
        tree.getClass();
        return LightTreePositioningStrategyKt.markElement$default(node, startOffset, endOffset, tree, null, 16, null);
    }

    public List<TextRange> markKtDiagnostic(KtSourceElement element, KtDiagnostic diagnostic) {
        element.getClass();
        diagnostic.getClass();
        return mark(element.getLighterASTNode(), element.getStartOffset(), element.getEndOffset(), element.getTreeStructure());
    }
}
