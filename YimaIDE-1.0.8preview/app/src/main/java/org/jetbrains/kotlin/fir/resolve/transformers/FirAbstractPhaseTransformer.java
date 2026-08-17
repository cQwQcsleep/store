package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\rH\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirAbstractPhaseTransformer;", "D", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultTransformer;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "baseTransformerPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "getBaseTransformerPhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "transformerPhase", "getTransformerPhase", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "data", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "checkSessionConsistency", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractPhaseTransformer<D> extends FirDefaultTransformer<D> implements SessionHolder {
    private final FirResolvePhase baseTransformerPhase;

    public FirAbstractPhaseTransformer(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        this.baseTransformerPhase = firResolvePhase;
        FirResolvePhase.Companion companion = FirResolvePhase.INSTANCE;
    }

    public final void checkSessionConsistency(FirFile file) {
        file.getClass();
        getSession();
        file.getModuleData().getSession();
    }

    public final FirResolvePhase getBaseTransformerPhase() {
        return this.baseTransformerPhase;
    }

    public FirResolvePhase getTransformerPhase() {
        return this.baseTransformerPhase;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, D data) {
        file.getClass();
        checkSessionConsistency(file);
        try {
            return super.transformFile(file, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
            wq6.a();
            return null;
        }
    }
}
