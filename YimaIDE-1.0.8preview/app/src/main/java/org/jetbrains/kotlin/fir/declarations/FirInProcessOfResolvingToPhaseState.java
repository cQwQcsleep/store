package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007\u0082\u0001\u0003\n\u000b\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseState;", "Lorg/jetbrains/kotlin/fir/declarations/FirResolveState;", "<init>", "()V", "resolvingTo", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvingTo", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "resolvePhase", "getResolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToJumpingPhaseState;", "Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseStateWithBarrier;", "Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseStateWithoutBarrier;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirInProcessOfResolvingToPhaseState extends FirResolveState {
    private FirInProcessOfResolvingToPhaseState() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolveState
    public FirResolvePhase getResolvePhase() {
        return getResolvingTo().getPrevious();
    }

    public abstract FirResolvePhase getResolvingTo();

    public /* synthetic */ FirInProcessOfResolvingToPhaseState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
