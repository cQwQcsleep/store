package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ5\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u00172\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0019J \u0010\u001a\u001a\u00020\u001b2\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017b\u0002\b R\u0014\u0010\u0005\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SplitPostponedLambdasNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "owner", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "fir", "lambdas", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "level", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Ljava/util/List;I)V", "getFir", "()Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getLambdas", "()Ljava/util/List;", "subGraphs", "getSubGraphs", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "copyData", Argument.Delimiters.none, "from", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "mapper", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SplitPostponedLambdasNode extends CFGNodeWithSubgraphs<FirStatement> {
    private final FirStatement fir;
    private final List<FirAnonymousFunction> lambdas;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SplitPostponedLambdasNode(ControlFlowGraph controlFlowGraph, FirStatement firStatement, List<? extends FirAnonymousFunction> list, int i) {
        super(controlFlowGraph, i, null);
        controlFlowGraph.getClass();
        firStatement.getClass();
        list.getClass();
        this.fir = firStatement;
        this.lambdas = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public <R, D> R accept(ControlFlowGraphVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitSplitPostponedLambdasNode(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    @CfgInternals
    public void copyData(CFGNode<?> from, ControlFlowNodeMapper mapper) {
        from.getClass();
        mapper.getClass();
        super.copyData(from, mapper);
    }

    public final List<FirAnonymousFunction> getLambdas() {
        return this.lambdas;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs
    public List<ControlFlowGraph> getSubGraphs() {
        List<FirAnonymousFunction> list = this.lambdas;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            FirControlFlowGraphReference controlFlowGraphReference = ((FirAnonymousFunction) it.next()).getControlFlowGraphReference();
            ControlFlowGraph controlFlowGraph = controlFlowGraphReference != null ? FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference) : null;
            if (controlFlowGraph != null) {
                arrayList.add(controlFlowGraph);
            }
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public FirStatement getFir() {
        return this.fir;
    }
}
