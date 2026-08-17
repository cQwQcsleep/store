package androidx.compose.ui.semantics;

import android.os.Trace;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a,\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\b*\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\f\u0010\u000b\u001a\u00020\u0005*\u00020\u0002H\u0000\u001a.\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\t2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0017H\u0000\"\u001e\u0010\f\u001a\u00020\u0005*\u00020\u00028@X\u0080\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\f\u0010\u000f\"\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"getAllSemanticsNodes", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "Landroidx/compose/ui/semantics/SemanticsOwner;", "mergingEnabled", "", "skipDeactivatedNodes", "getAllSemanticsNodesToMap", "", "", "useUnmergedTree", "isImportantForAccessibility", "isHidden", "isHidden$annotations", "(Landroidx/compose/ui/semantics/SemanticsNode;)V", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "DefaultFakeNodeBounds", "Landroidx/compose/ui/geometry/Rect;", "getAllUncoveredSemanticsNodesToIntObjectMap", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/semantics/SemanticsNodeWithAdjustedBounds;", "customRootNodeId", "shouldIgnoreNode", "Lkotlin/Function1;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SemanticsOwnerKt {
    private static final Rect DefaultFakeNodeBounds = new Rect(0.0f, 0.0f, 10.0f, 10.0f);

    public static final List<SemanticsNode> getAllSemanticsNodes(SemanticsOwner semanticsOwner, boolean z, boolean z2) {
        return CollectionsKt.toList(getAllSemanticsNodesToMap(semanticsOwner, !z, z2).values());
    }

    public static /* synthetic */ List getAllSemanticsNodes$default(SemanticsOwner semanticsOwner, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        return getAllSemanticsNodes(semanticsOwner, z, z2);
    }

    public static final Map<Integer, SemanticsNode> getAllSemanticsNodesToMap(SemanticsOwner semanticsOwner, boolean z, boolean z2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SemanticsNode unmergedRootSemanticsNode = z ? semanticsOwner.getUnmergedRootSemanticsNode() : semanticsOwner.getRootSemanticsNode();
        if (z2 && unmergedRootSemanticsNode.getLayoutNode().getIsDeactivated()) {
            return linkedHashMap;
        }
        getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(linkedHashMap, z2, unmergedRootSemanticsNode);
        return linkedHashMap;
    }

    public static /* synthetic */ Map getAllSemanticsNodesToMap$default(SemanticsOwner semanticsOwner, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return getAllSemanticsNodesToMap(semanticsOwner, z, z2);
    }

    private static final void getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(Map<Integer, SemanticsNode> map, boolean z, SemanticsNode semanticsNode) {
        map.put(Integer.valueOf(semanticsNode.getId()), semanticsNode);
        List children$ui$default = SemanticsNode.getChildren$ui$default(semanticsNode, false, false, !z, 3, null);
        int size = children$ui$default.size();
        for (int i = 0; i < size; i++) {
            getAllSemanticsNodesToMap$findAllSemanticNodesRecursive(map, z, (SemanticsNode) children$ui$default.get(i));
        }
    }

    public static final IntObjectMap<SemanticsNodeWithAdjustedBounds> getAllUncoveredSemanticsNodesToIntObjectMap(SemanticsOwner semanticsOwner, int i, Function1<? super SemanticsNode, Boolean> function1) {
        Trace.beginSection("getAllUncoveredSemanticsNodesToIntObjectMap");
        try {
            SemanticsNode unmergedRootSemanticsNode = semanticsOwner.getUnmergedRootSemanticsNode();
            if (unmergedRootSemanticsNode.getLayoutNode().isPlaced() && unmergedRootSemanticsNode.getLayoutNode().isAttached()) {
                MutableIntObjectMap mutableIntObjectMap = new MutableIntObjectMap(48);
                SemanticsRegion SemanticsRegion = SemanticsRegion_androidKt.SemanticsRegion();
                SemanticsRegion.set(IntRectKt.roundToIntRect(unmergedRootSemanticsNode.getBoundsInRoot()));
                getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$findAllSemanticNodesRecursive(SemanticsRegion, unmergedRootSemanticsNode, i, mutableIntObjectMap, function1, unmergedRootSemanticsNode, SemanticsRegion_androidKt.SemanticsRegion());
                return mutableIntObjectMap;
            }
            return IntObjectMapKt.emptyIntObjectMap();
        } finally {
            Trace.endSection();
        }
    }

    private static final void getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$findAllSemanticNodesRecursive(SemanticsRegion semanticsRegion, SemanticsNode semanticsNode, int i, MutableIntObjectMap<SemanticsNodeWithAdjustedBounds> mutableIntObjectMap, Function1<? super SemanticsNode, Boolean> function1, SemanticsNode semanticsNode2, SemanticsRegion semanticsRegion2) {
        LayoutInfo layoutInfo;
        SemanticsRegion semanticsRegion3 = semanticsRegion2;
        boolean z = (semanticsNode2.getLayoutNode().isPlaced() && semanticsNode2.getLayoutNode().isAttached()) ? false : true;
        if (!semanticsRegion.isEmpty() || semanticsNode2.getId() == semanticsNode.getId()) {
            if (!z || semanticsNode2.getIsFake()) {
                IntRect intRectRoundToIntRect = IntRectKt.roundToIntRect(semanticsNode2.getTouchBoundsInRoot());
                semanticsRegion3.set(intRectRoundToIntRect);
                int id = semanticsNode2.getId() == semanticsNode.getId() ? i : semanticsNode2.getId();
                if (!semanticsRegion3.intersect(semanticsRegion)) {
                    if (semanticsNode2.getIsFake()) {
                        SemanticsNode parent = semanticsNode2.getParent();
                        mutableIntObjectMap.set(id, new SemanticsNodeWithAdjustedBounds(semanticsNode2, IntRectKt.roundToIntRect((parent == null || (layoutInfo = parent.getLayoutInfo()) == null || !layoutInfo.isPlaced()) ? DefaultFakeNodeBounds : parent.getBoundsInRoot())));
                        return;
                    } else {
                        if (id == i) {
                            mutableIntObjectMap.set(id, new SemanticsNodeWithAdjustedBounds(semanticsNode2, semanticsRegion2.getBounds()));
                            return;
                        }
                        return;
                    }
                }
                mutableIntObjectMap.set(id, new SemanticsNodeWithAdjustedBounds(semanticsNode2, semanticsRegion3.getBounds()));
                List<SemanticsNode> replacedChildren$ui = semanticsNode2.getReplacedChildren$ui();
                int size = replacedChildren$ui.size() - 1;
                while (-1 < size) {
                    if (!((Boolean) function1.invoke(replacedChildren$ui.get(size))).booleanValue()) {
                        getAllUncoveredSemanticsNodesToIntObjectMap$lambda$0$findAllSemanticNodesRecursive(semanticsRegion, semanticsNode, i, mutableIntObjectMap, function1, replacedChildren$ui.get(size), semanticsRegion3);
                    }
                    size--;
                    semanticsRegion3 = semanticsRegion2;
                }
                if (isImportantForAccessibility(semanticsNode2)) {
                    semanticsRegion.difference(intRectRoundToIntRect);
                }
            }
        }
    }

    public static final boolean isHidden(SemanticsNode semanticsNode) {
        if (semanticsNode.isTransparent$ui()) {
            return true;
        }
        SemanticsConfiguration unmergedConfig = semanticsNode.getUnmergedConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        return unmergedConfig.contains(semanticsProperties.getHideFromAccessibility()) || semanticsNode.getUnmergedConfig().contains(semanticsProperties.getInvisibleToUser());
    }

    public static /* synthetic */ void isHidden$annotations(SemanticsNode semanticsNode) {
    }

    public static final boolean isImportantForAccessibility(SemanticsNode semanticsNode) {
        if (isHidden(semanticsNode)) {
            return false;
        }
        return semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || semanticsNode.getUnmergedConfig().containsImportantForAccessibility$ui();
    }
}
