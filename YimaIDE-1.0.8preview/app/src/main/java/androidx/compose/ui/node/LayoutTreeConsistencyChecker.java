package androidx.compose.ui.node;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\f\u0010\u0010\u001a\u00020\u000e*\u00020\u0003H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/node/LayoutTreeConsistencyChecker;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "relayoutNodes", "Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "postponedMeasureRequests", "", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;Ljava/util/List;)V", "assertConsistent", "", "isTreeConsistent", "", "node", "consistentLayoutState", "nodeToString", "", "logTree", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class LayoutTreeConsistencyChecker {
    public static final int $stable = 8;
    private final List<MeasureAndLayoutDelegate.PostponedRequest> postponedMeasureRequests;
    private final DepthSortedSetsForDifferentPasses relayoutNodes;
    private final LayoutNode root;

    public LayoutTreeConsistencyChecker(LayoutNode layoutNode, DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses, List<MeasureAndLayoutDelegate.PostponedRequest> list) {
        this.root = layoutNode;
        this.relayoutNodes = depthSortedSetsForDifferentPasses;
        this.postponedMeasureRequests = list;
    }

    private final boolean consistentLayoutState(LayoutNode layoutNode) {
        MeasureAndLayoutDelegate.PostponedRequest postponedRequest;
        LayoutNode parent$ui = layoutNode.getParent$ui();
        MeasureAndLayoutDelegate.PostponedRequest postponedRequest2 = null;
        LayoutNode.LayoutState layoutState$ui = parent$ui != null ? parent$ui.getLayoutState$ui() : null;
        if (layoutNode.isPlaced() || (layoutNode.getPlaceOrder$ui() != Integer.MAX_VALUE && parent$ui != null && parent$ui.isPlaced())) {
            if (layoutNode.getMeasurePending$ui()) {
                List<MeasureAndLayoutDelegate.PostponedRequest> list = this.postponedMeasureRequests;
                int size = list.size();
                int i = 0;
                while (true) {
                    if (i >= size) {
                        postponedRequest = null;
                        break;
                    }
                    postponedRequest = list.get(i);
                    MeasureAndLayoutDelegate.PostponedRequest postponedRequest3 = postponedRequest;
                    if (Intrinsics.areEqual(postponedRequest3.getNode(), layoutNode) && !postponedRequest3.getIsLookahead()) {
                        break;
                    }
                    i++;
                }
                if (postponedRequest != null) {
                    return true;
                }
            }
            if (layoutNode.getIsDeactivated()) {
                return true;
            }
            if (layoutNode.getMeasurePending$ui()) {
                return this.relayoutNodes.contains(layoutNode) || layoutNode.getLayoutState$ui() == LayoutNode.LayoutState.LookaheadMeasuring || (parent$ui != null && parent$ui.getMeasurePending$ui()) || ((parent$ui != null && parent$ui.getLookaheadMeasurePending$ui()) || layoutState$ui == LayoutNode.LayoutState.Measuring);
            }
            if (layoutNode.getLayoutPending$ui()) {
                if (!this.relayoutNodes.contains(layoutNode) && parent$ui != null && !parent$ui.getMeasurePending$ui() && !parent$ui.getLayoutPending$ui() && layoutState$ui != LayoutNode.LayoutState.Measuring && layoutState$ui != LayoutNode.LayoutState.LayingOut) {
                    List<MeasureAndLayoutDelegate.PostponedRequest> list2 = this.postponedMeasureRequests;
                    int size2 = list2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (!Intrinsics.areEqual(list2.get(i2).getNode(), layoutNode)) {
                        }
                    }
                    if (layoutNode.getLayoutState$ui() != LayoutNode.LayoutState.Measuring && layoutNode.getLayoutState$ui() != LayoutNode.LayoutState.LayingOut) {
                        return false;
                    }
                }
                return true;
            }
        }
        if (Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE)) {
            if (layoutNode.getLookaheadMeasurePending$ui()) {
                List<MeasureAndLayoutDelegate.PostponedRequest> list3 = this.postponedMeasureRequests;
                int size3 = list3.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    MeasureAndLayoutDelegate.PostponedRequest postponedRequest4 = list3.get(i3);
                    MeasureAndLayoutDelegate.PostponedRequest postponedRequest5 = postponedRequest4;
                    if (Intrinsics.areEqual(postponedRequest5.getNode(), layoutNode) && postponedRequest5.getIsLookahead()) {
                        postponedRequest2 = postponedRequest4;
                        break;
                    }
                }
                if (postponedRequest2 != null) {
                    return true;
                }
            }
            if (layoutNode.getLookaheadMeasurePending$ui()) {
                return this.relayoutNodes.contains(layoutNode, true) || (parent$ui != null && parent$ui.getLookaheadMeasurePending$ui()) || layoutState$ui == LayoutNode.LayoutState.LookaheadMeasuring || (parent$ui != null && parent$ui.getMeasurePending$ui() && Intrinsics.areEqual(layoutNode.getLookaheadRoot(), layoutNode));
            }
            if (layoutNode.getLookaheadLayoutPending$ui() && !this.relayoutNodes.contains(layoutNode, true) && parent$ui != null && !parent$ui.getLookaheadMeasurePending$ui() && !parent$ui.getLookaheadLayoutPending$ui() && layoutState$ui != LayoutNode.LayoutState.LookaheadMeasuring && layoutState$ui != LayoutNode.LayoutState.LookaheadLayingOut && (!parent$ui.getLayoutPending$ui() || !Intrinsics.areEqual(layoutNode.getLookaheadRoot(), layoutNode))) {
                return false;
            }
        }
        return true;
    }

    private final boolean isTreeConsistent(LayoutNode node) {
        if (!consistentLayoutState(node)) {
            return false;
        }
        List<LayoutNode> children$ui = node.getChildren$ui();
        int size = children$ui.size();
        for (int i = 0; i < size; i++) {
            if (!isTreeConsistent(children$ui.get(i))) {
                return false;
            }
        }
        return true;
    }

    private final String logTree() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tree state:\n");
        logTree$printSubTree(this, sb, this.root, 0);
        return sb.toString();
    }

    private static final void logTree$printSubTree(LayoutTreeConsistencyChecker layoutTreeConsistencyChecker, StringBuilder sb, LayoutNode layoutNode, int i) {
        String strNodeToString = layoutTreeConsistencyChecker.nodeToString(layoutNode);
        if (strNodeToString.length() > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append("..");
            }
            sb.append(strNodeToString);
            sb.append('\n');
            i++;
        }
        List<LayoutNode> children$ui = layoutNode.getChildren$ui();
        int size = children$ui.size();
        for (int i3 = 0; i3 < size; i3++) {
            logTree$printSubTree(layoutTreeConsistencyChecker, sb, children$ui.get(i3), i);
        }
    }

    private final String nodeToString(LayoutNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node);
        sb.append("[" + node.getLayoutState$ui() + ']');
        if (!node.isPlaced()) {
            sb.append("[!isPlaced]");
        }
        sb.append("[measuredByParent=" + node.getMeasuredByParent$ui() + ']');
        if (!consistentLayoutState(node)) {
            sb.append("[INCONSISTENT]");
        }
        return sb.toString();
    }

    public final void assertConsistent() {
        if (isTreeConsistent(this.root)) {
            return;
        }
        System.out.println((Object) logTree());
        k2d.a("Inconsistency found!");
    }
}
