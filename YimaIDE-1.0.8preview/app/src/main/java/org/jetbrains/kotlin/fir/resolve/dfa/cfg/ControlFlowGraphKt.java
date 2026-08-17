package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001c\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"previousNodeCount", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "getPreviousNodeCount", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)I", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowGraphKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int getPreviousNodeCount(CFGNode<?> cFGNode) {
        List<CFGNode<?>> previousNodes = cFGNode.getPreviousNodes();
        int i = 0;
        if ((previousNodes instanceof Collection) && previousNodes.isEmpty()) {
            return 0;
        }
        for (CFGNode<?> cFGNode2 : previousNodes) {
            if (Intrinsics.areEqual(cFGNode2.getOwner(), cFGNode.getOwner()) && !cFGNode.edgeFrom(cFGNode2).getKind().getIsBack() && (i = i + 1) < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return i;
    }
}
