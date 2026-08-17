package org.jetbrains.kotlin.diagnostics;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a<\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\b\b\u0002\u0010\n\u001a\u00020\u0004\u001aB\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\n\u001a\u00020\u0004\u001a<\u0010\u000e\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\n\u001a\u00020\u0004\u001a\u001a\u0010\u0014\u001a\u00020\u0004*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tH\u0002\u001a\u001a\u0010\u0015\u001a\u00020\u0004*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tH\u0000\u001a\n\u0010\u0016\u001a\u00020\u0017*\u00020\u0004\u001a\u001e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tH\u0002\"\u001c\u0010\u000f\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010\u0019\u001a\u00020\u0006*\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"markElement", Argument.Delimiters.none, "Lcom/intellij/openapi/util/TextRange;", "node", "Lcom/intellij/lang/LighterASTNode;", "startOffset", Argument.Delimiters.none, "endOffset", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "originalNode", "markRange", "from", "to", "markSingleElement", "DOC_AND_COMMENT_TOKENS", Argument.Delimiters.none, "Lcom/intellij/psi/tree/IElementType;", "kotlin.jvm.PlatformType", "FILLER_TOKENS", "nonFillerFirstChildOrSelf", "nonFillerLastChildOrSelf", "isFiller", Argument.Delimiters.none, "hasSyntaxErrors", "startOffsetSkippingComments", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "getStartOffsetSkippingComments", "(Lorg/jetbrains/kotlin/KtLightSourceElement;)I", "org.jetbrains.kotlin:frontend.common-psi"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LightTreePositioningStrategyKt {
    private static final Set<IElementType> DOC_AND_COMMENT_TOKENS;
    private static final Set<IElementType> FILLER_TOKENS;

    static {
        IElementType iElementType = KtTokens.WHITE_SPACE;
        IElementType iElementType2 = KtTokens.EOL_COMMENT;
        IElementType iElementType3 = KtTokens.BLOCK_COMMENT;
        IElementType iElementType4 = KtTokens.SHEBANG_COMMENT;
        IElementType iElementType5 = KtTokens.DOC_COMMENT;
        DOC_AND_COMMENT_TOKENS = SetsKt.setOf(new IElementType[]{iElementType, KtTokens.IDENTIFIER, iElementType2, iElementType3, iElementType4, iElementType5});
        FILLER_TOKENS = SetsKt.setOf(new IElementType[]{iElementType, iElementType2, iElementType3, iElementType4, iElementType5});
    }

    public static final int getStartOffsetSkippingComments(KtLightSourceElement ktLightSourceElement) {
        ktLightSourceElement.getClass();
        List children = LightTreeUtilsKt.getChildren(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement.getTreeStructure());
        ArrayList arrayList = new ArrayList();
        for (Object obj : children) {
            if (!FILLER_TOKENS.contains(((LighterASTNode) obj).getTokenType())) {
                break;
            }
            arrayList.add(obj);
        }
        int startOffset = ktLightSourceElement.getStartOffset();
        Iterator it = arrayList.iterator();
        int textLength = 0;
        while (it.hasNext()) {
            textLength += ((LighterASTNode) it.next()).getTextLength();
        }
        return startOffset + textLength;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasSyntaxErrors(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        Object objPrevious;
        if (Intrinsics.areEqual(lighterASTNode.getTokenType(), TokenType.ERROR_ELEMENT)) {
            return true;
        }
        List children = LightTreeUtilsKt.getChildren(lighterASTNode, flyweightCapableTreeStructure);
        ListIterator listIterator = children.listIterator(children.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            }
            objPrevious = listIterator.previous();
            IElementType tokenType = ((LighterASTNode) objPrevious).getTokenType();
            if (!(tokenType instanceof KtSingleValueToken) && !DOC_AND_COMMENT_TOKENS.contains(tokenType)) {
                break;
            }
        }
        LighterASTNode lighterASTNode2 = (LighterASTNode) objPrevious;
        return lighterASTNode2 != null && hasSyntaxErrors(lighterASTNode2, flyweightCapableTreeStructure);
    }

    public static final boolean isFiller(LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return FILLER_TOKENS.contains(lighterASTNode.getTokenType());
    }

    public static final List<TextRange> markElement(LighterASTNode lighterASTNode, int i, int i2, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode2) {
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        lighterASTNode2.getClass();
        return markRange(lighterASTNode, lighterASTNode, i, i2, flyweightCapableTreeStructure, lighterASTNode2);
    }

    public static /* synthetic */ List markElement$default(LighterASTNode lighterASTNode, int i, int i2, FlyweightCapableTreeStructure flyweightCapableTreeStructure, LighterASTNode lighterASTNode2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            lighterASTNode2 = lighterASTNode;
        }
        return markElement(lighterASTNode, i, i2, flyweightCapableTreeStructure, lighterASTNode2);
    }

    public static final List<TextRange> markRange(LighterASTNode lighterASTNode, LighterASTNode lighterASTNode2, int i, int i2, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode3) {
        lighterASTNode.getClass();
        lighterASTNode2.getClass();
        flyweightCapableTreeStructure.getClass();
        lighterASTNode3.getClass();
        return CollectionsKt.listOf(markSingleElement(lighterASTNode, lighterASTNode2, i, i2, flyweightCapableTreeStructure, lighterASTNode3));
    }

    public static final TextRange markSingleElement(LighterASTNode lighterASTNode, LighterASTNode lighterASTNode2, int i, int i2, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LighterASTNode lighterASTNode3) {
        lighterASTNode.getClass();
        lighterASTNode2.getClass();
        flyweightCapableTreeStructure.getClass();
        lighterASTNode3.getClass();
        LighterASTNode lighterASTNodeNonFillerFirstChildOrSelf = nonFillerFirstChildOrSelf(lighterASTNode, flyweightCapableTreeStructure);
        LighterASTNode lighterASTNodeNonFillerLastChildOrSelf = nonFillerLastChildOrSelf(lighterASTNode2, flyweightCapableTreeStructure);
        return new TextRange((flyweightCapableTreeStructure.getStartOffset(lighterASTNodeNonFillerFirstChildOrSelf) - flyweightCapableTreeStructure.getStartOffset(lighterASTNode3)) + i, (flyweightCapableTreeStructure.getEndOffset(lighterASTNodeNonFillerLastChildOrSelf) - flyweightCapableTreeStructure.getEndOffset(lighterASTNode3)) + i2);
    }

    private static final LighterASTNode nonFillerFirstChildOrSelf(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        Object next;
        Iterator it = LightTreeUtilsKt.getChildren(lighterASTNode, flyweightCapableTreeStructure).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (isFiller((LighterASTNode) next));
        LighterASTNode lighterASTNode2 = (LighterASTNode) next;
        return lighterASTNode2 == null ? lighterASTNode : lighterASTNode2;
    }

    public static final LighterASTNode nonFillerLastChildOrSelf(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        Object objPrevious;
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        List children = LightTreeUtilsKt.getChildren(lighterASTNode, flyweightCapableTreeStructure);
        ListIterator listIterator = children.listIterator(children.size());
        do {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            }
            objPrevious = listIterator.previous();
        } while (isFiller((LighterASTNode) objPrevious));
        LighterASTNode lighterASTNode2 = (LighterASTNode) objPrevious;
        return lighterASTNode2 == null ? lighterASTNode : lighterASTNode2;
    }
}
