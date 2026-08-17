package org.jetbrains.kotlin.util;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.Ref;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u001a\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u001a\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¨\u0006\u0007"}, d2 = {"getChildren", "", "Lcom/intellij/lang/LighterASTNode;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "getSingleChildOrNull", "getPreviousSibling", "org.jetbrains.kotlin:frontend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class LightTreeUtilsKt {
    public static final List<LighterASTNode> getChildren(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        Ref ref = new Ref();
        int children = flyweightCapableTreeStructure.getChildren(lighterASTNode, ref);
        if (children <= 0) {
            return CollectionsKt.emptyList();
        }
        Object obj = ref.get();
        obj.getClass();
        List<LighterASTNode> listTake = ArraysKt.take((Object[]) obj, children);
        listTake.getClass();
        return listTake;
    }

    public static final LighterASTNode getPreviousSibling(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        LighterASTNode lighterASTNode2 = (LighterASTNode) flyweightCapableTreeStructure.getParent(lighterASTNode);
        if (lighterASTNode2 == null) {
            return null;
        }
        List<LighterASTNode> children = getChildren(lighterASTNode2, flyweightCapableTreeStructure);
        return (LighterASTNode) CollectionsKt.getOrNull(children, children.indexOf(lighterASTNode) - 1);
    }

    public static final LighterASTNode getSingleChildOrNull(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        Ref ref = new Ref(new LighterASTNode[]{null});
        if (flyweightCapableTreeStructure.getChildren(lighterASTNode, ref) == 1) {
            return ((LighterASTNode[]) ref.get())[0];
        }
        return null;
    }
}
