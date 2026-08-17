package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0019\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ \u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0017b\u0002\b\u0010R'\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b@\u0007X\u0086.\u0082\u0001\u0002\b\u0010¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0082\u0001\u0004\u0017\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithExplicitSubgraphs;", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;", "owner", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "level", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;I)V", "subGraphs", Argument.Delimiters.none, "getSubGraphs", "()Ljava/util/List;", "setSubGraphs", "(Ljava/util/List;)V", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "copyData", Argument.Delimiters.none, "from", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "mapper", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ClassExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptEnterNode;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CFGNodeWithExplicitSubgraphs<E extends FirElement> extends CFGNodeWithSubgraphs<E> {
    public List<ControlFlowGraph> subGraphs;

    private CFGNodeWithExplicitSubgraphs(ControlFlowGraph controlFlowGraph, int i) {
        super(controlFlowGraph, i, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    @CfgInternals
    public void copyData(CFGNode<?> from, ControlFlowNodeMapper mapper) throws UninitializedPropertyAccessException {
        from.getClass();
        mapper.getClass();
        CFGNodeWithExplicitSubgraphs cFGNodeWithExplicitSubgraphs = (CFGNodeWithExplicitSubgraphs) from;
        super.copyData(from, mapper);
        if (cFGNodeWithExplicitSubgraphs.subGraphs != null) {
            List<ControlFlowGraph> subGraphs = cFGNodeWithExplicitSubgraphs.getSubGraphs();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subGraphs, 10));
            Iterator<T> it = subGraphs.iterator();
            while (it.hasNext()) {
                arrayList.add(mapper.get((ControlFlowGraph) it.next()));
            }
            setSubGraphs(arrayList);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs
    public final List<ControlFlowGraph> getSubGraphs() throws UninitializedPropertyAccessException {
        List<ControlFlowGraph> list = this.subGraphs;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("subGraphs");
        return null;
    }

    @CfgInternals
    public final void setSubGraphs(List<ControlFlowGraph> list) {
        list.getClass();
        this.subGraphs = list;
    }

    public /* synthetic */ CFGNodeWithExplicitSubgraphs(ControlFlowGraph controlFlowGraph, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(controlFlowGraph, i);
    }
}
