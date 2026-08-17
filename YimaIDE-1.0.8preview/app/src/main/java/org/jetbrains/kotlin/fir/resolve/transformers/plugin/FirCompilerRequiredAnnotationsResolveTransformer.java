package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirCompilerRequiredAnnotationsResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirCompilerRequiredAnnotationsResolveTransformer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "computationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;)V", "annotationTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer;", "getAnnotationTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/AbstractFirSpecificAnnotationResolveTransformer;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirCompilerRequiredAnnotationsResolveTransformer extends AbstractFirCompilerRequiredAnnotationsResolveTransformer {
    private final AbstractFirSpecificAnnotationResolveTransformer annotationTransformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirCompilerRequiredAnnotationsResolveTransformer(FirSession firSession, ScopeSession scopeSession, CompilerRequiredAnnotationsComputationSession compilerRequiredAnnotationsComputationSession) {
        super(firSession, compilerRequiredAnnotationsComputationSession);
        firSession.getClass();
        scopeSession.getClass();
        compilerRequiredAnnotationsComputationSession.getClass();
        this.annotationTransformer = new FirSpecificAnnotationResolveTransformer(firSession, scopeSession, compilerRequiredAnnotationsComputationSession);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.plugin.AbstractFirCompilerRequiredAnnotationsResolveTransformer
    public AbstractFirSpecificAnnotationResolveTransformer getAnnotationTransformer() {
        return this.annotationTransformer;
    }
}
