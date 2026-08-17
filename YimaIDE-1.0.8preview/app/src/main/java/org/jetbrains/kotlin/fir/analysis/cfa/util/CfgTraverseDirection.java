package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.Edge;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u001c\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00072\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\bJ\u001c\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00072\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\bJ\u001e\u0010\u000e\u001a\u00020\u000f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\b2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\bJ&\u0010\u0010\u001a\u00020\u00112\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\b2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u000e\u001a\u00020\u000fj\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/util/CfgTraverseDirection;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "Forward", "Backward", "nodes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "next", "node", "previous", "edge", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "isUsedInCfg", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum CfgTraverseDirection {
    Forward,
    Backward;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CfgTraverseDirection.values().length];
            try {
                iArr[CfgTraverseDirection.Forward.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CfgTraverseDirection.Backward.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<CfgTraverseDirection> getEntries() {
        return $ENTRIES;
    }

    public final Edge edge(CFGNode<?> previous, CFGNode<?> next) {
        previous.getClass();
        next.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return next.edgeFrom(previous);
        }
        if (i == 2) {
            return previous.edgeFrom(next);
        }
        bu8.a();
        return null;
    }

    public final boolean isUsedInCfg(CFGNode<?> previous, CFGNode<?> next, Edge edge) {
        boolean isDead;
        previous.getClass();
        next.getClass();
        edge.getClass();
        EdgeKind kind = edge.getKind();
        if (kind.getUsedInCfa()) {
            if (kind.getIsDead()) {
                int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
                if (i == 1) {
                    isDead = next.getIsDead();
                } else {
                    if (i != 2) {
                        bu8.a();
                        return false;
                    }
                    isDead = previous.getIsDead();
                }
                if (isDead) {
                }
            }
            return true;
        }
        return false;
    }

    public final List<CFGNode<?>> next(CFGNode<?> node) {
        node.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return node.getFollowingNodes();
        }
        if (i == 2) {
            return node.getPreviousNodes();
        }
        bu8.a();
        return null;
    }

    public final List<CFGNode<?>> nodes(ControlFlowGraph graph) {
        graph.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return graph.getNodes();
        }
        if (i == 2) {
            return CollectionsKt.asReversed(graph.getNodes());
        }
        bu8.a();
        return null;
    }

    public final List<CFGNode<?>> previous(CFGNode<?> node) {
        node.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return node.getPreviousNodes();
        }
        if (i == 2) {
            return node.getFollowingNodes();
        }
        bu8.a();
        return null;
    }
}
