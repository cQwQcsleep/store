package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.plugin.CompilerRequiredAnnotationsComputationSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirImplementationDetail
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J.\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fH\u0016Ê\u0001\u0002\b\u0012¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirCliJumpingPhaseComputationSessionForLocalClassesProvider;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirJumpingPhaseComputationSessionForLocalClassesProvider;", "<init>", "()V", "compilerRequiredAnnotationPhaseSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;", "superTypesPhaseSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationSession;", "statusPhaseSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "useSiteScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "designationMapForLocalClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "org.jetbrains.kotlin:resolve", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCliJumpingPhaseComputationSessionForLocalClassesProvider extends FirJumpingPhaseComputationSessionForLocalClassesProvider {
    public static final FirCliJumpingPhaseComputationSessionForLocalClassesProvider INSTANCE = new FirCliJumpingPhaseComputationSessionForLocalClassesProvider();

    private FirCliJumpingPhaseComputationSessionForLocalClassesProvider() {
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirJumpingPhaseComputationSessionForLocalClassesProvider
    public CompilerRequiredAnnotationsComputationSession compilerRequiredAnnotationPhaseSession() {
        return new CompilerRequiredAnnotationsComputationSession();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirJumpingPhaseComputationSessionForLocalClassesProvider
    public StatusComputationSession statusPhaseSession(FirSession useSiteSession, ScopeSession useSiteScopeSession, Map<FirClassLikeDeclaration, ? extends FirClassLikeDeclaration> designationMapForLocalClasses) {
        useSiteSession.getClass();
        useSiteScopeSession.getClass();
        designationMapForLocalClasses.getClass();
        return new StatusComputationSession(useSiteSession, useSiteScopeSession, designationMapForLocalClasses);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirJumpingPhaseComputationSessionForLocalClassesProvider
    public SupertypeComputationSession superTypesPhaseSession() {
        return new SupertypeComputationSession();
    }
}
