package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseStateWithoutBarrier;", "Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseState;", "resolvingTo", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "getResolvingTo", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInProcessOfResolvingToPhaseStateWithoutBarrier extends FirInProcessOfResolvingToPhaseState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<FirInProcessOfResolvingToPhaseState> phases;
    private final FirResolvePhase resolvingTo;

    static {
        List listDrop = CollectionsKt.drop(FirResolvePhase.getEntries(), 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop, 10));
        Iterator it = listDrop.iterator();
        while (it.hasNext()) {
            arrayList.add(new FirInProcessOfResolvingToPhaseStateWithoutBarrier((FirResolvePhase) it.next()));
        }
        phases = arrayList;
    }

    private FirInProcessOfResolvingToPhaseStateWithoutBarrier(FirResolvePhase firResolvePhase) {
        super(null);
        this.resolvingTo = firResolvePhase;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirInProcessOfResolvingToPhaseState
    public FirResolvePhase getResolvingTo() {
        return this.resolvingTo;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirResolveState
    public String toString() {
        return "ResolvingTo(" + getResolvingTo() + ')';
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0086\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseStateWithoutBarrier$Companion;", Argument.Delimiters.none, "<init>", "()V", "phases", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirInProcessOfResolvingToPhaseState;", "invoke", "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirInProcessOfResolvingToPhaseState invoke(FirResolvePhase phase) {
            phase.getClass();
            FirResolvePhase firResolvePhase = FirResolvePhase.RAW_FIR;
            if (phase != firResolvePhase) {
                return (FirInProcessOfResolvingToPhaseState) FirInProcessOfResolvingToPhaseStateWithoutBarrier.phases.get(phase.ordinal() - 1);
            }
            wec.a("Cannot resolve to ", firResolvePhase, " as it's a first phase");
            return null;
        }

        private Companion() {
        }
    }
}
