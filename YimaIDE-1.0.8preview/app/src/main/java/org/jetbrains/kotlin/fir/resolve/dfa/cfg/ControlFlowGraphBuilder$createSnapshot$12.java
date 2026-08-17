package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class ControlFlowGraphBuilder$createSnapshot$12 extends FunctionReferenceImpl implements Function1<WhenExitNode, WhenExitNode> {
    public ControlFlowGraphBuilder$createSnapshot$12(Object obj) {
        super(1, obj, ControlFlowGraphCopier.class, "get", "get(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", 0);
    }

    public final WhenExitNode invoke(WhenExitNode whenExitNode) {
        whenExitNode.getClass();
        return (WhenExitNode) ((ControlFlowGraphCopier) ((CallableReference) this).receiver).get(whenExitNode);
    }
}
