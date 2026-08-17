package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.Edge;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeKind;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\b\u0004\u001az\u0010\u0007\u001aJ\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012<\u0012:\u0012\u0004\u0012\u00020\n\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\tj\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f`\r0\tj\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f`\u000e0\b\"\b\b\u0000\u0010\u000b*\u00020\u000f\"\b\b\u0001\u0010\f*\u00020\u000f*\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\u0012\u001a\u0084\u0001\u0010\u0013\u001a\u00020\u0014\"\b\b\u0000\u0010\u000b*\u00020\u000f\"\b\b\u0001\u0010\f*\u00020\u000f*\u00020\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\u00122N\u0010\u0015\u001aJ\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012<\u0012:\u0012\u0004\u0012\u00020\n\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\tj\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f`\r0\tj\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f`\u000e0\u0016H\u0002\u001aÔ\u0001\u0010\u0017\u001a>\u0012\u0004\u0012\u00020\n\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\tj\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f`\r\u0018\u00010\tj\u0010\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f\u0018\u0001`\u000e\"\b\b\u0000\u0010\u000b*\u00020\u000f\"\b\b\u0001\u0010\f*\u00020\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\u00122\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00022\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00022N\u0010\u0015\u001aJ\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012<\u0012:\u0012\u0004\u0012\u00020\n\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\tj\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f`\r0\tj\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f`\u000e0\bH\u0002\"#\u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"#\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u001a"}, d2 = {"previousCfgNodes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "getPreviousCfgNodes", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Ljava/util/List;", "followingCfgNodes", "getFollowingCfgNodes", "traverseToFixedPoint", Argument.Delimiters.none, "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "K", "V", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "visitor", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowGraphVisitor;", "traverseOnce", Argument.Delimiters.none, "nodeMap", Argument.Delimiters.none, "edgeData", "source", "node", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CfgTraverserKt {
    private static final <K, V> PersistentMap<EdgeLabel, PersistentMap<K, V>> edgeData(PathAwareControlFlowGraphVisitor<K, V> pathAwareControlFlowGraphVisitor, CFGNode<?> cFGNode, CFGNode<?> cFGNode2, Map<CFGNode<?>, ? extends PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>>> map) {
        PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>> persistentMap;
        CfgTraverseDirection direction = pathAwareControlFlowGraphVisitor.getDirection();
        Edge edge = direction.edge(cFGNode, cFGNode2);
        if (direction.isUsedInCfg(cFGNode, cFGNode2, edge) && (persistentMap = map.get(cFGNode)) != null) {
            return pathAwareControlFlowGraphVisitor.visitEdge(cFGNode, cFGNode2, edge, persistentMap);
        }
        return null;
    }

    public static final List<CFGNode<?>> getFollowingCfgNodes(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        List<CFGNode<?>> followingNodes = cFGNode.getFollowingNodes();
        ArrayList arrayList = new ArrayList();
        for (Object obj : followingNodes) {
            CFGNode cFGNode2 = (CFGNode) obj;
            EdgeKind kind = cFGNode2.edgeFrom(cFGNode).getKind();
            if (kind.getUsedInCfa() && (cFGNode2.getIsDead() || !kind.getIsDead())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<CFGNode<?>> getPreviousCfgNodes(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        List<CFGNode<?>> previousNodes = cFGNode.getPreviousNodes();
        ArrayList arrayList = new ArrayList();
        for (Object obj : previousNodes) {
            EdgeKind kind = cFGNode.edgeFrom((CFGNode) obj).getKind();
            if (kind.getUsedInCfa() && (cFGNode.getIsDead() || !kind.getIsDead())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private static final <K, V> boolean traverseOnce(ControlFlowGraph controlFlowGraph, PathAwareControlFlowGraphVisitor<K, V> pathAwareControlFlowGraphVisitor, Map<CFGNode<?>, PersistentMap<EdgeLabel, PersistentMap<K, V>>> map) {
        PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>> persistentMap;
        CfgTraverseDirection direction = pathAwareControlFlowGraphVisitor.getDirection();
        boolean z = false;
        for (CFGNode<?> cFGNode : direction.nodes(controlFlowGraph)) {
            List<CFGNode<?>> listPrevious = direction.previous(cFGNode);
            ArrayList arrayList = new ArrayList();
            for (Object obj : listPrevious) {
                CFGNode<?> cFGNode2 = (CFGNode) obj;
                if (direction.isUsedInCfg(cFGNode2, cFGNode, direction.edge(cFGNode2, cFGNode))) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                PersistentMap persistentMapEdgeData = edgeData(pathAwareControlFlowGraphVisitor, (CFGNode) it.next(), cFGNode, map);
                if (persistentMapEdgeData != null) {
                    arrayList2.add(persistentMapEdgeData);
                }
            }
            Iterator it2 = arrayList2.iterator();
            if (it2.hasNext()) {
                PersistentMap<EdgeLabel, PersistentMap<K, V>> persistentMapMergePathAwareInfo = (PersistentMap<EdgeLabel, PersistentMap<K, V>>) it2.next();
                while (it2.hasNext()) {
                    persistentMapMergePathAwareInfo = pathAwareControlFlowGraphVisitor.mergePathAwareInfo(persistentMapMergePathAwareInfo, (PersistentMap) it2.next(), cFGNode);
                }
                persistentMap = persistentMapMergePathAwareInfo;
            } else {
                persistentMap = null;
            }
            PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>> persistentMapEmptyNormalPathInfo = persistentMap;
            if (persistentMapEmptyNormalPathInfo == null) {
                persistentMapEmptyNormalPathInfo = PathAwareControlFlowGraphVisitorKt.emptyNormalPathInfo();
            }
            PersistentMap<EdgeLabel, PersistentMap<K, V>> persistentMap2 = (PersistentMap) cFGNode.accept(pathAwareControlFlowGraphVisitor, persistentMapEmptyNormalPathInfo);
            if (!Intrinsics.areEqual(persistentMap2, map.put(cFGNode, persistentMap2))) {
                z = true;
            }
            if (cFGNode instanceof CFGNodeWithSubgraphs) {
                CFGNodeWithSubgraphs<?> cFGNodeWithSubgraphs = (CFGNodeWithSubgraphs) cFGNode;
                for (ControlFlowGraph controlFlowGraph2 : cFGNodeWithSubgraphs.getSubGraphs()) {
                    z |= pathAwareControlFlowGraphVisitor.visitSubGraph(cFGNodeWithSubgraphs, controlFlowGraph2) && traverseOnce(controlFlowGraph2, pathAwareControlFlowGraphVisitor, map);
                }
            }
        }
        return z;
    }

    public static final <K, V> Map<CFGNode<?>, PersistentMap<EdgeLabel, PersistentMap<K, V>>> traverseToFixedPoint(ControlFlowGraph controlFlowGraph, PathAwareControlFlowGraphVisitor<K, V> pathAwareControlFlowGraphVisitor) {
        controlFlowGraph.getClass();
        pathAwareControlFlowGraphVisitor.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (traverseOnce(controlFlowGraph, pathAwareControlFlowGraphVisitor, linkedHashMap)) {
        }
        return linkedHashMap;
    }
}
