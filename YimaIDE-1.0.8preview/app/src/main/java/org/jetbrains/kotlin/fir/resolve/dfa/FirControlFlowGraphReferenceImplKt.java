package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"controlFlowGraph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraph", "(Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirControlFlowGraphReferenceImplKt {
    public static final ControlFlowGraph getControlFlowGraph(FirControlFlowGraphReference firControlFlowGraphReference) {
        firControlFlowGraphReference.getClass();
        FirControlFlowGraphReferenceImpl firControlFlowGraphReferenceImpl = firControlFlowGraphReference instanceof FirControlFlowGraphReferenceImpl ? (FirControlFlowGraphReferenceImpl) firControlFlowGraphReference : null;
        if (firControlFlowGraphReferenceImpl != null) {
            return firControlFlowGraphReferenceImpl.getControlFlowGraph();
        }
        return null;
    }
}
