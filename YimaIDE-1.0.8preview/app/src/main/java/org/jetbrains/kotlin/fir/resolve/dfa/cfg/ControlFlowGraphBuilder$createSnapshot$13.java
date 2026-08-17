package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class ControlFlowGraphBuilder$createSnapshot$13 extends FunctionReferenceImpl implements Function1<TryExpressionExitNode, TryExpressionExitNode> {
    public ControlFlowGraphBuilder$createSnapshot$13(Object obj) {
        super(1, obj, ControlFlowGraphCopier.class, "get", "get(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", 0);
    }

    public final TryExpressionExitNode invoke(TryExpressionExitNode tryExpressionExitNode) {
        tryExpressionExitNode.getClass();
        return (TryExpressionExitNode) ((ControlFlowGraphCopier) ((CallableReference) this).receiver).get(tryExpressionExitNode);
    }
}
