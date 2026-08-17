package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\f\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000eH\u0016R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/InvokeReceiverResolveTask;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirTowerResolveTask;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "manager", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;", "towerDataElementsForName", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "onSuccessfulLevel", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;Lkotlin/jvm/functions/Function1;)V", "interceptTowerGroup", "towerGroup", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class InvokeReceiverResolveTask extends FirTowerResolveTask {
    private final Function1<TowerGroup, Unit> onSuccessfulLevel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public InvokeReceiverResolveTask(BodyResolveComponents bodyResolveComponents, TowerResolveManager towerResolveManager, TowerDataElementsForName towerDataElementsForName, CandidateCollector candidateCollector, CandidateFactory candidateFactory, Function1<? super TowerGroup, Unit> function1) {
        super(bodyResolveComponents, towerResolveManager, towerDataElementsForName, candidateCollector, candidateFactory);
        bodyResolveComponents.getClass();
        towerResolveManager.getClass();
        towerDataElementsForName.getClass();
        candidateCollector.getClass();
        candidateFactory.getClass();
        function1.getClass();
        this.onSuccessfulLevel = function1;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask
    public TowerGroup interceptTowerGroup(TowerGroup towerGroup) {
        towerGroup.getClass();
        return towerGroup.InvokeResolvePriority(InvokeResolvePriority.INVOKE_RECEIVER);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.tower.FirBaseTowerResolveTask
    public void onSuccessfulLevel(TowerGroup towerGroup) {
        towerGroup.getClass();
        this.onSuccessfulLevel.invoke(towerGroup);
    }
}
