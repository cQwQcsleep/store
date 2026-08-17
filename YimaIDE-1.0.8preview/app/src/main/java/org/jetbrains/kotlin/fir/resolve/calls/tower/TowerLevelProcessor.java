package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJB\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevelProcessor;", Argument.Delimiters.none, "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "explicitReceiverKind", "Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "resultCollector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "group", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;)V", "getCallInfo", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "getExplicitReceiverKind", "()Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "getResultCollector", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "getCandidateFactory", "()Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "getGroup", "()Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "consumeCandidate", "Lorg/jetbrains/kotlin/resolve/calls/tower/CandidateApplicability;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "givenExtensionReceiver", "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "objectsByName", Argument.Delimiters.none, "isFromOriginalTypeInPresenceOfSmartCast", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TowerLevelProcessor {
    private final CallInfo callInfo;
    private final CandidateFactory candidateFactory;
    private final ExplicitReceiverKind explicitReceiverKind;
    private final TowerGroup group;
    private final CandidateCollector resultCollector;

    public TowerLevelProcessor(CallInfo callInfo, ExplicitReceiverKind explicitReceiverKind, CandidateCollector candidateCollector, CandidateFactory candidateFactory, TowerGroup towerGroup) {
        callInfo.getClass();
        explicitReceiverKind.getClass();
        candidateCollector.getClass();
        candidateFactory.getClass();
        towerGroup.getClass();
        this.callInfo = callInfo;
        this.explicitReceiverKind = explicitReceiverKind;
        this.resultCollector = candidateCollector;
        this.candidateFactory = candidateFactory;
        this.group = towerGroup;
    }

    public static /* synthetic */ CandidateApplicability consumeCandidate$default(TowerLevelProcessor towerLevelProcessor, FirBasedSymbol firBasedSymbol, FirExpression firExpression, FirExpression firExpression2, FirScope firScope, boolean z, boolean z2, int i, Object obj) {
        if ((i & 16) != 0) {
            z = false;
        }
        if ((i & 32) != 0) {
            z2 = false;
        }
        return towerLevelProcessor.consumeCandidate(firBasedSymbol, firExpression, firExpression2, firScope, z, z2);
    }

    public final CandidateApplicability consumeCandidate(FirBasedSymbol<?> symbol, FirExpression dispatchReceiver, FirExpression givenExtensionReceiver, FirScope scope, boolean objectsByName, boolean isFromOriginalTypeInPresenceOfSmartCast) {
        symbol.getClass();
        scope.getClass();
        return this.resultCollector.consumeCandidate(this.group, this.candidateFactory.createCandidate(this.callInfo, symbol, this.explicitReceiverKind, scope, dispatchReceiver, givenExtensionReceiver, objectsByName, isFromOriginalTypeInPresenceOfSmartCast), this.candidateFactory.getContext());
    }

    public final CallInfo getCallInfo() {
        return this.callInfo;
    }

    public final CandidateFactory getCandidateFactory() {
        return this.candidateFactory;
    }

    public final ExplicitReceiverKind getExplicitReceiverKind() {
        return this.explicitReceiverKind;
    }

    public final TowerGroup getGroup() {
        return this.group;
    }

    public final CandidateCollector getResultCollector() {
        return this.resultCollector;
    }
}
