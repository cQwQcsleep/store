package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponentsKt;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.transformers.FirStatusResolveTransformerKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypesResolutionKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirTypeResolveTransformerKt;
import org.jetbrains.kotlin.fir.resolve.transformers.plugin.FirCompanionGenerationProcessorKt;
import org.jetbrains.kotlin.fir.resolve.transformers.plugin.FirCompilerRequiredAnnotationsResolveTransformerKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a)\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"runAllPhasesForLocalClassLikeDeclarations", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;)Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LocalClassesResolutionKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final <F extends FirClassLikeDeclaration> F runAllPhasesForLocalClassLikeDeclarations(F f, FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents, ResolutionMode resolutionMode) throws KotlinIllegalArgumentExceptionWithAttachments {
        f.getClass();
        bodyResolveTransformerComponents.getClass();
        resolutionMode.getClass();
        if (f.getStatus() instanceof FirResolvedDeclarationStatus) {
            return f;
        }
        if ((f instanceof FirRegularClass) || (f instanceof FirTypeAlias)) {
            bodyResolveTransformerComponents.getContext().storeClassOrTypealiasIfNotNested(f, bodyResolveTransformerComponents.getSession());
        }
        LocalClassesNavigationInfo localClassesNavigationInfoCollectLocalClassesNavigationInfo = LocalClassesNavigationKt.collectLocalClassesNavigationInfo(f);
        for (Map.Entry<FirClassLikeDeclaration, FirClassLikeDeclaration> entry : localClassesNavigationInfoCollectLocalClassesNavigationInfo.getParentForClass().entrySet()) {
            FirClassLikeDeclaration key = entry.getKey();
            FirClassLikeDeclaration value = entry.getValue();
            if (value != null) {
                bodyResolveTransformerComponents.getContext().getOuterLocalClassForNested().put(key.getSymbol(), value.getSymbol());
            }
        }
        FirCompilerRequiredAnnotationsResolveTransformerKt.runCompilerRequiredAnnotationsResolvePhaseForLocalClass(f, bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getScopeSession(), localClassesNavigationInfoCollectLocalClassesNavigationInfo, bodyResolveTransformerComponents.getFile(), bodyResolveTransformerComponents.getContainingDeclarations(), bodyResolveTransformerComponents.getContext());
        FirCompanionGenerationProcessorKt.runCompanionGenerationPhaseForLocalClass(f, bodyResolveTransformerComponents.getSession());
        FirSupertypesResolutionKt.runSupertypeResolvePhaseForLocalClass(f, bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getScopeSession(), BodyResolveComponentsKt.createCurrentScopeList(bodyResolveTransformerComponents), localClassesNavigationInfoCollectLocalClassesNavigationInfo, bodyResolveTransformerComponents.getFile(), bodyResolveTransformerComponents.getContainingDeclarations());
        FirTypeResolveTransformerKt.runTypeResolvePhaseForLocalClass(f, bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getScopeSession(), BodyResolveComponentsKt.createCurrentScopeList(bodyResolveTransformerComponents), bodyResolveTransformerComponents.getFile(), bodyResolveTransformerComponents.getContainingDeclarations());
        FirStatusResolveTransformerKt.runStatusResolveForLocalClass(f, bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getScopeSession(), localClassesNavigationInfoCollectLocalClassesNavigationInfo);
        FirImplicitBodyResolveKt.runContractAndBodiesResolutionForLocalClass(f, bodyResolveTransformerComponents, resolutionMode, localClassesNavigationInfoCollectLocalClassesNavigationInfo);
        return f;
    }
}
