package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ5\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u00142\u0006\u0010\u0015\u001a\u0002H\u0012H\u0016¢\u0006\u0002\u0010\u0016J \u0010\u0017\u001a\u00020\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017b\u0002\b\u001dR\u0014\u0010\u0005\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionExpressionNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "owner", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "fir", "level", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;I)V", "getFir", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "subGraphs", Argument.Delimiters.none, "getSubGraphs", "()Ljava/util/List;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "copyData", Argument.Delimiters.none, "from", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "mapper", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnonymousFunctionExpressionNode extends CFGNodeWithSubgraphs<FirAnonymousFunctionExpression> {
    private final FirAnonymousFunctionExpression fir;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnonymousFunctionExpressionNode(ControlFlowGraph controlFlowGraph, FirAnonymousFunctionExpression firAnonymousFunctionExpression, int i) {
        super(controlFlowGraph, i, null);
        controlFlowGraph.getClass();
        firAnonymousFunctionExpression.getClass();
        this.fir = firAnonymousFunctionExpression;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public <R, D> R accept(ControlFlowGraphVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitAnonymousFunctionExpressionNode(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    @CfgInternals
    public void copyData(CFGNode<?> from, ControlFlowNodeMapper mapper) {
        from.getClass();
        mapper.getClass();
        super.copyData(from, mapper);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs
    public List<ControlFlowGraph> getSubGraphs() {
        FirControlFlowGraphReference controlFlowGraphReference = getFir().getAnonymousFunction().getControlFlowGraphReference();
        return CollectionsKt.listOfNotNull(controlFlowGraphReference != null ? FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference) : null);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public FirAnonymousFunctionExpression getFir() {
        return this.fir;
    }
}
