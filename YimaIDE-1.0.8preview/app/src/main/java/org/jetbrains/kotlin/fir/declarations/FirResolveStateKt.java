package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElementWithResolveState;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\"\u001b\u0010\u0003\u001a\u00020\u0002*\u00020\u00048F¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"asResolveState", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedToPhaseState;", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;", "getResolvePhase$annotations", "(Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;)V", "getResolvePhase", "(Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;)Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolveStateKt {
    public static final FirResolvedToPhaseState asResolveState(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        return FirResolvedToPhaseState.INSTANCE.invoke(firResolvePhase);
    }

    public static final FirResolvePhase getResolvePhase(FirElementWithResolveState firElementWithResolveState) {
        FirResolvePhase resolvePhase;
        FirResolvePhase firResolvePhase;
        firElementWithResolveState.getClass();
        if (!(firElementWithResolveState instanceof FirSyntheticProperty)) {
            return firElementWithResolveState instanceof FirSyntheticPropertyAccessor ? getResolvePhase(((FirSyntheticPropertyAccessor) firElementWithResolveState).getDelegate()) : firElementWithResolveState.getResolveState().getResolvePhase();
        }
        FirSyntheticProperty firSyntheticProperty = (FirSyntheticProperty) firElementWithResolveState;
        FirSyntheticPropertyAccessor setter = firSyntheticProperty.getSetter();
        return (setter == null || (resolvePhase = getResolvePhase(setter)) == null || (firResolvePhase = (FirResolvePhase) ComparisonsKt.minOf(resolvePhase, getResolvePhase(firSyntheticProperty.getGetter()))) == null) ? getResolvePhase(firSyntheticProperty.getGetter()) : firResolvePhase;
    }

    public static /* synthetic */ void getResolvePhase$annotations(FirElementWithResolveState firElementWithResolveState) {
    }
}
