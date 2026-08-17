package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\b\u001a\u00020\t\"\u001d\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u001d\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0001*\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"#\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b*\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"firstPreviousNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "getFirstPreviousNode", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "lastPreviousNode", "getLastPreviousNode", "usedInDfa", Argument.Delimiters.none, "edge", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "previousLiveNodes", Argument.Delimiters.none, "getPreviousLiveNodes", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Ljava/util/List;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CFGNodeKt {
    public static final CFGNode<?> getFirstPreviousNode(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        return cFGNode.getPreviousNodes().get(0);
    }

    public static final CFGNode<?> getLastPreviousNode(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        return (CFGNode) CollectionsKt.last(cFGNode.getPreviousNodes());
    }

    public static final List<CFGNode<?>> getPreviousLiveNodes(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        if (cFGNode.getIsDead()) {
            return cFGNode.getPreviousNodes();
        }
        List<CFGNode<?>> previousNodes = cFGNode.getPreviousNodes();
        ArrayList arrayList = new ArrayList();
        for (Object obj : previousNodes) {
            if (!((CFGNode) obj).getIsDead()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final boolean usedInDfa(CFGNode<?> cFGNode, Edge edge) {
        cFGNode.getClass();
        edge.getClass();
        return cFGNode.getIsDead() ? edge.getKind().getUsedInDeadDfa() : edge.getKind().getUsedInDfa();
    }
}
