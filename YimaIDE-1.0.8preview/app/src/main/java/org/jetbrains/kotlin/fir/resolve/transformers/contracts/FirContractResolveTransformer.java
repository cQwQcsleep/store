package org.jetbrains.kotlin.fir.resolve.transformers.contracts;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00060\u000bR\u00020\u00018TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirContractResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirAbstractContractResolveTransformerDispatcher;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "outerBodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)V", "contractDeclarationsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirAbstractContractResolveTransformerDispatcher$FirDeclarationsContractResolveTransformer;", "getContractDeclarationsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/contracts/FirAbstractContractResolveTransformerDispatcher$FirDeclarationsContractResolveTransformer;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContractResolveTransformer extends FirAbstractContractResolveTransformerDispatcher {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirContractResolveTransformer(FirSession firSession, ScopeSession scopeSession, BodyResolveContext bodyResolveContext) {
        super(firSession, scopeSession, bodyResolveContext);
        firSession.getClass();
        scopeSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.contracts.FirAbstractContractResolveTransformerDispatcher
    public FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer getContractDeclarationsTransformer() {
        return new FirAbstractContractResolveTransformerDispatcher.FirDeclarationsContractResolveTransformer();
    }

    public /* synthetic */ FirContractResolveTransformer(FirSession firSession, ScopeSession scopeSession, BodyResolveContext bodyResolveContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, (i & 4) != 0 ? null : bodyResolveContext);
    }
}
