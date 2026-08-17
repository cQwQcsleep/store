package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B#\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0002\u0013\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirResolveProcessor;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getPhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "beforePhase", Argument.Delimiters.none, "afterPhase", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirGlobalResolveProcessor;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirTransformerBasedResolveProcessor;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirResolveProcessor implements SessionAndScopeSessionHolder {
    private final FirResolvePhase phase;
    private final ScopeSession scopeSession;
    private final FirSession session;

    private FirResolveProcessor(FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase) {
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.phase = firResolvePhase;
    }

    public void afterPhase() {
        if (this.phase != null) {
            FirLazyDeclarationResolverKt.getLazyDeclarationResolver(getSession()).finishResolvingPhase(this.phase);
        }
    }

    public void beforePhase() {
        if (this.phase != null) {
            FirLazyDeclarationResolverKt.getLazyDeclarationResolver(getSession()).startResolvingPhase(this.phase);
        }
    }

    public final FirResolvePhase getPhase() {
        return this.phase;
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    public /* synthetic */ FirResolveProcessor(FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, firResolvePhase);
    }
}
