package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0001\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ5\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00140\u00162\u0006\u0010\u0017\u001a\u0002H\u0014H\u0016¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0006\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitNodeMarker;", "owner", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "fir", "explicitReceiverExitNode", "level", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;I)V", "getFir", "()Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "getExplicitReceiverExitNode", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "setExplicitReceiverExitNode", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)V", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FunctionCallArgumentsExitNode extends CFGNode<FirFunctionCall> implements ExitNodeMarker {
    private CFGNode<?> explicitReceiverExitNode;
    private final FirFunctionCall fir;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionCallArgumentsExitNode(ControlFlowGraph controlFlowGraph, FirFunctionCall firFunctionCall, CFGNode<?> cFGNode, int i) {
        super(controlFlowGraph, i, null);
        controlFlowGraph.getClass();
        firFunctionCall.getClass();
        cFGNode.getClass();
        this.fir = firFunctionCall;
        this.explicitReceiverExitNode = cFGNode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public <R, D> R accept(ControlFlowGraphVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitFunctionCallArgumentsExitNode(this, data);
    }

    public final CFGNode<?> getExplicitReceiverExitNode() {
        return this.explicitReceiverExitNode;
    }

    public final void setExplicitReceiverExitNode(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        this.explicitReceiverExitNode = cFGNode;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public FirFunctionCall getFir() {
        return this.fir;
    }
}
