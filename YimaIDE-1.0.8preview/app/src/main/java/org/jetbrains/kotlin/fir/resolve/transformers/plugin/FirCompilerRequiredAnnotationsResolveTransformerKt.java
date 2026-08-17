package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.FirJumpingPhaseComputationSessionForLocalClassesProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.LocalClassesNavigationInfo;
import org.jetbrains.kotlin.fir.scopes.ImportingScopesKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aO\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"runCompilerRequiredAnnotationsResolvePhaseForLocalClass", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "localClassesNavigationInfo", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "bodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/util/List;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;)Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCompilerRequiredAnnotationsResolveTransformerKt {
    public static final <F extends FirClassLikeDeclaration> F runCompilerRequiredAnnotationsResolvePhaseForLocalClass(F f, FirSession firSession, ScopeSession scopeSession, LocalClassesNavigationInfo localClassesNavigationInfo, FirFile firFile, List<? extends FirDeclaration> list, BodyResolveContext bodyResolveContext) {
        f.getClass();
        firSession.getClass();
        scopeSession.getClass();
        localClassesNavigationInfo.getClass();
        firFile.getClass();
        list.getClass();
        bodyResolveContext.getClass();
        FirSpecificAnnotationForLocalClassesResolveTransformer firSpecificAnnotationForLocalClassesResolveTransformer = new FirSpecificAnnotationForLocalClassesResolveTransformer(firSession, scopeSession, FirJumpingPhaseComputationSessionForLocalClassesProviderKt.getJumpingPhaseComputationSessionForLocalClassesProvider(firSession).compilerRequiredAnnotationPhaseSession(), list, localClassesNavigationInfo, bodyResolveContext);
        firSpecificAnnotationForLocalClassesResolveTransformer.setScopes(ImportingScopesKt.createImportingScopes(firFile, firSpecificAnnotationForLocalClassesResolveTransformer.getSession(), firSpecificAnnotationForLocalClassesResolveTransformer.getScopeSession(), firSpecificAnnotationForLocalClassesResolveTransformer.getComputationSession().getUseCacheForImportScope()));
        return (F) FirTransformerUtilKt.transformSingle(f, firSpecificAnnotationForLocalClassesResolveTransformer, null);
    }
}
