package org.jetbrains.kotlin.fir.declarations;

import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseStateWithBarrier;", "Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseState;", "resolvingTo", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "getResolvingTo", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "barrier", "Ljava/util/concurrent/CountDownLatch;", "getBarrier", "()Ljava/util/concurrent/CountDownLatch;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInProcessOfResolvingToPhaseStateWithBarrier extends FirInProcessOfResolvingToPhaseState {
    private final CountDownLatch barrier;
    private final FirResolvePhase resolvingTo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirInProcessOfResolvingToPhaseStateWithBarrier(FirResolvePhase firResolvePhase) {
        super(null);
        firResolvePhase.getClass();
        this.resolvingTo = firResolvePhase;
        this.barrier = new CountDownLatch(1);
        FirResolvePhase resolvingTo = getResolvingTo();
        FirResolvePhase firResolvePhase2 = FirResolvePhase.RAW_FIR;
        if (resolvingTo != firResolvePhase2) {
            return;
        }
        wec.a("Cannot resolve to ", firResolvePhase2, " as it's a first phase");
        throw null;
    }

    public final CountDownLatch getBarrier() {
        return this.barrier;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirInProcessOfResolvingToPhaseState
    public FirResolvePhase getResolvingTo() {
        return this.resolvingTo;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolveState
    public String toString() {
        return "ResolvingToWithBarrier(" + getResolvingTo() + ')';
    }
}
