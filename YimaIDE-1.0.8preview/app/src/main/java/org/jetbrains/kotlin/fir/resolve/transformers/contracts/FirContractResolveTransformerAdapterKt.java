package org.jetbrains.kotlin.fir.resolve.transformers.contracts;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculatorForFullBodyResolve;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\t\u001a1\u0010\n\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u000b*\u0002H\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\b¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"runContractResolveForLocalClass", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "context", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "runContractResolveForFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "outerBodyResolveContext", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContractResolveTransformerAdapterKt {
    public static final <F extends FirFunction> F runContractResolveForFunction(F f, FirSession firSession, ScopeSession scopeSession, BodyResolveContext bodyResolveContext) {
        f.getClass();
        firSession.getClass();
        scopeSession.getClass();
        bodyResolveContext.getClass();
        return (F) FirTransformerUtilKt.transformSingle(f, new FirContractResolveTransformer(firSession, scopeSession, bodyResolveContext), ResolutionMode.ContextIndependent.INSTANCE);
    }

    public static final <F extends FirClassLikeDeclaration> F runContractResolveForLocalClass(F f, FirSession firSession, ScopeSession scopeSession, BodyResolveContext bodyResolveContext) {
        f.getClass();
        firSession.getClass();
        scopeSession.getClass();
        bodyResolveContext.getClass();
        ReturnTypeCalculatorForFullBodyResolve contract = ReturnTypeCalculatorForFullBodyResolve.INSTANCE.getContract();
        ReturnTypeCalculator returnTypeCalculator = bodyResolveContext.getReturnTypeCalculator();
        try {
            bodyResolveContext.setReturnTypeCalculator(contract);
            return (F) FirTransformerUtilKt.transformSingle(f, new FirContractResolveTransformer(firSession, scopeSession, bodyResolveContext), ResolutionMode.ContextIndependent.INSTANCE);
        } finally {
            bodyResolveContext.setReturnTypeCalculator(returnTypeCalculator);
        }
    }
}
