package org.jetbrains.kotlin.fir.declarations;

import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToJumpingPhaseState;", "Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseState;", "resolvingTo", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "getResolvingTo", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "latch", "Ljava/util/concurrent/CountDownLatch;", "getLatch", "()Ljava/util/concurrent/CountDownLatch;", "waitingFor", "getWaitingFor", "()Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToJumpingPhaseState;", "setWaitingFor", "(Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToJumpingPhaseState;)V", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInProcessOfResolvingToJumpingPhaseState extends FirInProcessOfResolvingToPhaseState {
    private final CountDownLatch latch;
    private final FirResolvePhase resolvingTo;
    private volatile FirInProcessOfResolvingToJumpingPhaseState waitingFor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirInProcessOfResolvingToJumpingPhaseState(FirResolvePhase firResolvePhase) {
        super(null);
        firResolvePhase.getClass();
        this.resolvingTo = firResolvePhase;
        this.latch = new CountDownLatch(1);
    }

    public final CountDownLatch getLatch() {
        return this.latch;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirInProcessOfResolvingToPhaseState
    public FirResolvePhase getResolvingTo() {
        return this.resolvingTo;
    }

    public final FirInProcessOfResolvingToJumpingPhaseState getWaitingFor() {
        return this.waitingFor;
    }

    public final void setWaitingFor(FirInProcessOfResolvingToJumpingPhaseState firInProcessOfResolvingToJumpingPhaseState) {
        this.waitingFor = firInProcessOfResolvingToJumpingPhaseState;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolveState
    public String toString() {
        return "ResolvingJumpingTo(" + getResolvingTo() + ')';
    }
}
