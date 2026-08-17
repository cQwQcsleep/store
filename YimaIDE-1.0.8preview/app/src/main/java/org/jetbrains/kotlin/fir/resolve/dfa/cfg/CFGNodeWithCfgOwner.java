package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0019\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ \u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0017b\u0002\b\u0014R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0001\u0005\u0015\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithCfgOwner;", "E", "Lorg/jetbrains/kotlin/fir/declarations/FirControlFlowGraphOwner;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;", "owner", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "level", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;I)V", "subGraphs", Argument.Delimiters.none, "getSubGraphs", "()Ljava/util/List;", "copyData", Argument.Delimiters.none, "from", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "mapper", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterValueParameterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalClassExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LocalFunctionDeclarationNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationEnterNode;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CFGNodeWithCfgOwner<E extends FirControlFlowGraphOwner> extends CFGNodeWithSubgraphs<E> {
    private CFGNodeWithCfgOwner(ControlFlowGraph controlFlowGraph, int i) {
        super(controlFlowGraph, i, null);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    @CfgInternals
    public void copyData(CFGNode<?> from, ControlFlowNodeMapper mapper) {
        from.getClass();
        mapper.getClass();
        super.copyData(from, mapper);
        Iterator<T> it = getSubGraphs().iterator();
        while (it.hasNext()) {
            mapper.get((ControlFlowGraph) it.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs
    public final List<ControlFlowGraph> getSubGraphs() {
        FirControlFlowGraphReference controlFlowGraphReference = getFir().getControlFlowGraphReference();
        return CollectionsKt.listOfNotNull(controlFlowGraphReference != null ? FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference) : null);
    }

    public /* synthetic */ CFGNodeWithCfgOwner(ControlFlowGraph controlFlowGraph, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(controlFlowGraph, i);
    }
}
