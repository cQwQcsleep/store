package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.contracts.FirContractResolveTransformerAdapterKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"runContractAndBodiesResolutionForLocalClass", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "localClassesNavigationInfo", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;)Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirImplicitBodyResolveKt {
    public static final <F extends FirClassLikeDeclaration> F runContractAndBodiesResolutionForLocalClass(F f, FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents, ResolutionMode resolutionMode, LocalClassesNavigationInfo localClassesNavigationInfo) {
        Map<FirCallableDeclaration, List<FirClassLikeDeclaration>> mapEmptyMap;
        ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession;
        f.getClass();
        bodyResolveTransformerComponents.getClass();
        resolutionMode.getClass();
        localClassesNavigationInfo.getClass();
        ReturnTypeCalculator returnTypeCalculator = bodyResolveTransformerComponents.getContext().getReturnTypeCalculator();
        ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump = returnTypeCalculator instanceof ReturnTypeCalculatorWithJump ? (ReturnTypeCalculatorWithJump) returnTypeCalculator : null;
        if (returnTypeCalculatorWithJump == null || (mapEmptyMap = returnTypeCalculatorWithJump.getDesignationMapForLocalClasses()) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        }
        Pair pair = TuplesKt.to(MapsKt.plus(mapEmptyMap, localClassesNavigationInfo.getDesignationMap()), SetsKt.plus(SetsKt.plus(localClassesNavigationInfo.getParentForClass().keySet(), f), bodyResolveTransformerComponents.getContext().getTargetedLocalClasses()));
        Map map = (Map) pair.component1();
        Set<? extends FirClassLikeDeclaration> set = (Set) pair.component2();
        if (returnTypeCalculatorWithJump == null || (implicitBodyResolveComputationSession = returnTypeCalculatorWithJump.getImplicitBodyResolveComputationSession()) == null) {
            implicitBodyResolveComputationSession = new ImplicitBodyResolveComputationSession();
        }
        ImplicitBodyResolveComputationSession implicitBodyResolveComputationSession2 = implicitBodyResolveComputationSession;
        ReturnTypeCalculatorWithJump returnTypeCalculatorWithJump2 = new ReturnTypeCalculatorWithJump(bodyResolveTransformerComponents.getScopeSession(), implicitBodyResolveComputationSession2, map, bodyResolveTransformerComponents.getTransformer(), bodyResolveTransformerComponents.getContext());
        BodyResolveContext context = bodyResolveTransformerComponents.getContext();
        ReturnTypeCalculator returnTypeCalculator2 = context.getReturnTypeCalculator();
        Set<FirClassLikeDeclaration> targetedLocalClasses = context.getTargetedLocalClasses();
        try {
            context.setReturnTypeCalculator(returnTypeCalculatorWithJump2);
            context.setTargetedLocalClasses(set);
            FirContractResolveTransformerAdapterKt.runContractResolveForLocalClass(f, bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getScopeSession(), bodyResolveTransformerComponents.getContext());
            return (F) f.transform(new FirImplicitAwareBodyResolveTransformer(bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getScopeSession(), implicitBodyResolveComputationSession2, FirResolvePhase.BODY_RESOLVE, false, returnTypeCalculatorWithJump2, bodyResolveTransformerComponents.getContext()), resolutionMode);
        } finally {
            context.setReturnTypeCalculator(returnTypeCalculator2);
            context.setTargetedLocalClasses(targetedLocalClasses);
        }
    }
}
