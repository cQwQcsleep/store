package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B7\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0001\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0001\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ5\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0014\"\u0004\b\u0001\u0010\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00150\u00172\u0006\u0010\u0018\u001a\u0002H\u0015H\u0016¢\u0006\u0002\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\t\u001a\u0006\u0012\u0002\b\u00030\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitNodeMarker;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TailrecExitNodeMarker;", "owner", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "fir", "leftOperandNode", "rightOperandNode", "level", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;I)V", "getFir", "()Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "getLeftOperandNode", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "getRightOperandNode", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BooleanOperatorExitNode extends CFGNode<FirBooleanOperatorExpression> implements ExitNodeMarker, TailrecExitNodeMarker {
    private final FirBooleanOperatorExpression fir;
    private final CFGNode<?> leftOperandNode;
    private final CFGNode<?> rightOperandNode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BooleanOperatorExitNode(ControlFlowGraph controlFlowGraph, FirBooleanOperatorExpression firBooleanOperatorExpression, CFGNode<?> cFGNode, CFGNode<?> cFGNode2, int i) {
        super(controlFlowGraph, i, null);
        controlFlowGraph.getClass();
        firBooleanOperatorExpression.getClass();
        cFGNode.getClass();
        cFGNode2.getClass();
        this.fir = firBooleanOperatorExpression;
        this.leftOperandNode = cFGNode;
        this.rightOperandNode = cFGNode2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public <R, D> R accept(ControlFlowGraphVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitBooleanOperatorExitNode(this, data);
    }

    public final CFGNode<?> getLeftOperandNode() {
        return this.leftOperandNode;
    }

    public final CFGNode<?> getRightOperandNode() {
        return this.rightOperandNode;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode
    public FirBooleanOperatorExpression getFir() {
        return this.fir;
    }
}
