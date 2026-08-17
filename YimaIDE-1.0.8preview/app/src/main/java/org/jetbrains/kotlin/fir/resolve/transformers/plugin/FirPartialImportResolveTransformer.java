package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.extensions.FirRegisteredPluginAnnotationsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirImportResolveTransformer;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\u00020\f*\u00020\n8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirPartialImportResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirImportResolveTransformer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "computationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/CompilerRequiredAnnotationsComputationSession;)V", "acceptableFqNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "isAcceptable", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/name/FqName;)Z", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "data", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirPartialImportResolveTransformer extends FirImportResolveTransformer {
    private final Set<FqName> acceptableFqNames;
    private final CompilerRequiredAnnotationsComputationSession computationSession;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirPartialImportResolveTransformer(FirSession firSession, CompilerRequiredAnnotationsComputationSession compilerRequiredAnnotationsComputationSession) {
        super(firSession, FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS);
        firSession.getClass();
        compilerRequiredAnnotationsComputationSession.getClass();
        this.computationSession = compilerRequiredAnnotationsComputationSession;
        this.acceptableFqNames = FirRegisteredPluginAnnotationsKt.getRegisteredPluginAnnotations(firSession).getAnnotations();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirImportResolveTransformer
    public boolean isAcceptable(FqName fqName) {
        fqName.getClass();
        return this.acceptableFqNames.contains(fqName);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirImportResolveTransformer, org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractPhaseTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, Object data) {
        file.getClass();
        if (this.computationSession.importsAreResolved(file)) {
            return file;
        }
        FirFile firFileTransformFile = super.transformFile(file, data);
        this.computationSession.recordThatImportsAreResolved(file);
        return firFileTransformFile;
    }
}
