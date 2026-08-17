package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ClassQualifierReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/QualifierReceiver;", "explicitReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "originalSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getOriginalSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getUseSiteSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "callableScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "classifierScope", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassQualifierReceiver extends QualifierReceiver {
    private final FirRegularClassSymbol classSymbol;
    private final FirClassLikeSymbol<?> originalSymbol;
    private final ScopeSession scopeSession;
    private final FirSession useSiteSession;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassQualifierReceiver(FirResolvedQualifier firResolvedQualifier, FirRegularClassSymbol firRegularClassSymbol, FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession, ScopeSession scopeSession) {
        super(firResolvedQualifier);
        firResolvedQualifier.getClass();
        firRegularClassSymbol.getClass();
        firClassLikeSymbol.getClass();
        firSession.getClass();
        scopeSession.getClass();
        this.classSymbol = firRegularClassSymbol;
        this.originalSymbol = firClassLikeSymbol;
        this.useSiteSession = firSession;
        this.scopeSession = scopeSession;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.QualifierReceiver
    public FirScope callableScope() {
        FirClass firClass = (FirRegularClass) this.classSymbol.getFir();
        return firClass.getScopeProvider().getStaticCallableMemberScope(firClass, this.useSiteSession, this.scopeSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.QualifierReceiver
    public FirScope classifierScope() {
        FirClass firClass = (FirRegularClass) this.classSymbol.getFir();
        return firClass.getScopeProvider().getNestedClassifierScope(firClass, this.useSiteSession, this.scopeSession);
    }

    public final FirRegularClassSymbol getClassSymbol() {
        return this.classSymbol;
    }

    public final FirClassLikeSymbol<?> getOriginalSymbol() {
        return this.originalSymbol;
    }

    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public final FirSession getUseSiteSession() {
        return this.useSiteSession;
    }
}
