package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H'b\u0002\b\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirAbstractProviderBasedScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "lookupInFir", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "provider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "getProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractProviderBasedScope extends FirScope {
    private final FirSymbolProvider provider;
    private final FirSession session;

    public FirAbstractProviderBasedScope(FirSession firSession, boolean z) {
        firSession.getClass();
        this.session = firSession;
        this.provider = z ? FirSymbolProviderKt.getSymbolProvider(firSession) : FirSymbolProviderKt.getDependenciesSymbolProvider(firSession);
    }

    public final FirSymbolProvider getProvider() {
        return this.provider;
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public abstract FirAbstractProviderBasedScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession);

    public /* synthetic */ FirAbstractProviderBasedScope(FirSession firSession, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, (i & 2) != 0 ? true : z);
    }
}
