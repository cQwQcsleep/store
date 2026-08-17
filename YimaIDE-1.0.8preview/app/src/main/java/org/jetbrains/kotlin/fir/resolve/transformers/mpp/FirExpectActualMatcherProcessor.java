package org.jetbrains.kotlin.fir.resolve.transformers.mpp;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.FirTransformerBasedResolveProcessor;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/mpp/FirExpectActualMatcherProcessor;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirTransformerBasedResolveProcessor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "enabled", Argument.Delimiters.none, "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", Argument.Delimiters.none, "getTransformer", "()Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "processFile", Argument.Delimiters.none, "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectActualMatcherProcessor extends FirTransformerBasedResolveProcessor {
    private final boolean enabled;
    private final FirTransformer transformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirExpectActualMatcherProcessor(FirSession firSession, ScopeSession scopeSession) {
        super(firSession, scopeSession, FirResolvePhase.EXPECT_ACTUAL_MATCHING);
        firSession.getClass();
        scopeSession.getClass();
        this.enabled = LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.MultiPlatformProjects);
        this.transformer = new FirExpectActualMatcherTransformer(firSession, scopeSession);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirTransformerBasedResolveProcessor
    public FirTransformer getTransformer() {
        return this.transformer;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirTransformerBasedResolveProcessor
    public void processFile(FirFile file) {
        file.getClass();
        if (this.enabled) {
            super.processFile(file);
        }
    }
}
