package org.jetbrains.kotlin.fir.resolve.calls.tower;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$invokeReceiverProcessor$1 implements Function1<TowerGroup, Unit> {
    final /* synthetic */ CandidateCollector $collector;
    final /* synthetic */ CallInfo $info;
    final /* synthetic */ boolean $invokeBuiltinExtensionMode;
    final /* synthetic */ FirInvokeResolveTowerExtension this$0;

    public FirInvokeResolveTowerExtension$enqueueInvokeReceiverTask$invokeReceiverProcessor$1(FirInvokeResolveTowerExtension firInvokeResolveTowerExtension, boolean z, CallInfo callInfo, CandidateCollector candidateCollector) {
        this.this$0 = firInvokeResolveTowerExtension;
        this.$invokeBuiltinExtensionMode = z;
        this.$info = callInfo;
        this.$collector = candidateCollector;
    }

    public final void invoke(TowerGroup towerGroup) {
        towerGroup.getClass();
        this.this$0.enqueueResolverTasksForInvokeReceiverCandidates(this.$invokeBuiltinExtensionMode, this.$info, towerGroup, this.$collector);
        List<ResolutionDiagnostic> listForwardedDiagnostics = this.$collector.forwardedDiagnostics();
        FirInvokeResolveTowerExtension firInvokeResolveTowerExtension = this.this$0;
        Iterator<T> it = listForwardedDiagnostics.iterator();
        while (it.hasNext()) {
            firInvokeResolveTowerExtension.candidateFactoriesAndCollectors.getResultCollector().addForwardedDiagnostic((ResolutionDiagnostic) it.next());
        }
        this.$collector.newDataSet();
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TowerGroup) obj);
        return Unit.INSTANCE;
    }
}
