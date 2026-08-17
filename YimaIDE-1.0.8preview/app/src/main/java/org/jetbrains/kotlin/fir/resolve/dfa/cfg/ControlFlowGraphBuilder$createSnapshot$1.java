package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class ControlFlowGraphBuilder$createSnapshot$1 extends FunctionReferenceImpl implements Function1<ControlFlowGraph, ControlFlowGraph> {
    public ControlFlowGraphBuilder$createSnapshot$1(Object obj) {
        super(1, obj, ControlFlowGraphCopier.class, "get", "get(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", 0);
    }

    public final ControlFlowGraph invoke(ControlFlowGraph controlFlowGraph) {
        controlFlowGraph.getClass();
        return ((ControlFlowGraphCopier) ((CallableReference) this).receiver).get(controlFlowGraph);
    }
}
