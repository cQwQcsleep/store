package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.Edge;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FinallyBlockExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.NormalPath;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PostponedPath;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.UncaughtExceptionPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022~\u0012<\u0012:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003`\u00070\u0005j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003`\b\u0012<\u0012:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003`\u00070\u0005j\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003`\b0\u0004B\u0011\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJx\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00072\"\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00072\"\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00072\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H&Jß\u0001\u0010\u000f\u001a:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00070\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\b2>\u0010\u0010\u001a:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00070\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\b2>\u0010\u0011\u001a:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00070\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\b2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0007b\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0014¢\u0006\u0002\b\u0014J\u001c\u0010\u0017\u001a\u00020\u00182\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J \u0001\u0010\u001c\u001a:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00070\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\b2\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u00132\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u001f\u001a\u00020 2>\u0010!\u001a:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00070\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\bH\u0016J\u008c\u0001\u0010\"\u001a:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00070\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\b2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132>\u0010!\u001a:\u0012\u0004\u0012\u00020\u0006\u0012 \u0012\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u00070\u0005j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\bH\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowGraphVisitor;", "K", Argument.Delimiters.none, "V", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", "direction", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/CfgTraverseDirection;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/cfa/util/CfgTraverseDirection;)V", "getDirection", "()Lorg/jetbrains/kotlin/fir/analysis/cfa/util/CfgTraverseDirection;", "mergeInfo", "a", "b", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "mergePathAwareInfo", "Lkotlin/jvm/JvmName;", ModuleXmlParser.NAME, "visitSubGraph", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "visitEdge", "from", "to", "metadata", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "data", "visitNode", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PathAwareControlFlowGraphVisitor<K, V> extends ControlFlowGraphVisitor<PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>>, PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>>> {
    private final CfgTraverseDirection direction;

    public /* synthetic */ PathAwareControlFlowGraphVisitor(CfgTraverseDirection cfgTraverseDirection, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CfgTraverseDirection.Forward : cfgTraverseDirection);
    }

    public final CfgTraverseDirection getDirection() {
        return this.direction;
    }

    public abstract PersistentMap<K, V> mergeInfo(PersistentMap<K, ? extends V> a, PersistentMap<K, ? extends V> b, CFGNode<?> node);

    /* JADX WARN: Multi-variable type inference failed */
    public final PersistentMap<EdgeLabel, PersistentMap<K, V>> mergePathAwareInfo(PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>> a, PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>> b, CFGNode<?> node) {
        a.getClass();
        b.getClass();
        node.getClass();
        PersistentMap.Builder builder = a.builder();
        Iterator<T> it = b.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object key2 = entry.getKey();
            Object value = entry.getValue();
            Object obj = a.get(key2);
            if (obj != null) {
                PersistentMap<K, V> persistentMapMergeInfo = mergeInfo((PersistentMap) obj, (PersistentMap) value, node);
                if (persistentMapMergeInfo != null) {
                    value = persistentMapMergeInfo;
                }
            }
            builder.put(key, value);
        }
        return builder.build();
    }

    public PersistentMap<EdgeLabel, PersistentMap<K, V>> visitEdge(CFGNode<?> from, CFGNode<?> to, Edge metadata, PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>> data) {
        from.getClass();
        to.getClass();
        metadata.getClass();
        data.getClass();
        EdgeLabel label = metadata.getLabel();
        if (from instanceof FinallyBlockExitNode) {
            if (!Intrinsics.areEqual(label, UncaughtExceptionPath.INSTANCE)) {
                PersistentMap persistentMap = (PersistentMap) data.get(label);
                return persistentMap == null ? ExtensionsKt.persistentMapOf() : ExtensionsKt.persistentMapOf(new Pair[]{TuplesKt.to(NormalPath.INSTANCE, persistentMap)});
            }
            PersistentMap.Builder builder = data.builder();
            Iterator<CFGNode<?>> it = this.direction.next(from).iterator();
            while (it.hasNext()) {
                EdgeLabel label2 = this.direction.edge(from, it.next()).getLabel();
                if (!Intrinsics.areEqual(label2, UncaughtExceptionPath.INSTANCE)) {
                    builder.remove(label2);
                }
            }
            return builder.build();
        }
        if (Intrinsics.areEqual(label, NormalPath.INSTANCE) || Intrinsics.areEqual(label, PostponedPath.INSTANCE) || data.isEmpty()) {
            return data;
        }
        Iterator<T> it2 = data.values().iterator();
        if (!it2.hasNext()) {
            c41.a("Empty collection can't be reduced.");
            return null;
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            next = mergeInfo((PersistentMap) next, (PersistentMap) it2.next(), to);
        }
        return ExtensionsKt.persistentMapOf(new Pair[]{TuplesKt.to(label, next)});
    }

    public PersistentMap<EdgeLabel, PersistentMap<K, V>> visitNode(CFGNode<?> node, PersistentMap<EdgeLabel, ? extends PersistentMap<K, ? extends V>> data) {
        node.getClass();
        data.getClass();
        return data;
    }

    public boolean visitSubGraph(CFGNodeWithSubgraphs<?> node, ControlFlowGraph graph) {
        node.getClass();
        graph.getClass();
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public /* bridge */ /* synthetic */ Object visitNode(CFGNode cFGNode, Object obj) {
        return visitNode((CFGNode<?>) cFGNode, (PersistentMap) obj);
    }

    public PathAwareControlFlowGraphVisitor(CfgTraverseDirection cfgTraverseDirection) {
        cfgTraverseDirection.getClass();
        this.direction = cfgTraverseDirection;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PathAwareControlFlowGraphVisitor() {
        CfgTraverseDirection cfgTraverseDirection = null;
        this(cfgTraverseDirection, 1, cfgTraverseDirection);
    }
}
