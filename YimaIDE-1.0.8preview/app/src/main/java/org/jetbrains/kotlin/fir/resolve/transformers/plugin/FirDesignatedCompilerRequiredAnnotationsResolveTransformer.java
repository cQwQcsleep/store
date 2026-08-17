package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.DesignationState;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirDesignatedCompilerRequiredAnnotationsResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirCompilerRequiredAnnotationsResolveTransformer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "computationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;", "designation", "Lorg/jetbrains/kotlin/fir/resolve/transformers/DesignationState;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/DesignationState;)V", "annotationTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer;", "getAnnotationTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDesignatedCompilerRequiredAnnotationsResolveTransformer extends AbstractFirCompilerRequiredAnnotationsResolveTransformer {
    private final AbstractFirSpecificAnnotationResolveTransformer annotationTransformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDesignatedCompilerRequiredAnnotationsResolveTransformer(FirSession firSession, ScopeSession scopeSession, CompilerRequiredAnnotationsComputationSession compilerRequiredAnnotationsComputationSession, DesignationState designationState) {
        super(firSession, compilerRequiredAnnotationsComputationSession);
        firSession.getClass();
        scopeSession.getClass();
        compilerRequiredAnnotationsComputationSession.getClass();
        designationState.getClass();
        this.annotationTransformer = new FirDesignatedSpecificAnnotationResolveTransformer(firSession, scopeSession, compilerRequiredAnnotationsComputationSession, designationState);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.plugin.AbstractFirCompilerRequiredAnnotationsResolveTransformer
    public AbstractFirSpecificAnnotationResolveTransformer getAnnotationTransformer() {
        return this.annotationTransformer;
    }
}
