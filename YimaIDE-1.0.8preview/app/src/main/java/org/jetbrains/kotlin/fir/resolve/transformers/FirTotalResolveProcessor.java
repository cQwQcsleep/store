package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirTotalResolveProcessor;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "processors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirResolveProcessor;", "process", Argument.Delimiters.none, "files", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTotalResolveProcessor {
    private final List<FirResolveProcessor> processors;
    private final ScopeSession scopeSession;
    private final FirSession session;

    public FirTotalResolveProcessor(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        ScopeSession scopeSession = new ScopeSession();
        this.scopeSession = scopeSession;
        this.processors = FirTotalResolveProcessorKt.createAllCompilerResolveProcessors(firSession, scopeSession);
    }

    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public final void process(List<? extends FirFile> files) {
        files.getClass();
        for (FirResolveProcessor firResolveProcessor : this.processors) {
            firResolveProcessor.beforePhase();
            try {
                if (firResolveProcessor instanceof FirTransformerBasedResolveProcessor) {
                    for (FirFile firFile : files) {
                        try {
                            ((FirTransformerBasedResolveProcessor) firResolveProcessor).processFile(firFile);
                            Unit unit = Unit.INSTANCE;
                        } catch (Throwable th) {
                            UtilsKt.getExceptionHandler(firFile.getModuleData().getSession()).handleExceptionOnFileAnalysis(firFile, th);
                            throw new KotlinNothingValueException();
                        }
                    }
                } else {
                    if (!(firResolveProcessor instanceof FirGlobalResolveProcessor)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    ((FirGlobalResolveProcessor) firResolveProcessor).process(files);
                }
                firResolveProcessor.afterPhase();
            } catch (Throwable th2) {
                firResolveProcessor.afterPhase();
                throw th2;
            }
        }
    }
}
