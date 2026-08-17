package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.analysis.checkers.SourceHelpersKt;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"explicitVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "Lorg/jetbrains/kotlin/KtSourceElement;", "getExplicitVisibility", "(Lorg/jetbrains/kotlin/KtSourceElement;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantVisibilityModifierSyntaxCheckerKt {
    public static final Visibility getExplicitVisibility(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        LighterASTNode lighterASTNodeVisibilityModifier = LightTreePositioningStrategiesKt.visibilityModifier(ktSourceElement.getTreeStructure(), ktSourceElement.getLighterASTNode());
        IElementType tokenType = lighterASTNodeVisibilityModifier != null ? lighterASTNodeVisibilityModifier.getTokenType() : null;
        KtModifierKeywordToken ktModifierKeywordToken = tokenType instanceof KtModifierKeywordToken ? (KtModifierKeywordToken) tokenType : null;
        if (ktModifierKeywordToken != null) {
            return SourceHelpersKt.toVisibilityOrNull(ktModifierKeywordToken);
        }
        return null;
    }
}
