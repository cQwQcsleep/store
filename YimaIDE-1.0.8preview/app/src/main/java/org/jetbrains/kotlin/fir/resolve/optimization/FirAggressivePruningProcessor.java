package org.jetbrains.kotlin.fir.resolve.optimization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.transformers.FirTransformerBasedResolveProcessor;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirBodyResolveProcessor;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/optimization/FirAggressivePruningProcessor;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirTransformerBasedResolveProcessor;", "delegate", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirBodyResolveProcessor;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirBodyResolveProcessor;)V", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "getTransformer", "()Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "processFile", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "beforePhase", "afterPhase", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAggressivePruningProcessor extends FirTransformerBasedResolveProcessor {
    private final FirBodyResolveProcessor delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAggressivePruningProcessor(FirBodyResolveProcessor firBodyResolveProcessor) {
        super(firBodyResolveProcessor.getSession(), firBodyResolveProcessor.getScopeSession(), FirResolvePhase.BODY_RESOLVE);
        firBodyResolveProcessor.getClass();
        this.delegate = firBodyResolveProcessor;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirResolveProcessor
    public void afterPhase() {
        this.delegate.afterPhase();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirResolveProcessor
    public void beforePhase() {
        this.delegate.beforePhase();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirTransformerBasedResolveProcessor
    public FirTransformer getTransformer() {
        return this.delegate.getTransformer();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirTransformerBasedResolveProcessor
    public void processFile(FirFile file) {
        file.getClass();
        this.delegate.processFile(file);
        file.transform(new FirPruningTransformer(new FirReachabilityAnalyzer(this.delegate.getSession()).collectReachableSymbols(file)), null);
    }
}
