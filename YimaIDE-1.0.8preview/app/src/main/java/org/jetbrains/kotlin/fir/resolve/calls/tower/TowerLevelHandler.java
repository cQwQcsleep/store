package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevelHandler;", Argument.Delimiters.none, "<init>", "()V", "processResult", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/ProcessResult;", "handleLevel", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "info", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "explicitReceiverKind", "Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "group", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "towerLevel", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevel;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TowerLevelHandler {
    private ProcessResult processResult = ProcessResult.SCOPE_EMPTY;

    public final ProcessResult handleLevel(CandidateCollector collector, CandidateFactory candidateFactory, CallInfo info, ExplicitReceiverKind explicitReceiverKind, TowerGroup group, TowerLevel towerLevel) {
        collector.getClass();
        candidateFactory.getClass();
        info.getClass();
        explicitReceiverKind.getClass();
        group.getClass();
        towerLevel.getClass();
        this.processResult = ProcessResult.SCOPE_EMPTY;
        TowerLevelProcessor towerLevelProcessor = new TowerLevelProcessor(info, explicitReceiverKind, collector, candidateFactory, group);
        CallKind callKind = info.getCallKind();
        if (Intrinsics.areEqual(callKind, CallKind.VariableAccess.INSTANCE)) {
            this.processResult = this.processResult.plus(towerLevel.processPropertiesByName(info, towerLevelProcessor));
            if (!collector.getShouldStopResolve() && (towerLevel instanceof ScopeBasedTowerLevel)) {
                ScopeBasedTowerLevel scopeBasedTowerLevel = (ScopeBasedTowerLevel) towerLevel;
                if (!scopeBasedTowerLevel.areThereExtensionReceiverOptions()) {
                    this.processResult = this.processResult.plus(scopeBasedTowerLevel.processObjectsByName(info, towerLevelProcessor));
                }
            }
        } else if (Intrinsics.areEqual(callKind, CallKind.Function.INSTANCE) || Intrinsics.areEqual(callKind, CallKind.CollectionLiteral.INSTANCE)) {
            this.processResult = this.processResult.plus(towerLevel.processFunctionsByName(info, towerLevelProcessor));
        } else {
            if (!Intrinsics.areEqual(callKind, CallKind.CallableReference.INSTANCE)) {
                pe1.a("Unsupported call kind in tower resolver: ", info.getCallKind());
                return null;
            }
            ProcessResult processResultPlus = this.processResult.plus(towerLevel.processFunctionsByName(info, towerLevelProcessor));
            this.processResult = processResultPlus;
            this.processResult = processResultPlus.plus(towerLevel.processPropertiesByName(info, towerLevelProcessor));
        }
        return this.processResult;
    }
}
